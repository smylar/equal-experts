package com.equalexperts.fb.service;

public interface ModificationService {

	/**
	 * Apply modifiers to the given range
	 * 
	 * @param start Range start value (inclusive)
	 * @param end Range end value (inclusive)
	 * @return The generated ouput
	 */
	String modifyRange(int start, int end);

}