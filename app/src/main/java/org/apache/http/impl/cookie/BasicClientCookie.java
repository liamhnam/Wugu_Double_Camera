package org.apache.http.impl.cookie;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;

public class BasicClientCookie implements Serializable, Cloneable, ClientCookie, SetCookie {
    private static final long serialVersionUID = -3869795591041535538L;
    private Map<String, String> attribs;
    private String cookieComment;
    private String cookieDomain;
    private Date cookieExpiryDate;
    private String cookiePath;
    private int cookieVersion;
    private boolean isSecure;
    private final String name;
    private String value;

    public BasicClientCookie(String str, String str2) {
        Args.notNull(str, "Name");
        this.name = str;
        this.attribs = new HashMap();
        this.value = str2;
    }

    public Object clone() {
        BasicClientCookie basicClientCookie = (BasicClientCookie) super.clone();
        basicClientCookie.attribs = new HashMap(this.attribs);
        return basicClientCookie;
    }

    @Override
    public boolean containsAttribute(String str) {
        return this.attribs.get(str) != null;
    }

    @Override
    public String getAttribute(String str) {
        return this.attribs.get(str);
    }

    @Override
    public String getComment() {
        return this.cookieComment;
    }

    @Override
    public String getCommentURL() {
        return null;
    }

    @Override
    public String getDomain() {
        return this.cookieDomain;
    }

    @Override
    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPath() {
        return this.cookiePath;
    }

    @Override
    public int[] getPorts() {
        return null;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public int getVersion() {
        return this.cookieVersion;
    }

    @Override
    public boolean isExpired(Date date) {
        Args.notNull(date, "Date");
        Date date2 = this.cookieExpiryDate;
        return date2 != null && date2.getTime() <= date.getTime();
    }

    @Override
    public boolean isPersistent() {
        return this.cookieExpiryDate != null;
    }

    @Override
    public boolean isSecure() {
        return this.isSecure;
    }

    public void setAttribute(String str, String str2) {
        this.attribs.put(str, str2);
    }

    @Override
    public void setComment(String str) {
        this.cookieComment = str;
    }

    @Override
    public void setDomain(String str) {
        this.cookieDomain = str != null ? str.toLowerCase(Locale.ENGLISH) : null;
    }

    @Override
    public void setExpiryDate(Date date) {
        this.cookieExpiryDate = date;
    }

    @Override
    public void setPath(String str) {
        this.cookiePath = str;
    }

    @Override
    public void setSecure(boolean z) {
        this.isSecure = z;
    }

    @Override
    public void setValue(String str) {
        this.value = str;
    }

    @Override
    public void setVersion(int i) {
        this.cookieVersion = i;
    }

    public String toString() {
        return "[version: " + Integer.toString(this.cookieVersion) + "][name: " + this.name + "][value: " + this.value + "][domain: " + this.cookieDomain + "][path: " + this.cookiePath + "][expiry: " + this.cookieExpiryDate + "]";
    }
}
