package ast;

public class FieldAccessExpr extends Expr{
    public final Expr stru;
    public final String field;

    public FieldAccessExpr(Expr stru, String field){
        this.stru = stru;
        this.field = field;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitFieldAccessExpr(this);
    }
}