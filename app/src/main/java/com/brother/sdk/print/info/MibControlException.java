package com.brother.sdk.print.info;

import java.io.IOException;
import java.net.InetAddress;

public class MibControlException extends IOException {
    private InetAddress inetAddress;
    private String oidString;

    public MibControlException() {
    }

    public MibControlException(String str, Throwable th) {
        super(str, th);
    }

    public MibControlException(String str) {
        super(str);
    }

    public MibControlException(Throwable th) {
        super(th);
    }

    public MibControlException setOidString(String str) {
        this.oidString = str;
        return this;
    }

    public InetAddress getInetAddress() {
        return this.inetAddress;
    }

    public MibControlException setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
        return this;
    }

    @Override
    public String getMessage() {
        if (this.oidString != null) {
            return super.getMessage() + " OID[" + this.oidString + "]";
        }
        return super.getMessage();
    }
}
