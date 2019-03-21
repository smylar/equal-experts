package com.equalexperts.fb.counters;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Count the terms within a string, converting integers to the word integer
 * 
 * @author a.nonymous
 *
 */
public final class TermAndIntegerCounter implements Counter {
	public static final String INTEGER = "integer";
	
	/**
	 * Return a map of each term in the input string with the number of times they occur
	 * 
	 * @param input
	 * @return
	 */
	public Map<String,Long> count(final String input) {
		return Stream.of(input.split("\\s+"))
					 .map(this::convertInteger)
					 .collect(Collectors.groupingBy(term -> term, Collectors.counting()));
	}
	
	private String convertInteger(final String term) {
		return term.matches("^\\d+$") ? INTEGER
									  : term;

	}
}
