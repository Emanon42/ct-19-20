package ast;

public class ChrLiteral extends Expr{
    public final char value;
    public ChrLiteral(char c){
        this.value = c;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitChrLiteral(this);
    }

    @Override
    public boolean isLegalLeftForAssign() {
        return false;
    }
}
