package com.faceunity.core.media.midea;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import java.io.FileInputStream;
import java.io.IOException;

public class MediaPlayerHelper {
    private boolean isPreparedMusic = false;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private MediaPlayerListener mMediaPlayerListener;
    private Handler mPlayerHandler;

    public interface MediaPlayerListener {
        void onCompletion();

        void onPause();

        void onStart();

        void onStop();
    }

    static void lambda$playMusic$0(MediaPlayer mediaPlayer, int i) {
    }

    public MediaPlayerHelper(Context context, MediaPlayerListener mediaPlayerListener) {
        this.mContext = context;
        this.mMediaPlayerListener = mediaPlayerListener;
        startPlayerThread();
    }

    public void playMusic(final String str, final boolean z) {
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m275x8792fe55(str, z);
            }
        });
    }

    void m275x8792fe55(String str, final boolean z) {
        this.isPreparedMusic = true;
        int iCheckFileType = checkFileType(str);
        if (iCheckFileType == 0) {
            return;
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            MediaPlayer mediaPlayer2 = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer2;
            mediaPlayer2.setAudioStreamType(3);
            this.mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public final void onBufferingUpdate(MediaPlayer mediaPlayer3, int i) {
                    MediaPlayerHelper.lambda$playMusic$0(mediaPlayer3, i);
                }
            });
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public final void onPrepared(MediaPlayer mediaPlayer3) {
                    this.f$0.m273xa43fb217(mediaPlayer3);
                }
            });
            this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public final void onCompletion(MediaPlayer mediaPlayer3) {
                    this.f$0.m274x95e95836(z, mediaPlayer3);
                }
            });
        } else {
            mediaPlayer.stop();
            this.mMediaPlayer.seekTo(0);
        }
        try {
            if (iCheckFileType == 1) {
                AssetFileDescriptor assetFileDescriptorOpenFd = this.mContext.getAssets().openFd(str);
                this.mMediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
                assetFileDescriptorOpenFd.close();
            } else {
                this.mMediaPlayer.setDataSource(str);
            }
            this.mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void m273xa43fb217(MediaPlayer mediaPlayer) {
        this.isPreparedMusic = false;
        this.mMediaPlayer.start();
        this.mMediaPlayerListener.onStart();
    }

    void m274x95e95836(boolean z, MediaPlayer mediaPlayer) {
        if (this.isPreparedMusic) {
            this.mMediaPlayerListener.onCompletion();
        } else if (z) {
            this.mMediaPlayer.seekTo(0);
            this.mMediaPlayer.start();
        } else {
            this.mMediaPlayerListener.onCompletion();
        }
    }

    public void pausePlay() {
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m272xd66924db();
            }
        });
    }

    void m272xd66924db() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            this.mMediaPlayerListener.onPause();
        }
    }

    public void replayMusic() {
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m277xd6f25706();
            }
        });
    }

    void m277xd6f25706() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.start();
            this.mMediaPlayerListener.onStart();
        }
    }

    public void stopPlay() {
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m278xeebc7e73();
            }
        });
    }

    void m278xeebc7e73() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayerListener.onStop();
        }
    }

    public void release() {
        this.mContext = null;
        this.mMediaPlayerListener = null;
        this.mPlayerHandler.removeCallbacksAndMessages(null);
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m276x4b57cd9b();
            }
        });
        stopPlayerThread();
    }

    void m276x4b57cd9b() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    public int getMusicCurrentPosition() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    private int checkFileType(String str) {
        int i = 0;
        try {
            this.mContext.getAssets().open(str).close();
            return 1;
        } catch (IOException unused) {
            i = 1;
            try {
                try {
                    new FileInputStream(str).close();
                } catch (IOException unused2) {
                }
                return 2;
            } catch (IOException unused3) {
                return i;
            }
        }
    }

    private void startPlayerThread() {
        HandlerThread handlerThread = new HandlerThread("music_filter");
        handlerThread.start();
        this.mPlayerHandler = new Handler(handlerThread.getLooper());
    }

    private void stopPlayerThread() {
        this.mPlayerHandler.getLooper().quitSafely();
        this.mPlayerHandler = null;
    }
}
