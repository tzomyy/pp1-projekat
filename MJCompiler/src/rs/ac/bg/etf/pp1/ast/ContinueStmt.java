// generated with ast extension for cup
// version 0.8
// 4/1/2023 18:33:11


package rs.ac.bg.etf.pp1.ast;

public class ContinueStmt extends Statement {

    public ContinueStmt () {
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
        buffer.append("ContinueStmt(\n");

        buffer.append(tab);
        buffer.append(") [ContinueStmt]");
        return buffer.toString();
    }
}
