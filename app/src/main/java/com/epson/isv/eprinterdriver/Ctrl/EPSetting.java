package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Point;
import com.epson.isv.eprinterdriver.Common.EpsPrinter;

public class EPSetting {
    protected static final int COLOR_GAMUT_ERGB = 1;
    protected static final int COLOR_GAMUT_SRGB = 0;
    public static final int COLOR_MODE_COLOR = 0;
    public static final int COLOR_MODE_MONOCHROME = 1;
    protected static final int PAPER_DIR_LANDSCAPE = 1;
    protected static final int PAPER_DIR_PORTRAIT = 0;
    public static final int PAPER_SOURCE_AUTO = 128;
    public static final int PAPER_SOURCE_CDTRAY = 8;
    protected static final int PAPER_SOURCE_CD_R = 8;
    protected static final int PAPER_SOURCE_CUT_SHEET_LOWER_FRONT = 2;
    protected static final int PAPER_SOURCE_CUT_SHEET_UPPER_FRONT = 4;
    public static final int PAPER_SOURCE_FRONT1 = 2;
    public static final int PAPER_SOURCE_FRONT2 = 4;
    public static final int PAPER_SOURCE_MANUAL = 16;
    public static final int PAPER_SOURCE_MANUAL2 = 512;
    protected static final int PAPER_SOURCE_MANUAL_FEED = 16;
    public static final int PAPER_SOURCE_NOT_SPEC = 0;
    public static final int PAPER_SOURCE_REAR = 1;
    public static final int PAPER_SOURCE_ROLL = 256;
    protected static final int PAPER_SOURCE_UPPER_THEN_LOWER_FRONT = 0;
    protected static final int PLATEN_GAP_DEFAULT = 0;
    protected static final int PLATEN_GAP_THICK_PAPER = 1;
    public static final int PRINT_DIR_BI = 0;
    public static final int PRINT_DIR_UNI = 1;
    private static EPSetting mGBean;
    private int band;
    String temporaryImageFilePath;
    private EpsPrinter selEpsPrinter = null;
    private PageAttribute selPageAttri = null;
    private int mediaSizeID = 0;
    private int mediaTypeID = 0;
    private boolean mBorderless = false;
    private boolean mDuplexPrint = false;
    private boolean mDuplexReverseBackpage = false;
    private int mDuplexInterval = 0;
    private Point mDuplexBindingMargin = new Point();
    private int mPaperDirection = 0;
    private int mUserDefWidth = 0;
    private int mUserDefHeight = 0;
    private Point mPhotoSealOffset = new Point();
    private int mTotalPages = 1;
    private boolean mFastCopy = false;
    private int mPlatenGapValue = 0;
    private int mPrintDirection = 0;
    private int mColorMode = 0;
    private int mPaperSource = 0;
    private int mColorGamut = 0;

    public static EPSetting instance() {
        if (mGBean == null) {
            mGBean = new EPSetting();
        }
        return mGBean;
    }

    public void setMediaSizeID(int i) {
        this.mediaSizeID = i;
    }

    public int getMediaSizeID() {
        return this.mediaSizeID;
    }

    public void setMediaTypeID(int i) {
        this.mediaTypeID = i;
    }

    public int getMediaTypeID() {
        return this.mediaTypeID;
    }

    public void setBorderless(boolean z) {
        this.mBorderless = z;
    }

    public boolean getBorderless() {
        return this.mBorderless;
    }

    public void setDuplexPrint(boolean z) {
        this.mDuplexPrint = z;
    }

    public boolean getDuplexPrint() {
        return this.mDuplexPrint;
    }

    protected void setDuplexReverseBackpage(boolean z) {
        this.mDuplexReverseBackpage = z;
    }

    protected boolean getDuplexReverseBackpage() {
        return this.mDuplexReverseBackpage;
    }

    protected void setDuplexInterval(int i) {
        this.mDuplexInterval = i;
    }

    protected int getDuplexInterval() {
        return this.mDuplexInterval;
    }

    protected void setDuplexBindingMargin(Point point) {
        this.mDuplexBindingMargin = point;
    }

    protected Point getDuplexBindingMargin() {
        return this.mDuplexBindingMargin;
    }

    protected void setPaperDirection(int i) {
        this.mPaperDirection = i;
    }

    protected int getPaperDirection() {
        return this.mPaperDirection;
    }

    public void setUserDefSize(int i, int i2) {
        this.mUserDefWidth = i;
        this.mUserDefHeight = i2;
        setMediaSizeID(99);
        setBorderless(false);
    }

    public int getUserDefWidth() {
        return this.mUserDefWidth;
    }

    public int getUserDefHeight() {
        return this.mUserDefHeight;
    }

    protected void setPhotoSealOffset(Point point) {
        this.mPhotoSealOffset = point;
    }

    protected Point getPhotoSealOffset() {
        return this.mPhotoSealOffset;
    }

    public void setTotalPages(int i) {
        this.mTotalPages = i;
    }

    public int getTotalPages() {
        return this.mTotalPages;
    }

    protected void setFastCopy(boolean z) {
        this.mFastCopy = z;
    }

    protected boolean getFastCopy() {
        return this.mFastCopy;
    }

    protected void setPlatenGapValue(int i) {
        this.mPlatenGapValue = i;
    }

    protected int getPlatenGapValue() {
        return this.mPlatenGapValue;
    }

    public void setPrintDirection(int i) {
        this.mPrintDirection = i;
    }

    public int getPrintDirection() {
        return this.mPrintDirection;
    }

    public void setColorMode(int i) {
        this.mColorMode = i;
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    public void setPaperSource(int i) {
        this.mPaperSource = i;
    }

    public int getPaperSource() {
        return this.mPaperSource;
    }

    protected void setColorGamut(int i) {
        this.mColorGamut = i;
    }

    protected int getColorGamut() {
        return this.mColorGamut;
    }

    public EpsPrinter getSelEpsPrinter() {
        return this.selEpsPrinter;
    }

    public void setSelEpsPrinter(EpsPrinter epsPrinter) {
        this.selEpsPrinter = epsPrinter;
    }

    public PageAttribute getSelPageAttri() {
        return this.selPageAttri;
    }

    public void setSelPageAttri(PageAttribute pageAttribute) {
        this.selPageAttri = pageAttribute;
        this.mediaSizeID = pageAttribute.getMediaSizeIdx();
        this.mediaTypeID = pageAttribute.getMediaTypeIdx();
    }

    protected int getBand() {
        return this.band;
    }

    protected void setBand(int i) {
        this.band = i;
    }

    public void setTemporaryImageFilePath(String str) {
        this.temporaryImageFilePath = str;
    }

    public String getTemporaryImageFilePath() {
        String str = this.temporaryImageFilePath;
        return str != null ? str : "/sdcard/temp.jpg";
    }
}
