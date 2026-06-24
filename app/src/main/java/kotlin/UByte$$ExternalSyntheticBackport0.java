package kotlin;

import org.bouncycastle.asn1.cmc.BodyPartID;

public final class UByte$$ExternalSyntheticBackport0 {
    public static int m1301m(int i, int i2) {
        return (int) ((((long) i) & BodyPartID.bodyIdMax) % (((long) i2) & BodyPartID.bodyIdMax));
    }

    public static long m1303m(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if ((j3 ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE)) {
            j2 = 0;
        }
        return j3 - j2;
    }

    public static int m$1(int i, int i2) {
        return (int) ((((long) i) & BodyPartID.bodyIdMax) / (((long) i2) & BodyPartID.bodyIdMax));
    }

    public static long m$1(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0L : 1L;
        }
        if (j >= 0) {
            return j / j2;
        }
        long j3 = ((j >>> 1) / j2) << 1;
        return j3 + ((long) (((j - (j3 * j2)) ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0 : 1));
    }
}
