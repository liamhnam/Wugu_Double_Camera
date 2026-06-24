package com.wugu.doublecamera.adapter;

import com.bumptech.glide.Glide;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.databinding.ItemStickerBinding;

public class StickerAdapter extends ABaseAdapter<ItemStickerBinding, String> {
    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemStickerBinding> vBViewHolder, int i, String str) {
        ItemStickerBinding itemStickerBinding = (ItemStickerBinding) vBViewHolder.getVBinding();
        Glide.with(itemStickerBinding.getRoot().getContext()).load(str).override(120, 120).into(itemStickerBinding.ivSticker);
    }
}
