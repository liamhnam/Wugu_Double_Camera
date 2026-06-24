package org.apache.http.impl.cookie;

import org.apache.http.cookie.InterfaceC2807SM;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;

public class BasicCommentHandler extends AbstractCookieAttributeHandler {
    @Override
    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, InterfaceC2807SM.COOKIE);
        setCookie.setComment(str);
    }
}
