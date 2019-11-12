package gen;

import ast.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.Stack;

public class CodeGenerator implements ASTVisitor<Register> {
    // return Register just for Expr!
    // maybe write another pass which return void for higher level ast node?

    /*
     * Simple register allocator.
     */

    // contains all the free temporary registers
    private Stack<Register> freeRegs = new Stack<Register>();

    public CodeGenerator() {
        freeRegs.addAll(Register.tmp);
    }

    private class RegisterAllocationError extends Error {}

    private Register getRegister() {
        try {
            return freeRegs.pop();
        } catch (EmptyStackException ese) {
            throw new RegisterAllocationError(); // no more free registers, bad luck!
        }
    }

    private void freeRegister(Register reg) {
        freeRegs.push(reg);
    }

    /*
    .data
    $LC1: .ascii "blablabla\0"
    $LC2: .ascii "blablabla\0"
    ...
    .text
    .global main
    otherFunc:
    ...
    main:
     */



    private PrintWriter writer; // use this writer to output the assembly instructions
    private LabelManager lm;
    private AsmWritter aw;
    private RegAllocater ra;
    // general entry point of code generator
    public void emitProgram(Program program, File outputFile) throws FileNotFoundException {
        // input: ast
        // output: ASM
        // actually, no real "global variable" since we can only declare.
        // only need to write string literals to .data and compute struct size.
        // need some sort of IR?
        /* ast -> ast+hashset(for literals)
                     +hashtable(struct and their sizes) -> ASM*/



        writer = new PrintWriter(outputFile);
        lm = new LabelManager();
        ra = new RegAllocater();
        aw = new AsmWritter(writer, lm, ra);


        program.accept(new DataVisitor(aw, lm));
        program.accept(new TextVisitor(aw, lm, ra));

        //visitProgram(program);
        writer.close();
    }


    //maybe need a code segment loader?

    @Override
    public Register visitProgram(Program p) {
        // TODO: to complete
        for (StructTypeDecl std: p.structTypeDecls){
            visitStructTypeDecl(std);
        }
        for (VarDecl vd: p.varDecls){
            visitVarDecl(vd);
        }
        for (FunDecl fd: p.funDecls){
            visitFunDecl(fd);
        }

        return null;
    }

    @Override
    public Register visitBaseType(BaseType bt) {
        return null;
    }

    @Override
    public Register visitStructTypeDecl(StructTypeDecl st) {
        return null;
    }

    @Override
    public Register visitBlock(Block b) {
        // TODO: to complete
        return null;
    }

    private Register visitBuildInFun(FunDecl fd){
        switch (fd.name){
            case "print_s":break;
            case "print_i":break;
            case "print_c":break;
            case "read_c":break;
            case "read_i":break;
            case "mcmalloc":break;
        }
        return null;
    }

    @Override
    public Register visitFunDecl(FunDecl fd) {
        // TODO: to complete
        if (fd.isBuildIn){
            visitBuildInFun(fd);
        }

        return null;
    }



    @Override
    public Register visitVarDecl(VarDecl vd) {
        // TODO: to complete
        return null;
    }

    @Override
    public Register visitVarExpr(VarExpr v) {
        // TODO: to complete
        return null;
    }

    @Override
    public Register visitPointerType(PointerType pt) {
        return null;
    }

    @Override
    public Register visitStructType(StructType st) {
        return null;
    }

    @Override
    public Register visitArrayType(ArrayType at) {
        return null;
    }

    @Override
    public Register visitIntLiteral(IntLiteral il) {
        return null;
    }

    @Override
    public Register visitStrLiteral(StrLiteral sl) {
        return null;
    }

    @Override
    public Register visitChrLiteral(ChrLiteral cl) {
        return null;
    }

    @Override
    public Register visitFunCallExpr(FunCallExpr fce) {
        return null;
    }

    @Override
    public Register visitBinOp(BinOp bo) {
        return null;
    }

    @Override
    public Register visitOp(Op o) {
        return null;
    }

    @Override
    public Register visitArrayAccessExpr(ArrayAccessExpr aae) {
        return null;
    }

    @Override
    public Register visitFieldAccessExpr(FieldAccessExpr fae) {
        return null;
    }

    @Override
    public Register visitValueAtExpr(ValueAtExpr vae) {
        return null;
    }

    @Override
    public Register visitSizeOfExpr(SizeOfExpr soe) {
        return null;
    }

    @Override
    public Register visitTypecastExpr(TypecastExpr te) {
        return null;
    }

    @Override
    public Register visitExprStmt(ExprStmt es) {
        return null;
    }

    @Override
    public Register visitWhile(While w) {
        return null;
    }

    @Override
    public Register visitIf(If i) {
        return null;
    }

    @Override
    public Register visitAssign(Assign a) {
        return null;
    }

    @Override
    public Register visitReturn(Return r) {
        return null;
    }
}
