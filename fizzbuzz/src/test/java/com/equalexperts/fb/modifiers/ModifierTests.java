package com.equalexperts.fb.modifiers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

/**
 * Test individual modifiers
 * 
 * @author a.nonymous
 *
 */
public class ModifierTests {

    @Test
    public void testIndividualMultipleModification() {
        Modifier<Integer,String> modifier = new MultipleModifier(3, "fizz", 1);
        
        Optional<String> output = modifier.modify(0);
        assertFalse(output.isPresent());
        
        output = modifier.modify(1);
        assertFalse(output.isPresent());
        
        output = modifier.modify(3);
        assertEquals("fizz", output.orElse(""));
        
        output = modifier.modify(-3);
        assertEquals("fizz", output.orElse(""));
    }
    
    @Test
    public void testIndividualContainsModification() {
        Modifier<Integer,String> modifier = new ContainsModifier("3", "lucky");
        
        Optional<String> output = modifier.modify(1);
        assertFalse(output.isPresent());
        
        output = modifier.modify(3);
        assertEquals("lucky", output.orElse(""));
        
        output = modifier.modify(30);
        assertEquals("lucky", output.orElse(""));
    }
}
