package org.simpleframework.xml.transform;

import java.io.File;

class FileTransform implements Transform<File> {
    FileTransform() {
    }

    @Override
    public File read(String str) {
        return new File(str);
    }

    @Override
    public String write(File file) {
        return file.getPath();
    }
}
