package com.wugu.doublecamera.adapter;

import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.UploadFormItem;
import com.wugu.doublecamera.databinding.ItemUploadFormSelectBinding;

public class UploadFormAdapter extends ABaseAdapter<ItemUploadFormSelectBinding, UploadFormItem> {
    private int selectedPosition = -1;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemUploadFormSelectBinding> vBViewHolder, int i, UploadFormItem uploadFormItem) {
        if (uploadFormItem == null || TextUtils.isEmpty(uploadFormItem.itemIconPath)) {
            return;
        }
        ItemUploadFormSelectBinding itemUploadFormSelectBinding = (ItemUploadFormSelectBinding) vBViewHolder.getVBinding();
        if (this.selectedPosition == i) {
            itemUploadFormSelectBinding.getRoot().setBackgroundResource(C1910R.drawable.shape_bg_select_blue);
        } else {
            itemUploadFormSelectBinding.getRoot().setBackground(null);
        }
        Glide.with(itemUploadFormSelectBinding.getRoot().getContext()).load(uploadFormItem.itemIconPath).override(200, 200).into(itemUploadFormSelectBinding.getRoot());
    }

    public void setSelectedPosition(int i) {
        int i2 = this.selectedPosition;
        if (i2 >= 0) {
            notifyItemChanged(i2);
        }
        notifyItemChanged(i);
        this.selectedPosition = i;
    }
}
