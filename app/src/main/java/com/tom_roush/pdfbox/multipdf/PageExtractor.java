package com.tom_roush.pdfbox.multipdf;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import java.io.IOException;

public class PageExtractor {
    private int endPage;
    private PDDocument sourceDocument;
    private int startPage;

    public PageExtractor(PDDocument pDDocument) {
        this.startPage = 1;
        this.endPage = 0;
        this.sourceDocument = pDDocument;
        this.endPage = pDDocument.getNumberOfPages();
    }

    public PageExtractor(PDDocument pDDocument, int i, int i2) {
        this(pDDocument);
        this.startPage = i;
        this.endPage = i2;
    }

    public PDDocument extract() throws IOException {
        PDDocument pDDocument = new PDDocument();
        pDDocument.setDocumentInformation(this.sourceDocument.getDocumentInformation());
        pDDocument.getDocumentCatalog().setViewerPreferences(this.sourceDocument.getDocumentCatalog().getViewerPreferences());
        for (int i = this.startPage; i <= this.endPage; i++) {
            PDPage page = this.sourceDocument.getPage(i - 1);
            PDPage pDPageImportPage = pDDocument.importPage(page);
            pDPageImportPage.setCropBox(page.getCropBox());
            pDPageImportPage.setMediaBox(page.getMediaBox());
            pDPageImportPage.setResources(page.getResources());
            pDPageImportPage.setRotation(page.getRotation());
        }
        return pDDocument;
    }

    public int getStartPage() {
        return this.startPage;
    }

    public void setStartPage(int i) {
        this.startPage = i;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void setEndPage(int i) {
        this.endPage = i;
    }
}
