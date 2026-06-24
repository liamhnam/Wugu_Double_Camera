package com.brother.sdk.common.util;

import java.util.Collections;

public class Tool {
    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? Collections.emptyList() : iterable;
    }

    public static class ValueCoordinator {
        private int mInterpolateRange;
        private int mRangeStart;
        private int mValueMax;

        public ValueCoordinator(int i, int i2) {
            this.mValueMax = i;
            this.mRangeStart = 0;
            this.mInterpolateRange = i2;
        }

        public ValueCoordinator(int i, int i2, int i3) {
            this.mValueMax = i;
            this.mRangeStart = i2;
            this.mInterpolateRange = i3;
        }

        public int coordinateValueInRange(int i) {
            float f = i / this.mValueMax;
            if (f > 1.0f) {
                f = 1.0f;
            }
            return this.mRangeStart + ((int) (this.mInterpolateRange * f));
        }
    }
}
