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
    public boolean isBaseType() {
        return true;
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
        switch(this) {
            case INT: return "INT";
            case CHAR: return "CHAR";
            case VOID: return "VOID";
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public int alignedSize() {
        switch (this) {
            case INT:
                return 4;
            case CHAR:
                return 4;
            case VOID:
                return 0;
            default:
                throw new RuntimeException("Sizeof called on " + this.toString());
        }
    }

    @Override
    public int realSize() {
        switch (this) {
            case INT:
                return 4;
            case CHAR:
                return 1;
            case VOID:
                return 0;
            default:
                throw new RuntimeException("Sizeof called on " + this.toString());
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
