package com.brother.sdk;

import android.app.Application;
import android.content.Context;
import com.brother.sdk.common.InvalidImplementException;
import java.lang.ref.WeakReference;

public class BrotherAndroidLib {
    private static BrotherAndroidLib mInstance;
    private final WeakReference<Context> mContext;

    private BrotherAndroidLib(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public static void initialize(Context context) {
        if (mInstance == null) {
            if (!(context instanceof Application)) {
                throw new IllegalArgumentException("Context must be Application context.");
            }
            mInstance = new BrotherAndroidLib(context);
        }
    }

    public static void terminate() {
        mInstance = null;
    }

    public static Context getAndroidContext() {
        BrotherAndroidLib brotherAndroidLib = mInstance;
        if (brotherAndroidLib == null || brotherAndroidLib.mContext.get() == null) {
            throw new InvalidImplementException("You must call BrotherAndroidLib::initialize(Application Context) at first in order to use Brother SDK.");
        }
        return mInstance.mContext.get();
    }
}
