package com.equalexperts.fb.counters;

import java.util.Map;

/**
 * A thing that counts things
 * 
 * @author a.nonymous
 *
 */
public interface Counter {
	
	/**
	 * Return a map of each term in the input string with the number of times they occur
	 * 
	 * @return
	 */
	public Map<String,Long> getCount();
	
	/**
	 * Count this item
	 * 
	 * @param item
	 */
	public void add(String item);
}
