// generated with ast extension for cup
// version 0.8
// 23/5/2023 18:21:34


package rs.ac.bg.etf.pp1.ast;

public class EndOfMethodVarDecl extends MethodVarDecl {

    public EndOfMethodVarDecl () {
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
        buffer.append("EndOfMethodVarDecl(\n");

        buffer.append(tab);
        buffer.append(") [EndOfMethodVarDecl]");
        return buffer.toString();
    }
}
