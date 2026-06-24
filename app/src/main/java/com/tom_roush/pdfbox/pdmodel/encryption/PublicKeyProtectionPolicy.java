package com.tom_roush.pdfbox.pdmodel.encryption;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PublicKeyProtectionPolicy extends ProtectionPolicy {
    private X509Certificate decryptionCertificate;
    private final List<PublicKeyRecipient> recipients = new ArrayList();

    public void addRecipient(PublicKeyRecipient publicKeyRecipient) {
        this.recipients.add(publicKeyRecipient);
    }

    public boolean removeRecipient(PublicKeyRecipient publicKeyRecipient) {
        return this.recipients.remove(publicKeyRecipient);
    }

    public Iterator<PublicKeyRecipient> getRecipientsIterator() {
        return this.recipients.iterator();
    }

    public X509Certificate getDecryptionCertificate() {
        return this.decryptionCertificate;
    }

    public void setDecryptionCertificate(X509Certificate x509Certificate) {
        this.decryptionCertificate = x509Certificate;
    }

    public int getNumberOfRecipients() {
        return this.recipients.size();
    }
}
