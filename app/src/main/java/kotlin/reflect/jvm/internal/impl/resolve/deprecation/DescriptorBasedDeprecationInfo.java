package kotlin.reflect.jvm.internal.impl.resolve.deprecation;

public abstract class DescriptorBasedDeprecationInfo extends DeprecationInfo {
    @Override
    public boolean getPropagatesToOverrides() {
        return true;
    }
}
