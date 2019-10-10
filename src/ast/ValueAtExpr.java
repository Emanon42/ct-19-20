package ast;

public class ValueAtExpr implements ASTNode{
    public final Expr expr;
    public ValueAtExpr(Expr e){
        this.expr = e;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitValueAtExpr(this);
    }
}