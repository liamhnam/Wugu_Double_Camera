package com.wugu.doublecamera.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.databinding.LayoutPictureSplicingBinding;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PictureSplicing extends ConstraintLayout implements View.OnTouchListener {
    private final List<Integer> bgViewIdList;
    private LayoutPictureSplicingBinding binding;
    private int currentSelectIndex;
    private FrameInfo frameInfo;
    private int frameX;
    private int frameY;
    private boolean isShowBg;
    private boolean isShowSelectStroke;
    private String photoBgPath;
    private final List<Integer> photoViewIdList;
    private final List<Integer> strokeViewIdList;

    public PictureSplicing(Context context) {
        this(context, null);
    }

    public PictureSplicing(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.strokeViewIdList = new ArrayList();
        this.photoViewIdList = new ArrayList();
        this.bgViewIdList = new ArrayList();
        this.isShowBg = false;
        this.isShowSelectStroke = true;
        initView(context);
    }

    private void initView(Context context) {
        this.binding = LayoutPictureSplicingBinding.inflate(LayoutInflater.from(context), this, true);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            chooseStrokeView(view, getStrokeViewByIndex(this.currentSelectIndex));
            int i = 0;
            while (true) {
                if (i >= this.strokeViewIdList.size()) {
                    break;
                }
                if (view.getId() == this.strokeViewIdList.get(i).intValue()) {
                    this.currentSelectIndex = i;
                    break;
                }
                i++;
            }
        }
        return false;
    }

    private void chooseStrokeView(View view, View view2) {
        if (view == null || view.getTag() == null) {
            return;
        }
        if (view == view2) {
            if (((Integer) view.getTag()).intValue() == 0) {
                view.setBackgroundResource(C1910R.drawable.shape_burst_index);
                view.setTag(1);
                return;
            } else {
                view.setBackground(null);
                view.setTag(0);
                return;
            }
        }
        if (view2 != null) {
            view2.setTag(0);
            view2.setBackground(null);
        }
        view.setTag(1);
        view.setBackgroundResource(C1910R.drawable.shape_burst_index);
    }

    private View getStrokeViewByIndex(int i) {
        Integer num = this.strokeViewIdList.get(i);
        if (num == null) {
            return null;
        }
        return this.binding.layoutStroke.findViewById(num.intValue());
    }

    private void addFramePhotoSelectStroke() {
        releaseAllView();
        this.binding.ivFrame.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m862xda008078();
            }
        });
    }

    void m862xda008078() {
        BitmapFactory.Options bitmapOptions = BitmapUtil.getBitmapOptions(getContext(), this.frameInfo.getFramePath());
        if (bitmapOptions == null) {
            return;
        }
        int width = this.binding.ivFrame.getWidth();
        int height = this.binding.ivFrame.getHeight();
        int i = bitmapOptions.outWidth;
        int i2 = bitmapOptions.outHeight;
        this.frameX = 0;
        this.frameY = 0;
        float f = width;
        float f2 = i;
        float f3 = f / f2;
        float f4 = height;
        float f5 = i2;
        float f6 = f4 / f5;
        if (f2 / f5 > f / f4) {
            this.frameY = ((int) (f4 - (f5 * f3))) / 2;
        } else {
            this.frameX = ((int) (f - (f2 * f6))) / 2;
            f3 = f6;
        }
        for (int i3 = 0; i3 < this.frameInfo.getPhotoInfoList().size(); i3++) {
            if (this.frameInfo.getPhotoInfoList().get(i3) != null) {
                int locationX = (int) (r1.getLocationX() * f3);
                int locationY = (int) (r1.getLocationY() * f3);
                int width2 = (int) (((double) (r1.getWidth() * f3)) + 2.5d);
                int height2 = (int) (((double) (r1.getHeight() * f3)) + 2.5d);
                int iGenerateViewId = View.generateViewId();
                int iGenerateViewId2 = View.generateViewId();
                this.photoViewIdList.add(Integer.valueOf(iGenerateViewId));
                this.strokeViewIdList.add(Integer.valueOf(iGenerateViewId2));
                if (this.isShowBg) {
                    int iGenerateViewId3 = View.generateViewId();
                    this.bgViewIdList.add(Integer.valueOf(iGenerateViewId3));
                    SubsamplingScaleImageView subsamplingScaleImageView = new SubsamplingScaleImageView(getContext());
                    subsamplingScaleImageView.setId(iGenerateViewId3);
                    subsamplingScaleImageView.setFocusable(false);
                    subsamplingScaleImageView.setClickable(false);
                    subsamplingScaleImageView.setMinimumScaleType(2);
                    subsamplingScaleImageView.setQuickScaleEnabled(false);
                    subsamplingScaleImageView.setLayoutParams(new ViewGroup.LayoutParams(width2, height2));
                    subsamplingScaleImageView.setX(this.frameX + locationX);
                    subsamplingScaleImageView.setY(this.frameY + locationY);
                    this.binding.layoutPicture.addView(subsamplingScaleImageView);
                }
                SubsamplingScaleImageView subsamplingScaleImageView2 = new SubsamplingScaleImageView(getContext());
                subsamplingScaleImageView2.setMinimumScaleType(2);
                subsamplingScaleImageView2.setMaxScale(2.0f);
                subsamplingScaleImageView2.setId(iGenerateViewId);
                subsamplingScaleImageView2.setQuickScaleEnabled(false);
                subsamplingScaleImageView2.setLayoutParams(new ViewGroup.LayoutParams(width2, height2));
                subsamplingScaleImageView2.setX(this.frameX + locationX);
                subsamplingScaleImageView2.setY(this.frameY + locationY);
                this.binding.layoutPicture.addView(subsamplingScaleImageView2);
                if (this.isShowSelectStroke) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    appCompatImageView.setId(iGenerateViewId2);
                    appCompatImageView.setLayoutParams(new ViewGroup.LayoutParams(width2, height2));
                    appCompatImageView.setX(this.frameX + locationX);
                    appCompatImageView.setY(this.frameY + locationY);
                    appCompatImageView.setTag(0);
                    this.binding.layoutStroke.addView(appCompatImageView);
                    appCompatImageView.setOnTouchListener(this);
                }
            }
        }
        this.currentSelectIndex = 0;
        chooseStrokeView(getStrokeViewByIndex(0), null);
    }

    public void setCurrentSelectIndex(int i) {
        this.currentSelectIndex = i;
    }

    public void setShowBg(boolean z) {
        this.isShowBg = z;
    }

    public void setShowSelectStroke(boolean z) {
        this.isShowSelectStroke = z;
    }

    public void setPictureBg(String str) {
        this.photoBgPath = str;
        List<Integer> list = this.bgViewIdList;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.bgViewIdList.size(); i++) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.binding.layoutPicture.findViewById(this.bgViewIdList.get(i).intValue());
            subsamplingScaleImageView.recycle();
            subsamplingScaleImageView.setImage(ImageSource.uri(this.photoBgPath));
        }
    }

    public String getPhotoBgPath() {
        return this.photoBgPath;
    }

    public void clearPictureBg() {
        this.photoBgPath = null;
        List<Integer> list = this.bgViewIdList;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.bgViewIdList.size(); i++) {
            ((SubsamplingScaleImageView) this.binding.layoutPicture.findViewById(this.bgViewIdList.get(i).intValue())).recycle();
        }
    }

    public void setFrameInfo(FrameInfo frameInfo) {
        if (frameInfo == null) {
            return;
        }
        this.frameInfo = frameInfo;
        Glide.with(getContext()).load(frameInfo.getFramePath()).into(this.binding.ivFrame);
        addFramePhotoSelectStroke();
    }

    public void setPicture(String str, boolean z) {
        if (this.photoViewIdList.isEmpty()) {
            ToastHelper.getInstance().showToast(getContext().getString(C1910R.string.pls_select_form_num));
            return;
        }
        SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.binding.layoutPicture.findViewById(this.photoViewIdList.get(this.currentSelectIndex).intValue());
        subsamplingScaleImageView.recycle();
        subsamplingScaleImageView.setImage(ImageSource.uri(str));
        subsamplingScaleImageView.setTag(str);
        if (!z || this.currentSelectIndex >= this.photoViewIdList.size() - 1) {
            return;
        }
        if (this.isShowSelectStroke) {
            chooseStrokeView(getStrokeViewByIndex(this.currentSelectIndex + 1), getStrokeViewByIndex(this.currentSelectIndex));
        }
        this.currentSelectIndex++;
    }

    public void compoundImage(final IStringListener iStringListener) {
        LoggerUtil.m1181d("PicturesSplicing", "compoundImage");
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.m863xe5ca5a21(iStringListener);
            }
        });
    }

    void m863xe5ca5a21(IStringListener iStringListener) throws Throwable {
        SubsamplingScaleImageView subsamplingScaleImageView;
        File filesDir = getContext().getFilesDir();
        LoggerUtil.m1181d("PicturesSplicing", "getFramePath " + this.frameInfo.getFramePath());
        Bitmap localBitmap = BitmapUtil.getLocalBitmap(getContext(), this.frameInfo.getFramePath());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(localBitmap.getWidth(), localBitmap.getHeight(), localBitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        LoggerUtil.m1181d("PicturesSplicing", "photoViewIdList.size() " + this.photoViewIdList.size());
        for (int i = 0; i < this.photoViewIdList.size(); i++) {
            SubsamplingScaleImageView subsamplingScaleImageView2 = (SubsamplingScaleImageView) this.binding.layoutPicture.findViewById(this.photoViewIdList.get(i).intValue());
            if (subsamplingScaleImageView2 != null) {
                RectF rectF = new RectF();
                subsamplingScaleImageView2.getPanRemaining(rectF);
                Bitmap viewBitmap = getViewBitmap((String) subsamplingScaleImageView2.getTag(), rectF, subsamplingScaleImageView2.getScale());
                if (viewBitmap != null) {
                    FramePhotoInfo framePhotoInfo = this.frameInfo.getPhotoInfoList().get(i);
                    Rect rect = new Rect(framePhotoInfo.getLocationX(), framePhotoInfo.getLocationY(), framePhotoInfo.getLocationX() + framePhotoInfo.getWidth(), framePhotoInfo.getLocationY() + framePhotoInfo.getHeight());
                    LoggerUtil.m1181d("PicturesSplicing", "photoBgPath " + this.photoBgPath);
                    if (this.isShowBg && !TextUtils.isEmpty(this.photoBgPath) && (subsamplingScaleImageView = (SubsamplingScaleImageView) this.binding.layoutPicture.findViewById(this.bgViewIdList.get(i).intValue())) != null) {
                        RectF rectF2 = new RectF();
                        subsamplingScaleImageView.getPanRemaining(rectF2);
                        Bitmap viewBitmap2 = getViewBitmap(this.photoBgPath, rectF2, subsamplingScaleImageView.getScale());
                        if (viewBitmap2 != null) {
                            canvas.drawBitmap(viewBitmap2, (Rect) null, rect, (Paint) null);
                            viewBitmap2.recycle();
                        }
                    }
                    canvas.drawBitmap(viewBitmap, (Rect) null, rect, (Paint) null);
                    viewBitmap.recycle();
                }
            }
        }
        canvas.drawBitmap(localBitmap, 0.0f, 0.0f, (Paint) null);
        File file = new File(filesDir, AppConfig.getFrameName());
        FileUtil.saveBitmap(bitmapCreateBitmap, file);
        OrderManager.replaceModelFinalPicPath = file.getAbsolutePath();
        if (iStringListener != null) {
            iStringListener.setValue(file.getAbsolutePath());
        }
    }

    private Bitmap getViewBitmap(String str, RectF rectF, float f) {
        try {
            BitmapRegionDecoder bitmapRegionDecoderNewInstance = BitmapRegionDecoder.newInstance(str, false);
            int i = (int) (rectF.left / f);
            int i2 = (int) (rectF.top / f);
            int i3 = (int) (rectF.right / f);
            int i4 = (int) (rectF.bottom / f);
            int iMax = Math.max(i, 0);
            int iMax2 = Math.max(i2, 0);
            int iMin = Math.min(bitmapRegionDecoderNewInstance.getWidth() - i3, bitmapRegionDecoderNewInstance.getWidth());
            int iMin2 = Math.min(bitmapRegionDecoderNewInstance.getHeight() - i4, bitmapRegionDecoderNewInstance.getHeight());
            if (iMin > iMax && iMin2 > iMax2) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmapDecodeRegion = bitmapRegionDecoderNewInstance.decodeRegion(new Rect(iMax, iMax2, iMin, iMin2), options);
                bitmapRegionDecoderNewInstance.recycle();
                return bitmapDecodeRegion;
            }
            return null;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void releaseAllView() {
        this.strokeViewIdList.clear();
        this.photoViewIdList.clear();
        for (int i = 0; i < this.binding.layoutStroke.getChildCount(); i++) {
            this.binding.layoutStroke.getChildAt(i).setOnTouchListener(null);
        }
        for (int i2 = 0; i2 < this.binding.layoutPicture.getChildCount(); i2++) {
            View childAt = this.binding.layoutPicture.getChildAt(i2);
            if (childAt instanceof SubsamplingScaleImageView) {
                ((SubsamplingScaleImageView) childAt).recycle();
            }
        }
        this.binding.layoutPicture.removeAllViews();
        this.binding.layoutStroke.removeAllViews();
    }
}
