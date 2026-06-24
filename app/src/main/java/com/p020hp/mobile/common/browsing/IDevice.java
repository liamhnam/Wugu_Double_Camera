package com.p020hp.mobile.common.browsing;

import kotlin.Metadata;
import org.apache.http.cookie.ClientCookie;

@Metadata(m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\n\u0010\b\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\nH&J\u000f\u0010\f\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/IDevice;", "", "makeAndModel", "", "getMakeAndModel", "()Ljava/lang/String;", "connectionType", "Lcom/hp/mobile/common/browsing/ConnectionType;", "hostAddress", "isCDM", "", "isLEDM", ClientCookie.PORT_ATTR, "", "()Ljava/lang/Integer;", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public interface IDevice {
    ConnectionType connectionType();

    String getMakeAndModel();

    String hostAddress();

    boolean isCDM();

    boolean isLEDM();

    Integer port();
}
