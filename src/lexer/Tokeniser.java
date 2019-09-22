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
    private static final Map<Character, Character> escapeChar;

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

        // initialize init symbol set and load
        legalInitSymbols = new HashSet<Character>();

        legalInitSymbols.add('=');
        legalInitSymbols.add('!');
        legalInitSymbols.add('<');
        legalInitSymbols.add('>');
        legalInitSymbols.add('&');
        legalInitSymbols.add('|');

        // initialize escape char table
        escapeChar = new HashMap<Character, Character>();

        escapeChar.put('t', '\t');
        escapeChar.put('b', '\b');
        escapeChar.put('n', '\n');
        escapeChar.put('r', '\r');
        escapeChar.put('f', '\f');
        escapeChar.put('\'', '\'');
        escapeChar.put('\"', '\"');
        escapeChar.put('\\', '\\');
        escapeChar.put('0', (char)0);
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

        // skip the single line comment
        if (c == '/' && scanner.hasNext() && scanner.peek() == '/'){
            //TODO: test single line comment mode
            while (scanner.hasNext()){
                if (scanner.next() == '\n'){
                    return next();
                }
            }
            return next(); // fix: single comment get recognized as DIV
        }

        // multi line comment
        if (c == '/' && scanner.hasNext() && scanner.peek() == '*'){
            //TODO: test multi-line comment mode
            scanner.next(); // fix: consume the '*'
            while (scanner.hasNext()){
                if (scanner.next() == '*' && scanner.hasNext() && scanner.peek() == '/'){
                    scanner.next(); // fix: consume the last '/'
                    return next();
                }
            }
            return next();
        }


        // identifier/keyword mode, build a string then check if it is a keyword. Otherwise, identifier.
        if (c == '_' || Character.isLetter(c)){
            //TODO: test and debug identifier/keyword mode
            StringBuilder data = new StringBuilder();
            data.append(c);
            if (!scanner.hasNext()){
                return new Token(TokenClass.IDENTIFIER, data.toString(), line, column);
            }

            // bug fix for scanner "eat up" next char
            if (scanner.peek() != '_' && !Character.isLetter(scanner.peek()) && !Character.isDigit(scanner.peek())){
                String result = data.toString();
                if (keywordMap.containsKey(result)){
                    return new Token(keywordMap.get(result), line, column);
                }else {
                    return new Token(TokenClass.IDENTIFIER, result, line, column);
                }
            }else {
                c = scanner.next();
            }

            while ((c == '_' || Character.isLetter(c) || Character.isDigit(c))){
                data.append(c);
                if (!scanner.hasNext()){
                    return new Token(TokenClass.IDENTIFIER, data.toString(), line, column);
                }
                // bug fix for scanner "eat up" next char
                if (scanner.peek() != '_' && !Character.isLetter(scanner.peek()) && !Character.isDigit(scanner.peek())){
                    break;
                }else {
                    c = scanner.next();
                }

            }

            String result = data.toString();
            if (keywordMap.containsKey(result)){
                return new Token(keywordMap.get(result), line, column);
            }else {
                return new Token(TokenClass.IDENTIFIER, result, line, column);
            }
        }

        // only for #include token
        if (c == '#'){
            //TODO: test the #include check
            char[] include = "include".toCharArray();
            for (char inc : include){
                if (!scanner.hasNext()){
                    error++;
                    return new Token(TokenClass.INVALID, line, column);
                }
                c = scanner.next();
                if (c != inc){
                    error++;
                    return new Token(TokenClass.INVALID, line, column);
                }
            }
            return new Token(TokenClass.INCLUDE, line, column);
        }

        // multi symbol mode
        if (legalInitSymbols.contains(c)){
            //TODO: test multi symbol mode
            StringBuilder data = new StringBuilder();
            data.append(c);
            if (!scanner.hasNext()){
                // back to single symbol mode
                if (data.length() == 1){
                    if (singleSymbolMap.containsKey(data.toString().charAt(0))){
                        return new Token(singleSymbolMap.get(data.toString().charAt(0)), line, column);
                    }else {
                        error++;
                        return new Token(TokenClass.INVALID, line, column);
                    }
                }else {
                    if (multiSymbolMap.containsKey(data.toString())){
                        return new Token(multiSymbolMap.get(data.toString()), line, column);
                    }else {
                        error++;
                        return new Token(TokenClass.INVALID, line, column);
                    }
                }
            }

            // bug fix for scanner "eat up" next char
            if (!legalInitSymbols.contains(scanner.peek())){
                if (data.length() == 1){
                    if (singleSymbolMap.containsKey(data.toString().charAt(0))){
                        return new Token(singleSymbolMap.get(data.toString().charAt(0)), line, column);
                    }else {
                        error++;
                        return new Token(TokenClass.INVALID, line, column);
                    }
                }else {
                    if (multiSymbolMap.containsKey(data.toString())){
                        return new Token(multiSymbolMap.get(data.toString()), line, column);
                    }else {
                        error++;
                        return new Token(TokenClass.INVALID, line, column);
                    }
                }
            }else {
                c = scanner.next();
            }


            while (legalInitSymbols.contains(c)){
                data.append(c);
                if (!scanner.hasNext()){
                    if (data.length() == 1){
                        if (singleSymbolMap.containsKey(data.toString().charAt(0))){
                            return new Token(singleSymbolMap.get(data.toString().charAt(0)), line, column);
                        }else {
                            error++;
                            return new Token(TokenClass.INVALID, line, column);
                        }
                    }else {
                        if (multiSymbolMap.containsKey(data.toString())){
                            return new Token(multiSymbolMap.get(data.toString()), line, column);
                        }else {
                            error++;
                            return new Token(TokenClass.INVALID, line, column);
                        }
                    }
                }

                // bug fix for scanner "eat up" next char
                if (!legalInitSymbols.contains(scanner.peek())){
                    break;
                }else {
                    c = scanner.next();
                }
            }

            // decide single or multi symbol
            if (data.length() == 1){
                if (singleSymbolMap.containsKey(data.toString().charAt(0))){
                    return new Token(singleSymbolMap.get(data.toString().charAt(0)), line, column);
                }else {
                    error++;
                    return new Token(TokenClass.INVALID, line, column);
                }
            }else {
                if (multiSymbolMap.containsKey(data.toString())){
                    return new Token(multiSymbolMap.get(data.toString()), line, column);
                }else {
                    error++;
                    return new Token(TokenClass.INVALID, line, column);
                }
            }

        }

        // single symbol mode
        if (singleSymbolMap.containsKey(c)){
            //TODO: test single symbol mode
            return new Token(singleSymbolMap.get(c), line, column);
        }

        // string literal mode
        if (c == '\"'){
            //TODO: string literal mode
            if (!scanner.hasNext()){
                error++;
                return new Token(TokenClass.INVALID, line, column);
            }
            StringBuilder data = new StringBuilder();
            c = scanner.next();

            while (c != '\"'){
                // error for illegal newline char in string
                if (c == '\n' || c == '\r'){
                    error++;
                    return new Token(TokenClass.INVALID, line, column);
                }

                if (c == '\\'){
                    // process escape sequence
                    if (!scanner.hasNext()){
                        error++;
                        return new Token(TokenClass.INVALID, line, column);
                    }
                    c = scanner.next();
                    if (escapeChar.containsKey(c)){
                        data.append(escapeChar.get(c));
                        c = scanner.next();
                    }else {
                        error++;
                        return new Token(TokenClass.INVALID, line, column);
                    }
                }else {
                    data.append(c);
                    if (!scanner.hasNext()){
                        error++;
                        return new Token(TokenClass.INVALID, line, column);
                    }
                    c = scanner.next();
                }
            }
            return new Token(TokenClass.STRING_LITERAL, data.toString(), line, column);
        }

        // char literal mode (only allow single char)
        if (c == '\''){
            //TODO: test char literal mode
            if (!scanner.hasNext()){
                error++;
                return new Token(TokenClass.INVALID, line, column);
            }
            StringBuilder data = new StringBuilder();
            c = scanner.next();

            // error for illegal newline char in string
            if (c == '\n' || c == '\r'){
                error++;
                return new Token(TokenClass.INVALID, line, column);
            }

            if (c == '\\'){
                // process escape sequence
                if (!scanner.hasNext()){
                    error++;
                    return new Token(TokenClass.INVALID, line, column);
                }
                c = scanner.next();
                if (escapeChar.containsKey(c)){
                    data.append(escapeChar.get(c));
                    c = scanner.next();
                }else {
                    error++;
                    return new Token(TokenClass.INVALID, line, column);
                }
            }else {
                data.append(c);
                if (!scanner.hasNext()){
                    error++;
                    return new Token(TokenClass.INVALID, line, column);
                }
                c = scanner.next();
            }
            
            if (c == '\''){
                return new Token(TokenClass.CHAR_LITERAL, data.toString(), line, column);
            }else {
                error++;
                return new Token(TokenClass.INVALID, line, column);
            }
            
        }

        // integer literal mode
        if (Character.isDigit(c)){
            //TODO: integer literal mode
            StringBuilder data = new StringBuilder();
            data.append(c);
            if (!scanner.hasNext()){
                return new Token(TokenClass.INT_LITERAL, data.toString(), line, column);
            }
            c = scanner.next();
            
            while (Character.isDigit(c)){
                data.append(c);
                if (!scanner.hasNext()){
                    return new Token(TokenClass.INT_LITERAL, data.toString(), line, column);
                }
                c = scanner.next();
            }
            
            return new Token(TokenClass.INT_LITERAL, data.toString(), line, column);
        }


        // if we reach this point, it means we did not recognise a valid token
        error(c, line, column);
        error++;
        return new Token(TokenClass.INVALID, line, column);
    }


}
