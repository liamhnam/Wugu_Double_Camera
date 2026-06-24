package org.apache.log4j.helpers;

import java.io.IOException;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SyslogWriter extends Writer {
    static String syslogHost;
    final int SYSLOG_PORT = 514;
    private InetAddress address;

    private DatagramSocket f2791ds;
    private final int port;

    @Override
    public void flush() {
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SyslogWriter(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = ". All logging will FAIL."
            java.lang.String r1 = "http://"
            r7.<init>()
            r2 = 514(0x202, float:7.2E-43)
            r7.SYSLOG_PORT = r2
            org.apache.log4j.helpers.SyslogWriter.syslogHost = r8
            if (r8 == 0) goto Lb3
            java.lang.String r3 = "["
            int r4 = r8.indexOf(r3)
            r5 = -1
            if (r4 != r5) goto L27
            r4 = 58
            int r6 = r8.indexOf(r4)
            int r4 = r8.lastIndexOf(r4)
            if (r6 != r4) goto L25
            goto L27
        L25:
            r1 = r5
            goto L6c
        L27:
            java.net.URL r4 = new java.net.URL     // Catch: java.net.MalformedURLException -> L65
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch: java.net.MalformedURLException -> L65
            r6.<init>(r1)     // Catch: java.net.MalformedURLException -> L65
            java.lang.StringBuffer r1 = r6.append(r8)     // Catch: java.net.MalformedURLException -> L65
            java.lang.String r1 = r1.toString()     // Catch: java.net.MalformedURLException -> L65
            r4.<init>(r1)     // Catch: java.net.MalformedURLException -> L65
            java.lang.String r1 = r4.getHost()     // Catch: java.net.MalformedURLException -> L65
            if (r1 == 0) goto L25
            java.lang.String r8 = r4.getHost()     // Catch: java.net.MalformedURLException -> L65
            boolean r1 = r8.startsWith(r3)     // Catch: java.net.MalformedURLException -> L65
            if (r1 == 0) goto L60
            int r1 = r8.length()     // Catch: java.net.MalformedURLException -> L65
            r3 = 1
            int r1 = r1 - r3
            char r1 = r8.charAt(r1)     // Catch: java.net.MalformedURLException -> L65
            r6 = 93
            if (r1 != r6) goto L60
            int r1 = r8.length()     // Catch: java.net.MalformedURLException -> L65
            int r1 = r1 - r3
            java.lang.String r8 = r8.substring(r3, r1)     // Catch: java.net.MalformedURLException -> L65
        L60:
            int r1 = r4.getPort()     // Catch: java.net.MalformedURLException -> L65
            goto L6c
        L65:
            r1 = move-exception
            java.lang.String r3 = "Malformed URL: will attempt to interpret as InetAddress."
            org.apache.log4j.helpers.LogLog.error(r3, r1)
            goto L25
        L6c:
            if (r1 != r5) goto L6f
            goto L70
        L6f:
            r2 = r1
        L70:
            r7.port = r2
            java.net.InetAddress r1 = java.net.InetAddress.getByName(r8)     // Catch: java.net.UnknownHostException -> L79
            r7.address = r1     // Catch: java.net.UnknownHostException -> L79
            goto L90
        L79:
            r1 = move-exception
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            java.lang.String r3 = "Could not find "
            r2.<init>(r3)
            java.lang.StringBuffer r2 = r2.append(r8)
            java.lang.StringBuffer r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            org.apache.log4j.helpers.LogLog.error(r2, r1)
        L90:
            java.net.DatagramSocket r1 = new java.net.DatagramSocket     // Catch: java.net.SocketException -> L98
            r1.<init>()     // Catch: java.net.SocketException -> L98
            r7.f2791ds = r1     // Catch: java.net.SocketException -> L98
            goto Lb2
        L98:
            r1 = move-exception
            r1.printStackTrace()
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            java.lang.String r3 = "Could not instantiate DatagramSocket to "
            r2.<init>(r3)
            java.lang.StringBuffer r8 = r2.append(r8)
            java.lang.StringBuffer r8 = r8.append(r0)
            java.lang.String r8 = r8.toString()
            org.apache.log4j.helpers.LogLog.error(r8, r1)
        Lb2:
            return
        Lb3:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r0 = "syslogHost"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.helpers.SyslogWriter.<init>(java.lang.String):void");
    }

    @Override
    public void write(char[] cArr, int i, int i2) throws IOException {
        write(new String(cArr, i, i2));
    }

    @Override
    public void write(String str) throws IOException {
        if (this.f2791ds == null || this.address == null) {
            return;
        }
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        if (length >= 1024) {
            length = 1024;
        }
        this.f2791ds.send(new DatagramPacket(bytes, length, this.address, this.port));
    }

    @Override
    public void close() {
        DatagramSocket datagramSocket = this.f2791ds;
        if (datagramSocket != null) {
            datagramSocket.close();
        }
    }
}
