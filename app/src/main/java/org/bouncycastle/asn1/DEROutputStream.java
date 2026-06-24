package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

class DEROutputStream extends ASN1OutputStream {
    DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    DEROutputStream getDERSubStream() {
        return this;
    }

    @Override
    ASN1OutputStream getDLSubStream() {
        return this;
    }

    @Override
    void writePrimitive(ASN1Primitive aSN1Primitive, boolean z) throws IOException {
        aSN1Primitive.toDERObject().encode(this, z);
    }
}
