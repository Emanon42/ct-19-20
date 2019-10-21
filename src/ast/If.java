package ast;

public class If extends Stmt{
    public final Expr expr;
    public final Stmt stmt;
    public final Stmt elseStmt;
    public If(Expr e, Stmt s1, Stmt elseStmt){
        this.expr = e;
        this.stmt = s1;
        this.elseStmt = elseStmt;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitIf(this);
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
        return true;
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
