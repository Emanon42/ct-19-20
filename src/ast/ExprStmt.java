package ast;

public class ExprStmt extends Stmt{
    public final Expr expr;
    public ExprStmt(Expr e){
        this.expr = e;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitExprStmt(this);
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
        return false;
    }

    @Override
    public boolean isAssign() {
        return false;
    }

    @Override
    public boolean isExprStmt() {
        return true;
    }

}
