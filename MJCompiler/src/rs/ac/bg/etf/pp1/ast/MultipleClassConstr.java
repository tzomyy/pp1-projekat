// generated with ast extension for cup
// version 0.8
// 2/1/2023 1:48:41


package rs.ac.bg.etf.pp1.ast;

public class MultipleClassConstr extends ClassConstr {

    private ClassConstr ClassConstr;
    private ConstructorDecl ConstructorDecl;

    public MultipleClassConstr (ClassConstr ClassConstr, ConstructorDecl ConstructorDecl) {
        this.ClassConstr=ClassConstr;
        if(ClassConstr!=null) ClassConstr.setParent(this);
        this.ConstructorDecl=ConstructorDecl;
        if(ConstructorDecl!=null) ConstructorDecl.setParent(this);
    }

    public ClassConstr getClassConstr() {
        return ClassConstr;
    }

    public void setClassConstr(ClassConstr ClassConstr) {
        this.ClassConstr=ClassConstr;
    }

    public ConstructorDecl getConstructorDecl() {
        return ConstructorDecl;
    }

    public void setConstructorDecl(ConstructorDecl ConstructorDecl) {
        this.ConstructorDecl=ConstructorDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassConstr!=null) ClassConstr.accept(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassConstr!=null) ClassConstr.traverseTopDown(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassConstr!=null) ClassConstr.traverseBottomUp(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleClassConstr(\n");

        if(ClassConstr!=null)
            buffer.append(ClassConstr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorDecl!=null)
            buffer.append(ConstructorDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleClassConstr]");
        return buffer.toString();
    }
}
