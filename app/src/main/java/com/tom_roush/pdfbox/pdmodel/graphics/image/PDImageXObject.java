package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDMetadata;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.util.filetypedetector.FileType;
import com.tom_roush.pdfbox.util.filetypedetector.FileTypeDetector;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.util.List;

public final class PDImageXObject extends PDXObject implements PDImage {
    private SoftReference<Bitmap> cachedImage;
    private PDColorSpace colorSpace;
    private final PDResources resources;

    public static PDImageXObject createThumbnail(COSStream cOSStream) throws IOException {
        return new PDImageXObject(new PDStream(cOSStream), null);
    }

    public PDImageXObject(PDDocument pDDocument) throws IOException {
        this(new PDStream(pDDocument), null);
    }

    public PDImageXObject(PDDocument pDDocument, InputStream inputStream, COSBase cOSBase, int i, int i2, int i3, PDColorSpace pDColorSpace) throws IOException {
        super(createRawStream(pDDocument, inputStream), COSName.IMAGE);
        getCOSObject().setItem(COSName.FILTER, cOSBase);
        this.resources = null;
        this.colorSpace = null;
        setBitsPerComponent(i3);
        setWidth(i);
        setHeight(i2);
        setColorSpace(pDColorSpace);
    }

    private static COSStream createRawStream(PDDocument pDDocument, InputStream inputStream) throws Throwable {
        OutputStream outputStreamCreateRawOutputStream;
        COSStream cOSStreamCreateCOSStream = pDDocument.getDocument().createCOSStream();
        try {
            outputStreamCreateRawOutputStream = cOSStreamCreateCOSStream.createRawOutputStream();
            try {
                IOUtils.copy(inputStream, outputStreamCreateRawOutputStream);
                if (outputStreamCreateRawOutputStream != null) {
                    outputStreamCreateRawOutputStream.close();
                }
                return cOSStreamCreateCOSStream;
            } catch (Throwable th) {
                th = th;
                if (outputStreamCreateRawOutputStream != null) {
                    outputStreamCreateRawOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStreamCreateRawOutputStream = null;
        }
    }

    public PDImageXObject(PDStream pDStream, PDResources pDResources) throws IOException {
        this(pDStream, pDResources, pDStream.createInputStream());
    }

    public static PDImageXObject createFromFile(String str, PDDocument pDDocument) throws IOException {
        return createFromFileByExtension(new File(str), pDDocument);
    }

    public static PDImageXObject createFromFileByExtension(File file, PDDocument pDDocument) throws IOException {
        String name = file.getName();
        int iLastIndexOf = file.getName().lastIndexOf(46);
        if (iLastIndexOf == -1) {
            throw new IllegalArgumentException("Image type not supported: " + name);
        }
        String lowerCase = name.substring(iLastIndexOf + 1).toLowerCase();
        if ("jpg".equals(lowerCase) || "jpeg".equals(lowerCase)) {
            FileInputStream fileInputStream = new FileInputStream(file);
            PDImageXObject pDImageXObjectCreateFromStream = JPEGFactory.createFromStream(pDDocument, fileInputStream);
            fileInputStream.close();
            return pDImageXObjectCreateFromStream;
        }
        if ("tif".equals(lowerCase) || "tiff".equals(lowerCase)) {
            return CCITTFactory.createFromFile(pDDocument, file);
        }
        if ("gif".equals(lowerCase) || "bmp".equals(lowerCase) || "png".equals(lowerCase)) {
            return LosslessFactory.createFromImage(pDDocument, BitmapFactory.decodeFile(file.getPath()));
        }
        throw new IOException("Image type not supported: " + name);
    }

    public static PDImageXObject createFromFileByContent(File file, PDDocument pDDocument) throws Throwable {
        BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                bufferedInputStream = new BufferedInputStream(fileInputStream2);
            } catch (IOException e) {
                e = e;
                bufferedInputStream = null;
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = null;
            }
            try {
                FileType fileTypeDetectFileType = FileTypeDetector.detectFileType(bufferedInputStream);
                IOUtils.closeQuietly(fileInputStream2);
                IOUtils.closeQuietly(bufferedInputStream);
                if (fileTypeDetectFileType == null) {
                    throw new IllegalArgumentException("Image type not supported: " + file.getName());
                }
                if (fileTypeDetectFileType.equals(FileType.JPEG)) {
                    FileInputStream fileInputStream3 = new FileInputStream(file);
                    PDImageXObject pDImageXObjectCreateFromStream = JPEGFactory.createFromStream(pDDocument, fileInputStream3);
                    fileInputStream3.close();
                    return pDImageXObjectCreateFromStream;
                }
                if (fileTypeDetectFileType.equals(FileType.TIFF)) {
                    return CCITTFactory.createFromFile(pDDocument, file);
                }
                if (fileTypeDetectFileType.equals(FileType.BMP) || fileTypeDetectFileType.equals(FileType.GIF) || fileTypeDetectFileType.equals(FileType.PNG)) {
                    return LosslessFactory.createFromImage(pDDocument, BitmapFactory.decodeFile(file.getPath()));
                }
                throw new IllegalArgumentException("Image type not supported: " + file.getName());
            } catch (IOException e2) {
                e = e2;
                fileInputStream = fileInputStream2;
                try {
                    throw new IOException("Could not determine file type: " + file.getName(), e);
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(fileInputStream);
                    IOUtils.closeQuietly(bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                IOUtils.closeQuietly(fileInputStream);
                IOUtils.closeQuietly(bufferedInputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            bufferedInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
        }
    }

    private PDImageXObject(PDStream pDStream, PDResources pDResources, COSInputStream cOSInputStream) {
        super(repair(pDStream, cOSInputStream), COSName.IMAGE);
        this.resources = pDResources;
    }

    private static PDStream repair(PDStream pDStream, COSInputStream cOSInputStream) {
        pDStream.getCOSObject().addAll(cOSInputStream.getDecodeResult().getParameters());
        return pDStream;
    }

    public PDMetadata getMetadata() {
        COSStream cOSStream = (COSStream) getCOSObject().getDictionaryObject(COSName.METADATA);
        if (cOSStream != null) {
            return new PDMetadata(cOSStream);
        }
        return null;
    }

    public void setMetadata(PDMetadata pDMetadata) {
        getCOSObject().setItem(COSName.METADATA, pDMetadata);
    }

    public int getStructParent() {
        return getCOSObject().getInt(COSName.STRUCT_PARENT, 0);
    }

    public void setStructParent(int i) {
        getCOSObject().setInt(COSName.STRUCT_PARENT, i);
    }

    @Override
    public Bitmap getImage() throws IOException {
        Bitmap bitmap;
        SoftReference<Bitmap> softReference = this.cachedImage;
        if (softReference != null && (bitmap = softReference.get()) != null) {
            return bitmap;
        }
        Bitmap rGBImage = SampledImageReader.getRGBImage(this, getColorKeyMask());
        PDImageXObject softMask = getSoftMask();
        if (softMask != null) {
            rGBImage = applyMask(rGBImage, softMask.getOpaqueImage(), true);
        } else {
            PDImageXObject mask = getMask();
            if (mask != null && mask.isStencil()) {
                rGBImage = applyMask(rGBImage, mask.getOpaqueImage(), false);
            }
        }
        this.cachedImage = new SoftReference<>(rGBImage);
        return rGBImage;
    }

    @Override
    public Bitmap getStencilImage(Paint paint) throws IOException {
        if (!isStencil()) {
            throw new IllegalStateException("Image is not a stencil");
        }
        return SampledImageReader.getStencilImage(this, paint);
    }

    public Bitmap getOpaqueImage() throws IOException {
        return SampledImageReader.getRGBImage(this, null);
    }

    private Bitmap applyMask(Bitmap bitmap, Bitmap bitmap2, boolean z) throws IOException {
        int iRed;
        Bitmap bitmapScaleImage = bitmap;
        Bitmap bitmapScaleImage2 = bitmap2;
        if (bitmapScaleImage2 == null) {
            return bitmapScaleImage;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (bitmap2.getWidth() < width || bitmap2.getHeight() < height) {
            bitmapScaleImage2 = scaleImage(bitmapScaleImage2, width, height);
        } else if (bitmap2.getWidth() > width || bitmap2.getHeight() > height) {
            width = bitmap2.getWidth();
            height = bitmap2.getHeight();
            bitmapScaleImage = scaleImage(bitmapScaleImage, width, height);
        }
        Bitmap bitmap3 = bitmapScaleImage;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int i = width * height;
        int[] iArr = new int[i];
        bitmap3.getPixels(iArr, 0, width, 0, 0, width, height);
        int[] iArr2 = new int[i];
        bitmapScaleImage2.getPixels(iArr2, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            if (z) {
                iRed = Color.red(iArr2[i2]);
            } else {
                iRed = 255 - Color.red(iArr2[i2]);
            }
            iArr2[i2] = Color.argb(iRed, Color.red(i3), Color.green(i3), Color.blue(i3));
        }
        bitmapCreateBitmap.setPixels(iArr2, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    private Bitmap scaleImage(Bitmap bitmap, int i, int i2) {
        return Bitmap.createScaledBitmap(bitmap, i, i2, true);
    }

    public PDImageXObject getMask() throws IOException {
        COSStream cOSStream;
        if ((getCOSObject().getDictionaryObject(COSName.MASK) instanceof COSArray) || (cOSStream = (COSStream) getCOSObject().getDictionaryObject(COSName.MASK)) == null) {
            return null;
        }
        return new PDImageXObject(new PDStream(cOSStream), null);
    }

    public COSArray getColorKeyMask() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.MASK);
        if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        }
        return null;
    }

    public PDImageXObject getSoftMask() throws IOException {
        COSStream cOSStream = (COSStream) getCOSObject().getDictionaryObject(COSName.SMASK);
        if (cOSStream != null) {
            return new PDImageXObject(new PDStream(cOSStream), null);
        }
        return null;
    }

    @Override
    public int getBitsPerComponent() {
        if (isStencil()) {
            return 1;
        }
        return getCOSObject().getInt(COSName.BITS_PER_COMPONENT, COSName.BPC);
    }

    @Override
    public void setBitsPerComponent(int i) {
        getCOSObject().setInt(COSName.BITS_PER_COMPONENT, i);
    }

    @Override
    public PDColorSpace getColorSpace() throws IOException {
        if (this.colorSpace == null) {
            COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.COLORSPACE, COSName.f2247CS);
            if (dictionaryObject != null) {
                this.colorSpace = PDColorSpace.create(dictionaryObject, this.resources);
            } else {
                if (isStencil()) {
                    return PDDeviceGray.INSTANCE;
                }
                throw new IOException("could not determine color space");
            }
        }
        return this.colorSpace;
    }

    @Override
    public InputStream createInputStream() throws IOException {
        return getStream().createInputStream();
    }

    @Override
    public InputStream createInputStream(List<String> list) throws IOException {
        return getStream().createInputStream(list);
    }

    @Override
    public boolean isEmpty() {
        return getStream().getCOSObject().getLength() == 0;
    }

    @Override
    public void setColorSpace(PDColorSpace pDColorSpace) {
        getCOSObject().setItem(COSName.COLORSPACE, pDColorSpace != null ? pDColorSpace.getCOSObject() : null);
    }

    @Override
    public int getHeight() {
        return getCOSObject().getInt(COSName.HEIGHT);
    }

    @Override
    public void setHeight(int i) {
        getCOSObject().setInt(COSName.HEIGHT, i);
    }

    @Override
    public int getWidth() {
        return getCOSObject().getInt(COSName.WIDTH);
    }

    @Override
    public void setWidth(int i) {
        getCOSObject().setInt(COSName.WIDTH, i);
    }

    @Override
    public boolean getInterpolate() {
        return getCOSObject().getBoolean(COSName.INTERPOLATE, false);
    }

    @Override
    public void setInterpolate(boolean z) {
        getCOSObject().setBoolean(COSName.INTERPOLATE, z);
    }

    @Override
    public void setDecode(COSArray cOSArray) {
        getCOSObject().setItem(COSName.DECODE, (COSBase) cOSArray);
    }

    @Override
    public COSArray getDecode() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.DECODE);
        if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        }
        return null;
    }

    @Override
    public boolean isStencil() {
        return getCOSObject().getBoolean(COSName.IMAGE_MASK, false);
    }

    @Override
    public void setStencil(boolean z) {
        getCOSObject().setBoolean(COSName.IMAGE_MASK, z);
    }

    @Override
    public String getSuffix() {
        List<COSName> filters = getStream().getFilters();
        if (filters == null) {
            return "png";
        }
        if (filters.contains(COSName.DCT_DECODE)) {
            return "jpg";
        }
        if (filters.contains(COSName.JPX_DECODE)) {
            return "jpx";
        }
        if (filters.contains(COSName.CCITTFAX_DECODE)) {
            return "tiff";
        }
        if (filters.contains(COSName.FLATE_DECODE) || filters.contains(COSName.LZW_DECODE) || filters.contains(COSName.RUN_LENGTH_DECODE)) {
            return "png";
        }
        Log.w("PdfBox-Android", "getSuffix() returns null, filters: " + filters);
        return null;
    }
}
