package com.equalexperts.fb.counters;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Test for things that count things
 * 
 * @author a.nonymous
 *
 */
public class CounterTests {
	
	@Test
	public void testCounter() {
		Map<String,Long> counts = new TermAndIntegerCounter().count("moo 10 cow moo 1 moocow 1.2");
		assertEquals(2L, counts.getOrDefault("moo", 0L), 0);
		assertEquals(1L, counts.getOrDefault("cow", 0L), 0);
		assertEquals(2L, counts.getOrDefault(TermAndIntegerCounter.INTEGER, 0L), 0); //should currently ignore the float value
		assertEquals(0L, counts.getOrDefault("duck", 0L), 0);
	}
	
	@Test
	public void testCounterFormatting() {
		String output = CountFormatter.TERM_AND_INTEGER.formatCount("moo 10 cow moo 1 moocow 1.2", List.of("moo", "cow", "integer", "duck"));
		assertEquals("moo: 2 cow: 1 integer: 2 duck: 0", output);
	}
}
