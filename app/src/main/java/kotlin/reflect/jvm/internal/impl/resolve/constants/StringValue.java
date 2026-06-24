package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.text.Typography;

public final class StringValue extends ConstantValue<String> {
    public StringValue(String value) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
    }

    @Override
    public SimpleType getType(ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(module, "module");
        SimpleType stringType = module.getBuiltIns().getStringType();
        Intrinsics.checkNotNullExpressionValue(stringType, "module.builtIns.stringType");
        return stringType;
    }

    @Override
    public String toString() {
        return "\"" + getValue() + Typography.quote;
    }
}
