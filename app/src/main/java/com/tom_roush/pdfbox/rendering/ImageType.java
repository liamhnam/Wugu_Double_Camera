package com.tom_roush.pdfbox.rendering;

import android.graphics.Bitmap;

public enum ImageType {
    BINARY {
        @Override
        Bitmap.Config toBitmapConfig() {
            return Bitmap.Config.ALPHA_8;
        }
    },
    GRAY {
        @Override
        Bitmap.Config toBitmapConfig() {
            return Bitmap.Config.ALPHA_8;
        }
    },
    RGB {
        @Override
        Bitmap.Config toBitmapConfig() {
            return Bitmap.Config.ARGB_8888;
        }
    },
    ARGB {
        @Override
        Bitmap.Config toBitmapConfig() {
            return Bitmap.Config.ARGB_8888;
        }
    };

    abstract Bitmap.Config toBitmapConfig();
}
