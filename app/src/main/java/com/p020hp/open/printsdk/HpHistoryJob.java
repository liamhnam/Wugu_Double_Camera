package com.p020hp.open.printsdk;

import kotlin.Metadata;

@Metadata(m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0015\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0012\u0010\t\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0012\u0010\u000b\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0012\u0010\r\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006R\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006R\u0012\u0010\u0015\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u0012\u0010\u0017\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0006R\u0012\u0010\u0019\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0006R\u0012\u0010\u001b\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0006R\u0012\u0010\u001d\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0006R\u0012\u0010\u001f\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0006R\u0012\u0010!\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0006R\u0012\u0010#\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0006¨\u0006%"}, m1293d2 = {"Lcom/hp/open/printsdk/HpHistoryJob;", "Lcom/hp/open/printsdk/HpPrintJob;", "()V", "jobPath", "", "getJobPath", "()Ljava/lang/String;", "jobSize", "getJobSize", "jobType", "getJobType", "optionsColor", "getOptionsColor", "optionsCopies", "getOptionsCopies", "optionsDpi", "", "getOptionsDpi", "()I", "optionsInputTrayType", "getOptionsInputTrayType", "optionsMediaMargin", "getOptionsMediaMargin", "optionsMediaSize", "getOptionsMediaSize", "optionsOrientation", "getOptionsOrientation", "optionsPageRanges", "getOptionsPageRanges", "optionsPaperType", "getOptionsPaperType", "optionsQuality", "getOptionsQuality", "optionsScale", "getOptionsScale", "optionsSides", "getOptionsSides", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class HpHistoryJob extends HpPrintJob {
    public abstract String getJobPath();

    public abstract String getJobSize();

    public abstract String getJobType();

    public abstract String getOptionsColor();

    public abstract String getOptionsCopies();

    public abstract int getOptionsDpi();

    public abstract String getOptionsInputTrayType();

    public abstract int getOptionsMediaMargin();

    public abstract String getOptionsMediaSize();

    public abstract String getOptionsOrientation();

    public abstract String getOptionsPageRanges();

    public abstract String getOptionsPaperType();

    public abstract String getOptionsQuality();

    public abstract String getOptionsScale();

    public abstract String getOptionsSides();
}
