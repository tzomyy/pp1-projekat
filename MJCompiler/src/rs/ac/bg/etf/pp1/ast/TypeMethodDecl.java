// generated with ast extension for cup
// version 0.8
// 18/4/2023 0:1:7


package rs.ac.bg.etf.pp1.ast;

public class TypeMethodDecl extends MethodDecl {

    private TypeMethod TypeMethod;
    private MethodFormPars MethodFormPars;
    private MethodVarDecl MethodVarDecl;
    private MethodStatement MethodStatement;

    public TypeMethodDecl (TypeMethod TypeMethod, MethodFormPars MethodFormPars, MethodVarDecl MethodVarDecl, MethodStatement MethodStatement) {
        this.TypeMethod=TypeMethod;
        if(TypeMethod!=null) TypeMethod.setParent(this);
        this.MethodFormPars=MethodFormPars;
        if(MethodFormPars!=null) MethodFormPars.setParent(this);
        this.MethodVarDecl=MethodVarDecl;
        if(MethodVarDecl!=null) MethodVarDecl.setParent(this);
        this.MethodStatement=MethodStatement;
        if(MethodStatement!=null) MethodStatement.setParent(this);
    }

    public TypeMethod getTypeMethod() {
        return TypeMethod;
    }

    public void setTypeMethod(TypeMethod TypeMethod) {
        this.TypeMethod=TypeMethod;
    }

    public MethodFormPars getMethodFormPars() {
        return MethodFormPars;
    }

    public void setMethodFormPars(MethodFormPars MethodFormPars) {
        this.MethodFormPars=MethodFormPars;
    }

    public MethodVarDecl getMethodVarDecl() {
        return MethodVarDecl;
    }

    public void setMethodVarDecl(MethodVarDecl MethodVarDecl) {
        this.MethodVarDecl=MethodVarDecl;
    }

    public MethodStatement getMethodStatement() {
        return MethodStatement;
    }

    public void setMethodStatement(MethodStatement MethodStatement) {
        this.MethodStatement=MethodStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TypeMethod!=null) TypeMethod.accept(visitor);
        if(MethodFormPars!=null) MethodFormPars.accept(visitor);
        if(MethodVarDecl!=null) MethodVarDecl.accept(visitor);
        if(MethodStatement!=null) MethodStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeMethod!=null) TypeMethod.traverseTopDown(visitor);
        if(MethodFormPars!=null) MethodFormPars.traverseTopDown(visitor);
        if(MethodVarDecl!=null) MethodVarDecl.traverseTopDown(visitor);
        if(MethodStatement!=null) MethodStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeMethod!=null) TypeMethod.traverseBottomUp(visitor);
        if(MethodFormPars!=null) MethodFormPars.traverseBottomUp(visitor);
        if(MethodVarDecl!=null) MethodVarDecl.traverseBottomUp(visitor);
        if(MethodStatement!=null) MethodStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TypeMethodDecl(\n");

        if(TypeMethod!=null)
            buffer.append(TypeMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodFormPars!=null)
            buffer.append(MethodFormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodVarDecl!=null)
            buffer.append(MethodVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodStatement!=null)
            buffer.append(MethodStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TypeMethodDecl]");
        return buffer.toString();
    }
}
