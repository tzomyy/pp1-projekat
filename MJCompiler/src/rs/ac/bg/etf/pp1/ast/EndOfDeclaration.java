// generated with ast extension for cup
// version 0.8
// 25/5/2023 23:50:18


package rs.ac.bg.etf.pp1.ast;

public class EndOfDeclaration extends MultipleConstDecl {

    public EndOfDeclaration () {
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
        buffer.append("EndOfDeclaration(\n");

        buffer.append(tab);
        buffer.append(") [EndOfDeclaration]");
        return buffer.toString();
    }
}
