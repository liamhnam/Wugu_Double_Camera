package com.wugu.stickerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.licheedev.commonsize.C1798R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StickerLayout extends FrameLayout implements View.OnTouchListener, IStickerTouchListener {
    private Context context;
    private boolean disableTouchSticker;
    private Rect focusRect;
    private StickerView focusSticker;
    private float focusStickerRotation;
    private int keyStickerId;
    private final LinkedHashMap<Integer, StickerView> mapSticker;
    private int pointX;
    private int pointY;

    public StickerLayout(Context context) {
        super(context);
        this.mapSticker = new LinkedHashMap<>();
        init(context);
    }

    public StickerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mapSticker = new LinkedHashMap<>();
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setOnTouchListener(this);
        this.disableTouchSticker = false;
        this.keyStickerId = 1;
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Iterator<Map.Entry<Integer, StickerView>> it = this.mapSticker.entrySet().iterator();
        while (it.hasNext()) {
            StickerView value = it.next().getValue();
            Rect viewRect = value.getViewRect();
            if (viewRect != null) {
                value.layout(viewRect.left, viewRect.top, viewRect.right, viewRect.bottom);
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        StickerView stickerView;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.pointX = (int) motionEvent.getX();
            this.pointY = (int) motionEvent.getY();
            StickerView stickerView2 = this.focusSticker;
            if (stickerView2 != null && stickerView2.getOpStatus() == -1) {
                this.focusSticker.setFocus(false);
            }
        } else if (action == 1) {
            this.disableTouchSticker = false;
            if (this.focusSticker != null) {
                this.pointX = (int) motionEvent.getX();
                this.pointY = (int) motionEvent.getY();
                if (this.focusSticker.getOpStatus() == 0) {
                    deleteFocusSticker();
                } else {
                    this.focusSticker.setOpStatus(-1);
                }
            }
        } else if (action == 2 && (stickerView = this.focusSticker) != null) {
            int opStatus = stickerView.getOpStatus();
            if (opStatus == 3) {
                moveRect(motionEvent);
            } else if (opStatus == 1) {
                zoomRect(motionEvent);
            } else if (opStatus == 2) {
                rotateRect(motionEvent);
            }
        }
        return true;
    }

    @Override
    public void stickerTouch(int i, boolean z) {
        if (this.disableTouchSticker) {
            return;
        }
        this.disableTouchSticker = true;
        StickerView stickerView = this.focusSticker;
        if (stickerView != null && !z) {
            stickerView.setFocus(false);
        }
        StickerView stickerView2 = this.mapSticker.get(Integer.valueOf(i));
        this.focusSticker = stickerView2;
        if (stickerView2 == null) {
            return;
        }
        this.focusRect = stickerView2.getViewRect();
        this.focusSticker.bringToFront();
        this.focusSticker.setFocus(true);
        this.focusStickerRotation = this.focusSticker.getRotation();
    }

    @Override
    public void clearFocus() {
        StickerView stickerView = this.focusSticker;
        if (stickerView != null) {
            stickerView.setFocus(false);
        }
    }

    public List<StickerView> getStickerList() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Integer, StickerView>> it = this.mapSticker.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        return arrayList;
    }

    public void addSticker(Bitmap bitmap, boolean z) {
        StickerView stickerView = this.focusSticker;
        if (stickerView != null) {
            stickerView.setFocus(false);
        }
        StickerView stickerView2 = new StickerView(this.context, z);
        stickerView2.setBitmap(bitmap);
        this.focusSticker = stickerView2;
        stickerView2.setFocus(true);
        this.mapSticker.put(Integer.valueOf(this.keyStickerId), stickerView2);
        if (z) {
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            this.focusRect = rect;
            this.focusSticker.setViewRect(rect);
        } else {
            this.focusRect = getDefaultRect(bitmap);
        }
        stickerView2.setLayoutParams(new FrameLayout.LayoutParams(this.focusRect.right - this.focusRect.left, this.focusRect.bottom - this.focusRect.top));
        addView(stickerView2);
        stickerView2.setKeyTag(this.keyStickerId);
        stickerView2.setStickerTouchListener(this);
        requestLayout();
        this.keyStickerId++;
    }

    private void deleteFocusSticker() {
        StickerView stickerView = this.focusSticker;
        if (stickerView == null) {
            return;
        }
        this.mapSticker.remove(Integer.valueOf(stickerView.getKeyTag()));
        removeView(this.focusSticker);
        this.focusSticker = null;
        this.focusRect = null;
    }

    private Rect getDefaultRect(Bitmap bitmap) {
        int width = bitmap.getWidth();
        float f = width;
        float width2 = f / getWidth();
        float height = bitmap.getHeight();
        float height2 = height / getHeight();
        float f2 = f / height;
        Rect rect = new Rect();
        if (height2 > width2) {
            rect.top = (getHeight() / 2) - (getHeight() / 8);
            rect.bottom = (getHeight() / 2) + (getHeight() / 8);
            int i = (int) ((rect.bottom - rect.top) * f2);
            rect.left = (getWidth() - i) / 2;
            rect.right = rect.left + i;
        } else {
            rect.left = (getWidth() / 2) - (getWidth() / 8);
            rect.right = (getWidth() / 2) + (getWidth() / 8);
            int i2 = (int) ((rect.right - rect.left) / f2);
            rect.top = (getHeight() - i2) / 2;
            rect.bottom = rect.top + i2;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1798R.dimen.normal_100dp);
        if (Math.min(rect.height(), rect.width()) < dimensionPixelSize) {
            int iMin = (dimensionPixelSize / Math.min(rect.height(), rect.width())) + 1;
            int iWidth = rect.width() * iMin;
            int iHeight = rect.height() * iMin;
            int iCenterX = rect.centerX() - (iWidth / 2);
            int iCenterY = rect.centerY() - (iHeight / 2);
            rect.set(iCenterX, iCenterY, iWidth + iCenterX, iHeight + iCenterY);
        }
        this.focusSticker.setViewRect(rect);
        this.focusSticker.setBaseHeight(height2 > width2);
        return rect;
    }

    private void moveRect(MotionEvent motionEvent) {
        if (this.focusSticker == null || this.focusRect == null) {
            return;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int i = x - this.pointX;
        int i2 = y - this.pointY;
        this.focusRect.top += i2;
        this.focusRect.bottom += i2;
        this.focusRect.left += i;
        this.focusRect.right += i;
        this.pointX = x;
        this.pointY = y;
        this.focusSticker.requestLayout();
    }

    private void zoomRect(MotionEvent motionEvent) {
        Rect rect;
        if (this.focusSticker == null || (rect = this.focusRect) == null) {
            return;
        }
        int i = (rect.left + this.focusRect.right) / 2;
        int i2 = (this.focusRect.top + this.focusRect.bottom) / 2;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int i3 = this.pointX;
        if (x <= i3 || y >= this.pointY) {
            if (x >= i3 || y <= this.pointY) {
                double distance = getDistance(x, y, i, i2);
                double distance2 = getDistance(this.pointX, this.pointY, i, i2);
                this.pointX = x;
                this.pointY = y;
                float f = (float) (((distance - distance2) * ((double) (distance < 50.0d ? 0.03f : distance < 100.0d ? 0.02f : distance < 160.0d ? 0.008f : distance < 600.0d ? 0.005f : 0.001f))) + 1.0d);
                int iHeight = (int) (this.focusRect.height() * f);
                int iWidth = (int) (this.focusRect.width() * f);
                if (Math.min(iHeight, iWidth) < 80 || Math.max(iHeight, iWidth) > 900) {
                    return;
                }
                float f2 = iWidth;
                float f3 = iHeight;
                float f4 = f2 / f3;
                float wHRatio = this.focusSticker.getWHRatio();
                if (Math.abs(f4 - wHRatio) > 0.1d) {
                    if (f4 > wHRatio) {
                        iWidth = (int) (f3 * wHRatio);
                    } else if (f4 < wHRatio) {
                        iHeight = (int) (f2 / wHRatio);
                    }
                }
                int i4 = iHeight / 2;
                this.focusRect.top = i2 - i4;
                this.focusRect.bottom = i2 + i4;
                int i5 = iWidth / 2;
                this.focusRect.left = i - i5;
                this.focusRect.right = i + i5;
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.focusSticker.getLayoutParams();
                layoutParams.width = iWidth;
                layoutParams.height = iHeight;
                this.focusSticker.setLayoutParams(layoutParams);
                this.focusSticker.requestLayout();
            }
        }
    }

    private double getDistance(int i, int i2, int i3, int i4) {
        if (i < i3) {
            i = i3;
        }
        if (i2 < i4) {
            i2 = i4;
        }
        return Math.sqrt(Math.pow(i3 - i, 2.0d) + Math.pow(i4 - i2, 2.0d));
    }

    private void rotateRect(MotionEvent motionEvent) {
        if (this.focusSticker == null || this.focusRect == null) {
            return;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int iCenterX = this.focusRect.centerX();
        int iCenterY = this.focusRect.centerY();
        float degrees = ((float) (Math.toDegrees(Math.atan2(y - iCenterY, x - iCenterX)) - Math.toDegrees(Math.atan2(this.pointY - iCenterY, this.pointX - iCenterX)))) + this.focusStickerRotation;
        if (degrees < 0.0f) {
            degrees += 360.0f;
        }
        this.focusSticker.setRotation(degrees);
        this.focusSticker.requestLayout();
    }
}
