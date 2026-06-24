package org.apache.http.impl.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

public class LaxRedirectStrategy extends DefaultRedirectStrategy {
    private static final String[] REDIRECT_METHODS = {HttpGet.METHOD_NAME, HttpPost.METHOD_NAME, "HEAD"};

    @Override
    protected boolean isRedirectable(String str) {
        for (String str2 : REDIRECT_METHODS) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
