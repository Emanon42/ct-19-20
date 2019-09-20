package lexer;

import lexer.Token.TokenClass;

import java.io.EOFException;
import java.io.IOException;

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

        // recognises the plus operator
        if (c == '+')
            return new Token(TokenClass.PLUS, line, column);

        // ... to be completed
        // TODO: refactor to single exit point for better debug experience
        switch(c){
            case '-':
                return new Token(TokenClass.MINUS, line, column);
            case '/':
                return new Token(TokenClass.DIV, line, column);
            case '%':
                return new Token(TokenClass.REM, line, column);
            case '{':
                return new Token(TokenClass.LBRA, line, column);
            case '}':
                return new Token(TokenClass.RBRA, line, column);
            case '(':
                return new Token(TokenClass.LPAR, line, column);
            case ')':
                return new Token(TokenClass.RPAR, line, column);
            case '[':
                return new Token(TokenClass.LSBR, line, column);
            case ']':
                return new Token(TokenClass.RSBR, line, column);
            case ';':
                return new Token(TokenClass.SC, line, column);
            case ',':
                return new Token(TokenClass.COMMA, line, column);
            case '=':
                return new Token(TokenClass.ASSIGN, line, column);
            case '.':
                return new Token(TokenClass.DOT, line, column);
        }

        // if we reach this point, it means we did not recognise a valid token
        error(c, line, column);
        return new Token(TokenClass.INVALID, line, column);
    }


}
