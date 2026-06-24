package com.faceunity.core.program.core;

import android.content.Context;
import android.opengl.GLES20;
import com.faceunity.core.utils.GlUtil;

public abstract class Program {
    private static final String TAG = "KIT_GlUtil";
    protected Drawable2d mDrawable2d;
    private int[] mOriginViewport;
    protected int mProgramHandle;

    public abstract void drawFrame(int i, float[] fArr, float[] fArr2);

    protected abstract Drawable2d getDrawable2d();

    protected abstract void getLocations();

    public Program(String str, String str2) {
        this.mOriginViewport = new int[4];
        this.mProgramHandle = GlUtil.createProgram(str, str2);
        this.mDrawable2d = getDrawable2d();
        getLocations();
    }

    public Program(Context context, int i, int i2) {
        this(Extensions.readTextFileFromResource(context, i), Extensions.readTextFileFromResource(context, i2));
    }

    public void updateVertexArray(float[] fArr) {
        this.mDrawable2d.updateVertexArray(fArr);
    }

    public void updateTexCoordArray(float[] fArr) {
        this.mDrawable2d.updateTexCoordArray(fArr);
    }

    public void drawFrame(int i, float[] fArr) {
        drawFrame(i, fArr, GlUtil.IDENTITY_MATRIX);
    }

    public void drawFrame(int i, float[] fArr, float[] fArr2, int i2, int i3, int i4, int i5) {
        GLES20.glGetIntegerv(2978, this.mOriginViewport, 0);
        GLES20.glViewport(i2, i3, i4, i5);
        drawFrame(i, fArr, fArr2);
        int[] iArr = this.mOriginViewport;
        GLES20.glViewport(iArr[0], iArr[1], iArr[2], iArr[3]);
    }

    public void release() {
        GLES20.glDeleteProgram(this.mProgramHandle);
        this.mProgramHandle = -1;
    }
}
