package ast;

public class StructType implements Type{

    public final String ident;
    public StructTypeDecl decl; // to be filled in by the name/type analyser
    public <T> T accept(ASTVisitor<T> v){
        return v.visitStructType(this);
    }

    @Override
    public boolean isStructType() {
        return true;
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
        return false;
    }

    @Override
    public String toString(){
        return "struct"+ident;
    }

    public StructType(String ident){
        this.ident = ident;
    }
}
