// generated with ast extension for cup
// version 0.8
// 27/5/2023 16:47:13


package rs.ac.bg.etf.pp1.ast;

public class MapStmt extends Statement {

    private Designator Designator;
    private Assignop Assignop;
    private DesignatorArr2 DesignatorArr2;
    private MapIdent MapIdent;
    private Expr Expr;
    private DummyMap DummyMap;
    private MapEnd MapEnd;

    public MapStmt (Designator Designator, Assignop Assignop, DesignatorArr2 DesignatorArr2, MapIdent MapIdent, Expr Expr, DummyMap DummyMap, MapEnd MapEnd) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.DesignatorArr2=DesignatorArr2;
        if(DesignatorArr2!=null) DesignatorArr2.setParent(this);
        this.MapIdent=MapIdent;
        if(MapIdent!=null) MapIdent.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.DummyMap=DummyMap;
        if(DummyMap!=null) DummyMap.setParent(this);
        this.MapEnd=MapEnd;
        if(MapEnd!=null) MapEnd.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public DesignatorArr2 getDesignatorArr2() {
        return DesignatorArr2;
    }

    public void setDesignatorArr2(DesignatorArr2 DesignatorArr2) {
        this.DesignatorArr2=DesignatorArr2;
    }

    public MapIdent getMapIdent() {
        return MapIdent;
    }

    public void setMapIdent(MapIdent MapIdent) {
        this.MapIdent=MapIdent;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public DummyMap getDummyMap() {
        return DummyMap;
    }

    public void setDummyMap(DummyMap DummyMap) {
        this.DummyMap=DummyMap;
    }

    public MapEnd getMapEnd() {
        return MapEnd;
    }

    public void setMapEnd(MapEnd MapEnd) {
        this.MapEnd=MapEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(DesignatorArr2!=null) DesignatorArr2.accept(visitor);
        if(MapIdent!=null) MapIdent.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(DummyMap!=null) DummyMap.accept(visitor);
        if(MapEnd!=null) MapEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(DesignatorArr2!=null) DesignatorArr2.traverseTopDown(visitor);
        if(MapIdent!=null) MapIdent.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(DummyMap!=null) DummyMap.traverseTopDown(visitor);
        if(MapEnd!=null) MapEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(DesignatorArr2!=null) DesignatorArr2.traverseBottomUp(visitor);
        if(MapIdent!=null) MapIdent.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(DummyMap!=null) DummyMap.traverseBottomUp(visitor);
        if(MapEnd!=null) MapEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MapStmt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorArr2!=null)
            buffer.append(DesignatorArr2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MapIdent!=null)
            buffer.append(MapIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyMap!=null)
            buffer.append(DummyMap.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MapEnd!=null)
            buffer.append(MapEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MapStmt]");
        return buffer.toString();
    }
}
