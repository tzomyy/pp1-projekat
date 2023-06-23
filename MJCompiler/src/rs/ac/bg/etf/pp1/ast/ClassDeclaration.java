// generated with ast extension for cup
// version 0.8
// 23/5/2023 18:21:34


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends Declaration {

    private Declaration Declaration;
    private ClassDecl ClassDecl;

    public ClassDeclaration (Declaration Declaration, ClassDecl ClassDecl) {
        this.Declaration=Declaration;
        if(Declaration!=null) Declaration.setParent(this);
        this.ClassDecl=ClassDecl;
        if(ClassDecl!=null) ClassDecl.setParent(this);
    }

    public Declaration getDeclaration() {
        return Declaration;
    }

    public void setDeclaration(Declaration Declaration) {
        this.Declaration=Declaration;
    }

    public ClassDecl getClassDecl() {
        return ClassDecl;
    }

    public void setClassDecl(ClassDecl ClassDecl) {
        this.ClassDecl=ClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Declaration!=null) Declaration.accept(visitor);
        if(ClassDecl!=null) ClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Declaration!=null) Declaration.traverseTopDown(visitor);
        if(ClassDecl!=null) ClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Declaration!=null) Declaration.traverseBottomUp(visitor);
        if(ClassDecl!=null) ClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclaration(\n");

        if(Declaration!=null)
            buffer.append(Declaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDecl!=null)
            buffer.append(ClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclaration]");
        return buffer.toString();
    }
}
