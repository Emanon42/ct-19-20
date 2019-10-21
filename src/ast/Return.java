package ast;

public class Return extends Stmt{
    public final Expr expr;
    public Return(Expr e){
        this.expr = e;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitReturn(this);
    }

    @Override
    public boolean isBlock() {
        return false;
    }

    @Override
    public boolean isWhile() {
        return false;
    }

    @Override
    public boolean isIf() {
        return false;
    }

    @Override
    public boolean isReturn() {
        return true;
    }

    @Override
    public boolean isAssign() {
        return false;
    }

    @Override
    public boolean isExprStmt() {
        return false;
    }
}
