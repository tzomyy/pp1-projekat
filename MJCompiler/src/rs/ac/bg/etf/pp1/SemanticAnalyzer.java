package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
	int methFormParams = 0;
	int methActParams = 0;
	int nvars = 0;

	Obj currMethod = null;
	Struct currType = null;
	Struct boolType = new Struct(Struct.Bool);
	
	HashMap<String, List<Obj>> methFuncParam = new HashMap<>();

	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
		
		formParams = new ArrayList<>();
		formParams.add(new Obj(Obj.Var, "", Tab.charType));
		methFuncParam.put("ord", formParams);
		
		formParams = new ArrayList<>();
		formParams.add(new Obj(Obj.Var, "", Tab.intType));
		methFuncParam.put("chr", formParams);
		
		Struct arrayType = new Struct(Struct.Array);
		arrayType.setElementType(Tab.noType);
		formParams = new ArrayList<>();
		formParams.add(new Obj(Obj.Var, "", Tab.intType));
		methFuncParam.put("len", formParams);
		
		formParams = new ArrayList<>();
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
			report_info("Definisana je main funkcija!", null);
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
				report_info("Definisana bool konstanta " + boolConstDecl.getConstName(), null);
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
		boolConstDecl.struct = boolType;
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
				report_info("Definisana int kontanta " + integerConstDecl.getConstName(), null);

				Obj intNode = Tab.insert(Obj.Con, integerConstDecl.getConstName(), Tab.intType);
				intNode.setAdr(integerConstDecl.getNumberConst());
			}
		}
		integerConstDecl.struct = Tab.intType;
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
				report_info("Definisana char konstanta " + charConstDecl.getConstName() , null);

				Obj intNode = Tab.insert(Obj.Con, charConstDecl.getConstName(), Tab.charType);
				intNode.setAdr(charConstDecl.getCharConst());
			}
		}
		charConstDecl.struct = Tab.charType;
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
		
		report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
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
					if (varDecl.getVarName() == "niz") {
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
		report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
	}
	
	public void visit(VarDecl varDecl) {
		nvars++;
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
		
		
		methDecl.getType().struct = currType;
		report_info("Definisana funkcija " + methDecl.getMethodName(), methDecl);
		Tab.openScope();
	}

	public void visit(VoidMethod methDecl) {
		currType = Tab.noType;
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

		//methDecl.getType().struct = currType;
		report_info("Definisana funkcija " + methDecl.getMethodName(), methDecl);
		Tab.openScope();
	}
	
	public void visit(ReturnNoExpr retExpr) {
		if (this.currMethod.getType() != Tab.noType) {
			report_error("Funkcija ima povratni tip!" , retExpr);
		}
	}

	public void visit(ReturnExpr retExpr) {
		if (this.currMethod == null) {
			report_error("Return naredba se nalazi van funkcije!", retExpr);
		}
		
		if (this.currMethod.getType() != retExpr.getExpr().struct) {
			report_error("Povratni izraz se ne poklapa sa tipom funkcije!" , retExpr);
		}

		this.hasReturn = true;
	}

	public void visit(MethodDecls methDecl) {
		if (!this.hasReturn && (currMethod.getType() != Tab.noType)) {
			report_error("Metoda " + this.currMethod.getName() + " nema povratnu vrednost u telu svoje funkcije",
					methDecl);
		}
		
		
		currMethod.setLevel(methFormParams);
		methFuncParam.put(currMethod.getName(), formParams);
		Tab.chainLocalSymbols(currMethod);
		
		Tab.closeScope();
		this.formParams = new ArrayList<>();
		this.methFormParams = 0;
		this.currMethod = null;
		this.hasReturn = false;
	}
	
	// brojanje parametara funkcije
	
	public void visit(SingleFormParam params) {
		
		if (Tab.find(params.getFormName()) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(params.getFormName()) != null) {
				report_error("Parametar s imenom "+ params.getFormName() + " je vec naveden!", params);
				return;
			}			
		}
		
		if (this.arrayType) {
			Struct arrayType = new Struct(Struct.Array, currType);
			Tab.insert(Obj.Var, params.getFormName() , arrayType);
			
		} else if (this.matrixType) {
			Struct arrayType = new Struct(Struct.Array, currType);
			Struct matrixType = new Struct(Struct.Array, arrayType);
			Tab.insert(Obj.Var, params.getFormName() , matrixType);
		} else {
			Tab.insert(Obj.Var, params.getFormName(), this.currType);
		}
		
		this.methFormParams++;
		this.formParams.add(Tab.find(params.getFormName()));
	}
	
	// statement

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
	
	public void visit(ReadStmt readStmt) {
		if (readStmt.getDesignator().obj.getKind() != Obj.Elem &&
				readStmt.getDesignator().obj.getKind() != Obj.Var) {
			report_error("U read statement-u designator mora biti promenljiva, element niza ili matrice!", readStmt);
		}
		if (readStmt.getDesignator().obj.getType().getKind() != Tab.intType.getKind() 
				&& readStmt.getDesignator().obj.getType().getKind() != Tab.charType.getKind()
				&& readStmt.getDesignator().obj.getType().getKind() != boolType.getKind()) {
			report_error("U read statement-u designator mora biti tipa int, char ili bool!", readStmt);
		}
	}
	
	public void visit(MapStmt mapStmt) {
		Obj node = Tab.find(mapStmt.getDesignator1().obj.getName());
		
		if (node == Tab.noObj) {
			report_error("Ne postoji promenljiva sa imenom " + node.getName(), mapStmt);
			return;
		}
		
		if (node.getType().getKind() != Struct.Array) {
			report_error("Promenljiva u map izrazu mora biti niz! ", mapStmt);
			return;
		}
		
		if (node.getType().getElemType() != Tab.intType && 
				node.getType().getElemType() != Tab.charType &&
				node.getType().getElemType() != boolType) {
			report_error("Elementi niza treba da budu int, char ili bool tipa!", mapStmt);
			return;
		}
		
		Obj identNode = Tab.find(mapStmt.getIdent());
		
		if (identNode == Tab.noObj) {
			report_error("Ne postoji promenljiva sa imenom " + identNode.getName(), mapStmt);
			return;
		}
		
		if (identNode.getType() != node.getType().getElemType()) {
			report_error("Tipovi u map izrazu nisu kompatibilni ", mapStmt);
			return;
		}
		
	}
	
	public void visit(DesignInc designator) {
		
		if (designator.getDesignator().obj.getKind() != Obj.Elem &&
				designator.getDesignator().obj.getKind() != Obj.Var) {
			report_error("U inkrement statement-u designator mora biti promenljiva, element niza ili matrice!", designator);
		}
		
		if (designator.getDesignator().obj.getType().getKind() != Tab.intType.getKind()) {
			report_error("U inkrement statement-u designator mora biti tipa int, char ili bool!", designator);
		}
		
		
	}
	
	public void visit(DesignDec designator) {
		
		if (designator.getDesignator().obj.getKind() != Obj.Elem &&
				designator.getDesignator().obj.getKind() != Obj.Var) {
			report_error("U dekrement statement-u designator mora biti promenljiva, element niza ili matrice!", designator);
		}
		
		if (designator.getDesignator().obj.getType().getKind() != Tab.intType.getKind()) {
			report_error("U dekrement statement-u designator mora biti tipa int, char ili bool!", designator);
		}
	}
	
	public void visit(DesignAssign designator) {
		if (designator.getDesignator().obj.getKind() != Obj.Elem &&
				designator.getDesignator().obj.getKind() != Obj.Var) {
			report_error("U dekrement statement-u designator mora biti promenljiva, element niza ili matrice!", designator);
		}
		if (!designator.getDesignator().obj.getType().compatibleWith(designator.getExpr().struct)) {
			report_error("Tip neterminala Expr" 
					+" mora biti kompatibilan sa tipom neterminala "
					+ designator.getDesignator().obj.getName(), designator);
		}
	}

	// expr

	public void visit(PrintExpr printExpr) {
		
		if (currType != Tab.intType && currType != Tab.charType && currType != boolType) {
			report_error("Povratna vrednost izraza u print naredbi nije odgovarajuceg tipa! " , printExpr);
		}
//		} else {
//			report_info("Print", printExpr);
//		}
	}
	
	
	public void visit(SingleExpr expr) {
		currType = expr.struct = expr.getTerm().struct;
		
	}

	public void visit(NegativeExpr expr) {
		if (expr.getTerm().struct != Tab.intType) {
			currType = expr.struct = Tab.noType;
			report_error("Negativni izraz treba da bude tipa int", expr);
		} else {
			currType = expr.struct = expr.getTerm().struct;
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
			report_error("Tipovi operanada treba da budu kompatabilni!", expr);
		}

	}

	// term

	public void visit(SingleTerm term) {
		term.struct = term.getFactor().struct;
	}

	public void visit(MultipleTerms term) {

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

	public void visit(FactFunc factor) {
		
		if (factor.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Promeljiva " + factor.getDesignator().obj.getName() + " nije funkcija", factor);
			this.actParams = new ArrayList<>();
			return;
		} 
		
		factor.struct = factor.getDesignator().obj.getType();
		
		//report_info(factor.getDesignator().obj.getName(), factor);
		List<Obj> funcParam = this.methFuncParam.get(factor.getDesignator().obj.getName());
		if (funcParam.size() != this.actParams.size()) {
			report_error("Broj argumenata funkcije bi trebalo da bude " + funcParam.size() + this.actParams.size(), factor);
			return;
		}
		int i = 0;
		for (Obj obj: funcParam) {
			if (!obj.getType().compatibleWith(this.actParams.get(i))) {
				this.actParams = new ArrayList<>();
				report_error("Tipovi na " + (i+1) + ". mestu u pozivu funkcije " 
				+ factor.getDesignator().obj.getName() +" nisu kompatablni", factor);
				return;
			}
			i++;
		}
		this.actParams = new ArrayList<>();
	}
	
	public void visit(FactConstrMatrix factor) {
		Struct arrayType = new Struct(Struct.Array, factor.getType().struct);
		Struct matrix = new Struct(Struct.Array, arrayType);
		factor.struct = matrix;
	}

	public void visit(FactConstrArray factor) {
		Struct arrayType = new Struct(Struct.Array, factor.getType().struct);
		factor.struct = arrayType;
	}
	
	public void visit(FactExpr factor) {
		factor.struct = factor.getExpr().struct;
	}
	
	// designator statement
	
	public void visit(DesignFunction function) {
		
		if (function.getDesignator().obj.getType().getKind() != Obj.Meth) {
			report_error("Promeljiva " + function.getDesignator().obj.getName() + " nije funkcija", function);
			return;
		}
		
		Collection<Obj> funcParam = methFuncParam.get(function.getDesignator().obj.getName());
				
		
		if (funcParam.size() != this.actParams.size()) {
			report_error("Broj argumenata funkcije " + "bi trebalo da bude " + funcParam.size(), function);
		}
		int i = 0;
		for (Obj obj: funcParam) {
			if (!obj.getType().compatibleWith(this.actParams.get(i))) {
				this.actParams = new ArrayList<>();
				report_error("Tipovi na " + (i+1) + ". mestu u pozivu funkcije " 
				+ function.getDesignator().obj.getName() +" nisu kompatablni", function);
				return;
			}
			i++;
		}
		this.actParams = new ArrayList<>();
	}
	
	//actparams
	
	List<Struct> actParams = new ArrayList<>();
	List<Obj> formParams = new ArrayList<>();
	
	public void visit(SingleActParam params) {		
		actParams.add(params.getExpr().struct);
	}
	
	public void visit(ActParams params) {		
		actParams.add(params.getExpr().struct);
	}

	// cond

	boolean flagRelop = false;
	Expr secondRelopExpr = null;

	public void visit(MoreConds condition) {
		flagRelop = true;
		secondRelopExpr = condition.getExpr();

	}

	public void visit(CondFact condition) {

		// ako ima samo jedan uslov bez operacija
		if (!flagRelop) {
			if (condition.getExpr().struct != boolType) {
				report_error("Uslov nije tipa boolean!", condition);
				return;
			} else {
				condition.struct = condition.getExpr().struct;
				return;
			}
		}

		if (!secondRelopExpr.struct.compatibleWith(condition.getExpr().struct)) {
			report_error("Tipovi u uslovu nisu kompatabilni!", condition);
			return;
		}

		if (condition.getExpr().struct.getKind() == Struct.Array) {
			if (!(currOperator == Operator.IS_EQUAL || currOperator == Operator.NOT_EQUAL)) {
				report_error("Nizovi mogu korisititi != ili  == od relacionih operatora!", condition);
			}
		}

		flagRelop = false;
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
		Obj designNode = designator.getMatrixDesignator().obj;
		
		if (designNode == Tab.noObj) {
			report_error("Varijabla " + designator.getMatrixDesignator().obj.getName() + " nije deklarisana! ", null);
		}
		designator.obj = designNode;
	}
	
	public void visit(DesignatorName designator) {
		Obj designNode = Tab.find(designator.getName());

		if (designNode == Tab.noObj) {
			report_error("Varijabla " + designator.getName() + " nije deklarisana! ", null);
		}
		designator.obj = designNode;
	}

	public void visit(ArrayDesignat designator) {

		Obj designNode = designator.getDesignatorName().obj;

		if (designNode.getType().getKind() != Struct.Array) {
			report_error("Promenljiva " + designator.getDesignatorName().obj.getName() + " nije niz!", designator);
		}
		if (designator.getExpr().struct != Tab.intType) {
			report_error("Greska: Izraz bi trebalo da bude tipa int! ", designator);
		}
		
		designator.obj = new Obj(Obj.Elem, designNode.getName(), designNode.getType().getElemType() );
	}
	
	public void visit(MatrixDesignat designator) {
		
		Obj designNode = designator.getDesignatorName().obj;

		if (designNode.getType().getKind() != Struct.Array) {
			report_error("Promenljiva " + designator.getDesignatorName().obj.getName() + " nije matrica!", designator);
		}
		if (designNode.getType().getElemType().getKind() != Struct.Array) {
			report_error("Promenljiva " + designator.getDesignatorName().obj.getName() + " nije matrica!", designator);
		}
		if (designator.getExpr().struct != Tab.intType) {
			report_error("Greska: Izraz bi trebalo da bude tipa int! ", designator);
		}
		
		designator.obj = new Obj(Obj.Elem, designNode.getName(), designNode.getType().getElemType().getElemType() );
	}
	
	public void visit(DesignError designator) {
		report_error("Greska kod designatorError-a ", designator);
	}
	
	// operatori

	enum Operator {
		ADD, SUB, MUL, DIV, PROC, IS_EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL, EQUAL
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
