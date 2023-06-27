// generated with ast extension for cup
// version 0.8
// 27/5/2023 16:47:13


package rs.ac.bg.etf.pp1.ast;

public class ConstructorDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private ConstructorFormPars ConstructorFormPars;
    private ConstructorVarDecl ConstructorVarDecl;
    private ConstructorStatement ConstructorStatement;

    public ConstructorDecl (String I1, ConstructorFormPars ConstructorFormPars, ConstructorVarDecl ConstructorVarDecl, ConstructorStatement ConstructorStatement) {
        this.I1=I1;
        this.ConstructorFormPars=ConstructorFormPars;
        if(ConstructorFormPars!=null) ConstructorFormPars.setParent(this);
        this.ConstructorVarDecl=ConstructorVarDecl;
        if(ConstructorVarDecl!=null) ConstructorVarDecl.setParent(this);
        this.ConstructorStatement=ConstructorStatement;
        if(ConstructorStatement!=null) ConstructorStatement.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ConstructorFormPars getConstructorFormPars() {
        return ConstructorFormPars;
    }

    public void setConstructorFormPars(ConstructorFormPars ConstructorFormPars) {
        this.ConstructorFormPars=ConstructorFormPars;
    }

    public ConstructorVarDecl getConstructorVarDecl() {
        return ConstructorVarDecl;
    }

    public void setConstructorVarDecl(ConstructorVarDecl ConstructorVarDecl) {
        this.ConstructorVarDecl=ConstructorVarDecl;
    }

    public ConstructorStatement getConstructorStatement() {
        return ConstructorStatement;
    }

    public void setConstructorStatement(ConstructorStatement ConstructorStatement) {
        this.ConstructorStatement=ConstructorStatement;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorFormPars!=null) ConstructorFormPars.accept(visitor);
        if(ConstructorVarDecl!=null) ConstructorVarDecl.accept(visitor);
        if(ConstructorStatement!=null) ConstructorStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorFormPars!=null) ConstructorFormPars.traverseTopDown(visitor);
        if(ConstructorVarDecl!=null) ConstructorVarDecl.traverseTopDown(visitor);
        if(ConstructorStatement!=null) ConstructorStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorFormPars!=null) ConstructorFormPars.traverseBottomUp(visitor);
        if(ConstructorVarDecl!=null) ConstructorVarDecl.traverseBottomUp(visitor);
        if(ConstructorStatement!=null) ConstructorStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ConstructorFormPars!=null)
            buffer.append(ConstructorFormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorVarDecl!=null)
            buffer.append(ConstructorVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorStatement!=null)
            buffer.append(ConstructorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstructorDecl]");
        return buffer.toString();
    }
}
