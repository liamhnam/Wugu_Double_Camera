package com.wugu.doublecamera.adapter;

import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.databinding.ItemRetakePictureBinding;

public class RetakeAdapter extends ABaseAdapter<ItemRetakePictureBinding, Bitmap> {
    private Bitmap frameBitmap;
    private int height;
    private boolean isShowFrame;
    private int selectedPosition = 0;
    private int width;

    private int f2437x;

    private int f2438y;

    public void setPictureInfo(int i, int i2, int i3, int i4) {
        this.height = i;
        this.width = i2;
        this.f2437x = i3;
        this.f2438y = i4;
    }

    public void setFrameBitmap(Bitmap bitmap) {
        this.frameBitmap = bitmap;
    }

    public void setSelectedPosition(int i) {
        this.selectedPosition = i;
    }

    public void setShowFrame(boolean z) {
        this.isShowFrame = z;
    }

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemRetakePictureBinding> vBViewHolder, int i, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        ItemRetakePictureBinding itemRetakePictureBinding = (ItemRetakePictureBinding) vBViewHolder.getVBinding();
        Glide.with(itemRetakePictureBinding.getRoot().getContext()).load(bitmap).override(300, 500).into(itemRetakePictureBinding.ivPhoto);
        if (this.selectedPosition == i) {
            itemRetakePictureBinding.getRoot().setBackgroundResource(C1910R.drawable.shape_selected);
        } else {
            itemRetakePictureBinding.getRoot().setBackground(null);
        }
    }
}
