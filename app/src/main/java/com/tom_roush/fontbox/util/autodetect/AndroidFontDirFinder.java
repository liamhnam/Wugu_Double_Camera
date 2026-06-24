package com.tom_roush.fontbox.util.autodetect;

public class AndroidFontDirFinder extends NativeFontDirFinder {
    @Override
    protected String[] getSearchableDirectories() {
        return new String[]{"/system/fonts"};
    }
}
