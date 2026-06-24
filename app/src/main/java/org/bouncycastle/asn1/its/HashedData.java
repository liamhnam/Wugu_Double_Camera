package org.bouncycastle.asn1.its;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;

public class HashedData extends ASN1Object implements ASN1Choice {
    private ASN1OctetString hashData;

    private HashedData(ASN1OctetString aSN1OctetString) {
        this.hashData = aSN1OctetString;
    }

    public HashedData(byte[] bArr) {
        this.hashData = new DEROctetString(bArr);
    }

    public ASN1OctetString getHashData() {
        return this.hashData;
    }

    public void setHashData(ASN1OctetString aSN1OctetString) {
        this.hashData = aSN1OctetString;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        return this.hashData;
    }
}
