package org.apache.log4j.varia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Roller {
    static Logger cat;
    static Class class$org$apache$log4j$varia$Roller;
    static String host;
    static int port;

    static {
        Class clsClass$ = class$org$apache$log4j$varia$Roller;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.varia.Roller");
            class$org$apache$log4j$varia$Roller = clsClass$;
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

    Roller() {
    }

    public static void main(String[] strArr) throws Throwable {
        BasicConfigurator.configure();
        if (strArr.length == 2) {
            init(strArr[0], strArr[1]);
        } else {
            usage("Wrong number of arguments.");
        }
        roll();
    }

    static void usage(String str) throws Throwable {
        System.err.println(str);
        PrintStream printStream = System.err;
        StringBuffer stringBuffer = new StringBuffer("Usage: java ");
        Class clsClass$ = class$org$apache$log4j$varia$Roller;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.varia.Roller");
            class$org$apache$log4j$varia$Roller = clsClass$;
        }
        printStream.println(stringBuffer.append(clsClass$.getName()).append("host_name port_number").toString());
        System.exit(1);
    }

    static void init(String str, String str2) throws Throwable {
        host = str;
        try {
            port = Integer.parseInt(str2);
        } catch (NumberFormatException unused) {
            usage(new StringBuffer("Second argument ").append(str2).append(" is not a valid integer.").toString());
        }
    }

    static void roll() {
        try {
            Socket socket = new Socket(host, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream.writeUTF(ExternallyRolledFileAppender.ROLL_OVER);
            String utf = dataInputStream.readUTF();
            if (ExternallyRolledFileAppender.f2804OK.equals(utf)) {
                cat.info("Roll over signal acknowledged by remote appender.");
            } else {
                cat.warn(new StringBuffer("Unexpected return code ").append(utf).append(" from remote entity.").toString());
                System.exit(2);
            }
        } catch (IOException e) {
            cat.error(new StringBuffer("Could not send roll signal on host ").append(host).append(" port ").append(port).append(" .").toString(), e);
            System.exit(2);
        }
        System.exit(0);
    }
}
