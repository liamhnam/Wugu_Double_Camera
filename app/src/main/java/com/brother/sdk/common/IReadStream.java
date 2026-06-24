package com.brother.sdk.common;

import java.io.IOException;

public interface IReadStream extends IUnknown {

    public static final String f478ID = "IReadStream";

    int length();

    int read(byte[] bArr, int i, int i2) throws IOException;

    void reset();
}
