package ast;

public class ArrayType implements Type {

    public final Type type;
    public final int index;

    public ArrayType(Type t, int i){
        this.type = t;
        this.index = i;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitArrayType(this);
    }

}
