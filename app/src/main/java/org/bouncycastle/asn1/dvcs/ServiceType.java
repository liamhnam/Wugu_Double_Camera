package org.bouncycastle.asn1.dvcs;

import java.math.BigInteger;
import org.apache.log4j.spi.LocationInfo;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;

public class ServiceType extends ASN1Object {
    private ASN1Enumerated value;
    public static final ServiceType CPD = new ServiceType(1);
    public static final ServiceType VSD = new ServiceType(2);
    public static final ServiceType VPKC = new ServiceType(3);
    public static final ServiceType CCPD = new ServiceType(4);

    public ServiceType(int i) {
        this.value = new ASN1Enumerated(i);
    }

    private ServiceType(ASN1Enumerated aSN1Enumerated) {
        this.value = aSN1Enumerated;
    }

    public static ServiceType getInstance(Object obj) {
        if (obj instanceof ServiceType) {
            return (ServiceType) obj;
        }
        if (obj != null) {
            return new ServiceType(ASN1Enumerated.getInstance(obj));
        }
        return null;
    }

    public static ServiceType getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Enumerated.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getValue() {
        return this.value.getValue();
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        return this.value;
    }

    public String toString() {
        int iIntValueExact = this.value.intValueExact();
        return "" + iIntValueExact + (iIntValueExact == CPD.value.intValueExact() ? "(CPD)" : iIntValueExact == VSD.value.intValueExact() ? "(VSD)" : iIntValueExact == VPKC.value.intValueExact() ? "(VPKC)" : iIntValueExact == CCPD.value.intValueExact() ? "(CCPD)" : LocationInfo.f2800NA);
    }
}
