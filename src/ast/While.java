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
}
