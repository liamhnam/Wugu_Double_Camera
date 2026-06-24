package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.Arrays;

public class AsnOctets extends AsnObject {
    static String HEX_PREFIX = "0x";
    private static final String UTF8 = "UTF-8";
    private int hash;
    byte[] value;

    public AsnOctets(char[] cArr) {
        this.hash = 0;
        this.value = new byte[cArr.length];
        this.type = (byte) 4;
        for (int i = 0; i < cArr.length; i++) {
            this.value[i] = (byte) cArr[i];
        }
    }

    public AsnOctets(String str) {
        this(str.toCharArray());
    }

    public AsnOctets(byte[] bArr) throws IllegalArgumentException {
        this(bArr, (byte) 4);
    }

    public AsnOctets(InetAddress inetAddress) throws IllegalArgumentException {
        this(inetAddress.getAddress(), (byte) 64);
    }

    public AsnOctets(byte[] bArr, byte b) throws IllegalArgumentException {
        this.hash = 0;
        if (bArr == null) {
            throw new IllegalArgumentException("Value is null");
        }
        this.value = Arrays.copyOf(bArr, bArr.length);
        this.type = b;
    }

    public AsnOctets(InputStream inputStream, int i) throws IOException {
        this.hash = 0;
        byte[] bArr = new byte[i];
        this.value = bArr;
        if (i != 0 && i != inputStream.read(bArr, 0, i)) {
            throw new IOException("AsnOctets(): Not enough data");
        }
    }

    public static void setHexPrefix(String str) {
        HEX_PREFIX = str;
    }

    public String getValue() {
        return toString();
    }

    public byte[] getBytes() {
        return (byte[]) this.value.clone();
    }

    @Override
    public String toString() {
        if (this.type == 64) {
            return toIpAddress();
        }
        if (this.type == 68) {
            return HEX_PREFIX + toHex();
        }
        int length = this.value.length;
        boolean z = true;
        for (int i = 0; i < length && z; i++) {
            byte b = this.value[i];
            z = (b >= 32 && b <= 126) || Character.isWhitespace((char) b) || this.value[i] == 0;
        }
        if (z) {
            try {
                return new String(this.value, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return "";
            }
        }
        return HEX_PREFIX + toHex();
    }

    @Override
    int size() {
        return this.value.length;
    }

    @Override
    void write(OutputStream outputStream, int i) throws IOException {
        AsnBuildHeader(outputStream, this.type, this.value.length);
        if (debug > 10) {
            System.out.println("\tAsnOctets(): value = " + toString() + ", pos = " + i);
        }
        int i2 = 0;
        while (true) {
            byte[] bArr = this.value;
            if (i2 >= bArr.length) {
                return;
            }
            outputStream.write(bArr[i2]);
            i2++;
        }
    }

    public String toIpAddress() {
        int length = this.value.length;
        if (length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = length - 1;
            if (i < i2) {
                sb.append(String.valueOf(getPositiveValue(i)) + ".");
                i++;
            } else {
                sb.append(String.valueOf(getPositiveValue(i2)));
                return sb.toString();
            }
        }
    }

    private long getPositiveValue(int i) {
        long j = this.value[i];
        return j < 0 ? j + 256 : j;
    }

    public String toHex() {
        int i;
        StringBuffer stringBuffer = new StringBuffer("");
        int length = this.value.length;
        if (length > 0) {
            int i2 = 0;
            while (true) {
                i = length - 1;
                if (i2 >= i) {
                    break;
                }
                stringBuffer.append(SnmpUtilities.toHex(this.value[i2])).append(":");
                i2++;
            }
            stringBuffer.append(SnmpUtilities.toHex(this.value[i]));
        }
        return stringBuffer.toString();
    }

    public String toDisplayString() {
        if (this.value.length > 0) {
            try {
                return new String(this.value, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return "";
    }

    public long[] toSubOid(boolean z) {
        long[] jArr;
        int i;
        int length = this.value.length;
        if (z) {
            jArr = new long[length];
            i = 0;
        } else {
            jArr = new long[length + 1];
            jArr[0] = length;
            i = 1;
        }
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i] = getPositiveValue(i2);
            i++;
        }
        return jArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AsnOctets) {
            byte[] bArr = this.value;
            int length = bArr.length;
            byte[] bArr2 = ((AsnOctets) obj).value;
            if (length == bArr2.length) {
                int i = 0;
                int i2 = 0;
                while (true) {
                    int i3 = length - 1;
                    if (length == 0) {
                        return true;
                    }
                    int i4 = i + 1;
                    int i5 = i2 + 1;
                    if (bArr[i] != bArr2[i2]) {
                        return false;
                    }
                    i = i4;
                    length = i3;
                    i2 = i5;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.hash;
        if (i == 0) {
            byte[] bArr = this.value;
            int length = bArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                i = (i * 31) + bArr[i3];
                i2++;
                i3++;
            }
            this.hash = i;
        }
        return i;
    }
}
