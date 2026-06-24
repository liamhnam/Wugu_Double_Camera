package org.apache.http.conn.scheme;

import java.net.Socket;

@Deprecated
public interface LayeredSocketFactory extends SocketFactory {
    Socket createSocket(Socket socket, String str, int i, boolean z);
}
