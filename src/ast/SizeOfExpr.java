package ast;

public class SizeOfExpr extends Expr{
    public final Type toCheck;
    public SizeOfExpr(Type t){
        this.toCheck = t;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitSizeOfExpr(this);
    }

    @Override
    public boolean isLegalLeftForAssign() {
        return false;
    }
}