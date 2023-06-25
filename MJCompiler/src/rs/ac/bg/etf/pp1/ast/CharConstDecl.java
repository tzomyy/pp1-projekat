// generated with ast extension for cup
// version 0.8
// 25/5/2023 19:27:33


package rs.ac.bg.etf.pp1.ast;

public class CharConstDecl extends SingleConstDecl {

    private String constName;
    private Character charConst;

    public CharConstDecl (String constName, Character charConst) {
        this.constName=constName;
        this.charConst=charConst;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Character getCharConst() {
        return charConst;
    }

    public void setCharConst(Character charConst) {
        this.charConst=charConst;
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
        buffer.append("CharConstDecl(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+charConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharConstDecl]");
        return buffer.toString();
    }
}
