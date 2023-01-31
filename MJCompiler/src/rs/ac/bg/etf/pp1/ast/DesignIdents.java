// generated with ast extension for cup
// version 0.8
// 31/0/2023 3:11:29


package rs.ac.bg.etf.pp1.ast;

public class DesignIdents extends MultipleDesignator {

    private MultipleDesignator MultipleDesignator;
    private SingleDesignator SingleDesignator;

    public DesignIdents (MultipleDesignator MultipleDesignator, SingleDesignator SingleDesignator) {
        this.MultipleDesignator=MultipleDesignator;
        if(MultipleDesignator!=null) MultipleDesignator.setParent(this);
        this.SingleDesignator=SingleDesignator;
        if(SingleDesignator!=null) SingleDesignator.setParent(this);
    }

    public MultipleDesignator getMultipleDesignator() {
        return MultipleDesignator;
    }

    public void setMultipleDesignator(MultipleDesignator MultipleDesignator) {
        this.MultipleDesignator=MultipleDesignator;
    }

    public SingleDesignator getSingleDesignator() {
        return SingleDesignator;
    }

    public void setSingleDesignator(SingleDesignator SingleDesignator) {
        this.SingleDesignator=SingleDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MultipleDesignator!=null) MultipleDesignator.accept(visitor);
        if(SingleDesignator!=null) SingleDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultipleDesignator!=null) MultipleDesignator.traverseTopDown(visitor);
        if(SingleDesignator!=null) SingleDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultipleDesignator!=null) MultipleDesignator.traverseBottomUp(visitor);
        if(SingleDesignator!=null) SingleDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignIdents(\n");

        if(MultipleDesignator!=null)
            buffer.append(MultipleDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleDesignator!=null)
            buffer.append(SingleDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignIdents]");
        return buffer.toString();
    }
}
