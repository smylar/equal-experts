package com.equalexperts.fb.service;

import java.util.List;
import java.util.Optional;

import com.equalexperts.fb.collectors.TermCollector;
import com.equalexperts.fb.counters.CountFormatter;
import com.equalexperts.fb.counters.Counter;

/**
 * Service to process a string with modifications and counts
 * 
 * @author a.nonymous
 *
 */
public final class ProcessService {
	
	private final ModificationService modService;
	private final CountFormatter countFormatter;
	private final Counter count;
	
	public ProcessService(ModificationService modService, Counter count, CountFormatter countFormatter) {
		this.countFormatter = countFormatter;
		this.modService = modService;
		this.count = count;
	}
	
	/**
	 * 
	 * @param start Start of the number range to process (inclusive)
	 * @param end End of the number range to process (inclusive)
	 * @param keysOfInterest Keys to include in count summary (in this order)
	 * @return The processed string
	 */
	public String process(final int start, final int end, List<String> keysOfInterest) {
		
		String modified = modService.modifyRange(start, end)
								    .doOnError(e -> System.out.println("Do some error handling"))
								    .collectInto(new TermCollector(Optional.of(count)), TermCollector::add)
								    .blockingGet()
								    .getResult();
		
		return modified + " " + countFormatter.format(count, keysOfInterest);
	}
}
