package com.faceunity.core.infe;

import com.faceunity.core.listener.OnVideoPlayListener;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&¨\u0006\u000f"}, m1293d2 = {"Lcom/faceunity/core/infe/IVideoRenderer;", "", "onDestroy", "", "onPause", "onResume", "setFURenderSwitch", "isOpen", "", "setTransitionFrameCount", "count", "", "startMediaPlayer", "callback", "Lcom/faceunity/core/listener/OnVideoPlayListener;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface IVideoRenderer {
    void onDestroy();

    void onPause();

    void onResume();

    void setFURenderSwitch(boolean isOpen);

    void setTransitionFrameCount(int count);

    void startMediaPlayer(OnVideoPlayListener callback);
}
