package ast;

public interface ASTNode {
    // the generic parameter of ASTVisitor decides which pass (e.g. print, eval) it uses.
    public <T> T accept(ASTVisitor<T> v);
}
