package com.p020hp.open.printsdk;

import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.p020hp.printsdk.exception.BaseException;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/open/printsdk/PrintOptionException;", "Lcom/hp/printsdk/exception/BaseException;", "errorCode", "", "message", "", "cause", "", "(ILjava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getErrorCode", "()I", "getMessage", "()Ljava/lang/String;", "Companion", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class PrintOptionException extends BaseException {

    public static final Companion INSTANCE = new Companion(null);

    public static final int f795d = 101;

    public static final int f796e = 102;

    public static final int f797f = 103;

    public static final int f798g = 104;

    public static final int f799h = ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE;

    public static final int f800i = 106;

    public static final int f801j = ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT;

    public static final int f802k = 108;

    public static final int f803l = 109;

    public static final int f804m = 110;

    public static final int f805n = 111;

    public static final int f806o = UiPosIndexEnum.HOME_COUNTDOWN;

    public static final int f807p = UiPosIndexEnum.HOME_REPLACE_BG_TAB;

    public final int f808a;

    public final String f809b;

    public final Throwable f810c;

    @Metadata(m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b(\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u0007R\u001c\u0010\u000e\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0007R\u001c\u0010\u0011\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0002\u001a\u0004\b\u0013\u0010\u0007R\u001c\u0010\u0014\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0002\u001a\u0004\b\u0016\u0010\u0007R\u001c\u0010\u0017\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0002\u001a\u0004\b\u0019\u0010\u0007R\u001c\u0010\u001a\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0002\u001a\u0004\b\u001c\u0010\u0007R\u001c\u0010\u001d\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0002\u001a\u0004\b\u001f\u0010\u0007R\u001c\u0010 \u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0002\u001a\u0004\b\"\u0010\u0007R\u001c\u0010#\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0002\u001a\u0004\b%\u0010\u0007R\u001c\u0010&\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0002\u001a\u0004\b(\u0010\u0007R\u001c\u0010)\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u0002\u001a\u0004\b+\u0010\u0007¨\u0006,"}, m1293d2 = {"Lcom/hp/open/printsdk/PrintOptionException$Companion;", "", "()V", "ColorError", "", "getColorError$annotations", "getColorError", "()I", "CopiesError", "getCopiesError$annotations", "getCopiesError", "DPIError", "getDPIError$annotations", "getDPIError", "InputTrayError", "getInputTrayError$annotations", "getInputTrayError", "MediaMarginError", "getMediaMarginError$annotations", "getMediaMarginError", "MediaSizeError", "getMediaSizeError$annotations", "getMediaSizeError", "OrientationError", "getOrientationError$annotations", "getOrientationError", "PageRangeError", "getPageRangeError$annotations", "getPageRangeError", "PaperTypeError", "getPaperTypeError$annotations", "getPaperTypeError", "PrintTypeError", "getPrintTypeError$annotations", "getPrintTypeError", "QualityError", "getQualityError$annotations", "getQualityError", "ScaleError", "getScaleError$annotations", "getScaleError", "SidesError", "getSidesError$annotations", "getSidesError", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getColorError$annotations() {
        }

        @JvmStatic
        public static void getCopiesError$annotations() {
        }

        @JvmStatic
        public static void getDPIError$annotations() {
        }

        @JvmStatic
        public static void getInputTrayError$annotations() {
        }

        @JvmStatic
        public static void getMediaMarginError$annotations() {
        }

        @JvmStatic
        public static void getMediaSizeError$annotations() {
        }

        @JvmStatic
        public static void getOrientationError$annotations() {
        }

        @JvmStatic
        public static void getPageRangeError$annotations() {
        }

        @JvmStatic
        public static void getPaperTypeError$annotations() {
        }

        @JvmStatic
        public static void getPrintTypeError$annotations() {
        }

        @JvmStatic
        public static void getQualityError$annotations() {
        }

        @JvmStatic
        public static void getScaleError$annotations() {
        }

        @JvmStatic
        public static void getSidesError$annotations() {
        }

        public final int getColorError() {
            return PrintOptionException.f797f;
        }

        public final int getCopiesError() {
            return PrintOptionException.f795d;
        }

        public final int getDPIError() {
            return PrintOptionException.f805n;
        }

        public final int getInputTrayError() {
            return PrintOptionException.f804m;
        }

        public final int getMediaMarginError() {
            return PrintOptionException.f803l;
        }

        public final int getMediaSizeError() {
            return PrintOptionException.f796e;
        }

        public final int getOrientationError() {
            return PrintOptionException.f801j;
        }

        public final int getPageRangeError() {
            return PrintOptionException.f802k;
        }

        public final int getPaperTypeError() {
            return PrintOptionException.f806o;
        }

        public final int getPrintTypeError() {
            return PrintOptionException.f807p;
        }

        public final int getQualityError() {
            return PrintOptionException.f799h;
        }

        public final int getScaleError() {
            return PrintOptionException.f798g;
        }

        public final int getSidesError() {
            return PrintOptionException.f800i;
        }
    }

    public PrintOptionException(int i, String message, Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.f808a = i;
        this.f809b = message;
        this.f810c = th;
    }

    public PrintOptionException(int i, String str, Throwable th, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i2 & 4) != 0 ? null : th);
    }

    public static final int getColorError() {
        return INSTANCE.getColorError();
    }

    public static final int getCopiesError() {
        return INSTANCE.getCopiesError();
    }

    public static final int getDPIError() {
        return INSTANCE.getDPIError();
    }

    public static final int getInputTrayError() {
        return INSTANCE.getInputTrayError();
    }

    public static final int getMediaMarginError() {
        return INSTANCE.getMediaMarginError();
    }

    public static final int getMediaSizeError() {
        return INSTANCE.getMediaSizeError();
    }

    public static final int getOrientationError() {
        return INSTANCE.getOrientationError();
    }

    public static final int getPageRangeError() {
        return INSTANCE.getPageRangeError();
    }

    public static final int getPaperTypeError() {
        return INSTANCE.getPaperTypeError();
    }

    public static final int getPrintTypeError() {
        return INSTANCE.getPrintTypeError();
    }

    public static final int getQualityError() {
        return INSTANCE.getQualityError();
    }

    public static final int getScaleError() {
        return INSTANCE.getScaleError();
    }

    public static final int getSidesError() {
        return INSTANCE.getSidesError();
    }

    @Override
    public Throwable getCause() {
        return this.f810c;
    }

    @Override
    public int getF808a() {
        return this.f808a;
    }

    @Override
    public String getMessage() {
        return this.f809b;
    }
}
