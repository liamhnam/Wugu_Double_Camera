package com.brother.sdk.common.device.printer;

import com.brother.sdk.common.device.MediaSize;

public enum PrintMargin {
    Normal {
        @Override
        public PrintableArea getPrintableArea(MediaSize mediaSize, PrinterModelType printerModelType) {
            int i = C06903.f494x2dd1d739[printerModelType.ordinal()];
            double d = ((i == 1 || i == 2 || i == 3) ? 0.16811d : i != 4 ? 0.0d : 0.11811d) * 2.0d;
            return new PrintableArea(mediaSize.width - d, mediaSize.height - d);
        }
    },
    Borderless {
        @Override
        public PrintableArea getPrintableArea(MediaSize mediaSize, PrinterModelType printerModelType) {
            int i = C06903.f494x2dd1d739[printerModelType.ordinal()];
            double d = ((i == 1 || i == 2 || i == 3) ? -0.15d : i != 4 ? 0.0d : -0.1d) * 2.0d;
            return new PrintableArea(mediaSize.width - d, mediaSize.height - d);
        }
    };

    public abstract PrintableArea getPrintableArea(MediaSize mediaSize, PrinterModelType printerModelType);

    static class C06903 {

        static final int[] f494x2dd1d739;

        static {
            int[] iArr = new int[PrinterModelType.values().length];
            f494x2dd1d739 = iArr;
            try {
                iArr[PrinterModelType.PRINT_MOBILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f494x2dd1d739[PrinterModelType.PRINT_LED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f494x2dd1d739[PrinterModelType.PRINT_LASER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f494x2dd1d739[PrinterModelType.PRINT_INKJET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static class PrintableArea {
        public final double height;
        public final double width;

        private PrintableArea(double d, double d2) {
            this.width = d;
            this.height = d2;
        }
    }
}
