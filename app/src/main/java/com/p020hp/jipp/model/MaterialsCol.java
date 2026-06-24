package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.BooleanType;
import com.p020hp.jipp.encoding.IntOrIntRange;
import com.p020hp.jipp.encoding.IntOrIntRangeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordOrName;
import com.p020hp.jipp.encoding.KeywordOrNameType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bS\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 r2\u00020\u0001:\u0001rB\u0007\b\u0016¢\u0006\u0002\u0010\u0002BË\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010\u0019J\u0010\u0010[\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0011\u0010\\\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000fHÆ\u0003J\u0010\u0010]\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010^\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010_\u001a\u0004\u0018\u00010\u0013HÆ\u0003¢\u0006\u0002\u0010IJ\u0010\u0010`\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010a\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010e\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010g\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010h\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010j\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJÔ\u0001\u0010k\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÆ\u0001¢\u0006\u0002\u0010lJ\u0013\u0010m\u001a\u00020\u00132\b\u0010n\u001a\u0004\u0018\u00010oHÖ\u0003J\t\u0010p\u001a\u00020\u0004HÖ\u0001J\b\u0010q\u001a\u00020\u0006H\u0016R\u001e\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b0\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b(\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b+\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'R \u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010#\u001a\u0004\b,\u0010\u001f\"\u0004\b-\u0010!R \u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u0010#\u001a\u0004\b/\u0010\u001f\"\u0004\b0\u0010!R \u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u0010#\u001a\u0004\b2\u0010\u001f\"\u0004\b3\u0010!R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b7\u001a\u0004\b5\u0010%\"\u0004\b6\u0010'R\u001e\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b:\u001a\u0004\b8\u0010%\"\u0004\b9\u0010'R \u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010#\u001a\u0004\b;\u0010\u001f\"\u0004\b<\u0010!R$\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\bA\u001a\u0004\b>\u0010\u001d\"\u0004\b?\u0010@R \u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\bD\u0010#\u001a\u0004\bB\u0010\u001f\"\u0004\bC\u0010!R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\bG\u001a\u0004\bE\u0010%\"\u0004\bF\u0010'R \u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR \u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\bP\u0010#\u001a\u0004\bN\u0010\u001f\"\u0004\bO\u0010!R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u0010\n\u0002\bU\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u0010\n\u0002\bZ\u001a\u0004\bV\u0010W\"\u0004\bX\u0010Y¨\u0006s"}, m1293d2 = {"Lcom/hp/jipp/model/MaterialsCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "materialAmount", "", "materialAmountUnits", "", "materialColor", "materialDiameter", "materialDiameterTolerance", "materialFillDensity", "materialKey", "materialName", "materialNozzleDiameter", "materialPurpose", "", "materialRate", "materialRateUnits", "materialRetraction", "", "materialShellThickness", "materialTemperature", "Lcom/hp/jipp/encoding/IntOrIntRange;", "materialType", "Lcom/hp/jipp/encoding/KeywordOrName;", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Lcom/hp/jipp/encoding/IntOrIntRange;Lcom/hp/jipp/encoding/KeywordOrName;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getMaterialAmount", "()Ljava/lang/Integer;", "setMaterialAmount", "(Ljava/lang/Integer;)V", "materialAmount$1", "Ljava/lang/Integer;", "getMaterialAmountUnits", "()Ljava/lang/String;", "setMaterialAmountUnits", "(Ljava/lang/String;)V", "materialAmountUnits$1", "getMaterialColor", "setMaterialColor", "materialColor$1", "getMaterialDiameter", "setMaterialDiameter", "materialDiameter$1", "getMaterialDiameterTolerance", "setMaterialDiameterTolerance", "materialDiameterTolerance$1", "getMaterialFillDensity", "setMaterialFillDensity", "materialFillDensity$1", "getMaterialKey", "setMaterialKey", "materialKey$1", "getMaterialName", "setMaterialName", "materialName$1", "getMaterialNozzleDiameter", "setMaterialNozzleDiameter", "materialNozzleDiameter$1", "getMaterialPurpose", "setMaterialPurpose", "(Ljava/util/List;)V", "materialPurpose$1", "getMaterialRate", "setMaterialRate", "materialRate$1", "getMaterialRateUnits", "setMaterialRateUnits", "materialRateUnits$1", "getMaterialRetraction", "()Ljava/lang/Boolean;", "setMaterialRetraction", "(Ljava/lang/Boolean;)V", "materialRetraction$1", "Ljava/lang/Boolean;", "getMaterialShellThickness", "setMaterialShellThickness", "materialShellThickness$1", "getMaterialTemperature", "()Lcom/hp/jipp/encoding/IntOrIntRange;", "setMaterialTemperature", "(Lcom/hp/jipp/encoding/IntOrIntRange;)V", "materialTemperature$1", "getMaterialType", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setMaterialType", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "materialType$1", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Lcom/hp/jipp/encoding/IntOrIntRange;Lcom/hp/jipp/encoding/KeywordOrName;)Lcom/hp/jipp/model/MaterialsCol;", "equals", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class MaterialsCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<MaterialsCol> cls;
    public static final IntType materialAmount;
    public static final KeywordType materialAmountUnits;
    public static final KeywordType materialColor;
    public static final IntType materialDiameter;
    public static final IntType materialDiameterTolerance;
    public static final IntType materialFillDensity;
    public static final KeywordType materialKey;
    public static final NameType materialName;
    public static final IntType materialNozzleDiameter;
    public static final KeywordType.Set materialPurpose;
    public static final IntType materialRate;
    public static final KeywordType materialRateUnits;
    public static final BooleanType materialRetraction;
    public static final IntType materialShellThickness;
    public static final IntOrIntRangeType materialTemperature;
    public static final KeywordOrNameType materialType;

    private Integer materialAmount;

    private String materialAmountUnits;

    private String materialColor;

    private Integer materialDiameter;

    private Integer materialDiameterTolerance;

    private Integer materialFillDensity;

    private String materialKey;

    private String materialName;

    private Integer materialNozzleDiameter;

    private List<String> materialPurpose;

    private Integer materialRate;

    private String materialRateUnits;

    private Boolean materialRetraction;

    private Integer materialShellThickness;

    private IntOrIntRange materialTemperature;

    private KeywordOrName materialType;

    public final Integer getMaterialAmount() {
        return this.materialAmount;
    }

    public final List<String> component10() {
        return this.materialPurpose;
    }

    public final Integer getMaterialRate() {
        return this.materialRate;
    }

    public final String getMaterialRateUnits() {
        return this.materialRateUnits;
    }

    public final Boolean getMaterialRetraction() {
        return this.materialRetraction;
    }

    public final Integer getMaterialShellThickness() {
        return this.materialShellThickness;
    }

    public final IntOrIntRange getMaterialTemperature() {
        return this.materialTemperature;
    }

    public final KeywordOrName getMaterialType() {
        return this.materialType;
    }

    public final String getMaterialAmountUnits() {
        return this.materialAmountUnits;
    }

    public final String getMaterialColor() {
        return this.materialColor;
    }

    public final Integer getMaterialDiameter() {
        return this.materialDiameter;
    }

    public final Integer getMaterialDiameterTolerance() {
        return this.materialDiameterTolerance;
    }

    public final Integer getMaterialFillDensity() {
        return this.materialFillDensity;
    }

    public final String getMaterialKey() {
        return this.materialKey;
    }

    public final String getMaterialName() {
        return this.materialName;
    }

    public final Integer getMaterialNozzleDiameter() {
        return this.materialNozzleDiameter;
    }

    public final MaterialsCol copy(Integer materialAmount2, String materialAmountUnits2, String materialColor2, Integer materialDiameter2, Integer materialDiameterTolerance2, Integer materialFillDensity2, String materialKey2, String materialName2, Integer materialNozzleDiameter2, List<String> materialPurpose2, Integer materialRate2, String materialRateUnits2, Boolean materialRetraction2, Integer materialShellThickness2, IntOrIntRange materialTemperature2, KeywordOrName materialType2) {
        return new MaterialsCol(materialAmount2, materialAmountUnits2, materialColor2, materialDiameter2, materialDiameterTolerance2, materialFillDensity2, materialKey2, materialName2, materialNozzleDiameter2, materialPurpose2, materialRate2, materialRateUnits2, materialRetraction2, materialShellThickness2, materialTemperature2, materialType2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MaterialsCol)) {
            return false;
        }
        MaterialsCol materialsCol = (MaterialsCol) other;
        return Intrinsics.areEqual(this.materialAmount, materialsCol.materialAmount) && Intrinsics.areEqual(this.materialAmountUnits, materialsCol.materialAmountUnits) && Intrinsics.areEqual(this.materialColor, materialsCol.materialColor) && Intrinsics.areEqual(this.materialDiameter, materialsCol.materialDiameter) && Intrinsics.areEqual(this.materialDiameterTolerance, materialsCol.materialDiameterTolerance) && Intrinsics.areEqual(this.materialFillDensity, materialsCol.materialFillDensity) && Intrinsics.areEqual(this.materialKey, materialsCol.materialKey) && Intrinsics.areEqual(this.materialName, materialsCol.materialName) && Intrinsics.areEqual(this.materialNozzleDiameter, materialsCol.materialNozzleDiameter) && Intrinsics.areEqual(this.materialPurpose, materialsCol.materialPurpose) && Intrinsics.areEqual(this.materialRate, materialsCol.materialRate) && Intrinsics.areEqual(this.materialRateUnits, materialsCol.materialRateUnits) && Intrinsics.areEqual(this.materialRetraction, materialsCol.materialRetraction) && Intrinsics.areEqual(this.materialShellThickness, materialsCol.materialShellThickness) && Intrinsics.areEqual(this.materialTemperature, materialsCol.materialTemperature) && Intrinsics.areEqual(this.materialType, materialsCol.materialType);
    }

    public int hashCode() {
        Integer num = this.materialAmount;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.materialAmountUnits;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.materialColor;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num2 = this.materialDiameter;
        int iHashCode4 = (iHashCode3 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.materialDiameterTolerance;
        int iHashCode5 = (iHashCode4 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.materialFillDensity;
        int iHashCode6 = (iHashCode5 + (num4 != null ? num4.hashCode() : 0)) * 31;
        String str3 = this.materialKey;
        int iHashCode7 = (iHashCode6 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.materialName;
        int iHashCode8 = (iHashCode7 + (str4 != null ? str4.hashCode() : 0)) * 31;
        Integer num5 = this.materialNozzleDiameter;
        int iHashCode9 = (iHashCode8 + (num5 != null ? num5.hashCode() : 0)) * 31;
        List<String> list = this.materialPurpose;
        int iHashCode10 = (iHashCode9 + (list != null ? list.hashCode() : 0)) * 31;
        Integer num6 = this.materialRate;
        int iHashCode11 = (iHashCode10 + (num6 != null ? num6.hashCode() : 0)) * 31;
        String str5 = this.materialRateUnits;
        int iHashCode12 = (iHashCode11 + (str5 != null ? str5.hashCode() : 0)) * 31;
        Boolean bool = this.materialRetraction;
        int iHashCode13 = (iHashCode12 + (bool != null ? bool.hashCode() : 0)) * 31;
        Integer num7 = this.materialShellThickness;
        int iHashCode14 = (iHashCode13 + (num7 != null ? num7.hashCode() : 0)) * 31;
        IntOrIntRange intOrIntRange = this.materialTemperature;
        int iHashCode15 = (iHashCode14 + (intOrIntRange != null ? intOrIntRange.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName = this.materialType;
        return iHashCode15 + (keywordOrName != null ? keywordOrName.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public MaterialsCol(Integer num, String str, String str2, Integer num2, Integer num3, Integer num4, String str3, String str4, Integer num5, List<String> list, Integer num6, String str5, Boolean bool, Integer num7, IntOrIntRange intOrIntRange, KeywordOrName keywordOrName) {
        this.materialAmount = num;
        this.materialAmountUnits = str;
        this.materialColor = str2;
        this.materialDiameter = num2;
        this.materialDiameterTolerance = num3;
        this.materialFillDensity = num4;
        this.materialKey = str3;
        this.materialName = str4;
        this.materialNozzleDiameter = num5;
        this.materialPurpose = list;
        this.materialRate = num6;
        this.materialRateUnits = str5;
        this.materialRetraction = bool;
        this.materialShellThickness = num7;
        this.materialTemperature = intOrIntRange;
        this.materialType = keywordOrName;
    }

    public MaterialsCol(Integer num, String str, String str2, Integer num2, Integer num3, Integer num4, String str3, String str4, Integer num5, List list, Integer num6, String str5, Boolean bool, Integer num7, IntOrIntRange intOrIntRange, KeywordOrName keywordOrName, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Integer num8;
        String str6;
        String str7;
        Integer num9;
        Integer num10;
        Integer num11;
        String str8;
        String str9;
        Integer num12;
        List list2;
        Integer num13;
        String str10;
        Boolean bool2;
        Integer num14;
        IntOrIntRange intOrIntRange2;
        KeywordOrName keywordOrName2 = null;
        if ((i & 1) != 0) {
            num8 = null;
        } else {
            num8 = num;
        }
        if ((i & 2) != 0) {
            str6 = null;
        } else {
            str6 = str;
        }
        if ((i & 4) != 0) {
            str7 = null;
        } else {
            str7 = str2;
        }
        if ((i & 8) != 0) {
            num9 = null;
        } else {
            num9 = num2;
        }
        if ((i & 16) != 0) {
            num10 = null;
        } else {
            num10 = num3;
        }
        if ((i & 32) != 0) {
            num11 = null;
        } else {
            num11 = num4;
        }
        if ((i & 64) != 0) {
            str8 = null;
        } else {
            str8 = str3;
        }
        if ((i & 128) != 0) {
            str9 = null;
        } else {
            str9 = str4;
        }
        if ((i & 256) != 0) {
            num12 = null;
        } else {
            num12 = num5;
        }
        if ((i & 512) != 0) {
            list2 = null;
        } else {
            list2 = list;
        }
        if ((i & 1024) != 0) {
            num13 = null;
        } else {
            num13 = num6;
        }
        if ((i & 2048) != 0) {
            str10 = null;
        } else {
            str10 = str5;
        }
        if ((i & 4096) != 0) {
            bool2 = null;
        } else {
            bool2 = bool;
        }
        if ((i & 8192) != 0) {
            num14 = null;
        } else {
            num14 = num7;
        }
        Integer num15 = num14;
        if ((i & 16384) != 0) {
            intOrIntRange2 = null;
        } else {
            intOrIntRange2 = intOrIntRange;
        }
        if ((i & 32768) != 0) {
        } else {
            keywordOrName2 = keywordOrName;
        }
        this(num8, str6, str7, num9, num10, num11, str8, str9, num12, list2, num13, str10, bool2, num15, intOrIntRange2, keywordOrName2);
    }

    public final Integer getMaterialAmount() {
        return this.materialAmount;
    }

    public final void setMaterialAmount(Integer num) {
        this.materialAmount = num;
    }

    public final String getMaterialAmountUnits() {
        return this.materialAmountUnits;
    }

    public final void setMaterialAmountUnits(String str) {
        this.materialAmountUnits = str;
    }

    public final String getMaterialColor() {
        return this.materialColor;
    }

    public final void setMaterialColor(String str) {
        this.materialColor = str;
    }

    public final Integer getMaterialDiameter() {
        return this.materialDiameter;
    }

    public final void setMaterialDiameter(Integer num) {
        this.materialDiameter = num;
    }

    public final Integer getMaterialDiameterTolerance() {
        return this.materialDiameterTolerance;
    }

    public final void setMaterialDiameterTolerance(Integer num) {
        this.materialDiameterTolerance = num;
    }

    public final Integer getMaterialFillDensity() {
        return this.materialFillDensity;
    }

    public final void setMaterialFillDensity(Integer num) {
        this.materialFillDensity = num;
    }

    public final String getMaterialKey() {
        return this.materialKey;
    }

    public final void setMaterialKey(String str) {
        this.materialKey = str;
    }

    public final String getMaterialName() {
        return this.materialName;
    }

    public final void setMaterialName(String str) {
        this.materialName = str;
    }

    public final Integer getMaterialNozzleDiameter() {
        return this.materialNozzleDiameter;
    }

    public final void setMaterialNozzleDiameter(Integer num) {
        this.materialNozzleDiameter = num;
    }

    public final List<String> getMaterialPurpose() {
        return this.materialPurpose;
    }

    public final void setMaterialPurpose(List<String> list) {
        this.materialPurpose = list;
    }

    public final Integer getMaterialRate() {
        return this.materialRate;
    }

    public final void setMaterialRate(Integer num) {
        this.materialRate = num;
    }

    public final String getMaterialRateUnits() {
        return this.materialRateUnits;
    }

    public final void setMaterialRateUnits(String str) {
        this.materialRateUnits = str;
    }

    public final Boolean getMaterialRetraction() {
        return this.materialRetraction;
    }

    public final void setMaterialRetraction(Boolean bool) {
        this.materialRetraction = bool;
    }

    public final Integer getMaterialShellThickness() {
        return this.materialShellThickness;
    }

    public final void setMaterialShellThickness(Integer num) {
        this.materialShellThickness = num;
    }

    public final IntOrIntRange getMaterialTemperature() {
        return this.materialTemperature;
    }

    public final void setMaterialTemperature(IntOrIntRange intOrIntRange) {
        this.materialTemperature = intOrIntRange;
    }

    public final KeywordOrName getMaterialType() {
        return this.materialType;
    }

    public final void setMaterialType(KeywordOrName keywordOrName) {
        this.materialType = keywordOrName;
    }

    public MaterialsCol() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[16];
        Integer num = this.materialAmount;
        attributeArr[0] = num != null ? materialAmount.mo418of(Integer.valueOf(num.intValue())) : null;
        String str = this.materialAmountUnits;
        attributeArr[1] = str != null ? materialAmountUnits.mo418of(str) : null;
        String str2 = this.materialColor;
        attributeArr[2] = str2 != null ? materialColor.mo418of(str2) : null;
        Integer num2 = this.materialDiameter;
        attributeArr[3] = num2 != null ? materialDiameter.mo418of(Integer.valueOf(num2.intValue())) : null;
        Integer num3 = this.materialDiameterTolerance;
        attributeArr[4] = num3 != null ? materialDiameterTolerance.mo418of(Integer.valueOf(num3.intValue())) : null;
        Integer num4 = this.materialFillDensity;
        attributeArr[5] = num4 != null ? materialFillDensity.mo418of(Integer.valueOf(num4.intValue())) : null;
        String str3 = this.materialKey;
        attributeArr[6] = str3 != null ? materialKey.mo418of(str3) : null;
        String str4 = this.materialName;
        attributeArr[7] = str4 != null ? materialName.m440of(str4) : null;
        Integer num5 = this.materialNozzleDiameter;
        attributeArr[8] = num5 != null ? materialNozzleDiameter.mo418of(Integer.valueOf(num5.intValue())) : null;
        List<String> list = this.materialPurpose;
        attributeArr[9] = list != null ? materialPurpose.mo417of((Iterable<? extends String>) list) : null;
        Integer num6 = this.materialRate;
        attributeArr[10] = num6 != null ? materialRate.mo418of(Integer.valueOf(num6.intValue())) : null;
        String str5 = this.materialRateUnits;
        attributeArr[11] = str5 != null ? materialRateUnits.mo418of(str5) : null;
        Boolean bool = this.materialRetraction;
        attributeArr[12] = bool != null ? materialRetraction.mo418of(Boolean.valueOf(bool.booleanValue())) : null;
        Integer num7 = this.materialShellThickness;
        attributeArr[13] = num7 != null ? materialShellThickness.mo418of(Integer.valueOf(num7.intValue())) : null;
        IntOrIntRange intOrIntRange = this.materialTemperature;
        attributeArr[14] = intOrIntRange != null ? materialTemperature.mo418of(intOrIntRange) : null;
        KeywordOrName keywordOrName = this.materialType;
        attributeArr[15] = keywordOrName != null ? materialType.mo418of(keywordOrName) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010!\u001a\u00020\u00022\u0010\u0010\"\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030$0#H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u001b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, m1293d2 = {"Lcom/hp/jipp/model/MaterialsCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/MaterialsCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "materialAmount", "Lcom/hp/jipp/encoding/IntType;", "materialAmountUnits", "Lcom/hp/jipp/encoding/KeywordType;", "materialColor", "materialDiameter", "materialDiameterTolerance", "materialFillDensity", "materialKey", "materialName", "Lcom/hp/jipp/encoding/NameType;", "materialNozzleDiameter", "materialPurpose", "Lcom/hp/jipp/encoding/KeywordType$Set;", "materialRate", "materialRateUnits", "materialRetraction", "Lcom/hp/jipp/encoding/BooleanType;", "materialShellThickness", "materialTemperature", "Lcom/hp/jipp/encoding/IntOrIntRangeType;", "materialType", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<MaterialsCol> {
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
        public MaterialsCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Integer num = (Integer) extractOne(attributes, MaterialsCol.materialAmount);
            String str = (String) extractOne(attributes, MaterialsCol.materialAmountUnits);
            String str2 = (String) extractOne(attributes, MaterialsCol.materialColor);
            Integer num2 = (Integer) extractOne(attributes, MaterialsCol.materialDiameter);
            Integer num3 = (Integer) extractOne(attributes, MaterialsCol.materialDiameterTolerance);
            Integer num4 = (Integer) extractOne(attributes, MaterialsCol.materialFillDensity);
            String str3 = (String) extractOne(attributes, MaterialsCol.materialKey);
            Name name = (Name) extractOne(attributes, MaterialsCol.materialName);
            return new MaterialsCol(num, str, str2, num2, num3, num4, str3, name != null ? name.getValue() : null, (Integer) extractOne(attributes, MaterialsCol.materialNozzleDiameter), extractAll(attributes, MaterialsCol.materialPurpose), (Integer) extractOne(attributes, MaterialsCol.materialRate), (String) extractOne(attributes, MaterialsCol.materialRateUnits), (Boolean) extractOne(attributes, MaterialsCol.materialRetraction), (Integer) extractOne(attributes, MaterialsCol.materialShellThickness), (IntOrIntRange) extractOne(attributes, MaterialsCol.materialTemperature), (KeywordOrName) extractOne(attributes, MaterialsCol.materialType));
        }

        @Override
        public Class<MaterialsCol> getCls() {
            return MaterialsCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = MaterialsCol.class;
        Types = companion;
        materialAmount = new IntType("material-amount");
        materialAmountUnits = new KeywordType("material-amount-units");
        materialColor = new KeywordType("material-color");
        materialDiameter = new IntType("material-diameter");
        materialDiameterTolerance = new IntType("material-diameter-tolerance");
        materialFillDensity = new IntType("material-fill-density");
        materialKey = new KeywordType("material-key");
        materialName = new NameType("material-name");
        materialNozzleDiameter = new IntType("material-nozzle-diameter");
        materialPurpose = new KeywordType.Set("material-purpose");
        materialRate = new IntType("material-rate");
        materialRateUnits = new KeywordType("material-rate-units");
        materialRetraction = new BooleanType("material-retraction");
        materialShellThickness = new IntType("material-shell-thickness");
        materialTemperature = new IntOrIntRangeType("material-temperature");
        materialType = new KeywordOrNameType("material-type");
    }

    public String toString() {
        return "MaterialsCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
