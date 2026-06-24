package org.eclipse.paho.client.mqttv3.util;

import com.tom_roush.fontbox.afm.AFMParser;
import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class Debug {
    private static final String CLASS_NAME;
    static Class class$0 = null;
    private static final String lineSep;
    private static final Logger log;
    private static final String separator = "==============";
    private String clientID;
    private ClientComms comms;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientComms");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        lineSep = System.getProperty("line.separator", "\n");
    }

    public Debug(String str, ClientComms clientComms) {
        this.clientID = str;
        this.comms = clientComms;
        log.setResourceName(str);
    }

    public void dumpClientDebug() {
        dumpClientComms();
        dumpConOptions();
        dumpClientState();
        dumpBaseDebug();
    }

    public void dumpBaseDebug() {
        dumpVersion();
        dumpSystemProperties();
        dumpMemoryTrace();
    }

    protected void dumpMemoryTrace() {
        log.dumpTrace();
    }

    protected void dumpVersion() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = lineSep;
        stringBuffer.append(new StringBuffer(String.valueOf(str)).append("============== Version Info ==============").append(str).toString());
        stringBuffer.append(new StringBuffer(String.valueOf(left(AFMParser.VERSION, 20, ' '))).append(":  ").append(ClientComms.VERSION).append(str).toString());
        stringBuffer.append(new StringBuffer(String.valueOf(left("Build Level", 20, ' '))).append(":  ").append(ClientComms.BUILD_LEVEL).append(str).toString());
        stringBuffer.append(new StringBuffer("==========================================").append(str).toString());
        log.fine(CLASS_NAME, "dumpVersion", stringBuffer.toString());
    }

    public void dumpSystemProperties() {
        log.fine(CLASS_NAME, "dumpSystemProperties", dumpProperties(System.getProperties(), "SystemProperties").toString());
    }

    public void dumpClientState() {
        ClientComms clientComms = this.comms;
        if (clientComms == null || clientComms.getClientState() == null) {
            return;
        }
        log.fine(CLASS_NAME, "dumpClientState", dumpProperties(this.comms.getClientState().getDebug(), new StringBuffer(String.valueOf(this.clientID)).append(" : ClientState").toString()).toString());
    }

    public void dumpClientComms() {
        ClientComms clientComms = this.comms;
        if (clientComms != null) {
            log.fine(CLASS_NAME, "dumpClientComms", dumpProperties(clientComms.getDebug(), new StringBuffer(String.valueOf(this.clientID)).append(" : ClientComms").toString()).toString());
        }
    }

    public void dumpConOptions() {
        ClientComms clientComms = this.comms;
        if (clientComms != null) {
            log.fine(CLASS_NAME, "dumpConOptions", dumpProperties(clientComms.getConOptions().getDebug(), new StringBuffer(String.valueOf(this.clientID)).append(" : Connect Options").toString()).toString());
        }
    }

    public static String dumpProperties(Properties properties, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        String str2 = lineSep;
        stringBuffer.append(new StringBuffer(String.valueOf(str2)).append("============== ").append(str).append(" ==============").append(str2).toString());
        while (enumerationPropertyNames.hasMoreElements()) {
            String str3 = (String) enumerationPropertyNames.nextElement();
            stringBuffer.append(new StringBuffer(String.valueOf(left(str3, 28, ' '))).append(":  ").append(properties.get(str3)).append(lineSep).toString());
        }
        stringBuffer.append(new StringBuffer("==========================================").append(lineSep).toString());
        return stringBuffer.toString();
    }

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length >= 0) {
                stringBuffer.append(c);
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
