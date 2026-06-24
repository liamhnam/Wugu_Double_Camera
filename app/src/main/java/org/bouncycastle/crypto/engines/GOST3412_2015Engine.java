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
import org.bouncycastle.util.Arrays;

public class GOST3412_2015Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;

    private static final byte[] f3167PI = {-4, -18, -35, 17, -49, 110, 49, 22, -5, -60, -6, -38, 35, -59, 4, 77, -23, 119, -16, -37, -109, 46, -103, -70, 23, 54, -15, -69, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, -51, 95, -63, -7, 24, 101, 90, -30, 92, -17, 33, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, 28, 60, SnmpConstants.GAUGE, -117, 1, -114, 79, 5, -124, 2, -82, -29, 106, -113, SnmpConstants.GET_REQ_MSG, 6, 11, -19, -104, ByteCompanionObject.MAX_VALUE, -44, -45, SnmpConstants.ASN_EXTENSION_ID, -21, 52, 44, 81, -22, -56, 72, -85, -14, 42, 104, SnmpConstants.GET_RSP_MSG, -3, 58, -50, -52, -75, 112, 14, 86, 8, 12, 118, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -65, 114, SnmpConstants.SNMP_ERR_DECODING_EXC, SnmpConstants.UINTEGER32, -100, -73, 93, -121, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, SnmpConstants.GETNEXT_REQ_MSG, -106, 41, 16, 123, -102, -57, -13, -111, 120, 111, -99, -98, -78, -79, 50, 117, 25, Base64.padSymbol, -1, 53, -118, 126, 109, 84, -58, -128, -61, -67, 13, 87, -33, -11, 36, -87, 62, SnmpConstants.GET_RPRT_MSG, SnmpConstants.TIMETICKS, -55, -41, 121, -42, -10, 124, 34, -71, 3, -32, SnmpConstants.SNMP_ERR_UNDOFAILED, -20, -34, 122, -108, -80, PSSSigner.TRAILER_IMPLICIT, -36, -24, 40, 80, 78, 51, 10, 74, SnmpConstants.TRPV2_REQ_MSG, -105, 96, 115, 30, 0, 98, SnmpConstants.OPAQUE, 26, -72, 56, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, 100, -97, 38, SnmpConstants.COUNTER, -83, SnmpConstants.NSAP_ADDRESS, SnmpConstants.COUNTER64, -110, 39, 94, 85, 47, -116, SnmpConstants.SET_REQ_MSG, SnmpConstants.GETBULK_REQ_MSG, 125, 105, -43, -107, 59, 7, 88, -77, 64, -122, -84, 29, -9, SnmpConstants.CONS_SEQ, 55, 107, -28, -120, -39, -25, -119, MdbAddr.DEF_ADDR, 27, -125, 73, 76, Utf8.REPLACEMENT_BYTE, -8, -2, -115, 83, -86, -112, -54, -40, -123, 97, 32, 113, 103, SnmpConstants.TRP_REQ_MSG, 45, 43, 9, 91, -53, -101, 37, -48, -66, -27, 108, 82, 89, SnmpConstants.INFORM_REQ_MSG, 116, -46, -26, -12, -76, SnmpConstants.ASN_PRIVATE, -47, 102, -81, -62, 57, 75, 99, -74};
    private static final byte[] inversePI = {SnmpConstants.GETBULK_REQ_MSG, 45, 50, -113, 14, SnmpConstants.CONS_SEQ, 56, SnmpConstants.ASN_PRIVATE, 84, -26, -98, 57, 85, 126, 82, -111, 100, 3, 87, 90, 28, 96, 7, 24, 33, 114, SnmpConstants.GET_RPRT_MSG, -47, 41, -58, SnmpConstants.TRP_REQ_MSG, Utf8.REPLACEMENT_BYTE, -32, 39, -115, 12, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -22, -82, -76, -102, 99, 73, -27, SnmpConstants.GAUGE, -28, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, -73, -56, 6, 112, -99, SnmpConstants.COUNTER, 117, 25, -55, -86, -4, 77, -65, 42, 115, -124, -43, -61, -81, 43, -122, SnmpConstants.TRPV2_REQ_MSG, -79, -78, 91, SnmpConstants.COUNTER64, -45, -97, -3, -44, SnmpConstants.SNMP_ERR_UNDOFAILED, -100, 47, -101, SnmpConstants.TIMETICKS, -17, -39, 121, -74, 83, ByteCompanionObject.MAX_VALUE, -63, -16, 35, -25, 37, 94, -75, 30, SnmpConstants.GET_RSP_MSG, -33, SnmpConstants.INFORM_REQ_MSG, -2, -84, 34, -7, -30, 74, PSSSigner.TRAILER_IMPLICIT, 53, -54, -18, 120, 5, 107, 81, MdbAddr.DEF_ADDR, 89, SnmpConstants.SET_REQ_MSG, -14, 113, 86, 17, 106, -119, -108, 101, -116, -69, 119, 60, 123, 40, -85, -46, 49, -34, -60, 95, -52, -49, 118, 44, -72, -40, 46, 54, -37, 105, -77, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, -107, -66, 98, SnmpConstants.GETNEXT_REQ_MSG, 59, 22, 102, -23, 92, 108, 109, -83, 55, 97, 75, -71, -29, -70, -15, SnmpConstants.GET_REQ_MSG, -123, -125, -38, SnmpConstants.UINTEGER32, -59, -80, 51, -6, -106, 111, 110, -62, -10, 80, -1, 93, -87, -114, 23, 27, -105, 125, -20, 88, -9, SnmpConstants.ASN_EXTENSION_ID, -5, 124, 9, 13, 122, 103, SnmpConstants.NSAP_ADDRESS, -121, -36, -24, 79, 29, 78, 4, -21, -8, -13, 62, Base64.padSymbol, -67, -118, -120, -35, -51, 11, SnmpConstants.SNMP_ERR_DECODING_EXC, -104, 2, -109, -128, -112, -48, 36, 52, -53, -19, -12, -50, -103, 16, SnmpConstants.OPAQUE, 64, -110, 58, 1, 38, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 26, 72, 104, -11, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -117, -57, -42, 32, 10, 8, 0, 76, -41, 116};
    private boolean forEncryption;
    private final byte[] lFactors = {-108, 32, -123, 16, -62, SnmpConstants.ASN_PRIVATE, 1, -5, 1, SnmpConstants.ASN_PRIVATE, -62, 16, -123, 32, -108, 1};
    private int KEY_LENGTH = 32;
    private int SUB_LENGTH = 32 / 2;
    private byte[][] subKeys = null;
    private byte[][] _gf_mul = init_gf256_mul_table();

    public GOST3412_2015Engine() {
    }

    private void m1407C(byte[] bArr, int i) {
        Arrays.clear(bArr);
        bArr[15] = (byte) i;
        m1409L(bArr);
    }

    private void m1408F(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArrLSX = LSX(bArr, bArr2);
        m1412X(bArrLSX, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.SUB_LENGTH);
        System.arraycopy(bArrLSX, 0, bArr2, 0, this.SUB_LENGTH);
    }

    private void GOST3412_2015Func(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[][] bArr3;
        byte[] bArrCopyOf = new byte[16];
        System.arraycopy(bArr, i, bArrCopyOf, 0, 16);
        int i3 = 9;
        if (this.forEncryption) {
            for (int i4 = 0; i4 < 9; i4++) {
                bArrCopyOf = Arrays.copyOf(LSX(this.subKeys[i4], bArrCopyOf), 16);
            }
            m1412X(bArrCopyOf, this.subKeys[9]);
        } else {
            while (true) {
                bArr3 = this.subKeys;
                if (i3 <= 0) {
                    break;
                }
                bArrCopyOf = Arrays.copyOf(XSL(bArr3[i3], bArrCopyOf), 16);
                i3--;
            }
            m1412X(bArrCopyOf, bArr3[0]);
        }
        System.arraycopy(bArrCopyOf, 0, bArr2, i2, 16);
    }

    private void m1409L(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            m1410R(bArr);
        }
    }

    private byte[] LSX(byte[] bArr, byte[] bArr2) {
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        m1412X(bArrCopyOf, bArr2);
        m1411S(bArrCopyOf);
        m1409L(bArrCopyOf);
        return bArrCopyOf;
    }

    private void m1410R(byte[] bArr) {
        byte bM1413l = m1413l(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = bM1413l;
    }

    private void m1411S(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = f3167PI[unsignedByte(bArr[i])];
        }
    }

    private void m1412X(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    private byte[] XSL(byte[] bArr, byte[] bArr2) {
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        m1412X(bArrCopyOf, bArr2);
        inverseL(bArrCopyOf);
        inverseS(bArrCopyOf);
        return bArrCopyOf;
    }

    private void generateSubKeys(byte[] bArr) {
        int i;
        if (bArr.length != this.KEY_LENGTH) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.subKeys = new byte[10][];
        for (int i2 = 0; i2 < 10; i2++) {
            this.subKeys[i2] = new byte[this.SUB_LENGTH];
        }
        int i3 = this.SUB_LENGTH;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        int i4 = 0;
        while (true) {
            i = this.SUB_LENGTH;
            if (i4 >= i) {
                break;
            }
            byte[][] bArr4 = this.subKeys;
            byte[] bArr5 = bArr4[0];
            byte b = bArr[i4];
            bArr2[i4] = b;
            bArr5[i4] = b;
            byte[] bArr6 = bArr4[1];
            byte b2 = bArr[i + i4];
            bArr3[i4] = b2;
            bArr6[i4] = b2;
            i4++;
        }
        byte[] bArr7 = new byte[i];
        for (int i5 = 1; i5 < 5; i5++) {
            for (int i6 = 1; i6 <= 8; i6++) {
                m1407C(bArr7, ((i5 - 1) * 8) + i6);
                m1408F(bArr7, bArr2, bArr3);
            }
            int i7 = i5 * 2;
            System.arraycopy(bArr2, 0, this.subKeys[i7], 0, this.SUB_LENGTH);
            System.arraycopy(bArr3, 0, this.subKeys[i7 + 1], 0, this.SUB_LENGTH);
        }
    }

    private static byte[][] init_gf256_mul_table() {
        byte[][] bArr = new byte[256][];
        for (int i = 0; i < 256; i++) {
            bArr[i] = new byte[256];
            for (int i2 = 0; i2 < 256; i2++) {
                bArr[i][i2] = kuz_mul_gf256_slow((byte) i, (byte) i2);
            }
        }
        return bArr;
    }

    private void inverseL(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            inverseR(bArr);
        }
    }

    private void inverseR(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte bM1413l = m1413l(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = bM1413l;
    }

    private void inverseS(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = inversePI[unsignedByte(bArr[i])];
        }
    }

    private static byte kuz_mul_gf256_slow(byte b, byte b2) {
        byte b3 = 0;
        for (byte b4 = 0; b4 < 8 && b != 0 && b2 != 0; b4 = (byte) (b4 + 1)) {
            if ((b2 & 1) != 0) {
                b3 = (byte) (b3 ^ b);
            }
            byte b5 = (byte) (b & 128);
            b = (byte) (b << 1);
            if (b5 != 0) {
                b = (byte) (b ^ 195);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    private byte m1413l(byte[] bArr) {
        byte b = bArr[15];
        for (int i = 14; i >= 0; i--) {
            b = (byte) (b ^ this._gf_mul[unsignedByte(bArr[i])][unsignedByte(this.lFactors[i])]);
        }
        return b;
    }

    private int unsignedByte(byte b) {
        return b & UByte.MAX_VALUE;
    }

    @Override
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    @Override
    public int getBlockSize() {
        return 16;
    }

    @Override
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            generateSubKeys(((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters != null) {
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException, DataLengthException {
        if (this.subKeys == null) {
            throw new IllegalStateException("GOST3412_2015 engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        GOST3412_2015Func(bArr, i, bArr2, i2);
        return 16;
    }

    @Override
    public void reset() {
    }
}
