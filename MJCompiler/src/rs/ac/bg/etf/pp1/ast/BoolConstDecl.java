// generated with ast extension for cup
// version 0.8
// 31/0/2023 1:38:29


package rs.ac.bg.etf.pp1.ast;

public class BoolConstDecl extends SingleConstDecl {

    private String constName;

    public BoolConstDecl (String constName) {
        this.constName=constName;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolConstDecl(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConstDecl]");
        return buffer.toString();
    }
}
