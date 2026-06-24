package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AsnUnsInteger extends AsnObject {
    protected long value;

    public AsnUnsInteger(long j) {
        this(j, SnmpConstants.TIMETICKS);
    }

    public AsnUnsInteger(long j, byte b) {
        this.value = j;
        this.type = b;
    }

    public AsnUnsInteger(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        if (i != inputStream.read(bArr, 0, i)) {
            throw new IOException("AsnUnsInteger(): Not enough data");
        }
        this.value = bytesToLong(bArr);
    }

    public long getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    int size() {
        int i;
        int i2;
        if (this.value < 0) {
            i = 255;
            i2 = 128;
        } else {
            i = 0;
            i2 = 0;
        }
        int i3 = 24;
        while (i3 > 0 && ((this.value >> i3) & 255) == i) {
            i3 -= 8;
        }
        if (((this.value >> i3) & 128) != i2) {
            i3 += 8;
        }
        return (i3 >> 3) + 1;
    }

    @Override
    void write(OutputStream outputStream, int i) throws IOException {
        int i2;
        int i3;
        if (this.value < 0) {
            i2 = 255;
            i3 = 128;
        } else {
            i2 = 0;
            i3 = 0;
        }
        int i4 = 24;
        while (i4 > 0 && ((this.value >> i4) & 255) == i2) {
            i4 -= 8;
        }
        if (((this.value >> i4) & 128) != i3) {
            i4 += 8;
        }
        AsnBuildHeader(outputStream, this.type, (i4 >> 3) + 1);
        if (debug > 10) {
            System.out.println("\tAsnUnsInteger(): value = " + this.value + ", pos = " + i);
        }
        while (i4 >= 0) {
            outputStream.write((byte) ((this.value >> i4) & 255));
            i4 -= 8;
        }
    }

    protected long bytesToLong(byte[] bArr) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        int length = bArr.length;
        long unsignedByte = 0;
        for (int i = 0; i < length; i++) {
            unsignedByte = (unsignedByte << 8) + ((long) dataInputStream.readUnsignedByte());
        }
        return unsignedByte;
    }

    public boolean equals(Object obj) {
        return (obj instanceof AsnUnsInteger) && this.value == ((AsnUnsInteger) obj).value;
    }

    public int hashCode() {
        long j = this.value;
        return (int) (j ^ (j >>> 32));
    }
}
