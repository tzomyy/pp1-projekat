// generated with ast extension for cup
// version 0.8
// 5/1/2023 17:59:25


package rs.ac.bg.etf.pp1.ast;

public class NoStmtCnst extends StmtConst {

    public NoStmtCnst () {
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
        buffer.append("NoStmtCnst(\n");

        buffer.append(tab);
        buffer.append(") [NoStmtCnst]");
        return buffer.toString();
    }
}
