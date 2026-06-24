package com.wugu.doublecamera.database.dbSheet;

public class FramePhotoEntity {
    private Long _id;
    private int degrees;
    private String frameKey;
    private int height;
    private String remark;
    private int width;

    private int f2448x;

    private int f2449y;

    public FramePhotoEntity(Long l, String str, int i, int i2, int i3, int i4, int i5, String str2) {
        this._id = l;
        this.frameKey = str;
        this.height = i;
        this.width = i2;
        this.f2448x = i3;
        this.f2449y = i4;
        this.degrees = i5;
        this.remark = str2;
    }

    public FramePhotoEntity() {
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

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getX() {
        return this.f2448x;
    }

    public void setX(int i) {
        this.f2448x = i;
    }

    public int getY() {
        return this.f2449y;
    }

    public void setY(int i) {
        this.f2449y = i;
    }

    public int getDegrees() {
        return this.degrees;
    }

    public void setDegrees(int i) {
        this.degrees = i;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }
}
