package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends InputStream {
    private int counter = 0;

    private InputStream f3777in;

    public CountingInputStream(InputStream inputStream) {
        this.f3777in = inputStream;
    }

    @Override
    public int read() throws IOException {
        int i = this.f3777in.read();
        if (i != -1) {
            this.counter++;
        }
        return i;
    }

    public int getCounter() {
        return this.counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }
}
