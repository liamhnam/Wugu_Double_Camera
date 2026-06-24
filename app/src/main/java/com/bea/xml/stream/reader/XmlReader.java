package com.bea.xml.stream.reader;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.util.Hashtable;
import kotlin.UByte;
import org.apache.http.protocol.HTTP;

public final class XmlReader extends Reader {
    private static final int MAXPUSHBACK = 512;
    private static final Hashtable charsets;
    private String assignedEncoding;
    private boolean closed;

    private Reader f469in;

    public static Reader createReader(InputStream inputStream) throws IOException {
        return new XmlReader(inputStream);
    }

    public static Reader createReader(InputStream inputStream, String str) throws IOException {
        if (str == null) {
            return new XmlReader(inputStream);
        }
        if ("UTF-8".equalsIgnoreCase(str) || "UTF8".equalsIgnoreCase(str)) {
            return new Utf8Reader(inputStream);
        }
        if ("US-ASCII".equalsIgnoreCase(str) || HTTP.ASCII.equalsIgnoreCase(str)) {
            return new AsciiReader(inputStream);
        }
        if ("ISO-8859-1".equalsIgnoreCase(str)) {
            return new Iso8859_1Reader(inputStream);
        }
        return new InputStreamReader(inputStream, std2java(str));
    }

    static {
        Hashtable hashtable = new Hashtable(31);
        charsets = hashtable;
        hashtable.put("UTF-16", "Unicode");
        hashtable.put("ISO-10646-UCS-2", "Unicode");
        hashtable.put("EBCDIC-CP-US", "cp037");
        hashtable.put("EBCDIC-CP-CA", "cp037");
        hashtable.put("EBCDIC-CP-NL", "cp037");
        hashtable.put("EBCDIC-CP-WT", "cp037");
        hashtable.put("EBCDIC-CP-DK", "cp277");
        hashtable.put("EBCDIC-CP-NO", "cp277");
        hashtable.put("EBCDIC-CP-FI", "cp278");
        hashtable.put("EBCDIC-CP-SE", "cp278");
        hashtable.put("EBCDIC-CP-IT", "cp280");
        hashtable.put("EBCDIC-CP-ES", "cp284");
        hashtable.put("EBCDIC-CP-GB", "cp285");
        hashtable.put("EBCDIC-CP-FR", "cp297");
        hashtable.put("EBCDIC-CP-AR1", "cp420");
        hashtable.put("EBCDIC-CP-HE", "cp424");
        hashtable.put("EBCDIC-CP-BE", "cp500");
        hashtable.put("EBCDIC-CP-CH", "cp500");
        hashtable.put("EBCDIC-CP-ROECE", "cp870");
        hashtable.put("EBCDIC-CP-YU", "cp870");
        hashtable.put("EBCDIC-CP-IS", "cp871");
        hashtable.put("EBCDIC-CP-AR2", "cp918");
    }

    private static String std2java(String str) {
        String str2 = (String) charsets.get(str.toUpperCase());
        return str2 != null ? str2 : str;
    }

    public String getEncoding() {
        return this.assignedEncoding;
    }

    private XmlReader(InputStream inputStream) throws IOException {
        super(inputStream);
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 512);
        byte[] bArr = new byte[4];
        int i = pushbackInputStream.read(bArr);
        if (i > 0) {
            pushbackInputStream.unread(bArr, 0, i);
        }
        if (i == 4) {
            int i2 = bArr[0] & UByte.MAX_VALUE;
            if (i2 != 0) {
                if (i2 == 60) {
                    int i3 = bArr[1] & UByte.MAX_VALUE;
                    if (i3 != 0) {
                        if (i3 == 63 && bArr[2] == 120 && bArr[3] == 109) {
                            useEncodingDecl(pushbackInputStream, "UTF8");
                            return;
                        }
                    } else if (bArr[2] == 63 && bArr[3] == 0) {
                        setEncoding(pushbackInputStream, "UnicodeLittle");
                        return;
                    }
                } else if (i2 != 76) {
                    if (i2 != 254) {
                        if (i2 == 255 && (bArr[1] & UByte.MAX_VALUE) == 254) {
                            setEncoding(pushbackInputStream, "UTF-16");
                            return;
                        }
                    } else if ((bArr[1] & UByte.MAX_VALUE) == 255) {
                        setEncoding(pushbackInputStream, "UTF-16");
                        return;
                    }
                } else if (bArr[1] == 111 && (bArr[2] & UByte.MAX_VALUE) == 167 && (bArr[3] & UByte.MAX_VALUE) == 148) {
                    useEncodingDecl(pushbackInputStream, "CP037");
                    return;
                }
            } else if (bArr[1] == 60 && bArr[2] == 0 && bArr[3] == 63) {
                setEncoding(pushbackInputStream, "UnicodeBig");
                return;
            }
        }
        setEncoding(pushbackInputStream, "UTF-8");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void useEncodingDecl(java.io.PushbackInputStream r18, java.lang.String r19) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.reader.XmlReader.useEncodingDecl(java.io.PushbackInputStream, java.lang.String):void");
    }

    private void setEncoding(InputStream inputStream, String str) throws IOException {
        this.assignedEncoding = str;
        this.f469in = createReader(inputStream, str);
    }

    @Override
    public int read(char[] cArr, int i, int i2) throws IOException {
        if (this.closed) {
            return -1;
        }
        int i3 = this.f469in.read(cArr, i, i2);
        if (i3 == -1) {
            close();
        }
        return i3;
    }

    @Override
    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
        int i = this.f469in.read();
        if (i == -1) {
            close();
        }
        return i;
    }

    @Override
    public boolean markSupported() {
        Reader reader = this.f469in;
        if (reader == null) {
            return false;
        }
        return reader.markSupported();
    }

    @Override
    public void mark(int i) throws IOException {
        Reader reader = this.f469in;
        if (reader != null) {
            reader.mark(i);
        }
    }

    @Override
    public void reset() throws IOException {
        Reader reader = this.f469in;
        if (reader != null) {
            reader.reset();
        }
    }

    @Override
    public long skip(long j) throws IOException {
        Reader reader = this.f469in;
        if (reader == null) {
            return 0L;
        }
        return reader.skip(j);
    }

    @Override
    public boolean ready() throws IOException {
        Reader reader = this.f469in;
        if (reader == null) {
            return false;
        }
        return reader.ready();
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.f469in.close();
        this.f469in = null;
        this.closed = true;
    }

    public static abstract class BaseReader extends Reader {
        protected byte[] buffer;
        protected int finish;
        protected InputStream instream;
        protected int start;

        public abstract String getEncoding();

        BaseReader(InputStream inputStream) {
            super(inputStream);
            this.instream = inputStream;
            this.buffer = new byte[8192];
        }

        @Override
        public boolean ready() throws IOException {
            InputStream inputStream = this.instream;
            return inputStream == null || this.finish - this.start > 0 || inputStream.available() != 0;
        }

        @Override
        public void close() throws IOException {
            InputStream inputStream = this.instream;
            if (inputStream != null) {
                inputStream.close();
                this.finish = 0;
                this.start = 0;
                this.buffer = null;
                this.instream = null;
            }
        }
    }

    static final class Utf8Reader extends BaseReader {
        private char nextChar;

        @Override
        public String getEncoding() {
            return "UTF-8";
        }

        Utf8Reader(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int read(char[] r10, int r11, int r12) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 454
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.reader.XmlReader.Utf8Reader.read(char[], int, int):int");
        }
    }

    static final class AsciiReader extends BaseReader {
        @Override
        public String getEncoding() {
            return "US-ASCII";
        }

        AsciiReader(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.instream == null) {
                return -1;
            }
            if (i + i2 > cArr.length || i < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = this.finish - this.start;
            if (i3 < 1) {
                this.start = 0;
                this.finish = this.instream.read(this.buffer, 0, this.buffer.length);
                if (this.finish <= 0) {
                    close();
                    return -1;
                }
                if (i2 > this.finish) {
                    i2 = this.finish;
                }
            } else if (i2 > i3) {
                i2 = i3;
            }
            for (int i4 = 0; i4 < i2; i4++) {
                byte[] bArr = this.buffer;
                int i5 = this.start;
                this.start = i5 + 1;
                byte b = bArr[i5];
                if (b < 0) {
                    throw new CharConversionException(new StringBuffer("Illegal ASCII character, 0x").append(Integer.toHexString(b & UByte.MAX_VALUE)).toString());
                }
                cArr[i + i4] = (char) b;
            }
            return i2;
        }
    }

    static final class Iso8859_1Reader extends BaseReader {
        @Override
        public String getEncoding() {
            return "ISO-8859-1";
        }

        Iso8859_1Reader(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.instream == null) {
                return -1;
            }
            if (i + i2 > cArr.length || i < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = this.finish - this.start;
            if (i3 < 1) {
                this.start = 0;
                this.finish = this.instream.read(this.buffer, 0, this.buffer.length);
                if (this.finish <= 0) {
                    close();
                    return -1;
                }
                if (i2 > this.finish) {
                    i2 = this.finish;
                }
            } else if (i2 > i3) {
                i2 = i3;
            }
            for (int i4 = 0; i4 < i2; i4++) {
                byte[] bArr = this.buffer;
                int i5 = this.start;
                this.start = i5 + 1;
                cArr[i + i4] = (char) (bArr[i5] & UByte.MAX_VALUE);
            }
            return i2;
        }
    }
}
