package sem;

import ast.*;

public class NameAnalysisVisitor extends BaseSemanticVisitor<Void> {
	public Scope scope;

	public NameAnalysisVisitor(){
		scope = new Scope();
	}

	public NameAnalysisVisitor(Scope s){
		this.scope = s;
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
		// To be completed...
		return null;
	}










	@Override
	public Void visitBaseType(BaseType bt) {
		// To be completed...
		return null;
	}



	@Override
	public Void visitBlock(Block b) {
		// To be completed...
		return null;
	}

	@Override
	public Void visitFunDecl(FunDecl p) {
		// To be completed...
		return null;
	}




	@Override
	public Void visitVarDecl(VarDecl vd) {
		// To be completed...
		return null;
	}

	@Override
	public Void visitVarExpr(VarExpr v) {
		// To be completed...
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
	// To be completed...


}
