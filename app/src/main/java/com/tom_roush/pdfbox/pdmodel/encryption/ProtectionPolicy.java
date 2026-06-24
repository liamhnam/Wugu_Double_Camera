package com.tom_roush.pdfbox.pdmodel.encryption;

public abstract class ProtectionPolicy {
    private static final int DEFAULT_KEY_LENGTH = 40;
    private int encryptionKeyLength = 40;

    public void setEncryptionKeyLength(int i) {
        if (i != 40 && i != 128 && i != 256) {
            throw new IllegalArgumentException("Invalid key length '" + i + "' value must be 40, 128 or 256!");
        }
        this.encryptionKeyLength = i;
    }

    public int getEncryptionKeyLength() {
        return this.encryptionKeyLength;
    }
}
