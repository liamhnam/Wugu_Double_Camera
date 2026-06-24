package com.tom_roush.pdfbox.multipdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class PDFCloneUtility {
    private final Map<Object, COSBase> clonedVersion = new HashMap();
    private final PDDocument destination;

    PDFCloneUtility(PDDocument pDDocument) {
        this.destination = pDDocument;
    }

    public PDDocument getDestination() {
        return this.destination;
    }

    public COSBase cloneForNewDocument(Object obj) throws IOException {
        ?? cOSDictionary;
        if (obj == null) {
            return null;
        }
        COSBase cOSArray = this.clonedVersion.get(obj);
        if (cOSArray == 0) {
            if (obj instanceof List) {
                cOSArray = new COSArray();
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    cOSArray.add(cloneForNewDocument(it.next()));
                }
            } else if ((obj instanceof COSObjectable) && !(obj instanceof COSBase)) {
                cOSArray = cloneForNewDocument(((COSObjectable) obj).getCOSObject());
                this.clonedVersion.put(obj, (COSBase) cOSArray);
            } else if (obj instanceof COSObject) {
                cOSArray = cloneForNewDocument(((COSObject) obj).getObject());
                this.clonedVersion.put(obj, (COSBase) cOSArray);
            } else if (obj instanceof COSArray) {
                cOSArray = new COSArray();
                COSArray cOSArray2 = (COSArray) obj;
                for (int i = 0; i < cOSArray2.size(); i++) {
                    cOSArray.add(cloneForNewDocument(cOSArray2.get(i)));
                }
                this.clonedVersion.put(obj, (COSBase) cOSArray);
            } else {
                if (obj instanceof COSStream) {
                    COSStream cOSStream = (COSStream) obj;
                    cOSDictionary = this.destination.getDocument().createCOSStream();
                    OutputStream outputStreamCreateRawOutputStream = cOSDictionary.createRawOutputStream();
                    InputStream inputStreamCreateRawInputStream = cOSStream.createRawInputStream();
                    IOUtils.copy(inputStreamCreateRawInputStream, outputStreamCreateRawOutputStream);
                    inputStreamCreateRawInputStream.close();
                    outputStreamCreateRawOutputStream.close();
                    this.clonedVersion.put(obj, (COSBase) cOSDictionary);
                    for (Map.Entry<COSName, COSBase> entry : cOSStream.entrySet()) {
                        cOSDictionary.setItem(entry.getKey(), cloneForNewDocument(entry.getValue()));
                    }
                } else if (obj instanceof COSDictionary) {
                    cOSDictionary = new COSDictionary();
                    this.clonedVersion.put(obj, (COSBase) cOSDictionary);
                    for (Map.Entry<COSName, COSBase> entry2 : ((COSDictionary) obj).entrySet()) {
                        cOSDictionary.setItem(entry2.getKey(), cloneForNewDocument(entry2.getValue()));
                    }
                } else {
                    cOSArray = (COSBase) obj;
                }
                cOSArray = cOSDictionary;
            }
        }
        this.clonedVersion.put(obj, (COSBase) cOSArray);
        return cOSArray;
    }

    public void cloneMerge(COSObjectable cOSObjectable, COSObjectable cOSObjectable2) throws IOException {
        COSBase cOSBaseCreateCOSStream;
        if (cOSObjectable != null && (cOSBaseCreateCOSStream = this.clonedVersion.get(cOSObjectable)) == 0) {
            if (!(cOSObjectable instanceof COSBase)) {
                cloneMerge(cOSObjectable.getCOSObject(), cOSObjectable2.getCOSObject());
                this.clonedVersion.put(cOSObjectable, cOSBaseCreateCOSStream);
            } else if (cOSObjectable instanceof COSObject) {
                if (cOSObjectable2 instanceof COSObject) {
                    cloneMerge(((COSObject) cOSObjectable).getObject(), ((COSObject) cOSObjectable2).getObject());
                } else if (cOSObjectable2 instanceof COSDictionary) {
                    cloneMerge(((COSObject) cOSObjectable).getObject(), cOSObjectable2);
                }
                this.clonedVersion.put(cOSObjectable, cOSBaseCreateCOSStream);
            } else if (cOSObjectable instanceof COSArray) {
                COSArray cOSArray = (COSArray) cOSObjectable;
                for (int i = 0; i < cOSArray.size(); i++) {
                    ((COSArray) cOSObjectable2).add(cloneForNewDocument(cOSArray.get(i)));
                }
                this.clonedVersion.put(cOSObjectable, cOSBaseCreateCOSStream);
            } else if (cOSObjectable instanceof COSStream) {
                COSStream cOSStream = (COSStream) cOSObjectable;
                cOSBaseCreateCOSStream = this.destination.getDocument().createCOSStream();
                OutputStream outputStreamCreateOutputStream = cOSBaseCreateCOSStream.createOutputStream(cOSStream.getFilters());
                IOUtils.copy(cOSStream.createInputStream(), outputStreamCreateOutputStream);
                outputStreamCreateOutputStream.close();
                this.clonedVersion.put(cOSObjectable, (COSBase) cOSBaseCreateCOSStream);
                for (Map.Entry<COSName, COSBase> entry : cOSStream.entrySet()) {
                    cOSBaseCreateCOSStream.setItem(entry.getKey(), cloneForNewDocument(entry.getValue()));
                }
            } else if (cOSObjectable instanceof COSDictionary) {
                this.clonedVersion.put(cOSObjectable, cOSBaseCreateCOSStream);
                for (Map.Entry<COSName, COSBase> entry2 : ((COSDictionary) cOSObjectable).entrySet()) {
                    COSName key = entry2.getKey();
                    COSBase value = entry2.getValue();
                    COSDictionary cOSDictionary = (COSDictionary) cOSObjectable2;
                    if (cOSDictionary.getItem(key) != null) {
                        cloneMerge(value, cOSDictionary.getItem(key));
                    } else {
                        cOSDictionary.setItem(key, cloneForNewDocument(value));
                    }
                }
            } else {
                cOSBaseCreateCOSStream = (COSBase) cOSObjectable;
            }
            this.clonedVersion.put(cOSObjectable, (COSBase) cOSBaseCreateCOSStream);
        }
    }
}
