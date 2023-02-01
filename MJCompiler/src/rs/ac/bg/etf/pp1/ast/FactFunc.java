// generated with ast extension for cup
// version 0.8
// 1/1/2023 20:49:49


package rs.ac.bg.etf.pp1.ast;

public class FactFunc extends Factor {

    private Designator Designator;
    private FactFuncParam FactFuncParam;

    public FactFunc (Designator Designator, FactFuncParam FactFuncParam) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FactFuncParam=FactFuncParam;
        if(FactFuncParam!=null) FactFuncParam.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FactFuncParam getFactFuncParam() {
        return FactFuncParam;
    }

    public void setFactFuncParam(FactFuncParam FactFuncParam) {
        this.FactFuncParam=FactFuncParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FactFuncParam!=null) FactFuncParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FactFuncParam!=null) FactFuncParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FactFuncParam!=null) FactFuncParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactFunc(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactFuncParam!=null)
            buffer.append(FactFuncParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactFunc]");
        return buffer.toString();
    }
}
