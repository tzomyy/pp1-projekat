// generated with ast extension for cup
// version 0.8
// 2/1/2023 23:50:43


package rs.ac.bg.etf.pp1.ast;

public class MoreVarDecls extends MoreVarDecl {

    private String I1;
    private Brackets Brackets;

    public MoreVarDecls (String I1, Brackets Brackets) {
        this.I1=I1;
        this.Brackets=Brackets;
        if(Brackets!=null) Brackets.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public Brackets getBrackets() {
        return Brackets;
    }

    public void setBrackets(Brackets Brackets) {
        this.Brackets=Brackets;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Brackets!=null) Brackets.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Brackets!=null) Brackets.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Brackets!=null) Brackets.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreVarDecls(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(Brackets!=null)
            buffer.append(Brackets.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreVarDecls]");
        return buffer.toString();
    }
}
