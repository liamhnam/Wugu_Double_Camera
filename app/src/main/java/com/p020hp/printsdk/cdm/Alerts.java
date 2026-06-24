package com.p020hp.printsdk.cdm;

import com.google.gson.annotations.SerializedName;
import com.p020hp.jipp.model.PrinterServiceType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0011B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\t\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/printsdk/cdm/Alerts;", "", "alerts", "", "Lcom/hp/printsdk/cdm/Alerts$Alert;", "(Ljava/util/List;)V", "getAlerts", "()Ljava/util/List;", "component1", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "", "Alert", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class Alerts {

    @SerializedName("alerts")
    public final List<Alert> alerts;

    @Metadata(m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/printsdk/cdm/Alerts$Alert;", "", "stringId", "", "(I)V", "getStringId", "()I", "component1", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Alert {

        @SerializedName("stringId")
        public final int stringId;

        public Alert(int i) {
            this.stringId = i;
        }

        public static Alert copy$default(Alert alert, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = alert.stringId;
            }
            return alert.copy(i);
        }

        public final int getStringId() {
            return this.stringId;
        }

        public final Alert copy(int stringId) {
            return new Alert(stringId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Alert) && this.stringId == ((Alert) other).stringId;
        }

        public final int getStringId() {
            return this.stringId;
        }

        public int hashCode() {
            return Integer.hashCode(this.stringId);
        }

        public String toString() {
            return "Alert(stringId=" + this.stringId + ')';
        }
    }

    public Alerts(List<Alert> list) {
        this.alerts = list;
    }

    public static Alerts copy$default(Alerts alerts, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = alerts.alerts;
        }
        return alerts.copy(list);
    }

    public final List<Alert> component1() {
        return this.alerts;
    }

    public final Alerts copy(List<Alert> alerts) {
        return new Alerts(alerts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Alerts) && Intrinsics.areEqual(this.alerts, ((Alerts) other).alerts);
    }

    public final List<Alert> getAlerts() {
        return this.alerts;
    }

    public int hashCode() {
        List<Alert> list = this.alerts;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "Alerts(alerts=" + this.alerts + ')';
    }
}
