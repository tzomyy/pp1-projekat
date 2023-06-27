package rs.ac.bg.etf.pp1;


import rs.etf.pp1.mj.runtime.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
		if(((PrintStmt) stmtConst.getParent()).getPrintExpr().getExpr().struct == Tab.charType ){			
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
		log.info(fpCnt.getCount());
		
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
	
	public void visit(DesignAssign design) {
//		if (flagMatrix) {
//			Code.put4(this.designator.getAdr());
//			flagMatrix = false;
//
//		}
		Code.store(design.getDesignator().obj);
		
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
		
		if(!(parent instanceof DesignAssign) && !(parent instanceof FactFunc)){
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
	
	public void visit(MatrixDesignat matrixdesig) {
		//matrix
		
	}
	
	public void visit(ArrayDesignat arr) {
		if (arr.getParent().getParent() instanceof DesignAssign) {
			return;
		}
		if (arr.getDesignatorName().obj.getType().getElemType() == Tab.charType) {
			Code.put(Code.baload);
		} else {
			Code.put(Code.aload);
		}
	}
	
	public void visit(DummyMatrix dummy) {
		Code.put(Code.aload);
		/*Obj matrix = ((MatrixDesignat) dummy.getParent()).getDesignatorName().obj;
		Code.load(matrix);*/
		
	}
	
	public void visit(DummyMatrix2 dummy) {
		if (dummy.getParent().getParent().getParent() instanceof DesignAssign) return;
		Code.put(Code.aload);
		// dodati proveru za char tj baload
	}
	
	Obj designator = null;
	
	public void visit(Assignop dummy) {
		if (dummy.getParent() instanceof DesignAssign) {
			designator = ((DesignAssign) dummy.getParent()).getDesignator().obj;
		}
	}
	
	boolean flagMatrix = false;
	public void visit(FactConstrMatrix factMatrix) {
		flagMatrix = true;
		// imam rows i cols na steku
		Code.put(Code.dup2); // rows, cols, rows, cols
		Code.put(Code.pop);  // rows, cols, rows
		Code.put(Code.newarray);
		Code.put(1);             // rows, cols, adr1
		
		Code.put(Code.dup);
		Code.put(Code.dup); // rows, cols, adr1, adr1
		Code.store(designator);
		
		Code.put(Code.arraylength); // rows, cols, adr1, rows
		
		int whilepc = Code.pc;
		
		Code.put(Code.const_1); // rows, cols, adr1, rows, 1		
		//Code.put(1);
		Code.put(Code.sub); // rows, cols, adr1, index
		
		Code.put(Code.dup); // rows, cols, adr1, index, index
		Code.put(Code.const_1); 
		Code.put(Code.const_1);
		Code.put(Code.sub); // rows, cols, adr1, index, index, 0

		int fixuppc = Code.pc + 1;
		Code.putFalseJump(Code.ge, 0); // skok na kraj petlje ako uslov nije ispunjen
		
		Code.put(Code.dup_x2); // rows, index, cols, adr1, index
		Code.put(Code.pop); // rows, index, cols, adr1
		Code.put(Code.dup_x2); // rows, adr1, index, cols, adr1
		Code.put(Code.pop); // rows, adr1, index, cols
		Code.put(Code.dup_x2); // rows, cols, adr1, index, cols
		
		Code.put(Code.newarray);
		if (factMatrix.getType().struct == Tab.charType) {
			Code.put(0); 
		} else {
			Code.put(1);
		}  // rows, cols, adr1, index, adr2
		
		Code.put(Code.dup_x2);
		Code.put(Code.pop);   // rows, cols, adr2, adr1, index
		Code.put(Code.dup_x2);
		Code.put(Code.pop);   // rows, cols, index, adr2, adr1
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x2); // rows, cols, adr1, adr1, index, adr2, adr1
		Code.put(Code.pop);    // rows, cols, adr1, adr1, index, adr2
		Code.put(Code.dup_x2); 
		Code.put(Code.pop);    // rows, cols, adr1, adr2, adr1, index
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x2); // rows, cols, adr1, index, index, adr2, adr1, index
		Code.put(Code.pop);    // rows, cols, adr1, index, index, adr2, adr1
		Code.put(Code.dup_x2);
		Code.put(Code.pop);    // rows, cols, adr1, index, adr1, index, adr2
		
		Code.put(Code.astore);  // rows, cols, adr1, index
		
		Code.putJump(whilepc);
		
		Code.fixup(fixuppc);
		
		Code.put(Code.pop);
		Code.put(Code.dup_x2); 
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		// ostala adr1 na steku koja treba da se upise u obj od matrice
		
		//Code.put(Code.pop);
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
		//Code.load(factVar.getDesignator().obj);
	}
	
	Stack<List<Integer>> stackOfAnds = new Stack<>();
	Stack<List<Integer>> stackOfOrs = new Stack<>();
 	
	public void visit(OrDummy afterOr) {
		stackOfOrs.peek().add(Code.pc + 1);
    	Code.putJump(0);
    	
    	List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	stackOfAnds.push(new ArrayList<Integer>());
	}
	
	public void visit(AfterIFConditionDummy afterIf) {
		List<Integer> orList = stackOfOrs.pop();
		for (int i = 0; i < orList.size(); i++) {
    		int onecond = orList.get(i);
    		Code.fixup(onecond);
    	}
		stackOfOrs.push(new ArrayList<Integer>());
	}
	
	public void visit(MoreConds conds) {
		stackOfAnds.peek().add(Code.pc + 1);		
		
		Relop rel = conds.getRelop();
		int code = 0;
		if (rel instanceof IsEqual) code = Code.eq;
		if (rel instanceof NotEqual) code = Code.ne;
		if (rel instanceof Greater) code = Code.gt;
		if (rel instanceof GreaterEqual) code = Code.ge;
		if (rel instanceof Less) code = Code.lt;
		if (rel instanceof LessEqual) code = Code.le;
		Code.putFalseJump(code, 0);
	}
	
	public void visit(EndOfCond singleTermCond) {
		Code.loadConst(1);
		stackOfAnds.peek().add(Code.pc + 1);
    	Code.putFalseJump(Code.eq, 0);
	}
	
	Stack<List<Integer>> stackOfElses = new Stack<>();
	
	public void visit(DummyAfterElse elsedummy) {
		stackOfElses.peek().add(Code.pc + 1);
    	Code.putJump(0);
    	
    	List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	stackOfAnds.push(new ArrayList<Integer>());
	}
	
	public void visit(DummyBeforeIf ifdummy) {
		List<Integer> andlist = new ArrayList<>();
		stackOfAnds.add(andlist);
		List<Integer> orlist = new ArrayList<>();
		stackOfOrs.add(orlist);
		List<Integer> elselist = new ArrayList<>();
		stackOfElses.add(elselist);
	}
	
	public void visit(ElseStmt ifstatement) {
		List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	List<Integer> elseList = stackOfElses.pop();
    	for (int i = 0; i < elseList.size(); i++) {
    		int onecond = elseList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	stackOfOrs.pop();
	}
	
	public void visit(NoElseStmt ifstatement) {
		List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	List<Integer> elseList = stackOfElses.pop();
    	for (int i = 0; i < elseList.size(); i++) {
    		int onecond = elseList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	stackOfOrs.pop();
	}
	
	Stack<List<Integer>> stackOfBreaks = new Stack<>();
	Stack<Integer> stackOfLoops = new Stack<>();
	
	public void visit(WhileStart whilestart) {
		List<Integer> andlist = new ArrayList<>();
		stackOfAnds.add(andlist);
		List<Integer> orlist = new ArrayList<>();
		stackOfOrs.add(orlist);
		List<Integer> breaklist = new ArrayList<>();
		stackOfBreaks.add(breaklist);
		stackOfLoops.push(Code.pc);
	}
	
	public void visit(WhileStmt whilestatement) {
		int adr = stackOfLoops.pop();
		Code.putJump(adr);
		
		List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	List<Integer> breakList = stackOfBreaks.pop();
    	for (int i = 0; i < breakList.size(); i++) {
    		int onecond = breakList.get(i);
    		Code.fixup(onecond);
    	}
		
    	stackOfOrs.pop();
	}
	
	public void visit(ContinueStmt cont) {
		int adr = stackOfLoops.peek();
    	Code.putJump(adr);
    }
	

    public void visit(BreakStmt breakstatement) {
    	stackOfBreaks.peek().add(Code.pc + 1);
    	Code.putJump(0);
    }
    
    public void visit(DesignatorArr2 design) {
    	
    	Code.put(Code.pop);
    	Code.put(Code.pop);
    	
    	Obj arr = design.getDesignator().obj;
    	Code.load(arr);
    	Code.put(Code.arraylength);
    	Code.put(Code.newarray);
    	if(design.getDesignator().obj.getType().getElemType() == Tab.charType) {
    		Code.put(0);
    	} else {
    		Code.put(1);
    	}
    	
    	Code.store(((MapStmt)design.getParent()).getDesignator().obj);
    	//Code.load(arr);
    	Code.loadConst(-1);
      }
    
    int loopMap = -1;
    Stack<Integer> stackOfMap = new Stack<>();
    
    public void visit(MapIdent ident) {
   
  
    	this.loopMap = Code.pc;
    	Code.loadConst(1);
    	Code.put(Code.add); // ind
    	Code.put(Code.dup);
    	Code.put(Code.dup); // ind, ind ind
    	// fali provera za skok iz petlje
    	
    	Code.load(((MapStmt)ident.getParent()).getDesignator().obj); // ind ind ind adr
    	Code.put(Code.arraylength); // ind ind inde len
    	stackOfMap.add(Code.pc + 1);
    	Code.putFalseJump(Code.lt, 0); // ind ind
    	Code.load(((MapStmt)ident.getParent()).getDesignatorArr2().getDesignator().obj); // ind, ind, adr1
    	Code.put(Code.dup_x1); // ind adr ind adr
    	Code.put(Code.pop); // ind adr ind
    	if (ident.obj.getType() == Tab.charType) {
    		Code.put(Code.baload);
    	} else {
    		Code.put(Code.aload);
    	} // ind val
    	
    	Code.store(ident.obj);  // ind
    	
    }
    
    public void visit(DummyMap dummy) {
    	// ovde treba da smestim u novi niz i da se vratim u petlju
    	
    	// na steku je ind val
    	
    	Code.put(Code.dup2);
    	Code.put(Code.pop); // ind val ind
    	Code.put(Code.dup_x1);
    	Code.put(Code.pop); // ind ind val
    	
    	Code.load(((MapStmt)dummy.getParent()).getDesignator().obj); // ind, ind  val adr2
    	Code.put(Code.dup_x2);
    	Code.put(Code.pop); //  ind adr2 ind val 
    	if (((MapStmt)dummy.getParent()).getDesignator().obj.getType() == Tab.charType) {
    		Code.put(Code.bastore);
    	} else {
    		Code.put(Code.astore);
    	} // ind
    	
    	
    	Code.putJump(loopMap);    	
    }
    
    public void visit(MapEnd map) {
    	for (int x: stackOfMap) {
    		Code.fixup(x);
    	}
    }

}
