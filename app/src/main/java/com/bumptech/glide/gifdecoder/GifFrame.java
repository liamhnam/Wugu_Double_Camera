package com.bumptech.glide.gifdecoder;

class GifFrame {
    static final int DISPOSAL_BACKGROUND = 2;
    static final int DISPOSAL_NONE = 1;
    static final int DISPOSAL_PREVIOUS = 3;
    static final int DISPOSAL_UNSPECIFIED = 0;
    int bufferFrameStart;
    int delay;
    int dispose;

    int f534ih;
    boolean interlace;

    int f535iw;

    int f536ix;

    int f537iy;
    int[] lct;
    int transIndex;
    boolean transparency;

    GifFrame() {
    }
}
