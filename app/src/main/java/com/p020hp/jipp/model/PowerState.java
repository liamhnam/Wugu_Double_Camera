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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, m1293d2 = {"Lcom/hp/jipp/model/PowerState;", "Lcom/hp/jipp/encoding/Enum;", "code", "", NamingTable.TAG, "", "(ILjava/lang/String;)V", "getCode", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "toString", StandardStructureTypes.CODE, "Companion", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PowerState extends Enum {

    public static final Companion INSTANCE = new Companion(null);
    public static final Map<Integer, PowerState> all;
    public static final PowerState hibernate;
    public static final PowerState hibernateVendor1;
    public static final PowerState hibernateVendor2;
    public static final PowerState hibernateVendor3;
    public static final PowerState hibernateVendor4;
    public static final PowerState hibernateVendor5;
    public static final PowerState noChange;
    public static final PowerState notApplicable;
    public static final PowerState offHard;
    public static final PowerState offHardGraceful;
    public static final PowerState offSoft;
    public static final PowerState offSoftGraceful;
    public static final PowerState offSoftVendor1;
    public static final PowerState offSoftVendor2;
    public static final PowerState offSoftVendor3;
    public static final PowerState offSoftVendor4;
    public static final PowerState offSoftVendor5;

    public static final PowerState f732on;
    public static final PowerState onVendor1;
    public static final PowerState onVendor2;
    public static final PowerState onVendor3;
    public static final PowerState onVendor4;
    public static final PowerState onVendor5;
    public static final PowerState resetHard;
    public static final PowerState resetHardGraceful;
    public static final PowerState resetInit;
    public static final PowerState resetMbr;
    public static final PowerState resetMbrGraceful;
    public static final PowerState resetNmi;
    public static final PowerState resetSoft;
    public static final PowerState resetSoftGraceful;
    public static final PowerState standby;
    public static final PowerState standbyVendor1;
    public static final PowerState standbyVendor2;
    public static final PowerState standbyVendor3;
    public static final PowerState standbyVendor4;
    public static final PowerState standbyVendor5;
    public static final PowerState suspend;
    public static final PowerState suspendVendor1;
    public static final PowerState suspendVendor2;
    public static final PowerState suspendVendor3;
    public static final PowerState suspendVendor4;
    public static final PowerState suspendVendor5;
    private final int code;
    private final String name;

    public static PowerState copy$default(PowerState powerState, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = powerState.getCode();
        }
        if ((i2 & 2) != 0) {
            str = powerState.getName();
        }
        return powerState.copy(i, str);
    }

    public final int component1() {
        return getCode();
    }

    public final String component2() {
        return getName();
    }

    public final PowerState copy(int code, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new PowerState(code, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerState)) {
            return false;
        }
        PowerState powerState = (PowerState) other;
        return getCode() == powerState.getCode() && Intrinsics.areEqual(getName(), powerState.getName());
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(getCode()) * 31;
        String name = getName();
        return iHashCode + (name != null ? name.hashCode() : 0);
    }

    public PowerState(int i, String name) {
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

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/PowerState$Type;", "Lcom/hp/jipp/encoding/EnumType;", "Lcom/hp/jipp/model/PowerState;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type extends EnumType<PowerState> {
        public Type(String name) {
            super(name, new Function1<Integer, PowerState>() {
                public final PowerState invoke(int i) {
                    return PowerState.INSTANCE.get(i);
                }

                @Override
                public PowerState invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/PowerState$SetType;", "Lcom/hp/jipp/encoding/EnumType$Set;", "Lcom/hp/jipp/model/PowerState;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType extends EnumType.Set<PowerState> {
        public SetType(String name) {
            super(name, new Function1<Integer, PowerState>() {
                public final PowerState invoke(int i) {
                    return PowerState.INSTANCE.get(i);
                }

                @Override
                public PowerState invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b+\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006/"}, m1293d2 = {"Lcom/hp/jipp/model/PowerState$Code;", "", "()V", PrinterStateReason.hibernate, "", "hibernateVendor1", "hibernateVendor2", "hibernateVendor3", "hibernateVendor4", "hibernateVendor5", "noChange", "notApplicable", "offHard", "offHardGraceful", "offSoft", "offSoftGraceful", "offSoftVendor1", "offSoftVendor2", "offSoftVendor3", "offSoftVendor4", "offSoftVendor5", "on", "onVendor1", "onVendor2", "onVendor3", "onVendor4", "onVendor5", "resetHard", "resetHardGraceful", "resetInit", "resetMbr", "resetMbrGraceful", "resetNmi", "resetSoft", "resetSoftGraceful", PrinterStateReason.standby, "standbyVendor1", "standbyVendor2", "standbyVendor3", "standbyVendor4", "standbyVendor5", PrinterStateReason.suspend, "suspendVendor1", "suspendVendor2", "suspendVendor3", "suspendVendor4", "suspendVendor5", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Code {
        public static final Code INSTANCE = new Code();
        public static final int hibernate = 70;
        public static final int hibernateVendor1 = 71;
        public static final int hibernateVendor2 = 72;
        public static final int hibernateVendor3 = 73;
        public static final int hibernateVendor4 = 74;
        public static final int hibernateVendor5 = 75;
        public static final int noChange = 190;
        public static final int notApplicable = 180;
        public static final int offHard = 60;
        public static final int offHardGraceful = 130;
        public static final int offSoft = 80;
        public static final int offSoftGraceful = 120;
        public static final int offSoftVendor1 = 81;
        public static final int offSoftVendor2 = 82;
        public static final int offSoftVendor3 = 83;
        public static final int offSoftVendor4 = 84;
        public static final int offSoftVendor5 = 85;
        public static final int on = 20;
        public static final int onVendor1 = 21;
        public static final int onVendor2 = 22;
        public static final int onVendor3 = 23;
        public static final int onVendor4 = 24;
        public static final int onVendor5 = 25;
        public static final int resetHard = 90;
        public static final int resetHardGraceful = 160;
        public static final int resetInit = 170;
        public static final int resetMbr = 100;
        public static final int resetMbrGraceful = 140;
        public static final int resetNmi = 110;
        public static final int resetSoft = 50;
        public static final int resetSoftGraceful = 150;
        public static final int standby = 30;
        public static final int standbyVendor1 = 31;
        public static final int standbyVendor2 = 32;
        public static final int standbyVendor3 = 33;
        public static final int standbyVendor4 = 34;
        public static final int standbyVendor5 = 35;
        public static final int suspend = 40;
        public static final int suspendVendor1 = 41;
        public static final int suspendVendor2 = 42;
        public static final int suspendVendor3 = 43;
        public static final int suspendVendor4 = 44;
        public static final int suspendVendor5 = 45;

        private Code() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b.\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0005H\u0086\u0002R\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u00064"}, m1293d2 = {"Lcom/hp/jipp/model/PowerState$Companion;", "", "()V", "all", "", "", "Lcom/hp/jipp/model/PowerState;", PrinterStateReason.hibernate, "hibernateVendor1", "hibernateVendor2", "hibernateVendor3", "hibernateVendor4", "hibernateVendor5", "noChange", "notApplicable", "offHard", "offHardGraceful", "offSoft", "offSoftGraceful", "offSoftVendor1", "offSoftVendor2", "offSoftVendor3", "offSoftVendor4", "offSoftVendor5", "on", "onVendor1", "onVendor2", "onVendor3", "onVendor4", "onVendor5", "resetHard", "resetHardGraceful", "resetInit", "resetMbr", "resetMbrGraceful", "resetNmi", "resetSoft", "resetSoftGraceful", PrinterStateReason.standby, "standbyVendor1", "standbyVendor2", "standbyVendor3", "standbyVendor4", "standbyVendor5", PrinterStateReason.suspend, "suspendVendor1", "suspendVendor2", "suspendVendor3", "suspendVendor4", "suspendVendor5", "get", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PowerState get(int value) {
            PowerState powerState = PowerState.all.get(Integer.valueOf(value));
            return powerState != null ? powerState : new PowerState(value, "???");
        }
    }

    static {
        PowerState powerState = new PowerState(20, "on");
        f732on = powerState;
        PowerState powerState2 = new PowerState(21, "on-vendor1");
        onVendor1 = powerState2;
        PowerState powerState3 = new PowerState(22, "on-vendor2");
        onVendor2 = powerState3;
        PowerState powerState4 = new PowerState(23, "on-vendor3");
        onVendor3 = powerState4;
        PowerState powerState5 = new PowerState(24, "on-vendor4");
        onVendor4 = powerState5;
        PowerState powerState6 = new PowerState(25, "on-vendor5");
        onVendor5 = powerState6;
        PowerState powerState7 = new PowerState(30, PrinterStateReason.standby);
        standby = powerState7;
        PowerState powerState8 = new PowerState(31, "standby-vendor1");
        standbyVendor1 = powerState8;
        PowerState powerState9 = new PowerState(32, "standby-vendor2");
        standbyVendor2 = powerState9;
        PowerState powerState10 = new PowerState(33, "standby-vendor3");
        standbyVendor3 = powerState10;
        PowerState powerState11 = new PowerState(34, "standby-vendor4");
        standbyVendor4 = powerState11;
        PowerState powerState12 = new PowerState(35, "standby-vendor5");
        standbyVendor5 = powerState12;
        PowerState powerState13 = new PowerState(40, PrinterStateReason.suspend);
        suspend = powerState13;
        PowerState powerState14 = new PowerState(41, "suspend-vendor1");
        suspendVendor1 = powerState14;
        PowerState powerState15 = new PowerState(42, "suspend-vendor2");
        suspendVendor2 = powerState15;
        PowerState powerState16 = new PowerState(43, "suspend-vendor3");
        suspendVendor3 = powerState16;
        PowerState powerState17 = new PowerState(44, "suspend-vendor4");
        suspendVendor4 = powerState17;
        PowerState powerState18 = new PowerState(45, "suspend-vendor5");
        suspendVendor5 = powerState18;
        PowerState powerState19 = new PowerState(50, "reset-soft");
        resetSoft = powerState19;
        PowerState powerState20 = new PowerState(60, "off-hard");
        offHard = powerState20;
        PowerState powerState21 = new PowerState(70, PrinterStateReason.hibernate);
        hibernate = powerState21;
        PowerState powerState22 = new PowerState(71, "hibernate-vendor1");
        hibernateVendor1 = powerState22;
        PowerState powerState23 = new PowerState(72, "hibernate-vendor2");
        hibernateVendor2 = powerState23;
        PowerState powerState24 = new PowerState(73, "hibernate-vendor3");
        hibernateVendor3 = powerState24;
        PowerState powerState25 = new PowerState(74, "hibernate-vendor4");
        hibernateVendor4 = powerState25;
        PowerState powerState26 = new PowerState(75, "hibernate-vendor5");
        hibernateVendor5 = powerState26;
        PowerState powerState27 = new PowerState(80, "off-soft");
        offSoft = powerState27;
        PowerState powerState28 = new PowerState(81, "off-soft-vendor1");
        offSoftVendor1 = powerState28;
        PowerState powerState29 = new PowerState(82, "off-soft-vendor2");
        offSoftVendor2 = powerState29;
        PowerState powerState30 = new PowerState(83, "off-soft-vendor3");
        offSoftVendor3 = powerState30;
        PowerState powerState31 = new PowerState(84, "off-soft-vendor4");
        offSoftVendor4 = powerState31;
        PowerState powerState32 = new PowerState(85, "off-soft-vendor5");
        offSoftVendor5 = powerState32;
        PowerState powerState33 = new PowerState(90, "reset-hard");
        resetHard = powerState33;
        PowerState powerState34 = new PowerState(100, "reset-mbr");
        resetMbr = powerState34;
        PowerState powerState35 = new PowerState(110, "reset-nmi");
        resetNmi = powerState35;
        PowerState powerState36 = new PowerState(120, "off-soft-graceful");
        offSoftGraceful = powerState36;
        PowerState powerState37 = new PowerState(130, "off-hard-graceful");
        offHardGraceful = powerState37;
        PowerState powerState38 = new PowerState(Code.resetMbrGraceful, "reset-mbr-graceful");
        resetMbrGraceful = powerState38;
        PowerState powerState39 = new PowerState(Code.resetSoftGraceful, "reset-soft-graceful");
        resetSoftGraceful = powerState39;
        PowerState powerState40 = new PowerState(160, "reset-hard-graceful");
        resetHardGraceful = powerState40;
        PowerState powerState41 = new PowerState(Code.resetInit, "reset-init");
        resetInit = powerState41;
        PowerState powerState42 = new PowerState(180, InputFilmScanMode.notApplicable);
        notApplicable = powerState42;
        PowerState powerState43 = new PowerState(Code.noChange, "no-change");
        noChange = powerState43;
        List<PowerState> listListOf = CollectionsKt.listOf((Object[]) new PowerState[]{powerState, powerState2, powerState3, powerState4, powerState5, powerState6, powerState7, powerState8, powerState9, powerState10, powerState11, powerState12, powerState13, powerState14, powerState15, powerState16, powerState17, powerState18, powerState19, powerState20, powerState21, powerState22, powerState23, powerState24, powerState25, powerState26, powerState27, powerState28, powerState29, powerState30, powerState31, powerState32, powerState33, powerState34, powerState35, powerState36, powerState37, powerState38, powerState39, powerState40, powerState41, powerState42, powerState43});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (PowerState powerState44 : listListOf) {
            arrayList.add(TuplesKt.m1300to(Integer.valueOf(powerState44.getCode()), powerState44));
        }
        all = MapsKt.toMap(arrayList);
    }
}
