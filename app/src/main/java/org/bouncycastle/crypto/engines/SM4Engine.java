package org.bouncycastle.crypto.engines;

import cc.uling.usdk.constants.MdbAddr;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.p037io.encoding.Base64;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;

public class SM4Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    private final int[] f3198X = new int[4];

    private int[] f3199rk;
    private static final byte[] Sbox = {-42, -112, -23, -2, -52, MdbAddr.DEF_ADDR, Base64.padSymbol, -73, 22, -74, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, -66, 4, -61, -86, SnmpConstants.OPAQUE, SnmpConstants.SNMP_ERR_DECODING_EXC, 38, 73, -122, 6, -103, -100, SnmpConstants.GAUGE, 80, -12, -111, -17, -104, 122, 51, 84, 11, SnmpConstants.TIMETICKS, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, -128, -33, -108, -6, 117, -113, Utf8.REPLACEMENT_BYTE, SnmpConstants.INFORM_REQ_MSG, SnmpConstants.UINTEGER32, 7, SnmpConstants.TRPV2_REQ_MSG, -4, -13, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, SnmpConstants.GET_RPRT_MSG, 104, 107, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -78, 113, 100, -38, -117, -8, -21, SnmpConstants.SNMP_ERR_UNDOFAILED, 75, 112, 86, -99, 53, 30, 36, 14, 94, 99, 88, -47, SnmpConstants.GET_RSP_MSG, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, SnmpConstants.COUNTER64, 87, -97, -45, 39, 82, 76, 54, 2, -25, SnmpConstants.GET_REQ_MSG, -60, -56, -98, -22, -65, -118, -46, 64, -57, 56, -75, SnmpConstants.SET_REQ_MSG, -9, -14, -50, -7, 97, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, SnmpConstants.GETNEXT_REQ_MSG, -32, -82, 93, SnmpConstants.TRP_REQ_MSG, -101, 52, 26, 85, -83, -109, 50, SnmpConstants.CONS_SEQ, -11, -116, -79, -29, 29, -10, -30, 46, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, 102, -54, 96, SnmpConstants.ASN_PRIVATE, 41, 35, -85, 13, 83, 78, 111, -43, -37, 55, SnmpConstants.NSAP_ADDRESS, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, 27, -81, -110, -69, -35, PSSSigner.TRAILER_IMPLICIT, ByteCompanionObject.MAX_VALUE, 17, -39, 92, SnmpConstants.COUNTER, SnmpConstants.ASN_EXTENSION_ID, 16, 90, -40, 10, -63, 49, -120, SnmpConstants.GETBULK_REQ_MSG, -51, 123, -67, 45, 116, -48, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -72, -27, -76, -80, -119, 105, -105, 74, 12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, 24, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};

    private static final int[] f3196CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    private static final int[] f3197FK = {-1548633402, 1453994832, 1736282519, -1301273892};

    private int m1422F0(int[] iArr, int i) {
        return m1427T((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i) ^ iArr[0];
    }

    private int m1423F1(int[] iArr, int i) {
        return m1427T((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i) ^ iArr[1];
    }

    private int m1424F2(int[] iArr, int i) {
        return m1427T((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i) ^ iArr[2];
    }

    private int m1425F3(int[] iArr, int i) {
        return m1427T((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i) ^ iArr[3];
    }

    private int m1426L(int i) {
        return rotateLeft(i, 24) ^ (((rotateLeft(i, 2) ^ i) ^ rotateLeft(i, 10)) ^ rotateLeft(i, 18));
    }

    private int L_ap(int i) {
        return rotateLeft(i, 23) ^ (rotateLeft(i, 13) ^ i);
    }

    private int m1427T(int i) {
        return m1426L(tau(i));
    }

    private int T_ap(int i) {
        return L_ap(tau(i));
    }

    private int[] expandKey(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int[] iArr2 = {Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), Pack.bigEndianToInt(bArr, 12)};
        int i = iArr2[0];
        int[] iArr3 = f3197FK;
        int[] iArr4 = {i ^ iArr3[0], iArr2[1] ^ iArr3[1], iArr2[2] ^ iArr3[2], iArr2[3] ^ iArr3[3]};
        if (z) {
            int i2 = iArr4[0];
            int i3 = (iArr4[1] ^ iArr4[2]) ^ iArr4[3];
            int[] iArr5 = f3196CK;
            int iT_ap = i2 ^ T_ap(i3 ^ iArr5[0]);
            iArr[0] = iT_ap;
            int iT_ap2 = T_ap((iT_ap ^ (iArr4[2] ^ iArr4[3])) ^ iArr5[1]) ^ iArr4[1];
            iArr[1] = iT_ap2;
            int iT_ap3 = T_ap((iT_ap2 ^ (iArr4[3] ^ iArr[0])) ^ iArr5[2]) ^ iArr4[2];
            iArr[2] = iT_ap3;
            iArr[3] = T_ap((iT_ap3 ^ (iArr[0] ^ iArr[1])) ^ iArr5[3]) ^ iArr4[3];
            for (int i4 = 4; i4 < 32; i4++) {
                iArr[i4] = iArr[i4 - 4] ^ T_ap(((iArr[i4 - 3] ^ iArr[i4 - 2]) ^ iArr[i4 - 1]) ^ f3196CK[i4]);
            }
        } else {
            int i5 = iArr4[0];
            int i6 = (iArr4[1] ^ iArr4[2]) ^ iArr4[3];
            int[] iArr6 = f3196CK;
            int iT_ap4 = i5 ^ T_ap(i6 ^ iArr6[0]);
            iArr[31] = iT_ap4;
            int iT_ap5 = T_ap((iT_ap4 ^ (iArr4[2] ^ iArr4[3])) ^ iArr6[1]) ^ iArr4[1];
            iArr[30] = iT_ap5;
            int iT_ap6 = T_ap((iT_ap5 ^ (iArr4[3] ^ iArr[31])) ^ iArr6[2]) ^ iArr4[2];
            iArr[29] = iT_ap6;
            iArr[28] = T_ap((iT_ap6 ^ (iArr[31] ^ iArr[30])) ^ iArr6[3]) ^ iArr4[3];
            for (int i7 = 27; i7 >= 0; i7--) {
                iArr[i7] = iArr[i7 + 4] ^ T_ap(((iArr[i7 + 3] ^ iArr[i7 + 2]) ^ iArr[i7 + 1]) ^ f3196CK[31 - i7]);
            }
        }
        return iArr;
    }

    private int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private int tau(int i) {
        byte[] bArr = Sbox;
        return (bArr[i & 255] & UByte.MAX_VALUE) | ((bArr[(i >> 24) & 255] & UByte.MAX_VALUE) << 24) | ((bArr[(i >> 16) & 255] & UByte.MAX_VALUE) << 16) | ((bArr[(i >> 8) & 255] & UByte.MAX_VALUE) << 8);
    }

    @Override
    public String getAlgorithmName() {
        return "SM4";
    }

    @Override
    public int getBlockSize() {
        return 16;
    }

    @Override
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("SM4 requires a 128 bit key");
        }
        this.f3199rk = expandKey(z, key);
    }

    @Override
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException, DataLengthException {
        if (this.f3199rk == null) {
            throw new IllegalStateException("SM4 not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.f3198X[0] = Pack.bigEndianToInt(bArr, i);
        this.f3198X[1] = Pack.bigEndianToInt(bArr, i + 4);
        this.f3198X[2] = Pack.bigEndianToInt(bArr, i + 8);
        this.f3198X[3] = Pack.bigEndianToInt(bArr, i + 12);
        for (int i3 = 0; i3 < 32; i3 += 4) {
            int[] iArr = this.f3198X;
            iArr[0] = m1422F0(iArr, this.f3199rk[i3]);
            int[] iArr2 = this.f3198X;
            iArr2[1] = m1423F1(iArr2, this.f3199rk[i3 + 1]);
            int[] iArr3 = this.f3198X;
            iArr3[2] = m1424F2(iArr3, this.f3199rk[i3 + 2]);
            int[] iArr4 = this.f3198X;
            iArr4[3] = m1425F3(iArr4, this.f3199rk[i3 + 3]);
        }
        Pack.intToBigEndian(this.f3198X[3], bArr2, i2);
        Pack.intToBigEndian(this.f3198X[2], bArr2, i2 + 4);
        Pack.intToBigEndian(this.f3198X[1], bArr2, i2 + 8);
        Pack.intToBigEndian(this.f3198X[0], bArr2, i2 + 12);
        return 16;
    }

    @Override
    public void reset() {
    }
}
