package rs.ac.bg.etf.pp1;


import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.FormPars;
import rs.ac.bg.etf.pp1.ast.MultipleFormPars;
import rs.ac.bg.etf.pp1.ast.SingleFormPar;
import rs.ac.bg.etf.pp1.ast.SingleFormParam;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VarDecls;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	protected int count;
	
	public int getCount(){
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor{
	
		public void visit(SingleFormParam formParamDecl){
			count++;
		}
		
	}
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(VarDecls varDecl){
			count++;
		}
	}

}
