package sem;

import ast.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeCheckVisitor extends BaseSemanticVisitor<Type> {

    //TODO: check block and return related stuff CAREFULLY and do more test, they are possible buggy
    //TODO: better error message: include the identifier name
    private boolean equal(Type a, Type b){
        //deal with null
        if (a == null || b == null){
            return a == b;
        }

        if (a.isBaseType() && b.isBaseType()){
            return a == b;
        }else if (a.isStructType() && b.isStructType()){
            return ((StructType)a).ident.equals(((StructType)b).ident);
        }else if (a.isPointerType() && b.isPointerType()){
            return equal(((PointerType)a).type, ((PointerType)b).type);
        }else if (a.isArrayType() && b.isArrayType()){
            return (((ArrayType)a).capacity == ((ArrayType)b).capacity) && equal(((ArrayType)a).innerType, ((ArrayType)b).innerType);
        }else {
            return false;
        }
    }
    private boolean possibleReturn(Stmt i){
        return i.isBlock() || i.isWhile() || i.isIf() || i.isReturn();
    }



    @Override
    public Type visitProgram(Program p) {
        for (StructTypeDecl std: p.structTypeDecls){
            visitStructTypeDecl(std);
        }
        for (VarDecl vd: p.varDecls){
            visitVarDecl(vd);
        }
        for (FunDecl fd: p.funDecls){
            visitFunDecl(fd);
        }
        return BaseType.VOID;
    }

    @Override
    public Type visitStructTypeDecl(StructTypeDecl st) {
        return st.st.accept(this);
    }

    @Override
    public Type visitVarDecl(VarDecl vd) {
        if (vd.type == BaseType.VOID) {
            error("cannot declare type \"void\" for variable " + vd.varName);
        }
        return vd.type;
    }

    @Override
    public Type visitFunDecl(FunDecl fd) {
        // if p is buildin func, skip checking
        if (fd.isBuildIn){
            return fd.type;
        }

        for (VarDecl vd: fd.params){
            visitVarDecl(vd);
        }

        // need to detect possible "Return" stmt in block and return as its type
        Type to_return = visitBlock(fd.block);
        if (to_return == null){
            if (fd.type == BaseType.VOID){
                return fd.type;
            }else {
                error("function "+fd.name+" has no return value but it is not void.");
                return fd.type;
            }
        }
        if (!equal(to_return, fd.type)){
            error("function "+fd.name+" return type mismatch: expected: "+fd.type.toString()+", found: "+to_return.toString());
        }
        return fd.type;
    }

    @Override
    public Type visitStructType(StructType st) {
        return st;
    }

    @Override
    public Type visitBaseType(BaseType bt) {
        return bt;
    }

    @Override
    public Type visitPointerType(PointerType pt) {
        return pt;
    }

    @Override
    public Type visitArrayType(ArrayType at) {
        return at;
    }

    // note: for Block, While, If and Return, the Type they returned represent that
    // they find a "Return" stmt inside their block, for the function they belong to
    // "return null" means there is no "Return" stmt in its scope
    @Override
    public Type visitBlock(Block b) {
        for (VarDecl vd: b.varDecls){
            visitVarDecl(vd);
        }
        List<Type> returnStmts = new ArrayList<>();
        for (Stmt s: b.stmts){
            if (possibleReturn(s)){
                Type maybeReturn = s.accept(this);
                if (maybeReturn != null){
                    // only load not-null return result
                    returnStmts.add(maybeReturn);
                }

            }else {
                s.accept(this);
            }
        }

        if (returnStmts.isEmpty()){
            return null;
        }else {
            // possible bug here, TODO: need more test
            Type to_compare = returnStmts.get(0);
            for (Type t: returnStmts){
                if (!equal(to_compare, t)){
                    error("Block has inconsistent \"Return\" statements: "+ Arrays.toString(returnStmts.toArray()));
                    break;
                }
            }
            return returnStmts.get(0);
        }
    }

    @Override
    public Type visitWhile(While w) {
        Type condition = w.expr.accept(this);
        if (!equal(condition, BaseType.INT)){
            error("expect INT for condition of while, found: " + w.expr.type.toString());
        }

        if (possibleReturn(w.stmt)){
            return w.stmt.accept(this);
        }else {
            w.stmt.accept(this);
            return null;
        }
    }

    @Override
    public Type visitIf(If i) {
        Type condition = i.expr.accept(this);
        if (!equal(condition, BaseType.INT)){
            error("expect INT for condition of while, found: " + i.expr.type.toString());
        }
        Type maybeReturn1 = null;
        if (possibleReturn(i.stmt)){
            maybeReturn1 = i.stmt.accept(this);
        }else {
            i.stmt.accept(this);
            return null;
        }

        if (i.elseStmt != null){
            Type maybeReturn2 = null;
            if (possibleReturn(i.elseStmt)){
                maybeReturn2 = i.elseStmt.accept(this);
            }else {
                i.elseStmt.accept(this);
                return null;
            }
            if (!equal(maybeReturn1, maybeReturn2)){
                if (maybeReturn1 == null || maybeReturn2 == null){
                    // bug fix: return non-null branch type
                    return maybeReturn1 == null ? maybeReturn2 : maybeReturn1;
                }
                error("return types are inconsistent in if/else branch: "+maybeReturn1.toString()+" vs "+maybeReturn2.toString());
            }
        }
        return maybeReturn1;
    }

    @Override
    public Type visitReturn(Return r) {
        if (r.expr == null) {
            return BaseType.VOID;
        }else {
            return r.expr.accept(this);
        }
    }

    @Override
    public Type visitExprStmt(ExprStmt es) {
        return es.expr.accept(this);
    }

    @Override
    public Type visitAssign(Assign a) {
        if (!a.lhs.isLegalLeftForAssign()) {
            error("left value cannot be " + a.lhs.toString() + " (must be variable/field access/array access/pointer dereference)");
        }

        Type lhs = a.lhs.accept(this);
        Type rhs = a.rhs.accept(this);

        if (lhs == BaseType.VOID || lhs.isArrayType()) {
            error("left cannot be " + lhs.toString());
        }

        if (!equal(lhs, rhs)) {
            error("Type inconsistent in assignment: " + lhs.toString()+" vs "+rhs.toString());
        }
        return null;
    }

    @Override
    public Type visitIntLiteral(IntLiteral il) {
        il.type = BaseType.INT;
        return il.type;
    }

    @Override
    public Type visitStrLiteral(StrLiteral sl) {
        sl.type = new ArrayType(BaseType.CHAR, sl.value.length()+1);
        return sl.type;
    }

    @Override
    public Type visitChrLiteral(ChrLiteral cl) {
        cl.type = BaseType.CHAR;
        return cl.type;
    }

    @Override
    public Type visitVarExpr(VarExpr v) {
        v.type = v.vd.type;
        return v.type;
    }

    @Override
    public Type visitFunCallExpr(FunCallExpr fce) {

        // is necessary to check funcDecl existence here??


        List<VarDecl> declaredParaDecls = fce.fd.params;
        Type declaredFuncType = fce.fd.type;

        List<Expr> actualArgs = fce.exprs;

        fce.type = declaredFuncType;

        if (declaredParaDecls.size() != actualArgs.size()){
            error("Arity mismatch: expected "+Integer.toString(declaredParaDecls.size())+" found "+Integer.toString(actualArgs.size()));
            return fce.type;
        }

        for (int i = 0; i < declaredParaDecls.size(); i++){
            Type argType = actualArgs.get(i).accept(this);
            if (!equal(argType, declaredParaDecls.get(i).type)){
                error("Type mismatch: in "+Integer.toString(i)+"th argument, expected type "+declaredParaDecls.get(i).type.toString()+" found: "+argType.toString());
            }
        }

        return fce.type;
    }

    private boolean isArithmtic(Op op){
        if (op == Op.ADD || op == Op.SUB || op == Op.MUL || op == Op.DIV || op == Op.MOD || op == Op.OR || op == Op.AND || op == Op.GT || op == Op.LT || op == Op.GE || op == Op.LE){
            return true;
        }else if (op == Op.NE || op == Op.EQ){
            return false;
        }else {
            error("can't find binary operator");
            assert false;
            return false;
        }
    }

    @Override
    public Type visitBinOp(BinOp bo) {
        Type lhs = bo.lhs.accept(this);
        Type rhs = bo.rhs.accept(this);
        bo.type = BaseType.INT;

        if (isArithmtic(bo.operator)){
            if (lhs == BaseType.INT && rhs == BaseType.INT){
                bo.type = BaseType.INT;
                return bo.type.accept(this);
            }else {
                error("Type mismatch in operator "+bo.operator.toString()+": "+lhs.toString()+" vs "+rhs.toString());
                return bo.type;// maybe null?
            }
        }else {
            if (!lhs.isArrayType() && !lhs.isStructType() && lhs != BaseType.VOID &&
                    !rhs.isArrayType() && !rhs.isStructType() && rhs != BaseType.VOID &&
                    equal(lhs, rhs)){
                bo.type = BaseType.INT;
                return bo.type.accept(this);
            }else {
                error("Type mismatch in equivalence operator "+bo.operator.toString()+": "+lhs.toString()+" vs "+rhs.toString()+"(cannot compare struct type or array type using equivalence operator");
                return bo.type;
            }
        }
    }

    @Override
    public Type visitArrayAccessExpr(ArrayAccessExpr aae) {
        Type expr = aae.array.accept(this);
        Type index = aae.index.accept(this);

        if (index != BaseType.INT) {
            error("Type mismatch: expect INT for array access index, found: "+index.toString());
        }

        if (!(expr.isArrayType() || expr.isPointerType())) {
            error("Expected ArrayType or PointerType for array access, found: "+expr.toString());
            return expr;
        }

        Type innerType = null;
        if (expr.isArrayType()){
            innerType = ((ArrayType)expr).innerType;
        }else if (expr.isPointerType()){
            innerType = ((PointerType)expr).type;
        }else {
            error("can't find proper innerType");
            assert false;
        }
        aae.type = innerType;
        return aae.type.accept(this);
    }

    @Override
    public Type visitFieldAccessExpr(FieldAccessExpr fae) {
        Type struType = fae.stru.accept(this);
        if (!struType.isStructType()){
            error("Type mismatch: expect struct type, found: "+struType.toString());
            return BaseType.VOID;
        }

        StructTypeDecl decl = ((StructType)struType).decl;
        VarDecl vd = null;
        for (VarDecl v: decl.varDecls){
            if (v.varName.equals(fae.field)){
                vd = v;
                break;
            }
        }

        if (vd == null){
            error("Undefined field \""+fae.field+"\" in struct type "+struType.toString());
            return BaseType.VOID;
        }
        fae.type = vd.type.accept(this);
        return fae.type;
    }

    @Override
    public Type visitValueAtExpr(ValueAtExpr vae) {
        Type pointer = vae.toDeref.accept(this);
        if (!pointer.isPointerType()) {
            error("Expected pointer type for dereference in expr " + vae.toString()+ ", found: "+ pointer);
            vae.type = BaseType.VOID;
            return vae.type;
        }

        vae.type = ((PointerType)pointer).type;
        return vae.type;
    }

    @Override
    public Type visitSizeOfExpr(SizeOfExpr soe) {
        soe.type = BaseType.INT;
        return soe.type;
    }

    @Override
    public Type visitTypecastExpr(TypecastExpr te) {

        Type from = te.fromExpr.accept(this);
        Type target = te.target;

        boolean validTypeCast = false;
        if (from == BaseType.CHAR && target == BaseType.INT){
            validTypeCast = true;
        }else if (from.isArrayType() && target.isPointerType()){
            validTypeCast = equal(((ArrayType)from).innerType, ((PointerType)target).type);
        }else if (from.isPointerType() && target.isPointerType()){
            validTypeCast = true;
        }

        if (!validTypeCast){
            error("Illegal type cast from "+from.toString()+" to "+target.toString());
        }
        te.type = target;
        return te.type;
    }

    @Override
    public Type visitOp(Op o) {
        return null; // do nothing
    }
}
