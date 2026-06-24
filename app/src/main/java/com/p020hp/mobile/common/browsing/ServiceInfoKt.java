package com.p020hp.mobile.common.browsing;

import android.net.nsd.NsdServiceInfo;
import java.net.InetAddress;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(m1292d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0000\u001a\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0086\u0002\u001a\u000e\u0010\t\u001a\u0004\u0018\u00010\u0004*\u00020\u0002H\u0000\u001a\f\u0010\n\u001a\u00020\u0007*\u00020\u0002H\u0000\u001a\n\u0010\u000b\u001a\u00020\u0007*\u00020\u0002\u001a\f\u0010\f\u001a\u00020\u0002*\u00020\rH\u0000¨\u0006\u000e"}, m1293d2 = {"connectionType", "Lcom/hp/mobile/common/browsing/ConnectionType;", "Lcom/hp/mobile/common/browsing/ServiceInfo;", "findIcon", "Ljava/net/URL;", "", "get", "", "key", "iconInfoToUrl", "idPerConnection", "identifier", "toServiceInfo", "Landroid/net/nsd/NsdServiceInfo;", "common-lib_release"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class ServiceInfoKt {
    public static final ConnectionType connectionType(ServiceInfo serviceInfo) {
        Intrinsics.checkNotNullParameter(serviceInfo, "<this>");
        return Intrinsics.areEqual(get(serviceInfo, NsAttributeKey.CONNECT_TYPE.getKey()), ConnectionType.USB.getType()) ? ConnectionType.USB : ConnectionType.WIFI;
    }

    public static final URL findIcon(Iterable<ServiceInfo> iterable) {
        ServiceInfo next;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<ServiceInfo> it = iterable.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (get(next, NsAttributeKey.ESCL_ICON.getKey()) != null) {
                break;
            }
        }
        ServiceInfo serviceInfo = next;
        if (serviceInfo != null) {
            return iconInfoToUrl(serviceInfo);
        }
        return null;
    }

    public static final String get(ServiceInfo serviceInfo, String key) {
        Object next;
        byte[] bArr;
        Intrinsics.checkNotNullParameter(serviceInfo, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Iterator<T> it = serviceInfo.getAttributes().keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = ((String) next).toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            Locale US2 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US2, "US");
            String lowerCase2 = key.toLowerCase(US2);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                break;
            }
        }
        String str = (String) next;
        if (str == null || (bArr = serviceInfo.getAttributes().get(str)) == null) {
            return null;
        }
        return new String(bArr, Charsets.UTF_8);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.net.URL iconInfoToUrl(com.p020hp.mobile.common.browsing.ServiceInfo r8) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            com.hp.mobile.common.browsing.NsAttributeKey r0 = com.p020hp.mobile.common.browsing.NsAttributeKey.ESCL_ICON
            java.lang.String r0 = r0.getKey()
            java.lang.String r0 = get(r8, r0)
            r1 = 0
            if (r0 == 0) goto Lb5
            int r2 = r8.getPort()
            r3 = 443(0x1bb, float:6.21E-43)
            java.lang.String r4 = "http"
            if (r2 != r3) goto L1f
            java.lang.String r2 = "https"
            goto L20
        L1f:
            r2 = r4
        L20:
            java.net.InetAddress r3 = r8.getHost()     // Catch: java.lang.Throwable -> La2
            java.lang.String r3 = r3.getHostAddress()     // Catch: java.lang.Throwable -> La2
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L39
            java.lang.String r7 = "hostAddress"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r7)     // Catch: java.lang.Throwable -> La2
            boolean r3 = com.p020hp.mobile.common.browsing.ConnectionTypeKt.isUsbHost(r3)     // Catch: java.lang.Throwable -> La2
            if (r3 != r5) goto L39
            r3 = r5
            goto L3a
        L39:
            r3 = r6
        L3a:
            if (r3 == 0) goto L44
            int r3 = r8.getPort()     // Catch: java.lang.Throwable -> La2
            r7 = -1
            if (r3 == r7) goto L44
            goto L45
        L44:
            r5 = r6
        L45:
            r3 = 2
            boolean r3 = kotlin.text.StringsKt.startsWith$default(r0, r4, r6, r3, r1)     // Catch: java.lang.Throwable -> La2
            if (r3 == 0) goto L7b
            java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> La2
            r3.<init>(r0)     // Catch: java.lang.Throwable -> La2
            if (r5 == 0) goto L69
            java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> La2
            java.net.InetAddress r4 = r8.getHost()     // Catch: java.lang.Throwable -> La2
            java.lang.String r4 = r4.getHostAddress()     // Catch: java.lang.Throwable -> La2
            int r8 = r8.getPort()     // Catch: java.lang.Throwable -> La2
            java.lang.String r3 = r3.getFile()     // Catch: java.lang.Throwable -> La2
            r0.<init>(r2, r4, r8, r3)     // Catch: java.lang.Throwable -> La2
            goto L9d
        L69:
            java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> La2
            java.net.InetAddress r8 = r8.getHost()     // Catch: java.lang.Throwable -> La2
            java.lang.String r8 = r8.getHostAddress()     // Catch: java.lang.Throwable -> La2
            java.lang.String r3 = r3.getFile()     // Catch: java.lang.Throwable -> La2
            r0.<init>(r2, r8, r3)     // Catch: java.lang.Throwable -> La2
            goto L9d
        L7b:
            if (r5 == 0) goto L8f
            java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> La2
            java.net.InetAddress r4 = r8.getHost()     // Catch: java.lang.Throwable -> La2
            java.lang.String r4 = r4.getHostAddress()     // Catch: java.lang.Throwable -> La2
            int r8 = r8.getPort()     // Catch: java.lang.Throwable -> La2
            r3.<init>(r2, r4, r8, r0)     // Catch: java.lang.Throwable -> La2
            goto L9c
        L8f:
            java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> La2
            java.net.InetAddress r8 = r8.getHost()     // Catch: java.lang.Throwable -> La2
            java.lang.String r8 = r8.getHostAddress()     // Catch: java.lang.Throwable -> La2
            r3.<init>(r2, r8, r0)     // Catch: java.lang.Throwable -> La2
        L9c:
            r0 = r3
        L9d:
            java.lang.Object r8 = kotlin.Result.m1772constructorimpl(r0)     // Catch: java.lang.Throwable -> La2
            goto Lab
        La2:
            r8 = move-exception
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            java.lang.Object r8 = kotlin.Result.m1772constructorimpl(r8)
        Lab:
            boolean r0 = kotlin.Result.m1778isFailureimpl(r8)
            if (r0 == 0) goto Lb2
            goto Lb3
        Lb2:
            r1 = r8
        Lb3:
            java.net.URL r1 = (java.net.URL) r1
        Lb5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.mobile.common.browsing.ServiceInfoKt.iconInfoToUrl(com.hp.mobile.common.browsing.ServiceInfo):java.net.URL");
    }

    public static final String idPerConnection(ServiceInfo serviceInfo) {
        Intrinsics.checkNotNullParameter(serviceInfo, "<this>");
        return connectionType(serviceInfo).name() + '/' + serviceInfo.getServiceName();
    }

    public static final String identifier(ServiceInfo serviceInfo) {
        Intrinsics.checkNotNullParameter(serviceInfo, "<this>");
        return serviceInfo.getServiceName() + '/' + StringsKt.trim(serviceInfo.getServiceType(), '.');
    }

    public static final ServiceInfo toServiceInfo(NsdServiceInfo nsdServiceInfo) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        String serviceType = nsdServiceInfo.getServiceType();
        Intrinsics.checkNotNullExpressionValue(serviceType, "serviceType");
        String strTrim = StringsKt.trim(serviceType, '.');
        InetAddress host = nsdServiceInfo.getHost();
        Intrinsics.checkNotNullExpressionValue(host, "host");
        int port = nsdServiceInfo.getPort();
        String serviceName = nsdServiceInfo.getServiceName();
        Intrinsics.checkNotNullExpressionValue(serviceName, "serviceName");
        Map<String, byte[]> attributes = nsdServiceInfo.getAttributes();
        Intrinsics.checkNotNullExpressionValue(attributes, "attributes");
        return new ServiceInfo(strTrim, host, port, serviceName, MapsKt.toMutableMap(attributes));
    }
}
