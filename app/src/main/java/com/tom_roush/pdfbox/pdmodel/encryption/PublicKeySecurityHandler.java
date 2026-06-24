package com.tom_roush.pdfbox.pdmodel.encryption;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.apache.log4j.spi.Configurator;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.cms.RecipientId;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class PublicKeySecurityHandler extends SecurityHandler {
    public static final String FILTER = "Adobe.PubSec";
    private static final String SUBFILTER = "adbe.pkcs7.s4";
    private PublicKeyProtectionPolicy policy;

    public PublicKeySecurityHandler() {
        this.policy = null;
    }

    public PublicKeySecurityHandler(PublicKeyProtectionPolicy publicKeyProtectionPolicy) {
        this.policy = publicKeyProtectionPolicy;
        this.keyLength = publicKeyProtectionPolicy.getEncryptionKeyLength();
    }

    @Override
    public void prepareForDecryption(PDEncryption pDEncryption, COSArray cOSArray, DecryptionMaterial decryptionMaterial) throws IOException {
        if (!(decryptionMaterial instanceof PublicKeyDecryptionMaterial)) {
            throw new IOException("Provided decryption material is not compatible with the document");
        }
        setDecryptMetadata(pDEncryption.isEncryptMetaData());
        if (pDEncryption.getLength() != 0) {
            this.keyLength = pDEncryption.getLength();
        }
        PublicKeyDecryptionMaterial publicKeyDecryptionMaterial = (PublicKeyDecryptionMaterial) decryptionMaterial;
        try {
            int recipientsLength = pDEncryption.getRecipientsLength();
            byte[][] bArr = new byte[recipientsLength][];
            StringBuilder sb = new StringBuilder();
            int i = 0;
            boolean z = false;
            byte[] content = null;
            int length = 0;
            while (i < pDEncryption.getRecipientsLength()) {
                byte[] bytes = pDEncryption.getRecipientStringAt(i).getBytes();
                Iterator<RecipientInformation> it = new CMSEnvelopedData(bytes).getRecipientInfos().getRecipients().iterator();
                int i2 = 0;
                while (true) {
                    if (it.hasNext()) {
                        RecipientInformation next = it.next();
                        X509Certificate certificate = publicKeyDecryptionMaterial.getCertificate();
                        X509CertificateHolder x509CertificateHolder = certificate != null ? new X509CertificateHolder(certificate.getEncoded()) : null;
                        RecipientId rid = next.getRID();
                        if (rid.match(x509CertificateHolder) && !z) {
                            content = next.getContent(new JceKeyTransEnvelopedRecipient((PrivateKey) publicKeyDecryptionMaterial.getPrivateKey()).setProvider(BouncyCastleProvider.PROVIDER_NAME));
                            z = true;
                            break;
                        }
                        i2++;
                        if (certificate != null) {
                            sb.append('\n');
                            sb.append(i2);
                            sb.append(": ");
                            if (rid instanceof KeyTransRecipientId) {
                                appendCertInfo(sb, (KeyTransRecipientId) rid, certificate, x509CertificateHolder);
                            }
                        }
                    }
                }
                bArr[i] = bytes;
                length += bytes.length;
                i++;
            }
            if (!z || content == null) {
                throw new IOException("The certificate matches none of " + i + " recipient entries" + sb.toString());
            }
            if (content.length != 24) {
                throw new IOException("The enveloped data does not contain 24 bytes");
            }
            byte[] bArr2 = new byte[4];
            int length2 = 20;
            System.arraycopy(content, 20, bArr2, 0, 4);
            AccessPermission accessPermission = new AccessPermission(bArr2);
            accessPermission.setReadOnly();
            setCurrentAccessPermission(accessPermission);
            byte[] bArr3 = new byte[length + 20];
            int i3 = 0;
            System.arraycopy(content, 0, bArr3, 0, 20);
            int i4 = 0;
            while (i4 < recipientsLength) {
                byte[] bArr4 = bArr[i4];
                System.arraycopy(bArr4, i3, bArr3, length2, bArr4.length);
                length2 += bArr4.length;
                i4++;
                i3 = 0;
            }
            byte[] bArrDigest = MessageDigests.getSHA1().digest(bArr3);
            this.encryptionKey = new byte[this.keyLength / 8];
            System.arraycopy(bArrDigest, 0, this.encryptionKey, 0, this.keyLength / 8);
        } catch (KeyStoreException e) {
            throw new IOException(e);
        } catch (CertificateEncodingException e2) {
            throw new IOException(e2);
        } catch (CMSException e3) {
            throw new IOException(e3);
        }
    }

    private void appendCertInfo(StringBuilder sb, KeyTransRecipientId keyTransRecipientId, X509Certificate x509Certificate, X509CertificateHolder x509CertificateHolder) {
        BigInteger serialNumber = keyTransRecipientId.getSerialNumber();
        if (serialNumber != null) {
            BigInteger serialNumber2 = x509Certificate.getSerialNumber();
            String string = serialNumber2 != null ? serialNumber2.toString(16) : "unknown";
            sb.append("serial-#: rid ");
            sb.append(serialNumber.toString(16));
            sb.append(" vs. cert ");
            sb.append(string);
            sb.append(" issuer: rid '");
            sb.append(keyTransRecipientId.getIssuer());
            sb.append("' vs. cert '");
            sb.append(x509CertificateHolder == null ? Configurator.NULL : x509CertificateHolder.getIssuer());
            sb.append("' ");
        }
    }

    @Override
    public void prepareDocumentForEncryption(PDDocument pDDocument) throws IOException {
        if (this.keyLength == 256) {
            throw new IOException("256 bit key length is not supported yet for public key security");
        }
        try {
            Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
            Security.addProvider(new BouncyCastleProvider());
            PDEncryption encryption = pDDocument.getEncryption();
            if (encryption == null) {
                encryption = new PDEncryption();
            }
            encryption.setFilter(FILTER);
            encryption.setLength(this.keyLength);
            encryption.setVersion(2);
            encryption.removeV45filters();
            encryption.setSubFilter(SUBFILTER);
            int length = 20;
            byte[] bArr = new byte[20];
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(192, new SecureRandom());
                System.arraycopy(keyGenerator.generateKey().getEncoded(), 0, bArr, 0, 20);
                encryption.setRecipients(computeRecipientsField(bArr));
                int length2 = 20;
                for (int i = 0; i < encryption.getRecipientsLength(); i++) {
                    length2 += encryption.getRecipientStringAt(i).getBytes().length;
                }
                byte[] bArr2 = new byte[length2];
                System.arraycopy(bArr, 0, bArr2, 0, 20);
                for (int i2 = 0; i2 < encryption.getRecipientsLength(); i2++) {
                    COSString recipientStringAt = encryption.getRecipientStringAt(i2);
                    System.arraycopy(recipientStringAt.getBytes(), 0, bArr2, length, recipientStringAt.getBytes().length);
                    length += recipientStringAt.getBytes().length;
                }
                byte[] bArrDigest = MessageDigests.getSHA1().digest(bArr2);
                this.encryptionKey = new byte[this.keyLength / 8];
                System.arraycopy(bArrDigest, 0, this.encryptionKey, 0, this.keyLength / 8);
                pDDocument.setEncryptionDictionary(encryption);
                pDDocument.getDocument().setEncryptionDictionary(encryption.getCOSDictionary());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } catch (GeneralSecurityException e2) {
            throw new IOException(e2);
        }
    }

    private byte[][] computeRecipientsField(byte[] bArr) throws GeneralSecurityException, IOException {
        byte[][] bArr2 = new byte[this.policy.getNumberOfRecipients()][];
        Iterator<PublicKeyRecipient> recipientsIterator = this.policy.getRecipientsIterator();
        int i = 0;
        while (recipientsIterator.hasNext()) {
            PublicKeyRecipient next = recipientsIterator.next();
            X509Certificate x509 = next.getX509();
            int permissionBytesForPublicKey = next.getPermission().getPermissionBytesForPublicKey();
            byte[] bArr3 = new byte[24];
            System.arraycopy(bArr, 0, bArr3, 0, 20);
            bArr3[20] = (byte) (permissionBytesForPublicKey >>> 24);
            bArr3[21] = (byte) (permissionBytesForPublicKey >>> 16);
            bArr3[22] = (byte) (permissionBytesForPublicKey >>> 8);
            bArr3[23] = (byte) permissionBytesForPublicKey;
            ASN1Primitive aSN1PrimitiveCreateDERForRecipient = createDERForRecipient(bArr3, x509);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ASN1OutputStream.create(byteArrayOutputStream, ASN1Encoding.DER).writeObject(aSN1PrimitiveCreateDERForRecipient);
            bArr2[i] = byteArrayOutputStream.toByteArray();
            i++;
        }
        return bArr2;
    }

    private ASN1Primitive createDERForRecipient(byte[] bArr, X509Certificate x509Certificate) throws GeneralSecurityException, IOException {
        try {
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("1.2.840.113549.3.2");
            KeyGenerator keyGenerator = KeyGenerator.getInstance("1.2.840.113549.3.2", BouncyCastleProvider.PROVIDER_NAME);
            Cipher cipher = Cipher.getInstance("1.2.840.113549.3.2", BouncyCastleProvider.PROVIDER_NAME);
            AlgorithmParameters algorithmParametersGenerateParameters = algorithmParameterGenerator.generateParameters();
            ASN1InputStream aSN1InputStream = new ASN1InputStream(algorithmParametersGenerateParameters.getEncoded("ASN.1"));
            ASN1Primitive object = aSN1InputStream.readObject();
            aSN1InputStream.close();
            keyGenerator.init(128);
            SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
            cipher.init(1, secretKeyGenerateKey, algorithmParametersGenerateParameters);
            byte[] bArrDoFinal = cipher.doFinal(bArr);
            return new ContentInfo(PKCSObjectIdentifiers.envelopedData, new EnvelopedData((OriginatorInfo) null, new DERSet(new RecipientInfo(computeRecipientInfo(x509Certificate, secretKeyGenerateKey.getEncoded()))), new EncryptedContentInfo(PKCSObjectIdentifiers.data, new AlgorithmIdentifier(new ASN1ObjectIdentifier("1.2.840.113549.3.2"), object), new DEROctetString(bArrDoFinal)), (ASN1Set) null)).toASN1Primitive();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not find a suitable javax.crypto provider", e);
        } catch (NoSuchPaddingException e2) {
            throw new RuntimeException("Could not find a suitable javax.crypto provider", e2);
        }
    }

    private KeyTransRecipientInfo computeRecipientInfo(X509Certificate x509Certificate, byte[] bArr) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, IOException, CertificateEncodingException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(x509Certificate.getTBSCertificate());
        TBSCertificate tBSCertificate = TBSCertificate.getInstance(aSN1InputStream.readObject());
        aSN1InputStream.close();
        AlgorithmIdentifier algorithm = tBSCertificate.getSubjectPublicKeyInfo().getAlgorithm();
        IssuerAndSerialNumber issuerAndSerialNumber = new IssuerAndSerialNumber(tBSCertificate.getIssuer(), tBSCertificate.getSerialNumber().getValue());
        try {
            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm().getId());
            cipher.init(1, x509Certificate.getPublicKey());
            return new KeyTransRecipientInfo(new RecipientIdentifier(issuerAndSerialNumber), algorithm, new DEROctetString(cipher.doFinal(bArr)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not find a suitable javax.crypto provider", e);
        } catch (NoSuchPaddingException e2) {
            throw new RuntimeException("Could not find a suitable javax.crypto provider", e2);
        }
    }

    @Override
    public boolean hasProtectionPolicy() {
        return this.policy != null;
    }
}
