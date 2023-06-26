// generated with ast extension for cup
// version 0.8
// 25/5/2023 23:50:18


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodCurly extends ClassConstrMethod {

    private ClassMethod ClassMethod;

    public ClassMethodCurly (ClassMethod ClassMethod) {
        this.ClassMethod=ClassMethod;
        if(ClassMethod!=null) ClassMethod.setParent(this);
    }

    public ClassMethod getClassMethod() {
        return ClassMethod;
    }

    public void setClassMethod(ClassMethod ClassMethod) {
        this.ClassMethod=ClassMethod;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassMethod!=null) ClassMethod.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassMethod!=null) ClassMethod.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassMethod!=null) ClassMethod.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassMethodCurly(\n");

        if(ClassMethod!=null)
            buffer.append(ClassMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodCurly]");
        return buffer.toString();
    }
}
