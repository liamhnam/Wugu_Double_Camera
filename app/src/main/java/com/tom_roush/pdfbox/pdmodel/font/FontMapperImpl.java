package com.tom_roush.pdfbox.pdmodel.font;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.p020hp.jipp.model.MediaColor;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.cff.CFFFont;
import com.tom_roush.fontbox.cff.CFFType1Font;
import com.tom_roush.fontbox.ttf.OpenTypeFont;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.type1.Type1Font;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;

final class FontMapperImpl implements FontMapper {
    private static final FontCache fontCache = new FontCache();
    private Map<String, FontInfo> fontInfoByName;
    private FontProvider fontProvider;
    private final TrueTypeFont lastResortFont;
    private final Map<String, List<String>> substitutes;

    FontMapperImpl() {
        HashMap map = new HashMap();
        this.substitutes = map;
        map.put("Courier", Arrays.asList("CourierNew", "CourierNewPSMT", "LiberationMono", "NimbusMonL-Regu", "DroidSansMono"));
        map.put("Courier-Bold", Arrays.asList("CourierNewPS-BoldMT", "CourierNew-Bold", "LiberationMono-Bold", "NimbusMonL-Bold", "DroidSansMono"));
        map.put("Courier-Oblique", Arrays.asList("CourierNewPS-ItalicMT", "CourierNew-Italic", "LiberationMono-Italic", "NimbusMonL-ReguObli", "DroidSansMono"));
        map.put("Courier-BoldOblique", Arrays.asList("CourierNewPS-BoldItalicMT", "CourierNew-BoldItalic", "LiberationMono-BoldItalic", "NimbusMonL-BoldObli", "DroidSansMono"));
        map.put("Helvetica", Arrays.asList("ArialMT", "Arial", "LiberationSans", "NimbusSanL-Regu", "Roboto-Regular"));
        map.put("Helvetica-Bold", Arrays.asList("Arial-BoldMT", "Arial-Bold", "LiberationSans-Bold", "NimbusSanL-Bold", "Roboto-Bold"));
        map.put("Helvetica-Oblique", Arrays.asList("Arial-ItalicMT", "Arial-Italic", "Helvetica-Italic", "LiberationSans-Italic", "NimbusSanL-ReguItal", "Roboto-Italic"));
        map.put("Helvetica-BoldOblique", Arrays.asList("Arial-BoldItalicMT", "Helvetica-BoldItalic", "LiberationSans-BoldItalic", "NimbusSanL-BoldItal", "Roboto-BoldItalic"));
        map.put("Times-Roman", Arrays.asList("TimesNewRomanPSMT", "TimesNewRoman", "TimesNewRomanPS", "LiberationSerif", "NimbusRomNo9L-Regu", "DroidSerif-Regular", "Roboto-Regular"));
        map.put("Times-Bold", Arrays.asList("TimesNewRomanPS-BoldMT", "TimesNewRomanPS-Bold", "TimesNewRoman-Bold", "LiberationSerif-Bold", "NimbusRomNo9L-Medi", "DroidSerif-Bold", "Roboto-Bold"));
        map.put("Times-Italic", Arrays.asList("TimesNewRomanPS-ItalicMT", "TimesNewRomanPS-Italic", "TimesNewRoman-Italic", "LiberationSerif-Italic", "NimbusRomNo9L-ReguItal", "DroidSerif-Italic", "Roboto-Italic"));
        map.put("Times-BoldItalic", Arrays.asList("TimesNewRomanPS-BoldItalicMT", "TimesNewRomanPS-BoldItalic", "TimesNewRoman-BoldItalic", "LiberationSerif-BoldItalic", "NimbusRomNo9L-MediItal", "DroidSerif-BoldItalic", "Roboto-BoldItalic"));
        map.put("Symbol", Arrays.asList("Symbol", "SymbolMT", "StandardSymL"));
        map.put("ZapfDingbats", Arrays.asList("ZapfDingbatsITC", "Dingbats", "MS-Gothic"));
        for (String str : Standard14Fonts.getNames()) {
            if (!this.substitutes.containsKey(str)) {
                this.substitutes.put(str, copySubstitutes(Standard14Fonts.getMappedFontName(str)));
            }
        }
        try {
            InputStream stream = PDFBoxResourceLoader.isReady() ? PDFBoxResourceLoader.getStream("com/tom_roush/pdfbox/resources/ttf/LiberationSans-Regular.ttf") : null;
            if (stream == null) {
                URL resource = FontMapper.class.getClassLoader().getResource("com/tom_roush/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
                if (resource == null) {
                    throw new IOException("Error loading resource: com/tom_roush/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
                }
                stream = resource.openStream();
            }
            this.lastResortFont = new TTFParser().parse(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class DefaultFontProvider {
        private static final FontProvider INSTANCE = new FileSystemFontProvider(FontMapperImpl.fontCache);

        private DefaultFontProvider() {
        }
    }

    public synchronized void setProvider(FontProvider fontProvider) {
        this.fontProvider = fontProvider;
        this.fontInfoByName = createFontInfoByName(fontProvider.getFontInfo());
    }

    public synchronized FontProvider getProvider() {
        if (this.fontProvider == null) {
            setProvider(DefaultFontProvider.INSTANCE);
        }
        return this.fontProvider;
    }

    public FontCache getFontCache() {
        return fontCache;
    }

    private Map<String, FontInfo> createFontInfoByName(List<? extends FontInfo> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FontInfo fontInfo : list) {
            Iterator<String> it = getPostScriptNames(fontInfo.getPostScriptName()).iterator();
            while (it.hasNext()) {
                linkedHashMap.put(it.next(), fontInfo);
            }
        }
        return linkedHashMap;
    }

    private Set<String> getPostScriptNames(String str) {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        hashSet.add(str.replaceAll("-", ""));
        return hashSet;
    }

    private List<String> copySubstitutes(String str) {
        return new ArrayList(this.substitutes.get(str));
    }

    public void addSubstitute(String str, String str2) {
        if (!this.substitutes.containsKey(str)) {
            this.substitutes.put(str, new ArrayList());
        }
        this.substitutes.get(str).add(str2);
    }

    private List<String> getSubstitutes(String str) {
        List<String> list = this.substitutes.get(str.replaceAll(" ", ""));
        return list != null ? list : Collections.emptyList();
    }

    private String getFallbackFontName(PDFontDescriptor pDFontDescriptor) {
        String str;
        if (pDFontDescriptor == null) {
            return "Times-Roman";
        }
        boolean z = false;
        if (pDFontDescriptor.getFontName() != null) {
            String lowerCase = pDFontDescriptor.getFontName().toLowerCase();
            if (lowerCase.contains(TtmlNode.BOLD) || lowerCase.contains(MediaColor.black) || lowerCase.contains("heavy")) {
                z = true;
            }
        }
        if (pDFontDescriptor.isFixedPitch()) {
            if (z && pDFontDescriptor.isItalic()) {
                str = "Courier-BoldOblique";
            } else if (z) {
                str = "Courier-Bold";
            } else {
                str = pDFontDescriptor.isItalic() ? "Courier-Oblique" : "Courier";
            }
        } else if (pDFontDescriptor.isSerif()) {
            if (z && pDFontDescriptor.isItalic()) {
                str = "Times-BoldItalic";
            } else if (z) {
                str = "Times-Bold";
            } else {
                if (!pDFontDescriptor.isItalic()) {
                    return "Times-Roman";
                }
                str = "Times-Italic";
            }
        } else if (z && pDFontDescriptor.isItalic()) {
            str = "Helvetica-BoldOblique";
        } else if (z) {
            str = "Helvetica-Bold";
        } else {
            str = pDFontDescriptor.isItalic() ? "Helvetica-Oblique" : "Helvetica";
        }
        return str;
    }

    @Override
    public FontMapping<TrueTypeFont> getTrueTypeFont(String str, PDFontDescriptor pDFontDescriptor) {
        TrueTypeFont trueTypeFont = (TrueTypeFont) findFont(FontFormat.TTF, str);
        if (trueTypeFont != null) {
            return new FontMapping<>(trueTypeFont, false);
        }
        TrueTypeFont trueTypeFont2 = (TrueTypeFont) findFont(FontFormat.TTF, getFallbackFontName(pDFontDescriptor));
        if (trueTypeFont2 == null) {
            trueTypeFont2 = this.lastResortFont;
        }
        return new FontMapping<>(trueTypeFont2, true);
    }

    @Override
    public FontMapping<FontBoxFont> getFontBoxFont(String str, PDFontDescriptor pDFontDescriptor) {
        FontBoxFont fontBoxFontFindFontBoxFont = findFontBoxFont(str);
        if (fontBoxFontFindFontBoxFont != null) {
            return new FontMapping<>(fontBoxFontFindFontBoxFont, false);
        }
        FontBoxFont fontBoxFontFindFontBoxFont2 = findFontBoxFont(getFallbackFontName(pDFontDescriptor));
        if (fontBoxFontFindFontBoxFont2 == null) {
            fontBoxFontFindFontBoxFont2 = this.lastResortFont;
        }
        return new FontMapping<>(fontBoxFontFindFontBoxFont2, true);
    }

    private FontBoxFont findFontBoxFont(String str) {
        Type1Font type1Font = (Type1Font) findFont(FontFormat.PFB, str);
        if (type1Font != null) {
            return type1Font;
        }
        CFFFont cFFFont = (CFFFont) findFont(FontFormat.OTF, str);
        if (cFFFont instanceof CFFType1Font) {
            return cFFFont;
        }
        TrueTypeFont trueTypeFont = (TrueTypeFont) findFont(FontFormat.TTF, str);
        if (trueTypeFont != null) {
            return trueTypeFont;
        }
        return null;
    }

    private FontBoxFont findFont(FontFormat fontFormat, String str) {
        if (str == null) {
            return null;
        }
        if (this.fontProvider == null) {
            getProvider();
        }
        FontInfo font = getFont(fontFormat, str);
        if (font != null) {
            return font.getFont();
        }
        FontInfo font2 = getFont(fontFormat, str.replaceAll("-", ""));
        if (font2 != null) {
            return font2.getFont();
        }
        Iterator<String> it = getSubstitutes(str).iterator();
        while (it.hasNext()) {
            FontInfo font3 = getFont(fontFormat, it.next());
            if (font3 != null) {
                return font3.getFont();
            }
        }
        FontInfo font4 = getFont(fontFormat, str.replaceAll(",", "-"));
        if (font4 != null) {
            return font4.getFont();
        }
        return null;
    }

    private FontInfo getFont(FontFormat fontFormat, String str) {
        if (str.contains(MqttTopic.SINGLE_LEVEL_WILDCARD)) {
            str = str.substring(str.indexOf(43) + 1);
        }
        FontInfo fontInfo = this.fontInfoByName.get(str);
        if (fontInfo == null || fontInfo.getFormat() != fontFormat) {
            return null;
        }
        return fontInfo;
    }

    @Override
    public CIDFontMapping getCIDFont(String str, PDFontDescriptor pDFontDescriptor, PDCIDSystemInfo pDCIDSystemInfo) {
        FontMatch fontMatchPoll;
        OpenTypeFont openTypeFont = (OpenTypeFont) findFont(FontFormat.OTF, str);
        if (openTypeFont != null) {
            return new CIDFontMapping(openTypeFont, null, false);
        }
        TrueTypeFont trueTypeFont = (TrueTypeFont) findFont(FontFormat.TTF, str);
        if (trueTypeFont != null) {
            return new CIDFontMapping(null, trueTypeFont, false);
        }
        if (pDCIDSystemInfo != null) {
            String str2 = pDCIDSystemInfo.getRegistry() + "-" + pDCIDSystemInfo.getOrdering();
            if ((str2.equals("Adobe-GB1") || str2.equals("Adobe-CNS1") || str2.equals("Adobe-Japan1") || str2.equals("Adobe-Korea1")) && (fontMatchPoll = getFontMatches(pDFontDescriptor, pDCIDSystemInfo).poll()) != null) {
                FontBoxFont font = fontMatchPoll.info.getFont();
                if (font instanceof OpenTypeFont) {
                    return new CIDFontMapping((OpenTypeFont) font, null, true);
                }
                return new CIDFontMapping(null, font, true);
            }
        }
        return new CIDFontMapping(null, this.lastResortFont, true);
    }

    private PriorityQueue<FontMatch> getFontMatches(PDFontDescriptor pDFontDescriptor, PDCIDSystemInfo pDCIDSystemInfo) {
        PriorityQueue<FontMatch> priorityQueue = new PriorityQueue<>(20);
        for (FontInfo fontInfo : this.fontInfoByName.values()) {
            if (pDCIDSystemInfo == null || isCharSetMatch(pDCIDSystemInfo, fontInfo)) {
                FontMatch fontMatch = new FontMatch(fontInfo);
                if (pDFontDescriptor.getPanose() != null && fontInfo.getPanose() != null) {
                    PDPanoseClassification panose = pDFontDescriptor.getPanose().getPanose();
                    if (panose.getFamilyKind() == fontInfo.getPanose().getFamilyKind()) {
                        if (panose.getSerifStyle() == fontInfo.getPanose().getSerifStyle()) {
                            fontMatch.score += 2.0d;
                        } else if (panose.getSerifStyle() >= 2 && panose.getSerifStyle() <= 5 && fontInfo.getPanose().getSerifStyle() >= 2 && fontInfo.getPanose().getSerifStyle() <= 5) {
                            fontMatch.score += 1.0d;
                        } else if (panose.getSerifStyle() >= 11 && panose.getSerifStyle() <= 13 && fontInfo.getPanose().getSerifStyle() >= 11 && fontInfo.getPanose().getSerifStyle() <= 13) {
                            fontMatch.score += 1.0d;
                        } else if (panose.getSerifStyle() != 0 && fontInfo.getPanose().getSerifStyle() != 0) {
                            fontMatch.score -= 1.0d;
                        }
                        int weight = fontInfo.getPanose().getWeight();
                        int weightClassAsPanose = fontInfo.getWeightClassAsPanose();
                        if (Math.abs(weight - weightClassAsPanose) > 2) {
                            weight = weightClassAsPanose;
                        }
                        if (panose.getWeight() == weight) {
                            fontMatch.score += 2.0d;
                        } else if (panose.getWeight() > 1 && weight > 1) {
                            fontMatch.score += 1.0d - (((double) Math.abs(panose.getWeight() - weight)) * 0.5d);
                        }
                    }
                } else if (pDFontDescriptor.getFontWeight() > 0.0f && fontInfo.getWeightClass() > 0) {
                    fontMatch.score += 1.0d - (((double) (Math.abs(pDFontDescriptor.getFontWeight() - fontInfo.getWeightClass()) / 100.0f)) * 0.5d);
                }
                priorityQueue.add(fontMatch);
            }
        }
        return priorityQueue;
    }

    private boolean isCharSetMatch(PDCIDSystemInfo pDCIDSystemInfo, FontInfo fontInfo) {
        if (fontInfo.getCIDSystemInfo() != null) {
            return fontInfo.getCIDSystemInfo().getRegistry().equals(pDCIDSystemInfo.getRegistry()) && fontInfo.getCIDSystemInfo().getOrdering().equals(pDCIDSystemInfo.getOrdering());
        }
        long codePageRange = fontInfo.getCodePageRange();
        if (pDCIDSystemInfo.getOrdering().equals("GB1") && (codePageRange & 262144) == 262144) {
            return true;
        }
        if (pDCIDSystemInfo.getOrdering().equals("CNS1") && (codePageRange & 1048576) == 1048576) {
            return true;
        }
        if (pDCIDSystemInfo.getOrdering().equals("Japan1") && (codePageRange & 131072) == 131072) {
            return true;
        }
        return (pDCIDSystemInfo.getOrdering().equals("Korea1") && (codePageRange & 524288) == 524288) || (codePageRange & 2097152) == 2097152;
    }

    private class FontMatch implements Comparable<FontMatch> {
        final FontInfo info;
        double score;

        FontMatch(FontInfo fontInfo) {
            this.info = fontInfo;
        }

        @Override
        public int compareTo(FontMatch fontMatch) {
            return Double.compare(fontMatch.score, this.score);
        }
    }

    private FontMatch printMatches(PriorityQueue<FontMatch> priorityQueue) {
        FontMatch fontMatchPeek = priorityQueue.peek();
        System.out.println("-------");
        while (!priorityQueue.isEmpty()) {
            FontMatch fontMatchPoll = priorityQueue.poll();
            FontInfo fontInfo = fontMatchPoll.info;
            System.out.println(fontMatchPoll.score + " | " + fontInfo.getMacStyle() + " " + fontInfo.getFamilyClass() + " " + fontInfo.getPanose() + " " + fontInfo.getCIDSystemInfo() + " " + fontInfo.getPostScriptName() + " " + fontInfo.getFormat());
        }
        System.out.println("-------");
        return fontMatchPeek;
    }
}
