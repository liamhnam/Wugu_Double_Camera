package com.epson.isv.eprinterdriver.Common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class EPLog {
    public static final int LOG_ERROR_CLOSE = -2;
    public static final int LOG_ERROR_NONE = 0;
    public static final int LOG_ERROR_OPEN = -1;
    private static BufferedWriter logBuf;

    public static int openFile(String str, boolean z) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(str), z));
            logBuf = bufferedWriter;
            if (!z) {
                return 0;
            }
            try {
                bufferedWriter.newLine();
                return 0;
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            logBuf = null;
            return -1;
        }
    }

    public static void logInfo(String str) {
        BufferedWriter bufferedWriter = logBuf;
        if (bufferedWriter != null) {
            try {
                bufferedWriter.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void logTime(String str, long j) {
        BufferedWriter bufferedWriter = logBuf;
        if (bufferedWriter != null) {
            try {
                bufferedWriter.write(str + getTimeFormat(j) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int closeFile() {
        BufferedWriter bufferedWriter = logBuf;
        if (bufferedWriter == null) {
            return 0;
        }
        try {
            bufferedWriter.close();
            logBuf = null;
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        }
    }

    private static String getTimeFormat(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        return i + MqttTopic.TOPIC_LEVEL_SEPARATOR + (i2 + 1) + MqttTopic.TOPIC_LEVEL_SEPARATOR + calendar.get(5) + "/ " + calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13) + "." + calendar.get(14);
    }
}
