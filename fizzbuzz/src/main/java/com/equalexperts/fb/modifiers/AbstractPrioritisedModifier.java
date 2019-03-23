package com.equalexperts.fb.modifiers;

import java.util.Optional;

/**
 * Abstract modifier that stores common parts, i.e. the output and the priority
 * 
 * @author a.nonymous
 *
 * @param <T>
 * @param <R>
 */
public abstract class AbstractPrioritisedModifier<T,R> implements Modifier<T,R> {
    private final R output;
    private final int priority;
    
    public AbstractPrioritisedModifier(R output, int priority) {
        this.output = output;
        this.priority = priority;
    }
    
    @Override
    public Optional<R> modify(T input) {
        return isApplicable(input) ? Optional.of(output)
                                      : Optional.empty();
    }

    @Override
    public int getPriority() {
        return priority;
    }
    
    @Override
    public abstract boolean isApplicable(T input);
}
