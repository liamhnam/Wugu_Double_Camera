package com.liulishuo.okdownload.core.interceptor.connect;

import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.download.DownloadChain;
import com.liulishuo.okdownload.core.exception.InterruptException;
import com.liulishuo.okdownload.core.interceptor.Interceptor;
import java.io.IOException;
import java.net.ProtocolException;
import org.apache.http.HttpHeaders;

public class RedirectInterceptor implements Interceptor.Connect {
    private static final int HTTP_PERMANENT_REDIRECT = 308;
    private static final int HTTP_TEMPORARY_REDIRECT = 307;
    static final int MAX_REDIRECT_TIMES = 10;

    private static boolean isRedirect(int i) {
        return i == 301 || i == 302 || i == 303 || i == 300 || i == 307 || i == 308;
    }

    @Override
    public DownloadConnection.Connected interceptConnect(DownloadChain downloadChain) throws IOException {
        DownloadConnection connectionOrCreate = downloadChain.getConnectionOrCreate();
        int i = 0;
        while (!downloadChain.getCache().isInterrupt()) {
            DownloadConnection.Connected connectedProcessConnect = downloadChain.processConnect();
            int responseCode = connectedProcessConnect.getResponseCode();
            if (!isRedirect(responseCode)) {
                return connectedProcessConnect;
            }
            i++;
            if (i >= 10) {
                throw new ProtocolException("Too many redirect requests: " + i);
            }
            String responseHeaderField = connectedProcessConnect.getResponseHeaderField(HttpHeaders.LOCATION);
            if (responseHeaderField == null) {
                throw new ProtocolException("Response code is " + responseCode + " but can't find Location field");
            }
            connectionOrCreate.release();
            connectionOrCreate = OkDownload.with().connectionFactory().create(responseHeaderField);
            downloadChain.setConnection(connectionOrCreate);
            downloadChain.setRedirectLocation(responseHeaderField);
        }
        throw InterruptException.SIGNAL;
    }
}
