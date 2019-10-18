package ast;

public class BinOp extends Expr{
    public final Expr lhs;
    public final Expr rhs;
    public final Op operator;
    public BinOp(Expr lhs, Op op, Expr rhs){
        this.lhs = lhs;
        this.operator = op;
        this.rhs = rhs;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitBinOp(this);
    }
}