package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.C1041C;
import com.google.android.exoplayer2.extractor.SeekMap;

interface Seeker extends SeekMap {
    long getDataEndPosition();

    long getTimeUs(long j);

    public static class UnseekableSeeker extends SeekMap.Unseekable implements Seeker {
        @Override
        public long getDataEndPosition() {
            return -1L;
        }

        @Override
        public long getTimeUs(long j) {
            return 0L;
        }

        public UnseekableSeeker() {
            super(C1041C.TIME_UNSET);
        }
    }
}
