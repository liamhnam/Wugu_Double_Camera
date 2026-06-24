package com.wugu.doublecamera.bean;

public class PictureSelectItem {
    private boolean isAdded;
    private String photoName;
    public int process;

    public PictureSelectItem(String str, boolean z) {
        this.photoName = str;
        this.isAdded = z;
    }

    public String getPhotoName() {
        return this.photoName;
    }

    public void setPhotoName(String str) {
        this.photoName = str;
    }

    public boolean isAdded() {
        return this.isAdded;
    }

    public void setAdded(boolean z) {
        this.isAdded = z;
    }
}
