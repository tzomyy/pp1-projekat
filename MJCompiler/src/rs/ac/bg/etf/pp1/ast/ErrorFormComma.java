// generated with ast extension for cup
// version 0.8
// 24/5/2023 1:45:28


package rs.ac.bg.etf.pp1.ast;

public class ErrorFormComma extends MultipleFormPar {

    private SingleFormPar SingleFormPar;

    public ErrorFormComma (SingleFormPar SingleFormPar) {
        this.SingleFormPar=SingleFormPar;
        if(SingleFormPar!=null) SingleFormPar.setParent(this);
    }

    public SingleFormPar getSingleFormPar() {
        return SingleFormPar;
    }

    public void setSingleFormPar(SingleFormPar SingleFormPar) {
        this.SingleFormPar=SingleFormPar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SingleFormPar!=null) SingleFormPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleFormPar!=null) SingleFormPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleFormPar!=null) SingleFormPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ErrorFormComma(\n");

        if(SingleFormPar!=null)
            buffer.append(SingleFormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ErrorFormComma]");
        return buffer.toString();
    }
}
