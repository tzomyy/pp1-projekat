// generated with ast extension for cup
// version 0.8
// 27/5/2023 16:47:13


package rs.ac.bg.etf.pp1.ast;

public class ElseStmt extends ElseStatement {

    private DummyAfterElse DummyAfterElse;
    private Statement Statement;

    public ElseStmt (DummyAfterElse DummyAfterElse, Statement Statement) {
        this.DummyAfterElse=DummyAfterElse;
        if(DummyAfterElse!=null) DummyAfterElse.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DummyAfterElse getDummyAfterElse() {
        return DummyAfterElse;
    }

    public void setDummyAfterElse(DummyAfterElse DummyAfterElse) {
        this.DummyAfterElse=DummyAfterElse;
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
        if(DummyAfterElse!=null) DummyAfterElse.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DummyAfterElse!=null) DummyAfterElse.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DummyAfterElse!=null) DummyAfterElse.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ElseStmt(\n");

        if(DummyAfterElse!=null)
            buffer.append(DummyAfterElse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ElseStmt]");
        return buffer.toString();
    }
}
