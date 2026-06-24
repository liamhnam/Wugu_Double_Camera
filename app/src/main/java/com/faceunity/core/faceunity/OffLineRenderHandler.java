package com.faceunity.core.faceunity;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.faceunity.core.support.SDKController;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0003\u0013\u0014\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\bH\u0002J\u0006\u0010\u000e\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m1293d2 = {"Lcom/faceunity/core/faceunity/OffLineRenderHandler;", "", "()V", "mBackgroundThread", "Landroid/os/HandlerThread;", "mCustomGLHandler", "Lcom/faceunity/core/faceunity/OffLineRenderHandler$CustomGLHandler;", "onPause", "", "onResume", "queueEvent", "runnable", "Ljava/lang/Runnable;", "releaseGLThread", "requestRender", "setRenderer", "renderer", "Lcom/faceunity/core/faceunity/OffLineRenderHandler$Renderer;", "startGLThread", "Companion", "CustomGLHandler", "Renderer", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class OffLineRenderHandler {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile OffLineRenderHandler INSTANCE = null;
    private static final int RENDER_WHAT = 999;
    private HandlerThread mBackgroundThread;
    private CustomGLHandler mCustomGLHandler;

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m1293d2 = {"Lcom/faceunity/core/faceunity/OffLineRenderHandler$Renderer;", "", "onDrawFrame", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public interface Renderer {
        void onDrawFrame();
    }

    @JvmStatic
    public static final OffLineRenderHandler getInstance() {
        return INSTANCE.getInstance();
    }

    private OffLineRenderHandler() {
    }

    public OffLineRenderHandler(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, m1293d2 = {"Lcom/faceunity/core/faceunity/OffLineRenderHandler$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/faceunity/OffLineRenderHandler;", "RENDER_WHAT", "", "getInstance", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final OffLineRenderHandler getInstance() {
            if (OffLineRenderHandler.INSTANCE == null) {
                synchronized (OffLineRenderHandler.class) {
                    if (OffLineRenderHandler.INSTANCE == null) {
                        OffLineRenderHandler.INSTANCE = new OffLineRenderHandler(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            OffLineRenderHandler offLineRenderHandler = OffLineRenderHandler.INSTANCE;
            if (offLineRenderHandler == null) {
                Intrinsics.throwNpe();
            }
            return offLineRenderHandler;
        }
    }

    public final void queueEvent(Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        Message message = new Message();
        message.obj = runnable;
        CustomGLHandler customGLHandler = this.mCustomGLHandler;
        if (customGLHandler != null) {
            customGLHandler.sendMessage(message);
        }
    }

    public final void requestRender() {
        Message message = new Message();
        message.what = 999;
        CustomGLHandler customGLHandler = this.mCustomGLHandler;
        if (customGLHandler != null) {
            customGLHandler.removeMessages(999);
        }
        CustomGLHandler customGLHandler2 = this.mCustomGLHandler;
        if (customGLHandler2 != null) {
            customGLHandler2.sendMessage(message);
        }
    }

    public final void onResume() {
        startGLThread();
    }

    public final void onPause() {
        releaseGLThread();
    }

    public final void setRenderer(Renderer renderer) {
        Intrinsics.checkParameterIsNotNull(renderer, "renderer");
        CustomGLHandler customGLHandler = this.mCustomGLHandler;
        if (customGLHandler != null) {
            customGLHandler.setRenderer(renderer);
        }
    }

    private final void startGLThread() {
        if (this.mBackgroundThread != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("OffLineRenderHandler");
        this.mBackgroundThread = handlerThread;
        handlerThread.start();
        HandlerThread handlerThread2 = this.mBackgroundThread;
        if (handlerThread2 == null) {
            Intrinsics.throwNpe();
        }
        Looper looper = handlerThread2.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper, "mBackgroundThread!!.looper");
        CustomGLHandler customGLHandler = new CustomGLHandler(looper);
        this.mCustomGLHandler = customGLHandler;
        customGLHandler.post(new Runnable() {
            @Override
            public final void run() {
                SDKController.INSTANCE.createEGLContext$fu_core_all_featureRelease();
            }
        });
    }

    private final void releaseGLThread() {
        if (this.mBackgroundThread == null) {
            return;
        }
        CustomGLHandler customGLHandler = this.mCustomGLHandler;
        if (customGLHandler != null) {
            customGLHandler.removeCallbacksAndMessages(0);
        }
        CustomGLHandler customGLHandler2 = this.mCustomGLHandler;
        if (customGLHandler2 != null) {
            customGLHandler2.post(new Runnable() {
                @Override
                public final void run() {
                    SDKController.INSTANCE.releaseEGLContext$fu_core_all_featureRelease();
                }
            });
        }
        HandlerThread handlerThread = this.mBackgroundThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        try {
            HandlerThread handlerThread2 = this.mBackgroundThread;
            if (handlerThread2 != null) {
                handlerThread2.join();
            }
            this.mBackgroundThread = null;
            this.mCustomGLHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, m1293d2 = {"Lcom/faceunity/core/faceunity/OffLineRenderHandler$CustomGLHandler;", "Landroid/os/Handler;", "looper", "Landroid/os/Looper;", "(Landroid/os/Looper;)V", "renderer", "Lcom/faceunity/core/faceunity/OffLineRenderHandler$Renderer;", "getRenderer", "()Lcom/faceunity/core/faceunity/OffLineRenderHandler$Renderer;", "setRenderer", "(Lcom/faceunity/core/faceunity/OffLineRenderHandler$Renderer;)V", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    private static final class CustomGLHandler extends Handler {
        private Renderer renderer;

        public CustomGLHandler(Looper looper) {
            super(looper);
            Intrinsics.checkParameterIsNotNull(looper, "looper");
        }

        public final Renderer getRenderer() {
            return this.renderer;
        }

        public final void setRenderer(Renderer renderer) {
            this.renderer = renderer;
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg != null) {
                if (msg.what == 999) {
                    Renderer renderer = this.renderer;
                    if (renderer != null) {
                        renderer.onDrawFrame();
                        return;
                    }
                    return;
                }
                if (msg.obj instanceof Runnable) {
                    Object obj = msg.obj;
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Runnable");
                    }
                    ((Runnable) obj).run();
                }
            }
        }
    }
}
