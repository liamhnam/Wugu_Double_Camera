package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class PDSignature implements COSObjectable {
    private final COSDictionary dictionary;
    public static final COSName FILTER_ADOBE_PPKLITE = COSName.ADOBE_PPKLITE;
    public static final COSName FILTER_ENTRUST_PPKEF = COSName.ENTRUST_PPKEF;
    public static final COSName FILTER_CICI_SIGNIT = COSName.CICI_SIGNIT;
    public static final COSName FILTER_VERISIGN_PPKVS = COSName.VERISIGN_PPKVS;
    public static final COSName SUBFILTER_ADBE_X509_RSA_SHA1 = COSName.ADBE_X509_RSA_SHA1;
    public static final COSName SUBFILTER_ADBE_PKCS7_DETACHED = COSName.ADBE_PKCS7_DETACHED;
    public static final COSName SUBFILTER_ETSI_CADES_DETACHED = COSName.getPDFName("ETSI.CAdES.detached");
    public static final COSName SUBFILTER_ADBE_PKCS7_SHA1 = COSName.ADBE_PKCS7_SHA1;

    public PDSignature() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.SIG);
    }

    public PDSignature(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public void setType(COSName cOSName) {
        this.dictionary.setItem(COSName.TYPE, (COSBase) cOSName);
    }

    public void setFilter(COSName cOSName) {
        this.dictionary.setItem(COSName.FILTER, (COSBase) cOSName);
    }

    public void setSubFilter(COSName cOSName) {
        this.dictionary.setItem(COSName.SUB_FILTER, (COSBase) cOSName);
    }

    public void setName(String str) {
        this.dictionary.setString(COSName.NAME, str);
    }

    public void setLocation(String str) {
        this.dictionary.setString(COSName.LOCATION, str);
    }

    public void setReason(String str) {
        this.dictionary.setString(COSName.REASON, str);
    }

    public void setContactInfo(String str) {
        this.dictionary.setString(COSName.CONTACT_INFO, str);
    }

    public void setSignDate(Calendar calendar) {
        this.dictionary.setDate(COSName.f2281M, calendar);
    }

    public String getFilter() {
        return this.dictionary.getNameAsString(COSName.FILTER);
    }

    public String getSubFilter() {
        return this.dictionary.getNameAsString(COSName.SUB_FILTER);
    }

    public String getName() {
        return this.dictionary.getString(COSName.NAME);
    }

    public String getLocation() {
        return this.dictionary.getString(COSName.LOCATION);
    }

    public String getReason() {
        return this.dictionary.getString(COSName.REASON);
    }

    public String getContactInfo() {
        return this.dictionary.getString(COSName.CONTACT_INFO);
    }

    public Calendar getSignDate() {
        return this.dictionary.getDate(COSName.f2281M);
    }

    public void setByteRange(int[] iArr) {
        if (iArr.length != 4) {
            return;
        }
        COSArray cOSArray = new COSArray();
        for (int i : iArr) {
            cOSArray.add((COSBase) COSInteger.get(i));
        }
        this.dictionary.setItem(COSName.BYTERANGE, (COSBase) cOSArray);
    }

    public int[] getByteRange() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.BYTERANGE);
        int size = cOSArray.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = cOSArray.getInt(i);
        }
        return iArr;
    }

    public byte[] getContents(InputStream inputStream) throws IOException {
        int[] byteRange = getByteRange();
        int i = byteRange[0] + byteRange[1] + 1;
        return getContents(new COSFilterInputStream(inputStream, new int[]{i, byteRange[2] - i}));
    }

    public byte[] getContents(byte[] bArr) throws IOException {
        int[] byteRange = getByteRange();
        int i = byteRange[0] + byteRange[1] + 1;
        return getContents(new COSFilterInputStream(bArr, new int[]{i, byteRange[2] - i}));
    }

    private byte[] getContents(COSFilterInputStream cOSFilterInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] bArr = new byte[1024];
        while (true) {
            int i = cOSFilterInputStream.read(bArr);
            if (i == -1) {
                cOSFilterInputStream.close();
                return COSString.parseHex(byteArrayOutputStream.toString("ISO-8859-1")).getBytes();
            }
            byte b = bArr[0];
            if (b == 60 || b == 40) {
                byteArrayOutputStream.write(bArr, 1, i);
            } else {
                int i2 = i - 1;
                byte b2 = bArr[i2];
                if (b2 == 62 || b2 == 41) {
                    byteArrayOutputStream.write(bArr, 0, i2);
                } else {
                    byteArrayOutputStream.write(bArr, 0, i);
                }
            }
        }
    }

    public void setContents(byte[] bArr) {
        COSString cOSString = new COSString(bArr);
        cOSString.setForceHexForm(true);
        this.dictionary.setItem(COSName.CONTENTS, (COSBase) cOSString);
    }

    public byte[] getSignedContent(InputStream inputStream) throws Throwable {
        COSFilterInputStream cOSFilterInputStream = null;
        try {
            COSFilterInputStream cOSFilterInputStream2 = new COSFilterInputStream(inputStream, getByteRange());
            try {
                byte[] byteArray = cOSFilterInputStream2.toByteArray();
                cOSFilterInputStream2.close();
                return byteArray;
            } catch (Throwable th) {
                th = th;
                cOSFilterInputStream = cOSFilterInputStream2;
                if (cOSFilterInputStream != null) {
                    cOSFilterInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public byte[] getSignedContent(byte[] bArr) throws Throwable {
        COSFilterInputStream cOSFilterInputStream = null;
        try {
            COSFilterInputStream cOSFilterInputStream2 = new COSFilterInputStream(bArr, getByteRange());
            try {
                byte[] byteArray = cOSFilterInputStream2.toByteArray();
                cOSFilterInputStream2.close();
                return byteArray;
            } catch (Throwable th) {
                th = th;
                cOSFilterInputStream = cOSFilterInputStream2;
                if (cOSFilterInputStream != null) {
                    cOSFilterInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public PDPropBuild getPropBuild() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.PROP_BUILD);
        if (cOSDictionary != null) {
            return new PDPropBuild(cOSDictionary);
        }
        return null;
    }

    public void setPropBuild(PDPropBuild pDPropBuild) {
        this.dictionary.setItem(COSName.PROP_BUILD, pDPropBuild);
    }
}
