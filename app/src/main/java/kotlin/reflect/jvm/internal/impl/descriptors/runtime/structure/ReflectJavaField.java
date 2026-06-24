package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;

public final class ReflectJavaField extends ReflectJavaMember implements JavaField {
    private final Field member;

    @Override
    public boolean getHasConstantNotNullInitializer() {
        return false;
    }

    public ReflectJavaField(Field member) {
        Intrinsics.checkNotNullParameter(member, "member");
        this.member = member;
    }

    @Override
    public Field getMember() {
        return this.member;
    }

    @Override
    public boolean isEnumEntry() {
        return getMember().isEnumConstant();
    }

    @Override
    public ReflectJavaType getType() {
        ReflectJavaType.Factory factory = ReflectJavaType.Factory;
        Type genericType = getMember().getGenericType();
        Intrinsics.checkNotNullExpressionValue(genericType, "member.genericType");
        return factory.create(genericType);
    }
}
