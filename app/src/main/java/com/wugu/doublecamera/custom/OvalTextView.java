package com.wugu.doublecamera.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class OvalTextView extends AppCompatTextView {
    private Paint backgroundPaint;

    public OvalTextView(Context context) {
        super(context);
        init();
    }

    public OvalTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public OvalTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.backgroundPaint = paint;
        paint.setColor(-1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = getWidth();
        float height = getHeight();
        canvas.drawCircle(width / 2.0f, height / 2.0f, Math.min(width, height) / 2.0f, this.backgroundPaint);
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(getText().toString(), getMeasuredWidth() / 2, (int) ((getMeasuredHeight() / 2) - ((paint.descent() + paint.ascent()) / 2.0f)), paint);
    }
}
