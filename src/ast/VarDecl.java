package ast;

public class VarDecl implements ASTNode {
    public final Type type;
    public final String varName;
    private Integer frameOffset = null; // relative position to current frame pointer - neg for lower, pos for higher
    private String asmLabel = null; // only set for global

    public VarDecl(Type type, String varName) {
	    this.type = type;
	    this.varName = varName;
    }

     public <T> T accept(ASTVisitor<T> v) {
	return v.visitVarDecl(this);
    }

    public void setFrameOffset(int genStackOffset) {
        if (asmLabel != null) {
            throw new RuntimeException("unable to set offset for global var: " + varName);
        }
        if (this.frameOffset != null) {
            throw new RuntimeException("unable to set offset multiple times for " + varName);
        }
        this.frameOffset = genStackOffset;
    }

    public int getFrameOffset() {
        if (frameOffset == null) {
            throw new NullPointerException("trying to get null offset for " + varName);
        }
        return frameOffset;
    }

    public boolean isGlobal() {
        return asmLabel != null;
    }

    public void setGlobalLabel(String label) {
        if (asmLabel != null) {
            throw new RuntimeException("unable to set label multiple times for " + varName);
        }
        asmLabel = label;
    }

    public String getGlobalLabel() {
        if (asmLabel == null) {
            throw new NullPointerException("trying to get null label for " + varName);
        }
        return asmLabel;
    }
}
