package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;

import rs.etf.pp1.symboltable.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	Logger log = Logger.getLogger(getClass());
	boolean errorDetected = false;
	boolean arrayType = false;

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
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());

		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola !", null);
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
//		System.out.println("Type: " + currType.getKind());
	}

	public void visit(BoolConstDecl boolConstDecl) {
		// da li je ime deklarisano
		// da li je dobrog tipa

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
				report_info("Naisla na konstantu", boolConstDecl);
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

	public void visit(IntegerConstDecl intConstDecl) {
		// da li je ime deklarisano
		// da li je dobrog tipa

		if (Tab.find(intConstDecl.getConstName()) != Tab.noObj) {
			report_error("Greska: Ime " + intConstDecl.getConstName() + " vec postoji u tabeli simbola!", intConstDecl);
			return;
		} else {
			if (!Tab.intType.equals(currType)) {
				report_error("Greska: Tip konstante " + intConstDecl.getConstName()
						+ " nije kompatibilan sa vrednoscu konstante!", intConstDecl);
				return;
			} else {
				report_info("Naisla na konstantu", intConstDecl);

				Obj intNode = Tab.insert(Obj.Con, intConstDecl.getConstName(), Tab.intType);
				intNode.setAdr(intConstDecl.getNumberConst());
			}
		}
	}

	public void visit(CharConstDecl charConstDecl) {
		// da li je ime deklarisano
		// da li je dobrog tipa

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
				report_info("Naisla na konstantu", charConstDecl);

				Obj intNode = Tab.insert(Obj.Con, charConstDecl.getConstName(), Tab.charType);
				intNode.setAdr(charConstDecl.getCharConst());
			}
		}
	}

	public void visit(Brackets brackets) {
		arrayType = true;
	}

	public void visit(MoreVarDecls varDecl) {
		// proveriti da li postoji takav simbol u tabeli simbola, tj. u tom opsegu
		// vazenja
		// ako ne postoji dodati ga

		if (Tab.find(varDecl.getVarName()) != Tab.noObj) {
			// ako je definisan ali ne u tom opsegu
			if (Tab.currentScope.findSymbol(varDecl.getVarName()) != null) {
				report_error("Greska: Simbol " + varDecl.getVarName() + " je vec definisan u tabeli simbola", varDecl);
			} else {
				if (arrayType == true) {
					Struct structType = new Struct(Struct.Array, currType);
					Tab.insert(Obj.Var, varDecl.getVarName() + "[]", structType);
				} else {
					Tab.insert(Obj.Var, varDecl.getVarName(), currType);
				}
			}
		} else {
			// ako uopste nije definisan
			if (arrayType == true) {
				Struct structType = new Struct(Struct.Array, currType);
				Tab.insert(Obj.Var, varDecl.getVarName() + "[]", structType);
			} else {
				Tab.insert(Obj.Var, varDecl.getVarName(), currType);
			}
		}

		if (arrayType == true) {
			arrayType = false;
		}
	}

	public void visit(EndOfVarDeclar varDecl) {
		// proveriti da li postoji takav simbol u tabeli simbola, tj. u tom opsegu
		// vazenja
		// ako ne postoji dodati ga

		if (Tab.find(varDecl.getVarName()) != Tab.noObj) {
			// ako je definisan ali ne u tom opsegu
			if (Tab.currentScope.findSymbol(varDecl.getVarName()) != null) {
				report_error("Greska: Simbol " + varDecl.getVarName() + " je vec definisan u tabeli simbola", varDecl);
			} else {
				if (arrayType == true) {
					Struct structType = new Struct(Struct.Array, currType);
					Tab.insert(Obj.Var, varDecl.getVarName() + "[]", structType);
				} else {
					Tab.insert(Obj.Var, varDecl.getVarName(), currType);
				}

			}
		} else {
			// ako uopste nije definisan
			if (arrayType == true) {
				Struct structType = new Struct(Struct.Array, currType);
				Tab.insert(Obj.Var, varDecl.getVarName() + "[]", structType);
			} else {
				Tab.insert(Obj.Var, varDecl.getVarName(), currType);
			}
		}

		if (arrayType == true) {
			arrayType = false;
		}

	}

	public void visit(TypeMethod methDecl) {
		// prvo se doda metoda u tabelu simbola pa se onda otvara opseg

		if (Tab.find(methDecl.getMethodName()) != Tab.noObj) {
			// ako je definisan ali ne u tom opsegu
			if (Tab.currentScope.findSymbol(methDecl.getMethodName()) != null) {
				report_error("Greska: Simbol " + methDecl.getMethodName() + " je vec definisan u tabeli simbola",
						methDecl);
			} else {
				currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
			}
		} else {
			// ako uopste nije definisan
			currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
		}

		methDecl.struct = currType;
		Tab.openScope();
	}

	public void visit(VoidMethod methDecl) {
//		report_info(currType, methDecl);

		methDecl.struct = currType = Tab.noType;

		if (Tab.find(methDecl.getMethodName()) != Tab.noObj) {
			// ako je definisan ali ne u tom opsegu
			if (Tab.currentScope.findSymbol(methDecl.getMethodName()) != null) {
				report_error("Greska: Simbol " + methDecl.getMethodName() + " je vec definisan u tabeli simbola",
						methDecl);
			} else {
				currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
			}
		} else {
			// ako uopste nije definisan
			currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
		}

		Tab.openScope();
	}

	public void visit(MethodVarDecls varDecl) {
		Tab.chainLocalSymbols(currMethod);
		Tab.closeScope();
	}

}
