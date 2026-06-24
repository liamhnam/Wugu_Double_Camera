package com.hiti.usb.utility;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class FileUtility {
    public static String ChangeFileExt(String str, String str2) {
        return str.replace(GetFileExt(str), str2);
    }

    private static void CopyDirectory(File file, File file2) throws IOException {
        if (file.isDirectory()) {
            if (!file2.exists()) {
                file2.mkdir();
            }
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                CopyDirectory(new File(file, list[i]), new File(file2, list[i]));
            }
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[1024];
        while (true) {
            int i2 = fileInputStream.read(bArr);
            if (i2 <= 0) {
                fileInputStream.close();
                fileOutputStream.close();
                return;
            }
            fileOutputStream.write(bArr, 0, i2);
        }
    }

    public static void CopyDirectory(String str, String str2) throws IOException {
        CopyDirectory(new File(str), new File(str2));
    }

    public static void CreateFolder(String str) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            file.mkdir();
        }
    }

    public static void DeleteALLFolder(String str) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(str);
            if (file.exists()) {
                deleteDir(file);
            }
        }
    }

    public static void DeleteFile(String str) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void DeleteFolder(String str) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(str);
            if (file.exists()) {
                for (String str2 : file.list()) {
                    new File(str + MqttTopic.TOPIC_LEVEL_SEPARATOR + str2).delete();
                }
            }
        }
    }

    public static boolean FileExist(String str) {
        if (str != null && Environment.getExternalStorageState().equals("mounted")) {
            return new File(str).exists();
        }
        return false;
    }

    private static long FolderSize(File file) {
        long jFolderSize = 0;
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            for (int i = 0; i < fileArrListFiles.length; i++) {
                jFolderSize += fileArrListFiles[i].isDirectory() ? FolderSize(fileArrListFiles[i]) : fileArrListFiles[i].length();
            }
        }
        return jFolderSize;
    }

    public static long FolderSize(String str) {
        return FolderSize(new File(str));
    }

    public static String GetFileExt(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        return iLastIndexOf != -1 ? str.substring(iLastIndexOf, str.length()) : ".jpg";
    }

    public static String GetFileName(String str) {
        String strReplace = str.replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
        return strReplace.substring(strReplace.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) + 1);
    }

    public static String GetFileNameWithoutExt(String str) {
        String strReplace = str.replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
        String strSubstring = strReplace.substring(strReplace.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) + 1);
        return strSubstring.substring(0, strSubstring.lastIndexOf("."));
    }

    public static long GetFileSize(String str) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0L;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    public static byte[] GetFileToByte(String str) throws Throwable {
        Throwable th;
        IOException e;
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        File file = new File(str);
        FileInputStream fileInputStream2 = null;
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bArr = new byte[1024];
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
            }
        } catch (IOException e4) {
            e = e4;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
        }
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, i);
            th = th2;
            fileInputStream2 = fileInputStream;
            try {
                fileInputStream2.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            throw th;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            fileInputStream.close();
        } catch (IOException e6) {
            e6.printStackTrace();
        }
        return byteArray;
    }

    public static String GetFolderFullPath(String str) {
        String strReplace = str.replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
        return strReplace.replace(MqttTopic.TOPIC_LEVEL_SEPARATOR + GetFileName(strReplace), "");
    }

    public static String GetFolderName(String str) {
        String strReplace = str.replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
        String strReplace2 = strReplace.replace(MqttTopic.TOPIC_LEVEL_SEPARATOR + GetFileName(strReplace), "");
        return strReplace2.substring(strReplace2.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) + 1);
    }

    public static String GetNewName(String str, String str2) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date(System.currentTimeMillis())) + str2 + GetFileExt(str);
    }

    public static String GetNewNameWithExt(String str, String str2) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date(System.currentTimeMillis())) + str2 + str;
    }

    public static String GetRealPathFromURI(Context context, Uri uri) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_data");
            cursorQuery.moveToFirst();
            return cursorQuery.getString(columnIndexOrThrow);
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    public static String GetSDAppRootPath(Context context) {
        File externalFilesDir;
        return (Environment.getExternalStorageState().equals("mounted") && (externalFilesDir = context.getExternalFilesDir(null)) != null) ? externalFilesDir.getAbsolutePath() : "";
    }

    public static String GetSDRootPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static boolean IsFromSDCard(Context context, String str) {
        String strGetSDAppRootPath = GetSDAppRootPath(context);
        return strGetSDAppRootPath.length() > 0 && str.contains(strGetSDAppRootPath);
    }

    public static boolean ReFullPathFile(String str, String str2) {
        return new File(str).renameTo(new File(str2));
    }

    public static String ReNameFile(String str, String str2) {
        new File(str).renameTo(new File(GetFolderFullPath(str) + MqttTopic.TOPIC_LEVEL_SEPARATOR + str2));
        return GetFolderFullPath(str) + MqttTopic.TOPIC_LEVEL_SEPARATOR + str2;
    }

    public static String ReadFile(String str) {
        FileReader fileReader;
        IOException e;
        BufferedReader bufferedReader;
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        try {
            fileReader = new FileReader(file);
            try {
                bufferedReader = new BufferedReader(fileReader);
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line + "\n");
                    } catch (IOException e2) {
                        e = e2;
                        e.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e = e3;
                bufferedReader = null;
            }
        } catch (IOException e4) {
            fileReader = null;
            e = e4;
            bufferedReader = null;
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        }
        if (fileReader != null) {
            try {
                fileReader.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static boolean SDCardState() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean SaveBitmap(String str, Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2.getPath());
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean SaveBitmapAndroidVersion(String str, Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2.getPath());
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Uri SavePhoto(Context context, String str, Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        boolean zCompress;
        OutputStream outputStreamOpenOutputStream;
        Log.i("SavePhoto", str);
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("_data", str);
        Uri uriInsert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        long id = ContentUris.parseId(uriInsert);
        Log.i("SavePhoto", "URI:" + uriInsert.toString());
        boolean z = false;
        try {
            outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uriInsert);
            zCompress = bitmap.compress(compressFormat, 100, outputStreamOpenOutputStream);
        } catch (Exception e) {
            e = e;
        }
        try {
            outputStreamOpenOutputStream.close();
            ContentValues contentValues2 = new ContentValues();
            File file = new File(str);
            contentValues2.put("datetaken", Long.valueOf(System.currentTimeMillis()));
            contentValues2.put("_size", Long.valueOf(file.length()));
            context.getContentResolver().update(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues2, "_id=?", new String[]{String.valueOf(id)});
            context.getContentResolver().notifyChange(uriInsert, null);
        } catch (Exception e2) {
            e = e2;
            z = zCompress;
            e.printStackTrace();
            zCompress = z;
        }
        if (!zCompress) {
            return null;
        }
        MediaScannerConnection.scanFile(context, new String[]{Environment.getExternalStorageDirectory().toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String str2, Uri uri) {
                Log.i("SavePhoto", "Scanned " + str2 + ":");
                Log.i("SavePhoto", "-> uri=" + uri);
            }
        });
        return uriInsert;
    }

    public static void WriteFile(String str, String str2) {
    }

    private static boolean deleteDir(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list == null) {
                return true;
            }
            for (String str : list) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        File file2 = new File(file.getAbsolutePath() + System.currentTimeMillis());
        file.renameTo(file2);
        return file2.delete();
    }
}
