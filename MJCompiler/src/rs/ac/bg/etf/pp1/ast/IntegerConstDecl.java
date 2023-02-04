// generated with ast extension for cup
// version 0.8
// 4/1/2023 18:33:11


package rs.ac.bg.etf.pp1.ast;

public class IntegerConstDecl extends SingleConstDecl {

    private String constName;
    private Integer numberConst;

    public IntegerConstDecl (String constName, Integer numberConst) {
        this.constName=constName;
        this.numberConst=numberConst;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Integer getNumberConst() {
        return numberConst;
    }

    public void setNumberConst(Integer numberConst) {
        this.numberConst=numberConst;
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
        buffer.append("IntegerConstDecl(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+numberConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IntegerConstDecl]");
        return buffer.toString();
    }
}
