package com.brother.sdk.scan;

import com.brother.sdk.common.Callback;
import java.io.File;

public abstract class ScanJobController extends Callback {
    private File mFolderPath;

    public abstract void onImageReadToFile(String str, int i);

    public ScanJobController(File file) {
        this.mFolderPath = file;
    }

    public File getOutputFolder() {
        return this.mFolderPath;
    }
}
