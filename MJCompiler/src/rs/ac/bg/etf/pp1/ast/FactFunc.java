// generated with ast extension for cup
// version 0.8
// 27/5/2023 16:47:13


package rs.ac.bg.etf.pp1.ast;

public class FactFunc extends Factor {

    private Designator Designator;
    private FactParam FactParam;

    public FactFunc (Designator Designator, FactParam FactParam) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FactParam=FactParam;
        if(FactParam!=null) FactParam.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FactParam getFactParam() {
        return FactParam;
    }

    public void setFactParam(FactParam FactParam) {
        this.FactParam=FactParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FactParam!=null) FactParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FactParam!=null) FactParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FactParam!=null) FactParam.traverseBottomUp(visitor);
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

        if(FactParam!=null)
            buffer.append(FactParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactFunc]");
        return buffer.toString();
    }
}
