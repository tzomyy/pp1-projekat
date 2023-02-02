// generated with ast extension for cup
// version 0.8
// 2/1/2023 23:50:43


package rs.ac.bg.etf.pp1.ast;

public class DesignAssignArray extends DesignatorStatement {

    private DesignArray DesignArray;
    private Designator Designator;

    public DesignAssignArray (DesignArray DesignArray, Designator Designator) {
        this.DesignArray=DesignArray;
        if(DesignArray!=null) DesignArray.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DesignArray getDesignArray() {
        return DesignArray;
    }

    public void setDesignArray(DesignArray DesignArray) {
        this.DesignArray=DesignArray;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignArray!=null) DesignArray.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignArray!=null) DesignArray.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignArray!=null) DesignArray.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignAssignArray(\n");

        if(DesignArray!=null)
            buffer.append(DesignArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignAssignArray]");
        return buffer.toString();
    }
}
