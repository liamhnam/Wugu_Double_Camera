package com.tom_roush.pdfbox.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

public class PDFBoxResourceLoader {
    public static FontLoadLevel LOAD_FONTS = FontLoadLevel.MINIMUM;
    private static AssetManager ASSET_MANAGER = null;

    public enum FontLoadLevel {
        FULL,
        MINIMUM,
        NONE
    }

    public static void init(Context context) {
        ASSET_MANAGER = context.getApplicationContext().getAssets();
    }

    public static boolean isReady() {
        return ASSET_MANAGER != null;
    }

    public static InputStream getStream(String str) throws IOException {
        if (ASSET_MANAGER == null) {
            Log.e("PdfBox-Android", "PDFBoxResourceLoader is not initialized, call PDFBoxResourceLoader.init() before use");
        }
        return ASSET_MANAGER.open(str);
    }
}
