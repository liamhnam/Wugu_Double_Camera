package com.wugu.doublecamera.network;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

public class ProgressRequestBody extends RequestBody {
    private final ProgressListener progressListener;
    private final RequestBody requestBody;

    interface ProgressListener {
        void onProgressChanged(long j, long j2);
    }

    ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.requestBody = requestBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType getContentType() {
        return this.requestBody.getContentType();
    }

    @Override
    public long contentLength() throws IOException {
        return this.requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        BufferedSink bufferedSinkBuffer = Okio.buffer(new ForwardingSink(bufferedSink) {
            long bytesWritten = 0;
            long totalBytes = 0;

            @Override
            public void write(Buffer buffer, long j) throws IOException {
                super.write(buffer, j);
                if (this.totalBytes == 0) {
                    this.totalBytes = ProgressRequestBody.this.contentLength();
                }
                this.bytesWritten += j;
                if (ProgressRequestBody.this.progressListener != null) {
                    ProgressRequestBody.this.progressListener.onProgressChanged(this.bytesWritten, this.totalBytes);
                }
            }
        });
        this.requestBody.writeTo(bufferedSinkBuffer);
        bufferedSinkBuffer.flush();
    }
}
