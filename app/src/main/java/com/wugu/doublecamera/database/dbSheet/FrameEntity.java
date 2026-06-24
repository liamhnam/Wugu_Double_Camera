package com.wugu.doublecamera.database.dbSheet;

public class FrameEntity {
    private Long _id;
    private int addOPrice;
    private int addPrice;
    private String aiModeIds;
    private int burstModeCount;
    private String colorParam;
    private String frameKey;
    private String frameLocalPath;
    private String frameName;
    private String frameNetUrl;
    private int frameType;
    private int index;
    private boolean isEnable;
    private boolean isNeedCut;
    private boolean isReplaceBg;
    private int oPrice;
    private int photoCount;
    private int price;
    private int printCount;
    private String remark;
    private String themeKey;

    public FrameEntity(Long l, String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, boolean z2, String str6, String str7, String str8, int i9, boolean z3) {
        this._id = l;
        this.frameKey = str;
        this.frameName = str2;
        this.frameNetUrl = str3;
        this.frameLocalPath = str4;
        this.themeKey = str5;
        this.frameType = i;
        this.photoCount = i2;
        this.burstModeCount = i3;
        this.oPrice = i4;
        this.price = i5;
        this.addOPrice = i6;
        this.addPrice = i7;
        this.printCount = i8;
        this.isNeedCut = z;
        this.isReplaceBg = z2;
        this.colorParam = str6;
        this.aiModeIds = str7;
        this.remark = str8;
        this.index = i9;
        this.isEnable = z3;
    }

    public FrameEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public String getFrameKey() {
        return this.frameKey;
    }

    public void setFrameKey(String str) {
        this.frameKey = str;
    }

    public String getFrameName() {
        return this.frameName;
    }

    public void setFrameName(String str) {
        this.frameName = str;
    }

    public String getFrameNetUrl() {
        return this.frameNetUrl;
    }

    public void setFrameNetUrl(String str) {
        this.frameNetUrl = str;
    }

    public String getFrameLocalPath() {
        return this.frameLocalPath;
    }

    public void setFrameLocalPath(String str) {
        this.frameLocalPath = str;
    }

    public String getThemeKey() {
        return this.themeKey;
    }

    public void setThemeKey(String str) {
        this.themeKey = str;
    }

    public int getFrameType() {
        return this.frameType;
    }

    public void setFrameType(int i) {
        this.frameType = i;
    }

    public int getPhotoCount() {
        return this.photoCount;
    }

    public void setPhotoCount(int i) {
        this.photoCount = i;
    }

    public int getBurstModeCount() {
        return this.burstModeCount;
    }

    public void setBurstModeCount(int i) {
        this.burstModeCount = i;
    }

    public int getOPrice() {
        return this.oPrice;
    }

    public void setOPrice(int i) {
        this.oPrice = i;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public int getAddOPrice() {
        return this.addOPrice;
    }

    public void setAddOPrice(int i) {
        this.addOPrice = i;
    }

    public int getAddPrice() {
        return this.addPrice;
    }

    public void setAddPrice(int i) {
        this.addPrice = i;
    }

    public int getPrintCount() {
        return this.printCount;
    }

    public void setPrintCount(int i) {
        this.printCount = i;
    }

    public boolean getIsNeedCut() {
        return this.isNeedCut;
    }

    public void setIsNeedCut(boolean z) {
        this.isNeedCut = z;
    }

    public boolean getIsReplaceBg() {
        return this.isReplaceBg;
    }

    public void setIsReplaceBg(boolean z) {
        this.isReplaceBg = z;
    }

    public String getColorParam() {
        return this.colorParam;
    }

    public void setColorParam(String str) {
        this.colorParam = str;
    }

    public String getAiModeIds() {
        return this.aiModeIds;
    }

    public void setAiModeIds(String str) {
        this.aiModeIds = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public boolean getIsEnable() {
        return this.isEnable;
    }

    public void setIsEnable(boolean z) {
        this.isEnable = z;
    }
}
