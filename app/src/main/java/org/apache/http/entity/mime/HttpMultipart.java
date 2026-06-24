package org.apache.http.entity.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
public class HttpMultipart extends AbstractMultipartForm {
    private final HttpMultipartMode mode;
    private final List<FormBodyPart> parts;

    static class C28081 {
        static final int[] $SwitchMap$org$apache$http$entity$mime$HttpMultipartMode;

        static {
            int[] iArr = new int[HttpMultipartMode.values().length];
            $SwitchMap$org$apache$http$entity$mime$HttpMultipartMode = iArr;
            try {
                iArr[HttpMultipartMode.BROWSER_COMPATIBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public HttpMultipart(String str, String str2) {
        this(str, null, str2);
    }

    public HttpMultipart(String str, Charset charset, String str2) {
        this(str, charset, str2, HttpMultipartMode.STRICT);
    }

    public HttpMultipart(String str, Charset charset, String str2, HttpMultipartMode httpMultipartMode) {
        super(str, charset, str2);
        this.mode = httpMultipartMode;
        this.parts = new ArrayList();
    }

    public void addBodyPart(FormBodyPart formBodyPart) {
        if (formBodyPart == null) {
            return;
        }
        this.parts.add(formBodyPart);
    }

    @Override
    protected void formatMultipartHeader(FormBodyPart formBodyPart, OutputStream outputStream) throws IOException {
        Header header = formBodyPart.getHeader();
        if (C28081.$SwitchMap$org$apache$http$entity$mime$HttpMultipartMode[this.mode.ordinal()] != 1) {
            Iterator<MinimalField> it = header.iterator();
            while (it.hasNext()) {
                AbstractMultipartForm.writeField(it.next(), outputStream);
            }
        } else {
            AbstractMultipartForm.writeField(header.getField("Content-Disposition"), this.charset, outputStream);
            if (formBodyPart.getBody().getFilename() != null) {
                AbstractMultipartForm.writeField(header.getField("Content-Type"), this.charset, outputStream);
            }
        }
    }

    @Override
    public List<FormBodyPart> getBodyParts() {
        return this.parts;
    }

    @Override
    public String getBoundary() {
        return super.getBoundary();
    }

    @Override
    public Charset getCharset() {
        return super.getCharset();
    }

    public HttpMultipartMode getMode() {
        return this.mode;
    }

    @Override
    public String getSubType() {
        return super.getSubType();
    }

    @Override
    public long getTotalLength() {
        return super.getTotalLength();
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(outputStream);
    }
}
