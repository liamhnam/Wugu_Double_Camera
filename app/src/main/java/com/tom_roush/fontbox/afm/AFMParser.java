package com.tom_roush.fontbox.afm;

import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.fontbox.util.Charsets;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AFMParser {
    public static final String ASCENDER = "Ascender";
    private static final int BITS_IN_HEX = 16;
    public static final String CAP_HEIGHT = "CapHeight";

    public static final String f2212CC = "CC";
    public static final String CHARACTERS = "Characters";
    public static final String CHARACTER_SET = "CharacterSet";
    public static final String CHARMETRICS_B = "B";
    public static final String CHARMETRICS_C = "C";
    public static final String CHARMETRICS_CH = "CH";
    public static final String CHARMETRICS_L = "L";
    public static final String CHARMETRICS_N = "N";
    public static final String CHARMETRICS_VV = "VV";
    public static final String CHARMETRICS_W = "W";
    public static final String CHARMETRICS_W0 = "W0";
    public static final String CHARMETRICS_W0X = "W0X";
    public static final String CHARMETRICS_W0Y = "W0Y";
    public static final String CHARMETRICS_W1 = "W1";
    public static final String CHARMETRICS_W1X = "W1X";
    public static final String CHARMETRICS_W1Y = "W1Y";
    public static final String CHARMETRICS_WX = "WX";
    public static final String CHARMETRICS_WY = "WY";
    public static final String CHAR_WIDTH = "CharWidth";
    public static final String COMMENT = "Comment";
    public static final String DESCENDER = "Descender";
    public static final String ENCODING_SCHEME = "EncodingScheme";
    public static final String END_CHAR_METRICS = "EndCharMetrics";
    public static final String END_COMPOSITES = "EndComposites";
    public static final String END_FONT_METRICS = "EndFontMetrics";
    public static final String END_KERN_DATA = "EndKernData";
    public static final String END_KERN_PAIRS = "EndKernPairs";
    public static final String END_TRACK_KERN = "EndTrackKern";
    public static final String ESC_CHAR = "EscChar";
    public static final String FAMILY_NAME = "FamilyName";
    public static final String FONT_BBOX = "FontBBox";
    public static final String FONT_NAME = "FontName";
    public static final String FULL_NAME = "FullName";
    public static final String IS_BASE_FONT = "IsBaseFont";
    public static final String IS_FIXED_PITCH = "IsFixedPitch";
    public static final String IS_FIXED_V = "IsFixedV";
    public static final String ITALIC_ANGLE = "ItalicAngle";
    public static final String KERN_PAIR_KP = "KP";
    public static final String KERN_PAIR_KPH = "KPH";
    public static final String KERN_PAIR_KPX = "KPX";
    public static final String KERN_PAIR_KPY = "KPY";
    public static final String MAPPING_SCHEME = "MappingScheme";
    public static final String NOTICE = "Notice";
    public static final String PCC = "PCC";
    public static final String START_CHAR_METRICS = "StartCharMetrics";
    public static final String START_COMPOSITES = "StartComposites";
    public static final String START_FONT_METRICS = "StartFontMetrics";
    public static final String START_KERN_DATA = "StartKernData";
    public static final String START_KERN_PAIRS = "StartKernPairs";
    public static final String START_KERN_PAIRS0 = "StartKernPairs0";
    public static final String START_KERN_PAIRS1 = "StartKernPairs1";
    public static final String START_TRACK_KERN = "StartTrackKern";
    public static final String STD_HW = "StdHW";
    public static final String STD_VW = "StdVW";
    public static final String UNDERLINE_POSITION = "UnderlinePosition";
    public static final String UNDERLINE_THICKNESS = "UnderlineThickness";
    public static final String VERSION = "Version";
    public static final String V_VECTOR = "VVector";
    public static final String WEIGHT = "Weight";
    public static final String X_HEIGHT = "XHeight";
    private final InputStream input;

    private boolean isEOL(int i) {
        return i == 13 || i == 10;
    }

    private boolean isWhitespace(int i) {
        return i == 32 || i == 9 || i == 13 || i == 10;
    }

    public AFMParser(InputStream inputStream) {
        this.input = inputStream;
    }

    public FontMetrics parse() throws IOException {
        return parseFontMetric(false);
    }

    public FontMetrics parse(boolean z) throws IOException {
        return parseFontMetric(z);
    }

    private FontMetrics parseFontMetric(boolean z) throws IOException {
        String string;
        FontMetrics fontMetrics = new FontMetrics();
        String string2 = readString();
        if (!START_FONT_METRICS.equals(string2)) {
            throw new IOException("Error: The AFM file should start with StartFontMetrics and not '" + string2 + "'");
        }
        fontMetrics.setAFMVersion(readFloat());
        boolean z2 = false;
        while (true) {
            string = readString();
            if (END_FONT_METRICS.equals(string)) {
                break;
            }
            if (FONT_NAME.equals(string)) {
                fontMetrics.setFontName(readLine());
            } else if (FULL_NAME.equals(string)) {
                fontMetrics.setFullName(readLine());
            } else if (FAMILY_NAME.equals(string)) {
                fontMetrics.setFamilyName(readLine());
            } else if (WEIGHT.equals(string)) {
                fontMetrics.setWeight(readLine());
            } else if (FONT_BBOX.equals(string)) {
                BoundingBox boundingBox = new BoundingBox();
                boundingBox.setLowerLeftX(readFloat());
                boundingBox.setLowerLeftY(readFloat());
                boundingBox.setUpperRightX(readFloat());
                boundingBox.setUpperRightY(readFloat());
                fontMetrics.setFontBBox(boundingBox);
            } else if (VERSION.equals(string)) {
                fontMetrics.setFontVersion(readLine());
            } else if (NOTICE.equals(string)) {
                fontMetrics.setNotice(readLine());
            } else if (ENCODING_SCHEME.equals(string)) {
                fontMetrics.setEncodingScheme(readLine());
            } else if (MAPPING_SCHEME.equals(string)) {
                fontMetrics.setMappingScheme(readInt());
            } else if (ESC_CHAR.equals(string)) {
                fontMetrics.setEscChar(readInt());
            } else if (CHARACTER_SET.equals(string)) {
                fontMetrics.setCharacterSet(readLine());
            } else if (CHARACTERS.equals(string)) {
                fontMetrics.setCharacters(readInt());
            } else if (IS_BASE_FONT.equals(string)) {
                fontMetrics.setIsBaseFont(readBoolean());
            } else if (V_VECTOR.equals(string)) {
                fontMetrics.setVVector(new float[]{readFloat(), readFloat()});
            } else if (IS_FIXED_V.equals(string)) {
                fontMetrics.setIsFixedV(readBoolean());
            } else if (CAP_HEIGHT.equals(string)) {
                fontMetrics.setCapHeight(readFloat());
            } else if (X_HEIGHT.equals(string)) {
                fontMetrics.setXHeight(readFloat());
            } else if (ASCENDER.equals(string)) {
                fontMetrics.setAscender(readFloat());
            } else if (DESCENDER.equals(string)) {
                fontMetrics.setDescender(readFloat());
            } else if (STD_HW.equals(string)) {
                fontMetrics.setStandardHorizontalWidth(readFloat());
            } else if (STD_VW.equals(string)) {
                fontMetrics.setStandardVerticalWidth(readFloat());
            } else if ("Comment".equals(string)) {
                fontMetrics.addComment(readLine());
            } else if (UNDERLINE_POSITION.equals(string)) {
                fontMetrics.setUnderlinePosition(readFloat());
            } else if (UNDERLINE_THICKNESS.equals(string)) {
                fontMetrics.setUnderlineThickness(readFloat());
            } else if (ITALIC_ANGLE.equals(string)) {
                fontMetrics.setItalicAngle(readFloat());
            } else if (CHAR_WIDTH.equals(string)) {
                fontMetrics.setCharWidth(new float[]{readFloat(), readFloat()});
            } else if (IS_FIXED_PITCH.equals(string)) {
                fontMetrics.setFixedPitch(readBoolean());
            } else if (START_CHAR_METRICS.equals(string)) {
                int i = readInt();
                ArrayList arrayList = new ArrayList(i);
                for (int i2 = 0; i2 < i; i2++) {
                    arrayList.add(parseCharMetric());
                }
                String string3 = readString();
                if (!string3.equals(END_CHAR_METRICS)) {
                    throw new IOException("Error: Expected 'EndCharMetrics' actual '" + string3 + "'");
                }
                fontMetrics.setCharMetrics(arrayList);
                z2 = true;
            } else if (!z && START_COMPOSITES.equals(string)) {
                int i3 = readInt();
                for (int i4 = 0; i4 < i3; i4++) {
                    fontMetrics.addComposite(parseComposite());
                }
                String string4 = readString();
                if (!string4.equals(END_COMPOSITES)) {
                    throw new IOException("Error: Expected 'EndComposites' actual '" + string4 + "'");
                }
            } else {
                if (z || !START_KERN_DATA.equals(string)) {
                    break;
                }
                parseKernData(fontMetrics);
            }
        }
        if (!z || !z2) {
            throw new IOException("Unknown AFM key '" + string + "'");
        }
        return fontMetrics;
    }

    private void parseKernData(FontMetrics fontMetrics) throws IOException {
        while (true) {
            String string = readString();
            if (string.equals(END_KERN_DATA)) {
                return;
            }
            int i = 0;
            if (START_TRACK_KERN.equals(string)) {
                int i2 = readInt();
                while (i < i2) {
                    TrackKern trackKern = new TrackKern();
                    trackKern.setDegree(readInt());
                    trackKern.setMinPointSize(readFloat());
                    trackKern.setMinKern(readFloat());
                    trackKern.setMaxPointSize(readFloat());
                    trackKern.setMaxKern(readFloat());
                    fontMetrics.addTrackKern(trackKern);
                    i++;
                }
                String string2 = readString();
                if (!string2.equals(END_TRACK_KERN)) {
                    throw new IOException("Error: Expected 'EndTrackKern' actual '" + string2 + "'");
                }
            } else if (START_KERN_PAIRS.equals(string)) {
                int i3 = readInt();
                while (i < i3) {
                    fontMetrics.addKernPair(parseKernPair());
                    i++;
                }
                String string3 = readString();
                if (!string3.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + string3 + "'");
                }
            } else if (START_KERN_PAIRS0.equals(string)) {
                int i4 = readInt();
                while (i < i4) {
                    fontMetrics.addKernPair0(parseKernPair());
                    i++;
                }
                String string4 = readString();
                if (!string4.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + string4 + "'");
                }
            } else if (START_KERN_PAIRS1.equals(string)) {
                int i5 = readInt();
                while (i < i5) {
                    fontMetrics.addKernPair1(parseKernPair());
                    i++;
                }
                String string5 = readString();
                if (!string5.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + string5 + "'");
                }
            } else {
                throw new IOException("Unknown kerning data type '" + string + "'");
            }
        }
    }

    private KernPair parseKernPair() throws IOException {
        KernPair kernPair = new KernPair();
        String string = readString();
        if (KERN_PAIR_KP.equals(string)) {
            String string2 = readString();
            String string3 = readString();
            float f = readFloat();
            float f2 = readFloat();
            kernPair.setFirstKernCharacter(string2);
            kernPair.setSecondKernCharacter(string3);
            kernPair.setX(f);
            kernPair.setY(f2);
        } else if (KERN_PAIR_KPH.equals(string)) {
            String strHexToString = hexToString(readString());
            String strHexToString2 = hexToString(readString());
            float f3 = readFloat();
            float f4 = readFloat();
            kernPair.setFirstKernCharacter(strHexToString);
            kernPair.setSecondKernCharacter(strHexToString2);
            kernPair.setX(f3);
            kernPair.setY(f4);
        } else if (KERN_PAIR_KPX.equals(string)) {
            String string4 = readString();
            String string5 = readString();
            float f5 = readFloat();
            kernPair.setFirstKernCharacter(string4);
            kernPair.setSecondKernCharacter(string5);
            kernPair.setX(f5);
            kernPair.setY(0.0f);
        } else if (KERN_PAIR_KPY.equals(string)) {
            String string6 = readString();
            String string7 = readString();
            float f6 = readFloat();
            kernPair.setFirstKernCharacter(string6);
            kernPair.setSecondKernCharacter(string7);
            kernPair.setX(0.0f);
            kernPair.setY(f6);
        } else {
            throw new IOException("Error expected kern pair command actual='" + string + "'");
        }
        return kernPair;
    }

    private String hexToString(String str) throws IOException {
        if (str.length() < 2) {
            throw new IOException("Error: Expected hex string of length >= 2 not='" + str);
        }
        if (str.charAt(0) != '<' || str.charAt(str.length() - 1) != '>') {
            throw new IOException("String should be enclosed by angle brackets '" + str + "'");
        }
        String strSubstring = str.substring(1, str.length() - 1);
        byte[] bArr = new byte[strSubstring.length() / 2];
        for (int i = 0; i < strSubstring.length(); i += 2) {
            try {
                bArr[i / 2] = (byte) Integer.parseInt("" + strSubstring.charAt(i) + strSubstring.charAt(i + 1), 16);
            } catch (NumberFormatException e) {
                throw new IOException("Error parsing AFM file:" + e);
            }
        }
        return new String(bArr, Charsets.ISO_8859_1);
    }

    private Composite parseComposite() throws IOException {
        Composite composite = new Composite();
        StringTokenizer stringTokenizer = new StringTokenizer(readLine(), " ;");
        String strNextToken = stringTokenizer.nextToken();
        if (!strNextToken.equals(f2212CC)) {
            throw new IOException("Expected 'CC' actual='" + strNextToken + "'");
        }
        composite.setName(stringTokenizer.nextToken());
        try {
            int i = Integer.parseInt(stringTokenizer.nextToken());
            for (int i2 = 0; i2 < i; i2++) {
                CompositePart compositePart = new CompositePart();
                String strNextToken2 = stringTokenizer.nextToken();
                if (!strNextToken2.equals(PCC)) {
                    throw new IOException("Expected 'PCC' actual='" + strNextToken2 + "'");
                }
                String strNextToken3 = stringTokenizer.nextToken();
                try {
                    int i3 = Integer.parseInt(stringTokenizer.nextToken());
                    int i4 = Integer.parseInt(stringTokenizer.nextToken());
                    compositePart.setName(strNextToken3);
                    compositePart.setXDisplacement(i3);
                    compositePart.setYDisplacement(i4);
                    composite.addPart(compositePart);
                } catch (NumberFormatException e) {
                    throw new IOException("Error parsing AFM document:" + e);
                }
            }
            return composite;
        } catch (NumberFormatException e2) {
            throw new IOException("Error parsing AFM document:" + e2);
        }
    }

    private CharMetric parseCharMetric() throws IOException {
        CharMetric charMetric = new CharMetric();
        StringTokenizer stringTokenizer = new StringTokenizer(readLine());
        while (stringTokenizer.hasMoreTokens()) {
            try {
                String strNextToken = stringTokenizer.nextToken();
                if (strNextToken.equals("C")) {
                    charMetric.setCharacterCode(Integer.parseInt(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_CH)) {
                    charMetric.setCharacterCode(Integer.parseInt(stringTokenizer.nextToken(), 16));
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_WX)) {
                    charMetric.setWx(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_W0X)) {
                    charMetric.setW0x(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_W1X)) {
                    charMetric.setW0x(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_WY)) {
                    charMetric.setWy(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_W0Y)) {
                    charMetric.setW0y(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_W1Y)) {
                    charMetric.setW0y(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals("W")) {
                    charMetric.setW(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_W0)) {
                    charMetric.setW0(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_W1)) {
                    charMetric.setW1(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals(CHARMETRICS_VV)) {
                    charMetric.setVv(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals("N")) {
                    charMetric.setName(stringTokenizer.nextToken());
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals("B")) {
                    String strNextToken2 = stringTokenizer.nextToken();
                    String strNextToken3 = stringTokenizer.nextToken();
                    String strNextToken4 = stringTokenizer.nextToken();
                    String strNextToken5 = stringTokenizer.nextToken();
                    BoundingBox boundingBox = new BoundingBox();
                    boundingBox.setLowerLeftX(Float.parseFloat(strNextToken2));
                    boundingBox.setLowerLeftY(Float.parseFloat(strNextToken3));
                    boundingBox.setUpperRightX(Float.parseFloat(strNextToken4));
                    boundingBox.setUpperRightY(Float.parseFloat(strNextToken5));
                    charMetric.setBoundingBox(boundingBox);
                    verifySemicolon(stringTokenizer);
                } else if (strNextToken.equals("L")) {
                    String strNextToken6 = stringTokenizer.nextToken();
                    String strNextToken7 = stringTokenizer.nextToken();
                    Ligature ligature = new Ligature();
                    ligature.setSuccessor(strNextToken6);
                    ligature.setLigature(strNextToken7);
                    charMetric.addLigature(ligature);
                    verifySemicolon(stringTokenizer);
                } else {
                    throw new IOException("Unknown CharMetrics command '" + strNextToken + "'");
                }
            } catch (NumberFormatException e) {
                throw new IOException("Error: Corrupt AFM document:" + e);
            }
        }
        return charMetric;
    }

    private void verifySemicolon(StringTokenizer stringTokenizer) throws IOException {
        if (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if (!strNextToken.equals(";")) {
                throw new IOException("Error: Expected semicolon in stream actual='" + strNextToken + "'");
            }
            return;
        }
        throw new IOException("CharMetrics is missing a semicolon after a command");
    }

    private boolean readBoolean() throws IOException {
        return Boolean.valueOf(readString()).booleanValue();
    }

    private int readInt() throws IOException {
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing AFM document:" + e);
        }
    }

    private float readFloat() throws IOException {
        return Float.parseFloat(readString());
    }

    private String readLine() throws IOException {
        StringBuilder sb = new StringBuilder(60);
        int i = this.input.read();
        while (isWhitespace(i)) {
            i = this.input.read();
        }
        sb.append((char) i);
        while (true) {
            int i2 = this.input.read();
            if (!isEOL(i2)) {
                sb.append((char) i2);
            } else {
                return sb.toString();
            }
        }
    }

    private String readString() throws IOException {
        StringBuilder sb = new StringBuilder(24);
        int i = this.input.read();
        while (isWhitespace(i)) {
            i = this.input.read();
        }
        sb.append((char) i);
        while (true) {
            int i2 = this.input.read();
            if (!isWhitespace(i2)) {
                sb.append((char) i2);
            } else {
                return sb.toString();
            }
        }
    }
}
