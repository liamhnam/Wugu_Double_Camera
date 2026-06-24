package com.wugu.doublecamera.adapter;

import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.StickerTab;
import com.wugu.doublecamera.databinding.ItemStickerTabBinding;
import com.wugu.doublecamera.utils.ViewUtil;

public class StickerTabAdapter extends ABaseAdapter<ItemStickerTabBinding, StickerTab> {
    private int selectedIndex = 0;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemStickerTabBinding> vBViewHolder, int i, StickerTab stickerTab) {
        if (stickerTab == null) {
            return;
        }
        ItemStickerTabBinding itemStickerTabBinding = (ItemStickerTabBinding) vBViewHolder.getVBinding();
        ViewUtil.setImageDrawable(itemStickerTabBinding.getRoot(), stickerTab.iconPath);
        if (AppConfig.isBelongHeadSys()) {
            if (i == this.selectedIndex) {
                ViewUtil.setImageViewGray(itemStickerTabBinding.getRoot(), false, 1.0f);
                return;
            } else {
                ViewUtil.setImageViewGray(itemStickerTabBinding.getRoot(), true, 0.0f);
                return;
            }
        }
        if (i == this.selectedIndex) {
            itemStickerTabBinding.getRoot().setAlpha(1.0f);
        } else {
            itemStickerTabBinding.getRoot().setAlpha(0.6f);
        }
    }

    public void setSelectedIndex(int i) {
        this.selectedIndex = i;
    }
}
