package com.wugu.doublecamera.bean;

import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;

public class FramePhotoInfo {
    private int degrees;
    private int height;
    private int locationX;
    private int locationY;
    private String remark;
    private int width;

    public FramePhotoEntity getFramePhotoEntity(String str) {
        return new FramePhotoEntity(null, str, this.height, this.width, this.locationX, this.locationY, this.degrees, this.remark);
    }

    public FramePhotoInfo() {
    }

    public FramePhotoInfo(FramePhotoEntity framePhotoEntity) {
        if (framePhotoEntity != null) {
            this.degrees = framePhotoEntity.getDegrees();
            this.height = framePhotoEntity.getHeight();
            this.width = framePhotoEntity.getWidth();
            this.locationX = framePhotoEntity.getX();
            this.locationY = framePhotoEntity.getY();
            this.remark = framePhotoEntity.getRemark();
        }
    }

    public FramePhotoInfo(int i, int i2, int i3, int i4, int i5) {
        this.height = i;
        this.width = i2;
        this.locationX = i3;
        this.locationY = i4;
        this.degrees = i5;
    }

    public FramePhotoInfo(int i, int i2, int i3, int i4, int i5, String str) {
        this.height = i;
        this.width = i2;
        this.locationX = i3;
        this.locationY = i4;
        this.degrees = i5;
        this.remark = str;
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

    public int getLocationX() {
        return this.locationX;
    }

    public void setLocationX(int i) {
        this.locationX = i;
    }

    public int getLocationY() {
        return this.locationY;
    }

    public void setLocationY(int i) {
        this.locationY = i;
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
