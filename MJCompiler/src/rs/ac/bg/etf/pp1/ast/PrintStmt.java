// generated with ast extension for cup
// version 0.8
// 6/1/2023 2:37:25


package rs.ac.bg.etf.pp1.ast;

public class PrintStmt extends Statement {

    private Expr Expr;
    private StmtConst StmtConst;

    public PrintStmt (Expr Expr, StmtConst StmtConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.StmtConst=StmtConst;
        if(StmtConst!=null) StmtConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public StmtConst getStmtConst() {
        return StmtConst;
    }

    public void setStmtConst(StmtConst StmtConst) {
        this.StmtConst=StmtConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(StmtConst!=null) StmtConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(StmtConst!=null) StmtConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(StmtConst!=null) StmtConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStmt(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StmtConst!=null)
            buffer.append(StmtConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStmt]");
        return buffer.toString();
    }
}
