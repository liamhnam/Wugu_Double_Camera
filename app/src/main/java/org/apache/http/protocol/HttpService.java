package org.apache.http.protocol;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.ProtocolException;
import org.apache.http.UnsupportedHttpVersionException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
import org.apache.http.util.EncodingUtils;

public class HttpService {
    private volatile ConnectionReuseStrategy connStrategy;
    private volatile HttpExpectationVerifier expectationVerifier;
    private volatile HttpRequestHandlerMapper handlerMapper;
    private volatile HttpParams params;
    private volatile HttpProcessor processor;
    private volatile HttpResponseFactory responseFactory;

    @Deprecated
    private static class HttpRequestHandlerResolverAdapter implements HttpRequestHandlerMapper {
        private final HttpRequestHandlerResolver resolver;

        public HttpRequestHandlerResolverAdapter(HttpRequestHandlerResolver httpRequestHandlerResolver) {
            this.resolver = httpRequestHandlerResolver;
        }

        @Override
        public HttpRequestHandler lookup(HttpRequest httpRequest) {
            return this.resolver.lookup(httpRequest.getRequestLine().getUri());
        }
    }

    @Deprecated
    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory) {
        this.params = null;
        this.processor = null;
        this.handlerMapper = null;
        this.connStrategy = null;
        this.responseFactory = null;
        this.expectationVerifier = null;
        setHttpProcessor(httpProcessor);
        setConnReuseStrategy(connectionReuseStrategy);
        setResponseFactory(httpResponseFactory);
    }

    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory, HttpRequestHandlerMapper httpRequestHandlerMapper) {
        this(httpProcessor, connectionReuseStrategy, httpResponseFactory, httpRequestHandlerMapper, (HttpExpectationVerifier) null);
    }

    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory, HttpRequestHandlerMapper httpRequestHandlerMapper, HttpExpectationVerifier httpExpectationVerifier) {
        this.params = null;
        this.processor = null;
        this.handlerMapper = null;
        this.connStrategy = null;
        this.responseFactory = null;
        this.expectationVerifier = null;
        this.processor = (HttpProcessor) Args.notNull(httpProcessor, "HTTP processor");
        this.connStrategy = connectionReuseStrategy == null ? DefaultConnectionReuseStrategy.INSTANCE : connectionReuseStrategy;
        this.responseFactory = httpResponseFactory == null ? DefaultHttpResponseFactory.INSTANCE : httpResponseFactory;
        this.handlerMapper = httpRequestHandlerMapper;
        this.expectationVerifier = httpExpectationVerifier;
    }

    @Deprecated
    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory, HttpRequestHandlerResolver httpRequestHandlerResolver, HttpParams httpParams) {
        this(httpProcessor, connectionReuseStrategy, httpResponseFactory, new HttpRequestHandlerResolverAdapter(httpRequestHandlerResolver), (HttpExpectationVerifier) null);
        this.params = httpParams;
    }

    @Deprecated
    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory, HttpRequestHandlerResolver httpRequestHandlerResolver, HttpExpectationVerifier httpExpectationVerifier, HttpParams httpParams) {
        this(httpProcessor, connectionReuseStrategy, httpResponseFactory, new HttpRequestHandlerResolverAdapter(httpRequestHandlerResolver), httpExpectationVerifier);
        this.params = httpParams;
    }

    public HttpService(HttpProcessor httpProcessor, HttpRequestHandlerMapper httpRequestHandlerMapper) {
        this(httpProcessor, (ConnectionReuseStrategy) null, (HttpResponseFactory) null, httpRequestHandlerMapper, (HttpExpectationVerifier) null);
    }

    protected void doService(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        HttpRequestHandler httpRequestHandlerLookup = this.handlerMapper != null ? this.handlerMapper.lookup(httpRequest) : null;
        if (httpRequestHandlerLookup != null) {
            httpRequestHandlerLookup.handle(httpRequest, httpResponse, httpContext);
        } else {
            httpResponse.setStatusCode(501);
        }
    }

    @Deprecated
    public HttpParams getParams() {
        return this.params;
    }

    protected void handleException(HttpException httpException, HttpResponse httpResponse) {
        httpResponse.setStatusCode(httpException instanceof MethodNotSupportedException ? 501 : httpException instanceof UnsupportedHttpVersionException ? 505 : httpException instanceof ProtocolException ? 400 : 500);
        String message = httpException.getMessage();
        if (message == null) {
            message = httpException.toString();
        }
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(EncodingUtils.getAsciiBytes(message));
        byteArrayEntity.setContentType("text/plain; charset=US-ASCII");
        httpResponse.setEntity(byteArrayEntity);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleRequest(org.apache.http.HttpServerConnection r8, org.apache.http.protocol.HttpContext r9) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "http.connection"
            r9.setAttribute(r0, r8)
            r0 = 500(0x1f4, float:7.0E-43)
            org.apache.http.HttpRequest r1 = r8.receiveRequestHeader()     // Catch: org.apache.http.HttpException -> L7a
            boolean r2 = r1 instanceof org.apache.http.HttpEntityEnclosingRequest     // Catch: org.apache.http.HttpException -> L7a
            r3 = 200(0xc8, float:2.8E-43)
            r4 = 0
            if (r2 == 0) goto L55
            r2 = r1
            org.apache.http.HttpEntityEnclosingRequest r2 = (org.apache.http.HttpEntityEnclosingRequest) r2     // Catch: org.apache.http.HttpException -> L7a
            boolean r2 = r2.expectContinue()     // Catch: org.apache.http.HttpException -> L7a
            if (r2 == 0) goto L4c
            org.apache.http.HttpResponseFactory r2 = r7.responseFactory     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.HttpVersion r5 = org.apache.http.HttpVersion.HTTP_1_1     // Catch: org.apache.http.HttpException -> L7a
            r6 = 100
            org.apache.http.HttpResponse r2 = r2.newHttpResponse(r5, r6, r9)     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.protocol.HttpExpectationVerifier r5 = r7.expectationVerifier     // Catch: org.apache.http.HttpException -> L7a
            if (r5 == 0) goto L3c
            org.apache.http.protocol.HttpExpectationVerifier r5 = r7.expectationVerifier     // Catch: org.apache.http.HttpException -> L2f
            r5.verify(r1, r2, r9)     // Catch: org.apache.http.HttpException -> L2f
            goto L3c
        L2f:
            r2 = move-exception
            org.apache.http.HttpResponseFactory r5 = r7.responseFactory     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.HttpVersion r6 = org.apache.http.HttpVersion.HTTP_1_0     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.HttpResponse r5 = r5.newHttpResponse(r6, r0, r9)     // Catch: org.apache.http.HttpException -> L7a
            r7.handleException(r2, r5)     // Catch: org.apache.http.HttpException -> L7a
            r2 = r5
        L3c:
            org.apache.http.StatusLine r5 = r2.getStatusLine()     // Catch: org.apache.http.HttpException -> L7a
            int r5 = r5.getStatusCode()     // Catch: org.apache.http.HttpException -> L7a
            if (r5 >= r3) goto L50
            r8.sendResponseHeader(r2)     // Catch: org.apache.http.HttpException -> L7a
            r8.flush()     // Catch: org.apache.http.HttpException -> L7a
        L4c:
            r2 = r1
            org.apache.http.HttpEntityEnclosingRequest r2 = (org.apache.http.HttpEntityEnclosingRequest) r2     // Catch: org.apache.http.HttpException -> L7a
            goto L52
        L50:
            r4 = r2
            goto L55
        L52:
            r8.receiveRequestEntity(r2)     // Catch: org.apache.http.HttpException -> L7a
        L55:
            java.lang.String r2 = "http.request"
            r9.setAttribute(r2, r1)     // Catch: org.apache.http.HttpException -> L7a
            if (r4 != 0) goto L6c
            org.apache.http.HttpResponseFactory r2 = r7.responseFactory     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.HttpVersion r4 = org.apache.http.HttpVersion.HTTP_1_1     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.HttpResponse r4 = r2.newHttpResponse(r4, r3, r9)     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.protocol.HttpProcessor r2 = r7.processor     // Catch: org.apache.http.HttpException -> L7a
            r2.process(r1, r9)     // Catch: org.apache.http.HttpException -> L7a
            r7.doService(r1, r4, r9)     // Catch: org.apache.http.HttpException -> L7a
        L6c:
            boolean r2 = r1 instanceof org.apache.http.HttpEntityEnclosingRequest     // Catch: org.apache.http.HttpException -> L7a
            if (r2 == 0) goto L86
            org.apache.http.HttpEntityEnclosingRequest r1 = (org.apache.http.HttpEntityEnclosingRequest) r1     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.HttpEntity r1 = r1.getEntity()     // Catch: org.apache.http.HttpException -> L7a
            org.apache.http.util.EntityUtils.consume(r1)     // Catch: org.apache.http.HttpException -> L7a
            goto L86
        L7a:
            r1 = move-exception
            org.apache.http.HttpResponseFactory r2 = r7.responseFactory
            org.apache.http.HttpVersion r3 = org.apache.http.HttpVersion.HTTP_1_0
            org.apache.http.HttpResponse r4 = r2.newHttpResponse(r3, r0, r9)
            r7.handleException(r1, r4)
        L86:
            java.lang.String r0 = "http.response"
            r9.setAttribute(r0, r4)
            org.apache.http.protocol.HttpProcessor r0 = r7.processor
            r0.process(r4, r9)
            r8.sendResponseHeader(r4)
            r8.sendResponseEntity(r4)
            r8.flush()
            org.apache.http.ConnectionReuseStrategy r0 = r7.connStrategy
            boolean r9 = r0.keepAlive(r4, r9)
            if (r9 != 0) goto La4
            r8.close()
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.protocol.HttpService.handleRequest(org.apache.http.HttpServerConnection, org.apache.http.protocol.HttpContext):void");
    }

    @Deprecated
    public void setConnReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        this.connStrategy = connectionReuseStrategy;
    }

    @Deprecated
    public void setExpectationVerifier(HttpExpectationVerifier httpExpectationVerifier) {
        this.expectationVerifier = httpExpectationVerifier;
    }

    @Deprecated
    public void setHandlerResolver(HttpRequestHandlerResolver httpRequestHandlerResolver) {
        this.handlerMapper = new HttpRequestHandlerResolverAdapter(httpRequestHandlerResolver);
    }

    @Deprecated
    public void setHttpProcessor(HttpProcessor httpProcessor) {
        Args.notNull(httpProcessor, "HTTP processor");
        this.processor = httpProcessor;
    }

    @Deprecated
    public void setParams(HttpParams httpParams) {
        this.params = httpParams;
    }

    @Deprecated
    public void setResponseFactory(HttpResponseFactory httpResponseFactory) {
        Args.notNull(httpResponseFactory, "Response factory");
        this.responseFactory = httpResponseFactory;
    }
}
