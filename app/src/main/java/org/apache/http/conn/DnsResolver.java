package org.apache.http.conn;

import java.net.InetAddress;

public interface DnsResolver {
    InetAddress[] resolve(String str);
}
