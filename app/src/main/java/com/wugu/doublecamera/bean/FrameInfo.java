package com.wugu.doublecamera.bean;

import android.text.TextUtils;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.enums.FrameRemarkEnum;
import com.wugu.doublecamera.utils.StringUtil;
import java.util.List;

public class FrameInfo {
    private int addOPrice;
    private int addPrice;
    private String aiModuleIds;
    private int burstModeCount;
    private String frameKey;
    private String frameName;
    private String framePath;
    private int frameType;
    private boolean isEnable;
    private boolean isNeedCut;
    private boolean isReplaceBg;
    private int oPrice;
    private List<FramePhotoInfo> photoInfoList;
    private int price;
    private int printCount;
    private String printerColorParam;
    private String remark;

    public FrameInfo() {
        this.frameType = 0;
    }

    public FrameInfo(FrameEntity frameEntity, List<FramePhotoInfo> list) {
        this.frameType = 0;
        if (frameEntity != null) {
            this.frameType = frameEntity.getFrameType();
            this.frameKey = frameEntity.getFrameKey();
            this.frameName = frameEntity.getFrameName();
            this.framePath = frameEntity.getFrameLocalPath();
            this.burstModeCount = frameEntity.getBurstModeCount();
            this.oPrice = frameEntity.getOPrice();
            this.price = frameEntity.getPrice();
            this.addOPrice = frameEntity.getAddOPrice();
            this.addPrice = frameEntity.getAddPrice();
            this.isNeedCut = frameEntity.getIsNeedCut();
            this.printCount = frameEntity.getPrintCount();
            this.aiModuleIds = frameEntity.getAiModeIds();
            this.remark = frameEntity.getRemark();
            this.printerColorParam = frameEntity.getColorParam();
            this.isEnable = frameEntity.getIsEnable();
            this.isReplaceBg = frameEntity.getIsReplaceBg();
            this.photoInfoList = list;
        }
    }

    public FrameInfo(int i, String str, String str2, String str3, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str4, String str5, String str6, boolean z3, List<FramePhotoInfo> list) {
        this.frameType = i;
        this.frameKey = str;
        this.frameName = str2;
        this.framePath = str3;
        this.burstModeCount = i2;
        this.oPrice = i3;
        this.price = i4;
        this.addOPrice = i5;
        this.addPrice = i6;
        this.isNeedCut = z;
        this.isReplaceBg = z2;
        this.printCount = i7;
        this.printerColorParam = str5;
        this.aiModuleIds = str6;
        this.remark = str4;
        this.isEnable = z3;
        this.photoInfoList = list;
    }

    public int getFrameType() {
        return this.frameType;
    }

    public void setFrameType(int i) {
        this.frameType = i;
    }

    public List<FramePhotoInfo> getPhotoInfoList() {
        return this.photoInfoList;
    }

    public void setPhotoInfoList(List<FramePhotoInfo> list) {
        this.photoInfoList = list;
    }

    public String getFrameKey() {
        return this.frameKey;
    }

    public void setFrameKey(String str) {
        this.frameKey = str;
    }

    public String getFrameName() {
        return this.frameName;
    }

    public void setFrameName(String str) {
        this.frameName = str;
    }

    public String getFramePath() {
        return this.framePath;
    }

    public void setFramePath(String str) {
        this.framePath = str;
    }

    public int getBurstModeCount() {
        return this.burstModeCount;
    }

    public void setBurstModeCount(int i) {
        this.burstModeCount = i;
    }

    public int getPrintCount() {
        return this.printCount;
    }

    public void setPrintCount(int i) {
        this.printCount = i;
    }

    public int getoPrice() {
        return this.oPrice;
    }

    public void setoPrice(int i) {
        this.oPrice = i;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public int getAddOPrice() {
        return this.addOPrice;
    }

    public void setAddOPrice(int i) {
        this.addOPrice = i;
    }

    public int getAddPrice() {
        return this.addPrice;
    }

    public void setAddPrice(int i) {
        this.addPrice = i;
    }

    public boolean isNeedCut() {
        return this.isNeedCut;
    }

    public boolean isBrokenLine() {
        if (TextUtils.isEmpty(this.remark) || !this.remark.contains(FrameRemarkEnum.IS_BROKEN_LINE)) {
            return false;
        }
        String remarkValueByKey = StringUtil.getRemarkValueByKey(this.remark, FrameRemarkEnum.IS_BROKEN_LINE);
        return !TextUtils.isEmpty(remarkValueByKey) && remarkValueByKey.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    public void setNeedCut(boolean z) {
        this.isNeedCut = z;
    }

    public boolean isReplaceBg() {
        return this.isReplaceBg;
    }

    public void setReplaceBg(boolean z) {
        this.isReplaceBg = z;
    }

    public String getAiModuleIds() {
        return this.aiModuleIds;
    }

    public void setAiModuleIds(String str) {
        this.aiModuleIds = str;
    }

    public String getPrinterColorParam() {
        return this.printerColorParam;
    }

    public void setPrinterColorParam(String str) {
        this.printerColorParam = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }
}
