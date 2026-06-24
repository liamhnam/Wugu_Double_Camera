package com.faceunity.core.glview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES30;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import com.faceunity.core.utils.FULogger;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GLTextureView extends TextureView implements TextureView.SurfaceTextureListener, View.OnLayoutChangeListener {
    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    private static final boolean LOG_ATTACH_DETACH = false;
    private static final boolean LOG_EGL = false;
    private static final boolean LOG_PAUSE_RESUME = false;
    private static final boolean LOG_RENDERER = false;
    private static final boolean LOG_RENDERER_DRAW_FRAME = false;
    private static final boolean LOG_SURFACE = false;
    private static final boolean LOG_THREADS = false;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;
    private static final String TAG = "GLTextureView";
    private static final GLThreadManager glThreadManager = new GLThreadManager();
    private int EGLContextClientVersion;
    private EGLContextFactory EGLContextFactory;
    private int debugFlags;
    private boolean detached;
    private EGLConfigChooser eglConfigChooser;
    private EGLWindowSurfaceFactory eglWindowSurfaceFactory;
    private GLThread glThread;
    private final WeakReference<GLTextureView> mThisWeakRef;
    private boolean preserveEGLContextOnPause;
    private Renderer renderer;
    private List<TextureView.SurfaceTextureListener> surfaceTextureListeners;

    public interface EGLConfigChooser {
        EGLConfig chooseConfig(EGLDisplay eGLDisplay);
    }

    public interface EGLContextFactory {
        EGLContext createContext(EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    public interface EGLWindowSurfaceFactory {
        EGLSurface createWindowSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void destroySurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    public interface Renderer {
        void onDrawFrame();

        void onSurfaceChanged(int i, int i2);

        void onSurfaceCreated(EGLConfig eGLConfig);
    }

    public GLTextureView(Context context) {
        super(context);
        this.mThisWeakRef = new WeakReference<>(this);
        this.surfaceTextureListeners = new ArrayList();
        init();
    }

    public GLTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mThisWeakRef = new WeakReference<>(this);
        this.surfaceTextureListeners = new ArrayList();
        init();
    }

    public GLTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mThisWeakRef = new WeakReference<>(this);
        this.surfaceTextureListeners = new ArrayList();
        init();
    }

    protected void finalize() throws Throwable {
        try {
            GLThread gLThread = this.glThread;
            if (gLThread != null) {
                gLThread.requestExitAndWait();
            }
        } finally {
            super.finalize();
        }
    }

    private void init() {
        setSurfaceTextureListener(this);
    }

    @Override
    public void setBackgroundDrawable(Drawable drawable) {
        FULogger.m295i(TAG, "setBackgroundDrawable pre");
    }

    public void setDebugFlags(int i) {
        this.debugFlags = i;
    }

    public int getDebugFlags() {
        return this.debugFlags;
    }

    public void setPreserveEGLContextOnPause(boolean z) {
        this.preserveEGLContextOnPause = z;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.preserveEGLContextOnPause;
    }

    public void setRenderer(Renderer renderer) {
        checkRenderThreadState();
        if (this.eglConfigChooser == null) {
            this.eglConfigChooser = new SimpleEGLConfigChooser(true);
        }
        if (this.EGLContextFactory == null) {
            this.EGLContextFactory = new DefaultContextFactory();
        }
        if (this.eglWindowSurfaceFactory == null) {
            this.eglWindowSurfaceFactory = new DefaultWindowSurfaceFactory();
        }
        this.renderer = renderer;
        GLThread gLThread = new GLThread(this.mThisWeakRef);
        this.glThread = gLThread;
        gLThread.start();
    }

    public void setEGLContextFactory(EGLContextFactory eGLContextFactory) {
        checkRenderThreadState();
        this.EGLContextFactory = eGLContextFactory;
    }

    public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory eGLWindowSurfaceFactory) {
        checkRenderThreadState();
        this.eglWindowSurfaceFactory = eGLWindowSurfaceFactory;
    }

    public void setEGLConfigChooser(EGLConfigChooser eGLConfigChooser) {
        checkRenderThreadState();
        this.eglConfigChooser = eGLConfigChooser;
    }

    public void setEGLConfigChooser(boolean z) {
        setEGLConfigChooser(new SimpleEGLConfigChooser(z));
    }

    public void setEGLConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
        setEGLConfigChooser(new ComponentSizeChooser(i, i2, i3, i4, i5, i6));
    }

    public void setEGLContextClientVersion(int i) {
        checkRenderThreadState();
        this.EGLContextClientVersion = i;
    }

    public void setRenderMode(int i) {
        this.glThread.setRenderMode(i);
    }

    public int getRenderMode() {
        return this.glThread.getRenderMode();
    }

    public void requestRender() {
        this.glThread.requestRender();
    }

    public void surfaceCreated(SurfaceTexture surfaceTexture) {
        this.glThread.surfaceCreated();
    }

    public void surfaceDestroyed(SurfaceTexture surfaceTexture) {
        this.glThread.surfaceDestroyed();
    }

    public void surfaceChanged(SurfaceTexture surfaceTexture, int i, int i2, int i3) {
        this.glThread.onWindowResize(i2, i3);
    }

    public void onPause() {
        this.glThread.onPause();
    }

    public void onResume() {
        this.glThread.onResume();
    }

    public void queueEvent(Runnable runnable) {
        this.glThread.queueEvent(runnable);
    }

    public void removeEvent(Runnable runnable) {
        this.glThread.removeEvent(runnable);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.detached && this.renderer != null) {
            GLThread gLThread = this.glThread;
            int renderMode = gLThread != null ? gLThread.getRenderMode() : 1;
            GLThread gLThread2 = new GLThread(this.mThisWeakRef);
            this.glThread = gLThread2;
            if (renderMode != 1) {
                gLThread2.setRenderMode(renderMode);
            }
            this.glThread.start();
        }
        this.detached = false;
    }

    @Override
    protected void onDetachedFromWindow() {
        GLThread gLThread = this.glThread;
        if (gLThread != null) {
            gLThread.requestExitAndWait();
        }
        this.detached = true;
        super.onDetachedFromWindow();
    }

    @Override
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        surfaceChanged(getSurfaceTexture(), 0, i3 - i, i4 - i2);
    }

    public void addSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        this.surfaceTextureListeners.add(surfaceTextureListener);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        surfaceCreated(surfaceTexture);
        surfaceChanged(surfaceTexture, 0, i, i2);
        Iterator<TextureView.SurfaceTextureListener> it = this.surfaceTextureListeners.iterator();
        while (it.hasNext()) {
            it.next().onSurfaceTextureAvailable(surfaceTexture, i, i2);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        surfaceChanged(surfaceTexture, 0, i, i2);
        Iterator<TextureView.SurfaceTextureListener> it = this.surfaceTextureListeners.iterator();
        while (it.hasNext()) {
            it.next().onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        surfaceDestroyed(surfaceTexture);
        Iterator<TextureView.SurfaceTextureListener> it = this.surfaceTextureListeners.iterator();
        while (it.hasNext()) {
            it.next().onSurfaceTextureDestroyed(surfaceTexture);
        }
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        Iterator<TextureView.SurfaceTextureListener> it = this.surfaceTextureListeners.iterator();
        while (it.hasNext()) {
            it.next().onSurfaceTextureUpdated(surfaceTexture);
        }
    }

    private class DefaultContextFactory implements EGLContextFactory {
        private int EGL_CONTEXT_CLIENT_VERSION;

        private DefaultContextFactory() {
            this.EGL_CONTEXT_CLIENT_VERSION = 12440;
        }

        @Override
        public EGLContext createContext(EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {this.EGL_CONTEXT_CLIENT_VERSION, GLTextureView.this.EGLContextClientVersion, 12344};
            EGLContext eGLContext = EGL14.EGL_NO_CONTEXT;
            if (GLTextureView.this.EGLContextClientVersion == 0) {
                iArr = null;
            }
            return EGL14.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr, 0);
        }

        @Override
        public void destroyContext(EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (EGL14.eglDestroyContext(eGLDisplay, eGLContext)) {
                return;
            }
            Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
            EglHelper.throwEglException("eglDestroyContex", EGL14.eglGetError());
        }
    }

    private static class DefaultWindowSurfaceFactory implements EGLWindowSurfaceFactory {
        private DefaultWindowSurfaceFactory() {
        }

        @Override
        public EGLSurface createWindowSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return EGL14.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, new int[]{12344}, 0);
            } catch (IllegalArgumentException e) {
                Log.e(GLTextureView.TAG, "eglCreateWindowSurface", e);
                return null;
            }
        }

        @Override
        public void destroySurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            EGL14.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    private abstract class BaseConfigChooser implements EGLConfigChooser {
        protected int[] mConfigSpec;

        abstract EGLConfig chooseConfig(EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public BaseConfigChooser(int[] iArr) {
            this.mConfigSpec = filterConfigSpec(iArr);
        }

        @Override
        public EGLConfig chooseConfig(EGLDisplay eGLDisplay) {
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            if (!EGL14.eglChooseConfig(eGLDisplay, this.mConfigSpec, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
                throw new IllegalArgumentException("eglChooseConfig failed");
            }
            EGLConfig eGLConfigChooseConfig = chooseConfig(eGLDisplay, eGLConfigArr);
            if (eGLConfigChooseConfig != null) {
                return eGLConfigChooseConfig;
            }
            throw new IllegalArgumentException("No config chosen");
        }

        private int[] filterConfigSpec(int[] iArr) {
            if (GLTextureView.this.EGLContextClientVersion != 2) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[length + 2];
            int i = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i);
            iArr2[i] = 12352;
            iArr2[length] = 4;
            iArr2[length + 1] = 12344;
            return iArr2;
        }
    }

    private class ComponentSizeChooser extends BaseConfigChooser {
        protected int alphaSize;
        protected int blueSize;
        protected int depthSize;
        protected int greenSize;
        protected int redSize;
        protected int stencilSize;
        private int[] value;

        public ComponentSizeChooser(int i, int i2, int i3, int i4, int i5, int i6) {
            super(new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344});
            this.value = new int[1];
            this.redSize = i;
            this.greenSize = i2;
            this.blueSize = i3;
            this.alphaSize = i4;
            this.depthSize = i5;
            this.stencilSize = i6;
        }

        @Override
        public EGLConfig chooseConfig(EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int iFindConfigAttrib = findConfigAttrib(eGLDisplay, eGLConfig, 12325, 0);
                int iFindConfigAttrib2 = findConfigAttrib(eGLDisplay, eGLConfig, 12326, 0);
                if (iFindConfigAttrib >= this.depthSize && iFindConfigAttrib2 >= this.stencilSize) {
                    int iFindConfigAttrib3 = findConfigAttrib(eGLDisplay, eGLConfig, 12324, 0);
                    int iFindConfigAttrib4 = findConfigAttrib(eGLDisplay, eGLConfig, 12323, 0);
                    int iFindConfigAttrib5 = findConfigAttrib(eGLDisplay, eGLConfig, 12322, 0);
                    int iFindConfigAttrib6 = findConfigAttrib(eGLDisplay, eGLConfig, 12321, 0);
                    if (iFindConfigAttrib3 == this.redSize && iFindConfigAttrib4 == this.greenSize && iFindConfigAttrib5 == this.blueSize && iFindConfigAttrib6 == this.alphaSize) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int findConfigAttrib(EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return EGL14.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.value, 0) ? this.value[0] : i2;
        }
    }

    private class SimpleEGLConfigChooser extends ComponentSizeChooser {
        public SimpleEGLConfigChooser(boolean z) {
            super(8, 8, 8, 8, z ? 16 : 0, 0);
        }
    }

    private static class EglHelper {
        EGLContext EGLContext;
        EGLConfig eglConfig;
        EGLDisplay eglDisplay;
        EGLSurface eglSurface;
        private WeakReference<GLTextureView> glTextureViewWeakRef;

        public EglHelper(WeakReference<GLTextureView> weakReference) {
            this.glTextureViewWeakRef = weakReference;
        }

        public void start() {
            EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
            this.eglDisplay = eGLDisplayEglGetDisplay;
            if (eGLDisplayEglGetDisplay == EGL14.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            int[] iArr = new int[2];
            if (!EGL14.eglInitialize(this.eglDisplay, iArr, 0, iArr, 1)) {
                throw new RuntimeException("eglInitialize failed");
            }
            GLTextureView gLTextureView = this.glTextureViewWeakRef.get();
            if (gLTextureView != null) {
                this.eglConfig = gLTextureView.eglConfigChooser.chooseConfig(this.eglDisplay);
                this.EGLContext = gLTextureView.EGLContextFactory.createContext(this.eglDisplay, this.eglConfig);
            } else {
                this.eglConfig = null;
                this.EGLContext = null;
            }
            EGLContext eGLContext = this.EGLContext;
            if (eGLContext == null || eGLContext == EGL14.EGL_NO_CONTEXT) {
                this.EGLContext = null;
                throwEglException("createContext");
            }
            this.eglSurface = null;
        }

        public boolean createSurface() {
            if (this.eglDisplay == null) {
                throw new RuntimeException("eglDisplay not initialized");
            }
            if (this.eglConfig == null) {
                throw new RuntimeException("eglConfig not initialized");
            }
            destroySurfaceImp();
            GLTextureView gLTextureView = this.glTextureViewWeakRef.get();
            if (gLTextureView != null) {
                this.eglSurface = gLTextureView.eglWindowSurfaceFactory.createWindowSurface(this.eglDisplay, this.eglConfig, gLTextureView.getSurfaceTexture());
            } else {
                this.eglSurface = null;
            }
            EGLSurface eGLSurface = this.eglSurface;
            if (eGLSurface == null || eGLSurface == EGL14.EGL_NO_SURFACE) {
                if (EGL14.eglGetError() == 12299) {
                    Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                }
                return false;
            }
            EGLDisplay eGLDisplay = this.eglDisplay;
            EGLSurface eGLSurface2 = this.eglSurface;
            if (EGL14.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.EGLContext)) {
                return true;
            }
            logEglErrorAsWarning("EGLHelper", "eglMakeCurrent", EGL14.eglGetError());
            return false;
        }

        public int swap() {
            if (EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface)) {
                return 12288;
            }
            return EGL14.eglGetError();
        }

        public void destroySurface() {
            destroySurfaceImp();
        }

        private void destroySurfaceImp() {
            EGLSurface eGLSurface = this.eglSurface;
            if (eGLSurface == null || eGLSurface == EGL14.EGL_NO_SURFACE) {
                return;
            }
            EGL14.eglMakeCurrent(this.eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            GLTextureView gLTextureView = this.glTextureViewWeakRef.get();
            if (gLTextureView != null) {
                gLTextureView.eglWindowSurfaceFactory.destroySurface(this.eglDisplay, this.eglSurface);
            }
            this.eglSurface = null;
        }

        public void finish() {
            if (this.EGLContext != null) {
                GLTextureView gLTextureView = this.glTextureViewWeakRef.get();
                if (gLTextureView != null) {
                    gLTextureView.EGLContextFactory.destroyContext(this.eglDisplay, this.EGLContext);
                }
                this.EGLContext = null;
            }
            EGLDisplay eGLDisplay = this.eglDisplay;
            if (eGLDisplay != null) {
                EGL14.eglTerminate(eGLDisplay);
                this.eglDisplay = null;
            }
        }

        private void throwEglException(String str) {
            throwEglException(str, EGL14.eglGetError());
        }

        public static void throwEglException(String str, int i) {
            throw new RuntimeException(formatEglError(str, i));
        }

        public static void logEglErrorAsWarning(String str, String str2, int i) {
            Log.w(str, formatEglError(str2, i));
        }

        public static String formatEglError(String str, int i) {
            return str + " failed: " + i;
        }
    }

    static class GLThread extends Thread {
        private EglHelper eglHelper;
        private boolean exited;
        private WeakReference<GLTextureView> glTextureViewWeakRef;
        private boolean hasSurface;
        private boolean haveEGLContext;
        private boolean haveEglSurface;
        private boolean paused;
        private boolean renderComplete;
        private boolean requestPaused;
        private boolean shouldExit;
        private boolean shouldReleaseEGLContext;
        private boolean surfaceIsBad;
        private boolean waitingForSurface;
        private ArrayList<Runnable> eventQueue = new ArrayList<>();
        private boolean sizeChanged = true;
        private int width = 0;
        private int height = 0;
        private boolean requestRender = true;
        private int renderMode = 1;

        GLThread(WeakReference<GLTextureView> weakReference) {
            this.glTextureViewWeakRef = weakReference;
        }

        @Override
        public void run() {
            setName("GLThread " + getId());
            try {
                guardedRun();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                GLTextureView.glThreadManager.threadExiting(this);
                throw th;
            }
            GLTextureView.glThreadManager.threadExiting(this);
        }

        private void stopEglSurfaceLocked() {
            if (this.haveEglSurface) {
                this.haveEglSurface = false;
                this.eglHelper.destroySurface();
            }
        }

        private void stopEGLContextLocked() {
            if (this.haveEGLContext) {
                this.eglHelper.finish();
                this.haveEGLContext = false;
                GLTextureView.glThreadManager.releaseEGLContextLocked(this);
            }
        }

        private void guardedRun() throws InterruptedException {
            boolean z;
            this.eglHelper = new EglHelper(this.glTextureViewWeakRef);
            this.haveEGLContext = false;
            this.haveEglSurface = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            boolean z7 = false;
            boolean z8 = false;
            int i = 0;
            int i2 = 0;
            boolean z9 = false;
            while (true) {
                Runnable runnableRemove = null;
                while (true) {
                    try {
                        synchronized (GLTextureView.glThreadManager) {
                            while (!this.shouldExit) {
                                if (!this.eventQueue.isEmpty()) {
                                    runnableRemove = this.eventQueue.remove(0);
                                } else {
                                    boolean z10 = this.paused;
                                    boolean z11 = this.requestPaused;
                                    if (z10 != z11) {
                                        this.paused = z11;
                                        GLTextureView.glThreadManager.notifyAll();
                                    } else {
                                        z11 = false;
                                    }
                                    if (this.shouldReleaseEGLContext) {
                                        stopEglSurfaceLocked();
                                        stopEGLContextLocked();
                                        this.shouldReleaseEGLContext = false;
                                        z4 = true;
                                    }
                                    if (z2) {
                                        stopEglSurfaceLocked();
                                        stopEGLContextLocked();
                                        z2 = false;
                                    }
                                    if (z11 && this.haveEglSurface) {
                                        stopEglSurfaceLocked();
                                    }
                                    if (z11 && this.haveEGLContext) {
                                        GLTextureView gLTextureView = this.glTextureViewWeakRef.get();
                                        if (!(gLTextureView == null ? false : gLTextureView.preserveEGLContextOnPause) || GLTextureView.glThreadManager.shouldReleaseEGLContextWhenPausing()) {
                                            stopEGLContextLocked();
                                        }
                                    }
                                    if (z11 && GLTextureView.glThreadManager.shouldTerminateEGLWhenPausing()) {
                                        this.eglHelper.finish();
                                    }
                                    if (!this.hasSurface && !this.waitingForSurface) {
                                        if (this.haveEglSurface) {
                                            stopEglSurfaceLocked();
                                        }
                                        this.waitingForSurface = true;
                                        this.surfaceIsBad = false;
                                        GLTextureView.glThreadManager.notifyAll();
                                    }
                                    if (this.hasSurface && this.waitingForSurface) {
                                        this.waitingForSurface = false;
                                        GLTextureView.glThreadManager.notifyAll();
                                    }
                                    if (z3) {
                                        this.renderComplete = true;
                                        GLTextureView.glThreadManager.notifyAll();
                                        z3 = false;
                                        z9 = false;
                                    }
                                    if (readyToDraw()) {
                                        if (!this.haveEGLContext) {
                                            if (z4) {
                                                z4 = false;
                                            } else if (GLTextureView.glThreadManager.tryAcquireEGLContextLocked(this)) {
                                                try {
                                                    this.eglHelper.start();
                                                    this.haveEGLContext = true;
                                                    GLTextureView.glThreadManager.notifyAll();
                                                    z5 = true;
                                                } catch (RuntimeException e) {
                                                    GLTextureView.glThreadManager.releaseEGLContextLocked(this);
                                                    throw e;
                                                }
                                            }
                                        }
                                        if (this.haveEGLContext && !this.haveEglSurface) {
                                            this.haveEglSurface = true;
                                            z6 = true;
                                            z7 = true;
                                            z8 = true;
                                        }
                                        if (this.haveEglSurface) {
                                            if (this.sizeChanged) {
                                                int i3 = this.width;
                                                int i4 = this.height;
                                                this.sizeChanged = false;
                                                i = i3;
                                                i2 = i4;
                                                z6 = true;
                                                z8 = true;
                                                z9 = true;
                                            }
                                            this.requestRender = false;
                                            GLTextureView.glThreadManager.notifyAll();
                                        }
                                    }
                                    GLTextureView.glThreadManager.wait();
                                }
                            }
                            synchronized (GLTextureView.glThreadManager) {
                                stopEglSurfaceLocked();
                                stopEGLContextLocked();
                            }
                            return;
                        }
                        if (runnableRemove != null) {
                            break;
                        }
                        if (z6) {
                            if (this.eglHelper.createSurface()) {
                                z6 = false;
                            } else {
                                synchronized (GLTextureView.glThreadManager) {
                                    this.surfaceIsBad = true;
                                    GLTextureView.glThreadManager.notifyAll();
                                }
                            }
                        }
                        if (z7) {
                            GLTextureView.glThreadManager.checkGLDriver();
                            z7 = false;
                        }
                        if (z5) {
                            GLTextureView gLTextureView2 = this.glTextureViewWeakRef.get();
                            if (gLTextureView2 != null) {
                                gLTextureView2.renderer.onSurfaceCreated(this.eglHelper.eglConfig);
                            }
                            z5 = false;
                        }
                        if (z8) {
                            GLTextureView gLTextureView3 = this.glTextureViewWeakRef.get();
                            if (gLTextureView3 != null) {
                                gLTextureView3.renderer.onSurfaceChanged(i, i2);
                            }
                            z8 = false;
                        }
                        GLTextureView gLTextureView4 = this.glTextureViewWeakRef.get();
                        if (gLTextureView4 != null) {
                            gLTextureView4.renderer.onDrawFrame();
                        }
                        int iSwap = this.eglHelper.swap();
                        if (iSwap == 12288) {
                            z = true;
                        } else if (iSwap != 12302) {
                            EglHelper.logEglErrorAsWarning("GLThread", "eglSwapBuffers", iSwap);
                            synchronized (GLTextureView.glThreadManager) {
                                z = true;
                                this.surfaceIsBad = true;
                                GLTextureView.glThreadManager.notifyAll();
                            }
                        } else {
                            z = true;
                            z2 = true;
                        }
                        if (z9) {
                            z3 = z;
                        }
                    } catch (Throwable th) {
                        synchronized (GLTextureView.glThreadManager) {
                            stopEglSurfaceLocked();
                            stopEGLContextLocked();
                            throw th;
                        }
                    }
                }
                runnableRemove.run();
            }
        }

        public boolean ableToDraw() {
            return this.haveEGLContext && this.haveEglSurface && readyToDraw();
        }

        private boolean readyToDraw() {
            return !this.paused && this.hasSurface && !this.surfaceIsBad && this.width > 0 && this.height > 0 && (this.requestRender || this.renderMode == 1);
        }

        public void setRenderMode(int i) {
            if (i >= 0 && i <= 1) {
                synchronized (GLTextureView.glThreadManager) {
                    this.renderMode = i;
                    GLTextureView.glThreadManager.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("renderMode");
        }

        public int getRenderMode() {
            int i;
            synchronized (GLTextureView.glThreadManager) {
                i = this.renderMode;
            }
            return i;
        }

        public void requestRender() {
            synchronized (GLTextureView.glThreadManager) {
                this.requestRender = true;
                GLTextureView.glThreadManager.notifyAll();
            }
        }

        public void surfaceCreated() {
            synchronized (GLTextureView.glThreadManager) {
                this.hasSurface = true;
                GLTextureView.glThreadManager.notifyAll();
                while (this.waitingForSurface && !this.exited) {
                    try {
                        GLTextureView.glThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void surfaceDestroyed() {
            synchronized (GLTextureView.glThreadManager) {
                this.hasSurface = false;
                GLTextureView.glThreadManager.notifyAll();
                while (!this.waitingForSurface && !this.exited) {
                    try {
                        GLTextureView.glThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void onPause() {
            synchronized (GLTextureView.glThreadManager) {
                this.requestPaused = true;
                GLTextureView.glThreadManager.notifyAll();
                while (!this.exited && !this.paused) {
                    try {
                        GLTextureView.glThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void onResume() {
            synchronized (GLTextureView.glThreadManager) {
                this.requestPaused = false;
                this.requestRender = true;
                this.renderComplete = false;
                GLTextureView.glThreadManager.notifyAll();
                while (!this.exited && this.paused && !this.renderComplete) {
                    try {
                        GLTextureView.glThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void onWindowResize(int i, int i2) {
            synchronized (GLTextureView.glThreadManager) {
                this.width = i;
                this.height = i2;
                this.sizeChanged = true;
                this.requestRender = true;
                this.renderComplete = false;
                GLTextureView.glThreadManager.notifyAll();
                while (!this.exited && !this.paused && !this.renderComplete && ableToDraw()) {
                    try {
                        GLTextureView.glThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void requestExitAndWait() {
            synchronized (GLTextureView.glThreadManager) {
                this.shouldExit = true;
                GLTextureView.glThreadManager.notifyAll();
                while (!this.exited) {
                    try {
                        GLTextureView.glThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void requestReleaseEGLContextLocked() {
            this.shouldReleaseEGLContext = true;
            GLTextureView.glThreadManager.notifyAll();
        }

        public void queueEvent(Runnable runnable) {
            if (runnable != null) {
                synchronized (GLTextureView.glThreadManager) {
                    this.eventQueue.add(runnable);
                    GLTextureView.glThreadManager.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("r must not be null");
        }

        public void removeEvent(Runnable runnable) {
            if (runnable != null) {
                synchronized (GLTextureView.glThreadManager) {
                    this.eventQueue.remove(runnable);
                    GLTextureView.glThreadManager.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("r must not be null");
        }
    }

    static class LogWriter extends Writer {
        private StringBuilder builder = new StringBuilder();

        LogWriter() {
        }

        @Override
        public void close() {
            flushBuilder();
        }

        @Override
        public void flush() {
            flushBuilder();
        }

        @Override
        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    flushBuilder();
                } else {
                    this.builder.append(c);
                }
            }
        }

        private void flushBuilder() {
            if (this.builder.length() > 0) {
                Log.v("GLTextureView", this.builder.toString());
                StringBuilder sb = this.builder;
                sb.delete(0, sb.length());
            }
        }
    }

    private void checkRenderThreadState() {
        if (this.glThread != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    private static class GLThreadManager {
        private static String TAG = "GLThreadManager";
        private static final int kGLES_20 = 131072;
        private static final String kMSM7K_RENDERER_PREFIX = "Q3Dimension MSM7500 ";
        private GLThread eglOwner;
        private boolean glesDriverCheckComplete;
        private int glesVersion;
        private boolean glesVersionCheckComplete;
        private boolean limitedGLESContexts;
        private boolean multipleGLESContextsAllowed;

        private GLThreadManager() {
        }

        public synchronized void threadExiting(GLThread gLThread) {
            gLThread.exited = true;
            if (this.eglOwner == gLThread) {
                this.eglOwner = null;
            }
            notifyAll();
        }

        public boolean tryAcquireEGLContextLocked(GLThread gLThread) {
            GLThread gLThread2 = this.eglOwner;
            if (gLThread2 == gLThread || gLThread2 == null) {
                this.eglOwner = gLThread;
                notifyAll();
                return true;
            }
            checkGLESVersion();
            if (this.multipleGLESContextsAllowed) {
                return true;
            }
            GLThread gLThread3 = this.eglOwner;
            if (gLThread3 == null) {
                return false;
            }
            gLThread3.requestReleaseEGLContextLocked();
            return false;
        }

        public void releaseEGLContextLocked(GLThread gLThread) {
            if (this.eglOwner == gLThread) {
                this.eglOwner = null;
            }
            notifyAll();
        }

        public synchronized boolean shouldReleaseEGLContextWhenPausing() {
            return this.limitedGLESContexts;
        }

        public synchronized boolean shouldTerminateEGLWhenPausing() {
            checkGLESVersion();
            return !this.multipleGLESContextsAllowed;
        }

        public synchronized void checkGLDriver() {
            if (!this.glesDriverCheckComplete) {
                checkGLESVersion();
                String strGlGetString = GLES30.glGetString(7937);
                if (this.glesVersion < 131072) {
                    this.multipleGLESContextsAllowed = !strGlGetString.startsWith(kMSM7K_RENDERER_PREFIX);
                    notifyAll();
                }
                this.limitedGLESContexts = this.multipleGLESContextsAllowed ? false : true;
                this.glesDriverCheckComplete = true;
            }
        }

        private void checkGLESVersion() {
            if (this.glesVersionCheckComplete) {
                return;
            }
            this.glesVersionCheckComplete = true;
        }
    }
}
