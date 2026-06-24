package com.proembed.service;

import android.os.StatFs;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import okhttp3.internal.p040ws.RealWebSocket;

public class StorageUtils {
    private static long readBlockSize(String str, int i) {
        StatFs statFs = new StatFs(str);
        long blockSize = statFs.getBlockSize();
        long blockCount = statFs.getBlockCount();
        long availableBlocks = statFs.getAvailableBlocks();
        if (i == 0) {
            return (blockSize * blockCount) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
        }
        return i == 1 ? (blockSize * availableBlocks) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE : ((blockCount * blockSize) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) - ((blockSize * availableBlocks) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE);
    }

    public static String getRealSizeOfNand() {
        if (readBlockSize(Constant.NAND_PATH, 0) / 1048576 < 3) {
            return "4G";
        }
        if (readBlockSize(Constant.NAND_PATH, 0) / 1048576 < 3 || readBlockSize(Constant.NAND_PATH, 0) / 1048576 >= 7) {
            if (readBlockSize(Constant.NAND_PATH, 0) / 1048576 >= 7 && readBlockSize(Constant.NAND_PATH, 0) / 1048576 < 15) {
                return "16G";
            }
            if (readBlockSize(Constant.NAND_PATH, 0) / 1048576 >= 15 && readBlockSize(Constant.NAND_PATH, 0) / 1048576 < 31) {
                return "32G";
            }
            if (readBlockSize(Constant.NAND_PATH, 0) / 1048576 >= 31 && readBlockSize(Constant.NAND_PATH, 0) / 1048576 < 63) {
                return "64G";
            }
            if (readBlockSize(Constant.NAND_PATH, 0) / 1048576 >= 63 && readBlockSize(Constant.NAND_PATH, 0) / 1048576 < 127) {
                return "128G";
            }
        }
        return "8G";
    }

    public static List<String> getAllUSBStorageLocations() {
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File("/proc/mounts");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String strNextLine = scanner.nextLine();
                    if (strNextLine.contains("storage")) {
                        String str = strNextLine.split(" ")[1];
                        while (scanner.hasNext()) {
                            String strNextLine2 = scanner.nextLine();
                            if (strNextLine2.startsWith(str)) {
                                String str2 = strNextLine2.split(" ")[1];
                                if (strNextLine2.contains("usb") || strNextLine2.contains("media_rw")) {
                                    arrayList.add(str2);
                                }
                            }
                        }
                        arrayList.add(str);
                        return arrayList;
                    }
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static long getTotalMemorySize() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 2048);
            String line = bufferedReader.readLine();
            String strSubstring = line.substring(line.indexOf("MemTotal:"));
            bufferedReader.close();
            return ((long) Integer.parseInt(strSubstring.replaceAll("\\D+", ""))) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
        } catch (IOException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getRealMeoSize() {
        return getTotalMemorySize() <= 512 ? "512M" : (getTotalMemorySize() <= 512 || getTotalMemorySize() > RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) ? (getTotalMemorySize() <= RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE || getTotalMemorySize() > 2048) ? (getTotalMemorySize() <= 2048 || getTotalMemorySize() > 6114) ? getTotalMemorySize() > 6114 ? "6G" : PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES : "4G" : "2G" : "1G";
    }
}
