package com.google.android.exoplayer2.drm;

import android.media.MediaDrmException;
import android.os.PersistableBundle;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.util.Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DummyExoMediaDrm<T extends ExoMediaCrypto> implements ExoMediaDrm<T> {
    @Override
    public void acquire() {
    }

    @Override
    public void closeSession(byte[] bArr) {
    }

    @Override
    public Class<T> getExoMediaCryptoType() {
        return null;
    }

    @Override
    public PersistableBundle getMetrics() {
        return null;
    }

    @Override
    public String getPropertyString(String str) {
        return "";
    }

    @Override
    public void release() {
    }

    @Override
    public void setOnEventListener(ExoMediaDrm.OnEventListener<? super T> onEventListener) {
    }

    @Override
    public void setOnKeyStatusChangeListener(ExoMediaDrm.OnKeyStatusChangeListener<? super T> onKeyStatusChangeListener) {
    }

    @Override
    public void setPropertyByteArray(String str, byte[] bArr) {
    }

    @Override
    public void setPropertyString(String str, String str2) {
    }

    public static <T extends ExoMediaCrypto> DummyExoMediaDrm<T> getInstance() {
        return new DummyExoMediaDrm<>();
    }

    @Override
    public byte[] openSession() throws MediaDrmException {
        throw new MediaDrmException("Attempting to open a session using a dummy ExoMediaDrm.");
    }

    @Override
    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] bArr, List<DrmInitData.SchemeData> list, int i, HashMap<String, String> map) {
        throw new IllegalStateException();
    }

    @Override
    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    @Override
    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        throw new IllegalStateException();
    }

    @Override
    public void provideProvisionResponse(byte[] bArr) {
        throw new IllegalStateException();
    }

    @Override
    public Map<String, String> queryKeyStatus(byte[] bArr) {
        throw new IllegalStateException();
    }

    @Override
    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    @Override
    public byte[] getPropertyByteArray(String str) {
        return Util.EMPTY_BYTE_ARRAY;
    }

    @Override
    public T createMediaCrypto(byte[] bArr) {
        throw new IllegalStateException();
    }
}
