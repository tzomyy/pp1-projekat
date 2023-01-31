// generated with ast extension for cup
// version 0.8
// 31/0/2023 3:11:29


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private SingleDesignator SingleDesignator;
    private MultipleDesignator MultipleDesignator;

    public Designator (SingleDesignator SingleDesignator, MultipleDesignator MultipleDesignator) {
        this.SingleDesignator=SingleDesignator;
        if(SingleDesignator!=null) SingleDesignator.setParent(this);
        this.MultipleDesignator=MultipleDesignator;
        if(MultipleDesignator!=null) MultipleDesignator.setParent(this);
    }

    public SingleDesignator getSingleDesignator() {
        return SingleDesignator;
    }

    public void setSingleDesignator(SingleDesignator SingleDesignator) {
        this.SingleDesignator=SingleDesignator;
    }

    public MultipleDesignator getMultipleDesignator() {
        return MultipleDesignator;
    }

    public void setMultipleDesignator(MultipleDesignator MultipleDesignator) {
        this.MultipleDesignator=MultipleDesignator;
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
        if(SingleDesignator!=null) SingleDesignator.accept(visitor);
        if(MultipleDesignator!=null) MultipleDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleDesignator!=null) SingleDesignator.traverseTopDown(visitor);
        if(MultipleDesignator!=null) MultipleDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleDesignator!=null) SingleDesignator.traverseBottomUp(visitor);
        if(MultipleDesignator!=null) MultipleDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        if(SingleDesignator!=null)
            buffer.append(SingleDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MultipleDesignator!=null)
            buffer.append(MultipleDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
