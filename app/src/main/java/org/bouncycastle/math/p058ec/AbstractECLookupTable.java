package org.bouncycastle.math.p058ec;

public abstract class AbstractECLookupTable implements ECLookupTable {
    @Override
    public ECPoint lookupVar(int i) {
        return lookup(i);
    }
}
