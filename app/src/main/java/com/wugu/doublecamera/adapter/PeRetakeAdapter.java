package com.wugu.doublecamera.adapter;

import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.databinding.ItemPeRetakePicBinding;

public class PeRetakeAdapter extends ABaseAdapter<ItemPeRetakePicBinding, Bitmap> {
    private int selectedPosition = 0;

    public void setSelectedPosition(int i, boolean z) {
        if (z) {
            notifyItemChanged(this.selectedPosition);
            notifyItemChanged(i);
        }
        this.selectedPosition = i;
    }

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemPeRetakePicBinding> vBViewHolder, int i, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        ItemPeRetakePicBinding itemPeRetakePicBinding = (ItemPeRetakePicBinding) vBViewHolder.getVBinding();
        Glide.with(itemPeRetakePicBinding.getRoot().getContext()).load(bitmap).override(300, 500).into(itemPeRetakePicBinding.ivPhoto);
        if (this.selectedPosition == i) {
            itemPeRetakePicBinding.getRoot().setBackgroundResource(C1910R.drawable.shape_selected);
        } else {
            itemPeRetakePicBinding.getRoot().setBackground(null);
        }
    }
}
