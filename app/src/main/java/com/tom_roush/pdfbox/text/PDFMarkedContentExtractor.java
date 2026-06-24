package com.tom_roush.pdfbox.text;

import com.tom_roush.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequence;
import com.tom_roush.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequenceWithProperties;
import com.tom_roush.pdfbox.contentstream.operator.markedcontent.DrawObject;
import com.tom_roush.pdfbox.contentstream.operator.markedcontent.EndMarkedContentSequence;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PDFMarkedContentExtractor extends PDFTextStreamEngine {
    private final Map<String, List<TextPosition>> characterListMapping;
    private final Stack<PDMarkedContent> currentMarkedContents;
    private final List<PDMarkedContent> markedContents;
    private final boolean suppressDuplicateOverlappingText;

    private boolean within(float f, float f2, float f3) {
        return f2 > f - f3 && f2 < f + f3;
    }

    @Override
    public void processPage(PDPage pDPage) throws IOException {
        super.processPage(pDPage);
    }

    public PDFMarkedContentExtractor() throws IOException {
        this(null);
    }

    public PDFMarkedContentExtractor(String str) throws IOException {
        this.suppressDuplicateOverlappingText = true;
        this.markedContents = new ArrayList();
        this.currentMarkedContents = new Stack<>();
        this.characterListMapping = new HashMap();
        addOperator(new BeginMarkedContentSequenceWithProperties());
        addOperator(new BeginMarkedContentSequence());
        addOperator(new EndMarkedContentSequence());
        addOperator(new DrawObject());
    }

    public void beginMarkedContentSequence(COSName cOSName, COSDictionary cOSDictionary) {
        PDMarkedContent pDMarkedContentCreate = PDMarkedContent.create(cOSName, cOSDictionary);
        if (this.currentMarkedContents.isEmpty()) {
            this.markedContents.add(pDMarkedContentCreate);
        } else {
            PDMarkedContent pDMarkedContentPeek = this.currentMarkedContents.peek();
            if (pDMarkedContentPeek != null) {
                pDMarkedContentPeek.addMarkedContent(pDMarkedContentCreate);
            }
        }
        this.currentMarkedContents.push(pDMarkedContentCreate);
    }

    public void endMarkedContentSequence() {
        if (this.currentMarkedContents.isEmpty()) {
            return;
        }
        this.currentMarkedContents.pop();
    }

    public void xobject(PDXObject pDXObject) {
        if (this.currentMarkedContents.isEmpty()) {
            return;
        }
        this.currentMarkedContents.peek().addXObject(pDXObject);
    }

    @Override
    protected void processTextPosition(TextPosition textPosition) {
        boolean z;
        boolean z2;
        String unicode = textPosition.getUnicode();
        float x = textPosition.getX();
        float y = textPosition.getY();
        List<TextPosition> arrayList = this.characterListMapping.get(unicode);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.characterListMapping.put(unicode, arrayList);
        }
        float width = (textPosition.getWidth() / unicode.length()) / 3.0f;
        Iterator<TextPosition> it = arrayList.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            TextPosition next = it.next();
            String unicode2 = next.getUnicode();
            float x2 = next.getX();
            float y2 = next.getY();
            if (unicode2 != null && within(x2, x, width) && within(y2, y, width)) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            arrayList.add(textPosition);
            z = true;
        }
        if (z) {
            ArrayList arrayList2 = new ArrayList();
            if (arrayList2.isEmpty()) {
                arrayList2.add(textPosition);
            } else {
                TextPosition textPosition2 = (TextPosition) arrayList2.get(arrayList2.size() - 1);
                if (textPosition.isDiacritic() && textPosition2.contains(textPosition)) {
                    textPosition2.mergeDiacritic(textPosition);
                } else if (textPosition2.isDiacritic() && textPosition.contains(textPosition2)) {
                    textPosition.mergeDiacritic(textPosition2);
                    arrayList2.remove(arrayList2.size() - 1);
                    arrayList2.add(textPosition);
                } else {
                    arrayList2.add(textPosition);
                }
            }
            if (this.currentMarkedContents.isEmpty()) {
                return;
            }
            this.currentMarkedContents.peek().addText(textPosition);
        }
    }

    public List<PDMarkedContent> getMarkedContents() {
        return this.markedContents;
    }
}
