package com.wugu.doublecamera.utils;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class FileUtil {
    public static String getExtension(File file) {
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(".");
        return iLastIndexOf == -1 ? name : name.substring(iLastIndexOf + 1);
    }

    public static String getExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        return iLastIndexOf == -1 ? str : str.substring(iLastIndexOf + 1);
    }

    public static File createFile(String str) throws IOException {
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    public static void createFolder(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static List<String> getFileList(String str) {
        File[] fileArrListFiles;
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        if (!file.exists() || file.isFile() || (fileArrListFiles = file.listFiles(new FileUtil$$ExternalSyntheticLambda0())) == null) {
            return arrayList;
        }
        for (File file2 : fileArrListFiles) {
            arrayList.add(file2.getName());
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static void deleteFileFolder(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles(new FileUtil$$ExternalSyntheticLambda0());
                if (fileArrListFiles == null) {
                    return;
                }
                for (File file2 : fileArrListFiles) {
                    deleteFileFolder(file2.getAbsolutePath());
                }
            }
            file.delete();
        }
    }

    public static boolean deleteFolderAllFiles(String str) {
        File[] fileArrListFiles;
        File file = new File(str);
        boolean z = true;
        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory() && !deleteFolderAllFiles(file2.getAbsolutePath())) {
                    z = false;
                }
                if (!file2.delete()) {
                    z = false;
                }
            }
        }
        return z;
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i = fileInputStream.read(bArr, 0, 1024);
                if (i == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i);
            }
            fileInputStream.close();
            StringBuilder sb = new StringBuilder(new BigInteger(1, messageDigest.digest()).toString(16));
            while (sb.length() < 32) {
                sb.insert(0, PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFileMD5(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(byteArray);
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFileMD5(String str, AssetManager assetManager) {
        InputStream fileInputStream;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (str.startsWith("file:///android_asset/")) {
                fileInputStream = assetManager.open(str.substring(22));
            } else {
                fileInputStream = new FileInputStream(str);
            }
            byte[] bArr = new byte[8192];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i);
            }
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            fileInputStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int[] getPictureFileWH(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
            return new int[]{options.outWidth, options.outHeight};
        } catch (Exception unused) {
            return new int[]{0};
        }
    }

    public static File zipLogFiles(File file, File file2, List<String> list) {
        File file3 = new File(AppConfig.BASE_FOLDER + "log_" + App.mCPUSerial + "_" + AppUtil.getSystemTime("yyyyMMdd") + ".zip");
        if (file3.exists()) {
            file3.delete();
        }
        try {
            file3.createNewFile();
            ArrayList arrayList = new ArrayList();
            if (file.exists() && file.isDirectory()) {
                arrayList.addAll(Arrays.asList(file.listFiles()));
            }
            if (file2 != null && file2.exists() && file2.isDirectory()) {
                arrayList.addAll(Arrays.asList(file2.listFiles()));
            }
            if (list != null) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    File file4 = new File(it.next());
                    if (file4.exists() && file4.isFile()) {
                        arrayList.add(file4);
                    }
                }
            }
            try {
                zipFiles(arrayList, file3);
                return file3;
            } catch (Exception e) {
                LoggerUtil.m1182e("FileUtil", "上传日志失败, " + e.getCause());
                file3.delete();
                return null;
            }
        } catch (Exception e2) {
            LoggerUtil.m1182e("FileUtil", "创建压缩文件失败, " + e2.getCause());
            return null;
        }
    }

    public static void zipFiles(List<File> list, File file) throws IOException {
        byte[] bArr = new byte[1024];
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        for (File file2 : list) {
            if (file2.isDirectory()) {
                zipDirectory(file2, file2.getName(), zipOutputStream);
            } else {
                FileInputStream fileInputStream = new FileInputStream(file2);
                zipOutputStream.putNextEntry(new ZipEntry(file2.getName()));
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    } else {
                        zipOutputStream.write(bArr, 0, i);
                    }
                }
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
        }
        zipOutputStream.close();
        fileOutputStream.close();
    }

    private static void zipDirectory(File file, String str, ZipOutputStream zipOutputStream) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                zipDirectory(file2, str + MqttTopic.TOPIC_LEVEL_SEPARATOR + file2.getName(), zipOutputStream);
            } else {
                byte[] bArr = new byte[1024];
                FileInputStream fileInputStream = new FileInputStream(file2);
                zipOutputStream.putNextEntry(new ZipEntry(str + MqttTopic.TOPIC_LEVEL_SEPARATOR + file2.getName()));
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    } else {
                        zipOutputStream.write(bArr, 0, i);
                    }
                }
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
        }
    }

    public static void unzipFile(String str, String str2) {
        ZipInputStream zipInputStream;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                zipInputStream = new ZipInputStream(fileInputStream, Charset.forName("GBK"));
            } finally {
            }
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        File file = new File(str2, nextEntry.getName());
                        File parentFile = file.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        if (!nextEntry.isDirectory()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i = zipInputStream.read(bArr);
                                    if (i <= 0) {
                                        break;
                                    } else {
                                        fileOutputStream.write(bArr, 0, i);
                                    }
                                }
                                fileOutputStream.close();
                            } catch (Throwable th) {
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                        zipInputStream.closeEntry();
                    } else {
                        zipInputStream.close();
                        fileInputStream.close();
                        return;
                    }
                } finally {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFolderPicture(File file) {
        File[] fileArrListFiles;
        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    String lowerCase = file2.getName().toLowerCase();
                    if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".png")) {
                        file2.delete();
                    }
                }
            }
        }
    }

    public static int[] getMp4WH(String str) {
        int iStrToInt;
        int iStrToInt2 = -1;
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            String strExtractMetadata = mediaMetadataRetriever.extractMetadata(18);
            String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
            mediaMetadataRetriever.release();
            iStrToInt = StringUtil.strToInt(strExtractMetadata, -1);
            try {
                iStrToInt2 = StringUtil.strToInt(strExtractMetadata2, -1);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            iStrToInt = -1;
        }
        return new int[]{iStrToInt, iStrToInt2};
    }

    public static boolean copyAssetFileToSDCard(String str, AssetManager assetManager, String str2) {
        try {
            InputStream inputStreamOpen = assetManager.open(str);
            File file = new File(str2);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    inputStreamOpen.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyFile(String str, String str2) {
        File file = new File(str);
        File file2 = new File(str2);
        File parentFile = file2.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i > 0) {
                            fileOutputStream.write(bArr, 0, i);
                        } else {
                            fileOutputStream.close();
                            fileInputStream.close();
                            return true;
                        }
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void notifyFile(String str) {
        MediaScannerConnection.scanFile(App.getInstance(), new String[]{str}, null, null);
    }

    public static void notifyFile(File file) {
        MediaScannerConnection.scanFile(App.getInstance(), new String[]{file.getAbsolutePath()}, null, null);
    }

    public static boolean saveBitmap(Bitmap bitmap, File file) throws Throwable {
        boolean z;
        FileOutputStream fileOutputStream;
        ?? r0 = 0;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            r0 = 85;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.flush();
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            z = true;
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            z = false;
            r0 = fileOutputStream2;
        } catch (Throwable th2) {
            th = th2;
            r0 = fileOutputStream;
            if (r0 != 0) {
                try {
                    r0.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        return z;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean saveBitmap(android.graphics.Bitmap r3, java.io.File r4, int r5) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "saveBitmap error "
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L23 java.lang.NullPointerException -> L25 java.io.IOException -> L27
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L23 java.lang.NullPointerException -> L25 java.io.IOException -> L27
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Throwable -> L1b java.lang.NullPointerException -> L1e java.io.IOException -> L20
            r3.compress(r4, r5, r2)     // Catch: java.lang.Throwable -> L1b java.lang.NullPointerException -> L1e java.io.IOException -> L20
            r2.flush()     // Catch: java.lang.Throwable -> L1b java.lang.NullPointerException -> L1e java.io.IOException -> L20
            r2.close()     // Catch: java.io.IOException -> L15
            goto L19
        L15:
            r3 = move-exception
            r3.printStackTrace()
        L19:
            r3 = 1
            goto L49
        L1b:
            r3 = move-exception
            r1 = r2
            goto L4a
        L1e:
            r3 = move-exception
            goto L21
        L20:
            r3 = move-exception
        L21:
            r1 = r2
            goto L28
        L23:
            r3 = move-exception
            goto L4a
        L25:
            r3 = move-exception
            goto L28
        L27:
            r3 = move-exception
        L28:
            java.lang.String r4 = "FileUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L23
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L23
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L23
            java.lang.StringBuilder r3 = r5.append(r3)     // Catch: java.lang.Throwable -> L23
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L23
            com.wugu.doublecamera.utils.LoggerUtil.m1182e(r4, r3)     // Catch: java.lang.Throwable -> L23
            if (r1 == 0) goto L48
            r1.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r3 = move-exception
            r3.printStackTrace()
        L48:
            r3 = 0
        L49:
            return r3
        L4a:
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.io.IOException -> L50
            goto L54
        L50:
            r4 = move-exception
            r4.printStackTrace()
        L54:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.utils.FileUtil.saveBitmap(android.graphics.Bitmap, java.io.File, int):boolean");
    }

    public static void genBlackPdfFile(String str) {
        FileOutputStream fileOutputStream;
        String str2 = App.getInstance().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + StringUtil.getFileNameFromPath(str).replace(".jpg", ".pdf").replace(".png", ".pdf");
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmapDecodeFile, 0.0f, 0.0f, paint);
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.Page pageStartPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), 1).create());
        pageStartPage.getCanvas().drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        pdfDocument.finishPage(pageStartPage);
        try {
            try {
                fileOutputStream = new FileOutputStream(str2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                pdfDocument.writeTo(fileOutputStream);
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } finally {
            pdfDocument.close();
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void saveDataToFile(java.lang.String r2, java.lang.String r3) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28
            if (r2 != 0) goto Ld
            return
        Ld:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28
            byte[] r3 = r3.getBytes()     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            r2.write(r3)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            r2.flush()     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            r2.close()     // Catch: java.io.IOException -> L32
            goto L36
        L20:
            r3 = move-exception
            r0 = r2
            goto L37
        L23:
            r3 = move-exception
            r0 = r2
            goto L29
        L26:
            r3 = move-exception
            goto L37
        L28:
            r3 = move-exception
        L29:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L26
            if (r0 == 0) goto L36
            r0.close()     // Catch: java.io.IOException -> L32
            goto L36
        L32:
            r2 = move-exception
            r2.printStackTrace()
        L36:
            return
        L37:
            if (r0 == 0) goto L41
            r0.close()     // Catch: java.io.IOException -> L3d
            goto L41
        L3d:
            r2 = move-exception
            r2.printStackTrace()
        L41:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.utils.FileUtil.saveDataToFile(java.lang.String, java.lang.String):void");
    }
}
