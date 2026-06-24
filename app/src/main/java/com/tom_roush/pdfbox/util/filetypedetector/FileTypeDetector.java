package com.tom_roush.pdfbox.util.filetypedetector;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.BufferedInputStream;
import java.io.IOException;

public final class FileTypeDetector {
    private static final ByteTrie<FileType> root;

    static {
        ByteTrie<FileType> byteTrie = new ByteTrie<>();
        root = byteTrie;
        byteTrie.setDefaultValue(FileType.UNKNOWN);
        byteTrie.addPath(FileType.JPEG, new byte[]{-1, -40});
        byteTrie.addPath(FileType.TIFF, "II".getBytes(Charsets.ISO_8859_1), new byte[]{42, 0});
        byteTrie.addPath(FileType.TIFF, "MM".getBytes(Charsets.ISO_8859_1), new byte[]{0, 42});
        byteTrie.addPath(FileType.PSD, "8BPS".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.PNG, new byte[]{-119, 80, 78, SnmpConstants.UINTEGER32, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, SnmpConstants.OPAQUE, 82});
        byteTrie.addPath(FileType.BMP, "BM".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.GIF, "GIF87a".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.GIF, "GIF89a".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.ICO, new byte[]{0, 0, 1, 0});
        byteTrie.addPath(FileType.PCX, new byte[]{10, 0, 1});
        byteTrie.addPath(FileType.PCX, new byte[]{10, 2, 1});
        byteTrie.addPath(FileType.PCX, new byte[]{10, 3, 1});
        byteTrie.addPath(FileType.PCX, new byte[]{10, 5, 1});
        byteTrie.addPath(FileType.RIFF, "RIFF".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.ARW, "II".getBytes(Charsets.ISO_8859_1), new byte[]{42, 0, 8, 0});
        byteTrie.addPath(FileType.CRW, "II".getBytes(Charsets.ISO_8859_1), new byte[]{26, 0, 0, 0}, "HEAPCCDR".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.CR2, "II".getBytes(Charsets.ISO_8859_1), new byte[]{42, 0, 16, 0, 0, 0, SnmpConstants.TIMETICKS, 82});
        byteTrie.addPath(FileType.NEF, "MM".getBytes(Charsets.ISO_8859_1), new byte[]{0, 42, 0, 0, 0, -128, 0});
        byteTrie.addPath(FileType.ORF, "IIRO".getBytes(Charsets.ISO_8859_1), new byte[]{8, 0});
        byteTrie.addPath(FileType.ORF, "IIRS".getBytes(Charsets.ISO_8859_1), new byte[]{8, 0});
        byteTrie.addPath(FileType.RAF, "FUJIFILMCCD-RAW".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.RW2, "II".getBytes(Charsets.ISO_8859_1), new byte[]{85, 0});
    }

    private FileTypeDetector() throws Exception {
    }

    public static FileType detectFileType(BufferedInputStream bufferedInputStream) throws IOException {
        if (!bufferedInputStream.markSupported()) {
            throw new IOException("Stream must support mark/reset");
        }
        ByteTrie<FileType> byteTrie = root;
        int maxDepth = byteTrie.getMaxDepth();
        bufferedInputStream.mark(maxDepth);
        byte[] bArr = new byte[maxDepth];
        if (bufferedInputStream.read(bArr) == -1) {
            throw new IOException("Stream ended before file's magic number could be determined.");
        }
        bufferedInputStream.reset();
        return byteTrie.find(bArr);
    }
}
