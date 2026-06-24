package com.printer.sdk;

import java.util.ArrayList;
import java.util.List;

public class PrintUserOrder {

    List<PrintUserTask> f2142a;

    private long f2143b;

    private String f2144c;

    private String f2145d;

    private String f2146e;

    private boolean f2147f;

    private String f2148g;

    public PrintUserOrder() {
        this.f2142a = new ArrayList();
        this.f2143b = 0L;
        this.f2144c = "";
        this.f2145d = "";
        this.f2146e = "";
        this.f2147f = false;
        this.f2148g = "正常裁切";
    }

    public PrintUserOrder(List<PrintUserTask> list, String str, String str2, String str3, String str4) {
        new ArrayList();
        this.f2143b = 0L;
        this.f2147f = false;
        this.f2142a = list;
        this.f2144c = str;
        this.f2145d = str2;
        this.f2146e = str3;
        this.f2148g = str4;
    }

    public String getBdpi() {
        return this.f2145d;
    }

    public String getCutType() {
        return this.f2148g;
    }

    public String getMode() {
        return this.f2144c;
    }

    public long getOrderid() {
        return this.f2143b;
    }

    public String getPrns() {
        return this.f2146e;
    }

    public List<PrintUserTask> getTask() {
        return this.f2142a;
    }

    public boolean isMatte() {
        return this.f2147f;
    }

    public void setBdpi(String str) {
        this.f2145d = str;
    }

    public void setCutType(String str) {
        this.f2148g = str;
    }

    public void setMatte(boolean z) {
        this.f2147f = z;
    }

    public void setMode(String str) {
        this.f2144c = str;
    }

    public void setOrderid(long j) {
        this.f2143b = j;
    }

    public void setPrns(String str) {
        this.f2146e = str;
    }

    public void setTask(List<PrintUserTask> list) {
        this.f2142a = list;
    }
}
