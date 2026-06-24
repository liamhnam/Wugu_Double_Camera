package com.tom_roush.fontbox.util.autodetect;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class NativeFontDirFinder implements FontDirFinder {
    protected abstract String[] getSearchableDirectories();

    @Override
    public List<File> find() {
        ArrayList arrayList = new ArrayList();
        String[] searchableDirectories = getSearchableDirectories();
        if (searchableDirectories != null) {
            for (String str : searchableDirectories) {
                File file = new File(str);
                if (file.exists() && file.canRead()) {
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }
}
