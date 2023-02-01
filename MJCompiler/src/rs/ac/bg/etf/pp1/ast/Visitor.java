// generated with ast extension for cup
// version 0.8
// 1/1/2023 20:49:49


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Designator Designator);
    public void visit(MethodDecl MethodDecl);
    public void visit(Factor Factor);
    public void visit(Mulop Mulop);
    public void visit(MultipleVarDecl MultipleVarDecl);
    public void visit(Declaration Declaration);
    public void visit(Expr Expr);
    public void visit(MethodFormPars MethodFormPars);
    public void visit(SingleConstDecl SingleConstDecl);
    public void visit(MultipleMethodDecl MultipleMethodDecl);
    public void visit(MethodStatement MethodStatement);
    public void visit(MultipleConstDecl MultipleConstDecl);
    public void visit(MultipleFormPar MultipleFormPar);
    public void visit(Addop Addop);
    public void visit(Statement Statement);
    public void visit(Relop Relop);
    public void visit(FactFuncParam FactFuncParam);
    public void visit(Term Term);
    public void visit(ActPars ActPars);
    public void visit(SingleFormPar SingleFormPar);
    public void visit(Percent Percent);
    public void visit(Slash Slash);
    public void visit(Asterisk Asterisk);
    public void visit(Minus Minus);
    public void visit(Plus Plus);
    public void visit(LessEqual LessEqual);
    public void visit(Less Less);
    public void visit(GreaterEqual GreaterEqual);
    public void visit(Greater Greater);
    public void visit(NotEqual NotEqual);
    public void visit(IsEqual IsEqual);
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(Type Type);
    public void visit(SingleDesignIdent SingleDesignIdent);
    public void visit(MultipleDesignExpr MultipleDesignExpr);
    public void visit(MultipleDesignIdent MultipleDesignIdent);
    public void visit(SingleActParam SingleActParam);
    public void visit(ActParams ActParams);
    public void visit(NoFactFuncParams NoFactFuncParams);
    public void visit(FactFuncParams FactFuncParams);
    public void visit(FactBoolean FactBoolean);
    public void visit(FactChar FactChar);
    public void visit(FactNum FactNum);
    public void visit(FactFunc FactFunc);
    public void visit(FactVar FactVar);
    public void visit(MultipleTerms MultipleTerms);
    public void visit(SingleTerm SingleTerm);
    public void visit(SingleExpr SingleExpr);
    public void visit(MultipleExpr MultipleExpr);
    public void visit(NegativeExpr NegativeExpr);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(DesignStmt DesignStmt);
    public void visit(EndOfFormPar EndOfFormPar);
    public void visit(MultipleFormPars MultipleFormPars);
    public void visit(SingleFormParam SingleFormParam);
    public void visit(FormPars FormPars);
    public void visit(NoMethodStmt NoMethodStmt);
    public void visit(MethodStmt MethodStmt);
    public void visit(NoMethodFormParams NoMethodFormParams);
    public void visit(MethodFormParams MethodFormParams);
    public void visit(VoidMethodDecl VoidMethodDecl);
    public void visit(TypeMethodDecl TypeMethodDecl);
    public void visit(NoMethodDeclList NoMethodDeclList);
    public void visit(MethodDecls MethodDecls);
    public void visit(ClassDecl ClassDecl);
    public void visit(EndOfVarDeclaration EndOfVarDeclaration);
    public void visit(MultipleVarDecls MultipleVarDecls);
    public void visit(NoBrackets NoBrackets);
    public void visit(Brackets Brackets);
    public void visit(SingleVarDecl SingleVarDecl);
    public void visit(VarDecl VarDecl);
    public void visit(EndOfDeclaration EndOfDeclaration);
    public void visit(MultipleConstDecls MultipleConstDecls);
    public void visit(CharConstDecl CharConstDecl);
    public void visit(IntegerConstDecl IntegerConstDecl);
    public void visit(BoolConstDecl BoolConstDecl);
    public void visit(ConstDecl ConstDecl);
    public void visit(NoDeclaration NoDeclaration);
    public void visit(ClassDeclaration ClassDeclaration);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(Program Program);

}
