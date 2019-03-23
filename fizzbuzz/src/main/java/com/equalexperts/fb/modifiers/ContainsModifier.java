package com.equalexperts.fb.modifiers;

/**
 * Modifier applied when the input contains a given number
 * 
 * @author a.nonymous
 *
 */
public class ContainsModifier extends AbstractPrioritisedModifier<Integer, String> {

    private final String contains;
    
    public ContainsModifier(String contains, String output) {
        this(contains, output, 0);
    }
    
    public ContainsModifier(String contains, String output, int priority) {
        super(output, priority);
        this.contains = contains;
    }
    
    @Override
    public boolean isApplicable(Integer input) {
        return input.toString().contains(contains);
    }

}
