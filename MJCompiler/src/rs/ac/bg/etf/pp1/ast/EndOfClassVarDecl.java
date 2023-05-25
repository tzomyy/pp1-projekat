// generated with ast extension for cup
// version 0.8
// 25/4/2023 22:6:41


package rs.ac.bg.etf.pp1.ast;

public class EndOfClassVarDecl extends ClassVarDecl {

    public EndOfClassVarDecl () {
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
        buffer.append("EndOfClassVarDecl(\n");

        buffer.append(tab);
        buffer.append(") [EndOfClassVarDecl]");
        return buffer.toString();
    }
}
