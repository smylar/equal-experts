package com.equalexperts.fb.collectors;

import java.util.Optional;

import com.equalexperts.fb.counters.Counter;

/**
 * Collect the modified terms into a final result
 * 
 * @author a.nonymous
 *
 */
public class TermCollector implements Collector<String,String> {
    
    private final Optional<Counter> count;
    private final StringBuffer output;
    
    public TermCollector(Optional<Counter> count) {
        this.count = count;
        this.output = new StringBuffer();
    }

    @Override
    public void add(String item) {
        output.append(" ");
        output.append(item);
        count.ifPresent(c -> c.add(item));
    }

    @Override
    public String getResult() {
        return output.toString().stripLeading();
    }
    
}
