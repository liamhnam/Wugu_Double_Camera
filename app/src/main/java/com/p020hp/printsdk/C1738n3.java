package com.p020hp.printsdk;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public final class C1738n3 extends Converter.Factory {

    public final SimpleXmlConverterFactory f1518a;

    public final GsonConverterFactory f1519b;

    public C1738n3() {
        SimpleXmlConverterFactory simpleXmlConverterFactoryCreate = SimpleXmlConverterFactory.create();
        Intrinsics.checkNotNullExpressionValue(simpleXmlConverterFactoryCreate, "create()");
        this.f1518a = simpleXmlConverterFactoryCreate;
        GsonConverterFactory gsonConverterFactoryCreate = GsonConverterFactory.create();
        Intrinsics.checkNotNullExpressionValue(gsonConverterFactoryCreate, "create()");
        this.f1519b = gsonConverterFactoryCreate;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        if (annotations.length <= 0) {
            return null;
        }
        String qualifiedName = JvmClassMappingKt.getAnnotationClass(annotations[0]).getQualifiedName();
        if (Intrinsics.areEqual(qualifiedName, InterfaceC1733m3.class.getName())) {
            return this.f1518a.responseBodyConverter(type, annotations, retrofit);
        }
        if (Intrinsics.areEqual(qualifiedName, InterfaceC1728l3.class.getName())) {
            return this.f1519b.responseBodyConverter(type, annotations, retrofit);
        }
        return null;
    }
}
