package ast;

import java.util.HashMap;

public class VarDecl implements ASTNode {
    public final Type type;
    public final String varName;
    private Integer frameOffset = null; // relative position to current frame pointer - neg for lower, pos for higher
    private String asmLabel = null; // only set for global
    private HashMap<String, String> asmStructFieldLables; // only for struct var

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
        if (type.isStructType()){
            asmStructFieldLables = new HashMap<String, String>();
            for (VarDecl v : ((StructType) type).decl.varDecls) {
                // initialise each field label to empty str
                asmStructFieldLables.put(v.varName, "");
            }
        }
    }

    public String getGlobalLabel() {
        if (asmLabel == null) {
            throw new NullPointerException("trying to get null label for " + varName);
        }
        return asmLabel;
    }

    public void setAsmStructFieldLables(String field, String label){
        if (!isGlobal()) {
            throw new RuntimeException("struct is not global/no label: " + varName);
        }

        if (!type.isStructType()) {
            throw new RuntimeException("var is not a struct: " + varName);
        }

        if (label.isEmpty()) {
            throw new RuntimeException("can't assign empty label to struct "+varName);
        }

        // check if the field exists by seeing if the label map contains the key
        if (!asmStructFieldLables.containsKey(field)) {
            throw new RuntimeException("struct: "+varName+" does not have that field: " +field);
        }

        // we do not allow relabelling
        if (!asmStructFieldLables.get(field).isEmpty()) {
            throw new RuntimeException("struct: "+varName+" has already labelled field: " + field);
        }

        asmStructFieldLables.put(field, label);
    }

    // returns label for that field, if the vardecl is a struct
    public String getAsmStructFieldLables(String field) {
        if (asmStructFieldLables == null) {
            throw new RuntimeException("struct: "+varName+" is not global");
        }

        if (!asmStructFieldLables.containsKey(field)) {
            throw new RuntimeException("struct: "+varName+" does not have that field " + field);
        }

        String label = asmStructFieldLables.get(field);

        if (label.isEmpty()) {
            throw new RuntimeException("struct: "+varName+" has empty field" + field);
        }

        return label;
    }

}
