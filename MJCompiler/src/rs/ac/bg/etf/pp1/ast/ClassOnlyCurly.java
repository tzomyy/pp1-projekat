// generated with ast extension for cup
// version 0.8
// 14/4/2023 23:14:54


package rs.ac.bg.etf.pp1.ast;

public class ClassOnlyCurly extends ClassConstrMethod {

    public ClassOnlyCurly () {
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
        buffer.append("ClassOnlyCurly(\n");

        buffer.append(tab);
        buffer.append(") [ClassOnlyCurly]");
        return buffer.toString();
    }
}
