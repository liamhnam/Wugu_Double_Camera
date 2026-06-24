package cc.uling.usdk.mgr;

import android.content.Context;
import cc.uling.usdk.USDK;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class LogManager {

    private static final String f448a = "USDK";

    private static SimpleDateFormat f449b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private static SimpleDateFormat f450c = new SimpleDateFormat("yyyyMMdd");

    private static File m257a(Context context, String str) {
        if (context == null) {
            throw new RuntimeException("The USDK is not initialized.");
        }
        File file = new File(context.getExternalFilesDir(null).getAbsolutePath() + File.separator + "logs" + (str == null ? "" : MqttTopic.TOPIC_LEVEL_SEPARATOR + str));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void m258a(String str, String str2) {
        write2File(m257a(USDK.getInstance().getApplication(), str).getAbsolutePath(), str2);
    }

    public static String readFromFile(String str) {
        String str2;
        File file = new File(str);
        if (!file.exists()) {
            return str + "-The file does not exist or has been deleted.";
        }
        if (file.isDirectory()) {
            return str + " is a directory file.";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String str3 = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    fileInputStream.close();
                    return str3;
                }
                str3 = str3 + line + "\n";
            }
        } catch (FileNotFoundException e) {
            str2 = str + "-The file does not exist";
            e.printStackTrace();
            return str2;
        } catch (IOException e2) {
            str2 = str + "-File read error.";
            e2.printStackTrace();
            return str2;
        }
    }

    public static void write(final String str, final String str2) {
        TPoolManager.getInstance().execute(new Runnable() {
            @Override
            public final void run() {
                LogManager.m258a(str, str2);
            }
        });
    }

    public static void write2File(String str, String str2) {
        Date date = new Date();
        String str3 = f450c.format(date);
        String str4 = f449b.format(date) + "|" + str2;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str, str3 + ".log");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file2, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str4);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
