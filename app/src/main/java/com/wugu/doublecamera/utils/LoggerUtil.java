package com.wugu.doublecamera.utils;

import com.wugu.doublecamera.AppConfig;
import de.mindpipe.android.logging.log4j.LogConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class LoggerUtil {
    public static void createLoggerFile() {
        try {
            String systemTime = AppUtil.getSystemTime("yyyyMMdd");
            LogConfigurator logConfigurator = new LogConfigurator();
            logConfigurator.setFileName(AppConfig.LOGGER_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + systemTime + ".log");
            logConfigurator.setRootLevel(Level.DEBUG);
            logConfigurator.setLevel("org.apache", Level.ERROR);
            logConfigurator.setFilePattern("%d - [%-5p::%c{2}] - %m%n");
            logConfigurator.setMaxFileSize(10485760L);
            logConfigurator.setMaxBackupSize(2);
            logConfigurator.configure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m1183i(String str, String str2) {
        Logger.getLogger(str).info(str2);
    }

    public static void m1181d(String str, String str2) {
        Logger.getLogger(str).debug(str2);
    }

    public static void m1182e(String str, String str2) {
        Logger.getLogger(str).error(str2);
    }
}
