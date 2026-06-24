package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public interface ConstructorDescriptor extends FunctionDescriptor {
    ClassDescriptor getConstructedClass();

    @Override
    ClassifierDescriptorWithTypeParameters getContainingDeclaration();

    @Override
    KotlinType getReturnType();

    @Override
    List<TypeParameterDescriptor> getTypeParameters();

    boolean isPrimary();

    @Override
    ConstructorDescriptor substitute(TypeSubstitutor typeSubstitutor);
}
