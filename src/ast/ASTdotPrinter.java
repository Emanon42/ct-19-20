package ast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ASTdotPrinter implements ASTVisitor<String> {
    private PrintWriter writer;
    private int nodeCnt;

    public ASTdotPrinter(File f) {
        nodeCnt = 0;
        try {
            writer = new PrintWriter(f);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public String visitBlock(Block b) {
        nodeCnt++;
        writer.println("Node"+nodeCnt+" [label=\"Block()\"];");
        // to complete
        String divider = "";
        for (VarDecl vd : b.varDecls){
            nodeCnt++;
            writer.println("Node"+nodeCnt+" [label=\"varDecl()\"];");
            vd.accept(this);
            divider = ", ";
        }
        for (Stmt stmt: b.stmts) {
            writer.print(divider);
            stmt.accept(this);
            divider = ", ";
        }
        writer.print(")");
        return null;
    }

    @Override
    public String visitFunDecl(FunDecl fd) {
        writer.print("FunDecl(");
        fd.type.accept(this);
        writer.print(","+fd.name+",");
        for (VarDecl vd : fd.params) {
            vd.accept(this);
            writer.print(",");
        }
        fd.block.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitProgram(Program p) {
        writer.print("Program(");
        String delimiter = "";
        for (StructTypeDecl std : p.structTypeDecls) {
            writer.print(delimiter);
            delimiter = ",";
            std.accept(this);
        }
        for (VarDecl vd : p.varDecls) {
            writer.print(delimiter);
            delimiter = ",";
            vd.accept(this);
        }
        for (FunDecl fd : p.funDecls) {
            writer.print(delimiter);
            delimiter = ",";
            fd.accept(this);
        }
        writer.print(")");
        writer.flush();
        return null;
    }

    @Override
    public String visitVarDecl(VarDecl vd){
        writer.print("VarDecl(");
        vd.type.accept(this);
        writer.print(","+vd.varName);
        writer.print(")");
        return null;
    }

    @Override
    public String visitVarExpr(VarExpr v) {
        writer.print("VarExpr(");
        writer.print(v.name);
        writer.print(")");
        return null;
    }

    @Override
    public String visitBaseType(BaseType bt) {
        // to complete ...
        writer.print(bt.toString());
        return null;
    }

    @Override
    public String visitStructTypeDecl(StructTypeDecl st) {
        // to complete ...
        writer.print("StructTypeDecl(");
        st.st.accept(this);
        for (VarDecl varDecl: st.varDecls) {
            writer.print(", ");
            varDecl.accept(this);
        }
        writer.print(")");
        return null;
    }

    @Override
    public String visitPointerType(PointerType pt) {
        //to be completed...
        writer.print("PointerType(");
        pt.type.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitStructType(StructType st) {
        //to be completed...
        writer.print("StructType(" + st.ident + ")");
        return null;
    }

    @Override
    public String visitArrayType(ArrayType at) {
        //to be completed...
        writer.print("ArrayType(");
        at.type.accept(this);
        writer.print(", " + at.capacity + ")");
        return null;
    }

    @Override
    public String visitIntLiteral(IntLiteral il) {
        //to be completed...
        writer.print("IntLiteral(" + il.value + ")");
        return null;
    }

    @Override
    public String visitStrLiteral(StrLiteral sl) {
        //to be completed...
        writer.print("StrLiteral(" + sl.value + ")");
        return null;
    }

    @Override
    public String visitChrLiteral(ChrLiteral cl) {
        //to be completed...
        writer.print("ChrLiteral(" + cl.value + ")");
        return null;
    }

    @Override
    public String visitFunCallExpr(FunCallExpr fce) {
        //to be completed...
        writer.print("FunCallExpr(" + fce.ident);
        for (Expr e: fce.exprs) {
            writer.print(", ");
            e.accept(this);
        }
        writer.print(")");
        return null;
    }

    @Override
    public String visitBinOp(BinOp bo) {
        //to be completed...
        writer.print("BinOp(");
        bo.lhs.accept(this);
        writer.print(", ");
        bo.operator.accept(this);
        writer.print(", ");
        bo.rhs.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitOp(Op o) {
        //to be completed...
        writer.print(o.toString());
        return null;
    }

    @Override
    public String visitArrayAccessExpr(ArrayAccessExpr aae) {
        //to be completed...
        writer.print("ArrayAccessExpr(");
        aae.array.accept(this);
        writer.print(", ");
        aae.index.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitFieldAccessExpr(FieldAccessExpr fae) {
        //to be completed...
        writer.print("FieldAccessExpr(");
        fae.stru.accept(this);
        writer.print(", )" + fae.field + ")");
        return null;
    }

    @Override
    public String visitValueAtExpr(ValueAtExpr vae) {
        //to be completed...
        writer.print("ValueAtExpr(");
        vae.expr.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitSizeOfExpr(SizeOfExpr soe) {
        //to be completed...
        writer.print("SizeOfExpr(");
        soe.type.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitTypecastExpr(TypecastExpr te) {
        //to be completed...
        writer.print("TypecastExpr(");
        te.type.accept(this);
        writer.print(", ");
        te.expr.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitExprStmt(ExprStmt es) {
        //to be completed...
        writer.print("ExprStmt(");
        es.expr.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitWhile(While w) {
        //to be completed...
        writer.print("While(");
        w.expr.accept(this);
        writer.print(", ");
        w.stmt.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitIf(If i) {
        //to be completed...
        writer.print("If(");
        i.expr.accept(this);
        writer.print(", ");
        i.stmt.accept(this);
        if (i.elseStmt != null) {
            writer.print(", ");
            i.elseStmt.accept(this);
        }
        writer.print(")");
        return null;
    }

    @Override
    public String visitAssign(Assign a) {
        //to be completed...
        writer.print("Assign(");
        a.lhs.accept(this);
        writer.print(", ");
        a.rhs.accept(this);
        writer.print(")");
        return null;
    }

    @Override
    public String visitReturn(Return r) {
        //to be completed...
        writer.print("Return(");
        if (r.expr != null) {
            r.expr.accept(this);
        }
        writer.print(")");
        return null;
    }
}
