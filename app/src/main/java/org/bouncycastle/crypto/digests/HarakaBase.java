package org.bouncycastle.crypto.digests;

import cc.uling.usdk.constants.MdbAddr;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.p037io.encoding.Base64;
import okio.Utf8;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.signers.PSSSigner;

public abstract class HarakaBase implements Digest {
    protected static final int DIGEST_SIZE = 32;

    private static final byte[][] f2995S = {new byte[]{99, 124, 119, 123, -14, 107, 111, -59, SnmpConstants.CONS_SEQ, 1, 103, 43, -2, -41, -85, 118}, new byte[]{-54, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -55, 125, -6, 89, SnmpConstants.UINTEGER32, -16, -83, -44, SnmpConstants.GET_RSP_MSG, -81, -100, SnmpConstants.TRP_REQ_MSG, 114, SnmpConstants.ASN_PRIVATE}, new byte[]{-73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, SnmpConstants.GETBULK_REQ_MSG, -27, -15, 113, -40, 49, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC}, new byte[]{4, -57, 35, -61, 24, -106, 5, -102, 7, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -128, -30, -21, 39, -78, 117}, new byte[]{9, -125, 44, 26, 27, 110, 90, SnmpConstants.GET_REQ_MSG, 82, 59, -42, -77, 41, -29, 47, -124}, new byte[]{83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49}, new byte[]{-48, -17, -86, -5, SnmpConstants.TIMETICKS, 77, 51, -123, SnmpConstants.NSAP_ADDRESS, -7, 2, ByteCompanionObject.MAX_VALUE, 80, 60, -97, SnmpConstants.GET_RPRT_MSG}, new byte[]{81, SnmpConstants.SET_REQ_MSG, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46}, new byte[]{-51, 12, SnmpConstants.SNMP_ERR_DECODING_EXC, -20, 95, -105, SnmpConstants.OPAQUE, 23, -60, SnmpConstants.TRPV2_REQ_MSG, 126, Base64.padSymbol, 100, 93, 25, 115}, new byte[]{96, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, 79, -36, 34, 42, -112, -120, SnmpConstants.COUNTER64, -18, -72, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, -34, 94, 11, -37}, new byte[]{-32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121}, new byte[]{-25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8}, new byte[]{-70, 120, 37, 46, 28, SnmpConstants.INFORM_REQ_MSG, -76, -58, -24, -35, 116, SnmpConstants.ASN_EXTENSION_ID, 75, -67, -117, -118}, new byte[]{112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98}, new byte[]{MdbAddr.DEF_ADDR, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33}, new byte[]{-116, SnmpConstants.GETNEXT_REQ_MSG, -119, 13, -65, -26, SnmpConstants.GAUGE, 104, SnmpConstants.COUNTER, -103, 45, SnmpConstants.SNMP_ERR_UNDOFAILED, -80, 84, -69, 22}};

    static byte[] aesEnc(byte[] bArr, byte[] bArr2) {
        byte[] bArrMixColumns = mixColumns(shiftRows(subBytes(bArr)));
        xorReverse(bArrMixColumns, bArr2);
        return bArrMixColumns;
    }

    private static byte[] mixColumns(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i + 1;
            int i4 = i2 * 4;
            int i5 = i4 + 1;
            int i6 = i4 + 2;
            int i7 = i4 + 3;
            bArr2[i] = (byte) ((((xTime(bArr[i4]) ^ xTime(bArr[i5])) ^ bArr[i5]) ^ bArr[i6]) ^ bArr[i7]);
            int i8 = i3 + 1;
            bArr2[i3] = (byte) ((((bArr[i4] ^ xTime(bArr[i5])) ^ xTime(bArr[i6])) ^ bArr[i6]) ^ bArr[i7]);
            int i9 = i8 + 1;
            bArr2[i8] = (byte) ((((bArr[i4] ^ bArr[i5]) ^ xTime(bArr[i6])) ^ xTime(bArr[i7])) ^ bArr[i7]);
            i = i9 + 1;
            bArr2[i9] = (byte) ((((bArr[i4] ^ xTime(bArr[i4])) ^ bArr[i5]) ^ bArr[i6]) ^ xTime(bArr[i7]));
        }
        return bArr2;
    }

    static byte sBox(byte b) {
        return f2995S[(b & UByte.MAX_VALUE) >>> 4][b & SnmpConstants.SNMP_ERR_UNDOFAILED];
    }

    static byte[] shiftRows(byte[] bArr) {
        return new byte[]{bArr[0], bArr[5], bArr[10], bArr[15], bArr[4], bArr[9], bArr[14], bArr[3], bArr[8], bArr[13], bArr[2], bArr[7], bArr[12], bArr[1], bArr[6], bArr[11]};
    }

    static byte[] subBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        bArr2[0] = sBox(bArr[0]);
        bArr2[1] = sBox(bArr[1]);
        bArr2[2] = sBox(bArr[2]);
        bArr2[3] = sBox(bArr[3]);
        bArr2[4] = sBox(bArr[4]);
        bArr2[5] = sBox(bArr[5]);
        bArr2[6] = sBox(bArr[6]);
        bArr2[7] = sBox(bArr[7]);
        bArr2[8] = sBox(bArr[8]);
        bArr2[9] = sBox(bArr[9]);
        bArr2[10] = sBox(bArr[10]);
        bArr2[11] = sBox(bArr[11]);
        bArr2[12] = sBox(bArr[12]);
        bArr2[13] = sBox(bArr[13]);
        bArr2[14] = sBox(bArr[14]);
        bArr2[15] = sBox(bArr[15]);
        return bArr2;
    }

    static byte xTime(byte b) {
        int i = b >>> 7;
        int i2 = b << 1;
        if (i > 0) {
            i2 ^= 27;
        }
        return (byte) (i2 & 255);
    }

    static byte[] xor(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[16];
        int i2 = 0;
        while (i2 < 16) {
            bArr3[i2] = (byte) (bArr2[i] ^ bArr[i2]);
            i2++;
            i++;
        }
        return bArr3;
    }

    static void xorReverse(byte[] bArr, byte[] bArr2) {
        bArr[0] = (byte) (bArr[0] ^ bArr2[15]);
        bArr[1] = (byte) (bArr[1] ^ bArr2[14]);
        bArr[2] = (byte) (bArr[2] ^ bArr2[13]);
        bArr[3] = (byte) (bArr[3] ^ bArr2[12]);
        bArr[4] = (byte) (bArr[4] ^ bArr2[11]);
        bArr[5] = (byte) (bArr[5] ^ bArr2[10]);
        bArr[6] = (byte) (bArr[6] ^ bArr2[9]);
        bArr[7] = (byte) (bArr[7] ^ bArr2[8]);
        bArr[8] = (byte) (bArr2[7] ^ bArr[8]);
        bArr[9] = (byte) (bArr2[6] ^ bArr[9]);
        bArr[10] = (byte) (bArr2[5] ^ bArr[10]);
        bArr[11] = (byte) (bArr2[4] ^ bArr[11]);
        bArr[12] = (byte) (bArr2[3] ^ bArr[12]);
        bArr[13] = (byte) (bArr2[2] ^ bArr[13]);
        bArr[14] = (byte) (bArr2[1] ^ bArr[14]);
        bArr[15] = (byte) (bArr2[0] ^ bArr[15]);
    }

    @Override
    public int getDigestSize() {
        return 32;
    }
}
