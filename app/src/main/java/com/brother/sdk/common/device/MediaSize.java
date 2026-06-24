package com.brother.sdk.common.device;

import com.p020hp.jipp.model.Media;

public enum MediaSize {
    SIXTEENK("iso_16K", 7.6771d, 10.6299d),
    A3(Media.isoA3_297x420mm, 11.6929d, 16.5354d),
    A4(Media.isoA4_210x297mm, 8.2677d, 11.6929d),
    A5(Media.isoA5_148x210mm, 5.8267d, 8.2677d),
    A6(Media.isoA6_105x148mm, 4.1338d, 5.8267d),
    B4(Media.isoB4_250x353mm, 9.8425d, 13.8976d),
    B5(Media.isoB5_176x250mm, 6.9291d, 9.8425d),
    B6(Media.isoB6_125x176mm, 4.9212d, 6.9291d),
    C5Envelope(Media.isoC5_162x229mm, 6.3779d, 9.0157d),
    DLEnvelope(Media.isoDl110x220mm, 4.3307d, 8.6614d),
    Letter(Media.naLetter8p5x11in, 8.5d, 11.0d),
    Legal(Media.naLegal8p5x14in, 8.5d, 14.0d),
    Ledger(Media.naLedger11x17in, 11.0d, 17.0d),
    Index4x6(Media.naIndex4x6_4x6in, 4.0d, 6.0d),
    Photo2L(Media.na5x7_5x7in, 5.0d, 7.0d),
    Executive(Media.naExecutive7p25x10p5in, 7.25d, 10.5d),
    JISB4(Media.jisB4_257x364mm, 10.1181d, 14.3307d),
    JISB5(Media.jisB5_182x257mm, 7.1653d, 10.1181d),
    Hagaki(Media.jpnHagaki100x148mm, 3.937d, 5.8267d),
    PhotoL(Media.oePhotoL3p5x5in, 3.5d, 5.0d),
    Folio(Media.omFolio210x330mm, 8.2677d, 12.9921d),
    BusinessCard("custom_card_60x90mm", 2.3622d, 3.5433d),
    BusinessCardLandscape("custom_card-land_90x60mm", 3.5433d, 2.3622d),
    Undefined("custom_undefined_0x0in", 0.0d, 0.0d);

    public final double height;
    public final String name;
    public final double width;

    MediaSize(String str, double d, double d2) {
        this.name = str;
        this.width = d;
        this.height = d2;
    }

    public static MediaSize fromName(String str) {
        for (MediaSize mediaSize : values()) {
            if (mediaSize.name.equals(str)) {
                return mediaSize;
            }
        }
        return Undefined;
    }
}
