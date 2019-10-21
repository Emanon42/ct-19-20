package ast;

public class ValueAtExpr extends Expr{
    public final Expr toDeref;
    public ValueAtExpr(Expr e){
        this.toDeref = e;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitValueAtExpr(this);
    }

    @Override
    public boolean isLegalLeftForAssign() {
        return true;
    }
}