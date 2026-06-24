package com.tom_roush.pdfbox.pdmodel.encryption;

import java.security.cert.X509Certificate;

public class PublicKeyRecipient {
    private AccessPermission permission;
    private X509Certificate x509;

    public X509Certificate getX509() {
        return this.x509;
    }

    public void setX509(X509Certificate x509Certificate) {
        this.x509 = x509Certificate;
    }

    public AccessPermission getPermission() {
        return this.permission;
    }

    public void setPermission(AccessPermission accessPermission) {
        this.permission = accessPermission;
    }
}
