package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import cc.uling.usdk.constants.MdbAddr;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.lang.reflect.Array;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.p037io.encoding.Base64;
import okio.Utf8;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;

public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;

    private long f3190A0;

    private long f3191A1;

    private long f3192A2;

    private long f3193A3;

    private int f3194BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;
    private static final byte[] logtable = {0, 0, 25, 1, 50, 2, 26, -58, 75, -57, 27, 104, 51, -18, -33, 3, 100, 4, -32, 14, 52, -115, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -17, 76, 113, 8, -56, -8, 105, 28, -63, 125, -62, 29, -75, -7, -71, 39, 106, 77, -28, SnmpConstants.INFORM_REQ_MSG, 114, -102, -55, 9, 120, 101, 47, -118, 5, 33, SnmpConstants.SNMP_ERR_UNDOFAILED, MdbAddr.DEF_ADDR, 36, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -16, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, SnmpConstants.NSAP_ADDRESS, 53, -109, -38, -114, -106, -113, -37, -67, 54, -48, -50, -108, SnmpConstants.SNMP_ERR_DECODING_EXC, 92, -46, -15, 64, SnmpConstants.COUNTER64, -125, 56, 102, -35, -3, SnmpConstants.CONS_SEQ, -65, 6, -117, 98, -77, 37, -30, -104, 34, -120, -111, 16, 126, 110, 72, -61, SnmpConstants.SET_REQ_MSG, -74, 30, SnmpConstants.GAUGE, 58, 107, 40, 84, -6, -123, Base64.padSymbol, -70, 43, 121, 10, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, -101, -97, 94, -54, 78, -44, -84, -27, -13, 115, SnmpConstants.TRPV2_REQ_MSG, 87, -81, 88, SnmpConstants.GET_RPRT_MSG, 80, -12, -22, -42, 116, 79, -82, -23, -43, -25, -26, -83, -24, 44, -41, 117, 122, -21, 22, 11, -11, 89, -53, 95, -80, -100, -87, 81, SnmpConstants.GET_REQ_MSG, ByteCompanionObject.MAX_VALUE, 12, -10, 111, 23, -60, 73, -20, -40, SnmpConstants.TIMETICKS, SnmpConstants.ASN_EXTENSION_ID, 45, SnmpConstants.TRP_REQ_MSG, 118, 123, -73, -52, -69, 62, 90, -5, 96, -79, -122, 59, 82, SnmpConstants.GETNEXT_REQ_MSG, 108, -86, 85, 41, -99, -105, -78, -121, -112, 97, -66, -36, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, Utf8.REPLACEMENT_BYTE, 91, -47, 83, 57, -124, 60, SnmpConstants.COUNTER, SnmpConstants.GET_RSP_MSG, 109, SnmpConstants.UINTEGER32, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 42, -98, 93, 86, -14, -45, -85, SnmpConstants.OPAQUE, 17, -110, -39, 35, 32, 46, -119, -76, 124, -72, 38, 119, -103, -29, SnmpConstants.GETBULK_REQ_MSG, 103, 74, -19, -34, -59, 49, -2, 24, 13, 99, -116, -128, SnmpConstants.ASN_PRIVATE, -9, 112, 7};
    private static final byte[] aLogtable = {0, 3, 5, SnmpConstants.SNMP_ERR_UNDOFAILED, 17, 51, 85, -1, 26, 46, 114, -106, SnmpConstants.GETNEXT_REQ_MSG, -8, SnmpConstants.SNMP_ERR_DECODING_EXC, 53, 95, MdbAddr.DEF_ADDR, 56, 72, -40, 115, -107, SnmpConstants.TRP_REQ_MSG, -9, 2, 6, 10, 30, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, 49, 83, -11, 4, 12, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 60, SnmpConstants.OPAQUE, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, SnmpConstants.INFORM_REQ_MSG, -15, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, ByteCompanionObject.MAX_VALUE, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, SnmpConstants.CONS_SEQ, 80, -16, 11, 29, 39, 105, -69, -42, 97, SnmpConstants.SET_REQ_MSG, -2, 25, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, SnmpConstants.GET_REQ_MSG, -5, 22, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, Utf8.REPLACEMENT_BYTE, SnmpConstants.COUNTER, -61, 94, -30, Base64.padSymbol, SnmpConstants.UINTEGER32, -55, 64, SnmpConstants.ASN_PRIVATE, 91, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, 126, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, -128, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, SnmpConstants.TIMETICKS, -59, 84, -4, SnmpConstants.ASN_EXTENSION_ID, 33, 99, SnmpConstants.GETBULK_REQ_MSG, -12, 7, 9, 27, 45, 119, -103, -80, -53, SnmpConstants.COUNTER64, -54, SnmpConstants.NSAP_ADDRESS, -49, 74, -34, 121, -117, -122, -111, SnmpConstants.GET_RPRT_MSG, -29, 62, SnmpConstants.GAUGE, -58, 81, -13, 14, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, SnmpConstants.TRPV2_REQ_MSG, -14, 13, 23, 57, 75, -35, 124, -124, -105, SnmpConstants.GET_RSP_MSG, -3, 28, 36, 108, -76, -57, 82, -10, 1, 3, 5, SnmpConstants.SNMP_ERR_UNDOFAILED, 17, 51, 85, -1, 26, 46, 114, -106, SnmpConstants.GETNEXT_REQ_MSG, -8, SnmpConstants.SNMP_ERR_DECODING_EXC, 53, 95, MdbAddr.DEF_ADDR, 56, 72, -40, 115, -107, SnmpConstants.TRP_REQ_MSG, -9, 2, 6, 10, 30, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, 49, 83, -11, 4, 12, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 60, SnmpConstants.OPAQUE, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, SnmpConstants.INFORM_REQ_MSG, -15, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, ByteCompanionObject.MAX_VALUE, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, SnmpConstants.CONS_SEQ, 80, -16, 11, 29, 39, 105, -69, -42, 97, SnmpConstants.SET_REQ_MSG, -2, 25, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, SnmpConstants.GET_REQ_MSG, -5, 22, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, Utf8.REPLACEMENT_BYTE, SnmpConstants.COUNTER, -61, 94, -30, Base64.padSymbol, SnmpConstants.UINTEGER32, -55, 64, SnmpConstants.ASN_PRIVATE, 91, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, 126, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, -128, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, SnmpConstants.TIMETICKS, -59, 84, -4, SnmpConstants.ASN_EXTENSION_ID, 33, 99, SnmpConstants.GETBULK_REQ_MSG, -12, 7, 9, 27, 45, 119, -103, -80, -53, SnmpConstants.COUNTER64, -54, SnmpConstants.NSAP_ADDRESS, -49, 74, -34, 121, -117, -122, -111, SnmpConstants.GET_RPRT_MSG, -29, 62, SnmpConstants.GAUGE, -58, 81, -13, 14, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, SnmpConstants.TRPV2_REQ_MSG, -14, 13, 23, 57, 75, -35, 124, -124, -105, SnmpConstants.GET_RSP_MSG, -3, 28, 36, 108, -76, -57, 82, -10, 1};

    private static final byte[] f3188S = {99, 124, 119, 123, -14, 107, 111, -59, SnmpConstants.CONS_SEQ, 1, 103, 43, -2, -41, -85, 118, -54, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -55, 125, -6, 89, SnmpConstants.UINTEGER32, -16, -83, -44, SnmpConstants.GET_RSP_MSG, -81, -100, SnmpConstants.TRP_REQ_MSG, 114, SnmpConstants.ASN_PRIVATE, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, SnmpConstants.GETBULK_REQ_MSG, -27, -15, 113, -40, 49, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, 4, -57, 35, -61, 24, -106, 5, -102, 7, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, SnmpConstants.GET_REQ_MSG, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, SnmpConstants.TIMETICKS, 77, 51, -123, SnmpConstants.NSAP_ADDRESS, -7, 2, ByteCompanionObject.MAX_VALUE, 80, 60, -97, SnmpConstants.GET_RPRT_MSG, 81, SnmpConstants.SET_REQ_MSG, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46, -51, 12, SnmpConstants.SNMP_ERR_DECODING_EXC, -20, 95, -105, SnmpConstants.OPAQUE, 23, -60, SnmpConstants.TRPV2_REQ_MSG, 126, Base64.padSymbol, 100, 93, 25, 115, 96, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, 79, -36, 34, 42, -112, -120, SnmpConstants.COUNTER64, -18, -72, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, SnmpConstants.INFORM_REQ_MSG, -76, -58, -24, -35, 116, SnmpConstants.ASN_EXTENSION_ID, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, MdbAddr.DEF_ADDR, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, SnmpConstants.GETNEXT_REQ_MSG, -119, 13, -65, -26, SnmpConstants.GAUGE, 104, SnmpConstants.COUNTER, -103, 45, SnmpConstants.SNMP_ERR_UNDOFAILED, -80, 84, -69, 22};

    private static final byte[] f3189Si = {82, 9, 106, -43, SnmpConstants.CONS_SEQ, 54, SnmpConstants.GETBULK_REQ_MSG, 56, -65, 64, SnmpConstants.SET_REQ_MSG, -98, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -13, -41, -5, 124, -29, 57, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -101, 47, -1, -121, 52, -114, SnmpConstants.TIMETICKS, SnmpConstants.OPAQUE, -60, -34, -23, -53, 84, 123, -108, 50, SnmpConstants.INFORM_REQ_MSG, -62, 35, Base64.padSymbol, -18, 76, -107, 11, SnmpConstants.GAUGE, -6, -61, 78, 8, 46, SnmpConstants.GETNEXT_REQ_MSG, 102, 40, -39, 36, -78, 118, 91, SnmpConstants.GET_RSP_MSG, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, SnmpConstants.TRP_REQ_MSG, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, SnmpConstants.COUNTER64, 87, SnmpConstants.TRPV2_REQ_MSG, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, SnmpConstants.NSAP_ADDRESS, 6, -48, 44, 30, -113, -54, Utf8.REPLACEMENT_BYTE, SnmpConstants.SNMP_ERR_UNDOFAILED, 2, -63, -81, -67, 3, 1, SnmpConstants.SNMP_ERR_DECODING_EXC, -118, 107, 58, -111, 17, SnmpConstants.COUNTER, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, SnmpConstants.UINTEGER32, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, SnmpConstants.ASN_PRIVATE, -2, 120, -51, 90, -12, SnmpConstants.ASN_EXTENSION_ID, -35, SnmpConstants.GET_RPRT_MSG, 51, -120, 7, -57, 49, -79, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 16, 89, 39, -128, -20, 95, 96, 81, ByteCompanionObject.MAX_VALUE, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, SnmpConstants.GET_REQ_MSG, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, MdbAddr.DEF_ADDR, 105, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 99, 85, 33, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};
    static byte[][] shifts0 = {new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, 24, 32}};
    static byte[][] shifts1 = {new byte[]{0, 24, 16, 8}, new byte[]{0, 32, 24, 16}, new byte[]{0, 40, 32, 24}, new byte[]{0, SnmpConstants.CONS_SEQ, 40, 24}, new byte[]{0, 56, 40, 32}};

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i) {
        if (i == 128) {
            this.f3194BC = 32;
            this.BC_MASK = BodyPartID.bodyIdMax;
            this.shifts0SC = shifts0[0];
            this.shifts1SC = shifts1[0];
        } else if (i == 160) {
            this.f3194BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = shifts0[1];
            this.shifts1SC = shifts1[1];
        } else if (i == 192) {
            this.f3194BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = shifts0[2];
            this.shifts1SC = shifts1[2];
        } else if (i == 224) {
            this.f3194BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = shifts0[3];
            this.shifts1SC = shifts1[3];
        } else {
            if (i != 256) {
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
            }
            this.f3194BC = 64;
            this.BC_MASK = -1L;
            this.shifts0SC = shifts0[4];
            this.shifts1SC = shifts1[4];
        }
        this.blockBits = i;
    }

    private void InvMixColumn() {
        long jMul0xe = 0;
        long jMul0xe2 = 0;
        long jMul0xe3 = 0;
        long jMul0xe4 = 0;
        for (int i = 0; i < this.f3194BC; i += 8) {
            int i2 = (int) ((this.f3190A0 >> i) & 255);
            int i3 = (int) ((this.f3191A1 >> i) & 255);
            int i4 = (int) ((this.f3192A2 >> i) & 255);
            long j = jMul0xe3;
            int i5 = (int) ((this.f3193A3 >> i) & 255);
            int i6 = -1;
            int i7 = i2 != 0 ? logtable[i2 & 255] & UByte.MAX_VALUE : -1;
            int i8 = i3 != 0 ? logtable[i3 & 255] & UByte.MAX_VALUE : -1;
            int i9 = i4 != 0 ? logtable[i4 & 255] & UByte.MAX_VALUE : -1;
            if (i5 != 0) {
                i6 = logtable[i5 & 255] & UByte.MAX_VALUE;
            }
            jMul0xe |= ((long) ((((mul0xe(i7) ^ mul0xb(i8)) ^ mul0xd(i9)) ^ mul0x9(i6)) & 255)) << i;
            jMul0xe4 |= ((long) ((((mul0xe(i8) ^ mul0xb(i9)) ^ mul0xd(i6)) ^ mul0x9(i7)) & 255)) << i;
            jMul0xe2 |= ((long) ((((mul0xe(i9) ^ mul0xb(i6)) ^ mul0xd(i7)) ^ mul0x9(i8)) & 255)) << i;
            jMul0xe3 = (((long) ((((mul0xe(i6) ^ mul0xb(i7)) ^ mul0xd(i8)) ^ mul0x9(i9)) & 255)) << i) | j;
        }
        this.f3190A0 = jMul0xe;
        this.f3191A1 = jMul0xe4;
        this.f3192A2 = jMul0xe2;
        this.f3193A3 = jMul0xe3;
    }

    private void KeyAddition(long[] jArr) {
        this.f3190A0 ^= jArr[0];
        this.f3191A1 ^= jArr[1];
        this.f3192A2 ^= jArr[2];
        this.f3193A3 ^= jArr[3];
    }

    private void MixColumn() {
        long jMul0x2 = 0;
        long jMul0x22 = 0;
        long jMul0x23 = 0;
        long jMul0x24 = 0;
        for (int i = 0; i < this.f3194BC; i += 8) {
            int i2 = (int) ((this.f3190A0 >> i) & 255);
            int i3 = (int) ((this.f3191A1 >> i) & 255);
            int i4 = (int) ((this.f3192A2 >> i) & 255);
            long j = jMul0x23;
            int i5 = (int) ((this.f3193A3 >> i) & 255);
            jMul0x2 |= ((long) ((((mul0x2(i2) ^ mul0x3(i3)) ^ i4) ^ i5) & 255)) << i;
            jMul0x24 |= ((long) ((((mul0x2(i3) ^ mul0x3(i4)) ^ i5) ^ i2) & 255)) << i;
            jMul0x22 |= ((long) ((((mul0x2(i4) ^ mul0x3(i5)) ^ i2) ^ i3) & 255)) << i;
            jMul0x23 = (((long) ((((mul0x2(i5) ^ mul0x3(i2)) ^ i3) ^ i4) & 255)) << i) | j;
        }
        this.f3190A0 = jMul0x2;
        this.f3191A1 = jMul0x24;
        this.f3192A2 = jMul0x22;
        this.f3193A3 = jMul0x23;
    }

    private void ShiftRow(byte[] bArr) {
        this.f3191A1 = shift(this.f3191A1, bArr[1]);
        this.f3192A2 = shift(this.f3192A2, bArr[2]);
        this.f3193A3 = shift(this.f3193A3, bArr[3]);
    }

    private void Substitution(byte[] bArr) {
        this.f3190A0 = applyS(this.f3190A0, bArr);
        this.f3191A1 = applyS(this.f3191A1, bArr);
        this.f3192A2 = applyS(this.f3192A2, bArr);
        this.f3193A3 = applyS(this.f3193A3, bArr);
    }

    private long applyS(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.f3194BC; i += 8) {
            j2 |= ((long) (bArr[(int) ((j >> i) & 255)] & UByte.MAX_VALUE)) << i;
        }
        return j2;
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(f3189Si);
        ShiftRow(this.shifts1SC);
        for (int i = this.ROUNDS - 1; i > 0; i--) {
            KeyAddition(jArr[i]);
            InvMixColumn();
            Substitution(f3189Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int i = 1; i < this.ROUNDS; i++) {
            Substitution(f3188S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[i]);
        }
        Substitution(f3188S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int i;
        int i2;
        int i3 = 8;
        int length = bArr.length * 8;
        int i4 = 4;
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 15, 4);
        if (length == 128) {
            i = 4;
        } else if (length == 160) {
            i = 5;
        } else if (length == 192) {
            i = 6;
        } else if (length == 224) {
            i = 7;
        } else {
            if (length != 256) {
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
            }
            i = 8;
        }
        this.ROUNDS = length >= this.blockBits ? i + 6 : (this.f3194BC / 8) + 6;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < bArr.length) {
            bArr2[i6 % 4][i6 / 4] = bArr[i7];
            i6++;
            i7++;
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < i && i9 < (this.ROUNDS + 1) * (this.f3194BC / 8)) {
            int i10 = 0;
            while (i10 < i4) {
                int i11 = this.f3194BC;
                long[] jArr2 = jArr[i9 / (i11 / 8)];
                jArr2[i10] = (((long) (bArr2[i10][i8] & UByte.MAX_VALUE)) << ((i9 * 8) % i11)) | jArr2[i10];
                i10++;
                jArr = jArr;
                i4 = 4;
            }
            i8++;
            i9++;
            i4 = 4;
        }
        long[][] jArr3 = jArr;
        int i12 = 0;
        while (i9 < (this.ROUNDS + 1) * (this.f3194BC / i3)) {
            int i13 = i5;
            while (i13 < 4) {
                byte[] bArr3 = bArr2[i13];
                i13++;
                bArr3[i5] = (byte) (bArr3[i5] ^ f3188S[bArr2[i13 % 4][i - 1] & UByte.MAX_VALUE]);
            }
            byte[] bArr4 = bArr2[i5];
            int i14 = i12 + 1;
            bArr4[i5] = (byte) (rcon[i12] ^ bArr4[i5]);
            int i15 = 1;
            if (i <= 6) {
                while (i15 < i) {
                    for (int i16 = i5; i16 < 4; i16++) {
                        byte[] bArr5 = bArr2[i16];
                        bArr5[i15] = (byte) (bArr5[i15] ^ bArr5[i15 - 1]);
                    }
                    i15++;
                }
            } else {
                while (true) {
                    i2 = 4;
                    if (i15 >= 4) {
                        break;
                    }
                    int i17 = i5;
                    while (i17 < i2) {
                        byte[] bArr6 = bArr2[i17];
                        bArr6[i15] = (byte) (bArr6[i15] ^ bArr6[i15 - 1]);
                        i17++;
                        i2 = 4;
                    }
                    i15++;
                }
                for (int i18 = i5; i18 < 4; i18++) {
                    byte[] bArr7 = bArr2[i18];
                    bArr7[4] = (byte) (bArr7[4] ^ f3188S[bArr7[3] & UByte.MAX_VALUE]);
                }
                int i19 = 5;
                while (i19 < i) {
                    int i20 = i5;
                    while (i20 < i2) {
                        byte[] bArr8 = bArr2[i20];
                        bArr8[i19] = (byte) (bArr8[i19] ^ bArr8[i19 - 1]);
                        i20++;
                        i2 = 4;
                    }
                    i19++;
                    i2 = 4;
                }
            }
            int i21 = i5;
            while (i21 < i && i9 < (this.ROUNDS + 1) * (this.f3194BC / i3)) {
                int i22 = i5;
                while (i22 < 4) {
                    int i23 = this.f3194BC;
                    long[] jArr4 = jArr3[i9 / (i23 / 8)];
                    jArr4[i22] = (((long) (bArr2[i22][i21] & UByte.MAX_VALUE)) << ((i9 * 8) % i23)) | jArr4[i22];
                    i22++;
                    bArr2 = bArr2;
                }
                i21++;
                i9++;
                i5 = 0;
                i3 = 8;
            }
            i12 = i14;
            bArr2 = bArr2;
            i5 = 0;
            i3 = 8;
        }
        return jArr3;
    }

    private byte mul0x2(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & UByte.MAX_VALUE) + 25];
        }
        return (byte) 0;
    }

    private byte mul0x3(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & UByte.MAX_VALUE) + 1];
        }
        return (byte) 0;
    }

    private byte mul0x9(int i) {
        if (i >= 0) {
            return aLogtable[i + 199];
        }
        return (byte) 0;
    }

    private byte mul0xb(int i) {
        if (i >= 0) {
            return aLogtable[i + 104];
        }
        return (byte) 0;
    }

    private byte mul0xd(int i) {
        if (i >= 0) {
            return aLogtable[i + 238];
        }
        return (byte) 0;
    }

    private byte mul0xe(int i) {
        if (i >= 0) {
            return aLogtable[i + 223];
        }
        return (byte) 0;
    }

    private void packBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.f3194BC; i2 += 8) {
            int i3 = i + 1;
            bArr[i] = (byte) (this.f3190A0 >> i2);
            int i4 = i3 + 1;
            bArr[i3] = (byte) (this.f3191A1 >> i2);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (this.f3192A2 >> i2);
            i = i5 + 1;
            bArr[i5] = (byte) (this.f3193A3 >> i2);
        }
    }

    private long shift(long j, int i) {
        return ((j << (this.f3194BC - i)) | (j >>> i)) & this.BC_MASK;
    }

    private void unpackBlock(byte[] bArr, int i) {
        this.f3190A0 = bArr[i] & UByte.MAX_VALUE;
        this.f3191A1 = bArr[r0] & UByte.MAX_VALUE;
        this.f3192A2 = bArr[r8] & UByte.MAX_VALUE;
        int i2 = i + 1 + 1 + 1 + 1;
        this.f3193A3 = bArr[r0] & UByte.MAX_VALUE;
        for (int i3 = 8; i3 != this.f3194BC; i3 += 8) {
            int i4 = i2 + 1;
            this.f3190A0 |= ((long) (bArr[i2] & UByte.MAX_VALUE)) << i3;
            int i5 = i4 + 1;
            this.f3191A1 |= ((long) (bArr[i4] & UByte.MAX_VALUE)) << i3;
            int i6 = i5 + 1;
            this.f3192A2 |= ((long) (bArr[i5] & UByte.MAX_VALUE)) << i3;
            i2 = i6 + 1;
            this.f3193A3 |= ((long) (bArr[i6] & UByte.MAX_VALUE)) << i3;
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override
    public int getBlockSize() {
        return this.f3194BC / 2;
    }

    @Override
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
        }
        this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
        this.forEncryption = z;
    }

    @Override
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        }
        int i3 = this.f3194BC;
        if ((i3 / 2) + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if ((i3 / 2) + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        long[][] jArr = this.workingKey;
        if (z) {
            encryptBlock(jArr);
        } else {
            decryptBlock(jArr);
        }
        packBlock(bArr2, i2);
        return this.f3194BC / 2;
    }

    @Override
    public void reset() {
    }
}
