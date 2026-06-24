package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;

@Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00042\u00020\u0001:\u0003\u0004\u0005\u0006J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0007"}, m1293d2 = {"Lkotlin/time/TimeSource;", "", "markNow", "Lkotlin/time/TimeMark;", "Companion", "Monotonic", "WithComparableMarks", "kotlin-stdlib"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public interface TimeSource {

    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(m1292d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m1293d2 = {"Lkotlin/time/TimeSource$WithComparableMarks;", "Lkotlin/time/TimeSource;", "markNow", "Lkotlin/time/ComparableTimeMark;", "kotlin-stdlib"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface WithComparableMarks extends TimeSource {
        @Override
        ComparableTimeMark markNow();
    }

    TimeMark markNow();

    @Metadata(m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\n"}, m1293d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource$WithComparableMarks;", "()V", "markNow", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "markNow-z9LOYto", "()J", "toString", "", "ValueTimeMark", "kotlin-stdlib"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public static final class Monotonic implements WithComparableMarks {
        public static final Monotonic INSTANCE = new Monotonic();

        private Monotonic() {
        }

        @Override
        public ComparableTimeMark markNow() {
            return ValueTimeMark.m3259boximpl(m3258markNowz9LOYto());
        }

        @Override
        public TimeMark markNow() {
            return ValueTimeMark.m3259boximpl(m3258markNowz9LOYto());
        }

        public long m3258markNowz9LOYto() {
            return MonotonicTimeSource.INSTANCE.m3253markNowz9LOYto();
        }

        public String toString() {
            return MonotonicTimeSource.INSTANCE.toString();
        }

        @Metadata(m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u0018\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\rH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0006J\u001a\u0010\u000f\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0018\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0001H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\rH\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u001b\u0010\u001c\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\"\u0010!J\u001b\u0010#\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\rH\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b$\u0010!J\u0010\u0010%\u001a\u00020&HÖ\u0001¢\u0006\u0004\b'\u0010(R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006)"}, m1293d2 = {"Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "Lkotlin/time/ComparableTimeMark;", "reading", "", "Lkotlin/time/ValueTimeMarkReading;", "constructor-impl", "(J)J", "compareTo", "", "other", "compareTo-6eNON_k", "(JJ)I", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "hasNotPassedNow", "hasNotPassedNow-impl", "(J)Z", "hasPassedNow", "hasPassedNow-impl", "hashCode", "hashCode-impl", "(J)I", "minus", "minus-UwyO8pc", "(JLkotlin/time/ComparableTimeMark;)J", "duration", "minus-LRDsOJo", "(JJ)J", "minus-6eNON_k", "plus", "plus-LRDsOJo", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "kotlin-stdlib"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
        @JvmInline
        public static final class ValueTimeMark implements ComparableTimeMark {
            private final long reading;

            public static final ValueTimeMark m3259boximpl(long j) {
                return new ValueTimeMark(j);
            }

            public static long m3262constructorimpl(long j) {
                return j;
            }

            public static boolean m3264equalsimpl(long j, Object obj) {
                return (obj instanceof ValueTimeMark) && j == ((ValueTimeMark) obj).getReading();
            }

            public static final boolean m3265equalsimpl0(long j, long j2) {
                return j == j2;
            }

            public static int m3268hashCodeimpl(long j) {
                return Long.hashCode(j);
            }

            public static String m3273toStringimpl(long j) {
                return "ValueTimeMark(reading=" + j + ')';
            }

            @Override
            public boolean equals(Object obj) {
                return m3264equalsimpl(this.reading, obj);
            }

            @Override
            public int hashCode() {
                return m3268hashCodeimpl(this.reading);
            }

            public String toString() {
                return m3273toStringimpl(this.reading);
            }

            public final long getReading() {
                return this.reading;
            }

            @Override
            public int compareTo(ComparableTimeMark comparableTimeMark) {
                return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
            }

            @Override
            public ComparableTimeMark mo3110minusLRDsOJo(long j) {
                return m3259boximpl(m3274minusLRDsOJo(j));
            }

            @Override
            public TimeMark mo3110minusLRDsOJo(long j) {
                return m3259boximpl(m3274minusLRDsOJo(j));
            }

            @Override
            public ComparableTimeMark mo3112plusLRDsOJo(long j) {
                return m3259boximpl(m3275plusLRDsOJo(j));
            }

            @Override
            public TimeMark mo3112plusLRDsOJo(long j) {
                return m3259boximpl(m3275plusLRDsOJo(j));
            }

            private ValueTimeMark(long j) {
                this.reading = j;
            }

            public static int m3261compareToimpl(long j, ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m3259boximpl(j).compareTo(other);
            }

            public static long m3263elapsedNowUwyO8pc(long j) {
                return MonotonicTimeSource.INSTANCE.m3252elapsedFrom6eNON_k(j);
            }

            @Override
            public long mo3109elapsedNowUwyO8pc() {
                return m3263elapsedNowUwyO8pc(this.reading);
            }

            public static long m3272plusLRDsOJo(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m3250adjustReading6QKq23U(j, j2);
            }

            public long m3275plusLRDsOJo(long j) {
                return m3272plusLRDsOJo(this.reading, j);
            }

            public static long m3270minusLRDsOJo(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m3250adjustReading6QKq23U(j, Duration.m3172unaryMinusUwyO8pc(j2));
            }

            public long m3274minusLRDsOJo(long j) {
                return m3270minusLRDsOJo(this.reading, j);
            }

            public static boolean m3267hasPassedNowimpl(long j) {
                return !Duration.m3153isNegativeimpl(m3263elapsedNowUwyO8pc(j));
            }

            @Override
            public boolean hasPassedNow() {
                return m3267hasPassedNowimpl(this.reading);
            }

            public static boolean m3266hasNotPassedNowimpl(long j) {
                return Duration.m3153isNegativeimpl(m3263elapsedNowUwyO8pc(j));
            }

            @Override
            public boolean hasNotPassedNow() {
                return m3266hasNotPassedNowimpl(this.reading);
            }

            @Override
            public long mo3111minusUwyO8pc(ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m3271minusUwyO8pc(this.reading, other);
            }

            public static long m3271minusUwyO8pc(long j, ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                if (!(other instanceof ValueTimeMark)) {
                    throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + ((Object) m3273toStringimpl(j)) + " and " + other);
                }
                return m3269minus6eNON_k(j, ((ValueTimeMark) other).getReading());
            }

            public static final long m3269minus6eNON_k(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m3251differenceBetweenfRLX17w(j, j2);
            }

            public static final int m3260compareTo6eNON_k(long j, long j2) {
                return Duration.m3119compareToLRDsOJo(m3269minus6eNON_k(j, j2), Duration.INSTANCE.m3222getZEROUwyO8pc());
            }
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lkotlin/time/TimeSource$Companion;", "", "()V", "kotlin-stdlib"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
