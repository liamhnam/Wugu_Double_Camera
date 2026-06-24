package org.simpleframework.xml.transform;

import java.net.URL;

class URLTransform implements Transform<URL> {
    URLTransform() {
    }

    @Override
    public URL read(String str) throws Exception {
        return new URL(str);
    }

    @Override
    public String write(URL url) throws Exception {
        return url.toString();
    }
}
