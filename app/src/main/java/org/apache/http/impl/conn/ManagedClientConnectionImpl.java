package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Deprecated
class ManagedClientConnectionImpl implements ManagedClientConnection {
    private volatile long duration;
    private final ClientConnectionManager manager;
    private final ClientConnectionOperator operator;
    private volatile HttpPoolEntry poolEntry;
    private volatile boolean reusable;

    ManagedClientConnectionImpl(ClientConnectionManager clientConnectionManager, ClientConnectionOperator clientConnectionOperator, HttpPoolEntry httpPoolEntry) {
        Args.notNull(clientConnectionManager, "Connection manager");
        Args.notNull(clientConnectionOperator, "Connection operator");
        Args.notNull(httpPoolEntry, "HTTP pool entry");
        this.manager = clientConnectionManager;
        this.operator = clientConnectionOperator;
        this.poolEntry = httpPoolEntry;
        this.reusable = false;
        this.duration = Long.MAX_VALUE;
    }

    private OperatedClientConnection ensureConnection() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry != null) {
            return httpPoolEntry.getConnection();
        }
        throw new ConnectionShutdownException();
    }

    private HttpPoolEntry ensurePoolEntry() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry != null) {
            return httpPoolEntry;
        }
        throw new ConnectionShutdownException();
    }

    private OperatedClientConnection getConnection() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry == null) {
            return null;
        }
        return httpPoolEntry.getConnection();
    }

    @Override
    public void abortConnection() {
        synchronized (this) {
            if (this.poolEntry == null) {
                return;
            }
            this.reusable = false;
            try {
                this.poolEntry.getConnection().shutdown();
            } catch (IOException unused) {
            }
            this.manager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
            this.poolEntry = null;
        }
    }

    @Override
    public void bind(Socket socket) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry != null) {
            OperatedClientConnection connection = httpPoolEntry.getConnection();
            httpPoolEntry.getTracker().reset();
            connection.close();
        }
    }

    HttpPoolEntry detach() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        this.poolEntry = null;
        return httpPoolEntry;
    }

    @Override
    public void flush() {
        ensureConnection().flush();
    }

    public Object getAttribute(String str) {
        OperatedClientConnection operatedClientConnectionEnsureConnection = ensureConnection();
        if (operatedClientConnectionEnsureConnection instanceof HttpContext) {
            return ((HttpContext) operatedClientConnectionEnsureConnection).getAttribute(str);
        }
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public InetAddress getLocalAddress() {
        return ensureConnection().getLocalAddress();
    }

    @Override
    public int getLocalPort() {
        return ensureConnection().getLocalPort();
    }

    public ClientConnectionManager getManager() {
        return this.manager;
    }

    @Override
    public HttpConnectionMetrics getMetrics() {
        return ensureConnection().getMetrics();
    }

    HttpPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    @Override
    public InetAddress getRemoteAddress() {
        return ensureConnection().getRemoteAddress();
    }

    @Override
    public int getRemotePort() {
        return ensureConnection().getRemotePort();
    }

    @Override
    public HttpRoute getRoute() {
        return ensurePoolEntry().getEffectiveRoute();
    }

    @Override
    public SSLSession getSSLSession() {
        Socket socket = ensureConnection().getSocket();
        if (socket instanceof SSLSocket) {
            return ((SSLSocket) socket).getSession();
        }
        return null;
    }

    @Override
    public Socket getSocket() {
        return ensureConnection().getSocket();
    }

    @Override
    public int getSocketTimeout() {
        return ensureConnection().getSocketTimeout();
    }

    @Override
    public Object getState() {
        return ensurePoolEntry().getState();
    }

    @Override
    public boolean isMarkedReusable() {
        return this.reusable;
    }

    @Override
    public boolean isOpen() {
        OperatedClientConnection connection = getConnection();
        if (connection != null) {
            return connection.isOpen();
        }
        return false;
    }

    @Override
    public boolean isResponseAvailable(int i) {
        return ensureConnection().isResponseAvailable(i);
    }

    @Override
    public boolean isSecure() {
        return ensureConnection().isSecure();
    }

    @Override
    public boolean isStale() {
        OperatedClientConnection connection = getConnection();
        if (connection != null) {
            return connection.isStale();
        }
        return true;
    }

    @Override
    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) {
        HttpHost targetHost;
        OperatedClientConnection connection;
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            Asserts.notNull(tracker, "Route tracker");
            Asserts.check(tracker.isConnected(), "Connection not open");
            Asserts.check(tracker.isTunnelled(), "Protocol layering without a tunnel not supported");
            Asserts.check(!tracker.isLayered(), "Multiple protocol layering not supported");
            targetHost = tracker.getTargetHost();
            connection = this.poolEntry.getConnection();
        }
        this.operator.updateSecureConnection(connection, targetHost, httpContext, httpParams);
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            this.poolEntry.getTracker().layerProtocol(connection.isSecure());
        }
    }

    @Override
    public void markReusable() {
        this.reusable = true;
    }

    @Override
    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) {
        OperatedClientConnection connection;
        Args.notNull(httpRoute, "Route");
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            Asserts.notNull(this.poolEntry.getTracker(), "Route tracker");
            Asserts.check(!r0.isConnected(), "Connection already open");
            connection = this.poolEntry.getConnection();
        }
        HttpHost proxyHost = httpRoute.getProxyHost();
        this.operator.openConnection(connection, proxyHost != null ? proxyHost : httpRoute.getTargetHost(), httpRoute.getLocalAddress(), httpContext, httpParams);
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            if (proxyHost == null) {
                tracker.connectTarget(connection.isSecure());
            } else {
                tracker.connectProxy(proxyHost, connection.isSecure());
            }
        }
    }

    @Override
    public void receiveResponseEntity(HttpResponse httpResponse) {
        ensureConnection().receiveResponseEntity(httpResponse);
    }

    @Override
    public HttpResponse receiveResponseHeader() {
        return ensureConnection().receiveResponseHeader();
    }

    @Override
    public void releaseConnection() {
        synchronized (this) {
            if (this.poolEntry == null) {
                return;
            }
            this.manager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
            this.poolEntry = null;
        }
    }

    public Object removeAttribute(String str) {
        OperatedClientConnection operatedClientConnectionEnsureConnection = ensureConnection();
        if (operatedClientConnectionEnsureConnection instanceof HttpContext) {
            return ((HttpContext) operatedClientConnectionEnsureConnection).removeAttribute(str);
        }
        return null;
    }

    @Override
    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        ensureConnection().sendRequestEntity(httpEntityEnclosingRequest);
    }

    @Override
    public void sendRequestHeader(HttpRequest httpRequest) {
        ensureConnection().sendRequestHeader(httpRequest);
    }

    public void setAttribute(String str, Object obj) {
        OperatedClientConnection operatedClientConnectionEnsureConnection = ensureConnection();
        if (operatedClientConnectionEnsureConnection instanceof HttpContext) {
            ((HttpContext) operatedClientConnectionEnsureConnection).setAttribute(str, obj);
        }
    }

    @Override
    public void setIdleDuration(long j, TimeUnit timeUnit) {
        this.duration = j > 0 ? timeUnit.toMillis(j) : -1L;
    }

    @Override
    public void setSocketTimeout(int i) {
        ensureConnection().setSocketTimeout(i);
    }

    @Override
    public void setState(Object obj) {
        ensurePoolEntry().setState(obj);
    }

    @Override
    public void shutdown() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry != null) {
            OperatedClientConnection connection = httpPoolEntry.getConnection();
            httpPoolEntry.getTracker().reset();
            connection.shutdown();
        }
    }

    @Override
    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) {
        OperatedClientConnection connection;
        Args.notNull(httpHost, "Next proxy");
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            Asserts.notNull(tracker, "Route tracker");
            Asserts.check(tracker.isConnected(), "Connection not open");
            connection = this.poolEntry.getConnection();
        }
        connection.update(null, httpHost, z, httpParams);
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            this.poolEntry.getTracker().tunnelProxy(httpHost, z);
        }
    }

    @Override
    public void tunnelTarget(boolean z, HttpParams httpParams) {
        HttpHost targetHost;
        OperatedClientConnection connection;
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            Asserts.notNull(tracker, "Route tracker");
            Asserts.check(tracker.isConnected(), "Connection not open");
            Asserts.check(!tracker.isTunnelled(), "Connection is already tunnelled");
            targetHost = tracker.getTargetHost();
            connection = this.poolEntry.getConnection();
        }
        connection.update(null, targetHost, z, httpParams);
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            this.poolEntry.getTracker().tunnelTarget(z);
        }
    }

    @Override
    public void unmarkReusable() {
        this.reusable = false;
    }
}
