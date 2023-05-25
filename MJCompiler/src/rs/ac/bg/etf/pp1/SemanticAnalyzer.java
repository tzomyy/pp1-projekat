package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;

import rs.etf.pp1.symboltable.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	Logger log = Logger.getLogger(getClass());
	boolean errorDetected = false;
	boolean arrayType = false;
	boolean matrixType = false;
	boolean hasReturn = false;
	int depthWhile = 0;

	Obj currMethod = null;
	Struct currType = null;
	Struct boolType = new Struct(Struct.Bool);

	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public boolean passed() {
		return !errorDetected;
	}

	public void visit(ProgName progName) {

		// ubaci se objektni cvor u tabelu simbola i otvori se opseg
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {

		if (Tab.find("main") == Tab.noObj) {
			report_error("Nije definisana main funkcija!", null);
		} else {
			report_error("Definisana je main funkcija!", null);
		}

		// uvezuju se simboli sa ospegom iznad i zatvara se opseg
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());

		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola!", null);
			type.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
		currType = type.struct;
	}

	public void visit(BoolConstDecl boolConstDecl) {

		if (Tab.find(boolConstDecl.getConstName()) != Tab.noObj) {
			report_error("Greska: Ime " + boolConstDecl.getConstName() + " vec postoji u tabeli simbola!",
					boolConstDecl);
			return;
		} else {
			if (!boolType.equals(currType)) {
				report_error("Greska: Tip konstante " + boolConstDecl.getConstName()
						+ " nije kompatibilan sa vrednoscu konstante!", boolConstDecl);
				return;
			} else {
				report_info("Naisla na bool const", null);
				int boolValue;
				if (boolConstDecl.getBooleanConst() == true) {
					boolValue = 1;
				} else {
					boolValue = 0;
				}
				Obj boolNode = Tab.insert(Obj.Con, boolConstDecl.getConstName(), boolType);
				boolNode.setAdr(boolValue);
			}
		}
	}

	public void visit(IntegerConstDecl integerConstDecl) {

		if (Tab.find(integerConstDecl.getConstName()) != Tab.noObj) {
			report_error("Greska: Ime " + integerConstDecl.getConstName() + " vec postoji u tabeli simbola!",
					integerConstDecl);
			return;
		} else {
			if (!Tab.intType.equals(currType)) {
				report_error("Greska: Tip konstante " + integerConstDecl.getConstName()
						+ " nije kompatibilan sa vrednoscu konstante!", integerConstDecl);
				return;
			} else {
				report_info("Naisla na intr const", null);

				Obj intNode = Tab.insert(Obj.Con, integerConstDecl.getConstName(), Tab.intType);
				intNode.setAdr(integerConstDecl.getNumberConst());
			}
		}		
	}

	public void visit(CharConstDecl charConstDecl) {

		if (Tab.find(charConstDecl.getConstName()) != Tab.noObj) {
			report_error("Greska: Ime " + charConstDecl.getConstName() + " vec postoji u tabeli simbola!",
					charConstDecl);
			return;
		} else {
			if (!Tab.charType.equals(currType)) {
				report_error("Greska: Tip konstante " + charConstDecl.getConstName()
						+ " nije kompatibilan sa vrednoscu konstante!", charConstDecl);
				return;
			} else {
				report_info("Naisla na char const", null);

				Obj intNode = Tab.insert(Obj.Con, charConstDecl.getConstName(), Tab.charType);
				intNode.setAdr(charConstDecl.getCharConst());
			}
		}
	}

	public void visit(MatrixBrackets brackets) {
		this.matrixType = true;
	}

	public void visit(ArrayBrackets brackets) {
		this.arrayType = true;
	}

	public void visit(EndOfVarDeclar varDecl) {
		// treba proveriti da li se takav simbol nalazi u tabeli simbola, tj. da li se
		// nalazi u tom opsegu vazenja
		// ako ne postoji potrebno je dodati ga

		if ((Tab.find(varDecl.getVarName())) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(varDecl.getVarName()) != null) {
				report_error("Greska: Ime " + varDecl.getVarName() + " vec postoji u tabeli simbola i u tom opsegu!",
						varDecl);
			} else {
				if (this.matrixType) {
					Struct arrayType = new Struct(Struct.Array, currType);
					Struct matrix = new Struct(Struct.Array, arrayType);

					Tab.insert(Obj.Var, varDecl.getVarName(), matrix);
				} else if (this.arrayType) {
					
					Struct arrayType = new Struct(Struct.Array, currType);
					Tab.insert(Obj.Var, varDecl.getVarName(), arrayType);

				} else {
					Tab.insert(Obj.Var, varDecl.getVarName(), currType);

				}
			}
		} else {
			if (this.matrixType) {
				Struct arrayType = new Struct(Struct.Array, currType);
				Struct matrix = new Struct(Struct.Array, arrayType);
				Tab.insert(Obj.Var, varDecl.getVarName(), matrix);
			} else if (this.arrayType) {

				Struct arrayType = new Struct(Struct.Array, currType);
				Tab.insert(Obj.Var, varDecl.getVarName(), arrayType);

			} else {
				Tab.insert(Obj.Var, varDecl.getVarName(), currType);

			}
		}

		if (this.matrixType == true) {
			this.matrixType = false;
		}

		if (this.arrayType == true) {
			this.arrayType = false;
		}
	}

	public void visit(MoreVarDecls varDecl) {
		

		if ((Tab.find(varDecl.getVarName())) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(varDecl.getVarName()) != null) {
				report_error("Greska: Ime " + varDecl.getVarName() + " vec postoji u tabeli simbola!", varDecl);
				return;
			} else {
				if (this.matrixType) {
					
					Struct arrayType = new Struct(Struct.Array, currType);
					Struct matrix = new Struct(Struct.Array, arrayType);
					Tab.insert(Obj.Var, varDecl.getVarName(), matrix);
				} else if (this.arrayType) {

					Struct arrayType = new Struct(Struct.Array, currType);
					Tab.insert(Obj.Var, varDecl.getVarName(), arrayType);
					if(varDecl.getVarName() == "niz") {
					}

				} else {
					Tab.insert(Obj.Var, varDecl.getVarName(), currType);
					

				}
			}
		} else {
			if (this.matrixType) {

				Struct arrayType = new Struct(Struct.Array, currType);
				Struct matrix = new Struct(Struct.Array, arrayType);
				Tab.insert(Obj.Var, varDecl.getVarName(), matrix);

			} else if (this.arrayType) {
				Struct arrayType = new Struct(Struct.Array, currType);
				Tab.insert(Obj.Var, varDecl.getVarName(), arrayType);

			} else {

				Tab.insert(Obj.Var, varDecl.getVarName(), currType);

				Obj nesto = Tab.find(varDecl.getVarName());

			}
		}

		if (this.matrixType == true) {
			this.matrixType = false;
		}
		if (this.arrayType == true) {
			this.arrayType = false;
		}
	}

	public void visit(TypeMethod methDecl) {
		// proveriti da li se nalazi u tabeli simbola i da li se nalazi u tom opsegu
		// ako ne dodati ga
		// nakon dodavanja otvoriti opseg

		if (Tab.find(methDecl.getMethodName()) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(methDecl.getMethodName()) != null) {
				report_error("Greska: Simbol " + methDecl.getMethodName() + " je vec definisan u tabeli simbola",
						methDecl);
				return;
			} else {
				currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
			}
		} else {
			currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
		}

		methDecl.struct = currType;
		Tab.openScope();
	}

	public void visit(VoidMethod methDecl) {
		methDecl.struct = currType = Tab.noType;
		if (Tab.find(methDecl.getMethodName()) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(methDecl.getMethodName()) != null) {
				report_error("Greska: Simbol " + methDecl.getMethodName() + " je vec definisan u tabeli simbola",
						methDecl);
				return;
			} else {
				currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
			}
		} else {
			currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
		}

		Tab.openScope();
	}

	public void visit(ReturnExpr retExpr) {
		if (this.currMethod == null) {
			report_error("Return naredba se nalazi van funkcije!", retExpr);
		}

		this.hasReturn = true;
	}

	public void visit(MethodDecls methDecl) {
		if (!this.hasReturn && (currMethod.getType() != Tab.noType)) {
			report_error("Metoda " + this.currMethod.getName() + " nema povratnu vrednost u telu svoje funkcije",
					methDecl);
		}
		this.currMethod = null;
		this.hasReturn = false;
	}

	public void visit(WhileStart whileStart) {
		this.depthWhile++;
	}

	public void visit(WhileEnd whileEnd) {
		this.depthWhile--;
	}

	public void visit(BreakStmt breakStmt) {
		if (this.depthWhile == 0) {
			report_error("Break se nalazi van while petlje! ", breakStmt);
		}
	}

	public void visit(ContinueStmt breakStmt) {
		if (this.depthWhile == 0) {
			report_error("Continue se nalazi van while petlje! ", breakStmt);
		}
	}

	public void visit(PrintExpr printExpr) {

		if (currType != Tab.intType && currType != Tab.charType && currType != boolType) {
			report_error("Povratna vrednost izraza u print naredbi nije odgovarajuceg tipa!", printExpr);
		} else {
			report_info("Print", printExpr);
		}
	}

	// expr
	
	public void visit(SingleExpr expr) {
		expr.struct = expr.getTerm().struct;
	}

	public void visit(NegativeExpr expr) {
		if (expr.getTerm().struct != Tab.intType) {
			expr.struct = Tab.noType;
			report_error("Negativni izraz treba da bude tipa int", expr);
		} else {
			expr.struct = expr.getTerm().struct;
		}		
		
	}
	
	public void visit(MultipleExpr expr) {
		
		if (expr.getExpr().struct != Tab.intType || expr.getTerm().struct != Tab.intType) {
			report_error("Izrazi operanada treba da budu tipa int!", expr);
		}
		if (expr.getExpr().struct.compatibleWith(expr.getTerm().struct)) {
			expr.struct = expr.getExpr().struct;
		} else {
			expr.struct = Tab.noType;
			report_error("Tipovi operanada treba da budu kompatabilni!" , expr);
		}		
		
	}
	
	// term
	
	
	public void visit (SingleTerm term) {		
		term.struct = term.getFactor().struct;
	}
	
	public void visit (MultipleTerms term) {
		
		if (term.getTerm().struct != Tab.intType || term.getFactor().struct != Tab.intType) {
			report_error("Tip operanada treba da bude int! ", term);
		}
		term.struct = term.getTerm().struct;
	}

	// Setovanje tipa faktora
	
	public void visit(FactNum factor) {
		factor.struct = Tab.intType;
	}

	public void visit(FactChar factor) {
		factor.struct = Tab.charType;
	}

	public void visit(FactBoolean factor) {
		factor.struct = boolType;
	}

	public void visit(FactVar factor) {
		factor.struct = factor.getDesignator().obj.getType();
	}
	
	public void visit(FactConstrMatrix factor) {
		Struct arrayType = new Struct(Struct.Array, factor.getType().struct);
		Struct matrix = new Struct(Struct.Array, arrayType);
		factor.struct = matrix;
	}
	
	public void visit(FactConstrArray factor) {
		report_info(factor.getType().struct.getKind() + "", null);
		Struct arrayType = new Struct(Struct.Array, factor.getType().struct);
		factor.struct = arrayType;
	}
	
	// cond
	
	
	public void visit(SingleCondFact condition) {
		condition.struct = condition.getExpr().struct;
	}
	
	public void visit(CondFacts condition) {
		if (!condition.getCondFact().struct.compatibleWith(condition.getExpr().struct)) {
			report_error("Tipovi u uslovu nisu kompatabilni!", condition);
			return;
		}
		
		if (condition.getExpr().struct.getKind() == Struct.Array) {
			if (!(currOperator == Operator.IS_EQUAL || currOperator == Operator.NOT_EQUAL)) {
				report_error("Nizovi mogu korisititi != ili  == od relacionih operatora!", condition);
			}
		}
	}

	// designator

	public void visit(SingleDesignIdent designator) {
		Obj designNode = Tab.find(designator.getVar());

		if (designNode == Tab.noObj) {
			report_error("Varijabla " + designator.getVar() + " nije deklarisana! ", null);
		}
		designator.obj = designNode;
	}
	
	public void visit(MultipleDesignExpr designator) {
		
		Obj designNode = designator.getDesignator().obj;
		
		if (designNode.getType().getKind() != Struct.Array) {
			report_error("Promenljiva " + designator.getDesignator().obj.getName() + " nije niz!", designator);			
		}
		if (designator.getExpr().struct != Tab.intType) {
			report_error("Greska: Izraz bi trebalo da bude tipa int! ", designator);
		}
		
	}
	
	// operatori
	
	enum Operator{
		ADD, SUB,
		MUL, DIV, PROC,
		IS_EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL,
		EQUAL
	};
	
	Operator currOperator = null;
	
	public void visit(Assignop op) {
		currOperator = Operator.EQUAL;
	}
	
	public void visit(IsEqual op) {
		currOperator = Operator.IS_EQUAL;
	}
	
	public void visit(NotEqual op) {
		currOperator = Operator.NOT_EQUAL;
	}
	
	public void visit(Greater op) {
		currOperator = Operator.GREATER;
	}
	
	public void visit(GreaterEqual op) {
		currOperator = Operator.GREATER_EQUAL;
	}
	
	public void visit(Less op) {
		currOperator = Operator.LESS;
	}
	
	public void visit(LessEqual op) {
		currOperator = Operator.LESS_EQUAL;
	}
	
	public void visit(Plus op) {
		currOperator = Operator.ADD;
	}
	
	public void visit(Minus op) {
		currOperator = Operator.SUB;
	}
	
	public void visit(Asterisk op) {
		currOperator = Operator.MUL;
	}
	
	public void visit(Slash op) {
		currOperator = Operator.DIV;
	}
	
	public void visit(Percent op) {
		currOperator = Operator.PROC;
	}

}
