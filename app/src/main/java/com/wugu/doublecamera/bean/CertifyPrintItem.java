package com.wugu.doublecamera.bean;

public class CertifyPrintItem {
    public int aiPercent;
    public String filePath;
    public int price;
    public int printCount;

    public CertifyPrintItem(String str, int i) {
        this.filePath = str;
        this.printCount = i;
    }

    public CertifyPrintItem(String str, int i, int i2) {
        this.filePath = str;
        this.printCount = i;
        this.aiPercent = i2;
    }
}
