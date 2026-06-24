package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.BooleanType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.model.PrinterState;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 J2\u00020\u0001:\u0001JB\u0007\b\u0016¢\u0006\u0002\u0010\u0002Bk\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\u0010\u0010;\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u0010<\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\"J\u000b\u0010>\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0011\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000eHÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0010HÆ\u0003Jt\u0010C\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u0010DJ\u0013\u0010E\u001a\u00020\b2\b\u0010F\u001a\u0004\u0018\u00010GHÖ\u0003J\t\u0010H\u001a\u00020\u0004HÖ\u0001J\b\u0010I\u001a\u00020\u0006H\u0016R\u001e\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR \u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b)\u001a\u0004\b'\u0010\u001d\"\u0004\b(\u0010\u001fR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b,\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\b1\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\b5\u001a\u0004\b2\u0010\u0015\"\u0004\b3\u00104R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\b:\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006K"}, m1293d2 = {"Lcom/hp/jipp/model/SystemConfiguredPrinters;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "printerId", "", "printerInfo", "", "printerIsAcceptingJobs", "", "printerName", "printerServiceType", "printerState", "Lcom/hp/jipp/model/PrinterState;", "printerStateReasons", "", "printerXriSupported", "Lcom/hp/jipp/model/PrinterXriSupported;", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lcom/hp/jipp/model/PrinterState;Ljava/util/List;Lcom/hp/jipp/model/PrinterXriSupported;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getPrinterId", "()Ljava/lang/Integer;", "setPrinterId", "(Ljava/lang/Integer;)V", "printerId$1", "Ljava/lang/Integer;", "getPrinterInfo", "()Ljava/lang/String;", "setPrinterInfo", "(Ljava/lang/String;)V", "printerInfo$1", "getPrinterIsAcceptingJobs", "()Ljava/lang/Boolean;", "setPrinterIsAcceptingJobs", "(Ljava/lang/Boolean;)V", "printerIsAcceptingJobs$1", "Ljava/lang/Boolean;", "getPrinterName", "setPrinterName", "printerName$1", "getPrinterServiceType", "setPrinterServiceType", "printerServiceType$1", "getPrinterState", "()Lcom/hp/jipp/model/PrinterState;", "setPrinterState", "(Lcom/hp/jipp/model/PrinterState;)V", "printerState$1", "getPrinterStateReasons", "setPrinterStateReasons", "(Ljava/util/List;)V", "printerStateReasons$1", "getPrinterXriSupported", "()Lcom/hp/jipp/model/PrinterXriSupported;", "setPrinterXriSupported", "(Lcom/hp/jipp/model/PrinterXriSupported;)V", "printerXriSupported$1", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lcom/hp/jipp/model/PrinterState;Ljava/util/List;Lcom/hp/jipp/model/PrinterXriSupported;)Lcom/hp/jipp/model/SystemConfiguredPrinters;", "equals", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class SystemConfiguredPrinters implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<SystemConfiguredPrinters> cls;
    public static final IntType printerId;
    public static final TextType printerInfo;
    public static final BooleanType printerIsAcceptingJobs;
    public static final NameType printerName;
    public static final KeywordType printerServiceType;
    public static final PrinterState.Type printerState;
    public static final KeywordType.Set printerStateReasons;
    public static final AttributeCollection.Type<PrinterXriSupported> printerXriSupported;

    private Integer printerId;

    private String printerInfo;

    private Boolean printerIsAcceptingJobs;

    private String printerName;

    private String printerServiceType;

    private PrinterState printerState;

    private List<String> printerStateReasons;

    private PrinterXriSupported printerXriSupported;

    public final Integer getPrinterId() {
        return this.printerId;
    }

    public final String getPrinterInfo() {
        return this.printerInfo;
    }

    public final Boolean getPrinterIsAcceptingJobs() {
        return this.printerIsAcceptingJobs;
    }

    public final String getPrinterName() {
        return this.printerName;
    }

    public final String getPrinterServiceType() {
        return this.printerServiceType;
    }

    public final PrinterState getPrinterState() {
        return this.printerState;
    }

    public final List<String> component7() {
        return this.printerStateReasons;
    }

    public final PrinterXriSupported getPrinterXriSupported() {
        return this.printerXriSupported;
    }

    public final SystemConfiguredPrinters copy(Integer printerId2, String printerInfo2, Boolean printerIsAcceptingJobs2, String printerName2, String printerServiceType2, PrinterState printerState2, List<String> printerStateReasons2, PrinterXriSupported printerXriSupported2) {
        return new SystemConfiguredPrinters(printerId2, printerInfo2, printerIsAcceptingJobs2, printerName2, printerServiceType2, printerState2, printerStateReasons2, printerXriSupported2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SystemConfiguredPrinters)) {
            return false;
        }
        SystemConfiguredPrinters systemConfiguredPrinters = (SystemConfiguredPrinters) other;
        return Intrinsics.areEqual(this.printerId, systemConfiguredPrinters.printerId) && Intrinsics.areEqual(this.printerInfo, systemConfiguredPrinters.printerInfo) && Intrinsics.areEqual(this.printerIsAcceptingJobs, systemConfiguredPrinters.printerIsAcceptingJobs) && Intrinsics.areEqual(this.printerName, systemConfiguredPrinters.printerName) && Intrinsics.areEqual(this.printerServiceType, systemConfiguredPrinters.printerServiceType) && Intrinsics.areEqual(this.printerState, systemConfiguredPrinters.printerState) && Intrinsics.areEqual(this.printerStateReasons, systemConfiguredPrinters.printerStateReasons) && Intrinsics.areEqual(this.printerXriSupported, systemConfiguredPrinters.printerXriSupported);
    }

    public int hashCode() {
        Integer num = this.printerId;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.printerInfo;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        Boolean bool = this.printerIsAcceptingJobs;
        int iHashCode3 = (iHashCode2 + (bool != null ? bool.hashCode() : 0)) * 31;
        String str2 = this.printerName;
        int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.printerServiceType;
        int iHashCode5 = (iHashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        PrinterState printerState2 = this.printerState;
        int iHashCode6 = (iHashCode5 + (printerState2 != null ? printerState2.hashCode() : 0)) * 31;
        List<String> list = this.printerStateReasons;
        int iHashCode7 = (iHashCode6 + (list != null ? list.hashCode() : 0)) * 31;
        PrinterXriSupported printerXriSupported2 = this.printerXriSupported;
        return iHashCode7 + (printerXriSupported2 != null ? printerXriSupported2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public SystemConfiguredPrinters(Integer num, String str, Boolean bool, String str2, String str3, PrinterState printerState2, List<String> list, PrinterXriSupported printerXriSupported2) {
        this.printerId = num;
        this.printerInfo = str;
        this.printerIsAcceptingJobs = bool;
        this.printerName = str2;
        this.printerServiceType = str3;
        this.printerState = printerState2;
        this.printerStateReasons = list;
        this.printerXriSupported = printerXriSupported2;
    }

    public SystemConfiguredPrinters(Integer num, String str, Boolean bool, String str2, String str3, PrinterState printerState2, List list, PrinterXriSupported printerXriSupported2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Integer num2;
        String str4;
        Boolean bool2;
        String str5;
        String str6;
        PrinterState printerState3;
        List list2;
        PrinterXriSupported printerXriSupported3 = null;
        if ((i & 1) != 0) {
            num2 = null;
        } else {
            num2 = num;
        }
        if ((i & 2) != 0) {
            str4 = null;
        } else {
            str4 = str;
        }
        if ((i & 4) != 0) {
            bool2 = null;
        } else {
            bool2 = bool;
        }
        if ((i & 8) != 0) {
            str5 = null;
        } else {
            str5 = str2;
        }
        if ((i & 16) != 0) {
            str6 = null;
        } else {
            str6 = str3;
        }
        if ((i & 32) != 0) {
            printerState3 = null;
        } else {
            printerState3 = printerState2;
        }
        if ((i & 64) != 0) {
            list2 = null;
        } else {
            list2 = list;
        }
        if ((i & 128) != 0) {
        } else {
            printerXriSupported3 = printerXriSupported2;
        }
        this(num2, str4, bool2, str5, str6, printerState3, list2, printerXriSupported3);
    }

    public final Integer getPrinterId() {
        return this.printerId;
    }

    public final void setPrinterId(Integer num) {
        this.printerId = num;
    }

    public final String getPrinterInfo() {
        return this.printerInfo;
    }

    public final void setPrinterInfo(String str) {
        this.printerInfo = str;
    }

    public final Boolean getPrinterIsAcceptingJobs() {
        return this.printerIsAcceptingJobs;
    }

    public final void setPrinterIsAcceptingJobs(Boolean bool) {
        this.printerIsAcceptingJobs = bool;
    }

    public final String getPrinterName() {
        return this.printerName;
    }

    public final void setPrinterName(String str) {
        this.printerName = str;
    }

    public final String getPrinterServiceType() {
        return this.printerServiceType;
    }

    public final void setPrinterServiceType(String str) {
        this.printerServiceType = str;
    }

    public final PrinterState getPrinterState() {
        return this.printerState;
    }

    public final void setPrinterState(PrinterState printerState2) {
        this.printerState = printerState2;
    }

    public final List<String> getPrinterStateReasons() {
        return this.printerStateReasons;
    }

    public final void setPrinterStateReasons(List<String> list) {
        this.printerStateReasons = list;
    }

    public final PrinterXriSupported getPrinterXriSupported() {
        return this.printerXriSupported;
    }

    public final void setPrinterXriSupported(PrinterXriSupported printerXriSupported2) {
        this.printerXriSupported = printerXriSupported2;
    }

    public SystemConfiguredPrinters() {
        this(null, null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[8];
        Integer num = this.printerId;
        attributeArr[0] = num != null ? printerId.mo418of(Integer.valueOf(num.intValue())) : null;
        String str = this.printerInfo;
        attributeArr[1] = str != null ? printerInfo.m442of(str) : null;
        Boolean bool = this.printerIsAcceptingJobs;
        attributeArr[2] = bool != null ? printerIsAcceptingJobs.mo418of(Boolean.valueOf(bool.booleanValue())) : null;
        String str2 = this.printerName;
        attributeArr[3] = str2 != null ? printerName.m440of(str2) : null;
        String str3 = this.printerServiceType;
        attributeArr[4] = str3 != null ? printerServiceType.mo418of(str3) : null;
        PrinterState printerState2 = this.printerState;
        attributeArr[5] = printerState2 != null ? printerState.mo418of((Object) printerState2) : null;
        List<String> list = this.printerStateReasons;
        attributeArr[6] = list != null ? printerStateReasons.mo417of((Iterable<? extends String>) list) : null;
        PrinterXriSupported printerXriSupported2 = this.printerXriSupported;
        attributeArr[7] = printerXriSupported2 != null ? printerXriSupported.mo418of(printerXriSupported2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u001b\u001a\u00020\u00022\u0010\u0010\u001c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e0\u001dH\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m1293d2 = {"Lcom/hp/jipp/model/SystemConfiguredPrinters$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/SystemConfiguredPrinters;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "printerId", "Lcom/hp/jipp/encoding/IntType;", "printerInfo", "Lcom/hp/jipp/encoding/TextType;", "printerIsAcceptingJobs", "Lcom/hp/jipp/encoding/BooleanType;", "printerName", "Lcom/hp/jipp/encoding/NameType;", "printerServiceType", "Lcom/hp/jipp/encoding/KeywordType;", "printerState", "Lcom/hp/jipp/model/PrinterState$Type;", "printerStateReasons", "Lcom/hp/jipp/encoding/KeywordType$Set;", "printerXriSupported", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/PrinterXriSupported;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<SystemConfiguredPrinters> {
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
        public SystemConfiguredPrinters convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Integer num = (Integer) extractOne(attributes, SystemConfiguredPrinters.printerId);
            Text text = (Text) extractOne(attributes, SystemConfiguredPrinters.printerInfo);
            String value = text != null ? text.getValue() : null;
            Boolean bool = (Boolean) extractOne(attributes, SystemConfiguredPrinters.printerIsAcceptingJobs);
            Name name = (Name) extractOne(attributes, SystemConfiguredPrinters.printerName);
            return new SystemConfiguredPrinters(num, value, bool, name != null ? name.getValue() : null, (String) extractOne(attributes, SystemConfiguredPrinters.printerServiceType), (PrinterState) extractOne(attributes, SystemConfiguredPrinters.printerState), extractAll(attributes, SystemConfiguredPrinters.printerStateReasons), (PrinterXriSupported) extractOne(attributes, SystemConfiguredPrinters.printerXriSupported));
        }

        @Override
        public Class<SystemConfiguredPrinters> getCls() {
            return SystemConfiguredPrinters.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = SystemConfiguredPrinters.class;
        Types = companion;
        printerId = new IntType("printer-id");
        printerInfo = new TextType("printer-info");
        printerIsAcceptingJobs = new BooleanType("printer-is-accepting-jobs");
        printerName = new NameType("printer-name");
        printerServiceType = new KeywordType("printer-service-type");
        printerState = new PrinterState.Type("printer-state");
        printerStateReasons = new KeywordType.Set("printer-state-reasons");
        printerXriSupported = new AttributeCollection.Type<>("printer-xri-supported", PrinterXriSupported.INSTANCE);
    }

    public String toString() {
        return "SystemConfiguredPrinters(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
