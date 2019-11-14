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
    public int alignedSize() {
        int size = 0;

        // Each field for a struct must be 4-byte aligned
        for (VarDecl v : decl.varDecls) {
            size += (int) (4.0 * Math.ceil((double)v.type.alignedSize() / 4.0));
        }
        return size;
    }

    @Override
    public int realSize() {
        int size = 0;

        // Each field for a struct must be 4-byte aligned
        for (VarDecl v : decl.varDecls) {
            size += v.type.realSize();
        }
        return size;
    }

    @Override
    public String toString(){
        return "struct"+ident;
    }

    public StructType(String ident){
        this.ident = ident;
    }
}
