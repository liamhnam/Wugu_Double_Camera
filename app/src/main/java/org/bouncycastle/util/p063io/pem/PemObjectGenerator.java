package org.bouncycastle.util.p063io.pem;

public interface PemObjectGenerator {
    PemObject generate() throws PemGenerationException;
}
