package ast;

public abstract class Stmt implements ASTNode {
    public abstract <T> T accept(ASTVisitor<T> v);

    public abstract boolean isBlock();

    public abstract boolean isWhile();

    public abstract boolean isIf();

    public abstract boolean isReturn();

    public abstract boolean isAssign();

    public abstract boolean isExprStmt();

}
