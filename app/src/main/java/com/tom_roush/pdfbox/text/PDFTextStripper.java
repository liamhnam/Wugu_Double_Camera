package com.tom_roush.pdfbox.text;

import android.util.Log;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageTree;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation.PDThreadBead;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.util.QuickSort;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class PDFTextStripper extends PDFTextStreamEngine {
    private static final float END_OF_LAST_TEXT_X_RESET_VALUE = -1.0f;
    private static final float EXPECTED_START_OF_NEXT_WORD_X_RESET_VALUE = -3.4028235E38f;
    private static final float LAST_WORD_SPACING_RESET_VALUE = -1.0f;
    private static final String[] LIST_ITEM_EXPRESSIONS;
    private static final float MAX_HEIGHT_FOR_LINE_RESET_VALUE = -1.0f;
    private static final float MAX_Y_FOR_LINE_RESET_VALUE = -3.4028235E38f;
    private static final float MIN_Y_TOP_FOR_LINE_RESET_VALUE = Float.MAX_VALUE;
    private static Map<Character, Character> MIRRORING_CHAR_MAP = null;
    private static float defaultDropThreshold = 2.5f;
    private static float defaultIndentThreshold = 2.0f;
    private static final boolean useCustomQuickSort;
    protected final String LINE_SEPARATOR;
    private boolean addMoreFormatting;
    private String articleEnd;
    private String articleStart;
    private float averageCharTolerance;
    private List<PDRectangle> beadRectangles;
    private Map<String, TreeMap<Float, TreeSet<Float>>> characterListMapping;
    protected ArrayList<List<TextPosition>> charactersByArticle;
    private int currentPageNo;
    protected PDDocument document;
    private float dropThreshold;
    private PDOutlineItem endBookmark;
    private int endBookmarkPageNumber;
    private int endPage;
    private boolean inParagraph;
    private float indentThreshold;
    private String lineSeparator;
    private List<Pattern> listOfPatterns;
    protected Writer output;
    private String pageEnd;
    private String pageStart;
    private String paragraphEnd;
    private String paragraphStart;
    private boolean shouldSeparateByBeads;
    private boolean sortByPosition;
    private float spacingTolerance;
    private PDOutlineItem startBookmark;
    private int startBookmarkPageNumber;
    private int startPage;
    private boolean suppressDuplicateOverlappingText;
    private String wordSeparator;

    private boolean within(float f, float f2, float f3) {
        return f2 < f + f3 && f2 > f - f3;
    }

    protected void endDocument(PDDocument pDDocument) throws IOException {
    }

    protected void endPage(PDPage pDPage) throws IOException {
    }

    protected void startDocument(PDDocument pDDocument) throws IOException {
    }

    protected void startPage(PDPage pDPage) throws IOException {
    }

    static {
        String property;
        String property2;
        String lowerCase;
        try {
            lowerCase = "PDFTextStripper".toLowerCase();
            property = System.getProperty(lowerCase + ".indent");
        } catch (SecurityException unused) {
            property = null;
        }
        try {
            property2 = System.getProperty(lowerCase + ".drop");
        } catch (SecurityException unused2) {
            property2 = null;
        }
        if (property != null && property.length() > 0) {
            try {
                defaultIndentThreshold = Float.parseFloat(property);
            } catch (NumberFormatException unused3) {
            }
        }
        if (property2 != null && property2.length() > 0) {
            try {
                defaultDropThreshold = Float.parseFloat(property2);
            } catch (NumberFormatException unused4) {
            }
        }
        boolean z = false;
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.specification.version"), ".");
            int i = Integer.parseInt(stringTokenizer.nextToken());
            int i2 = stringTokenizer.hasMoreTokens() ? Integer.parseInt(stringTokenizer.nextToken()) : 0;
            if (i == 1 && i2 <= 6) {
                z = true;
            }
        } catch (NumberFormatException | SecurityException unused5) {
        }
        useCustomQuickSort = true ^ z;
        LIST_ITEM_EXPRESSIONS = new String[]{"\\.", "\\d+\\.", "\\[\\d+\\]", "\\d+\\)", "[A-Z]\\.", "[a-z]\\.", "[A-Z]\\)", "[a-z]\\)", "[IVXL]+\\.", "[ivxl]+\\."};
        MIRRORING_CHAR_MAP = new HashMap();
        InputStream resourceAsStream = PDFBoxResourceLoader.isReady() ? null : PDFTextStripper.class.getClassLoader().getResourceAsStream("com/tom_roush/pdfbox/resources/text/BidiMirroring.txt");
        try {
            try {
                try {
                    if (PDFBoxResourceLoader.isReady()) {
                        resourceAsStream = PDFBoxResourceLoader.getStream("com/tom_roush/pdfbox/resources/text/BidiMirroring.txt");
                    }
                    parseBidiFile(resourceAsStream);
                    resourceAsStream.close();
                } catch (IOException e) {
                    Log.w("PdfBox-Android", "Could not parse BidiMirroring.txt, mirroring char map will be empty: " + e.getMessage());
                    resourceAsStream.close();
                }
            } catch (Throwable th) {
                try {
                    resourceAsStream.close();
                } catch (IOException e2) {
                    Log.e("PdfBox-Android", "Could not close BidiMirroring.txt ", e2);
                }
                throw th;
            }
        } catch (IOException e3) {
            Log.e("PdfBox-Android", "Could not close BidiMirroring.txt ", e3);
        }
    }

    public PDFTextStripper() throws IOException {
        String property = System.getProperty("line.separator");
        this.LINE_SEPARATOR = property;
        this.lineSeparator = property;
        this.wordSeparator = " ";
        this.paragraphStart = "";
        this.paragraphEnd = "";
        this.pageStart = "";
        this.pageEnd = property;
        this.articleStart = "";
        this.articleEnd = "";
        this.currentPageNo = 0;
        this.startPage = 1;
        this.endPage = Integer.MAX_VALUE;
        this.startBookmark = null;
        this.startBookmarkPageNumber = -1;
        this.endBookmarkPageNumber = -1;
        this.endBookmark = null;
        this.suppressDuplicateOverlappingText = true;
        this.shouldSeparateByBeads = true;
        this.sortByPosition = false;
        this.addMoreFormatting = false;
        this.indentThreshold = defaultIndentThreshold;
        this.dropThreshold = defaultDropThreshold;
        this.spacingTolerance = 0.5f;
        this.averageCharTolerance = 0.3f;
        this.beadRectangles = null;
        this.charactersByArticle = new ArrayList<>();
        this.characterListMapping = new HashMap();
        this.listOfPatterns = null;
    }

    public String getText(PDDocument pDDocument) throws IOException {
        StringWriter stringWriter = new StringWriter();
        writeText(pDDocument, stringWriter);
        return stringWriter.toString();
    }

    private void resetEngine() {
        this.currentPageNo = 0;
        this.document = null;
        ArrayList<List<TextPosition>> arrayList = this.charactersByArticle;
        if (arrayList != null) {
            arrayList.clear();
        }
        Map<String, TreeMap<Float, TreeSet<Float>>> map = this.characterListMapping;
        if (map != null) {
            map.clear();
        }
    }

    public void writeText(PDDocument pDDocument, Writer writer) throws IOException {
        resetEngine();
        this.document = pDDocument;
        this.output = writer;
        if (getAddMoreFormatting()) {
            String str = this.lineSeparator;
            this.paragraphEnd = str;
            this.pageStart = str;
            this.articleStart = str;
            this.articleEnd = str;
        }
        startDocument(this.document);
        processPages(this.document.getPages());
        endDocument(this.document);
    }

    protected void processPages(PDPageTree pDPageTree) throws IOException {
        PDOutlineItem pDOutlineItem;
        PDOutlineItem pDOutlineItem2 = this.startBookmark;
        PDPage pDPageFindDestinationPage = pDOutlineItem2 == null ? null : pDOutlineItem2.findDestinationPage(this.document);
        if (pDPageFindDestinationPage != null) {
            this.startBookmarkPageNumber = pDPageTree.indexOf(pDPageFindDestinationPage) + 1;
        } else {
            this.startBookmarkPageNumber = -1;
        }
        PDOutlineItem pDOutlineItem3 = this.endBookmark;
        PDPage pDPageFindDestinationPage2 = pDOutlineItem3 != null ? pDOutlineItem3.findDestinationPage(this.document) : null;
        if (pDPageFindDestinationPage2 != null) {
            this.endBookmarkPageNumber = pDPageTree.indexOf(pDPageFindDestinationPage2) + 1;
        } else {
            this.endBookmarkPageNumber = -1;
        }
        if (this.startBookmarkPageNumber == -1 && (pDOutlineItem = this.startBookmark) != null && this.endBookmarkPageNumber == -1 && this.endBookmark != null && pDOutlineItem.getCOSObject() == this.endBookmark.getCOSObject()) {
            this.startBookmarkPageNumber = 0;
            this.endBookmarkPageNumber = 0;
        }
        for (PDPage pDPage : pDPageTree) {
            this.currentPageNo++;
            if (pDPage.hasContents()) {
                processPage(pDPage);
            }
        }
    }

    @Override
    public void processPage(PDPage pDPage) throws IOException {
        int i = this.currentPageNo;
        if (i < this.startPage || i > this.endPage) {
            return;
        }
        int i2 = this.startBookmarkPageNumber;
        if (i2 == -1 || i >= i2) {
            int i3 = this.endBookmarkPageNumber;
            if (i3 == -1 || i <= i3) {
                startPage(pDPage);
                int size = 1;
                if (this.shouldSeparateByBeads) {
                    fillBeadRectangles(pDPage);
                    size = 1 + (this.beadRectangles.size() * 2);
                }
                int size2 = this.charactersByArticle.size();
                this.charactersByArticle.ensureCapacity(size);
                int iMax = Math.max(size, size2);
                for (int i4 = 0; i4 < iMax; i4++) {
                    if (i4 < size2) {
                        this.charactersByArticle.get(i4).clear();
                    } else if (size < size2) {
                        this.charactersByArticle.remove(i4);
                    } else {
                        this.charactersByArticle.add(new ArrayList());
                    }
                }
                this.characterListMapping.clear();
                super.processPage(pDPage);
                writePage();
                endPage(pDPage);
            }
        }
    }

    private void fillBeadRectangles(PDPage pDPage) {
        this.beadRectangles = new ArrayList();
        for (PDThreadBead pDThreadBead : pDPage.getThreadBeads()) {
            if (pDThreadBead == null) {
                this.beadRectangles.add(null);
            } else {
                PDRectangle rectangle = pDThreadBead.getRectangle();
                PDRectangle mediaBox = pDPage.getMediaBox();
                float upperRightY = mediaBox.getUpperRightY() - rectangle.getLowerLeftY();
                rectangle.setLowerLeftY(mediaBox.getUpperRightY() - rectangle.getUpperRightY());
                rectangle.setUpperRightY(upperRightY);
                PDRectangle cropBox = pDPage.getCropBox();
                if (cropBox.getLowerLeftX() != 0.0f || cropBox.getLowerLeftY() != 0.0f) {
                    rectangle.setLowerLeftX(rectangle.getLowerLeftX() - cropBox.getLowerLeftX());
                    rectangle.setLowerLeftY(rectangle.getLowerLeftY() - cropBox.getLowerLeftY());
                    rectangle.setUpperRightX(rectangle.getUpperRightX() - cropBox.getLowerLeftX());
                    rectangle.setUpperRightY(rectangle.getUpperRightY() - cropBox.getLowerLeftY());
                }
                this.beadRectangles.add(rectangle);
            }
        }
    }

    protected void startArticle() throws IOException {
        startArticle(true);
    }

    protected void startArticle(boolean z) throws IOException {
        this.output.write(getArticleStart());
    }

    protected void endArticle() throws IOException {
        this.output.write(getArticleEnd());
    }

    protected void writePage() throws IOException {
        float x;
        float y;
        float width;
        float height;
        float spacingTolerance;
        float f;
        if (this.charactersByArticle.size() > 0) {
            writePageStart();
        }
        Iterator<List<TextPosition>> it = this.charactersByArticle.iterator();
        PositionWrapper positionWrapper = null;
        PositionWrapper positionWrapperHandleLineSeparation = null;
        float f2 = -3.4028235E38f;
        float f3 = -1.0f;
        float f4 = -1.0f;
        float fMax = -1.0f;
        float fMin = Float.MAX_VALUE;
        boolean z = true;
        while (it.hasNext()) {
            List<TextPosition> next = it.next();
            if (getSortByPosition()) {
                TextPositionComparator textPositionComparator = new TextPositionComparator();
                if (useCustomQuickSort) {
                    QuickSort.sort(next, textPositionComparator);
                } else {
                    Collections.sort(next, textPositionComparator);
                }
            }
            next.iterator();
            startArticle();
            ArrayList arrayList = new ArrayList();
            float f5 = -1.0f;
            boolean z2 = true;
            for (TextPosition textPosition : next) {
                PositionWrapper positionWrapper2 = new PositionWrapper(textPosition);
                String unicode = textPosition.getUnicode();
                if (positionWrapper != null && (textPosition.getFont() != positionWrapper.getTextPosition().getFont() || textPosition.getFontSize() != positionWrapper.getTextPosition().getFontSize())) {
                    f5 = -1.0f;
                }
                if (getSortByPosition()) {
                    x = textPosition.getXDirAdj();
                    y = textPosition.getYDirAdj();
                    width = textPosition.getWidthDirAdj();
                    height = textPosition.getHeightDir();
                } else {
                    x = textPosition.getX();
                    y = textPosition.getY();
                    width = textPosition.getWidth();
                    height = textPosition.getHeight();
                }
                float f6 = fMin;
                float f7 = height;
                Iterator<List<TextPosition>> it2 = it;
                int length = textPosition.getIndividualWidths().length;
                float widthOfSpace = textPosition.getWidthOfSpace();
                if (widthOfSpace == 0.0f || Float.isNaN(widthOfSpace)) {
                    spacingTolerance = Float.MAX_VALUE;
                } else if (f4 < 0.0f) {
                    spacingTolerance = getSpacingTolerance() * widthOfSpace;
                } else {
                    spacingTolerance = ((widthOfSpace + f4) / 2.0f) * getSpacingTolerance();
                }
                f5 = f5 < 0.0f ? width / length : (f5 + (width / length)) / 2.0f;
                float averageCharTolerance = getAverageCharTolerance() * f5;
                float f8 = f3 != -1.0f ? averageCharTolerance > spacingTolerance ? f3 + spacingTolerance : f3 + averageCharTolerance : -3.4028235E38f;
                if (positionWrapper != null) {
                    if (z2) {
                        positionWrapper.setArticleStart();
                        z2 = false;
                    }
                    if (overlap(y, f7, f2, fMax)) {
                        f = f6;
                    } else {
                        writeLine(normalize(arrayList));
                        arrayList.clear();
                        positionWrapperHandleLineSeparation = handleLineSeparation(positionWrapper2, positionWrapper, positionWrapperHandleLineSeparation, fMax);
                        fMax = -1.0f;
                        f2 = -3.4028235E38f;
                        f8 = -3.4028235E38f;
                        f = Float.MAX_VALUE;
                    }
                    if (f8 != -3.4028235E38f && f8 < x && positionWrapper.getTextPosition().getUnicode() != null && !positionWrapper.getTextPosition().getUnicode().endsWith(" ")) {
                        arrayList.add(LineItem.getWordSeparator());
                    }
                } else {
                    f = f6;
                }
                if (y >= f2) {
                    f2 = y;
                }
                f3 = x + width;
                if (unicode != null) {
                    if (z && positionWrapper == null) {
                        writeParagraphStart();
                    }
                    arrayList.add(new LineItem(textPosition));
                }
                fMax = Math.max(fMax, f7);
                fMin = Math.min(f, y - f7);
                if (z) {
                    positionWrapper2.setParagraphStart();
                    positionWrapper2.setLineStart();
                    positionWrapperHandleLineSeparation = positionWrapper2;
                    z = false;
                }
                positionWrapper = positionWrapper2;
                it = it2;
                f4 = widthOfSpace;
            }
            Iterator<List<TextPosition>> it3 = it;
            float f9 = fMin;
            if (arrayList.size() > 0) {
                writeLine(normalize(arrayList));
                writeParagraphEnd();
            }
            endArticle();
            it = it3;
            fMin = f9;
        }
        writePageEnd();
    }

    private boolean overlap(float f, float f2, float f3, float f4) {
        return within(f, f3, 0.1f) || (f3 <= f && f3 >= f - f2) || (f <= f3 && f >= f3 - f4);
    }

    protected void writeLineSeparator() throws IOException {
        this.output.write(getLineSeparator());
    }

    protected void writeWordSeparator() throws IOException {
        this.output.write(getWordSeparator());
    }

    protected void writeCharacters(TextPosition textPosition) throws IOException {
        this.output.write(textPosition.getUnicode());
    }

    protected void writeString(String str, List<TextPosition> list) throws IOException {
        writeString(str);
    }

    protected void writeString(String str) throws IOException {
        this.output.write(str);
    }

    @Override
    protected void processTextPosition(TextPosition textPosition) {
        boolean z;
        int i;
        int i2;
        int i3;
        boolean z2;
        int size = 0;
        if (this.suppressDuplicateOverlappingText) {
            String unicode = textPosition.getUnicode();
            float x = textPosition.getX();
            float y = textPosition.getY();
            TreeMap<Float, TreeSet<Float>> treeMap = this.characterListMapping.get(unicode);
            if (treeMap == null) {
                treeMap = new TreeMap<>();
                this.characterListMapping.put(unicode, treeMap);
            }
            float width = (textPosition.getWidth() / unicode.length()) / 3.0f;
            Iterator<TreeSet<Float>> it = treeMap.subMap(Float.valueOf(x - width), Float.valueOf(x + width)).values().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!it.next().subSet(Float.valueOf(y - width), Float.valueOf(y + width)).isEmpty()) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
            if (z2) {
                z = false;
            } else {
                TreeSet<Float> treeSet = treeMap.get(Float.valueOf(x));
                if (treeSet == null) {
                    treeSet = new TreeSet<>();
                    treeMap.put(Float.valueOf(x), treeSet);
                }
                treeSet.add(Float.valueOf(y));
                z = true;
            }
        } else {
            z = true;
        }
        if (z) {
            float x2 = textPosition.getX();
            float y2 = textPosition.getY();
            if (this.shouldSeparateByBeads) {
                int i4 = -1;
                i = -1;
                i2 = -1;
                i3 = -1;
                for (int i5 = 0; i5 < this.beadRectangles.size() && i4 == -1; i5++) {
                    PDRectangle pDRectangle = this.beadRectangles.get(i5);
                    if (pDRectangle == null) {
                        i4 = 0;
                    } else if (pDRectangle.contains(x2, y2)) {
                        i4 = (i5 * 2) + 1;
                    } else if ((x2 < pDRectangle.getLowerLeftX() || y2 < pDRectangle.getUpperRightY()) && i == -1) {
                        i = i5 * 2;
                    } else if (x2 < pDRectangle.getLowerLeftX() && i2 == -1) {
                        i2 = i5 * 2;
                    } else if (y2 < pDRectangle.getUpperRightY() && i3 == -1) {
                        i3 = i5 * 2;
                    }
                }
                size = i4;
            } else {
                i = -1;
                i2 = -1;
                i3 = -1;
            }
            if (size == -1) {
                if (i != -1) {
                    size = i;
                } else if (i2 != -1) {
                    size = i2;
                } else {
                    size = i3 != -1 ? i3 : this.charactersByArticle.size() - 1;
                }
            }
            List<TextPosition> list = this.charactersByArticle.get(size);
            if (list.isEmpty()) {
                list.add(textPosition);
                return;
            }
            TextPosition textPosition2 = list.get(list.size() - 1);
            if (textPosition.isDiacritic() && textPosition2.contains(textPosition)) {
                textPosition2.mergeDiacritic(textPosition);
                return;
            }
            if (textPosition2.isDiacritic() && textPosition.contains(textPosition2)) {
                textPosition.mergeDiacritic(textPosition2);
                list.remove(list.size() - 1);
                list.add(textPosition);
                return;
            }
            list.add(textPosition);
        }
    }

    public int getStartPage() {
        return this.startPage;
    }

    public void setStartPage(int i) {
        this.startPage = i;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void setEndPage(int i) {
        this.endPage = i;
    }

    public void setLineSeparator(String str) {
        this.lineSeparator = str;
    }

    public String getLineSeparator() {
        return this.lineSeparator;
    }

    public String getWordSeparator() {
        return this.wordSeparator;
    }

    public void setWordSeparator(String str) {
        this.wordSeparator = str;
    }

    public boolean getSuppressDuplicateOverlappingText() {
        return this.suppressDuplicateOverlappingText;
    }

    protected int getCurrentPageNo() {
        return this.currentPageNo;
    }

    protected Writer getOutput() {
        return this.output;
    }

    protected List<List<TextPosition>> getCharactersByArticle() {
        return this.charactersByArticle;
    }

    public void setSuppressDuplicateOverlappingText(boolean z) {
        this.suppressDuplicateOverlappingText = z;
    }

    public boolean getSeparateByBeads() {
        return this.shouldSeparateByBeads;
    }

    public void setShouldSeparateByBeads(boolean z) {
        this.shouldSeparateByBeads = z;
    }

    public PDOutlineItem getEndBookmark() {
        return this.endBookmark;
    }

    public void setEndBookmark(PDOutlineItem pDOutlineItem) {
        this.endBookmark = pDOutlineItem;
    }

    public PDOutlineItem getStartBookmark() {
        return this.startBookmark;
    }

    public void setStartBookmark(PDOutlineItem pDOutlineItem) {
        this.startBookmark = pDOutlineItem;
    }

    public boolean getAddMoreFormatting() {
        return this.addMoreFormatting;
    }

    public void setAddMoreFormatting(boolean z) {
        this.addMoreFormatting = z;
    }

    public boolean getSortByPosition() {
        return this.sortByPosition;
    }

    public void setSortByPosition(boolean z) {
        this.sortByPosition = z;
    }

    public float getSpacingTolerance() {
        return this.spacingTolerance;
    }

    public void setSpacingTolerance(float f) {
        this.spacingTolerance = f;
    }

    public float getAverageCharTolerance() {
        return this.averageCharTolerance;
    }

    public void setAverageCharTolerance(float f) {
        this.averageCharTolerance = f;
    }

    public float getIndentThreshold() {
        return this.indentThreshold;
    }

    public void setIndentThreshold(float f) {
        this.indentThreshold = f;
    }

    public float getDropThreshold() {
        return this.dropThreshold;
    }

    public void setDropThreshold(float f) {
        this.dropThreshold = f;
    }

    public String getParagraphStart() {
        return this.paragraphStart;
    }

    public void setParagraphStart(String str) {
        this.paragraphStart = str;
    }

    public String getParagraphEnd() {
        return this.paragraphEnd;
    }

    public void setParagraphEnd(String str) {
        this.paragraphEnd = str;
    }

    public String getPageStart() {
        return this.pageStart;
    }

    public void setPageStart(String str) {
        this.pageStart = str;
    }

    public String getPageEnd() {
        return this.pageEnd;
    }

    public void setPageEnd(String str) {
        this.pageEnd = str;
    }

    public String getArticleStart() {
        return this.articleStart;
    }

    public void setArticleStart(String str) {
        this.articleStart = str;
    }

    public String getArticleEnd() {
        return this.articleEnd;
    }

    public void setArticleEnd(String str) {
        this.articleEnd = str;
    }

    private PositionWrapper handleLineSeparation(PositionWrapper positionWrapper, PositionWrapper positionWrapper2, PositionWrapper positionWrapper3, float f) throws IOException {
        positionWrapper.setLineStart();
        isParagraphSeparation(positionWrapper, positionWrapper2, positionWrapper3, f);
        if (positionWrapper.isParagraphStart()) {
            if (positionWrapper2.isArticleStart()) {
                if (positionWrapper2.isLineStart()) {
                    writeLineSeparator();
                }
                writeParagraphStart();
            } else {
                writeLineSeparator();
                writeParagraphSeparator();
            }
        } else {
            writeLineSeparator();
        }
        return positionWrapper;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void isParagraphSeparation(com.tom_roush.pdfbox.text.PDFTextStripper.PositionWrapper r6, com.tom_roush.pdfbox.text.PDFTextStripper.PositionWrapper r7, com.tom_roush.pdfbox.text.PDFTextStripper.PositionWrapper r8, float r9) {
        /*
            r5 = this;
            r0 = 1
            if (r8 != 0) goto L5
            goto L9f
        L5:
            com.tom_roush.pdfbox.text.TextPosition r1 = r6.getTextPosition()
            float r1 = r1.getYDirAdj()
            com.tom_roush.pdfbox.text.TextPosition r7 = r7.getTextPosition()
            float r7 = r7.getYDirAdj()
            float r1 = r1 - r7
            float r7 = java.lang.Math.abs(r1)
            float r1 = r5.getDropThreshold()
            float r9 = r5.multiplyFloat(r1, r9)
            com.tom_roush.pdfbox.text.TextPosition r1 = r6.getTextPosition()
            float r1 = r1.getXDirAdj()
            com.tom_roush.pdfbox.text.TextPosition r2 = r8.getTextPosition()
            float r2 = r2.getXDirAdj()
            float r1 = r1 - r2
            float r2 = r5.getIndentThreshold()
            com.tom_roush.pdfbox.text.TextPosition r3 = r6.getTextPosition()
            float r3 = r3.getWidthOfSpace()
            float r2 = r5.multiplyFloat(r2, r3)
            com.tom_roush.pdfbox.text.TextPosition r3 = r6.getTextPosition()
            float r3 = r3.getWidth()
            r4 = 1048576000(0x3e800000, float:0.25)
            float r3 = r5.multiplyFloat(r4, r3)
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 <= 0) goto L56
            goto L9f
        L56:
            int r7 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r7 <= 0) goto L65
            boolean r7 = r8.isParagraphStart()
            if (r7 != 0) goto L61
            goto L9f
        L61:
            r6.setHangingIndent()
            goto L9e
        L65:
            com.tom_roush.pdfbox.text.TextPosition r7 = r6.getTextPosition()
            float r7 = r7.getWidthOfSpace()
            float r7 = -r7
            int r7 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r7 >= 0) goto L79
            boolean r7 = r8.isParagraphStart()
            if (r7 != 0) goto L9e
            goto L9f
        L79:
            float r7 = java.lang.Math.abs(r1)
            int r7 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r7 >= 0) goto L9e
            boolean r7 = r8.isHangingIndent()
            if (r7 == 0) goto L8b
            r6.setHangingIndent()
            goto L9e
        L8b:
            boolean r7 = r8.isParagraphStart()
            if (r7 == 0) goto L9e
            java.util.regex.Pattern r7 = r5.matchListItemPattern(r8)
            if (r7 == 0) goto L9e
            java.util.regex.Pattern r8 = r5.matchListItemPattern(r6)
            if (r7 != r8) goto L9e
            goto L9f
        L9e:
            r0 = 0
        L9f:
            if (r0 == 0) goto La4
            r6.setParagraphStart()
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.text.PDFTextStripper.isParagraphSeparation(com.tom_roush.pdfbox.text.PDFTextStripper$PositionWrapper, com.tom_roush.pdfbox.text.PDFTextStripper$PositionWrapper, com.tom_roush.pdfbox.text.PDFTextStripper$PositionWrapper, float):void");
    }

    private float multiplyFloat(float f, float f2) {
        return Math.round((f * f2) * 1000.0f) / 1000.0f;
    }

    protected void writeParagraphSeparator() throws IOException {
        writeParagraphEnd();
        writeParagraphStart();
    }

    protected void writeParagraphStart() throws IOException {
        if (this.inParagraph) {
            writeParagraphEnd();
            this.inParagraph = false;
        }
        this.output.write(getParagraphStart());
        this.inParagraph = true;
    }

    protected void writeParagraphEnd() throws IOException {
        if (!this.inParagraph) {
            writeParagraphStart();
        }
        this.output.write(getParagraphEnd());
        this.inParagraph = false;
    }

    protected void writePageStart() throws IOException {
        this.output.write(getPageStart());
    }

    protected void writePageEnd() throws IOException {
        this.output.write(getPageEnd());
    }

    private Pattern matchListItemPattern(PositionWrapper positionWrapper) {
        return matchPattern(positionWrapper.getTextPosition().getUnicode(), getListItemPatterns());
    }

    protected void setListItemPatterns(List<Pattern> list) {
        this.listOfPatterns = list;
    }

    protected List<Pattern> getListItemPatterns() {
        if (this.listOfPatterns == null) {
            this.listOfPatterns = new ArrayList();
            for (String str : LIST_ITEM_EXPRESSIONS) {
                this.listOfPatterns.add(Pattern.compile(str));
            }
        }
        return this.listOfPatterns;
    }

    protected static Pattern matchPattern(String str, List<Pattern> list) {
        for (Pattern pattern : list) {
            if (pattern.matcher(str).matches()) {
                return pattern;
            }
        }
        return null;
    }

    private void writeLine(List<WordWithTextPositions> list) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WordWithTextPositions wordWithTextPositions = list.get(i);
            writeString(wordWithTextPositions.getText(), wordWithTextPositions.getTextPositions());
            if (i < size - 1) {
                writeWordSeparator();
            }
        }
    }

    private List<WordWithTextPositions> normalize(List<LineItem> list) {
        LinkedList linkedList = new LinkedList();
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        Iterator<LineItem> it = list.iterator();
        while (it.hasNext()) {
            sb = normalizeAdd(linkedList, sb, arrayList, it.next());
        }
        if (sb.length() > 0) {
            linkedList.add(createWord(sb.toString(), arrayList));
        }
        return linkedList;
    }

    private String handleDirection(String str) {
        Bidi bidi = new Bidi(str, -2);
        if (!bidi.isMixed() && bidi.getBaseLevel() == 0) {
            return str;
        }
        int runCount = bidi.getRunCount();
        byte[] bArr = new byte[runCount];
        Integer[] numArr = new Integer[runCount];
        for (int i = 0; i < runCount; i++) {
            bArr[i] = (byte) bidi.getRunLevel(i);
            numArr[i] = Integer.valueOf(i);
        }
        Bidi.reorderVisually(bArr, 0, numArr, 0, runCount);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < runCount; i2++) {
            int iIntValue = numArr[i2].intValue();
            int runStart = bidi.getRunStart(iIntValue);
            int runLimit = bidi.getRunLimit(iIntValue);
            if ((bArr[iIntValue] & 1) != 0) {
                while (true) {
                    runLimit--;
                    if (runLimit >= runStart) {
                        char cCharAt = str.charAt(runLimit);
                        if (Character.isMirrored(str.codePointAt(runLimit))) {
                            if (MIRRORING_CHAR_MAP.containsKey(Character.valueOf(cCharAt))) {
                                sb.append(MIRRORING_CHAR_MAP.get(Character.valueOf(cCharAt)));
                            } else {
                                sb.append(cCharAt);
                            }
                        } else {
                            sb.append(cCharAt);
                        }
                    }
                }
            } else {
                sb.append((CharSequence) str, runStart, runLimit);
            }
        }
        return sb.toString();
    }

    private static void parseBidiFile(InputStream inputStream) throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
        while (true) {
            String line = lineNumberReader.readLine();
            if (line == null) {
                return;
            }
            int iIndexOf = line.indexOf(35);
            if (iIndexOf != -1) {
                line = line.substring(0, iIndexOf);
            }
            if (line.length() >= 2) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                int iCountTokens = stringTokenizer.countTokens();
                Character[] chArr = new Character[iCountTokens];
                for (int i = 0; i < iCountTokens; i++) {
                    chArr[i] = Character.valueOf((char) Integer.parseInt(stringTokenizer.nextToken().trim(), 16));
                }
                if (iCountTokens == 2) {
                    MIRRORING_CHAR_MAP.put(chArr[0], chArr[1]);
                }
            }
        }
    }

    private WordWithTextPositions createWord(String str, List<TextPosition> list) {
        return new WordWithTextPositions(normalizeWord(str), list);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String normalizeWord(java.lang.String r7) {
        /*
            r6 = this;
            int r0 = r7.length()
            r1 = 0
            r2 = 0
            r3 = r2
        L7:
            if (r2 >= r0) goto L6a
            char r4 = r7.charAt(r2)
            r5 = 64256(0xfb00, float:9.0042E-41)
            if (r5 > r4) goto L17
            r5 = 65023(0xfdff, float:9.1117E-41)
            if (r4 <= r5) goto L21
        L17:
            r5 = 65136(0xfe70, float:9.1275E-41)
            if (r5 > r4) goto L67
            r5 = 65279(0xfeff, float:9.1475E-41)
            if (r4 > r5) goto L67
        L21:
            if (r1 != 0) goto L2a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r5 = r0 * 2
            r1.<init>(r5)
        L2a:
            java.lang.String r3 = r7.substring(r3, r2)
            r1.append(r3)
            r3 = 65010(0xfdf2, float:9.1098E-41)
            if (r4 != r3) goto L52
            if (r2 <= 0) goto L52
            int r3 = r2 + (-1)
            char r4 = r7.charAt(r3)
            r5 = 1575(0x627, float:2.207E-42)
            if (r4 == r5) goto L4b
            char r3 = r7.charAt(r3)
            r4 = 65165(0xfe8d, float:9.1316E-41)
            if (r3 != r4) goto L52
        L4b:
            java.lang.String r3 = "لله"
            r1.append(r3)
            goto L65
        L52:
            int r3 = r2 + 1
            java.lang.String r3 = r7.substring(r2, r3)
            java.text.Normalizer$Form r4 = java.text.Normalizer.Form.NFKC
            java.lang.String r3 = java.text.Normalizer.normalize(r3, r4)
            java.lang.String r3 = r3.trim()
            r1.append(r3)
        L65:
            int r3 = r2 + 1
        L67:
            int r2 = r2 + 1
            goto L7
        L6a:
            if (r1 != 0) goto L71
            java.lang.String r7 = r6.handleDirection(r7)
            return r7
        L71:
            java.lang.String r7 = r7.substring(r3, r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            java.lang.String r7 = r6.handleDirection(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.text.PDFTextStripper.normalizeWord(java.lang.String):java.lang.String");
    }

    private StringBuilder normalizeAdd(List<WordWithTextPositions> list, StringBuilder sb, List<TextPosition> list2, LineItem lineItem) {
        if (lineItem.isWordSeparator()) {
            list.add(createWord(sb.toString(), new ArrayList(list2)));
            StringBuilder sb2 = new StringBuilder();
            list2.clear();
            return sb2;
        }
        TextPosition textPosition = lineItem.getTextPosition();
        sb.append(textPosition.getUnicode());
        list2.add(textPosition);
        return sb;
    }

    private static final class LineItem {
        public static LineItem WORD_SEPARATOR = new LineItem();
        private final TextPosition textPosition;

        public static LineItem getWordSeparator() {
            return WORD_SEPARATOR;
        }

        private LineItem() {
            this.textPosition = null;
        }

        LineItem(TextPosition textPosition) {
            this.textPosition = textPosition;
        }

        public TextPosition getTextPosition() {
            return this.textPosition;
        }

        public boolean isWordSeparator() {
            return this.textPosition == null;
        }
    }

    private static final class WordWithTextPositions {
        String text;
        List<TextPosition> textPositions;

        WordWithTextPositions(String str, List<TextPosition> list) {
            this.text = str;
            this.textPositions = list;
        }

        public String getText() {
            return this.text;
        }

        public List<TextPosition> getTextPositions() {
            return this.textPositions;
        }
    }

    private static final class PositionWrapper {
        private TextPosition position;
        private boolean isLineStart = false;
        private boolean isParagraphStart = false;
        private boolean isPageBreak = false;
        private boolean isHangingIndent = false;
        private boolean isArticleStart = false;

        PositionWrapper(TextPosition textPosition) {
            this.position = textPosition;
        }

        public TextPosition getTextPosition() {
            return this.position;
        }

        public boolean isLineStart() {
            return this.isLineStart;
        }

        public void setLineStart() {
            this.isLineStart = true;
        }

        public boolean isParagraphStart() {
            return this.isParagraphStart;
        }

        public void setParagraphStart() {
            this.isParagraphStart = true;
        }

        public boolean isArticleStart() {
            return this.isArticleStart;
        }

        public void setArticleStart() {
            this.isArticleStart = true;
        }

        public boolean isPageBreak() {
            return this.isPageBreak;
        }

        public void setPageBreak() {
            this.isPageBreak = true;
        }

        public boolean isHangingIndent() {
            return this.isHangingIndent;
        }

        public void setHangingIndent() {
            this.isHangingIndent = true;
        }
    }
}
