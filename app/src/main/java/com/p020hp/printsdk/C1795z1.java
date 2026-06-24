package com.p020hp.printsdk;

import com.p020hp.printsdk.C1790y1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.p037io.CloseableKt;

public final class C1795z1 extends Lambda implements Function1<C1790y1.a, Unit> {

    public final C1790y1 f2055a;

    public final int f2056b;

    public final C1790y1.b f2057c;

    public final Ref.BooleanRef f2058d;

    public final Ref.ObjectRef<byte[]> f2059e;

    public final int f2060f;

    public final int f2061g;

    public C1795z1(C1790y1 c1790y1, int i, C1790y1.b bVar, Ref.BooleanRef booleanRef, Ref.ObjectRef<byte[]> objectRef, int i2, int i3) {
        super(1);
        this.f2055a = c1790y1;
        this.f2056b = i;
        this.f2057c = bVar;
        this.f2058d = booleanRef;
        this.f2059e = objectRef;
        this.f2060f = i2;
        this.f2061g = i3;
    }

    @Override
    public Unit invoke(C1790y1.a aVar) throws IOException {
        C1790y1 c1790y1;
        String str;
        C1790y1.a pdObject = aVar;
        Intrinsics.checkNotNullParameter(pdObject, "$this$pdObject");
        Ref.ObjectRef<byte[]> objectRef = this.f2059e;
        int i = this.f2060f;
        int i2 = this.f2061g;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater(1), 16384);
        try {
            deflaterOutputStream.write(objectRef.element, i, i2);
            CloseableKt.closeFinally(deflaterOutputStream, null);
            pdObject.f2021b = byteArrayOutputStream.toByteArray();
            this.f2055a.write("/Width " + this.f2056b + '\n');
            int iOrdinal = this.f2055a.f2016b.f1959a.f1553a.ordinal();
            if (iOrdinal == 1) {
                this.f2055a.write("/ColorSpace /DeviceRGB\n");
            } else {
                if (iOrdinal != 2) {
                    throw new IOException(this.f2055a.f2016b.f1959a.f1553a + " not supported");
                }
                this.f2055a.write("/ColorSpace /DeviceGray\n");
            }
            this.f2055a.write("/Height " + this.f2057c.f2025c + '\n');
            this.f2055a.write("/Filter /FlateDecode\n");
            this.f2055a.write("/Subtype /Image\n");
            C1790y1 c1790y12 = this.f2055a;
            StringBuilder sb = new StringBuilder("/Length ");
            byte[] bArr = pdObject.f2021b;
            Intrinsics.checkNotNull(bArr);
            c1790y12.write(sb.append(bArr.length).append('\n').toString());
            this.f2055a.write("/Type /XObject\n");
            this.f2055a.write("/BitsPerComponent 8\n");
            byte[] bArr2 = pdObject.f2021b;
            Intrinsics.checkNotNull(bArr2);
            if (!(bArr2.length == 0) || this.f2058d.element) {
                c1790y1 = this.f2055a;
                str = "/Name /ColorStrip\n";
            } else {
                c1790y1 = this.f2055a;
                str = "/Name /WhiteStrip\n";
            }
            c1790y1.write(str);
            return Unit.INSTANCE;
        } finally {
        }
    }
}
