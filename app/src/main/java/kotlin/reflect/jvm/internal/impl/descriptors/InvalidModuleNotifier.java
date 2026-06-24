package kotlin.reflect.jvm.internal.impl.descriptors;

public interface InvalidModuleNotifier {
    void notifyModuleInvalidated(ModuleDescriptor moduleDescriptor);
}
