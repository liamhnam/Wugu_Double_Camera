package com.wugu.doublecamera.database.dbSheet;

public class FrameThemeEntity {
    private Long _id;
    private String backgroundNetUrl;
    private String backgroundPath;
    private String buttonNetUrl;
    private String buttonPath;
    private int index;
    private boolean isEnable;
    private String limitTime;
    private String parentId;
    private String remark;
    private String themeKey;
    private String themeName;

    public FrameThemeEntity(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, boolean z) {
        this._id = l;
        this.themeKey = str;
        this.themeName = str2;
        this.backgroundNetUrl = str3;
        this.buttonNetUrl = str4;
        this.backgroundPath = str5;
        this.buttonPath = str6;
        this.parentId = str7;
        this.remark = str8;
        this.limitTime = str9;
        this.index = i;
        this.isEnable = z;
    }

    public FrameThemeEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public String getThemeKey() {
        return this.themeKey;
    }

    public void setThemeKey(String str) {
        this.themeKey = str;
    }

    public String getThemeName() {
        return this.themeName;
    }

    public void setThemeName(String str) {
        this.themeName = str;
    }

    public String getBackgroundNetUrl() {
        return this.backgroundNetUrl;
    }

    public void setBackgroundNetUrl(String str) {
        this.backgroundNetUrl = str;
    }

    public String getButtonNetUrl() {
        return this.buttonNetUrl;
    }

    public void setButtonNetUrl(String str) {
        this.buttonNetUrl = str;
    }

    public String getBackgroundPath() {
        return this.backgroundPath;
    }

    public void setBackgroundPath(String str) {
        this.backgroundPath = str;
    }

    public String getButtonPath() {
        return this.buttonPath;
    }

    public void setButtonPath(String str) {
        this.buttonPath = str;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String getLimitTime() {
        return this.limitTime;
    }

    public void setLimitTime(String str) {
        this.limitTime = str;
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
