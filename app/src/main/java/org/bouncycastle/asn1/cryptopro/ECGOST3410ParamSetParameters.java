package org.bouncycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class ECGOST3410ParamSetParameters extends ASN1Object {

    ASN1Integer f2814a;

    ASN1Integer f2815b;

    ASN1Integer f2816p;

    ASN1Integer f2817q;

    ASN1Integer f2818x;

    ASN1Integer f2819y;

    public ECGOST3410ParamSetParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i, BigInteger bigInteger5) {
        this.f2814a = new ASN1Integer(bigInteger);
        this.f2815b = new ASN1Integer(bigInteger2);
        this.f2816p = new ASN1Integer(bigInteger3);
        this.f2817q = new ASN1Integer(bigInteger4);
        this.f2818x = new ASN1Integer(i);
        this.f2819y = new ASN1Integer(bigInteger5);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f2814a = (ASN1Integer) objects.nextElement();
        this.f2815b = (ASN1Integer) objects.nextElement();
        this.f2816p = (ASN1Integer) objects.nextElement();
        this.f2817q = (ASN1Integer) objects.nextElement();
        this.f2818x = (ASN1Integer) objects.nextElement();
        this.f2819y = (ASN1Integer) objects.nextElement();
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof ECGOST3410ParamSetParameters)) {
            return (ECGOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getA() {
        return this.f2814a.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f2816p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f2817q.getPositiveValue();
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(6);
        aSN1EncodableVector.add(this.f2814a);
        aSN1EncodableVector.add(this.f2815b);
        aSN1EncodableVector.add(this.f2816p);
        aSN1EncodableVector.add(this.f2817q);
        aSN1EncodableVector.add(this.f2818x);
        aSN1EncodableVector.add(this.f2819y);
        return new DERSequence(aSN1EncodableVector);
    }
}
