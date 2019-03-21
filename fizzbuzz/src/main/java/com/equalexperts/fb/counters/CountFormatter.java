package com.equalexperts.fb.counters;

import java.util.List;
import java.util.function.Supplier;

/**
 * Perform a count with the selected counter and format the output.
 * 
 * @author a.nonymous
 *
 */
public enum CountFormatter {
	TERM_AND_INTEGER(TermAndIntegerCounter::new);
	
	private Supplier<Counter> counter;
	
	private CountFormatter(Supplier<Counter> counter) {
		this.counter = counter;
	}
	
	/**
	 * Format a string containing counts;
	 * 
	 * @param input
	 * @param keysOfInterest Key to output in the order they are required
	 * @return
	 */
	public String formatCount(String input, List<String> keysOfInterest) {
		var counts = counter.get().count(input);
		StringBuffer output = new StringBuffer();
		keysOfInterest.forEach(k -> {
			output.append(k);
			output.append(": ");
			output.append(counts.getOrDefault(k, 0L).toString());
			output.append(" ");
		});
		return output.toString().trim();
	}
}
