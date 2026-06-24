package kotlin;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import okhttp3.internal.p040ws.WebSocketProtocol;

@Metadata(m1292d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001vB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u0010J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0013J\u001b\u0010&\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0010J\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0013J\u001b\u00102\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001b\u00107\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010\u0013J\u001b\u00107\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b;\u0010\u001fJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0010J\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0013J\u001b\u0010?\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010GJ\u001b\u0010J\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u0010J\u001b\u0010J\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0013J\u001b\u0010J\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bM\u0010\u001fJ\u001b\u0010J\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u0018J\u001b\u0010O\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bP\u0010\u0010J\u001b\u0010O\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u0013J\u001b\u0010O\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bR\u0010\u001fJ\u001b\u0010O\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bS\u0010\u0018J\u0010\u0010T\u001a\u00020UH\u0087\b¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020YH\u0087\b¢\u0006\u0004\bZ\u0010[J\u0010\u0010\\\u001a\u00020]H\u0087\b¢\u0006\u0004\b^\u0010_J\u0010\u0010`\u001a\u00020\rH\u0087\b¢\u0006\u0004\ba\u0010-J\u0010\u0010b\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bg\u0010\u0005J\u000f\u0010h\u001a\u00020iH\u0016¢\u0006\u0004\bj\u0010kJ\u0016\u0010l\u001a\u00020\u000eH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bm\u0010WJ\u0016\u0010n\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bo\u0010-J\u0016\u0010p\u001a\u00020\u0014H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bq\u0010eJ\u0016\u0010r\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bs\u0010\u0005J\u001b\u0010t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bu\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006w"}, m1293d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-Mh2AYeg", TtmlNode.TAG_DIV, "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(SLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(S)I", "inc", "inc-Mh2AYeg", "inv", "inv-Mh2AYeg", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(SB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-xj2QHRw", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toDouble", "", "toDouble-impl", "(S)D", "toFloat", "", "toFloat-impl", "(S)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
@JvmInline
public final class UShort implements Comparable<UShort> {
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    public static final UShort m2047boximpl(short s) {
        return new UShort(s);
    }

    public static short m2053constructorimpl(short s) {
        return s;
    }

    public static boolean m2059equalsimpl(short s, Object obj) {
        return (obj instanceof UShort) && s == ((UShort) obj).getData();
    }

    public static final boolean m2060equalsimpl0(short s, short s2) {
        return s == s2;
    }

    public static void getData$annotations() {
    }

    public static int m2065hashCodeimpl(short s) {
        return Short.hashCode(s);
    }

    private static final byte m2091toByteimpl(short s) {
        return (byte) s;
    }

    private static final double m2092toDoubleimpl(short s) {
        return s & MAX_VALUE;
    }

    private static final float m2093toFloatimpl(short s) {
        return s & MAX_VALUE;
    }

    private static final int m2094toIntimpl(short s) {
        return s & MAX_VALUE;
    }

    private static final long m2095toLongimpl(short s) {
        return ((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    private static final short m2096toShortimpl(short s) {
        return s;
    }

    private static final short m2101toUShortMh2AYeg(short s) {
        return s;
    }

    public boolean equals(Object obj) {
        return m2059equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m2065hashCodeimpl(this.data);
    }

    public final short getData() {
        return this.data;
    }

    @Override
    public int compareTo(UShort uShort) {
        return Intrinsics.compare(getData() & MAX_VALUE, uShort.getData() & MAX_VALUE);
    }

    private UShort(short s) {
        this.data = s;
    }

    private static final int m2048compareTo7apg3OU(short s, byte b) {
        return Intrinsics.compare(s & MAX_VALUE, b & UByte.MAX_VALUE);
    }

    private int m2051compareToxj2QHRw(short s) {
        return Intrinsics.compare(getData() & MAX_VALUE, s & MAX_VALUE);
    }

    private static int m2052compareToxj2QHRw(short s, short s2) {
        return Intrinsics.compare(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    private static final int m2050compareToWZ4Q5Ns(short s, int i) {
        return Integer.compare(UInt.m1867constructorimpl(s & MAX_VALUE) ^ Integer.MIN_VALUE, i ^ Integer.MIN_VALUE);
    }

    private static final int m2049compareToVKZWuLQ(short s, long j) {
        return Long.compare(ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX) ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE);
    }

    private static final int m2077plus7apg3OU(short s, byte b) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) + UInt.m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m2080plusxj2QHRw(short s, short s2) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) + UInt.m1867constructorimpl(s2 & MAX_VALUE));
    }

    private static final int m2079plusWZ4Q5Ns(short s, int i) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) + i);
    }

    private static final long m2078plusVKZWuLQ(short s, long j) {
        return ULong.m1946constructorimpl(ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX) + j);
    }

    private static final int m2068minus7apg3OU(short s, byte b) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) - UInt.m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m2071minusxj2QHRw(short s, short s2) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) - UInt.m1867constructorimpl(s2 & MAX_VALUE));
    }

    private static final int m2070minusWZ4Q5Ns(short s, int i) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) - i);
    }

    private static final long m2069minusVKZWuLQ(short s, long j) {
        return ULong.m1946constructorimpl(ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX) - j);
    }

    private static final int m2087times7apg3OU(short s, byte b) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) * UInt.m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m2090timesxj2QHRw(short s, short s2) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) * UInt.m1867constructorimpl(s2 & MAX_VALUE));
    }

    private static final int m2089timesWZ4Q5Ns(short s, int i) {
        return UInt.m1867constructorimpl(UInt.m1867constructorimpl(s & MAX_VALUE) * i);
    }

    private static final long m2088timesVKZWuLQ(short s, long j) {
        return ULong.m1946constructorimpl(ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX) * j);
    }

    private static final int m2055div7apg3OU(short s, byte b) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m2058divxj2QHRw(short s, short s2) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(s2 & MAX_VALUE));
    }

    private static final int m2057divWZ4Q5Ns(short s, int i) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1867constructorimpl(s & MAX_VALUE), i);
    }

    private static final long m2056divVKZWuLQ(short s, long j) {
        return UByte$$ExternalSyntheticBackport0.m$1(ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX), j);
    }

    private static final int m2083rem7apg3OU(short s, byte b) {
        return UByte$$ExternalSyntheticBackport0.m1301m(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m2086remxj2QHRw(short s, short s2) {
        return UByte$$ExternalSyntheticBackport0.m1301m(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(s2 & MAX_VALUE));
    }

    private static final int m2085remWZ4Q5Ns(short s, int i) {
        return UByte$$ExternalSyntheticBackport0.m1301m(UInt.m1867constructorimpl(s & MAX_VALUE), i);
    }

    private static final long m2084remVKZWuLQ(short s, long j) {
        return UByte$$ExternalSyntheticBackport0.m1303m(ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX), j);
    }

    private static final int m2061floorDiv7apg3OU(short s, byte b) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m2064floorDivxj2QHRw(short s, short s2) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(s2 & MAX_VALUE));
    }

    private static final int m2063floorDivWZ4Q5Ns(short s, int i) {
        return UByte$$ExternalSyntheticBackport0.m$1(UInt.m1867constructorimpl(s & MAX_VALUE), i);
    }

    private static final long m2062floorDivVKZWuLQ(short s, long j) {
        return UByte$$ExternalSyntheticBackport0.m$1(ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX), j);
    }

    private static final byte m2072mod7apg3OU(short s, byte b) {
        return UByte.m1790constructorimpl((byte) UByte$$ExternalSyntheticBackport0.m1301m(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(b & UByte.MAX_VALUE)));
    }

    private static final short m2075modxj2QHRw(short s, short s2) {
        return m2053constructorimpl((short) UByte$$ExternalSyntheticBackport0.m1301m(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(s2 & MAX_VALUE)));
    }

    private static final int m2074modWZ4Q5Ns(short s, int i) {
        return UByte$$ExternalSyntheticBackport0.m1301m(UInt.m1867constructorimpl(s & MAX_VALUE), i);
    }

    private static final long m2073modVKZWuLQ(short s, long j) {
        return UByte$$ExternalSyntheticBackport0.m1303m(ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX), j);
    }

    private static final short m2066incMh2AYeg(short s) {
        return m2053constructorimpl((short) (s + 1));
    }

    private static final short m2054decMh2AYeg(short s) {
        return m2053constructorimpl((short) (s - 1));
    }

    private static final UIntRange m2081rangeToxj2QHRw(short s, short s2) {
        return new UIntRange(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(s2 & MAX_VALUE), null);
    }

    private static final UIntRange m2082rangeUntilxj2QHRw(short s, short s2) {
        return URangesKt.m3052untilJ1ME1BU(UInt.m1867constructorimpl(s & MAX_VALUE), UInt.m1867constructorimpl(s2 & MAX_VALUE));
    }

    private static final short m2046andxj2QHRw(short s, short s2) {
        return m2053constructorimpl((short) (s & s2));
    }

    private static final short m2076orxj2QHRw(short s, short s2) {
        return m2053constructorimpl((short) (s | s2));
    }

    private static final short m2102xorxj2QHRw(short s, short s2) {
        return m2053constructorimpl((short) (s ^ s2));
    }

    private static final short m2067invMh2AYeg(short s) {
        return m2053constructorimpl((short) (~s));
    }

    private static final byte m2098toUBytew2LRezQ(short s) {
        return UByte.m1790constructorimpl((byte) s);
    }

    private static final int m2099toUIntpVg5ArA(short s) {
        return UInt.m1867constructorimpl(s & MAX_VALUE);
    }

    private static final long m2100toULongsVKNKU(short s) {
        return ULong.m1946constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    public static String m2097toStringimpl(short s) {
        return String.valueOf(s & MAX_VALUE);
    }

    public String toString() {
        return m2097toStringimpl(this.data);
    }
}
