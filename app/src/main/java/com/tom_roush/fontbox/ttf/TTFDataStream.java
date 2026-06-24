package com.tom_roush.fontbox.ttf;

import androidx.core.view.InputDeviceCompat;
import com.tom_roush.fontbox.util.Charsets;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

abstract class TTFDataStream implements Closeable {
    @Override
    public abstract void close() throws IOException;

    public abstract long getCurrentPosition() throws IOException;

    public abstract InputStream getOriginalData() throws IOException;

    public abstract int read() throws IOException;

    public abstract int read(byte[] bArr, int i, int i2) throws IOException;

    public abstract long readLong() throws IOException;

    public abstract short readSignedShort() throws IOException;

    public abstract int readUnsignedShort() throws IOException;

    public abstract void seek(long j) throws IOException;

    TTFDataStream() {
    }

    public float read32Fixed() throws IOException {
        return (float) (((double) readSignedShort()) + (((double) readUnsignedShort()) / 65536.0d));
    }

    public String readString(int i) throws IOException {
        return readString(i, Charsets.ISO_8859_1);
    }

    public String readString(int i, String str) throws IOException {
        return new String(read(i), str);
    }

    public String readString(int i, Charset charset) throws IOException {
        return new String(read(i), charset);
    }

    public int readSignedByte() throws IOException {
        int i = read();
        return i < 127 ? i : i + InputDeviceCompat.SOURCE_ANY;
    }

    public int readUnsignedByte() throws IOException {
        int i = read();
        if (i != -1) {
            return i;
        }
        throw new EOFException("premature EOF");
    }

    public long readUnsignedInt() throws IOException {
        long j = read();
        long j2 = read();
        long j3 = read();
        long j4 = read();
        if (j4 >= 0) {
            return (j << 24) + (j2 << 16) + (j3 << 8) + (j4 << 0);
        }
        throw new EOFException();
    }

    public int[] readUnsignedByteArray(int i) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = read();
        }
        return iArr;
    }

    public int[] readUnsignedShortArray(int i) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readUnsignedShort();
        }
        return iArr;
    }

    public Calendar readInternationalDate() throws IOException {
        long j = readLong();
        Calendar gregorianCalendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.set(1904, 0, 1, 0, 0, 0);
        gregorianCalendar.set(14, 0);
        gregorianCalendar.setTimeInMillis(gregorianCalendar.getTimeInMillis() + (j * 1000));
        return gregorianCalendar;
    }

    public String readTag() throws IOException {
        return new String(read(4), Charsets.US_ASCII);
    }

    public byte[] read(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = read(bArr, i2, i - i2);
            if (i3 == -1) {
                break;
            }
            i2 += i3;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Unexpected end of TTF stream reached");
    }
}
