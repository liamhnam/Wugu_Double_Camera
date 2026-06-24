package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import cc.uling.usdk.constants.MdbAddr;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.lang.reflect.Array;
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

public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    private static final int f3125m1 = -2139062144;

    private static final int f3126m2 = 2139062143;

    private static final int f3127m3 = 27;

    private static final int f3128m4 = -1061109568;

    private static final int f3129m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    private static final byte[] f3123S = {99, 124, 119, 123, -14, 107, 111, -59, SnmpConstants.CONS_SEQ, 1, 103, 43, -2, -41, -85, 118, -54, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -55, 125, -6, 89, SnmpConstants.UINTEGER32, -16, -83, -44, SnmpConstants.GET_RSP_MSG, -81, -100, SnmpConstants.TRP_REQ_MSG, 114, SnmpConstants.ASN_PRIVATE, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, SnmpConstants.GETBULK_REQ_MSG, -27, -15, 113, -40, 49, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, 4, -57, 35, -61, 24, -106, 5, -102, 7, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, SnmpConstants.GET_REQ_MSG, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, SnmpConstants.TIMETICKS, 77, 51, -123, SnmpConstants.NSAP_ADDRESS, -7, 2, ByteCompanionObject.MAX_VALUE, 80, 60, -97, SnmpConstants.GET_RPRT_MSG, 81, SnmpConstants.SET_REQ_MSG, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46, -51, 12, SnmpConstants.SNMP_ERR_DECODING_EXC, -20, 95, -105, SnmpConstants.OPAQUE, 23, -60, SnmpConstants.TRPV2_REQ_MSG, 126, Base64.padSymbol, 100, 93, 25, 115, 96, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, 79, -36, 34, 42, -112, -120, SnmpConstants.COUNTER64, -18, -72, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, SnmpConstants.INFORM_REQ_MSG, -76, -58, -24, -35, 116, SnmpConstants.ASN_EXTENSION_ID, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, MdbAddr.DEF_ADDR, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, SnmpConstants.GETNEXT_REQ_MSG, -119, 13, -65, -26, SnmpConstants.GAUGE, 104, SnmpConstants.COUNTER, -103, 45, SnmpConstants.SNMP_ERR_UNDOFAILED, -80, 84, -69, 22};

    private static final byte[] f3124Si = {82, 9, 106, -43, SnmpConstants.CONS_SEQ, 54, SnmpConstants.GETBULK_REQ_MSG, 56, -65, 64, SnmpConstants.SET_REQ_MSG, -98, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -13, -41, -5, 124, -29, 57, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -101, 47, -1, -121, 52, -114, SnmpConstants.TIMETICKS, SnmpConstants.OPAQUE, -60, -34, -23, -53, 84, 123, -108, 50, SnmpConstants.INFORM_REQ_MSG, -62, 35, Base64.padSymbol, -18, 76, -107, 11, SnmpConstants.GAUGE, -6, -61, 78, 8, 46, SnmpConstants.GETNEXT_REQ_MSG, 102, 40, -39, 36, -78, 118, 91, SnmpConstants.GET_RSP_MSG, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, SnmpConstants.TRP_REQ_MSG, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, SnmpConstants.COUNTER64, 87, SnmpConstants.TRPV2_REQ_MSG, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, SnmpConstants.NSAP_ADDRESS, 6, -48, 44, 30, -113, -54, Utf8.REPLACEMENT_BYTE, SnmpConstants.SNMP_ERR_UNDOFAILED, 2, -63, -81, -67, 3, 1, SnmpConstants.SNMP_ERR_DECODING_EXC, -118, 107, 58, -111, 17, SnmpConstants.COUNTER, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, SnmpConstants.UINTEGER32, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, SnmpConstants.ASN_PRIVATE, -2, 120, -51, 90, -12, SnmpConstants.ASN_EXTENSION_ID, -35, SnmpConstants.GET_RPRT_MSG, 51, -120, 7, -57, 49, -79, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 16, 89, 39, -128, -20, 95, 96, 81, ByteCompanionObject.MAX_VALUE, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, SnmpConstants.GET_REQ_MSG, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, MdbAddr.DEF_ADDR, 105, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 99, 85, 33, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};

    public AESLightEngine() {
    }

    private static int FFmulX(int i) {
        return (((i & f3125m1) >>> 7) * 27) ^ ((f3126m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (f3129m5 & i) << 2;
        int i3 = i & f3128m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int iLittleEndianToInt = Pack.littleEndianToInt(bArr, i + 0);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int i3 = this.ROUNDS;
        int[] iArr2 = iArr[i3];
        int i4 = iLittleEndianToInt ^ iArr2[0];
        int i5 = iLittleEndianToInt2 ^ iArr2[1];
        int i6 = iLittleEndianToInt3 ^ iArr2[2];
        int i7 = i3 - 1;
        int i8 = iLittleEndianToInt4 ^ iArr2[3];
        while (true) {
            byte[] bArr3 = f3124Si;
            int i9 = i4 & 255;
            if (i7 <= 1) {
                int iInv_mcol = inv_mcol((((bArr3[i9] & UByte.MAX_VALUE) ^ ((bArr3[(i8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i5 >> 24) & 255] << 24)) ^ iArr[i7][0];
                int iInv_mcol2 = inv_mcol((((bArr3[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i6 >> 24) & 255] << 24)) ^ iArr[i7][1];
                int iInv_mcol3 = inv_mcol((((bArr3[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i8 >> 24) & 255] << 24)) ^ iArr[i7][2];
                int iInv_mcol4 = inv_mcol((((bArr3[i8 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i4 >> 24) & 255] << 24)) ^ iArr[i7][3];
                int i10 = (((bArr3[iInv_mcol & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iInv_mcol4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iInv_mcol3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iInv_mcol2 >> 24) & 255] << 24);
                int[] iArr3 = iArr[0];
                int i11 = i10 ^ iArr3[0];
                int i12 = ((((bArr3[iInv_mcol2 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iInv_mcol >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iInv_mcol4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iInv_mcol3 >> 24) & 255] << 24)) ^ iArr3[1];
                int i13 = ((((bArr3[iInv_mcol3 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iInv_mcol2 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iInv_mcol >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iInv_mcol4 >> 24) & 255] << 24)) ^ iArr3[2];
                int i14 = ((((bArr3[iInv_mcol4 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iInv_mcol3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iInv_mcol2 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iInv_mcol >> 24) & 255] << 24)) ^ iArr3[3];
                Pack.intToLittleEndian(i11, bArr2, i2 + 0);
                Pack.intToLittleEndian(i12, bArr2, i2 + 4);
                Pack.intToLittleEndian(i13, bArr2, i2 + 8);
                Pack.intToLittleEndian(i14, bArr2, i2 + 12);
                return;
            }
            int iInv_mcol5 = inv_mcol((((bArr3[i9] & UByte.MAX_VALUE) ^ ((bArr3[(i8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i5 >> 24) & 255] << 24)) ^ iArr[i7][0];
            int iInv_mcol6 = inv_mcol((((bArr3[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i6 >> 24) & 255] << 24)) ^ iArr[i7][1];
            int iInv_mcol7 = inv_mcol((((bArr3[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i8 >> 24) & 255] << 24)) ^ iArr[i7][2];
            int iInv_mcol8 = inv_mcol((((bArr3[i8 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i4 >> 24) & 255] << 24));
            int i15 = i7 - 1;
            int i16 = iInv_mcol8 ^ iArr[i7][3];
            int iInv_mcol9 = inv_mcol((((bArr3[iInv_mcol5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i16 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iInv_mcol7 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iInv_mcol6 >> 24) & 255] << 24)) ^ iArr[i15][0];
            int iInv_mcol10 = inv_mcol((((bArr3[iInv_mcol6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iInv_mcol5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i16 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iInv_mcol7 >> 24) & 255] << 24)) ^ iArr[i15][1];
            int iInv_mcol11 = inv_mcol((((bArr3[iInv_mcol7 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iInv_mcol6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iInv_mcol5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i16 >> 24) & 255] << 24)) ^ iArr[i15][2];
            int iInv_mcol12 = inv_mcol((((bArr3[i16 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iInv_mcol7 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iInv_mcol6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iInv_mcol5 >> 24) & 255] << 24));
            int i17 = i15 - 1;
            i8 = iInv_mcol12 ^ iArr[i15][3];
            i4 = iInv_mcol9;
            i5 = iInv_mcol10;
            i6 = iInv_mcol11;
            i7 = i17;
        }
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int iLittleEndianToInt = Pack.littleEndianToInt(bArr, i + 0);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int[] iArr2 = iArr[0];
        int i3 = iLittleEndianToInt ^ iArr2[0];
        int i4 = iLittleEndianToInt2 ^ iArr2[1];
        int i5 = iLittleEndianToInt3 ^ iArr2[2];
        int i6 = iLittleEndianToInt4 ^ iArr2[3];
        int i7 = 1;
        while (i7 < this.ROUNDS - 1) {
            byte[] bArr3 = f3123S;
            int iMcol = mcol((((bArr3[i3 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i6 >> 24) & 255] << 24)) ^ iArr[i7][0];
            int iMcol2 = mcol((((bArr3[i4 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i3 >> 24) & 255] << 24)) ^ iArr[i7][1];
            int iMcol3 = mcol((((bArr3[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i4 >> 24) & 255] << 24)) ^ iArr[i7][2];
            int iMcol4 = mcol((((bArr3[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i5 >> 24) & 255] << 24));
            int i8 = i7 + 1;
            int i9 = iMcol4 ^ iArr[i7][3];
            int iMcol5 = mcol((((bArr3[iMcol & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iMcol2 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iMcol3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i9 >> 24) & 255] << 24)) ^ iArr[i8][0];
            int iMcol6 = mcol((((bArr3[iMcol2 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iMcol3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iMcol >> 24) & 255] << 24)) ^ iArr[i8][1];
            int iMcol7 = mcol((((bArr3[iMcol3 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i9 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iMcol >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iMcol2 >> 24) & 255] << 24)) ^ iArr[i8][2];
            int iMcol8 = mcol((((bArr3[i9 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(iMcol >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(iMcol2 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(iMcol3 >> 24) & 255] << 24));
            int i10 = i8 + 1;
            i6 = iMcol8 ^ iArr[i8][3];
            i3 = iMcol5;
            i4 = iMcol6;
            i5 = iMcol7;
            i7 = i10;
        }
        byte[] bArr4 = f3123S;
        int iMcol9 = mcol((((bArr4[i3 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i6 >> 24) & 255] << 24)) ^ iArr[i7][0];
        int iMcol10 = mcol((((bArr4[i4 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i3 >> 24) & 255] << 24)) ^ iArr[i7][1];
        int iMcol11 = mcol((((bArr4[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i4 >> 24) & 255] << 24)) ^ iArr[i7][2];
        int iMcol12 = mcol((((bArr4[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i5 >> 24) & 255] << 24)) ^ iArr[i7][3];
        int i11 = (((bArr4[iMcol9 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(iMcol10 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(iMcol11 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(iMcol12 >> 24) & 255] << 24);
        int[] iArr3 = iArr[i7 + 1];
        int i12 = i11 ^ iArr3[0];
        int i13 = ((((bArr4[iMcol10 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(iMcol11 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(iMcol12 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(iMcol9 >> 24) & 255] << 24)) ^ iArr3[1];
        int i14 = ((((bArr4[iMcol11 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(iMcol12 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(iMcol9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(iMcol10 >> 24) & 255] << 24)) ^ iArr3[2];
        int i15 = ((((bArr4[iMcol12 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(iMcol9 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(iMcol10 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(iMcol11 >> 24) & 255] << 24)) ^ iArr3[3];
        Pack.intToLittleEndian(i12, bArr2, i2 + 0);
        Pack.intToLittleEndian(i13, bArr2, i2 + 4);
        Pack.intToLittleEndian(i14, bArr2, i2 + 8);
        Pack.intToLittleEndian(i15, bArr2, i2 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        int i2 = i + 6;
        this.ROUNDS = i2;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2 + 1, 4);
        int i3 = 8;
        char c = 3;
        if (i == 4) {
            int iLittleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt;
            int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt2;
            int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt3;
            int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt4;
            for (int i4 = 1; i4 <= 10; i4++) {
                iLittleEndianToInt ^= subWord(shift(iLittleEndianToInt4, 8)) ^ rcon[i4 - 1];
                int[] iArr2 = iArr[i4];
                iArr2[0] = iLittleEndianToInt;
                iLittleEndianToInt2 ^= iLittleEndianToInt;
                iArr2[1] = iLittleEndianToInt2;
                iLittleEndianToInt3 ^= iLittleEndianToInt2;
                iArr2[2] = iLittleEndianToInt3;
                iLittleEndianToInt4 ^= iLittleEndianToInt3;
                iArr2[3] = iLittleEndianToInt4;
            }
        } else if (i == 6) {
            int iLittleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt5;
            int iLittleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt6;
            int iLittleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt7;
            int iLittleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt8;
            int iLittleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int iLittleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int i5 = 1;
            int i6 = 1;
            while (true) {
                int[] iArr3 = iArr[i5];
                iArr3[0] = iLittleEndianToInt9;
                iArr3[1] = iLittleEndianToInt10;
                int iSubWord = subWord(shift(iLittleEndianToInt10, 8)) ^ i6;
                int i7 = i6 << 1;
                int i8 = iLittleEndianToInt5 ^ iSubWord;
                int[] iArr4 = iArr[i5];
                iArr4[2] = i8;
                int i9 = iLittleEndianToInt6 ^ i8;
                iArr4[3] = i9;
                int i10 = iLittleEndianToInt7 ^ i9;
                int[] iArr5 = iArr[i5 + 1];
                iArr5[0] = i10;
                int i11 = iLittleEndianToInt8 ^ i10;
                iArr5[1] = i11;
                int i12 = iLittleEndianToInt9 ^ i11;
                iArr5[2] = i12;
                int i13 = iLittleEndianToInt10 ^ i12;
                iArr5[3] = i13;
                int iSubWord2 = subWord(shift(i13, 8)) ^ i7;
                i6 = i7 << 1;
                iLittleEndianToInt5 = i8 ^ iSubWord2;
                int[] iArr6 = iArr[i5 + 2];
                iArr6[0] = iLittleEndianToInt5;
                iLittleEndianToInt6 = i9 ^ iLittleEndianToInt5;
                iArr6[1] = iLittleEndianToInt6;
                iLittleEndianToInt7 = i10 ^ iLittleEndianToInt6;
                iArr6[2] = iLittleEndianToInt7;
                iLittleEndianToInt8 = i11 ^ iLittleEndianToInt7;
                iArr6[3] = iLittleEndianToInt8;
                i5 += 3;
                if (i5 >= 13) {
                    break;
                }
                iLittleEndianToInt9 = i12 ^ iLittleEndianToInt8;
                iLittleEndianToInt10 = i13 ^ iLittleEndianToInt9;
            }
        } else {
            if (i != 8) {
                throw new IllegalStateException("Should never get here");
            }
            int iLittleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt11;
            int iLittleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt12;
            int iLittleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt13;
            int iLittleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt14;
            int iLittleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = iLittleEndianToInt15;
            int iLittleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = iLittleEndianToInt16;
            int iLittleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = iLittleEndianToInt17;
            int iLittleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = iLittleEndianToInt18;
            int i14 = 2;
            int i15 = 1;
            while (true) {
                int iSubWord3 = subWord(shift(iLittleEndianToInt18, i3)) ^ i15;
                i15 <<= 1;
                iLittleEndianToInt11 ^= iSubWord3;
                int[] iArr7 = iArr[i14];
                iArr7[0] = iLittleEndianToInt11;
                iLittleEndianToInt12 ^= iLittleEndianToInt11;
                iArr7[1] = iLittleEndianToInt12;
                iLittleEndianToInt13 ^= iLittleEndianToInt12;
                iArr7[2] = iLittleEndianToInt13;
                iLittleEndianToInt14 ^= iLittleEndianToInt13;
                iArr7[c] = iLittleEndianToInt14;
                int i16 = i14 + 1;
                if (i16 >= 15) {
                    break;
                }
                iLittleEndianToInt15 ^= subWord(iLittleEndianToInt14);
                int[] iArr8 = iArr[i16];
                iArr8[0] = iLittleEndianToInt15;
                iLittleEndianToInt16 ^= iLittleEndianToInt15;
                iArr8[1] = iLittleEndianToInt16;
                iLittleEndianToInt17 ^= iLittleEndianToInt16;
                iArr8[2] = iLittleEndianToInt17;
                iLittleEndianToInt18 ^= iLittleEndianToInt17;
                iArr8[3] = iLittleEndianToInt18;
                i14 = i16 + 1;
                i3 = 8;
                c = 3;
            }
        }
        if (!z) {
            for (int i17 = 1; i17 < this.ROUNDS; i17++) {
                for (int i18 = 0; i18 < 4; i18++) {
                    int[] iArr9 = iArr[i17];
                    iArr9[i18] = inv_mcol(iArr9[i18]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int iShift = shift(i, 8) ^ i;
        int iFFmulX = i ^ FFmulX(iShift);
        int iFFmulX2 = iShift ^ FFmulX2(iFFmulX);
        return iFFmulX ^ (iFFmulX2 ^ shift(iFFmulX2, 16));
    }

    private static int mcol(int i) {
        int iShift = shift(i, 8);
        int i2 = i ^ iShift;
        return FFmulX(i2) ^ (iShift ^ shift(i2, 16));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = f3123S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & UByte.MAX_VALUE) | ((bArr[(i >> 8) & 255] & UByte.MAX_VALUE) << 8) | ((bArr[(i >> 16) & 255] & UByte.MAX_VALUE) << 16);
    }

    @Override
    public String getAlgorithmName() {
        return "AES";
    }

    @Override
    public int getBlockSize() {
        return 16;
    }

    @Override
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
        this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
        this.forEncryption = z;
    }

    @Override
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 > bArr2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            encryptBlock(bArr, i, bArr2, i2, iArr);
        } else {
            decryptBlock(bArr, i, bArr2, i2, iArr);
        }
        return 16;
    }

    @Override
    public void reset() {
    }
}
