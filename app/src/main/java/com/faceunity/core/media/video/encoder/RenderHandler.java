package com.faceunity.core.media.video.encoder;

import android.opengl.EGLContext;
import android.opengl.Matrix;
import android.text.TextUtils;
import android.view.Surface;
import com.faceunity.core.program.ProgramTexture2d;
import com.faceunity.core.program.core.EglCore;
import com.faceunity.core.program.core.Program;
import com.faceunity.core.program.core.WindowSurface;

public final class RenderHandler implements Runnable {
    private static final boolean DEBUG = false;
    private static final String TAG = "Video_RenderHandler";
    private EglCore mEglCore;
    private WindowSurface mInputWindowSurface;
    private Program mProgramTexture2d;
    private volatile int mRequestDraw;
    private volatile boolean mRequestRelease;
    private volatile boolean mRequestSetEglContext;
    private EGLContext mShard_context;
    private Surface mSurface;
    private int mTexId;
    private final Object mSync = new Object();
    private float[] mTexMatrix = new float[16];
    private float[] mMvpMatrix = new float[16];

    public static final RenderHandler createHandler(String str) {
        RenderHandler renderHandler = new RenderHandler();
        synchronized (renderHandler.mSync) {
            if (TextUtils.isEmpty(str)) {
                str = TAG;
            }
            new Thread(renderHandler, str).start();
            try {
                renderHandler.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
        return renderHandler;
    }

    public final void setEglContext(EGLContext eGLContext, Surface surface, int i) {
        synchronized (this.mSync) {
            if (this.mRequestRelease) {
                return;
            }
            this.mShard_context = eGLContext;
            this.mTexId = i;
            this.mSurface = surface;
            this.mRequestSetEglContext = true;
            Matrix.setIdentityM(this.mTexMatrix, 0);
            Matrix.setIdentityM(this.mMvpMatrix, 0);
            this.mSync.notifyAll();
            try {
                this.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public final void draw(int i, float[] fArr, float[] fArr2) {
        synchronized (this.mSync) {
            if (this.mRequestRelease) {
                return;
            }
            this.mTexId = i;
            if (fArr != null && fArr.length >= 16) {
                System.arraycopy(fArr, 0, this.mTexMatrix, 0, 16);
            } else {
                Matrix.setIdentityM(this.mTexMatrix, 0);
            }
            if (fArr2 != null && fArr2.length >= 16) {
                System.arraycopy(fArr2, 0, this.mMvpMatrix, 0, 16);
            } else {
                Matrix.setIdentityM(this.mMvpMatrix, 0);
            }
            this.mRequestDraw++;
            this.mSync.notifyAll();
        }
    }

    public boolean isValid() {
        boolean z;
        synchronized (this.mSync) {
            Surface surface = this.mSurface;
            z = !(surface instanceof Surface) || surface.isValid();
        }
        return z;
    }

    public final void release() {
        synchronized (this.mSync) {
            if (this.mRequestRelease) {
                return;
            }
            this.mRequestRelease = true;
            this.mSync.notifyAll();
            try {
                this.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mSync
            monitor-enter(r0)
            r1 = 0
            r5.mRequestRelease = r1     // Catch: java.lang.Throwable -> L80
            r5.mRequestSetEglContext = r1     // Catch: java.lang.Throwable -> L80
            r5.mRequestDraw = r1     // Catch: java.lang.Throwable -> L80
            java.lang.Object r2 = r5.mSync     // Catch: java.lang.Throwable -> L80
            r2.notifyAll()     // Catch: java.lang.Throwable -> L80
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L80
        L10:
            java.lang.Object r2 = r5.mSync
            monitor-enter(r2)
            boolean r0 = r5.mRequestRelease     // Catch: java.lang.Throwable -> L7d
            r3 = 1
            if (r0 == 0) goto L1a
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L7d
            goto L69
        L1a:
            boolean r0 = r5.mRequestSetEglContext     // Catch: java.lang.Throwable -> L7d
            if (r0 == 0) goto L23
            r5.mRequestSetEglContext = r1     // Catch: java.lang.Throwable -> L7d
            r5.internalPrepare()     // Catch: java.lang.Throwable -> L7d
        L23:
            int r0 = r5.mRequestDraw     // Catch: java.lang.Throwable -> L7d
            if (r0 <= 0) goto L29
            r0 = r3
            goto L2a
        L29:
            r0 = r1
        L2a:
            if (r0 == 0) goto L31
            int r4 = r5.mRequestDraw     // Catch: java.lang.Throwable -> L7d
            int r4 = r4 - r3
            r5.mRequestDraw = r4     // Catch: java.lang.Throwable -> L7d
        L31:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L7d
            if (r0 == 0) goto L5c
            com.faceunity.core.program.core.EglCore r0 = r5.mEglCore
            if (r0 == 0) goto L10
            int r0 = r5.mTexId
            if (r0 < 0) goto L10
            com.faceunity.core.program.core.WindowSurface r0 = r5.mInputWindowSurface
            r0.makeCurrent()
            r0 = 1065353216(0x3f800000, float:1.0)
            android.opengl.GLES20.glClearColor(r0, r0, r0, r0)
            r0 = 16640(0x4100, float:2.3318E-41)
            android.opengl.GLES20.glClear(r0)
            com.faceunity.core.program.core.Program r0 = r5.mProgramTexture2d
            int r2 = r5.mTexId
            float[] r3 = r5.mTexMatrix
            float[] r4 = r5.mMvpMatrix
            r0.drawFrame(r2, r3, r4)
            com.faceunity.core.program.core.WindowSurface r0 = r5.mInputWindowSurface
            r0.swapBuffers()
            goto L10
        L5c:
            java.lang.Object r0 = r5.mSync
            monitor-enter(r0)
            java.lang.Object r2 = r5.mSync     // Catch: java.lang.Throwable -> L66 java.lang.InterruptedException -> L68
            r2.wait()     // Catch: java.lang.Throwable -> L66 java.lang.InterruptedException -> L68
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L66
            goto L10
        L66:
            r1 = move-exception
            goto L7b
        L68:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L66
        L69:
            java.lang.Object r1 = r5.mSync
            monitor-enter(r1)
            r5.mRequestRelease = r3     // Catch: java.lang.Throwable -> L78
            r5.internalRelease()     // Catch: java.lang.Throwable -> L78
            java.lang.Object r0 = r5.mSync     // Catch: java.lang.Throwable -> L78
            r0.notifyAll()     // Catch: java.lang.Throwable -> L78
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L78
            return
        L78:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L78
            throw r0
        L7b:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L66
            throw r1
        L7d:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L7d
            throw r0
        L80:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L80
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.faceunity.core.media.video.encoder.RenderHandler.run():void");
    }

    private final void internalPrepare() {
        internalRelease();
        this.mEglCore = new EglCore(this.mShard_context, 1);
        WindowSurface windowSurface = new WindowSurface(this.mEglCore, this.mSurface, true);
        this.mInputWindowSurface = windowSurface;
        windowSurface.makeCurrent();
        this.mProgramTexture2d = new ProgramTexture2d();
        this.mSurface = null;
        this.mSync.notifyAll();
    }

    private final void internalRelease() {
        WindowSurface windowSurface = this.mInputWindowSurface;
        if (windowSurface != null) {
            windowSurface.release();
            this.mInputWindowSurface = null;
        }
        Program program = this.mProgramTexture2d;
        if (program != null) {
            program.release();
            this.mProgramTexture2d = null;
        }
        EglCore eglCore = this.mEglCore;
        if (eglCore != null) {
            eglCore.release();
            this.mEglCore = null;
        }
    }
}
