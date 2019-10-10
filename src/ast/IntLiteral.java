package ast;

public class IntLiteral implements ASTNode {
    public final int value;
    public IntLiteral(int i){
        this.value = i;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitIntLiteral(this);
    }

}
