// generated with ast extension for cup
// version 0.8
// 19/4/2023 13:26:15


package rs.ac.bg.etf.pp1.ast;

public class ErrorEndOfVarDecl extends EndOfVarDecl {

    public ErrorEndOfVarDecl () {
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
        buffer.append("ErrorEndOfVarDecl(\n");

        buffer.append(tab);
        buffer.append(") [ErrorEndOfVarDecl]");
        return buffer.toString();
    }
}
