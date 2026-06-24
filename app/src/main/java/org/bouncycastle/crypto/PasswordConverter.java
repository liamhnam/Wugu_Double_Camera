package org.bouncycastle.crypto;

import org.apache.http.protocol.HTTP;

public enum PasswordConverter implements CharToByteConverter {
    ASCII {
        @Override
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS5PasswordToBytes(cArr);
        }

        @Override
        public String getType() {
            return HTTP.ASCII;
        }
    },
    UTF8 {
        @Override
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(cArr);
        }

        @Override
        public String getType() {
            return "UTF8";
        }
    },
    PKCS12 {
        @Override
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
        }

        @Override
        public String getType() {
            return "PKCS12";
        }
    }
}
