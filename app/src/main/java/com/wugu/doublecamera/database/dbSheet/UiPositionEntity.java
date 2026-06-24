package com.wugu.doublecamera.database.dbSheet;

public class UiPositionEntity {
    private Long _id;
    private String defaultResPath;
    private int index;
    private boolean isVisible;
    private String resNetUrl;
    private String resPath;

    private int f2452x;

    private int f2453y;

    public UiPositionEntity(Long l, int i, int i2, int i3, String str, String str2, String str3, boolean z) {
        this._id = l;
        this.index = i;
        this.f2452x = i2;
        this.f2453y = i3;
        this.resNetUrl = str;
        this.resPath = str2;
        this.defaultResPath = str3;
        this.isVisible = z;
    }

    public UiPositionEntity() {
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

    public int getX() {
        return this.f2452x;
    }

    public void setX(int i) {
        this.f2452x = i;
    }

    public int getY() {
        return this.f2453y;
    }

    public void setY(int i) {
        this.f2453y = i;
    }

    public String getResNetUrl() {
        return this.resNetUrl;
    }

    public void setResNetUrl(String str) {
        this.resNetUrl = str;
    }

    public String getResPath() {
        return this.resPath;
    }

    public void setResPath(String str) {
        this.resPath = str;
    }

    public String getDefaultResPath() {
        return this.defaultResPath;
    }

    public void setDefaultResPath(String str) {
        this.defaultResPath = str;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean z) {
        this.isVisible = z;
    }
}
