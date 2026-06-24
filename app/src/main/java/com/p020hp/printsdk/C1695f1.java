package com.p020hp.printsdk;

import android.graphics.BitmapFactory;
import com.p020hp.jipp.model.JpegFeaturesSupported;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p037io.ByteStreamsKt;
import kotlin.p037io.CloseableKt;
import kotlin.ranges.RangesKt;

public final class C1695f1 {
    public final boolean m511a(InputStream file, List<String> jpegFeaturesSupported, int i, int i2) throws IOException {
        long j;
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(jpegFeaturesSupported, "jpegFeaturesSupported");
        File tempProgressiveFile = File.createTempFile("progressive", "");
        Intrinsics.checkNotNullExpressionValue(tempProgressiveFile, "tempProgressiveFile");
        FileOutputStream fileOutputStream = new FileOutputStream(tempProgressiveFile);
        try {
            try {
                ByteStreamsKt.copyTo$default(file, fileOutputStream, 0, 2, null);
                CloseableKt.closeFinally(file, null);
                CloseableKt.closeFinally(fileOutputStream, null);
                boolean zContains = jpegFeaturesSupported.contains("progressive");
                boolean zContains2 = jpegFeaturesSupported.contains(JpegFeaturesSupported.cmyk);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(new FileInputStream(tempProgressiveFile), null, options);
                int iCoerceAtLeast = RangesKt.coerceAtLeast(options.outWidth, options.outHeight);
                if (iCoerceAtLeast > i || iCoerceAtLeast > i2 || !Intrinsics.areEqual(options.outMimeType, "image/jpeg")) {
                    return true;
                }
                C1689e1 c1689e1 = new C1689e1(new FileInputStream(tempProgressiveFile));
                if (c1689e1.m509b(2) != -40) {
                    return true;
                }
                do {
                    short sM509b = c1689e1.m509b(2);
                    if (sM509b != -64) {
                        if (sM509b != -39) {
                            if ((!(-64 <= sM509b && sM509b < -48) || sM509b == -60 || sM509b == -56 || sM509b == -52) ? false : true) {
                            }
                        }
                        tempProgressiveFile.delete();
                        return (zContains || sM509b == -64) ? false : true;
                    }
                    if (sM509b != -64) {
                        long jM509b = c1689e1.m509b(2);
                        if (jM509b < 2) {
                            break;
                        }
                        j = jM509b - ((long) 2);
                    } else {
                        c1689e1.skip(7L);
                        c1689e1.m509b(1);
                        return ((short) c1689e1.f1188a[0]) == 4 && !zContains2;
                    }
                } while (j == c1689e1.skip(j));
                return false;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }
}
