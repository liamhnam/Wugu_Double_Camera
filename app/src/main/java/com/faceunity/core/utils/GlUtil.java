package com.faceunity.core.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import com.p020hp.jipp.model.TimeoutPredicate;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;

public abstract class GlUtil {
    public static final float[] IDENTITY_MATRIX;
    private static final int SIZEOF_FLOAT = 4;
    public static final String TAG = "KIT_GlUtil";

    static {
        float[] fArr = new float[16];
        IDENTITY_MATRIX = fArr;
        Matrix.setIdentityM(fArr, 0);
    }

    private GlUtil() {
    }

    public static int createProgram(String str, String str2) {
        int iLoadShader;
        int iLoadShader2 = loadShader(35633, str);
        if (iLoadShader2 == 0 || (iLoadShader = loadShader(35632, str2)) == 0) {
            return 0;
        }
        int iGlCreateProgram = GLES20.glCreateProgram();
        checkGlError("glCreateProgram");
        if (iGlCreateProgram == 0) {
            Log.e(TAG, "Could not create program");
        }
        GLES20.glAttachShader(iGlCreateProgram, iLoadShader2);
        checkGlError("glAttachShader");
        GLES20.glAttachShader(iGlCreateProgram, iLoadShader);
        checkGlError("glAttachShader");
        GLES20.glLinkProgram(iGlCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        if (iArr[0] == 1) {
            return iGlCreateProgram;
        }
        Log.e(TAG, "Could not link program: ");
        Log.e(TAG, GLES20.glGetProgramInfoLog(iGlCreateProgram));
        GLES20.glDeleteProgram(iGlCreateProgram);
        return 0;
    }

    public static int loadShader(int i, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        checkGlError("glCreateShader type=" + i);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        Log.e(TAG, "Could not compile shader " + i + ":");
        Log.e(TAG, " " + GLES20.glGetShaderInfoLog(iGlCreateShader));
        GLES20.glDeleteShader(iGlCreateShader);
        return 0;
    }

    public static void checkGlError(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            Log.e(TAG, str + ": glError 0x" + Integer.toHexString(iGlGetError));
        }
    }

    public static void checkLocation(int i, String str) {
        if (i < 0) {
            Log.e(TAG, "Unable to locate '" + str + "' in program");
        }
    }

    public static int createImageTexture(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i4 = iArr[0];
        checkGlError("glGenTextures");
        GLES20.glBindTexture(3553, i4);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        checkGlError("loadImageTexture");
        GLES20.glTexImage2D(3553, 0, i3, i, i2, 0, i3, 5121, byteBuffer);
        checkGlError("loadImageTexture");
        return i4;
    }

    public static int createImageTexture(Bitmap bitmap) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        checkGlError("glGenTextures");
        GLES20.glBindTexture(3553, i);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        checkGlError("loadImageTexture");
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        checkGlError("loadImageTexture");
        return i;
    }

    public static FloatBuffer createFloatBuffer(float[] fArr) {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect.asFloatBuffer();
        floatBufferAsFloatBuffer.put(fArr);
        floatBufferAsFloatBuffer.position(0);
        return floatBufferAsFloatBuffer;
    }

    public static void logVersionInfo() {
        Log.i(TAG, "vendor  : " + GLES20.glGetString(7936));
        Log.i(TAG, "com.faceunity.core.renderer: " + GLES20.glGetString(7937));
        Log.i(TAG, "version : " + GLES20.glGetString(7938));
        int[] iArr = new int[1];
        GLES30.glGetIntegerv(33307, iArr, 0);
        int i = iArr[0];
        GLES30.glGetIntegerv(33308, iArr, 0);
        int i2 = iArr[0];
        if (GLES30.glGetError() == 0) {
            Log.i(TAG, "glVersion: " + i + "." + i2);
        }
    }

    public static int getGlMajorVersion() {
        int[] iArr = new int[1];
        GLES30.glGetIntegerv(33307, iArr, 0);
        return iArr[0];
    }

    public static int createTextureObject(int i) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlError("glGenTextures");
        int i2 = iArr[0];
        GLES20.glBindTexture(i, i2);
        checkGlError("glBindTexture " + i2);
        GLES20.glTexParameterf(i, 10241, 9729.0f);
        GLES20.glTexParameterf(i, 10240, 9729.0f);
        GLES20.glTexParameteri(i, 10242, 33071);
        GLES20.glTexParameteri(i, 10243, 33071);
        checkGlError("glTexParameter");
        return i2;
    }

    public static void deleteTextures(int[] iArr) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        GLES20.glDeleteTextures(iArr.length, iArr, 0);
    }

    public static void createFrameBuffers(int[] iArr, int[] iArr2, int i, int i2) {
        GLES20.glGenFramebuffers(iArr2.length, iArr2, 0);
        GLES20.glGenTextures(iArr.length, iArr, 0);
        GLES20.glBindFramebuffer(36160, iArr2[0]);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    public static void deleteFrameBuffers(int[] iArr) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        GLES20.glDeleteFramebuffers(iArr.length, iArr, 0);
    }

    public static float[] changeMvpMatrixCrop(float f, float f2, float f3, float f4) {
        float f5 = ((f * f4) / f2) / f3;
        float[] fArr = IDENTITY_MATRIX;
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        float f6 = f5 > 1.0f ? 1.0f : 1.0f / f5;
        if (f5 <= 1.0f) {
            f5 = 1.0f;
        }
        Matrix.scaleM(fArrCopyOf, 0, f6, f5, 1.0f);
        return fArrCopyOf;
    }

    public static float[] changeMvpMatrixInside(float f, float f2, float f3, float f4) {
        float f5 = ((f * f4) / f2) / f3;
        float[] fArr = IDENTITY_MATRIX;
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        float f6 = f5 > 1.0f ? 1.0f / f5 : 1.0f;
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        Matrix.scaleM(fArrCopyOf, 0, f6, f5, 1.0f);
        return fArrCopyOf;
    }

    public static int getSupportGlVersion(Context context) {
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) context.getSystemService(TimeoutPredicate.activity)).getDeviceConfigurationInfo();
        int i = deviceConfigurationInfo.reqGlEsVersion >= 196608 ? 3 : 2;
        Log.d(TAG, "reqGlEsVersion: " + Integer.toHexString(deviceConfigurationInfo.reqGlEsVersion) + ", glEsVersion: " + deviceConfigurationInfo.getGlEsVersion() + ", return: " + i);
        return i;
    }
}
