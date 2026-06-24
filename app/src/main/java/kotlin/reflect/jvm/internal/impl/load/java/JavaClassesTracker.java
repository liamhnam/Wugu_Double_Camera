package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;

public interface JavaClassesTracker {
    void reportClass(JavaClassDescriptor javaClassDescriptor);

    public static final class Default implements JavaClassesTracker {
        public static final Default INSTANCE = new Default();

        @Override
        public void reportClass(JavaClassDescriptor classDescriptor) {
            Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        }

        private Default() {
        }
    }
}
