package com.wugu.doublecamera.adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.view.ViewCompat;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.databinding.ItemCheckFrameBinding;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.StringUtil;
import java.util.List;
import java.util.Locale;

public class CheckFrameAdapter extends ABaseAdapter<ItemCheckFrameBinding, FrameInfo> {
    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemCheckFrameBinding> vBViewHolder, int i, FrameInfo frameInfo) {
        if (frameInfo == null) {
            return;
        }
        ItemCheckFrameBinding itemCheckFrameBinding = (ItemCheckFrameBinding) vBViewHolder.getVBinding();
        Bitmap localBitmap = BitmapUtil.getLocalBitmap(getContext(), frameInfo.getFramePath());
        if (localBitmap == null) {
            return;
        }
        List<FramePhotoInfo> photoInfoList = frameInfo.getPhotoInfoList();
        Bitmap bitmapCopy = localBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmapCopy);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#DFDFDF"));
        paint.setStyle(Paint.Style.FILL);
        int i2 = 1;
        for (FramePhotoInfo framePhotoInfo : photoInfoList) {
            Rect rect = new Rect(framePhotoInfo.getLocationX(), framePhotoInfo.getLocationY(), framePhotoInfo.getLocationX() + framePhotoInfo.getWidth(), framePhotoInfo.getLocationY() + framePhotoInfo.getHeight());
            paint.setAlpha(230);
            canvas.drawRect(rect, paint);
            int i3 = (rect.left + rect.right) / 2;
            int i4 = (rect.top + rect.bottom) / 2;
            Paint paint2 = new Paint();
            paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
            paint2.setTextSize(120.0f);
            paint2.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(String.valueOf(i2), i3, i4, paint2);
            i2++;
        }
        itemCheckFrameBinding.tvSize.setText(String.format(Locale.getDefault(), "%d x %d", Integer.valueOf(localBitmap.getWidth()), Integer.valueOf(localBitmap.getHeight())));
        if (AppConfig.isBelongHeadSys()) {
            itemCheckFrameBinding.tvCut.setVisibility(frameInfo.isNeedCut() ? 0 : 8);
        } else {
            itemCheckFrameBinding.tvCut.setVisibility(0);
            int iStrToInt = StringUtil.strToInt(frameInfo.getPrinterColorParam(), 0);
            if (iStrToInt == 1) {
                itemCheckFrameBinding.tvCut.setText("预览彩色，打印黑白");
            } else if (iStrToInt == 2) {
                itemCheckFrameBinding.tvCut.setText("全彩色");
            } else {
                itemCheckFrameBinding.tvCut.setText("全黑白");
            }
        }
        itemCheckFrameBinding.imageFrame.setImageBitmap(bitmapCopy);
    }
}
