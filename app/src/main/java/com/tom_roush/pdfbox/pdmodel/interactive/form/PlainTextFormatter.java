package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PlainText;
import java.io.IOException;
import java.util.List;

class PlainTextFormatter {
    private static final int FONTSCALE = 1000;
    private final AppearanceStyle appearanceStyle;
    private final PDPageContentStream contents;
    private float horizontalOffset;
    private final TextAlign textAlignment;
    private final PlainText textContent;
    private float verticalOffset;
    private final float width;
    private final boolean wrapLines;

    PlainTextFormatter(Builder builder, C19011 c19011) {
        this(builder);
    }

    enum TextAlign {
        LEFT(0),
        CENTER(1),
        RIGHT(2),
        JUSTIFY(4);

        private final int alignment;

        TextAlign(int i) {
            this.alignment = i;
        }

        int getTextAlign() {
            return this.alignment;
        }

        public static TextAlign valueOf(int i) {
            for (TextAlign textAlign : values()) {
                if (textAlign.getTextAlign() == i) {
                    return textAlign;
                }
            }
            return LEFT;
        }
    }

    static class Builder {
        private AppearanceStyle appearanceStyle;
        private PDPageContentStream contents;
        private PlainText textContent;
        private boolean wrapLines = false;
        private float width = 0.0f;
        private TextAlign textAlignment = TextAlign.LEFT;
        private float horizontalOffset = 0.0f;
        private float verticalOffset = 0.0f;

        Builder(PDPageContentStream pDPageContentStream) {
            this.contents = pDPageContentStream;
        }

        Builder style(AppearanceStyle appearanceStyle) {
            this.appearanceStyle = appearanceStyle;
            return this;
        }

        Builder wrapLines(boolean z) {
            this.wrapLines = z;
            return this;
        }

        Builder width(float f) {
            this.width = f;
            return this;
        }

        Builder textAlign(int i) {
            this.textAlignment = TextAlign.valueOf(i);
            return this;
        }

        Builder textAlign(TextAlign textAlign) {
            this.textAlignment = textAlign;
            return this;
        }

        Builder text(PlainText plainText) {
            this.textContent = plainText;
            return this;
        }

        Builder initialOffset(float f, float f2) {
            this.horizontalOffset = f;
            this.verticalOffset = f2;
            return this;
        }

        PlainTextFormatter build() {
            return new PlainTextFormatter(this, null);
        }
    }

    private PlainTextFormatter(Builder builder) {
        this.appearanceStyle = builder.appearanceStyle;
        this.wrapLines = builder.wrapLines;
        this.width = builder.width;
        this.contents = builder.contents;
        this.textContent = builder.textContent;
        this.textAlignment = builder.textAlignment;
        this.horizontalOffset = builder.horizontalOffset;
        this.verticalOffset = builder.verticalOffset;
    }

    public void format() throws IOException {
        PlainText plainText = this.textContent;
        if (plainText == null || plainText.getParagraphs().isEmpty()) {
            return;
        }
        boolean z = true;
        for (PlainText.Paragraph paragraph : this.textContent.getParagraphs()) {
            if (this.wrapLines) {
                processLines(paragraph.getLines(this.appearanceStyle.getFont(), this.appearanceStyle.getFontSize(), this.width), z);
                z = false;
            } else {
                float stringWidth = (this.appearanceStyle.getFont().getStringWidth(paragraph.getText()) * this.appearanceStyle.getFontSize()) / 1000.0f;
                float f = 0.0f;
                if (stringWidth < this.width) {
                    int i = C19011.f2389x27a78692[this.textAlignment.ordinal()];
                    if (i == 1) {
                        f = (this.width - stringWidth) / 2.0f;
                    } else if (i == 2) {
                        f = this.width - stringWidth;
                    }
                }
                this.contents.newLineAtOffset(this.horizontalOffset + f, this.verticalOffset);
                this.contents.showText(paragraph.getText());
            }
        }
    }

    static class C19011 {

        static final int[] f2389x27a78692;

        static {
            int[] iArr = new int[TextAlign.values().length];
            f2389x27a78692 = iArr;
            try {
                iArr[TextAlign.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2389x27a78692[TextAlign.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2389x27a78692[TextAlign.JUSTIFY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void processLines(List<PlainText.Line> list, boolean z) throws IOException {
        float f = 0.0f;
        float width = 0.0f;
        float interWordSpacing = 0.0f;
        for (PlainText.Line line : list) {
            int i = C19011.f2389x27a78692[this.textAlignment.ordinal()];
            if (i == 1) {
                width = (this.width - line.getWidth()) / 2.0f;
            } else if (i == 2) {
                width = this.width - line.getWidth();
            } else if (i != 3) {
                width = 0.0f;
            } else if (list.indexOf(line) != list.size() - 1) {
                interWordSpacing = line.getInterWordSpacing(this.width);
            }
            float f2 = (-f) + width + this.horizontalOffset;
            if (list.indexOf(line) == 0 && z) {
                this.contents.newLineAtOffset(f2, this.verticalOffset);
            } else {
                this.verticalOffset -= this.appearanceStyle.getLeading();
                this.contents.newLineAtOffset(f2, -this.appearanceStyle.getLeading());
            }
            f += f2;
            List<PlainText.Word> words = line.getWords();
            for (PlainText.Word word : words) {
                this.contents.showText(word.getText());
                float fFloatValue = ((Float) word.getAttributes().getIterator().getAttribute(PlainText.TextAttribute.WIDTH)).floatValue();
                if (words.indexOf(word) != words.size() - 1) {
                    this.contents.newLineAtOffset(fFloatValue + interWordSpacing, 0.0f);
                    f = f + fFloatValue + interWordSpacing;
                }
            }
        }
        this.horizontalOffset -= f;
    }
}
