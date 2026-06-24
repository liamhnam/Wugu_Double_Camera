package org.xmlpull.p065v1.wrapper.classic;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;

public class XmlPullParserDelegate implements XmlPullParser {

    protected XmlPullParser f3794pp;

    public XmlPullParserDelegate(XmlPullParser xmlPullParser) {
        this.f3794pp = xmlPullParser;
    }

    @Override
    public String getText() {
        return this.f3794pp.getText();
    }

    @Override
    public void setFeature(String str, boolean z) throws XmlPullParserException {
        this.f3794pp.setFeature(str, z);
    }

    @Override
    public char[] getTextCharacters(int[] iArr) {
        return this.f3794pp.getTextCharacters(iArr);
    }

    @Override
    public int getColumnNumber() {
        return this.f3794pp.getColumnNumber();
    }

    @Override
    public int getNamespaceCount(int i) throws XmlPullParserException {
        return this.f3794pp.getNamespaceCount(i);
    }

    @Override
    public String getNamespacePrefix(int i) throws XmlPullParserException {
        return this.f3794pp.getNamespacePrefix(i);
    }

    @Override
    public String getAttributeName(int i) {
        return this.f3794pp.getAttributeName(i);
    }

    @Override
    public String getName() {
        return this.f3794pp.getName();
    }

    @Override
    public boolean getFeature(String str) {
        return this.f3794pp.getFeature(str);
    }

    @Override
    public String getInputEncoding() {
        return this.f3794pp.getInputEncoding();
    }

    @Override
    public String getAttributeValue(int i) {
        return this.f3794pp.getAttributeValue(i);
    }

    @Override
    public String getNamespace(String str) {
        return this.f3794pp.getNamespace(str);
    }

    @Override
    public void setInput(Reader reader) throws XmlPullParserException {
        this.f3794pp.setInput(reader);
    }

    @Override
    public int getLineNumber() {
        return this.f3794pp.getLineNumber();
    }

    @Override
    public Object getProperty(String str) {
        return this.f3794pp.getProperty(str);
    }

    @Override
    public boolean isEmptyElementTag() throws XmlPullParserException {
        return this.f3794pp.isEmptyElementTag();
    }

    @Override
    public boolean isAttributeDefault(int i) {
        return this.f3794pp.isAttributeDefault(i);
    }

    @Override
    public String getNamespaceUri(int i) throws XmlPullParserException {
        return this.f3794pp.getNamespaceUri(i);
    }

    @Override
    public int next() throws XmlPullParserException, IOException {
        return this.f3794pp.next();
    }

    @Override
    public int nextToken() throws XmlPullParserException, IOException {
        return this.f3794pp.nextToken();
    }

    @Override
    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        this.f3794pp.defineEntityReplacementText(str, str2);
    }

    @Override
    public int getAttributeCount() {
        return this.f3794pp.getAttributeCount();
    }

    @Override
    public boolean isWhitespace() throws XmlPullParserException {
        return this.f3794pp.isWhitespace();
    }

    @Override
    public String getPrefix() {
        return this.f3794pp.getPrefix();
    }

    @Override
    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        this.f3794pp.require(i, str, str2);
    }

    @Override
    public String nextText() throws XmlPullParserException, IOException {
        return this.f3794pp.nextText();
    }

    @Override
    public String getAttributeType(int i) {
        return this.f3794pp.getAttributeType(i);
    }

    @Override
    public int getDepth() {
        return this.f3794pp.getDepth();
    }

    @Override
    public int nextTag() throws XmlPullParserException, IOException {
        return this.f3794pp.nextTag();
    }

    @Override
    public int getEventType() throws XmlPullParserException {
        return this.f3794pp.getEventType();
    }

    @Override
    public String getAttributePrefix(int i) {
        return this.f3794pp.getAttributePrefix(i);
    }

    @Override
    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        this.f3794pp.setInput(inputStream, str);
    }

    @Override
    public String getAttributeValue(String str, String str2) {
        return this.f3794pp.getAttributeValue(str, str2);
    }

    @Override
    public void setProperty(String str, Object obj) throws XmlPullParserException {
        this.f3794pp.setProperty(str, obj);
    }

    @Override
    public String getPositionDescription() {
        return this.f3794pp.getPositionDescription();
    }

    @Override
    public String getNamespace() {
        return this.f3794pp.getNamespace();
    }

    @Override
    public String getAttributeNamespace(int i) {
        return this.f3794pp.getAttributeNamespace(i);
    }
}
