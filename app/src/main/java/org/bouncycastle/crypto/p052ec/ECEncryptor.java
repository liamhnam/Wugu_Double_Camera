package org.bouncycastle.crypto.p052ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.math.p058ec.ECPoint;

public interface ECEncryptor {
    ECPair encrypt(ECPoint eCPoint);

    void init(CipherParameters cipherParameters);
}
