package org.apache.http.conn;

public interface ConnectionReleaseTrigger {
    void abortConnection();

    void releaseConnection();
}
