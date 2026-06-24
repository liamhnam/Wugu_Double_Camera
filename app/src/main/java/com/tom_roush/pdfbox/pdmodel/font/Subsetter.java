package com.tom_roush.pdfbox.pdmodel.font;

import java.io.IOException;

interface Subsetter {
    void addToSubset(int i);

    void subset() throws IOException;
}
