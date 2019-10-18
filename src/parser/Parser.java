package parser;

import ast.*;

import lexer.Token;
import lexer.Tokeniser;
import lexer.Token.TokenClass;

import java.util.*;
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

    public Program parse() {
        // get the first token
        nextToken();

        return parseProgram();
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


    private Program parseProgram() {
        parseIncludes();
        List<StructTypeDecl> stds = parseStructDecls(new ArrayList<>());
        List<VarDecl> vds = parseVarDecls(new ArrayList<>());
//        List<FunDecl> fds = new ArrayList<>();
//        parseFunDecls();
        List<FunDecl> fds = parseFunDecls(new ArrayList<>());
        expect(TokenClass.EOF);
        return new Program(stds, vds, fds);
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

    private List<StructTypeDecl> parseStructDecls(List<StructTypeDecl> stds) {
        if (accept(TokenClass.STRUCT) && lookAheadAccept(2, TokenClass.LBRA)){
            nextToken(); // consume the struct token
            String struIdent = null;
            if (accept(TokenClass.IDENTIFIER)){
                struIdent = token.data;
                nextToken();
            }else {
                error(TokenClass.IDENTIFIER);
                return null;
            }
            //expect(TokenClass.IDENTIFIER);
            expect(TokenClass.LBRA);
            List<VarDecl> vds = parseVarDecls_pos(new ArrayList<>()); // one or more field
            expect(TokenClass.RBRA);
            expect(TokenClass.SC);
            stds.add(new StructTypeDecl(new StructType(struIdent), vds));
            parseStructDecls(stds);
        }

        return stds;
    }

    private Type parseType(){
        Type innerType = null;
        if (accept(TokenClass.STRUCT)){
            nextToken();
            if (accept(TokenClass.IDENTIFIER)){
                innerType = new StructType(token.data);
                nextToken();
            }else {
                error(TokenClass.IDENTIFIER);
                return null;
            }
            //expect(TokenClass.IDENTIFIER); // name of struct type
        }else if (accept(TokenClass.INT, TokenClass.CHAR, TokenClass.VOID)){
            innerType = BaseType.fromTokenClass(token.tokenClass);
            nextToken();
        }else{
            // raise an error if not match type keyword
            error(typeList);
            return null;
        }
        // return pointer type
        if (accept(TokenClass.ASTERIX)){
            nextToken();
            return new PointerType(innerType);
        }
        return innerType;
    }

    private List<VarDecl> parseVarDecls_pos(List<VarDecl> vds){
        if (accept(typeList)){
            return parseVarDecls(vds);
        }else {
            // if not match type decl, raise an error
            error(typeList);
            return null;
        }
    }

    private List<VarDecl> parseVarDecls(List<VarDecl> vds) {
        // TODO: bug here: int x; print(); <- will be recognized as funcall since '(' appear in lookahead 4
        // int a()
        // int* a()
        // struct aa bb()
        // struct aa* bb()


        int offset = 0;
        if (accept(typeList)){
            // peek 1 for check '('
            offset = 1;
            // if struct, skip its name ident
            if (accept(TokenClass.STRUCT)){
                offset += 1;
            }
            // if pointer, skip the asterix
            if (lookAheadAccept(offset, TokenClass.ASTERIX)){
                offset += 1;
            }
            // skip the var/fun/stru ident
            offset += 1;
        }

        if ((offset != 0) && accept(typeList) && lookAheadAccept(offset, TokenClass.SC, TokenClass.LSBR)){
            Type type = parseType();
            String varName = null;
            if (accept(TokenClass.IDENTIFIER)){
                varName = token.data;
                nextToken();
            }else{
                error(TokenClass.IDENTIFIER);
                return null;
            }
            //expect(TokenClass.IDENTIFIER); // name of var
            if (accept(TokenClass.SC)){
                nextToken();
                vds.add(new VarDecl(type, varName));
                parseVarDecls(vds);
            }else if (accept(TokenClass.LSBR)){
                nextToken();
                String index = null;
                if (accept(TokenClass.INT_LITERAL)){
                    index = token.data;
                    nextToken();
                }else {
                    error(TokenClass.INT_LITERAL);
                    return null;
                }
                //expect(TokenClass.INT_LITERAL);
                expect(TokenClass.RSBR);
                expect(TokenClass.SC);
                type = new ArrayType(type, Integer.parseInt(index));
                vds.add(new VarDecl(type, varName));
                parseVarDecls(vds);
            }else {
                error(TokenClass.SC, TokenClass.LSBR);
                return null;
            }
        }
        return vds;
    }

    private List<FunDecl> parseFunDecls(List<FunDecl> fds) {
        if (accept(typeList)){
            Type type = null;
            String ident = null;
            Block block = null;
            type = parseType();
            if (accept(TokenClass.IDENTIFIER)){
                ident = token.data;
                nextToken();
            }else {
                error(TokenClass.IDENTIFIER);
                return null;
            }
            //expect(TokenClass.IDENTIFIER);
            expect(TokenClass.LPAR);
            List<VarDecl> params = parseParams(new ArrayList<>());
            expect(TokenClass.RPAR);
            block = parseBlock();
            fds.add(new FunDecl(type, ident, params, block));
            parseFunDecls(fds);
        }
        return fds;
    }
    // possible param bugs here
    private List<VarDecl> parseParams(List<VarDecl> params){
        if (accept(typeList)){
            Type type = parseType();
            String varName = null;
            if (accept(TokenClass.IDENTIFIER)){
                varName = token.data;
                nextToken();
            }else {
                error(TokenClass.IDENTIFIER);
                return null;
            }
            params.add(new VarDecl(type, varName));
            //expect(TokenClass.IDENTIFIER);
            params = parseParams_kle(params);
        }
        return params;
    }

    private List<VarDecl> parseParams_kle(List<VarDecl> params){
        if (accept(TokenClass.COMMA)){
            nextToken();
            if (accept(typeList)){
                Type type = parseType();
                String varName = null;
                if (accept(TokenClass.IDENTIFIER)){
                    varName = token.data;
                    nextToken();
                }else {
                    error(TokenClass.IDENTIFIER);
                    return null;
                }
                params.add(new VarDecl(type, varName));
                //expect(TokenClass.IDENTIFIER);
            }else {
                error(typeList);
            }
            parseParams_kle(params);
        }
        return params;
    }

    private Block parseBlock(){
        expect(TokenClass.LBRA);
        List<VarDecl> vds = parseVarDecls(new ArrayList<>());
        List<Stmt> stmts = parseStmts_kle(new ArrayList<>());
        expect(TokenClass.RBRA);
        return new Block(vds, stmts);
    }

    private List<Stmt> parseStmts_kle(List<Stmt> stmts){
        if (accept(stmtFirst)){
            Stmt s = parseStmts();
            stmts.add(s);
            parseStmts_kle(stmts);
        }
        return stmts;
    }
    // actually parse SINGLE stmt
    private Stmt parseStmts(){
        if (accept(TokenClass.LBRA)){
            Block b = parseBlock();
            return b;
        }else if (accept(TokenClass.WHILE)){
            nextToken();
            expect(TokenClass.LPAR);
            Expr whileExp = parseExp();
            expect(TokenClass.RPAR);
            Stmt whileStmt = parseStmts();// belong to while
            return new While(whileExp, whileStmt);
        }else if (accept(TokenClass.IF)){
            nextToken();
            expect(TokenClass.LPAR);
            Expr ifExp = parseExp();
            expect(TokenClass.RPAR);
            Stmt ifStmt = parseStmts();// belong to if
            Stmt elseStmt = parseStmt_else();
            return new If(ifExp, ifStmt, elseStmt);
        }else if (accept(TokenClass.RETURN)){
            nextToken();
            Expr returnExpr = null;
            returnExpr = parseExp_opt();
            expect(TokenClass.SC);
            return new Return(returnExpr);
        }else if (accept(expFirst)){
            Expr lhs = parseExp();
            Expr rhs = null;
            if (accept(TokenClass.ASSIGN)){
                nextToken();
                rhs = parseExp();
                expect(TokenClass.SC);
                return new Assign(lhs, rhs);
            }else if (accept(TokenClass.SC)){
                nextToken();
                return new ExprStmt(lhs);
            }else {
                error(TokenClass.SC, TokenClass.ASSIGN);
                return null;
            }
        }else{
            error(stmtFirst);
            return null;
        }
    }

    private Stmt parseStmt_else(){
        if (accept(TokenClass.ELSE)){
            nextToken();
            return parseStmts();
        }
        return null;
    }

    private Expr parseExp_opt(){
        if (accept(expFirst)){
            return parseExp();
        }
        return null;
    }

    private Expr parseExp(){
        if (accept(expFirst)){
            return parseExp_or();
        }else {
            error(expFirst);
            return null;
        }
    }
    // how does first method know the operator in second method???
    private Expr parseExp_or(){
        if (accept(expFirst)){
            Expr lhs = parseExp_and();
            lhs = parseExp_or_term(lhs);
            return lhs;
        }else {
            error(expFirst);
            return null;
        }

    }

    private Expr parseExp_or_term(Expr lhs){
        if (accept(TokenClass.OR)){
            nextToken();
            Expr rhs = parseExp_and();
            lhs = new BinOp(lhs, Op.OR, rhs);
            parseExp_or_term(lhs);
        }
        return lhs;
    }

    private Expr parseExp_and(){
        if (accept(expFirst)){
            Expr lhs = parseExp_eq();
            lhs = parseExp_and_term(lhs);
            return lhs;
        }else {
            error(expFirst);
            return null;
        }
    }

    private Expr parseExp_and_term(Expr lhs){
        if (accept(TokenClass.AND)){
            nextToken();
            Expr rhs = parseExp_eq();
            lhs = new BinOp(lhs, Op.AND, rhs);
            parseExp_and_term(lhs);
        }
        return lhs;
    }
    // how does first method know the operator in second method???
    private Expr parseExp_eq(){
        if (accept(expFirst)){
            Expr lhs = parseExp_comp();
            lhs = parseExp_eq_term(lhs);
            return lhs;
        }else {
            error(expFirst);
            return null;
        }
    }

    private Expr parseExp_eq_term(Expr lhs){
        if (accept(TokenClass.EQ, TokenClass.NE)){
            Op op = Op.fromTokenClass(token.tokenClass);
            nextToken();
            Expr rhs = parseExp_comp();
            lhs = new BinOp(lhs, op, rhs);
            parseExp_eq_term(lhs);
        }
        return lhs;
    }

    private Expr parseExp_comp(){
        if (accept(expFirst)){
            Expr lhs = parseExp_add();
            lhs = parseExp_comp_term(lhs);
            return lhs;
        }else {
            error(expFirst);
            return null;
        }
    }

    private Expr parseExp_comp_term(Expr lhs){
        if (accept(TokenClass.LT, TokenClass.GT, TokenClass.LE, TokenClass.GE)){
            Op op = Op.fromTokenClass(token.tokenClass);
            nextToken();
            Expr rhs = parseExp_add();
            lhs = new BinOp(lhs, op, rhs);
            parseExp_comp_term(lhs);
        }
        return lhs;
    }

    private Expr parseExp_add(){
        if (accept(expFirst)){
            Expr lhs = parseExp_mult();
            lhs = parseExp_add_term(lhs);
            return lhs;
        }else {
            error(expFirst);
            return null;
        }
    }

    private Expr parseExp_add_term(Expr lhs){
        if (accept(TokenClass.PLUS, TokenClass.MINUS)){
            Op op = Op.fromTokenClass(token.tokenClass);
            nextToken();
            Expr rhs = parseExp_mult();
            lhs = new BinOp(lhs, op, rhs);
            parseExp_add_term(lhs);
        }
        return lhs;
    }

    private Expr parseExp_mult(){
        if (accept(expFirst)){
            Expr lhs = parseExp_lv2();
            lhs = parseExp_mult_term(lhs);
            return lhs;
        }else {
            error(expFirst);
            return null;
        }

    }

    private Expr parseExp_mult_term(Expr lhs){
        if (accept(TokenClass.ASTERIX, TokenClass.DIV, TokenClass.REM)){
            Op op = Op.fromTokenClass(token.tokenClass);
            nextToken();
            Expr rhs = parseExp_lv2();
            lhs = new BinOp(lhs, op, rhs);
            parseExp_mult_term(lhs);
        }
        return lhs;
    }

    private Expr parseExp_lv2(){
        if (accept(TokenClass.SIZEOF)){
            nextToken();
            expect(TokenClass.LPAR);
            Type t = parseType();
            expect(TokenClass.RPAR);
            return new SizeOfExpr(t);
        }else if (accept(TokenClass.ASTERIX)){// dereference(valueAt)
            nextToken();
            Expr valueAt = parseExp_lv2();
            return new ValueAtExpr(valueAt);
        }else if (accept(TokenClass.LPAR) && lookAheadAccept(1, typeList)){// type cast
            nextToken();
            Type target = parseType();
            expect(TokenClass.RPAR);
            Expr toCast = parseExp_lv2();
            return new TypecastExpr(target, toCast);
        }else if (accept(TokenClass.MINUS)){
            nextToken();
            Expr e = parseExp_lv2();
            return new BinOp(new IntLiteral(0), Op.SUB, e);
        }else if (accept(expBottomFirst)){
            return parseExp_lv1();
        }else {
            error(expFirst);
            return null;
        }
    }

    private Expr parseExp_lv1(){
        if (accept(expBottomFirst)){
            Expr lhs = parseExp_bottom();
            lhs = parseExp_lv1_term(lhs);
            return lhs;
        }else {
            error(expBottomFirst);
            return null;
        }
    }

    private Expr parseExp_bottom(){
        if (accept(expBottomFirst)){
            if (accept(TokenClass.LPAR)){
                nextToken();
                Expr e = parseExp();
                expect(TokenClass.RPAR);
                return e;
            }else if (accept(TokenClass.INT_LITERAL, TokenClass.CHAR_LITERAL, TokenClass.STRING_LITERAL)){
                Expr lit = null;
                if (token.tokenClass == TokenClass.INT_LITERAL){
                    lit = new IntLiteral(Integer.parseInt(token.data));
                }else if (token.tokenClass == TokenClass.CHAR_LITERAL){
                    lit = new ChrLiteral(token.data.charAt(0));
                }else {
                    lit = new StrLiteral(token.data);
                }
                nextToken();
                return lit;
            }else if (accept(TokenClass.IDENTIFIER) && lookAheadAccept(1, TokenClass.LPAR)){
                // fn_call
                return parseFn_call();
            }else if (accept(TokenClass.IDENTIFIER)){
                Expr ident = new VarExpr(token.data);
                nextToken();
                return ident;
            }else {
                error(TokenClass.LPAR, TokenClass.INT_LITERAL, TokenClass.CHAR_LITERAL, TokenClass.STRING_LITERAL, TokenClass.IDENTIFIER);
                return null;
            }
        }else {
            error(expBottomFirst);
            return null;
        }
    }

    private Expr parseExp_lv1_term(Expr lhs){
        if (accept(TokenClass.DOT)){// field access
            nextToken();
            String ident = null;
            if (accept(TokenClass.IDENTIFIER)){
                ident = token.data;
                nextToken();
            }else {
                error(TokenClass.IDENTIFIER);
                return null;
            }
            // TODO: add test for nested field access - stru1.stru2.x...
            lhs = new FieldAccessExpr(lhs, ident);
            //expect(TokenClass.IDENTIFIER);
            parseExp_lv1_term(lhs);
        }else if (accept(TokenClass.LSBR)){// array access
            nextToken();
            Expr index = parseExp();
            expect(TokenClass.RSBR);
            lhs = new ArrayAccessExpr(lhs, index);
            parseExp_lv1_term(lhs);
        }
        return lhs;
    }

    private Expr parseFn_call(){
        String name = null;
        if (accept(TokenClass.IDENTIFIER)){
            name = token.data;
            nextToken();
        }else {
            error(TokenClass.IDENTIFIER);
            return null;
        }
        //expect(TokenClass.IDENTIFIER);
        expect(TokenClass.LPAR);
        List<Expr> exprs = parseArgs_opt(new ArrayList<>());
        expect(TokenClass.RPAR);
        return new FunCallExpr(name, exprs);
    }

    private List<Expr> parseArgs_opt(List<Expr> exprs){
        if (accept(expFirst)){
            Expr e = parseExp();
            exprs.add(e);
            exprs = parseArgs_kle(exprs);
            return exprs;
        }
        return exprs;
    }

    private List<Expr> parseArgs_kle(List<Expr> exprs){
        if (accept(TokenClass.COMMA)){
            nextToken();
            Expr e = parseExp();
            exprs.add(e);
            parseArgs_kle(exprs);
            return exprs;
        }
        return exprs;
    }
}
