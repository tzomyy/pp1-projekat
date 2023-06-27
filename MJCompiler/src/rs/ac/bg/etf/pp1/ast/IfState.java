// generated with ast extension for cup
// version 0.8
// 27/5/2023 16:47:13


package rs.ac.bg.etf.pp1.ast;

public class IfState extends IfStatement {

    private DummyBeforeIf DummyBeforeIf;
    private BeforeIFConditionDummy BeforeIFConditionDummy;
    private Condition Condition;
    private AfterIFConditionDummy AfterIFConditionDummy;

    public IfState (DummyBeforeIf DummyBeforeIf, BeforeIFConditionDummy BeforeIFConditionDummy, Condition Condition, AfterIFConditionDummy AfterIFConditionDummy) {
        this.DummyBeforeIf=DummyBeforeIf;
        if(DummyBeforeIf!=null) DummyBeforeIf.setParent(this);
        this.BeforeIFConditionDummy=BeforeIFConditionDummy;
        if(BeforeIFConditionDummy!=null) BeforeIFConditionDummy.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.AfterIFConditionDummy=AfterIFConditionDummy;
        if(AfterIFConditionDummy!=null) AfterIFConditionDummy.setParent(this);
    }

    public DummyBeforeIf getDummyBeforeIf() {
        return DummyBeforeIf;
    }

    public void setDummyBeforeIf(DummyBeforeIf DummyBeforeIf) {
        this.DummyBeforeIf=DummyBeforeIf;
    }

    public BeforeIFConditionDummy getBeforeIFConditionDummy() {
        return BeforeIFConditionDummy;
    }

    public void setBeforeIFConditionDummy(BeforeIFConditionDummy BeforeIFConditionDummy) {
        this.BeforeIFConditionDummy=BeforeIFConditionDummy;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public AfterIFConditionDummy getAfterIFConditionDummy() {
        return AfterIFConditionDummy;
    }

    public void setAfterIFConditionDummy(AfterIFConditionDummy AfterIFConditionDummy) {
        this.AfterIFConditionDummy=AfterIFConditionDummy;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DummyBeforeIf!=null) DummyBeforeIf.accept(visitor);
        if(BeforeIFConditionDummy!=null) BeforeIFConditionDummy.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(AfterIFConditionDummy!=null) AfterIFConditionDummy.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DummyBeforeIf!=null) DummyBeforeIf.traverseTopDown(visitor);
        if(BeforeIFConditionDummy!=null) BeforeIFConditionDummy.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(AfterIFConditionDummy!=null) AfterIFConditionDummy.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DummyBeforeIf!=null) DummyBeforeIf.traverseBottomUp(visitor);
        if(BeforeIFConditionDummy!=null) BeforeIFConditionDummy.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(AfterIFConditionDummy!=null) AfterIFConditionDummy.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfState(\n");

        if(DummyBeforeIf!=null)
            buffer.append(DummyBeforeIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BeforeIFConditionDummy!=null)
            buffer.append(BeforeIFConditionDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AfterIFConditionDummy!=null)
            buffer.append(AfterIFConditionDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfState]");
        return buffer.toString();
    }
}
