package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassObjectAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaClassObjectAnnotationArgument extends ReflectJavaAnnotationArgument implements JavaClassObjectAnnotationArgument {
    private final Class<?> klass;

    public ReflectJavaClassObjectAnnotationArgument(Name name, Class<?> klass) {
        super(name, null);
        Intrinsics.checkNotNullParameter(klass, "klass");
        this.klass = klass;
    }

    @Override
    public JavaType getReferencedType() {
        return ReflectJavaType.Factory.create(this.klass);
    }
}
