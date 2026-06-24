package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

class BandRender implements IRenderRequest {
    IBandRenderer bandRender;

    public BandRender(IBandRenderer iBandRenderer) {
        this.bandRender = iBandRenderer;
    }

    @Override
    public void OnDraw(Canvas canvas, DrawInfo drawInfo) {
        Bitmap band = this.bandRender.getBand(drawInfo.getCurPage(), drawInfo.getPrintableWidth(), drawInfo.getPrintableHeight(), drawInfo.getDrawBandRect());
        if (band != null) {
            canvas.save();
            canvas.clipRect(0, 0, canvas.getWidth(), canvas.getHeight());
            canvas.drawBitmap(band, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
        }
    }
}
