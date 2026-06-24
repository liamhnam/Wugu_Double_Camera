package com.bumptech.glide.manager;

import android.app.Activity;

final class FirstFrameWaiter implements FrameWaiter {
    @Override
    public void registerSelf(Activity activity) {
    }

    FirstFrameWaiter() {
    }
}
