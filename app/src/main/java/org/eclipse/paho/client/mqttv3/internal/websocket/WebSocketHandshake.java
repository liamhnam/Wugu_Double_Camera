package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.spi.LocationInfo;

public class WebSocketHandshake {
    private static final String ACCEPT_SALT = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final String EMPTY = "";
    private static final String HTTP_HEADER_CONNECTION = "connection";
    private static final String HTTP_HEADER_CONNECTION_VALUE = "upgrade";
    private static final String HTTP_HEADER_SEC_WEBSOCKET_ACCEPT = "sec-websocket-accept";
    private static final String HTTP_HEADER_SEC_WEBSOCKET_PROTOCOL = "sec-websocket-protocol";
    private static final String HTTP_HEADER_UPGRADE = "upgrade";
    private static final String HTTP_HEADER_UPGRADE_WEBSOCKET = "websocket";
    private static final String LINE_SEPARATOR = "\r\n";
    private static final String SHA1_PROTOCOL = "SHA1";
    String host;
    InputStream input;
    OutputStream output;
    int port;
    String uri;

    public WebSocketHandshake(InputStream inputStream, OutputStream outputStream, String str, String str2, int i) {
        this.input = inputStream;
        this.output = outputStream;
        this.uri = str;
        this.host = str2;
        this.port = i;
    }

    public void execute() throws IOException {
        String strEncode = Base64.encode(new StringBuffer("mqtt3-").append(System.currentTimeMillis() / 1000).toString());
        sendHandshakeRequest(strEncode);
        receiveHandshakeResponse(strEncode);
    }

    private void sendHandshakeRequest(String str) throws IOException {
        try {
            String rawPath = "/mqtt";
            URI uri = new URI(this.uri);
            if (uri.getRawPath() != null && uri.getRawPath().length() != 0) {
                rawPath = uri.getRawPath();
                if (uri.getRawQuery() != null && uri.getRawQuery().length() != 0) {
                    rawPath = new StringBuffer(String.valueOf(rawPath)).append(LocationInfo.f2800NA).append(uri.getRawQuery()).toString();
                }
            }
            PrintWriter printWriter = new PrintWriter(this.output);
            printWriter.print(new StringBuffer("GET ").append(rawPath).append(" HTTP/1.1\r\n").toString());
            int i = this.port;
            if (i != 80 && i != 443) {
                printWriter.print(new StringBuffer("Host: ").append(this.host).append(":").append(this.port).append(LINE_SEPARATOR).toString());
            } else {
                printWriter.print(new StringBuffer("Host: ").append(this.host).append(LINE_SEPARATOR).toString());
            }
            printWriter.print("Upgrade: websocket\r\n");
            printWriter.print("Connection: Upgrade\r\n");
            printWriter.print(new StringBuffer("Sec-WebSocket-Key: ").append(str).append(LINE_SEPARATOR).toString());
            printWriter.print("Sec-WebSocket-Protocol: mqttv3.1\r\n");
            printWriter.print("Sec-WebSocket-Version: 13\r\n");
            String userInfo = uri.getUserInfo();
            if (userInfo != null) {
                printWriter.print(new StringBuffer("Authorization: Basic ").append(Base64.encode(userInfo)).append(LINE_SEPARATOR).toString());
            }
            printWriter.print(LINE_SEPARATOR);
            printWriter.flush();
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void receiveHandshakeResponse(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.input));
        ArrayList arrayList = new ArrayList();
        String line = bufferedReader.readLine();
        if (line == null) {
            throw new IOException("WebSocket Response header: Invalid response from Server, It may not support WebSockets.");
        }
        while (!line.equals("")) {
            arrayList.add(line);
            line = bufferedReader.readLine();
        }
        Map headers = getHeaders(arrayList);
        String str2 = (String) headers.get(HTTP_HEADER_CONNECTION);
        if (str2 == null || str2.equalsIgnoreCase("upgrade")) {
            throw new IOException("WebSocket Response header: Incorrect connection header");
        }
        if (((String) headers.get("upgrade")).toLowerCase().indexOf(HTTP_HEADER_UPGRADE_WEBSOCKET) == -1) {
            throw new IOException("WebSocket Response header: Incorrect upgrade.");
        }
        if (((String) headers.get(HTTP_HEADER_SEC_WEBSOCKET_PROTOCOL)) == null) {
            throw new IOException("WebSocket Response header: empty sec-websocket-protocol");
        }
        if (!headers.containsKey(HTTP_HEADER_SEC_WEBSOCKET_ACCEPT)) {
            throw new IOException("WebSocket Response header: Missing Sec-WebSocket-Accept");
        }
        try {
            verifyWebSocketKey(str, (String) headers.get(HTTP_HEADER_SEC_WEBSOCKET_ACCEPT));
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        } catch (HandshakeFailedException unused) {
            throw new IOException("WebSocket Response header: Incorrect Sec-WebSocket-Key");
        }
    }

    private Map getHeaders(ArrayList arrayList) {
        HashMap map = new HashMap();
        for (int i = 1; i < arrayList.size(); i++) {
            String[] strArrSplit = ((String) arrayList.get(i)).split(":");
            map.put(strArrSplit[0].toLowerCase(), strArrSplit[1]);
        }
        return map;
    }

    private void verifyWebSocketKey(String str, String str2) throws HandshakeFailedException, NoSuchAlgorithmException {
        if (!Base64.encodeBytes(sha1(new StringBuffer(String.valueOf(str)).append("258EAFA5-E914-47DA-95CA-C5AB0DC85B11").toString())).trim().equals(str2.trim())) {
            throw new HandshakeFailedException();
        }
    }

    private byte[] sha1(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(SHA1_PROTOCOL).digest(str.getBytes());
    }
}
