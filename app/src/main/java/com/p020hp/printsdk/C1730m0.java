package com.p020hp.printsdk;

import com.p020hp.open.printsdk.CoreManager;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.pdfbox.pdmodel.font.CIDFontMapping;
import com.tom_roush.pdfbox.pdmodel.font.FontMapper;
import com.tom_roush.pdfbox.pdmodel.font.FontMapping;
import com.tom_roush.pdfbox.pdmodel.font.PDCIDSystemInfo;
import com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class C1730m0 implements FontMapper {
    public final TrueTypeFont m587a(PDFontDescriptor pDFontDescriptor) throws IOException {
        String strValueOf;
        LinkedHashMap<String, String> customTTF$print_core_thirdPartyRelease = CoreManager.INSTANCE.getCustomTTF$print_core_thirdPartyRelease();
        String fontName = pDFontDescriptor != null ? pDFontDescriptor.getFontName() : null;
        Iterator<Map.Entry<String, String>> it = customTTF$print_core_thirdPartyRelease.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                strValueOf = null;
                break;
            }
            strValueOf = it.next().getValue();
            if (strValueOf != null) {
                break;
            }
        }
        Set<String> setKeySet = customTTF$print_core_thirdPartyRelease.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet, "customTTF.keys");
        if (CollectionsKt.contains(setKeySet, fontName)) {
            strValueOf = String.valueOf(customTTF$print_core_thirdPartyRelease.get(fontName));
        } else {
            boolean z = false;
            if (strValueOf != null) {
                if (strValueOf.length() > 0) {
                    z = true;
                }
            }
            if (!z) {
                strValueOf = "com/tom_roush/pdfbox/resources/ttf/LiberationSans-Regular.ttf";
            }
        }
        InputStream stream = PDFBoxResourceLoader.isReady() ? PDFBoxResourceLoader.getStream(strValueOf) : null;
        if (stream == null) {
            stream = FontMapper.class.getResourceAsStream(MqttTopic.TOPIC_LEVEL_SEPARATOR + strValueOf);
        }
        if (stream == null) {
            throw new IOException("Error loading resource: " + strValueOf);
        }
        TrueTypeFont trueTypeFont = new TTFParser().parse(stream);
        Intrinsics.checkNotNullExpressionValue(trueTypeFont, "ttfParser.parse(ttfStream)");
        return trueTypeFont;
    }

    @Override
    public CIDFontMapping getCIDFont(String str, PDFontDescriptor pDFontDescriptor, PDCIDSystemInfo pDCIDSystemInfo) {
        return new CIDFontMapping(null, m587a(pDFontDescriptor), true);
    }

    @Override
    public FontMapping<FontBoxFont> getFontBoxFont(String str, PDFontDescriptor pDFontDescriptor) {
        return new FontMapping<>(m587a(pDFontDescriptor), true);
    }

    @Override
    public FontMapping<TrueTypeFont> getTrueTypeFont(String str, PDFontDescriptor pDFontDescriptor) {
        return new FontMapping<>(m587a(pDFontDescriptor), true);
    }
}
