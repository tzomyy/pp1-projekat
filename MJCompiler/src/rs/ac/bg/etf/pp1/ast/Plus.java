// generated with ast extension for cup
// version 0.8
// 21/5/2023 13:24:49


package rs.ac.bg.etf.pp1.ast;

public class Plus extends Addop {

    public Plus () {
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
        buffer.append("Plus(\n");

        buffer.append(tab);
        buffer.append(") [Plus]");
        return buffer.toString();
    }
}
