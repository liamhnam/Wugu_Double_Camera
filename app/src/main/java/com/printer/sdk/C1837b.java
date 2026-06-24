package com.printer.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.media.ExifInterface;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.faceunity.core.media.video.VideoRecordHelper;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.UByte;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class C1837b implements PrintType {

    private static final String f2155b = System.getenv("EXTERNAL_STORAGE") + "/DCIM/Camera/";

    private static final String f2156c = System.getenv("EXTERNAL_STORAGE") + "/CPreview/";

    private static final String f2157d = System.getenv("EXTERNAL_STORAGE") + "/CPreview/CPrint/";

    public static final String f2154a = System.getenv("EXTERNAL_STORAGE") + "/CPreview/ICC/User/";

    public static int m709a(String str) {
        try {
            String attribute = new ExifInterface(str).getAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKE);
            if (attribute == null) {
                return 0;
            }
            return Integer.parseInt(attribute);
        } catch (Exception unused) {
            return 0;
        }
    }

    private static Bitmap m710a(Bitmap bitmap, int i, int i2, int i3, int i4, ColorMatrix colorMatrix) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-1);
        if (colorMatrix != null) {
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        }
        canvas.drawBitmap(bitmap, (i3 - i) / 2, (i4 - i2) / 2, paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(-1);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        canvas.drawRect(0.0f, 0.0f, i3, i4, paint2);
        return bitmapCreateBitmap;
    }

    public static Bitmap m711a(Bitmap bitmap, int i, String str) {
        if (bitmap == null) {
            return null;
        }
        try {
            if (iccdrv.InitDstICCFile(i, str) <= 0) {
                return null;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = width * height;
            int[] iArr = new int[i2];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            bitmap.recycle();
            byte[] bArr = new byte[i2 * 3];
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = iArr[i4];
                bArr[i3 + 0] = (byte) ((i5 >> 0) & 255);
                bArr[i3 + 1] = (byte) ((i5 >> 8) & 255);
                bArr[i3 + 2] = (byte) ((i5 >> 16) & 255);
                i3 += 3;
            }
            byte[] bArrTransform = iccdrv.Transform(bArr, width, height);
            int i6 = 0;
            for (int i7 = 0; i7 < i2; i7++) {
                iArr[i7] = (bArrTransform[i6 + 0] & UByte.MAX_VALUE) | ((bArrTransform[i6 + 1] & UByte.MAX_VALUE) << 8) | ((bArrTransform[i6 + 2] & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK;
                i6 += 3;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            Log.w("###########", "time=" + (System.currentTimeMillis() - jCurrentTimeMillis));
            return bitmapCreateBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Bitmap m712a(Bitmap bitmap, float[] fArr) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setColorFilter(new ColorMatrixColorFilter(m716a(fArr)));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    private static Bitmap m713a(String str, int i, int i2, boolean z, boolean z2, int i3, float[] fArr, int i4, String str2) {
        int i5;
        ColorMatrix colorMatrixM744c;
        Bitmap bitmapM710a;
        StringBuilder sb;
        Bitmap bitmapCreateBitmap = (str == null || str.length() == 0) ? Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) : BitmapFactory.decodeFile(str);
        int width = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(-1.0f, 1.0f);
        int i6 = z2 ? i + i : i;
        if ((width < height && i6 > i2) || (width > height && i6 < i2)) {
            matrix.postRotate(90.0f);
            height = width;
            width = height;
        }
        float f = i6 / width;
        float f2 = i2 / height;
        if (!z ? f <= f2 : f >= f2) {
            f = f2;
        }
        matrix.postScale(f, f);
        if (z2) {
            matrix.postScale(0.5f, 1.0f);
            i6 /= 2;
        }
        int i7 = i6;
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), matrix, true);
        int width2 = bitmapCreateBitmap2.getWidth();
        int height2 = bitmapCreateBitmap2.getHeight();
        int i8 = width2 > i7 ? (width2 - i7) / 2 : 0;
        int i9 = height2 > i2 ? (height2 - i2) / 2 : 0;
        if (i8 > 0 || i9 > 0) {
            i5 = i7;
            bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap2, i8, i9, i7, i2, (Matrix) null, false);
            width2 = bitmapCreateBitmap2.getWidth();
            height2 = bitmapCreateBitmap2.getHeight();
        } else {
            i5 = i7;
        }
        if (i3 == 0) {
            colorMatrixM744c = null;
        } else {
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 == 3 || i3 == 4) {
                        bitmapM710a = m710a(bitmapCreateBitmap2, width2, height2, i5, i2, null);
                        sb = new StringBuilder();
                    } else if (i3 == 5 && fArr != null && fArr.length == 6) {
                        bitmapM710a = m710a(bitmapCreateBitmap2, width2, height2, i5, i2, m716a(fArr));
                        sb = new StringBuilder();
                    }
                    return m711a(bitmapM710a, i4, sb.append(f2154a).append(str2).toString());
                }
                if (fArr != null && fArr.length == 6) {
                    colorMatrixM744c = m716a(fArr);
                }
                return null;
            }
            colorMatrixM744c = m744c();
        }
        return m710a(bitmapCreateBitmap2, width2, height2, i5, i2, colorMatrixM744c);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap m714a(java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.C1837b.m714a(java.lang.String, java.lang.String):android.graphics.Bitmap");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap m715a(java.lang.String[] r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.C1837b.m715a(java.lang.String[], java.lang.String):android.graphics.Bitmap");
    }

    private static ColorMatrix m716a(float[] fArr) {
        ColorMatrix colorMatrix = new ColorMatrix();
        ColorMatrix colorMatrix2 = new ColorMatrix();
        ColorMatrix colorMatrix3 = new ColorMatrix();
        ColorMatrix colorMatrix4 = new ColorMatrix();
        ColorMatrix colorMatrix5 = new ColorMatrix();
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[5];
        colorMatrix5.set(new float[]{f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        colorMatrix2.setSaturation(fArr[3]);
        float f5 = fArr[4];
        colorMatrix3.setScale(f5, f5, f5, 1.0f);
        colorMatrix4.set(new float[]{1.0f, 0.0f, 0.0f, 0.0f, f4, 0.0f, 1.0f, 0.0f, 0.0f, f4, 0.0f, 0.0f, 1.0f, 0.0f, f4, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        colorMatrix.postConcat(colorMatrix5);
        colorMatrix.postConcat(colorMatrix2);
        colorMatrix.postConcat(colorMatrix3);
        colorMatrix.postConcat(colorMatrix4);
        return colorMatrix;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.printer.sdk.PrintTask m717a(java.lang.String r13, java.lang.String r14, java.lang.String r15, int r16, boolean r17, boolean r18, java.lang.String r19, int r20, float[] r21, int r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.C1837b.m717a(java.lang.String, java.lang.String, java.lang.String, int, boolean, boolean, java.lang.String, int, float[], int, java.lang.String):com.printer.sdk.PrintTask");
    }

    public static C1836a m718a(String str, boolean z, String str2, String str3, boolean z2) {
        Bitmap bitmapM714a;
        StringBuilder sb;
        if (z) {
            bitmapM714a = m735b(str, str3);
            sb = new StringBuilder("300x600_");
        } else {
            bitmapM714a = m714a(str, str3);
            sb = new StringBuilder("300x300_");
        }
        String string = sb.append(str2).append("x1").toString();
        if (bitmapM714a == null) {
            return null;
        }
        String str4 = System.currentTimeMillis() + "_" + string + ".jpg";
        return z2 ? new C1836a(str4, bitmapM714a) : new C1836a(m721a(bitmapM714a, f2157d + str4), null);
    }

    public static C1836a m719a(String[] strArr, boolean z, String str, String str2, boolean z2) {
        Bitmap bitmapM715a;
        StringBuilder sb;
        if (z) {
            bitmapM715a = m736b(strArr, str2);
            sb = new StringBuilder("300x600_");
        } else {
            bitmapM715a = m715a(strArr, str2);
            sb = new StringBuilder("300x300_");
        }
        String string = sb.append(str).append("x2").toString();
        if (bitmapM715a == null) {
            return null;
        }
        String str3 = System.currentTimeMillis() + "_" + string + ".jpg";
        return z2 ? new C1836a(str3, bitmapM715a) : new C1836a(m721a(bitmapM715a, f2157d + str3), null);
    }

    public static C1841f m720a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int i2 = 1088 + i;
        byte[] bArr = new byte[i2];
        byte[] bArr2 = new byte[i2];
        byte[] bArr3 = new byte[i2];
        byte[] bArr4 = new byte[14];
        byte[] bArr5 = new byte[40];
        byte[] bArr6 = new byte[1024];
        byte[] bArr7 = new byte[10];
        byte[] bArr8 = new byte[i];
        byte[] bArr9 = new byte[i];
        byte[] bArr10 = new byte[i];
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        m728a(bArr4, i2, 1088);
        m741b(bArr5, width, height);
        m727a(bArr6);
        m740b(bArr7);
        m729a(bArr8, bArr9, bArr10, iArr);
        System.arraycopy(bArr4, 0, bArr, 0, 14);
        System.arraycopy(bArr5, 0, bArr, 14, 40);
        System.arraycopy(bArr6, 0, bArr, 54, 1024);
        System.arraycopy(bArr7, 0, bArr, 1078, 10);
        System.arraycopy(bArr8, 0, bArr, 1088, i);
        System.arraycopy(bArr4, 0, bArr2, 0, 14);
        System.arraycopy(bArr5, 0, bArr2, 14, 40);
        System.arraycopy(bArr6, 0, bArr2, 54, 1024);
        System.arraycopy(bArr7, 0, bArr2, 1078, 10);
        System.arraycopy(bArr9, 0, bArr2, 1088, i);
        System.arraycopy(bArr4, 0, bArr3, 0, 14);
        System.arraycopy(bArr5, 0, bArr3, 14, 40);
        System.arraycopy(bArr6, 0, bArr3, 54, 1024);
        System.arraycopy(bArr7, 0, bArr3, 1078, 10);
        System.arraycopy(bArr10, 0, bArr3, 1088, i);
        C1841f c1841f = new C1841f();
        c1841f.m777c(bArr);
        c1841f.m775b(bArr2);
        c1841f.m773a(bArr3);
        return c1841f;
    }

    public static String m721a(Bitmap bitmap, String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return m731a(file.getAbsolutePath(), 0) ? file.getAbsolutePath() : "";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String m722a(String str, Bitmap bitmap) {
        return str + "=" + bitmap.getWidth() + "*" + bitmap.getHeight() + "\r\n";
    }

    public static List<PrintOrder> m723a() {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = new File(f2156c).listFiles();
        if (fileArrListFiles != null) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    String[] strArrSplit = fileArrListFiles[i].getName().split("\\_");
                    if (strArrSplit.length == 4) {
                        List<PrintTask> listM745c = m745c(fileArrListFiles[i].getPath());
                        int copy = 0;
                        for (int i2 = 0; i2 < listM745c.size(); i2++) {
                            copy += listM745c.get(i2).getCopy();
                        }
                        PrintOrder printOrder = new PrintOrder(listM745c, fileArrListFiles[i].getName(), copy);
                        printOrder.setOrderid(Long.parseLong(strArrSplit[0]));
                        printOrder.setB600(strArrSplit[1].equalsIgnoreCase("300x600"));
                        printOrder.setMedia(strArrSplit[2]);
                        printOrder.setMatte(strArrSplit[3].equalsIgnoreCase("M"));
                        arrayList.add(printOrder);
                    }
                }
            }
        }
        return arrayList;
    }

    public static void m724a(Canvas canvas, StringBuffer stringBuffer, int i) {
    }

    public static void m725a(PrintOrder printOrder) {
        File file = new File(f2156c);
        File[] fileArrListFiles = file.listFiles();
        if (file.listFiles() == null) {
            return;
        }
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].isDirectory() && fileArrListFiles[i].getName().equalsIgnoreCase(printOrder.getName())) {
                m726a(fileArrListFiles[i]);
            }
        }
    }

    private static void m726a(File file) {
        if (file == null || !file.exists() || !file.isDirectory() || file.listFiles() == null) {
            return;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                file2.delete();
            } else if (file2.isDirectory()) {
                m726a(file2);
            }
        }
        file.delete();
    }

    private static void m727a(byte[] bArr) {
        for (int i = 0; i < bArr.length / 4; i += 4) {
            byte b = (byte) i;
            bArr[i + 0] = b;
            bArr[i + 1] = b;
            bArr[i + 2] = b;
            bArr[i + 3] = 0;
        }
    }

    private static void m728a(byte[] bArr, int i, int i2) {
        bArr[0] = SnmpConstants.GAUGE;
        bArr[1] = 77;
        bArr[2] = (byte) (i >> 0);
        bArr[3] = (byte) (i >> 8);
        bArr[4] = (byte) (i >> 16);
        bArr[5] = (byte) (i >> 24);
        bArr[6] = 0;
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = 0;
        bArr[10] = (byte) (i2 >> 0);
        bArr[11] = (byte) (i2 >> 8);
        bArr[12] = (byte) (i2 >> 16);
        bArr[13] = (byte) (i2 >> 24);
    }

    private static void m729a(byte[] bArr, byte[] bArr2, byte[] bArr3, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            bArr[i] = (byte) Color.red(iArr[i]);
            bArr2[i] = (byte) Color.green(iArr[i]);
            bArr3[i] = (byte) Color.blue(iArr[i]);
        }
    }

    public static boolean m730a(Bitmap bitmap, String str, String str2) {
        File file = new File(f2156c + str + MqttTopic.TOPIC_LEVEL_SEPARATOR + str2);
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return m731a(file.getAbsolutePath(), 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean m731a(String str, int i) {
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKE, "" + i);
            exifInterface.saveAttributes();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static String[] m732a(Context context) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = new File(f2154a).listFiles();
        if (fileArrListFiles != null) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (!fileArrListFiles[i].isDirectory()) {
                    String name = fileArrListFiles[i].getName();
                    if (name.endsWith(".dat") || name.endsWith(".icc") || name.endsWith(".icm")) {
                        arrayList.add(name);
                    }
                }
            }
        }
        Collections.sort(arrayList);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static int m733b(String str, int i) {
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            String attribute = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKE);
            if (attribute == null || attribute.length() == 0) {
                attribute = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
            }
            int i2 = Integer.parseInt(attribute) + i;
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKE, "" + i2);
            exifInterface.saveAttributes();
            return i2;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static Bitmap m734b(Bitmap bitmap, int i, String str) {
        if (bitmap == null) {
            return null;
        }
        try {
            if (iccdrv.InitDstICCFile(i, f2154a + str) <= 0) {
                Log.e("##############", "InitICCFile=err");
                return null;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = width * height;
            int[] iArr = new int[i2];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            byte[] bArr = new byte[i2 * 3];
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = iArr[i4];
                bArr[i3 + 0] = (byte) ((i5 >> 0) & 255);
                bArr[i3 + 1] = (byte) ((i5 >> 8) & 255);
                bArr[i3 + 2] = (byte) ((i5 >> 16) & 255);
                i3 += 3;
            }
            byte[] bArrTransform = iccdrv.Transform(bArr, width, height);
            int i6 = 0;
            for (int i7 = 0; i7 < i2; i7++) {
                iArr[i7] = (bArrTransform[i6 + 0] & UByte.MAX_VALUE) | ((bArrTransform[i6 + 1] & UByte.MAX_VALUE) << 8) | ((bArrTransform[i6 + 2] & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK;
                i6 += 3;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            Log.w("#############", "time=" + (System.currentTimeMillis() - jCurrentTimeMillis));
            return bitmapCreateBitmap;
        } catch (Exception e) {
            Log.e("###########", "InitICCFile=" + e.toString());
            return null;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap m735b(java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.C1837b.m735b(java.lang.String, java.lang.String):android.graphics.Bitmap");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap m736b(java.lang.String[] r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.C1837b.m736b(java.lang.String[], java.lang.String):android.graphics.Bitmap");
    }

    public static C1836a m737b(String[] strArr, boolean z, String str, String str2, boolean z2) {
        Bitmap bitmapM743c;
        StringBuilder sb;
        if (z) {
            bitmapM743c = m746d(strArr, str2);
            sb = new StringBuilder("300x600_");
        } else {
            bitmapM743c = m743c(strArr, str2);
            sb = new StringBuilder("300x300_");
        }
        String string = sb.append(str).append("x4").toString();
        if (bitmapM743c == null) {
            return null;
        }
        String str3 = System.currentTimeMillis() + "_" + string + ".jpg";
        return z2 ? new C1836a(str3, bitmapM743c) : new C1836a(m721a(bitmapM743c, f2157d + str3), null);
    }

    public static void m738b() {
        File file = new File(f2157d);
        if (file.exists() && file.isDirectory() && file.listFiles() != null) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    file2.delete();
                } else if (file2.isDirectory()) {
                    m726a(file2);
                }
            }
            file.delete();
        }
    }

    public static void m739b(String str) {
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return;
        }
        file.delete();
    }

    private static void m740b(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    private static void m741b(byte[] bArr, int i, int i2) {
        bArr[0] = 40;
        bArr[1] = 0;
        bArr[2] = 0;
        bArr[3] = 0;
        bArr[4] = (byte) (i >> 0);
        bArr[5] = (byte) (i >> 8);
        bArr[6] = (byte) (i >> 16);
        bArr[7] = (byte) (i >> 24);
        bArr[8] = (byte) (i2 >> 0);
        bArr[9] = (byte) (i2 >> 8);
        bArr[10] = (byte) (i2 >> 16);
        bArr[11] = (byte) (i2 >> 24);
        bArr[12] = 1;
        bArr[13] = 0;
        bArr[14] = 8;
        bArr[15] = 0;
        bArr[16] = 0;
        bArr[17] = 0;
        bArr[18] = 0;
        bArr[19] = 0;
        bArr[20] = 0;
        bArr[21] = 0;
        bArr[22] = 0;
        bArr[23] = 0;
        bArr[24] = 0;
        bArr[25] = 0;
        bArr[26] = 0;
        bArr[27] = 0;
        bArr[28] = 0;
        bArr[29] = 0;
        bArr[30] = 0;
        bArr[31] = 0;
        bArr[32] = 0;
        bArr[33] = 0;
        bArr[34] = 0;
        bArr[35] = 0;
        bArr[36] = 0;
        bArr[37] = 0;
        bArr[38] = 0;
        bArr[39] = 0;
    }

    public static boolean m742b(Context context) {
        try {
            String[] list = context.getAssets().list("User");
            if (list == null) {
                return true;
            }
            for (int i = 0; i < list.length; i++) {
                if (!new File(list[i]).isDirectory() && ((list[i].endsWith(".dat") || list[i].endsWith(".icc") || list[i].endsWith(".icm")) && !C1839d.m750a(context, "User" + File.separator + list[i], f2154a + list[i]))) {
                    return false;
                }
            }
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private static Bitmap m743c(String[] strArr, String str) {
        str.hashCode();
        if (!str.equals("6x8")) {
            return null;
        }
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(strArr[0]);
        Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(strArr[1]);
        Bitmap bitmapDecodeFile3 = BitmapFactory.decodeFile(strArr[2]);
        Bitmap bitmapDecodeFile4 = BitmapFactory.decodeFile(strArr[3]);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(VideoRecordHelper.MAX_VIDEO_LENGTH, 2436, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        if (bitmapDecodeFile != null) {
            canvas.drawBitmap(bitmapDecodeFile, 86, 42, (Paint) null);
        }
        if (bitmapDecodeFile2 != null) {
            canvas.drawBitmap(bitmapDecodeFile2, 86, bitmapDecodeFile.getHeight() + 42 + 48, (Paint) null);
        }
        if (bitmapDecodeFile3 != null) {
            canvas.drawBitmap(bitmapDecodeFile3, 86, bitmapDecodeFile.getHeight() + 42 + 48 + bitmapDecodeFile2.getHeight() + 48, (Paint) null);
        }
        if (bitmapDecodeFile4 != null) {
            canvas.drawBitmap(bitmapDecodeFile4, 86, bitmapDecodeFile.getHeight() + 42 + 48 + bitmapDecodeFile2.getHeight() + 48 + bitmapDecodeFile3.getHeight() + 48, (Paint) null);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        canvas.drawRect(0.0f, 0.0f, VideoRecordHelper.MAX_VIDEO_LENGTH, 2436, paint);
        return bitmapCreateBitmap;
    }

    private static ColorMatrix m744c() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{0.9f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.9f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.9f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        return colorMatrix;
    }

    private static List<PrintTask> m745c(String str) {
        String name;
        String[] strArrSplit;
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (!fileArrListFiles[i].isDirectory() && (strArrSplit = (name = fileArrListFiles[i].getName()).split("\\_")) != null && strArrSplit.length == 7) {
                    PrintTask printTask = new PrintTask(str + MqttTopic.TOPIC_LEVEL_SEPARATOR, name);
                    int iM709a = m709a(fileArrListFiles[i].getAbsolutePath());
                    long j = Long.parseLong(strArrSplit[0]);
                    int i2 = Integer.parseInt(strArrSplit[1]);
                    printTask.setTaskid(j);
                    printTask.setCopy(i2);
                    printTask.setPast(iM709a);
                    if (iM709a >= i2) {
                        printTask.setStus(2);
                    }
                    arrayList.add(printTask);
                }
            }
        }
        return arrayList;
    }

    private static Bitmap m746d(String[] strArr, String str) {
        str.hashCode();
        if (!str.equals("6x8")) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(strArr[0]);
        Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(strArr[1]);
        Bitmap bitmapDecodeFile3 = BitmapFactory.decodeFile(strArr[2]);
        Bitmap bitmapDecodeFile4 = BitmapFactory.decodeFile(strArr[3]);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(VideoRecordHelper.MAX_VIDEO_LENGTH, 4872, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        if (bitmapDecodeFile != null) {
            canvas.drawBitmap(bitmapDecodeFile, 86, 84, (Paint) null);
        }
        if (bitmapDecodeFile2 != null) {
            canvas.drawBitmap(bitmapDecodeFile2, 86, bitmapDecodeFile.getHeight() + 84 + 96, (Paint) null);
        }
        if (bitmapDecodeFile3 != null) {
            canvas.drawBitmap(bitmapDecodeFile3, 86, bitmapDecodeFile.getHeight() + 84 + 96 + bitmapDecodeFile2.getHeight() + 96, (Paint) null);
        }
        if (bitmapDecodeFile4 != null) {
            canvas.drawBitmap(bitmapDecodeFile4, 86, bitmapDecodeFile.getHeight() + 84 + 96 + bitmapDecodeFile2.getHeight() + 96 + bitmapDecodeFile3.getHeight() + 96, (Paint) null);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        canvas.drawRect(0.0f, 0.0f, VideoRecordHelper.MAX_VIDEO_LENGTH, 4872, paint);
        stringBuffer.append(m722a("扩展", bitmapCreateBitmap));
        m724a(canvas, stringBuffer, VideoRecordHelper.MAX_VIDEO_LENGTH);
        return bitmapCreateBitmap;
    }
}
