// generated with ast extension for cup
// version 0.8
// 31/0/2023 3:11:29


package rs.ac.bg.etf.pp1.ast;

public class Slash extends Mulop {

    public Slash () {
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
        buffer.append("Slash(\n");

        buffer.append(tab);
        buffer.append(") [Slash]");
        return buffer.toString();
    }
}
