package com.wugu.doublecamera.adapter;

import android.content.Context;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.PictureSelectItem;
import com.wugu.doublecamera.databinding.ItemUploadPictureBinding;
import org.apache.log4j.spi.Configurator;

public class UploadPictureAdapter extends ABaseAdapter<ItemUploadPictureBinding, PictureSelectItem> {
    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemUploadPictureBinding> vBViewHolder, int i, PictureSelectItem pictureSelectItem) {
        if (pictureSelectItem == null) {
            return;
        }
        ItemUploadPictureBinding itemUploadPictureBinding = (ItemUploadPictureBinding) vBViewHolder.getVBinding();
        Context context = itemUploadPictureBinding.getRoot().getContext();
        if (TextUtils.isEmpty(pictureSelectItem.getPhotoName()) || pictureSelectItem.getPhotoName().equals(Configurator.NULL)) {
            String str = context.getString(C1910R.string.downing) + " (" + pictureSelectItem.process + "%)";
            itemUploadPictureBinding.tvUpload.setVisibility(0);
            itemUploadPictureBinding.tvUpload.setText(str);
            return;
        }
        itemUploadPictureBinding.tvUpload.setVisibility(8);
        if (pictureSelectItem.isAdded()) {
            itemUploadPictureBinding.ivPhoto.setBackgroundResource(C1910R.drawable.shape_selected);
            itemUploadPictureBinding.ivSelected.setVisibility(0);
        } else {
            itemUploadPictureBinding.ivPhoto.setBackground(null);
            itemUploadPictureBinding.ivSelected.setVisibility(8);
        }
        Glide.with(context).load(pictureSelectItem.getPhotoName()).override(600, 600).into(itemUploadPictureBinding.ivPhoto);
    }
}
