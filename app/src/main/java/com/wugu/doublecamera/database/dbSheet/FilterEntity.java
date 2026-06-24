package com.wugu.doublecamera.database.dbSheet;

public class FilterEntity {
    private Long _id;
    private String filterImagePath;
    private int filterIntensity;
    private String filterKey;
    private String filterName;
    private int filterType;
    private int index;
    private boolean isEnable;

    public FilterEntity(Long l, int i, String str, String str2, String str3, int i2, int i3, boolean z) {
        this._id = l;
        this.index = i;
        this.filterName = str;
        this.filterKey = str2;
        this.filterImagePath = str3;
        this.filterIntensity = i2;
        this.filterType = i3;
        this.isEnable = z;
    }

    public FilterEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getFilterName() {
        return this.filterName;
    }

    public void setFilterName(String str) {
        this.filterName = str;
    }

    public String getFilterKey() {
        return this.filterKey;
    }

    public void setFilterKey(String str) {
        this.filterKey = str;
    }

    public String getFilterImagePath() {
        return this.filterImagePath;
    }

    public void setFilterImagePath(String str) {
        this.filterImagePath = str;
    }

    public int getFilterIntensity() {
        return this.filterIntensity;
    }

    public void setFilterIntensity(int i) {
        this.filterIntensity = i;
    }

    public int getFilterType() {
        return this.filterType;
    }

    public void setFilterType(int i) {
        this.filterType = i;
    }

    public boolean getIsEnable() {
        return this.isEnable;
    }

    public void setIsEnable(boolean z) {
        this.isEnable = z;
    }
}
