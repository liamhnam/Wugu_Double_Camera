package com.tom_roush.pdfbox.pdmodel.common;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PDStream implements COSObjectable {
    private final COSStream stream;

    public PDStream(PDDocument pDDocument) {
        this.stream = pDDocument.getDocument().createCOSStream();
    }

    public PDStream(COSDocument cOSDocument) {
        this.stream = cOSDocument.createCOSStream();
    }

    public PDStream(COSStream cOSStream) {
        this.stream = cOSStream;
    }

    public PDStream(PDDocument pDDocument, InputStream inputStream) throws IOException {
        this(pDDocument, inputStream, (COSBase) null);
    }

    public PDStream(PDDocument pDDocument, InputStream inputStream, COSName cOSName) throws IOException {
        this(pDDocument, inputStream, (COSBase) cOSName);
    }

    public PDStream(PDDocument pDDocument, InputStream inputStream, COSArray cOSArray) throws IOException {
        this(pDDocument, inputStream, (COSBase) cOSArray);
    }

    private PDStream(PDDocument pDDocument, InputStream inputStream, COSBase cOSBase) throws IOException {
        OutputStream outputStreamCreateOutputStream = null;
        try {
            COSStream cOSStreamCreateCOSStream = pDDocument.getDocument().createCOSStream();
            this.stream = cOSStreamCreateCOSStream;
            outputStreamCreateOutputStream = cOSStreamCreateCOSStream.createOutputStream(cOSBase);
            IOUtils.copy(inputStream, outputStreamCreateOutputStream);
        } finally {
            if (outputStreamCreateOutputStream != null) {
                outputStreamCreateOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Deprecated
    public void addCompression() {
        if (getFilters() == null) {
            if (this.stream.getLength() > 0) {
                OutputStream outputStreamCreateOutputStream = null;
                try {
                    try {
                        byte[] byteArray = IOUtils.toByteArray(this.stream.createInputStream());
                        outputStreamCreateOutputStream = this.stream.createOutputStream(COSName.FLATE_DECODE);
                        outputStreamCreateOutputStream.write(byteArray);
                        return;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                    IOUtils.closeQuietly(outputStreamCreateOutputStream);
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(COSName.FLATE_DECODE);
            setFilters(arrayList);
        }
    }

    @Override
    public COSStream getCOSObject() {
        return this.stream;
    }

    public OutputStream createOutputStream() throws IOException {
        return this.stream.createOutputStream();
    }

    public OutputStream createOutputStream(COSName cOSName) throws IOException {
        return this.stream.createOutputStream(cOSName);
    }

    public COSInputStream createInputStream() throws IOException {
        return this.stream.createInputStream();
    }

    public InputStream createInputStream(List<String> list) throws IOException {
        InputStream inputStreamCreateRawInputStream = this.stream.createRawInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<COSName> filters = getFilters();
        if (filters != null) {
            for (int i = 0; i < filters.size(); i++) {
                COSName cOSName = filters.get(i);
                if (list != null && list.contains(cOSName.getName())) {
                    break;
                }
                FilterFactory.INSTANCE.getFilter(cOSName).decode(inputStreamCreateRawInputStream, byteArrayOutputStream, this.stream, i);
                IOUtils.closeQuietly(inputStreamCreateRawInputStream);
                inputStreamCreateRawInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
            }
        }
        return inputStreamCreateRawInputStream;
    }

    @Deprecated
    public COSStream getStream() {
        return this.stream;
    }

    public int getLength() {
        return this.stream.getInt(COSName.LENGTH, 0);
    }

    public List<COSName> getFilters() {
        COSBase filters = this.stream.getFilters();
        if (filters instanceof COSName) {
            COSName cOSName = (COSName) filters;
            return new COSArrayList(cOSName, cOSName, this.stream, COSName.FILTER);
        }
        if (filters instanceof COSArray) {
            return ((COSArray) filters).toList();
        }
        return null;
    }

    public void setFilters(List<COSName> list) {
        this.stream.setItem(COSName.FILTER, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public List<Object> getDecodeParms() throws IOException {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.DECODE_PARMS);
        if (dictionaryObject == null) {
            dictionaryObject = this.stream.getDictionaryObject(COSName.f2253DP);
        }
        if (dictionaryObject instanceof COSDictionary) {
            return new COSArrayList(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dictionaryObject), dictionaryObject, this.stream, COSName.DECODE_PARMS);
        }
        if (!(dictionaryObject instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setDecodeParms(List<?> list) {
        this.stream.setItem(COSName.DECODE_PARMS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.stream.getDictionaryObject(COSName.f2260F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.stream.setItem(COSName.f2260F, pDFileSpecification);
    }

    public List<String> getFileFilters() {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.F_FILTER);
        if (dictionaryObject instanceof COSName) {
            COSName cOSName = (COSName) dictionaryObject;
            return new COSArrayList(cOSName.getName(), cOSName, this.stream, COSName.F_FILTER);
        }
        if (dictionaryObject instanceof COSArray) {
            return COSArrayList.convertCOSNameCOSArrayToList((COSArray) dictionaryObject);
        }
        return null;
    }

    public void setFileFilters(List<String> list) {
        this.stream.setItem(COSName.F_FILTER, (COSBase) COSArrayList.convertStringListToCOSNameCOSArray(list));
    }

    public List<Object> getFileDecodeParams() throws IOException {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.F_DECODE_PARMS);
        if (dictionaryObject instanceof COSDictionary) {
            return new COSArrayList(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dictionaryObject), dictionaryObject, this.stream, COSName.F_DECODE_PARMS);
        }
        if (!(dictionaryObject instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setFileDecodeParams(List<?> list) {
        this.stream.setItem("FDecodeParams", (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public byte[] toByteArray() throws Throwable {
        COSInputStream cOSInputStreamCreateInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        try {
            cOSInputStreamCreateInputStream = createInputStream();
            while (true) {
                try {
                    int i = cOSInputStreamCreateInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } catch (Throwable th) {
                    th = th;
                    if (cOSInputStreamCreateInputStream != null) {
                        cOSInputStreamCreateInputStream.close();
                    }
                    throw th;
                }
            }
            if (cOSInputStreamCreateInputStream != null) {
                cOSInputStreamCreateInputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            cOSInputStreamCreateInputStream = null;
        }
    }

    public PDMetadata getMetadata() {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.METADATA);
        if (dictionaryObject != null) {
            if (dictionaryObject instanceof COSStream) {
                return new PDMetadata((COSStream) dictionaryObject);
            }
            if (!(dictionaryObject instanceof COSNull)) {
                throw new IllegalStateException("Expected a COSStream but was a " + dictionaryObject.getClass().getSimpleName());
            }
        }
        return null;
    }

    public void setMetadata(PDMetadata pDMetadata) {
        this.stream.setItem(COSName.METADATA, pDMetadata);
    }

    public int getDecodedStreamLength() {
        return this.stream.getInt(COSName.f2251DL);
    }

    public void setDecodedStreamLength(int i) {
        this.stream.setInt(COSName.f2251DL, i);
    }
}
