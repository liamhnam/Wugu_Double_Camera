package com.wugu.doublecamera.bean.p023vo;

public class CreateOrderVo {
    public String couponCode;
    public OrderDetail orderDetails;
    public int orderType;
    public String parentOrderId;
    public int paymentScanType;
    public int paymentType;
    public float realAmount;
    public float totalAmount;

    public CreateOrderVo(String str, String str2, int i, int i2, int i3, int i4, OrderDetail orderDetail) {
        this.couponCode = str;
        this.parentOrderId = str2;
        this.totalAmount = i / 100.0f;
        this.realAmount = i2 / 100.0f;
        this.paymentType = i3;
        if (i3 == 10) {
            this.paymentScanType = 0;
        } else if (i3 == 11) {
            this.paymentScanType = 2;
        }
        this.orderType = i4;
        this.orderDetails = orderDetail;
    }

    public static class OrderDetail {
        public String benefitType;
        public int isAI;
        public float price;
        public String productId;
        public int quantity;
        public String resName;
        public String topicId;
        public String topicName;

        public OrderDetail(String str, int i, int i2) {
            this.productId = str;
            this.quantity = i;
            this.price = i2 / 100.0f;
        }

        public OrderDetail(String str, int i, float f, int i2, String str2, String str3, String str4, String str5) {
            this.productId = str;
            this.quantity = i;
            this.price = f;
            this.isAI = i2;
            this.topicId = str2;
            this.topicName = str3;
            this.resName = str4;
            this.benefitType = str5;
        }
    }
}
