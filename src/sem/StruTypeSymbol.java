package sem;

import ast.StructTypeDecl;

public class StruTypeSymbol extends Symbol{
    public final StructTypeDecl std;

    public StruTypeSymbol(StructTypeDecl std){
        super(std.st.ident);
        this.std = std;
        this.type = SymbolEnum.STRU_SYMBOL;
    }
}
