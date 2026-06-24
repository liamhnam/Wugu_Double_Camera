package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.ttf.OTFParser;
import com.tom_roush.fontbox.ttf.OpenTypeFont;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeCollection;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.type1.Type1Font;
import com.tom_roush.fontbox.util.autodetect.FontFileFinder;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

final class FileSystemFontProvider extends FontProvider {
    private final FontCache cache;
    private final List<FSFontInfo> fontInfoList = new ArrayList();

    private static class FSFontInfo extends FontInfo {
        private final CIDSystemInfo cidSystemInfo;
        private final File file;
        private final FontFormat format;
        private final int macStyle;
        private final PDPanoseClassification panose;
        private transient FileSystemFontProvider parent;
        private final String postScriptName;
        private final int sFamilyClass;
        private final int ulCodePageRange1;
        private final int ulCodePageRange2;
        private final int usWeightClass;

        private FSFontInfo(File file, FontFormat fontFormat, String str, CIDSystemInfo cIDSystemInfo, int i, int i2, int i3, int i4, int i5, byte[] bArr, FileSystemFontProvider fileSystemFontProvider) {
            this.file = file;
            this.format = fontFormat;
            this.postScriptName = str;
            this.cidSystemInfo = cIDSystemInfo;
            this.usWeightClass = i;
            this.sFamilyClass = i2;
            this.ulCodePageRange1 = i3;
            this.ulCodePageRange2 = i4;
            this.macStyle = i5;
            this.panose = bArr != null ? new PDPanoseClassification(bArr) : null;
            this.parent = fileSystemFontProvider;
        }

        @Override
        public String getPostScriptName() {
            return this.postScriptName;
        }

        @Override
        public FontFormat getFormat() {
            return this.format;
        }

        @Override
        public CIDSystemInfo getCIDSystemInfo() {
            return this.cidSystemInfo;
        }

        @Override
        public FontBoxFont getFont() throws Throwable {
            FontBoxFont type1Font;
            FontBoxFont font = this.parent.cache.getFont(this);
            if (font != null) {
                return font;
            }
            int i = C18832.$SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat[this.format.ordinal()];
            if (i == 1) {
                type1Font = this.parent.getType1Font(this.postScriptName, this.file);
            } else if (i == 2) {
                type1Font = this.parent.getTrueTypeFont(this.postScriptName, this.file);
            } else if (i == 3) {
                type1Font = this.parent.getOTFFont(this.postScriptName, this.file);
            } else {
                throw new RuntimeException("can't happen");
            }
            this.parent.cache.addFont(this, type1Font);
            return type1Font;
        }

        @Override
        public int getFamilyClass() {
            return this.sFamilyClass;
        }

        @Override
        public int getWeightClass() {
            return this.usWeightClass;
        }

        @Override
        public int getCodePageRange1() {
            return this.ulCodePageRange1;
        }

        @Override
        public int getCodePageRange2() {
            return this.ulCodePageRange2;
        }

        @Override
        public int getMacStyle() {
            return this.macStyle;
        }

        @Override
        public PDPanoseClassification getPanose() {
            return this.panose;
        }

        @Override
        public String toString() {
            return super.toString() + " " + this.file;
        }
    }

    static class C18832 {
        static final int[] $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat;

        static {
            int[] iArr = new int[FontFormat.values().length];
            $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat = iArr;
            try {
                iArr[FontFormat.PFB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat[FontFormat.TTF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat[FontFormat.OTF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static final class FSIgnored extends FSFontInfo {
        private FSIgnored(File file, FontFormat fontFormat, String str) {
            super(file, fontFormat, str, null, 0, 0, 0, 0, 0, null, null);
        }
    }

    FileSystemFontProvider(FontCache fontCache) throws Throwable {
        this.cache = fontCache;
        if (PDFBoxResourceLoader.LOAD_FONTS == PDFBoxResourceLoader.FontLoadLevel.NONE) {
            return;
        }
        if (PDFBoxResourceLoader.LOAD_FONTS == PDFBoxResourceLoader.FontLoadLevel.MINIMUM) {
            try {
                addTrueTypeFont(new File("/system/fonts/DroidSans.ttf"));
                addTrueTypeFont(new File("/system/fonts/DroidSans-Bold.ttf"));
                addTrueTypeFont(new File("/system/fonts/DroidSansMono.ttf"));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Log.v("PdfBox-Android", "Will search the local system for fonts");
            ArrayList arrayList = new ArrayList();
            Iterator<URI> it = new FontFileFinder().find().iterator();
            while (it.hasNext()) {
                arrayList.add(new File(it.next()));
            }
            Log.v("PdfBox-Android", "Found " + arrayList.size() + " fonts on the local system");
            List<FSFontInfo> listLoadDiskCache = loadDiskCache(arrayList);
            if (listLoadDiskCache != null && listLoadDiskCache.size() > 0) {
                this.fontInfoList.addAll(listLoadDiskCache);
                return;
            }
            Log.w("PdfBox-Android", "Building font cache, this may take a while");
            scanFonts(arrayList);
            saveDiskCache();
            Log.w("PdfBox-Android", "Finished building on-disk font cache, found " + this.fontInfoList.size() + " fonts");
        } catch (AccessControlException e2) {
            Log.e("PdfBox-Android", "Error accessing the file system", e2);
        }
    }

    private void scanFonts(List<File> list) throws Throwable {
        for (File file : list) {
            try {
                if (file.getPath().toLowerCase().endsWith(".ttf") || file.getPath().toLowerCase().endsWith(".otf")) {
                    addTrueTypeFont(file);
                } else if (file.getPath().toLowerCase().endsWith(".ttc") || file.getPath().toLowerCase().endsWith(".otc")) {
                    addTrueTypeCollection(file);
                } else if (file.getPath().toLowerCase().endsWith(".pfb")) {
                    addType1Font(file);
                }
            } catch (IOException e) {
                Log.e("PdfBox-Android", "Error parsing font " + file.getPath(), e);
            }
        }
    }

    private File getDiskCacheFile() {
        String property = System.getProperty("pdfbox.fontcache");
        if (property == null && (property = System.getProperty("user.home")) == null) {
            property = System.getProperty("java.io.tmpdir");
        }
        return new File(property, ".pdfbox.cache");
    }

    private void saveDiskCache() throws Throwable {
        BufferedWriter bufferedWriter;
        ?? r1 = 0;
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(getDiskCacheFile()));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            Iterator<FSFontInfo> it = this.fontInfoList.iterator();
            while (it.hasNext()) {
                FSFontInfo next = it.next();
                bufferedWriter.write(next.postScriptName.trim());
                bufferedWriter.write("|");
                bufferedWriter.write(next.format.toString());
                bufferedWriter.write("|");
                if (next.cidSystemInfo != null) {
                    bufferedWriter.write(next.cidSystemInfo.getRegistry() + '-' + next.cidSystemInfo.getOrdering() + '-' + next.cidSystemInfo.getSupplement());
                }
                bufferedWriter.write("|");
                if (next.usWeightClass > -1) {
                    bufferedWriter.write(Integer.toHexString(next.usWeightClass));
                }
                bufferedWriter.write("|");
                if (next.sFamilyClass > -1) {
                    bufferedWriter.write(Integer.toHexString(next.sFamilyClass));
                }
                bufferedWriter.write("|");
                bufferedWriter.write(Integer.toHexString(next.ulCodePageRange1));
                bufferedWriter.write("|");
                bufferedWriter.write(Integer.toHexString(next.ulCodePageRange2));
                bufferedWriter.write("|");
                if (next.macStyle > -1) {
                    bufferedWriter.write(Integer.toHexString(next.macStyle));
                }
                bufferedWriter.write("|");
                if (next.panose != null) {
                    byte[] bytes = next.panose.getBytes();
                    for (int i = 0; i < 10; i++) {
                        String hexString = Integer.toHexString(bytes[i]);
                        if (hexString.length() == 1) {
                            bufferedWriter.write(48);
                        }
                        bufferedWriter.write(hexString);
                    }
                }
                bufferedWriter.write("|");
                bufferedWriter.write(next.file.getAbsolutePath());
                bufferedWriter.newLine();
            }
            IOUtils.closeQuietly(bufferedWriter);
            r1 = it;
        } catch (IOException e2) {
            e = e2;
            bufferedWriter2 = bufferedWriter;
            Log.e("PdfBox-Android", "Could not write to font cache", e);
            IOUtils.closeQuietly(bufferedWriter2);
            r1 = bufferedWriter2;
        } catch (Throwable th2) {
            th = th2;
            r1 = bufferedWriter;
            IOUtils.closeQuietly(r1);
            throw th;
        }
    }

    private List<FSFontInfo> loadDiskCache(List<File> list) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        CIDSystemInfo cIDSystemInfo;
        byte[] bArr;
        HashSet hashSet = new HashSet();
        Iterator<File> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getAbsolutePath());
        }
        ArrayList arrayList = new ArrayList();
        File diskCacheFile = getDiskCacheFile();
        if (diskCacheFile.exists()) {
            try {
                bufferedReader2 = new BufferedReader(new FileReader(diskCacheFile));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        String[] strArrSplit = line.split("\\|", 10);
                        if (strArrSplit.length < 10) {
                            try {
                                try {
                                    Log.e("PdfBox-Android", "Incorrect line '" + line + "' in font disk cache is skipped");
                                } catch (IOException e) {
                                    e = e;
                                    Log.e("PdfBox-Android", "Error loading font cache, will be re-built", e);
                                    IOUtils.closeQuietly(bufferedReader2);
                                    return null;
                                }
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                IOUtils.closeQuietly(bufferedReader);
                                throw th;
                            }
                        } else {
                            String str = strArrSplit[0];
                            FontFormat fontFormatValueOf = FontFormat.valueOf(strArrSplit[1]);
                            if (strArrSplit[2].length() > 0) {
                                String[] strArrSplit2 = strArrSplit[2].split("-");
                                cIDSystemInfo = new CIDSystemInfo(strArrSplit2[0], strArrSplit2[1], Integer.parseInt(strArrSplit2[2]));
                            } else {
                                cIDSystemInfo = null;
                            }
                            int i = strArrSplit[3].length() > 0 ? (int) Long.parseLong(strArrSplit[3], 16) : -1;
                            int i2 = strArrSplit[4].length() > 0 ? (int) Long.parseLong(strArrSplit[4], 16) : -1;
                            int i3 = (int) Long.parseLong(strArrSplit[5], 16);
                            bufferedReader3 = bufferedReader2;
                            try {
                                int i4 = (int) Long.parseLong(strArrSplit[6], 16);
                                int i5 = strArrSplit[7].length() > 0 ? (int) Long.parseLong(strArrSplit[7], 16) : -1;
                                char c = '\b';
                                if (strArrSplit[8].length() > 0) {
                                    byte[] bArr2 = new byte[10];
                                    int i6 = 0;
                                    for (int i7 = 10; i6 < i7; i7 = 10) {
                                        String str2 = strArrSplit[c];
                                        int i8 = i6 * 2;
                                        bArr2[i6] = (byte) (Integer.parseInt(str2.substring(i8, i8 + 2), 16) & 255);
                                        i6++;
                                        c = '\b';
                                    }
                                    bArr = bArr2;
                                } else {
                                    bArr = null;
                                }
                                File file = new File(strArrSplit[9]);
                                arrayList.add(new FSFontInfo(file, fontFormatValueOf, str, cIDSystemInfo, i, i2, i3, i4, i5, bArr, this));
                                hashSet.remove(file.getAbsolutePath());
                                bufferedReader2 = bufferedReader3;
                            } catch (IOException e2) {
                                e = e2;
                                bufferedReader2 = bufferedReader3;
                                Log.e("PdfBox-Android", "Error loading font cache, will be re-built", e);
                                IOUtils.closeQuietly(bufferedReader2);
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedReader = bufferedReader3;
                                IOUtils.closeQuietly(bufferedReader);
                                throw th;
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader3 = bufferedReader2;
                    }
                }
                IOUtils.closeQuietly(bufferedReader2);
            } catch (IOException e4) {
                e = e4;
                bufferedReader2 = null;
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
            }
        }
        if (hashSet.size() <= 0) {
            return arrayList;
        }
        Log.w("PdfBox-Android", "New fonts found, font cache will be re-built");
        return null;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void addTrueTypeCollection(final java.io.File r7) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = "PdfBox-Android"
            java.lang.String r1 = "Could not load font file: "
            r2 = 0
            com.tom_roush.fontbox.ttf.TrueTypeCollection r3 = new com.tom_roush.fontbox.ttf.TrueTypeCollection     // Catch: java.lang.Throwable -> L1d java.io.IOException -> L1f java.lang.NullPointerException -> L36
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L1d java.io.IOException -> L1f java.lang.NullPointerException -> L36
            com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider$1 r2 = new com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider$1     // Catch: java.lang.Throwable -> L16 java.io.IOException -> L19 java.lang.NullPointerException -> L1b
            r2.<init>()     // Catch: java.lang.Throwable -> L16 java.io.IOException -> L19 java.lang.NullPointerException -> L1b
            r3.processAllFonts(r2)     // Catch: java.lang.Throwable -> L16 java.io.IOException -> L19 java.lang.NullPointerException -> L1b
        L12:
            r3.close()
            goto L4d
        L16:
            r7 = move-exception
            r2 = r3
            goto L4e
        L19:
            r2 = move-exception
            goto L23
        L1b:
            r2 = move-exception
            goto L3a
        L1d:
            r7 = move-exception
            goto L4e
        L1f:
            r3 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
        L23:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L16
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L16
            java.lang.StringBuilder r7 = r4.append(r7)     // Catch: java.lang.Throwable -> L16
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L16
            android.util.Log.e(r0, r7, r2)     // Catch: java.lang.Throwable -> L16
            if (r3 == 0) goto L4d
            goto L12
        L36:
            r3 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
        L3a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L16
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L16
            java.lang.StringBuilder r7 = r4.append(r7)     // Catch: java.lang.Throwable -> L16
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L16
            android.util.Log.e(r0, r7, r2)     // Catch: java.lang.Throwable -> L16
            if (r3 == 0) goto L4d
            goto L12
        L4d:
            return
        L4e:
            if (r2 == 0) goto L53
            r2.close()
        L53:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider.addTrueTypeCollection(java.io.File):void");
    }

    private void addTrueTypeFont(File file) throws Throwable {
        try {
            if (file.getPath().endsWith(".otf")) {
                addTrueTypeFontImpl(new OTFParser(false, true).parse(file), file);
            } else {
                addTrueTypeFontImpl(new TTFParser(false, true).parse(file), file);
            }
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Could not load font file: " + file, e);
        } catch (NullPointerException e2) {
            Log.e("PdfBox-Android", "Could not load font file: " + file, e2);
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addTrueTypeFontImpl(com.tom_roush.fontbox.ttf.TrueTypeFont r27, java.io.File r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 545
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider.addTrueTypeFontImpl(com.tom_roush.fontbox.ttf.TrueTypeFont, java.io.File):void");
    }

    private void addType1Font(File file) throws Throwable {
        FileInputStream fileInputStream;
        String str;
        FileInputStream fileInputStream2 = new FileInputStream(file);
        try {
            try {
                Type1Font type1FontCreateWithPFB = Type1Font.createWithPFB(fileInputStream2);
                fileInputStream = fileInputStream2;
                str = "Could not load font file: ";
                try {
                    this.fontInfoList.add(new FSFontInfo(file, FontFormat.PFB, type1FontCreateWithPFB.getName(), null, -1, -1, 0, 0, -1, null, this));
                    Log.v("PdfBox-Android", "PFB: '" + type1FontCreateWithPFB.getName() + "' / '" + type1FontCreateWithPFB.getFamilyName() + "' / '" + type1FontCreateWithPFB.getWeight() + "'");
                } catch (IOException e) {
                    e = e;
                    Log.e("PdfBox-Android", str + file, e);
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream.close();
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            fileInputStream = fileInputStream2;
            str = "Could not load font file: ";
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = fileInputStream2;
            fileInputStream.close();
            throw th;
        }
        fileInputStream.close();
    }

    public TrueTypeFont getTrueTypeFont(String str, File file) {
        try {
            TrueTypeFont trueTypeFont = readTrueTypeFont(str, file);
            Log.d("PdfBox-Android", "Loaded " + str + " from " + file);
            return trueTypeFont;
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Could not load font file: " + file, e);
            return null;
        } catch (NullPointerException e2) {
            Log.e("PdfBox-Android", "Could not load font file: " + file, e2);
            return null;
        }
    }

    private TrueTypeFont readTrueTypeFont(String str, File file) throws IOException {
        if (file.getName().toLowerCase().endsWith(".ttc")) {
            TrueTypeCollection trueTypeCollection = new TrueTypeCollection(file);
            TrueTypeFont fontByName = trueTypeCollection.getFontByName(str);
            if (fontByName != null) {
                return fontByName;
            }
            trueTypeCollection.close();
            throw new IOException("Font " + str + " not found in " + file);
        }
        return new TTFParser(false, true).parse(file);
    }

    public OpenTypeFont getOTFFont(String str, File file) {
        try {
            OpenTypeFont openTypeFont = new OTFParser(false, true).parse(file);
            Log.d("PdfBox-Android", "Loaded " + str + " from " + file);
            return openTypeFont;
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Could not load font file: " + file, e);
            return null;
        }
    }

    public Type1Font getType1Font(String str, File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    Type1Font type1FontCreateWithPFB = Type1Font.createWithPFB(fileInputStream);
                    Log.d("PdfBox-Android", "Loaded " + str + " from " + file);
                    IOUtils.closeQuietly(fileInputStream);
                    return type1FontCreateWithPFB;
                } catch (IOException e) {
                    e = e;
                    Log.e("PdfBox-Android", "Could not load font file: " + file, e);
                    IOUtils.closeQuietly(fileInputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                IOUtils.closeQuietly(fileInputStream2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly(fileInputStream2);
            throw th;
        }
    }

    @Override
    public String toDebugString() {
        StringBuilder sb = new StringBuilder();
        for (FSFontInfo fSFontInfo : this.fontInfoList) {
            sb.append(fSFontInfo.getFormat());
            sb.append(": ");
            sb.append(fSFontInfo.getPostScriptName());
            sb.append(": ");
            sb.append(fSFontInfo.file.getPath());
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public List<? extends FontInfo> getFontInfo() {
        return this.fontInfoList;
    }
}
