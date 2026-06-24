package com.wugu.doublecamera.network;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {
    private BufferedSource bufferedSource;
    private final ProgressListener progressListener;
    private final ResponseBody responseBody;

    interface ProgressListener {
        void onProgressChanged(long j, long j2);
    }

    ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType get$contentType() {
        return this.responseBody.get$contentType();
    }

    @Override
    public long getContentLength() {
        return this.responseBody.getContentLength();
    }

    @Override
    public BufferedSource getBodySource() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(source(this.responseBody.getBodySource()));
        }
        return this.bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0;

            @Override
            public long read(Buffer buffer, long j) throws IOException {
                long j2 = super.read(buffer, j);
                this.totalBytesRead += j2 != -1 ? j2 : 0L;
                ProgressResponseBody.this.progressListener.onProgressChanged(this.totalBytesRead, ProgressResponseBody.this.responseBody.getContentLength());
                return j2;
            }
        };
    }
}
