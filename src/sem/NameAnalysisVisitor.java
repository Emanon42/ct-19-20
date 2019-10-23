package sem;

import ast.*;

import java.util.Hashtable;
import java.util.Map;

public class NameAnalysisVisitor extends BaseSemanticVisitor<Void> {
	public Scope scope;
	public Map<String, StruTypeSymbol> globalStruTypeTable;

	public NameAnalysisVisitor(){
		this.scope = new Scope();
		globalStruTypeTable = new Hashtable<>();
	}

	public NameAnalysisVisitor(Scope s){
		this.scope = s;
		globalStruTypeTable = new Hashtable<>();
	}

	private boolean notDeclared(String name){
		Symbol s = scope.lookupCurrent(name);
		if (s == null){
			return true;
		}else {
			error("redefinition of \"" + s.name + "\" as different symbol");
			return false;
		}
	}

	@Override
	public Void visitProgram(Program p) {

		// To be completed...
		for (StructTypeDecl std : p.structTypeDecls){
			visitStructTypeDecl(std);
		}
		for (VarDecl vd : p.varDecls){
			visitVarDecl(vd);
		}
		for (FunDecl fd : p.funDecls){
			visitFunDecl(fd);
		}
		return null;
	}

	@Override
	public Void visitStructTypeDecl(StructTypeDecl sts) {
		// check in global struct type table instead of scope
		if (!globalStruTypeTable.containsKey(sts.st.ident)){

			// load to global struct type table
			StruTypeSymbol to_put = new StruTypeSymbol(sts);
			this.globalStruTypeTable.put(to_put.name, to_put);

			sts.st.accept(this); // visit its first subnode: StructType
			Scope oldScope = this.scope; // save current scope
			this.scope = new Scope(oldScope); // create and switch to new inner scope to process the vardecls
			for (VarDecl vd : sts.varDecls){
				// early check for "nested define struct field" and terminate
				if (vd.type.isStructType()){
					StructType to_check = (StructType) vd.type;
					if (to_check.ident.equals(sts.st.ident)){
						error("field has incomplete type " + sts.st.ident);
						break;
					}
				}
				visitVarDecl(vd);
			}
			this.scope = oldScope; // switch back
		}else {
			error("struct type: " + sts.st.ident + " has been declared!");
		}
		return null;
	}

	@Override
	public Void visitVarDecl(VarDecl vd) {
		if (notDeclared(vd.varName)){
			vd.type.accept(this);
			this.scope.put(new VarSymbol(vd));
		}
		return null;
	}

	@Override
	public Void visitStructType(StructType st) {
		if (globalStruTypeTable.containsKey(st.ident)){
			// possible multiple assign to same value here. maybe FIX later?
			st.decl = globalStruTypeTable.get(st.ident).std;
		}else {
			error("field has incomplete type " + st.ident);
		}
		return null;
	}

	@Override
	public Void visitBaseType(BaseType bt) {
		return null; // do nothing
	}

	@Override
	public Void visitPointerType(PointerType pt) {
		pt.type.accept(this);
		return null;
	}

	@Override
	public Void visitArrayType(ArrayType at) {
		at.innerType.accept(this);
		return null;
	}

	@Override
	public Void visitFunDecl(FunDecl p) {
		// should we let it analysis or just crash???
		notDeclared(p.name);
		scope.put(new FnSymbol(p));

		Scope oldScope = this.scope;
		this.scope = new Scope(oldScope);

		p.type.accept(this);
		for (VarDecl vd : p.params){
			visitVarDecl(vd);
		}
		visitFnBlk(p.block);

		this.scope = oldScope;
		return null;
	}


	// for block in funcDecl, no need to switch scope
	public void visitFnBlk(Block b){
		for (VarDecl vd : b.varDecls){
			visitVarDecl(vd);
		}
		for (Stmt s : b.stmts){
			s.accept(this);
		}
	}

	// for regular block, switch to inner scope
	@Override
	public Void visitBlock(Block b) {
		Scope oldScope = this.scope;
		this.scope = new Scope(oldScope);

		for (VarDecl vd : b.varDecls){
			visitVarDecl(vd);
		}
		for (Stmt s : b.stmts){
			s.accept(this);
		}

		this.scope = oldScope;
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

	@Override
	public Void visitExprStmt(ExprStmt es) {
		es.expr.accept(this);
		return null;
	}

	@Override
	public Void visitIntLiteral(IntLiteral il) {
		return null; // do nothing
	}

	@Override
	public Void visitStrLiteral(StrLiteral sl) {
		return null;
	}

	@Override
	public Void visitChrLiteral(ChrLiteral cl) {
		return null;
	}

	@Override
	public Void visitVarExpr(VarExpr v) {
		Symbol vs = this.scope.lookup(v.name);
		if (vs != null && vs.isVar()){
			v.vd = ((VarSymbol)vs).vd;
		}else if (vs != null && !vs.isVar()){
			error(v.name + " is not variable expression!");
		}else {
			error(v.name + " not declared!");
		}

		// fix for type check null pointer
		if (v.vd == null){
			v.vd = new VarDecl(BaseType.VOID, v.name);
		}

		return null;
	}

	@Override
	public Void visitFunCallExpr(FunCallExpr fce) {
		Symbol fs = this.scope.lookup(fce.ident);
		if (fs != null && fs.isFn()){
			fce.fd = ((FnSymbol)fs).fd;
		}else if (fs != null && !fs.isFn()){
			error(fce.ident + " is not a function!");
		}else {
			error(fce.ident + " not declared!");
		}
		for (Expr e: fce.exprs){
			e.accept(this);
		}

		// fix for type check null pointer
		if (fce.fd == null){
			fce.fd = new FunDecl(BaseType.VOID, fce.ident);
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
		te.fromExpr.accept(this);
		return null;
	}

	@Override
	public Void visitOp(Op o) {
		return null;
	}
}
