package org.bouncycastle.asn1;

import java.io.IOException;
import org.apache.log4j.helpers.DateLayout;

public abstract class ASN1Null extends ASN1Primitive {
    ASN1Null() {
    }

    public static ASN1Null getInstance(Object obj) {
        if (obj instanceof ASN1Null) {
            return (ASN1Null) obj;
        }
        if (obj == null) {
            return null;
        }
        try {
            return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
        } catch (IOException e) {
            throw new IllegalArgumentException("failed to construct NULL from byte[]: " + e.getMessage());
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
        }
    }

    @Override
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        return aSN1Primitive instanceof ASN1Null;
    }

    @Override
    abstract void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException;

    @Override
    public int hashCode() {
        return -1;
    }

    public String toString() {
        return DateLayout.NULL_DATE_FORMAT;
    }
}
