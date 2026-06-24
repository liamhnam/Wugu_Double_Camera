package kotlin.internal;

import com.p020hp.jipp.model.Media;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.ULong;

@Metadata(m1292d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m1293d2 = {"differenceModulo", "Lkotlin/UInt;", "a", Media.f726b, Media.f727c, "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, m1294k = 2, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class UProgressionUtilKt {
    private static final int m2990differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int iM1301m = UByte$$ExternalSyntheticBackport0.m1301m(i, i3);
        int iM1301m2 = UByte$$ExternalSyntheticBackport0.m1301m(i2, i3);
        int iCompare = Integer.compare(iM1301m ^ Integer.MIN_VALUE, iM1301m2 ^ Integer.MIN_VALUE);
        int iM1867constructorimpl = UInt.m1867constructorimpl(iM1301m - iM1301m2);
        return iCompare >= 0 ? iM1867constructorimpl : UInt.m1867constructorimpl(iM1867constructorimpl + i3);
    }

    private static final long m2991differenceModulosambcqE(long j, long j2, long j3) {
        long jM1303m = UByte$$ExternalSyntheticBackport0.m1303m(j, j3);
        long jM1303m2 = UByte$$ExternalSyntheticBackport0.m1303m(j2, j3);
        int iCompare = Long.compare(jM1303m ^ Long.MIN_VALUE, jM1303m2 ^ Long.MIN_VALUE);
        long jM1946constructorimpl = ULong.m1946constructorimpl(jM1303m - jM1303m2);
        return iCompare >= 0 ? jM1946constructorimpl : ULong.m1946constructorimpl(jM1946constructorimpl + j3);
    }

    public static final int m2993getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) >= 0 ? i2 : UInt.m1867constructorimpl(i2 - m2990differenceModuloWZ9TVnA(i2, i, UInt.m1867constructorimpl(i3)));
        }
        if (i3 < 0) {
            return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) <= 0 ? i2 : UInt.m1867constructorimpl(i2 + m2990differenceModuloWZ9TVnA(i, i2, UInt.m1867constructorimpl(-i3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    public static final long m2992getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        if (j3 > 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) >= 0 ? j2 : ULong.m1946constructorimpl(j2 - m2991differenceModulosambcqE(j2, j, ULong.m1946constructorimpl(j3)));
        }
        if (j3 < 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) <= 0 ? j2 : ULong.m1946constructorimpl(j2 + m2991differenceModulosambcqE(j, j2, ULong.m1946constructorimpl(-j3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
