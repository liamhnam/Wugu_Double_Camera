package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import androidx.core.view.ViewCompat;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.jvm.internal.ByteCompanionObject;

public abstract class AsnObject implements SnmpConstants {
    static int debug;
    protected byte type;
    protected int startPos = 0;
    protected int headerLength = 0;
    protected int contentsLength = 0;
    protected boolean isCorrect = true;

    AsnObject add(AsnObject asnObject) {
        return asnObject;
    }

    AsnObject findPdu() {
        return null;
    }

    AsnObject findTrapPduv1() {
        return null;
    }

    int getLengthBytes(int i) {
        if (i < 128) {
            return 1;
        }
        int i2 = ViewCompat.MEASURED_STATE_MASK;
        int i3 = 4;
        while ((i & i2) == 0) {
            i2 >>= 8;
            i3--;
        }
        return i3 + 1;
    }

    int size() {
        return 0;
    }

    public abstract String toString();

    abstract void write(OutputStream outputStream, int i) throws IOException;

    public static void setDebug(int i) {
        debug = i;
    }

    AsnObject AsnReadHeader(InputStream inputStream) throws IOException {
        return AsnReadHeader(inputStream, 0);
    }

    AsnObject AsnReadHeader(InputStream inputStream, int i) throws IOException {
        int iAvailable = inputStream.available();
        byte b = (byte) inputStream.read();
        this.type = b;
        if (b != -1) {
            int lengthPacket = getLengthPacket(inputStream);
            int iAvailable2 = iAvailable - inputStream.available();
            byte[] bArr = new byte[lengthPacket];
            int i2 = lengthPacket > 0 ? inputStream.read(bArr, 0, lengthPacket) : 0;
            if (i2 > -1) {
                AsnObject asnObjectAsnMakeMe = AsnMakeMe(new ByteArrayInputStream(bArr), this.type, lengthPacket, i, iAvailable2);
                if (i2 == lengthPacket) {
                    return asnObjectAsnMakeMe;
                }
                asnObjectAsnMakeMe.isCorrect = false;
                if (debug <= 10) {
                    return asnObjectAsnMakeMe;
                }
                System.out.println("\nAsnObject.AsnReadHeader(): incorrect packet. Length = " + lengthPacket + ", Got = " + i2);
                return asnObjectAsnMakeMe;
            }
            if (debug > 10) {
                System.out.println("\nAsnObject.AsnReadHeader(): Length = " + lengthPacket + ", Got = " + i2 + ". Not processing any further.");
            }
        }
        return null;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject AsnMakeMe(java.io.InputStream r3, byte r4, int r5, int r6, int r7) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject.AsnMakeMe(java.io.InputStream, byte, int, int, int):com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject");
    }

    void AsnBuildHeader(OutputStream outputStream, byte b, int i) throws IOException {
        this.type = b;
        outputStream.write(b);
        int lengthBytes = getLengthBytes(i);
        this.headerLength = lengthBytes + 1;
        this.contentsLength = i;
        if (debug > 10) {
            System.out.println("AsnBuildHeader(): type = 0x" + SnmpUtilities.toHex(this.type) + ", headerLength = " + this.headerLength + ", contentsLength = " + this.contentsLength);
        }
        if (lengthBytes > 1) {
            lengthBytes--;
            outputStream.write((byte) (((byte) lengthBytes) | 128));
        }
        while (lengthBytes != 0) {
            lengthBytes--;
            outputStream.write((byte) ((i >> (lengthBytes << 3)) & 255));
        }
    }

    int getLengthPacket(InputStream inputStream) throws IOException {
        byte b = (byte) inputStream.read();
        if ((b & 128) == 0) {
            return b & ByteCompanionObject.MAX_VALUE;
        }
        int i = b & ByteCompanionObject.MAX_VALUE;
        if (i >= 4) {
            return 0;
        }
        byte[] bArr = new byte[i];
        if (inputStream.read(bArr, 0, i) != i) {
            throw new IOException("AsnObject.getLengthPacket(): Not enough data");
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        int unsignedByte = 0;
        for (int i2 = 0; i2 < i; i2++) {
            unsignedByte = (unsignedByte << 8) + dataInputStream.readUnsignedByte();
        }
        return unsignedByte;
    }

    int getContentsPos() {
        return this.startPos + this.headerLength;
    }

    int getContentsLength() {
        return this.contentsLength;
    }

    public byte getRespType() {
        return this.type;
    }

    public String getRespTypeString() {
        byte b = this.type;
        if (b == -64) {
            return "ASN_PRIVATE";
        }
        if (b == 16) {
            return "ASN_SEQUENCE";
        }
        if (b == 17) {
            return "ASN_SET";
        }
        if (b == 31) {
            return "ASN_EXTENSION_ID";
        }
        if (b == 32) {
            return "ASN_CONSTRUCTOR";
        }
        switch (b) {
            case 0:
                return "ASN_UNIVERSAL";
            case 1:
                return "ASN_BOOLEAN";
            case 2:
                return "ASN_INTEGER";
            case 3:
                return "ASN_BIT_STR";
            case 4:
                return "ASN_OCTET_STR";
            case 5:
                return "ASN_NULL";
            case 6:
                return "ASN_OBJECT_ID";
            default:
                switch (b) {
                    case 64:
                        return "IPADDRESS";
                    case 65:
                        return "COUNTER";
                    case 66:
                        return "GAUGE";
                    case 67:
                        return "TIMETICKS";
                    case 68:
                        return "OPAQUE";
                    case 69:
                        return "NSAP_ADDRESS";
                    case 70:
                        return "COUNTER64";
                    case 71:
                        return "UINTEGER32";
                    default:
                        return "0x" + SnmpUtilities.toHex(this.type);
                }
        }
    }
}
