package org.bouncycastle.jcajce.provider.keystore.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Enumeration;
import org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi;
import org.bouncycastle.jcajce.util.JcaJceHelper;

public class AdaptingKeyStoreSpi extends KeyStoreSpi {
    public static final String COMPAT_OVERRIDE = "keystore.type.compat";
    private final JKSKeyStoreSpi jksStore;
    private KeyStoreSpi keyStoreSpi;
    private final KeyStoreSpi primaryStore;

    public AdaptingKeyStoreSpi(JcaJceHelper jcaJceHelper, KeyStoreSpi keyStoreSpi) {
        this.jksStore = new JKSKeyStoreSpi(jcaJceHelper);
        this.primaryStore = keyStoreSpi;
        this.keyStoreSpi = keyStoreSpi;
    }

    @Override
    public Enumeration<String> engineAliases() {
        return this.keyStoreSpi.engineAliases();
    }

    @Override
    public boolean engineContainsAlias(String str) {
        return this.keyStoreSpi.engineContainsAlias(str);
    }

    @Override
    public void engineDeleteEntry(String str) throws KeyStoreException {
        this.keyStoreSpi.engineDeleteEntry(str);
    }

    @Override
    public Certificate engineGetCertificate(String str) {
        return this.keyStoreSpi.engineGetCertificate(str);
    }

    @Override
    public String engineGetCertificateAlias(Certificate certificate) {
        return this.keyStoreSpi.engineGetCertificateAlias(certificate);
    }

    @Override
    public Certificate[] engineGetCertificateChain(String str) {
        return this.keyStoreSpi.engineGetCertificateChain(str);
    }

    @Override
    public Date engineGetCreationDate(String str) {
        return this.keyStoreSpi.engineGetCreationDate(str);
    }

    @Override
    public Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        return this.keyStoreSpi.engineGetKey(str, cArr);
    }

    @Override
    public boolean engineIsCertificateEntry(String str) {
        return this.keyStoreSpi.engineIsCertificateEntry(str);
    }

    @Override
    public boolean engineIsKeyEntry(String str) {
        return this.keyStoreSpi.engineIsKeyEntry(str);
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void engineLoad(java.io.InputStream r3, char[] r4) throws java.security.NoSuchAlgorithmException, java.io.IOException, java.security.cert.CertificateException {
        /*
            r2 = this;
            if (r3 != 0) goto Lb
            java.security.KeyStoreSpi r3 = r2.primaryStore
            r2.keyStoreSpi = r3
            r0 = 0
            r3.engineLoad(r0, r4)
            goto L45
        Lb:
            java.lang.String r0 = "keystore.type.compat"
            boolean r0 = org.bouncycastle.util.Properties.isOverrideSet(r0)
            if (r0 != 0) goto L1d
            java.security.KeyStoreSpi r0 = r2.primaryStore
            boolean r1 = r0 instanceof org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi
            if (r1 != 0) goto L1a
            goto L1d
        L1a:
            r2.keyStoreSpi = r0
            goto L40
        L1d:
            boolean r0 = r3.markSupported()
            if (r0 != 0) goto L29
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream
            r0.<init>(r3)
            r3 = r0
        L29:
            r0 = 8
            r3.mark(r0)
            org.bouncycastle.jcajce.provider.keystore.util.JKSKeyStoreSpi r0 = r2.jksStore
            boolean r0 = r0.engineProbe(r3)
            if (r0 == 0) goto L39
            org.bouncycastle.jcajce.provider.keystore.util.JKSKeyStoreSpi r0 = r2.jksStore
            goto L3b
        L39:
            java.security.KeyStoreSpi r0 = r2.primaryStore
        L3b:
            r2.keyStoreSpi = r0
            r3.reset()
        L40:
            java.security.KeyStoreSpi r0 = r2.keyStoreSpi
            r0.engineLoad(r3, r4)
        L45:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.util.AdaptingKeyStoreSpi.engineLoad(java.io.InputStream, char[]):void");
    }

    @Override
    public void engineLoad(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, CertificateException {
        this.keyStoreSpi.engineLoad(loadStoreParameter);
    }

    @Override
    public boolean engineProbe(InputStream inputStream) throws IOException {
        KeyStoreSpi keyStoreSpi = this.keyStoreSpi;
        if (keyStoreSpi instanceof PKCS12KeyStoreSpi) {
            return ((PKCS12KeyStoreSpi) keyStoreSpi).engineProbe(inputStream);
        }
        return false;
    }

    @Override
    public void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
        this.keyStoreSpi.engineSetCertificateEntry(str, certificate);
    }

    @Override
    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
        this.keyStoreSpi.engineSetKeyEntry(str, key, cArr, certificateArr);
    }

    @Override
    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
        this.keyStoreSpi.engineSetKeyEntry(str, bArr, certificateArr);
    }

    @Override
    public int engineSize() {
        return this.keyStoreSpi.engineSize();
    }

    @Override
    public void engineStore(OutputStream outputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, CertificateException {
        this.keyStoreSpi.engineStore(outputStream, cArr);
    }

    @Override
    public void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, CertificateException {
        this.keyStoreSpi.engineStore(loadStoreParameter);
    }
}
