package org.apache.http.impl.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.InterfaceC2807SM;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;

public class BasicSecureHandler extends AbstractCookieAttributeHandler {
    @Override
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, InterfaceC2807SM.COOKIE);
        Args.notNull(cookieOrigin, "Cookie origin");
        return !cookie.isSecure() || cookieOrigin.isSecure();
    }

    @Override
    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, InterfaceC2807SM.COOKIE);
        setCookie.setSecure(true);
    }
}
