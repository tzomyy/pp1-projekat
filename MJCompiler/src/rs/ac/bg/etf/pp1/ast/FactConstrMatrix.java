// generated with ast extension for cup
// version 0.8
// 27/5/2023 16:47:13


package rs.ac.bg.etf.pp1.ast;

public class FactConstrMatrix extends Factor {

    private Type Type;
    private Expr Expr;
    private DummyNewMatrix DummyNewMatrix;
    private Expr Expr1;

    public FactConstrMatrix (Type Type, Expr Expr, DummyNewMatrix DummyNewMatrix, Expr Expr1) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.DummyNewMatrix=DummyNewMatrix;
        if(DummyNewMatrix!=null) DummyNewMatrix.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public DummyNewMatrix getDummyNewMatrix() {
        return DummyNewMatrix;
    }

    public void setDummyNewMatrix(DummyNewMatrix DummyNewMatrix) {
        this.DummyNewMatrix=DummyNewMatrix;
    }

    public Expr getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr Expr1) {
        this.Expr1=Expr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(DummyNewMatrix!=null) DummyNewMatrix.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(DummyNewMatrix!=null) DummyNewMatrix.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(DummyNewMatrix!=null) DummyNewMatrix.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactConstrMatrix(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyNewMatrix!=null)
            buffer.append(DummyNewMatrix.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactConstrMatrix]");
        return buffer.toString();
    }
}
