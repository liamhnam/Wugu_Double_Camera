package com.p020hp.printsdk;

import android.graphics.Bitmap;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.nio.ByteBuffer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;

public final class C1793z implements AutoCloseable {

    public static final EnumC1716j1 f2043i = EnumC1716j1.Rgba;

    public final PdfiumCore f2044a;

    public final PdfDocument f2045b;

    public final int f2046c;

    public final int f2047d;

    public final int f2048e;

    public Bitmap f2049f;

    public ByteBuffer f2050g;

    public final Logger f2051h;

    public static final class a extends Lambda implements Function0<String> {

        public static final a f2052a = new a();

        public a() {
            super(0);
        }

        @Override
        public String invoke() {
            return "RENDER WITH PDFIUM";
        }
    }

    public C1793z(PdfiumCore pdfiumCore, PdfDocument doc, int i) {
        Intrinsics.checkNotNullParameter(pdfiumCore, "pdfiumCore");
        Intrinsics.checkNotNullParameter(doc, "doc");
        this.f2044a = pdfiumCore;
        this.f2045b = doc;
        this.f2046c = i;
        this.f2047d = pdfiumCore.getPageWidthPoint(doc, i);
        this.f2048e = pdfiumCore.getPageHeightPoint(doc, i);
        this.f2051h = LoggerKt.logger(this);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m693a(java.io.OutputStream r10, com.p020hp.printsdk.C1773v r11, com.p020hp.printsdk.C1694f0 r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1793z.m693a(java.io.OutputStream, com.hp.printsdk.v, com.hp.printsdk.f0):void");
    }

    @Override
    public void close() {
        this.f2044a.closePage(this.f2045b, this.f2046c);
    }

    public final void m692a(Bitmap bitmap, C1773v c1773v, C1694f0 c1694f0) throws Throwable {
        bitmap.eraseColor(-1);
        int i = -c1773v.f1876b;
        int i2 = -c1773v.f1877c;
        int iRoundToInt = MathKt.roundToInt(((double) this.f2047d) * c1773v.f1880f);
        int iRoundToInt2 = MathKt.roundToInt(((double) this.f2048e) * c1773v.f1880f);
        this.f2051h.invoke("area: " + c1773v + ", bitmap.size: " + bitmap.getWidth() + " x " + bitmap.getHeight() + ", pagePoint: " + this.f2047d + " x " + this.f2048e + ", startX: " + i + ", startY: " + i2 + ", drawSizeX: " + iRoundToInt + ", drawSizeY: " + iRoundToInt2);
        this.f2051h.m446d(a.f2052a);
        this.f2044a.renderPageBitmap(this.f2045b, bitmap, this.f2046c, i, i2, iRoundToInt, iRoundToInt2, c1694f0.f1199c);
    }
}
