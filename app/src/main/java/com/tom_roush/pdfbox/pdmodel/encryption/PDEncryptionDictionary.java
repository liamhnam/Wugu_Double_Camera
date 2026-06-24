package com.tom_roush.pdfbox.pdmodel.encryption;

import com.tom_roush.pdfbox.cos.COSDictionary;

@Deprecated
public class PDEncryptionDictionary extends PDEncryption {
    public PDEncryptionDictionary() {
    }

    public PDEncryptionDictionary(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }
}
