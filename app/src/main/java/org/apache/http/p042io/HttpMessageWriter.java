package org.apache.http.p042io;

import org.apache.http.HttpMessage;

public interface HttpMessageWriter<T extends HttpMessage> {
    void write(T t);
}
