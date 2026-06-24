package org.apache.http.client.protocol;

import org.apache.commons.logging.Log;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthState;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Deprecated
public class RequestProxyAuthentication extends RequestAuthenticationBase {
    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Log log;
        String str;
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        if (httpRequest.containsHeader("Proxy-Authorization")) {
            return;
        }
        HttpRoutedConnection httpRoutedConnection = (HttpRoutedConnection) httpContext.getAttribute("http.connection");
        if (httpRoutedConnection == null) {
            log = this.log;
            str = "HTTP connection not set in the context";
        } else {
            if (httpRoutedConnection.getRoute().isTunnelled()) {
                return;
            }
            AuthState authState = (AuthState) httpContext.getAttribute("http.auth.proxy-scope");
            if (authState != null) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Proxy auth state: " + authState.getState());
                }
                process(authState, httpRequest, httpContext);
                return;
            }
            log = this.log;
            str = "Proxy auth state not set in the context";
        }
        log.debug(str);
    }
}
