package com.equalexperts.fb.service;

import java.util.List;

import com.equalexperts.fb.modifiers.Modifier;

/**
 * Apply modifiers to a range of integer values;
 * 
 * @author a.nonymous
 *
 */
public final class ModificationServiceImpl implements ModificationService {
	
	private final List<Modifier<Integer, String>> modifiers;

	public ModificationServiceImpl(List<Modifier<Integer, String>> modifiers) {
		this.modifiers = modifiers;
	}

	/* (non-Javadoc)
	 * @see com.equalexperts.fb.service.ModificationService#modifyRange(int, int)
	 */
	@Override
	public String modifyRange(final int start, final int end) {
		return modify(start, end, new StringBuffer());
	}
	
	private String modify(final int start, final int end, StringBuffer output) {
		if (start > end) {
			return output.toString().trim();
		}
		
		output.append(applyModifiers(start));
		output.append(" ");
		
		return modify(start+1, end, output); 
	}
	
	private String applyModifiers(int input) {
		return modifiers.stream()
						.sorted((m1,m2) -> m1.getPriority() - m2.getPriority())
						.dropWhile(m -> !m.isApplicable(input))
						.findFirst()
						.flatMap(m -> m.modify(input))
						.orElse(Integer.toString(input));
	}
}
