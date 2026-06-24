package com.tom_roush.fontbox.cff;

import androidx.exifinterface.media.ExifInterface;
import com.tom_roush.fontbox.afm.AFMParser;
import com.tom_roush.fontbox.cff.CFFOperator;
import com.tom_roush.fontbox.ttf.CFFTable;
import com.tom_roush.fontbox.util.Charsets;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CFFParser {
    private static final String TAG_OTTO = "OTTO";
    private static final String TAG_TTCF = "ttcf";
    private static final String TAG_TTFONLY = "\u0000\u0001\u0000\u0000";
    private String debugFontName;
    private ByteSource source;
    private String[] stringIndex = null;

    public interface ByteSource {
        byte[] getBytes() throws IOException;
    }

    public List<CFFFont> parse(byte[] bArr, ByteSource byteSource) throws IOException {
        this.source = byteSource;
        return parse(bArr);
    }

    public List<CFFFont> parse(byte[] bArr) throws IOException {
        CFFDataInput cFFDataInput;
        boolean z;
        CFFDataInput cFFDataInput2 = new CFFDataInput(bArr);
        String tagName = readTagName(cFFDataInput2);
        if (TAG_OTTO.equals(tagName)) {
            short s = cFFDataInput2.readShort();
            cFFDataInput2.readShort();
            cFFDataInput2.readShort();
            cFFDataInput2.readShort();
            int i = 0;
            while (true) {
                if (i >= s) {
                    cFFDataInput = cFFDataInput2;
                    z = false;
                    break;
                }
                String tagName2 = readTagName(cFFDataInput2);
                readLong(cFFDataInput2);
                long j = readLong(cFFDataInput2);
                long j2 = readLong(cFFDataInput2);
                if (tagName2.equals(CFFTable.TAG)) {
                    int i2 = (int) j2;
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, (int) j, bArr2, 0, i2);
                    cFFDataInput = new CFFDataInput(bArr2);
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                throw new IOException("CFF tag not found in this OpenType font.");
            }
            cFFDataInput2 = cFFDataInput;
        } else {
            if (TAG_TTCF.equals(tagName)) {
                throw new IOException("True Type Collection fonts are not supported.");
            }
            if (TAG_TTFONLY.equals(tagName)) {
                throw new IOException("OpenType fonts containing a true type font are not supported.");
            }
            cFFDataInput2.setPosition(0);
        }
        readHeader(cFFDataInput2);
        String[] stringIndexData = readStringIndexData(cFFDataInput2);
        byte[][] indexData = readIndexData(cFFDataInput2);
        this.stringIndex = readStringIndexData(cFFDataInput2);
        byte[][] indexData2 = readIndexData(cFFDataInput2);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < stringIndexData.length; i3++) {
            CFFFont font = parseFont(cFFDataInput2, stringIndexData[i3], indexData[i3]);
            font.setGlobalSubrIndex(indexData2);
            font.setData(this.source);
            arrayList.add(font);
        }
        return arrayList;
    }

    private static String readTagName(CFFDataInput cFFDataInput) throws IOException {
        return new String(cFFDataInput.readBytes(4), Charsets.ISO_8859_1);
    }

    private static long readLong(CFFDataInput cFFDataInput) throws IOException {
        return cFFDataInput.readCard16() | (cFFDataInput.readCard16() << 16);
    }

    private static Header readHeader(CFFDataInput cFFDataInput) throws IOException {
        Header header = new Header();
        header.major = cFFDataInput.readCard8();
        header.minor = cFFDataInput.readCard8();
        header.hdrSize = cFFDataInput.readCard8();
        header.offSize = cFFDataInput.readOffSize();
        return header;
    }

    private static int[] readIndexDataOffsets(CFFDataInput cFFDataInput) throws IOException {
        int card16 = cFFDataInput.readCard16();
        if (card16 == 0) {
            return null;
        }
        int offSize = cFFDataInput.readOffSize();
        int[] iArr = new int[card16 + 1];
        for (int i = 0; i <= card16; i++) {
            int offset = cFFDataInput.readOffset(offSize);
            if (offset > cFFDataInput.length()) {
                throw new IOException("illegal offset value " + offset + " in CFF font");
            }
            iArr[i] = offset;
        }
        return iArr;
    }

    private static byte[][] readIndexData(CFFDataInput cFFDataInput) throws IOException {
        int[] indexDataOffsets = readIndexDataOffsets(cFFDataInput);
        if (indexDataOffsets == null) {
            return null;
        }
        int length = indexDataOffsets.length - 1;
        byte[][] bArr = new byte[length][];
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            bArr[i] = cFFDataInput.readBytes(indexDataOffsets[i2] - indexDataOffsets[i]);
            i = i2;
        }
        return bArr;
    }

    private static String[] readStringIndexData(CFFDataInput cFFDataInput) throws IOException {
        int[] indexDataOffsets = readIndexDataOffsets(cFFDataInput);
        if (indexDataOffsets == null) {
            return null;
        }
        int length = indexDataOffsets.length - 1;
        String[] strArr = new String[length];
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            strArr[i] = new String(cFFDataInput.readBytes(indexDataOffsets[i2] - indexDataOffsets[i]), Charsets.ISO_8859_1);
            i = i2;
        }
        return strArr;
    }

    private static DictData readDictData(CFFDataInput cFFDataInput) throws IOException {
        DictData dictData = new DictData();
        while (cFFDataInput.hasRemaining()) {
            dictData.add(readEntry(cFFDataInput));
        }
        return dictData;
    }

    private static DictData readDictData(CFFDataInput cFFDataInput, int i) throws IOException {
        DictData dictData = new DictData();
        int position = cFFDataInput.getPosition() + i;
        while (cFFDataInput.getPosition() < position) {
            dictData.add(readEntry(cFFDataInput));
        }
        return dictData;
    }

    private static DictData.Entry readEntry(CFFDataInput cFFDataInput) throws IOException {
        DictData.Entry entry = new DictData.Entry();
        while (true) {
            int unsignedByte = cFFDataInput.readUnsignedByte();
            if (unsignedByte >= 0 && unsignedByte <= 21) {
                entry.operator = readOperator(cFFDataInput, unsignedByte);
                return entry;
            }
            if (unsignedByte == 28 || unsignedByte == 29) {
                entry.operands.add(readIntegerNumber(cFFDataInput, unsignedByte));
            } else if (unsignedByte == 30) {
                entry.operands.add(readRealNumber(cFFDataInput, unsignedByte));
            } else {
                if (unsignedByte < 32 || unsignedByte > 254) {
                    break;
                }
                entry.operands.add(readIntegerNumber(cFFDataInput, unsignedByte));
            }
        }
        throw new IllegalArgumentException();
    }

    private static CFFOperator readOperator(CFFDataInput cFFDataInput, int i) throws IOException {
        return CFFOperator.getOperator(readOperatorKey(cFFDataInput, i));
    }

    private static CFFOperator.Key readOperatorKey(CFFDataInput cFFDataInput, int i) throws IOException {
        if (i == 12) {
            return new CFFOperator.Key(i, cFFDataInput.readUnsignedByte());
        }
        return new CFFOperator.Key(i);
    }

    private static Integer readIntegerNumber(CFFDataInput cFFDataInput, int i) throws IOException {
        if (i == 28) {
            return Integer.valueOf(cFFDataInput.readShort());
        }
        if (i == 29) {
            return Integer.valueOf(cFFDataInput.readInt());
        }
        if (i >= 32 && i <= 246) {
            return Integer.valueOf(i - 139);
        }
        if (i >= 247 && i <= 250) {
            return Integer.valueOf(((i - 247) * 256) + cFFDataInput.readUnsignedByte() + 108);
        }
        if (i >= 251 && i <= 254) {
            return Integer.valueOf((((-(i - 251)) * 256) - cFFDataInput.readUnsignedByte()) - 108);
        }
        throw new IllegalArgumentException();
    }

    private static Double readRealNumber(CFFDataInput cFFDataInput, int i) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            int unsignedByte = cFFDataInput.readUnsignedByte();
            int[] iArr = {unsignedByte / 16, unsignedByte % 16};
            for (int i2 = 0; i2 < 2; i2++) {
                int i3 = iArr[i2];
                switch (i3) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        sb.append(i3);
                        z2 = false;
                        break;
                    case 10:
                        sb.append(".");
                        break;
                    case 11:
                        sb.append(ExifInterface.LONGITUDE_EAST);
                        z2 = true;
                        break;
                    case 12:
                        sb.append("E-");
                        z2 = true;
                        break;
                    case 13:
                        break;
                    case 14:
                        sb.append("-");
                        break;
                    case 15:
                        z = true;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        if (z2) {
            sb.append(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        }
        return Double.valueOf(sb.toString());
    }

    private CFFFont parseFont(CFFDataInput cFFDataInput, String str, byte[] bArr) throws IOException {
        CFFFont cFFType1Font;
        CFFCharset cFFISOAdobeCharset;
        DictData dictData = readDictData(new CFFDataInput(bArr));
        if (dictData.getEntry("SyntheticBase") != null) {
            throw new IOException("Synthetic Fonts are not supported");
        }
        boolean z = dictData.getEntry("ROS") != null;
        if (z) {
            CFFCIDFont cFFCIDFont = new CFFCIDFont();
            DictData.Entry entry = dictData.getEntry("ROS");
            cFFCIDFont.setRegistry(readString(entry.getNumber(0).intValue()));
            cFFCIDFont.setOrdering(readString(entry.getNumber(1).intValue()));
            cFFCIDFont.setSupplement(entry.getNumber(2).intValue());
            cFFType1Font = cFFCIDFont;
        } else {
            cFFType1Font = new CFFType1Font();
        }
        this.debugFontName = str;
        cFFType1Font.setName(str);
        cFFType1Font.addValueToTopDict("version", getString(dictData, "version"));
        cFFType1Font.addValueToTopDict(AFMParser.NOTICE, getString(dictData, AFMParser.NOTICE));
        cFFType1Font.addValueToTopDict(ExifInterface.TAG_COPYRIGHT, getString(dictData, ExifInterface.TAG_COPYRIGHT));
        cFFType1Font.addValueToTopDict(AFMParser.FULL_NAME, getString(dictData, AFMParser.FULL_NAME));
        cFFType1Font.addValueToTopDict(AFMParser.FAMILY_NAME, getString(dictData, AFMParser.FAMILY_NAME));
        cFFType1Font.addValueToTopDict(AFMParser.WEIGHT, getString(dictData, AFMParser.WEIGHT));
        cFFType1Font.addValueToTopDict("isFixedPitch", dictData.getBoolean("isFixedPitch", false));
        cFFType1Font.addValueToTopDict(AFMParser.ITALIC_ANGLE, dictData.getNumber(AFMParser.ITALIC_ANGLE, 0));
        cFFType1Font.addValueToTopDict(AFMParser.UNDERLINE_POSITION, dictData.getNumber(AFMParser.UNDERLINE_POSITION, -100));
        cFFType1Font.addValueToTopDict(AFMParser.UNDERLINE_THICKNESS, dictData.getNumber(AFMParser.UNDERLINE_THICKNESS, 50));
        cFFType1Font.addValueToTopDict("PaintType", dictData.getNumber("PaintType", 0));
        cFFType1Font.addValueToTopDict("CharstringType", dictData.getNumber("CharstringType", 2));
        cFFType1Font.addValueToTopDict("FontMatrix", dictData.getArray("FontMatrix", Arrays.asList(Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d), Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d))));
        cFFType1Font.addValueToTopDict("UniqueID", dictData.getNumber("UniqueID", null));
        cFFType1Font.addValueToTopDict(AFMParser.FONT_BBOX, dictData.getArray(AFMParser.FONT_BBOX, Arrays.asList(0, 0, 0, 0)));
        cFFType1Font.addValueToTopDict("StrokeWidth", dictData.getNumber("StrokeWidth", 0));
        cFFType1Font.addValueToTopDict("XUID", dictData.getArray("XUID", null));
        cFFDataInput.setPosition(dictData.getEntry("CharStrings").getNumber(0).intValue());
        byte[][] indexData = readIndexData(cFFDataInput);
        DictData.Entry entry2 = dictData.getEntry("charset");
        if (entry2 != null) {
            int iIntValue = entry2.getNumber(0).intValue();
            if (!z && iIntValue == 0) {
                cFFISOAdobeCharset = CFFISOAdobeCharset.getInstance();
            } else if (!z && iIntValue == 1) {
                cFFISOAdobeCharset = CFFExpertCharset.getInstance();
            } else if (!z && iIntValue == 2) {
                cFFISOAdobeCharset = CFFExpertSubsetCharset.getInstance();
            } else {
                cFFDataInput.setPosition(iIntValue);
                cFFISOAdobeCharset = readCharset(cFFDataInput, indexData.length, z);
            }
        } else if (z) {
            cFFISOAdobeCharset = new EmptyCharset(indexData.length);
        } else {
            cFFISOAdobeCharset = CFFISOAdobeCharset.getInstance();
        }
        cFFType1Font.setCharset(cFFISOAdobeCharset);
        cFFType1Font.charStrings = indexData;
        if (z) {
            CFFCIDFont cFFCIDFont2 = (CFFCIDFont) cFFType1Font;
            parseCIDFontDicts(cFFDataInput, dictData, cFFCIDFont2, indexData.length);
            if (dictData.getEntry("FontMatrix") == null) {
                List<Map<String, Object>> fontDicts = cFFCIDFont2.getFontDicts();
                if (fontDicts.size() > 0 && fontDicts.get(0).containsKey("FontMatrix")) {
                    cFFType1Font.addValueToTopDict("FontMatrix", (List) fontDicts.get(0).get("FontMatrix"));
                } else {
                    cFFType1Font.addValueToTopDict("FontMatrix", dictData.getArray("FontMatrix", Arrays.asList(Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d), Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d))));
                }
            }
        } else {
            parseType1Dicts(cFFDataInput, dictData, (CFFType1Font) cFFType1Font, cFFISOAdobeCharset);
        }
        return cFFType1Font;
    }

    private void parseCIDFontDicts(CFFDataInput cFFDataInput, DictData dictData, CFFCIDFont cFFCIDFont, int i) throws IOException {
        DictData.Entry entry = dictData.getEntry("FDArray");
        if (entry == null) {
            throw new IOException("FDArray is missing for a CIDKeyed Font.");
        }
        cFFDataInput.setPosition(entry.getNumber(0).intValue());
        byte[][] indexData = readIndexData(cFFDataInput);
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        for (byte[] bArr : indexData) {
            DictData dictData2 = readDictData(new CFFDataInput(bArr));
            DictData.Entry entry2 = dictData2.getEntry(StandardStructureTypes.PRIVATE);
            if (entry2 == null) {
                throw new IOException("Font DICT invalid without \"Private\" entry");
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(AFMParser.FONT_NAME, getString(dictData2, AFMParser.FONT_NAME));
            linkedHashMap.put("FontType", dictData2.getNumber("FontType", 0));
            linkedHashMap.put(AFMParser.FONT_BBOX, dictData2.getArray(AFMParser.FONT_BBOX, null));
            linkedHashMap.put("FontMatrix", dictData2.getArray("FontMatrix", null));
            linkedList2.add(linkedHashMap);
            int iIntValue = entry2.getNumber(1).intValue();
            cFFDataInput.setPosition(iIntValue);
            DictData dictData3 = readDictData(cFFDataInput, entry2.getNumber(0).intValue());
            Map<String, Object> privateDict = readPrivateDict(dictData3);
            linkedList.add(privateDict);
            int iIntValue2 = ((Integer) dictData3.getNumber("Subrs", 0)).intValue();
            if (iIntValue2 > 0) {
                cFFDataInput.setPosition(iIntValue + iIntValue2);
                privateDict.put("Subrs", readIndexData(cFFDataInput));
            }
        }
        cFFDataInput.setPosition(dictData.getEntry("FDSelect").getNumber(0).intValue());
        FDSelect fDSelect = readFDSelect(cFFDataInput, i, cFFCIDFont);
        cFFCIDFont.setFontDict(linkedList2);
        cFFCIDFont.setPrivDict(linkedList);
        cFFCIDFont.setFdSelect(fDSelect);
    }

    private Map<String, Object> readPrivateDict(DictData dictData) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(17);
        linkedHashMap.put("BlueValues", dictData.getArray("BlueValues", null));
        linkedHashMap.put("OtherBlues", dictData.getArray("OtherBlues", null));
        linkedHashMap.put("FamilyBlues", dictData.getArray("FamilyBlues", null));
        linkedHashMap.put("FamilyOtherBlues", dictData.getArray("FamilyOtherBlues", null));
        linkedHashMap.put("BlueScale", dictData.getNumber("BlueScale", Double.valueOf(0.039625d)));
        linkedHashMap.put("BlueShift", dictData.getNumber("BlueShift", 7));
        linkedHashMap.put("BlueFuzz", dictData.getNumber("BlueFuzz", 1));
        linkedHashMap.put(AFMParser.STD_HW, dictData.getNumber(AFMParser.STD_HW, null));
        linkedHashMap.put(AFMParser.STD_VW, dictData.getNumber(AFMParser.STD_VW, null));
        linkedHashMap.put("StemSnapH", dictData.getArray("StemSnapH", null));
        linkedHashMap.put("StemSnapV", dictData.getArray("StemSnapV", null));
        linkedHashMap.put("ForceBold", dictData.getBoolean("ForceBold", false));
        linkedHashMap.put("LanguageGroup", dictData.getNumber("LanguageGroup", 0));
        linkedHashMap.put("ExpansionFactor", dictData.getNumber("ExpansionFactor", Double.valueOf(0.06d)));
        linkedHashMap.put("initialRandomSeed", dictData.getNumber("initialRandomSeed", 0));
        linkedHashMap.put("defaultWidthX", dictData.getNumber("defaultWidthX", 0));
        linkedHashMap.put("nominalWidthX", dictData.getNumber("nominalWidthX", 0));
        return linkedHashMap;
    }

    private void parseType1Dicts(CFFDataInput cFFDataInput, DictData dictData, CFFType1Font cFFType1Font, CFFCharset cFFCharset) throws IOException {
        CFFEncoding encoding;
        DictData.Entry entry = dictData.getEntry("Encoding");
        int iIntValue = entry != null ? entry.getNumber(0).intValue() : 0;
        if (iIntValue == 0) {
            encoding = CFFStandardEncoding.getInstance();
        } else if (iIntValue == 1) {
            encoding = CFFExpertEncoding.getInstance();
        } else {
            cFFDataInput.setPosition(iIntValue);
            encoding = readEncoding(cFFDataInput, cFFCharset);
        }
        cFFType1Font.setEncoding(encoding);
        DictData.Entry entry2 = dictData.getEntry(StandardStructureTypes.PRIVATE);
        if (entry2 == null) {
            throw new IOException("Private dictionary entry missing for font " + cFFType1Font.fontName);
        }
        int iIntValue2 = entry2.getNumber(1).intValue();
        cFFDataInput.setPosition(iIntValue2);
        DictData dictData2 = readDictData(cFFDataInput, entry2.getNumber(0).intValue());
        for (Map.Entry<String, Object> entry3 : readPrivateDict(dictData2).entrySet()) {
            cFFType1Font.addToPrivateDict(entry3.getKey(), entry3.getValue());
        }
        int iIntValue3 = ((Integer) dictData2.getNumber("Subrs", 0)).intValue();
        if (iIntValue3 > 0) {
            cFFDataInput.setPosition(iIntValue2 + iIntValue3);
            cFFType1Font.addToPrivateDict("Subrs", readIndexData(cFFDataInput));
        }
    }

    private String readString(int i) throws IOException {
        if (i >= 0 && i <= 390) {
            return CFFStandardString.getName(i);
        }
        int i2 = i - 391;
        String[] strArr = this.stringIndex;
        if (i2 < strArr.length) {
            return strArr[i2];
        }
        return "SID" + i;
    }

    private String getString(DictData dictData, String str) throws IOException {
        DictData.Entry entry = dictData.getEntry(str);
        if (entry != null) {
            return readString(entry.getNumber(0).intValue());
        }
        return null;
    }

    private CFFEncoding readEncoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset) throws IOException {
        int card8 = cFFDataInput.readCard8();
        int i = card8 & 127;
        if (i == 0) {
            return readFormat0Encoding(cFFDataInput, cFFCharset, card8);
        }
        if (i == 1) {
            return readFormat1Encoding(cFFDataInput, cFFCharset, card8);
        }
        throw new IllegalArgumentException();
    }

    private Format0Encoding readFormat0Encoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset, int i) throws IOException {
        Format0Encoding format0Encoding = new Format0Encoding();
        format0Encoding.format = i;
        format0Encoding.nCodes = cFFDataInput.readCard8();
        format0Encoding.add(0, 0, ".notdef");
        for (int i2 = 1; i2 <= format0Encoding.nCodes; i2++) {
            int card8 = cFFDataInput.readCard8();
            int sIDForGID = cFFCharset.getSIDForGID(i2);
            format0Encoding.add(card8, sIDForGID, readString(sIDForGID));
        }
        if ((i & 128) != 0) {
            readSupplement(cFFDataInput, format0Encoding);
        }
        return format0Encoding;
    }

    private Format1Encoding readFormat1Encoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset, int i) throws IOException {
        Format1Encoding format1Encoding = new Format1Encoding();
        format1Encoding.format = i;
        format1Encoding.nRanges = cFFDataInput.readCard8();
        format1Encoding.add(0, 0, ".notdef");
        int i2 = 1;
        for (int i3 = 0; i3 < format1Encoding.nRanges; i3++) {
            int card8 = cFFDataInput.readCard8();
            int card82 = cFFDataInput.readCard8();
            for (int i4 = 0; i4 < card82 + 1; i4++) {
                int sIDForGID = cFFCharset.getSIDForGID(i2);
                format1Encoding.add(card8 + i4, sIDForGID, readString(sIDForGID));
                i2++;
            }
        }
        if ((i & 128) != 0) {
            readSupplement(cFFDataInput, format1Encoding);
        }
        return format1Encoding;
    }

    private void readSupplement(CFFDataInput cFFDataInput, CFFBuiltInEncoding cFFBuiltInEncoding) throws IOException {
        cFFBuiltInEncoding.nSups = cFFDataInput.readCard8();
        cFFBuiltInEncoding.supplement = new CFFBuiltInEncoding.Supplement[cFFBuiltInEncoding.nSups];
        for (int i = 0; i < cFFBuiltInEncoding.supplement.length; i++) {
            CFFBuiltInEncoding.Supplement supplement = new CFFBuiltInEncoding.Supplement();
            supplement.code = cFFDataInput.readCard8();
            supplement.sid = cFFDataInput.readSID();
            supplement.name = readString(supplement.sid);
            cFFBuiltInEncoding.supplement[i] = supplement;
            cFFBuiltInEncoding.add(supplement.code, supplement.sid, readString(supplement.sid));
        }
    }

    private static FDSelect readFDSelect(CFFDataInput cFFDataInput, int i, CFFCIDFont cFFCIDFont) throws IOException {
        int card8 = cFFDataInput.readCard8();
        if (card8 == 0) {
            return readFormat0FDSelect(cFFDataInput, card8, i, cFFCIDFont);
        }
        if (card8 == 3) {
            return readFormat3FDSelect(cFFDataInput, card8, i, cFFCIDFont);
        }
        throw new IllegalArgumentException();
    }

    private static Format0FDSelect readFormat0FDSelect(CFFDataInput cFFDataInput, int i, int i2, CFFCIDFont cFFCIDFont) throws IOException {
        Format0FDSelect format0FDSelect = new Format0FDSelect(cFFCIDFont);
        format0FDSelect.format = i;
        format0FDSelect.fds = new int[i2];
        for (int i3 = 0; i3 < format0FDSelect.fds.length; i3++) {
            format0FDSelect.fds[i3] = cFFDataInput.readCard8();
        }
        return format0FDSelect;
    }

    private static Format3FDSelect readFormat3FDSelect(CFFDataInput cFFDataInput, int i, int i2, CFFCIDFont cFFCIDFont) throws IOException {
        Format3FDSelect format3FDSelect = new Format3FDSelect(cFFCIDFont);
        format3FDSelect.format = i;
        format3FDSelect.nbRanges = cFFDataInput.readCard16();
        format3FDSelect.range3 = new Range3[format3FDSelect.nbRanges];
        for (int i3 = 0; i3 < format3FDSelect.nbRanges; i3++) {
            Range3 range3 = new Range3();
            range3.first = cFFDataInput.readCard16();
            range3.f2221fd = cFFDataInput.readCard8();
            format3FDSelect.range3[i3] = range3;
        }
        format3FDSelect.sentinel = cFFDataInput.readCard16();
        return format3FDSelect;
    }

    private static final class Format3FDSelect extends FDSelect {
        private int format;
        private int nbRanges;
        private Range3[] range3;
        private int sentinel;

        private Format3FDSelect(CFFCIDFont cFFCIDFont) {
            super(cFFCIDFont);
        }

        @Override
        public int getFDIndex(int i) {
            for (int i2 = 0; i2 < this.nbRanges; i2++) {
                if (this.range3[i2].first <= i) {
                    int i3 = i2 + 1;
                    if (i3 >= this.nbRanges) {
                        if (this.sentinel > i) {
                            return this.range3[i2].f2221fd;
                        }
                        return -1;
                    }
                    if (this.range3[i3].first > i) {
                        return this.range3[i2].f2221fd;
                    }
                }
            }
            return 0;
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + " nbRanges=" + this.nbRanges + ", range3=" + Arrays.toString(this.range3) + " sentinel=" + this.sentinel + "]";
        }
    }

    private static final class Range3 {

        private int f2221fd;
        private int first;

        private Range3() {
        }

        public String toString() {
            return getClass().getName() + "[first=" + this.first + ", fd=" + this.f2221fd + "]";
        }
    }

    private static class Format0FDSelect extends FDSelect {
        private int[] fds;
        private int format;

        private Format0FDSelect(CFFCIDFont cFFCIDFont) {
            super(cFFCIDFont);
        }

        @Override
        public int getFDIndex(int i) {
            int[] iArr = this.fds;
            if (i < iArr.length) {
                return iArr[i];
            }
            return 0;
        }

        public String toString() {
            return getClass().getName() + "[fds=" + Arrays.toString(this.fds) + "]";
        }
    }

    private CFFCharset readCharset(CFFDataInput cFFDataInput, int i, boolean z) throws IOException {
        int card8 = cFFDataInput.readCard8();
        if (card8 == 0) {
            return readFormat0Charset(cFFDataInput, card8, i, z);
        }
        if (card8 == 1) {
            return readFormat1Charset(cFFDataInput, card8, i, z);
        }
        if (card8 == 2) {
            return readFormat2Charset(cFFDataInput, card8, i, z);
        }
        throw new IllegalArgumentException();
    }

    private Format0Charset readFormat0Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format0Charset format0Charset = new Format0Charset(z);
        format0Charset.format = i;
        if (z) {
            format0Charset.addCID(0, 0);
        } else {
            format0Charset.addSID(0, 0, ".notdef");
        }
        for (int i3 = 1; i3 < i2; i3++) {
            int sid = cFFDataInput.readSID();
            if (z) {
                format0Charset.addCID(i3, sid);
            } else {
                format0Charset.addSID(i3, sid, readString(sid));
            }
        }
        return format0Charset;
    }

    private Format1Charset readFormat1Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format1Charset format1Charset = new Format1Charset(z);
        format1Charset.format = i;
        if (z) {
            format1Charset.addCID(0, 0);
            format1Charset.rangesCID2GID = new ArrayList();
        } else {
            format1Charset.addSID(0, 0, ".notdef");
        }
        int i3 = 1;
        while (i3 < i2) {
            int sid = cFFDataInput.readSID();
            int card8 = cFFDataInput.readCard8();
            if (z) {
                format1Charset.rangesCID2GID.add(new RangeMapping(i3, sid, card8));
            } else {
                for (int i4 = 0; i4 < card8 + 1; i4++) {
                    int i5 = sid + i4;
                    format1Charset.addSID(i3 + i4, i5, readString(i5));
                }
            }
            i3 = i3 + card8 + 1;
        }
        return format1Charset;
    }

    private Format2Charset readFormat2Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format2Charset format2Charset = new Format2Charset(z);
        format2Charset.format = i;
        if (z) {
            format2Charset.addCID(0, 0);
            format2Charset.rangesCID2GID = new ArrayList();
        } else {
            format2Charset.addSID(0, 0, ".notdef");
        }
        int i3 = 1;
        while (i3 < i2) {
            int sid = cFFDataInput.readSID();
            int card16 = cFFDataInput.readCard16();
            if (z) {
                format2Charset.rangesCID2GID.add(new RangeMapping(i3, sid, card16));
            } else {
                for (int i4 = 0; i4 < card16 + 1; i4++) {
                    int i5 = sid + i4;
                    format2Charset.addSID(i3 + i4, i5, readString(i5));
                }
            }
            i3 = i3 + card16 + 1;
        }
        return format2Charset;
    }

    private static class Header {
        private int hdrSize;
        private int major;
        private int minor;
        private int offSize;

        private Header() {
        }

        public String toString() {
            return getClass().getName() + "[major=" + this.major + ", minor=" + this.minor + ", hdrSize=" + this.hdrSize + ", offSize=" + this.offSize + "]";
        }
    }

    private static class DictData {
        private final Map<String, Entry> entries;

        private DictData() {
            this.entries = new HashMap();
        }

        public void add(Entry entry) {
            if (entry.operator != null) {
                this.entries.put(entry.operator.getName(), entry);
            }
        }

        public Entry getEntry(String str) {
            return this.entries.get(str);
        }

        public Boolean getBoolean(String str, boolean z) {
            Entry entry = getEntry(str);
            if (entry != null) {
                z = entry.getBoolean(0).booleanValue();
            }
            return Boolean.valueOf(z);
        }

        public List<Number> getArray(String str, List<Number> list) {
            Entry entry = getEntry(str);
            return entry != null ? entry.getArray() : list;
        }

        public Number getNumber(String str, Number number) {
            Entry entry = getEntry(str);
            return entry != null ? entry.getNumber(0) : number;
        }

        public String toString() {
            return getClass().getName() + "[entries=" + this.entries + "]";
        }

        private static class Entry {
            private List<Number> operands;
            private CFFOperator operator;

            private Entry() {
                this.operands = new ArrayList();
                this.operator = null;
            }

            public Number getNumber(int i) {
                return this.operands.get(i);
            }

            public Boolean getBoolean(int i) {
                Number number = this.operands.get(i);
                if (number instanceof Integer) {
                    int iIntValue = number.intValue();
                    if (iIntValue == 0) {
                        return Boolean.FALSE;
                    }
                    if (iIntValue == 1) {
                        return Boolean.TRUE;
                    }
                }
                throw new IllegalArgumentException();
            }

            public List<Number> getArray() {
                return this.operands;
            }

            public String toString() {
                return getClass().getName() + "[operands=" + this.operands + ", operator=" + this.operator + "]";
            }
        }
    }

    static abstract class CFFBuiltInEncoding extends CFFEncoding {
        private int nSups;
        private Supplement[] supplement;

        CFFBuiltInEncoding() {
        }

        static class Supplement {
            private int code;
            private String name;
            private int sid;

            Supplement() {
            }

            public int getCode() {
                return this.code;
            }

            public int getSID() {
                return this.sid;
            }

            public String getName() {
                return this.name;
            }

            public String toString() {
                return getClass().getName() + "[code=" + this.code + ", sid=" + this.sid + "]";
            }
        }
    }

    private static class Format0Encoding extends CFFBuiltInEncoding {
        private int format;
        private int nCodes;

        private Format0Encoding() {
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", nCodes=" + this.nCodes + ", supplement=" + Arrays.toString(((CFFBuiltInEncoding) this).supplement) + "]";
        }
    }

    private static class Format1Encoding extends CFFBuiltInEncoding {
        private int format;
        private int nRanges;

        private Format1Encoding() {
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", nRanges=" + this.nRanges + ", supplement=" + Arrays.toString(((CFFBuiltInEncoding) this).supplement) + "]";
        }
    }

    static abstract class EmbeddedCharset extends CFFCharset {
        protected EmbeddedCharset(boolean z) {
            super(z);
        }
    }

    private static class EmptyCharset extends EmbeddedCharset {
        protected EmptyCharset(int i) {
            super(true);
            addCID(0, 0);
            for (int i2 = 1; i2 <= i; i2++) {
                addCID(i2, i2);
            }
        }

        public String toString() {
            return getClass().getName();
        }
    }

    private static class Format0Charset extends EmbeddedCharset {
        private int format;

        protected Format0Charset(boolean z) {
            super(z);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + "]";
        }
    }

    private static class Format1Charset extends EmbeddedCharset {
        private int format;
        private List<RangeMapping> rangesCID2GID;

        protected Format1Charset(boolean z) {
            super(z);
        }

        @Override
        public int getCIDForGID(int i) {
            if (isCIDFont()) {
                for (RangeMapping rangeMapping : this.rangesCID2GID) {
                    if (rangeMapping.isInRange(i)) {
                        return rangeMapping.mapValue(i);
                    }
                }
            }
            return super.getCIDForGID(i);
        }

        @Override
        public int getGIDForCID(int i) {
            if (isCIDFont()) {
                for (RangeMapping rangeMapping : this.rangesCID2GID) {
                    if (rangeMapping.isInReverseRange(i)) {
                        return rangeMapping.mapReverseValue(i);
                    }
                }
            }
            return super.getGIDForCID(i);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + "]";
        }
    }

    private static class Format2Charset extends EmbeddedCharset {
        private int format;
        private List<RangeMapping> rangesCID2GID;

        protected Format2Charset(boolean z) {
            super(z);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + "]";
        }
    }

    private static final class RangeMapping {
        private final int endMappedValue;
        private final int endValue;
        private final int startMappedValue;
        private final int startValue;

        private RangeMapping(int i, int i2, int i3) {
            this.startValue = i;
            this.endValue = i + i3;
            this.startMappedValue = i2;
            this.endMappedValue = i2 + i3;
        }

        boolean isInRange(int i) {
            return i >= this.startValue && i <= this.endValue;
        }

        boolean isInReverseRange(int i) {
            return i >= this.startMappedValue && i <= this.endMappedValue;
        }

        int mapValue(int i) {
            if (isInRange(i)) {
                return this.startMappedValue + (i - this.startValue);
            }
            return 0;
        }

        int mapReverseValue(int i) {
            if (isInReverseRange(i)) {
                return this.startValue + (i - this.startMappedValue);
            }
            return 0;
        }

        public String toString() {
            return getClass().getName() + "[start value=" + this.startValue + ", end value=" + this.endValue + ", start mapped-value=" + this.startMappedValue + ", end mapped-value=" + this.endMappedValue + "]";
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.debugFontName + "]";
    }
}
