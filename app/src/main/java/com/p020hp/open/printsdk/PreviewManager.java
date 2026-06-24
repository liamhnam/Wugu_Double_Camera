package com.p020hp.open.printsdk;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import com.p020hp.jipp.model.TimeoutPredicate;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.open.printsdk.options.PrintOptions;
import com.p020hp.printsdk.C1673b4;
import com.p020hp.printsdk.C1673b4.b;
import com.p020hp.printsdk.C1673b4.c;
import com.p020hp.printsdk.base.Preview;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(m1292d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100\u0014H\u0016J\u000f\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J&\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100!H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m1293d2 = {"Lcom/hp/open/printsdk/PreviewManager;", "Lcom/hp/printsdk/base/Preview;", TimeoutPredicate.activity, "Landroidx/appcompat/app/AppCompatActivity;", "file", "Lcom/hp/open/printsdk/HpPrintFile;", "(Landroidx/appcompat/app/AppCompatActivity;Lcom/hp/open/printsdk/HpPrintFile;)V", "getActivity", "()Landroidx/appcompat/app/AppCompatActivity;", "getFile", "()Lcom/hp/open/printsdk/HpPrintFile;", "log", "Lcom/hp/mobile/common/utils/Logger;", "previewImpl", "Lcom/hp/printsdk/preview/PreviewImpl;", "getPreviewBitmaps", "", "pageNum", "", "callback", "Lkotlin/Function1;", "Landroid/graphics/Bitmap;", "getPreviewRatio", "", "()Ljava/lang/Double;", "getTotalPreviewPageCount", "stop", "updatePrintOptions", MqttCmdEnum.C2S_PRINTER_ERROR, "Lcom/hp/open/printsdk/HpPrinter;", "options", "Lcom/hp/open/printsdk/options/PrintOptions;", "onUpdateCompleted", "Lkotlin/Function0;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class PreviewManager implements Preview {

    public final AppCompatActivity f791a;

    public final HpPrintFile f792b;

    public final Logger f793c;

    public final C1673b4 f794d;

    public PreviewManager(AppCompatActivity activity, HpPrintFile file) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(file, "file");
        this.f791a = activity;
        this.f792b = file;
        this.f793c = LoggerKt.logger(this);
        this.f794d = new C1673b4(activity, file);
    }

    public final AppCompatActivity getF791a() {
        return this.f791a;
    }

    public final HpPrintFile getF792b() {
        return this.f792b;
    }

    @Override
    public Double getPreviewRatio() {
        Double previewRatio = this.f794d.getPreviewRatio();
        this.f793c.invoke("Call getPreviewRatio() with return: " + previewRatio.doubleValue());
        return previewRatio;
    }

    @Override
    public int getTotalPreviewPageCount() {
        int f780b = this.f794d.f927a.getF780b();
        this.f793c.invoke("Call getTotalPreviewPageCount() with return: " + f780b);
        return f780b;
    }

    @Override
    public void stop() {
        this.f793c.invoke("Call stop()");
        C1673b4 c1673b4 = this.f794d;
        c1673b4.getClass();
        BuildersKt__Builders_commonKt.launch$default(c1673b4, Dispatchers.getIO(), null, c1673b4.new c(null), 2, null);
    }

    @Override
    public void getPreviewBitmaps(int pageNum, Function1<? super Bitmap, Unit> callback) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f793c.invoke("Call getPreviewBitmaps() with pageNum: " + pageNum);
        try {
            C1673b4 c1673b4 = this.f794d;
            c1673b4.getClass();
            Intrinsics.checkNotNullParameter(callback, "callback");
            BuildersKt__Builders_commonKt.launch$default(c1673b4, Dispatchers.getIO(), null, c1673b4.new b(pageNum, callback, null), 2, null);
            objM1772constructorimpl = Result.m1772constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
        if (thM1775exceptionOrNullimpl != null) {
            this.f793c.m448e("Call getPreviewBitmaps() failed.", thM1775exceptionOrNullimpl);
        }
    }

    @Override
    public void updatePrintOptions(HpPrinter printer, PrintOptions options, Function0<Unit> onUpdateCompleted) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(onUpdateCompleted, "onUpdateCompleted");
        this.f793c.invoke("Call updatePrintOptions() with printer: " + printer + ", options: " + options);
        try {
            C1673b4 c1673b4 = this.f794d;
            c1673b4.getClass();
            Intrinsics.checkNotNullParameter(printer, "printer");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(onUpdateCompleted, "onUpdateCompleted");
            c1673b4.f934h = options;
            BuildersKt__Builders_commonKt.launch$default(c1673b4, Dispatchers.getIO(), null, new C1673b4.f(printer, c1673b4, options, onUpdateCompleted, null), 2, null);
            objM1772constructorimpl = Result.m1772constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
        if (thM1775exceptionOrNullimpl != null) {
            this.f793c.m448e("Call updatePrintOptions() failed.", thM1775exceptionOrNullimpl);
        }
    }
}
