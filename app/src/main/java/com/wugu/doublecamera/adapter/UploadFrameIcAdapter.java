package com.wugu.doublecamera.adapter;

import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.UpFrameIcItem;
import com.wugu.doublecamera.databinding.ItemUploadFrameIcSelectBinding;

public class UploadFrameIcAdapter extends ABaseAdapter<ItemUploadFrameIcSelectBinding, UpFrameIcItem> {
    private int selectedPosition = 0;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemUploadFrameIcSelectBinding> vBViewHolder, int i, UpFrameIcItem upFrameIcItem) {
        if (upFrameIcItem == null) {
            return;
        }
        ItemUploadFrameIcSelectBinding itemUploadFrameIcSelectBinding = (ItemUploadFrameIcSelectBinding) vBViewHolder.getVBinding();
        if (this.selectedPosition == i) {
            itemUploadFrameIcSelectBinding.getRoot().setBackgroundResource(C1910R.drawable.shape_bg_select_blue);
        } else {
            itemUploadFrameIcSelectBinding.getRoot().setBackground(null);
        }
        Glide.with(itemUploadFrameIcSelectBinding.getRoot().getContext()).load(upFrameIcItem.upFrameIcPath).override(200, 200).into(itemUploadFrameIcSelectBinding.getRoot());
    }

    public void setSelectedPosition(int i) {
        int i2 = this.selectedPosition;
        if (i2 >= 0) {
            notifyItemChanged(i2);
        }
        notifyItemChanged(i);
        this.selectedPosition = i;
    }

    public int getSelectedPosition() {
        return this.selectedPosition;
    }
}
