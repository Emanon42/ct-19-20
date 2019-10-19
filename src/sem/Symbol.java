package sem;

public abstract class Symbol {
	public String name;
	public SymbolEnum type;
	
	
	public Symbol(String name) {
		this.name = name;
	}

	public boolean isVar(){
		return type == SymbolEnum.VAR_SIMBOL;
	}

	public boolean isStru(){
		return type == SymbolEnum.STRU_SYMBOL;
	}

	public boolean isFn(){
		return type == SymbolEnum.FN_SIMBOL;
	}
}
