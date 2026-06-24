package com.p020hp.mobile.common.models.cdm;

import com.google.gson.annotations.SerializedName;
import com.p020hp.jipp.model.MaterialPurpose;
import com.tom_roush.fontbox.ttf.NamingTable;

public class CDMSystemIdentity {

    @SerializedName("deviceUuid")
    public String f760do;

    @SerializedName("makeAndModel")
    public MakeAndModel f3852for;

    @SerializedName("productNumber")
    public String f761if;

    public static class MakeAndModel {

        @SerializedName(MaterialPurpose.base)
        public String base;

        @SerializedName("family")
        public String family;

        @SerializedName(NamingTable.TAG)
        public String name;
    }
}
