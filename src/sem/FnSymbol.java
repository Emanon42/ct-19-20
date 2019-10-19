package sem;

import ast.FunDecl;

public class FnSymbol extends Symbol{
    public final FunDecl fd;

    public FnSymbol(FunDecl fd){
        super(fd.name);
        this.fd = fd;
        this.type = SymbolEnum.FN_SIMBOL;
    }
}
