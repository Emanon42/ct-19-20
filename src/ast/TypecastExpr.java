package ast;

public class TypecastExpr implements ASTNode{
    public final Type type;
    public final Expr expr;
    public TypecastExpr(Type t, Expr e){
        this.expr = e;
        this.type = t;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitTypecastExpr(this);
    }
}