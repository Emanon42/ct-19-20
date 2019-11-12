package gen;


import java.io.PrintWriter;

public class AsmWritter {
    private PrintWriter writer;
    private LabelManager labeller;
    private String currentLabel;

    public AsmWritter(PrintWriter writer, LabelManager lm) {
        this.writer = writer;
        this.labeller = lm;
        this.currentLabel = "";
    }

    public AsmWritter withLabel(String l){
        if (!this.currentLabel.isEmpty()) {
            throw new RuntimeException("has set a label");
        }
        this.currentLabel = l;
        return this;
    }

    public void write(String s){
        if (!currentLabel.isEmpty()){
            currentLabel += ":    ";
        }
        writer.print(currentLabel + s);
        writer.print("\n");
        writer.flush();
        currentLabel = "";
    }

    // newline
    public void newline(){
        write("");
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

    // syscall
    public void syscall() {
        write("syscall");
    }

    // load immediate: li $register, 1
    public void li(Register r, int i) {
        write(String.format("li %s, %d", r, i));
    }

    // load address from label: la $register, some_global
    public void la(Register r, String label) {
        labeller.verifyLabel(label);
        write(String.format("la %s, %s", r, label));
    }

    // add
    public void add(Register value, Register x, Register y) {
        write(String.format("add %s, %s, %s", value, x, y));
    }

    // addi
    public void add(Register value, Register x, int y) {
        write(String.format("addi %s, %s, %d", value, x, y));
    }

    // sub: value = x - y
    public void sub(Register value, Register x, Register y) {
        write(String.format("sub %s, %s, %s", value, x, y));
    }

    // subi: value = $x - i
    public void sub(Register value, Register x, int i) {
        write(String.format("subi %s, %s, %d", value, x, i));
    }

    // seq: value = x == y
    public void seq(Register value, Register x, Register y) {
        write(String.format("seq %s, %s, %s", value, x, y));
    }

    // sne: value = x != y
    public void sne(Register value, Register x, Register y) {
        write(String.format("sne %s, %s, %s", value, x, y));
    }

    // mul: value = x * y (with some weird HI LO behaviour lol)
    public void mul(Register value, Register x, Register y) {
        write(String.format("mul %s, %s, %s", value, x, y));
    }

    // mul: value = x * y (with some weird HI LO behaviour lol)
    public void mul(Register value, Register x, int y) {
        write(String.format("mul %s, %s, %d", value, x, y));
    }

    // load byte: lb $target, $offset($from)
    public void lb(Register target, Register from, int offset) {
        write(String.format("lb %s, %d(%s)", target, offset, from));
    }

    // load word: lw $target, $offset($from)
    public void lw(Register target, Register from, int offset) {
        write(String.format("lw %s, %d(%s)", target, offset, from));
    }

    // move: move $target, $from
    public void move(Register target, Register from) {
        write(String.format("move %s, %s", target, from));
    }

    // store word: sw $from, offset($target)
    public void sw(Register from, Register target, int offset) {
        write(String.format("sw %s %d(%s)", from, offset, target));
    }

    // store byte: sw $from, offset($target)
    public void sb(Register from, Register target, int offset) {
        write(String.format("sb %s %d(%s)", from, offset, target));
    }

    // divide: div $number, $dividedBy (lo = quotient, hi = remainder)
    public void div(Register number, Register dividedBy) {
        write(String.format("div %s %s", number, dividedBy));
    }

    // move from hi to target: mfhi $target
    public void mfhi(Register target) {
        write(String.format("mfhi %s", target));
    }

    // move from lo to target: mflo $target
    public void mflo(Register target) {
        write(String.format("mflo %s", target));
    }

    // branch if equal zero: beq $x, $zero, label
    public void beqz(Register value, String label) {
        labeller.verifyLabel(label);
        write(String.format("beq %s, $zero, %s", value, label));
    }

    // branch if not equal zero: bnez $x, label
    public void bnez(Register value, String label) {
        labeller.verifyLabel(label);
        write(String.format("bnez %s, %s", value, label));
    }

    // branch if greater than zero: bgtz $x, label
    public void bgtz(Register value, String label) {
        labeller.verifyLabel(label);
        write(String.format("bgtz %s, %s", value, label));
    }

    // set if less than
    public void slt(Register result, Register x, Register y){
        write(String.format("slt %s, %s, %s", result, x, y));
    }

    // set if greater than
    public void sgt(Register result, Register x, Register y){
        write(String.format("sgt %s, %s, %s", result, x, y));
    }

    // set if less equal
    public void sle(Register result, Register x, Register y){
        write(String.format("sle %s, %s, %s", result, x, y));
    }

    // set if grater equal
    public void sge(Register result, Register x, Register y){
        write(String.format("sge %s, %s, %s", result, x, y));
    }

    // jump to label: j label
    public void j(String label) {
        labeller.verifyLabel(label);
        write(String.format("j %s", label));
    }

    // branch to label: b label
    public void b(String label) {
        labeller.verifyLabel(label);
        write(String.format("b %s", label));
    }

    // jump register unconditionally: jr $target
    public void jr(Register register) {
        write(String.format("jr %s", register));
    }

    // jump and link : jal label
    public void jal(String genLabel) {
        write(String.format("jal %s", genLabel));
    }

    // nop: no operation
    public void nop() {
        write("nop");
    }

}
