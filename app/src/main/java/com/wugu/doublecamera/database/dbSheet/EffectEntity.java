package com.wugu.doublecamera.database.dbSheet;

public class EffectEntity {
    private Long _id;
    private String effectDemoUrl;
    private String effectName;
    private int effectType;
    private String effectUrl;
    private int index;
    private boolean isEnable;
    private String remark;

    public EffectEntity(Long l, int i, int i2, String str, String str2, String str3, String str4, boolean z) {
        this._id = l;
        this.effectType = i;
        this.index = i2;
        this.effectName = str;
        this.effectUrl = str2;
        this.effectDemoUrl = str3;
        this.remark = str4;
        this.isEnable = z;
    }

    public EffectEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public int getEffectType() {
        return this.effectType;
    }

    public void setEffectType(int i) {
        this.effectType = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getEffectName() {
        return this.effectName;
    }

    public void setEffectName(String str) {
        this.effectName = str;
    }

    public String getEffectUrl() {
        return this.effectUrl;
    }

    public void setEffectUrl(String str) {
        this.effectUrl = str;
    }

    public String getEffectDemoUrl() {
        return this.effectDemoUrl;
    }

    public void setEffectDemoUrl(String str) {
        this.effectDemoUrl = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public boolean getIsEnable() {
        return this.isEnable;
    }

    public void setIsEnable(boolean z) {
        this.isEnable = z;
    }
}
