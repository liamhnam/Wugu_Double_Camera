package com.brother.sdk.common;

public enum ContentType {
    IMAGE_JPEG(true),
    IMAGE_PNG(true),
    IMAGE_BMP(true),
    IMAGE_TIFF(true),
    DOC_PDF(false),
    DOC_MS_WORD(false),
    DOC_MS_EXCEL(false),
    DOC_MS_POWERPOINT(false),
    DOC_TEXT(false);

    public final boolean isImage;

    ContentType(boolean z) {
        this.isImage = z;
    }
}
