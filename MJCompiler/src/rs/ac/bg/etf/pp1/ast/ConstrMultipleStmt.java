// generated with ast extension for cup
// version 0.8
// 21/5/2023 13:24:49


package rs.ac.bg.etf.pp1.ast;

public class ConstrMultipleStmt extends ConstructorStatement {

    private ConstructorStatement ConstructorStatement;
    private Statement Statement;

    public ConstrMultipleStmt (ConstructorStatement ConstructorStatement, Statement Statement) {
        this.ConstructorStatement=ConstructorStatement;
        if(ConstructorStatement!=null) ConstructorStatement.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ConstructorStatement getConstructorStatement() {
        return ConstructorStatement;
    }

    public void setConstructorStatement(ConstructorStatement ConstructorStatement) {
        this.ConstructorStatement=ConstructorStatement;
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
        if(ConstructorStatement!=null) ConstructorStatement.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorStatement!=null) ConstructorStatement.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorStatement!=null) ConstructorStatement.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstrMultipleStmt(\n");

        if(ConstructorStatement!=null)
            buffer.append(ConstructorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstrMultipleStmt]");
        return buffer.toString();
    }
}
