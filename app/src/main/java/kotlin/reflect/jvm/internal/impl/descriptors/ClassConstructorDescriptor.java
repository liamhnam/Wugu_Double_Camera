package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public interface ClassConstructorDescriptor extends ConstructorDescriptor {
    @Override
    ClassConstructorDescriptor getOriginal();

    @Override
    ClassConstructorDescriptor substitute(TypeSubstitutor typeSubstitutor);
}
