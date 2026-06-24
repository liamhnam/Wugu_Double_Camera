package org.bouncycastle.crypto.digests;

import cc.uling.usdk.constants.MdbAddr;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.p037io.encoding.Base64;
import okio.Utf8;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;

public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;

    private static final byte[] f3006S = {41, 46, SnmpConstants.TIMETICKS, -55, SnmpConstants.GET_RSP_MSG, -40, 124, 1, Base64.padSymbol, 54, 84, SnmpConstants.GETNEXT_REQ_MSG, -20, -16, 6, SnmpConstants.SNMP_ERR_DECODING_EXC, 98, SnmpConstants.TRPV2_REQ_MSG, 5, -13, SnmpConstants.ASN_PRIVATE, -57, 115, -116, -104, -109, 43, -39, PSSSigner.TRAILER_IMPLICIT, 76, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -54, 30, -101, 87, 60, -3, -44, -32, 22, 103, SnmpConstants.GAUGE, 111, 24, -118, 23, -27, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -66, 78, -60, -42, -38, -98, -34, 73, SnmpConstants.GET_REQ_MSG, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, -78, 7, Utf8.REPLACEMENT_BYTE, -108, -62, 16, -119, 11, 34, 95, 33, -128, ByteCompanionObject.MAX_VALUE, 93, -102, 90, -112, 50, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, 25, SnmpConstants.CONS_SEQ, -77, 72, SnmpConstants.GETBULK_REQ_MSG, -75, -47, -41, 94, -110, 42, -84, 86, -86, -58, 79, -72, 56, -46, -106, SnmpConstants.TRP_REQ_MSG, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, SnmpConstants.NSAP_ADDRESS, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, 45, SnmpConstants.GET_RPRT_MSG, 2, 27, 96, 37, -83, -82, -80, -71, -10, 28, SnmpConstants.COUNTER64, 97, 105, 52, 64, 126, SnmpConstants.SNMP_ERR_UNDOFAILED, 85, SnmpConstants.UINTEGER32, SnmpConstants.SET_REQ_MSG, 35, -35, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, 38, 44, 83, 13, 110, -123, 40, -124, 9, -45, -33, -51, -12, SnmpConstants.COUNTER, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, MdbAddr.DEF_ADDR, 123, 8, 12, -67, -79, 74, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, 29, 57, -14, -17, -73, 14, 102, 88, -48, -28, SnmpConstants.INFORM_REQ_MSG, 119, 114, -8, -21, 117, 75, 10, 49, SnmpConstants.OPAQUE, 80, -76, -113, -19, SnmpConstants.ASN_EXTENSION_ID, 26, -37, -103, -115, 51, -97, 17, -125, SnmpConstants.SNMP_ERR_DECODINGASN_EXC};

    private byte[] f3007C;
    private int COff;

    private byte[] f3008M;

    private byte[] f3009X;
    private int mOff;
    private int xOff;

    public MD2Digest() {
        this.f3009X = new byte[48];
        this.f3008M = new byte[16];
        this.f3007C = new byte[16];
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.f3009X = new byte[48];
        this.f3008M = new byte[16];
        this.f3007C = new byte[16];
        copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.f3009X;
        System.arraycopy(bArr, 0, this.f3009X, 0, bArr.length);
        this.xOff = mD2Digest.xOff;
        byte[] bArr2 = mD2Digest.f3008M;
        System.arraycopy(bArr2, 0, this.f3008M, 0, bArr2.length);
        this.mOff = mD2Digest.mOff;
        byte[] bArr3 = mD2Digest.f3007C;
        System.arraycopy(bArr3, 0, this.f3007C, 0, bArr3.length);
        this.COff = mD2Digest.COff;
    }

    @Override
    public Memoable copy() {
        return new MD2Digest(this);
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        int length = this.f3008M.length;
        int i2 = this.mOff;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.f3008M;
            if (i2 >= bArr2.length) {
                processCheckSum(bArr2);
                processBlock(this.f3008M);
                processBlock(this.f3007C);
                System.arraycopy(this.f3009X, this.xOff, bArr, i, 16);
                reset();
                return 16;
            }
            bArr2[i2] = b;
            i2++;
        }
    }

    @Override
    public String getAlgorithmName() {
        return "MD2";
    }

    @Override
    public int getByteLength() {
        return 16;
    }

    @Override
    public int getDigestSize() {
        return 16;
    }

    protected void processBlock(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f3009X;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            for (int i4 = 0; i4 < 48; i4++) {
                byte[] bArr3 = this.f3009X;
                byte b = (byte) (f3006S[i2] ^ bArr3[i4]);
                bArr3[i4] = b;
                i2 = b & UByte.MAX_VALUE;
            }
            i2 = (i2 + i3) % 256;
        }
    }

    protected void processCheckSum(byte[] bArr) {
        byte b = this.f3007C[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f3007C;
            b = (byte) (f3006S[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            bArr2[i] = b;
        }
    }

    @Override
    public void reset() {
        this.xOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f3009X;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.mOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.f3008M;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.COff = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.f3007C;
            if (i3 == bArr3.length) {
                return;
            }
            bArr3[i3] = 0;
            i3++;
        }
    }

    @Override
    public void reset(Memoable memoable) {
        copyIn((MD2Digest) memoable);
    }

    @Override
    public void update(byte b) {
        byte[] bArr = this.f3008M;
        int i = this.mOff;
        int i2 = i + 1;
        this.mOff = i2;
        bArr[i] = b;
        if (i2 == 16) {
            processCheckSum(bArr);
            processBlock(this.f3008M);
            this.mOff = 0;
        }
    }

    @Override
    public void update(byte[] bArr, int i, int i2) {
        while (this.mOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > 16) {
            System.arraycopy(bArr, i, this.f3008M, 0, 16);
            processCheckSum(this.f3008M);
            processBlock(this.f3008M);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
