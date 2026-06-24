package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

public abstract class WrappedType extends KotlinType {
    protected abstract KotlinType getDelegate();

    public boolean isComputed() {
        return true;
    }

    public WrappedType() {
        super(null);
    }

    @Override
    public Annotations getAnnotations() {
        return getDelegate().getAnnotations();
    }

    @Override
    public TypeConstructor getConstructor() {
        return getDelegate().getConstructor();
    }

    @Override
    public List<TypeProjection> getArguments() {
        return getDelegate().getArguments();
    }

    @Override
    public boolean isMarkedNullable() {
        return getDelegate().isMarkedNullable();
    }

    @Override
    public MemberScope getMemberScope() {
        return getDelegate().getMemberScope();
    }

    @Override
    public final UnwrappedType unwrap() {
        KotlinType delegate = getDelegate();
        while (delegate instanceof WrappedType) {
            delegate = ((WrappedType) delegate).getDelegate();
        }
        return (UnwrappedType) delegate;
    }

    public String toString() {
        return isComputed() ? getDelegate().toString() : "<Not computed yet>";
    }
}
