// generated with ast extension for cup
// version 0.8
// 1/1/2023 21:15:58


package rs.ac.bg.etf.pp1.ast;

public class MultipleFormPars extends MultipleFormPar {

    private MultipleFormPar MultipleFormPar;
    private SingleFormPar SingleFormPar;

    public MultipleFormPars (MultipleFormPar MultipleFormPar, SingleFormPar SingleFormPar) {
        this.MultipleFormPar=MultipleFormPar;
        if(MultipleFormPar!=null) MultipleFormPar.setParent(this);
        this.SingleFormPar=SingleFormPar;
        if(SingleFormPar!=null) SingleFormPar.setParent(this);
    }

    public MultipleFormPar getMultipleFormPar() {
        return MultipleFormPar;
    }

    public void setMultipleFormPar(MultipleFormPar MultipleFormPar) {
        this.MultipleFormPar=MultipleFormPar;
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
        if(MultipleFormPar!=null) MultipleFormPar.accept(visitor);
        if(SingleFormPar!=null) SingleFormPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultipleFormPar!=null) MultipleFormPar.traverseTopDown(visitor);
        if(SingleFormPar!=null) SingleFormPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultipleFormPar!=null) MultipleFormPar.traverseBottomUp(visitor);
        if(SingleFormPar!=null) SingleFormPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFormPars(\n");

        if(MultipleFormPar!=null)
            buffer.append(MultipleFormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleFormPar!=null)
            buffer.append(SingleFormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleFormPars]");
        return buffer.toString();
    }
}
