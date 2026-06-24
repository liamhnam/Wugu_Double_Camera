package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class DHParameter extends ASN1Object {

    ASN1Integer f2840g;

    ASN1Integer f2841l;

    ASN1Integer f2842p;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f2842p = new ASN1Integer(bigInteger);
        this.f2840g = new ASN1Integer(bigInteger2);
        this.f2841l = i != 0 ? new ASN1Integer(i) : null;
    }

    private DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f2842p = ASN1Integer.getInstance(objects.nextElement());
        this.f2840g = ASN1Integer.getInstance(objects.nextElement());
        this.f2841l = objects.hasMoreElements() ? (ASN1Integer) objects.nextElement() : null;
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getG() {
        return this.f2840g.getPositiveValue();
    }

    public BigInteger getL() {
        ASN1Integer aSN1Integer = this.f2841l;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f2842p.getPositiveValue();
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f2842p);
        aSN1EncodableVector.add(this.f2840g);
        if (getL() != null) {
            aSN1EncodableVector.add(this.f2841l);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
