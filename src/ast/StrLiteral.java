package ast;

public class StrLiteral {
    public final String value;
    public StrLiteral(String s){
        this.value = s;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitStrLiteral(this);
    }
}
