package sem;

import ast.StructTypeDecl;

public class StruSymbol extends Symbol{
    public final StructTypeDecl std;

    public StruSymbol(StructTypeDecl std){
        super(std.st.ident);
        this.std = std;
        this.type = SymbolEnum.STRU_SYMBOL;
    }
}
