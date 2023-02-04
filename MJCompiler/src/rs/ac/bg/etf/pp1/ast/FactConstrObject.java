// generated with ast extension for cup
// version 0.8
// 4/1/2023 18:33:11


package rs.ac.bg.etf.pp1.ast;

public class FactConstrObject extends Factor {

    private Type Type;
    private FactParam FactParam;

    public FactConstrObject (Type Type, FactParam FactParam) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FactParam=FactParam;
        if(FactParam!=null) FactParam.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(Type!=null) Type.accept(visitor);
        if(FactParam!=null) FactParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FactParam!=null) FactParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FactParam!=null) FactParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactConstrObject(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactParam!=null)
            buffer.append(FactParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactConstrObject]");
        return buffer.toString();
    }
}
