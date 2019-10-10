package ast;

public class ArrayAccessExpr implements ASTNode{
    public final Expr array;
    public final Expr index;

    public ArrayAccessExpr(Expr array, Expr index){
        this.array = array;
        this.index = index;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitArrayAccessExpr(this);
    }

}