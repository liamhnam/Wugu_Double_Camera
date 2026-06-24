package org.bouncycastle.tsp.ers;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;

public abstract class ERSCachingData implements ERSData {
    private Map<AlgorithmIdentifier, byte[]> preCalcs = new HashMap();

    protected abstract byte[] calculateHash(DigestCalculator digestCalculator);

    @Override
    public byte[] getHash(DigestCalculator digestCalculator) {
        AlgorithmIdentifier algorithmIdentifier = digestCalculator.getAlgorithmIdentifier();
        if (this.preCalcs.containsKey(algorithmIdentifier)) {
            return this.preCalcs.get(algorithmIdentifier);
        }
        byte[] bArrCalculateHash = calculateHash(digestCalculator);
        this.preCalcs.put(algorithmIdentifier, bArrCalculateHash);
        return bArrCalculateHash;
    }
}
