package com.tom_roush.pdfbox.pdmodel.encryption;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

public final class StandardSecurityHandler extends SecurityHandler {
    private static final int DEFAULT_VERSION = 1;
    public static final String FILTER = "Standard";
    private StandardProtectionPolicy policy;
    public static final Class<?> PROTECTION_POLICY_CLASS = StandardProtectionPolicy.class;
    private static final byte[] ENCRYPT_PADDING = {40, -65, 78, 94, 78, 117, -118, SnmpConstants.COUNTER, 100, 0, 78, 86, -1, -6, 1, 8, 46, 46, 0, -74, -48, 104, 62, -128, 47, 12, -87, -2, 100, 83, 105, 122};
    private static final String[] HASHES_2B = {"SHA-256", McElieceCCA2KeyGenParameterSpec.SHA384, "SHA-512"};

    public StandardSecurityHandler() {
    }

    public StandardSecurityHandler(StandardProtectionPolicy standardProtectionPolicy) {
        this.policy = standardProtectionPolicy;
        this.keyLength = standardProtectionPolicy.getEncryptionKeyLength();
    }

    private int computeVersionNumber() {
        if (this.keyLength == 40) {
            return 1;
        }
        return this.keyLength == 256 ? 5 : 2;
    }

    private int computeRevisionNumber(int i) {
        if (i < 2 && !this.policy.getPermissions().hasAnyRevision3PermissionSet()) {
            return 2;
        }
        if (i == 5) {
            return 6;
        }
        if (i == 4) {
            return 4;
        }
        return (i == 2 || i == 3 || this.policy.getPermissions().hasAnyRevision3PermissionSet()) ? 3 : 4;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void prepareForDecryption(com.tom_roush.pdfbox.pdmodel.encryption.PDEncryption r22, com.tom_roush.pdfbox.cos.COSArray r23, com.tom_roush.pdfbox.pdmodel.encryption.DecryptionMaterial r24) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.encryption.StandardSecurityHandler.prepareForDecryption(com.tom_roush.pdfbox.pdmodel.encryption.PDEncryption, com.tom_roush.pdfbox.cos.COSArray, com.tom_roush.pdfbox.pdmodel.encryption.DecryptionMaterial):void");
    }

    private byte[] getDocumentIDBytes(COSArray cOSArray) {
        return (cOSArray == null || cOSArray.size() < 1) ? new byte[0] : ((COSString) cOSArray.getObject(0)).getBytes();
    }

    private void validatePerms(PDEncryption pDEncryption, int i, boolean z) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(2, new SecretKeySpec(this.encryptionKey, "AES"));
            byte[] bArrDoFinal = cipher.doFinal(pDEncryption.getPerms());
            if (bArrDoFinal[9] != 97 || bArrDoFinal[10] != 100 || bArrDoFinal[11] != 98) {
                Log.w("PdfBox-Android", "Verification of permissions failed (constant)");
            }
            int i2 = (bArrDoFinal[0] & UByte.MAX_VALUE) | ((bArrDoFinal[1] & UByte.MAX_VALUE) << 8) | ((bArrDoFinal[2] & UByte.MAX_VALUE) << 16) | ((bArrDoFinal[3] & UByte.MAX_VALUE) << 24);
            if (i2 != i) {
                Log.w("PdfBox-Android", "Verification of permissions failed (" + String.format("%08X", Integer.valueOf(i2)) + " != " + String.format("%08X", Integer.valueOf(i)) + ")");
            }
            if ((!z || bArrDoFinal[8] == 84) && (z || bArrDoFinal[8] == 70)) {
                return;
            }
            Log.w("PdfBox-Android", "Verification of permissions failed (EncryptMetadata)");
        } catch (GeneralSecurityException e) {
            logIfStrongEncryptionMissing();
            throw new IOException(e);
        }
    }

    @Override
    public void prepareDocumentForEncryption(PDDocument pDDocument) throws IOException {
        PDEncryption encryption = pDDocument.getEncryption();
        if (encryption == null) {
            encryption = new PDEncryption();
        }
        int iComputeVersionNumber = computeVersionNumber();
        int iComputeRevisionNumber = computeRevisionNumber(iComputeVersionNumber);
        encryption.setFilter("Standard");
        encryption.setVersion(iComputeVersionNumber);
        if (iComputeVersionNumber != 4 && iComputeVersionNumber != 5) {
            encryption.removeV45filters();
        }
        encryption.setRevision(iComputeRevisionNumber);
        encryption.setLength(this.keyLength);
        String ownerPassword = this.policy.getOwnerPassword();
        String userPassword = this.policy.getUserPassword();
        if (ownerPassword == null) {
            ownerPassword = "";
        }
        String str = userPassword != null ? userPassword : "";
        String str2 = ownerPassword.isEmpty() ? str : ownerPassword;
        int permissionBytes = this.policy.getPermissions().getPermissionBytes();
        encryption.setPermissions(permissionBytes);
        int i = this.keyLength / 8;
        if (iComputeRevisionNumber == 6) {
            prepareEncryptionDictRev6(str2, str, encryption, permissionBytes);
        } else {
            prepareEncryptionDictRev2345(str2, str, encryption, permissionBytes, pDDocument, iComputeRevisionNumber, i);
        }
        pDDocument.setEncryptionDictionary(encryption);
        pDDocument.getDocument().setEncryptionDictionary(encryption.getCOSDictionary());
    }

    private void prepareEncryptionDictRev6(String str, String str2, PDEncryption pDEncryption, int i) throws IOException {
        try {
            SecureRandom secureRandom = new SecureRandom();
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            this.encryptionKey = new byte[32];
            secureRandom.nextBytes(this.encryptionKey);
            byte[] bArrTruncate127 = truncate127(str2.getBytes(Charsets.UTF_8));
            byte[] bArr = new byte[8];
            byte[] bArr2 = new byte[8];
            secureRandom.nextBytes(bArr);
            secureRandom.nextBytes(bArr2);
            byte[] bArrConcat = concat(computeHash2B(concat(bArrTruncate127, bArr), bArrTruncate127, null), bArr, bArr2);
            cipher.init(1, new SecretKeySpec(computeHash2B(concat(bArrTruncate127, bArr2), bArrTruncate127, null), "AES"), new IvParameterSpec(new byte[16]));
            byte[] bArrDoFinal = cipher.doFinal(this.encryptionKey);
            byte[] bArrTruncate1272 = truncate127(str.getBytes(Charsets.UTF_8));
            byte[] bArr3 = new byte[8];
            byte[] bArr4 = new byte[8];
            secureRandom.nextBytes(bArr3);
            secureRandom.nextBytes(bArr4);
            byte[] bArrConcat2 = concat(computeHash2B(concat(bArrTruncate1272, bArr3, bArrConcat), bArrTruncate1272, bArrConcat), bArr3, bArr4);
            cipher.init(1, new SecretKeySpec(computeHash2B(concat(bArrTruncate1272, bArr4, bArrConcat), bArrTruncate1272, bArrConcat), "AES"), new IvParameterSpec(new byte[16]));
            byte[] bArrDoFinal2 = cipher.doFinal(this.encryptionKey);
            pDEncryption.setUserKey(bArrConcat);
            pDEncryption.setUserEncryptionKey(bArrDoFinal);
            pDEncryption.setOwnerKey(bArrConcat2);
            pDEncryption.setOwnerEncryptionKey(bArrDoFinal2);
            prepareEncryptionDictAES(pDEncryption, COSName.AESV3);
            byte[] bArr5 = new byte[16];
            bArr5[0] = (byte) i;
            bArr5[1] = (byte) (i >>> 8);
            bArr5[2] = (byte) (i >>> 16);
            bArr5[3] = (byte) (i >>> 24);
            bArr5[4] = -1;
            bArr5[5] = -1;
            bArr5[6] = -1;
            bArr5[7] = -1;
            bArr5[8] = 84;
            bArr5[9] = 97;
            bArr5[10] = 100;
            bArr5[11] = 98;
            for (int i2 = 12; i2 <= 15; i2++) {
                bArr5[i2] = (byte) secureRandom.nextInt();
            }
            cipher.init(1, new SecretKeySpec(this.encryptionKey, "AES"), new IvParameterSpec(new byte[16]));
            pDEncryption.setPerms(cipher.doFinal(bArr5));
        } catch (GeneralSecurityException e) {
            logIfStrongEncryptionMissing();
            throw new IOException(e);
        }
    }

    private void prepareEncryptionDictRev2345(String str, String str2, PDEncryption pDEncryption, int i, PDDocument pDDocument, int i2, int i3) throws IOException {
        COSArray documentID = pDDocument.getDocument().getDocumentID();
        if (documentID == null || documentID.size() < 2) {
            MessageDigest md5 = MessageDigests.getMD5();
            md5.update(BigInteger.valueOf(System.currentTimeMillis()).toByteArray());
            md5.update(str.getBytes(Charsets.ISO_8859_1));
            md5.update(str2.getBytes(Charsets.ISO_8859_1));
            md5.update(pDDocument.getDocument().toString().getBytes(Charsets.ISO_8859_1));
            COSString cOSString = new COSString(md5.digest(toString().getBytes(Charsets.ISO_8859_1)));
            documentID = new COSArray();
            documentID.add((COSBase) cOSString);
            documentID.add((COSBase) cOSString);
            pDDocument.getDocument().setDocumentID(documentID);
        }
        COSString cOSString2 = (COSString) documentID.getObject(0);
        byte[] bArrComputeOwnerPassword = computeOwnerPassword(str.getBytes(Charsets.ISO_8859_1), str2.getBytes(Charsets.ISO_8859_1), i2, i3);
        byte[] bArrComputeUserPassword = computeUserPassword(str2.getBytes(Charsets.ISO_8859_1), bArrComputeOwnerPassword, i, cOSString2.getBytes(), i2, i3, true);
        this.encryptionKey = computeEncryptedKey(str2.getBytes(Charsets.ISO_8859_1), bArrComputeOwnerPassword, null, null, null, i, cOSString2.getBytes(), i2, i3, true, false);
        pDEncryption.setOwnerKey(bArrComputeOwnerPassword);
        pDEncryption.setUserKey(bArrComputeUserPassword);
        if (i2 == 4) {
            prepareEncryptionDictAES(pDEncryption, COSName.AESV2);
        }
    }

    private void prepareEncryptionDictAES(PDEncryption pDEncryption, COSName cOSName) {
        PDCryptFilterDictionary pDCryptFilterDictionary = new PDCryptFilterDictionary();
        pDCryptFilterDictionary.setCryptFilterMethod(cOSName);
        pDCryptFilterDictionary.setLength(this.keyLength);
        pDEncryption.setStdCryptFilterDictionary(pDCryptFilterDictionary);
        pDEncryption.setStreamFilterName(COSName.STD_CF);
        pDEncryption.setStringFilterName(COSName.STD_CF);
        setAES(true);
    }

    public boolean isOwnerPassword(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, boolean z) throws IOException {
        byte[] bArrComputeHash2A;
        if (i2 == 6 || i2 == 5) {
            byte[] bArrTruncate127 = truncate127(bArr);
            byte[] bArr5 = new byte[32];
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr3, 0, bArr5, 0, 32);
            System.arraycopy(bArr3, 32, bArr6, 0, 8);
            if (i2 == 5) {
                bArrComputeHash2A = computeSHA256(bArrTruncate127, bArr6, bArr2);
            } else {
                bArrComputeHash2A = computeHash2A(bArrTruncate127, bArr6, bArr2);
            }
            return Arrays.equals(bArrComputeHash2A, bArr5);
        }
        return isUserPassword(getUserPassword(bArr, bArr3, i2, i3), bArr2, bArr3, i, bArr4, i2, i3, z);
    }

    public byte[] getUserPassword(byte[] bArr, byte[] bArr2, int i, int i2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArrComputeRC4key = computeRC4key(bArr, i, i2);
        if (i == 2) {
            encryptDataRC4(bArrComputeRC4key, bArr2, byteArrayOutputStream);
        } else if (i == 3 || i == 4) {
            int length = bArrComputeRC4key.length;
            byte[] bArr3 = new byte[length];
            byte[] byteArray = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, byteArray, 0, bArr2.length);
            for (int i3 = 19; i3 >= 0; i3--) {
                System.arraycopy(bArrComputeRC4key, 0, bArr3, 0, bArrComputeRC4key.length);
                for (int i4 = 0; i4 < length; i4++) {
                    bArr3[i4] = (byte) (bArr3[i4] ^ ((byte) i3));
                }
                byteArrayOutputStream.reset();
                encryptDataRC4(bArr3, byteArray, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] computeEncryptedKey(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i, byte[] bArr6, int i2, int i3, boolean z, boolean z2) throws IOException {
        if (i2 == 6 || i2 == 5) {
            return computeEncryptedKeyRev56(bArr, z2, bArr2, bArr3, bArr4, bArr5, i2);
        }
        return computeEncryptedKeyRev234(bArr, bArr2, i, bArr6, z, i3, i2);
    }

    private byte[] computeEncryptedKeyRev234(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, boolean z, int i2, int i3) {
        byte[] bArrTruncateOrPad = truncateOrPad(bArr);
        MessageDigest md5 = MessageDigests.getMD5();
        md5.update(bArrTruncateOrPad);
        md5.update(bArr2);
        md5.update((byte) i);
        md5.update((byte) (i >>> 8));
        md5.update((byte) (i >>> 16));
        md5.update((byte) (i >>> 24));
        md5.update(bArr3);
        if (i3 == 4 && !z) {
            md5.update(new byte[]{-1, -1, -1, -1});
        }
        byte[] bArrDigest = md5.digest();
        if (i3 == 3 || i3 == 4) {
            for (int i4 = 0; i4 < 50; i4++) {
                md5.update(bArrDigest, 0, i2);
                bArrDigest = md5.digest();
            }
        }
        byte[] bArr4 = new byte[i2];
        System.arraycopy(bArrDigest, 0, bArr4, 0, i2);
        return bArr4;
    }

    private byte[] computeEncryptedKeyRev56(byte[] bArr, boolean z, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i) throws IOException {
        byte[] bArrComputeHash2A;
        if (z) {
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr2, 40, bArr6, 0, 8);
            if (i == 5) {
                bArrComputeHash2A = computeSHA256(bArr, bArr6, bArr3);
            } else {
                bArrComputeHash2A = computeHash2A(bArr, bArr6, bArr3);
            }
        } else {
            byte[] bArr7 = new byte[8];
            System.arraycopy(bArr3, 40, bArr7, 0, 8);
            if (i == 5) {
                bArrComputeHash2A = computeSHA256(bArr, bArr7, null);
            } else {
                bArrComputeHash2A = computeHash2A(bArr, bArr7, null);
            }
            bArr4 = bArr5;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(2, new SecretKeySpec(bArrComputeHash2A, "AES"), new IvParameterSpec(new byte[16]));
            return cipher.doFinal(bArr4);
        } catch (GeneralSecurityException e) {
            logIfStrongEncryptionMissing();
            throw new IOException(e);
        }
    }

    public byte[] computeUserPassword(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, int i3, boolean z) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArrComputeEncryptedKey = computeEncryptedKey(bArr, bArr2, null, null, null, i, bArr3, i2, i3, z, true);
        if (i2 == 2) {
            encryptDataRC4(bArrComputeEncryptedKey, ENCRYPT_PADDING, byteArrayOutputStream);
        } else if (i2 == 3 || i2 == 4) {
            MessageDigest md5 = MessageDigests.getMD5();
            md5.update(ENCRYPT_PADDING);
            md5.update(bArr3);
            byteArrayOutputStream.write(md5.digest());
            int length = bArrComputeEncryptedKey.length;
            byte[] bArr4 = new byte[length];
            for (int i4 = 0; i4 < 20; i4++) {
                System.arraycopy(bArrComputeEncryptedKey, 0, bArr4, 0, length);
                for (int i5 = 0; i5 < length; i5++) {
                    bArr4[i5] = (byte) (bArr4[i5] ^ i4);
                }
                InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                encryptDataRC4(bArr4, byteArrayInputStream, byteArrayOutputStream);
            }
            byte[] bArr5 = new byte[32];
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, bArr5, 0, 16);
            System.arraycopy(ENCRYPT_PADDING, 0, bArr5, 16, 16);
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(bArr5);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] computeOwnerPassword(byte[] bArr, byte[] bArr2, int i, int i2) throws IOException {
        if (i == 2 && i2 != 5) {
            throw new IOException("Expected length=5 actual=" + i2);
        }
        byte[] bArrComputeRC4key = computeRC4key(bArr, i, i2);
        byte[] bArrTruncateOrPad = truncateOrPad(bArr2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encryptDataRC4(bArrComputeRC4key, new ByteArrayInputStream(bArrTruncateOrPad), byteArrayOutputStream);
        if (i == 3 || i == 4) {
            int length = bArrComputeRC4key.length;
            byte[] bArr3 = new byte[length];
            for (int i3 = 1; i3 < 20; i3++) {
                System.arraycopy(bArrComputeRC4key, 0, bArr3, 0, bArrComputeRC4key.length);
                for (int i4 = 0; i4 < length; i4++) {
                    bArr3[i4] = (byte) (bArr3[i4] ^ ((byte) i3));
                }
                InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                encryptDataRC4(bArr3, byteArrayInputStream, byteArrayOutputStream);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] computeRC4key(byte[] bArr, int i, int i2) {
        MessageDigest md5 = MessageDigests.getMD5();
        byte[] bArrDigest = md5.digest(truncateOrPad(bArr));
        if (i == 3 || i == 4) {
            for (int i3 = 0; i3 < 50; i3++) {
                md5.update(bArrDigest, 0, i2);
                bArrDigest = md5.digest();
            }
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArrDigest, 0, bArr2, 0, i2);
        return bArr2;
    }

    private byte[] truncateOrPad(byte[] bArr) {
        byte[] bArr2 = ENCRYPT_PADDING;
        int length = bArr2.length;
        byte[] bArr3 = new byte[length];
        int iMin = Math.min(bArr.length, length);
        System.arraycopy(bArr, 0, bArr3, 0, iMin);
        System.arraycopy(bArr2, 0, bArr3, iMin, bArr2.length - iMin);
        return bArr3;
    }

    public boolean isUserPassword(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, boolean z) throws IOException {
        byte[] bArrComputeHash2A;
        if (i2 == 2) {
            return Arrays.equals(bArr2, computeUserPassword(bArr, bArr3, i, bArr4, i2, i3, z));
        }
        if (i2 == 3 || i2 == 4) {
            return Arrays.equals(Arrays.copyOf(bArr2, 16), Arrays.copyOf(computeUserPassword(bArr, bArr3, i, bArr4, i2, i3, z), 16));
        }
        if (i2 == 6 || i2 == 5) {
            byte[] bArrTruncate127 = truncate127(bArr);
            byte[] bArr5 = new byte[32];
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr2, 0, bArr5, 0, 32);
            System.arraycopy(bArr2, 32, bArr6, 0, 8);
            if (i2 == 5) {
                bArrComputeHash2A = computeSHA256(bArrTruncate127, bArr6, null);
            } else {
                bArrComputeHash2A = computeHash2A(bArrTruncate127, bArr6, null);
            }
            return Arrays.equals(bArrComputeHash2A, bArr5);
        }
        throw new IOException("Unknown Encryption Revision " + i2);
    }

    public boolean isUserPassword(String str, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, int i3, boolean z) throws IOException {
        if (i2 == 6 || i2 == 5) {
            return isUserPassword(str.getBytes(Charsets.UTF_8), bArr, bArr2, i, bArr3, i2, i3, z);
        }
        return isUserPassword(str.getBytes(Charsets.ISO_8859_1), bArr, bArr2, i, bArr3, i2, i3, z);
    }

    public boolean isOwnerPassword(String str, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, int i3, boolean z) throws IOException {
        return isOwnerPassword(str.getBytes(Charsets.ISO_8859_1), bArr, bArr2, i, bArr3, i2, i3, z);
    }

    private byte[] computeHash2A(byte[] bArr, byte[] bArr2, byte[] bArr3) throws IOException {
        if (bArr3 == null) {
            bArr3 = new byte[0];
        } else {
            if (bArr3.length < 48) {
                throw new IOException("Bad U length");
            }
            if (bArr3.length > 48) {
                byte[] bArr4 = new byte[48];
                System.arraycopy(bArr3, 0, bArr4, 0, 48);
                bArr3 = bArr4;
            }
        }
        byte[] bArrTruncate127 = truncate127(bArr);
        return computeHash2B(concat(bArrTruncate127, bArr2, bArr3), bArrTruncate127, bArr3);
    }

    private static byte[] computeHash2B(byte[] bArr, byte[] bArr2, byte[] bArr3) throws IOException {
        byte[] bArr4;
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(bArr);
            byte[] bArr5 = null;
            int i = 0;
            while (true) {
                if (i >= 64 && (bArr5[bArr5.length - 1] & UByte.MAX_VALUE) <= i - 32) {
                    break;
                }
                if (bArr3 != null && bArr3.length >= 48) {
                    bArr4 = new byte[(bArr2.length + bArrDigest.length + 48) * 64];
                } else {
                    bArr4 = new byte[(bArr2.length + bArrDigest.length) * 64];
                }
                int length = 0;
                for (int i2 = 0; i2 < 64; i2++) {
                    System.arraycopy(bArr2, 0, bArr4, length, bArr2.length);
                    int length2 = length + bArr2.length;
                    System.arraycopy(bArrDigest, 0, bArr4, length2, bArrDigest.length);
                    length = length2 + bArrDigest.length;
                    if (bArr3 != null && bArr3.length >= 48) {
                        System.arraycopy(bArr3, 0, bArr4, length, 48);
                        length += 48;
                    }
                }
                byte[] bArr6 = new byte[16];
                byte[] bArr7 = new byte[16];
                System.arraycopy(bArrDigest, 0, bArr6, 0, 16);
                System.arraycopy(bArrDigest, 16, bArr7, 0, 16);
                Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                cipher.init(1, new SecretKeySpec(bArr6, "AES"), new IvParameterSpec(bArr7));
                byte[] bArrDoFinal = cipher.doFinal(bArr4);
                byte[] bArr8 = new byte[16];
                System.arraycopy(bArrDoFinal, 0, bArr8, 0, 16);
                i++;
                bArr5 = bArrDoFinal;
                bArrDigest = MessageDigest.getInstance(HASHES_2B[new BigInteger(1, bArr8).mod(new BigInteger(ExifInterface.GPS_MEASUREMENT_3D)).intValue()]).digest(bArrDoFinal);
            }
            if (bArrDigest.length <= 32) {
                return bArrDigest;
            }
            byte[] bArr9 = new byte[32];
            System.arraycopy(bArrDigest, 0, bArr9, 0, 32);
            return bArr9;
        } catch (GeneralSecurityException e) {
            logIfStrongEncryptionMissing();
            throw new IOException(e);
        }
    }

    private static byte[] computeSHA256(byte[] bArr, byte[] bArr2, byte[] bArr3) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            messageDigest.update(bArr2);
            return bArr3 == null ? messageDigest.digest() : messageDigest.digest(bArr3);
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }

    private static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static byte[] concat(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length + bArr2.length, bArr3.length);
        return bArr4;
    }

    private static byte[] truncate127(byte[] bArr) {
        if (bArr.length <= 127) {
            return bArr;
        }
        byte[] bArr2 = new byte[127];
        System.arraycopy(bArr, 0, bArr2, 0, 127);
        return bArr2;
    }

    private static void logIfStrongEncryptionMissing() {
        try {
            if (Cipher.getMaxAllowedKeyLength("AES") != Integer.MAX_VALUE) {
                Log.w("PdfBox-Android", "JCE unlimited strength jurisdiction policy files are not installed");
            }
        } catch (NoSuchAlgorithmException unused) {
        }
    }

    @Override
    public boolean hasProtectionPolicy() {
        return this.policy != null;
    }
}
