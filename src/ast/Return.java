package ast;

public class Return extends Stmt{
    public final Expr expr;
    public Return(Expr e){
        this.expr = e;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitReturn(this);
    }
}
