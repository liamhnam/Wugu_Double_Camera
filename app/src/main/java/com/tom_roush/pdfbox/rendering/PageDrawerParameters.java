package com.tom_roush.pdfbox.rendering;

import com.tom_roush.pdfbox.pdmodel.PDPage;

public final class PageDrawerParameters {
    private final PDPage page;
    private final PDFRenderer renderer;

    PageDrawerParameters(PDFRenderer pDFRenderer, PDPage pDPage) {
        this.renderer = pDFRenderer;
        this.page = pDPage;
    }

    public PDPage getPage() {
        return this.page;
    }

    PDFRenderer getRenderer() {
        return this.renderer;
    }
}
