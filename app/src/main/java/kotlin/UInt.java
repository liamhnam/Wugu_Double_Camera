package kotlin;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.jvm.JvmInline;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import org.bouncycastle.asn1.cmc.BodyPartID;

@Metadata(m1292d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 {2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001{B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\u000fJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u000bJ\u001b\u0010$\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u001dJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010\u0005J\u0016\u0010-\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010\u0005J\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000fJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u0010/\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u001dJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0016J\u001b\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u001dJ\u001b\u00104\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000fJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u001dJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0016J\u001b\u0010B\u001a\u00020C2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020C2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bG\u0010EJ\u001b\u0010H\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u000fJ\u001b\u0010H\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u000bJ\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u001dJ\u001b\u0010H\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0016J\u001e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u0003H\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010\u000bJ\u001e\u0010P\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u0003H\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bQ\u0010\u000bJ\u001b\u0010R\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bS\u0010\u000fJ\u001b\u0010R\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bT\u0010\u000bJ\u001b\u0010R\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bU\u0010\u001dJ\u001b\u0010R\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bV\u0010\u0016J\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\\H\u0087\b¢\u0006\u0004\b]\u0010^J\u0010\u0010_\u001a\u00020`H\u0087\b¢\u0006\u0004\ba\u0010bJ\u0010\u0010c\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bd\u0010\u0005J\u0010\u0010e\u001a\u00020fH\u0087\b¢\u0006\u0004\bg\u0010hJ\u0010\u0010i\u001a\u00020jH\u0087\b¢\u0006\u0004\bk\u0010lJ\u000f\u0010m\u001a\u00020nH\u0016¢\u0006\u0004\bo\u0010pJ\u0016\u0010q\u001a\u00020\rH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\br\u0010ZJ\u0016\u0010s\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bt\u0010\u0005J\u0016\u0010u\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bv\u0010hJ\u0016\u0010w\u001a\u00020\u0014H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bx\u0010lJ\u001b\u0010y\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bz\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006|"}, m1293d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", TtmlNode.TAG_DIV, "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(IB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-WZ4Q5Ns", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
@JvmInline
public final class UInt implements Comparable<UInt> {
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    public static final UInt m1861boximpl(int i) {
        return new UInt(i);
    }

    public static int m1867constructorimpl(int i) {
        return i;
    }

    public static boolean m1873equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).getData();
    }

    public static final boolean m1874equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public static void getData$annotations() {
    }

    public static int m1879hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    private static final byte m1907toByteimpl(int i) {
        return (byte) i;
    }

    private static final int m1910toIntimpl(int i) {
        return i;
    }

    private static final long m1911toLongimpl(int i) {
        return ((long) i) & BodyPartID.bodyIdMax;
    }

    private static final short m1912toShortimpl(int i) {
        return (short) i;
    }

    private static final int m1915toUIntpVg5ArA(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m1873equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m1879hashCodeimpl(this.data);
    }

    public final int getData() {
        return this.data;
    }

    @Override
    public int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(getData(), uInt.getData());
    }

    private UInt(int i) {
        this.data = i;
    }

    private static final int m1862compareTo7apg3OU(int i, byte b) {
        return Integer.compare(i ^ Integer.MIN_VALUE, m1867constructorimpl(b & UByte.MAX_VALUE) ^ Integer.MIN_VALUE);
    }

    private static final int m1866compareToxj2QHRw(int i, short s) {
        return Integer.compare(i ^ Integer.MIN_VALUE, m1867constructorimpl(s & UShort.MAX_VALUE) ^ Integer.MIN_VALUE);
    }

    private int m1864compareToWZ4Q5Ns(int i) {
        return UnsignedKt.uintCompare(getData(), i);
    }

    private static int m1865compareToWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2);
    }

    private static final int m1863compareToVKZWuLQ(int i, long j) {
        return Long.compare(ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax) ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE);
    }

    private static final int m1891plus7apg3OU(int i, byte b) {
        return m1867constructorimpl(i + m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m1894plusxj2QHRw(int i, short s) {
        return m1867constructorimpl(i + m1867constructorimpl(s & UShort.MAX_VALUE));
    }

    private static final int m1893plusWZ4Q5Ns(int i, int i2) {
        return m1867constructorimpl(i + i2);
    }

    private static final long m1892plusVKZWuLQ(int i, long j) {
        return ULong.m1946constructorimpl(ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax) + j);
    }

    private static final int m1882minus7apg3OU(int i, byte b) {
        return m1867constructorimpl(i - m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m1885minusxj2QHRw(int i, short s) {
        return m1867constructorimpl(i - m1867constructorimpl(s & UShort.MAX_VALUE));
    }

    private static final int m1884minusWZ4Q5Ns(int i, int i2) {
        return m1867constructorimpl(i - i2);
    }

    private static final long m1883minusVKZWuLQ(int i, long j) {
        return ULong.m1946constructorimpl(ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax) - j);
    }

    private static final int m1903times7apg3OU(int i, byte b) {
        return m1867constructorimpl(i * m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m1906timesxj2QHRw(int i, short s) {
        return m1867constructorimpl(i * m1867constructorimpl(s & UShort.MAX_VALUE));
    }

    private static final int m1905timesWZ4Q5Ns(int i, int i2) {
        return m1867constructorimpl(i * i2);
    }

    private static final long m1904timesVKZWuLQ(int i, long j) {
        return ULong.m1946constructorimpl(ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax) * j);
    }

    private static final int m1869div7apg3OU(int i, byte b) {
        return UByte$$ExternalSyntheticBackport0.m$1(i, m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m1872divxj2QHRw(int i, short s) {
        return UByte$$ExternalSyntheticBackport0.m$1(i, m1867constructorimpl(s & UShort.MAX_VALUE));
    }

    private static final int m1871divWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m2123uintDivideJ1ME1BU(i, i2);
    }

    private static final long m1870divVKZWuLQ(int i, long j) {
        return UByte$$ExternalSyntheticBackport0.m$1(ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax), j);
    }

    private static final int m1897rem7apg3OU(int i, byte b) {
        return UByte$$ExternalSyntheticBackport0.m1301m(i, m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m1900remxj2QHRw(int i, short s) {
        return UByte$$ExternalSyntheticBackport0.m1301m(i, m1867constructorimpl(s & UShort.MAX_VALUE));
    }

    private static final int m1899remWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m2124uintRemainderJ1ME1BU(i, i2);
    }

    private static final long m1898remVKZWuLQ(int i, long j) {
        return UByte$$ExternalSyntheticBackport0.m1303m(ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax), j);
    }

    private static final int m1875floorDiv7apg3OU(int i, byte b) {
        return UByte$$ExternalSyntheticBackport0.m$1(i, m1867constructorimpl(b & UByte.MAX_VALUE));
    }

    private static final int m1878floorDivxj2QHRw(int i, short s) {
        return UByte$$ExternalSyntheticBackport0.m$1(i, m1867constructorimpl(s & UShort.MAX_VALUE));
    }

    private static final int m1877floorDivWZ4Q5Ns(int i, int i2) {
        return UByte$$ExternalSyntheticBackport0.m$1(i, i2);
    }

    private static final long m1876floorDivVKZWuLQ(int i, long j) {
        return UByte$$ExternalSyntheticBackport0.m$1(ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax), j);
    }

    private static final byte m1886mod7apg3OU(int i, byte b) {
        return UByte.m1790constructorimpl((byte) UByte$$ExternalSyntheticBackport0.m1301m(i, m1867constructorimpl(b & UByte.MAX_VALUE)));
    }

    private static final short m1889modxj2QHRw(int i, short s) {
        return UShort.m2053constructorimpl((short) UByte$$ExternalSyntheticBackport0.m1301m(i, m1867constructorimpl(s & UShort.MAX_VALUE)));
    }

    private static final int m1888modWZ4Q5Ns(int i, int i2) {
        return UByte$$ExternalSyntheticBackport0.m1301m(i, i2);
    }

    private static final long m1887modVKZWuLQ(int i, long j) {
        return UByte$$ExternalSyntheticBackport0.m1303m(ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax), j);
    }

    private static final int m1880incpVg5ArA(int i) {
        return m1867constructorimpl(i + 1);
    }

    private static final int m1868decpVg5ArA(int i) {
        return m1867constructorimpl(i - 1);
    }

    private static final UIntRange m1895rangeToWZ4Q5Ns(int i, int i2) {
        return new UIntRange(i, i2, null);
    }

    private static final UIntRange m1896rangeUntilWZ4Q5Ns(int i, int i2) {
        return URangesKt.m3052untilJ1ME1BU(i, i2);
    }

    private static final int m1901shlpVg5ArA(int i, int i2) {
        return m1867constructorimpl(i << i2);
    }

    private static final int m1902shrpVg5ArA(int i, int i2) {
        return m1867constructorimpl(i >>> i2);
    }

    private static final int m1860andWZ4Q5Ns(int i, int i2) {
        return m1867constructorimpl(i & i2);
    }

    private static final int m1890orWZ4Q5Ns(int i, int i2) {
        return m1867constructorimpl(i | i2);
    }

    private static final int m1918xorWZ4Q5Ns(int i, int i2) {
        return m1867constructorimpl(i ^ i2);
    }

    private static final int m1881invpVg5ArA(int i) {
        return m1867constructorimpl(~i);
    }

    private static final byte m1914toUBytew2LRezQ(int i) {
        return UByte.m1790constructorimpl((byte) i);
    }

    private static final short m1917toUShortMh2AYeg(int i) {
        return UShort.m2053constructorimpl((short) i);
    }

    private static final long m1916toULongsVKNKU(int i) {
        return ULong.m1946constructorimpl(((long) i) & BodyPartID.bodyIdMax);
    }

    private static final float m1909toFloatimpl(int i) {
        return (float) UnsignedKt.uintToDouble(i);
    }

    private static final double m1908toDoubleimpl(int i) {
        return UnsignedKt.uintToDouble(i);
    }

    public static String m1913toStringimpl(int i) {
        return String.valueOf(((long) i) & BodyPartID.bodyIdMax);
    }

    public String toString() {
        return m1913toStringimpl(this.data);
    }
}
