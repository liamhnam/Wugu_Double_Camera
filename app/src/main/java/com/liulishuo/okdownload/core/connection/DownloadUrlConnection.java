package com.liulishuo.okdownload.core.connection;

import com.liulishuo.okdownload.core.connection.DownloadConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class DownloadUrlConnection implements DownloadConnection, DownloadConnection.Connected {
    protected URLConnection connection;

    DownloadUrlConnection(URLConnection uRLConnection) {
        this.connection = uRLConnection;
    }

    public DownloadUrlConnection(String str, Configuration configuration) throws IOException {
        this(new URL(str), configuration);
    }

    public DownloadUrlConnection(URL url, Configuration configuration) throws IOException {
        if (configuration == null || configuration.proxy == null) {
            this.connection = url.openConnection();
        } else {
            this.connection = url.openConnection(configuration.proxy);
        }
        if (configuration != null) {
            if (configuration.readTimeout != null) {
                this.connection.setReadTimeout(configuration.readTimeout.intValue());
            }
            if (configuration.connectTimeout != null) {
                this.connection.setConnectTimeout(configuration.connectTimeout.intValue());
            }
        }
    }

    public DownloadUrlConnection(String str) throws IOException {
        this(str, (Configuration) null);
    }

    @Override
    public void addHeader(String str, String str2) {
        this.connection.addRequestProperty(str, str2);
    }

    @Override
    public DownloadConnection.Connected execute() throws IOException {
        this.connection.connect();
        return this;
    }

    @Override
    public int getResponseCode() throws IOException {
        URLConnection uRLConnection = this.connection;
        if (uRLConnection instanceof HttpURLConnection) {
            return ((HttpURLConnection) uRLConnection).getResponseCode();
        }
        return 0;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.connection.getInputStream();
    }

    @Override
    public boolean setRequestMethod(String str) throws ProtocolException {
        URLConnection uRLConnection = this.connection;
        if (!(uRLConnection instanceof HttpURLConnection)) {
            return false;
        }
        ((HttpURLConnection) uRLConnection).setRequestMethod(str);
        return true;
    }

    @Override
    public Map<String, List<String>> getResponseHeaderFields() {
        return this.connection.getHeaderFields();
    }

    @Override
    public String getResponseHeaderField(String str) {
        return this.connection.getHeaderField(str);
    }

    @Override
    public void release() {
        try {
            this.connection.getInputStream().close();
        } catch (IOException unused) {
        }
    }

    @Override
    public Map<String, List<String>> getRequestProperties() {
        return this.connection.getRequestProperties();
    }

    @Override
    public String getRequestProperty(String str) {
        return this.connection.getRequestProperty(str);
    }

    public static class Factory implements DownloadConnection.Factory {
        private final Configuration configuration;

        public Factory() {
            this(null);
        }

        public Factory(Configuration configuration) {
            this.configuration = configuration;
        }

        DownloadConnection create(URL url) throws IOException {
            return new DownloadUrlConnection(url, this.configuration);
        }

        @Override
        public DownloadConnection create(String str) throws IOException {
            return new DownloadUrlConnection(str, this.configuration);
        }
    }

    public static class Configuration {
        private Integer connectTimeout;
        private Proxy proxy;
        private Integer readTimeout;

        public Configuration proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        public Configuration readTimeout(int i) {
            this.readTimeout = Integer.valueOf(i);
            return this;
        }

        public Configuration connectTimeout(int i) {
            this.connectTimeout = Integer.valueOf(i);
            return this;
        }
    }
}
