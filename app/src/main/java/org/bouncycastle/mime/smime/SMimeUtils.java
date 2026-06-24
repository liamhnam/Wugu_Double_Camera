package org.bouncycastle.mime.smime;

import com.p020hp.jipp.model.JobPasswordEncryption;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.util.Strings;

class SMimeUtils {
    private static final Map RFC3851_MICALGS;
    private static final Map RFC5751_MICALGS;
    private static final Map STANDARD_MICALGS;
    private static final Map forMic;

    private static final byte[] f3629nl = {13, 10};

    static {
        HashMap map = new HashMap();
        map.put(CMSAlgorithm.MD5, JobPasswordEncryption.md5);
        map.put(CMSAlgorithm.SHA1, "sha-1");
        map.put(CMSAlgorithm.SHA224, "sha-224");
        map.put(CMSAlgorithm.SHA256, "sha-256");
        map.put(CMSAlgorithm.SHA384, "sha-384");
        map.put(CMSAlgorithm.SHA512, "sha-512");
        map.put(CMSAlgorithm.GOST3411, "gostr3411-94");
        map.put(CMSAlgorithm.GOST3411_2012_256, "gostr3411-2012-256");
        map.put(CMSAlgorithm.GOST3411_2012_512, "gostr3411-2012-512");
        Map mapUnmodifiableMap = Collections.unmodifiableMap(map);
        RFC5751_MICALGS = mapUnmodifiableMap;
        HashMap map2 = new HashMap();
        map2.put(CMSAlgorithm.MD5, JobPasswordEncryption.md5);
        map2.put(CMSAlgorithm.SHA1, "sha1");
        map2.put(CMSAlgorithm.SHA224, "sha224");
        map2.put(CMSAlgorithm.SHA256, "sha256");
        map2.put(CMSAlgorithm.SHA384, "sha384");
        map2.put(CMSAlgorithm.SHA512, "sha512");
        map2.put(CMSAlgorithm.GOST3411, "gostr3411-94");
        map2.put(CMSAlgorithm.GOST3411_2012_256, "gostr3411-2012-256");
        map2.put(CMSAlgorithm.GOST3411_2012_512, "gostr3411-2012-512");
        RFC3851_MICALGS = Collections.unmodifiableMap(map2);
        STANDARD_MICALGS = mapUnmodifiableMap;
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Object obj : mapUnmodifiableMap.keySet()) {
            treeMap.put(STANDARD_MICALGS.get(obj).toString(), (ASN1ObjectIdentifier) obj);
        }
        for (Object obj2 : RFC3851_MICALGS.keySet()) {
            treeMap.put(RFC3851_MICALGS.get(obj2).toString(), (ASN1ObjectIdentifier) obj2);
        }
        forMic = Collections.unmodifiableMap(treeMap);
    }

    SMimeUtils() {
    }

    static InputStream autoBuffer(InputStream inputStream) {
        return inputStream instanceof FileInputStream ? new BufferedInputStream(inputStream) : inputStream;
    }

    static OutputStream autoBuffer(OutputStream outputStream) {
        return outputStream instanceof FileOutputStream ? new BufferedOutputStream(outputStream) : outputStream;
    }

    static OutputStream createUnclosable(OutputStream outputStream) {
        return new FilterOutputStream(outputStream) {
            @Override
            public void close() throws IOException {
            }

            @Override
            public void write(byte[] bArr, int i, int i2) throws IOException {
                bArr.getClass();
                int i3 = i2 + i;
                if ((i | i2 | (bArr.length - i3) | i3) < 0) {
                    throw new IndexOutOfBoundsException();
                }
                this.out.write(bArr, i, i2);
            }
        };
    }

    static ASN1ObjectIdentifier getDigestOID(String str) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) forMic.get(Strings.toLowerCase(str));
        if (aSN1ObjectIdentifier != null) {
            return aSN1ObjectIdentifier;
        }
        throw new IllegalArgumentException("unknown micalg passed: " + str);
    }

    static String getParameter(String str, List<String> list) {
        for (String str2 : list) {
            if (str2.startsWith(str)) {
                return str2;
            }
        }
        return null;
    }

    static String lessQuotes(String str) {
        return (str == null || str.length() <= 1 || str.charAt(0) != '\"' || str.charAt(str.length() - 1) != '\"') ? str : str.substring(1, str.length() - 1);
    }
}
