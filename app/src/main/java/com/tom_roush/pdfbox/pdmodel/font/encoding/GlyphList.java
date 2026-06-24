package com.tom_roush.pdfbox.pdmodel.font.encoding;

import android.util.Log;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class GlyphList {
    private static final GlyphList DEFAULT = load("glyphlist.txt", 4281);
    private static final GlyphList ZAPF_DINGBATS = load("zapfdingbats.txt", 201);
    private final Map<String, String> nameToUnicode;
    private final Map<String, String> uniNameToUnicodeCache = new HashMap();
    private final Map<String, String> unicodeToName;

    static {
        try {
            if (System.getProperty("glyphlist_ext") == null) {
            } else {
                throw new UnsupportedOperationException("glyphlist_ext is no longer supported, use GlyphList.DEFAULT.addGlyphs(Properties) instead");
            }
        } catch (SecurityException unused) {
        }
    }

    private static GlyphList load(String str, int i) {
        try {
            if (PDFBoxResourceLoader.isReady()) {
                return new GlyphList(PDFBoxResourceLoader.getStream("com/tom_roush/pdfbox/resources/glyphlist/" + str), i);
            }
            return new GlyphList(GlyphList.class.getClassLoader().getResourceAsStream("com/tom_roush/pdfbox/resources/glyphlist/" + str), i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static GlyphList getAdobeGlyphList() {
        return DEFAULT;
    }

    public static GlyphList getZapfDingbats() {
        return ZAPF_DINGBATS;
    }

    public GlyphList(InputStream inputStream, int i) throws IOException {
        this.nameToUnicode = new HashMap(i);
        this.unicodeToName = new HashMap(i);
        loadList(inputStream);
    }

    public GlyphList(GlyphList glyphList, InputStream inputStream) throws IOException {
        this.nameToUnicode = new HashMap(glyphList.nameToUnicode);
        this.unicodeToName = new HashMap(glyphList.unicodeToName);
        loadList(inputStream);
    }

    private void loadList(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
        while (bufferedReader.ready()) {
            try {
                String line = bufferedReader.readLine();
                if (line != null && !line.startsWith(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                    String[] strArrSplit = line.split(";");
                    if (strArrSplit.length < 2) {
                        throw new IOException("Invalid glyph list entry: " + line);
                    }
                    String str = strArrSplit[0];
                    String[] strArrSplit2 = strArrSplit[1].split(" ");
                    if (this.nameToUnicode.containsKey(str)) {
                        Log.w("PdfBox-Android", "duplicate value for " + str + " -> " + strArrSplit[1] + " " + this.nameToUnicode.get(str));
                    }
                    int length = strArrSplit2.length;
                    int[] iArr = new int[length];
                    int length2 = strArrSplit2.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length2) {
                        iArr[i2] = Integer.parseInt(strArrSplit2[i], 16);
                        i++;
                        i2++;
                    }
                    String str2 = new String(iArr, 0, length);
                    this.nameToUnicode.put(str, str2);
                    if (!this.unicodeToName.containsKey(str2)) {
                        this.unicodeToName.put(str2, str);
                    }
                }
            } finally {
                bufferedReader.close();
            }
        }
    }

    public String codePointToName(int i) {
        String str = this.unicodeToName.get(new String(new int[]{i}, 0, 1));
        return str == null ? ".notdef" : str;
    }

    public String sequenceToName(String str) {
        String str2 = this.unicodeToName.get(str);
        return str2 == null ? ".notdef" : str2;
    }

    public String toUnicode(String str) {
        if (str == null) {
            return null;
        }
        String str2 = this.nameToUnicode.get(str);
        if (str2 != null) {
            return str2;
        }
        String strValueOf = this.uniNameToUnicodeCache.get(str);
        if (strValueOf == null) {
            if (str.indexOf(46) > 0) {
                strValueOf = toUnicode(str.substring(0, str.indexOf(46)));
            } else if (str.startsWith("uni") && str.length() == 7) {
                int length = str.length();
                StringBuilder sb = new StringBuilder();
                int i = 3;
                while (true) {
                    int i2 = i + 4;
                    if (i2 > length) {
                        break;
                    }
                    try {
                        int i3 = Integer.parseInt(str.substring(i, i2), 16);
                        if (i3 > 55295 && i3 < 57344) {
                            Log.w("PdfBox-Android", "Unicode character name with disallowed code area: " + str);
                        } else {
                            sb.append((char) i3);
                        }
                        i = i2;
                    } catch (NumberFormatException unused) {
                        Log.w("PdfBox-Android", "Not a number in Unicode character name: " + str);
                    }
                    Log.w("PdfBox-Android", "Not a number in Unicode character name: " + str);
                }
                strValueOf = sb.toString();
            } else if (str.startsWith("u") && str.length() == 5) {
                try {
                    int i4 = Integer.parseInt(str.substring(1), 16);
                    if (i4 > 55295 && i4 < 57344) {
                        Log.w("PdfBox-Android", "Unicode character name with disallowed code area: " + str);
                    } else {
                        strValueOf = String.valueOf((char) i4);
                    }
                } catch (NumberFormatException unused2) {
                    Log.w("PdfBox-Android", "Not a number in Unicode character name: " + str);
                }
            }
            this.uniNameToUnicodeCache.put(str, strValueOf);
        }
        return strValueOf;
    }
}
