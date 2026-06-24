package com.tom_roush.fontbox.util.autodetect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class WindowsFontDirFinder implements FontDirFinder {
    private String getWinDir(String str) throws IOException {
        Process processExec;
        Runtime runtime = Runtime.getRuntime();
        if (str.startsWith("Windows 9")) {
            processExec = runtime.exec("command.com /c echo %windir%");
        } else {
            processExec = runtime.exec("cmd.exe /c echo %windir%");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        return line;
    }

    @Override
    public List<File> find() {
        String winDir;
        ArrayList arrayList = new ArrayList();
        try {
            winDir = System.getProperty("env.windir");
        } catch (SecurityException unused) {
            winDir = null;
        }
        String property = System.getProperty("os.name");
        if (winDir == null) {
            try {
                winDir = getWinDir(property);
            } catch (IOException unused2) {
            }
        }
        if (winDir != null) {
            if (winDir.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                winDir = winDir.substring(0, winDir.length() - 1);
            }
            File file = new File(winDir + File.separator + "FONTS");
            if (file.exists() && file.canRead()) {
                arrayList.add(file);
            }
            File file2 = new File(winDir.substring(0, 2) + File.separator + "PSFONTS");
            if (file2.exists() && file2.canRead()) {
                arrayList.add(file2);
            }
        } else {
            String str = property.endsWith("NT") ? "WINNT" : "WINDOWS";
            char c = 'C';
            char c2 = 'C';
            while (true) {
                if (c2 > 'E') {
                    break;
                }
                File file3 = new File(c2 + ":" + File.separator + str + File.separator + "FONTS");
                if (file3.exists() && file3.canRead()) {
                    arrayList.add(file3);
                    break;
                }
                c2 = (char) (c2 + 1);
            }
            while (true) {
                if (c > 'E') {
                    break;
                }
                File file4 = new File(c + ":" + File.separator + "PSFONTS");
                if (file4.exists() && file4.canRead()) {
                    arrayList.add(file4);
                    break;
                }
                c = (char) (c + 1);
            }
        }
        return arrayList;
    }
}
