package com.p020hp.printsdk;

import com.p020hp.jipp.model.Sides;
import com.p020hp.printsdk.InterfaceC1754r0;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

public final class C1790y1 extends Writer {

    public final OutputStream f2015a;

    public final C1785x1 f2016b;

    public int f2017c;

    public final ArrayList<Integer> f2018d;

    public final ArrayList<Integer> f2019e;

    public static final class a {

        public final int f2020a;

        public byte[] f2021b;

        public char[] f2022c;

        public a(int i) {
            this.f2020a = i;
        }
    }

    public static final class b {

        public final int f2023a;

        public final int f2024b;

        public final int f2025c;

        public final int f2026d;

        public b(int i, int i2, int i3, int i4) {
            this.f2023a = i;
            this.f2024b = i2;
            this.f2025c = i3;
            this.f2026d = i4;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f2023a == bVar.f2023a && this.f2024b == bVar.f2024b && this.f2025c == bVar.f2025c && this.f2026d == bVar.f2026d;
        }

        public int hashCode() {
            return (((((Integer.hashCode(this.f2023a) * 31) + Integer.hashCode(this.f2024b)) * 31) + Integer.hashCode(this.f2025c)) * 31) + Integer.hashCode(this.f2026d);
        }

        public String toString() {
            return "Swath(objectNumber=" + this.f2023a + ", imageNumber=" + this.f2024b + ", height=" + this.f2025c + ", yOffset=" + this.f2026d + ')';
        }
    }

    public static final class c extends Lambda implements Function1<a, Unit> {
        public c() {
            super(1);
        }

        @Override
        public Unit invoke(a aVar) throws IOException {
            a pdObject = aVar;
            Intrinsics.checkNotNullParameter(pdObject, "$this$pdObject");
            C1790y1.this.write("/Type /Catalog\n");
            C1790y1.this.write("/Pages " + (pdObject.f2020a + 1) + " 0 R\n");
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function1<a, Unit> {
        public d() {
            super(1);
        }

        @Override
        public Unit invoke(a aVar) throws IOException {
            a pdObject = aVar;
            Intrinsics.checkNotNullParameter(pdObject, "$this$pdObject");
            C1790y1.this.write("/Count " + C1790y1.this.f2018d.size() + '\n');
            C1790y1.this.write("/Kids [ ");
            int size = C1790y1.this.f2018d.size();
            for (int i = 0; i < size; i++) {
                C1790y1.this.write(C1790y1.this.f2018d.get(i).intValue() + " 0 R ");
            }
            C1790y1.this.write("]\n");
            C1790y1.this.write("/Type /Pages\n");
            return Unit.INSTANCE;
        }
    }

    public static final class f extends Lambda implements Function1<a, Unit> {

        public final AbstractC1755r1 f2030a;

        public final List<b> f2031b;

        public final AbstractC1760s1 f2032c;

        public final C1790y1 f2033d;

        public f(AbstractC1755r1 abstractC1755r1, List<b> list, AbstractC1760s1 abstractC1760s1, C1790y1 c1790y1) {
            super(1);
            this.f2030a = abstractC1755r1;
            this.f2031b = list;
            this.f2032c = abstractC1760s1;
            this.f2033d = c1790y1;
        }

        @Override
        public Unit invoke(a aVar) throws IOException {
            a pdObject = aVar;
            Intrinsics.checkNotNullParameter(pdObject, "$this$pdObject");
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            charArrayWriter.write((72.0d / ((double) this.f2030a.mo568a())) + " 0 0 " + (72.0d / ((double) this.f2030a.mo568a())) + " 0 0 cm\n");
            for (b bVar : this.f2031b) {
                int i = (this.f2032c.f1758b - bVar.f2026d) - bVar.f2025c;
                charArrayWriter.write("/P <</MCID 0>> BDC q\n");
                charArrayWriter.write(this.f2032c.f1757a + " 0 0 " + bVar.f2025c + " 0 " + i + " cm\n");
                charArrayWriter.write("/Image" + bVar.f2024b + " Do Q\n");
            }
            pdObject.f2022c = charArrayWriter.toCharArray();
            this.f2033d.write("/Length " + charArrayWriter.size() + '\n');
            return Unit.INSTANCE;
        }
    }

    public static final class g extends Lambda implements Function1<a, Unit> {

        public final List<b> f2035b;

        public final AbstractC1760s1 f2036c;

        public final AbstractC1755r1 f2037d;

        public g(List<b> list, AbstractC1760s1 abstractC1760s1, AbstractC1755r1 abstractC1755r1) {
            super(1);
            this.f2035b = list;
            this.f2036c = abstractC1760s1;
            this.f2037d = abstractC1755r1;
        }

        @Override
        public Unit invoke(a aVar) throws IOException {
            a pdObject = aVar;
            Intrinsics.checkNotNullParameter(pdObject, "$this$pdObject");
            C1790y1.this.write("/Type /Page\n");
            C1790y1.this.write("/Parent 2 0 R\n");
            C1790y1.this.write("/Resources <<\n");
            C1790y1.this.write("/XObject <<\n");
            for (b bVar : this.f2035b) {
                C1790y1.this.write("/Image" + bVar.f2024b + ' ' + bVar.f2023a + " 0 R\n");
            }
            C1790y1.this.write(">>\n");
            C1790y1.this.write(">>\n");
            C1790y1.this.write("/MediaBox [ 0 0 " + ((this.f2036c.f1757a * 72) / this.f2037d.mo568a()) + ' ' + ((this.f2036c.f1758b * 72) / this.f2037d.mo568a()) + " ]\n");
            C1790y1.this.write("/Contents [ " + (pdObject.f2020a + 1) + " 0 R ]\n");
            return Unit.INSTANCE;
        }
    }

    public C1790y1(OutputStream outputStream, C1785x1 settings) {
        Intrinsics.checkNotNullParameter(outputStream, "outputStream");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.f2015a = outputStream;
        this.f2016b = settings;
        this.f2018d = new ArrayList<>();
        this.f2019e = new ArrayList<>();
    }

    public final int m687a(int i, Function1<? super a, Unit> function1) throws IOException {
        a aVar = new a(i);
        if (i < this.f2019e.size()) {
            this.f2019e.set(i, Integer.valueOf(this.f2017c));
        } else {
            if (i != this.f2019e.size()) {
                throw new IOException("Cannot create object beyond last position");
            }
            this.f2019e.add(Integer.valueOf(this.f2017c));
        }
        write(aVar.f2020a + " 0 obj\n");
        write("<<\n");
        function1.invoke(aVar);
        write(">>\n");
        if (aVar.f2021b != null) {
            write("stream\n");
            this.f2015a.flush();
            OutputStream outputStream = this.f2015a;
            byte[] bArr = aVar.f2021b;
            Intrinsics.checkNotNull(bArr);
            outputStream.write(bArr);
            int i2 = this.f2017c;
            byte[] bArr2 = aVar.f2021b;
            Intrinsics.checkNotNull(bArr2);
            this.f2017c = i2 + bArr2.length;
            write("\nendstream\n");
        } else if (aVar.f2022c != null) {
            write("stream\n");
            this.f2015a.flush();
            char[] cArr = aVar.f2022c;
            Intrinsics.checkNotNull(cArr);
            write(cArr);
            write("\nendstream\n");
        }
        write("endobj\n");
        return i;
    }

    public final void m688a() throws IOException {
        m687a(1, new c());
        m687a(2, new d());
        int i = this.f2017c;
        write("xref\n");
        write("1 " + (this.f2019e.size() - 1) + '\n');
        Iterator it = CollectionsKt.drop(this.f2019e, 1).iterator();
        while (it.hasNext()) {
            String str = String.format("%010d 00000 n \n", Arrays.copyOf(new Object[]{Integer.valueOf(((Number) it.next()).intValue())}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            write(str);
        }
        write("trailer\n");
        write("<<\n");
        write("/Size " + (this.f2019e.size() - 1) + '\n');
        write("/Root 1 0 R\n");
        write(">>\n");
        write("startxref\n");
        write(new StringBuilder().append(i).append('\n').toString());
        write("%%EOF\n");
    }

    @Override
    public void close() throws IOException {
        this.f2015a.close();
    }

    @Override
    public void flush() throws IOException {
        this.f2015a.flush();
    }

    @Override
    public void write(char[] chars, int i, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(chars, "chars");
        this.f2017c += i2;
        OutputStream outputStream = this.f2015a;
        byte[] bytes = new String(chars).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        outputStream.write(bytes, i, i2);
    }

    public final void m689a(AbstractC1755r1 document) {
        Intrinsics.checkNotNullParameter(document, "document");
        this.f2019e.add(-1);
        this.f2019e.add(-1);
        this.f2019e.add(-1);
        write("%PDF-1.7\n");
        write("%PCLm 1.0\n");
        Iterator<AbstractC1760s1> it = AbstractC1755r1.m630a(document.m631a(new e()), this.f2016b.f1959a, false, 2, null).iterator();
        while (it.hasNext()) {
            m690a(document, it.next());
        }
        m688a();
        this.f2015a.flush();
    }

    public final void m690a(AbstractC1755r1 abstractC1755r1, AbstractC1760s1 abstractC1760s1) throws IOException {
        boolean z;
        int i;
        int iRoundToInt = MathKt.roundToInt(Math.ceil(((double) abstractC1760s1.f1758b) / ((double) this.f2016b.f1960b)));
        IntRange intRangeUntil = RangesKt.until(0, iRoundToInt);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        int i2 = 0;
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            int iNextInt = ((IntIterator) it).nextInt();
            int i3 = iRoundToInt - 1;
            if (iNextInt == i3) {
                i = abstractC1760s1.f1758b - (i3 * this.f2016b.f1960b);
            } else {
                i = this.f2016b.f1960b;
            }
            arrayList.add(new b(this.f2019e.size() + 2 + (iNextInt * 2), iNextInt, i, i2));
            i2 += i;
        }
        this.f2018d.add(Integer.valueOf(m687a(this.f2019e.size(), new g(arrayList, abstractC1760s1, abstractC1755r1))));
        m687a(this.f2019e.size(), new f(abstractC1755r1, arrayList, abstractC1760s1, this));
        int i4 = abstractC1760s1.f1757a;
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        int i5 = UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC / this.f2016b.f1960b;
        int size = arrayList.size() - 1;
        if (i5 <= 0) {
            throw new IllegalArgumentException("Step must be positive, was: " + i5 + '.');
        }
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, size, i5);
        if (progressionLastElement < 0) {
            return;
        }
        int i6 = 0;
        while (true) {
            int i7 = 0;
            for (int i8 = 0; i8 < i5; i8++) {
                int i9 = i6 + i8;
                if (i9 >= arrayList.size()) {
                    break;
                }
                i7 += ((b) arrayList.get(i9)).f2025c;
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            b bVar = (b) arrayList.get(i6);
            int i10 = bVar.f2023a;
            int i11 = bVar.f2026d;
            ?? r2 = (byte[]) objectRef.element;
            int i12 = i7 * i4 * this.f2016b.f1959a.f1553a.f1362a;
            if (!((r2 == 0 || r2.length != i12) ? false : z)) {
                r2 = new byte[i12];
            }
            abstractC1760s1.mo579a(i11, i7, this.f2016b.f1959a.f1553a, r2);
            objectRef.element = r2;
            int i13 = 0;
            while (i13 < i5) {
                int i14 = i6 + i13;
                if (i14 >= arrayList.size()) {
                    break;
                }
                b bVar2 = (b) arrayList.get(i14);
                int i15 = (bVar2.f2026d - ((b) arrayList.get(i6)).f2026d) * i4;
                int i16 = this.f2016b.f1959a.f1553a.f1362a;
                m687a(this.f2019e.size(), new C1795z1(this, i4, bVar2, booleanRef, objectRef, i15 * i16, bVar2.f2025c * i4 * i16));
                m687a(this.f2019e.size(), new C1665a2(this));
                this.f2015a.flush();
                booleanRef.element = false;
                i13++;
                i6 = i6;
                progressionLastElement = progressionLastElement;
            }
            int i17 = i6;
            int i18 = progressionLastElement;
            if (i17 == i18) {
                return;
            }
            i6 = i17 + i5;
            progressionLastElement = i18;
            z = true;
        }
    }

    public static final class e extends Lambda implements Function1<AbstractC1755r1, Iterable<? extends AbstractC1760s1>> {
        public e() {
            super(1);
        }

        @Override
        public final Iterable<AbstractC1760s1> invoke(AbstractC1755r1 doc) {
            AbstractC1760s1 c1770u1;
            Intrinsics.checkNotNullParameter(doc, "doc");
            C1790y1 c1790y1 = C1790y1.this;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(doc, 10));
            int i = 0;
            for (AbstractC1760s1 abstractC1760s1 : doc) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                AbstractC1760s1 abstractC1760s12 = abstractC1760s1;
                c1790y1.getClass();
                if (!InterfaceC1754r0.b.m627a(i)) {
                    if (Intrinsics.areEqual(c1790y1.f2016b.f1959a.f1554b, Sides.twoSidedLongEdge) && Intrinsics.areEqual(c1790y1.f2016b.f1961c, "rotated")) {
                        c1770u1 = new C1780w1(abstractC1760s12, abstractC1760s12.f1757a, abstractC1760s12.f1758b);
                    } else if (Intrinsics.areEqual(c1790y1.f2016b.f1959a.f1554b, Sides.twoSidedLongEdge) && Intrinsics.areEqual(c1790y1.f2016b.f1961c, "flipped")) {
                        c1770u1 = new C1775v1(abstractC1760s12, abstractC1760s12.f1757a, abstractC1760s12.f1758b);
                    } else if (Intrinsics.areEqual(c1790y1.f2016b.f1959a.f1554b, Sides.twoSidedShortEdge) && Intrinsics.areEqual(c1790y1.f2016b.f1961c, "flipped")) {
                        c1770u1 = new C1770u1(abstractC1760s12, abstractC1760s12.f1757a, abstractC1760s12.f1758b);
                    }
                    abstractC1760s12 = c1770u1;
                }
                arrayList.add(abstractC1760s12);
                i = i2;
            }
            return arrayList;
        }
    }
}
