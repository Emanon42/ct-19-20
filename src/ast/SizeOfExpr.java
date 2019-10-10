package ast;

public class SizeOfExpr implements ASTNode{
    public final Type type;
    public SizeOfExpr(Type t){
        this.type = t;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitSizeOfExpr(this);
    }
}