package org.bouncycastle.asn1.its;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;

public class RectangularRegion extends ASN1Object {
    private RectangularRegion(ASN1Sequence aSN1Sequence) {
    }

    public static RectangularRegion getInstance(Object obj) {
        if (obj instanceof RectangularRegion) {
            return (RectangularRegion) obj;
        }
        if (obj != null) {
            return new RectangularRegion(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        return null;
    }
}
