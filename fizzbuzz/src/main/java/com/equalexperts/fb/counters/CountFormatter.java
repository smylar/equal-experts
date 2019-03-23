package com.equalexperts.fb.counters;

import java.util.List;

/**
 * Format count output.
 * 
 * @author a.nonymous
 *
 */
@FunctionalInterface
public interface CountFormatter {
	
	/**
	 * Format a count
	 * 
	 * @param count The counter to format
	 * @param keysOfInterest Key to output in the order they are required
	 * @return
	 */
	public String format(Counter count, List<String> keysOfInterest);
	
	
}
