package org.xmlpull.mxp1;

import java.io.IOException;
import org.xmlpull.p065v1.XmlPullParserException;

public class MXParserNonValidating extends MXParserCachingStrings {
    private boolean processDocDecl;

    protected void processAttlistDecl(char c) throws XmlPullParserException, IOException {
    }

    protected void processEntityDecl(char c) throws XmlPullParserException, IOException {
    }

    protected char processExternalId(char c) throws XmlPullParserException, IOException {
        return c;
    }

    protected void processNotationDecl(char c) throws XmlPullParserException, IOException {
    }

    protected void processPEReference() throws XmlPullParserException, IOException {
    }

    @Override
    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            if (this.eventType != 0) {
                throw new XmlPullParserException("process DOCDECL feature can only be changed before parsing", this, null);
            }
            this.processDocDecl = z;
            return;
        }
        super.setFeature(str, z);
    }

    @Override
    public boolean getFeature(String str) {
        if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            return this.processDocDecl;
        }
        return super.getFeature(str);
    }

    @Override
    protected char more() throws XmlPullParserException, IOException {
        return super.more();
    }

    @Override
    protected char[] lookuEntityReplacement(int i) throws XmlPullParserException, IOException {
        if (!this.allStringsInterned) {
            int iFastHash = MXParser.fastHash(this.buf, this.posStart, this.posEnd - this.posStart);
            for (int i2 = this.entityEnd - 1; i2 >= 0; i2--) {
                if (iFastHash == this.entityNameHash[i2] && i == this.entityNameBuf[i2].length) {
                    char[] cArr = this.entityNameBuf[i2];
                    for (int i3 = 0; i3 < i; i3++) {
                        if (this.buf[this.posStart + i3] != cArr[i3]) {
                            break;
                        }
                    }
                    if (this.tokenize) {
                        this.text = this.entityReplacement[i2];
                    }
                    return this.entityReplacementBuf[i2];
                }
            }
            return null;
        }
        this.entityRefName = newString(this.buf, this.posStart, this.posEnd - this.posStart);
        for (int i4 = this.entityEnd - 1; i4 >= 0; i4--) {
            if (this.entityRefName == this.entityName[i4]) {
                if (this.tokenize) {
                    this.text = this.entityReplacement[i4];
                }
                return this.entityReplacementBuf[i4];
            }
        }
        return null;
    }

    @Override
    protected void parseDocdecl() throws XmlPullParserException, IOException {
        boolean z = this.tokenize;
        try {
            if (more() != 'O') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            if (more() != 'C') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            if (more() != 'T') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            if (more() != 'Y') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            if (more() != 'P') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            if (more() != 'E') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            this.posStart = this.pos;
            char cRequireNextS = requireNextS();
            int i = this.pos;
            char name = readName(cRequireNextS);
            int i2 = this.pos;
            char cSkipS = skipS(name);
            if (cSkipS == 'S' || cSkipS == 'P') {
                cSkipS = skipS(processExternalId(cSkipS));
            }
            if (cSkipS == '[') {
                processInternalSubset();
            }
            char cSkipS2 = skipS(cSkipS);
            if (cSkipS2 != '>') {
                throw new XmlPullParserException(new StringBuffer("expected > to finish <[DOCTYPE but got ").append(printable(cSkipS2)).toString(), this, null);
            }
            this.posEnd = this.pos - 1;
        } finally {
            this.tokenize = z;
        }
    }

    protected void processInternalSubset() throws XmlPullParserException, IOException {
        while (true) {
            char cMore = more();
            if (cMore == ']') {
                return;
            }
            if (cMore == '%') {
                processPEReference();
            } else if (isS(cMore)) {
                skipS(cMore);
            } else {
                processMarkupDecl(cMore);
            }
        }
    }

    protected void processMarkupDecl(char c) throws XmlPullParserException, IOException {
        if (c != '<') {
            throw new XmlPullParserException(new StringBuffer("expected < for markupdecl in DTD not ").append(printable(c)).toString(), this, null);
        }
        char cMore = more();
        if (cMore == '?') {
            parsePI();
            return;
        }
        if (cMore == '!') {
            if (more() == '-') {
                parseComment();
                return;
            }
            char cMore2 = more();
            if (cMore2 == 'A') {
                processAttlistDecl(cMore2);
                return;
            }
            if (cMore2 != 'E') {
                if (cMore2 == 'N') {
                    processNotationDecl(cMore2);
                    return;
                }
                throw new XmlPullParserException(new StringBuffer("expected markupdecl after <! in DTD not ").append(printable(cMore2)).toString(), this, null);
            }
            char cMore3 = more();
            if (cMore3 == 'L') {
                processElementDecl(cMore3);
                return;
            } else {
                if (cMore3 == 'N') {
                    processEntityDecl(cMore3);
                    return;
                }
                throw new XmlPullParserException(new StringBuffer("expected ELEMENT or ENTITY after <! in DTD not ").append(printable(cMore3)).toString(), this, null);
            }
        }
        throw new XmlPullParserException(new StringBuffer("expected markupdecl in DTD not ").append(printable(cMore)).toString(), this, null);
    }

    protected void processElementDecl(char c) throws XmlPullParserException, IOException {
        readName(requireNextS());
        requireNextS();
    }

    protected char readName(char c) throws XmlPullParserException, IOException {
        if (isNameStartChar(c)) {
            throw new XmlPullParserException(new StringBuffer("XML name must start with name start character not ").append(printable(c)).toString(), this, null);
        }
        while (isNameChar(c)) {
            c = more();
        }
        return c;
    }
}
