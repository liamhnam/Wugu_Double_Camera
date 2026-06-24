package com.tom_roush.pdfbox.pdmodel.encryption;

public final class StandardProtectionPolicy extends ProtectionPolicy {
    private String ownerPassword;
    private AccessPermission permissions;
    private String userPassword;

    public StandardProtectionPolicy(String str, String str2, AccessPermission accessPermission) {
        this.ownerPassword = str;
        this.userPassword = str2;
        this.permissions = accessPermission;
    }

    public AccessPermission getPermissions() {
        return this.permissions;
    }

    public void setPermissions(AccessPermission accessPermission) {
        this.permissions = accessPermission;
    }

    public String getOwnerPassword() {
        return this.ownerPassword;
    }

    public void setOwnerPassword(String str) {
        this.ownerPassword = str;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String str) {
        this.userPassword = str;
    }
}
