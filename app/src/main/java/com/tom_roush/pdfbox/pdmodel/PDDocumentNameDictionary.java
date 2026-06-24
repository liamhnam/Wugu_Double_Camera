package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public class PDDocumentNameDictionary implements COSObjectable {
    private final PDDocumentCatalog catalog;
    private final COSDictionary nameDictionary;

    public PDDocumentNameDictionary(PDDocumentCatalog pDDocumentCatalog) {
        COSBase dictionaryObject = pDDocumentCatalog.getCOSObject().getDictionaryObject(COSName.NAMES);
        if (dictionaryObject != null) {
            this.nameDictionary = (COSDictionary) dictionaryObject;
        } else {
            COSDictionary cOSDictionary = new COSDictionary();
            this.nameDictionary = cOSDictionary;
            pDDocumentCatalog.getCOSObject().setItem(COSName.NAMES, (COSBase) cOSDictionary);
        }
        this.catalog = pDDocumentCatalog;
    }

    public PDDocumentNameDictionary(PDDocumentCatalog pDDocumentCatalog, COSDictionary cOSDictionary) {
        this.catalog = pDDocumentCatalog;
        this.nameDictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.nameDictionary;
    }

    public PDDestinationNameTreeNode getDests() {
        COSDictionary cOSDictionary = (COSDictionary) this.nameDictionary.getDictionaryObject(COSName.DESTS);
        if (cOSDictionary == null) {
            cOSDictionary = (COSDictionary) this.catalog.getCOSObject().getDictionaryObject(COSName.DESTS);
        }
        if (cOSDictionary != null) {
            return new PDDestinationNameTreeNode(cOSDictionary);
        }
        return null;
    }

    public void setDests(PDDestinationNameTreeNode pDDestinationNameTreeNode) {
        this.nameDictionary.setItem(COSName.DESTS, pDDestinationNameTreeNode);
        this.catalog.getCOSObject().setItem(COSName.DESTS, (COSObjectable) null);
    }

    public PDEmbeddedFilesNameTreeNode getEmbeddedFiles() {
        COSDictionary cOSDictionary = (COSDictionary) this.nameDictionary.getDictionaryObject(COSName.EMBEDDED_FILES);
        if (cOSDictionary != null) {
            return new PDEmbeddedFilesNameTreeNode(cOSDictionary);
        }
        return null;
    }

    public void setEmbeddedFiles(PDEmbeddedFilesNameTreeNode pDEmbeddedFilesNameTreeNode) {
        this.nameDictionary.setItem(COSName.EMBEDDED_FILES, pDEmbeddedFilesNameTreeNode);
    }

    public PDJavascriptNameTreeNode getJavaScript() {
        COSDictionary cOSDictionary = (COSDictionary) this.nameDictionary.getDictionaryObject(COSName.JAVA_SCRIPT);
        if (cOSDictionary != null) {
            return new PDJavascriptNameTreeNode(cOSDictionary);
        }
        return null;
    }

    public void setJavascript(PDJavascriptNameTreeNode pDJavascriptNameTreeNode) {
        this.nameDictionary.setItem(COSName.JAVA_SCRIPT, pDJavascriptNameTreeNode);
    }
}
