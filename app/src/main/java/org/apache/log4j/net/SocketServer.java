package org.apache.log4j.net;

import java.io.File;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RootLogger;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class SocketServer {
    static String CONFIG_FILE_EXT = ".lcf";
    static String GENERIC = "generic";
    static Logger cat;
    static Class class$org$apache$log4j$net$SocketServer;
    static int port;
    static SocketServer server;
    File dir;
    LoggerRepository genericHierarchy;
    Hashtable hierarchyMap = new Hashtable(11);

    static {
        Class clsClass$ = class$org$apache$log4j$net$SocketServer;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.net.SocketServer");
            class$org$apache$log4j$net$SocketServer = clsClass$;
        }
        cat = Logger.getLogger(clsClass$);
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static void main(String[] strArr) throws Throwable {
        if (strArr.length == 3) {
            init(strArr[0], strArr[1], strArr[2]);
        } else {
            usage("Wrong number of arguments.");
        }
        try {
            cat.info(new StringBuffer("Listening on port ").append(port).toString());
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                cat.info("Waiting to accept a new client.");
                Socket socketAccept = serverSocket.accept();
                InetAddress inetAddress = socketAccept.getInetAddress();
                cat.info(new StringBuffer().append("Connected to client at ").append(inetAddress).toString());
                LoggerRepository loggerRepositoryConfigureHierarchy = (LoggerRepository) server.hierarchyMap.get(inetAddress);
                if (loggerRepositoryConfigureHierarchy == null) {
                    loggerRepositoryConfigureHierarchy = server.configureHierarchy(inetAddress);
                }
                cat.info("Starting new socket node.");
                new Thread(new SocketNode(socketAccept, loggerRepositoryConfigureHierarchy)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void usage(String str) throws Throwable {
        System.err.println(str);
        PrintStream printStream = System.err;
        StringBuffer stringBuffer = new StringBuffer("Usage: java ");
        Class clsClass$ = class$org$apache$log4j$net$SocketServer;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.net.SocketServer");
            class$org$apache$log4j$net$SocketServer = clsClass$;
        }
        printStream.println(stringBuffer.append(clsClass$.getName()).append(" port configFile directory").toString());
        System.exit(1);
    }

    static void init(String str, String str2, String str3) throws Throwable {
        try {
            port = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            usage(new StringBuffer("Could not interpret port number [").append(str).append("].").toString());
        }
        PropertyConfigurator.configure(str2);
        File file = new File(str3);
        if (!file.isDirectory()) {
            usage(new StringBuffer("[").append(str3).append("] is not a directory.").toString());
        }
        server = new SocketServer(file);
    }

    public SocketServer(File file) {
        this.dir = file;
    }

    LoggerRepository configureHierarchy(InetAddress inetAddress) throws Throwable {
        cat.info(new StringBuffer("Locating configuration file for ").append(inetAddress).toString());
        String string = inetAddress.toString();
        int iIndexOf = string.indexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        if (iIndexOf == -1) {
            cat.warn(new StringBuffer("Could not parse the inetAddress [").append(inetAddress).append("]. Using default hierarchy.").toString());
            return genericHierarchy();
        }
        File file = new File(this.dir, new StringBuffer().append(string.substring(0, iIndexOf)).append(CONFIG_FILE_EXT).toString());
        if (file.exists()) {
            Hierarchy hierarchy = new Hierarchy(new RootLogger(Level.DEBUG));
            this.hierarchyMap.put(inetAddress, hierarchy);
            new PropertyConfigurator().doConfigure(file.getAbsolutePath(), hierarchy);
            return hierarchy;
        }
        cat.warn(new StringBuffer("Could not find config file [").append(file).append("].").toString());
        return genericHierarchy();
    }

    LoggerRepository genericHierarchy() throws Throwable {
        if (this.genericHierarchy == null) {
            File file = new File(this.dir, new StringBuffer().append(GENERIC).append(CONFIG_FILE_EXT).toString());
            if (file.exists()) {
                this.genericHierarchy = new Hierarchy(new RootLogger(Level.DEBUG));
                new PropertyConfigurator().doConfigure(file.getAbsolutePath(), this.genericHierarchy);
            } else {
                cat.warn(new StringBuffer("Could not find config file [").append(file).append("]. Will use the default hierarchy.").toString());
                this.genericHierarchy = LogManager.getLoggerRepository();
            }
        }
        return this.genericHierarchy;
    }
}
