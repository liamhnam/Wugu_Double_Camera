package org.bouncycastle.crypto.p052ec;

import org.bouncycastle.crypto.CipherParameters;

public interface ECPairTransform {
    void init(CipherParameters cipherParameters);

    ECPair transform(ECPair eCPair);
}
