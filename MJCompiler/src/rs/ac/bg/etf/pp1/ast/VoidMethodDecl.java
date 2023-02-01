// generated with ast extension for cup
// version 0.8
// 1/1/2023 21:39:54


package rs.ac.bg.etf.pp1.ast;

public class VoidMethodDecl extends MultipleMethodDecl {

    private String methodName;
    private MethodFormPars MethodFormPars;
    private VarDecl VarDecl;
    private MethodStatement MethodStatement;

    public VoidMethodDecl (String methodName, MethodFormPars MethodFormPars, VarDecl VarDecl, MethodStatement MethodStatement) {
        this.methodName=methodName;
        this.MethodFormPars=MethodFormPars;
        if(MethodFormPars!=null) MethodFormPars.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
        this.MethodStatement=MethodStatement;
        if(MethodStatement!=null) MethodStatement.setParent(this);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName=methodName;
    }

    public MethodFormPars getMethodFormPars() {
        return MethodFormPars;
    }

    public void setMethodFormPars(MethodFormPars MethodFormPars) {
        this.MethodFormPars=MethodFormPars;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
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
        if(MethodFormPars!=null) MethodFormPars.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
        if(MethodStatement!=null) MethodStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodFormPars!=null) MethodFormPars.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
        if(MethodStatement!=null) MethodStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodFormPars!=null) MethodFormPars.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        if(MethodStatement!=null) MethodStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VoidMethodDecl(\n");

        buffer.append(" "+tab+methodName);
        buffer.append("\n");

        if(MethodFormPars!=null)
            buffer.append(MethodFormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
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
