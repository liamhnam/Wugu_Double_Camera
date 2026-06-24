package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;

public class McEliecePublicKey extends ASN1Object {

    private final GF2Matrix f3638g;

    private final int f3639n;

    private final int f3640t;

    public McEliecePublicKey(int i, int i2, GF2Matrix gF2Matrix) {
        this.f3639n = i;
        this.f3640t = i2;
        this.f3638g = new GF2Matrix(gF2Matrix);
    }

    private McEliecePublicKey(ASN1Sequence aSN1Sequence) {
        this.f3639n = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).intValueExact();
        this.f3640t = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).intValueExact();
        this.f3638g = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
    }

    public static McEliecePublicKey getInstance(Object obj) {
        if (obj instanceof McEliecePublicKey) {
            return (McEliecePublicKey) obj;
        }
        if (obj != null) {
            return new McEliecePublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GF2Matrix getG() {
        return new GF2Matrix(this.f3638g);
    }

    public int getN() {
        return this.f3639n;
    }

    public int getT() {
        return this.f3640t;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f3639n));
        aSN1EncodableVector.add(new ASN1Integer(this.f3640t));
        aSN1EncodableVector.add(new DEROctetString(this.f3638g.getEncoded()));
        return new DERSequence(aSN1EncodableVector);
    }
}
