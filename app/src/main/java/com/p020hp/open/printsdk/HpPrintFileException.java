package com.p020hp.open.printsdk;

import com.p020hp.printsdk.exception.BaseException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/open/printsdk/HpPrintFileException;", "Lcom/hp/printsdk/exception/BaseException;", "errorCode", "", "message", "", "cause", "", "(ILjava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getErrorCode", "()I", "getMessage", "()Ljava/lang/String;", "Companion", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class HpPrintFileException extends BaseException {

    public static final Companion INSTANCE = new Companion(null);

    public static final int f781d = 201;

    public static final int f782e = 202;

    public static final int f783f = 203;

    public final int f784a;

    public final String f785b;

    public final Throwable f786c;

    @Metadata(m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u0007¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/open/printsdk/HpPrintFileException$Companion;", "", "()V", "Can_Not_Open_File", "", "getCan_Not_Open_File$annotations", "getCan_Not_Open_File", "()I", "Mime_Type_Not_Found", "getMime_Type_Not_Found$annotations", "getMime_Type_Not_Found", "Mime_Type_Not_Support", "getMime_Type_Not_Support$annotations", "getMime_Type_Not_Support", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getCan_Not_Open_File$annotations() {
        }

        @JvmStatic
        public static void getMime_Type_Not_Found$annotations() {
        }

        @JvmStatic
        public static void getMime_Type_Not_Support$annotations() {
        }

        public final int getCan_Not_Open_File() {
            return HpPrintFileException.f783f;
        }

        public final int getMime_Type_Not_Found() {
            return HpPrintFileException.f782e;
        }

        public final int getMime_Type_Not_Support() {
            return HpPrintFileException.f781d;
        }
    }

    public HpPrintFileException(int i, String message, Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.f784a = i;
        this.f785b = message;
        this.f786c = th;
    }

    public HpPrintFileException(int i, String str, Throwable th, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i2 & 4) != 0 ? null : th);
    }

    public static final int getCan_Not_Open_File() {
        return INSTANCE.getCan_Not_Open_File();
    }

    public static final int getMime_Type_Not_Found() {
        return INSTANCE.getMime_Type_Not_Found();
    }

    public static final int getMime_Type_Not_Support() {
        return INSTANCE.getMime_Type_Not_Support();
    }

    @Override
    public Throwable getCause() {
        return this.f786c;
    }

    @Override
    public int getF784a() {
        return this.f784a;
    }

    @Override
    public String getMessage() {
        return this.f785b;
    }
}
