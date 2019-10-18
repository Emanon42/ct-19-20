package ast;

import lexer.Token;

public enum Op implements ASTNode{
    ADD, SUB, MUL, DIV, MOD, GT, LT, GE, LE, NE, EQ, OR, AND;

    public <T> T accept(ASTVisitor<T> v) {
        return v.visitOp(this);
    }

    public static Op fromTokenClass(Token.TokenClass t) {
        switch (t) {
            case PLUS: return ADD;
            case MINUS: return SUB;
            case ASTERIX: return MUL;
            case DIV: return DIV;
            case REM: return MOD;
            case GT: return GT;
            case LT: return LT;
            case GE: return GE;
            case LE: return LE;
            case NE: return NE;
            case EQ: return EQ;
            case OR: return OR;
            case AND: return AND;
            default: return null;
        }
    }
}
