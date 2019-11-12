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
        String label = String.format("_str%d", counter);
        if (labels.contains(label)) {
            throw new RuntimeException("Gen Error: duplicate label: " + label);
        }else {
            labels.add(label);
            counter++;
            return label;
        }
    }

    public String addNumLabel(String prefix){
        String label = String.format("_%s%d", prefix, counter);
        if (labels.contains(label)) {
            throw new RuntimeException("Gen Error: duplicate label: " + label);
        }else {
            labels.add(label);
            counter++;
            return label;
        }
    }

    public String addNumLabel(String prefix, String postfix){
        String label = String.format("_%s%d_%s", prefix, counter, postfix);
        if (labels.contains(label)) {
            throw new RuntimeException("Gen Error: duplicate label: " + label);
        }else {
            labels.add(label);
            counter++;
            return label;
        }
    }

    public void verifyLabel(String label) {
        if (!labels.contains(label)) {
            throw new RuntimeException("Gen Error : using undefined label: " + label);
        }
    }

}
