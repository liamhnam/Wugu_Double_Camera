package org.apache.http;

import java.util.Locale;

public interface HttpResponse extends HttpMessage {
    HttpEntity getEntity();

    @Deprecated
    Locale getLocale();

    StatusLine getStatusLine();

    void setEntity(HttpEntity httpEntity);

    @Deprecated
    void setLocale(Locale locale);

    void setReasonPhrase(String str);

    void setStatusCode(int i);

    void setStatusLine(ProtocolVersion protocolVersion, int i);

    void setStatusLine(ProtocolVersion protocolVersion, int i, String str);

    void setStatusLine(StatusLine statusLine);
}
