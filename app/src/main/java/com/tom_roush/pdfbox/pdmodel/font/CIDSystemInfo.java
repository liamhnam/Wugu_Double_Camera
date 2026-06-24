package com.tom_roush.pdfbox.pdmodel.font;

public final class CIDSystemInfo {
    private final String ordering;
    private final String registry;
    private final int supplement;

    CIDSystemInfo(String str, String str2, int i) {
        this.registry = str;
        this.ordering = str2;
        this.supplement = i;
    }

    public String getRegistry() {
        return this.registry;
    }

    public String getOrdering() {
        return this.ordering;
    }

    public int getSupplement() {
        return this.supplement;
    }

    public String toString() {
        return getRegistry() + "-" + getOrdering() + "-" + getSupplement();
    }
}
