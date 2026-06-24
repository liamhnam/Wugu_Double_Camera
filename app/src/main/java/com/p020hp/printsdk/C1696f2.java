package com.p020hp.printsdk;

import com.p020hp.printsdk.C1671b2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;

public final class C1696f2 extends DataOutputStream {

    public static final byte[] f1200c;

    public final C1683d2 f1201a;

    public final Function2<AbstractC1760s1, C1677c2, C1677c2> f1202b;

    public static final class a extends Lambda implements Function1<AbstractC1755r1, Iterable<? extends AbstractC1760s1>> {

        public final AbstractC1755r1 f1204b;

        public a(AbstractC1755r1 abstractC1755r1) {
            super(1);
            this.f1204b = abstractC1755r1;
        }

        @Override
        public final Iterable<AbstractC1760s1> invoke(AbstractC1755r1 it) {
            AbstractC1760s1 c1775v1;
            Intrinsics.checkNotNullParameter(it, "it");
            C1696f2 c1696f2 = C1696f2.this;
            AbstractC1755r1 abstractC1755r1 = this.f1204b;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(it, 10));
            int i = 0;
            for (AbstractC1760s1 abstractC1760s1 : it) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                AbstractC1760s1 abstractC1760s12 = abstractC1760s1;
                C1677c2 c1677c2M487a = c1696f2.f1201a.m487a(abstractC1755r1, abstractC1760s12, i);
                Pair pairM1300to = TuplesKt.m1300to(Integer.valueOf(c1677c2M487a.f1029z), Integer.valueOf(c1677c2M487a.f1028y));
                if (Intrinsics.areEqual(pairM1300to, TuplesKt.m1300to(-1, -1))) {
                    c1775v1 = new C1780w1(abstractC1760s12, abstractC1760s12.f1757a, abstractC1760s12.f1758b);
                } else if (Intrinsics.areEqual(pairM1300to, TuplesKt.m1300to(1, -1))) {
                    c1775v1 = new C1770u1(abstractC1760s12, abstractC1760s12.f1757a, abstractC1760s12.f1758b);
                } else if (Intrinsics.areEqual(pairM1300to, TuplesKt.m1300to(-1, 1))) {
                    c1775v1 = new C1775v1(abstractC1760s12, abstractC1760s12.f1757a, abstractC1760s12.f1758b);
                } else {
                    arrayList.add(abstractC1760s12);
                    i = i2;
                }
                abstractC1760s12 = c1775v1;
                arrayList.add(abstractC1760s12);
                i = i2;
            }
            return arrayList;
        }
    }

    static {
        byte[] bytes = "RaS2".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        f1200c = bytes;
    }

    public C1696f2(OutputStream outputStream, C1683d2 settings, Function2<? super AbstractC1760s1, ? super C1677c2, C1677c2> headerCustomizer) {
        super(outputStream);
        Intrinsics.checkNotNullParameter(outputStream, "outputStream");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(headerCustomizer, "headerCustomizer");
        this.f1201a = settings;
        this.f1202b = headerCustomizer;
    }

    public C1696f2(OutputStream outputStream, C1683d2 c1683d2, Function2 function2, int i) {
        this(outputStream, (i & 2) != 0 ? new C1683d2(null, null, null, 7) : c1683d2, (i & 4) != 0 ? C1690e2.f1190a : null);
    }

    public final void m512a(AbstractC1755r1 abstractC1755r1) {
        Object[] objArr;
        AbstractC1755r1 doc = abstractC1755r1;
        Intrinsics.checkNotNullParameter(doc, "doc");
        write(f1200c);
        boolean z = false;
        byte[] bArr = null;
        int i = 0;
        for (AbstractC1760s1 abstractC1760s1 : AbstractC1755r1.m630a(doc.m631a(new a(doc)), this.f1201a.f1160a, false, 2, null)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AbstractC1760s1 abstractC1760s12 = abstractC1760s1;
            C1677c2 c1677c2Invoke = this.f1202b.invoke(abstractC1760s12, this.f1201a.m487a(doc, abstractC1760s12, i));
            c1677c2Invoke.getClass();
            Intrinsics.checkNotNullParameter(this, "output");
            byte[] bytes = "PwgRaster".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            int i3 = 64;
            write(bytes, z ? 1 : 0, Math.min(64, bytes.length));
            write(new byte[64 - bytes.length]);
            byte[] bytes2 = c1677c2Invoke.f1004a.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            write(bytes2, z ? 1 : 0, Math.min(64, bytes2.length));
            write(new byte[64 - bytes2.length]);
            byte[] bytes3 = c1677c2Invoke.f1005b.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes3, "this as java.lang.String).getBytes(charset)");
            write(bytes3, z ? 1 : 0, Math.min(64, bytes3.length));
            write(new byte[64 - bytes3.length]);
            byte[] bytes4 = c1677c2Invoke.f1006c.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes4, "this as java.lang.String).getBytes(charset)");
            write(bytes4, z ? 1 : 0, Math.min(64, bytes4.length));
            write(new byte[64 - bytes4.length]);
            write(new byte[12]);
            writeInt(c1677c2Invoke.f1007d.f1131a);
            writeInt(c1677c2Invoke.f1008e ? 1 : 0);
            writeInt(c1677c2Invoke.f1009f);
            writeInt(c1677c2Invoke.f1010g);
            write(new byte[16]);
            writeInt(c1677c2Invoke.f1011h ? 1 : 0);
            writeInt(c1677c2Invoke.f1012i.f1131a);
            writeInt(c1677c2Invoke.f1013j.f1060a);
            write(new byte[12]);
            writeInt(c1677c2Invoke.f1014k.f1112a);
            writeInt(c1677c2Invoke.f1015l);
            write(new byte[8]);
            writeInt(c1677c2Invoke.f1016m);
            writeInt(c1677c2Invoke.f1017n.f1118a);
            write(new byte[4]);
            writeInt(c1677c2Invoke.f1018o);
            writeInt(c1677c2Invoke.f1019p);
            write(new byte[8]);
            writeInt(c1677c2Invoke.f1020q ? 1 : 0);
            writeInt(c1677c2Invoke.f1021r);
            writeInt(c1677c2Invoke.f1022s);
            write(new byte[4]);
            writeInt(c1677c2Invoke.f1023t);
            writeInt(c1677c2Invoke.f1024u);
            writeInt(c1677c2Invoke.f1001K);
            writeInt(c1677c2Invoke.f1025v.f1032a);
            writeInt(c1677c2Invoke.f1026w.f1056a);
            write(new byte[16]);
            writeInt(c1677c2Invoke.f1002L);
            write(new byte[28]);
            writeInt(c1677c2Invoke.f1027x);
            writeInt(c1677c2Invoke.f1028y);
            writeInt(c1677c2Invoke.f1029z);
            writeInt(c1677c2Invoke.f991A);
            writeInt(c1677c2Invoke.f992B);
            writeInt(c1677c2Invoke.f993C);
            writeInt(c1677c2Invoke.f994D);
            writeInt(c1677c2Invoke.f995E);
            writeInt(c1677c2Invoke.f996F.f1124a);
            write(new byte[20]);
            writeInt(c1677c2Invoke.f997G);
            writeInt(c1677c2Invoke.f998H.length);
            byte[] bArr2 = c1677c2Invoke.f998H;
            write(bArr2, z ? 1 : 0, bArr2.length);
            write(new byte[1088 - c1677c2Invoke.f998H.length]);
            write(new byte[64]);
            byte[] bytes5 = c1677c2Invoke.f999I.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes5, "this as java.lang.String).getBytes(charset)");
            write(bytes5, z ? 1 : 0, Math.min(64, bytes5.length));
            write(new byte[64 - bytes5.length]);
            byte[] bytes6 = c1677c2Invoke.f1000J.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes6, "this as java.lang.String).getBytes(charset)");
            write(bytes6, z ? 1 : 0, Math.min(64, bytes6.length));
            write(new byte[64 - bytes6.length]);
            int i4 = z ? 1 : 0;
            byte[] bArr3 = bArr;
            while (true) {
                int i5 = abstractC1760s12.f1758b;
                if (i4 < i5) {
                    int iMin = Math.min(i3, i5 - i4);
                    EnumC1716j1 colorSpace = this.f1201a.f1160a.f1553a;
                    Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
                    int i6 = abstractC1760s12.f1757a * colorSpace.f1362a * iMin;
                    if (((bArr3 == null || bArr3.length != i6) ? z ? 1 : 0 : 1) == 0) {
                        bArr3 = new byte[i6];
                    }
                    abstractC1760s12.mo579a(i4, iMin, this.f1201a.f1160a.f1553a, bArr3);
                    ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
                    C1671b2 c1671b2 = c1677c2Invoke.f1003M;
                    ByteArrayInputStream inputPixels = new ByteArrayInputStream(bArr3);
                    c1671b2.getClass();
                    Intrinsics.checkNotNullParameter(inputPixels, "inputPixels");
                    Intrinsics.checkNotNullParameter(outputBytes, "outputBytes");
                    C1671b2.a aVar = new C1671b2.a(inputPixels, outputBytes, c1671b2.f910a, c1671b2.f911b);
                    while (true) {
                        aVar.f916e = z;
                        if (aVar.f918g) {
                            byte[] bArr4 = aVar.f917f;
                            aVar.f917f = aVar.f919h;
                            aVar.f919h = bArr4;
                            aVar.f918g = z;
                            aVar.f916e = true;
                        }
                        if (aVar.f916e || aVar.m473a(aVar.f917f)) {
                            aVar.f920i = 1;
                            while (true) {
                                if (aVar.f920i > 256) {
                                    break;
                                }
                                if (!aVar.m473a(aVar.f919h)) {
                                    aVar.f918g = z;
                                    break;
                                } else {
                                    if (!Arrays.equals(aVar.f917f, aVar.f919h)) {
                                        aVar.f918g = true;
                                        break;
                                    }
                                    aVar.f920i++;
                                }
                            }
                            objArr = 1;
                        } else {
                            objArr = z ? 1 : 0;
                        }
                        if (objArr != 0) {
                            aVar.f913b.write(aVar.f920i - 1);
                            aVar.f921j = z ? 1 : 0;
                            while (true) {
                                int i7 = aVar.f921j;
                                byte[] bArr5 = aVar.f917f;
                                if (i7 < bArr5.length) {
                                    int i8 = aVar.f914c;
                                    int i9 = i7 + i8;
                                    if (i9 == bArr5.length) {
                                        aVar.f922k = 1;
                                        aVar.m472a();
                                    } else {
                                        if (aVar.m474a(bArr5, i7, i8, bArr5, i9)) {
                                            aVar.f922k = 2;
                                            int i10 = aVar.f921j + (aVar.f914c * 2);
                                            while (aVar.f922k < 128) {
                                                byte[] bArr6 = aVar.f917f;
                                                if (i10 >= bArr6.length || !aVar.m474a(bArr6, aVar.f921j, aVar.f914c, bArr6, i10)) {
                                                    break;
                                                }
                                                aVar.f922k++;
                                                i10 += aVar.f914c;
                                            }
                                        } else {
                                            aVar.f922k = 2;
                                            int i11 = aVar.f921j;
                                            int i12 = aVar.f914c * 2;
                                            while (true) {
                                                i11 += i12;
                                                byte[] bArr7 = aVar.f917f;
                                                if (i11 >= bArr7.length || aVar.f922k >= 128) {
                                                    break;
                                                }
                                                int i13 = aVar.f914c;
                                                if (aVar.m474a(bArr7, i11 - i13, i13, bArr7, i11)) {
                                                    aVar.f922k--;
                                                    break;
                                                } else {
                                                    aVar.f922k++;
                                                    i12 = aVar.f914c;
                                                }
                                            }
                                            int i14 = aVar.f922k;
                                            if (i14 != 1) {
                                                aVar.f913b.write(257 - i14);
                                                aVar.f913b.write(aVar.f917f, aVar.f921j, aVar.f914c * aVar.f922k);
                                                aVar.f921j += aVar.f922k * aVar.f914c;
                                            }
                                        }
                                        aVar.m472a();
                                    }
                                    z = false;
                                }
                            }
                        }
                    }
                    write(outputBytes.toByteArray());
                    outputBytes.size();
                    i4 += iMin;
                    z = false;
                    bArr = null;
                    i3 = 64;
                }
            }
            doc = abstractC1755r1;
            i = i2;
        }
    }
}
