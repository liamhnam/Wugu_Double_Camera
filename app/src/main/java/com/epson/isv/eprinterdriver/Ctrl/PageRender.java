package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

class PageRender implements IRenderRequest {
    Matrix mConvertM;
    int mDegrees;
    JobAttribute mJobAttribute;
    Point mOffset;
    Bitmap mPageBitmap;
    DrawInfo mPageInfo = new DrawInfo(0, 0);
    IPageRenderer mPageRender;
    Matrix mPostOffsetM;
    Matrix mPreOffsetM;
    Matrix mRevertM;

    boolean updatePageInfo(DrawInfo drawInfo) {
        if (drawInfo.getCurPage() <= this.mPageInfo.getCurPage()) {
            return false;
        }
        DrawInfo drawInfo2 = new DrawInfo(drawInfo.getPrintableWidth(), drawInfo.getPrintableHeight());
        this.mPageInfo = drawInfo2;
        drawInfo2.setCurPage(drawInfo.getCurPage());
        this.mPageInfo.setTotalPages(drawInfo.getTotalPages());
        return true;
    }

    public PageRender(IPageRenderer iPageRenderer, JobAttribute jobAttribute) {
        this.mPageRender = iPageRenderer;
        this.mJobAttribute = jobAttribute;
    }

    @Override
    public void OnDraw(Canvas canvas, DrawInfo drawInfo) {
        int i;
        int i2;
        int i3;
        if (updatePageInfo(drawInfo)) {
            EPSetting ePSettingInstance = EPSetting.instance();
            short curPage = drawInfo.getCurPage();
            int paperDirection = ePSettingInstance.getPaperDirection();
            if (paperDirection == 1) {
                this.mDegrees = 270;
            } else {
                this.mDegrees = 0;
            }
            boolean duplexPrint = ePSettingInstance.getDuplexPrint();
            if (duplexPrint) {
                boolean duplexReverseBackpage = ePSettingInstance.getDuplexReverseBackpage();
                if (curPage % 2 == 0 && duplexReverseBackpage) {
                    if (paperDirection == 1) {
                        this.mDegrees = -270;
                    } else {
                        this.mDegrees = 180;
                    }
                }
            }
            if (duplexPrint) {
                this.mOffset = ePSettingInstance.getDuplexBindingMargin();
            } else {
                this.mOffset = ePSettingInstance.getPhotoSealOffset();
            }
            int printableWidth = drawInfo.getPrintableWidth();
            int printableHeight = drawInfo.getPrintableHeight();
            int i4 = this.mDegrees;
            if (i4 == 270 || i4 == -270) {
                printableWidth = drawInfo.getPrintableHeight();
                printableHeight = drawInfo.getPrintableWidth();
            }
            this.mPageBitmap = this.mPageRender.getPage(curPage, printableWidth, printableHeight);
            int printableWidth2 = drawInfo.getPrintableWidth();
            int printableHeight2 = drawInfo.getPrintableHeight();
            this.mConvertM = new Matrix();
            this.mRevertM = new Matrix();
            this.mPreOffsetM = new Matrix();
            this.mPostOffsetM = new Matrix();
            int i5 = this.mDegrees;
            if (i5 == -270) {
                this.mConvertM.postRotate(i5);
                this.mConvertM.postTranslate(printableHeight2, 0.0f);
                this.mRevertM.postRotate(this.mDegrees * (-1));
                this.mPreOffsetM.postTranslate(-this.mOffset.x, 0.0f);
                this.mPostOffsetM.postTranslate(this.mOffset.y, 0.0f);
            } else if (i5 == 0) {
                this.mPreOffsetM.postTranslate(0.0f, -this.mOffset.y);
                this.mPostOffsetM.postTranslate(this.mOffset.x, 0.0f);
            } else if (i5 == 90) {
                this.mConvertM.postRotate(i5);
                this.mConvertM.postTranslate(printableHeight2, 0.0f);
                this.mRevertM.postRotate(-this.mDegrees);
                this.mPreOffsetM.postTranslate(-this.mOffset.x, 0.0f);
                this.mPostOffsetM.postTranslate(this.mOffset.y, 0.0f);
            } else if (i5 == 180) {
                this.mConvertM.postRotate(i5);
                this.mConvertM.postTranslate(printableWidth2, printableHeight2);
                this.mRevertM.postRotate(-this.mDegrees);
                this.mPreOffsetM.postTranslate(0.0f, -this.mOffset.y);
                this.mPostOffsetM.postTranslate(-this.mOffset.x, 0.0f);
            } else if (i5 == 270) {
                this.mConvertM.postRotate(i5);
                this.mConvertM.postTranslate(0.0f, printableWidth2);
                this.mRevertM.postRotate(-this.mDegrees);
                this.mPreOffsetM.postTranslate(-this.mOffset.x, 0.0f);
                this.mPostOffsetM.postTranslate(-this.mOffset.y, 0.0f);
            }
        }
        if (this.mPageInfo.getPrintableHeight() > drawInfo.getDrawBandRect().top) {
            RectF rectF = new RectF(drawInfo.getDrawBandRect());
            RectF rectF2 = new RectF();
            this.mConvertM.mapRect(rectF2, rectF);
            this.mPreOffsetM.mapRect(rectF2);
            int i6 = (int) rectF2.left;
            int i7 = (int) rectF2.top;
            int i8 = (int) (rectF2.right - rectF2.left);
            int i9 = (int) (rectF2.bottom - rectF2.top);
            Matrix matrix = new Matrix(this.mPostOffsetM);
            if (i6 < 0) {
                if (this.mDegrees == 270) {
                    matrix.postTranslate(0.0f, i6 * (-1));
                }
                i8 += i6;
                i = 0;
            } else {
                i = i6;
            }
            int i10 = i8;
            if (i7 < 0) {
                if (this.mDegrees == 0) {
                    matrix.postTranslate(0.0f, i7 * (-1));
                }
                i2 = i9 + i7;
                i3 = 0;
            } else {
                i2 = i9;
                i3 = i7;
            }
            Bitmap bitmap = this.mPageBitmap;
            if (bitmap == null || i < 0 || i3 < 0 || i10 <= 0 || i2 <= 0) {
                return;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i, i3, i10, i2, this.mRevertM, false);
            canvas.save();
            canvas.clipRect(0, 0, canvas.getWidth(), canvas.getHeight());
            canvas.setMatrix(matrix);
            canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
        }
    }
}
