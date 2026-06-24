package com.faceunity.core.program.core;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

public abstract class Extensions {
    public static byte[] getBytes(InputStream inputStream) {
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            inputStream.close();
            return bArr;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static byte[] getBytes(AssetManager assetManager, String str) {
        try {
            return getBytes(assetManager.open(str));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static String readTextFileFromResource(Context context, int i) {
        return new String(getBytes(context.getResources().openRawResource(i)));
    }
}
