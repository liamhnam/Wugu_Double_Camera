package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.arthenica.ffmpegkit.StreamInformation;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class PlainText {
    private static final float FONTSCALE = 1000.0f;
    private final List<Paragraph> paragraphs;

    PlainText(String str) {
        List listAsList = Arrays.asList(str.split("\\n"));
        this.paragraphs = new ArrayList();
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            this.paragraphs.add(new Paragraph((String) it.next()));
        }
    }

    PlainText(List<String> list) {
        this.paragraphs = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.paragraphs.add(new Paragraph(it.next()));
        }
    }

    List<Paragraph> getParagraphs() {
        return this.paragraphs;
    }

    static class TextAttribute extends AttributedCharacterIterator.Attribute {
        public static final AttributedCharacterIterator.Attribute WIDTH = new TextAttribute(StreamInformation.KEY_WIDTH);
        private static final long serialVersionUID = -3138885145941283005L;

        protected TextAttribute(String str) {
            super(str);
        }
    }

    static class Paragraph {
        private final String textContent;

        Paragraph(String str) {
            this.textContent = str;
        }

        String getText() {
            return this.textContent;
        }

        List<Line> getLines(PDFont pDFont, float f, float f2) throws IOException {
            BreakIterator lineInstance = BreakIterator.getLineInstance();
            lineInstance.setText(this.textContent);
            float f3 = f / PlainText.FONTSCALE;
            int iFirst = lineInstance.first();
            int next = lineInstance.next();
            ArrayList arrayList = new ArrayList();
            Line line = new Line();
            float stringWidth = 0.0f;
            while (true) {
                int i = next;
                int i2 = iFirst;
                iFirst = i;
                if (iFirst != -1) {
                    String strSubstring = this.textContent.substring(i2, iFirst);
                    float stringWidth2 = pDFont.getStringWidth(strSubstring) * f3;
                    stringWidth += stringWidth2;
                    if (stringWidth >= f2 && Character.isWhitespace(strSubstring.charAt(strSubstring.length() - 1))) {
                        stringWidth -= pDFont.getStringWidth(strSubstring.substring(strSubstring.length() - 1)) * f3;
                    }
                    if (stringWidth >= f2) {
                        line.setWidth(line.calculateWidth(pDFont, f));
                        arrayList.add(line);
                        line = new Line();
                        stringWidth = pDFont.getStringWidth(strSubstring) * f3;
                    }
                    AttributedString attributedString = new AttributedString(strSubstring);
                    attributedString.addAttribute(TextAttribute.WIDTH, Float.valueOf(stringWidth2));
                    Word word = new Word(strSubstring);
                    word.setAttributes(attributedString);
                    line.addWord(word);
                    next = lineInstance.next();
                } else {
                    line.setWidth(line.calculateWidth(pDFont, f));
                    arrayList.add(line);
                    return arrayList;
                }
            }
        }
    }

    static class Line {
        private float lineWidth;
        private final List<Word> words = new ArrayList();

        Line() {
        }

        float getWidth() {
            return this.lineWidth;
        }

        void setWidth(float f) {
            this.lineWidth = f;
        }

        float calculateWidth(PDFont pDFont, float f) throws IOException {
            float f2 = f / PlainText.FONTSCALE;
            float fFloatValue = 0.0f;
            for (Word word : this.words) {
                fFloatValue += ((Float) word.getAttributes().getIterator().getAttribute(TextAttribute.WIDTH)).floatValue();
                String text = word.getText();
                if (this.words.indexOf(word) == this.words.size() - 1 && Character.isWhitespace(text.charAt(text.length() - 1))) {
                    fFloatValue -= pDFont.getStringWidth(text.substring(text.length() - 1)) * f2;
                }
            }
            return fFloatValue;
        }

        List<Word> getWords() {
            return this.words;
        }

        float getInterWordSpacing(float f) {
            return (f - this.lineWidth) / (this.words.size() - 1);
        }

        void addWord(Word word) {
            this.words.add(word);
        }
    }

    static class Word {
        private AttributedString attributedString;
        private final String textContent;

        Word(String str) {
            this.textContent = str;
        }

        String getText() {
            return this.textContent;
        }

        AttributedString getAttributes() {
            return this.attributedString;
        }

        void setAttributes(AttributedString attributedString) {
            this.attributedString = attributedString;
        }
    }
}
