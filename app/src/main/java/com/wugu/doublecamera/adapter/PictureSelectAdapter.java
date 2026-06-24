package com.wugu.doublecamera.adapter;

import android.content.Context;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.PictureSelectItem;
import com.wugu.doublecamera.databinding.ItemPictureSelectBinding;
import org.apache.log4j.spi.Configurator;

public class PictureSelectAdapter extends ABaseAdapter<ItemPictureSelectBinding, PictureSelectItem> {
    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemPictureSelectBinding> vBViewHolder, int i, PictureSelectItem pictureSelectItem) {
        if (pictureSelectItem == null) {
            return;
        }
        ItemPictureSelectBinding itemPictureSelectBinding = (ItemPictureSelectBinding) vBViewHolder.getVBinding();
        Context context = itemPictureSelectBinding.getRoot().getContext();
        if (TextUtils.isEmpty(pictureSelectItem.getPhotoName()) || pictureSelectItem.getPhotoName().equals(Configurator.NULL)) {
            return;
        }
        if (pictureSelectItem.isAdded()) {
            itemPictureSelectBinding.ivPhoto.setBackgroundResource(C1910R.drawable.shape_selected);
            itemPictureSelectBinding.ivSelected.setVisibility(0);
        } else {
            itemPictureSelectBinding.ivPhoto.setBackground(null);
            itemPictureSelectBinding.ivSelected.setVisibility(8);
        }
        Glide.with(context).load(pictureSelectItem.getPhotoName()).override(500, 500).into(itemPictureSelectBinding.ivPhoto);
    }
}
