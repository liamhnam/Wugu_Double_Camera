package org.apache.http.p042io;

public interface BufferInfo {
    int available();

    int capacity();

    int length();
}
