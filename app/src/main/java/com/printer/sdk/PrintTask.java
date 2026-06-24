package com.printer.sdk;

import java.io.Serializable;

public class PrintTask implements Serializable {

    private String f2136a;

    private String f2137b;

    private int f2138c;

    private int f2139d;

    private int f2140e;

    private long f2141f;

    public PrintTask() {
        this.f2136a = "";
        this.f2137b = "";
        this.f2138c = 1;
        this.f2139d = 0;
        this.f2140e = 0;
        this.f2141f = 0L;
    }

    public PrintTask(String str, String str2) {
        this.f2138c = 1;
        this.f2139d = 0;
        this.f2140e = 0;
        this.f2141f = 0L;
        this.f2137b = str;
        this.f2136a = str2;
    }

    public PrintTask(String str, String str2, int i) {
        this.f2139d = 0;
        this.f2140e = 0;
        this.f2141f = 0L;
        this.f2137b = str;
        this.f2136a = str2;
        this.f2138c = i;
    }

    public PrintTask(String str, String str2, int i, int i2) {
        this.f2140e = 0;
        this.f2141f = 0L;
        this.f2137b = str;
        this.f2136a = str2;
        this.f2138c = i;
        this.f2139d = i2;
    }

    public int getCopy() {
        return this.f2138c;
    }

    public String getFullPath() {
        return this.f2137b + this.f2136a;
    }

    public String getName() {
        return this.f2136a;
    }

    public int getPast() {
        return this.f2140e;
    }

    public String getPath() {
        return this.f2137b;
    }

    public int getStus() {
        return this.f2139d;
    }

    public long getTaskid() {
        return this.f2141f;
    }

    public void setCopy(int i) {
        this.f2138c = i;
    }

    public void setName(String str) {
        this.f2136a = str;
    }

    public void setPast(int i) {
        this.f2140e = i;
    }

    public void setPath(String str) {
        this.f2137b = str;
    }

    public void setStus(int i) {
        this.f2139d = i;
    }

    public void setTaskid(long j) {
        this.f2141f = j;
    }
}
