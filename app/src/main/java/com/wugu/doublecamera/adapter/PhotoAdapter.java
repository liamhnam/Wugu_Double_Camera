package com.wugu.doublecamera.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.PhotoItem;
import com.wugu.doublecamera.databinding.ItemPhotoBinding;
import java.io.File;
import org.apache.log4j.spi.Configurator;

public class PhotoAdapter extends ABaseAdapter<ItemPhotoBinding, PhotoItem> {
    private Bitmap frameBitmap;
    private boolean isShowFrame;

    public void setFrameBitmap(Bitmap bitmap) {
        this.frameBitmap = bitmap;
    }

    public void setShowFrame(boolean z) {
        this.isShowFrame = z;
    }

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemPhotoBinding> vBViewHolder, int i, PhotoItem photoItem) {
        if (photoItem == null) {
            return;
        }
        ItemPhotoBinding itemPhotoBinding = (ItemPhotoBinding) vBViewHolder.getVBinding();
        Context context = itemPhotoBinding.getRoot().getContext();
        if (photoItem.getX() + photoItem.getWidth() > this.frameBitmap.getWidth() || photoItem.getY() + photoItem.getHeight() > this.frameBitmap.getHeight() || photoItem.getWidth() <= 0 || photoItem.getHeight() <= 0) {
            return;
        }
        if (this.isShowFrame) {
            Glide.with(context).load(Bitmap.createBitmap(this.frameBitmap, photoItem.getX(), photoItem.getY(), photoItem.getWidth(), photoItem.getHeight())).into(itemPhotoBinding.ivPhotoBorder);
        }
        if (TextUtils.isEmpty(photoItem.getPhotoName()) || photoItem.getPhotoName().equals(Configurator.NULL)) {
            return;
        }
        File file = new File(context.getFilesDir(), photoItem.getPhotoName());
        if (file.exists()) {
            Glide.with(context).load(file).into(itemPhotoBinding.ivPhoto);
        }
    }
}
