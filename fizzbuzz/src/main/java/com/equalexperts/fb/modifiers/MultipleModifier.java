package com.equalexperts.fb.modifiers;

/**
 * Modifier that returns a given string if the input is a given multiple
 * 
 * @author a.nonymous
 *
 */
public final class MultipleModifier extends AbstractPrioritisedModifier<Integer, String> {

    private final int multiple;
    
    
    public MultipleModifier(int multiple, String output) {
        this(multiple, output, 0);
    }
    
    public MultipleModifier(int multiple, String output, int priority) {
        super(output, priority);
        this.multiple = multiple;
    }

    @Override
    public boolean isApplicable(Integer input) {
        return input != 0 && input % multiple == 0;
    }

}
