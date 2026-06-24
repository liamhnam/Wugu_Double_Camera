package com.p020hp.jipp.util;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0007\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\b\u001a\u0016\u0010\t\u001a\u00020\u0006*\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u0001H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m1293d2 = {"BITS_IN_NYBBLE", "", "NYBBLE_MASK", "hexChars", "", "toHexString", "", "", "", "toWrappedHexString", "chunk", "jipp-core"}, m1294k = 2, m1295mv = {1, 4, 0})
public final class BytesKt {
    private static final int BITS_IN_NYBBLE = 4;
    private static final int NYBBLE_MASK = 15;
    private static final char[] hexChars;

    public static final String toWrappedHexString(byte[] bArr) {
        return toWrappedHexString$default(bArr, 0, 1, null);
    }

    static {
        char[] charArray = "0123456789abcdef".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "(this as java.lang.String).toCharArray()");
        hexChars = charArray;
    }

    public static final String toHexString(byte b) {
        char[] cArr = hexChars;
        char c = cArr[(b >> 4) & 15];
        return new StringBuilder().append(c).append(cArr[b & SnmpConstants.SNMP_ERR_UNDOFAILED]).toString();
    }

    public static final String toHexString(byte[] toHexString) {
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        return ArraysKt.joinToString$default(toHexString, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() {
            @Override
            public CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }

            public final CharSequence invoke(byte b) {
                return BytesKt.toHexString(b);
            }
        }, 30, (Object) null);
    }

    public static String toWrappedHexString$default(byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 32;
        }
        return toWrappedHexString(bArr, i);
    }

    public static final String toWrappedHexString(final byte[] toWrappedHexString, final int i) {
        Intrinsics.checkNotNullParameter(toWrappedHexString, "$this$toWrappedHexString");
        return CollectionsKt.joinToString$default(new IntRange(0, toWrappedHexString.length / i), "\n", null, null, 0, null, new Function1<Integer, CharSequence>() {
            {
                super(1);
            }

            @Override
            public CharSequence invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final CharSequence invoke(int i2) {
                byte[] bArr = toWrappedHexString;
                int i3 = i;
                byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArr, i2 * i3, Math.min(bArr.length, (i2 + 1) * i3));
                StringBuilder sbAppend = new StringBuilder().append(BytesKt.toHexString(bArrCopyOfRange)).append(" ");
                ArrayList arrayList = new ArrayList(bArrCopyOfRange.length);
                int length = bArrCopyOfRange.length;
                for (int i4 = 0; i4 < length; i4++) {
                    byte b = bArrCopyOfRange[i4];
                    arrayList.add(Character.valueOf((32 <= b && 127 >= b) ? (char) b : '.'));
                }
                return sbAppend.append(CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null)).toString();
            }
        }, 30, null);
    }
}
