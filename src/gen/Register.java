package gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cdubach
 */
public class Register {


    /*
     * definition of registers
     */

    // basic regs

    public static final Register v0 = new Register(2,"v0");

    public static final List<Register> arg = new ArrayList<Register>();
    static {
        arg.add(new Register(4,"a0"));
        arg.add(new Register(5,"a1"));
        arg.add(new Register(6,"a2"));
        arg.add(new Register(7,"a3"));
    }

    public static final List<Register> tmp = new ArrayList<Register>();
    static {
        for (int i=8; i<=15; i++)
            tmp.add(new Register(i,"t"+(i-8)));
        for (int i=16; i<=23; i++)
            tmp.add(new Register(i,"s"+(i-16)));
        for (int i=24; i<=25; i++)
            tmp.add(new Register(i,"t"+(i-24+8)));
    }

    public static final Register gp = new Register(28,"gp");
    public static final Register sp = new Register(29,"sp");
    public static final Register fp = new Register(30,"fp");
    public static final Register ra = new Register(31,"ra");

    // different sets of basic regs
    public static final List<Register> unfreeable = new ArrayList<>();
    static {
        unfreeable.addAll(arg);
        unfreeable.add(v0);
        unfreeable.add(gp);
        unfreeable.add(sp);
        unfreeable.add(fp);
        unfreeable.add(ra);

    }



    public static final List<Register> toSnapshot = new ArrayList<>();
    static {
        // not include sp, fp and ra!!!
        // remember to handly snapshot them
        toSnapshot.addAll(tmp);
        toSnapshot.addAll(arg);
        toSnapshot.add(gp);
    }





    private final int num;      // register number
    private final String name;  // register name


    private Register(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public String toString() {
        return "$"+name;
    }

}
