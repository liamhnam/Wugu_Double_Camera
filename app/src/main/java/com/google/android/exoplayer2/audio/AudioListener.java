package com.google.android.exoplayer2.audio;

public interface AudioListener {
    default void onAudioAttributesChanged(AudioAttributes audioAttributes) {
    }

    default void onAudioSessionId(int i) {
    }

    default void onVolumeChanged(float f) {
    }
}
