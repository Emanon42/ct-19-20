package gen;
import ast.*;

import java.util.List;

public class DataVisitor implements ASTVisitor<Void> {
    private AsmWritter writer;
    private LabelManager labelTable;

    public DataVisitor(AsmWritter aw, LabelManager lm){
        this.writer = aw;
        this.labelTable = lm;
    }

    @Override
    public Void visitProgram(Program p) {
        writer.directive("data");
        for (StructTypeDecl std: p.structTypeDecls){
            visitStructTypeDecl(std);
        }
        for (VarDecl vd: p.varDecls){
            visitGlobalVarDecl(vd);
        }
        for (FunDecl fd: p.funDecls){
            visitFunDecl(fd);
        }
        return null;
    }

    public void visitGlobalStructDecl(VarDecl varDecl, StructType structType) {


        // add struct info as comment
        writer.comment(String.format("%s as \"struct %s\" (size %d)", varDecl.varName, structType.ident, structType.sizeof()));

        // maybe need to set label to this stru decl?

        for (VarDecl field: structType.decl.varDecls){
            String label = labelTable.addLabel(String.format("$%s_%s", varDecl.varName, field.varName));
            int size = field.type.sizeof();
            writer.withLabel(label).dataSpace(size);

        }
    }

    public void visitGlobalVarDecl(VarDecl varDecl) {
        if (varDecl.type.isStructType()) {
            visitGlobalStructDecl(varDecl, (StructType) varDecl.type);
        }else {
            String label = labelTable.addLabel(varDecl.varName);
            //varDecl.setGlobalLabel(label);

            int size = varDecl.type.sizeof();
            writer.withLabel(label).dataSpace(size);
        }


    }

    public Void visitStrLiteral(StrLiteral s) {

        //s.genLabel = strLabeller.num();
        String label = labelTable.addGlobalStringLabel();
        writer.withLabel(label).dataAsciiNullTerminated(s.extractValue());
        return null;
    }



    // damned visitor design pattern
    @Override
    public Void visitBaseType(BaseType bt) {
        return null;
    }

    @Override
    public Void visitStructTypeDecl(StructTypeDecl st) {
        st.st.accept(this);
        for (VarDecl vd: st.varDecls){
            visitVarDecl(vd);
        }
        return null;
    }

    @Override
    public Void visitBlock(Block b) {
        for (VarDecl vd: b.varDecls){
            visitVarDecl(vd);
        }
        for (Stmt s: b.stmts){
            s.accept(this);
        }
        return null;
    }

    @Override
    public Void visitFunDecl(FunDecl p) {
        p.type.accept(this);
        for (VarDecl vd: p.params){
            visitVarDecl(vd);
        }
        p.block.accept(this);
        return null;
    }

    @Override
    public Void visitVarDecl(VarDecl vd) {
        vd.type.accept(this);
        return null;
    }

    @Override
    public Void visitVarExpr(VarExpr v) {
        return null;
    }

    @Override
    public Void visitPointerType(PointerType pt) {
        pt.type.accept(this);
        return null;
    }

    @Override
    public Void visitStructType(StructType st) {
        return null;
    }

    @Override
    public Void visitArrayType(ArrayType at) {
        at.innerType.accept(this);
        return null;
    }

    @Override
    public Void visitIntLiteral(IntLiteral il) {
        return null;
    }

    @Override
    public Void visitChrLiteral(ChrLiteral cl) {
        return null;
    }

    @Override
    public Void visitFunCallExpr(FunCallExpr fce) {
        for (Expr e: fce.exprs){
            e.accept(this);
        }
        return null;
    }

    @Override
    public Void visitBinOp(BinOp bo) {
        bo.lhs.accept(this);
        bo.rhs.accept(this);
        return null;
    }

    @Override
    public Void visitOp(Op o) {
        return null;
    }

    @Override
    public Void visitArrayAccessExpr(ArrayAccessExpr aae) {
        aae.array.accept(this);
        aae.index.accept(this);
        return null;
    }

    @Override
    public Void visitFieldAccessExpr(FieldAccessExpr fae) {
        fae.stru.accept(this);
        return null;
    }

    @Override
    public Void visitValueAtExpr(ValueAtExpr vae) {
        vae.toDeref.accept(this);
        return null;
    }

    @Override
    public Void visitSizeOfExpr(SizeOfExpr soe) {
        soe.toCheck.accept(this);
        return null;
    }

    @Override
    public Void visitTypecastExpr(TypecastExpr te) {
        te.target.accept(this);
        te.fromExpr.accept(this);
        return null;
    }

    @Override
    public Void visitExprStmt(ExprStmt es) {
        es.expr.accept(this);
        return null;
    }

    @Override
    public Void visitWhile(While w) {
        w.expr.accept(this);
        w.stmt.accept(this);
        return null;
    }

    @Override
    public Void visitIf(If i) {
        i.expr.accept(this);
        i.stmt.accept(this);
        if (i.elseStmt != null) {
            i.elseStmt.accept(this);
        }
        return null;
    }

    @Override
    public Void visitAssign(Assign a) {
        a.lhs.accept(this);
        a.rhs.accept(this);
        return null;
    }

    @Override
    public Void visitReturn(Return r) {
        if (r.expr != null) {
            r.expr.accept(this);
        }
        return null;
    }
}
