// generated with ast extension for cup
// version 0.8
// 14/4/2023 20:31:10


package rs.ac.bg.etf.pp1.ast;

public class EndOfConstrVarDecl extends ConstructorVarDecl {

    public EndOfConstrVarDecl () {
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
        buffer.append("EndOfConstrVarDecl(\n");

        buffer.append(tab);
        buffer.append(") [EndOfConstrVarDecl]");
        return buffer.toString();
    }
}
