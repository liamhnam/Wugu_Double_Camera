package org.apache.http;

import java.io.Closeable;

public interface HttpConnection extends Closeable {
    @Override
    void close();

    HttpConnectionMetrics getMetrics();

    int getSocketTimeout();

    boolean isOpen();

    boolean isStale();

    void setSocketTimeout(int i);

    void shutdown();
}
