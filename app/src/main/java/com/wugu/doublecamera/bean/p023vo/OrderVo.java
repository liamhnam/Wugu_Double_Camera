package com.wugu.doublecamera.bean.p023vo;

public class OrderVo {
    private String createTime;
    private String finalPhotoUrl;
    private String frameKey;
    private String orderNumber;
    private int orderPrice;
    private int printCount;
    private String remark;
    private String subPhotoUrls;

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String str) {
        this.orderNumber = str;
    }

    public String getFrameKey() {
        return this.frameKey;
    }

    public void setFrameKey(String str) {
        this.frameKey = str;
    }

    public int getPrintCount() {
        return this.printCount;
    }

    public void setPrintCount(int i) {
        this.printCount = i;
    }

    public int getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(int i) {
        this.orderPrice = i;
    }

    public String getFinalPhotoUrl() {
        return this.finalPhotoUrl;
    }

    public void setFinalPhotoUrl(String str) {
        this.finalPhotoUrl = str;
    }

    public String getSubPhotoUrls() {
        return this.subPhotoUrls;
    }

    public void setSubPhotoUrls(String str) {
        this.subPhotoUrls = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }
}
