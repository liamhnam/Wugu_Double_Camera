package com.p020hp.mobile.common.browsing;

import kotlin.Metadata;

@Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/NsAttributeKey;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "UUID", "MAKE_AND_MODEL", "IPP_PATH", "IPP_LOCATION", "IPP_PRINT_WFDS", "IPP_ADMIN_URL", "IPP_PDL", "ESCL_ICON", "ESCL_ROOT", "ESCL_VERSION", "CDM_DISCO_URL", "CONNECT_TYPE", "PRODUCT_NUMBER", "SERIAL_NUMBER", "PRINTER_IDENTITY", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public enum NsAttributeKey {
    UUID("uuid"),
    MAKE_AND_MODEL("ty"),
    IPP_PATH("rp"),
    IPP_LOCATION("note"),
    IPP_PRINT_WFDS("print_wfds"),
    IPP_ADMIN_URL("adminurl"),
    IPP_PDL("pdl"),
    ESCL_ICON("representation"),
    ESCL_ROOT("rs"),
    ESCL_VERSION("vers"),
    CDM_DISCO_URL("discourl"),
    CONNECT_TYPE("connect_type"),
    PRODUCT_NUMBER("prod_num"),
    SERIAL_NUMBER("serial_number"),
    PRINTER_IDENTITY("printer_identity");

    public final String key;

    NsAttributeKey(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
