package org.apache.http.impl.cookie;

import java.util.Collections;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;

public class IgnoreSpec extends CookieSpecBase {
    @Override
    public List<Header> formatCookies(List<Cookie> list) {
        return Collections.emptyList();
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Header getVersionHeader() {
        return null;
    }

    @Override
    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) {
        return Collections.emptyList();
    }
}
