package com.google.android.exoplayer2.metadata.id3;

import com.google.android.exoplayer2.metadata.Metadata;

public abstract class Id3Frame implements Metadata.Entry {

    public final String f578id;

    @Override
    public int describeContents() {
        return 0;
    }

    public Id3Frame(String str) {
        this.f578id = str;
    }

    public String toString() {
        return this.f578id;
    }
}
