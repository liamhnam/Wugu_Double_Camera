package com.p020hp.printsdk;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;

public final class C1719k extends AbstractC1704h {

    public final AbstractC1704h f1383c;

    public final List<IntRange> f1384d;

    public final int f1385e;

    public final Lazy f1386f;

    public static final class a extends Lambda implements Function0<Unit> {
        public a() {
            super(0);
        }

        @Override
        public Unit invoke() {
            C1719k.this.f1383c.close();
            return Unit.INSTANCE;
        }
    }

    public static final class b implements Iterator<AbstractC1760s1>, KMappedMarker {

        public AbstractC1760s1 f1388a;

        public final Iterator<IndexedValue<AbstractC1760s1>> f1389b;

        public final C1719k f1390c;

        public b(Iterator<? extends IndexedValue<? extends AbstractC1760s1>> it, C1719k c1719k) {
            this.f1389b = it;
            this.f1390c = c1719k;
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean hasNext() {
            /*
                r6 = this;
                com.hp.printsdk.s1 r0 = r6.f1388a
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L7
                goto L50
            L7:
                java.util.Iterator<kotlin.collections.IndexedValue<com.hp.printsdk.s1>> r0 = r6.f1389b
                boolean r0 = r0.hasNext()
                if (r0 == 0) goto L4e
                java.util.Iterator<kotlin.collections.IndexedValue<com.hp.printsdk.s1>> r0 = r6.f1389b
                java.lang.Object r0 = r0.next()
                kotlin.collections.IndexedValue r0 = (kotlin.collections.IndexedValue) r0
                com.hp.printsdk.k r3 = r6.f1390c
                java.util.List<kotlin.ranges.IntRange> r3 = r3.f1384d
                boolean r4 = r3 instanceof java.util.Collection
                if (r4 == 0) goto L26
                boolean r4 = r3.isEmpty()
                if (r4 == 0) goto L26
                goto L43
            L26:
                java.util.Iterator r3 = r3.iterator()
            L2a:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L43
                java.lang.Object r4 = r3.next()
                kotlin.ranges.IntRange r4 = (kotlin.ranges.IntRange) r4
                int r5 = r0.getIndex()
                int r5 = r5 + r2
                boolean r4 = r4.contains(r5)
                if (r4 == 0) goto L2a
                r3 = r2
                goto L44
            L43:
                r3 = r1
            L44:
                if (r3 == 0) goto L7
                java.lang.Object r0 = r0.getValue()
                com.hp.printsdk.s1 r0 = (com.p020hp.printsdk.AbstractC1760s1) r0
                r6.f1388a = r0
            L4e:
                com.hp.printsdk.s1 r0 = r6.f1388a
            L50:
                if (r0 == 0) goto L53
                r1 = r2
            L53:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1719k.b.hasNext():boolean");
        }

        @Override
        public AbstractC1760s1 next() {
            AbstractC1760s1 abstractC1760s1 = this.f1388a;
            this.f1388a = null;
            if (abstractC1760s1 != null) {
                return abstractC1760s1;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public static final class c extends Lambda implements Function0<Integer> {
        public c() {
            super(0);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Integer invoke() {
            /*
                r10 = this;
                kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
                com.hp.printsdk.k r1 = com.p020hp.printsdk.C1719k.this
                com.hp.printsdk.h r1 = r1.f1383c
                int r1 = r1.getPageCount()
                r2 = 0
                r0.<init>(r2, r1)
                com.hp.printsdk.k r1 = com.p020hp.printsdk.C1719k.this
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>()
                java.util.Iterator r0 = r0.iterator()
            L19:
                boolean r4 = r0.hasNext()
                if (r4 == 0) goto L65
                java.lang.Object r4 = r0.next()
                r5 = r4
                java.lang.Number r5 = (java.lang.Number) r5
                int r5 = r5.intValue()
                java.util.List<kotlin.ranges.IntRange> r6 = r1.f1384d
                boolean r6 = r6.isEmpty()
                r7 = 1
                if (r6 != 0) goto L5f
                java.util.List<kotlin.ranges.IntRange> r6 = r1.f1384d
                boolean r8 = r6 instanceof java.util.Collection
                if (r8 == 0) goto L40
                boolean r8 = r6.isEmpty()
                if (r8 == 0) goto L40
                goto L5a
            L40:
                java.util.Iterator r6 = r6.iterator()
            L44:
                boolean r8 = r6.hasNext()
                if (r8 == 0) goto L5a
                java.lang.Object r8 = r6.next()
                kotlin.ranges.IntRange r8 = (kotlin.ranges.IntRange) r8
                int r9 = r5 + 1
                boolean r8 = r8.contains(r9)
                if (r8 == 0) goto L44
                r5 = r7
                goto L5b
            L5a:
                r5 = r2
            L5b:
                if (r5 == 0) goto L5e
                goto L5f
            L5e:
                r7 = r2
            L5f:
                if (r7 == 0) goto L19
                r3.add(r4)
                goto L19
            L65:
                int r0 = r3.size()
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1719k.c.invoke():java.lang.Object");
        }
    }

    public C1719k(AbstractC1704h origin, List<IntRange> values) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(values, "values");
        this.f1383c = origin;
        this.f1384d = values;
        this.f1385e = origin.mo568a();
        m527a(new a());
        this.f1386f = LazyKt.lazy(new c());
    }

    @Override
    public int mo568a() {
        return this.f1385e;
    }

    @Override
    public int getPageCount() {
        return ((Number) this.f1386f.getValue()).intValue();
    }

    @Override
    public Iterator<AbstractC1760s1> iterator() {
        return new b(CollectionsKt.withIndex(this.f1383c.iterator()), this);
    }
}
