package com.tom_roush.pdfbox.cos;

import com.tom_roush.pdfbox.filter.DecodeResult;
import com.tom_roush.pdfbox.filter.Filter;
import com.tom_roush.pdfbox.p022io.RandomAccess;
import com.tom_roush.pdfbox.p022io.RandomAccessInputStream;
import com.tom_roush.pdfbox.p022io.RandomAccessOutputStream;
import com.tom_roush.pdfbox.p022io.ScratchFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class COSInputStream extends FilterInputStream {
    private final List<DecodeResult> decodeResults;

    static COSInputStream create(List<Filter> list, COSDictionary cOSDictionary, InputStream inputStream, ScratchFile scratchFile) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (scratchFile != null) {
                    final RandomAccess randomAccessCreateBuffer = scratchFile.createBuffer();
                    arrayList.add(list.get(i).decode(inputStream, new RandomAccessOutputStream(randomAccessCreateBuffer), cOSDictionary, i));
                    inputStream = new RandomAccessInputStream(randomAccessCreateBuffer) {
                        @Override
                        public void close() throws IOException {
                            randomAccessCreateBuffer.close();
                        }
                    };
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    arrayList.add(list.get(i).decode(inputStream, byteArrayOutputStream, cOSDictionary, i));
                    inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                }
            }
        }
        return new COSInputStream(inputStream, arrayList);
    }

    private COSInputStream(InputStream inputStream, List<DecodeResult> list) {
        super(inputStream);
        this.decodeResults = list;
    }

    public DecodeResult getDecodeResult() {
        if (this.decodeResults.isEmpty()) {
            return DecodeResult.DEFAULT;
        }
        return this.decodeResults.get(r0.size() - 1);
    }
}
