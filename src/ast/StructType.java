package ast;

public class StructType implements Type{

    public final String ident;
    public <T> T accept(ASTVisitor<T> v){
        return v.visitStructType(this);
    }

    public StructType(String ident){
        this.ident = ident;
    }
}
