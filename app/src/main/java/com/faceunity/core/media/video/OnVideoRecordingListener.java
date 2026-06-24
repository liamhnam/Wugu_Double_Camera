package com.faceunity.core.media.video;

import java.io.File;

public interface OnVideoRecordingListener {
    void onFinish(File file);

    void onPrepared();

    void onProcess(Long l);
}
