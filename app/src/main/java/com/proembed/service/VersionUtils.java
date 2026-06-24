package com.proembed.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionUtils {
    public static String getKernelVersion() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/version");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream), 8192);
            String str = "";
            while (true) {
                try {
                    try {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            str = str + line;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                            fileInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                    bufferedReader.close();
                    fileInputStream.close();
                }
            }
            bufferedReader.close();
            fileInputStream.close();
            if (str == "") {
                return "";
            }
            try {
                String strSubstring = str.substring(str.indexOf("version ") + 8);
                return strSubstring.substring(0, strSubstring.indexOf(" "));
            } catch (IndexOutOfBoundsException e4) {
                e4.printStackTrace();
                return "";
            }
        } catch (FileNotFoundException e5) {
            e5.printStackTrace();
            return "";
        }
    }

    public static String getSystemVersionInfo() {
        return Utils.getValueFromProp(Constant.SYSTEM_VERSION_INFO);
    }

    public static String getFirmwareDate() {
        Matcher matcher = Pattern.compile("[1-9]\\d{3}(((0[13578]|1[02])([0-2]\\d|3[01]))|((0[469]|11)([0-2]\\d|30))|(02([01]\\d|2[0-8])))").matcher(Utils.getValueFromProp(Constant.DATE_PROP));
        return matcher.find() ? matcher.group() : "";
    }

    public static String getAndroidModle() {
        String strSubstring = getSystemVersionInfo().substring(0, 6);
        return strSubstring.contains("312x") ? "rk3128" : strSubstring;
    }

    public static String[] getCpuInfo() {
        String[] strArr = {"", ""};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            String[] strArrSplit = bufferedReader.readLine().split("\\s+");
            for (int i = 2; i < strArrSplit.length; i++) {
                strArr[0] = strArr[0] + strArrSplit[i] + " ";
            }
            strArr[1] = strArr[1] + bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return strArr;
    }
}
