package com.wugu.doublecamera.bean.dto;

import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.utils.MathUtil;
import java.util.List;

public class AllResDto {
    public int count;
    public List<Data> data;
    public int pageIndex;
    public int pageSize;

    public static class Data {
        public String benefitType;
        public List<Items> items;
        public int model;
        public int orderNo;
        public String topicId;
        public String topicName;
    }

    public static class Items {
        public float addNowPrice;
        public float addPrice;
        public String aiModeIds;
        public int burstModeCount;
        public String easyId;
        public int frameType;
        public String hash;
        public int isAddArCode;
        public int isAddQrCode;
        public int isBrokenLine;
        public int isNeedCut;
        public int isReplaceBg;
        public String name;
        public int orderNo;
        public List<PhotoList> photoList;
        public int printCount;
        public String printer;
        public float productNowPrice;
        public float productPrice;
        public String rIndex;
        public String themeName;
        public String topicId;
        public int type;
        public String url;

        public FrameEntity getFrameEntity(String str, String str2, String str3, int i) {
            List<PhotoList> list = this.photoList;
            return new FrameEntity(null, this.easyId, this.name, str2, str3, str, i, list != null ? list.size() : 0, this.burstModeCount, MathUtil.getPriceValue(this.productPrice), MathUtil.getPriceValue(this.productNowPrice), MathUtil.getPriceValue(this.addPrice), MathUtil.getPriceValue(this.addNowPrice), this.printCount, this.isNeedCut == 1, this.isReplaceBg == 1, this.printer, this.aiModeIds, getRemarkStr(), this.orderNo, true);
        }

        public String getRemarkStr() {
            return "isAddQrCode:" + this.isAddQrCode + ",isAddArCode:" + this.isAddArCode + ",isBrokenLine:" + this.isBrokenLine + ",";
        }
    }

    public static class PhotoList {
        public int degrees;
        public int height;
        public int locationX;
        public int locationY;
        public String notes;
        public int width;

        public FramePhotoEntity getFramePhotoEntity(String str) {
            return new FramePhotoEntity(null, str, this.height, this.width, this.locationX, this.locationY, this.degrees, this.notes);
        }
    }
}
