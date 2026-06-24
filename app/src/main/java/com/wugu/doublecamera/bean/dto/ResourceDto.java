package com.wugu.doublecamera.bean.dto;

import java.util.List;

public class ResourceDto {
    public int count;
    public List<ResDto> data;
    public int pageIndex;
    public int pageSize;

    public static class ResDto {
        public String hash;

        public String f2443id;
        public String index;
        public String limitTime;
        public String name;
        public String notes;
        public String parentId;
        public String resourceId;
        public String themeName;
        public int type;
        public String url;
    }
}
