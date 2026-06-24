package com.p020hp.open.printsdk;

import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.p020hp.jipp.model.JobPasswordEncryption;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u0000 /2\u00020\u0001:\u0001/B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u000f\b\u0002\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\b\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010'\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010)\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u0010*\u001a\u00020\u0007J\u0006\u0010+\u001a\u00020\u0007J\r\u0010\t\u001a\u00020\u0007H\u0000¢\u0006\u0002\b,J\b\u0010-\u001a\u00020\fH\u0016J\f\u0010.\u001a\u00020\f*\u00020\fH\u0002R\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010\u0004¨\u00060"}, m1293d2 = {"Lcom/hp/open/printsdk/HpPrintFile;", "Ljava/io/Closeable;", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "fileUri", "isPdfPrintingEnhancement", "", "(Landroid/net/Uri;Z)V", "printingEnhancement", "(Z)V", "contentName", "", "getContentName", "()Ljava/lang/String;", "setContentName", "(Ljava/lang/String;)V", "inputStream", "Ljava/io/InputStream;", "getInputStream", "()Ljava/io/InputStream;", "setInputStream", "(Ljava/io/InputStream;)V", "mimeType", "getMimeType", "setMimeType", "pageCount", "", "getPageCount", "()I", "setPageCount", "(I)V", "getUri", "()Landroid/net/Uri;", "setUri", "close", "", "getFileInformation", "getRealFilePath", "getUriContentName", "getUriInputStream", "getUriMimeType", "isImageFile", "isPdfFile", "printingEnhancement$print_core_thirdPartyRelease", "toString", JobPasswordEncryption.md5, "Companion", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class HpPrintFile implements Closeable {

    public final boolean f779a;

    public int f780b;
    public String contentName;
    public InputStream inputStream;
    public String mimeType;
    public Uri uri;

    public HpPrintFile(Uri uri) {
        this(uri, uri.getBooleanQueryParameter("printingEnhancement", false));
        Intrinsics.checkNotNullParameter(uri, "uri");
    }

    public HpPrintFile(Uri fileUri, boolean z) {
        this(z);
        Intrinsics.checkNotNullParameter(fileUri, "fileUri");
        PDFBoxResourceLoader.init(CoreManager.INSTANCE.getContext$print_core_thirdPartyRelease());
        Uri uriBuild = fileUri.buildUpon().clearQuery().build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "fileUri.buildUpon().clearQuery().build()");
        setUri(uriBuild);
        setMimeType(m454d(getUri()));
        setInputStream(m453c(getUri()));
        setContentName(m452b(getUri()));
        m451a(getUri());
    }

    public HpPrintFile(boolean z) {
        this.f779a = z;
        this.f780b = 1;
    }

    public final void m451a(Uri uri) {
        Object objM1772constructorimpl;
        Unit unit;
        if (isImageFile()) {
            this.f780b = 1;
            return;
        }
        if (isPdfFile()) {
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = CoreManager.INSTANCE.getContext$print_core_thirdPartyRelease().getContentResolver().openFileDescriptor(uri, PDPageLabelRange.STYLE_ROMAN_LOWER);
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    PdfRenderer pdfRenderer = new PdfRenderer(parcelFileDescriptorOpenFileDescriptor);
                    this.f780b = pdfRenderer.getPageCount();
                    pdfRenderer.close();
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                objM1772constructorimpl = Result.m1772constructorimpl(unit);
            } catch (Throwable th) {
                objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
            if (thM1775exceptionOrNullimpl != null) {
                throw new HpPrintFileException(HpPrintFileException.INSTANCE.getCan_Not_Open_File(), "Can not open stream from uri: " + uri, thM1775exceptionOrNullimpl);
            }
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String m452b(android.net.Uri r15) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.open.printsdk.HpPrintFile.m452b(android.net.Uri):java.lang.String");
    }

    public final InputStream m453c(Uri uri) {
        Object objM1772constructorimpl;
        try {
            InputStream inputStreamOpenInputStream = CoreManager.INSTANCE.getContext$print_core_thirdPartyRelease().getContentResolver().openInputStream(uri);
            Intrinsics.checkNotNull(inputStreamOpenInputStream);
            objM1772constructorimpl = Result.m1772constructorimpl(inputStreamOpenInputStream);
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
        if (thM1775exceptionOrNullimpl == null) {
            return (InputStream) objM1772constructorimpl;
        }
        throw new HpPrintFileException(HpPrintFileException.INSTANCE.getCan_Not_Open_File(), "Can not open stream from uri: " + uri, thM1775exceptionOrNullimpl);
    }

    @Override
    public void close() throws IOException {
        if (this.inputStream != null) {
            getInputStream().close();
        }
    }

    public final String m454d(Uri uri) {
        String type = CoreManager.INSTANCE.getContext$print_core_thirdPartyRelease().getContentResolver().getType(uri);
        if (type == null) {
            type = null;
        } else if (!new Regex("image/(jpg|jpeg)").matches(type) && !StringsKt.contains$default((CharSequence) type, (CharSequence) "pdf", false, 2, (Object) null)) {
            throw new HpPrintFileException(HpPrintFileException.INSTANCE.getMime_Type_Not_Support(), "Mime Type: " + type + " is not supported", null, 4, null);
        }
        if (type != null) {
            return type;
        }
        throw new HpPrintFileException(HpPrintFileException.INSTANCE.getMime_Type_Not_Found(), "Can not get mime type from uri: " + uri, null, 4, null);
    }

    public final String getContentName() {
        String str = this.contentName;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contentName");
        return null;
    }

    public final InputStream getInputStream() {
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            return inputStream;
        }
        Intrinsics.throwUninitializedPropertyAccessException("inputStream");
        return null;
    }

    public final String getMimeType() {
        String str = this.mimeType;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mimeType");
        return null;
    }

    public final int getF780b() {
        return this.f780b;
    }

    public final Uri getUri() {
        Uri uri = this.uri;
        if (uri != null) {
            return uri;
        }
        Intrinsics.throwUninitializedPropertyAccessException("uri");
        return null;
    }

    public final boolean isImageFile() {
        return StringsKt.contains$default((CharSequence) getMimeType(), (CharSequence) TtmlNode.TAG_IMAGE, false, 2, (Object) null);
    }

    public final boolean isPdfFile() {
        return StringsKt.contains$default((CharSequence) getMimeType(), (CharSequence) "pdf", false, 2, (Object) null);
    }

    public final boolean printingEnhancement$print_core_thirdPartyRelease() {
        return this.f779a && isPdfFile();
    }

    public final void setContentName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contentName = str;
    }

    public final void setInputStream(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "<set-?>");
        this.inputStream = inputStream;
    }

    public final void setMimeType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mimeType = str;
    }

    public final void setPageCount(int i) {
        this.f780b = i;
    }

    public final void setUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<set-?>");
        this.uri = uri;
    }

    public String toString() {
        return "HpPrintFile(contentName: " + getContentName() + ", mimeType: " + getMimeType() + ", pageCount: " + this.f780b + ", uri: " + getUri() + ')';
    }
}
