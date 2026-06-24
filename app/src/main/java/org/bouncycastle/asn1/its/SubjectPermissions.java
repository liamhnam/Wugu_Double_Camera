package org.bouncycastle.asn1.its;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

public class SubjectPermissions extends ASN1Object implements ASN1Choice {
    public static SubjectPermissions getInstance(Object obj) {
        if (obj instanceof SubjectPermissions) {
            return (SubjectPermissions) obj;
        }
        return null;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        return null;
    }
}
