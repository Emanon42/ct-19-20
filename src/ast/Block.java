package ast;

import java.util.List;

public class Block extends Stmt {

    public final List<VarDecl> varDecls;
    public final List<Stmt> stmts;

    public Block(List<VarDecl> varDecls, List<Stmt> stmts) {
        this.varDecls = varDecls;
        this.stmts = stmts;
    }

    public <T> T accept(ASTVisitor<T> v) {
	    return v.visitBlock(this);
    }

    @Override
    public boolean isBlock() {
        return true;
    }

    @Override
    public boolean isWhile() {
        return false;
    }

    @Override
    public boolean isIf() {
        return false;
    }

    @Override
    public boolean isReturn() {
        return false;
    }

    @Override
    public boolean isAssign() {
        return false;
    }

    @Override
    public boolean isExprStmt() {
        return false;
    }
}
