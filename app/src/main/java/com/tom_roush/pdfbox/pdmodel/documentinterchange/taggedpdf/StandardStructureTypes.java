package com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StandardStructureTypes {
    public static final String ANNOT = "Annot";
    public static final String ART = "Art";
    public static final String BIB_ENTRY = "BibEntry";
    public static final String BLOCK_QUOTE = "BlockQuote";
    public static final String CAPTION = "Caption";
    public static final String CODE = "Code";
    public static final String DIV = "Div";
    public static final String DOCUMENT = "Document";
    public static final String FORM = "Form";
    public static final String FORMULA = "Formula";
    public static final String Figure = "Figure";

    public static final String f2367H = "H";

    public static final String f2368H1 = "H1";

    public static final String f2369H2 = "H2";

    public static final String f2370H3 = "H3";

    public static final String f2371H4 = "H4";

    public static final String f2372H5 = "H5";

    public static final String f2373H6 = "H6";
    public static final String INDEX = "Index";

    public static final String f2374L = "L";
    public static final String LBL = "Lbl";

    public static final String f2375LI = "LI";
    public static final String LINK = "Link";
    public static final String L_BODY = "LBody";
    public static final String NON_STRUCT = "NonStruct";
    public static final String NOTE = "Note";

    public static final String f2376P = "P";
    public static final String PART = "Part";
    public static final String PRIVATE = "Private";
    public static final String QUOTE = "Quote";

    public static final String f2377RB = "RB";
    public static final String REFERENCE = "Reference";

    public static final String f2378RP = "RP";

    public static final String f2379RT = "RT";
    public static final String RUBY = "Ruby";
    public static final String SECT = "Sect";
    public static final String SPAN = "Span";
    public static final String TABLE = "Table";

    public static final String f2380TD = "TD";

    public static final String f2381TH = "TH";
    public static final String TOC = "TOC";
    public static final String TOCI = "TOCI";

    public static final String f2382TR = "TR";
    public static final String T_BODY = "TBody";
    public static final String T_FOOT = "TFoot";
    public static final String T_HEAD = "THead";
    public static final String WARICHU = "Warichu";

    public static final String f2383WP = "WP";

    public static final String f2384WT = "WT";
    public static List<String> types = new ArrayList();

    private StandardStructureTypes() {
    }

    static {
        for (Field field : StandardStructureTypes.class.getFields()) {
            if (Modifier.isFinal(field.getModifiers())) {
                try {
                    types.add(field.get(null).toString());
                } catch (IllegalAccessException e) {
                    Log.e("PdfBox-Android", e.getMessage(), e);
                } catch (IllegalArgumentException e2) {
                    Log.e("PdfBox-Android", e2.getMessage(), e2);
                }
            }
        }
        Collections.sort(types);
    }
}
