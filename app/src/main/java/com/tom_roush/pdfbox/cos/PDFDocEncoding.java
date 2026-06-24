package com.tom_roush.pdfbox.cos;

import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;
import com.google.android.exoplayer2.extractor.p018ts.TsExtractor;
import com.p020hp.jipp.model.PowerState;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import kotlin.UByte;
import kotlin.text.Typography;
import org.apache.log4j.net.SyslogAppender;

final class PDFDocEncoding {
    private static final char REPLACEMENT_CHARACTER = 65533;
    private static final int[] CODE_TO_UNI = new int[256];
    private static final Map<Character, Integer> UNI_TO_CODE = new HashMap(256);

    static {
        for (int i = 0; i < 256; i++) {
            set(i, (char) i);
        }
        set(24, (char) 728);
        set(25, (char) 711);
        set(26, (char) 710);
        set(27, (char) 729);
        set(28, (char) 733);
        set(29, (char) 731);
        set(30, (char) 730);
        set(31, (char) 732);
        set(127, (char) 65533);
        set(128, Typography.bullet);
        set(129, Typography.dagger);
        set(130, Typography.doubleDagger);
        set(131, Typography.ellipsis);
        set(132, Typography.mdash);
        set(133, Typography.ndash);
        set(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, (char) 402);
        set(TsExtractor.TS_STREAM_TYPE_E_AC3, (char) 8260);
        set(SyslogAppender.LOG_LOCAL1, (char) 8249);
        set(137, (char) 8250);
        set(TsExtractor.TS_STREAM_TYPE_DTS, (char) 8722);
        set(139, (char) 8240);
        set(PowerState.Code.resetMbrGraceful, Typography.lowDoubleQuote);
        set(141, Typography.leftDoubleQuote);
        set(PageAttribute.MediaTypeID.EPS_MTID_PLOOFING_WHITE_MAT, Typography.rightDoubleQuote);
        set(143, Typography.leftSingleQuote);
        set(SyslogAppender.LOG_LOCAL2, Typography.rightSingleQuote);
        set(145, Typography.lowSingleQuote);
        set(146, Typography.f2738tm);
        set(147, (char) 64257);
        set(148, (char) 64258);
        set(149, (char) 321);
        set(PowerState.Code.resetSoftGraceful, (char) 338);
        set(151, (char) 352);
        set(SyslogAppender.LOG_LOCAL3, (char) 376);
        set(153, (char) 381);
        set(154, (char) 305);
        set(155, (char) 322);
        set(156, (char) 339);
        set(157, (char) 353);
        set(158, (char) 382);
        set(159, (char) 65533);
        set(160, Typography.euro);
    }

    private PDFDocEncoding() {
    }

    private static void set(int i, char c) {
        CODE_TO_UNI[i] = c;
        UNI_TO_CODE.put(Character.valueOf(c), Integer.valueOf(i));
    }

    public static String toString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            int i = b & UByte.MAX_VALUE;
            int[] iArr = CODE_TO_UNI;
            if (i >= iArr.length) {
                sb.append('?');
            } else {
                sb.append((char) iArr[i]);
            }
        }
        return sb.toString();
    }

    public static byte[] getBytes(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (char c : str.toCharArray()) {
            Integer num = UNI_TO_CODE.get(Character.valueOf(c));
            if (num == null) {
                byteArrayOutputStream.write(0);
            } else {
                byteArrayOutputStream.write(num.intValue());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean containsChar(char c) {
        return UNI_TO_CODE.containsKey(Character.valueOf(c));
    }
}
