package com.wugu.doublecamera.bean;

import java.util.List;

public class FrameThemeInfo {
    private String backgroundPath;
    private String buttonPath;
    private List<FrameInfo> frameInfoList;
    private boolean isEnable;
    private String limitTime;
    private String remark;
    private String themeKey;
    private String themeName;

    public String getThemeKey() {
        return this.themeKey;
    }

    public void setThemeKey(String str) {
        this.themeKey = str;
    }

    public String getBackgroundPath() {
        return this.backgroundPath;
    }

    public void setBackgroundPath(String str) {
        this.backgroundPath = str;
    }

    public String getButtonPath() {
        return this.buttonPath;
    }

    public void setButtonPath(String str) {
        this.buttonPath = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String getLimitTime() {
        return this.limitTime;
    }

    public void setLimitTime(String str) {
        this.limitTime = str;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public List<FrameInfo> getFrameInfoList() {
        return this.frameInfoList;
    }

    public void setFrameInfoList(List<FrameInfo> list) {
        this.frameInfoList = list;
    }

    public String getThemeName() {
        return this.themeName;
    }

    public void setThemeName(String str) {
        this.themeName = str;
    }
}
