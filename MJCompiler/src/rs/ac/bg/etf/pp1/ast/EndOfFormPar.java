// generated with ast extension for cup
// version 0.8
// 1/1/2023 20:49:49


package rs.ac.bg.etf.pp1.ast;

public class EndOfFormPar extends MultipleFormPar {

    public EndOfFormPar () {
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
        buffer.append("EndOfFormPar(\n");

        buffer.append(tab);
        buffer.append(") [EndOfFormPar]");
        return buffer.toString();
    }
}
