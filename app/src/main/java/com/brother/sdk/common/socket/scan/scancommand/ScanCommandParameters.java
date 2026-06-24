package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.scanner.ScanPaperSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScanCommandParameters {

    public static class ScanSize {
        public int Height;
        public int Width;
    }

    public static class ScanArea {
        public int XEnd;
        public int XStart;
        public int YEnd;
        public int YStart;

        ScanArea() {
        }
    }

    public static class ScannerInformation {
        public int mMaxHeightMM;
        public int mMaxHeightPixel;
        public int mMaxWidthMM;
        public int mMaxWidthPixel;
        public ScanResolution mResolution;
        public ScanPaperSource mSource;

        ScannerInformation() {
        }
    }

    public static class ScanResolution {
        public int Height;
        public int Width;

        public ScanResolution(int i, int i2) {
            this.Width = i;
            this.Height = i2;
        }
    }

    public static class ScanDocumentSideMargin {
        public double Width = 3.0d;
        public double Height = 3.0d;

        ScanDocumentSideMargin() {
        }
    }

    public static class Point {

        public int f519x;

        public int f520y;

        Point(int i, int i2) {
            this.f519x = i;
            this.f520y = i2;
        }
    }

    public enum ProtocolGeneration {
        ScanProtocol_Undefined(-1),
        ScanProtocol_Pre1999(0),
        ScanProtocol_2000(1),
        ScanProtocol_2001(2),
        ScanProtocol_2003(3),
        ScanProtocol_2005(4),
        ScanProtocol_2008(5),
        ScanProtocol_2009(6),
        ScanProtocol_2010(7),
        ScanProtocol_2011(8),
        ScanProtocol_2012(9),
        ScanProtocol_2013(10),
        ScanProtocol_2014(11),
        ScanProtocol_2014_2(12);

        private final int value;

        ProtocolGeneration(int i) {
            this.value = i;
        }

        public int toValue() {
            return this.value;
        }

        public static ProtocolGeneration fromValue(int i) {
            for (ProtocolGeneration protocolGeneration : values()) {
                if (protocolGeneration.toValue() == i) {
                    return protocolGeneration;
                }
            }
            return ScanProtocol_Undefined;
        }
    }

    public enum DataCompression {
        NONE(0),
        MH(1),
        RLENGTH(2),
        JPEG(3);

        private final int value;

        DataCompression(int i) {
            this.value = i;
        }

        public int toValue() {
            return this.value;
        }

        public static DataCompression fromValue(int i) {
            for (DataCompression dataCompression : values()) {
                if (dataCompression.toValue() == i) {
                    return dataCompression;
                }
            }
            return null;
        }
    }

    public enum JpegCompression {
        MAX(0),
        MID(1),
        MIN(1);

        private final int value;

        JpegCompression(int i) {
            this.value = i;
        }

        public int toValue() {
            return this.value;
        }

        public static JpegCompression fromValue(int i) {
            for (JpegCompression jpegCompression : values()) {
                if (jpegCompression.toValue() == i) {
                    return jpegCompression;
                }
            }
            return null;
        }
    }

    public enum CarrierSheet {
        NoSupport(0),
        ADS(1);

        private final int value;

        CarrierSheet(int i) {
            this.value = i;
        }

        public int toValue() {
            return this.value;
        }

        public static CarrierSheet fromValue(int i) {
            for (CarrierSheet carrierSheet : values()) {
                if (carrierSheet.toValue() == i) {
                    return carrierSheet;
                }
            }
            return null;
        }
    }

    public static abstract class EdgeCoordinate {
        private static final EdgeCoordinate[] $VALUES;
        public static final EdgeCoordinate ADSEdge;
        public static final EdgeCoordinate NormalEdge;
        private final int value;

        public abstract float getEdgeValue();

        public static EdgeCoordinate valueOf(String str) {
            return (EdgeCoordinate) Enum.valueOf(EdgeCoordinate.class, str);
        }

        public static EdgeCoordinate[] values() {
            return (EdgeCoordinate[]) $VALUES.clone();
        }

        static {
            int i = 0;
            EdgeCoordinate edgeCoordinate = new EdgeCoordinate("NormalEdge", i, i) {
                @Override
                public float getEdgeValue() {
                    return 0.0f;
                }
            };
            NormalEdge = edgeCoordinate;
            int i2 = 1;
            EdgeCoordinate edgeCoordinate2 = new EdgeCoordinate("ADSEdge", i2, i2) {
                @Override
                public float getEdgeValue() {
                    return 11.28f;
                }
            };
            ADSEdge = edgeCoordinate2;
            $VALUES = new EdgeCoordinate[]{edgeCoordinate, edgeCoordinate2};
        }

        private EdgeCoordinate(String str, int i, int i2) {
            this.value = i2;
        }

        public int toValue() {
            return this.value;
        }

        public static EdgeCoordinate fromValue(int i) {
            for (EdgeCoordinate edgeCoordinate : values()) {
                if (edgeCoordinate.toValue() == i) {
                    return edgeCoordinate;
                }
            }
            return null;
        }
    }

    public static abstract class ScanMode {
        private static final ScanMode[] $VALUES;
        public static final ScanMode C256;
        public static final ScanMode CGRAY;
        public static final ScanMode ERRDIF;
        public static final ScanMode GRAY256;
        public static final ScanMode TEXT;
        private final int value;

        abstract ScanImageDataFormat convertToImageFormat(boolean z);

        public static ScanMode valueOf(String str) {
            return (ScanMode) Enum.valueOf(ScanMode.class, str);
        }

        public static ScanMode[] values() {
            return (ScanMode[]) $VALUES.clone();
        }

        static {
            int i = 0;
            ScanMode scanMode = new ScanMode("TEXT", i, i) {
                @Override
                public ScanImageDataFormat convertToImageFormat(boolean z) {
                    return ScanImageDataFormat.Bitmap_BlackAndWhite;
                }
            };
            TEXT = scanMode;
            int i2 = 1;
            ScanMode scanMode2 = new ScanMode("ERRDIF", i2, i2) {
                @Override
                public ScanImageDataFormat convertToImageFormat(boolean z) {
                    return ScanImageDataFormat.Bitmap_BlackAndWhite;
                }
            };
            ERRDIF = scanMode2;
            int i3 = 2;
            ScanMode scanMode3 = new ScanMode("GRAY256", i3, i3) {
                @Override
                public ScanImageDataFormat convertToImageFormat(boolean z) {
                    return ScanImageDataFormat.Bitmap_Gray8;
                }
            };
            GRAY256 = scanMode3;
            int i4 = 3;
            ScanMode scanMode4 = new ScanMode("C256", i4, i4) {
                @Override
                public ScanImageDataFormat convertToImageFormat(boolean z) {
                    return ScanImageDataFormat.Bitmap_RGB8;
                }
            };
            C256 = scanMode4;
            int i5 = 4;
            ScanMode scanMode5 = new ScanMode("CGRAY", i5, i5) {
                @Override
                public ScanImageDataFormat convertToImageFormat(boolean z) {
                    if (z) {
                        return ScanImageDataFormat.Bitmap_BGR24;
                    }
                    return ScanImageDataFormat.Bitmap_RGB24;
                }
            };
            CGRAY = scanMode5;
            $VALUES = new ScanMode[]{scanMode, scanMode2, scanMode3, scanMode4, scanMode5};
        }

        private ScanMode(String str, int i, int i2) {
            this.value = i2;
        }

        public int toValue() {
            return this.value;
        }

        static ScanMode fromValue(int i) {
            for (ScanMode scanMode : values()) {
                if (scanMode.toValue() == i) {
                    return scanMode;
                }
            }
            return null;
        }
    }

    public enum ScanImageDataFormat {
        Jpeg(0),
        Bitmap_BlackAndWhite(1),
        Bitmap_Gray8(2),
        Bitmap_RGB8(3),
        Bitmap_RGB24(4),
        Bitmap_BGR24(5),
        Bitmap_R_G_B_24(6);

        private final int value;

        ScanImageDataFormat(int i) {
            this.value = i;
        }

        public int toValue() {
            return this.value;
        }

        static ScanImageDataFormat fromValue(int i) {
            for (ScanImageDataFormat scanImageDataFormat : values()) {
                if (scanImageDataFormat.toValue() == i) {
                    return scanImageDataFormat;
                }
            }
            return null;
        }
    }

    public enum ErrorDetect {
        NoDetect(0),
        MultiPaperFeedDetect(1),
        LessThanB4InADF(2);

        private final int value;

        ErrorDetect(int i) {
            this.value = i;
        }

        static int calculateSum(List<ErrorDetect> list) {
            int i = 0;
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (ErrorDetect errorDetect : list) {
                    if (!arrayList.contains(errorDetect)) {
                        arrayList.add(errorDetect);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    i += ((ErrorDetect) it.next()).value;
                }
            }
            return i;
        }

        static ErrorDetect fromValue(int i) {
            for (ErrorDetect errorDetect : values()) {
                if (errorDetect.value == i) {
                    return errorDetect;
                }
            }
            return null;
        }
    }
}
