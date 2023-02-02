// generated with ast extension for cup
// version 0.8
// 3/1/2023 0:1:27


package rs.ac.bg.etf.pp1.ast;

public class MultipleVarDecl extends VarDeclarations {

    private MoreVarDecl MoreVarDecl;
    private VarDeclarations VarDeclarations;

    public MultipleVarDecl (MoreVarDecl MoreVarDecl, VarDeclarations VarDeclarations) {
        this.MoreVarDecl=MoreVarDecl;
        if(MoreVarDecl!=null) MoreVarDecl.setParent(this);
        this.VarDeclarations=VarDeclarations;
        if(VarDeclarations!=null) VarDeclarations.setParent(this);
    }

    public MoreVarDecl getMoreVarDecl() {
        return MoreVarDecl;
    }

    public void setMoreVarDecl(MoreVarDecl MoreVarDecl) {
        this.MoreVarDecl=MoreVarDecl;
    }

    public VarDeclarations getVarDeclarations() {
        return VarDeclarations;
    }

    public void setVarDeclarations(VarDeclarations VarDeclarations) {
        this.VarDeclarations=VarDeclarations;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreVarDecl!=null) MoreVarDecl.accept(visitor);
        if(VarDeclarations!=null) VarDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreVarDecl!=null) MoreVarDecl.traverseTopDown(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreVarDecl!=null) MoreVarDecl.traverseBottomUp(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleVarDecl(\n");

        if(MoreVarDecl!=null)
            buffer.append(MoreVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclarations!=null)
            buffer.append(VarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleVarDecl]");
        return buffer.toString();
    }
}
