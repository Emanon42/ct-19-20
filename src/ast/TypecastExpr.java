package ast;

public class TypecastExpr extends Expr{
    public final Type target;
    public final Expr fromExpr;
    public TypecastExpr(Type t, Expr e){
        this.fromExpr = e;
        this.target = t;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitTypecastExpr(this);
    }

    @Override
    public boolean isLegalLeftForAssign() {
        return false;
    }
}