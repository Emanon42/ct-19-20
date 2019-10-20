package sem;

public enum SymbolEnum {
    VAR_SIMBOL, STRU_SYMBOL, FN_SIMBOL;
    @Override
    public String toString(){
        switch(this){
            case VAR_SIMBOL: return "Variable declartion";
            case STRU_SYMBOL: return "Struct declaration";
            case FN_SIMBOL: return "Function declaration";
            default: throw new IllegalArgumentException();
        }
    }
}
