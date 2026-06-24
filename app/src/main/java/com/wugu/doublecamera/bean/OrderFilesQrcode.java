package com.wugu.doublecamera.bean;

import java.util.List;

public class OrderFilesQrcode {
    public String frame;
    public List<String> imgs;
    public String url;
    public String video;

    public OrderFilesQrcode(String str, List<String> list, String str2, String str3) {
        this.url = str;
        this.imgs = list;
        this.frame = str2;
        this.video = str3;
    }
}
