// generated with ast extension for cup
// version 0.8
// 19/4/2023 13:26:15


package rs.ac.bg.etf.pp1.ast;

public class PrintStmt extends Statement {

    private PrintExpr PrintExpr;
    private StmtConst StmtConst;

    public PrintStmt (PrintExpr PrintExpr, StmtConst StmtConst) {
        this.PrintExpr=PrintExpr;
        if(PrintExpr!=null) PrintExpr.setParent(this);
        this.StmtConst=StmtConst;
        if(StmtConst!=null) StmtConst.setParent(this);
    }

    public PrintExpr getPrintExpr() {
        return PrintExpr;
    }

    public void setPrintExpr(PrintExpr PrintExpr) {
        this.PrintExpr=PrintExpr;
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
        if(PrintExpr!=null) PrintExpr.accept(visitor);
        if(StmtConst!=null) StmtConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PrintExpr!=null) PrintExpr.traverseTopDown(visitor);
        if(StmtConst!=null) StmtConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PrintExpr!=null) PrintExpr.traverseBottomUp(visitor);
        if(StmtConst!=null) StmtConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStmt(\n");

        if(PrintExpr!=null)
            buffer.append(PrintExpr.toString("  "+tab));
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
