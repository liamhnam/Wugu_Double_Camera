package com.wugu.doublecamera.adapter;

import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.FrameThemeInfo;
import com.wugu.doublecamera.databinding.ItemFrameThemeBinding;
import com.wugu.doublecamera.utils.ViewUtil;

public class FrameThemeAdapter extends ABaseAdapter<ItemFrameThemeBinding, FrameThemeInfo> {
    private int selectedIndex;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemFrameThemeBinding> vBViewHolder, int i, FrameThemeInfo frameThemeInfo) {
        if (frameThemeInfo == null) {
            return;
        }
        ItemFrameThemeBinding itemFrameThemeBinding = (ItemFrameThemeBinding) vBViewHolder.getVBinding();
        ViewUtil.setImageDrawable(itemFrameThemeBinding.ivThemeImage, frameThemeInfo.getThemeName());
        if (i == this.selectedIndex) {
            itemFrameThemeBinding.ivThemeImage.setImageAlpha(255);
            ViewUtil.setImageViewGray(itemFrameThemeBinding.ivThemeImage, false, 1.0f);
        } else {
            ViewUtil.setImageViewGray(itemFrameThemeBinding.ivThemeImage, true, 0.2f);
            itemFrameThemeBinding.ivThemeImage.setImageAlpha(200);
        }
    }

    public void setSelectedIndex(int i) {
        this.selectedIndex = i;
    }
}
