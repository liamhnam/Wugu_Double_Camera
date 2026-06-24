package com.proembed.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LogUtils {
    public static void startLog(final String str) {
        File file = new File(str);
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            new Thread(new Runnable() {
                @Override
                public void run() throws Throwable {
                    Utils.execFor7("logcat -v time  > " + str);
                }
            }).start();
        }
    }

    public static void stopLog() {
        new Thread(new Runnable() {
            @Override
            public void run() throws Throwable {
                Utils.execFor7("killall logcat");
            }
        }).start();
    }

    public static void saveToSDCard(String str, String str2) throws Throwable {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
            file = new File(str);
        }
        writeFile(file, str2);
    }

    private static void writeFile(File file, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e2) {
                e = e2;
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e4) {
            e = e4;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
        } catch (IOException e5) {
            e = e5;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }
}
