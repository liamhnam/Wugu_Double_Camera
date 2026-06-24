package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class FilterFactory {
    public static final FilterFactory INSTANCE = new FilterFactory();
    private final Map<COSName, Filter> filters;

    private FilterFactory() {
        HashMap map = new HashMap();
        this.filters = map;
        FlateFilter flateFilter = new FlateFilter();
        DCTFilter dCTFilter = new DCTFilter();
        CCITTFaxFilter cCITTFaxFilter = new CCITTFaxFilter();
        LZWFilter lZWFilter = new LZWFilter();
        ASCIIHexFilter aSCIIHexFilter = new ASCIIHexFilter();
        ASCII85Filter aSCII85Filter = new ASCII85Filter();
        RunLengthDecodeFilter runLengthDecodeFilter = new RunLengthDecodeFilter();
        CryptFilter cryptFilter = new CryptFilter();
        map.put(COSName.FLATE_DECODE, flateFilter);
        map.put(COSName.FLATE_DECODE_ABBREVIATION, flateFilter);
        map.put(COSName.DCT_DECODE, dCTFilter);
        map.put(COSName.DCT_DECODE_ABBREVIATION, dCTFilter);
        map.put(COSName.CCITTFAX_DECODE, cCITTFaxFilter);
        map.put(COSName.CCITTFAX_DECODE_ABBREVIATION, cCITTFaxFilter);
        map.put(COSName.LZW_DECODE, lZWFilter);
        map.put(COSName.LZW_DECODE_ABBREVIATION, lZWFilter);
        map.put(COSName.ASCII_HEX_DECODE, aSCIIHexFilter);
        map.put(COSName.ASCII_HEX_DECODE_ABBREVIATION, aSCIIHexFilter);
        map.put(COSName.ASCII85_DECODE, aSCII85Filter);
        map.put(COSName.ASCII85_DECODE_ABBREVIATION, aSCII85Filter);
        map.put(COSName.RUN_LENGTH_DECODE, runLengthDecodeFilter);
        map.put(COSName.RUN_LENGTH_DECODE_ABBREVIATION, runLengthDecodeFilter);
        map.put(COSName.CRYPT, cryptFilter);
    }

    public Filter getFilter(String str) throws IOException {
        return getFilter(COSName.getPDFName(str));
    }

    public Filter getFilter(COSName cOSName) throws IOException {
        Filter filter = this.filters.get(cOSName);
        if (filter != null) {
            return filter;
        }
        throw new IOException("Invalid filter: " + cOSName);
    }

    Collection<Filter> getAllFilters() {
        return this.filters.values();
    }
}
