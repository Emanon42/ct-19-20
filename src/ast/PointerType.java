package ast;

public class PointerType implements Type {

    public final Type type;

    public <T> T accept(ASTVisitor<T> v){
        return v.visitPointerType(this);
    }

    public PointerType(Type type){
        this.type = type;

    }
}
