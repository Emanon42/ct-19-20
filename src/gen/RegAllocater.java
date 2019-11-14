package gen;

import java.util.EmptyStackException;
import java.util.Stack;

import static gen.Register.unfreeable;

public class RegAllocater {

    // maintain a stack for free regs
    public Stack<Register> availableRegs = new Stack<Register>();

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
        for (Register r : unfreeable) {
            if (r == reg) {
                throw new RuntimeException("Attempted to free non-freeable register.");
            }
        }
        if (!availableRegs.contains(reg)){
            availableRegs.push(reg);
        }

    }

}
