package org.apache.http.protocol;

import org.apache.http.HttpRequest;
import org.apache.http.util.Args;
import org.apache.log4j.spi.LocationInfo;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class UriHttpRequestHandlerMapper implements HttpRequestHandlerMapper {
    private final UriPatternMatcher<HttpRequestHandler> matcher;

    public UriHttpRequestHandlerMapper() {
        this(new UriPatternMatcher());
    }

    protected UriHttpRequestHandlerMapper(UriPatternMatcher<HttpRequestHandler> uriPatternMatcher) {
        this.matcher = (UriPatternMatcher) Args.notNull(uriPatternMatcher, "Pattern matcher");
    }

    protected String getRequestPath(HttpRequest httpRequest) {
        String uri = httpRequest.getRequestLine().getUri();
        int iIndexOf = uri.indexOf(LocationInfo.f2800NA);
        return (iIndexOf == -1 && (iIndexOf = uri.indexOf(MqttTopic.MULTI_LEVEL_WILDCARD)) == -1) ? uri : uri.substring(0, iIndexOf);
    }

    @Override
    public HttpRequestHandler lookup(HttpRequest httpRequest) {
        Args.notNull(httpRequest, "HTTP request");
        return this.matcher.lookup(getRequestPath(httpRequest));
    }

    public void register(String str, HttpRequestHandler httpRequestHandler) {
        Args.notNull(str, "Pattern");
        Args.notNull(httpRequestHandler, "Handler");
        this.matcher.register(str, httpRequestHandler);
    }

    public void unregister(String str) {
        this.matcher.unregister(str);
    }
}
