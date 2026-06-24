package kotlin.reflect.jvm.internal.impl.types.model;

public enum TypeVariance {
    IN("in"),
    OUT("out"),
    INV("");

    private final String presentation;

    TypeVariance(String str) {
        this.presentation = str;
    }

    @Override
    public String toString() {
        return this.presentation;
    }
}
