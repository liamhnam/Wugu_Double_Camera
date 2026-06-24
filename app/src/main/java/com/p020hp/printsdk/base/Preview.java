package com.p020hp.printsdk.base;

import android.graphics.Bitmap;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.open.printsdk.options.PrintOptions;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007H&J\u000f\u0010\t\u001a\u0004\u0018\u00010\nH&¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\u0005H&J\b\u0010\r\u001a\u00020\u0003H&J&\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H&¨\u0006\u0015"}, m1293d2 = {"Lcom/hp/printsdk/base/Preview;", "", "getPreviewBitmaps", "", "pageNum", "", "callback", "Lkotlin/Function1;", "Landroid/graphics/Bitmap;", "getPreviewRatio", "", "()Ljava/lang/Double;", "getTotalPreviewPageCount", "stop", "updatePrintOptions", MqttCmdEnum.C2S_PRINTER_ERROR, "Lcom/hp/open/printsdk/HpPrinter;", "options", "Lcom/hp/open/printsdk/options/PrintOptions;", "onUpdateCompleted", "Lkotlin/Function0;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public interface Preview {
    void getPreviewBitmaps(int pageNum, Function1<? super Bitmap, Unit> callback);

    Double getPreviewRatio();

    int getTotalPreviewPageCount();

    void stop();

    void updatePrintOptions(HpPrinter printer, PrintOptions options, Function0<Unit> onUpdateCompleted);
}
