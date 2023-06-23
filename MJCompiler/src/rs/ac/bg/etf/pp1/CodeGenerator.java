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
	
	public int getMainPc(){
		return mainPc;
	}
	
	public void visit(PrintStmt printStmt){
		if(printStmt.getPrintExpr().getExpr().struct == Tab.intType){
			Code.loadConst(5);
			Code.put(Code.print);
			log.info("uso u print");
		}else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(FactNum cnst){
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getN1());
		log.info(cnst.getN1());
		Code.load(con);
		log.info("uso u print");
	}
	
	public void visit(TypeMethod methodTypeName){
		
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
		log.info("uso u print");
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
		log.info("uso u print");
	}
	
	public void visit(MethodDecls methodDecl){
		log.info("uso u print");
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

}
