package org.apache.http.impl.execchain;

import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.http.HttpEntity;

class RequestEntityExecHandler implements InvocationHandler {
    private static final Method WRITE_TO_METHOD;
    private boolean consumed = false;
    private final HttpEntity original;

    static {
        try {
            WRITE_TO_METHOD = HttpEntity.class.getMethod("writeTo", OutputStream.class);
        } catch (NoSuchMethodException e) {
            throw new Error(e);
        }
    }

    RequestEntityExecHandler(HttpEntity httpEntity) {
        this.original = httpEntity;
    }

    public HttpEntity getOriginal() {
        return this.original;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            if (method.equals(WRITE_TO_METHOD)) {
                this.consumed = true;
            }
            return method.invoke(this.original, objArr);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                throw cause;
            }
            throw e;
        }
    }

    public boolean isConsumed() {
        return this.consumed;
    }
}
