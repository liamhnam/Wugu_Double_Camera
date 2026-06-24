package com.tom_roush.pdfbox.multipdf;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAction;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Splitter {
    private PDDocument currentDestinationDocument;
    private List<PDDocument> destinationDocuments;
    private PDDocument sourceDocument;
    private int splitLength = 1;
    private int startPage = Integer.MIN_VALUE;
    private int endPage = Integer.MAX_VALUE;
    private int currentPageNumber = 0;

    public List<PDDocument> split(PDDocument pDDocument) throws IOException {
        this.destinationDocuments = new ArrayList();
        this.sourceDocument = pDDocument;
        processPages();
        return this.destinationDocuments;
    }

    public void setSplitAtPage(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Number of pages is smaller than one");
        }
        this.splitLength = i;
    }

    public void setStartPage(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Start page is smaller than one");
        }
        this.startPage = i;
    }

    public void setEndPage(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("End page is smaller than one");
        }
        this.endPage = i;
    }

    private void processPages() throws IOException {
        for (int i = 0; i < this.sourceDocument.getNumberOfPages(); i++) {
            PDPage page = this.sourceDocument.getPage(i);
            int i2 = this.currentPageNumber;
            if (i2 + 1 >= this.startPage && i2 + 1 <= this.endPage) {
                processPage(page);
                this.currentPageNumber++;
            } else if (i2 > this.endPage) {
                return;
            } else {
                this.currentPageNumber = i2 + 1;
            }
        }
    }

    private void createNewDocumentIfNecessary() throws IOException {
        if (splitAtPage(this.currentPageNumber) || this.currentDestinationDocument == null) {
            PDDocument pDDocumentCreateNewDocument = createNewDocument();
            this.currentDestinationDocument = pDDocumentCreateNewDocument;
            this.destinationDocuments.add(pDDocumentCreateNewDocument);
        }
    }

    protected boolean splitAtPage(int i) {
        return i % this.splitLength == 0;
    }

    protected PDDocument createNewDocument() throws IOException {
        PDDocument pDDocument = new PDDocument();
        pDDocument.getDocument().setVersion(getSourceDocument().getVersion());
        pDDocument.setDocumentInformation(getSourceDocument().getDocumentInformation());
        pDDocument.getDocumentCatalog().setViewerPreferences(getSourceDocument().getDocumentCatalog().getViewerPreferences());
        return pDDocument;
    }

    protected void processPage(PDPage pDPage) throws IOException {
        createNewDocumentIfNecessary();
        PDPage pDPageImportPage = getDestinationDocument().importPage(pDPage);
        pDPageImportPage.setCropBox(pDPage.getCropBox());
        pDPageImportPage.setMediaBox(pDPage.getMediaBox());
        pDPageImportPage.setResources(pDPage.getResources());
        pDPageImportPage.setRotation(pDPage.getRotation());
        processAnnotations(pDPageImportPage);
    }

    private void processAnnotations(PDPage pDPage) throws IOException {
        for (PDAnnotation pDAnnotation : pDPage.getAnnotations()) {
            if (pDAnnotation instanceof PDAnnotationLink) {
                PDAnnotationLink pDAnnotationLink = (PDAnnotationLink) pDAnnotation;
                PDDestination destination = pDAnnotationLink.getDestination();
                if (destination == null && pDAnnotationLink.getAction() != null) {
                    PDAction action = pDAnnotationLink.getAction();
                    if (action instanceof PDActionGoTo) {
                        destination = ((PDActionGoTo) action).getDestination();
                    }
                }
                if (destination instanceof PDPageDestination) {
                    ((PDPageDestination) destination).setPage(null);
                }
            }
            pDAnnotation.setPage(null);
        }
    }

    protected final PDDocument getSourceDocument() {
        return this.sourceDocument;
    }

    protected final PDDocument getDestinationDocument() {
        return this.currentDestinationDocument;
    }
}
