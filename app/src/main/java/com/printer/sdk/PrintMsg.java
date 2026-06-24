package com.printer.sdk;

public class PrintMsg {

    private int f2094a;

    private long f2095b;

    private long f2096c;

    private String f2097d;

    private PrintStus f2098e;

    private PrintOrder f2099f;

    private String[] f2100g;

    public PrintMsg(int i, long j, long j2, String str) {
        this.f2098e = null;
        this.f2099f = null;
        this.f2097d = str;
        this.f2094a = i;
        this.f2095b = j;
        this.f2096c = j2;
    }

    public PrintMsg(int i, long j, PrintOrder printOrder, String str) {
        this.f2096c = 0L;
        this.f2098e = null;
        this.f2099f = printOrder;
        this.f2094a = i;
        this.f2095b = j;
        this.f2097d = str;
    }

    public PrintMsg(int i, long j, String str) {
        this.f2096c = 0L;
        this.f2098e = null;
        this.f2099f = null;
        this.f2097d = str;
        this.f2094a = i;
        this.f2095b = j;
    }

    public PrintMsg(int i, PrintStus printStus) {
        this.f2096c = 0L;
        this.f2097d = "";
        this.f2099f = null;
        this.f2098e = printStus;
        this.f2094a = i;
        this.f2095b = 0L;
    }

    public PrintMsg(int i, String str) {
        this.f2096c = 0L;
        this.f2098e = null;
        this.f2099f = null;
        this.f2097d = str;
        this.f2094a = i;
        this.f2095b = 0L;
    }

    public PrintMsg(int i, String[] strArr) {
        this.f2095b = 0L;
        this.f2096c = 0L;
        this.f2097d = "";
        this.f2098e = null;
        this.f2099f = null;
        this.f2094a = i;
        this.f2100g = strArr;
    }

    public PrintMsg(String str) {
        this.f2096c = 0L;
        this.f2098e = null;
        this.f2099f = null;
        this.f2094a = 0;
        this.f2095b = 0L;
        this.f2097d = str;
    }

    public long getArg1() {
        return this.f2095b;
    }

    public long getArg2() {
        return this.f2096c;
    }

    public String[] getIcc() {
        return this.f2100g;
    }

    public String getMsg() {
        return this.f2097d;
    }

    public PrintStus getPrintStus() {
        return this.f2098e;
    }

    public int getRet() {
        return this.f2094a;
    }

    public PrintOrder getmPrintOrder() {
        return this.f2099f;
    }

    public PrintStus getmPrintStus() {
        return this.f2098e;
    }

    public void setArg1(long j) {
        this.f2095b = j;
    }

    public void setArg2(long j) {
        this.f2096c = j;
    }

    public void setIcc(String[] strArr) {
        this.f2100g = strArr;
    }

    public void setMsg(String str) {
        this.f2097d = str;
    }

    public void setPrintStus(PrintStus printStus) {
        this.f2098e = printStus;
    }

    public void setRet(int i) {
        this.f2094a = i;
    }

    public void setmPrintOrder(PrintOrder printOrder) {
        this.f2099f = printOrder;
    }

    public void setmPrintStus(PrintStus printStus) {
        this.f2098e = printStus;
    }

    public String toString() {
        return "PMsg=".concat("{ ").concat("msg:'" + this.f2097d + "', ").concat("ret:'" + this.f2094a + "', ").concat("arg1:" + this.f2095b).concat("arg2:" + this.f2096c).concat(" }");
    }
}
