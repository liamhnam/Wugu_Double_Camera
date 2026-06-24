package com.faceunity.core.utils;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import java.util.Arrays;

public class GestureTouchHandler {
    private static final boolean DEBUG = true;
    private static final float MAX_SCALE = 4.0f;
    private static final float MIN_SCALE = 0.25f;
    private static final String TAG = "KIT_GestureTouchHandler";
    private float downX;
    private float downY;
    private int mHeight;
    private float[] mIdentityPoints;
    private float mIdentitySize;
    private int mMode;
    private OnTouchResultListener mOnTouchResultListener;
    private float mTouchDistance;
    private int mTouchSlop;
    private int mWidth;
    private Matrix downMatrix = new Matrix();
    private Matrix moveMatrix = new Matrix();
    private PointF mMiddlePoint = new PointF();
    private float[] mResultPoints = new float[8];
    private Matrix mResultMatrix = new Matrix();

    public static class ActionMode {
        public static final int NONE = 0;
        public static final int ROTATE_AND_ZOOM = 2;
        public static final int TRANS = 1;
        public static final int ZOOM_MULTI = 3;
    }

    public interface OnTouchResultListener {
        void onClick();

        void onTransform(float f, float f2, float f3, float f4);
    }

    public GestureTouchHandler(Context context) {
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setOnTouchResultListener(OnTouchResultListener onTouchResultListener) {
        this.mOnTouchResultListener = onTouchResultListener;
    }

    public void setViewSize(int i, int i2) {
        Log.d(TAG, "setViewSize() width = [" + i + "], height = [" + i2 + "]");
        this.mWidth = i;
        this.mHeight = i2;
        float f = i;
        float f2 = i2;
        this.mIdentityPoints = new float[]{0.0f, 0.0f, f, 0.0f, 0.0f, f2, f, f2};
        this.mIdentitySize = getScaledRectSize();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.downX = motionEvent.getX();
            this.downY = motionEvent.getY();
            this.downMatrix.set(this.mResultMatrix);
            this.mMode = 1;
            return true;
        }
        if (actionMasked == 1) {
            this.mMode = 0;
            this.mMiddlePoint = null;
        } else if (actionMasked == 2) {
            float x = motionEvent.getX() - this.downX;
            float y = motionEvent.getY() - this.downY;
            if (((float) Math.sqrt((x * x) + (y * y))) < this.mTouchSlop) {
                Log.i(TAG, "onTouchEvent: 点击操作 " + this.mTouchSlop);
                OnTouchResultListener onTouchResultListener = this.mOnTouchResultListener;
                if (onTouchResultListener != null) {
                    onTouchResultListener.onClick();
                }
            } else {
                int i = this.mMode;
                if (i == 1) {
                    this.moveMatrix.set(this.downMatrix);
                    float x2 = motionEvent.getX() - this.downX;
                    float y2 = motionEvent.getY() - this.downY;
                    this.moveMatrix.postTranslate(x2, y2);
                    this.mResultMatrix.set(this.moveMatrix);
                    mapPoints(this.moveMatrix, this.mResultPoints);
                    float[] fArr = this.mResultPoints;
                    float f = fArr[0];
                    int i2 = this.mWidth;
                    float f2 = f / i2;
                    float f3 = fArr[1];
                    int i3 = this.mHeight;
                    float f4 = f3 / i3;
                    float f5 = fArr[2] / i2;
                    float f6 = fArr[5] / i3;
                    OnTouchResultListener onTouchResultListener2 = this.mOnTouchResultListener;
                    if (onTouchResultListener2 != null) {
                        onTouchResultListener2.onTransform(f2, f4, f5, f6);
                    }
                    Log.d(TAG, "平移操作 dx:" + ((int) x2) + ", dy:" + ((int) y2) + ", move mtx:" + this.moveMatrix + ". points:" + Arrays.toString(this.mResultPoints));
                    Log.i(TAG, "onTouchEvent: x1 " + f2 + ", y1 " + f4 + ", x2:" + f5 + ", y2:" + f6);
                } else if (i == 3) {
                    this.moveMatrix.set(this.downMatrix);
                    float multiTouchDistance = getMultiTouchDistance(motionEvent) / this.mTouchDistance;
                    float scaledRectSize = getScaledRectSize() / this.mIdentitySize;
                    if ((scaledRectSize > MAX_SCALE && multiTouchDistance > 1.0f) || (scaledRectSize < MIN_SCALE && multiTouchDistance < 1.0f)) {
                        return false;
                    }
                    this.moveMatrix.postScale(multiTouchDistance, multiTouchDistance, this.mMiddlePoint.x, this.mMiddlePoint.y);
                    this.mResultMatrix.set(this.moveMatrix);
                    float f7 = this.mWidth;
                    float f8 = this.mHeight;
                    float[] fArr2 = this.mResultPoints;
                    float f9 = fArr2[0] / f7;
                    float f10 = fArr2[1] / f8;
                    float f11 = fArr2[2] / f7;
                    float f12 = fArr2[5] / f8;
                    OnTouchResultListener onTouchResultListener3 = this.mOnTouchResultListener;
                    if (onTouchResultListener3 != null) {
                        onTouchResultListener3.onTransform(f9, f10, f11, f12);
                    }
                    Log.d(TAG, "多点缩放操作 " + multiTouchDistance);
                    Log.i(TAG, "onTouchEvent: x1 " + f9 + ", y1 " + f10 + ", x2:" + f11 + ", y2:" + f12);
                }
            }
        } else {
            if (actionMasked == 5) {
                this.mMode = 3;
                this.mTouchDistance = getMultiTouchDistance(motionEvent);
                this.mMiddlePoint = getMiddleTouchPoint(motionEvent);
                this.downMatrix.set(this.mResultMatrix);
                return true;
            }
            if (actionMasked != 6) {
                return false;
            }
            this.mMode = 0;
            this.mMiddlePoint = null;
        }
        return true;
    }

    private float getScaledRectSize() {
        this.mResultMatrix.mapPoints(this.mResultPoints, this.mIdentityPoints);
        float[] fArr = this.mResultPoints;
        float f = fArr[0];
        float f2 = fArr[1];
        return (float) Math.sqrt(Math.pow(fArr[2] - f, 2.0d) + Math.pow(fArr[3] - f2, 2.0d));
    }

    private void mapPoints(Matrix matrix, float[] fArr) {
        matrix.mapPoints(fArr, this.mIdentityPoints);
    }

    public static float getMultiTouchDistance(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public static PointF getMiddleTouchPoint(MotionEvent motionEvent) {
        PointF pointF = new PointF();
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
        return pointF;
    }
}
