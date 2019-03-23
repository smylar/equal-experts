package com.equalexperts.fb.counters;

import java.util.List;

/**
 * Formatter that adds a colon and space between the term and count
 * 
 * @author a.nonymous
 *
 */
public class ColonSpaceCountFormatter implements CountFormatter {

    @Override
    public String format(Counter count, List<String> keysOfInterest) {
        var counts = count.getCount();
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
