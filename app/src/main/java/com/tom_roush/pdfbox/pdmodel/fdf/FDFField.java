package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAction;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionFactory;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FDFField implements COSObjectable {
    private COSDictionary field;

    public FDFField() {
        this.field = new COSDictionary();
    }

    public FDFField(COSDictionary cOSDictionary) {
        this.field = cOSDictionary;
    }

    public FDFField(Element element) throws IOException {
        this();
        setPartialFieldName(element.getAttribute(NamingTable.TAG));
        NodeList childNodes = element.getChildNodes();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem instanceof Element) {
                Element element2 = (Element) nodeItem;
                if (element2.getTagName().equals("value")) {
                    setValue(XMLUtil.getNodeValue(element2));
                } else if (element2.getTagName().equals("value-richtext")) {
                    setRichText(new COSString(XMLUtil.getNodeValue(element2)));
                } else if (element2.getTagName().equals("field")) {
                    arrayList.add(new FDFField(element2));
                }
            }
        }
        if (arrayList.size() > 0) {
            setKids(arrayList);
        }
    }

    public void writeXML(Writer writer) throws IOException {
        writer.write("<field name=\"" + getPartialFieldName() + "\">\n");
        Object value = getValue();
        if (value != null) {
            if (value instanceof COSString) {
                writer.write("<value>" + escapeXML(((COSString) value).getString()) + "</value>\n");
            } else if (value instanceof COSStream) {
                writer.write("<value>" + escapeXML(((COSStream) value).toTextString()) + "</value>\n");
            }
        }
        String richText = getRichText();
        if (richText != null) {
            writer.write("<value-richtext>" + escapeXML(richText) + "</value-richtext>\n");
        }
        List<FDFField> kids = getKids();
        if (kids != null) {
            Iterator<FDFField> it = kids.iterator();
            while (it.hasNext()) {
                it.next().writeXML(writer);
            }
        }
        writer.write("</field>\n");
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.field;
    }

    public List<FDFField> getKids() {
        COSArray cOSArray = (COSArray) this.field.getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(new FDFField((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setKids(List<FDFField> list) {
        this.field.setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public String getPartialFieldName() {
        return this.field.getString(COSName.f2310T);
    }

    public void setPartialFieldName(String str) {
        this.field.setString(COSName.f2310T, str);
    }

    public Object getValue() throws IOException {
        COSBase dictionaryObject = this.field.getDictionaryObject(COSName.f2320V);
        if (dictionaryObject instanceof COSName) {
            return ((COSName) dictionaryObject).getName();
        }
        if (dictionaryObject instanceof COSArray) {
            return COSArrayList.convertCOSStringCOSArrayToList((COSArray) dictionaryObject);
        }
        if ((dictionaryObject instanceof COSString) || (dictionaryObject instanceof COSStream)) {
            return dictionaryObject;
        }
        if (dictionaryObject == null) {
            return null;
        }
        throw new IOException("Error:Unknown type for field import" + dictionaryObject);
    }

    public COSBase getCOSValue() throws IOException {
        COSBase dictionaryObject = this.field.getDictionaryObject(COSName.f2320V);
        if ((dictionaryObject instanceof COSName) || (dictionaryObject instanceof COSArray) || (dictionaryObject instanceof COSString) || (dictionaryObject instanceof COSStream)) {
            return dictionaryObject;
        }
        if (dictionaryObject == null) {
            return null;
        }
        throw new IOException("Error:Unknown type for field import" + dictionaryObject);
    }

    public void setValue(Object obj) throws IOException {
        COSBase cOSObject;
        if (obj instanceof List) {
            cOSObject = COSArrayList.convertStringListToCOSStringCOSArray((List) obj);
        } else if (obj instanceof String) {
            cOSObject = COSName.getPDFName((String) obj);
        } else if (obj instanceof COSObjectable) {
            cOSObject = ((COSObjectable) obj).getCOSObject();
        } else {
            if (obj != null) {
                throw new IOException("Error:Unknown type for field import" + obj);
            }
            cOSObject = null;
        }
        this.field.setItem(COSName.f2320V, cOSObject);
    }

    public void setValue(COSBase cOSBase) {
        this.field.setItem(COSName.f2320V, cOSBase);
    }

    public Integer getFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.f2262FF);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setFieldFlags(Integer num) {
        this.field.setItem(COSName.f2262FF, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setFieldFlags(int i) {
        this.field.setInt(COSName.f2262FF, i);
    }

    public Integer getSetFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.SET_FF);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setSetFieldFlags(Integer num) {
        this.field.setItem(COSName.SET_FF, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setSetFieldFlags(int i) {
        this.field.setInt(COSName.SET_FF, i);
    }

    public Integer getClearFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.CLR_FF);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setClearFieldFlags(Integer num) {
        this.field.setItem(COSName.CLR_FF, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setClearFieldFlags(int i) {
        this.field.setInt(COSName.CLR_FF, i);
    }

    public Integer getWidgetFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setWidgetFieldFlags(Integer num) {
        this.field.setItem(COSName.f2260F, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setWidgetFieldFlags(int i) {
        this.field.setInt(COSName.f2260F, i);
    }

    public Integer getSetWidgetFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.SET_F);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setSetWidgetFieldFlags(Integer num) {
        this.field.setItem(COSName.SET_F, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setSetWidgetFieldFlags(int i) {
        this.field.setInt(COSName.SET_F, i);
    }

    public Integer getClearWidgetFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.CLR_F);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setClearWidgetFieldFlags(Integer num) {
        this.field.setItem(COSName.CLR_F, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setClearWidgetFieldFlags(int i) {
        this.field.setInt(COSName.CLR_F, i);
    }

    public PDAppearanceDictionary getAppearanceDictionary() {
        COSDictionary cOSDictionary = (COSDictionary) this.field.getDictionaryObject(COSName.f2230AP);
        if (cOSDictionary != null) {
            return new PDAppearanceDictionary(cOSDictionary);
        }
        return null;
    }

    public void setAppearanceDictionary(PDAppearanceDictionary pDAppearanceDictionary) {
        this.field.setItem(COSName.f2230AP, pDAppearanceDictionary);
    }

    public FDFNamedPageReference getAppearanceStreamReference() {
        COSDictionary cOSDictionary = (COSDictionary) this.field.getDictionaryObject(COSName.AP_REF);
        if (cOSDictionary != null) {
            return new FDFNamedPageReference(cOSDictionary);
        }
        return null;
    }

    public void setAppearanceStreamReference(FDFNamedPageReference fDFNamedPageReference) {
        this.field.setItem(COSName.AP_REF, fDFNamedPageReference);
    }

    public FDFIconFit getIconFit() {
        COSDictionary cOSDictionary = (COSDictionary) this.field.getDictionaryObject(COSName.f2270IF);
        if (cOSDictionary != null) {
            return new FDFIconFit(cOSDictionary);
        }
        return null;
    }

    public void setIconFit(FDFIconFit fDFIconFit) {
        this.field.setItem(COSName.f2270IF, fDFIconFit);
    }

    public List<Object> getOptions() {
        COSArray cOSArray = (COSArray) this.field.getDictionaryObject(COSName.OPT);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSString) {
                arrayList.add(((COSString) object).getString());
            } else {
                arrayList.add(new FDFOptionElement((COSArray) object));
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setOptions(List<Object> list) {
        this.field.setItem(COSName.OPT, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDAction getAction() {
        return PDActionFactory.createAction((COSDictionary) this.field.getDictionaryObject(COSName.f2228A));
    }

    public void setAction(PDAction pDAction) {
        this.field.setItem(COSName.f2228A, pDAction);
    }

    public PDAdditionalActions getAdditionalActions() {
        COSDictionary cOSDictionary = (COSDictionary) this.field.getDictionaryObject(COSName.f2229AA);
        if (cOSDictionary != null) {
            return new PDAdditionalActions(cOSDictionary);
        }
        return null;
    }

    public void setAdditionalActions(PDAdditionalActions pDAdditionalActions) {
        this.field.setItem(COSName.f2229AA, pDAdditionalActions);
    }

    public String getRichText() {
        COSBase dictionaryObject = this.field.getDictionaryObject(COSName.f2300RV);
        if (dictionaryObject == null) {
            return null;
        }
        if (dictionaryObject instanceof COSString) {
            return ((COSString) dictionaryObject).getString();
        }
        return ((COSStream) dictionaryObject).toTextString();
    }

    public void setRichText(COSString cOSString) {
        this.field.setItem(COSName.f2300RV, (COSBase) cOSString);
    }

    public void setRichText(COSStream cOSStream) {
        this.field.setItem(COSName.f2300RV, (COSBase) cOSStream);
    }

    private String escapeXML(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"') {
                sb.append("&quot;");
            } else if (cCharAt == '<') {
                sb.append("&lt;");
            } else if (cCharAt == '>') {
                sb.append("&gt;");
            } else if (cCharAt == '&') {
                sb.append("&amp;");
            } else if (cCharAt == '\'') {
                sb.append("&apos;");
            } else if (cCharAt > '~') {
                sb.append("&#").append((int) cCharAt).append(";");
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }
}
