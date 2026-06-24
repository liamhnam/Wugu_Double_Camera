package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import kotlin.jvm.internal.ByteCompanionObject;

public class AsnObjectId extends AsnObject {
    private long[] value;

    private int getSIDLen(long j) {
        int i = 1;
        while (true) {
            j >>= 7;
            if (j == 0) {
                return i;
            }
            i++;
        }
    }

    AsnObjectId() {
        this.value = new long[]{1, 3, 6, 1, 4, 1, 674, 10889, 2, 1, 0};
    }

    AsnObjectId(InputStream inputStream, int i) throws IOException {
        byte b;
        this.value = new long[]{1, 3, 6, 1, 4, 1, 674, 10889, 2, 1, 0};
        byte[] bArr = new byte[i];
        if (i != inputStream.read(bArr, 0, i)) {
            throw new IOException("AsnObjectId(): Not enough data");
        }
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            if (bArr[i3] >= 0) {
                i2++;
            }
        }
        long[] jArr = new long[i2];
        this.value = jArr;
        byte b2 = bArr[0];
        jArr[0] = b2 / 40;
        jArr[1] = b2 % 40;
        int i4 = 1;
        for (int i5 = 2; i5 < this.value.length; i5++) {
            long j = 0;
            do {
                b = bArr[i4];
                j = (j << 7) | ((long) (b & ByteCompanionObject.MAX_VALUE));
                i4++;
            } while (b < 0);
            this.value[i5] = j;
        }
    }

    public AsnObjectId(String str) throws IllegalArgumentException {
        this.value = new long[]{1, 3, 6, 1, 4, 1, 674, 10889, 2, 1, 0};
        this.value = toArrayOfLongs(str);
    }

    private long[] toArrayOfLongs(String str) throws IllegalArgumentException {
        if (str != null && str.length() > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
            long[] jArr = new long[stringTokenizer.countTokens()];
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    jArr[i] = Long.valueOf(stringTokenizer.nextToken()).longValue();
                    i++;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("AsnObjectId(): Bad OID '" + str + "' " + e.getMessage());
                } catch (NoSuchElementException unused) {
                }
            }
            return jArr;
        }
        throw new IllegalArgumentException("AsnObjectId(): Bad OID '" + str + "' ");
    }

    public boolean startsWith(AsnObjectId asnObjectId) {
        if (asnObjectId.value.length >= this.value.length) {
            return false;
        }
        boolean z = true;
        int i = 0;
        while (true) {
            long[] jArr = asnObjectId.value;
            if (i >= jArr.length || !z) {
                break;
            }
            z = jArr[i] == this.value[i];
            i++;
        }
        return z;
    }

    public void add(long j) {
        long[] jArr = this.value;
        int length = jArr.length;
        long[] jArr2 = new long[length + 1];
        this.value = jArr2;
        System.arraycopy(jArr, 0, jArr2, 0, length);
        this.value[length] = j;
    }

    public void add(long[] jArr) {
        long[] jArr2 = this.value;
        int length = jArr2.length;
        int length2 = jArr.length;
        long[] jArr3 = new long[length + length2];
        this.value = jArr3;
        System.arraycopy(jArr2, 0, jArr3, 0, length);
        System.arraycopy(jArr, 0, this.value, length, length2);
    }

    public void add(String str) throws IllegalArgumentException {
        add(toArrayOfLongs(str));
    }

    @Override
    int size() {
        long[] jArr = this.value;
        if (jArr.length > 1) {
            int sIDLen = getSIDLen((jArr[0] * 40) + jArr[1]);
            int i = 2;
            while (true) {
                long[] jArr2 = this.value;
                if (i >= jArr2.length) {
                    return sIDLen;
                }
                sIDLen += getSIDLen(jArr2[i]);
                i++;
            }
        } else {
            if (jArr.length == 1) {
                return getSIDLen(jArr[0] * 40);
            }
            return getSIDLen(0L);
        }
    }

    @Override
    void write(OutputStream outputStream, int i) throws IOException {
        AsnBuildHeader(outputStream, (byte) 6, size());
        if (debug > 10) {
            System.out.println("\tAsnObjectId(): value = " + toString() + ", pos = " + i);
        }
        long[] jArr = this.value;
        if (jArr.length > 1) {
            EncodeSID(outputStream, (jArr[0] * 40) + jArr[1]);
            int i2 = 2;
            while (true) {
                long[] jArr2 = this.value;
                if (i2 >= jArr2.length) {
                    return;
                }
                EncodeSID(outputStream, jArr2[i2]);
                i2++;
            }
        } else if (jArr.length == 1) {
            EncodeSID(outputStream, jArr[0] * 40);
        } else {
            EncodeSID(outputStream, 0L);
        }
    }

    private void EncodeSID(OutputStream outputStream, long j) throws IOException {
        int i = 15;
        int i2 = 28;
        while (i2 > 0 && ((j >> i2) & ((long) i)) == 0) {
            i2 -= 7;
            i = 127;
        }
        while (i2 >= 0) {
            outputStream.write((byte) (((j >> i2) & ((long) i)) | ((long) (i2 > 0 ? 128 : 0))));
            i2 -= 7;
            i = 127;
        }
    }

    public String getValue() {
        return toString();
    }

    @Override
    public String toString() {
        return toString(this.value);
    }

    public String toString(long[] jArr) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < jArr.length - 1 && i < 100; i++) {
            stringBuffer.append(jArr[i]).append(".");
        }
        if (jArr.length - 1 > 100) {
            stringBuffer.append("[.. cut ..].");
        }
        stringBuffer.append(jArr[jArr.length - 1]);
        return stringBuffer.toString();
    }

    public int getSize() {
        return this.value.length;
    }

    public synchronized long getElementAt(int i) throws ArrayIndexOutOfBoundsException {
        long[] jArr;
        jArr = this.value;
        if (i >= jArr.length) {
            throw new ArrayIndexOutOfBoundsException(i + " >= " + this.value.length);
        }
        try {
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ArrayIndexOutOfBoundsException(i + " < 0");
        }
        return jArr[i];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AsnObjectId) {
            long[] jArr = this.value;
            int length = jArr.length;
            long[] jArr2 = ((AsnObjectId) obj).value;
            if (length == jArr2.length) {
                int i = 0;
                int i2 = 0;
                while (true) {
                    int i3 = length - 1;
                    if (length == 0) {
                        return true;
                    }
                    int i4 = i + 1;
                    long j = jArr[i];
                    int i5 = i2 + 1;
                    if (j != jArr2[i2]) {
                        return false;
                    }
                    i2 = i5;
                    i = i4;
                    length = i3;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        long[] jArr = this.value;
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            long j = jArr[i3];
            i2 = (i2 * 31) + ((int) (j ^ (j >>> 32)));
            i++;
            i3++;
        }
        return i2;
    }
}
