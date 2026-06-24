package org.apache.http.message;

import com.tom_roush.fontbox.afm.AFMParser;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionURI;
import java.io.Serializable;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

public class BasicRequestLine implements Serializable, Cloneable, RequestLine {
    private static final long serialVersionUID = 2810581718468737193L;
    private final String method;
    private final ProtocolVersion protoversion;
    private final String uri;

    public BasicRequestLine(String str, String str2, ProtocolVersion protocolVersion) {
        this.method = (String) Args.notNull(str, "Method");
        this.uri = (String) Args.notNull(str2, PDActionURI.SUB_TYPE);
        this.protoversion = (ProtocolVersion) Args.notNull(protocolVersion, AFMParser.VERSION);
    }

    public Object clone() {
        return super.clone();
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return this.protoversion;
    }

    @Override
    public String getUri() {
        return this.uri;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatRequestLine((CharArrayBuffer) null, this).toString();
    }
}
