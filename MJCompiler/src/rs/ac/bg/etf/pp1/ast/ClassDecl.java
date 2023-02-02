// generated with ast extension for cup
// version 0.8
// 2/1/2023 23:50:43


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private ClassExtends ClassExtends;
    private ClassVarDecl ClassVarDecl;
    private ClassConstrMethod ClassConstrMethod;

    public ClassDecl (String I1, ClassExtends ClassExtends, ClassVarDecl ClassVarDecl, ClassConstrMethod ClassConstrMethod) {
        this.I1=I1;
        this.ClassExtends=ClassExtends;
        if(ClassExtends!=null) ClassExtends.setParent(this);
        this.ClassVarDecl=ClassVarDecl;
        if(ClassVarDecl!=null) ClassVarDecl.setParent(this);
        this.ClassConstrMethod=ClassConstrMethod;
        if(ClassConstrMethod!=null) ClassConstrMethod.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ClassExtends getClassExtends() {
        return ClassExtends;
    }

    public void setClassExtends(ClassExtends ClassExtends) {
        this.ClassExtends=ClassExtends;
    }

    public ClassVarDecl getClassVarDecl() {
        return ClassVarDecl;
    }

    public void setClassVarDecl(ClassVarDecl ClassVarDecl) {
        this.ClassVarDecl=ClassVarDecl;
    }

    public ClassConstrMethod getClassConstrMethod() {
        return ClassConstrMethod;
    }

    public void setClassConstrMethod(ClassConstrMethod ClassConstrMethod) {
        this.ClassConstrMethod=ClassConstrMethod;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassExtends!=null) ClassExtends.accept(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.accept(visitor);
        if(ClassConstrMethod!=null) ClassConstrMethod.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassExtends!=null) ClassExtends.traverseTopDown(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.traverseTopDown(visitor);
        if(ClassConstrMethod!=null) ClassConstrMethod.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassExtends!=null) ClassExtends.traverseBottomUp(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.traverseBottomUp(visitor);
        if(ClassConstrMethod!=null) ClassConstrMethod.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ClassExtends!=null)
            buffer.append(ClassExtends.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDecl!=null)
            buffer.append(ClassVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassConstrMethod!=null)
            buffer.append(ClassConstrMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
