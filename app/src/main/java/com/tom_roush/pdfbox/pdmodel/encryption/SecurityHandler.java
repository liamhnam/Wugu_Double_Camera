package com.tom_roush.pdfbox.pdmodel.encryption;

import android.util.Log;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class SecurityHandler {
    private static final byte[] AES_SALT = {115, SnmpConstants.COUNTER, 108, 84};
    private static final int DEFAULT_KEY_LENGTH = 40;
    private boolean decryptMetadata;
    protected byte[] encryptionKey;
    private boolean useAES;
    protected int keyLength = 40;
    private final RC4Cipher rc4 = new RC4Cipher();
    private final Set<COSBase> objects = new HashSet();
    private AccessPermission currentAccessPermission = null;

    public abstract boolean hasProtectionPolicy();

    public abstract void prepareDocumentForEncryption(PDDocument pDDocument) throws IOException;

    public abstract void prepareForDecryption(PDEncryption pDEncryption, COSArray cOSArray, DecryptionMaterial decryptionMaterial) throws IOException;

    protected void setDecryptMetadata(boolean z) {
        this.decryptMetadata = z;
    }

    private void encryptData(long j, long j2, InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        if (this.useAES && this.encryptionKey.length == 32) {
            encryptDataAES256(inputStream, outputStream, z);
        } else {
            byte[] bArrCalcFinalKey = calcFinalKey(j, j2);
            if (this.useAES) {
                encryptDataAESother(bArrCalcFinalKey, inputStream, outputStream, z);
            } else {
                encryptDataRC4(bArrCalcFinalKey, inputStream, outputStream);
            }
        }
        outputStream.flush();
    }

    private byte[] calcFinalKey(long j, long j2) {
        byte[] bArr = this.encryptionKey;
        int length = bArr.length + 5;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[length - 5] = (byte) (j & 255);
        bArr2[length - 4] = (byte) ((j >> 8) & 255);
        bArr2[length - 3] = (byte) ((j >> 16) & 255);
        bArr2[length - 2] = (byte) (j2 & 255);
        bArr2[length - 1] = (byte) ((j2 >> 8) & 255);
        MessageDigest md5 = MessageDigests.getMD5();
        md5.update(bArr2);
        if (this.useAES) {
            md5.update(AES_SALT);
        }
        byte[] bArrDigest = md5.digest();
        int iMin = Math.min(length, 16);
        byte[] bArr3 = new byte[iMin];
        System.arraycopy(bArrDigest, 0, bArr3, 0, iMin);
        return bArr3;
    }

    protected void encryptDataRC4(byte[] bArr, InputStream inputStream, OutputStream outputStream) throws IOException {
        this.rc4.setKey(bArr);
        this.rc4.write(inputStream, outputStream);
    }

    protected void encryptDataRC4(byte[] bArr, byte[] bArr2, OutputStream outputStream) throws IOException {
        this.rc4.setKey(bArr);
        this.rc4.write(bArr2, outputStream);
    }

    private void encryptDataAESother(byte[] bArr, InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        byte[] bArr2 = new byte[16];
        if (!prepareAESInitializationVector(z, bArr2, inputStream, outputStream)) {
            return;
        }
        try {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(z ? 2 : 1, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
                byte[] bArr3 = new byte[256];
                while (true) {
                    int i = inputStream.read(bArr3);
                    if (i != -1) {
                        outputStream.write(cipher.update(bArr3, 0, i));
                    } else {
                        outputStream.write(cipher.doFinal());
                        return;
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } catch (InvalidAlgorithmParameterException e2) {
            throw new IOException(e2);
        } catch (InvalidKeyException e3) {
            throw new IOException(e3);
        } catch (BadPaddingException e4) {
            throw new IOException(e4);
        } catch (IllegalBlockSizeException e5) {
            throw new IOException(e5);
        } catch (NoSuchPaddingException e6) {
            throw new IOException(e6);
        }
    }

    private void encryptDataAES256(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        byte[] bArr = new byte[16];
        if (prepareAESInitializationVector(z, bArr, inputStream, outputStream)) {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(z ? 2 : 1, new SecretKeySpec(this.encryptionKey, "AES"), new IvParameterSpec(bArr));
                CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
                try {
                    try {
                        IOUtils.copy(cipherInputStream, outputStream);
                    } catch (IOException e) {
                        if (!(e.getCause() instanceof GeneralSecurityException)) {
                            throw e;
                        }
                        Log.d("PdfBox-Android", "A GeneralSecurityException occured when decrypting some stream data", e);
                    }
                } finally {
                    cipherInputStream.close();
                }
            } catch (GeneralSecurityException e2) {
                throw new IOException(e2);
            }
        }
    }

    private boolean prepareAESInitializationVector(boolean z, byte[] bArr, InputStream inputStream, OutputStream outputStream) throws IOException {
        if (z) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return false;
            }
            if (i == bArr.length) {
                return true;
            }
            throw new IOException("AES initialization vector not fully read: only " + i + " bytes read instead of " + bArr.length);
        }
        new SecureRandom().nextBytes(bArr);
        outputStream.write(bArr);
        return true;
    }

    public void decrypt(COSBase cOSBase, long j, long j2) throws IOException {
        if (this.objects.contains(cOSBase)) {
            return;
        }
        this.objects.add(cOSBase);
        if (cOSBase instanceof COSString) {
            decryptString((COSString) cOSBase, j, j2);
            return;
        }
        if (cOSBase instanceof COSStream) {
            decryptStream((COSStream) cOSBase, j, j2);
        } else if (cOSBase instanceof COSDictionary) {
            decryptDictionary((COSDictionary) cOSBase, j, j2);
        } else if (cOSBase instanceof COSArray) {
            decryptArray((COSArray) cOSBase, j, j2);
        }
    }

    public void decryptStream(COSStream cOSStream, long j, long j2) throws IOException {
        COSName cOSName = cOSStream.getCOSName(COSName.TYPE);
        if ((this.decryptMetadata || !COSName.METADATA.equals(cOSName)) && !COSName.XREF.equals(cOSName)) {
            if (COSName.METADATA.equals(cOSName)) {
                InputStream inputStreamCreateRawInputStream = cOSStream.createRawInputStream();
                byte[] bArr = new byte[10];
                inputStreamCreateRawInputStream.read(bArr);
                inputStreamCreateRawInputStream.close();
                if (Arrays.equals(bArr, "<?xpacket ".getBytes(Charsets.ISO_8859_1))) {
                    Log.w("PdfBox-Android", "Metadata is not encrypted, but was expected to be");
                    Log.w("PdfBox-Android", "Read PDF specification about EncryptMetadata (default value: true)");
                    return;
                }
            }
            decryptDictionary(cOSStream, j, j2);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOUtils.toByteArray(cOSStream.createRawInputStream()));
            OutputStream outputStreamCreateRawOutputStream = cOSStream.createRawOutputStream();
            try {
                encryptData(j, j2, byteArrayInputStream, outputStreamCreateRawOutputStream, true);
            } finally {
                outputStreamCreateRawOutputStream.close();
            }
        }
    }

    public void encryptStream(COSStream cOSStream, long j, int i) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOUtils.toByteArray(cOSStream.createRawInputStream()));
        OutputStream outputStreamCreateRawOutputStream = cOSStream.createRawOutputStream();
        try {
            encryptData(j, i, byteArrayInputStream, outputStreamCreateRawOutputStream, false);
        } finally {
            outputStreamCreateRawOutputStream.close();
        }
    }

    private void decryptDictionary(COSDictionary cOSDictionary, long j, long j2) throws IOException {
        if (cOSDictionary.getItem(COSName.f2242CF) != null) {
            return;
        }
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.TYPE);
        boolean z = COSName.SIG.equals(dictionaryObject) || COSName.DOC_TIME_STAMP.equals(dictionaryObject);
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            if (!z || !COSName.CONTENTS.equals(entry.getKey())) {
                COSBase value = entry.getValue();
                if ((value instanceof COSString) || (value instanceof COSArray) || (value instanceof COSDictionary)) {
                    decrypt(value, j, j2);
                }
            }
        }
    }

    private void decryptString(COSString cOSString, long j, long j2) throws IOException {
        InputStream byteArrayInputStream = new ByteArrayInputStream(cOSString.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encryptData(j, j2, byteArrayInputStream, byteArrayOutputStream, true);
            cOSString.setValue(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Failed to decrypt COSString of length " + cOSString.getBytes().length + " in object " + j + ": " + e.getMessage());
        }
    }

    public void encryptString(COSString cOSString, long j, int i) throws IOException {
        InputStream byteArrayInputStream = new ByteArrayInputStream(cOSString.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encryptData(j, i, byteArrayInputStream, byteArrayOutputStream, false);
        cOSString.setValue(byteArrayOutputStream.toByteArray());
    }

    private void decryptArray(COSArray cOSArray, long j, long j2) throws IOException {
        for (int i = 0; i < cOSArray.size(); i++) {
            decrypt(cOSArray.get(i), j, j2);
        }
    }

    public int getKeyLength() {
        return this.keyLength;
    }

    public void setKeyLength(int i) {
        this.keyLength = i;
    }

    public void setCurrentAccessPermission(AccessPermission accessPermission) {
        this.currentAccessPermission = accessPermission;
    }

    public AccessPermission getCurrentAccessPermission() {
        return this.currentAccessPermission;
    }

    public boolean isAES() {
        return this.useAES;
    }

    public void setAES(boolean z) {
        this.useAES = z;
    }
}
