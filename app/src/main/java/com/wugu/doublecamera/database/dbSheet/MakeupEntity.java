package com.wugu.doublecamera.database.dbSheet;

public class MakeupEntity {
    private Long _id;
    private int index;
    private boolean isEnable;
    private String makeupBundlePath;
    private String makeupDemoPath;
    public int makeupIntensity;
    private String makeupJsonPath;
    private String makeupName;
    public int makeupType;

    public MakeupEntity(Long l, int i, String str, String str2, String str3, String str4, int i2, int i3, boolean z) {
        this._id = l;
        this.index = i;
        this.makeupName = str;
        this.makeupBundlePath = str2;
        this.makeupJsonPath = str3;
        this.makeupDemoPath = str4;
        this.makeupIntensity = i2;
        this.makeupType = i3;
        this.isEnable = z;
    }

    public MakeupEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getMakeupName() {
        return this.makeupName;
    }

    public void setMakeupName(String str) {
        this.makeupName = str;
    }

    public String getMakeupBundlePath() {
        return this.makeupBundlePath;
    }

    public void setMakeupBundlePath(String str) {
        this.makeupBundlePath = str;
    }

    public String getMakeupJsonPath() {
        return this.makeupJsonPath;
    }

    public void setMakeupJsonPath(String str) {
        this.makeupJsonPath = str;
    }

    public String getMakeupDemoPath() {
        return this.makeupDemoPath;
    }

    public void setMakeupDemoPath(String str) {
        this.makeupDemoPath = str;
    }

    public int getMakeupIntensity() {
        return this.makeupIntensity;
    }

    public void setMakeupIntensity(int i) {
        this.makeupIntensity = i;
    }

    public int getMakeupType() {
        return this.makeupType;
    }

    public void setMakeupType(int i) {
        this.makeupType = i;
    }

    public boolean getIsEnable() {
        return this.isEnable;
    }

    public void setIsEnable(boolean z) {
        this.isEnable = z;
    }
}
