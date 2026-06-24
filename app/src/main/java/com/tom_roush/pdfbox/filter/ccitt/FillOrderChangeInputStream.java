package com.tom_roush.pdfbox.filter.ccitt;

import cc.uling.usdk.constants.MdbAddr;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.p037io.encoding.Base64;
import okio.Utf8;
import org.bouncycastle.crypto.signers.PSSSigner;

public final class FillOrderChangeInputStream extends FilterInputStream {
    private static final byte[] FLIP_TABLE = {0, -128, 64, SnmpConstants.ASN_PRIVATE, 32, SnmpConstants.GET_REQ_MSG, 96, -32, 16, -112, 80, -48, SnmpConstants.CONS_SEQ, -80, 112, -16, 8, -120, 72, -56, 40, SnmpConstants.GET_RPRT_MSG, 104, -24, 24, -104, 88, -40, 56, -72, 120, -8, 4, -124, SnmpConstants.OPAQUE, -60, 36, SnmpConstants.TRP_REQ_MSG, 100, -28, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, -108, 84, -44, 52, -76, 116, -12, 12, -116, 76, -52, 44, -84, 108, -20, 28, -100, 92, -36, 60, PSSSigner.TRAILER_IMPLICIT, 124, -4, 2, SnmpConstants.SNMP_VAR_ENDOFMIBVIEW, SnmpConstants.GAUGE, -62, 34, SnmpConstants.GET_RSP_MSG, 98, -30, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -110, 82, -46, 50, -78, 114, -14, 10, -118, 74, -54, 42, -86, 106, -22, 26, -102, 90, -38, 58, -70, 122, -6, 6, -122, SnmpConstants.COUNTER64, -58, 38, SnmpConstants.INFORM_REQ_MSG, 102, -26, 22, -106, 86, -42, 54, -74, 118, -10, 14, -114, 78, -50, 46, -82, 110, -18, 30, -98, 94, -34, 62, -66, 126, -2, 1, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, SnmpConstants.COUNTER, -63, 33, SnmpConstants.GETNEXT_REQ_MSG, 97, MdbAddr.DEF_ADDR, 17, -111, 81, -47, 49, -79, 113, -15, 9, -119, 73, -55, 41, -87, 105, -23, 25, -103, 89, -39, 57, -71, 121, -7, 5, -123, SnmpConstants.NSAP_ADDRESS, -59, 37, SnmpConstants.GETBULK_REQ_MSG, 101, -27, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, -107, 85, -43, 53, -75, 117, -11, 13, -115, 77, -51, 45, -83, 109, -19, 29, -99, 93, -35, Base64.padSymbol, -67, 125, -3, 3, -125, SnmpConstants.TIMETICKS, -61, 35, SnmpConstants.SET_REQ_MSG, 99, -29, SnmpConstants.SNMP_ERR_DECODING_EXC, -109, 83, -45, 51, -77, 115, -13, 11, -117, 75, -53, 43, -85, 107, -21, 27, -101, 91, -37, 59, -69, 123, -5, 7, -121, SnmpConstants.UINTEGER32, -57, 39, SnmpConstants.TRPV2_REQ_MSG, 103, -25, 23, -105, 87, -41, 55, -73, 119, -9, SnmpConstants.SNMP_ERR_UNDOFAILED, -113, 79, -49, 47, -81, 111, -17, SnmpConstants.ASN_EXTENSION_ID, -97, 95, -33, Utf8.REPLACEMENT_BYTE, -65, ByteCompanionObject.MAX_VALUE, -1};

    public FillOrderChangeInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = super.read(bArr, i, i2);
        if (i3 > 0) {
            int i4 = i + i3;
            while (i < i4) {
                bArr[i] = FLIP_TABLE[bArr[i] & UByte.MAX_VALUE];
                i++;
            }
        }
        return i3;
    }

    @Override
    public int read() throws IOException {
        int i = super.read();
        return i < 0 ? i : FLIP_TABLE[i] & UByte.MAX_VALUE;
    }
}
