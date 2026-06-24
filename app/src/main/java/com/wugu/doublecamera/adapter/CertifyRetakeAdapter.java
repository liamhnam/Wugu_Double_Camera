package com.wugu.doublecamera.adapter;

import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.databinding.ItemRetakePictureBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.utils.ViewUtil;

public class CertifyRetakeAdapter extends ABaseAdapter<ItemRetakePictureBinding, Bitmap> {
    private int selectedPosition = 0;

    public void setSelectedPosition(int i) {
        this.selectedPosition = i;
    }

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemRetakePictureBinding> vBViewHolder, int i, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        ItemRetakePictureBinding itemRetakePictureBinding = (ItemRetakePictureBinding) vBViewHolder.getVBinding();
        Glide.with(itemRetakePictureBinding.getRoot().getContext()).load(bitmap).into(itemRetakePictureBinding.ivPhoto);
        ViewUtil.setUiLocate(itemRetakePictureBinding.getRoot(), UiPosIndexEnum.CERTIFY_CONFIRM_ITEM_BG);
    }
}
