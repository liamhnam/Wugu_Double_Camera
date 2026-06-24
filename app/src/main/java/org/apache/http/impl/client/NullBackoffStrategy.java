package org.apache.http.impl.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.ConnectionBackoffStrategy;

public class NullBackoffStrategy implements ConnectionBackoffStrategy {
    @Override
    public boolean shouldBackoff(Throwable th) {
        return false;
    }

    @Override
    public boolean shouldBackoff(HttpResponse httpResponse) {
        return false;
    }
}
