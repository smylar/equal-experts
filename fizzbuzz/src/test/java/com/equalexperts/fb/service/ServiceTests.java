package com.equalexperts.fb.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import com.equalexperts.fb.counters.ColonSpaceCountFormatter;
import com.equalexperts.fb.counters.TermAndIntegerCounter;
import com.equalexperts.fb.modifiers.ContainsModifier;
import com.equalexperts.fb.modifiers.Modifier;
import com.equalexperts.fb.modifiers.MultipleModifier;

public class ServiceTests {

	@Test
	public void testModificationRangeWithSingleModifier() {
		@SuppressWarnings("unchecked")
		Modifier<Integer,String> modifier = (Modifier<Integer,String>)mock(Modifier.class);
		
		when(modifier.modify(anyInt())).thenReturn(Optional.empty());
		when(modifier.isApplicable(anyInt())).thenReturn(false);
		when(modifier.modify(2)).thenReturn(Optional.of("moo"));
		when(modifier.isApplicable(2)).thenReturn(true);
		
		ModificationService modService = new ModificationServiceImpl(Set.of(modifier));
		assertEquals("1 moo 3 4 5", asString(modService, 1, 5));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testModificationPriority() {
		
		Modifier<Integer,String> modifier = (Modifier<Integer,String>)mock(Modifier.class);
		Modifier<Integer,String> modifierlower = (Modifier<Integer,String>)mock(Modifier.class);
		

		when(modifierlower.modify(1)).thenReturn(Optional.of("moo"));
		when(modifierlower.isApplicable(1)).thenReturn(true);
		when(modifierlower.getPriority()).thenReturn(2);
		when(modifier.modify(1)).thenReturn(Optional.of("cow"));
		when(modifier.isApplicable(1)).thenReturn(true);
		when(modifier.getPriority()).thenReturn(1);
		
		ModificationService modService = new ModificationServiceImpl(Set.of(modifierlower, modifier));
		assertEquals("cow", asString(modService, 1, 1));
	}
	
	/*
	 * The test in the instructions
	 */
	@Test
	public void testActualStep1() {
		Set<Modifier<Integer,String>> modifiers = new HashSet<>();
		modifiers.add(new MultipleModifier(3, "fizz", 3));
		modifiers.add(new MultipleModifier(5, "buzz", 2));
		// I do realise you could concatenate the 3 and 5 modifiers for the 15 modifier
		//but I believe having a distinct modifier is simpler and more flexible
		modifiers.add(new MultipleModifier(15, "fizzbuzz", 1));
		ModificationService modService = new ModificationServiceImpl(modifiers);
		assertEquals("1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz", asString(modService, 1, 20));
	}
	
	@Test
	public void testActualStep2() {
		Set<Modifier<Integer,String>> modifiers = new HashSet<>();
		modifiers.add(new MultipleModifier(3, "fizz", 3));
		modifiers.add(new MultipleModifier(5, "buzz", 2));
		modifiers.add(new MultipleModifier(15, "fizzbuzz", 1));
		modifiers.add(new ContainsModifier("3", "lucky"));
		ModificationService modService = new ModificationServiceImpl(modifiers);
		assertEquals("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz", asString(modService, 1, 20));
	}
	
	@Test
	public void testActualStep3() {
		Set<Modifier<Integer,String>> modifiers = new HashSet<>();
		modifiers.add(new MultipleModifier(3, "fizz", 3));
		modifiers.add(new MultipleModifier(5, "buzz", 2));
		modifiers.add(new MultipleModifier(15, "fizzbuzz", 1));
		modifiers.add(new ContainsModifier("3", "lucky"));
		ProcessService processor = new ProcessService(new ModificationServiceImpl(modifiers), 
													  new TermAndIntegerCounter(),
													  new ColonSpaceCountFormatter());
		assertEquals("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz fizz: 4 buzz: 3 fizzbuzz: 1 lucky: 2 integer: 10", 
					processor.process(1, 20, List.of("fizz", "buzz", "fizzbuzz", "lucky", "integer")));
	}
	
	private String asString (ModificationService modService, int start, int end) {
		List<String> result = modService.modifyRange(start, end)
				         				.collect(ArrayList<String>::new, List::add)
				         				.blockingGet();
		
		return String.join(" ", result);
		
	}
}
