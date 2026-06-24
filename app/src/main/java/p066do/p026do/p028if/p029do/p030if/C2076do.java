package p066do.p026do.p028if.p029do.p030if;

import android.hardware.usb.UsbDeviceConnection;
import android.net.nsd.NsdServiceInfo;
import com.p020hp.mobile.common.browsing.ConnectionType;
import com.p020hp.mobile.common.browsing.NsAttributeKey;
import com.p020hp.mobile.common.browsing.ServiceType;
import com.p020hp.mobile.common.browsing.ServicesGroup;
import com.p020hp.mobile.common.identity.DeviceIdentity;
import com.p020hp.mobile.common.query.delegate.Delegate;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.http.HttpHost;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p066do.p026do.p028if.p029do.C2070do;

public final class C2076do {

    public class Cdo {

        public static final int[] f2518do;

        static {
            int[] iArr = new int[ServiceType.values().length];
            ServiceType serviceType = ServiceType.CDM;
            iArr[3] = 1;
            ServiceType serviceType2 = ServiceType.LEDM;
            iArr[2] = 2;
            ServiceType serviceType3 = ServiceType.IPP;
            iArr[4] = 3;
            ServiceType serviceType4 = ServiceType.IPP_SECURE;
            iArr[5] = 4;
            ServiceType serviceType5 = ServiceType.ESCL;
            iArr[0] = 5;
            ServiceType serviceType6 = ServiceType.ESCL_SECURE;
            iArr[1] = 6;
            f2518do = iArr;
        }
    }

    public static final ConnectionType m1219do(NsdServiceInfo nsdServiceInfo) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        return Intrinsics.areEqual(m1223do(nsdServiceInfo, NsAttributeKey.CONNECT_TYPE.getKey()), ConnectionType.USB.getType()) ? ConnectionType.USB : ConnectionType.WIFI;
    }

    public static final DeviceIdentity m1221do() {
        return new DeviceIdentity("", "", null, null, 12, null);
    }

    public static final Object m1222do(Object obj, List<String> list, int i) {
        if (i >= list.size()) {
            return obj;
        }
        if (!(obj instanceof JSONObject)) {
            throw new IllegalArgumentException("Something wrong with configuration " + list + " : " + i);
        }
        Object objOpt = ((JSONObject) obj).opt(list.get(i));
        if (objOpt != null) {
            return m1222do(objOpt, list, i + 1);
        }
        return null;
    }

    public static final String m1223do(NsdServiceInfo nsdServiceInfo, String key) {
        Object next;
        byte[] bArr;
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Iterator<T> it = nsdServiceInfo.getAttributes().keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            String it2 = (String) next;
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = it2.toLowerCase(US);
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
        if (str == null || (bArr = nsdServiceInfo.getAttributes().get(str)) == null) {
            return null;
        }
        return new String(bArr, Charsets.UTF_8);
    }

    public static final URI m1224do(NsdServiceInfo nsdServiceInfo, String str, String str2, boolean z) {
        StringBuilder sbAppend;
        String strTrimStart;
        String str3 = (!z || nsdServiceInfo.getPort() == -1) ? "" : ":" + nsdServiceInfo.getPort();
        InetAddress host = nsdServiceInfo.getHost();
        if (host instanceof Inet4Address) {
            sbAppend = new StringBuilder().append(str).append("://").append(StringsKt.trimStart(nsdServiceInfo.getHost().getHostAddress().toString(), '/')).append(str3).append('/');
            strTrimStart = StringsKt.trimStart(str2, '/');
        } else {
            if (!(host instanceof Inet6Address)) {
                return null;
            }
            sbAppend = new StringBuilder().append(str).append("://[").append(StringsKt.trimStart(nsdServiceInfo.getHost().getHostAddress().toString(), '/')).append(']').append(str3).append('/');
            strTrimStart = StringsKt.trimStart(str2, '/');
        }
        return m1231if(sbAppend.append(strTrimStart).toString());
    }

    public static URI m1225do(NsdServiceInfo nsdServiceInfo, String str, String str2, boolean z, int i) {
        String str3 = (i & 1) != 0 ? "https" : null;
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return m1224do(nsdServiceInfo, str3, str2, z);
    }

    public static final JSONArray m1226do(JSONArray jSONArray, JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject subOrigin = jSONArray.getJSONObject(i);
            Intrinsics.checkNotNullExpressionValue(subOrigin, "subOrigin");
            jSONArray2.put(m1227do(subOrigin, jSONObject, false));
        }
        return jSONArray2;
    }

    public static final void m1228do(NsdServiceInfo nsdServiceInfo, ConnectionType connectionType) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        Intrinsics.checkNotNullParameter(connectionType, "connectionType");
        nsdServiceInfo.setAttribute(NsAttributeKey.CONNECT_TYPE.getKey(), connectionType.getType());
    }

    public static boolean m1229do(UsbDeviceConnection usbDeviceConnection) {
        byte[] bArr = new byte[256];
        int iControlTransfer = usbDeviceConnection.controlTransfer(128, 6, 8448, 0, bArr, 256, 0);
        return (-1 == iControlTransfer || iControlTransfer == 0 || (((short) (((bArr[7] & UByte.MAX_VALUE) << 8) | (bArr[6] & UByte.MAX_VALUE))) & 2) != 2) ? false : true;
    }

    public static final boolean m1230do(NsdServiceInfo nsdServiceInfo, NsdServiceInfo nsdServiceInfo2) {
        String serviceType;
        String serviceType2;
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        String[] serviceTypes = ServicesGroup.INSTANCE.getPRINT().getServiceTypes();
        String serviceType3 = nsdServiceInfo.getServiceType();
        Intrinsics.checkNotNullExpressionValue(serviceType3, "this.serviceType");
        boolean zContains = ArraysKt.contains(serviceTypes, StringsKt.trim(serviceType3, '.'));
        String strTrim = null;
        if (zContains) {
            if (ArraysKt.contains(ServicesGroup.INSTANCE.getPRINT().getServiceTypes(), (nsdServiceInfo2 == null || (serviceType2 = nsdServiceInfo2.getServiceType()) == null) ? null : StringsKt.trim(serviceType2, '.'))) {
                return true;
            }
        }
        String[] serviceTypes2 = ServicesGroup.INSTANCE.getSCAN().getServiceTypes();
        String serviceType4 = nsdServiceInfo.getServiceType();
        Intrinsics.checkNotNullExpressionValue(serviceType4, "this.serviceType");
        if (ArraysKt.contains(serviceTypes2, StringsKt.trim(serviceType4, '.'))) {
            String[] serviceTypes3 = ServicesGroup.INSTANCE.getSCAN().getServiceTypes();
            if (nsdServiceInfo2 != null && (serviceType = nsdServiceInfo2.getServiceType()) != null) {
                strTrim = StringsKt.trim(serviceType, '.');
            }
            if (ArraysKt.contains(serviceTypes3, strTrim)) {
                return true;
            }
        }
        return false;
    }

    public static final String m1757for(NsdServiceInfo nsdServiceInfo) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        return m1219do(nsdServiceInfo).name() + '/' + nsdServiceInfo.getServiceName();
    }

    public static final URI m1231if(String str) {
        Object objM1772constructorimpl;
        try {
            objM1772constructorimpl = Result.m1772constructorimpl(new URI(str));
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
            objM1772constructorimpl = null;
        }
        return (URI) objM1772constructorimpl;
    }

    public static final Pair<ServiceType, URI> m1232if(NsdServiceInfo nsdServiceInfo, String suffix) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        boolean zAreEqual = Intrinsics.areEqual(m1223do(nsdServiceInfo, NsAttributeKey.CONNECT_TYPE.getKey()), ConnectionType.USB.getType());
        String serviceType = nsdServiceInfo.getServiceType();
        Intrinsics.checkNotNullExpressionValue(serviceType, "serviceType");
        ServiceType serviceTypeM1220do = m1220do(serviceType);
        int i = serviceTypeM1220do == null ? -1 : Cdo.f2518do[serviceTypeM1220do.ordinal()];
        String str = HttpHost.DEFAULT_SCHEME_NAME;
        switch (i) {
            case 1:
                if (!zAreEqual) {
                    str = "https";
                }
                URI uriM1224do = m1224do(nsdServiceInfo, str, suffix, zAreEqual);
                if (uriM1224do != null) {
                    return new Pair<>(serviceTypeM1220do, uriM1224do);
                }
                return null;
            case 2:
                URI uriM1224do2 = m1224do(nsdServiceInfo, HttpHost.DEFAULT_SCHEME_NAME, suffix, zAreEqual);
                if (uriM1224do2 != null) {
                    return new Pair<>(serviceTypeM1220do, uriM1224do2);
                }
                return null;
            case 3:
                URI uriM1224do3 = m1224do(nsdServiceInfo, HttpHost.DEFAULT_SCHEME_NAME, suffix, true);
                if (uriM1224do3 != null) {
                    return new Pair<>(serviceTypeM1220do, uriM1224do3);
                }
                return null;
            case 4:
                URI uriM1225do = m1225do(nsdServiceInfo, (String) null, suffix, true, 1);
                if (uriM1225do != null) {
                    return new Pair<>(serviceTypeM1220do, uriM1225do);
                }
                return null;
            case 5:
                URI uriM1224do4 = m1224do(nsdServiceInfo, HttpHost.DEFAULT_SCHEME_NAME, suffix, zAreEqual);
                if (uriM1224do4 != null) {
                    return new Pair<>(serviceTypeM1220do, uriM1224do4);
                }
                return null;
            case 6:
                URI uriM1225do2 = m1225do(nsdServiceInfo, (String) null, suffix, zAreEqual, 1);
                if (uriM1225do2 != null) {
                    return new Pair<>(serviceTypeM1220do, uriM1225do2);
                }
                return null;
            default:
                return null;
        }
    }

    public static final boolean m1233if() {
        List<String> FILTER_MODEL = C2070do.f2502if;
        Intrinsics.checkNotNullExpressionValue(FILTER_MODEL, "FILTER_MODEL");
        if (!FILTER_MODEL.isEmpty()) {
            return true;
        }
        List<String> FILTER_SKU = C2070do.f3917for;
        Intrinsics.checkNotNullExpressionValue(FILTER_SKU, "FILTER_SKU");
        return FILTER_SKU.isEmpty() ^ true;
    }

    public static final boolean m1234if(NsdServiceInfo nsdServiceInfo) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        String strM1223do = m1223do(nsdServiceInfo, NsAttributeKey.UUID.getKey());
        return strM1223do == null || StringsKt.isBlank(strM1223do);
    }

    public static final String m1758new(NsdServiceInfo nsdServiceInfo) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        StringBuilder sbAppend = new StringBuilder().append(nsdServiceInfo.getServiceName()).append('/');
        String serviceType = nsdServiceInfo.getServiceType();
        Intrinsics.checkNotNullExpressionValue(serviceType, "serviceType");
        return sbAppend.append(StringsKt.trim(serviceType, '.')).toString();
    }

    public static final boolean m1759try(NsdServiceInfo nsdServiceInfo) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "<this>");
        String serviceType = nsdServiceInfo.getServiceType();
        Intrinsics.checkNotNullExpressionValue(serviceType, "this.serviceType");
        if (m1220do(serviceType) != ServiceType.IPP) {
            String serviceType2 = nsdServiceInfo.getServiceType();
            Intrinsics.checkNotNullExpressionValue(serviceType2, "this.serviceType");
            if (m1220do(serviceType2) != ServiceType.IPP_SECURE) {
                String serviceType3 = nsdServiceInfo.getServiceType();
                Intrinsics.checkNotNullExpressionValue(serviceType3, "this.serviceType");
                if (m1220do(serviceType3) != ServiceType.ESCL) {
                    String serviceType4 = nsdServiceInfo.getServiceType();
                    Intrinsics.checkNotNullExpressionValue(serviceType4, "this.serviceType");
                    if (m1220do(serviceType4) != ServiceType.ESCL_SECURE) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static final JSONObject m1227do(JSONObject origin, JSONObject fieldPaths, boolean z) throws JSONException {
        JSONArray jSONArrayM1226do;
        Object objOpt;
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(fieldPaths, "fieldPaths");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> itKeys = fieldPaths.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            if (!Intrinsics.areEqual(key, Delegate.CONTENT_PATH_KEY) && (objOpt = fieldPaths.opt(key)) != null) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                linkedHashMap.put(key, objOpt);
            }
        }
        if (linkedHashMap.isEmpty()) {
            return origin;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                jSONObject.put(str, m1222do(origin, (List<String>) StringsKt.split$default((CharSequence) value, new String[]{MqttTopic.TOPIC_LEVEL_SEPARATOR}, false, 0, 6, (Object) null), 0));
            } else if (value instanceof JSONObject) {
                JSONObject jSONObject2 = (JSONObject) value;
                Intrinsics.checkNotNullParameter(jSONObject2, "<this>");
                String strOptString = jSONObject2.optString(Delegate.CONTENT_PATH_KEY);
                Intrinsics.checkNotNullExpressionValue(strOptString, "this.optString(Delegate.CONTENT_PATH_KEY)");
                Object objM1222do = m1222do(origin, (List<String>) StringsKt.split$default((CharSequence) strOptString, new String[]{MqttTopic.TOPIC_LEVEL_SEPARATOR}, false, 0, 6, (Object) null), 0);
                if (objM1222do == null) {
                    jSONArrayM1226do = new JSONArray();
                } else if (objM1222do instanceof JSONArray) {
                    jSONArrayM1226do = m1226do((JSONArray) objM1222do, jSONObject2);
                } else {
                    if (!z || !(objM1222do instanceof JSONObject)) {
                        throw new IllegalArgumentException("Something wrong with configuration " + str + " : " + value);
                    }
                    JSONArray jSONArrayPut = new JSONArray().put(objM1222do);
                    Intrinsics.checkNotNullExpressionValue(jSONArrayPut, "JSONArray().put(subOrigins)");
                    jSONArrayM1226do = m1226do(jSONArrayPut, jSONObject2);
                }
                jSONObject.put(str, jSONArrayM1226do);
            } else {
                continue;
            }
        }
        return jSONObject;
    }

    public static NsdServiceInfo m1218do(String serviceName, ServiceType serviceType, InetAddress host, int i, int i2) {
        if ((i2 & 1) != 0) {
            serviceName = "DefaultName";
        }
        if ((i2 & 4) != 0) {
            host = InetAddress.getByName("127.0.0.1");
            Intrinsics.checkNotNullExpressionValue(host, "getByName(\"127.0.0.1\")");
        }
        if ((i2 & 8) != 0) {
            i = 1;
        }
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(host, "host");
        NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setServiceName(serviceName);
        nsdServiceInfo.setServiceType(serviceType.getType());
        nsdServiceInfo.setHost(host);
        nsdServiceInfo.setPort(i);
        return nsdServiceInfo;
    }

    public static final ServiceType m1220do(String str) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            String value = StringsKt.trim(str, '.');
            ServiceType.INSTANCE.getClass();
            Intrinsics.checkNotNullParameter(value, "value");
            objM1772constructorimpl = Result.m1772constructorimpl((ServiceType) ServiceType.map.get(value));
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
            objM1772constructorimpl = null;
        }
        return (ServiceType) objM1772constructorimpl;
    }
}
