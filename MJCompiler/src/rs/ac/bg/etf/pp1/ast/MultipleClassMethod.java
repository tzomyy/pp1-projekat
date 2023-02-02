// generated with ast extension for cup
// version 0.8
// 3/1/2023 0:1:27


package rs.ac.bg.etf.pp1.ast;

public class MultipleClassMethod extends ClassMethod {

    private ClassMethod ClassMethod;
    private MethodDecl MethodDecl;

    public MultipleClassMethod (ClassMethod ClassMethod, MethodDecl MethodDecl) {
        this.ClassMethod=ClassMethod;
        if(ClassMethod!=null) ClassMethod.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public ClassMethod getClassMethod() {
        return ClassMethod;
    }

    public void setClassMethod(ClassMethod ClassMethod) {
        this.ClassMethod=ClassMethod;
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassMethod!=null) ClassMethod.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassMethod!=null) ClassMethod.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassMethod!=null) ClassMethod.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleClassMethod(\n");

        if(ClassMethod!=null)
            buffer.append(ClassMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleClassMethod]");
        return buffer.toString();
    }
}
