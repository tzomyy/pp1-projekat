// generated with ast extension for cup
// version 0.8
// 2/1/2023 23:50:43


package rs.ac.bg.etf.pp1.ast;

public class MultipleDesignators extends DesignArray {

    private DesignArray DesignArray;
    private DesignOptional DesignOptional;

    public MultipleDesignators (DesignArray DesignArray, DesignOptional DesignOptional) {
        this.DesignArray=DesignArray;
        if(DesignArray!=null) DesignArray.setParent(this);
        this.DesignOptional=DesignOptional;
        if(DesignOptional!=null) DesignOptional.setParent(this);
    }

    public DesignArray getDesignArray() {
        return DesignArray;
    }

    public void setDesignArray(DesignArray DesignArray) {
        this.DesignArray=DesignArray;
    }

    public DesignOptional getDesignOptional() {
        return DesignOptional;
    }

    public void setDesignOptional(DesignOptional DesignOptional) {
        this.DesignOptional=DesignOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignArray!=null) DesignArray.accept(visitor);
        if(DesignOptional!=null) DesignOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignArray!=null) DesignArray.traverseTopDown(visitor);
        if(DesignOptional!=null) DesignOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignArray!=null) DesignArray.traverseBottomUp(visitor);
        if(DesignOptional!=null) DesignOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleDesignators(\n");

        if(DesignArray!=null)
            buffer.append(DesignArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignOptional!=null)
            buffer.append(DesignOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleDesignators]");
        return buffer.toString();
    }
}
