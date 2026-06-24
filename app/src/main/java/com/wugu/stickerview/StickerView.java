package com.wugu.stickerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.wugu.stickerview.databinding.LayoutStickerBinding;

public class StickerView extends ConstraintLayout {
    private LayoutStickerBinding binding;
    private Bitmap bitmap;
    private boolean isBaseHeight;
    private boolean isBitmapText;
    private boolean isFocus;
    private int keyTag;
    private int opStatus;
    private float ratioWH;
    private float rotation;
    private IStickerTouchListener stickerTouchListener;
    private Rect viewRect;

    public StickerView(Context context) {
        super(context);
        this.opStatus = -1;
        this.keyTag = 0;
        initView(context);
    }

    public StickerView(Context context, boolean z) {
        super(context);
        this.opStatus = -1;
        this.keyTag = 0;
        initView(context);
        this.isBitmapText = z;
        if (z) {
            this.binding.ivStickerIcon.setPadding(0, 0, 0, 0);
        }
    }

    public StickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.opStatus = -1;
        this.keyTag = 0;
        initView(context);
    }

    private void initView(Context context) {
        this.binding = LayoutStickerBinding.inflate(LayoutInflater.from(context), this, true);
        setTouchListener();
    }

    public Rect getViewRect() {
        return this.viewRect;
    }

    public void setViewRect(Rect rect) {
        this.viewRect = rect;
    }

    public boolean isBitmapText() {
        return this.isBitmapText;
    }

    public void setStickerTouchListener(IStickerTouchListener iStickerTouchListener) {
        this.stickerTouchListener = iStickerTouchListener;
    }

    public void setFocus(boolean z) {
        this.isFocus = z;
        this.binding.ivDelete.setVisibility(z ? 0 : 8);
        this.binding.ivRotate.setVisibility(z ? 0 : 8);
        if (!this.isBitmapText) {
            this.binding.ivZoom.setVisibility(z ? 0 : 8);
        } else {
            this.binding.ivZoom.setVisibility(8);
        }
        if (z) {
            this.binding.ivStickerIcon.setBackgroundResource(C2056R.drawable.shape_bg_sticker);
            setTouchListener();
        } else {
            this.binding.ivStickerIcon.setBackground(null);
            releaseTouchListener();
        }
    }

    public int[] getPadding() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.binding.ivStickerIcon.getLayoutParams();
        return new int[]{this.binding.ivStickerIcon.getPaddingStart() + layoutParams.leftMargin, this.binding.ivStickerIcon.getPaddingTop() + layoutParams.topMargin, this.binding.ivStickerIcon.getPaddingEnd() + layoutParams.rightMargin, this.binding.ivStickerIcon.getPaddingBottom() + layoutParams.bottomMargin};
    }

    public int getOpStatus() {
        return this.opStatus;
    }

    public void setOpStatus(int i) {
        this.opStatus = i;
    }

    public void setKeyTag(int i) {
        this.keyTag = i;
    }

    public int getKeyTag() {
        return this.keyTag;
    }

    public float getWHRatio() {
        return this.ratioWH;
    }

    public boolean isBaseHeight() {
        return this.isBaseHeight;
    }

    public void setBaseHeight(boolean z) {
        this.isBaseHeight = z;
    }

    private void setTouchListener() {
        this.binding.ivDelete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m1749lambda$setTouchListener$0$comwugustickerviewStickerView(view, motionEvent);
            }
        });
        this.binding.ivZoom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m1750lambda$setTouchListener$1$comwugustickerviewStickerView(view, motionEvent);
            }
        });
        this.binding.ivRotate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m1751lambda$setTouchListener$2$comwugustickerviewStickerView(view, motionEvent);
            }
        });
        this.binding.ivStickerIcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m1752lambda$setTouchListener$3$comwugustickerviewStickerView(view, motionEvent);
            }
        });
    }

    boolean m1749lambda$setTouchListener$0$comwugustickerviewStickerView(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1) {
            this.opStatus = 0;
        }
        return false;
    }

    boolean m1750lambda$setTouchListener$1$comwugustickerviewStickerView(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.opStatus = 1;
        return false;
    }

    boolean m1751lambda$setTouchListener$2$comwugustickerviewStickerView(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.opStatus = 2;
        return false;
    }

    boolean m1752lambda$setTouchListener$3$comwugustickerviewStickerView(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        IStickerTouchListener iStickerTouchListener = this.stickerTouchListener;
        if (iStickerTouchListener != null) {
            iStickerTouchListener.stickerTouch(this.keyTag, this.isFocus);
        }
        if (this.opStatus != -1) {
            return false;
        }
        this.opStatus = 3;
        return false;
    }

    private void releaseTouchListener() {
        this.binding.ivRotate.setOnTouchListener(null);
        this.binding.ivZoom.setOnTouchListener(null);
        this.binding.ivDelete.setOnTouchListener(null);
    }

    @Override
    public float getRotation() {
        return this.rotation;
    }

    @Override
    public void setRotation(float f) {
        super.setRotation(f);
        this.rotation = f;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.ratioWH = bitmap.getWidth() / bitmap.getHeight();
        this.binding.ivStickerIcon.setImageBitmap(bitmap);
    }

    public void setImageGray(boolean z) {
        if (!z) {
            this.binding.ivStickerIcon.setColorFilter((ColorFilter) null);
            return;
        }
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        this.binding.ivStickerIcon.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }
}
