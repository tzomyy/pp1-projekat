// generated with ast extension for cup
// version 0.8
// 3/1/2023 0:1:27


package rs.ac.bg.etf.pp1.ast;

public class EndOfStatements extends StatementList {

    public EndOfStatements () {
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
        buffer.append("EndOfStatements(\n");

        buffer.append(tab);
        buffer.append(") [EndOfStatements]");
        return buffer.toString();
    }
}
