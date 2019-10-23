package sem;

import ast.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SemanticAnalyzer {
	
	public int analyze(ast.Program prog) {
		// List of visitors
		ArrayList<SemanticVisitor> visitors = new ArrayList<SemanticVisitor>() {{
			add(new NameAnalysisVisitor());
			add(new TypeCheckVisitor());
		}};
		// Error accumulator
		int errors = 0;

		/*
			void print_s(char* s);
			void print_i(int i);
			void print_c(char c);
			char read_c();
			int read_i();
			void* mcmalloc(int size);

		 */
		List<FunDecl> funDecls = new LinkedList<>(Arrays.asList(
				new FunDecl(BaseType.VOID, "print_s", new PointerType(BaseType.CHAR), "s"),
				new FunDecl(BaseType.VOID, "print_i", BaseType.INT, "i"),
				new FunDecl(BaseType.VOID, "print_c", BaseType.CHAR, "c"),
				new FunDecl(BaseType.CHAR, "read_c"),
				new FunDecl(BaseType.INT, "read_i"),
				new FunDecl(new PointerType(BaseType.VOID), "mcmalloc", BaseType.INT, "size")
		));

		funDecls.addAll(prog.funDecls);
		prog.funDecls = funDecls;
		
		// Apply each visitor to the AST
		for (SemanticVisitor v : visitors) {
			// another solution: stop when error happen
			if (errors > 0){
				break;
			}
			prog.accept(v);
			errors += v.getErrorCount();
		}
		
		// Return the number of errors.
		return errors;
	}
}
