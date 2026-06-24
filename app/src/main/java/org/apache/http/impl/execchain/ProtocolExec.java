package org.apache.http.impl.execchain;

import java.io.IOException;
import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.util.Args;

public class ProtocolExec implements ClientExecChain {
    private final HttpProcessor httpProcessor;
    private final Log log = LogFactory.getLog(ProtocolExec.class);
    private final ClientExecChain requestExecutor;

    public ProtocolExec(ClientExecChain clientExecChain, HttpProcessor httpProcessor) {
        Args.notNull(clientExecChain, "HTTP client request executor");
        Args.notNull(httpProcessor, "HTTP protocol processor");
        this.requestExecutor = clientExecChain;
        this.httpProcessor = httpProcessor;
    }

    @Override
    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws HttpException, IOException {
        URI uriCreate;
        String userInfo;
        Args.notNull(httpRoute, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        HttpRequest original = httpRequestWrapper.getOriginal();
        HttpHost httpHost = null;
        if (original instanceof HttpUriRequest) {
            uriCreate = ((HttpUriRequest) original).getURI();
        } else {
            String uri = original.getRequestLine().getUri();
            try {
                uriCreate = URI.create(uri);
            } catch (IllegalArgumentException e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Unable to parse '" + uri + "' as a valid URI; request URI and Host header may be inconsistent", e);
                }
                uriCreate = null;
            }
        }
        httpRequestWrapper.setURI(uriCreate);
        rewriteRequestURI(httpRequestWrapper, httpRoute);
        HttpHost httpHost2 = (HttpHost) httpRequestWrapper.getParams().getParameter(ClientPNames.VIRTUAL_HOST);
        if (httpHost2 != null && httpHost2.getPort() == -1) {
            int port = httpRoute.getTargetHost().getPort();
            if (port != -1) {
                httpHost2 = new HttpHost(httpHost2.getHostName(), port, httpHost2.getSchemeName());
            }
            if (this.log.isDebugEnabled()) {
                this.log.debug("Using virtual host" + httpHost2);
            }
        }
        if (httpHost2 != null) {
            httpHost = httpHost2;
        } else if (uriCreate != null && uriCreate.isAbsolute() && uriCreate.getHost() != null) {
            httpHost = new HttpHost(uriCreate.getHost(), uriCreate.getPort(), uriCreate.getScheme());
        }
        if (httpHost == null) {
            httpHost = httpRoute.getTargetHost();
        }
        if (uriCreate != null && (userInfo = uriCreate.getUserInfo()) != null) {
            CredentialsProvider credentialsProvider = httpClientContext.getCredentialsProvider();
            if (credentialsProvider == null) {
                credentialsProvider = new BasicCredentialsProvider();
                httpClientContext.setCredentialsProvider(credentialsProvider);
            }
            credentialsProvider.setCredentials(new AuthScope(httpHost), new UsernamePasswordCredentials(userInfo));
        }
        httpClientContext.setAttribute("http.target_host", httpHost);
        httpClientContext.setAttribute("http.route", httpRoute);
        httpClientContext.setAttribute("http.request", httpRequestWrapper);
        this.httpProcessor.process(httpRequestWrapper, httpClientContext);
        CloseableHttpResponse closeableHttpResponseExecute = this.requestExecutor.execute(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
        try {
            httpClientContext.setAttribute("http.response", closeableHttpResponseExecute);
            this.httpProcessor.process(closeableHttpResponseExecute, httpClientContext);
            return closeableHttpResponseExecute;
        } catch (IOException e2) {
            closeableHttpResponseExecute.close();
            throw e2;
        } catch (RuntimeException e3) {
            closeableHttpResponseExecute.close();
            throw e3;
        } catch (HttpException e4) {
            closeableHttpResponseExecute.close();
            throw e4;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void rewriteRequestURI(org.apache.http.client.methods.HttpRequestWrapper r4, org.apache.http.conn.routing.HttpRoute r5) throws org.apache.http.ProtocolException {
        /*
            r3 = this;
            java.net.URI r0 = r4.getURI()     // Catch: java.net.URISyntaxException -> L36
            if (r0 == 0) goto L35
            org.apache.http.HttpHost r1 = r5.getProxyHost()     // Catch: java.net.URISyntaxException -> L36
            r2 = 1
            if (r1 == 0) goto L22
            boolean r1 = r5.isTunnelled()     // Catch: java.net.URISyntaxException -> L36
            if (r1 != 0) goto L22
            boolean r1 = r0.isAbsolute()     // Catch: java.net.URISyntaxException -> L36
            if (r1 != 0) goto L2e
            org.apache.http.HttpHost r5 = r5.getTargetHost()     // Catch: java.net.URISyntaxException -> L36
            java.net.URI r5 = org.apache.http.client.utils.URIUtils.rewriteURI(r0, r5, r2)     // Catch: java.net.URISyntaxException -> L36
            goto L32
        L22:
            boolean r5 = r0.isAbsolute()     // Catch: java.net.URISyntaxException -> L36
            if (r5 == 0) goto L2e
            r5 = 0
            java.net.URI r5 = org.apache.http.client.utils.URIUtils.rewriteURI(r0, r5, r2)     // Catch: java.net.URISyntaxException -> L36
            goto L32
        L2e:
            java.net.URI r5 = org.apache.http.client.utils.URIUtils.rewriteURI(r0)     // Catch: java.net.URISyntaxException -> L36
        L32:
            r4.setURI(r5)     // Catch: java.net.URISyntaxException -> L36
        L35:
            return
        L36:
            r5 = move-exception
            org.apache.http.ProtocolException r0 = new org.apache.http.ProtocolException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid URI: "
            r1.<init>(r2)
            org.apache.http.RequestLine r4 = r4.getRequestLine()
            java.lang.String r4 = r4.getUri()
            java.lang.StringBuilder r4 = r1.append(r4)
            java.lang.String r4 = r4.toString()
            r0.<init>(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.execchain.ProtocolExec.rewriteRequestURI(org.apache.http.client.methods.HttpRequestWrapper, org.apache.http.conn.routing.HttpRoute):void");
    }
}
