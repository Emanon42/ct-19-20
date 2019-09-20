package lexer;

import lexer.Token.TokenClass;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author cdubach
 */
public class Tokeniser {

    private Scanner scanner;

    private int error = 0;
    public int getErrorCount() {
	return this.error;
    }

    public Tokeniser(Scanner scanner) {
        this.scanner = scanner;
    }

    private void error(char c, int line, int col) {
        System.out.println("Lexing error: unrecognised character ("+c+") at "+line+":"+col);
	    error++;
    }

    private static final Map<String, TokenClass> keywordMap;
    private static final Map<Character, TokenClass> singleSymbolMap;
    private static final Map<String, TokenClass> multiSymbolMap;
    private static final HashSet<Character> legalInitSymbols;

    static {
        // initialize keyword hashmap and put value statically
        keywordMap = new HashMap<String, TokenClass>();

        keywordMap.put("int", TokenClass.INT);
        keywordMap.put("void", TokenClass.VOID);
        keywordMap.put("char", TokenClass.CHAR);

        keywordMap.put("if", TokenClass.IF);
        keywordMap.put("else", TokenClass.ELSE);
        keywordMap.put("while", TokenClass.WHILE);
        keywordMap.put("return", TokenClass.RETURN);
        keywordMap.put("struct", TokenClass.STRUCT);
        keywordMap.put("sizeof", TokenClass.SIZEOF);
        keywordMap.put("#include", TokenClass.INCLUDE);

        // initialize single symbol hashmap and put value statically
        singleSymbolMap = new HashMap<Character, TokenClass>();

        singleSymbolMap.put('=', TokenClass.ASSIGN);
        singleSymbolMap.put('<', TokenClass.LT);
        singleSymbolMap.put('>', TokenClass.GT);

        singleSymbolMap.put('{', TokenClass.LBRA);
        singleSymbolMap.put('}', TokenClass.RBRA);
        singleSymbolMap.put('(', TokenClass.LPAR);
        singleSymbolMap.put(')', TokenClass.RPAR);
        singleSymbolMap.put('[', TokenClass.LSBR);
        singleSymbolMap.put(']', TokenClass.RSBR);

        singleSymbolMap.put(';', TokenClass.SC);
        singleSymbolMap.put(',', TokenClass.COMMA);
        singleSymbolMap.put('.', TokenClass.DOT);

        singleSymbolMap.put('+', TokenClass.PLUS);
        singleSymbolMap.put('-', TokenClass.MINUS);
        singleSymbolMap.put('*', TokenClass.ASTERIX);
        singleSymbolMap.put('/', TokenClass.DIV);
        singleSymbolMap.put('%', TokenClass.REM);

        // initialize multi symbol hashmap and put value statically
        multiSymbolMap = new HashMap<String, TokenClass>();

        multiSymbolMap.put("==", TokenClass.EQ);
        multiSymbolMap.put("!=", TokenClass.NE);
        multiSymbolMap.put("<=", TokenClass.LE);
        multiSymbolMap.put(">=", TokenClass.GE);
        multiSymbolMap.put("&&", TokenClass.AND);
        multiSymbolMap.put("||", TokenClass.OR);

        // initialize init symbol set from symbol map
        legalInitSymbols = new HashSet<Character>(singleSymbolMap.keySet());
    }


    public Token nextToken() {
        Token result;
        try {
             result = next();
        } catch (EOFException eof) {
            // end of file, nothing to worry about, just return EOF token
            return new Token(TokenClass.EOF, scanner.getLine(), scanner.getColumn());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            // something went horribly wrong, abort
            System.exit(-1);
            return null;
        }
        return result;
    }

    /*
     * //TODO: To be completed
     */
    private Token next() throws IOException {

        int line = scanner.getLine();
        int column = scanner.getColumn();

        // get the next character
        char c = scanner.next();

        // skip white spaces
        if (Character.isWhitespace(c))
            return next();

        // string mode, build a string then check if it is a keyword. Otherwise, identifier.
        if (c == '#' || c == '_' || Character.isLetter(c)){
            //TODO: identifier/keyword mode
        }

        // single/multi symbol mode
        if (legalInitSymbols.contains(c)){
            //TODO: single/multi symbol mode
        }

        // string literal mode
        if (c == '\"'){
            //TODO: string literal mode
        }

        // char literal mode (only allow single char)
        if (c == '\''){
            //TODO: char literal mode
        }

        // integer literal mode
        if (Character.isDigit(c)){
            //TODO: integer literal mode
        }

        // comment mode
        if (c == '/'){
            //TODO: comment mode
        }


        // if we reach this point, it means we did not recognise a valid token
        error(c, line, column);
        return new Token(TokenClass.INVALID, line, column);
    }


}
