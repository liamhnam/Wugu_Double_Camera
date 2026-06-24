package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioSink;
import java.nio.ByteBuffer;

public class ForwardingAudioSink implements AudioSink {
    private final AudioSink sink;

    public ForwardingAudioSink(AudioSink audioSink) {
        this.sink = audioSink;
    }

    @Override
    public void setListener(AudioSink.Listener listener) {
        this.sink.setListener(listener);
    }

    @Override
    public boolean supportsOutput(int i, int i2) {
        return this.sink.supportsOutput(i, i2);
    }

    @Override
    public long getCurrentPositionUs(boolean z) {
        return this.sink.getCurrentPositionUs(z);
    }

    @Override
    public void configure(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6) throws AudioSink.ConfigurationException {
        this.sink.configure(i, i2, i3, i4, iArr, i5, i6);
    }

    @Override
    public void play() {
        this.sink.play();
    }

    @Override
    public void handleDiscontinuity() {
        this.sink.handleDiscontinuity();
    }

    @Override
    public boolean handleBuffer(ByteBuffer byteBuffer, long j) throws AudioSink.WriteException, AudioSink.InitializationException {
        return this.sink.handleBuffer(byteBuffer, j);
    }

    @Override
    public void playToEndOfStream() throws AudioSink.WriteException {
        this.sink.playToEndOfStream();
    }

    @Override
    public boolean isEnded() {
        return this.sink.isEnded();
    }

    @Override
    public boolean hasPendingData() {
        return this.sink.hasPendingData();
    }

    @Override
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.sink.setPlaybackParameters(playbackParameters);
    }

    @Override
    public PlaybackParameters getPlaybackParameters() {
        return this.sink.getPlaybackParameters();
    }

    @Override
    public void setAudioAttributes(AudioAttributes audioAttributes) {
        this.sink.setAudioAttributes(audioAttributes);
    }

    @Override
    public void setAudioSessionId(int i) {
        this.sink.setAudioSessionId(i);
    }

    @Override
    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        this.sink.setAuxEffectInfo(auxEffectInfo);
    }

    @Override
    public void enableTunnelingV21(int i) {
        this.sink.enableTunnelingV21(i);
    }

    @Override
    public void disableTunneling() {
        this.sink.disableTunneling();
    }

    @Override
    public void setVolume(float f) {
        this.sink.setVolume(f);
    }

    @Override
    public void pause() {
        this.sink.pause();
    }

    @Override
    public void flush() {
        this.sink.flush();
    }

    @Override
    public void reset() {
        this.sink.reset();
    }
}
