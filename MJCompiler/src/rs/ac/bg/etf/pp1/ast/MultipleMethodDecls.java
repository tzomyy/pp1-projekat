// generated with ast extension for cup
// version 0.8
// 31/0/2023 1:38:29


package rs.ac.bg.etf.pp1.ast;

public class MultipleMethodDecls extends MultipleMethodDecl {

    private MultipleMethodDecl MultipleMethodDecl;
    private SingleMethodDecl SingleMethodDecl;

    public MultipleMethodDecls (MultipleMethodDecl MultipleMethodDecl, SingleMethodDecl SingleMethodDecl) {
        this.MultipleMethodDecl=MultipleMethodDecl;
        if(MultipleMethodDecl!=null) MultipleMethodDecl.setParent(this);
        this.SingleMethodDecl=SingleMethodDecl;
        if(SingleMethodDecl!=null) SingleMethodDecl.setParent(this);
    }

    public MultipleMethodDecl getMultipleMethodDecl() {
        return MultipleMethodDecl;
    }

    public void setMultipleMethodDecl(MultipleMethodDecl MultipleMethodDecl) {
        this.MultipleMethodDecl=MultipleMethodDecl;
    }

    public SingleMethodDecl getSingleMethodDecl() {
        return SingleMethodDecl;
    }

    public void setSingleMethodDecl(SingleMethodDecl SingleMethodDecl) {
        this.SingleMethodDecl=SingleMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MultipleMethodDecl!=null) MultipleMethodDecl.accept(visitor);
        if(SingleMethodDecl!=null) SingleMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultipleMethodDecl!=null) MultipleMethodDecl.traverseTopDown(visitor);
        if(SingleMethodDecl!=null) SingleMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultipleMethodDecl!=null) MultipleMethodDecl.traverseBottomUp(visitor);
        if(SingleMethodDecl!=null) SingleMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleMethodDecls(\n");

        if(MultipleMethodDecl!=null)
            buffer.append(MultipleMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleMethodDecl!=null)
            buffer.append(SingleMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleMethodDecls]");
        return buffer.toString();
    }
}
