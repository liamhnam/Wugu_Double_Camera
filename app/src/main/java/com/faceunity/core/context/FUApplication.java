package com.faceunity.core.context;

import android.app.Application;
import com.google.android.exoplayer2.util.MimeTypes;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0004H\u0003R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, m1293d2 = {"Lcom/faceunity/core/context/FUApplication;", "", "()V", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "application$annotations", "getApplication", "()Landroid/app/Application;", "mApplication", "reflectionGetApplication", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUApplication {
    public static final FUApplication INSTANCE = new FUApplication();
    private static volatile Application mApplication;

    @JvmStatic
    public static void application$annotations() {
    }

    private FUApplication() {
    }

    public static final Application getApplication() {
        if (mApplication == null) {
            final FUApplication fUApplication = INSTANCE;
            synchronized (new PropertyReference0(fUApplication) {
                {
                    super(fUApplication);
                }

                @Override
                public String getName() {
                    return "javaClass";
                }

                @Override
                public KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinPackage(JvmClassMappingKt.class, "fu_core_all_featureRelease");
                }

                @Override
                public String getSignature() {
                    return "getJavaClass(Ljava/lang/Object;)Ljava/lang/Class;";
                }

                @Override
                public Object get() {
                    return JvmClassMappingKt.getJavaClass((FUApplication) this.receiver);
                }
            }) {
                if (mApplication == null) {
                    mApplication = fUApplication.reflectionGetApplication();
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        Application application = mApplication;
        if (application == null) {
            Intrinsics.throwNpe();
        }
        return application;
    }

    private final Application reflectionGetApplication() throws IllegalAccessException, InvocationTargetException {
        Object objInvoke = Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
        if (objInvoke != null) {
            return (Application) objInvoke;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
    }
}
