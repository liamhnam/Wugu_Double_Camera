package com.tom_roush.pdfbox.pdmodel.common.filespecification;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;

public class PDComplexFileSpecification extends PDFileSpecification {
    private COSDictionary efDictionary;

    private final COSDictionary f2350fs;

    public PDComplexFileSpecification() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.f2350fs = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FILESPEC);
    }

    public PDComplexFileSpecification(COSDictionary cOSDictionary) {
        if (cOSDictionary == null) {
            COSDictionary cOSDictionary2 = new COSDictionary();
            this.f2350fs = cOSDictionary2;
            cOSDictionary2.setItem(COSName.TYPE, (COSBase) COSName.FILESPEC);
            return;
        }
        this.f2350fs = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.f2350fs;
    }

    private COSDictionary getEFDictionary() {
        COSDictionary cOSDictionary;
        if (this.efDictionary == null && (cOSDictionary = this.f2350fs) != null) {
            this.efDictionary = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.f2259EF);
        }
        return this.efDictionary;
    }

    private COSBase getObjectFromEFDictionary(COSName cOSName) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary != null) {
            return eFDictionary.getDictionaryObject(cOSName);
        }
        return null;
    }

    public String getFilename() {
        String fileUnicode = getFileUnicode();
        if (fileUnicode == null) {
            fileUnicode = getFileDos();
        }
        if (fileUnicode == null) {
            fileUnicode = getFileMac();
        }
        if (fileUnicode == null) {
            fileUnicode = getFileUnix();
        }
        return fileUnicode == null ? getFile() : fileUnicode;
    }

    public String getFileUnicode() {
        return this.f2350fs.getString(COSName.f2319UF);
    }

    public void setFileUnicode(String str) {
        this.f2350fs.setString(COSName.f2319UF, str);
    }

    @Override
    public String getFile() {
        return this.f2350fs.getString(COSName.f2260F);
    }

    @Override
    public void setFile(String str) {
        this.f2350fs.setString(COSName.f2260F, str);
    }

    public String getFileDos() {
        return this.f2350fs.getString(COSName.DOS);
    }

    public void setFileDos(String str) {
        this.f2350fs.setString(COSName.DOS, str);
    }

    public String getFileMac() {
        return this.f2350fs.getString(COSName.MAC);
    }

    public void setFileMac(String str) {
        this.f2350fs.setString(COSName.MAC, str);
    }

    public String getFileUnix() {
        return this.f2350fs.getString(COSName.UNIX);
    }

    public void setFileUnix(String str) {
        this.f2350fs.setString(COSName.UNIX, str);
    }

    public void setVolatile(boolean z) {
        this.f2350fs.setBoolean(COSName.f2320V, z);
    }

    public boolean isVolatile() {
        return this.f2350fs.getBoolean(COSName.f2320V, false);
    }

    public PDEmbeddedFile getEmbeddedFile() {
        COSStream cOSStream = (COSStream) getObjectFromEFDictionary(COSName.f2260F);
        if (cOSStream != null) {
            return new PDEmbeddedFile(cOSStream);
        }
        return null;
    }

    public void setEmbeddedFile(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.f2350fs.setItem(COSName.f2259EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.f2260F, pDEmbeddedFile);
        }
    }

    public PDEmbeddedFile getEmbeddedFileDos() {
        COSStream cOSStream = (COSStream) getObjectFromEFDictionary(COSName.DOS);
        if (cOSStream != null) {
            return new PDEmbeddedFile(cOSStream);
        }
        return null;
    }

    public void setEmbeddedFileDos(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.f2350fs.setItem(COSName.f2259EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.DOS, pDEmbeddedFile);
        }
    }

    public PDEmbeddedFile getEmbeddedFileMac() {
        COSStream cOSStream = (COSStream) getObjectFromEFDictionary(COSName.MAC);
        if (cOSStream != null) {
            return new PDEmbeddedFile(cOSStream);
        }
        return null;
    }

    public void setEmbeddedFileMac(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.f2350fs.setItem(COSName.f2259EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.MAC, pDEmbeddedFile);
        }
    }

    public PDEmbeddedFile getEmbeddedFileUnix() {
        COSStream cOSStream = (COSStream) getObjectFromEFDictionary(COSName.UNIX);
        if (cOSStream != null) {
            return new PDEmbeddedFile(cOSStream);
        }
        return null;
    }

    public void setEmbeddedFileUnix(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.f2350fs.setItem(COSName.f2259EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.UNIX, pDEmbeddedFile);
        }
    }

    public PDEmbeddedFile getEmbeddedFileUnicode() {
        COSStream cOSStream = (COSStream) getObjectFromEFDictionary(COSName.f2319UF);
        if (cOSStream != null) {
            return new PDEmbeddedFile(cOSStream);
        }
        return null;
    }

    public void setEmbeddedFileUnicode(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.f2350fs.setItem(COSName.f2259EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.f2319UF, pDEmbeddedFile);
        }
    }

    public void setFileDescription(String str) {
        this.f2350fs.setString(COSName.DESC, str);
    }

    public String getFileDescription() {
        return this.f2350fs.getString(COSName.DESC);
    }
}
