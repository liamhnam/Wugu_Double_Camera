package kotlin.reflect.jvm.internal.impl.load.java.lazy;

public interface JavaResolverSettings {
    public static final Companion Companion = Companion.$$INSTANCE;

    boolean getCorrectNullabilityForNotNullTypeParameter();

    boolean getIgnoreNullabilityForErasedValueParameters();

    boolean getTypeEnhancementImprovementsInStrictMode();

    public static final class Default implements JavaResolverSettings {
        public static final Default INSTANCE = new Default();

        @Override
        public boolean getCorrectNullabilityForNotNullTypeParameter() {
            return false;
        }

        @Override
        public boolean getIgnoreNullabilityForErasedValueParameters() {
            return false;
        }

        @Override
        public boolean getTypeEnhancementImprovementsInStrictMode() {
            return false;
        }

        private Default() {
        }
    }

    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
