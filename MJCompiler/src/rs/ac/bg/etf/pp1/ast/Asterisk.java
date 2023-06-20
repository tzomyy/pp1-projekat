// generated with ast extension for cup
// version 0.8
// 20/5/2023 22:42:58


package rs.ac.bg.etf.pp1.ast;

public class Asterisk extends Mulop {

    public Asterisk () {
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
        buffer.append("Asterisk(\n");

        buffer.append(tab);
        buffer.append(") [Asterisk]");
        return buffer.toString();
    }
}
