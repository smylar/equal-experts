package com.equalexperts.fb.counters;

import java.util.Map;

@FunctionalInterface
public interface Counter {
	public Map<String,Long> count(String input);
}
