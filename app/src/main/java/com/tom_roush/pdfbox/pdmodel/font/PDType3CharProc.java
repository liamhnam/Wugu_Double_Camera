package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.pdfbox.contentstream.PDContentStream;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class PDType3CharProc implements COSObjectable, PDContentStream {
    private final COSStream charStream;
    private final PDType3Font font;

    public PDType3CharProc(PDType3Font pDType3Font, COSStream cOSStream) {
        this.font = pDType3Font;
        this.charStream = cOSStream;
    }

    @Override
    public COSStream getCOSObject() {
        return this.charStream;
    }

    public PDType3Font getFont() {
        return this.font;
    }

    public PDStream getContentStream() {
        return new PDStream(this.charStream);
    }

    @Override
    public InputStream getContents() throws IOException {
        return this.charStream.createInputStream();
    }

    @Override
    public PDResources getResources() {
        return this.font.getResources();
    }

    @Override
    public PDRectangle getBBox() {
        return this.font.getFontBBox();
    }

    public PDRectangle getGlyphBBox() throws IOException {
        ArrayList arrayList = new ArrayList();
        PDFStreamParser pDFStreamParser = new PDFStreamParser(this);
        for (Object nextToken = pDFStreamParser.parseNextToken(); nextToken != null; nextToken = pDFStreamParser.parseNextToken()) {
            if (nextToken instanceof COSObject) {
                arrayList.add(((COSObject) nextToken).getObject());
            } else {
                if (nextToken instanceof Operator) {
                    if (!((Operator) nextToken).getName().equals("d1") || arrayList.size() != 6) {
                        return null;
                    }
                    for (int i = 0; i < 6; i++) {
                        if (!(arrayList.get(i) instanceof COSNumber)) {
                            return null;
                        }
                    }
                    return new PDRectangle(((COSNumber) arrayList.get(2)).floatValue(), ((COSNumber) arrayList.get(3)).floatValue(), ((COSNumber) arrayList.get(4)).floatValue() - ((COSNumber) arrayList.get(2)).floatValue(), ((COSNumber) arrayList.get(5)).floatValue() - ((COSNumber) arrayList.get(3)).floatValue());
                }
                arrayList.add((COSBase) nextToken);
            }
        }
        return null;
    }

    @Override
    public Matrix getMatrix() {
        return this.font.getFontMatrix();
    }

    public float getWidth() throws IOException {
        ArrayList arrayList = new ArrayList();
        PDFStreamParser pDFStreamParser = new PDFStreamParser(this);
        for (Object nextToken = pDFStreamParser.parseNextToken(); nextToken != null; nextToken = pDFStreamParser.parseNextToken()) {
            if (nextToken instanceof COSObject) {
                arrayList.add(((COSObject) nextToken).getObject());
            } else {
                if (nextToken instanceof Operator) {
                    return parseWidth((Operator) nextToken, arrayList);
                }
                arrayList.add((COSBase) nextToken);
            }
        }
        throw new IOException("Unexpected end of stream");
    }

    private float parseWidth(Operator operator, List list) throws IOException {
        if (operator.getName().equals("d0") || operator.getName().equals("d1")) {
            Object obj = list.get(0);
            if (obj instanceof Number) {
                return ((Number) obj).floatValue();
            }
            if (obj instanceof COSNumber) {
                return ((COSNumber) obj).floatValue();
            }
            throw new IOException("Unexpected argument type: " + obj.getClass().getName());
        }
        throw new IOException("First operator must be d0 or d1");
    }
}
