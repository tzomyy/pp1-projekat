// generated with ast extension for cup
// version 0.8
// 18/4/2023 0:23:42


package rs.ac.bg.etf.pp1.ast;

public class VoidMethodDecl extends MethodDecl {

    private VoidMethod VoidMethod;
    private MethodFormPars MethodFormPars;
    private MethodVarDecl MethodVarDecl;
    private MethodStatement MethodStatement;

    public VoidMethodDecl (VoidMethod VoidMethod, MethodFormPars MethodFormPars, MethodVarDecl MethodVarDecl, MethodStatement MethodStatement) {
        this.VoidMethod=VoidMethod;
        if(VoidMethod!=null) VoidMethod.setParent(this);
        this.MethodFormPars=MethodFormPars;
        if(MethodFormPars!=null) MethodFormPars.setParent(this);
        this.MethodVarDecl=MethodVarDecl;
        if(MethodVarDecl!=null) MethodVarDecl.setParent(this);
        this.MethodStatement=MethodStatement;
        if(MethodStatement!=null) MethodStatement.setParent(this);
    }

    public VoidMethod getVoidMethod() {
        return VoidMethod;
    }

    public void setVoidMethod(VoidMethod VoidMethod) {
        this.VoidMethod=VoidMethod;
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
        if(VoidMethod!=null) VoidMethod.accept(visitor);
        if(MethodFormPars!=null) MethodFormPars.accept(visitor);
        if(MethodVarDecl!=null) MethodVarDecl.accept(visitor);
        if(MethodStatement!=null) MethodStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VoidMethod!=null) VoidMethod.traverseTopDown(visitor);
        if(MethodFormPars!=null) MethodFormPars.traverseTopDown(visitor);
        if(MethodVarDecl!=null) MethodVarDecl.traverseTopDown(visitor);
        if(MethodStatement!=null) MethodStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VoidMethod!=null) VoidMethod.traverseBottomUp(visitor);
        if(MethodFormPars!=null) MethodFormPars.traverseBottomUp(visitor);
        if(MethodVarDecl!=null) MethodVarDecl.traverseBottomUp(visitor);
        if(MethodStatement!=null) MethodStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VoidMethodDecl(\n");

        if(VoidMethod!=null)
            buffer.append(VoidMethod.toString("  "+tab));
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
        buffer.append(") [VoidMethodDecl]");
        return buffer.toString();
    }
}
