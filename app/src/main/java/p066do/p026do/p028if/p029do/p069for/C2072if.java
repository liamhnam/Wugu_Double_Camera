package p066do.p026do.p028if.p029do.p069for;

import com.p020hp.mobile.common.identity.Json;
import com.p020hp.mobile.common.identity.Xml;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public final class C2072if extends Converter.Factory {

    public final SimpleXmlConverterFactory f2507do;

    public final GsonConverterFactory f2508if;

    public C2072if() {
        SimpleXmlConverterFactory simpleXmlConverterFactoryCreate = SimpleXmlConverterFactory.create();
        Intrinsics.checkNotNullExpressionValue(simpleXmlConverterFactoryCreate, "create()");
        this.f2507do = simpleXmlConverterFactoryCreate;
        GsonConverterFactory gsonConverterFactoryCreate = GsonConverterFactory.create();
        Intrinsics.checkNotNullExpressionValue(gsonConverterFactoryCreate, "create()");
        this.f2508if = gsonConverterFactoryCreate;
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
        if (Intrinsics.areEqual(qualifiedName, Xml.class.getName())) {
            return this.f2507do.responseBodyConverter(type, annotations, retrofit);
        }
        if (Intrinsics.areEqual(qualifiedName, Json.class.getName())) {
            return this.f2508if.responseBodyConverter(type, annotations, retrofit);
        }
        return null;
    }
}
