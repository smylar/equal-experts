package com.equalexperts.fb.collectors;

public interface Collector<T,R> {
	public void add(T item);
	
	public R getResult();
}
