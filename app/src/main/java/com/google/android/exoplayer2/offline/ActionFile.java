package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloadRequest;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Deprecated
final class ActionFile {
    private static final int VERSION = 0;
    private final AtomicFile atomicFile;

    public ActionFile(File file) {
        this.atomicFile = new AtomicFile(file);
    }

    public boolean exists() {
        return this.atomicFile.exists();
    }

    public void delete() {
        this.atomicFile.delete();
    }

    public DownloadRequest[] load() throws IOException {
        if (!exists()) {
            return new DownloadRequest[0];
        }
        try {
            InputStream inputStreamOpenRead = this.atomicFile.openRead();
            DataInputStream dataInputStream = new DataInputStream(inputStreamOpenRead);
            int i = dataInputStream.readInt();
            if (i > 0) {
                throw new IOException("Unsupported action file version: " + i);
            }
            int i2 = dataInputStream.readInt();
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < i2; i3++) {
                try {
                    arrayList.add(readDownloadRequest(dataInputStream));
                } catch (DownloadRequest.UnsupportedRequestException unused) {
                }
            }
            DownloadRequest[] downloadRequestArr = (DownloadRequest[]) arrayList.toArray(new DownloadRequest[0]);
            Util.closeQuietly(inputStreamOpenRead);
            return downloadRequestArr;
        } catch (Throwable th) {
            Util.closeQuietly((Closeable) null);
            throw th;
        }
    }

    private static DownloadRequest readDownloadRequest(DataInputStream dataInputStream) throws IOException {
        byte[] bArr;
        String utf = dataInputStream.readUTF();
        int i = dataInputStream.readInt();
        Uri uri = Uri.parse(dataInputStream.readUTF());
        boolean z = dataInputStream.readBoolean();
        int i2 = dataInputStream.readInt();
        String utf2 = null;
        if (i2 != 0) {
            byte[] bArr2 = new byte[i2];
            dataInputStream.readFully(bArr2);
            bArr = bArr2;
        } else {
            bArr = null;
        }
        boolean z2 = true;
        boolean z3 = i == 0 && "progressive".equals(utf);
        ArrayList arrayList = new ArrayList();
        if (!z3) {
            int i3 = dataInputStream.readInt();
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.add(readKey(utf, i, dataInputStream));
            }
        }
        if (i >= 2 || (!DownloadRequest.TYPE_DASH.equals(utf) && !DownloadRequest.TYPE_HLS.equals(utf) && !DownloadRequest.TYPE_SS.equals(utf))) {
            z2 = false;
        }
        if (!z2 && dataInputStream.readBoolean()) {
            utf2 = dataInputStream.readUTF();
        }
        String strGenerateDownloadId = i < 3 ? generateDownloadId(uri, utf2) : dataInputStream.readUTF();
        if (z) {
            throw new DownloadRequest.UnsupportedRequestException();
        }
        return new DownloadRequest(strGenerateDownloadId, utf, uri, arrayList, utf2, bArr);
    }

    private static StreamKey readKey(String str, int i, DataInputStream dataInputStream) throws IOException {
        int i2;
        int i3;
        int i4;
        if ((DownloadRequest.TYPE_HLS.equals(str) || DownloadRequest.TYPE_SS.equals(str)) && i == 0) {
            i2 = dataInputStream.readInt();
            i3 = dataInputStream.readInt();
            i4 = 0;
        } else {
            int i5 = dataInputStream.readInt();
            int i6 = dataInputStream.readInt();
            int i7 = dataInputStream.readInt();
            i4 = i5;
            i2 = i6;
            i3 = i7;
        }
        return new StreamKey(i4, i2, i3);
    }

    private static String generateDownloadId(Uri uri, String str) {
        return str != null ? str : uri.toString();
    }
}
