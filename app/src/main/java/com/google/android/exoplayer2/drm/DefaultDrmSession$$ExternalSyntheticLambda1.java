package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher;

public final class DefaultDrmSession$$ExternalSyntheticLambda1 implements EventDispatcher.Event {
    @Override
    public final void sendTo(Object obj) {
        ((DefaultDrmSessionEventListener) obj).onDrmKeysRestored();
    }
}
