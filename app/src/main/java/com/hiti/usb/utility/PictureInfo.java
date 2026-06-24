package com.hiti.usb.utility;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.ExifInterface;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PictureInfo {
    public static String DateToString(Date date, String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(date);
    }

    public static String GetDateTime(String str) {
        try {
            String attribute = new ExifInterface(str).getAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME);
            if (attribute != null) {
                return DateToString(StringToDate(attribute, "yyyy:MM:dd HH:mm"), "yyyy-MM-dd");
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Point GetImageSize(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(fileInputStream, null, options);
        fileInputStream.close();
        return new Point(options.outWidth, options.outHeight);
    }

    public static boolean GetImgOrientation(Point point) {
        return point.x >= point.y;
    }

    public static Date StringToDate(String str, String str2) {
        try {
            return new SimpleDateFormat(str2, Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
