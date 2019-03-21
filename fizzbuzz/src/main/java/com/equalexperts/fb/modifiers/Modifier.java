package com.equalexperts.fb.modifiers;

import java.util.Optional;

/**
 * Interface defining a modifier, which will optionally convert an input to a different output
 * 
 * @author a.nonymous
 *
 * @param <T> Type of the input
 * @param <R> Type of the output
 */
public interface Modifier<T, R> {

	/**
	 * Test this modifier is applicable
	 * 
	 * @param input
	 * @return
	 */
	public boolean isApplicable(T input);
	
	/**
	 * Return modification if this modifier applies
	 * 
	 * @param input
	 * @return Optional containing new value, or empty optional if it does not apply
	 */
	public Optional<R> modify(T input); 
	
	/**
	 * Priority defines which modifier gets tried first if more than 1 may be applied
	 * 
	 * @return Number indicating priority, lower number is higher priority
	 */
	public int getPriority();
}
