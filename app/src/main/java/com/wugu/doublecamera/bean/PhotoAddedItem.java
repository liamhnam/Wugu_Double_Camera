package com.wugu.doublecamera.bean;

public class PhotoAddedItem {
    private String demoPath;
    private String name;
    private int photoAddedType;

    public PhotoAddedItem(int i, String str, String str2) {
        this.photoAddedType = i;
        this.name = str;
        this.demoPath = str2;
    }

    public PhotoAddedItem() {
    }

    public int getPhotoAddedType() {
        return this.photoAddedType;
    }

    public void setPhotoAddedType(int i) {
        this.photoAddedType = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDemoPath() {
        return this.demoPath;
    }

    public void setDemoPath(String str) {
        this.demoPath = str;
    }
}
