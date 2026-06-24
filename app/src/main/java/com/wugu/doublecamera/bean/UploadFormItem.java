package com.wugu.doublecamera.bean;

public class UploadFormItem {
    public String itemIconPath;
    public int photoCount;

    public UploadFormItem(String str, int i) {
        this.itemIconPath = str;
        this.photoCount = i;
    }

    public UploadFormItem() {
    }
}
