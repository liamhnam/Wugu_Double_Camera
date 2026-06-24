package com.tom_roush.pdfbox.cos;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.internal.ViewUtils;
import com.tom_roush.fontbox.afm.AFMParser;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionURI;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDRectlinearMeasureDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHeaders;
import org.apache.log4j.HTMLLayout;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class COSName extends COSBase implements Comparable<COSName> {
    private final int hashCode;
    private final String name;
    private static Map<String, COSName> nameMap = new ConcurrentHashMap(8192);
    private static Map<String, COSName> commonNameMap = new HashMap(ViewUtils.EDGE_TO_EDGE_FLAGS);

    public static final COSName f2228A = new COSName("A");

    public static final COSName f2229AA = new COSName("AA");
    public static final COSName ACRO_FORM = new COSName("AcroForm");
    public static final COSName ACTUAL_TEXT = new COSName("ActualText");
    public static final COSName ADBE_PKCS7_DETACHED = new COSName("adbe.pkcs7.detached");
    public static final COSName ADBE_PKCS7_SHA1 = new COSName("adbe.pkcs7.sha1");
    public static final COSName ADBE_X509_RSA_SHA1 = new COSName("adbe.x509.rsa_sha1");
    public static final COSName ADOBE_PPKLITE = new COSName("Adobe.PPKLite");
    public static final COSName AESV2 = new COSName("AESV2");
    public static final COSName AESV3 = new COSName("AESV3");
    public static final COSName AFTER = new COSName("After");
    public static final COSName AIS = new COSName("AIS");
    public static final COSName ALT = new COSName("Alt");
    public static final COSName ALPHA = new COSName("Alpha");
    public static final COSName ALTERNATE = new COSName("Alternate");
    public static final COSName ANNOT = new COSName(StandardStructureTypes.ANNOT);
    public static final COSName ANNOTS = new COSName("Annots");
    public static final COSName ANTI_ALIAS = new COSName("AntiAlias");

    public static final COSName f2230AP = new COSName("AP");
    public static final COSName AP_REF = new COSName("APRef");
    public static final COSName APP = new COSName("App");
    public static final COSName ART_BOX = new COSName(PDViewerPreferences.BOUNDARY_ART_BOX);
    public static final COSName ARTIFACT = new COSName("Artifact");

    public static final COSName f2231AS = new COSName("AS");
    public static final COSName ASCENT = new COSName("Ascent");
    public static final COSName ASCII_HEX_DECODE = new COSName("ASCIIHexDecode");
    public static final COSName ASCII_HEX_DECODE_ABBREVIATION = new COSName("AHx");
    public static final COSName ASCII85_DECODE = new COSName("ASCII85Decode");
    public static final COSName ASCII85_DECODE_ABBREVIATION = new COSName("A85");
    public static final COSName ATTACHED = new COSName("Attached");
    public static final COSName AUTHOR = new COSName("Author");
    public static final COSName AVG_WIDTH = new COSName("AvgWidth");

    public static final COSName f2232B = new COSName("B");
    public static final COSName BACKGROUND = new COSName("Background");
    public static final COSName BASE_ENCODING = new COSName("BaseEncoding");
    public static final COSName BASE_FONT = new COSName("BaseFont");
    public static final COSName BASE_STATE = new COSName("BaseState");
    public static final COSName BBOX = new COSName("BBox");

    public static final COSName f2233BC = new COSName(BouncyCastleProvider.PROVIDER_NAME);

    public static final COSName f2234BE = new COSName("BE");
    public static final COSName BEFORE = new COSName("Before");

    public static final COSName f2235BG = new COSName("BG");
    public static final COSName BITS_PER_COMPONENT = new COSName("BitsPerComponent");
    public static final COSName BITS_PER_COORDINATE = new COSName("BitsPerCoordinate");
    public static final COSName BITS_PER_FLAG = new COSName("BitsPerFlag");
    public static final COSName BITS_PER_SAMPLE = new COSName(ExifInterface.TAG_BITS_PER_SAMPLE);
    public static final COSName BLACK_IS_1 = new COSName("BlackIs1");
    public static final COSName BLACK_POINT = new COSName("BlackPoint");
    public static final COSName BLEED_BOX = new COSName(PDViewerPreferences.BOUNDARY_BLEED_BOX);

    public static final COSName f2236BM = new COSName("BM");
    public static final COSName BORDER = new COSName("Border");
    public static final COSName BOUNDS = new COSName("Bounds");
    public static final COSName BPC = new COSName("BPC");

    public static final COSName f2237BS = new COSName("BS");
    public static final COSName BTN = new COSName("Btn");
    public static final COSName BYTERANGE = new COSName("ByteRange");

    public static final COSName f2238C = new COSName("C");

    public static final COSName f2239C0 = new COSName("C0");

    public static final COSName f2240C1 = new COSName("C1");

    public static final COSName f2241CA = new COSName("CA");
    public static final COSName CA_NS = new COSName("ca");
    public static final COSName CALGRAY = new COSName("CalGray");
    public static final COSName CALRGB = new COSName("CalRGB");
    public static final COSName CAP = new COSName("Cap");
    public static final COSName CAP_HEIGHT = new COSName(AFMParser.CAP_HEIGHT);
    public static final COSName CATALOG = new COSName("Catalog");
    public static final COSName CCITTFAX_DECODE = new COSName("CCITTFaxDecode");
    public static final COSName CCITTFAX_DECODE_ABBREVIATION = new COSName("CCF");
    public static final COSName CENTER_WINDOW = new COSName("CenterWindow");

    public static final COSName f2242CF = new COSName("CF");
    public static final COSName CFM = new COSName("CFM");

    public static final COSName f2243CH = new COSName("Ch");
    public static final COSName CHAR_PROCS = new COSName("CharProcs");
    public static final COSName CHAR_SET = new COSName("CharSet");
    public static final COSName CICI_SIGNIT = new COSName("CICI.SignIt");
    public static final COSName CID_FONT_TYPE0 = new COSName("CIDFontType0");
    public static final COSName CID_FONT_TYPE2 = new COSName("CIDFontType2");
    public static final COSName CID_TO_GID_MAP = new COSName("CIDToGIDMap");
    public static final COSName CID_SET = new COSName("CIDSet");
    public static final COSName CIDSYSTEMINFO = new COSName("CIDSystemInfo");

    public static final COSName f2244CL = new COSName("CL");
    public static final COSName CLR_F = new COSName("ClrF");
    public static final COSName CLR_FF = new COSName("ClrFf");
    public static final COSName CMAP = new COSName("CMap");
    public static final COSName CMAPNAME = new COSName("CMapName");
    public static final COSName CMYK = new COSName("CMYK");

    public static final COSName f2245CO = new COSName("CO");
    public static final COSName COLOR_BURN = new COSName("ColorBurn");
    public static final COSName COLOR_DODGE = new COSName("ColorDodge");
    public static final COSName COLORANTS = new COSName("Colorants");
    public static final COSName COLORS = new COSName("Colors");
    public static final COSName COLORSPACE = new COSName(ExifInterface.TAG_COLOR_SPACE);
    public static final COSName COLUMNS = new COSName("Columns");
    public static final COSName COMPATIBLE = new COSName("Compatible");
    public static final COSName COMPONENTS = new COSName("Components");
    public static final COSName CONTACT_INFO = new COSName("ContactInfo");
    public static final COSName CONTENTS = new COSName("Contents");
    public static final COSName COORDS = new COSName("Coords");
    public static final COSName COUNT = new COSName("Count");

    public static final COSName f2246CP = new COSName("CP");
    public static final COSName CREATION_DATE = new COSName("CreationDate");
    public static final COSName CREATOR = new COSName("Creator");
    public static final COSName CROP_BOX = new COSName(PDViewerPreferences.BOUNDARY_CROP_BOX);
    public static final COSName CRYPT = new COSName("Crypt");

    public static final COSName f2247CS = new COSName("CS");

    public static final COSName f2248D = new COSName("D");

    public static final COSName f2249DA = new COSName("DA");
    public static final COSName DARKEN = new COSName("Darken");
    public static final COSName DATE = new COSName("Date");
    public static final COSName DCT_DECODE = new COSName("DCTDecode");
    public static final COSName DCT_DECODE_ABBREVIATION = new COSName("DCT");
    public static final COSName DECODE = new COSName("Decode");
    public static final COSName DECODE_PARMS = new COSName("DecodeParms");
    public static final COSName DEFAULT = new COSName("default");
    public static final COSName DEFAULT_CMYK = new COSName("DefaultCMYK");
    public static final COSName DEFAULT_GRAY = new COSName("DefaultGray");
    public static final COSName DEFAULT_RGB = new COSName("DefaultRGB");
    public static final COSName DESC = new COSName("Desc");
    public static final COSName DESCENDANT_FONTS = new COSName("DescendantFonts");
    public static final COSName DESCENT = new COSName("Descent");
    public static final COSName DEST = new COSName("Dest");
    public static final COSName DEST_OUTPUT_PROFILE = new COSName("DestOutputProfile");
    public static final COSName DESTS = new COSName("Dests");
    public static final COSName DEVICECMYK = new COSName("DeviceCMYK");
    public static final COSName DEVICEGRAY = new COSName("DeviceGray");
    public static final COSName DEVICEN = new COSName("DeviceN");
    public static final COSName DEVICERGB = new COSName("DeviceRGB");

    public static final COSName f2250DI = new COSName("Di");
    public static final COSName DIFFERENCE = new COSName("Difference");
    public static final COSName DIFFERENCES = new COSName("Differences");
    public static final COSName DIGEST_METHOD = new COSName("DigestMethod");
    public static final COSName DIGEST_RIPEMD160 = new COSName("RIPEMD160");
    public static final COSName DIGEST_SHA1 = new COSName("SHA1");
    public static final COSName DIGEST_SHA256 = new COSName("SHA256");
    public static final COSName DIGEST_SHA384 = new COSName("SHA384");
    public static final COSName DIGEST_SHA512 = new COSName("SHA512");
    public static final COSName DIRECTION = new COSName("Direction");
    public static final COSName DISPLAY_DOC_TITLE = new COSName("DisplayDocTitle");

    public static final COSName f2251DL = new COSName(ASN1Encoding.f2807DL);

    public static final COSName f2252DM = new COSName("Dm");
    public static final COSName DOC = new COSName("Doc");
    public static final COSName DOC_CHECKSUM = new COSName("DocChecksum");
    public static final COSName DOC_TIME_STAMP = new COSName("DocTimeStamp");
    public static final COSName DOMAIN = new COSName("Domain");
    public static final COSName DOS = new COSName("DOS");

    public static final COSName f2253DP = new COSName("DP");

    public static final COSName f2254DR = new COSName("DR");

    public static final COSName f2255DS = new COSName("DS");
    public static final COSName DUPLEX = new COSName("Duplex");
    public static final COSName DUR = new COSName("Dur");

    public static final COSName f2256DV = new COSName("DV");

    public static final COSName f2257DW = new COSName("DW");
    public static final COSName DW2 = new COSName("DW2");

    public static final COSName f2258E = new COSName(ExifInterface.LONGITUDE_EAST);
    public static final COSName EARLY_CHANGE = new COSName("EarlyChange");

    public static final COSName f2259EF = new COSName("EF");
    public static final COSName EMBEDDED_FDFS = new COSName("EmbeddedFDFs");
    public static final COSName EMBEDDED_FILES = new COSName("EmbeddedFiles");
    public static final COSName EMPTY = new COSName("");
    public static final COSName ENCODE = new COSName("Encode");
    public static final COSName ENCODED_BYTE_ALIGN = new COSName("EncodedByteAlign");
    public static final COSName ENCODING = new COSName("Encoding");
    public static final COSName ENCODING_90MS_RKSJ_H = new COSName("90ms-RKSJ-H");
    public static final COSName ENCODING_90MS_RKSJ_V = new COSName("90ms-RKSJ-V");
    public static final COSName ENCODING_ETEN_B5_H = new COSName("ETen-B5-H");
    public static final COSName ENCODING_ETEN_B5_V = new COSName("ETen-B5-V");
    public static final COSName ENCRYPT = new COSName("Encrypt");
    public static final COSName ENCRYPT_META_DATA = new COSName("EncryptMetadata");
    public static final COSName END_OF_LINE = new COSName("EndOfLine");
    public static final COSName ENTRUST_PPKEF = new COSName("Entrust.PPKEF");
    public static final COSName EXCLUSION = new COSName("Exclusion");
    public static final COSName EXT_G_STATE = new COSName("ExtGState");
    public static final COSName EXTEND = new COSName("Extend");
    public static final COSName EXTENDS = new COSName("Extends");

    public static final COSName f2260F = new COSName(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION);
    public static final COSName F_DECODE_PARMS = new COSName("FDecodeParms");
    public static final COSName F_FILTER = new COSName("FFilter");

    public static final COSName f2261FB = new COSName("FB");
    public static final COSName FDF = new COSName("FDF");

    public static final COSName f2262FF = new COSName("Ff");
    public static final COSName FIELDS = new COSName("Fields");
    public static final COSName FILESPEC = new COSName("Filespec");
    public static final COSName FILTER = new COSName("Filter");
    public static final COSName FIRST = new COSName("First");
    public static final COSName FIRST_CHAR = new COSName("FirstChar");
    public static final COSName FIT_WINDOW = new COSName("FitWindow");

    public static final COSName f2263FL = new COSName("FL");
    public static final COSName FLAGS = new COSName("Flags");
    public static final COSName FLATE_DECODE = new COSName("FlateDecode");
    public static final COSName FLATE_DECODE_ABBREVIATION = new COSName("Fl");
    public static final COSName FONT = new COSName("Font");
    public static final COSName FONT_BBOX = new COSName(AFMParser.FONT_BBOX);
    public static final COSName FONT_DESC = new COSName("FontDescriptor");
    public static final COSName FONT_FAMILY = new COSName("FontFamily");
    public static final COSName FONT_FILE = new COSName("FontFile");
    public static final COSName FONT_FILE2 = new COSName("FontFile2");
    public static final COSName FONT_FILE3 = new COSName("FontFile3");
    public static final COSName FONT_MATRIX = new COSName("FontMatrix");
    public static final COSName FONT_NAME = new COSName(AFMParser.FONT_NAME);
    public static final COSName FONT_STRETCH = new COSName("FontStretch");
    public static final COSName FONT_WEIGHT = new COSName("FontWeight");
    public static final COSName FORM = new COSName(StandardStructureTypes.FORM);
    public static final COSName FORMTYPE = new COSName("FormType");
    public static final COSName FRM = new COSName("FRM");

    public static final COSName f2264FT = new COSName("FT");
    public static final COSName FUNCTION = new COSName("Function");
    public static final COSName FUNCTION_TYPE = new COSName("FunctionType");
    public static final COSName FUNCTIONS = new COSName("Functions");

    public static final COSName f2265G = new COSName("G");
    public static final COSName GAMMA = new COSName(ExifInterface.TAG_GAMMA);
    public static final COSName GROUP = new COSName(PDAnnotationMarkup.RT_GROUP);
    public static final COSName GTS_PDFA1 = new COSName("GTS_PDFA1");

    public static final COSName f2266H = new COSName(StandardStructureTypes.f2367H);
    public static final COSName HARD_LIGHT = new COSName("HardLight");
    public static final COSName HEIGHT = new COSName("Height");
    public static final COSName HIDE_MENUBAR = new COSName("HideMenubar");
    public static final COSName HIDE_TOOLBAR = new COSName("HideToolbar");
    public static final COSName HIDE_WINDOWUI = new COSName("HideWindowUI");

    public static final COSName f2267I = new COSName("I");

    public static final COSName f2268IC = new COSName("IC");
    public static final COSName ICCBASED = new COSName("ICCBased");

    public static final COSName f2269ID = new COSName("ID");
    public static final COSName ID_TREE = new COSName("IDTree");
    public static final COSName IDENTITY = new COSName("Identity");
    public static final COSName IDENTITY_H = new COSName("Identity-H");
    public static final COSName IDENTITY_V = new COSName("Identity-V");

    public static final COSName f2270IF = new COSName("IF");

    public static final COSName f2271IM = new COSName("IM");
    public static final COSName IMAGE = new COSName("Image");
    public static final COSName IMAGE_MASK = new COSName("ImageMask");
    public static final COSName INDEX = new COSName("Index");
    public static final COSName INDEXED = new COSName("Indexed");
    public static final COSName INFO = new COSName("Info");
    public static final COSName INKLIST = new COSName("InkList");
    public static final COSName INTERPOLATE = new COSName("Interpolate");

    public static final COSName f2272IT = new COSName("IT");
    public static final COSName ITALIC_ANGLE = new COSName(AFMParser.ITALIC_ANGLE);
    public static final COSName JAVA_SCRIPT = new COSName(PDActionJavaScript.SUB_TYPE);
    public static final COSName JBIG2_DECODE = new COSName("JBIG2Decode");
    public static final COSName JBIG2_GLOBALS = new COSName("JBIG2Globals");
    public static final COSName JPX_DECODE = new COSName("JPXDecode");

    public static final COSName f2273JS = new COSName("JS");

    public static final COSName f2274K = new COSName("K");
    public static final COSName KEYWORDS = new COSName("Keywords");
    public static final COSName KIDS = new COSName("Kids");

    public static final COSName f2275L = new COSName("L");
    public static final COSName LAB = new COSName("Lab");
    public static final COSName LANG = new COSName("Lang");
    public static final COSName LAST = new COSName("Last");
    public static final COSName LAST_CHAR = new COSName("LastChar");
    public static final COSName LAST_MODIFIED = new COSName("LastModified");

    public static final COSName f2276LC = new COSName("LC");

    public static final COSName f2277LE = new COSName("LE");
    public static final COSName LEADING = new COSName("Leading");
    public static final COSName LEGAL_ATTESTATION = new COSName("LegalAttestation");
    public static final COSName LENGTH = new COSName("Length");
    public static final COSName LENGTH1 = new COSName("Length1");
    public static final COSName LENGTH2 = new COSName("Length2");
    public static final COSName LIGHTEN = new COSName("Lighten");
    public static final COSName LIMITS = new COSName("Limits");

    public static final COSName f2278LJ = new COSName("LJ");

    public static final COSName f2279LL = new COSName("LL");
    public static final COSName LLE = new COSName("LLE");
    public static final COSName LLO = new COSName("LLO");
    public static final COSName LOCATION = new COSName(HttpHeaders.LOCATION);
    public static final COSName LUMINOSITY = new COSName("Luminosity");

    public static final COSName f2280LW = new COSName("LW");
    public static final COSName LZW_DECODE = new COSName("LZWDecode");
    public static final COSName LZW_DECODE_ABBREVIATION = new COSName("LZW");

    public static final COSName f2281M = new COSName("M");
    public static final COSName MAC = new COSName("Mac");
    public static final COSName MAC_ROMAN_ENCODING = new COSName("MacRomanEncoding");
    public static final COSName MARK_INFO = new COSName("MarkInfo");
    public static final COSName MASK = new COSName("Mask");
    public static final COSName MATRIX = new COSName("Matrix");
    public static final COSName MAX_LEN = new COSName("MaxLen");
    public static final COSName MAX_WIDTH = new COSName("MaxWidth");
    public static final COSName MCID = new COSName("MCID");
    public static final COSName MDP = new COSName("MDP");
    public static final COSName MEDIA_BOX = new COSName(PDViewerPreferences.BOUNDARY_MEDIA_BOX);
    public static final COSName METADATA = new COSName("Metadata");
    public static final COSName MISSING_WIDTH = new COSName("MissingWidth");

    public static final COSName f2282MK = new COSName("MK");

    public static final COSName f2283ML = new COSName("ML");
    public static final COSName MM_TYPE1 = new COSName("MMType1");
    public static final COSName MOD_DATE = new COSName("ModDate");
    public static final COSName MULTIPLY = new COSName("Multiply");

    public static final COSName f2284N = new COSName("N");
    public static final COSName NAME = new COSName("Name");
    public static final COSName NAMES = new COSName("Names");
    public static final COSName NEED_APPEARANCES = new COSName("NeedAppearances");
    public static final COSName NEXT = new COSName("Next");

    public static final COSName f2285NM = new COSName("NM");
    public static final COSName NON_EFONT_NO_WARN = new COSName("NonEFontNoWarn");
    public static final COSName NON_FULL_SCREEN_PAGE_MODE = new COSName("NonFullScreenPageMode");
    public static final COSName NONE = new COSName("None");
    public static final COSName NORMAL = new COSName(PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
    public static final COSName NUMS = new COSName("Nums");

    public static final COSName f2286O = new COSName(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE);
    public static final COSName OBJ = new COSName("Obj");
    public static final COSName OBJ_STM = new COSName("ObjStm");

    public static final COSName f2287OC = new COSName("OC");
    public static final COSName OCG = new COSName("OCG");
    public static final COSName OCGS = new COSName("OCGs");
    public static final COSName OCPROPERTIES = new COSName("OCProperties");

    public static final COSName f2288OE = new COSName("OE");
    public static final COSName OFF = new COSName("OFF");
    public static final COSName Off = new COSName("Off");

    public static final COSName f2289ON = new COSName("ON");

    public static final COSName f2290OP = new COSName("OP");
    public static final COSName OP_NS = new COSName("op");
    public static final COSName OPEN_ACTION = new COSName("OpenAction");
    public static final COSName OPEN_TYPE = new COSName("OpenType");
    public static final COSName OPM = new COSName("OPM");
    public static final COSName OPT = new COSName("Opt");
    public static final COSName ORDER = new COSName("Order");
    public static final COSName ORDERING = new COSName("Ordering");

    public static final COSName f2291OS = new COSName("OS");
    public static final COSName OUTLINES = new COSName("Outlines");
    public static final COSName OUTPUT_CONDITION = new COSName("OutputCondition");
    public static final COSName OUTPUT_CONDITION_IDENTIFIER = new COSName("OutputConditionIdentifier");
    public static final COSName OUTPUT_INTENT = new COSName("OutputIntent");
    public static final COSName OUTPUT_INTENTS = new COSName("OutputIntents");
    public static final COSName OVERLAY = new COSName("Overlay");

    public static final COSName f2292P = new COSName("P");
    public static final COSName PAGE = new COSName("Page");
    public static final COSName PAGE_LABELS = new COSName("PageLabels");
    public static final COSName PAGE_LAYOUT = new COSName("PageLayout");
    public static final COSName PAGE_MODE = new COSName("PageMode");
    public static final COSName PAGES = new COSName("Pages");
    public static final COSName PAINT_TYPE = new COSName("PaintType");
    public static final COSName PANOSE = new COSName("Panose");
    public static final COSName PARAMS = new COSName("Params");
    public static final COSName PARENT = new COSName("Parent");
    public static final COSName PARENT_TREE = new COSName("ParentTree");
    public static final COSName PARENT_TREE_NEXT_KEY = new COSName("ParentTreeNextKey");
    public static final COSName PATTERN = new COSName("Pattern");
    public static final COSName PATTERN_TYPE = new COSName("PatternType");
    public static final COSName PDF_DOC_ENCODING = new COSName("PDFDocEncoding");
    public static final COSName PERMS = new COSName("Perms");

    public static final COSName f2293PG = new COSName("Pg");
    public static final COSName PRE_RELEASE = new COSName("PreRelease");
    public static final COSName PREDICTOR = new COSName("Predictor");
    public static final COSName PREV = new COSName("Prev");
    public static final COSName PRINT_AREA = new COSName("PrintArea");
    public static final COSName PRINT_CLIP = new COSName("PrintClip");
    public static final COSName PRINT_SCALING = new COSName("PrintScaling");
    public static final COSName PROC_SET = new COSName("ProcSet");
    public static final COSName PROCESS = new COSName("Process");
    public static final COSName PRODUCER = new COSName("Producer");
    public static final COSName PROP_BUILD = new COSName("Prop_Build");
    public static final COSName PROPERTIES = new COSName("Properties");

    public static final COSName f2294PS = new COSName("PS");
    public static final COSName PUB_SEC = new COSName("PubSec");

    public static final COSName f2295Q = new COSName("Q");
    public static final COSName QUADPOINTS = new COSName("QuadPoints");

    public static final COSName f2296R = new COSName("R");
    public static final COSName RANGE = new COSName("Range");

    public static final COSName f2297RC = new COSName("RC");

    public static final COSName f2298RD = new COSName("RD");
    public static final COSName REASON = new COSName("Reason");
    public static final COSName REASONS = new COSName("Reasons");
    public static final COSName RECIPIENTS = new COSName("Recipients");
    public static final COSName RECT = new COSName("Rect");
    public static final COSName REGISTRY = new COSName("Registry");
    public static final COSName REGISTRY_NAME = new COSName("RegistryName");
    public static final COSName RENAME = new COSName("Rename");
    public static final COSName RESOURCES = new COSName("Resources");
    public static final COSName RGB = new COSName("RGB");

    public static final COSName f2299RI = new COSName("RI");
    public static final COSName ROLE_MAP = new COSName("RoleMap");
    public static final COSName ROOT = new COSName("Root");
    public static final COSName ROTATE = new COSName("Rotate");
    public static final COSName ROWS = new COSName("Rows");
    public static final COSName RUN_LENGTH_DECODE = new COSName("RunLengthDecode");
    public static final COSName RUN_LENGTH_DECODE_ABBREVIATION = new COSName(PDRectlinearMeasureDictionary.SUBTYPE);

    public static final COSName f2300RV = new COSName("RV");

    public static final COSName f2301S = new COSName("S");

    public static final COSName f2302SA = new COSName("SA");
    public static final COSName SCREEN = new COSName("Screen");

    public static final COSName f2303SE = new COSName("SE");
    public static final COSName SEPARATION = new COSName("Separation");
    public static final COSName SET_F = new COSName("SetF");
    public static final COSName SET_FF = new COSName("SetFf");
    public static final COSName SHADING = new COSName("Shading");
    public static final COSName SHADING_TYPE = new COSName("ShadingType");
    public static final COSName SIG = new COSName("Sig");
    public static final COSName SIG_FLAGS = new COSName("SigFlags");
    public static final COSName SIZE = new COSName("Size");

    public static final COSName f2304SM = new COSName("SM");
    public static final COSName SMASK = new COSName("SMask");
    public static final COSName SOFT_LIGHT = new COSName("SoftLight");

    public static final COSName f2305SS = new COSName("SS");

    public static final COSName f2306ST = new COSName("St");
    public static final COSName STANDARD_ENCODING = new COSName("StandardEncoding");
    public static final COSName STATE = new COSName("State");
    public static final COSName STATE_MODEL = new COSName("StateModel");
    public static final COSName STATUS = new COSName("Status");
    public static final COSName STD_CF = new COSName("StdCF");
    public static final COSName STEM_H = new COSName("StemH");
    public static final COSName STEM_V = new COSName("StemV");
    public static final COSName STM_F = new COSName("StmF");
    public static final COSName STR_F = new COSName("StrF");
    public static final COSName STRUCT_PARENT = new COSName("StructParent");
    public static final COSName STRUCT_PARENTS = new COSName("StructParents");
    public static final COSName STRUCT_TREE_ROOT = new COSName("StructTreeRoot");
    public static final COSName STYLE = new COSName("Style");
    public static final COSName SUB_FILTER = new COSName("SubFilter");
    public static final COSName SUBJ = new COSName("Subj");
    public static final COSName SUBJECT = new COSName("Subject");
    public static final COSName SUBTYPE = new COSName("Subtype");
    public static final COSName SUPPLEMENT = new COSName("Supplement");

    public static final COSName f2307SV = new COSName("SV");

    public static final COSName f2308SW = new COSName("SW");

    public static final COSName f2309SY = new COSName("Sy");

    public static final COSName f2310T = new COSName("T");
    public static final COSName TARGET = new COSName("Target");
    public static final COSName TEMPLATES = new COSName("Templates");
    public static final COSName THREADS = new COSName("Threads");
    public static final COSName THUMB = new COSName("Thumb");

    public static final COSName f2311TI = new COSName("TI");
    public static final COSName TILING_TYPE = new COSName("TilingType");
    public static final COSName TIME_STAMP = new COSName("TimeStamp");
    public static final COSName TITLE = new COSName(HTMLLayout.TITLE_OPTION);

    public static final COSName f2312TK = new COSName("TK");

    public static final COSName f2313TM = new COSName("TM");
    public static final COSName TO_UNICODE = new COSName("ToUnicode");

    public static final COSName f2314TR = new COSName(StandardStructureTypes.f2382TR);
    public static final COSName TR2 = new COSName("TR2");
    public static final COSName TRAPPED = new COSName("Trapped");
    public static final COSName TRANS = new COSName("Trans");
    public static final COSName TRANSPARENCY = new COSName("Transparency");
    public static final COSName TREF = new COSName("TRef");
    public static final COSName TRIM_BOX = new COSName(PDViewerPreferences.BOUNDARY_TRIM_BOX);
    public static final COSName TRUE_TYPE = new COSName("TrueType");
    public static final COSName TRUSTED_MODE = new COSName("TrustedMode");

    public static final COSName f2315TU = new COSName("TU");

    public static final COSName f2316TX = new COSName("Tx");
    public static final COSName TYPE = new COSName("Type");
    public static final COSName TYPE0 = new COSName("Type0");
    public static final COSName TYPE1 = new COSName("Type1");
    public static final COSName TYPE3 = new COSName("Type3");

    public static final COSName f2317U = new COSName(PDBorderStyleDictionary.STYLE_UNDERLINE);

    public static final COSName f2318UE = new COSName("UE");

    public static final COSName f2319UF = new COSName("UF");
    public static final COSName UNCHANGED = new COSName("Unchanged");
    public static final COSName UNIX = new COSName("Unix");
    public static final COSName URI = new COSName(PDActionURI.SUB_TYPE);
    public static final COSName URL = new COSName("URL");

    public static final COSName f2320V = new COSName(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
    public static final COSName VERISIGN_PPKVS = new COSName("VeriSign.PPKVS");
    public static final COSName VERSION = new COSName(AFMParser.VERSION);
    public static final COSName VERTICES = new COSName("Vertices");
    public static final COSName VERTICES_PER_ROW = new COSName("VerticesPerRow");
    public static final COSName VIEW_AREA = new COSName("ViewArea");
    public static final COSName VIEW_CLIP = new COSName("ViewClip");
    public static final COSName VIEWER_PREFERENCES = new COSName("ViewerPreferences");

    public static final COSName f2321W = new COSName("W");

    public static final COSName f2322W2 = new COSName("W2");
    public static final COSName WHITE_POINT = new COSName(ExifInterface.TAG_WHITE_POINT);
    public static final COSName WIDTH = new COSName("Width");
    public static final COSName WIDTHS = new COSName("Widths");
    public static final COSName WIN_ANSI_ENCODING = new COSName("WinAnsiEncoding");
    public static final COSName XFA = new COSName("XFA");
    public static final COSName X_STEP = new COSName("XStep");
    public static final COSName XHEIGHT = new COSName(AFMParser.X_HEIGHT);
    public static final COSName XOBJECT = new COSName("XObject");
    public static final COSName XREF = new COSName("XRef");
    public static final COSName XREF_STM = new COSName("XRefStm");
    public static final COSName Y_STEP = new COSName("YStep");
    public static final COSName YES = new COSName("Yes");

    public static COSName getPDFName(String str) {
        if (str == null) {
            return null;
        }
        COSName cOSName = commonNameMap.get(str);
        if (cOSName != null) {
            return cOSName;
        }
        COSName cOSName2 = nameMap.get(str);
        return cOSName2 == null ? new COSName(str, false) : cOSName2;
    }

    private COSName(String str, boolean z) {
        this.name = str;
        if (z) {
            commonNameMap.put(str, this);
        } else {
            nameMap.put(str, this);
        }
        this.hashCode = str.hashCode();
    }

    private COSName(String str) {
        this(str, true);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "COSName{" + this.name + "}";
    }

    public boolean equals(Object obj) {
        return (obj instanceof COSName) && this.name.equals(((COSName) obj).name);
    }

    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public int compareTo(COSName cOSName) {
        return this.name.compareTo(cOSName.name);
    }

    public boolean isEmpty() {
        return this.name.isEmpty();
    }

    @Override
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromName(this);
    }

    public void writePDF(OutputStream outputStream) throws IOException {
        outputStream.write(47);
        for (byte b : getName().getBytes(Charsets.US_ASCII)) {
            int i = (b + 256) % 256;
            if ((i >= 65 && i <= 90) || ((i >= 97 && i <= 122) || ((i >= 48 && i <= 57) || i == 43 || i == 45 || i == 95 || i == 64 || i == 42 || i == 36 || i == 59 || i == 46))) {
                outputStream.write(i);
            } else {
                outputStream.write(35);
                outputStream.write(String.format("%02X", Integer.valueOf(i)).getBytes(Charsets.US_ASCII));
            }
        }
    }

    public static synchronized void clearResources() {
        nameMap.clear();
    }
}
