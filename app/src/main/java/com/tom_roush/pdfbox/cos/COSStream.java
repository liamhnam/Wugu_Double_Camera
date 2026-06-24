package com.tom_roush.pdfbox.cos;

import android.util.Log;
import com.tom_roush.pdfbox.filter.Filter;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.p022io.RandomAccess;
import com.tom_roush.pdfbox.p022io.RandomAccessInputStream;
import com.tom_roush.pdfbox.p022io.RandomAccessOutputStream;
import com.tom_roush.pdfbox.p022io.ScratchFile;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class COSStream extends COSDictionary implements Closeable {
    private boolean isWriting;
    private RandomAccess randomAccess;
    private final ScratchFile scratchFile;

    public COSStream() {
        this(ScratchFile.getMainMemoryOnlyInstance());
    }

    public COSStream(ScratchFile scratchFile) {
        this.scratchFile = scratchFile == null ? ScratchFile.getMainMemoryOnlyInstance() : scratchFile;
    }

    private void checkClosed() throws IOException {
        RandomAccess randomAccess = this.randomAccess;
        if (randomAccess != null && randomAccess.isClosed()) {
            throw new IOException("COSStream has been closed and cannot be read. Perhaps its enclosing PDDocument has been closed?");
        }
    }

    @Deprecated
    public InputStream getFilteredStream() throws IOException {
        return createRawInputStream();
    }

    private void ensureRandomAccessExists(boolean z) throws IOException {
        if (this.randomAccess == null) {
            if (z) {
                Log.d("PdfBox-Android", "Create InputStream called without data being written before to stream.");
            }
            this.randomAccess = this.scratchFile.createBuffer();
        }
    }

    public InputStream createRawInputStream() throws IOException {
        checkClosed();
        if (this.isWriting) {
            throw new IllegalStateException("Cannot read while there is an open stream writer");
        }
        ensureRandomAccessExists(true);
        return new RandomAccessInputStream(this.randomAccess);
    }

    @Deprecated
    public InputStream getUnfilteredStream() throws IOException {
        return createInputStream();
    }

    public COSInputStream createInputStream() throws IOException {
        checkClosed();
        if (this.isWriting) {
            throw new IllegalStateException("Cannot read while there is an open stream writer");
        }
        ensureRandomAccessExists(true);
        return COSInputStream.create(getFilterList(), this, new RandomAccessInputStream(this.randomAccess), this.scratchFile);
    }

    @Deprecated
    public OutputStream createUnfilteredStream() throws IOException {
        return createOutputStream();
    }

    public OutputStream createOutputStream() throws IOException {
        return createOutputStream(null);
    }

    public OutputStream createOutputStream(COSBase cOSBase) throws IOException {
        checkClosed();
        if (this.isWriting) {
            throw new IllegalStateException("Cannot have more than one open stream writer.");
        }
        if (cOSBase != null) {
            setItem(COSName.FILTER, cOSBase);
        }
        this.randomAccess = this.scratchFile.createBuffer();
        COSOutputStream cOSOutputStream = new COSOutputStream(getFilterList(), this, new RandomAccessOutputStream(this.randomAccess), this.scratchFile);
        this.isWriting = true;
        return new FilterOutputStream(cOSOutputStream) {
            @Override
            public void write(byte[] bArr, int i, int i2) throws IOException {
                this.out.write(bArr, i, i2);
            }

            @Override
            public void close() throws IOException {
                super.close();
                COSStream.this.setInt(COSName.LENGTH, (int) COSStream.this.randomAccess.length());
                COSStream.this.isWriting = false;
            }
        };
    }

    @Deprecated
    public OutputStream createFilteredStream() throws IOException {
        return createRawOutputStream();
    }

    public OutputStream createRawOutputStream() throws IOException {
        checkClosed();
        if (this.isWriting) {
            throw new IllegalStateException("Cannot have more than one open stream writer.");
        }
        this.randomAccess = this.scratchFile.createBuffer();
        RandomAccessOutputStream randomAccessOutputStream = new RandomAccessOutputStream(this.randomAccess);
        this.isWriting = true;
        return new FilterOutputStream(randomAccessOutputStream) {
            @Override
            public void write(byte[] bArr, int i, int i2) throws IOException {
                this.out.write(bArr, i, i2);
            }

            @Override
            public void close() throws IOException {
                super.close();
                COSStream.this.setInt(COSName.LENGTH, (int) COSStream.this.randomAccess.length());
                COSStream.this.isWriting = false;
            }
        };
    }

    private List<Filter> getFilterList() throws IOException {
        ArrayList arrayList = new ArrayList();
        COSBase filters = getFilters();
        if (filters instanceof COSName) {
            arrayList.add(FilterFactory.INSTANCE.getFilter((COSName) filters));
        } else if (filters instanceof COSArray) {
            COSArray cOSArray = (COSArray) filters;
            for (int i = 0; i < cOSArray.size(); i++) {
                arrayList.add(FilterFactory.INSTANCE.getFilter((COSName) cOSArray.get(i)));
            }
        }
        return arrayList;
    }

    public long getLength() {
        if (this.isWriting) {
            throw new IllegalStateException("There is an open OutputStream associated with this COSStream. It must be closed before queryinglength of this COSStream.");
        }
        return getInt(COSName.LENGTH, 0);
    }

    public COSBase getFilters() {
        return getDictionaryObject(COSName.FILTER);
    }

    @Deprecated
    public void setFilters(COSBase cOSBase) throws IOException {
        setItem(COSName.FILTER, cOSBase);
    }

    @Deprecated
    public String getString() {
        return toTextString();
    }

    public String toTextString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        COSInputStream cOSInputStreamCreateInputStream = null;
        try {
            cOSInputStreamCreateInputStream = createInputStream();
            IOUtils.copy(cOSInputStreamCreateInputStream, byteArrayOutputStream);
            IOUtils.closeQuietly(cOSInputStreamCreateInputStream);
            return new COSString(byteArrayOutputStream.toByteArray()).getString();
        } catch (IOException unused) {
            IOUtils.closeQuietly(cOSInputStreamCreateInputStream);
            return "";
        } catch (Throwable th) {
            IOUtils.closeQuietly(cOSInputStreamCreateInputStream);
            throw th;
        }
    }

    @Override
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromStream(this);
    }

    @Override
    public void close() throws IOException {
        RandomAccess randomAccess = this.randomAccess;
        if (randomAccess != null) {
            randomAccess.close();
        }
    }
}
