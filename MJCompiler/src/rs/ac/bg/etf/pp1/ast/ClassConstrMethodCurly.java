// generated with ast extension for cup
// version 0.8
// 6/1/2023 2:37:25


package rs.ac.bg.etf.pp1.ast;

public class ClassConstrMethodCurly extends ClassConstrMethod {

    private ClassConstr ClassConstr;
    private ClassMethod ClassMethod;

    public ClassConstrMethodCurly (ClassConstr ClassConstr, ClassMethod ClassMethod) {
        this.ClassConstr=ClassConstr;
        if(ClassConstr!=null) ClassConstr.setParent(this);
        this.ClassMethod=ClassMethod;
        if(ClassMethod!=null) ClassMethod.setParent(this);
    }

    public ClassConstr getClassConstr() {
        return ClassConstr;
    }

    public void setClassConstr(ClassConstr ClassConstr) {
        this.ClassConstr=ClassConstr;
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
        if(ClassConstr!=null) ClassConstr.accept(visitor);
        if(ClassMethod!=null) ClassMethod.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassConstr!=null) ClassConstr.traverseTopDown(visitor);
        if(ClassMethod!=null) ClassMethod.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassConstr!=null) ClassConstr.traverseBottomUp(visitor);
        if(ClassMethod!=null) ClassMethod.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassConstrMethodCurly(\n");

        if(ClassConstr!=null)
            buffer.append(ClassConstr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethod!=null)
            buffer.append(ClassMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassConstrMethodCurly]");
        return buffer.toString();
    }
}
