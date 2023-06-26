// generated with ast extension for cup
// version 0.8
// 25/5/2023 23:50:18


package rs.ac.bg.etf.pp1.ast;

public class End implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public End () {
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
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("End(\n");

        buffer.append(tab);
        buffer.append(") [End]");
        return buffer.toString();
    }
}
