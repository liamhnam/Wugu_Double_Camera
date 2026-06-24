package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Map;

public final class ErrorStateDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {
    private final DrmSession.DrmSessionException error;

    @Override
    public void acquire() {
    }

    @Override
    public T getMediaCrypto() {
        return null;
    }

    @Override
    public byte[] getOfflineLicenseKeySetId() {
        return null;
    }

    @Override
    public int getState() {
        return 1;
    }

    @Override
    public boolean playClearSamplesWithoutKeys() {
        return false;
    }

    @Override
    public Map<String, String> queryKeyStatus() {
        return null;
    }

    @Override
    public void release() {
    }

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.error = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSessionException);
    }

    @Override
    public DrmSession.DrmSessionException getError() {
        return this.error;
    }
}
