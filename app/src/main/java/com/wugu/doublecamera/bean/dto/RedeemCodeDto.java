package com.wugu.doublecamera.bean.dto;

public class RedeemCodeDto {
    private float amount;
    private String code;
    private int count;
    private String deviceId;
    private float spend;
    private int type;

    public RedeemCodeDto(int i) {
        this.type = i;
    }

    public RedeemCodeDto() {
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public float getSpend() {
        return this.spend;
    }

    public void setSpend(float f) {
        this.spend = f;
    }
}
