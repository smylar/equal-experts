package com.equalexperts.fb.service;

import java.util.List;

import com.equalexperts.fb.counters.CountFormatter;

/**
 * Service to process a string with modifications and counts
 * 
 * @author a.nonymous
 *
 */
public final class ProcessService {
	
	private final ModificationService modService;
	private final CountFormatter countFormatter;
	
	public ProcessService(ModificationService modService, CountFormatter countFormatter) {
		this.countFormatter = countFormatter;
		this.modService = modService;
	}
	
	/**
	 * 
	 * @param start Start of the number range to process (inclusive)
	 * @param end End of the number range to process (inclusive)
	 * @param keysOfInterest Keys to include in count summary (in this order)
	 * @return The processed string
	 */
	public String process(int start, int end, List<String> keysOfInterest) {
		String modified = modService.modifyRange(start, end);
		String counts = countFormatter.formatCount(modified, keysOfInterest);
		return modified + " " + counts;
	}
}
