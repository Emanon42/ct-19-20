package gen;


import java.io.PrintWriter;

public class AsmWritter {
    private PrintWriter writer;
    private String label;

    public AsmWritter(PrintWriter writer) {
        this.writer = writer;
        this.label = "";
    }

    public AsmWritter withLabel(String l){
        if (!this.label.isEmpty()) {
            throw new RuntimeException("has set a label");
        }
        this.label = l;
        return this;
    }

    public void write(String s){
        if (!label.isEmpty()){
            label += ": \n    ";
        }
        writer.print(label + s);
        writer.print("\n");
        writer.flush();
        label = "";
    }

    // .space 4
    public void dataSpace(int space) {
        write(String.format(".space %d", space));
    }

    // .asciiz "cyka blyat" # (ending with null byte \0)
    public void dataAsciiNullTerminated(String s) {
        write(String.format(".asciiz \"%s\"", s));
    }

    // # blablabla
    public void comment(String s){
        write(String.format("# %s", s));
    }

    // .directive
    public void directive(String s) {
        write(String.format(".%s", s));
    }

}
