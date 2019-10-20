package ast;

public class PointerType implements Type {

    public final Type type;

    public <T> T accept(ASTVisitor<T> v){
        return v.visitPointerType(this);
    }

    @Override
    public boolean isStructType() {
        return false;
    }

    public PointerType(Type type){
        this.type = type;

    }

    @Override
    public String toString(){
        return "*"+type.toString();
    }
}
