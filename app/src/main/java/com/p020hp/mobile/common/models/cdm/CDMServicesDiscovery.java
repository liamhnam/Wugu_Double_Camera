package com.p020hp.mobile.common.models.cdm;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CDMServicesDiscovery {

    @SerializedName("services")
    public List<C1652if> services;

    @SerializedName("version")
    public String version;

    public static class C1651do {

        @SerializedName("rel")
        public String f756do;

        @SerializedName("href")
        public String f757if;
    }

    public static class C1652if {

        @SerializedName("serviceGun")
        public String f758do;

        @SerializedName("links")
        public List<C1651do> f759if;
    }
}
