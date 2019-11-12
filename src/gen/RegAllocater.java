package gen;

import java.util.EmptyStackException;
import java.util.Stack;

public class RegAllocater {

    // maintain a stack for free regs
    private Stack<Register> availableRegs = new Stack<Register>();

    public RegAllocater() {
        availableRegs.addAll(Register.tmp);
    }


    public Register get() {
        try {
            return availableRegs.pop();
        } catch (EmptyStackException ese) {
            System.out.println("run out of regs");
            throw ese;
        }
    }

    public void free(Register reg) {
        availableRegs.push(reg);
    }

}
