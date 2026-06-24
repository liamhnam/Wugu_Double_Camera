package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;

public interface VariableDescriptor extends ValueDescriptor {
    ConstantValue<?> mo3065getCompileTimeInitializer();

    boolean isConst();

    boolean isLateInit();

    boolean isVar();
}
