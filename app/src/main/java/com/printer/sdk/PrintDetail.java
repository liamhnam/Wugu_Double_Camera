package com.printer.sdk;

import android.graphics.Bitmap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PrintDetail implements Serializable {

    private List<PrintTask> f2074a = new ArrayList();

    private int f2075b = 0;

    private int f2076c = 0;

    private int f2077d = 0;

    private int f2078e = 0;

    private String f2079f = "";

    private Bitmap f2080g;

    public Bitmap getActBitmap() {
        return this.f2080g;
    }

    public String getActBitmapName() {
        return this.f2079f;
    }

    public int getCuter() {
        return this.f2075b;
    }

    public int getMatte() {
        return this.f2078e;
    }

    public int getOvercoat() {
        return this.f2077d;
    }

    public int getRewind() {
        return this.f2076c;
    }

    public List<PrintTask> getTask() {
        return this.f2074a;
    }

    public void setActBitmap(Bitmap bitmap) {
        this.f2080g = bitmap;
    }

    public void setActBitmapName(String str) {
        this.f2079f = str;
    }

    public void setCuter(int i) {
        this.f2075b = i;
    }

    public void setMatte(int i) {
        this.f2078e = i;
    }

    public void setOvercoat(int i) {
        this.f2077d = i;
    }

    public void setRewind(int i) {
        this.f2076c = i;
    }

    public void setTask(List<PrintTask> list) {
        this.f2074a = list;
    }
}
