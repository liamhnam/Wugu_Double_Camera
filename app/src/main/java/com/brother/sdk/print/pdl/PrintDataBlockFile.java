package com.brother.sdk.print.pdl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PrintDataBlockFile extends PrintDataBlock {
    private File mFile;

    @Override
    protected InputStream next() {
        return null;
    }

    public PrintDataBlockFile(File file) {
        this.mFile = file;
    }

    @Override
    public int length() {
        return (int) this.mFile.length();
    }

    @Override
    protected InputStream init() {
        try {
            return new FileInputStream(this.mFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
