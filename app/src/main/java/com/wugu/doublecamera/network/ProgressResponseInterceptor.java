package com.wugu.doublecamera.network;

import com.wugu.doublecamera.network.ProgressResponseBody;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ProgressResponseInterceptor implements Interceptor {
    private ProgressResponseBody.ProgressListener progressListener;

    public ProgressResponseInterceptor(ProgressResponseBody.ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response responseProceed = chain.proceed(chain.request());
        return responseProceed.newBuilder().body(new ProgressResponseBody(responseProceed.body(), this.progressListener)).build();
    }
}
