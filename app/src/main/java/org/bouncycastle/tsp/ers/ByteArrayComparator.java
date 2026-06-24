package org.bouncycastle.tsp.ers;

import java.util.Comparator;
import kotlin.UByte;

class ByteArrayComparator implements Comparator {
    ByteArrayComparator() {
    }

    @Override
    public int compare(Object obj, Object obj2) {
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = (byte[]) obj2;
        for (int i = 0; i < bArr.length && i < bArr2.length; i++) {
            int i2 = bArr[i] & UByte.MAX_VALUE;
            int i3 = bArr2[i] & UByte.MAX_VALUE;
            if (i2 != i3) {
                return i2 - i3;
            }
        }
        return bArr.length - bArr2.length;
    }
}
