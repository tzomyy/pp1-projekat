// generated with ast extension for cup
// version 0.8
// 5/1/2023 17:59:25


package rs.ac.bg.etf.pp1.ast;

public class ClassConstrCurly extends ClassConstrMethod {

    private ClassConstr ClassConstr;

    public ClassConstrCurly (ClassConstr ClassConstr) {
        this.ClassConstr=ClassConstr;
        if(ClassConstr!=null) ClassConstr.setParent(this);
    }

    public ClassConstr getClassConstr() {
        return ClassConstr;
    }

    public void setClassConstr(ClassConstr ClassConstr) {
        this.ClassConstr=ClassConstr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassConstr!=null) ClassConstr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassConstr!=null) ClassConstr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassConstr!=null) ClassConstr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassConstrCurly(\n");

        if(ClassConstr!=null)
            buffer.append(ClassConstr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassConstrCurly]");
        return buffer.toString();
    }
}
