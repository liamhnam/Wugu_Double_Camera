package com.tom_roush.pdfbox.multipdf;

import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Overlay {
    private LayoutPage defaultOverlayPage;
    private LayoutPage evenPageOverlayPage;
    private LayoutPage firstPageOverlayPage;
    private LayoutPage lastPageOverlayPage;
    private LayoutPage oddPageOverlayPage;
    private final Map<Integer, PDDocument> specificPageOverlay = new HashMap();
    private Map<Integer, LayoutPage> specificPageOverlayPage = new HashMap();
    private Position position = Position.BACKGROUND;
    private String inputFileName = null;
    private PDDocument inputPDFDocument = null;
    private String defaultOverlayFilename = null;
    private PDDocument defaultOverlay = null;
    private String firstPageOverlayFilename = null;
    private PDDocument firstPageOverlay = null;
    private String lastPageOverlayFilename = null;
    private PDDocument lastPageOverlay = null;
    private String allPagesOverlayFilename = null;
    private PDDocument allPagesOverlay = null;
    private String oddPageOverlayFilename = null;
    private PDDocument oddPageOverlay = null;
    private String evenPageOverlayFilename = null;
    private PDDocument evenPageOverlay = null;
    private int numberOfOverlayPages = 0;
    private boolean useAllOverlayPages = false;

    public enum Position {
        FOREGROUND,
        BACKGROUND
    }

    public PDDocument overlay(Map<Integer, String> map) throws IOException {
        loadPDFs();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            PDDocument pDDocumentLoadPDF = loadPDF(entry.getValue());
            this.specificPageOverlay.put(entry.getKey(), pDDocumentLoadPDF);
            this.specificPageOverlayPage.put(entry.getKey(), getLayoutPage(pDDocumentLoadPDF));
        }
        processPages(this.inputPDFDocument);
        return this.inputPDFDocument;
    }

    public void close() throws IOException {
        PDDocument pDDocument = this.defaultOverlay;
        if (pDDocument != null) {
            pDDocument.close();
        }
        PDDocument pDDocument2 = this.firstPageOverlay;
        if (pDDocument2 != null) {
            pDDocument2.close();
        }
        PDDocument pDDocument3 = this.lastPageOverlay;
        if (pDDocument3 != null) {
            pDDocument3.close();
        }
        PDDocument pDDocument4 = this.allPagesOverlay;
        if (pDDocument4 != null) {
            pDDocument4.close();
        }
        PDDocument pDDocument5 = this.oddPageOverlay;
        if (pDDocument5 != null) {
            pDDocument5.close();
        }
        PDDocument pDDocument6 = this.evenPageOverlay;
        if (pDDocument6 != null) {
            pDDocument6.close();
        }
        Map<Integer, PDDocument> map = this.specificPageOverlay;
        if (map != null) {
            Iterator<Map.Entry<Integer, PDDocument>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().close();
            }
            this.specificPageOverlay.clear();
            this.specificPageOverlayPage.clear();
        }
    }

    private void loadPDFs() throws IOException {
        String str = this.inputFileName;
        if (str != null) {
            this.inputPDFDocument = loadPDF(str);
        }
        String str2 = this.defaultOverlayFilename;
        if (str2 != null) {
            this.defaultOverlay = loadPDF(str2);
        }
        PDDocument pDDocument = this.defaultOverlay;
        if (pDDocument != null) {
            this.defaultOverlayPage = getLayoutPage(pDDocument);
        }
        String str3 = this.firstPageOverlayFilename;
        if (str3 != null) {
            this.firstPageOverlay = loadPDF(str3);
        }
        PDDocument pDDocument2 = this.firstPageOverlay;
        if (pDDocument2 != null) {
            this.firstPageOverlayPage = getLayoutPage(pDDocument2);
        }
        String str4 = this.lastPageOverlayFilename;
        if (str4 != null) {
            this.lastPageOverlay = loadPDF(str4);
        }
        PDDocument pDDocument3 = this.lastPageOverlay;
        if (pDDocument3 != null) {
            this.lastPageOverlayPage = getLayoutPage(pDDocument3);
        }
        String str5 = this.oddPageOverlayFilename;
        if (str5 != null) {
            this.oddPageOverlay = loadPDF(str5);
        }
        PDDocument pDDocument4 = this.oddPageOverlay;
        if (pDDocument4 != null) {
            this.oddPageOverlayPage = getLayoutPage(pDDocument4);
        }
        String str6 = this.evenPageOverlayFilename;
        if (str6 != null) {
            this.evenPageOverlay = loadPDF(str6);
        }
        PDDocument pDDocument5 = this.evenPageOverlay;
        if (pDDocument5 != null) {
            this.evenPageOverlayPage = getLayoutPage(pDDocument5);
        }
        String str7 = this.allPagesOverlayFilename;
        if (str7 != null) {
            this.allPagesOverlay = loadPDF(str7);
        }
        PDDocument pDDocument6 = this.allPagesOverlay;
        if (pDDocument6 != null) {
            Map<Integer, LayoutPage> layoutPages = getLayoutPages(pDDocument6);
            this.specificPageOverlayPage = layoutPages;
            this.useAllOverlayPages = true;
            this.numberOfOverlayPages = layoutPages.size();
        }
    }

    private PDDocument loadPDF(String str) throws IOException {
        return PDDocument.load(new File(str));
    }

    private static final class LayoutPage {
        private final COSStream overlayContentStream;
        private final PDRectangle overlayMediaBox;
        private final COSDictionary overlayResources;

        LayoutPage(PDRectangle pDRectangle, COSStream cOSStream, COSDictionary cOSDictionary, C18651 c18651) {
            this(pDRectangle, cOSStream, cOSDictionary);
        }

        private LayoutPage(PDRectangle pDRectangle, COSStream cOSStream, COSDictionary cOSDictionary) {
            this.overlayMediaBox = pDRectangle;
            this.overlayContentStream = cOSStream;
            this.overlayResources = cOSDictionary;
        }
    }

    private LayoutPage getLayoutPage(PDDocument pDDocument) throws IOException {
        PDPage page = pDDocument.getPage(0);
        COSBase dictionaryObject = page.getCOSObject().getDictionaryObject(COSName.CONTENTS);
        PDResources resources = page.getResources();
        if (resources == null) {
            resources = new PDResources();
        }
        return new LayoutPage(page.getMediaBox(), createContentStream(dictionaryObject), resources.getCOSObject(), null);
    }

    private Map<Integer, LayoutPage> getLayoutPages(PDDocument pDDocument) throws IOException {
        int numberOfPages = pDDocument.getNumberOfPages();
        HashMap map = new HashMap(numberOfPages);
        for (int i = 0; i < numberOfPages; i++) {
            PDPage page = pDDocument.getPage(i);
            COSBase dictionaryObject = page.getCOSObject().getDictionaryObject(COSName.CONTENTS);
            PDResources resources = page.getResources();
            if (resources == null) {
                resources = new PDResources();
            }
            map.put(Integer.valueOf(i), new LayoutPage(page.getMediaBox(), createContentStream(dictionaryObject), resources.getCOSObject(), null));
        }
        return map;
    }

    private COSStream createContentStream(COSBase cOSBase) throws IOException {
        List<COSStream> listCreateContentStreamList = createContentStreamList(cOSBase);
        COSStream cOSStream = new COSStream();
        OutputStream outputStreamCreateOutputStream = cOSStream.createOutputStream(COSName.FLATE_DECODE);
        Iterator<COSStream> it = listCreateContentStreamList.iterator();
        while (it.hasNext()) {
            COSInputStream cOSInputStreamCreateInputStream = it.next().createInputStream();
            byte[] bArr = new byte[2048];
            while (true) {
                int i = cOSInputStreamCreateInputStream.read(bArr);
                if (i > 0) {
                    outputStreamCreateOutputStream.write(bArr, 0, i);
                }
            }
            outputStreamCreateOutputStream.flush();
        }
        outputStreamCreateOutputStream.close();
        return cOSStream;
    }

    private List<COSStream> createContentStreamList(COSBase cOSBase) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (cOSBase instanceof COSStream) {
            arrayList.add((COSStream) cOSBase);
        } else if (cOSBase instanceof COSArray) {
            Iterator<COSBase> it = ((COSArray) cOSBase).iterator();
            while (it.hasNext()) {
                arrayList.addAll(createContentStreamList(it.next()));
            }
        } else if (cOSBase instanceof COSObject) {
            arrayList.addAll(createContentStreamList(((COSObject) cOSBase).getObject()));
        } else {
            throw new IOException("Contents are unknown type:" + cOSBase.getClass().getName());
        }
        return arrayList;
    }

    private void processPages(PDDocument pDDocument) throws IOException {
        int i = 0;
        for (PDPage pDPage : pDDocument.getPages()) {
            COSDictionary cOSObject = pDPage.getCOSObject();
            COSBase dictionaryObject = cOSObject.getDictionaryObject(COSName.CONTENTS);
            COSArray cOSArray = new COSArray();
            int i2 = C18651.$SwitchMap$com$tom_roush$pdfbox$multipdf$Overlay$Position[this.position.ordinal()];
            if (i2 == 1) {
                cOSArray.add((COSBase) createStream("q\n"));
                addOriginalContent(dictionaryObject, cOSArray);
                cOSArray.add((COSBase) createStream("Q\n"));
                overlayPage(cOSArray, pDPage, i + 1, pDDocument.getNumberOfPages());
            } else if (i2 == 2) {
                overlayPage(cOSArray, pDPage, i + 1, pDDocument.getNumberOfPages());
                addOriginalContent(dictionaryObject, cOSArray);
            } else {
                throw new IOException("Unknown type of position:" + this.position);
            }
            cOSObject.setItem(COSName.CONTENTS, (COSBase) cOSArray);
            i++;
        }
    }

    static class C18651 {
        static final int[] $SwitchMap$com$tom_roush$pdfbox$multipdf$Overlay$Position;

        static {
            int[] iArr = new int[Position.values().length];
            $SwitchMap$com$tom_roush$pdfbox$multipdf$Overlay$Position = iArr;
            try {
                iArr[Position.FOREGROUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$multipdf$Overlay$Position[Position.BACKGROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void addOriginalContent(COSBase cOSBase, COSArray cOSArray) throws IOException {
        if (cOSBase instanceof COSStream) {
            cOSArray.add(cOSBase);
        } else {
            if (cOSBase instanceof COSArray) {
                cOSArray.addAll((COSArray) cOSBase);
                return;
            }
            throw new IOException("Unknown content type:" + cOSBase.getClass().getName());
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void overlayPage(com.tom_roush.pdfbox.cos.COSArray r3, com.tom_roush.pdfbox.pdmodel.PDPage r4, int r5, int r6) throws java.io.IOException {
        /*
            r2 = this;
            boolean r0 = r2.useAllOverlayPages
            if (r0 != 0) goto L1d
            java.util.Map<java.lang.Integer, com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage> r0 = r2.specificPageOverlayPage
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L1d
            java.util.Map<java.lang.Integer, com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage> r6 = r2.specificPageOverlayPage
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r5 = r6.get(r5)
            com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage r5 = (com.tom_roush.pdfbox.multipdf.Overlay.LayoutPage) r5
            goto L59
        L1d:
            r0 = 1
            if (r5 != r0) goto L26
            com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage r1 = r2.firstPageOverlayPage
            if (r1 == 0) goto L26
        L24:
            r5 = r1
            goto L59
        L26:
            if (r5 != r6) goto L2e
            com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage r6 = r2.lastPageOverlayPage
            if (r6 == 0) goto L2e
        L2c:
            r5 = r6
            goto L59
        L2e:
            int r6 = r5 % 2
            if (r6 != r0) goto L37
            com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage r1 = r2.oddPageOverlayPage
            if (r1 == 0) goto L37
            goto L24
        L37:
            if (r6 != 0) goto L3e
            com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage r6 = r2.evenPageOverlayPage
            if (r6 == 0) goto L3e
            goto L2c
        L3e:
            com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage r6 = r2.defaultOverlayPage
            if (r6 == 0) goto L43
            goto L2c
        L43:
            boolean r6 = r2.useAllOverlayPages
            if (r6 == 0) goto L58
            int r5 = r5 - r0
            int r6 = r2.numberOfOverlayPages
            int r5 = r5 % r6
            java.util.Map<java.lang.Integer, com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage> r6 = r2.specificPageOverlayPage
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r5 = r6.get(r5)
            com.tom_roush.pdfbox.multipdf.Overlay$LayoutPage r5 = (com.tom_roush.pdfbox.multipdf.Overlay.LayoutPage) r5
            goto L59
        L58:
            r5 = 0
        L59:
            if (r5 == 0) goto L78
            com.tom_roush.pdfbox.pdmodel.PDResources r6 = r4.getResources()
            if (r6 != 0) goto L69
            com.tom_roush.pdfbox.pdmodel.PDResources r6 = new com.tom_roush.pdfbox.pdmodel.PDResources
            r6.<init>()
            r4.setResources(r6)
        L69:
            com.tom_roush.pdfbox.cos.COSStream r6 = com.tom_roush.pdfbox.multipdf.Overlay.LayoutPage.access$100(r5)
            com.tom_roush.pdfbox.cos.COSName r6 = r2.createOverlayXObject(r4, r5, r6)
            com.tom_roush.pdfbox.cos.COSStream r4 = r2.createOverlayStream(r4, r5, r6)
            r3.add(r4)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.multipdf.Overlay.overlayPage(com.tom_roush.pdfbox.cos.COSArray, com.tom_roush.pdfbox.pdmodel.PDPage, int, int):void");
    }

    private COSName createOverlayXObject(PDPage pDPage, LayoutPage layoutPage, COSStream cOSStream) {
        PDFormXObject pDFormXObject = new PDFormXObject(cOSStream);
        pDFormXObject.setResources(new PDResources(layoutPage.overlayResources));
        pDFormXObject.setFormType(1);
        pDFormXObject.setBBox(layoutPage.overlayMediaBox.createRetranslatedRectangle());
        pDFormXObject.setMatrix(new AffineTransform());
        return pDPage.getResources().add(pDFormXObject, "OL");
    }

    private COSStream createOverlayStream(PDPage pDPage, LayoutPage layoutPage, COSName cOSName) throws IOException {
        PDRectangle mediaBox = pDPage.getMediaBox();
        return createStream("q\nq 1 0 0 1 " + float2String((mediaBox.getWidth() - layoutPage.overlayMediaBox.getWidth()) / 2.0f) + " " + float2String((mediaBox.getHeight() - layoutPage.overlayMediaBox.getHeight()) / 2.0f) + " cm /" + cOSName.getName() + " Do Q\nQ\n");
    }

    private String float2String(float f) {
        String plainString = new BigDecimal(String.valueOf(f)).toPlainString();
        if (plainString.indexOf(46) > -1 && !plainString.endsWith(".0")) {
            while (plainString.endsWith(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES) && !plainString.endsWith(".0")) {
                plainString = plainString.substring(0, plainString.length() - 1);
            }
        }
        return plainString;
    }

    private COSStream createStream(String str) throws IOException {
        COSStream cOSStream = new COSStream();
        OutputStream outputStreamCreateOutputStream = cOSStream.createOutputStream(COSName.FLATE_DECODE);
        outputStreamCreateOutputStream.write(str.getBytes("ISO-8859-1"));
        outputStreamCreateOutputStream.close();
        return cOSStream;
    }

    public void setOverlayPosition(Position position) {
        this.position = position;
    }

    public void setInputFile(String str) {
        this.inputFileName = str;
    }

    public void setInputPDF(PDDocument pDDocument) {
        this.inputPDFDocument = pDDocument;
    }

    public String getInputFile() {
        return this.inputFileName;
    }

    public void setDefaultOverlayFile(String str) {
        this.defaultOverlayFilename = str;
    }

    public void setDefaultOverlayPDF(PDDocument pDDocument) {
        this.defaultOverlay = pDDocument;
    }

    public String getDefaultOverlayFile() {
        return this.defaultOverlayFilename;
    }

    public void setFirstPageOverlayFile(String str) {
        this.firstPageOverlayFilename = str;
    }

    public void setFirstPageOverlayPDF(PDDocument pDDocument) {
        this.firstPageOverlay = pDDocument;
    }

    public void setLastPageOverlayFile(String str) {
        this.lastPageOverlayFilename = str;
    }

    public void setLastPageOverlayPDF(PDDocument pDDocument) {
        this.lastPageOverlay = pDDocument;
    }

    public void setAllPagesOverlayFile(String str) {
        this.allPagesOverlayFilename = str;
    }

    public void setAllPagesOverlayPDF(PDDocument pDDocument) {
        this.allPagesOverlay = pDDocument;
    }

    public void setOddPageOverlayFile(String str) {
        this.oddPageOverlayFilename = str;
    }

    public void setOddPageOverlayPDF(PDDocument pDDocument) {
        this.oddPageOverlay = pDDocument;
    }

    public void setEvenPageOverlayFile(String str) {
        this.evenPageOverlayFilename = str;
    }

    public void setEvenPageOverlayPDF(PDDocument pDDocument) {
        this.evenPageOverlay = pDDocument;
    }
}
