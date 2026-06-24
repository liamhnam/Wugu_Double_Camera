package com.wugu.doublecamera.adapter;

import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.PhotoAddedItem;
import com.wugu.doublecamera.databinding.ItemPhotoAddedBinding;

public class PhotoAddedAdapter extends ABaseAdapter<ItemPhotoAddedBinding, PhotoAddedItem> {
    private int selectedPosition = 0;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemPhotoAddedBinding> vBViewHolder, int i, PhotoAddedItem photoAddedItem) {
        ItemPhotoAddedBinding itemPhotoAddedBinding = (ItemPhotoAddedBinding) vBViewHolder.getVBinding();
        if (photoAddedItem == null) {
            return;
        }
        itemPhotoAddedBinding.tvName.setText(photoAddedItem.getName());
        Glide.with(itemPhotoAddedBinding.getRoot().getContext()).load(photoAddedItem.getDemoPath()).into(itemPhotoAddedBinding.ivDemo);
        if (this.selectedPosition == i) {
            itemPhotoAddedBinding.getRoot().setBackgroundResource(C1910R.drawable.shape_selected);
        } else {
            itemPhotoAddedBinding.getRoot().setBackground(null);
        }
    }

    public void setSelectedPosition(int i) {
        this.selectedPosition = i;
    }
}
