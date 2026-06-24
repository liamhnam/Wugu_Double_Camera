package com.bumptech.glide.manager;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

final class FirstFrameAndAfterTrimMemoryWaiter implements FrameWaiter, ComponentCallbacks2 {
    @Override
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override
    public void onTrimMemory(int i) {
    }

    @Override
    public void registerSelf(Activity activity) {
    }

    FirstFrameAndAfterTrimMemoryWaiter() {
    }

    @Override
    public void onLowMemory() {
        onTrimMemory(20);
    }
}
