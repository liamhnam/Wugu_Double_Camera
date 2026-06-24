package com.wugu.doublecamera.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.wugu.doublecamera.utils.SizeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaletteView extends View {
    private static final int MAX_CACHE_STEP = 20;
    private Paint mBlackPaint;
    private Bitmap mBufferBitmap;
    private Canvas mBufferCanvas;
    private Callback mCallback;
    private boolean mCanEraser;
    private int mDrawSize;
    private List<DrawingInfo> mDrawingList;
    private int mEraserSize;
    private float mLastX;
    private float mLastY;
    private Mode mMode;
    private Paint mPaint;
    private Path mPath;
    private int mPenAlpha;
    private List<DrawingInfo> mRemovedList;
    private Xfermode mXferModeClear;
    private Xfermode mXferModeDraw;

    public interface Callback {
        void onUndoRedoStatusChanged();
    }

    public enum Mode {
        DRAW,
        ERASER
    }

    public PaletteView(Context context) {
        super(context);
        this.mPenAlpha = 255;
        this.mMode = Mode.DRAW;
        init();
    }

    public PaletteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPenAlpha = 255;
        this.mMode = Mode.DRAW;
        init();
    }

    public PaletteView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPenAlpha = 255;
        this.mMode = Mode.DRAW;
        init();
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    private void init() {
        setDrawingCacheEnabled(true);
        Paint paint = new Paint(5);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mPaint.setFilterBitmap(true);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mDrawSize = SizeUtil.dp2px(getResources(), 3.0f);
        this.mEraserSize = SizeUtil.dp2px(getResources(), 30.0f);
        this.mPaint.setStrokeWidth(this.mDrawSize);
        this.mPaint.setColor(-16776961);
        this.mXferModeDraw = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        this.mXferModeClear = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        this.mPaint.setXfermode(this.mXferModeDraw);
    }

    private void initBuffer() {
        this.mBufferBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        this.mBufferCanvas = new Canvas(this.mBufferBitmap);
    }

    private static abstract class DrawingInfo {
        Paint paint;

        abstract void draw(Canvas canvas);

        private DrawingInfo() {
        }
    }

    private static class PathDrawingInfo extends DrawingInfo {
        Path path;

        private PathDrawingInfo() {
            super();
        }

        @Override
        void draw(Canvas canvas) {
            canvas.drawPath(this.path, this.paint);
        }
    }

    public Mode getMode() {
        return this.mMode;
    }

    public void setMode(Mode mode) {
        if (mode != this.mMode) {
            this.mMode = mode;
            if (mode == Mode.DRAW) {
                this.mPaint.setXfermode(this.mXferModeDraw);
                this.mPaint.setStrokeWidth(this.mDrawSize);
            } else {
                this.mPaint.setXfermode(this.mXferModeClear);
                this.mPaint.setStrokeWidth(this.mEraserSize);
            }
        }
    }

    public void setEraserSize(int i) {
        this.mEraserSize = i;
    }

    public void setPenRawSize(int i) {
        this.mDrawSize = i;
        if (this.mMode == Mode.DRAW) {
            this.mPaint.setStrokeWidth(this.mDrawSize);
        }
    }

    public void setPenColor(int i) {
        this.mPaint.setColor(i);
    }

    private void reDraw() {
        Bitmap bitmap;
        if (this.mDrawingList == null || (bitmap = this.mBufferBitmap) == null || bitmap.isRecycled()) {
            return;
        }
        this.mBufferBitmap.eraseColor(0);
        Iterator<DrawingInfo> it = this.mDrawingList.iterator();
        while (it.hasNext()) {
            it.next().draw(this.mBufferCanvas);
        }
        invalidate();
    }

    public int getPenColor() {
        return this.mPaint.getColor();
    }

    public int getPenSize() {
        return this.mDrawSize;
    }

    public int getEraserSize() {
        return this.mEraserSize;
    }

    public void setPenAlpha(int i) {
        this.mPenAlpha = i;
        if (this.mMode == Mode.DRAW) {
            this.mPaint.setAlpha(i);
        }
    }

    public int getPenAlpha() {
        return this.mPenAlpha;
    }

    public boolean canRedo() {
        List<DrawingInfo> list = this.mRemovedList;
        return list != null && list.size() > 0;
    }

    public boolean canUndo() {
        List<DrawingInfo> list = this.mDrawingList;
        return list != null && list.size() > 0;
    }

    public void redo() {
        List<DrawingInfo> list = this.mRemovedList;
        int size = list == null ? 0 : list.size();
        if (size > 0) {
            this.mDrawingList.add(this.mRemovedList.remove(size - 1));
            this.mCanEraser = true;
            reDraw();
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.onUndoRedoStatusChanged();
            }
        }
    }

    public void undo() {
        List<DrawingInfo> list = this.mDrawingList;
        int size = list == null ? 0 : list.size();
        if (size > 0) {
            DrawingInfo drawingInfoRemove = this.mDrawingList.remove(size - 1);
            if (this.mRemovedList == null) {
                this.mRemovedList = new ArrayList(20);
            }
            if (size == 1) {
                this.mCanEraser = false;
            }
            this.mRemovedList.add(drawingInfoRemove);
            reDraw();
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.onUndoRedoStatusChanged();
            }
        }
    }

    public void clear() {
        if (this.mBufferBitmap != null) {
            List<DrawingInfo> list = this.mDrawingList;
            if (list != null) {
                list.clear();
            }
            List<DrawingInfo> list2 = this.mRemovedList;
            if (list2 != null) {
                list2.clear();
            }
            this.mCanEraser = false;
            if (!this.mBufferBitmap.isRecycled()) {
                this.mBufferBitmap.eraseColor(0);
                invalidate();
            }
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.onUndoRedoStatusChanged();
            }
        }
    }

    public Bitmap getDrawBitmap() {
        return this.mBufferBitmap;
    }

    public Bitmap buildBitmap() {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getDrawingCache());
        destroyDrawingCache();
        return bitmapCreateBitmap;
    }

    private void saveDrawingPath() {
        List<DrawingInfo> list = this.mDrawingList;
        if (list == null) {
            this.mDrawingList = new ArrayList(20);
        } else if (list.size() == 20) {
            this.mDrawingList.remove(0);
        }
        Path path = new Path(this.mPath);
        Paint paint = new Paint(this.mPaint);
        PathDrawingInfo pathDrawingInfo = new PathDrawingInfo();
        pathDrawingInfo.path = path;
        pathDrawingInfo.paint = paint;
        this.mDrawingList.add(pathDrawingInfo);
        this.mCanEraser = true;
        List<DrawingInfo> list2 = this.mRemovedList;
        if (list2 != null) {
            list2.clear();
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onUndoRedoStatusChanged();
        }
    }

    public void setGray(boolean z) {
        if (z) {
            this.mBlackPaint = new Paint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            this.mBlackPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        } else {
            this.mBlackPaint = null;
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.mBufferBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        canvas.drawBitmap(this.mBufferBitmap, 0.0f, 0.0f, this.mBlackPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.mLastX = x;
            this.mLastY = y;
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            this.mPath.moveTo(x, y);
        } else if (action == 1) {
            if (this.mMode == Mode.DRAW || this.mCanEraser) {
                saveDrawingPath();
            }
            this.mPath.reset();
        } else if (action == 2) {
            Path path = this.mPath;
            float f = this.mLastX;
            float f2 = this.mLastY;
            path.quadTo(f, f2, (x + f) / 2.0f, (y + f2) / 2.0f);
            if (this.mBufferBitmap == null) {
                initBuffer();
            }
            if (this.mMode != Mode.ERASER || this.mCanEraser) {
                this.mBufferCanvas.drawPath(this.mPath, this.mPaint);
                invalidate();
                this.mLastX = x;
                this.mLastY = y;
            }
        }
        return true;
    }
}
