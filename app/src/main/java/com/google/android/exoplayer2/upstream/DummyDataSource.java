package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import java.io.IOException;

public final class DummyDataSource implements DataSource {
    public static final DummyDataSource INSTANCE = new DummyDataSource();
    public static final DataSource.Factory FACTORY = new DataSource.Factory() {
        @Override
        public final DataSource createDataSource() {
            return DummyDataSource.$r8$lambda$9BpKLyGsZEvVQGK2JL1PVuvhcCc();
        }
    };

    public static DummyDataSource $r8$lambda$9BpKLyGsZEvVQGK2JL1PVuvhcCc() {
        return new DummyDataSource();
    }

    @Override
    public void addTransferListener(TransferListener transferListener) {
    }

    @Override
    public void close() {
    }

    @Override
    public Uri getUri() {
        return null;
    }

    private DummyDataSource() {
    }

    @Override
    public long open(DataSpec dataSpec) throws IOException {
        throw new IOException("Dummy source");
    }

    @Override
    public int read(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException();
    }
}
