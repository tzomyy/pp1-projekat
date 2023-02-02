// generated with ast extension for cup
// version 0.8
// 3/1/2023 0:1:27


package rs.ac.bg.etf.pp1.ast;

public class EndOfVarDeclarations extends VarDeclarations {

    private EndOfVarDecl EndOfVarDecl;

    public EndOfVarDeclarations (EndOfVarDecl EndOfVarDecl) {
        this.EndOfVarDecl=EndOfVarDecl;
        if(EndOfVarDecl!=null) EndOfVarDecl.setParent(this);
    }

    public EndOfVarDecl getEndOfVarDecl() {
        return EndOfVarDecl;
    }

    public void setEndOfVarDecl(EndOfVarDecl EndOfVarDecl) {
        this.EndOfVarDecl=EndOfVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EndOfVarDecl!=null) EndOfVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EndOfVarDecl!=null) EndOfVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EndOfVarDecl!=null) EndOfVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EndOfVarDeclarations(\n");

        if(EndOfVarDecl!=null)
            buffer.append(EndOfVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EndOfVarDeclarations]");
        return buffer.toString();
    }
}
