package com.hiti.usb.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ZipUtility {
    private static final boolean localLOG = false;
    private static final String tag = "ZipUtility";

    public static boolean UnpackZip(String str, String str2) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str2)));
            byte[] bArr = new byte[1024];
            if (str.lastIndexOf("\\") != str.length() - 1 && str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) != str.length() - 1) {
                str = str + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            }
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    return true;
                }
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    new File(str + name).mkdirs();
                } else {
                    FileOutputStream fileOutputStream = new FileOutputStream(str + name);
                    while (true) {
                        int i = zipInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    }
                    fileOutputStream.close();
                    zipInputStream.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String UnpackZipForFW(String str, String str2) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str2)));
            byte[] bArr = new byte[1024];
            if (str.lastIndexOf("\\") != str.length() - 1 && str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) != str.length() - 1) {
                str = str + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            }
            FileUtility.CreateFolder(str);
            String str3 = "";
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    return str3;
                }
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    new File(str + name).mkdirs();
                } else {
                    FileOutputStream fileOutputStream = new FileOutputStream(str + name);
                    while (true) {
                        int i = zipInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    }
                    fileOutputStream.close();
                    zipInputStream.closeEntry();
                    str3 = str + name;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
