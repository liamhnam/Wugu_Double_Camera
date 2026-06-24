package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

class C2225x24bfe126 extends FunctionReference implements Function1<ClassId, ClassId> {
    public static final C2225x24bfe126 INSTANCE = new C2225x24bfe126();

    C2225x24bfe126() {
        super(1);
    }

    @Override
    public final String getName() {
        return "getOuterClassId";
    }

    @Override
    public final String getSignature() {
        return "getOuterClassId()Lorg/jetbrains/kotlin/name/ClassId;";
    }

    @Override
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ClassId.class);
    }

    @Override
    public final ClassId invoke(ClassId p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return p0.getOuterClassId();
    }
}
