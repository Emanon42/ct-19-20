package ast;

import java.util.List;

public class FunCallExpr {
    public final String ident;
    public final List<Expr> exprs;
    public FunCallExpr(String s, List<Expr> exprs){
        this.ident = s;
        this.exprs = exprs;
    }

    public <T> T accept(ASTVisitor<T> v){
        return v.visitFunCallExpr(this);
    }
}