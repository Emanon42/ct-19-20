package ast;

import java.util.List;

public class FunCallExpr extends Expr{
    public final String ident;
    public final List<Expr> exprs;
    public FunDecl fd; // to be filled in by the name analyser
    public FunCallExpr(String s, List<Expr> exprs){
        this.ident = s;
        this.exprs = exprs;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitFunCallExpr(this);
    }

    @Override
    public boolean isLegalLeftForAssign() {
        return false;
    }
}