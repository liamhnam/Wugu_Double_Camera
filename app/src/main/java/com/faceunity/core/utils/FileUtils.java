package com.faceunity.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Log;
import com.faceunity.core.entity.TextureImage;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0007J*\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u0004H\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\n\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u000e\u001a\u00020\u0004H\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0011H\u0007J\"\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0011H\u0007J\"\u0010\u0017\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0011H\u0007J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J$\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u001c2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0004H\u0007J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u0014H\u0007J\u001a\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u0012\u0010\"\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u001a\u0010#\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u001a\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u001a\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0004H\u0003J\u0012\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0012\u001a\u00020\u0004H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006("}, m1293d2 = {"Lcom/faceunity/core/utils/FileUtils;", "", "()V", "TAG", "", "copyAssetsToExternalFilesDir", "context", "Landroid/content/Context;", "assetsPath", "fileName", "dir", "getCacheVideoFile", "Ljava/io/File;", "getCurrentVideoFileName", "getDateTimeString", "getExternalFileDir", "getPhotoOrientation", "", "path", "loadBitmapFromExternal", "Landroid/graphics/Bitmap;", "screenWidth", "screenHeight", "loadBitmapFromExternalUnRotate", "loadBitmapFromLocal", "loadBundleFromLocal", "", "loadParamsFromLocal", "Ljava/util/LinkedHashMap;", "jsonPath", "loadRgbaByteFromBitmap", "bitmap", "loadRgbaColorFromLocal", "", "loadStringFromExternal", "loadStringFromLocal", "loadTextureImageFromLocal", "Lcom/faceunity/core/entity/TextureImage;", "readInputByPath", "Ljava/io/InputStream;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FileUtils {
    public static final FileUtils INSTANCE = new FileUtils();
    private static final String TAG = "KIT_FileUtils";

    private FileUtils() {
    }

    @JvmStatic
    private static final InputStream readInputByPath(Context context, String path) {
        if (StringsKt.isBlank(path)) {
            return null;
        }
        try {
            try {
                return context.getAssets().open(path);
            } catch (IOException unused) {
                return new FileInputStream(path);
            }
        } catch (IOException unused2) {
            return null;
        }
    }

    @JvmStatic
    private static final InputStream readInputByPath(String path) {
        if (StringsKt.isBlank(path)) {
            return null;
        }
        try {
            return new FileInputStream(path);
        } catch (IOException unused) {
            return null;
        }
    }

    @JvmStatic
    public static final byte[] loadBundleFromLocal(Context context, String path) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(path, "path");
        InputStream inputByPath = readInputByPath(context, path);
        try {
            if (inputByPath != null) {
                try {
                    byte[] bArr = new byte[inputByPath.available()];
                    inputByPath.read(bArr);
                    return bArr;
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        inputByPath.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            FULogger.m293d(TAG, "loadBundleFromLocal failed path:" + path);
            return null;
        } finally {
            try {
                inputByPath.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    @JvmStatic
    public static final String loadStringFromLocal(Context context, String path) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(path, "path");
        InputStream inputByPath = readInputByPath(context, path);
        String str = null;
        if (inputByPath != null) {
            try {
                try {
                    byte[] bArr = new byte[inputByPath.available()];
                    inputByPath.read(bArr);
                    String str2 = new String(bArr, Charsets.UTF_8);
                    try {
                        inputByPath.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    str = str2;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                try {
                    inputByPath.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        FULogger.m293d(TAG, "loadStringFromLocal failed path:" + path);
        return str;
    }

    @JvmStatic
    public static final Bitmap loadBitmapFromLocal(Context context, String path) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(path, "path");
        InputStream inputByPath = readInputByPath(context, path);
        try {
            if (inputByPath == null) {
                return null;
            }
            try {
                Drawable drawableCreateFromStream = Drawable.createFromStream(inputByPath, null);
                if (drawableCreateFromStream == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
                }
                return ((BitmapDrawable) drawableCreateFromStream).getBitmap();
            } catch (Exception e) {
                e.printStackTrace();
                FULogger.m293d(TAG, "loadBitmapFromLocal failed path:" + path);
                try {
                    inputByPath.close();
                    return null;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        } finally {
            try {
                inputByPath.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    @JvmStatic
    public static final LinkedHashMap<String, Object> loadParamsFromLocal(Context context, String jsonPath) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(jsonPath, "jsonPath");
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        try {
            String strLoadStringFromLocal = loadStringFromLocal(context, jsonPath);
            if (strLoadStringFromLocal != null) {
                JSONObject jSONObject = new JSONObject(strLoadStringFromLocal);
                Iterator<String> itKeys = jSONObject.keys();
                Intrinsics.checkExpressionValueIsNotNull(itKeys, "jsonObject.keys()");
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    Object objOpt = jSONObject.opt(next);
                    if ((objOpt instanceof String) || (objOpt instanceof Double) || (objOpt instanceof Integer)) {
                        linkedHashMap.put(next, objOpt);
                    } else if (objOpt instanceof JSONArray) {
                        int length = ((JSONArray) objOpt).length();
                        double[] dArr = new double[length];
                        for (int i = 0; i < length; i++) {
                            dArr[i] = ((JSONArray) objOpt).optDouble(i);
                        }
                        linkedHashMap.put(next, dArr);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkedHashMap;
    }

    @JvmStatic
    public static final double[] loadRgbaColorFromLocal(Context context, String path) throws IOException {
        double[] dArr;
        JSONArray jSONArrayOptJSONArray;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(path, "path");
        InputStream inputByPath = readInputByPath(context, path);
        String string = null;
        try {
            if (inputByPath != null) {
                try {
                    byte[] bArr = new byte[inputByPath.available()];
                    inputByPath.read(bArr);
                    jSONArrayOptJSONArray = new JSONObject(new String(bArr, Charsets.UTF_8)).optJSONArray("rgba");
                } catch (IOException e) {
                    e = e;
                    dArr = null;
                } catch (JSONException e2) {
                    e = e2;
                    dArr = null;
                }
                if (jSONArrayOptJSONArray == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.json.JSONArray");
                }
                dArr = new double[jSONArrayOptJSONArray.length()];
                try {
                    int length = jSONArrayOptJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        dArr[i] = jSONArrayOptJSONArray.optDouble(i);
                    }
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                } catch (JSONException e4) {
                    e = e4;
                    e.printStackTrace();
                }
            } else {
                dArr = null;
            }
            StringBuilder sbAppend = new StringBuilder("loadRgbaColorFromLocal  path:").append(path).append("   colors:");
            if (dArr != null) {
                string = Arrays.toString(dArr);
                Intrinsics.checkExpressionValueIsNotNull(string, "java.util.Arrays.toString(this)");
            }
            FULogger.m293d(TAG, sbAppend.append(string).toString());
            return dArr;
        } finally {
            inputByPath.close();
        }
    }

    @JvmStatic
    public static final TextureImage loadTextureImageFromLocal(Context context, String path) throws IOException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(path, "path");
        InputStream inputByPath = readInputByPath(context, path);
        try {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputByPath.close();
        }
        Bitmap bitmapDecodeStream = inputByPath != null ? BitmapFactory.decodeStream(inputByPath) : null;
        if (bitmapDecodeStream != null) {
            return new TextureImage(bitmapDecodeStream.getWidth(), bitmapDecodeStream.getHeight(), loadRgbaByteFromBitmap(bitmapDecodeStream));
        }
        FULogger.m293d(TAG, "loadTextureImageFromLocal failed path:" + path);
        return null;
    }

    @JvmStatic
    public static final byte[] loadRgbaByteFromBitmap(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        byte[] bArr = new byte[bitmap.getByteCount()];
        bitmap.copyPixelsToBuffer(ByteBuffer.wrap(bArr));
        return bArr;
    }

    public static String copyAssetsToExternalFilesDir$default(Context context, String str, String str2, String str3, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = "assets";
        }
        return copyAssetsToExternalFilesDir(context, str, str2, str3);
    }

    @JvmStatic
    public static final String copyAssetsToExternalFilesDir(Context context, String assetsPath, String fileName, String dir) throws IOException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(assetsPath, "assetsPath");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        Intrinsics.checkParameterIsNotNull(dir, "dir");
        StringBuilder sb = new StringBuilder();
        File externalFileDir = INSTANCE.getExternalFileDir(context);
        if (externalFileDir == null) {
            Intrinsics.throwNpe();
        }
        File file = new File(sb.append(externalFileDir.getPath()).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(dir).toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file + '/' + fileName);
        if (file2.exists()) {
            String absolutePath = file2.getAbsolutePath();
            Intrinsics.checkExpressionValueIsNotNull(absolutePath, "file.absolutePath");
            return absolutePath;
        }
        InputStream inputStreamOpen = context.getAssets().open(assetsPath);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamOpen);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        byte[] bArr = new byte[1024];
        for (int i = bufferedInputStream.read(bArr); i > 0; i = bufferedInputStream.read(bArr)) {
            bufferedOutputStream.write(bArr, 0, i);
            bufferedOutputStream.flush();
        }
        bufferedOutputStream.close();
        fileOutputStream.close();
        Log.d("FileUtils", "Copy " + fileName + " into " + file + " succeeded.");
        String absolutePath2 = file2.getAbsolutePath();
        Intrinsics.checkExpressionValueIsNotNull(absolutePath2, "file.absolutePath");
        return absolutePath2;
    }

    @JvmStatic
    public static final File getCacheVideoFile(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        StringBuilder sb = new StringBuilder();
        FileUtils fileUtils = INSTANCE;
        File file = new File(sb.append(fileUtils.getExternalFileDir(context).getPath()).append(File.separator).append(MimeTypes.BASE_TYPE_VIDEO).toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, fileUtils.getCurrentVideoFileName());
        if (file2.exists()) {
            file2.delete();
        }
        return file2;
    }

    private final String getCurrentVideoFileName() {
        return getDateTimeString() + ".mp4";
    }

    private final String getDateTimeString() {
        String str = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new GregorianCalendar().getTime());
        Intrinsics.checkExpressionValueIsNotNull(str, "SimpleDateFormat(\"yyyyMM…cale.US).format(now.time)");
        return str;
    }

    private final File getExternalFileDir(Context context) {
        File fileDir = context.getExternalFilesDir(null);
        if (fileDir == null) {
            fileDir = context.getFilesDir();
        }
        Intrinsics.checkExpressionValueIsNotNull(fileDir, "fileDir");
        return fileDir;
    }

    @JvmStatic
    public static final String loadStringFromExternal(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        InputStream inputByPath = readInputByPath(path);
        String str = null;
        if (inputByPath != null) {
            try {
                try {
                    byte[] bArr = new byte[inputByPath.available()];
                    inputByPath.read(bArr);
                    String str2 = new String(bArr, Charsets.UTF_8);
                    try {
                        inputByPath.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    str = str2;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                try {
                    inputByPath.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        FULogger.m293d(TAG, "loadStringFromLocal failed path:" + path);
        return str;
    }

    @JvmStatic
    public static final Bitmap loadBitmapFromExternal(String path, int screenWidth) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        options.inSampleSize = 1;
        if (i > i2) {
            if (i2 > screenWidth) {
                options.inSampleSize = i2 / screenWidth;
            }
        } else if (i > screenWidth) {
            options.inSampleSize = i / screenWidth;
        }
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int photoOrientation = INSTANCE.getPhotoOrientation(path);
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
        return bitmapUtils.rotateBitmap(bitmap, photoOrientation);
    }

    @JvmStatic
    public static final Bitmap loadBitmapFromExternal(String path, int screenWidth, int screenHeight) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Bitmap bitmapLoadBitmapFromExternalUnRotate = loadBitmapFromExternalUnRotate(path, screenWidth, screenHeight);
        return bitmapLoadBitmapFromExternalUnRotate != null ? BitmapUtils.INSTANCE.rotateBitmap(bitmapLoadBitmapFromExternalUnRotate, INSTANCE.getPhotoOrientation(path)) : bitmapLoadBitmapFromExternalUnRotate;
    }

    @JvmStatic
    public static final Bitmap loadBitmapFromExternalUnRotate(String path, int screenWidth, int screenHeight) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            if (i3 > screenHeight || i2 > screenWidth) {
                int i4 = i3 / 2;
                int i5 = i2 / 2;
                while (i4 / i >= screenHeight && i5 / i >= screenWidth) {
                    i *= 2;
                }
            }
            options.inSampleSize = i;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(path, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final int getPhotoOrientation(String path) {
        int attributeInt;
        Intrinsics.checkParameterIsNotNull(path, "path");
        try {
            attributeInt = new ExifInterface(path).getAttributeInt("Orientation", -1);
        } catch (IOException e) {
            e.printStackTrace();
            attributeInt = 0;
        }
        if (attributeInt == 3) {
            return 180;
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? 0 : 270;
        }
        return 90;
    }

    @JvmStatic
    public static final String copyAssetsToExternalFilesDir(Context context, String assetsPath, String fileName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(assetsPath, "assetsPath");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        File file = new File(INSTANCE.getExternalFileDir(context).getPath() + File.separator + "assets");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, fileName);
        if (file2.exists()) {
            return file2.getAbsolutePath();
        }
        try {
            InputStream inputStreamOpen = context.getAssets().open(assetsPath);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamOpen);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] bArr = new byte[1024];
            for (int i = bufferedInputStream.read(bArr); i > 0; i = bufferedInputStream.read(bArr)) {
                bufferedOutputStream.write(bArr, 0, i);
                bufferedOutputStream.flush();
            }
            bufferedOutputStream.close();
            fileOutputStream.close();
            return file2.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
