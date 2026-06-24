package org.apache.http.entity.mime.content;

import java.nio.charset.Charset;
import org.apache.http.entity.ContentType;
import org.apache.http.util.Args;

public abstract class AbstractContentBody implements ContentBody {
    private final ContentType contentType;

    @Deprecated
    public AbstractContentBody(String str) {
        this(ContentType.parse(str));
    }

    public AbstractContentBody(ContentType contentType) {
        Args.notNull(contentType, "Content type");
        this.contentType = contentType;
    }

    @Override
    public String getCharset() {
        Charset charset = this.contentType.getCharset();
        if (charset != null) {
            return charset.name();
        }
        return null;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    @Override
    public String getMediaType() {
        String mimeType = this.contentType.getMimeType();
        int iIndexOf = mimeType.indexOf(47);
        return iIndexOf != -1 ? mimeType.substring(0, iIndexOf) : mimeType;
    }

    @Override
    public String getMimeType() {
        return this.contentType.getMimeType();
    }

    @Override
    public String getSubType() {
        String mimeType = this.contentType.getMimeType();
        int iIndexOf = mimeType.indexOf(47);
        if (iIndexOf != -1) {
            return mimeType.substring(iIndexOf + 1);
        }
        return null;
    }
}
