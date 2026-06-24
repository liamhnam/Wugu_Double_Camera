package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

public class AsnSequence extends AsnObject {
    private Vector<AsnObject> children;

    @Override
    public String toString() {
        return "";
    }

    public AsnSequence() {
        this(SnmpConstants.CONS_SEQ);
    }

    AsnSequence(byte b) {
        this.type = b;
        this.children = new Vector<>(1, 1);
    }

    AsnSequence(InputStream inputStream, int i, int i2) throws IOException {
        this();
        if (debug > 10) {
            System.out.println("AsnSequence(): Length = " + i + ", Pos = " + i2);
        }
        while (true) {
            AsnObject asnObjectAsnReadHeader = AsnReadHeader(inputStream, i2);
            if (asnObjectAsnReadHeader == null) {
                return;
            }
            i2 += asnObjectAsnReadHeader.headerLength + asnObjectAsnReadHeader.contentsLength;
            add(asnObjectAsnReadHeader);
        }
    }

    @Override
    public AsnObject add(AsnObject asnObject) {
        if (asnObject.isCorrect) {
            this.children.addElement(asnObject);
        }
        return asnObject;
    }

    AsnObject replaceChild(AsnObject asnObject, AsnObject asnObject2) {
        int iIndexOf = this.children.indexOf(asnObject);
        if (iIndexOf <= 0) {
            return asnObject;
        }
        this.children.setElementAt(asnObject2, iIndexOf);
        return asnObject2;
    }

    @Override
    int size() {
        Enumeration<AsnObject> enumerationElements = this.children.elements();
        int lengthBytes = 0;
        while (enumerationElements.hasMoreElements()) {
            int size = enumerationElements.nextElement().size();
            lengthBytes += size + getLengthBytes(size) + 1;
        }
        return lengthBytes;
    }

    public void write(OutputStream outputStream) throws IOException {
        write(outputStream, 0);
    }

    @Override
    void write(OutputStream outputStream, int i) throws IOException {
        int size = size();
        this.startPos = i;
        AsnBuildHeader(outputStream, this.type, size);
        if (debug > 10) {
            System.out.println("\tAsnSequence.write(): begin, startPos = " + this.startPos);
        }
        int i2 = i + this.headerLength;
        Enumeration<AsnObject> enumerationElements = this.children.elements();
        while (enumerationElements.hasMoreElements()) {
            AsnObject asnObjectNextElement = enumerationElements.nextElement();
            asnObjectNextElement.write(outputStream, i2);
            asnObjectNextElement.startPos = i2;
            i2 += asnObjectNextElement.headerLength + asnObjectNextElement.contentsLength;
        }
        if (debug > 10) {
            System.out.println("\tAsnSequence.write(): end");
        }
    }

    @Override
    AsnObject findPdu() {
        Enumeration<AsnObject> enumerationElements = this.children.elements();
        AsnObject asnObjectFindPdu = null;
        while (enumerationElements.hasMoreElements() && (asnObjectFindPdu = enumerationElements.nextElement().findPdu()) == null) {
        }
        if (!this.isCorrect && asnObjectFindPdu != null) {
            asnObjectFindPdu.isCorrect = false;
        }
        return asnObjectFindPdu;
    }

    public AsnObject getObj(int i) {
        try {
            return this.children.elementAt(i);
        } catch (ArrayIndexOutOfBoundsException unused) {
            return new AsnNull();
        }
    }

    public int getObjCount() {
        return this.children.size();
    }
}
