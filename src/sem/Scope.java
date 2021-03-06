package sem;

import java.util.HashMap;
import java.util.Map;

public class Scope {
	private Scope outer;
	// only contains FnSym and VarSym.
	private Map<String, Symbol> symbolTable;
	
	public Scope(Scope outer) { 
		this.outer = outer;
		symbolTable = new HashMap<>();
	}
	
	public Scope() { this(null); }
	
	public Symbol lookup(String name) {
		Symbol s = lookupCurrent(name);
		if (s != null){
			return s;
		}else if (outer != null){
			return outer.lookup(name);
		}else {
			return null;
		}
	}
	
	public Symbol lookupCurrent(String name) {
		return symbolTable.getOrDefault(name, null);
	}
	
	public void put(Symbol sym) {
		symbolTable.put(sym.name, sym);
	}
}
