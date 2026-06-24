package com.wugu.doublecamera.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.licheedev.commonsize.C1798R;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.bean.AnimPoint;

public class PhotoEntryAnim extends View {
    private Bitmap bitmap;
    private final AnimPoint mPoint;
    private final Paint paint;

    public PhotoEntryAnim(Context context) {
        this(context, null);
    }

    public PhotoEntryAnim(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPoint = new AnimPoint(100);
        Paint paint = new Paint();
        this.paint = paint;
        Drawable drawable = ContextCompat.getDrawable(context, C1910R.mipmap.ic_photo_anim);
        if (drawable != null) {
            this.bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        paint.setAntiAlias(true);
        paint.setTextSize(getResources().getDimensionPixelSize(C1798R.dimen.normal_42sp));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.mPoint != null) {
            this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.paint.setStyle(Paint.Style.FILL);
            float width = getWidth() / 2.0f;
            float height = getHeight() / 2.0f;
            canvas.drawCircle(width, height, this.mPoint.getRadius(), this.paint);
            if (this.bitmap != null) {
                canvas.drawBitmap(this.bitmap, width - (r4.getWidth() / 2.0f), height - (this.bitmap.getHeight() / 2.0f), this.paint);
            }
            this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        }
        super.onDraw(canvas);
    }

    void setPointRadius(int i) {
        this.mPoint.setRadius(i);
        invalidate();
    }
}
