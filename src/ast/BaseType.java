package ast;

import lexer.Token;

public enum BaseType implements Type {
    INT, CHAR, VOID;

    public <T> T accept(ASTVisitor<T> v) {
        return v.visitBaseType(this);
    }

    @Override
    public boolean isStructType() {
        return false;
    }

    @Override
    public String toString(){
        switch(this) {
            case INT: return "INT";
            case CHAR: return "CHAR";
            case VOID: return "VOID";
            default: throw new IllegalArgumentException();
        }
    }

    public static BaseType fromTokenClass(Token.TokenClass tc){
        if (tc == Token.TokenClass.INT) {
            return INT;
        } else if (tc == Token.TokenClass.CHAR) {
            return CHAR;
        } else if (tc == Token.TokenClass.VOID) {
            return VOID;
        }

        return null;
    }
}
