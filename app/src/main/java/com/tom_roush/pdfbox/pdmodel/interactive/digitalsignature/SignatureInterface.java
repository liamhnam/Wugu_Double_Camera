package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.IOException;
import java.io.InputStream;

public interface SignatureInterface {
    byte[] sign(InputStream inputStream) throws IOException;
}
