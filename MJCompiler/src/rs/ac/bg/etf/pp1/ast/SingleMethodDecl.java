// generated with ast extension for cup
// version 0.8
// 31/0/2023 1:47:57


package rs.ac.bg.etf.pp1.ast;

public class SingleMethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Relop Relop;

    public SingleMethodDecl (Relop Relop) {
        this.Relop=Relop;
        if(Relop!=null) Relop.setParent(this);
    }

    public Relop getRelop() {
        return Relop;
    }

    public void setRelop(Relop Relop) {
        this.Relop=Relop;
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
        if(Relop!=null) Relop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Relop!=null) Relop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Relop!=null) Relop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleMethodDecl(\n");

        if(Relop!=null)
            buffer.append(Relop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleMethodDecl]");
        return buffer.toString();
    }
}
