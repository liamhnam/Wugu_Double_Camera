package org.apache.http.impl.execchain;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.http.HttpEntity;
import org.apache.http.conn.EofSensorInputStream;
import org.apache.http.conn.EofSensorWatcher;
import org.apache.http.entity.HttpEntityWrapper;

class ResponseEntityWrapper extends HttpEntityWrapper implements EofSensorWatcher {
    private final ConnectionHolder connReleaseTrigger;

    public ResponseEntityWrapper(HttpEntity httpEntity, ConnectionHolder connectionHolder) {
        super(httpEntity);
        this.connReleaseTrigger = connectionHolder;
    }

    private void cleanup() {
        ConnectionHolder connectionHolder = this.connReleaseTrigger;
        if (connectionHolder != null) {
            connectionHolder.abortConnection();
        }
    }

    @Override
    @Deprecated
    public void consumeContent() {
        releaseConnection();
    }

    @Override
    public boolean eofDetected(InputStream inputStream) {
        try {
            inputStream.close();
            releaseConnection();
            cleanup();
            return false;
        } catch (Throwable th) {
            cleanup();
            throw th;
        }
    }

    @Override
    public InputStream getContent() {
        return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    public void releaseConnection() {
        ConnectionHolder connectionHolder = this.connReleaseTrigger;
        if (connectionHolder != null) {
            try {
                if (connectionHolder.isReusable()) {
                    this.connReleaseTrigger.releaseConnection();
                }
            } finally {
                cleanup();
            }
        }
    }

    @Override
    public boolean streamAbort(InputStream inputStream) {
        cleanup();
        return false;
    }

    @Override
    public boolean streamClosed(InputStream inputStream) {
        try {
            ConnectionHolder connectionHolder = this.connReleaseTrigger;
            boolean z = (connectionHolder == null || connectionHolder.isReleased()) ? false : true;
            try {
                inputStream.close();
                releaseConnection();
            } catch (SocketException e) {
                if (z) {
                    throw e;
                }
            }
            return false;
        } finally {
            cleanup();
        }
    }

    @Override
    public void writeTo(OutputStream outputStream) {
        try {
            this.wrappedEntity.writeTo(outputStream);
            releaseConnection();
        } finally {
            cleanup();
        }
    }
}
