package org.bouncycastle.crypto.p052ec;

import org.bouncycastle.math.p058ec.ECPoint;

public class ECPair {

    private final ECPoint f3104x;

    private final ECPoint f3105y;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.f3104x = eCPoint;
        this.f3105y = eCPoint2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public ECPoint getX() {
        return this.f3104x;
    }

    public ECPoint getY() {
        return this.f3105y;
    }

    public int hashCode() {
        return this.f3104x.hashCode() + (this.f3105y.hashCode() * 37);
    }
}
