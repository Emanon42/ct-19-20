package ast;

public class While extends Stmt{
    public final Expr expr;
    public final Stmt stmt;
    public While(Expr e, Stmt s){
        this.expr = e;
        this.stmt = s;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitWhile(this);
    }

    @Override
    public boolean isBlock() {
        return false;
    }

    @Override
    public boolean isWhile() {
        return true;
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
        return false;
    }
}
