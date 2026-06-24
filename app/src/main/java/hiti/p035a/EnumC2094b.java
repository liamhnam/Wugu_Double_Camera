package hiti.p035a;

import com.p020hp.jipp.model.Status;

public enum EnumC2094b {
    UNKNOWN(0),
    P520L(Status.Code.serverErrorServiceUnavailable),
    P525N(1294),
    P310W(Status.Code.serverErrorPrinterIsDeactivated),
    P750L(Status.Code.serverErrorOperationNotSupported),
    P461(Status.Code.serverErrorMultipleDocumentJobsNotSupported),
    P530D(15),
    CS200E(777),
    CS220E(778),
    CS290E(781),
    CS280E(41730),
    P910L(14);


    private int f2565id;

    EnumC2094b(int i) {
        this.f2565id = i;
    }

    public static EnumC2094b m1274a(int i) {
        for (EnumC2094b enumC2094b : values()) {
            if (enumC2094b.f2565id == i) {
                return enumC2094b;
            }
        }
        return UNKNOWN;
    }
}
