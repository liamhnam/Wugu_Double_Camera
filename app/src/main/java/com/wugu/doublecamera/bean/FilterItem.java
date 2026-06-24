package com.wugu.doublecamera.bean;

public class FilterItem {
    private String filterDemoImagePath;
    private String filterName;

    public FilterItem(String str, String str2) {
        this.filterName = str;
        this.filterDemoImagePath = str2;
    }

    public String getFilterName() {
        return this.filterName;
    }

    public void setFilterName(String str) {
        this.filterName = str;
    }

    public String getFilterDemoImagePath() {
        return this.filterDemoImagePath;
    }

    public void setFilterDemoImagePath(String str) {
        this.filterDemoImagePath = str;
    }
}
