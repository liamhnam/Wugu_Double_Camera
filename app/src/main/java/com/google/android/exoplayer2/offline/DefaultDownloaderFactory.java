package com.google.android.exoplayer2.offline;

import android.net.Uri;
import java.lang.reflect.Constructor;
import java.util.List;

public class DefaultDownloaderFactory implements DownloaderFactory {
    private static final Constructor<? extends Downloader> DASH_DOWNLOADER_CONSTRUCTOR;
    private static final Constructor<? extends Downloader> HLS_DOWNLOADER_CONSTRUCTOR;
    private static final Constructor<? extends Downloader> SS_DOWNLOADER_CONSTRUCTOR;
    private final DownloaderConstructorHelper downloaderConstructorHelper;

    static {
        Constructor<? extends Downloader> downloaderConstructor;
        Constructor<? extends Downloader> downloaderConstructor2;
        Constructor<? extends Downloader> downloaderConstructor3 = null;
        try {
            downloaderConstructor = getDownloaderConstructor(Class.forName("com.google.android.exoplayer2.source.dash.offline.DashDownloader"));
        } catch (ClassNotFoundException unused) {
            downloaderConstructor = null;
        }
        DASH_DOWNLOADER_CONSTRUCTOR = downloaderConstructor;
        try {
            downloaderConstructor2 = getDownloaderConstructor(Class.forName("com.google.android.exoplayer2.source.hls.offline.HlsDownloader"));
        } catch (ClassNotFoundException unused2) {
            downloaderConstructor2 = null;
        }
        HLS_DOWNLOADER_CONSTRUCTOR = downloaderConstructor2;
        try {
            downloaderConstructor3 = getDownloaderConstructor(Class.forName("com.google.android.exoplayer2.source.smoothstreaming.offline.SsDownloader"));
        } catch (ClassNotFoundException unused3) {
        }
        SS_DOWNLOADER_CONSTRUCTOR = downloaderConstructor3;
    }

    public DefaultDownloaderFactory(DownloaderConstructorHelper downloaderConstructorHelper) {
        this.downloaderConstructorHelper = downloaderConstructorHelper;
    }

    @Override
    public Downloader createDownloader(DownloadRequest downloadRequest) {
        String str = downloadRequest.type;
        str.hashCode();
        switch (str) {
            case "ss":
                return createDownloader(downloadRequest, SS_DOWNLOADER_CONSTRUCTOR);
            case "hls":
                return createDownloader(downloadRequest, HLS_DOWNLOADER_CONSTRUCTOR);
            case "dash":
                return createDownloader(downloadRequest, DASH_DOWNLOADER_CONSTRUCTOR);
            case "progressive":
                return new ProgressiveDownloader(downloadRequest.uri, downloadRequest.customCacheKey, this.downloaderConstructorHelper);
            default:
                throw new IllegalArgumentException("Unsupported type: " + downloadRequest.type);
        }
    }

    private Downloader createDownloader(DownloadRequest downloadRequest, Constructor<? extends Downloader> constructor) {
        if (constructor == null) {
            throw new IllegalStateException("Module missing for: " + downloadRequest.type);
        }
        try {
            return constructor.newInstance(downloadRequest.uri, downloadRequest.streamKeys, this.downloaderConstructorHelper);
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate downloader for: " + downloadRequest.type, e);
        }
    }

    private static Constructor<? extends Downloader> getDownloaderConstructor(Class<?> cls) {
        try {
            return cls.asSubclass(Downloader.class).getConstructor(Uri.class, List.class, DownloaderConstructorHelper.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Downloader constructor missing", e);
        }
    }
}
