package com.google.android.exoplayer2.extractor;

public final class DummyExtractorOutput implements ExtractorOutput {
    @Override
    public void endTracks() {
    }

    @Override
    public void seekMap(SeekMap seekMap) {
    }

    @Override
    public TrackOutput track(int i, int i2) {
        return new DummyTrackOutput();
    }
}
