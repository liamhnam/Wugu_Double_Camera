package kotlin.reflect.jvm.internal.impl.types;

public interface CustomTypeParameter {
    boolean isTypeParameter();

    KotlinType substitutionResult(KotlinType kotlinType);
}
