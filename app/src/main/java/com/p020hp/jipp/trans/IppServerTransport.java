package com.p020hp.jipp.trans;

import java.io.IOException;
import java.net.URI;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, m1293d2 = {"Lcom/hp/jipp/trans/IppServerTransport;", "", "handle", "Lcom/hp/jipp/trans/IppPacketData;", "uri", "Ljava/net/URI;", "request", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public interface IppServerTransport {
    IppPacketData handle(URI uri, IppPacketData request) throws IOException;
}
