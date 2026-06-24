package com.wugu.doublecamera.database.dbSheet;

public class StickerEntity {
    private Long _id;
    private String folderIconPath;
    private String folderName;
    private boolean isEnable;
    private String localPath;
    private String networkUrl;
    private String remark;

    public StickerEntity(Long l, String str, String str2, String str3, String str4, String str5, boolean z) {
        this._id = l;
        this.folderName = str;
        this.folderIconPath = str2;
        this.networkUrl = str3;
        this.localPath = str4;
        this.remark = str5;
        this.isEnable = z;
    }

    public StickerEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String str) {
        this.folderName = str;
    }

    public String getFolderIconPath() {
        return this.folderIconPath;
    }

    public void setFolderIconPath(String str) {
        this.folderIconPath = str;
    }

    public String getNetworkUrl() {
        return this.networkUrl;
    }

    public void setNetworkUrl(String str) {
        this.networkUrl = str;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
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
