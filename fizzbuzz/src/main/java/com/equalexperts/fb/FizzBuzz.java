package com.equalexperts.fb;

import java.util.List;
import java.util.Set;

import com.equalexperts.fb.counters.ColonSpaceCountFormatter;
import com.equalexperts.fb.counters.TermAndIntegerCounter;
import com.equalexperts.fb.modifiers.ContainsModifier;
import com.equalexperts.fb.modifiers.Modifier;
import com.equalexperts.fb.modifiers.MultipleModifier;
import com.equalexperts.fb.service.ModificationServiceImpl;
import com.equalexperts.fb.service.ProcessService;

public class FizzBuzz {

	public static void main(String[] args) {
		int start = 1;
		int end = 20;
		if (args.length == 2)
		try {
			start = Integer.parseInt(args[0]);
			end = Integer.parseInt(args[1]);
		} catch (Exception ex) {
			System.out.println("Program requires 2 integer arguments a start number and an end number");
			System.out.println("or, if no arguments provided then the default is 1 20");
			System.exit(1);
		}
		ProcessService processor = new ProcessService(new ModificationServiceImpl(loadModifiers()), 
													 new TermAndIntegerCounter(),
													 new ColonSpaceCountFormatter());
		
		System.out.println(processor.process(start, end, loadKeysOfInterest()));

	}
	
	private static Set<Modifier<Integer,String>> loadModifiers() {
		//these could probably come from a resource file
		return Set.of(new MultipleModifier(3, "fizz", 3),
					   new MultipleModifier(5, "buzz", 2),
					   new MultipleModifier(15, "fizzbuzz", 1),
					   new ContainsModifier("3", "lucky"));
	}
	
	private static List<String> loadKeysOfInterest() {
		return List.of("fizz", "buzz", "fizzbuzz", "lucky", "integer");
	}

}
