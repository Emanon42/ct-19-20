package ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunDecl implements ASTNode {
    public final Type type;
    public final String name;
    public final List<VarDecl> params;
    public final Block block;
    public final boolean isBuildIn;

    public FunDecl(Type type, String name, List<VarDecl> params, Block block) {
	    this.type = type;
	    this.name = name;
	    this.params = params;
	    this.block = block;
	    this.isBuildIn = false;
    }

    // for buildIn func only
    public FunDecl(Type type, String name, Type paramType, String param) {
        this.type = type;
        this.name = name;

        this.params = new ArrayList<>();
        if (paramType != null) {
            params.add(new VarDecl(paramType, param));
        }

        this.block = new Block(Collections.emptyList(), Collections.emptyList());
        this.isBuildIn = true;
    }

    public FunDecl(Type type, String name) {
        this.type = type;
        this.name = name;

        this.params = new ArrayList<>();

        this.block = new Block(Collections.emptyList(), Collections.emptyList());
        this.isBuildIn = true;
    }

    public <T> T accept(ASTVisitor<T> v) {
	return v.visitFunDecl(this);
    }
}
