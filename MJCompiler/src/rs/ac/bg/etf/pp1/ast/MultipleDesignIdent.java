// generated with ast extension for cup
// version 0.8
// 26/4/2023 12:46:41


package rs.ac.bg.etf.pp1.ast;

public class MultipleDesignIdent extends Designator {

    private Designator Designator;
    private String var;

    public MultipleDesignIdent (Designator Designator, String var) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.var=var;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var=var;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleDesignIdent(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+var);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleDesignIdent]");
        return buffer.toString();
    }
}
