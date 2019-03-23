package com.equalexperts.fb.service;

import java.util.Set;
import com.equalexperts.fb.modifiers.Modifier;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

/**
 * Apply modifiers to a range of integer values;
 * 
 * @author a.nonymous
 *
 */
public final class ModificationServiceImpl implements ModificationService {
    
    private final Set<Modifier<Integer, String>> modifiers;

    public ModificationServiceImpl(Set<Modifier<Integer, String>> modifiers) {
        this.modifiers = modifiers;
    }

    /* (non-Javadoc)
     * @see com.equalexperts.fb.service.ModificationService#modifyRange(int, int)
     */
    @Override    
    public Observable<String> modifyRange(final int start, final int end) {
        
        return Observable.<String>create(emitter -> {
            try {
                modify(start, end, emitter);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
    
    private void modify(final int start, final int end, ObservableEmitter<String> emitter) {
        for (int i = start ; i <= end ; i++) {
            emitter.onNext(applyModifiers(i));
        }
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
