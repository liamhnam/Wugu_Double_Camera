package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;

public abstract class DelegatingSimpleType extends SimpleType {
    protected abstract SimpleType getDelegate();

    public abstract DelegatingSimpleType replaceDelegate(SimpleType simpleType);

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
    public SimpleType refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return replaceDelegate((SimpleType) kotlinTypeRefiner.refineType((KotlinTypeMarker) getDelegate()));
    }
}
