package com.p020hp.open.printsdk.options;

import com.p020hp.printsdk.C1661R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0005\u0006\u0007B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/open/printsdk/options/OrientationType;", "Lcom/hp/open/printsdk/options/Type;", "labelId", "", "(I)V", "Landscape", "None", "Portrait", "Lcom/hp/open/printsdk/options/OrientationType$Portrait;", "Lcom/hp/open/printsdk/options/OrientationType$Landscape;", "Lcom/hp/open/printsdk/options/OrientationType$None;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class OrientationType extends Type {

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/OrientationType$Landscape;", "Lcom/hp/open/printsdk/options/OrientationType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Landscape extends OrientationType {
        public static final Landscape INSTANCE = new Landscape();

        public Landscape() {
            super(C1661R.string.hp_print_sdk_types_landscape, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/OrientationType$None;", "Lcom/hp/open/printsdk/options/OrientationType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class None extends OrientationType {
        public static final None INSTANCE = new None();

        public None() {
            super(C1661R.string.hp_print_sdk_types_auto, null);
        }
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m1293d2 = {"Lcom/hp/open/printsdk/options/OrientationType$Portrait;", "Lcom/hp/open/printsdk/options/OrientationType;", "()V", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Portrait extends OrientationType {
        public static final Portrait INSTANCE = new Portrait();

        public Portrait() {
            super(C1661R.string.hp_print_sdk_types_portrait, null);
        }
    }

    public OrientationType(int i) {
        super(i, null, 2, 0 == true ? 1 : 0);
    }

    public OrientationType(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }
}
