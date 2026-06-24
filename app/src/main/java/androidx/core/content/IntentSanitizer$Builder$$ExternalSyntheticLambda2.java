package androidx.core.content;

import androidx.core.util.Predicate;

public final class IntentSanitizer$Builder$$ExternalSyntheticLambda2 implements Predicate {
    public final String f$0;

    public IntentSanitizer$Builder$$ExternalSyntheticLambda2(String str) {
        this.f$0 = str;
    }

    @Override
    public final boolean test(Object obj) {
        return this.f$0.equals((String) obj);
    }
}
