package com.printer.sdk;

import com.p020hp.jipp.model.JpegFeaturesSupported;

public class iccdrv {
    static {
        try {
            System.loadLibrary(JpegFeaturesSupported.icc);
        } catch (Exception unused) {
        }
    }

    public static native int InitDstICCFile(int i, String str);

    public static native byte[] Transform(byte[] bArr, int i, int i2);
}
