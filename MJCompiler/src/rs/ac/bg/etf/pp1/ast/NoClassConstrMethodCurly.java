// generated with ast extension for cup
// version 0.8
// 17/4/2023 23:35:9


package rs.ac.bg.etf.pp1.ast;

public class NoClassConstrMethodCurly extends ClassConstrMethod {

    public NoClassConstrMethodCurly () {
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
        buffer.append("NoClassConstrMethodCurly(\n");

        buffer.append(tab);
        buffer.append(") [NoClassConstrMethodCurly]");
        return buffer.toString();
    }
}
