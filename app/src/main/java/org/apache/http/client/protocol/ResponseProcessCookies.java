package org.apache.http.client.protocol;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.InterfaceC2807SM;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

public class ResponseProcessCookies implements HttpResponseInterceptor {
    private final Log log = LogFactory.getLog(ResponseProcessCookies.class);

    private void processCookies(HeaderIterator headerIterator, CookieSpec cookieSpec, CookieOrigin cookieOrigin, CookieStore cookieStore) {
        while (headerIterator.hasNext()) {
            Header headerNextHeader = headerIterator.nextHeader();
            try {
                for (Cookie cookie : cookieSpec.parse(headerNextHeader, cookieOrigin)) {
                    try {
                        cookieSpec.validate(cookie, cookieOrigin);
                        cookieStore.addCookie(cookie);
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Cookie accepted: \"" + cookie + "\". ");
                        }
                    } catch (MalformedCookieException e) {
                        if (this.log.isWarnEnabled()) {
                            this.log.warn("Cookie rejected: \"" + cookie + "\". " + e.getMessage());
                        }
                    }
                }
            } catch (MalformedCookieException e2) {
                if (this.log.isWarnEnabled()) {
                    this.log.warn("Invalid cookie header: \"" + headerNextHeader + "\". " + e2.getMessage());
                }
            }
        }
    }

    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        Log log;
        String str;
        Args.notNull(httpResponse, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        HttpClientContext httpClientContextAdapt = HttpClientContext.adapt(httpContext);
        CookieSpec cookieSpec = httpClientContextAdapt.getCookieSpec();
        if (cookieSpec == null) {
            log = this.log;
            str = "Cookie spec not specified in HTTP context";
        } else {
            CookieStore cookieStore = httpClientContextAdapt.getCookieStore();
            if (cookieStore == null) {
                log = this.log;
                str = "Cookie store not specified in HTTP context";
            } else {
                CookieOrigin cookieOrigin = httpClientContextAdapt.getCookieOrigin();
                if (cookieOrigin != null) {
                    processCookies(httpResponse.headerIterator(InterfaceC2807SM.SET_COOKIE), cookieSpec, cookieOrigin, cookieStore);
                    if (cookieSpec.getVersion() > 0) {
                        processCookies(httpResponse.headerIterator(InterfaceC2807SM.SET_COOKIE2), cookieSpec, cookieOrigin, cookieStore);
                        return;
                    }
                    return;
                }
                log = this.log;
                str = "Cookie origin not specified in HTTP context";
            }
        }
        log.debug(str);
    }
}
