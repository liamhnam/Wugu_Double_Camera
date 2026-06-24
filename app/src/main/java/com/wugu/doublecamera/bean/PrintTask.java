package com.wugu.doublecamera.bean;

public class PrintTask {
    public boolean arg1;
    public int count;
    public String filePath;

    public PrintTask(String str, int i, boolean z) {
        this.filePath = str;
        this.count = i;
        this.arg1 = z;
    }
}
