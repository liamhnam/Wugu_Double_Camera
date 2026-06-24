package com.tom_roush.fontbox.cff;

public abstract class FDSelect {
    protected final CFFCIDFont owner;

    public abstract int getFDIndex(int i);

    public FDSelect(CFFCIDFont cFFCIDFont) {
        this.owner = cFFCIDFont;
    }
}
