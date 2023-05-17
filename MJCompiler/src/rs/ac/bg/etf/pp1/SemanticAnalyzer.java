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

					Tab.insert(Obj.Var, varDecl.getVarName() + "[][]", matrix);
				} else if (this.arrayType) {

					Struct arrayType = new Struct(Struct.Array, currType);
					Tab.insert(Obj.Var, varDecl.getVarName() + "[]", arrayType);

				} else {
					Tab.insert(Obj.Var, varDecl.getVarName(), currType);
				}
			}
		} else {
			if (this.matrixType) {
				Struct arrayType = new Struct(Struct.Array, currType);
				Struct matrix = new Struct(Struct.Array, arrayType);
				Tab.insert(Obj.Var, varDecl.getVarName() + "[][]", matrix);
			} else if (this.arrayType) {

				Struct arrayType = new Struct(Struct.Array, currType);
				Tab.insert(Obj.Var, varDecl.getVarName() + "[]", arrayType);

			} else {
				Tab.insert(Obj.Var, varDecl.getVarName(), currType);
			}
		}

		if (this.matrixType == true) {
			this.matrixType = false;
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
					Tab.insert(Obj.Var, varDecl.getVarName() + "[][]", matrix);
				} else if (this.arrayType) {

					Struct arrayType = new Struct(Struct.Array, currType);
					Tab.insert(Obj.Var, varDecl.getVarName() + "[]", arrayType);

				} else {
					Tab.insert(Obj.Var, varDecl.getVarName(), currType);
				}
			}
		} else {
			if (this.matrixType) {
				
				Struct arrayType = new Struct(Struct.Array, currType);
				Struct matrix = new Struct(Struct.Array, arrayType);
				Tab.insert(Obj.Var, varDecl.getVarName() + "[][]", matrix);
				
			} else if (this.arrayType) {

				Struct arrayType = new Struct(Struct.Array, currType);
				Tab.insert(Obj.Var, varDecl.getVarName() + "[]", arrayType);

			} else {
				
				Tab.insert(Obj.Var, varDecl.getVarName(), currType);
				
			}
		}

		if (this.matrixType == true) {
			this.matrixType = false;
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
		this.hasReturn = true;
	}

	public void visit(MethodDecls methDecl) {
		if (!this.hasReturn && (currMethod.getType() != Tab.noType)) {
			report_error("Metoda " + this.currMethod.getName() + " nema povratnu vrednost u telu svoje funkcije",
					methDecl);
		}
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
			report_error("Break se nalazi van while petlje", breakStmt);
		}
	}

	public void visit(ClassName className) {
		// treba da se proveri da li postoji simbol u tabeli simbola
		// ako ne postoji dodati ga

	}

//	public void visit(MethodVarDecls methDecls) {
//		
//		if (Tab.find("main") == Tab.noObj) {
//			report_error("Nije definisana main funkcija!", methDecls);
//		} else {
//			report_error("Definisana je main funkcija!" , methDecls);
//		}
//		
////		if (currMethod != null) Tab.chainLocalSymbols(currMethod);
////		currMethod = null;
////		Tab.closeScope();
//	}

//	public void visit(TypeMethod methDecl) {
//		// prvo se doda metoda u tabelu simbola pa se onda otvara opseg
//
//		if (Tab.find(methDecl.getMethodName()) != Tab.noObj) {
//			// ako je definisan ali ne u tom opsegu
//			if (Tab.currentScope.findSymbol(methDecl.getMethodName()) != null) {
//				report_error("Greska: Simbol " + methDecl.getMethodName() + " je vec definisan u tabeli simbola",
//						methDecl);
//			} else {
//				currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
//			}
//		} else {
//			// ako uopste nije definisan
//			currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
//		}
//
//		methDecl.struct = currType;
//		Tab.openScope();
//	}
//
//	public void visit(VoidMethod methDecl) {
////		report_info(currType, methDecl);
//
//		methDecl.struct = currType = Tab.noType;
//
//		if (Tab.find(methDecl.getMethodName()) != Tab.noObj) {
//			// ako je definisan ali ne u tom opsegu
//			if (Tab.currentScope.findSymbol(methDecl.getMethodName()) != null) {
//				report_error("Greska: Simbol " + methDecl.getMethodName() + " je vec definisan u tabeli simbola",
//						methDecl);
//			} else {
//				currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
//			}
//		} else {
//			// ako uopste nije definisan
//			currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
//		}
//
//		Tab.openScope();
//	}
//
//	public void visit(MethodVarDecls varDecl) {
//		
//		if (Tab.find("main") == Tab.noObj) {
//			report_error("Nije definisana main funkcija!", varDecl);
//		} else {
//			report_info("Definisan je main", varDecl);
//		}
//		
//		if (currMethod != null) Tab.chainLocalSymbols(currMethod);
//		currMethod = null;
//		Tab.closeScope();
//	}
//	
//	public void visit(ReturnExpr returnExpr) {
//		
//		
//	}

}
