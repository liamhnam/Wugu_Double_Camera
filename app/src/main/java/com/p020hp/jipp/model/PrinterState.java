package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Enum;
import com.p020hp.jipp.encoding.EnumType;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterState;", "Lcom/hp/jipp/encoding/Enum;", "code", "", NamingTable.TAG, "", "(ILjava/lang/String;)V", "getCode", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "toString", StandardStructureTypes.CODE, "Companion", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PrinterState extends Enum {

    public static final Companion INSTANCE = new Companion(null);
    public static final Map<Integer, PrinterState> all;
    public static final PrinterState idle;
    public static final PrinterState processing;
    public static final PrinterState stopped;
    private final int code;
    private final String name;

    public static PrinterState copy$default(PrinterState printerState, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = printerState.getCode();
        }
        if ((i2 & 2) != 0) {
            str = printerState.getName();
        }
        return printerState.copy(i, str);
    }

    public final int component1() {
        return getCode();
    }

    public final String component2() {
        return getName();
    }

    public final PrinterState copy(int code, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new PrinterState(code, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrinterState)) {
            return false;
        }
        PrinterState printerState = (PrinterState) other;
        return getCode() == printerState.getCode() && Intrinsics.areEqual(getName(), printerState.getName());
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(getCode()) * 31;
        String name = getName();
        return iHashCode + (name != null ? name.hashCode() : 0);
    }

    public PrinterState(int i, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.code = i;
        this.name = name;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterState$Type;", "Lcom/hp/jipp/encoding/EnumType;", "Lcom/hp/jipp/model/PrinterState;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type extends EnumType<PrinterState> {
        public Type(String name) {
            super(name, new Function1<Integer, PrinterState>() {
                public final PrinterState invoke(int i) {
                    return PrinterState.INSTANCE.get(i);
                }

                @Override
                public PrinterState invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterState$SetType;", "Lcom/hp/jipp/encoding/EnumType$Set;", "Lcom/hp/jipp/model/PrinterState;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType extends EnumType.Set<PrinterState> {
        public SetType(String name) {
            super(name, new Function1<Integer, PrinterState>() {
                public final PrinterState invoke(int i) {
                    return PrinterState.INSTANCE.get(i);
                }

                @Override
                public PrinterState invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterState$Code;", "", "()V", WhichPrinter.idle, "", "processing", WhichPrinter.stopped, "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Code {
        public static final Code INSTANCE = new Code();
        public static final int idle = 3;
        public static final int processing = 4;
        public static final int stopped = 5;

        private Code() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0005H\u0086\u0002R\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m1293d2 = {"Lcom/hp/jipp/model/PrinterState$Companion;", "", "()V", "all", "", "", "Lcom/hp/jipp/model/PrinterState;", WhichPrinter.idle, "processing", WhichPrinter.stopped, "get", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PrinterState get(int value) {
            PrinterState printerState = PrinterState.all.get(Integer.valueOf(value));
            return printerState != null ? printerState : new PrinterState(value, "???");
        }
    }

    static {
        PrinterState printerState = new PrinterState(3, WhichPrinter.idle);
        idle = printerState;
        PrinterState printerState2 = new PrinterState(4, "processing");
        processing = printerState2;
        PrinterState printerState3 = new PrinterState(5, WhichPrinter.stopped);
        stopped = printerState3;
        List<PrinterState> listListOf = CollectionsKt.listOf((Object[]) new PrinterState[]{printerState, printerState2, printerState3});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (PrinterState printerState4 : listListOf) {
            arrayList.add(TuplesKt.m1300to(Integer.valueOf(printerState4.getCode()), printerState4));
        }
        all = MapsKt.toMap(arrayList);
    }
}
