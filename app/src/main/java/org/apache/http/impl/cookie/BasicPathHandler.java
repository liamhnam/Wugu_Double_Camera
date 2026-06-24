package org.apache.http.impl.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.InterfaceC2807SM;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;
import org.apache.http.util.TextUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class BasicPathHandler implements CookieAttributeHandler {
    @Override
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, InterfaceC2807SM.COOKIE);
        Args.notNull(cookieOrigin, "Cookie origin");
        String path = cookieOrigin.getPath();
        String path2 = cookie.getPath();
        if (path2 == null) {
            path2 = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        if (path2.length() > 1 && path2.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            path2 = path2.substring(0, path2.length() - 1);
        }
        boolean zStartsWith = path.startsWith(path2);
        return (!zStartsWith || path.length() == path2.length() || path2.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) ? zStartsWith : path.charAt(path2.length()) == '/';
    }

    @Override
    public void parse(SetCookie setCookie, String str) {
        Args.notNull(setCookie, InterfaceC2807SM.COOKIE);
        if (TextUtils.isBlank(str)) {
            str = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        setCookie.setPath(str);
    }

    @Override
    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws CookieRestrictionViolationException {
        if (!match(cookie, cookieOrigin)) {
            throw new CookieRestrictionViolationException("Illegal path attribute \"" + cookie.getPath() + "\". Path of origin: \"" + cookieOrigin.getPath() + "\"");
        }
    }
}
