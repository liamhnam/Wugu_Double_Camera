package com.tom_roush.pdfbox.pdmodel.common;

import com.p020hp.jipp.model.MaterialAmountUnit;
import com.p020hp.jipp.model.Media;
import com.p020hp.jipp.model.MediaType;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class PDPageLabels implements COSObjectable {
    private PDDocument doc;
    private Map<Integer, PDPageLabelRange> labels;

    private interface LabelHandler {
        void newLabel(int i, String str);
    }

    public PDPageLabels(PDDocument pDDocument) {
        this.labels = new TreeMap();
        this.doc = pDDocument;
        PDPageLabelRange pDPageLabelRange = new PDPageLabelRange();
        pDPageLabelRange.setStyle("D");
        this.labels.put(0, pDPageLabelRange);
    }

    public PDPageLabels(PDDocument pDDocument, COSDictionary cOSDictionary) throws IOException {
        this(pDDocument);
        if (cOSDictionary == null) {
            return;
        }
        findLabels(new PDNumberTreeNode(cOSDictionary, COSDictionary.class));
    }

    private void findLabels(PDNumberTreeNode pDNumberTreeNode) throws IOException {
        if (pDNumberTreeNode.getKids() != null) {
            Iterator<PDNumberTreeNode> it = pDNumberTreeNode.getKids().iterator();
            while (it.hasNext()) {
                findLabels(it.next());
            }
        } else if (pDNumberTreeNode.getNumbers() != null) {
            for (Map.Entry<Integer, COSObjectable> entry : pDNumberTreeNode.getNumbers().entrySet()) {
                if (entry.getKey().intValue() >= 0) {
                    this.labels.put(entry.getKey(), new PDPageLabelRange((COSDictionary) entry.getValue()));
                }
            }
        }
    }

    public int getPageRangeCount() {
        return this.labels.size();
    }

    public PDPageLabelRange getPageLabelRange(int i) {
        return this.labels.get(Integer.valueOf(i));
    }

    public void setLabelItem(int i, PDPageLabelRange pDPageLabelRange) {
        if (i < 0) {
            throw new IllegalArgumentException("startPage parameter of setLabelItem may not be < 0");
        }
        this.labels.put(Integer.valueOf(i), pDPageLabelRange);
    }

    @Override
    public COSBase getCOSObject() {
        COSDictionary cOSDictionary = new COSDictionary();
        COSArray cOSArray = new COSArray();
        for (Map.Entry<Integer, PDPageLabelRange> entry : this.labels.entrySet()) {
            cOSArray.add((COSBase) COSInteger.get(entry.getKey().intValue()));
            cOSArray.add(entry.getValue());
        }
        cOSDictionary.setItem(COSName.NUMS, (COSBase) cOSArray);
        return cOSDictionary;
    }

    public Map<String, Integer> getPageIndicesByLabels() {
        final HashMap map = new HashMap(this.doc.getNumberOfPages());
        computeLabels(new LabelHandler() {
            @Override
            public void newLabel(int i, String str) {
                map.put(str, Integer.valueOf(i));
            }
        });
        return map;
    }

    public String[] getLabelsByPageIndices() {
        final String[] strArr = new String[this.doc.getNumberOfPages()];
        computeLabels(new LabelHandler() {
            @Override
            public void newLabel(int i, String str) {
                if (i < PDPageLabels.this.doc.getNumberOfPages()) {
                    strArr[i] = str;
                }
            }
        });
        return strArr;
    }

    private void computeLabels(LabelHandler labelHandler) {
        Iterator<Map.Entry<Integer, PDPageLabelRange>> it = this.labels.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<Integer, PDPageLabelRange> next = it.next();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry<Integer, PDPageLabelRange> next2 = it.next();
                LabelGenerator labelGenerator = new LabelGenerator(next.getValue(), next2.getKey().intValue() - next.getKey().intValue());
                while (labelGenerator.hasNext()) {
                    labelHandler.newLabel(i, labelGenerator.next());
                    i++;
                }
                next = next2;
            }
            LabelGenerator labelGenerator2 = new LabelGenerator(next.getValue(), this.doc.getNumberOfPages() - next.getKey().intValue());
            while (labelGenerator2.hasNext()) {
                labelHandler.newLabel(i, labelGenerator2.next());
                i++;
            }
        }
    }

    private static class LabelGenerator implements Iterator<String> {
        private static final String[][] ROMANS = {new String[]{"", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix"}, new String[]{"", "x", "xx", "xxx", "xl", MaterialAmountUnit.f721l, "lx", "lxx", "lxxx", "xc"}, new String[]{"", Media.f727c, MqttCmdEnum.S2C_CLEAR_TEMP, "ccc", MediaType.f731cd, Media.f728d, "dc", "dcc", "dccc", "cm"}};
        private int currentPage = 0;
        private final PDPageLabelRange labelInfo;
        private final int numPages;

        LabelGenerator(PDPageLabelRange pDPageLabelRange, int i) {
            this.labelInfo = pDPageLabelRange;
            this.numPages = i;
        }

        @Override
        public boolean hasNext() {
            return this.currentPage < this.numPages;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            StringBuilder sb = new StringBuilder();
            if (this.labelInfo.getPrefix() != null) {
                String prefix = this.labelInfo.getPrefix();
                while (prefix.lastIndexOf(0) != -1) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
                sb.append(prefix);
            }
            if (this.labelInfo.getStyle() != null) {
                sb.append(getNumber(this.labelInfo.getStart() + this.currentPage, this.labelInfo.getStyle()));
            }
            this.currentPage++;
            return sb.toString();
        }

        private String getNumber(int i, String str) {
            if ("D".equals(str)) {
                return Integer.toString(i);
            }
            if ("a".equals(str)) {
                return makeLetterLabel(i);
            }
            if ("A".equals(str)) {
                return makeLetterLabel(i).toUpperCase();
            }
            if (PDPageLabelRange.STYLE_ROMAN_LOWER.equals(str)) {
                return makeRomanLabel(i);
            }
            if ("R".equals(str)) {
                return makeRomanLabel(i).toUpperCase();
            }
            return Integer.toString(i);
        }

        private static String makeRomanLabel(int i) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < 3 && i > 0; i2++) {
                sb.insert(0, ROMANS[i2][i % 10]);
                i /= 10;
            }
            for (int i3 = 0; i3 < i; i3++) {
                sb.insert(0, 'm');
            }
            return sb.toString();
        }

        private static String makeLetterLabel(int i) {
            StringBuilder sb = new StringBuilder();
            int i2 = i / 26;
            int i3 = i % 26;
            int iSignum = i2 + Integer.signum(i3);
            int iSignum2 = i3 + ((1 - Integer.signum(i3)) * 26) + 64;
            for (int i4 = 0; i4 < iSignum; i4++) {
                sb.appendCodePoint(iSignum2);
            }
            return sb.toString();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
