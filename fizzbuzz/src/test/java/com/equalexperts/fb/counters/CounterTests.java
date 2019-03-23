package com.equalexperts.fb.counters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
        
        Counter counter = new TermAndIntegerCounter();
        
        Stream.of("moo", "10", "cow", "moo", "1", "moocow", "1.2")
              .forEach(counter::add);
        
        Map<String,Long> counts = counter.getCount();
        assertEquals(2L, counts.getOrDefault("moo", 0L), 0);
        assertEquals(1L, counts.getOrDefault("cow", 0L), 0);
        assertEquals(2L, counts.getOrDefault(TermAndIntegerCounter.INTEGER, 0L), 0); //should currently ignore the float value
        assertEquals(0L, counts.getOrDefault("duck", 0L), 0);
    }
    
    @Test
    public void testCounterFormatting() {
        Counter counter = mock(Counter.class);
        when(counter.getCount()).thenReturn(Map.of("moo", 2L, "integer", 2L, "cow", 1L));
        
        String output = new ColonSpaceCountFormatter().format(counter, List.of("moo", "cow", "integer", "duck"));
        assertEquals("moo: 2 cow: 1 integer: 2 duck: 0", output);
    }
}
