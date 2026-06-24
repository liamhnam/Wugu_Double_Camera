package com.tom_roush.fontbox.util.autodetect;

public class MacFontDirFinder extends NativeFontDirFinder {
    @Override
    protected String[] getSearchableDirectories() {
        return new String[]{System.getProperty("user.home") + "/Library/Fonts/", "/Library/Fonts/", "/System/Library/Fonts/", "/Network/Library/Fonts/"};
    }
}
