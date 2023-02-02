// generated with ast extension for cup
// version 0.8
// 3/1/2023 0:1:27


package rs.ac.bg.etf.pp1.ast;

public class Program implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private Declaration Declaration;
    private MultipleMethodDecl MultipleMethodDecl;

    public Program (String I1, Declaration Declaration, MultipleMethodDecl MultipleMethodDecl) {
        this.I1=I1;
        this.Declaration=Declaration;
        if(Declaration!=null) Declaration.setParent(this);
        this.MultipleMethodDecl=MultipleMethodDecl;
        if(MultipleMethodDecl!=null) MultipleMethodDecl.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public Declaration getDeclaration() {
        return Declaration;
    }

    public void setDeclaration(Declaration Declaration) {
        this.Declaration=Declaration;
    }

    public MultipleMethodDecl getMultipleMethodDecl() {
        return MultipleMethodDecl;
    }

    public void setMultipleMethodDecl(MultipleMethodDecl MultipleMethodDecl) {
        this.MultipleMethodDecl=MultipleMethodDecl;
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
        if(Declaration!=null) Declaration.accept(visitor);
        if(MultipleMethodDecl!=null) MultipleMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Declaration!=null) Declaration.traverseTopDown(visitor);
        if(MultipleMethodDecl!=null) MultipleMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Declaration!=null) Declaration.traverseBottomUp(visitor);
        if(MultipleMethodDecl!=null) MultipleMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(Declaration!=null)
            buffer.append(Declaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MultipleMethodDecl!=null)
            buffer.append(MultipleMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program]");
        return buffer.toString();
    }
}
