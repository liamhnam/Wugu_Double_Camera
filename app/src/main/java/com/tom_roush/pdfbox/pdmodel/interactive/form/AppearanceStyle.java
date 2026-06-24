package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.pdmodel.font.PDFont;

class AppearanceStyle {
    private PDFont font;
    private float fontSize = 12.0f;
    private float leading = 14.4f;

    AppearanceStyle() {
    }

    PDFont getFont() {
        return this.font;
    }

    void setFont(PDFont pDFont) {
        this.font = pDFont;
    }

    float getFontSize() {
        return this.fontSize;
    }

    void setFontSize(float f) {
        this.fontSize = f;
        this.leading = f * 1.2f;
    }

    float getLeading() {
        return this.leading;
    }

    void setLeading(float f) {
        this.leading = f;
    }
}
