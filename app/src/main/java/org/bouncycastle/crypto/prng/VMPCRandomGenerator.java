package org.bouncycastle.crypto.prng;

import cc.uling.usdk.constants.MdbAddr;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.p037io.encoding.Base64;
import okio.Utf8;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;

public class VMPCRandomGenerator implements RandomGenerator {

    private byte f3396n = 0;

    private byte[] f3395P = {-69, 44, 98, ByteCompanionObject.MAX_VALUE, -75, -86, -44, 13, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, -2, -78, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, -53, SnmpConstants.GET_REQ_MSG, SnmpConstants.GETNEXT_REQ_MSG, 8, 24, 113, 86, -24, 73, 2, 16, -60, -34, 53, SnmpConstants.GETBULK_REQ_MSG, -20, -128, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -72, 105, -38, 47, 117, -52, SnmpConstants.GET_RSP_MSG, 9, 54, 3, 97, 45, -3, -32, -35, 5, SnmpConstants.TIMETICKS, -112, -83, -56, MdbAddr.DEF_ADDR, -81, 87, -101, 76, -40, 81, -82, 80, -123, 60, 10, -28, -13, -100, 38, 35, 83, -55, -125, -105, SnmpConstants.COUNTER64, -79, -103, 100, 49, 119, -43, 29, -42, 120, -67, 94, -80, -118, 34, 56, -8, 104, 43, 42, -59, -45, -9, PSSSigner.TRAILER_IMPLICIT, 111, -33, 4, -27, -107, 62, 37, -122, SnmpConstants.INFORM_REQ_MSG, 11, -113, -15, 36, 14, -41, 64, -77, -49, 126, 6, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, -102, 77, 28, SnmpConstants.SET_REQ_MSG, -37, 50, -110, 88, 17, 39, -12, 89, -48, 78, 106, 23, 91, -84, -1, 7, SnmpConstants.ASN_PRIVATE, 101, 121, -4, -57, -51, 118, SnmpConstants.GAUGE, 93, -25, 58, 52, 122, SnmpConstants.CONS_SEQ, 40, SnmpConstants.SNMP_ERR_UNDOFAILED, 115, 1, -7, -47, -46, 25, -23, -111, -71, 90, -19, SnmpConstants.COUNTER, 109, -76, -61, -98, -65, 99, -6, SnmpConstants.ASN_EXTENSION_ID, 51, 96, SnmpConstants.UINTEGER32, -119, -16, -106, 26, 95, -109, Base64.padSymbol, 55, 75, -39, SnmpConstants.GET_RPRT_MSG, -63, 27, -10, 57, -117, -73, 12, 32, -50, -120, 110, -74, 116, -114, -115, 22, 41, -14, -121, -11, -21, 112, -29, -5, 85, -97, -58, SnmpConstants.OPAQUE, 74, SnmpConstants.NSAP_ADDRESS, 125, -30, 107, 92, 108, 102, -87, -116, -18, -124, SnmpConstants.SNMP_ERR_DECODING_EXC, SnmpConstants.TRPV2_REQ_MSG, 30, -99, -36, 103, 72, -70, 46, -26, SnmpConstants.TRP_REQ_MSG, -85, 124, -108, 0, 33, -17, -22, -66, -54, 114, 79, 82, -104, Utf8.REPLACEMENT_BYTE, -62, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 123, 59, 84};

    private byte f3397s = -66;

    @Override
    public void addSeedMaterial(long j) {
        addSeedMaterial(Pack.longToBigEndian(j));
    }

    @Override
    public void addSeedMaterial(byte[] bArr) {
        for (byte b : bArr) {
            byte[] bArr2 = this.f3395P;
            byte b2 = this.f3397s;
            byte b3 = this.f3396n;
            byte b4 = bArr2[(b2 + bArr2[b3 & UByte.MAX_VALUE] + b) & 255];
            this.f3397s = b4;
            byte b5 = bArr2[b3 & UByte.MAX_VALUE];
            bArr2[b3 & UByte.MAX_VALUE] = bArr2[b4 & UByte.MAX_VALUE];
            bArr2[b4 & UByte.MAX_VALUE] = b5;
            this.f3396n = (byte) ((b3 + 1) & 255);
        }
    }

    @Override
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override
    public void nextBytes(byte[] bArr, int i, int i2) {
        synchronized (this.f3395P) {
            int i3 = i2 + i;
            while (i != i3) {
                byte[] bArr2 = this.f3395P;
                byte b = this.f3397s;
                byte b2 = this.f3396n;
                byte b3 = bArr2[(b + bArr2[b2 & UByte.MAX_VALUE]) & 255];
                this.f3397s = b3;
                bArr[i] = bArr2[(bArr2[bArr2[b3 & UByte.MAX_VALUE] & UByte.MAX_VALUE] + 1) & 255];
                byte b4 = bArr2[b2 & UByte.MAX_VALUE];
                bArr2[b2 & UByte.MAX_VALUE] = bArr2[b3 & UByte.MAX_VALUE];
                bArr2[b3 & UByte.MAX_VALUE] = b4;
                this.f3396n = (byte) ((b2 + 1) & 255);
                i++;
            }
        }
    }
}
