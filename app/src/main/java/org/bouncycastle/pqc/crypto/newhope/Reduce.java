package org.bouncycastle.pqc.crypto.newhope;

import com.epson.isv.eprinterdriver.Common.ErrorCode;
import kotlin.UShort;

class Reduce {
    static final int QInv = 12287;
    static final int RLog = 18;
    static final int RMask = 262143;

    Reduce() {
    }

    static short barrett(short s) {
        int i = s & UShort.MAX_VALUE;
        return (short) (i - (((i * 5) >>> 16) * ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_QUERY_EXECUTING));
    }

    static short montgomery(int i) {
        return (short) (((((i * QInv) & RMask) * ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_QUERY_EXECUTING) + i) >>> 18);
    }
}
