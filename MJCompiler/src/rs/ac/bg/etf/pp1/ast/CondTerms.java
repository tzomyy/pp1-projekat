// generated with ast extension for cup
// version 0.8
// 23/5/2023 18:21:34


package rs.ac.bg.etf.pp1.ast;

public class CondTerms extends CondTerm {

    private CondFact CondFact;
    private CondFact CondFact1;

    public CondTerms (CondFact CondFact, CondFact CondFact1) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.CondFact1=CondFact1;
        if(CondFact1!=null) CondFact1.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public CondFact getCondFact1() {
        return CondFact1;
    }

    public void setCondFact1(CondFact CondFact1) {
        this.CondFact1=CondFact1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFact!=null) CondFact.accept(visitor);
        if(CondFact1!=null) CondFact1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(CondFact1!=null) CondFact1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(CondFact1!=null) CondFact1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTerms(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact1!=null)
            buffer.append(CondFact1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTerms]");
        return buffer.toString();
    }
}
