package com.p020hp.printsdk;

import com.p020hp.jipp.model.Sides;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;

public abstract class AbstractC1755r1 implements Iterable<AbstractC1760s1>, KMappedMarker {

    public static final a f1671a = new a();

    public static final class a {

        public static final class C3370a extends Lambda implements Function1<AbstractC1755r1, Iterable<? extends AbstractC1760s1>> {

            public static final C3370a f1672a = new C3370a();

            public C3370a() {
                super(1);
            }

            @Override
            public final Iterable<AbstractC1760s1> invoke(AbstractC1755r1 it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AbstractC1760s1 abstractC1760s1 = (AbstractC1760s1) CollectionsKt.last(it);
                return CollectionsKt.plus((Iterable) it, (Iterable) CollectionsKt.listOf(new C1765t1(abstractC1760s1.f1757a, abstractC1760s1.f1758b)));
            }
        }

        public final AbstractC1755r1 m632a(AbstractC1755r1 abstractC1755r1, boolean z) {
            if (z) {
                return CollectionsKt.count(abstractC1755r1) % 2 == 1 ? abstractC1755r1.m631a(C3370a.f1672a) : abstractC1755r1;
            }
            return abstractC1755r1;
        }
    }

    public static final class b extends AbstractC1755r1 {

        public final int f1673b;

        public final Iterable<AbstractC1760s1> f1674c;

        public b(AbstractC1755r1 abstractC1755r1, Function1<? super AbstractC1755r1, ? extends Iterable<? extends AbstractC1760s1>> function1) {
            this.f1673b = abstractC1755r1.mo568a();
            this.f1674c = (Iterable) function1.invoke(abstractC1755r1);
        }

        @Override
        public int mo568a() {
            return this.f1673b;
        }

        @Override
        public Iterator<AbstractC1760s1> iterator() {
            return this.f1674c.iterator();
        }
    }

    public static AbstractC1755r1 m630a(AbstractC1755r1 abstractC1755r1, C1741o1 settings, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        Intrinsics.checkNotNullParameter(settings, "settings");
        if (!Intrinsics.areEqual(settings.f1554b, Sides.oneSided)) {
            abstractC1755r1 = f1671a.m632a(abstractC1755r1, z);
        }
        if (settings.f1557e) {
            abstractC1755r1 = abstractC1755r1.m631a(C1751q1.f1623a);
        }
        int i2 = settings.f1558f;
        return i2 <= 1 ? abstractC1755r1 : abstractC1755r1.m631a(new C1746p1(i2, abstractC1755r1));
    }

    public abstract int mo568a();

    public final AbstractC1755r1 m631a(Function1<? super AbstractC1755r1, ? extends Iterable<? extends AbstractC1760s1>> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        return new b(this, transform);
    }

    @Override
    public Iterator<AbstractC1760s1> iterator() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
