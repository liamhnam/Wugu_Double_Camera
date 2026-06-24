package com.printer.sdk;

public class PrintStus {

    private String f2111a = "v1.0";

    private String f2112b = "";

    private String f2113c = "";

    private String f2114d = "";

    private String f2115e = "";

    private String f2116f = "";

    private String f2117g = "";

    private String f2118h = "";

    private String f2119i = "";

    private String f2120j = "";

    private String f2121k = "";

    private String f2122l = "";

    private String f2123m = "";

    private String f2124n = "";

    private String f2125o = "";

    private int f2126p = 0;

    private String f2127q = "";

    private String f2128r = "";

    private String f2129s = "";

    private int f2130t = 0;

    private int f2131u = 0;

    private int f2132v = 0;

    private int f2133w = 0;

    private int f2134x = 0;

    private int f2135y = 0;

    public int getActPrintFreeBuff() {
        return this.f2130t;
    }

    public int getActPrintHalfSizeMediaCounter() {
        return this.f2134x;
    }

    public int getActPrintInitMediaCount() {
        return this.f2131u;
    }

    public String getActPrintMediaInfo() {
        return this.f2128r;
    }

    public int getActPrintRemainQuantity() {
        return this.f2132v;
    }

    public String getActPrintResolution() {
        return this.f2129s;
    }

    public int getActPrintStatus() {
        return this.f2126p;
    }

    public String getActPrintVersion() {
        return this.f2127q;
    }

    public int getActPrintVolumeLifeCounter() {
        return this.f2133w;
    }

    public int getActPrintVolumeMatteCounter() {
        return this.f2135y;
    }

    public String getPrintColorControlDataChecksum() {
        return this.f2125o;
    }

    public String getPrintColorControlDataVersion() {
        return this.f2124n;
    }

    public String getPrintFreeBuff() {
        return this.f2117g;
    }

    public String getPrintHalfSizeMediaCounter() {
        return this.f2122l;
    }

    public String getPrintHorResolution() {
        return this.f2115e;
    }

    public String getPrintInitMediaCount() {
        return this.f2118h;
    }

    public String getPrintMediaInfo() {
        return this.f2114d;
    }

    public String getPrintRemainQuantity() {
        return this.f2119i;
    }

    public String getPrintSerialNO() {
        return this.f2120j;
    }

    public String getPrintStatus() {
        return this.f2112b;
    }

    public String getPrintVerResolution() {
        return this.f2116f;
    }

    public String getPrintVersion() {
        return this.f2113c;
    }

    public String getPrintVolumeLifeCounter() {
        return this.f2121k;
    }

    public String getPrintVolumeMatteCounter() {
        return this.f2123m;
    }

    public String getVersion() {
        return this.f2111a;
    }

    public void setActPrintFreeBuff(int i) {
        this.f2130t = i;
    }

    public void setActPrintHalfSizeMediaCounter(int i) {
        this.f2134x = i;
    }

    public void setActPrintInitMediaCount(int i) {
        this.f2131u = i;
    }

    public void setActPrintMediaInfo(String str) {
        this.f2128r = str;
    }

    public void setActPrintRemainQuantity(int i) {
        this.f2132v = i;
    }

    public void setActPrintResolution(String str) {
        this.f2129s = str;
    }

    public void setActPrintStatus(int i) {
        this.f2126p = i;
    }

    public void setActPrintVersion(String str) {
        this.f2127q = str;
    }

    public void setActPrintVolumeLifeCounter(int i) {
        this.f2133w = i;
    }

    public void setActPrintVolumeMatteCounter(int i) {
        this.f2135y = i;
    }

    public void setPrintColorControlDataChecksum(String str) {
        this.f2125o = str;
    }

    public void setPrintColorControlDataVersion(String str) {
        this.f2124n = str;
    }

    public void setPrintFreeBuff(String str) {
        this.f2117g = str;
    }

    public void setPrintHalfSizeMediaCounter(String str) {
        this.f2122l = str;
    }

    public void setPrintHorResolution(String str) {
        this.f2115e = str;
    }

    public void setPrintInitMediaCount(String str) {
        this.f2118h = str;
    }

    public void setPrintMediaInfo(String str) {
        this.f2114d = str;
    }

    public void setPrintRemainQuantity(String str) {
        this.f2119i = str;
    }

    public void setPrintSerialNO(String str) {
        this.f2120j = str;
    }

    public void setPrintStatus(String str) {
        this.f2112b = str;
    }

    public void setPrintVerResolution(String str) {
        this.f2116f = str;
    }

    public void setPrintVersion(String str) {
        this.f2113c = str;
    }

    public void setPrintVolumeLifeCounter(String str) {
        this.f2121k = str;
    }

    public void setPrintVolumeMatteCounter(String str) {
        this.f2123m = str;
    }

    public void setVersion(String str) {
        this.f2111a = str;
    }

    public String toActString() {
        return "".concat("打印机状态 = ").concat("" + this.f2126p).concat("\r\n").concat("打印机型号 = ").concat(this.f2127q).concat("\r\n").concat("耗材型号 = ").concat(this.f2128r).concat("\r\n").concat("分辨率  = ").concat(this.f2129s).concat("\r\n").concat("打印机空闲缓存 = ").concat("" + this.f2130t).concat("\r\n").concat("初始耗材数量 = ").concat("" + this.f2131u).concat("\r\n").concat("剩余耗材数量 = ").concat("" + this.f2132v).concat("\r\n").concat("机打印计数器 = ").concat("" + this.f2133w).concat("\r\n").concat("半尺剩余耗材数量 = ").concat("" + this.f2134x).concat("\r\n").concat("打印机粗面打印计数器 = ").concat("" + this.f2135y).concat("\r\n");
    }

    public String toString() {
        return "".concat("Version = ").concat(this.f2111a).concat("\r\n").concat("PrintStatus = ").concat(this.f2112b).concat("\r\n").concat("PrintVersion = ").concat(this.f2113c).concat("\r\n").concat("PrintMediaInfo = ").concat(this.f2114d).concat("\r\n").concat("PrintHorResolution = ").concat(this.f2115e).concat("\r\n").concat("PrintVerResolution = ").concat(this.f2116f).concat("\r\n").concat("PrintFreeBuff = ").concat(this.f2117g).concat("\r\n").concat("PrintInitMediaCount = ").concat(this.f2118h).concat("\r\n").concat("PrintRemainQuantity = ").concat(this.f2119i).concat("\r\n").concat("PrintSerialNO = ").concat(this.f2120j).concat("\r\n").concat("PrintVolumeLifeCounter = ").concat(this.f2121k).concat("\r\n").concat("PrintHalfSizeMediaCounter = ").concat(this.f2122l).concat("\r\n").concat("PrintVolumeMatteCounter = ").concat(this.f2123m).concat("\r\n").concat("PrintColorControlDataVersion = ").concat(this.f2124n).concat("\r\n").concat("PrintColorControlDataChecksum = ").concat(this.f2125o).concat("\r\n");
    }
}
