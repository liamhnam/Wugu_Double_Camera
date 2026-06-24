package com.wugu.doublecamera.database.dbSheet;

public class SoundSettingEntity {
    private Long _id;
    private String localPath;
    private String netUrl;
    private String type;

    public SoundSettingEntity(Long l, String str, String str2, String str3) {
        this._id = l;
        this.netUrl = str;
        this.localPath = str2;
        this.type = str3;
    }

    public SoundSettingEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public String getNetUrl() {
        return this.netUrl;
    }

    public void setNetUrl(String str) {
        this.netUrl = str;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
