package com.epson.isv.eprinterdriver.Ctrl;

import android.os.Parcel;
import android.os.Parcelable;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;

public class PageAttribute implements Parcelable {
    protected static final Parcelable.Creator<PageAttribute> CREATOR = new Parcelable.Creator<PageAttribute>() {
        @Override
        public PageAttribute createFromParcel(Parcel parcel) {
            return new PageAttribute(parcel);
        }

        @Override
        public PageAttribute[] newArray(int i) {
            return new PageAttribute[i];
        }
    };
    byte apfAutoCorrect;
    short bottomMargin;
    byte brightness;
    byte cdDimIn;
    byte cdDimOut;
    int cmdType;
    int colorGamut;
    byte colorMode;
    int colorPlane;
    byte contrast;
    int copies;
    int countParams;
    int duplex;
    int duplexInterval;
    int feedDirection;
    byte inputResolution;
    short leftMargin;
    int mediaSizeIdx;
    int mediaTypeIdx;
    int paperSource;
    int[] parameters;
    byte platenGapSetting;
    byte printDirection;
    int printLayout;
    int printQuality;
    byte redeye;
    short rightMargin;
    byte saturation;
    byte sharpness;
    short topMargin;
    int userDefHeight;
    int userDefWidth;
    int version;

    public static final class MediaSizeID {
        public static final int EPS_MSID_10X12 = 69;
        public static final int EPS_MSID_10X15 = 13;
        public static final int EPS_MSID_11X14 = 65;
        public static final int EPS_MSID_12X12 = 70;
        public static final int EPS_MSID_12X18 = 85;
        public static final int EPS_MSID_16K = 76;
        public static final int EPS_MSID_16X20 = 129;
        public static final int EPS_MSID_200X300 = 14;
        public static final int EPS_MSID_2L = 28;
        public static final int EPS_MSID_4X6 = 10;
        public static final int EPS_MSID_5X8 = 11;
        public static final int EPS_MSID_8K = 77;
        public static final int EPS_MSID_8X10 = 12;
        public static final int EPS_MSID_8X10_5 = 104;
        public static final int EPS_MSID_8_27X13 = 106;
        public static final int EPS_MSID_8_5X13 = 86;
        public static final int EPS_MSID_A2 = 67;
        public static final int EPS_MSID_A3 = 62;
        public static final int EPS_MSID_A3NOBI = 61;
        public static final int EPS_MSID_A4 = 0;
        public static final int EPS_MSID_A5 = 3;
        public static final int EPS_MSID_A5_24HOLE = 48;
        public static final int EPS_MSID_A6 = 4;
        public static final int EPS_MSID_ALBUM_A5 = 38;
        public static final int EPS_MSID_ALBUM_L = 37;
        public static final int EPS_MSID_B3 = 66;
        public static final int EPS_MSID_B4 = 63;
        public static final int EPS_MSID_B5 = 5;
        public static final int EPS_MSID_B6 = 46;
        public static final int EPS_MSID_BUZCARD_55X91 = 36;
        public static final int EPS_MSID_BUZCARD_89X50 = 34;
        public static final int EPS_MSID_CARD_54X86 = 35;
        public static final int EPS_MSID_CHOKEI_3 = 22;
        public static final int EPS_MSID_CHOKEI_4 = 23;
        public static final int EPS_MSID_CHOKEI_40 = 52;
        public static final int EPS_MSID_DBLPOSTCARD = 17;
        public static final int EPS_MSID_ENV_10_L = 18;
        public static final int EPS_MSID_ENV_10_P = 29;
        public static final int EPS_MSID_ENV_B5_P = 111;
        public static final int EPS_MSID_ENV_C4_P = 45;
        public static final int EPS_MSID_ENV_C5_P = 56;
        public static final int EPS_MSID_ENV_C6_L = 19;
        public static final int EPS_MSID_ENV_C6_P = 30;
        public static final int EPS_MSID_ENV_DL_L = 20;
        public static final int EPS_MSID_ENV_DL_P = 31;
        public static final int EPS_MSID_EXECUTIVE = 6;
        public static final int EPS_MSID_HALFCUT = 128;
        public static final int EPS_MSID_HALFLETTER = 7;
        public static final int EPS_MSID_HIVISION = 43;
        public static final int EPS_MSID_INDIAN_LEGAL = 60;
        public static final int EPS_MSID_KAKU_2 = 44;
        public static final int EPS_MSID_KAKU_20 = 47;
        public static final int EPS_MSID_L = 15;
        public static final int EPS_MSID_LEGAL = 2;
        public static final int EPS_MSID_LETTER = 1;
        public static final int EPS_MSID_MEISHI = 33;
        public static final int EPS_MSID_MEXICO_OFICIO = 58;
        public static final int EPS_MSID_NEWENV_P = 32;
        public static final int EPS_MSID_NEWEVN_L = 21;
        public static final int EPS_MSID_OFICIO9 = 59;
        public static final int EPS_MSID_PALBUM_2L = 40;
        public static final int EPS_MSID_PALBUM_A4 = 42;
        public static final int EPS_MSID_PALBUM_A5_L = 41;
        public static final int EPS_MSID_PALBUM_L_L = 39;
        public static final int EPS_MSID_PANORAMIC = 8;
        public static final int EPS_MSID_POSTCARD = 16;
        public static final int EPS_MSID_QUADRAPLEPOSTCARD = 53;
        public static final int EPS_MSID_SP1 = 71;
        public static final int EPS_MSID_SP2 = 72;
        public static final int EPS_MSID_SP3 = 73;
        public static final int EPS_MSID_SP4 = 74;
        public static final int EPS_MSID_SP5 = 75;
        public static final int EPS_MSID_SQUARE_5 = 88;
        public static final int EPS_MSID_SQUARE_8_27 = 87;
        public static final int EPS_MSID_SRA3 = 84;
        public static final int EPS_MSID_TRIM_4X6 = 9;
        public static final int EPS_MSID_UNKNOWN = 255;
        public static final int EPS_MSID_USB = 64;
        public static final int EPS_MSID_USC = 68;
        public static final int EPS_MSID_USER = 99;
        public static final int EPS_MSID_YOKEI_0 = 54;
        public static final int EPS_MSID_YOKEI_1 = 24;
        public static final int EPS_MSID_YOKEI_2 = 25;
        public static final int EPS_MSID_YOKEI_3 = 26;
        public static final int EPS_MSID_YOKEI_4 = 27;
        public static final int EPS_MSID_YOKEI_6 = 57;
    }

    public static final class MediaTypeID {
        public static final int EPS_MTID_360INKJET = 1;
        public static final int EPS_MTID_3D = 51;
        public static final int EPS_MTID_ARCHMATTE = 15;
        public static final int EPS_MTID_AUTO_PLAIN = 253;
        public static final int EPS_MTID_BACKLIGHT = 10;
        public static final int EPS_MTID_BARYTA = 174;
        public static final int EPS_MTID_BROCHURE = 48;
        public static final int EPS_MTID_BSMATTE_DS = 50;
        public static final int EPS_MTID_BS_HALFGLOSSY_DS = 71;
        public static final int EPS_MTID_BUSINESSCOAT = 45;
        public static final int EPS_MTID_BUSINESS_PLAIN = 57;
        public static final int EPS_MTID_CDDVD = 91;
        public static final int EPS_MTID_CDDVDGLOSSY = 93;
        public static final int EPS_MTID_CDDVDHIGH = 92;
        public static final int EPS_MTID_CLEANING = 99;
        public static final int EPS_MTID_CLPHOTO = 23;
        public static final int EPS_MTID_COATED = 176;
        public static final int EPS_MTID_COLOR = 56;
        public static final int EPS_MTID_DSMATTE = 22;
        public static final int EPS_MTID_DURABRITE = 30;
        public static final int EPS_MTID_ECOPHOTO = 24;
        public static final int EPS_MTID_ENVELOPE = 37;
        public static final int EPS_MTID_GLOSSYCAST = 44;
        public static final int EPS_MTID_GLOSSYHAGAKI = 42;
        public static final int EPS_MTID_GLOSSYPHOTO = 43;
        public static final int EPS_MTID_GROSSY_ROLL_STICKER = 60;
        public static final int EPS_MTID_HAGAKIATENA = 32;
        public static final int EPS_MTID_HAGAKIINKJET = 28;
        public static final int EPS_MTID_HAGAKIRECL = 27;
        public static final int EPS_MTID_HIGH_QUALITY_PLAIN = 70;
        public static final int EPS_MTID_IRON = 2;
        public static final int EPS_MTID_LABEL = 183;
        public static final int EPS_MTID_LCPP = 52;
        public static final int EPS_MTID_LETTERHEAD = 54;
        public static final int EPS_MTID_MATTE = 5;
        public static final int EPS_MTID_MATTEBOARD = 18;
        public static final int EPS_MTID_MATTEMEISHI = 31;
        public static final int EPS_MTID_MATTE_DS = 49;
        public static final int EPS_MTID_MCGLOSSY = 14;
        public static final int EPS_MTID_MEDICINEBAG = 46;
        public static final int EPS_MTID_MINIPHOTO = 8;
        public static final int EPS_MTID_OHP = 9;
        public static final int EPS_MTID_PGPHOTO = 11;
        public static final int EPS_MTID_PGPHOTOEG = 36;
        public static final int EPS_MTID_PHOTO = 6;
        public static final int EPS_MTID_PHOTOADSHEET = 4;
        public static final int EPS_MTID_PHOTOALBUM = 33;
        public static final int EPS_MTID_PHOTOFILM = 7;
        public static final int EPS_MTID_PHOTOGLOSS = 19;
        public static final int EPS_MTID_PHOTOINKJET = 3;
        public static final int EPS_MTID_PHOTOINKJET2 = 29;
        public static final int EPS_MTID_PHOTOSTAND = 34;
        public static final int EPS_MTID_PHOTOSTD = 41;
        public static final int EPS_MTID_PLAIN = 0;
        public static final int EPS_MTID_PLAIN1 = 61;
        public static final int EPS_MTID_PLAIN2 = 62;
        public static final int EPS_MTID_PLAIN_ROLL_STICKER = 59;
        public static final int EPS_MTID_PLATINA = 38;
        public static final int EPS_MTID_PLOOFING_WHITE_MAT = 142;
        public static final int EPS_MTID_PLPHOTO = 13;
        public static final int EPS_MTID_PREPRINTED = 53;
        public static final int EPS_MTID_PROGLOSS = 17;
        public static final int EPS_MTID_PROOFSEMI = 26;
        public static final int EPS_MTID_PSPHOTO = 12;
        public static final int EPS_MTID_RCB = 35;
        public static final int EPS_MTID_RECYCLED = 55;
        public static final int EPS_MTID_SEMIPROOF = 20;
        public static final int EPS_MTID_SEMI_THICK = 77;
        public static final int EPS_MTID_SFHAGAKI = 40;
        public static final int EPS_MTID_SPECIAL = 75;
        public static final int EPS_MTID_SUPERFINE2 = 21;
        public static final int EPS_MTID_THICKPAPER = 47;
        public static final int EPS_MTID_THICKPAPER1 = 65;
        public static final int EPS_MTID_THICKPAPER2 = 66;
        public static final int EPS_MTID_THICKPAPER3 = 67;
        public static final int EPS_MTID_THICKPAPER4 = 68;
        public static final int EPS_MTID_THICKPAPER5 = 72;
        public static final int EPS_MTID_THINPAPER1 = 69;
        public static final int EPS_MTID_TRANSPARENCY = 74;
        public static final int EPS_MTID_ULTRASMOOTH = 39;
        public static final int EPS_MTID_UNKNOWN = 255;
        public static final int EPS_MTID_UNSPECIFIED = 76;
        public static final int EPS_MTID_VELVETFINEART = 25;
        public static final int EPS_MTID_WATERCOLOR = 16;
    }

    public static final class PrintQuality {
        public static final int EPS_MQID_BEST_PLAIN = 8;
        public static final int EPS_MQID_DRAFT = 1;
        public static final int EPS_MQID_HIGH = 4;
        public static final int EPS_MQID_NORMAL = 2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected PageAttribute(Parcel parcel) {
        this.version = 4;
        readFromParcel(parcel);
    }

    protected void readFromParcel(Parcel parcel) {
        this.version = parcel.readInt();
        this.mediaSizeIdx = parcel.readInt();
        this.mediaTypeIdx = parcel.readInt();
        this.printLayout = parcel.readInt();
        this.printQuality = parcel.readInt();
        this.paperSource = parcel.readInt();
        this.printDirection = parcel.readByte();
        this.colorMode = parcel.readByte();
        this.brightness = parcel.readByte();
        this.contrast = parcel.readByte();
        this.saturation = parcel.readByte();
        this.apfAutoCorrect = parcel.readByte();
        this.sharpness = parcel.readByte();
        this.redeye = parcel.readByte();
        this.topMargin = (short) parcel.readInt();
        this.leftMargin = (short) parcel.readInt();
        this.bottomMargin = (short) parcel.readInt();
        this.rightMargin = (short) parcel.readInt();
        this.cdDimIn = parcel.readByte();
        this.cdDimOut = parcel.readByte();
        this.cmdType = parcel.readInt();
        this.colorPlane = parcel.readInt();
        this.inputResolution = parcel.readByte();
        this.duplex = parcel.readInt();
        this.copies = parcel.readInt();
        this.feedDirection = parcel.readInt();
        this.userDefWidth = parcel.readInt();
        this.userDefHeight = parcel.readInt();
        this.duplexInterval = parcel.readInt();
        this.platenGapSetting = parcel.readByte();
        this.colorGamut = parcel.readInt();
        this.countParams = parcel.readByte();
        parcel.readIntArray(this.parameters);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.version);
        parcel.writeInt(this.mediaSizeIdx);
        parcel.writeInt(this.mediaTypeIdx);
        parcel.writeInt(this.printLayout);
        parcel.writeInt(this.printQuality);
        parcel.writeInt(this.paperSource);
        parcel.writeByte(this.printDirection);
        parcel.writeByte(this.colorMode);
        parcel.writeByte(this.brightness);
        parcel.writeByte(this.contrast);
        parcel.writeByte(this.saturation);
        parcel.writeByte(this.apfAutoCorrect);
        parcel.writeByte(this.sharpness);
        parcel.writeByte(this.redeye);
        parcel.writeInt(this.topMargin);
        parcel.writeInt(this.leftMargin);
        parcel.writeInt(this.bottomMargin);
        parcel.writeInt(this.rightMargin);
        parcel.writeByte(this.cdDimIn);
        parcel.writeByte(this.cdDimOut);
        parcel.writeInt(this.cmdType);
        parcel.writeInt(this.colorPlane);
        parcel.writeByte(this.inputResolution);
        parcel.writeInt(this.duplex);
        parcel.writeInt(this.copies);
        parcel.writeInt(this.feedDirection);
        parcel.writeInt(this.userDefWidth);
        parcel.writeInt(this.userDefHeight);
        parcel.writeInt(this.duplexInterval);
        parcel.writeByte(this.platenGapSetting);
        parcel.writeInt(this.colorGamut);
        parcel.writeInt(this.countParams);
        parcel.writeIntArray(this.parameters);
    }

    protected static final class ColorPlane {
        public static final int EPS_CP_256COLOR = 1;
        public static final int EPS_CP_FULLCOLOR = 0;
        public static final int EPS_CP_JPEG = 2;
        public static final int EPS_CP_PRINTCMD = 3;

        protected ColorPlane() {
        }
    }

    protected static final class CommandDataType {
        public static final int EPS_MNT_CLEANING = 2;
        public static final int EPS_MNT_CUSTOM = 1;
        public static final int EPS_MNT_NOZZLE = 3;
        public static final int EPS_MNT_PAPER_FEED = 4;
        public static final int EPS_MNT_PRINT_H_ALIGNMENT = 6;
        public static final int EPS_MNT_PRINT_V_ALIGNMENT = 5;
        public static final int EPS_MNT_SELECT_H_ALIGNMENT = 8;
        public static final int EPS_MNT_SELECT_V_ALIGNMENT = 7;
        public static final int EPS_MNT_UNKNOWN = 0;

        protected CommandDataType() {
        }
    }

    protected static final class PrintLayout {
        public static final int EPS_MLID_BORDERLESS = 1;
        public static final int EPS_MLID_BORDERS = 2;
        public static final int EPS_MLID_CDLABEL = 4;
        public static final int EPS_MLID_CUSTOM = 0;
        public static final int EPS_MLID_DIVIDE16 = 8;

        protected PrintLayout() {
        }
    }

    protected static final class PaperSource {
        public static final int EPS_MPID_AUTO = 128;
        public static final int EPS_MPID_CDTRAY = 8;
        public static final int EPS_MPID_FRONT1 = 2;
        public static final int EPS_MPID_FRONT2 = 4;
        public static final int EPS_MPID_FRONT3 = 32;
        public static final int EPS_MPID_FRONT4 = 64;
        public static final int EPS_MPID_HIGHCAP = 2048;
        public static final int EPS_MPID_MANUAL2 = 512;
        public static final int EPS_MPID_MPTRAY = 32768;
        public static final int EPS_MPID_MPTRAY_IJ = 1024;
        public static final int EPS_MPID_NOT_SPEC = 0;
        public static final int EPS_MPID_REAR = 1;
        public static final int EPS_MPID_REARMANUAL = 16;
        public static final int EPS_MPID_ROLL = 256;
        public static final int EPS_PAPER_PATH_CD_R = 8;
        public static final int EPS_PAPER_PATH_CUT_SHEET_LOWER_FRONT = 2;
        public static final int EPS_PAPER_PATH_CUT_SHEET_UPPER_FRONT = 4;
        public static final int EPS_PAPER_PATH_MANUAL_FEED = 16;
        public static final int EPS_PAPER_PATH_UPPER_THEN_LOWER_FRONT = 0;

        protected PaperSource() {
        }
    }

    protected static final class PrintDirection {
        public static final int EPS_PD_BIDIREC = 0;
        public static final int EPS_PD_UNIDIREC = 1;

        protected PrintDirection() {
        }
    }

    protected static final class ColorMode {
        public static final int EPS_CM_COLOR = 0;
        public static final int EPS_CM_MONOCHROME = 1;
        public static final int EPS_CM_SEPIA = 2;

        protected ColorMode() {
        }
    }

    protected static final class APFAutoCorrect {
        public static final int EPS_APF_ACT_NIGHTVIEW = 5;
        public static final int EPS_APF_ACT_NOTHING = 0;
        public static final int EPS_APF_ACT_PIM = 2;
        public static final int EPS_APF_ACT_PORTRATE = 3;
        public static final int EPS_APF_ACT_STANDARD = 1;
        public static final int EPS_APF_ACT_VIEW = 4;

        protected APFAutoCorrect() {
        }
    }

    protected static final class RedEye {
        public static final int EPS_APF_RDE_CORRECT = 1;
        public static final int EPS_APF_RDE_NOTHING = 0;

        protected RedEye() {
        }
    }

    protected static final class InputResolution {
        public static final int EPS_IR_150X150 = 4;
        public static final int EPS_IR_300X300 = 8;
        public static final int EPS_IR_360X360 = 1;

        protected InputResolution() {
        }
    }

    protected static final class DupLex {
        public static final int EPS_DUPLEX_LONG = 1;
        public static final int EPS_DUPLEX_NONE = 0;
        public static final int EPS_DUPLEX_SHORT = 2;

        protected DupLex() {
        }
    }

    protected static final class FeedDirection {
        public static final int EPS_FEEDDIR_LANDSCAPE = 1;
        public static final int EPS_FEEDDIR_PORTRAIT = 0;

        protected FeedDirection() {
        }
    }

    protected static final class ColorGamut {
        public static final int EPS_COLOR_GAMUT_ERGB = 1;
        public static final int EPS_COLOR_GAMUT_SRGB = 0;

        protected ColorGamut() {
        }
    }

    protected PageAttribute() {
        this.version = 4;
        this.inputResolution = (byte) 1;
        this.userDefWidth = 0;
        this.userDefHeight = 0;
    }

    public PageAttribute(int i, int i2, int i3) {
        this.version = 4;
        this.mediaSizeIdx = i;
        this.mediaTypeIdx = i2;
        this.printQuality = i3;
        this.printLayout = 2;
        this.inputResolution = (byte) 1;
        this.userDefWidth = 0;
        this.userDefHeight = 0;
    }

    protected int getVersion() {
        return this.version;
    }

    protected int getMediaSizeIdx() {
        return this.mediaSizeIdx;
    }

    protected int getMediaTypeIdx() {
        return this.mediaTypeIdx;
    }

    protected int getPrintQuality() {
        return this.printQuality;
    }

    protected void setBorder(short s, short s2, short s3, short s4) {
        this.leftMargin = s;
        this.topMargin = s2;
        this.rightMargin = s3;
        this.bottomMargin = s4;
        this.printLayout = 0;
    }

    protected void setCDLabel(byte b, byte b2) {
        if (b < 18 || b > 46) {
            this.cdDimIn = SnmpConstants.SNMP_ERR_INCONSISTENTNAME;
        } else {
            this.cdDimIn = b;
        }
        if (b2 < 114 || b2 > 120) {
            this.cdDimOut = (byte) 114;
        } else {
            this.cdDimOut = b2;
        }
        this.printLayout = 4;
        this.paperSource = 8;
    }

    protected short getTopMargin() {
        return this.topMargin;
    }

    protected short getLeftMargin() {
        return this.leftMargin;
    }

    protected short getBottomMargin() {
        return this.bottomMargin;
    }

    protected short getRightMargin() {
        return this.rightMargin;
    }

    protected int getPrintLayout() {
        return this.printLayout;
    }

    protected byte getCDDimIn() {
        return this.cdDimIn;
    }

    protected byte getCDDimOut() {
        return this.cdDimOut;
    }

    protected int getPaperSource() {
        return this.paperSource;
    }

    protected void setPaperSource(int i) {
        this.paperSource = i;
    }

    protected void setPrintLayout(int i) {
        this.printLayout = i;
    }

    protected byte getInputResolution() {
        return this.inputResolution;
    }

    protected void setInputResolution(byte b) {
        this.inputResolution = b;
    }

    protected int getDuplex() {
        return this.duplex;
    }

    protected void setDuplex(int i) {
        this.duplex = i;
    }

    protected int getCopies() {
        return this.copies;
    }

    protected void setCopies(int i) {
        this.copies = i;
    }

    protected int getFeedDirection() {
        return this.feedDirection;
    }

    protected void setFeedDirection(int i) {
        this.feedDirection = i;
    }

    protected void setPrintQuality(int i) {
        this.printQuality = i;
    }

    protected void setMediaTypeIdx(int i) {
        this.mediaTypeIdx = i;
    }

    protected void setMediaSizeIdx(int i) {
        this.mediaSizeIdx = i;
    }

    protected void setUserDefSize(int i, int i2) {
        this.userDefWidth = i;
        this.userDefHeight = i2;
    }

    protected int getUserDefWidth() {
        return this.userDefWidth;
    }

    protected int getUserDefHeight() {
        return this.userDefHeight;
    }

    protected void setDuplexInterval(int i) {
        this.duplexInterval = i;
    }

    protected int getDuplexInterval() {
        return this.duplexInterval;
    }

    protected void setPlatenGapValue(byte b) {
        this.platenGapSetting = b;
    }

    protected byte getPlatenGapValue() {
        return this.platenGapSetting;
    }

    protected void setPrintDirection(int i) {
        this.printDirection = (byte) i;
    }

    protected int getPrintDirection() {
        return this.printDirection;
    }

    protected void setColorGamut(int i) {
        this.colorGamut = i;
    }

    protected int getColorGamut() {
        return this.colorGamut;
    }

    protected void setColorPlane(int i) {
        this.colorPlane = i;
    }

    protected int getColorPlane() {
        return this.colorPlane;
    }

    protected void setColorMode(int i) {
        this.colorMode = (byte) i;
    }

    protected int getColorMode() {
        return this.colorMode;
    }

    protected void setCommandDataType(int i) {
        this.cmdType = i;
    }

    protected int getCommandDataType() {
        return this.cmdType;
    }

    protected void setCountParams(int i) {
        this.countParams = i;
    }

    protected int getCountParams() {
        return this.countParams;
    }

    protected void setParameters(int[] iArr) {
        this.parameters = (int[]) iArr.clone();
    }

    protected int[] getParameters() {
        return this.parameters;
    }
}
