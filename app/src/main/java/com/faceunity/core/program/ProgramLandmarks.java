package com.faceunity.core.program;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.faceunity.core.program.core.Drawable2d;
import com.faceunity.core.program.core.Program;
import com.faceunity.core.utils.GlUtil;
import com.p020hp.jipp.model.PowerState;
import java.nio.Buffer;

public class ProgramLandmarks extends Program {
    private static final float[] POINT_COLOR = {1.0f, 0.0f, 0.0f, 1.0f};
    private static final float POINT_SIZE = 6.0f;
    private static final String fragmentShaderCode = "precision mediump float;uniform vec4 vColor;void main() {  gl_FragColor = vColor;}";
    private static final String vertexShaderCode = "uniform mat4 uMVPMatrix;attribute vec4 vPosition;uniform float uPointSize;void main() {  gl_Position = uMVPMatrix * vPosition;  gl_PointSize = uPointSize;}";
    private int mCameraHeight;
    private int mCameraOrientation;
    private int mCameraType;
    private int mCameraWidth;
    private int mColorHandle;
    private int mMVPMatrixHandle;
    private final float[] mMvpMatrix;
    private int mPointSizeHandle;
    private int mPositionHandle;

    public ProgramLandmarks() {
        super(vertexShaderCode, fragmentShaderCode);
        this.mMvpMatrix = new float[16];
    }

    @Override
    protected Drawable2d getDrawable2d() {
        return new Drawable2d(new float[PowerState.Code.resetSoftGraceful]);
    }

    @Override
    protected void getLocations() {
        this.mPositionHandle = GLES20.glGetAttribLocation(this.mProgramHandle, "vPosition");
        GlUtil.checkGlError("vPosition");
        this.mColorHandle = GLES20.glGetUniformLocation(this.mProgramHandle, "vColor");
        GlUtil.checkGlError("vColor");
        this.mMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgramHandle, "uMVPMatrix");
        GlUtil.checkGlError("glGetUniformLocation");
        this.mPointSizeHandle = GLES20.glGetUniformLocation(this.mProgramHandle, "uPointSize");
        GlUtil.checkGlError("uPointSize");
    }

    @Override
    public void drawFrame(int i, float[] fArr, float[] fArr2) {
        GLES20.glUseProgram(this.mProgramHandle);
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glVertexAttribPointer(this.mPositionHandle, 2, 5126, false, 8, (Buffer) this.mDrawable2d.vertexArray());
        GLES20.glUniform4fv(this.mColorHandle, 1, POINT_COLOR, 0);
        GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, fArr2, 0);
        GLES20.glUniform1f(this.mPointSizeHandle, POINT_SIZE);
        GLES20.glDrawArrays(0, 0, this.mDrawable2d.vertexCount());
        GLES20.glDisableVertexAttribArray(this.mPositionHandle);
        GLES20.glUseProgram(0);
    }

    public void drawFrame(int i, int i2, int i3, int i4) {
        drawFrame(0, null, this.mMvpMatrix, i, i2, i3, i4);
    }

    public void refresh(float[] fArr, int i, int i2, int i3, int i4, float[] fArr2) {
        if (this.mCameraWidth != i || this.mCameraHeight != i2 || this.mCameraOrientation != i3 || this.mCameraType != i4) {
            float[] fArr3 = new float[16];
            Matrix.orthoM(fArr3, 0, 0.0f, i, 0.0f, i2, -1.0f, 1.0f);
            float[] fArr4 = new float[16];
            Matrix.setRotateM(fArr4, 0, 360 - i3, 0.0f, 0.0f, 1.0f);
            if (i4 == 0) {
                Matrix.rotateM(fArr4, 0, 180.0f, 1.0f, 0.0f, 0.0f);
            }
            float[] fArr5 = new float[16];
            Matrix.multiplyMM(fArr5, 0, fArr4, 0, fArr3, 0);
            Matrix.multiplyMM(this.mMvpMatrix, 0, fArr2, 0, fArr5, 0);
            this.mCameraWidth = i;
            this.mCameraHeight = i2;
            this.mCameraOrientation = i3;
            this.mCameraType = i4;
        }
        updateVertexArray(fArr);
    }
}
