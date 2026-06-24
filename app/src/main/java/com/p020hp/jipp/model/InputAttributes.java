package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.BooleanType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordOrName;
import com.p020hp.jipp.encoding.KeywordOrNameType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Resolution;
import com.p020hp.jipp.encoding.ResolutionType;
import com.p020hp.jipp.model.Orientation;
import com.p020hp.jipp.model.PrintQuality;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b_\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u0083\u00012\u00020\u0001:\u0004\u0083\u0001\u0084\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002Bã\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u001eJ\u0010\u0010j\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010k\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\u0010\u0010n\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010-J\u0010\u0010o\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010-J\u0011\u0010p\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019HÆ\u0003J\u0010\u0010q\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010-J\u000b\u0010r\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010t\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010$J\u0010\u0010u\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010-J\u000b\u0010v\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010x\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010-J\u000b\u0010y\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010z\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010-J\u000b\u0010{\u001a\u0004\u0018\u00010\u000fHÆ\u0003Jì\u0001\u0010|\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010}J\u0014\u0010~\u001a\u00020\u00042\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001HÖ\u0003J\n\u0010\u0081\u0001\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0082\u0001\u001a\u00020\tH\u0016R\u001e\u0010\u001f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030 0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010(\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R \u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b6\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001e\u0010\n\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b9\u001a\u0004\b7\u00103\"\u0004\b8\u00105R \u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u00101\u001a\u0004\b:\u0010-\"\u0004\b;\u0010/R\u001e\u0010\f\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b?\u001a\u0004\b=\u00103\"\u0004\b>\u00105R \u0010\r\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\bB\u00101\u001a\u0004\b@\u0010-\"\u0004\bA\u0010/R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\bG\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\bL\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\bQ\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u0010\n\u0002\bV\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR \u0010\u0016\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\bY\u00101\u001a\u0004\bW\u0010-\"\u0004\bX\u0010/R \u0010\u0017\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b\\\u00101\u001a\u0004\bZ\u0010-\"\u0004\b[\u0010/R$\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019X\u0086\u000e¢\u0006\u0010\n\u0002\b`\u001a\u0004\b]\u0010\"\"\u0004\b^\u0010_R \u0010\u001b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\bc\u00101\u001a\u0004\ba\u0010-\"\u0004\bb\u0010/R\u001e\u0010\u001c\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\bf\u001a\u0004\bd\u00103\"\u0004\be\u00105R\u001e\u0010\u001d\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\bi\u001a\u0004\bg\u00103\"\u0004\bh\u00105¨\u0006\u0085\u0001"}, m1293d2 = {"Lcom/hp/jipp/model/InputAttributes;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "inputAutoScaling", "", "inputAutoSkewCorrection", "inputBrightness", "", "inputColorMode", "", "inputContentType", "inputContrast", "inputFilmScanMode", "inputImagesToTransfer", "inputMedia", "Lcom/hp/jipp/encoding/KeywordOrName;", "inputOrientationRequested", "Lcom/hp/jipp/model/Orientation;", "inputQuality", "Lcom/hp/jipp/model/PrintQuality;", "inputResolution", "Lcom/hp/jipp/encoding/Resolution;", "inputScalingHeight", "inputScalingWidth", "inputScanRegions", "", "Lcom/hp/jipp/model/InputAttributes$InputScanRegions;", "inputSharpness", "inputSides", "inputSource", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/Orientation;Lcom/hp/jipp/model/PrintQuality;Lcom/hp/jipp/encoding/Resolution;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getInputAutoScaling", "()Ljava/lang/Boolean;", "setInputAutoScaling", "(Ljava/lang/Boolean;)V", "inputAutoScaling$1", "Ljava/lang/Boolean;", "getInputAutoSkewCorrection", "setInputAutoSkewCorrection", "inputAutoSkewCorrection$1", "getInputBrightness", "()Ljava/lang/Integer;", "setInputBrightness", "(Ljava/lang/Integer;)V", "inputBrightness$1", "Ljava/lang/Integer;", "getInputColorMode", "()Ljava/lang/String;", "setInputColorMode", "(Ljava/lang/String;)V", "inputColorMode$1", "getInputContentType", "setInputContentType", "inputContentType$1", "getInputContrast", "setInputContrast", "inputContrast$1", "getInputFilmScanMode", "setInputFilmScanMode", "inputFilmScanMode$1", "getInputImagesToTransfer", "setInputImagesToTransfer", "inputImagesToTransfer$1", "getInputMedia", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setInputMedia", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "inputMedia$1", "getInputOrientationRequested", "()Lcom/hp/jipp/model/Orientation;", "setInputOrientationRequested", "(Lcom/hp/jipp/model/Orientation;)V", "inputOrientationRequested$1", "getInputQuality", "()Lcom/hp/jipp/model/PrintQuality;", "setInputQuality", "(Lcom/hp/jipp/model/PrintQuality;)V", "inputQuality$1", "getInputResolution", "()Lcom/hp/jipp/encoding/Resolution;", "setInputResolution", "(Lcom/hp/jipp/encoding/Resolution;)V", "inputResolution$1", "getInputScalingHeight", "setInputScalingHeight", "inputScalingHeight$1", "getInputScalingWidth", "setInputScalingWidth", "inputScalingWidth$1", "getInputScanRegions", "setInputScanRegions", "(Ljava/util/List;)V", "inputScanRegions$1", "getInputSharpness", "setInputSharpness", "inputSharpness$1", "getInputSides", "setInputSides", "inputSides$1", "getInputSource", "setInputSource", "inputSource$1", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", PrinterServiceType.copy, "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/Orientation;Lcom/hp/jipp/model/PrintQuality;Lcom/hp/jipp/encoding/Resolution;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/hp/jipp/model/InputAttributes;", "equals", "other", "", "hashCode", "toString", "Companion", "InputScanRegions", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class InputAttributes implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<InputAttributes> cls;
    public static final BooleanType inputAutoScaling;
    public static final BooleanType inputAutoSkewCorrection;
    public static final IntType inputBrightness;
    public static final KeywordType inputColorMode;
    public static final KeywordType inputContentType;
    public static final IntType inputContrast;
    public static final KeywordType inputFilmScanMode;
    public static final IntType inputImagesToTransfer;
    public static final KeywordOrNameType inputMedia;
    public static final Orientation.Type inputOrientationRequested;
    public static final PrintQuality.Type inputQuality;
    public static final ResolutionType inputResolution;
    public static final IntType inputScalingHeight;
    public static final IntType inputScalingWidth;
    public static final AttributeCollection.SetType<InputScanRegions> inputScanRegions;
    public static final IntType inputSharpness;
    public static final KeywordType inputSides;
    public static final KeywordType inputSource;

    private Boolean inputAutoScaling;

    private Boolean inputAutoSkewCorrection;

    private Integer inputBrightness;

    private String inputColorMode;

    private String inputContentType;

    private Integer inputContrast;

    private String inputFilmScanMode;

    private Integer inputImagesToTransfer;

    private KeywordOrName inputMedia;

    private Orientation inputOrientationRequested;

    private PrintQuality inputQuality;

    private Resolution inputResolution;

    private Integer inputScalingHeight;

    private Integer inputScalingWidth;

    private List<InputScanRegions> inputScanRegions;

    private Integer inputSharpness;

    private String inputSides;

    private String inputSource;

    public final Boolean getInputAutoScaling() {
        return this.inputAutoScaling;
    }

    public final Orientation getInputOrientationRequested() {
        return this.inputOrientationRequested;
    }

    public final PrintQuality getInputQuality() {
        return this.inputQuality;
    }

    public final Resolution getInputResolution() {
        return this.inputResolution;
    }

    public final Integer getInputScalingHeight() {
        return this.inputScalingHeight;
    }

    public final Integer getInputScalingWidth() {
        return this.inputScalingWidth;
    }

    public final List<InputScanRegions> component15() {
        return this.inputScanRegions;
    }

    public final Integer getInputSharpness() {
        return this.inputSharpness;
    }

    public final String getInputSides() {
        return this.inputSides;
    }

    public final String getInputSource() {
        return this.inputSource;
    }

    public final Boolean getInputAutoSkewCorrection() {
        return this.inputAutoSkewCorrection;
    }

    public final Integer getInputBrightness() {
        return this.inputBrightness;
    }

    public final String getInputColorMode() {
        return this.inputColorMode;
    }

    public final String getInputContentType() {
        return this.inputContentType;
    }

    public final Integer getInputContrast() {
        return this.inputContrast;
    }

    public final String getInputFilmScanMode() {
        return this.inputFilmScanMode;
    }

    public final Integer getInputImagesToTransfer() {
        return this.inputImagesToTransfer;
    }

    public final KeywordOrName getInputMedia() {
        return this.inputMedia;
    }

    public final InputAttributes copy(Boolean inputAutoScaling2, Boolean inputAutoSkewCorrection2, Integer inputBrightness2, String inputColorMode2, String inputContentType2, Integer inputContrast2, String inputFilmScanMode2, Integer inputImagesToTransfer2, KeywordOrName inputMedia2, Orientation inputOrientationRequested2, PrintQuality inputQuality2, Resolution inputResolution2, Integer inputScalingHeight2, Integer inputScalingWidth2, List<InputScanRegions> inputScanRegions2, Integer inputSharpness2, String inputSides2, String inputSource2) {
        return new InputAttributes(inputAutoScaling2, inputAutoSkewCorrection2, inputBrightness2, inputColorMode2, inputContentType2, inputContrast2, inputFilmScanMode2, inputImagesToTransfer2, inputMedia2, inputOrientationRequested2, inputQuality2, inputResolution2, inputScalingHeight2, inputScalingWidth2, inputScanRegions2, inputSharpness2, inputSides2, inputSource2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InputAttributes)) {
            return false;
        }
        InputAttributes inputAttributes = (InputAttributes) other;
        return Intrinsics.areEqual(this.inputAutoScaling, inputAttributes.inputAutoScaling) && Intrinsics.areEqual(this.inputAutoSkewCorrection, inputAttributes.inputAutoSkewCorrection) && Intrinsics.areEqual(this.inputBrightness, inputAttributes.inputBrightness) && Intrinsics.areEqual(this.inputColorMode, inputAttributes.inputColorMode) && Intrinsics.areEqual(this.inputContentType, inputAttributes.inputContentType) && Intrinsics.areEqual(this.inputContrast, inputAttributes.inputContrast) && Intrinsics.areEqual(this.inputFilmScanMode, inputAttributes.inputFilmScanMode) && Intrinsics.areEqual(this.inputImagesToTransfer, inputAttributes.inputImagesToTransfer) && Intrinsics.areEqual(this.inputMedia, inputAttributes.inputMedia) && Intrinsics.areEqual(this.inputOrientationRequested, inputAttributes.inputOrientationRequested) && Intrinsics.areEqual(this.inputQuality, inputAttributes.inputQuality) && Intrinsics.areEqual(this.inputResolution, inputAttributes.inputResolution) && Intrinsics.areEqual(this.inputScalingHeight, inputAttributes.inputScalingHeight) && Intrinsics.areEqual(this.inputScalingWidth, inputAttributes.inputScalingWidth) && Intrinsics.areEqual(this.inputScanRegions, inputAttributes.inputScanRegions) && Intrinsics.areEqual(this.inputSharpness, inputAttributes.inputSharpness) && Intrinsics.areEqual(this.inputSides, inputAttributes.inputSides) && Intrinsics.areEqual(this.inputSource, inputAttributes.inputSource);
    }

    public int hashCode() {
        Boolean bool = this.inputAutoScaling;
        int iHashCode = (bool != null ? bool.hashCode() : 0) * 31;
        Boolean bool2 = this.inputAutoSkewCorrection;
        int iHashCode2 = (iHashCode + (bool2 != null ? bool2.hashCode() : 0)) * 31;
        Integer num = this.inputBrightness;
        int iHashCode3 = (iHashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        String str = this.inputColorMode;
        int iHashCode4 = (iHashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.inputContentType;
        int iHashCode5 = (iHashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num2 = this.inputContrast;
        int iHashCode6 = (iHashCode5 + (num2 != null ? num2.hashCode() : 0)) * 31;
        String str3 = this.inputFilmScanMode;
        int iHashCode7 = (iHashCode6 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Integer num3 = this.inputImagesToTransfer;
        int iHashCode8 = (iHashCode7 + (num3 != null ? num3.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName = this.inputMedia;
        int iHashCode9 = (iHashCode8 + (keywordOrName != null ? keywordOrName.hashCode() : 0)) * 31;
        Orientation orientation = this.inputOrientationRequested;
        int iHashCode10 = (iHashCode9 + (orientation != null ? orientation.hashCode() : 0)) * 31;
        PrintQuality printQuality = this.inputQuality;
        int iHashCode11 = (iHashCode10 + (printQuality != null ? printQuality.hashCode() : 0)) * 31;
        Resolution resolution = this.inputResolution;
        int iHashCode12 = (iHashCode11 + (resolution != null ? resolution.hashCode() : 0)) * 31;
        Integer num4 = this.inputScalingHeight;
        int iHashCode13 = (iHashCode12 + (num4 != null ? num4.hashCode() : 0)) * 31;
        Integer num5 = this.inputScalingWidth;
        int iHashCode14 = (iHashCode13 + (num5 != null ? num5.hashCode() : 0)) * 31;
        List<InputScanRegions> list = this.inputScanRegions;
        int iHashCode15 = (iHashCode14 + (list != null ? list.hashCode() : 0)) * 31;
        Integer num6 = this.inputSharpness;
        int iHashCode16 = (iHashCode15 + (num6 != null ? num6.hashCode() : 0)) * 31;
        String str4 = this.inputSides;
        int iHashCode17 = (iHashCode16 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.inputSource;
        return iHashCode17 + (str5 != null ? str5.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public InputAttributes(Boolean bool, Boolean bool2, Integer num, String str, String str2, Integer num2, String str3, Integer num3, KeywordOrName keywordOrName, Orientation orientation, PrintQuality printQuality, Resolution resolution, Integer num4, Integer num5, List<InputScanRegions> list, Integer num6, String str4, String str5) {
        this.inputAutoScaling = bool;
        this.inputAutoSkewCorrection = bool2;
        this.inputBrightness = num;
        this.inputColorMode = str;
        this.inputContentType = str2;
        this.inputContrast = num2;
        this.inputFilmScanMode = str3;
        this.inputImagesToTransfer = num3;
        this.inputMedia = keywordOrName;
        this.inputOrientationRequested = orientation;
        this.inputQuality = printQuality;
        this.inputResolution = resolution;
        this.inputScalingHeight = num4;
        this.inputScalingWidth = num5;
        this.inputScanRegions = list;
        this.inputSharpness = num6;
        this.inputSides = str4;
        this.inputSource = str5;
    }

    public InputAttributes(Boolean bool, Boolean bool2, Integer num, String str, String str2, Integer num2, String str3, Integer num3, KeywordOrName keywordOrName, Orientation orientation, PrintQuality printQuality, Resolution resolution, Integer num4, Integer num5, List list, Integer num6, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Boolean bool3;
        Boolean bool4;
        Integer num7;
        String str6;
        String str7;
        Integer num8;
        String str8;
        Integer num9;
        KeywordOrName keywordOrName2;
        Orientation orientation2;
        PrintQuality printQuality2;
        Resolution resolution2;
        Integer num10;
        Integer num11;
        List list2;
        Integer num12;
        String str9;
        String str10 = null;
        if ((i & 1) != 0) {
            bool3 = null;
        } else {
            bool3 = bool;
        }
        if ((i & 2) != 0) {
            bool4 = null;
        } else {
            bool4 = bool2;
        }
        if ((i & 4) != 0) {
            num7 = null;
        } else {
            num7 = num;
        }
        if ((i & 8) != 0) {
            str6 = null;
        } else {
            str6 = str;
        }
        if ((i & 16) != 0) {
            str7 = null;
        } else {
            str7 = str2;
        }
        if ((i & 32) != 0) {
            num8 = null;
        } else {
            num8 = num2;
        }
        if ((i & 64) != 0) {
            str8 = null;
        } else {
            str8 = str3;
        }
        if ((i & 128) != 0) {
            num9 = null;
        } else {
            num9 = num3;
        }
        if ((i & 256) != 0) {
            keywordOrName2 = null;
        } else {
            keywordOrName2 = keywordOrName;
        }
        if ((i & 512) != 0) {
            orientation2 = null;
        } else {
            orientation2 = orientation;
        }
        if ((i & 1024) != 0) {
            printQuality2 = null;
        } else {
            printQuality2 = printQuality;
        }
        if ((i & 2048) != 0) {
            resolution2 = null;
        } else {
            resolution2 = resolution;
        }
        if ((i & 4096) != 0) {
            num10 = null;
        } else {
            num10 = num4;
        }
        if ((i & 8192) != 0) {
            num11 = null;
        } else {
            num11 = num5;
        }
        Integer num13 = num11;
        if ((i & 16384) != 0) {
            list2 = null;
        } else {
            list2 = list;
        }
        if ((i & 32768) != 0) {
            num12 = null;
        } else {
            num12 = num6;
        }
        if ((i & 65536) != 0) {
            str9 = null;
        } else {
            str9 = str4;
        }
        if ((i & 131072) != 0) {
        } else {
            str10 = str5;
        }
        this(bool3, bool4, num7, str6, str7, num8, str8, num9, keywordOrName2, orientation2, printQuality2, resolution2, num10, num13, list2, num12, str9, str10);
    }

    public final Boolean getInputAutoScaling() {
        return this.inputAutoScaling;
    }

    public final void setInputAutoScaling(Boolean bool) {
        this.inputAutoScaling = bool;
    }

    public final Boolean getInputAutoSkewCorrection() {
        return this.inputAutoSkewCorrection;
    }

    public final void setInputAutoSkewCorrection(Boolean bool) {
        this.inputAutoSkewCorrection = bool;
    }

    public final Integer getInputBrightness() {
        return this.inputBrightness;
    }

    public final void setInputBrightness(Integer num) {
        this.inputBrightness = num;
    }

    public final String getInputColorMode() {
        return this.inputColorMode;
    }

    public final void setInputColorMode(String str) {
        this.inputColorMode = str;
    }

    public final String getInputContentType() {
        return this.inputContentType;
    }

    public final void setInputContentType(String str) {
        this.inputContentType = str;
    }

    public final Integer getInputContrast() {
        return this.inputContrast;
    }

    public final void setInputContrast(Integer num) {
        this.inputContrast = num;
    }

    public final String getInputFilmScanMode() {
        return this.inputFilmScanMode;
    }

    public final void setInputFilmScanMode(String str) {
        this.inputFilmScanMode = str;
    }

    public final Integer getInputImagesToTransfer() {
        return this.inputImagesToTransfer;
    }

    public final void setInputImagesToTransfer(Integer num) {
        this.inputImagesToTransfer = num;
    }

    public final KeywordOrName getInputMedia() {
        return this.inputMedia;
    }

    public final void setInputMedia(KeywordOrName keywordOrName) {
        this.inputMedia = keywordOrName;
    }

    public final Orientation getInputOrientationRequested() {
        return this.inputOrientationRequested;
    }

    public final void setInputOrientationRequested(Orientation orientation) {
        this.inputOrientationRequested = orientation;
    }

    public final PrintQuality getInputQuality() {
        return this.inputQuality;
    }

    public final void setInputQuality(PrintQuality printQuality) {
        this.inputQuality = printQuality;
    }

    public final Resolution getInputResolution() {
        return this.inputResolution;
    }

    public final void setInputResolution(Resolution resolution) {
        this.inputResolution = resolution;
    }

    public final Integer getInputScalingHeight() {
        return this.inputScalingHeight;
    }

    public final void setInputScalingHeight(Integer num) {
        this.inputScalingHeight = num;
    }

    public final Integer getInputScalingWidth() {
        return this.inputScalingWidth;
    }

    public final void setInputScalingWidth(Integer num) {
        this.inputScalingWidth = num;
    }

    public final List<InputScanRegions> getInputScanRegions() {
        return this.inputScanRegions;
    }

    public final void setInputScanRegions(List<InputScanRegions> list) {
        this.inputScanRegions = list;
    }

    public final Integer getInputSharpness() {
        return this.inputSharpness;
    }

    public final void setInputSharpness(Integer num) {
        this.inputSharpness = num;
    }

    public final String getInputSides() {
        return this.inputSides;
    }

    public final void setInputSides(String str) {
        this.inputSides = str;
    }

    public final String getInputSource() {
        return this.inputSource;
    }

    public final void setInputSource(String str) {
        this.inputSource = str;
    }

    public InputAttributes() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[18];
        Boolean bool = this.inputAutoScaling;
        attributeArr[0] = bool != null ? inputAutoScaling.mo418of(Boolean.valueOf(bool.booleanValue())) : null;
        Boolean bool2 = this.inputAutoSkewCorrection;
        attributeArr[1] = bool2 != null ? inputAutoSkewCorrection.mo418of(Boolean.valueOf(bool2.booleanValue())) : null;
        Integer num = this.inputBrightness;
        attributeArr[2] = num != null ? inputBrightness.mo418of(Integer.valueOf(num.intValue())) : null;
        String str = this.inputColorMode;
        attributeArr[3] = str != null ? inputColorMode.mo418of(str) : null;
        String str2 = this.inputContentType;
        attributeArr[4] = str2 != null ? inputContentType.mo418of(str2) : null;
        Integer num2 = this.inputContrast;
        attributeArr[5] = num2 != null ? inputContrast.mo418of(Integer.valueOf(num2.intValue())) : null;
        String str3 = this.inputFilmScanMode;
        attributeArr[6] = str3 != null ? inputFilmScanMode.mo418of(str3) : null;
        Integer num3 = this.inputImagesToTransfer;
        attributeArr[7] = num3 != null ? inputImagesToTransfer.mo418of(Integer.valueOf(num3.intValue())) : null;
        KeywordOrName keywordOrName = this.inputMedia;
        attributeArr[8] = keywordOrName != null ? inputMedia.mo418of(keywordOrName) : null;
        Orientation orientation = this.inputOrientationRequested;
        attributeArr[9] = orientation != null ? inputOrientationRequested.mo418of((Object) orientation) : null;
        PrintQuality printQuality = this.inputQuality;
        attributeArr[10] = printQuality != null ? inputQuality.mo418of((Object) printQuality) : null;
        Resolution resolution = this.inputResolution;
        attributeArr[11] = resolution != null ? inputResolution.mo418of(resolution) : null;
        Integer num4 = this.inputScalingHeight;
        attributeArr[12] = num4 != null ? inputScalingHeight.mo418of(Integer.valueOf(num4.intValue())) : null;
        Integer num5 = this.inputScalingWidth;
        attributeArr[13] = num5 != null ? inputScalingWidth.mo418of(Integer.valueOf(num5.intValue())) : null;
        List<InputScanRegions> list = this.inputScanRegions;
        attributeArr[14] = list != null ? inputScanRegions.mo417of((Iterable) list) : null;
        Integer num6 = this.inputSharpness;
        attributeArr[15] = num6 != null ? inputSharpness.mo418of(Integer.valueOf(num6.intValue())) : null;
        String str4 = this.inputSides;
        attributeArr[16] = str4 != null ? inputSides.mo418of(str4) : null;
        String str5 = this.inputSource;
        attributeArr[17] = str5 != null ? inputSource.mo418of(str5) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010%\u001a\u00020\u00022\u0010\u0010&\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030(0'H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00168\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u00188\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, m1293d2 = {"Lcom/hp/jipp/model/InputAttributes$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/InputAttributes;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "inputAutoScaling", "Lcom/hp/jipp/encoding/BooleanType;", "inputAutoSkewCorrection", "inputBrightness", "Lcom/hp/jipp/encoding/IntType;", "inputColorMode", "Lcom/hp/jipp/encoding/KeywordType;", "inputContentType", "inputContrast", "inputFilmScanMode", "inputImagesToTransfer", "inputMedia", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "inputOrientationRequested", "Lcom/hp/jipp/model/Orientation$Type;", "inputQuality", "Lcom/hp/jipp/model/PrintQuality$Type;", "inputResolution", "Lcom/hp/jipp/encoding/ResolutionType;", "inputScalingHeight", "inputScalingWidth", "inputScanRegions", "Lcom/hp/jipp/encoding/AttributeCollection$SetType;", "Lcom/hp/jipp/model/InputAttributes$InputScanRegions;", "inputSharpness", "inputSides", "inputSource", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<InputAttributes> {
        @Deprecated(message = "Remove this symbol")
        public static void getTypes$annotations() {
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override
        public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
        }

        @Override
        public AttributeCollection convert(List list) {
            return convert((List<? extends Attribute<?>>) list);
        }

        @Override
        public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
        }

        @Override
        public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
        }

        @Override
        public InputAttributes convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new InputAttributes((Boolean) extractOne(attributes, InputAttributes.inputAutoScaling), (Boolean) extractOne(attributes, InputAttributes.inputAutoSkewCorrection), (Integer) extractOne(attributes, InputAttributes.inputBrightness), (String) extractOne(attributes, InputAttributes.inputColorMode), (String) extractOne(attributes, InputAttributes.inputContentType), (Integer) extractOne(attributes, InputAttributes.inputContrast), (String) extractOne(attributes, InputAttributes.inputFilmScanMode), (Integer) extractOne(attributes, InputAttributes.inputImagesToTransfer), (KeywordOrName) extractOne(attributes, InputAttributes.inputMedia), (Orientation) extractOne(attributes, InputAttributes.inputOrientationRequested), (PrintQuality) extractOne(attributes, InputAttributes.inputQuality), (Resolution) extractOne(attributes, InputAttributes.inputResolution), (Integer) extractOne(attributes, InputAttributes.inputScalingHeight), (Integer) extractOne(attributes, InputAttributes.inputScalingWidth), extractAll(attributes, InputAttributes.inputScanRegions), (Integer) extractOne(attributes, InputAttributes.inputSharpness), (String) extractOne(attributes, InputAttributes.inputSides), (String) extractOne(attributes, InputAttributes.inputSource));
        }

        @Override
        public Class<InputAttributes> getCls() {
            return InputAttributes.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = InputAttributes.class;
        Types = companion;
        inputAutoScaling = new BooleanType("input-auto-scaling");
        inputAutoSkewCorrection = new BooleanType("input-auto-skew-correction");
        inputBrightness = new IntType("input-brightness");
        inputColorMode = new KeywordType("input-color-mode");
        inputContentType = new KeywordType("input-content-type");
        inputContrast = new IntType("input-contrast");
        inputFilmScanMode = new KeywordType("input-film-scan-mode");
        inputImagesToTransfer = new IntType("input-images-to-transfer");
        inputMedia = new KeywordOrNameType("input-media");
        inputOrientationRequested = new Orientation.Type("input-orientation-requested");
        inputQuality = new PrintQuality.Type("input-quality");
        inputResolution = new ResolutionType("input-resolution");
        inputScalingHeight = new IntType("input-scaling-height");
        inputScalingWidth = new IntType("input-scaling-width");
        inputScanRegions = new AttributeCollection.SetType<>("input-scan-regions", InputScanRegions.INSTANCE);
        inputSharpness = new IntType("input-sharpness");
        inputSides = new KeywordType("input-sides");
        inputSource = new KeywordType("input-source");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 *2\u00020\u0001:\u0001*B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000fJ>\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020\u0004HÖ\u0001J\b\u0010(\u001a\u00020)H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R \u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u0017\u0010\u000f\"\u0004\b\u0018\u0010\u0011R \u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011¨\u0006+"}, m1293d2 = {"Lcom/hp/jipp/model/InputAttributes$InputScanRegions;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xDimension", "", "xOrigin", "yDimension", "yOrigin", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXDimension", "()Ljava/lang/Integer;", "setXDimension", "(Ljava/lang/Integer;)V", "xDimension$1", "Ljava/lang/Integer;", "getXOrigin", "setXOrigin", "xOrigin$1", "getYDimension", "setYDimension", "yDimension$1", "getYOrigin", "setYOrigin", "yOrigin$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/InputAttributes$InputScanRegions;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class InputScanRegions implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<InputScanRegions> cls;
        public static final IntType xDimension;
        public static final IntType xOrigin;
        public static final IntType yDimension;
        public static final IntType yOrigin;

        private Integer xDimension;

        private Integer xOrigin;

        private Integer yDimension;

        private Integer yOrigin;

        public static InputScanRegions copy$default(InputScanRegions inputScanRegions, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
            if ((i & 1) != 0) {
                num = inputScanRegions.xDimension;
            }
            if ((i & 2) != 0) {
                num2 = inputScanRegions.xOrigin;
            }
            if ((i & 4) != 0) {
                num3 = inputScanRegions.yDimension;
            }
            if ((i & 8) != 0) {
                num4 = inputScanRegions.yOrigin;
            }
            return inputScanRegions.copy(num, num2, num3, num4);
        }

        public final Integer getXDimension() {
            return this.xDimension;
        }

        public final Integer getXOrigin() {
            return this.xOrigin;
        }

        public final Integer getYDimension() {
            return this.yDimension;
        }

        public final Integer getYOrigin() {
            return this.yOrigin;
        }

        public final InputScanRegions copy(Integer xDimension2, Integer xOrigin2, Integer yDimension2, Integer yOrigin2) {
            return new InputScanRegions(xDimension2, xOrigin2, yDimension2, yOrigin2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InputScanRegions)) {
                return false;
            }
            InputScanRegions inputScanRegions = (InputScanRegions) other;
            return Intrinsics.areEqual(this.xDimension, inputScanRegions.xDimension) && Intrinsics.areEqual(this.xOrigin, inputScanRegions.xOrigin) && Intrinsics.areEqual(this.yDimension, inputScanRegions.yDimension) && Intrinsics.areEqual(this.yOrigin, inputScanRegions.yOrigin);
        }

        public int hashCode() {
            Integer num = this.xDimension;
            int iHashCode = (num != null ? num.hashCode() : 0) * 31;
            Integer num2 = this.xOrigin;
            int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
            Integer num3 = this.yDimension;
            int iHashCode3 = (iHashCode2 + (num3 != null ? num3.hashCode() : 0)) * 31;
            Integer num4 = this.yOrigin;
            return iHashCode3 + (num4 != null ? num4.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public InputScanRegions(Integer num, Integer num2, Integer num3, Integer num4) {
            this.xDimension = num;
            this.xOrigin = num2;
            this.yDimension = num3;
            this.yOrigin = num4;
        }

        public InputScanRegions(Integer num, Integer num2, Integer num3, Integer num4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                num2 = null;
            }
            if ((i & 4) != 0) {
                num3 = null;
            }
            if ((i & 8) != 0) {
                num4 = null;
            }
            this(num, num2, num3, num4);
        }

        public final Integer getXDimension() {
            return this.xDimension;
        }

        public final void setXDimension(Integer num) {
            this.xDimension = num;
        }

        public final Integer getXOrigin() {
            return this.xOrigin;
        }

        public final void setXOrigin(Integer num) {
            this.xOrigin = num;
        }

        public final Integer getYDimension() {
            return this.yDimension;
        }

        public final void setYDimension(Integer num) {
            this.yDimension = num;
        }

        public final Integer getYOrigin() {
            return this.yOrigin;
        }

        public final void setYOrigin(Integer num) {
            this.yOrigin = num;
        }

        public InputScanRegions() {
            this(null, null, null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute<Integer> attributeOf;
            Attribute<Integer> attributeOf2;
            Attribute<Integer> attributeOf3;
            Attribute[] attributeArr = new Attribute[4];
            Integer num = this.xDimension;
            Attribute<Integer> attributeOf4 = null;
            if (num != null) {
                attributeOf = xDimension.mo418of(Integer.valueOf(num.intValue()));
            } else {
                attributeOf = null;
            }
            attributeArr[0] = attributeOf;
            Integer num2 = this.xOrigin;
            if (num2 != null) {
                attributeOf2 = xOrigin.mo418of(Integer.valueOf(num2.intValue()));
            } else {
                attributeOf2 = null;
            }
            attributeArr[1] = attributeOf2;
            Integer num3 = this.yDimension;
            if (num3 != null) {
                attributeOf3 = yDimension.mo418of(Integer.valueOf(num3.intValue()));
            } else {
                attributeOf3 = null;
            }
            attributeArr[2] = attributeOf3;
            Integer num4 = this.yOrigin;
            if (num4 != null) {
                attributeOf4 = yOrigin.mo418of(Integer.valueOf(num4.intValue()));
            }
            attributeArr[3] = attributeOf4;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/model/InputAttributes$InputScanRegions$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/InputAttributes$InputScanRegions;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xDimension", "Lcom/hp/jipp/encoding/IntType;", "xOrigin", "yDimension", "yOrigin", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<InputScanRegions> {
            @Deprecated(message = "Remove this symbol")
            public static void getTypes$annotations() {
            }

            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Override
            public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
            }

            @Override
            public AttributeCollection convert(List list) {
                return convert((List<? extends Attribute<?>>) list);
            }

            @Override
            public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
            }

            @Override
            public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
            }

            @Override
            public InputScanRegions convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new InputScanRegions((Integer) extractOne(attributes, InputScanRegions.xDimension), (Integer) extractOne(attributes, InputScanRegions.xOrigin), (Integer) extractOne(attributes, InputScanRegions.yDimension), (Integer) extractOne(attributes, InputScanRegions.yOrigin));
            }

            @Override
            public Class<InputScanRegions> getCls() {
                return InputScanRegions.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = InputScanRegions.class;
            Types = companion;
            xDimension = new IntType("x-dimension");
            xOrigin = new IntType("x-origin");
            yDimension = new IntType("y-dimension");
            yOrigin = new IntType("y-origin");
        }

        public String toString() {
            return "InputScanRegions(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    public String toString() {
        return "InputAttributes(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
