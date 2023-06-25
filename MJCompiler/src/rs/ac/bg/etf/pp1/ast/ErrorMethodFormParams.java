// generated with ast extension for cup
// version 0.8
// 25/5/2023 19:27:33


package rs.ac.bg.etf.pp1.ast;

public class ErrorMethodFormParams extends MethodFormPars {

    public ErrorMethodFormParams () {
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
        buffer.append("ErrorMethodFormParams(\n");

        buffer.append(tab);
        buffer.append(") [ErrorMethodFormParams]");
        return buffer.toString();
    }
}
