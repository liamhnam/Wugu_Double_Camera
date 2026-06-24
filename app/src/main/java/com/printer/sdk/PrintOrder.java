package com.printer.sdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PrintOrder implements Serializable {

    private List<PrintTask> f2101a;

    private long f2102b;

    private String f2103c;

    private int f2104d;

    private int f2105e;

    private boolean f2106f;

    private String f2107g;

    private boolean f2108h;

    private String f2109i;

    private String f2110j;

    public PrintOrder() {
        this.f2101a = new ArrayList();
        this.f2102b = 0L;
        this.f2103c = "";
        this.f2104d = 0;
        this.f2105e = 0;
        this.f2106f = false;
        this.f2107g = "";
        this.f2108h = false;
        this.f2109i = "";
        this.f2110j = "正常裁切";
    }

    public PrintOrder(List<PrintTask> list, String str) {
        new ArrayList();
        this.f2102b = 0L;
        this.f2104d = 0;
        this.f2105e = 0;
        this.f2106f = false;
        this.f2107g = "";
        this.f2108h = false;
        this.f2109i = "";
        this.f2110j = "正常裁切";
        this.f2101a = list;
        this.f2103c = str;
    }

    public PrintOrder(List<PrintTask> list, String str, int i) {
        new ArrayList();
        this.f2102b = 0L;
        this.f2104d = 0;
        this.f2106f = false;
        this.f2107g = "";
        this.f2108h = false;
        this.f2109i = "";
        this.f2110j = "正常裁切";
        this.f2101a = list;
        this.f2103c = str;
        this.f2105e = i;
    }

    public String getCutType() {
        return this.f2110j;
    }

    public String getMedia() {
        return this.f2107g;
    }

    public String getName() {
        return this.f2103c;
    }

    public long getOrderid() {
        return this.f2102b;
    }

    public int getPics() {
        return this.f2105e;
    }

    public String getPrns() {
        return this.f2109i;
    }

    public int getStus() {
        return this.f2104d;
    }

    public List<PrintTask> getTask() {
        return this.f2101a;
    }

    public boolean isB600() {
        return this.f2108h;
    }

    public boolean isMatte() {
        return this.f2106f;
    }

    public void setB600(boolean z) {
        this.f2108h = z;
    }

    public void setCutType(String str) {
        this.f2110j = str;
    }

    public void setMatte(boolean z) {
        this.f2106f = z;
    }

    public void setMedia(String str) {
        this.f2107g = str;
    }

    public void setName(String str) {
        this.f2103c = str;
    }

    public void setOrderid(long j) {
        this.f2102b = j;
    }

    public void setPics(int i) {
        this.f2105e = i;
    }

    public void setPrns(String str) {
        this.f2109i = str;
    }

    public void setStus(int i) {
        this.f2104d = i;
    }

    public void setTask(List<PrintTask> list) {
        this.f2101a = list;
    }

    public String toString() {
        return "\r\n".concat("orderid = ").concat("" + this.f2102b).concat("\r\n").concat("name = ").concat(this.f2103c).concat("\r\n").concat("stus = ").concat("" + this.f2104d).concat("\r\n").concat("media = ").concat(this.f2107g).concat("\r\n").concat("pics = ").concat("" + this.f2105e).concat("\r\n").concat("matte = ").concat("" + this.f2106f).concat("\r\n").concat("b600 = ").concat("" + this.f2108h).concat("\r\n").concat("cutType = ").concat("" + this.f2110j).concat("\r\n").concat("task = ").concat("" + this.f2101a.size()).concat("\r\n");
    }
}
