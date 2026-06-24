package org.apache.http.impl.cookie;

import java.io.Serializable;
import java.util.Date;
import org.apache.http.cookie.SetCookie2;

public class BasicClientCookie2 extends BasicClientCookie implements Serializable, SetCookie2 {
    private static final long serialVersionUID = -7744598295706617057L;
    private String commentURL;
    private boolean discard;
    private int[] ports;

    public BasicClientCookie2(String str, String str2) {
        super(str, str2);
    }

    @Override
    public Object clone() {
        BasicClientCookie2 basicClientCookie2 = (BasicClientCookie2) super.clone();
        int[] iArr = this.ports;
        if (iArr != null) {
            basicClientCookie2.ports = (int[]) iArr.clone();
        }
        return basicClientCookie2;
    }

    @Override
    public String getCommentURL() {
        return this.commentURL;
    }

    @Override
    public int[] getPorts() {
        return this.ports;
    }

    @Override
    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    @Override
    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    @Override
    public void setCommentURL(String str) {
        this.commentURL = str;
    }

    @Override
    public void setDiscard(boolean z) {
        this.discard = z;
    }

    @Override
    public void setPorts(int[] iArr) {
        this.ports = iArr;
    }
}
