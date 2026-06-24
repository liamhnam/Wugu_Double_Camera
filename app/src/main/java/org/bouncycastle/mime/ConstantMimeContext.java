package org.bouncycastle.mime;

import java.io.IOException;
import java.io.InputStream;

public class ConstantMimeContext implements MimeContext, MimeMultipartContext {
    @Override
    public InputStream applyContext(Headers headers, InputStream inputStream) throws IOException {
        return inputStream;
    }

    @Override
    public MimeContext createContext(int i) throws IOException {
        return this;
    }
}
