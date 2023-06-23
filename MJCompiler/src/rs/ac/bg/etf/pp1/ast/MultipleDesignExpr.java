// generated with ast extension for cup
// version 0.8
// 23/5/2023 18:21:34


package rs.ac.bg.etf.pp1.ast;

public class MultipleDesignExpr extends Designator {

    private MatrixDesignator MatrixDesignator;

    public MultipleDesignExpr (MatrixDesignator MatrixDesignator) {
        this.MatrixDesignator=MatrixDesignator;
        if(MatrixDesignator!=null) MatrixDesignator.setParent(this);
    }

    public MatrixDesignator getMatrixDesignator() {
        return MatrixDesignator;
    }

    public void setMatrixDesignator(MatrixDesignator MatrixDesignator) {
        this.MatrixDesignator=MatrixDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MatrixDesignator!=null) MatrixDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MatrixDesignator!=null) MatrixDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MatrixDesignator!=null) MatrixDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleDesignExpr(\n");

        if(MatrixDesignator!=null)
            buffer.append(MatrixDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleDesignExpr]");
        return buffer.toString();
    }
}
