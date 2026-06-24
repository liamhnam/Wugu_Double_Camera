package com.proembed.service;

import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketClient {
    public static final String TAG = "liao-IPCSocket";

    public BufferedReader f2211in;
    private boolean isConnected;
    public SocketAddress localAddress;
    public Socket mSocket;
    public InputStreamReader mSocketInputStream;
    public DataOutputStream mSocketOutputStream;
    public PrintWriter out;
    private boolean stopRead;

    public SocketClient() {
        this.mSocket = null;
        this.isConnected = false;
        this.stopRead = true;
        try {
            this.mSocket = new Socket("127.0.0.1", 7078);
            this.isConnected = true;
            this.stopRead = false;
        } catch (Exception e) {
            this.isConnected = false;
            this.stopRead = true;
            Log.i(TAG, "Connect fail");
            e.printStackTrace();
        }
    }

    public void setKeyVal(String str, String str2) {
        sendMsg(str + "=" + str2);
    }

    public void sendMsg(String str) {
        try {
            PrintWriter printWriter = new PrintWriter(this.mSocket.getOutputStream());
            this.out = printWriter;
            printWriter.println(str);
            this.out.flush();
        } catch (IOException unused) {
            Log.d(TAG, "sendMsg faile");
        }
    }

    public String read() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mSocket.getInputStream()));
            this.f2211in = bufferedReader;
            String line = bufferedReader.readLine();
            return line == null ? "" : line;
        } catch (IOException unused) {
            Log.d(TAG, "read faile");
            return "";
        }
    }

    private class ConnectSocketThread extends Thread {
        private ConnectSocketThread() {
        }

        @Override
        public void run() {
            try {
                SocketClient.this.mSocket = new Socket("127.0.0.1", 7078);
                SocketClient.this.isConnected = true;
                SocketClient.this.stopRead = false;
            } catch (Exception unused) {
                SocketClient.this.isConnected = false;
                SocketClient.this.stopRead = true;
                Log.i(SocketClient.TAG, "Connect fail");
            }
        }
    }

    public void closeSocket() {
        try {
            this.mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnect() {
        return this.isConnected;
    }

    private boolean isLinked() {
        Socket socket = this.mSocket;
        return socket != null && this.isConnected && !socket.isClosed() && this.mSocket.isBound() && this.mSocket.isConnected();
    }
}
