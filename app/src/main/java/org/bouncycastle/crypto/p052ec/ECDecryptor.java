package org.bouncycastle.crypto.p052ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.math.p058ec.ECPoint;

public interface ECDecryptor {
    ECPoint decrypt(ECPair eCPair);

    void init(CipherParameters cipherParameters);
}
