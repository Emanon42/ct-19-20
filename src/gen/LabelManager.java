package gen;

import java.util.HashSet;
import java.util.Set;

public class LabelManager {
    private Set<String> labels;
    private int counter;

    public LabelManager(){
        labels = new HashSet<>();
        counter = 0;
    }

    public String addLabel(String label){
        if (labels.contains(label)) {
            throw new RuntimeException("Gen Error: duplicate label: " + label);
        }else {
            labels.add(label);
            return label;
        }
    }

    public String addGlobalStringLabel(){
        String label = String.format("$str%d", counter);
        if (labels.contains(label)) {
            throw new RuntimeException("Gen Error: duplicate label: " + label);
        }else {
            labels.add(label);
            counter++;
            return label;
        }
    }

}
