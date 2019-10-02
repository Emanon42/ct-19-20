package parser;

import lexer.Token;
import lexer.Tokeniser;
import lexer.Token.TokenClass;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;


/**
 * @author cdubach
 */
public class Parser {

    private Token token;

    // use for backtracking (useful for distinguishing decls from procs when parsing a program for instance)
    private Queue<Token> buffer = new LinkedList<>();

    private final Tokeniser tokeniser;

    private static final TokenClass[] typeList = {
            TokenClass.INT,
            TokenClass.CHAR,
            TokenClass.VOID,
            TokenClass.STRUCT
    };

    private static final TokenClass[] expFirst = {
            TokenClass.SIZEOF,
            TokenClass.ASTERIX,
            TokenClass.LPAR,
            TokenClass.MINUS,
            TokenClass.IDENTIFIER,
            TokenClass.INT_LITERAL,
            TokenClass.CHAR_LITERAL,
            TokenClass.STRING_LITERAL
    };

    private static final TokenClass[] expBottomFirst = {
            TokenClass.LPAR,
            TokenClass.IDENTIFIER,
            TokenClass.INT_LITERAL,
            TokenClass.CHAR_LITERAL,
            TokenClass.STRING_LITERAL
    };

    private static final TokenClass[] stmtFirst = Stream.concat(
            Stream.of(TokenClass.LBRA,
                      TokenClass.WHILE,
                      TokenClass.IF,
                      TokenClass.RETURN),
            Arrays.stream(expFirst)
    ).toArray(TokenClass[]::new);




    public Parser(Tokeniser tokeniser) {
        this.tokeniser = tokeniser;
    }

    public void parse() {
        // get the first token
        nextToken();

        parseProgram();
    }

    public int getErrorCount() {
        return error;
    }

    private int error = 0;
    private Token lastErrorToken;

    private void error(TokenClass... expected) {

        if (lastErrorToken == token) {
            // skip this error, same token causing trouble
            return;
        }

        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (TokenClass e : expected) {
            sb.append(sep);
            sb.append(e);
            sep = "|";
        }
        System.out.println("Parsing error: expected ("+sb+") found ("+token+") at "+token.position);

        error++;
        lastErrorToken = token;
    }

    /*
     * Look ahead the i^th element from the stream of token.
     * i should be >= 1
     */
    private Token lookAhead(int i) {
        // ensures the buffer has the element we want to look ahead
        while (buffer.size() < i)
            buffer.add(tokeniser.nextToken());
        assert buffer.size() >= i;

        int cnt=1;
        for (Token t : buffer) {
            if (cnt == i)
                return t;
            cnt++;
        }

        assert false; // should never reach this
        return null;
    }

    /*
     * Returns true if peeked token is equals to any of the expected ones.
     */
    private boolean lookAheadAccept(int i, TokenClass... expected) {
        boolean result = false;
        for (TokenClass e : expected)
            result |= (e == lookAhead(i).tokenClass);
        return result;
    }


    /*
     * Consumes the next token from the tokeniser or the buffer if not empty.
     */
    private void nextToken() {
        if (!buffer.isEmpty())
            token = buffer.remove();
        else
            token = tokeniser.nextToken();
        //System.out.println(token);
    }

    /*
     * If the current token is equals to the expected one, then skip it, otherwise report an error.
     * Returns the expected token or null if an error occurred.
     */
    private Token expect(TokenClass... expected) {
        for (TokenClass e : expected) {
            if (e == token.tokenClass) {
                Token cur = token;
                nextToken();
                return cur;
            }
        }

        error(expected);
        return null;
    }

    /*
    * Returns true if the current token is equals to any of the expected ones.
    */
    private boolean accept(TokenClass... expected) {
        boolean result = false;
        for (TokenClass e : expected)
            result |= (e == token.tokenClass); // result = result | (e == token.tokenClass)
        return result;
    }


    private void parseProgram() {
        parseIncludes();
        parseStructDecls();
        parseVarDecls();
        parseFunDecls();
        expect(TokenClass.EOF);
    }

    // includes are ignored, so does not need to return an AST node
    private void parseIncludes() {
        if (accept(TokenClass.INCLUDE)) {
            nextToken();
            expect(TokenClass.STRING_LITERAL);
            parseIncludes();
        }
        // do nothing after if statement corresponding to branch of epsilon in ebnf
    }

    private void parseStructDecls() {
        if (accept(TokenClass.STRUCT) && lookAheadAccept(2, TokenClass.LBRA)){
            nextToken(); // consume the struct token
            expect(TokenClass.IDENTIFIER);
            expect(TokenClass.LBRA);
            parseVarDecls_pos(); // one or more field
            expect(TokenClass.RBRA);
            expect(TokenClass.SC);
            parseStructDecls();
        }
    }

    private void parseType(){
        if (accept(TokenClass.STRUCT)){
            nextToken();
            expect(TokenClass.IDENTIFIER); // name of struct type
        }else if (accept(TokenClass.INT, TokenClass.CHAR, TokenClass.VOID)){
            nextToken();
        }else{
            // raise an error if not match type keyword
            error(typeList);
            return;
        }
        // skip the '*'
        if (accept(TokenClass.ASTERIX)){
            nextToken();
        }

    }

    private void parseVarDecls_pos(){
        if (accept(typeList)){
            parseVarDecls();
        }else {
            // if not match type decl, raise an error
            error(typeList);
        }
    }

    private void parseVarDecls() {
        if (accept(typeList) && !lookAheadAccept(2, TokenClass.LPAR) && !lookAheadAccept(3, TokenClass.LPAR) && !lookAheadAccept(4, TokenClass.LPAR)){
            parseType();
            expect(TokenClass.IDENTIFIER); // name of var
            if (accept(TokenClass.SC)){
                nextToken();
                parseVarDecls();
            }else if (accept(TokenClass.LSBR)){
                nextToken();
                expect(TokenClass.INT_LITERAL);
                expect(TokenClass.RSBR);
                expect(TokenClass.SC);
                parseVarDecls();
            }else {
                error(TokenClass.SC, TokenClass.LSBR);
            }
        }
    }

    private void parseFunDecls() {
        if (accept(typeList)){
            parseType();
            expect(TokenClass.IDENTIFIER);
            expect(TokenClass.LPAR);
            parseParams();
            expect(TokenClass.RPAR);
            parseBlock();
            parseFunDecls();
        }

    }

    private void parseParams(){
        if (accept(typeList)){
            parseType();
            expect(TokenClass.IDENTIFIER);
            parseParams_kle();
        }
    }

    private void parseParams_kle(){
        if (accept(TokenClass.COMMA)){
            nextToken();
            if (accept(typeList)){
                parseType();
                expect(TokenClass.IDENTIFIER);
            }else {
                error(typeList);
            }
            parseParams_kle();
        }
    }

    private void parseBlock(){
        expect(TokenClass.LBRA);
        parseVarDecls();
        parseStmts_kle();
        expect(TokenClass.RBRA);
    }

    private void parseStmts_kle(){
        if (accept(stmtFirst)){
            parseStmts();
            parseStmts_kle();
        }
    }

    private void parseStmts(){
        if (accept(TokenClass.LBRA)){
            parseBlock();
        }else if (accept(TokenClass.WHILE)){
            nextToken();
            expect(TokenClass.LPAR);
            parseExp();
            expect(TokenClass.RPAR);
            parseStmts();
        }else if (accept(TokenClass.IF)){
            nextToken();
            expect(TokenClass.LPAR);
            parseExp();
            expect(TokenClass.RPAR);
            parseStmts();
            parseStmt_else();
        }else if (accept(TokenClass.RETURN)){
            nextToken();
            parseExp_opt();
            expect(TokenClass.SC);
        }else if (accept(expFirst)){
            parseExp();
            if (accept(TokenClass.ASSIGN)){
                nextToken();
                parseExp();
                expect(TokenClass.SC);
            }else if (accept(TokenClass.SC)){
                nextToken();
            }else {
                error(TokenClass.SC, TokenClass.ASSIGN);
            }
        }else{
            error(stmtFirst);
        }
    }

    private void parseStmt_else(){
        if (accept(TokenClass.ELSE)){
            nextToken();
            parseStmts();
        }
    }

    private void parseExp_opt(){
        if (accept(expFirst)){
            parseExp();
        }
    }

    private void parseExp(){
        if (accept(expFirst)){
            parseExp_or();
        }else {
            error(expFirst);
        }
    }

    private void parseExp_or(){
        if (accept(expFirst)){
            parseExp_and();
            parseExp_or_term();
        }else {
            error(expFirst);
        }

    }

    private void parseExp_or_term(){
        if (accept(TokenClass.OR)){
            nextToken();
            parseExp_and();
            parseExp_or_term();
        }
    }

    private void parseExp_and(){
        if (accept(expFirst)){
            parseExp_eq();
            parseExp_and_term();
        }else {
            error(expFirst);
        }

    }

    private void parseExp_and_term(){
        if (accept(TokenClass.AND)){
            nextToken();
            parseExp_eq();
            parseExp_and_term();
        }
    }

    private void parseExp_eq(){
        if (accept(expFirst)){
            parseExp_comp();
            parseExp_eq_term();
        }else {
            error(expFirst);
        }
    }

    private void parseExp_eq_term(){
        if (accept(TokenClass.EQ, TokenClass.NE)){
            nextToken();
            parseExp_comp();
            parseExp_eq_term();
        }
    }

    private void parseExp_comp(){
        if (accept(expFirst)){
            parseExp_add();
            parseExp_comp_term();
        }else {
            error(expFirst);
        }

    }

    private void parseExp_comp_term(){
        if (accept(TokenClass.LT, TokenClass.GT, TokenClass.LE, TokenClass.GE)){
            nextToken();
            parseExp_add();
            parseExp_comp_term();
        }
    }

    private void parseExp_add(){
        if (accept(expFirst)){
            parseExp_mult();
            parseExp_add_term();
        }else {
            error(expFirst);
        }

    }

    private void parseExp_add_term(){
        if (accept(TokenClass.PLUS, TokenClass.MINUS)){
            nextToken();
            parseExp_mult();
            parseExp_add_term();
        }
    }

    private void parseExp_mult(){
        if (accept(expFirst)){
            parseExp_lv2();
            parseExp_mult_term();
        }else {
            error(expFirst);
        }

    }

    private void parseExp_mult_term(){
        if (accept(TokenClass.ASTERIX, TokenClass.DIV, TokenClass.REM)){
            nextToken();
            parseExp_lv2();
            parseExp_mult_term();
        }
    }

    private void parseExp_lv2(){
        if (accept(TokenClass.SIZEOF)){
            nextToken();
            expect(TokenClass.LPAR);
            parseType();
            expect(TokenClass.RPAR);
        }else if (accept(TokenClass.ASTERIX)){
            nextToken();
            parseExp_lv2();
        }else if (accept(TokenClass.LPAR)){
            nextToken();
            parseType();
            expect(TokenClass.RPAR);
            parseExp_lv2();
        }else if (accept(TokenClass.MINUS)){
            nextToken();
            parseExp_lv2();
        }else if (accept(expBottomFirst)){
            parseExp_lv1();
        }else {
            error(expFirst);
        }
    }

    private void parseExp_lv1(){
        if (accept(expBottomFirst)){
            parseExp_bottom();
            parseExp_lv1_term();
        }else {
            error(expBottomFirst);
        }

    }

    private void parseExp_bottom(){
        if (accept(expBottomFirst)){
            if (accept(TokenClass.LPAR)){
                nextToken();
                parseExp();
                expect(TokenClass.RPAR);
            }else if (accept(TokenClass.INT_LITERAL, TokenClass.CHAR_LITERAL, TokenClass.STRING_LITERAL)){
                nextToken();
            }else if (accept(TokenClass.IDENTIFIER) && lookAheadAccept(1, TokenClass.LPAR)){
                // fn_call
                parseFn_call();
            }else if (accept(TokenClass.IDENTIFIER)){
                nextToken();
            }
        }else {
            error(expBottomFirst);
        }
    }

    private void parseExp_lv1_term(){
        if (accept(TokenClass.DOT)){
            nextToken();
            expect(TokenClass.IDENTIFIER);
            parseExp_lv1_term();
        }else if (accept(TokenClass.LSBR)){
            nextToken();
            parseExp();
            expect(TokenClass.RSBR);
            parseExp_lv1_term();
        }
    }

    private void parseFn_call(){
        expect(TokenClass.IDENTIFIER);
        expect(TokenClass.LPAR);
        parseArgs_opt();
        expect(TokenClass.RPAR);
    }

    private void parseArgs_opt(){
        if (accept(expFirst)){
            parseExp();
            parseArgs_kle();
        }
    }

    private void parseArgs_kle(){
        if (accept(TokenClass.COMMA)){
            nextToken();
            parseExp();
            parseArgs_kle();
        }
    }
}
