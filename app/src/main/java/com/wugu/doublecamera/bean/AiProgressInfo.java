package com.wugu.doublecamera.bean;

public class AiProgressInfo {
    public String filePath;
    public int percent;

    public AiProgressInfo(String str, int i) {
        this.filePath = str;
        this.percent = i;
    }

    public AiProgressInfo() {
    }
}
