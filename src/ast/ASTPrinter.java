package ast;

import java.io.PrintWriter;

public class ASTPrinter implements ASTVisitor<Void> {

    private PrintWriter writer;

    public ASTPrinter(PrintWriter writer) {
            this.writer = writer;
    }

    @Override
    public Void visitBlock(Block b) {
        writer.print("Block(");
        // to complete
        writer.print(")");
        return null;
    }

    @Override
    public Void visitFunDecl(FunDecl fd) {
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
    public Void visitProgram(Program p) {
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
    public Void visitVarDecl(VarDecl vd){
        writer.print("VarDecl(");
        vd.type.accept(this);
        writer.print(","+vd.varName);
        writer.print(")");
        return null;
    }

    @Override
    public Void visitVarExpr(VarExpr v) {
        writer.print("VarExpr(");
        writer.print(v.name);
        writer.print(")");
        return null;
    }

    @Override
    public Void visitBaseType(BaseType bt) {
        // to complete ...
        return null;
    }

    @Override
    public Void visitStructTypeDecl(StructTypeDecl st) {
        // to complete ...
        return null;
    }

    @Override
    public Void visitPointerType(PointerType pt) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitStructType(StructType st) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitArrayType(ArrayType at) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitIntLiteral(IntLiteral il) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitStrLiteral(StrLiteral sl) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitChrLiteral(ChrLiteral cl) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitFunCallExpr(FunCallExpr fce) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitBinOp(BinOp bo) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitOp(Op o) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitArrayAccessExpr(ArrayAccessExpr aae) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitFieldAccessExpr(FieldAccessExpr fae) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitValueAtExpr(ValueAtExpr vae) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitSizeOfExpr(SizeOfExpr soe) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitTypecastExpr(TypecastExpr te) {
//to be completed...
        return null;
    }

    @Override
    public Void visitExprStmt(ExprStmt es) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitWhile(While w) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitIf(If i) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitAssign(Assign a) {
        //to be completed...
        return null;
    }

    @Override
    public Void visitReturn(Return r) {
        //to be completed...
        return null;
    }

    // to complete ...
    
}
