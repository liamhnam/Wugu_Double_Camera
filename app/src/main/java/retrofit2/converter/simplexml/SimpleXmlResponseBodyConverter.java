package retrofit2.converter.simplexml;

import java.io.IOException;
import okhttp3.ResponseBody;
import org.simpleframework.xml.Serializer;
import retrofit2.Converter;

final class SimpleXmlResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Class<T> cls;
    private final Serializer serializer;
    private final boolean strict;

    SimpleXmlResponseBodyConverter(Class<T> cls, Serializer serializer, boolean z) {
        this.cls = cls;
        this.serializer = serializer;
        this.strict = z;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        try {
            try {
                T t = (T) this.serializer.read((Class) this.cls, responseBody.charStream(), this.strict);
                if (t != null) {
                    return t;
                }
                throw new IllegalStateException("Could not deserialize body as " + this.cls);
            } catch (IOException e) {
                throw e;
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new RuntimeException(e3);
            }
        } finally {
            responseBody.close();
        }
    }
}
