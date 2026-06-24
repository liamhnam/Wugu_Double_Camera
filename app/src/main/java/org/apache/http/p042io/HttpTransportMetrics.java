package org.apache.http.p042io;

public interface HttpTransportMetrics {
    long getBytesTransferred();

    void reset();
}
