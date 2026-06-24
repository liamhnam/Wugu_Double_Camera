package org.apache.http.cookie;

import java.io.Serializable;
import java.util.Comparator;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class CookieIdentityComparator implements Serializable, Comparator<Cookie> {
    private static final long serialVersionUID = 4466565437490631532L;

    @Override
    public int compare(Cookie cookie, Cookie cookie2) {
        int iCompareTo = cookie.getName().compareTo(cookie2.getName());
        if (iCompareTo == 0) {
            String domain = cookie.getDomain();
            if (domain == null) {
                domain = "";
            } else if (domain.indexOf(46) == -1) {
                domain = domain + ".local";
            }
            String domain2 = cookie2.getDomain();
            iCompareTo = domain.compareToIgnoreCase(domain2 != null ? domain2.indexOf(46) == -1 ? domain2 + ".local" : domain2 : "");
        }
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        String path = cookie.getPath();
        String str = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        if (path == null) {
            path = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        String path2 = cookie2.getPath();
        if (path2 != null) {
            str = path2;
        }
        return path.compareTo(str);
    }
}
