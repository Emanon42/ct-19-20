package ast;

public class StrLiteral extends Expr{
    public final String value;
    public String asmLabel;
    public StrLiteral(String s){
        this.value = s;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitStrLiteral(this);
    }

    @Override
    public boolean isLegalLeftForAssign() {
        return false;
    }

    public String extractValue() {
        return this.value.replace("\\", "\\\\")
                         .replace("\t", "\\t")
                         .replace("\b", "\\b")
                         .replace("\n", "\\n")
                         .replace("\r", "\\r")
                         .replace("\f", "\\f")
                         .replace("\"", "\\\"")
                         .replace("\0", "\\0");
    }
}
