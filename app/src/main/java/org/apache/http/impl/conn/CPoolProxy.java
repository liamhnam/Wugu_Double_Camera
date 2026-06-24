package org.apache.http.impl.conn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnection;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.protocol.HttpContext;

class CPoolProxy implements InvocationHandler {
    private static final Method CLOSE_METHOD;
    private static final Method IS_OPEN_METHOD;
    private static final Method IS_STALE_METHOD;
    private static final Method SHUTDOWN_METHOD;
    private volatile CPoolEntry poolEntry;

    static {
        try {
            CLOSE_METHOD = HttpConnection.class.getMethod("close", new Class[0]);
            SHUTDOWN_METHOD = HttpConnection.class.getMethod("shutdown", new Class[0]);
            IS_OPEN_METHOD = HttpConnection.class.getMethod("isOpen", new Class[0]);
            IS_STALE_METHOD = HttpConnection.class.getMethod("isStale", new Class[0]);
        } catch (NoSuchMethodException e) {
            throw new Error(e);
        }
    }

    CPoolProxy(CPoolEntry cPoolEntry) {
        this.poolEntry = cPoolEntry;
    }

    public static CPoolEntry detach(HttpClientConnection httpClientConnection) {
        return getHandler(httpClientConnection).detach();
    }

    private static CPoolProxy getHandler(HttpClientConnection httpClientConnection) {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(httpClientConnection);
        if (CPoolProxy.class.isInstance(invocationHandler)) {
            return (CPoolProxy) CPoolProxy.class.cast(invocationHandler);
        }
        throw new IllegalStateException("Unexpected proxy handler class: " + invocationHandler);
    }

    public static CPoolEntry getPoolEntry(HttpClientConnection httpClientConnection) {
        CPoolEntry poolEntry = getHandler(httpClientConnection).getPoolEntry();
        if (poolEntry != null) {
            return poolEntry;
        }
        throw new ConnectionShutdownException();
    }

    public static HttpClientConnection newProxy(CPoolEntry cPoolEntry) {
        return (HttpClientConnection) Proxy.newProxyInstance(CPoolProxy.class.getClassLoader(), new Class[]{ManagedHttpClientConnection.class, HttpContext.class}, new CPoolProxy(cPoolEntry));
    }

    public void close() {
        CPoolEntry cPoolEntry = this.poolEntry;
        if (cPoolEntry != null) {
            cPoolEntry.closeConnection();
        }
    }

    CPoolEntry detach() {
        CPoolEntry cPoolEntry = this.poolEntry;
        this.poolEntry = null;
        return cPoolEntry;
    }

    HttpClientConnection getConnection() {
        CPoolEntry cPoolEntry = this.poolEntry;
        if (cPoolEntry == null) {
            return null;
        }
        return cPoolEntry.getConnection();
    }

    CPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        boolean zIsStale;
        if (method.equals(CLOSE_METHOD)) {
            close();
            return null;
        }
        if (method.equals(SHUTDOWN_METHOD)) {
            shutdown();
            return null;
        }
        if (method.equals(IS_OPEN_METHOD)) {
            zIsStale = isOpen();
        } else {
            if (!method.equals(IS_STALE_METHOD)) {
                HttpClientConnection connection = getConnection();
                if (connection == null) {
                    throw new ConnectionShutdownException();
                }
                try {
                    return method.invoke(connection, objArr);
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    if (cause != null) {
                        throw cause;
                    }
                    throw e;
                }
            }
            zIsStale = isStale();
        }
        return Boolean.valueOf(zIsStale);
    }

    public boolean isOpen() {
        if (this.poolEntry != null) {
            return !r0.isClosed();
        }
        return false;
    }

    public boolean isStale() {
        HttpClientConnection connection = getConnection();
        if (connection != null) {
            return connection.isStale();
        }
        return true;
    }

    public void shutdown() {
        CPoolEntry cPoolEntry = this.poolEntry;
        if (cPoolEntry != null) {
            cPoolEntry.shutdownConnection();
        }
    }
}
