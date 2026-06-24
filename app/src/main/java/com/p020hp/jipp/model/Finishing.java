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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, m1293d2 = {"Lcom/hp/jipp/model/Finishing;", "Lcom/hp/jipp/encoding/Enum;", "code", "", NamingTable.TAG, "", "(ILjava/lang/String;)V", "getCode", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "toString", StandardStructureTypes.CODE, "Companion", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class Finishing extends Enum {

    public static final Companion INSTANCE = new Companion(null);
    public static final Map<Integer, Finishing> all;
    public static final Finishing bale;
    public static final Finishing bind;
    public static final Finishing bindBottom;
    public static final Finishing bindLeft;
    public static final Finishing bindRight;
    public static final Finishing bindTop;
    public static final Finishing bookletMaker;
    public static final Finishing coat;
    public static final Finishing cover;
    public static final Finishing edgeStitch;
    public static final Finishing edgeStitchBottom;
    public static final Finishing edgeStitchLeft;
    public static final Finishing edgeStitchRight;
    public static final Finishing edgeStitchTop;
    public static final Finishing fold;
    public static final Finishing foldAccordion;
    public static final Finishing foldDoubleGate;
    public static final Finishing foldEngineeringZ;
    public static final Finishing foldGate;
    public static final Finishing foldHalf;
    public static final Finishing foldHalfZ;
    public static final Finishing foldLeftGate;
    public static final Finishing foldLetter;
    public static final Finishing foldParallel;
    public static final Finishing foldPoster;
    public static final Finishing foldRightGate;
    public static final Finishing foldZ;
    public static final Finishing jogOffset;
    public static final Finishing laminate;
    public static final Finishing none;
    public static final Finishing punch;
    public static final Finishing punchBottomLeft;
    public static final Finishing punchBottomRight;
    public static final Finishing punchDualBottom;
    public static final Finishing punchDualLeft;
    public static final Finishing punchDualRight;
    public static final Finishing punchDualTop;
    public static final Finishing punchMultipleBottom;
    public static final Finishing punchMultipleLeft;
    public static final Finishing punchMultipleRight;
    public static final Finishing punchMultipleTop;
    public static final Finishing punchQuadBottom;
    public static final Finishing punchQuadLeft;
    public static final Finishing punchQuadRight;
    public static final Finishing punchQuadTop;
    public static final Finishing punchTopLeft;
    public static final Finishing punchTopRight;
    public static final Finishing punchTripleBottom;
    public static final Finishing punchTripleLeft;
    public static final Finishing punchTripleRight;
    public static final Finishing punchTripleTop;
    public static final Finishing saddleStitch;
    public static final Finishing staple;
    public static final Finishing stapleBottomLeft;
    public static final Finishing stapleBottomRight;
    public static final Finishing stapleDualBottom;
    public static final Finishing stapleDualLeft;
    public static final Finishing stapleDualRight;
    public static final Finishing stapleDualTop;
    public static final Finishing stapleTopLeft;
    public static final Finishing stapleTopRight;
    public static final Finishing stapleTripleBottom;
    public static final Finishing stapleTripleLeft;
    public static final Finishing stapleTripleRight;
    public static final Finishing stapleTripleTop;
    public static final Finishing trim;
    public static final Finishing trimAfterCopies;
    public static final Finishing trimAfterDocuments;
    public static final Finishing trimAfterJob;
    public static final Finishing trimAfterPages;
    private final int code;
    private final String name;

    public static Finishing copy$default(Finishing finishing, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = finishing.getCode();
        }
        if ((i2 & 2) != 0) {
            str = finishing.getName();
        }
        return finishing.copy(i, str);
    }

    public final int component1() {
        return getCode();
    }

    public final String component2() {
        return getName();
    }

    public final Finishing copy(int code, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Finishing(code, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Finishing)) {
            return false;
        }
        Finishing finishing = (Finishing) other;
        return getCode() == finishing.getCode() && Intrinsics.areEqual(getName(), finishing.getName());
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(getCode()) * 31;
        String name = getName();
        return iHashCode + (name != null ? name.hashCode() : 0);
    }

    public Finishing(int i, String name) {
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

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/Finishing$Type;", "Lcom/hp/jipp/encoding/EnumType;", "Lcom/hp/jipp/model/Finishing;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type extends EnumType<Finishing> {
        public Type(String name) {
            super(name, new Function1<Integer, Finishing>() {
                public final Finishing invoke(int i) {
                    return Finishing.INSTANCE.get(i);
                }

                @Override
                public Finishing invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/Finishing$SetType;", "Lcom/hp/jipp/encoding/EnumType$Set;", "Lcom/hp/jipp/model/Finishing;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType extends EnumType.Set<Finishing> {
        public SetType(String name) {
            super(name, new Function1<Integer, Finishing>() {
                public final Finishing invoke(int i) {
                    return Finishing.INSTANCE.get(i);
                }

                @Override
                public Finishing invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\bF\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006J"}, m1293d2 = {"Lcom/hp/jipp/model/Finishing$Code;", "", "()V", FinishingTemplate.bale, "", FinishingTemplate.bind, "bindBottom", "bindLeft", "bindRight", "bindTop", "bookletMaker", FinishingTemplate.coat, FinishingTemplate.cover, "edgeStitch", "edgeStitchBottom", "edgeStitchLeft", "edgeStitchRight", "edgeStitchTop", FinishingTemplate.fold, "foldAccordion", "foldDoubleGate", "foldEngineeringZ", "foldGate", "foldHalf", "foldHalfZ", "foldLeftGate", "foldLetter", "foldParallel", "foldPoster", "foldRightGate", "foldZ", "jogOffset", FinishingTemplate.laminate, "none", FinishingTemplate.punch, "punchBottomLeft", "punchBottomRight", "punchDualBottom", "punchDualLeft", "punchDualRight", "punchDualTop", "punchMultipleBottom", "punchMultipleLeft", "punchMultipleRight", "punchMultipleTop", "punchQuadBottom", "punchQuadLeft", "punchQuadRight", "punchQuadTop", "punchTopLeft", "punchTopRight", "punchTripleBottom", "punchTripleLeft", "punchTripleRight", "punchTripleTop", "saddleStitch", FinishingTemplate.staple, "stapleBottomLeft", "stapleBottomRight", "stapleDualBottom", "stapleDualLeft", "stapleDualRight", "stapleDualTop", "stapleTopLeft", "stapleTopRight", "stapleTripleBottom", "stapleTripleLeft", "stapleTripleRight", "stapleTripleTop", FinishingTemplate.trim, "trimAfterCopies", "trimAfterDocuments", "trimAfterJob", "trimAfterPages", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Code {
        public static final Code INSTANCE = new Code();
        public static final int bale = 12;
        public static final int bind = 7;
        public static final int bindBottom = 53;
        public static final int bindLeft = 50;
        public static final int bindRight = 52;
        public static final int bindTop = 51;
        public static final int bookletMaker = 13;
        public static final int coat = 15;
        public static final int cover = 6;
        public static final int edgeStitch = 9;
        public static final int edgeStitchBottom = 27;
        public static final int edgeStitchLeft = 24;
        public static final int edgeStitchRight = 26;
        public static final int edgeStitchTop = 25;
        public static final int fold = 10;
        public static final int foldAccordion = 90;
        public static final int foldDoubleGate = 91;
        public static final int foldEngineeringZ = 101;
        public static final int foldGate = 92;
        public static final int foldHalf = 93;
        public static final int foldHalfZ = 94;
        public static final int foldLeftGate = 95;
        public static final int foldLetter = 96;
        public static final int foldParallel = 97;
        public static final int foldPoster = 98;
        public static final int foldRightGate = 99;
        public static final int foldZ = 100;
        public static final int jogOffset = 14;
        public static final int laminate = 16;
        public static final int none = 3;
        public static final int punch = 5;
        public static final int punchBottomLeft = 71;
        public static final int punchBottomRight = 73;
        public static final int punchDualBottom = 77;
        public static final int punchDualLeft = 74;
        public static final int punchDualRight = 76;
        public static final int punchDualTop = 75;
        public static final int punchMultipleBottom = 89;
        public static final int punchMultipleLeft = 86;
        public static final int punchMultipleRight = 88;
        public static final int punchMultipleTop = 87;
        public static final int punchQuadBottom = 85;
        public static final int punchQuadLeft = 82;
        public static final int punchQuadRight = 84;
        public static final int punchQuadTop = 83;
        public static final int punchTopLeft = 70;
        public static final int punchTopRight = 72;
        public static final int punchTripleBottom = 81;
        public static final int punchTripleLeft = 78;
        public static final int punchTripleRight = 80;
        public static final int punchTripleTop = 79;
        public static final int saddleStitch = 8;
        public static final int staple = 4;
        public static final int stapleBottomLeft = 21;
        public static final int stapleBottomRight = 23;
        public static final int stapleDualBottom = 31;
        public static final int stapleDualLeft = 28;
        public static final int stapleDualRight = 30;
        public static final int stapleDualTop = 29;
        public static final int stapleTopLeft = 20;
        public static final int stapleTopRight = 22;
        public static final int stapleTripleBottom = 35;
        public static final int stapleTripleLeft = 32;
        public static final int stapleTripleRight = 34;
        public static final int stapleTripleTop = 33;
        public static final int trim = 11;
        public static final int trimAfterCopies = 62;
        public static final int trimAfterDocuments = 61;
        public static final int trimAfterJob = 63;
        public static final int trimAfterPages = 60;

        private Code() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\bI\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u0005H\u0086\u0002R\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010H\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010I\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010K\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006O"}, m1293d2 = {"Lcom/hp/jipp/model/Finishing$Companion;", "", "()V", "all", "", "", "Lcom/hp/jipp/model/Finishing;", FinishingTemplate.bale, FinishingTemplate.bind, "bindBottom", "bindLeft", "bindRight", "bindTop", "bookletMaker", FinishingTemplate.coat, FinishingTemplate.cover, "edgeStitch", "edgeStitchBottom", "edgeStitchLeft", "edgeStitchRight", "edgeStitchTop", FinishingTemplate.fold, "foldAccordion", "foldDoubleGate", "foldEngineeringZ", "foldGate", "foldHalf", "foldHalfZ", "foldLeftGate", "foldLetter", "foldParallel", "foldPoster", "foldRightGate", "foldZ", "jogOffset", FinishingTemplate.laminate, "none", FinishingTemplate.punch, "punchBottomLeft", "punchBottomRight", "punchDualBottom", "punchDualLeft", "punchDualRight", "punchDualTop", "punchMultipleBottom", "punchMultipleLeft", "punchMultipleRight", "punchMultipleTop", "punchQuadBottom", "punchQuadLeft", "punchQuadRight", "punchQuadTop", "punchTopLeft", "punchTopRight", "punchTripleBottom", "punchTripleLeft", "punchTripleRight", "punchTripleTop", "saddleStitch", FinishingTemplate.staple, "stapleBottomLeft", "stapleBottomRight", "stapleDualBottom", "stapleDualLeft", "stapleDualRight", "stapleDualTop", "stapleTopLeft", "stapleTopRight", "stapleTripleBottom", "stapleTripleLeft", "stapleTripleRight", "stapleTripleTop", FinishingTemplate.trim, "trimAfterCopies", "trimAfterDocuments", "trimAfterJob", "trimAfterPages", "get", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Finishing get(int value) {
            Finishing finishing = Finishing.all.get(Integer.valueOf(value));
            return finishing != null ? finishing : new Finishing(value, "???");
        }
    }

    static {
        Finishing finishing = new Finishing(3, "none");
        none = finishing;
        Finishing finishing2 = new Finishing(4, FinishingTemplate.staple);
        staple = finishing2;
        Finishing finishing3 = new Finishing(5, FinishingTemplate.punch);
        punch = finishing3;
        Finishing finishing4 = new Finishing(6, FinishingTemplate.cover);
        cover = finishing4;
        Finishing finishing5 = new Finishing(7, FinishingTemplate.bind);
        bind = finishing5;
        Finishing finishing6 = new Finishing(8, FinishingTemplate.saddleStitch);
        saddleStitch = finishing6;
        Finishing finishing7 = new Finishing(9, FinishingTemplate.edgeStitch);
        edgeStitch = finishing7;
        Finishing finishing8 = new Finishing(10, FinishingTemplate.fold);
        fold = finishing8;
        Finishing finishing9 = new Finishing(11, FinishingTemplate.trim);
        trim = finishing9;
        Finishing finishing10 = new Finishing(12, FinishingTemplate.bale);
        bale = finishing10;
        Finishing finishing11 = new Finishing(13, FinishingTemplate.bookletMaker);
        bookletMaker = finishing11;
        Finishing finishing12 = new Finishing(14, FinishingTemplate.jogOffset);
        jogOffset = finishing12;
        Finishing finishing13 = new Finishing(15, FinishingTemplate.coat);
        coat = finishing13;
        Finishing finishing14 = new Finishing(16, FinishingTemplate.laminate);
        laminate = finishing14;
        Finishing finishing15 = new Finishing(20, FinishingTemplate.stapleTopLeft);
        stapleTopLeft = finishing15;
        Finishing finishing16 = new Finishing(21, FinishingTemplate.stapleBottomLeft);
        stapleBottomLeft = finishing16;
        Finishing finishing17 = new Finishing(22, FinishingTemplate.stapleTopRight);
        stapleTopRight = finishing17;
        Finishing finishing18 = new Finishing(23, FinishingTemplate.stapleBottomRight);
        stapleBottomRight = finishing18;
        Finishing finishing19 = new Finishing(24, FinishingTemplate.edgeStitchLeft);
        edgeStitchLeft = finishing19;
        Finishing finishing20 = new Finishing(25, FinishingTemplate.edgeStitchTop);
        edgeStitchTop = finishing20;
        Finishing finishing21 = new Finishing(26, FinishingTemplate.edgeStitchRight);
        edgeStitchRight = finishing21;
        Finishing finishing22 = new Finishing(27, FinishingTemplate.edgeStitchBottom);
        edgeStitchBottom = finishing22;
        Finishing finishing23 = new Finishing(28, FinishingTemplate.stapleDualLeft);
        stapleDualLeft = finishing23;
        Finishing finishing24 = new Finishing(29, FinishingTemplate.stapleDualTop);
        stapleDualTop = finishing24;
        Finishing finishing25 = new Finishing(30, FinishingTemplate.stapleDualRight);
        stapleDualRight = finishing25;
        Finishing finishing26 = new Finishing(31, FinishingTemplate.stapleDualBottom);
        stapleDualBottom = finishing26;
        Finishing finishing27 = new Finishing(32, FinishingTemplate.stapleTripleLeft);
        stapleTripleLeft = finishing27;
        Finishing finishing28 = new Finishing(33, FinishingTemplate.stapleTripleTop);
        stapleTripleTop = finishing28;
        Finishing finishing29 = new Finishing(34, FinishingTemplate.stapleTripleRight);
        stapleTripleRight = finishing29;
        Finishing finishing30 = new Finishing(35, FinishingTemplate.stapleTripleBottom);
        stapleTripleBottom = finishing30;
        Finishing finishing31 = new Finishing(50, FinishingTemplate.bindLeft);
        bindLeft = finishing31;
        Finishing finishing32 = new Finishing(51, FinishingTemplate.bindTop);
        bindTop = finishing32;
        Finishing finishing33 = new Finishing(52, FinishingTemplate.bindRight);
        bindRight = finishing33;
        Finishing finishing34 = new Finishing(53, FinishingTemplate.bindBottom);
        bindBottom = finishing34;
        Finishing finishing35 = new Finishing(60, FinishingTemplate.trimAfterPages);
        trimAfterPages = finishing35;
        Finishing finishing36 = new Finishing(61, FinishingTemplate.trimAfterDocuments);
        trimAfterDocuments = finishing36;
        Finishing finishing37 = new Finishing(62, FinishingTemplate.trimAfterCopies);
        trimAfterCopies = finishing37;
        Finishing finishing38 = new Finishing(63, FinishingTemplate.trimAfterJob);
        trimAfterJob = finishing38;
        Finishing finishing39 = new Finishing(70, FinishingTemplate.punchTopLeft);
        punchTopLeft = finishing39;
        Finishing finishing40 = new Finishing(71, FinishingTemplate.punchBottomLeft);
        punchBottomLeft = finishing40;
        Finishing finishing41 = new Finishing(72, FinishingTemplate.punchTopRight);
        punchTopRight = finishing41;
        Finishing finishing42 = new Finishing(73, FinishingTemplate.punchBottomRight);
        punchBottomRight = finishing42;
        Finishing finishing43 = new Finishing(74, FinishingTemplate.punchDualLeft);
        punchDualLeft = finishing43;
        Finishing finishing44 = new Finishing(75, FinishingTemplate.punchDualTop);
        punchDualTop = finishing44;
        Finishing finishing45 = new Finishing(76, FinishingTemplate.punchDualRight);
        punchDualRight = finishing45;
        Finishing finishing46 = new Finishing(77, FinishingTemplate.punchDualBottom);
        punchDualBottom = finishing46;
        Finishing finishing47 = new Finishing(78, FinishingTemplate.punchTripleLeft);
        punchTripleLeft = finishing47;
        Finishing finishing48 = new Finishing(79, FinishingTemplate.punchTripleTop);
        punchTripleTop = finishing48;
        Finishing finishing49 = new Finishing(80, FinishingTemplate.punchTripleRight);
        punchTripleRight = finishing49;
        Finishing finishing50 = new Finishing(81, FinishingTemplate.punchTripleBottom);
        punchTripleBottom = finishing50;
        Finishing finishing51 = new Finishing(82, FinishingTemplate.punchQuadLeft);
        punchQuadLeft = finishing51;
        Finishing finishing52 = new Finishing(83, FinishingTemplate.punchQuadTop);
        punchQuadTop = finishing52;
        Finishing finishing53 = new Finishing(84, FinishingTemplate.punchQuadRight);
        punchQuadRight = finishing53;
        Finishing finishing54 = new Finishing(85, FinishingTemplate.punchQuadBottom);
        punchQuadBottom = finishing54;
        Finishing finishing55 = new Finishing(86, FinishingTemplate.punchMultipleLeft);
        punchMultipleLeft = finishing55;
        Finishing finishing56 = new Finishing(87, FinishingTemplate.punchMultipleTop);
        punchMultipleTop = finishing56;
        Finishing finishing57 = new Finishing(88, FinishingTemplate.punchMultipleRight);
        punchMultipleRight = finishing57;
        Finishing finishing58 = new Finishing(89, FinishingTemplate.punchMultipleBottom);
        punchMultipleBottom = finishing58;
        Finishing finishing59 = new Finishing(90, FinishingTemplate.foldAccordion);
        foldAccordion = finishing59;
        Finishing finishing60 = new Finishing(91, FinishingTemplate.foldDoubleGate);
        foldDoubleGate = finishing60;
        Finishing finishing61 = new Finishing(92, FinishingTemplate.foldGate);
        foldGate = finishing61;
        Finishing finishing62 = new Finishing(93, FinishingTemplate.foldHalf);
        foldHalf = finishing62;
        Finishing finishing63 = new Finishing(94, FinishingTemplate.foldHalfZ);
        foldHalfZ = finishing63;
        Finishing finishing64 = new Finishing(95, FinishingTemplate.foldLeftGate);
        foldLeftGate = finishing64;
        Finishing finishing65 = new Finishing(96, FinishingTemplate.foldLetter);
        foldLetter = finishing65;
        Finishing finishing66 = new Finishing(97, FinishingTemplate.foldParallel);
        foldParallel = finishing66;
        Finishing finishing67 = new Finishing(98, FinishingTemplate.foldPoster);
        foldPoster = finishing67;
        Finishing finishing68 = new Finishing(99, FinishingTemplate.foldRightGate);
        foldRightGate = finishing68;
        Finishing finishing69 = new Finishing(100, FinishingTemplate.foldZ);
        foldZ = finishing69;
        Finishing finishing70 = new Finishing(101, FinishingTemplate.foldEngineeringZ);
        foldEngineeringZ = finishing70;
        List<Finishing> listListOf = CollectionsKt.listOf((Object[]) new Finishing[]{finishing, finishing2, finishing3, finishing4, finishing5, finishing6, finishing7, finishing8, finishing9, finishing10, finishing11, finishing12, finishing13, finishing14, finishing15, finishing16, finishing17, finishing18, finishing19, finishing20, finishing21, finishing22, finishing23, finishing24, finishing25, finishing26, finishing27, finishing28, finishing29, finishing30, finishing31, finishing32, finishing33, finishing34, finishing35, finishing36, finishing37, finishing38, finishing39, finishing40, finishing41, finishing42, finishing43, finishing44, finishing45, finishing46, finishing47, finishing48, finishing49, finishing50, finishing51, finishing52, finishing53, finishing54, finishing55, finishing56, finishing57, finishing58, finishing59, finishing60, finishing61, finishing62, finishing63, finishing64, finishing65, finishing66, finishing67, finishing68, finishing69, finishing70});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (Finishing finishing71 : listListOf) {
            arrayList.add(TuplesKt.m1300to(Integer.valueOf(finishing71.getCode()), finishing71));
        }
        all = MapsKt.toMap(arrayList);
    }
}
