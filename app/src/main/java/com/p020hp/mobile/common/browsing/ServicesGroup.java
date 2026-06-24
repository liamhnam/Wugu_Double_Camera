package com.p020hp.mobile.common.browsing;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/ServicesGroup;", "", "serviceTypes", "", "", "([Ljava/lang/String;)V", "getServiceTypes", "()[Ljava/lang/String;", "[Ljava/lang/String;", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class ServicesGroup {
    public final String[] serviceTypes;

    public static final Companion INSTANCE = new Companion(null);
    public static final ServicesGroup PRINT = new ServicesGroup(ServiceType.IPP_SECURE.getType(), ServiceType.IPP.getType(), ServiceType.LEDM.getType(), ServiceType.CDM.getType());
    public static final ServicesGroup SCAN = new ServicesGroup(ServiceType.ESCL_SECURE.getType(), ServiceType.ESCL.getType(), ServiceType.LEDM.getType(), ServiceType.CDM.getType());
    public static final ServicesGroup ALL = new ServicesGroup(ServiceType.IPP_SECURE.getType(), ServiceType.ESCL_SECURE.getType(), ServiceType.LEDM.getType(), ServiceType.CDM.getType(), ServiceType.IPP.getType(), ServiceType.ESCL.getType());
    public static final ServicesGroup SECONDARY = new ServicesGroup(ServiceType.LEDM.getType(), ServiceType.CDM.getType());

    @Metadata(m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u0007R\u001c\u0010\u000e\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0007¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/ServicesGroup$Companion;", "", "()V", "ALL", "Lcom/hp/mobile/common/browsing/ServicesGroup;", "getALL$annotations", "getALL", "()Lcom/hp/mobile/common/browsing/ServicesGroup;", "PRINT", "getPRINT$annotations", "getPRINT", "SCAN", "getSCAN$annotations", "getSCAN", "SECONDARY", "getSECONDARY$annotations", "getSECONDARY", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getALL$annotations() {
        }

        @JvmStatic
        public static void getPRINT$annotations() {
        }

        @JvmStatic
        public static void getSCAN$annotations() {
        }

        @JvmStatic
        public static void getSECONDARY$annotations() {
        }

        public final ServicesGroup getALL() {
            return ServicesGroup.ALL;
        }

        public final ServicesGroup getPRINT() {
            return ServicesGroup.PRINT;
        }

        public final ServicesGroup getSCAN() {
            return ServicesGroup.SCAN;
        }

        public final ServicesGroup getSECONDARY() {
            return ServicesGroup.SECONDARY;
        }
    }

    public ServicesGroup(String... serviceTypes) {
        Intrinsics.checkNotNullParameter(serviceTypes, "serviceTypes");
        this.serviceTypes = serviceTypes;
    }

    public static final ServicesGroup getALL() {
        return INSTANCE.getALL();
    }

    public static final ServicesGroup getPRINT() {
        return INSTANCE.getPRINT();
    }

    public static final ServicesGroup getSCAN() {
        return INSTANCE.getSCAN();
    }

    public static final ServicesGroup getSECONDARY() {
        return INSTANCE.getSECONDARY();
    }

    public final String[] getServiceTypes() {
        return this.serviceTypes;
    }
}
