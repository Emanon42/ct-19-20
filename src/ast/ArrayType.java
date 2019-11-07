package ast;

public class ArrayType implements Type {

    public final Type innerType;
    public final int capacity;

    public ArrayType(Type t, int i){
        this.innerType = t;
        this.capacity = i;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitArrayType(this);
    }

    @Override
    public boolean isStructType() {
        return false;
    }

    @Override
    public boolean isBaseType() {
        return false;
    }

    @Override
    public boolean isPointerType() {
        return false;
    }

    @Override
    public boolean isArrayType() {
        return true;
    }

    @Override
    public int sizeof() {
        double compact = capacity * innerType.sizeof();
        int aligned = (int) (4.0 * Math.ceil(compact / 4.0));
        return aligned;
    }

    @Override
    public String toString(){
        return innerType.toString() + Integer.toString(capacity);
    }

}
