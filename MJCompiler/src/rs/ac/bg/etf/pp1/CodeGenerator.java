package rs.ac.bg.etf.pp1;


import rs.etf.pp1.mj.runtime.*;
import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;

import rs.etf.pp1.symboltable.*;

public class CodeGenerator extends VisitorAdaptor {
	Logger log = Logger.getLogger(getClass());
	private int mainPc;
	private boolean flagReturn = false;
	
	Struct boolType = new Struct(Struct.Bool);
	
	public int getMainPc(){
		return mainPc;
	}
	
	public void visit(PrintStmt printStmt){
		if(printStmt.getPrintExpr().getExpr().struct == Tab.charType){
			Code.put(Code.bprint);
		}else{
			Code.put(Code.print);
		}
	}
	
	public void visit(StmtCnst stmtConst) {
		Code.loadConst(stmtConst.getNum());
	}
	
	public void visit(NoStmtCnst stmtConst) {
		if(((PrintStmt) stmtConst.getParent()).getPrintExpr().struct == Tab.charType ){			
			Code.loadConst(1);
		}else{
			Code.loadConst(5);
		}
	}
	
	public void visit(FactNum cnst){
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getN1());
		Code.load(con);
	}
	
	public void visit(TypeMethod methodTypeName){
		
		
		methodTypeName.obj = Tab.find(methodTypeName.getMethodName());
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(VoidMethod methodTypeName){
		
		if("main".equalsIgnoreCase(methodTypeName.getMethodName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj = Tab.find(methodTypeName.getMethodName());
		
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethodDecls methodDecl){
		
		if ( methodDecl.getMethodDecl().obj.getType() != Tab.noType && !flagReturn) {
			Code.put(Code.trap);
			Code.put(1);
			return;
		} 
		
		if (flagReturn) {
			flagReturn = false;
			return;
		}
		
		
		
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignAssign designator) {
		Code.store(designator.getDesignator().obj);
	}
	
	public void visit(DesignInc designator) {
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designator.getDesignator().obj);
	}
	
	public void visit(DesignDec designator) {
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designator.getDesignator().obj);
	}	
	
	public void visit(SingleDesignIdent designator){
		SyntaxNode parent = designator.getParent();
		
		if(DesignAssign.class != parent.getClass() && FactFunc.class != parent.getClass()){
			Code.load(designator.obj);
		}
	}
	
	public void visit(FactFunc funcCall){
		Obj functionObj = funcCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	public void visit(ReturnExpr returnExpr){
		flagReturn = true;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnNoExpr returnExpr){
		flagReturn = true;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MultipleExpr AddExpt) {
		if (AddExpt.getAddop() instanceof Plus) {
			Code.put(Code.add);
		} else if (AddExpt.getAddop() instanceof Minus) {
			Code.put(Code.sub);
		}
		
	}
	
	public void visit(ReadStmt readStmt) {		
		if (readStmt.getDesignator().obj.getType() == Tab.intType 
				|| readStmt.getDesignator().obj.getType() == boolType ) {
			Code.put(Code.read);
		} else {
			Code.put(Code.bread);
		}
		
		Code.store(readStmt.getDesignator().obj);
	}
	
	public void visit(FactChar factChar) {
		Code.loadConst(((int) factChar.getC1()));
	}
	
	
	public void visit(FactBoolean factBoolean) {
		if (factBoolean.getB1() == true) {
			Code.loadConst(1);
		} else {
			Code.loadConst(0);
		}
	}
	
	
	public void visit(FactConstrArray factArray) {
		Code.put(Code.newarray);
		if (factArray.struct.getElemType() == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
	
	Obj rows = new Obj(Obj.Var, "rows", Tab.intType);
	Obj columns = new Obj(Obj.Var, "columns", Tab.intType );
	
	public void visit(FactConstrMatrix factMatrix) {
		Code.put(Code.pop);
		Code.store(columns);
		Code.put(Code.pop);
		Code.store(rows);
		log.info(rows.getAdr());
		
		Code.put2(rows.getAdr());
		
		Code.put(Code.newarray);
		Code.put(1);
		Code.put(Code.newarray);
		if (factMatrix.getType().struct == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
	
	public void visit(NegativeExpr expr) {
		Code.put(Code.neg);
	}
	
	public void visit(MultipleTerms term) {
		if (term.getMulop() instanceof Asterisk) {
			Code.put(Code.mul);
		} else if (term.getMulop() instanceof Slash) {
			Code.put(Code.div);
		} else if (term.getMulop() instanceof Percent) {
			Code.put(Code.rem);
		}
	}
	
	public void visit(DesignatorName array) {
		Code.load(array.obj);
		//Code.load(array.getExpr())
		//array.obj = array.getDesignatorName().obj;
	}
	
	public void visit(FactVar factVar) {
		Code.load(factVar.getDesignator().obj);
	}
	
	public void visit(DummyMatrix dummyMatrix) {
		Code.put(Code.aload);
	}
	
	

}
