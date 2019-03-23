package com.equalexperts.fb.counters;

import java.util.HashMap;
import java.util.Map;

/**
 * Count the terms within a string, converting integers to the word integer
 * 
 * @author a.nonymous
 *
 */
public final class TermAndIntegerCounter implements Counter {
    public static final String INTEGER = "integer";
    
    private final Map<String, Long> count = new HashMap<>();

    @Override
    public Map<String, Long> getCount() {
        return count;
    }

    @Override
    public void add(String item) {
        String term = convertInteger(item);
        count.compute(term, (k,v) -> v == null ? 1L : v+1);
    }
    
    private String convertInteger(final String term) {
        return term.matches("^\\d+$") ? INTEGER
                                      : term;

    }
}
