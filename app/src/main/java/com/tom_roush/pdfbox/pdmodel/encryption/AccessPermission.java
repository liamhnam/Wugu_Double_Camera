package com.tom_roush.pdfbox.pdmodel.encryption;

import kotlin.UByte;

public class AccessPermission {
    private static final int ASSEMBLE_DOCUMENT_BIT = 11;
    private static final int DEFAULT_PERMISSIONS = -4;
    private static final int DEGRADED_PRINT_BIT = 12;
    private static final int EXTRACT_BIT = 5;
    private static final int EXTRACT_FOR_ACCESSIBILITY_BIT = 10;
    private static final int FILL_IN_FORM_BIT = 9;
    private static final int MODIFICATION_BIT = 4;
    private static final int MODIFY_ANNOTATIONS_BIT = 6;
    private static final int PRINT_BIT = 3;
    private int bytes;
    private boolean readOnly;

    public AccessPermission() {
        this.readOnly = false;
        this.bytes = -4;
    }

    public AccessPermission(byte[] bArr) {
        this.readOnly = false;
        this.bytes = (bArr[3] & UByte.MAX_VALUE) | ((((((0 | (bArr[0] & UByte.MAX_VALUE)) << 8) | (bArr[1] & UByte.MAX_VALUE)) << 8) | (bArr[2] & UByte.MAX_VALUE)) << 8);
    }

    public AccessPermission(int i) {
        this.readOnly = false;
        this.bytes = i;
    }

    private boolean isPermissionBitOn(int i) {
        return ((1 << (i - 1)) & this.bytes) != 0;
    }

    private boolean setPermissionBit(int i, boolean z) {
        int i2 = this.bytes;
        int i3 = z ? (1 << (i - 1)) | i2 : ((1 << (i - 1)) ^ (-1)) & i2;
        this.bytes = i3;
        return ((1 << (i - 1)) & i3) != 0;
    }

    public boolean isOwnerPermission() {
        return canAssembleDocument() && canExtractContent() && canExtractForAccessibility() && canFillInForm() && canModify() && canModifyAnnotations() && canPrint() && canPrintDegraded();
    }

    public static AccessPermission getOwnerAccessPermission() {
        AccessPermission accessPermission = new AccessPermission();
        accessPermission.setCanAssembleDocument(true);
        accessPermission.setCanExtractContent(true);
        accessPermission.setCanExtractForAccessibility(true);
        accessPermission.setCanFillInForm(true);
        accessPermission.setCanModify(true);
        accessPermission.setCanModifyAnnotations(true);
        accessPermission.setCanPrint(true);
        accessPermission.setCanPrintDegraded(true);
        return accessPermission;
    }

    public int getPermissionBytesForPublicKey() {
        setPermissionBit(1, true);
        setPermissionBit(7, false);
        setPermissionBit(8, false);
        for (int i = 13; i <= 32; i++) {
            setPermissionBit(i, false);
        }
        return this.bytes;
    }

    public int getPermissionBytes() {
        return this.bytes;
    }

    public boolean canPrint() {
        return isPermissionBitOn(3);
    }

    public void setCanPrint(boolean z) {
        if (this.readOnly) {
            return;
        }
        setPermissionBit(3, z);
    }

    public boolean canModify() {
        return isPermissionBitOn(4);
    }

    public void setCanModify(boolean z) {
        if (this.readOnly) {
            return;
        }
        setPermissionBit(4, z);
    }

    public boolean canExtractContent() {
        return isPermissionBitOn(5);
    }

    public void setCanExtractContent(boolean z) {
        if (this.readOnly) {
            return;
        }
        setPermissionBit(5, z);
    }

    public boolean canModifyAnnotations() {
        return isPermissionBitOn(6);
    }

    public void setCanModifyAnnotations(boolean z) {
        if (this.readOnly) {
            return;
        }
        setPermissionBit(6, z);
    }

    public boolean canFillInForm() {
        return isPermissionBitOn(9);
    }

    public void setCanFillInForm(boolean z) {
        if (this.readOnly) {
            return;
        }
        setPermissionBit(9, z);
    }

    public boolean canExtractForAccessibility() {
        return isPermissionBitOn(10);
    }

    public void setCanExtractForAccessibility(boolean z) {
        if (this.readOnly) {
            return;
        }
        setPermissionBit(10, z);
    }

    public boolean canAssembleDocument() {
        return isPermissionBitOn(11);
    }

    public void setCanAssembleDocument(boolean z) {
        if (this.readOnly) {
            return;
        }
        setPermissionBit(11, z);
    }

    public boolean canPrintDegraded() {
        return isPermissionBitOn(12);
    }

    public void setCanPrintDegraded(boolean z) {
        if (this.readOnly) {
            return;
        }
        setPermissionBit(12, z);
    }

    public void setReadOnly() {
        this.readOnly = true;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    protected boolean hasAnyRevision3PermissionSet() {
        if (canFillInForm() || canExtractForAccessibility() || canAssembleDocument()) {
            return true;
        }
        return canPrintDegraded();
    }
}
