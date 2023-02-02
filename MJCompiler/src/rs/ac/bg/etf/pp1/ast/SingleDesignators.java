// generated with ast extension for cup
// version 0.8
// 3/1/2023 0:1:27


package rs.ac.bg.etf.pp1.ast;

public class SingleDesignators extends DesignArray {

    private DesignOptional DesignOptional;

    public SingleDesignators (DesignOptional DesignOptional) {
        this.DesignOptional=DesignOptional;
        if(DesignOptional!=null) DesignOptional.setParent(this);
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
        if(DesignOptional!=null) DesignOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignOptional!=null) DesignOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignOptional!=null) DesignOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleDesignators(\n");

        if(DesignOptional!=null)
            buffer.append(DesignOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleDesignators]");
        return buffer.toString();
    }
}
