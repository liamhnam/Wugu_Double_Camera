package com.wugu.doublecamera.bean;

public class PhotoItem {
    private float degrees;
    private int height;
    private String photoName;
    private int width;

    private int f2439x;

    private int f2440y;

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

    public float getDegrees() {
        return this.degrees;
    }

    public void setDegrees(float f) {
        this.degrees = f;
    }

    public String getPhotoName() {
        return this.photoName;
    }

    public void setPhotoName(String str) {
        this.photoName = str;
    }

    public int getX() {
        return this.f2439x;
    }

    public void setX(int i) {
        this.f2439x = i;
    }

    public int getY() {
        return this.f2440y;
    }

    public void setY(int i) {
        this.f2440y = i;
    }
}
