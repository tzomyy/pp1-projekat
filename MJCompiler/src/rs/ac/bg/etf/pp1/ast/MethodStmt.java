// generated with ast extension for cup
// version 0.8
// 19/4/2023 13:26:15


package rs.ac.bg.etf.pp1.ast;

public class MethodStmt extends MethodStatement {

    private MethodStatement MethodStatement;
    private Statement Statement;

    public MethodStmt (MethodStatement MethodStatement, Statement Statement) {
        this.MethodStatement=MethodStatement;
        if(MethodStatement!=null) MethodStatement.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public MethodStatement getMethodStatement() {
        return MethodStatement;
    }

    public void setMethodStatement(MethodStatement MethodStatement) {
        this.MethodStatement=MethodStatement;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodStatement!=null) MethodStatement.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodStatement!=null) MethodStatement.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodStatement!=null) MethodStatement.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodStmt(\n");

        if(MethodStatement!=null)
            buffer.append(MethodStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodStmt]");
        return buffer.toString();
    }
}
