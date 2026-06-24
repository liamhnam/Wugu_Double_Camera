package com.printer.sdk;

public class PrintUserTask {

    private String f2149a;

    private int f2150b;

    private long f2151c = 0;

    public PrintUserTask(String str, int i) {
        this.f2149a = str;
        this.f2150b = i;
    }

    public int getCopy() {
        return this.f2150b;
    }

    public String getPath() {
        return this.f2149a;
    }

    public long getTaskid() {
        return this.f2151c;
    }

    public void setCopy(int i) {
        this.f2150b = i;
    }

    public void setPath(String str) {
        this.f2149a = str;
    }

    public void setTaskid(long j) {
        this.f2151c = j;
    }
}
