package com.wugu.doublecamera.widget;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Looper;
import android.text.TextUtils;
import com.p020hp.jipp.model.IdentifyAction;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.utils.LoggerUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class SoundHelper {
    private int currentMusicIndex;
    private MediaPlayer mediaPlayer;
    private String[] musicNameList;
    private int peChangeFrameSoundTime;
    private long peLastPlayChangeFrameMillis;
    private final HashMap<Integer, Integer> soundMap;
    private SoundPool soundPool;

    private static class Holder {
        public static SoundHelper instance = new SoundHelper();

        private Holder() {
        }
    }

    private SoundHelper() {
        this.soundPool = null;
        this.soundMap = new HashMap<>();
    }

    public static SoundHelper getInstance() {
        return Holder.instance;
    }

    public void updateSoundRes() {
        this.soundMap.clear();
        SoundPool soundPool = this.soundPool;
        if (soundPool != null) {
            soundPool.release();
        }
        this.soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        mapPutSound(1);
        mapPutSound(5);
        mapPutSound(2);
        mapPutSound(3);
        mapPutSound(6);
        mapPutSound(4);
        mapPutSound(8);
        mapPutSound(7);
        mapPutSound(9);
        mapPutSound(10);
        mapPutSound(11);
        mapPutSound(12);
        mapPutSound(13);
        mapPutSound(14);
        mapPutSound(15);
        mapPutSound(16);
        mapPutSound(17);
    }

    public void playSoundEffect(int i) {
        if (this.soundMap.containsKey(Integer.valueOf(i))) {
            if (i == 14) {
                if (System.currentTimeMillis() - this.peLastPlayChangeFrameMillis < this.peChangeFrameSoundTime) {
                    return;
                } else {
                    this.peLastPlayChangeFrameMillis = System.currentTimeMillis();
                }
            }
            Integer num = this.soundMap.get(Integer.valueOf(i));
            if (num == null) {
                return;
            }
            this.soundPool.play(num.intValue(), 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void releaseAllSoundRes() {
        this.soundMap.clear();
        SoundPool soundPool = this.soundPool;
        if (soundPool != null) {
            soundPool.release();
        }
    }

    public void releaseMusicPlayer() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    public void resetSoundRes() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(null);
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        if (this.musicNameList != null) {
            this.musicNameList = null;
        }
        this.currentMusicIndex = 0;
        releaseAllSoundRes();
        ThreadClock.sleep(1000L);
        startPlayBgm();
        updateSoundRes();
    }

    private int getMediaDuration(String str) {
        if (!str.endsWith("mp3") && !str.endsWith("ogg")) {
            return 8000;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                try {
                    mediaMetadataRetriever.setDataSource(str);
                    String strExtractMetadata = mediaMetadataRetriever.extractMetadata(9);
                    if (strExtractMetadata != null) {
                        mediaMetadataRetriever.release();
                        int i = Integer.parseInt(strExtractMetadata);
                        try {
                            mediaMetadataRetriever.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return i;
                    }
                    mediaMetadataRetriever.release();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    mediaMetadataRetriever.release();
                }
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return 8000;
    }

    private void mapPutSound(int i) {
        String soundPathByType = DbHelper.getInstance().getSoundPathByType(i);
        if (TextUtils.isEmpty(soundPathByType)) {
            return;
        }
        if (i == 14) {
            this.peChangeFrameSoundTime = getMediaDuration(soundPathByType);
        }
        try {
            File file = new File(soundPathByType);
            FileInputStream fileInputStream = new FileInputStream(file);
            int iLoad = this.soundPool.load(fileInputStream.getFD(), 0L, file.length(), 1);
            if (iLoad == 0) {
                return;
            }
            this.soundMap.put(Integer.valueOf(i), Integer.valueOf(iLoad));
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startPlayBgm() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if ((mediaPlayer == null || mediaPlayer.getCurrentPosition() <= 0 || !this.mediaPlayer.isPlaying()) && App.mIsIdle) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                ThreadHelper.getInstance().createThread(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.playBgm();
                    }
                });
            } else {
                playBgm();
            }
        }
    }

    public void playBgm() {
        if (TextUtils.isEmpty(SpManager.getInstance().getBgmPaths())) {
            return;
        }
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null && mediaPlayer.getCurrentPosition() > 0) {
            this.mediaPlayer.start();
            return;
        }
        if (this.musicNameList == null) {
            this.musicNameList = SpManager.getInstance().getBgmPaths().split(",");
        }
        if (this.mediaPlayer == null) {
            this.currentMusicIndex = 0;
            playMusic();
        }
    }

    private void playMusic() {
        String[] strArr = this.musicNameList;
        if (strArr == null) {
            return;
        }
        String strTrim = strArr[this.currentMusicIndex].trim();
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        this.mediaPlayer = new MediaPlayer();
        File file = new File(strTrim);
        LoggerUtil.m1181d(IdentifyAction.sound, "playMusic " + strTrim + " " + file.exists());
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                this.mediaPlayer.reset();
                this.mediaPlayer.setDataSource(fileInputStream.getFD());
                this.mediaPlayer.prepare();
                this.mediaPlayer.start();
                this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public final void onCompletion(MediaPlayer mediaPlayer2) {
                        this.f$0.m1727lambda$playMusic$1$comwugudoublecamerawidgetSoundHelper(mediaPlayer2);
                    }
                });
                return;
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
                return;
            }
        }
        int i = this.currentMusicIndex + 1;
        this.currentMusicIndex = i;
        if (i >= this.musicNameList.length) {
            this.currentMusicIndex = 0;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1728lambda$playMusic$2$comwugudoublecamerawidgetSoundHelper();
                }
            });
        }
    }

    void m1727lambda$playMusic$1$comwugudoublecamerawidgetSoundHelper(MediaPlayer mediaPlayer) {
        int i = this.currentMusicIndex + 1;
        this.currentMusicIndex = i;
        if (i >= this.musicNameList.length) {
            this.currentMusicIndex = 0;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1726lambda$playMusic$0$comwugudoublecamerawidgetSoundHelper();
                }
            });
        }
    }

    void m1726lambda$playMusic$0$comwugudoublecamerawidgetSoundHelper() {
        ThreadClock.sleep(1000L);
        playMusic();
    }

    void m1728lambda$playMusic$2$comwugudoublecamerawidgetSoundHelper() {
        ThreadClock.sleep(1000L);
        playMusic();
    }

    public void stopPlayBgm() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mediaPlayer.pause();
    }
}
