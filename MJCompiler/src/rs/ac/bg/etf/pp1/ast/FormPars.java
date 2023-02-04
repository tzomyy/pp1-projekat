// generated with ast extension for cup
// version 0.8
// 4/1/2023 18:33:11


package rs.ac.bg.etf.pp1.ast;

public class FormPars implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MultipleFormPar MultipleFormPar;

    public FormPars (MultipleFormPar MultipleFormPar) {
        this.MultipleFormPar=MultipleFormPar;
        if(MultipleFormPar!=null) MultipleFormPar.setParent(this);
    }

    public MultipleFormPar getMultipleFormPar() {
        return MultipleFormPar;
    }

    public void setMultipleFormPar(MultipleFormPar MultipleFormPar) {
        this.MultipleFormPar=MultipleFormPar;
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
        if(MultipleFormPar!=null) MultipleFormPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultipleFormPar!=null) MultipleFormPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultipleFormPar!=null) MultipleFormPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPars(\n");

        if(MultipleFormPar!=null)
            buffer.append(MultipleFormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPars]");
        return buffer.toString();
    }
}
