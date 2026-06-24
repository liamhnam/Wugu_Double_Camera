package org.bouncycastle.crypto.engines;

import cc.uling.usdk.constants.MdbAddr;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import kotlin.UByte;
import kotlin.UShort;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.p037io.encoding.Base64;
import okio.Utf8;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;

public class Zuc128CoreEngine implements StreamCipher, Memoable {
    private final int[] BRC;

    private final int[] f3221F;
    private final int[] LFSR;
    private final byte[] keyStream;
    private int theIndex;
    private int theIterations;
    private Zuc128CoreEngine theResetState;

    private static final byte[] f3219S0 = {62, 114, 91, SnmpConstants.UINTEGER32, -54, -32, 0, 51, 4, -47, 84, -104, 9, -71, 109, -53, 123, 27, -7, 50, -81, -99, 106, SnmpConstants.GETBULK_REQ_MSG, -72, 45, -4, 29, 8, 83, 3, -112, 77, 78, -124, -103, -28, -50, -39, -111, -35, -74, -123, 72, -117, 41, 110, -84, -51, -63, -8, 30, 115, SnmpConstants.TIMETICKS, 105, -58, -75, -67, -3, 57, 99, 32, -44, 56, 118, 125, -78, SnmpConstants.TRPV2_REQ_MSG, -49, -19, 87, -59, -13, 44, -69, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 33, 6, 85, -101, -29, -17, 94, 49, 79, ByteCompanionObject.MAX_VALUE, 90, SnmpConstants.TRP_REQ_MSG, 13, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, 81, 73, 95, -70, 88, 28, 74, 22, -43, 23, SnmpConstants.GET_RPRT_MSG, -110, 36, SnmpConstants.ASN_EXTENSION_ID, -116, -1, -40, -82, 46, 1, -45, -83, 59, 75, -38, SnmpConstants.COUNTER64, -21, -55, -34, -102, -113, -121, -41, 58, -128, 111, 47, -56, -79, -76, 55, -9, 10, 34, SnmpConstants.SNMP_ERR_DECODING_EXC, 40, 124, -52, 60, -119, -57, -61, -106, 86, 7, -65, 126, -16, 11, 43, -105, 82, 53, SnmpConstants.COUNTER, 121, 97, SnmpConstants.INFORM_REQ_MSG, 76, 16, -2, PSSSigner.TRAILER_IMPLICIT, 38, -107, -120, -118, -80, SnmpConstants.SET_REQ_MSG, -5, SnmpConstants.ASN_PRIVATE, 24, -108, -14, MdbAddr.DEF_ADDR, -27, -23, 93, -48, -36, 17, 102, 100, 92, -20, 89, SnmpConstants.GAUGE, 117, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -11, 116, -100, -86, 35, 14, -122, -85, -66, 42, 2, -25, 103, -26, SnmpConstants.OPAQUE, SnmpConstants.GET_RSP_MSG, 108, -62, -109, -97, -15, -10, -6, 54, -46, 80, 104, -98, 98, 113, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, Base64.padSymbol, -42, 64, -60, -30, SnmpConstants.SNMP_ERR_UNDOFAILED, -114, -125, 119, 107, 37, 5, Utf8.REPLACEMENT_BYTE, 12, SnmpConstants.CONS_SEQ, -22, 112, -73, SnmpConstants.GETNEXT_REQ_MSG, -24, -87, 101, -115, 39, 26, -37, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -77, SnmpConstants.GET_REQ_MSG, -12, SnmpConstants.NSAP_ADDRESS, 122, 25, -33, -18, 120, 52, 96};

    private static final byte[] f3220S1 = {85, -62, 99, 113, 59, -56, SnmpConstants.UINTEGER32, -122, -97, 60, -38, 91, 41, -86, -3, 119, -116, -59, -108, 12, SnmpConstants.INFORM_REQ_MSG, 26, SnmpConstants.SNMP_ERR_DECODING_EXC, 0, -29, SnmpConstants.GET_RPRT_MSG, 22, 114, 64, -7, -8, SnmpConstants.GAUGE, SnmpConstants.OPAQUE, 38, 104, -106, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -39, SnmpConstants.NSAP_ADDRESS, 62, 16, 118, -58, SnmpConstants.TRPV2_REQ_MSG, -117, 57, SnmpConstants.TIMETICKS, MdbAddr.DEF_ADDR, 58, -75, 86, 42, SnmpConstants.ASN_PRIVATE, 109, -77, 5, 34, 102, -65, -36, 11, -6, 98, 72, -35, 32, 17, 6, 54, -55, -63, -49, -10, 39, 82, -69, 105, -11, -44, -121, ByteCompanionObject.MAX_VALUE, -124, 76, -46, -100, 87, SnmpConstants.TRP_REQ_MSG, PSSSigner.TRAILER_IMPLICIT, 79, -102, -33, -2, -42, -115, 122, -21, 43, 83, -40, 92, SnmpConstants.GETNEXT_REQ_MSG, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 23, -5, 35, -43, 125, SnmpConstants.CONS_SEQ, 103, 115, 8, 9, -18, -73, 112, Utf8.REPLACEMENT_BYTE, 97, -78, 25, -114, 78, -27, 75, -109, -113, 93, -37, -87, -83, -15, -82, 46, -53, 13, -4, -12, 45, SnmpConstants.COUNTER64, 110, 29, -105, -24, -47, -23, 77, 55, SnmpConstants.GETBULK_REQ_MSG, 117, 94, -125, -98, -85, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -99, -71, 28, -32, -51, 73, -119, 1, -74, -67, 88, 36, SnmpConstants.GET_RSP_MSG, 95, 56, 120, -103, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, -112, 80, -72, -107, -28, -48, -111, -57, -50, -19, SnmpConstants.SNMP_ERR_UNDOFAILED, -76, 111, SnmpConstants.GET_REQ_MSG, -52, -16, 2, 74, 121, -61, -34, SnmpConstants.SET_REQ_MSG, -17, -22, 81, -26, 107, 24, -20, 27, 44, -128, -9, 116, -25, -1, 33, 90, 106, 84, 30, SnmpConstants.COUNTER, 49, -110, 53, -60, 51, 7, 10, -70, 126, 14, 52, -120, -79, -104, 124, -13, Base64.padSymbol, 96, 108, 123, -54, -45, SnmpConstants.ASN_EXTENSION_ID, 50, 101, 4, 40, 100, -66, -123, -101, 47, 89, -118, -41, -80, 37, -84, -81, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 3, -30, -14};
    private static final short[] EK_d = {17623, 9916, 25195, 4958, 22409, 13794, 28981, 2479, 19832, 12051, 27588, 6897, 24102, 15437, 30874, 18348};

    protected Zuc128CoreEngine() {
        this.LFSR = new int[16];
        this.f3221F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
    }

    protected Zuc128CoreEngine(Zuc128CoreEngine zuc128CoreEngine) {
        this.LFSR = new int[16];
        this.f3221F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
        reset(zuc128CoreEngine);
    }

    private int AddM(int i, int i2) {
        int i3 = i + i2;
        return (Integer.MAX_VALUE & i3) + (i3 >>> 31);
    }

    private void BitReorganization() {
        int[] iArr = this.BRC;
        int[] iArr2 = this.LFSR;
        iArr[0] = ((iArr2[15] & 2147450880) << 1) | (iArr2[14] & 65535);
        iArr[1] = ((iArr2[11] & 65535) << 16) | (iArr2[9] >>> 15);
        iArr[2] = ((iArr2[7] & 65535) << 16) | (iArr2[5] >>> 15);
        iArr[3] = (iArr2[0] >>> 15) | ((iArr2[2] & 65535) << 16);
    }

    private static int m1435L1(int i) {
        return ROT(i, 24) ^ (((ROT(i, 2) ^ i) ^ ROT(i, 10)) ^ ROT(i, 18));
    }

    private static int m1436L2(int i) {
        return ROT(i, 30) ^ (((ROT(i, 8) ^ i) ^ ROT(i, 14)) ^ ROT(i, 22));
    }

    private void LFSRWithInitialisationMode(int i) {
        int i2 = this.LFSR[0];
        int iAddM = AddM(AddM(AddM(AddM(AddM(AddM(i2, MulByPow2(i2, 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15)), i);
        int[] iArr = this.LFSR;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = iArr[5];
        iArr[5] = iArr[6];
        iArr[6] = iArr[7];
        iArr[7] = iArr[8];
        iArr[8] = iArr[9];
        iArr[9] = iArr[10];
        iArr[10] = iArr[11];
        iArr[11] = iArr[12];
        iArr[12] = iArr[13];
        iArr[13] = iArr[14];
        iArr[14] = iArr[15];
        iArr[15] = iAddM;
    }

    private void LFSRWithWorkMode() {
        int i = this.LFSR[0];
        int iAddM = AddM(AddM(AddM(AddM(AddM(i, MulByPow2(i, 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15));
        int[] iArr = this.LFSR;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = iArr[5];
        iArr[5] = iArr[6];
        iArr[6] = iArr[7];
        iArr[7] = iArr[8];
        iArr[8] = iArr[9];
        iArr[9] = iArr[10];
        iArr[10] = iArr[11];
        iArr[11] = iArr[12];
        iArr[12] = iArr[13];
        iArr[13] = iArr[14];
        iArr[14] = iArr[15];
        iArr[15] = iAddM;
    }

    private static int MAKEU31(byte b, short s, byte b2) {
        return ((b & UByte.MAX_VALUE) << 23) | ((s & UShort.MAX_VALUE) << 8) | (b2 & UByte.MAX_VALUE);
    }

    private static int MAKEU32(byte b, byte b2, byte b3, byte b4) {
        return ((b & UByte.MAX_VALUE) << 24) | ((b2 & UByte.MAX_VALUE) << 16) | ((b3 & UByte.MAX_VALUE) << 8) | (b4 & UByte.MAX_VALUE);
    }

    private static int MulByPow2(int i, int i2) {
        return ((i >>> (31 - i2)) | (i << i2)) & Integer.MAX_VALUE;
    }

    static int ROT(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static void encode32be(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        bArr[i2 + 3] = (byte) i;
    }

    private void makeKeyStream() {
        encode32be(makeKeyStreamWord(), this.keyStream, 0);
    }

    private void setKeyAndIV(byte[] bArr, byte[] bArr2) {
        setKeyAndIV(this.LFSR, bArr, bArr2);
        int[] iArr = this.f3221F;
        iArr[0] = 0;
        iArr[1] = 0;
        int i = 32;
        while (true) {
            BitReorganization();
            if (i <= 0) {
                m1437F();
                LFSRWithWorkMode();
                return;
            } else {
                LFSRWithInitialisationMode(m1437F() >>> 1);
                i--;
            }
        }
    }

    int m1437F() {
        int[] iArr = this.BRC;
        int i = iArr[0];
        int[] iArr2 = this.f3221F;
        int i2 = iArr2[0];
        int i3 = iArr2[1];
        int i4 = (i ^ i2) + i3;
        int i5 = i2 + iArr[1];
        int i6 = iArr[2] ^ i3;
        int iM1435L1 = m1435L1((i5 << 16) | (i6 >>> 16));
        int iM1436L2 = m1436L2((i6 << 16) | (i5 >>> 16));
        int[] iArr3 = this.f3221F;
        byte[] bArr = f3219S0;
        byte b = bArr[iM1435L1 >>> 24];
        byte[] bArr2 = f3220S1;
        iArr3[0] = MAKEU32(b, bArr2[(iM1435L1 >>> 16) & 255], bArr[(iM1435L1 >>> 8) & 255], bArr2[iM1435L1 & 255]);
        this.f3221F[1] = MAKEU32(bArr[iM1436L2 >>> 24], bArr2[(iM1436L2 >>> 16) & 255], bArr[(iM1436L2 >>> 8) & 255], bArr2[iM1436L2 & 255]);
        return i4;
    }

    @Override
    public Memoable copy() {
        return new Zuc128CoreEngine(this);
    }

    @Override
    public String getAlgorithmName() {
        return "Zuc-128";
    }

    protected int getMaxIterations() {
        return 2047;
    }

    @Override
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] iv;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            iv = null;
        }
        byte[] key = cipherParameters instanceof KeyParameter ? ((KeyParameter) cipherParameters).getKey() : null;
        this.theIndex = 0;
        this.theIterations = 0;
        setKeyAndIV(key, iv);
        this.theResetState = (Zuc128CoreEngine) copy();
    }

    protected int makeKeyStreamWord() {
        int i = this.theIterations;
        this.theIterations = i + 1;
        if (i >= getMaxIterations()) {
            throw new IllegalStateException("Too much data processed by singleKey/IV");
        }
        BitReorganization();
        int iM1437F = m1437F() ^ this.BRC[3];
        LFSRWithWorkMode();
        return iM1437F;
    }

    @Override
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (this.theResetState == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            bArr2[i4 + i3] = returnByte(bArr[i4 + i]);
        }
        return i2;
    }

    @Override
    public void reset() {
        Zuc128CoreEngine zuc128CoreEngine = this.theResetState;
        if (zuc128CoreEngine != null) {
            reset(zuc128CoreEngine);
        }
    }

    @Override
    public void reset(Memoable memoable) {
        Zuc128CoreEngine zuc128CoreEngine = (Zuc128CoreEngine) memoable;
        int[] iArr = zuc128CoreEngine.LFSR;
        int[] iArr2 = this.LFSR;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = zuc128CoreEngine.f3221F;
        int[] iArr4 = this.f3221F;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        int[] iArr5 = zuc128CoreEngine.BRC;
        int[] iArr6 = this.BRC;
        System.arraycopy(iArr5, 0, iArr6, 0, iArr6.length);
        byte[] bArr = zuc128CoreEngine.keyStream;
        byte[] bArr2 = this.keyStream;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.theIndex = zuc128CoreEngine.theIndex;
        this.theIterations = zuc128CoreEngine.theIterations;
        this.theResetState = zuc128CoreEngine;
    }

    @Override
    public byte returnByte(byte b) {
        if (this.theIndex == 0) {
            makeKeyStream();
        }
        byte[] bArr = this.keyStream;
        int i = this.theIndex;
        byte b2 = (byte) (b ^ bArr[i]);
        this.theIndex = (i + 1) % 4;
        return b2;
    }

    protected void setKeyAndIV(int[] iArr, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("A key of 16 bytes is needed");
        }
        if (bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("An IV of 16 bytes is needed");
        }
        int[] iArr2 = this.LFSR;
        byte b = bArr[0];
        short[] sArr = EK_d;
        iArr2[0] = MAKEU31(b, sArr[0], bArr2[0]);
        this.LFSR[1] = MAKEU31(bArr[1], sArr[1], bArr2[1]);
        this.LFSR[2] = MAKEU31(bArr[2], sArr[2], bArr2[2]);
        this.LFSR[3] = MAKEU31(bArr[3], sArr[3], bArr2[3]);
        this.LFSR[4] = MAKEU31(bArr[4], sArr[4], bArr2[4]);
        this.LFSR[5] = MAKEU31(bArr[5], sArr[5], bArr2[5]);
        this.LFSR[6] = MAKEU31(bArr[6], sArr[6], bArr2[6]);
        this.LFSR[7] = MAKEU31(bArr[7], sArr[7], bArr2[7]);
        this.LFSR[8] = MAKEU31(bArr[8], sArr[8], bArr2[8]);
        this.LFSR[9] = MAKEU31(bArr[9], sArr[9], bArr2[9]);
        this.LFSR[10] = MAKEU31(bArr[10], sArr[10], bArr2[10]);
        this.LFSR[11] = MAKEU31(bArr[11], sArr[11], bArr2[11]);
        this.LFSR[12] = MAKEU31(bArr[12], sArr[12], bArr2[12]);
        this.LFSR[13] = MAKEU31(bArr[13], sArr[13], bArr2[13]);
        this.LFSR[14] = MAKEU31(bArr[14], sArr[14], bArr2[14]);
        this.LFSR[15] = MAKEU31(bArr[15], sArr[15], bArr2[15]);
    }
}
