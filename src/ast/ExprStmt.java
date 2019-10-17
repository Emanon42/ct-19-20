package ast;

public class ExprStmt extends Stmt{
    public final Expr expr;
    public ExprStmt(Expr e){
        this.expr = e;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitExprStmt(this);
    }

}
