package com.wugu.doublecamera.bean.dto;

import java.util.List;

public class PatchVersionDto {
    public String newestApkUrl;
    public List<PatchInfo> patchInfoList;

    public static class PatchInfo {
        public String apkHash;
        public int newVersionCode;
        public int odlVersionCode;
        public String patchUrl;
    }
}
