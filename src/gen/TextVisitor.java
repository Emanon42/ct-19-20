package gen;

import ast.*;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TextVisitor implements ASTVisitor<Register> {
    public AsmWritter writer;
    public LabelManager labeller;
    public RegAllocater regAllocater;
    private static HashSet<String> buildInFuncSet;
    static {
        buildInFuncSet = new HashSet<String>();
        buildInFuncSet.add("print_i");
        buildInFuncSet.add("print_s");
        buildInFuncSet.add("read_i");
        buildInFuncSet.add("mcmalloc");
        buildInFuncSet.add("print_c");
        buildInFuncSet.add("read_c");
    }
    private static HashSet<Op> binOpComparation;
    static {
        binOpComparation = new HashSet<Op>();
        binOpComparation.add(Op.LT);
        binOpComparation.add(Op.GT);
        binOpComparation.add(Op.LE);
        binOpComparation.add(Op.GE);
        binOpComparation.add(Op.ADD);
        binOpComparation.add(Op.SUB);
        binOpComparation.add(Op.EQ);
        binOpComparation.add(Op.NE);
    }
    private static HashSet<Op> binOpArithmic;
    static {
        binOpArithmic = new HashSet<Op>();
        binOpArithmic.add(Op.MUL);
        binOpArithmic.add(Op.MOD);
        binOpArithmic.add(Op.DIV);
    }
    private int frameOffset; // use to point "where are we up to" in current stack frame (only useful in function traverse)
    private final int snapshotRegSize = Register.toSnapshot.size() * 4; // in bytes

    public TextVisitor(AsmWritter aw, LabelManager lm, RegAllocater ra) {
        this.writer = aw;
        this.labeller = lm;
        this.regAllocater = ra;
    }

    @Override
    public Register visitProgram(Program p) {
        writer.directive("text");

        for (FunDecl fd : p.funDecls) {
            visitFunDecl(fd);
        }

        // setting entry point
        writer.withLabel("main").directive(String.format("globl %s", "main"));
        writer.jal("fn_main_prologue");

        // exit and clean
        writer.move(Register.arg.get(0), Register.v0);
        writer.li(Register.v0, 17);
        writer.syscall();

        return null;
    }



    // funDecl, block, return, funCallExpr: traverse function calling structure
    // funDecl do prologue and epilogue
    // funCallExpr do pre-call and post-return
    // fuck u MIPS calling convention, I reject my humanity jojo!!!
    /*
    high address
      |                new stack memory layout in planning:
      |
      |                .    |
      |                .    - caller block
      |                .    |
      |
      |                $ra  - caller return address (old $sp)
      |                $fp  - caller frame pointer (old)
      |
      |                argN |
      |                .    |
      |                .    - args zone (above new(callee) FP!) (in backward!)
      |                arg2 |
      |                arg1 |
      |                         (caller should update SP until here)
      |                         <-----calling happens here (new FP points here! new FP use old SP value!)
      |                    $gp  |
      |                    ...  |
      |                    $s1  - snapshot ALL REGS (in backward!)
      |                    $s0  |
      |
      |                    .    |
      |                    .    - callee block
     \|/                   .    |
    low address
     */

    private int charSizeFix(int size){
        if (size == 1){
            return 4;
        }else {
            return size;
        }
    }

    @Override
    public Register visitFunDecl(FunDecl f) {
        // do prologue and epilogue here
        // prologue: reset FP, snapshot regs, set $ra (need to skip prologue)
        // traverse args, set their offset in their varDecl (assume caller has put them in stack mem properly)
        // CHECK the sequence of args!!!

        // Ignore inbuilt declarations
        if (f.isBuildIn) {
            return null;
        }

        // set prologue label
        f.asmLabel = labeller.addLabel("fn_" + f.name + "_prologue");
        writer.withLabel(f.asmLabel).comment(f.name + " starts here");

        // reset current frame offset and frame pointer
        frameOffset = 0;
        writer.comment("reset frame pointer");
        writer.move(Register.fp, Register.sp);

        // traverse args and set their offset (be backward!)
        writer.comment("setting args frame offset (relative to current fp)");
        int argOffset = 0; // relative to FP
        for (VarDecl arg : f.params){
            arg.setFrameOffset(argOffset); //TODO: possible bug here
            int size = arg.type.sizeof();
            argOffset += size;
            writer.comment(String.format("arg %s offset: %d($fp)", arg.varName, argOffset));
        }
        if (argOffset == 0){
            writer.comment("no args in current function");
        }

        // snapshot regs (remember to update SP and frameOffset!)
        writer.comment("snapshot registers");
        writer.sub(Register.sp, Register.sp, snapshotRegSize); // update SP for allocating space of regs data
        frameOffset -= snapshotRegSize; // update frameOffset
        int snapshotOffset = 0; // relative to SP
        for (Register r: Register.toSnapshot) {
            writer.sw(r, Register.sp, snapshotOffset);
            snapshotOffset += 4;
        }

        // set $ra as epilogue
        writer.comment("Set $ra to epilogue");
        String epilogueLabel = labeller.addLabel("fn_" + f.name + "_epilogue");
        writer.la(Register.ra, epilogueLabel);

        // prologue ends, content start
        String contentLabel = labeller.addLabel("fn_" + f.name + "_content");
        writer.withLabel(contentLabel).newline();
        visitBlock(f.block);
        writer.comment("Store default return value to $v0"); // visitReturn will skip this
        writer.li(Register.v0, 0);

        // epilogue starts
        writer.withLabel(epilogueLabel).newline();
        // reset SP as current function FP: dispose all things in this procedure
        writer.move(Register.sp, Register.fp);
        writer.sub(Register.sp, Register.sp, snapshotRegSize); // move SP down for restore regs data
        // Restore registers to caller's state
        writer.comment("restore registers");
        int i = 0;
        for (Register r: Register.toSnapshot) {
            writer.lw(r, Register.sp, i);
            i += 4;
        }
        writer.add(Register.sp, Register.sp, snapshotRegSize); // recover SP to dispose snapshot
        frameOffset += snapshotRegSize;

        // Jump to $ra
        writer.jr(Register.ra);

        return null;
    }

    @Override
    public Register visitFunCallExpr(FunCallExpr f) {
        // do pre-call and post-return here
        // pre-call: save current $ra, $fp and save args for callee
        // post-return: extract return value
        // NOTICE the sequence of args!!!

        if (f.fd.isBuildIn) {
            return visitFunBuildIn(f);
        }

        // pre-order a reg for saving result
        Register funCallResult = regAllocater.get();

        // pre-call
        writer.comment("precall for " + f.fd.name);
        // Store current return address
        writer.comment("Store current $ra in stack mem");
        writer.sub(Register.sp, Register.sp, 4);
        writer.sw(Register.ra, Register.sp, 0);
        // Store current frame pointer address
        writer.comment("Store current $fp in stack mem");
        writer.sub(Register.sp, Register.sp, 4);
        writer.sw(Register.fp, Register.sp, 0);

        // evaluate args and load to stack mem
        // pre-allocate space
        writer.comment("pre-allocate space for args");
        int totalArgSize = 0;
        for (VarDecl declaredArg : f.fd.params){
            totalArgSize += declaredArg.type.sizeof();
        }
        writer.sub(Register.sp, Register.sp, totalArgSize);

        // evaluate and load
        writer.comment("eval and store args");
        int loadArgsOffset = 0;
        for (int i = 0; i < f.fd.params.size(); i++) {
            VarDecl declaredArg = f.fd.params.get(i);
            Expr actualArg = f.exprs.get(i);
            Type argType = actualArg.type;

            writer.comment(String.format("storing arg %s offset: %d($sp)", declaredArg.varName, loadArgsOffset));
            Register result = actualArg.accept(this);
            writer.sw(result, Register.sp, loadArgsOffset);
            regAllocater.free(result); // remember to free!!

            int expectedOffset = declaredArg.getFrameOffset();
            int argSize = argType.sizeof();
            //System.out.println("expectedOffset: " + expectedOffset + " loadoffset: " + loadArgsOffset);
            assert expectedOffset == loadArgsOffset;
            loadArgsOffset += argSize;

        }
        assert loadArgsOffset == totalArgSize;

        // pre-call ends, jump to callee
        writer.comment("perform jump to callee: " + f.fd.name);
        writer.jal(f.fd.asmLabel);

        // post-return : extract return value, restore $ra, $fp and dispose args
        writer.comment("postreturn for " + f.fd.name);
        //reset $sp to dispose args
        writer.comment("dispose args space");
        writer.add(Register.sp, Register.sp, loadArgsOffset); // assume epilogue of callee has restored $sp
        // restore fp
        writer.comment("restore $fp from stack mem");
        writer.lw(Register.fp, Register.sp, 0);
        writer.add(Register.sp, Register.sp, 4);
        // restore ra
        writer.comment("restore $ra from stack mem");
        writer.lw(Register.ra, Register.sp, 0);
        writer.add(Register.sp, Register.sp, 4);

        // extract return value of callee
        writer.comment("extract return value");
        writer.move(funCallResult, Register.v0); // in visitReturn, store return value in $v0
        return funCallResult;

    }

    @Override
    public Register visitBlock(Block b) {
        int oldOffset = frameOffset;

        // pre-allocate stack space for vardecls
        int preAllocSize = 0;
        for (VarDecl local : b.varDecls){
            int size = charSizeFix(local.type.sizeof());
            frameOffset -= size;
            preAllocSize += size;
            writer.comment(String.format("local var %s frameOffset(relative to $fp): %d", local.varName, frameOffset));
            local.setFrameOffset(frameOffset);
        }
        if (preAllocSize != 0){
            writer.comment(String.format("pre-alloc %d bytes for local var",preAllocSize));
            writer.sub(Register.sp, Register.sp, preAllocSize);
        }else {
            writer.comment("no local var detected");
        }

        // visit stmts
        for (Stmt s : b.stmts){
            s.accept(this);
        }

        // release local var mem
        int toReleaseSize = 0;
        for (VarDecl local : b.varDecls){
            int size = charSizeFix(local.type.sizeof());
            frameOffset += size;
            toReleaseSize += size;
        }

        assert toReleaseSize == preAllocSize;

        if (toReleaseSize != 0){
            writer.comment("release pre-allocated mem for local vars");
            writer.add(Register.sp, Register.sp, toReleaseSize);
        }

        assert frameOffset == oldOffset;

        return null;
    }

    @Override
    public Register visitReturn(Return r) {
        writer.comment("return " + r.toString());
        if (r.expr != null) {
            Register toReturn = r.expr.accept(this);
            writer.comment("store return value at $v0");
            writer.move(Register.v0, toReturn);
            regAllocater.free(toReturn);
        } else {
            writer.comment("store default return value at $v0");
            writer.li(Register.v0, 0);
        }

        writer.comment("jump to epilogue of current func (defined at $ra)");
        writer.jr(Register.ra);
        return null;
    }





    @Override
    public Register visitExprStmt(ExprStmt es) {
        writer.comment("exprStmt " + es.toString());
        Register result = es.expr.accept(this); // can be null!
        if (result != null){
            regAllocater.free(result);
        }
        return null;
    }

    @Override
    public Register visitIf(If i) {
        String endLabel = labeller.addNumLabel("if", "end");
        String elseLabel = null;
        if (i.elseStmt != null){
            elseLabel = labeller.addNumLabel("if", "else");
        }else {
            elseLabel = endLabel;
        }

        writer.comment("if " + i.toString());
        Register conditionResult = i.expr.accept(this);
        writer.beqz(conditionResult, elseLabel);
        regAllocater.free(conditionResult);

        i.stmt.accept(this);
        writer.b(endLabel);


        if (i.elseStmt == null) {
            writer.withLabel(endLabel).nop();
            return null;
        }

        writer.withLabel(elseLabel).comment("else");
        i.elseStmt.accept(this);
        writer.withLabel(endLabel).nop();

        return null;
    }

    @Override
    public Register visitWhile(While w) {
        String startLabel = labeller.addNumLabel("while", "start");
        String endLabel = labeller.addNumLabel("while", "end");

        writer.withLabel(startLabel).comment(String.format("while (%s)", w.expr.toString()));

        Register conditionResult = w.expr.accept(this);
        writer.beqz(conditionResult, endLabel);
        regAllocater.free(conditionResult);

        w.stmt.accept(this);

        writer.b(startLabel);
        writer.withLabel(endLabel).nop();
        return null;
    }




















    public void assignValue(Register sourceValue, Type type, Register targetAddress, int offset){
        if (type == BaseType.CHAR) {
            writer.sb(sourceValue, targetAddress, offset);
            //regAllocater.free(sourceValue);
            //regAllocater.free(targetAddress);
        } else if (type == BaseType.INT || type.isPointerType()) {
            writer.sw(sourceValue, targetAddress, offset);
            //regAllocater.free(sourceValue);
            //regAllocater.free(targetAddress);
        } else if (type.isStructType()) {

            // Note, sourceValue is actually referring to the struct's address
            // Don't forget this!

            StructTypeDecl struct = ((StructType) type).decl;

            int totalSize = 0;
            for (VarDecl v : struct.varDecls) {
                // Read the value at the struct address (which may or may not have been incremented)
                Register innerSourceValue = getValue(sourceValue, v.type);
                assignValue(innerSourceValue, v.type, targetAddress, offset);
                regAllocater.free(innerSourceValue);

                // Increment our read offset and struct address by the size we've just read
                int size = v.type.sizeof();
                writer.add(sourceValue, sourceValue, size);
                offset += size;
                totalSize += size;
            }

            // Restore sourceValue to original address
            writer.sub(sourceValue, sourceValue, totalSize);
            //regAllocater.free(sourceValue);
            //regAllocater.free(targetAddress);
        } else {
            throw new RuntimeException(
                    "storeValue hasn't been implemented for type: " + type.toString());
        }
    }

    @Override
    public Register visitAssign(Assign a) {
        writer.comment("assign " + a.toString());
        Register lhs = addressOf(a.lhs);
        Register rhs = a.rhs.accept(this);
        assignValue(rhs, a.rhs.type, lhs, 0);
        regAllocater.free(lhs);
        regAllocater.free(rhs);
        return null;
    }



















    // purify above funcs

    // Get value of a certain type stored at this address
    public Register getValue(Register address, Type t) {
        Register value = regAllocater.get();
        if (t == BaseType.CHAR) {
            writer.lb(value, address, 0);
        } else if (t == BaseType.INT || t.isPointerType()) {
            writer.lw(value, address, 0);
        } else if (t.isArrayType() || t.isStructType()) {
            writer.move(value, address);
        } else if (t == BaseType.VOID) {
            writer.nop();
        } else {
            throw new RuntimeException(
                    "getValue not implemented yet with type " + t.toString());
        }

        regAllocater.free(address);
        return value;
    }

    public Register addressOf(Expr e) {
        if (e instanceof VarExpr) {
            return addressOf((VarExpr) e);
        } else if (e instanceof ArrayAccessExpr) {
            return addressOf((ArrayAccessExpr) e);
        } else if (e instanceof FieldAccessExpr) {
            return addressOf((FieldAccessExpr) e);
        } else if (e instanceof ValueAtExpr) {
            return addressOf((ValueAtExpr) e);
        }
        throw new RuntimeException("Got expression that I don't know how to addressOf - " + e.toString());
    }

    private Register addressOf(ArrayAccessExpr e) {
        Register pointer = e.array.accept(this);
        Register index = e.index.accept(this);

        int size = e.type.realSize();
        writer.mul(index, index, size);
        writer.add(pointer, pointer, index);
        regAllocater.free(index);

        return pointer;
    }

    private Register addressOf(VarExpr v) {
        VarDecl decl = v.vd;
        Register value = regAllocater.get();

        if (decl.isGlobal()) {
            // Load address into to value register
            String label = decl.getGlobalLabel();
            writer.la(value, label);
            return value;
        }else {
            // locate var using frame offset
            writer.add(value, Register.fp, decl.getFrameOffset());
            return value;
        }
    }

    private Register addressOf(FieldAccessExpr f) {
        assert f.stru.type.isStructType();

        // Only varExpr.fieldName can take advantage of directly addressing by label
        if (f.stru instanceof VarExpr) {
            if (((VarExpr) f.stru).vd.isGlobal()) {
                String label = ((VarExpr) f.stru).vd.getGlobalLabel();
                Register address = regAllocater.get();
                writer.la(address, label);
                return address;
            }
        }

        // Get the address of the struct
        Register address = f.stru.accept(this);

        // Offset the address by whichever amount
        StructType structType = (StructType) f.stru.type;
        int offset = 0; // todo: only calculate this stuff once (also elsewhere in assign)
        for (VarDecl v : structType.decl.varDecls) {
            if (v.varName.equals(f.field)) {
                writer.add(address, address, offset);
                return address;
            }
            offset += v.type.sizeof();
        }

        throw new RuntimeException("could not find field in: " + f.toString());
    }

    private Register addressOf(ValueAtExpr e) {
        assert e.toDeref.type instanceof PointerType;
        // Store address of the pointer in a register
        Register locationOfPointer = addressOf(e.toDeref);

        // Read the pointer into our register
        writer.lw(locationOfPointer, locationOfPointer, 0);

        // This is what the above load word has done
        Register pointer = locationOfPointer;

        return pointer;
    }








































    @Override
    public Register visitIntLiteral(IntLiteral il) {
        Register val = regAllocater.get();
        writer.li(val, il.value);
        return val;
    }

    @Override
    public Register visitStrLiteral(StrLiteral sl) {
        Register val = regAllocater.get();
        writer.la(val, sl.asmLabel);
        return val;
    }

    @Override
    public Register visitChrLiteral(ChrLiteral cl) {
        Register val = regAllocater.get();
        writer.li(val, cl.value);
        return val;
    }

    @Override
    public Register visitVarExpr(VarExpr v) {
        assert v.type == v.vd.type;
        //System.out.println(" v.name: " + v.name + " v.type: " + v.type + " vd.type: " + v.vd.type);
        Register address = addressOf(v);
        return getValue(address, v.type);
    }

    @Override
    public Register visitArrayAccessExpr(ArrayAccessExpr aae) {
        Register address = addressOf(aae);
        return getValue(address, aae.type);
    }

    @Override
    public Register visitFieldAccessExpr(FieldAccessExpr fae) {
        assert fae.type != null;
        Register address = addressOf(fae);
        return getValue(address, fae.type);
    }

    @Override
    public Register visitValueAtExpr(ValueAtExpr vae) {
        assert vae.toDeref.type.isPointerType();
        Register address = addressOf(vae);
        return getValue(address, vae.type);
    }

    @Override
    public Register visitSizeOfExpr(SizeOfExpr soe) {
        Register val = regAllocater.get();
        writer.li(val, soe.toCheck.realSize());
        return val;
    }

    @Override
    public Register visitTypecastExpr(TypecastExpr te) {
        Register value  = te.fromExpr.accept(this);
        return value;
    }































    private Register and(Register x, Expr yExpr){
        // Generate a result register
        Register result = regAllocater.get();

        // Generate a "false", "true", "end" label ahead of time
        String falseLabel = labeller.addNumLabel("and_false");
        String trueLabel = labeller.addNumLabel("and_true");
        String finishLabel = labeller.addNumLabel("and_finish");

        // Plan:
        // - jump to FALSE if X fails, otherwise continue (jump to CHECK_Y)
        // - CHECK_Y: jump to TRUE if Y success, otherwise continue (jump to FALSE)
        // - FALSE  : set result to 0, then finish (jump to FINISH)
        // - TRUE   : set result to 1
        // - FINISH : return the result

        // Jump to FALSE if X is zero
        writer.beqz(x, falseLabel);

        // Jump to TRUE if Y success
        // If y is greater than zero, we want to skip to the true label
        Register y = yExpr.accept(this);
        writer.bgtz(y, trueLabel);
        regAllocater.free(y);


        // FALSE: Set result to 0, jump to finish
        writer.withLabel(falseLabel).li(result, 0);
        writer.b(finishLabel);

        // TRUE : Set result to 1
        writer.withLabel(trueLabel).li(result, 1);

        // Emit finish label
        writer.withLabel(finishLabel).nop();
        regAllocater.free(x);

        return result;
    }

    private Register or(Register x, Expr yExpr){
        // Generate a result register
        Register result = regAllocater.get();

        // Generate a "false", "true", "end" label ahead of time
        String falseLabel = labeller.addNumLabel("or_false");
        String trueLabel = labeller.addNumLabel("or_true");
        String finishLabel = labeller.addNumLabel("or_finish");

        // Plan:
        // - jump to TRUE if X success, otherwise continue (jump to CHECK_Y)
        // - CHECK_Y: continue if Y success, otherwise jump to FALSE
        // - TRUE   : set result to 1 (jump to FINISH)
        // - FALSE  : set result to 0
        // - FINISH : return the result

        // Jump to TRUE if X success
        writer.bnez(x, trueLabel);

        // Jump to FALSE if Y fail
        // If y is greater than zero, we want to skip to the true label
        Register y = yExpr.accept(this);
        writer.beqz(y, falseLabel);
        regAllocater.free(y);

        // TRUE : Set result to 1, jump to finish
        writer.withLabel(trueLabel).li(result, 1);
        writer.b(finishLabel);

        // FALSE: Set result to 0
        writer.withLabel(falseLabel).li(result, 0);

        // Emit finish label
        writer.withLabel(finishLabel).nop();
        regAllocater.free(x);

        return result;
    }

    private Register mul(Register x, Register y) {
        Register result = regAllocater.get();
        writer.mul(result, x, y);
        regAllocater.free(y);
        regAllocater.free(x);
        return result;
    }

    private Register mod(Register num, Register dividedBy) {
        Register result = regAllocater.get();
        writer.div(num, dividedBy);
        writer.mfhi(result);
        regAllocater.free(num);
        regAllocater.free(dividedBy);
        return result;
    }

    private Register div(Register num, Register dividedBy) {
        Register result = regAllocater.get();
        writer.div(num, dividedBy);
        writer.mflo(result);
        regAllocater.free(num);
        regAllocater.free(dividedBy);
        return result;
    }

    private Register compare(Register x, Register y, Op op) {
        Register result = regAllocater.get();
        switch (op){
            case LT:
                writer.slt(result, x, y);
                regAllocater.free(x);
                regAllocater.free(y);
                return result;
            case GT:
                writer.sgt(result, x, y);
                regAllocater.free(x);
                regAllocater.free(y);
                return result;
            case LE:
                writer.sle(result, x, y);
                regAllocater.free(x);
                regAllocater.free(y);
                return result;
            case GE:
                writer.sge(result, x, y);
                regAllocater.free(x);
                regAllocater.free(y);
                return result;
            case ADD:
                writer.add(result, x, y);
                regAllocater.free(x);
                regAllocater.free(y);
                return result;
            case SUB:
                writer.sub(result, x, y);
                regAllocater.free(x);
                regAllocater.free(y);
                return result;
            case EQ:
                writer.seq(result, x, y);
                regAllocater.free(x);
                regAllocater.free(y);
                return result;
            case NE:
                writer.sne(result, x, y);
                regAllocater.free(x);
                regAllocater.free(y);
                return result;
            default:
                throw new RuntimeException("unsupported operation");
        }
    }


    // now binOP
    @Override
    public Register visitBinOp(BinOp bo) {
        if (bo.operator == Op.AND){
            Register lhs = bo.lhs.accept(this);
            return and(lhs, bo.rhs);
        }else if (bo.operator == Op.OR){
            Register lhs = bo.lhs.accept(this);
            return or(lhs, bo.rhs);
        }else if (binOpArithmic.contains(bo.operator)){
            Register lhs = bo.lhs.accept(this);
            Register rhs = bo.rhs.accept(this);
            switch (bo.operator){
                case MUL:
                    return mul(lhs, rhs);
                case MOD:
                    return mod(lhs, rhs);
                case DIV:
                    return div(lhs, rhs);
                default:
                    throw new RuntimeException("unsupported operation");
            }
        }else if (binOpComparation.contains(bo.operator)){
            Register lhs = bo.lhs.accept(this);
            Register rhs = bo.rhs.accept(this);
            return compare(lhs, rhs, bo.operator);
        }else {
            throw new RuntimeException("unsupported operation");
        }
    }

    @Override
    public Register visitOp(Op o) {
        return null;
    }


































    private Register visitFunBuildIn(FunCallExpr f){
        if (!f.fd.isBuildIn) {
            return null;
        }

        switch (f.ident){
            case "print_i":
                return print_i(f.fd, f.exprs);
            case "print_s":
                return print_s(f.fd, f.exprs);
            case "read_i":
                return read_i(f.fd, f.exprs);
            case "mcmalloc":
                return mcmalloc(f.fd, f.exprs);
            case "print_c":
                return print_c(f.fd, f.exprs);
            case "read_c":
                return read_c(f.fd, f.exprs);
            default:
                throw new RuntimeException("attempt to call undefined inbuilt " + f.fd.name);
        }
    }

    private Register print_i(FunDecl f, List<Expr> args) {
        Expr arg = args.get(0);
        if (arg instanceof IntLiteral) {
            // this is left here for efficiency. removing this will just make an extra intermediary register.
            writer.li(Register.arg.get(0), ((IntLiteral) arg).value);
        } else {
            Register val = arg.accept(this);
            writer.move(Register.arg.get(0), val);
            regAllocater.free(val);
        }

        writer.comment("print_i($a0)");
        writer.li(Register.v0, 1);
        writer.syscall();
        return null;
    }

    private Register print_s(FunDecl f, List<Expr> args) {
        Expr arg = args.get(0);
        Register val = arg.accept(this);
        writer.move(Register.arg.get(0), val);
        regAllocater.free(val);

        writer.comment("print_s($a0)");
        writer.li(Register.v0, 4);
        writer.syscall();
        return null;
    }

    private Register read_i(FunDecl f, List<Expr> args) {
        writer.comment("$v0 = read_i()");
        // Call syscall 5 - this sets the read integer to v0
        writer.li(Register.v0, 5);
        writer.syscall();

        Register value = regAllocater.get();
        writer.move(value, Register.v0);
        return value;
    }

    private Register mcmalloc(FunDecl f, List<Expr> args) {
        // Get the bytes required to allocate
        Expr arg = args.get(0);
        Register byteCount = arg.accept(this);
        // Set the argument of the syscall to these bytes
        writer.move(Register.arg.get(0), byteCount);
        regAllocater.free(byteCount);

        // Call syscall 9 - this puts the address in v0
        writer.comment("mcmalloc($a0)");
        writer.li(Register.v0, 9);
        writer.syscall();

        Register value = regAllocater.get();
        writer.move(value, Register.v0);
        return value;
    }

    private Register print_c(FunDecl f, List<Expr> args) {
        Expr arg = args.get(0);

        if (arg instanceof ChrLiteral) {
            // this is left here for efficiency. removing this will just make an extra intermediary register.
            writer.li(Register.arg.get(0), ((ChrLiteral) arg).value);
        } else {
            Register val = arg.accept(this);
            writer.move(Register.arg.get(0), val);
            regAllocater.free(val);
        }

        writer.comment("print_c($a0)");
        writer.li(Register.v0, 11);
        writer.syscall();

        return null;
    }

    private Register read_c(FunDecl f, List<Expr> args) {
        // Call syscall 12 - this sets the read character to v0
        writer.comment("$v0 = read_c()");
        writer.li(Register.v0, 12);
        writer.syscall();

        Register value = regAllocater.get();
        writer.move(value, Register.v0);
        return value;
    }



























    @Override
    public Register visitBaseType(BaseType bt) {
        return null;
    }

    @Override
    public Register visitStructTypeDecl(StructTypeDecl st) {
        st.st.accept(this);
        for (VarDecl vd: st.varDecls){
            visitVarDecl(vd);
        }
        return null;
    }


    @Override
    public Register visitVarDecl(VarDecl vd) {
        vd.type.accept(this);
        return null;
    }



    @Override
    public Register visitPointerType(PointerType pt) {
        pt.type.accept(this);
        return null;
    }

    @Override
    public Register visitStructType(StructType st) {
        return null;
    }

    @Override
    public Register visitArrayType(ArrayType at) {
        at.innerType.accept(this);
        return null;
    }














}


