package ast;

public interface Type extends ASTNode {

    public <T> T accept(ASTVisitor<T> v);

    public boolean isStructType();

    public boolean isBaseType();

    public boolean isPointerType();

    public boolean isArrayType();

    public int sizeof();

    public int realSize();

}
