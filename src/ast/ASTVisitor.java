package ast;

public interface ASTVisitor<T> {
    // each method corresponds to an AST node (where they are called)
    public T visitBaseType(BaseType bt);
    public T visitStructTypeDecl(StructTypeDecl st);
    public T visitBlock(Block b);
    public T visitFunDecl(FunDecl p);
    public T visitProgram(Program p);
    public T visitVarDecl(VarDecl vd);
    public T visitVarExpr(VarExpr v);
    public T visitPointerType(PointerType pt);
    public T visitStructType(StructType st);
    public T visitArrayType(ArrayType at);
    public T visitIntLiteral(IntLiteral il);
    public T visitStrLiteral(StrLiteral sl);
    public T visitChrLiteral(ChrLiteral cl);
    public T visitFunCallExpr(FunCallExpr fce);
    public T visitBinOp(BinOp bo);
    public T visitOp(Op o);
    public T visitArrayAccessExpr(ArrayAccessExpr aae);
    public T visitFieldAccessExpr(FieldAccessExpr fae);
    public T visitValueAtExpr(ValueAtExpr vae);
    public T visitSizeOfExpr(SizeOfExpr soe);
    public T visitTypecastExpr(TypecastExpr te);

    // to complete ... (should have one visit method for each concrete AST node class)
}
