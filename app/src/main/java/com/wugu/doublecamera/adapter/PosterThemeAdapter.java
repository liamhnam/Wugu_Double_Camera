package com.wugu.doublecamera.adapter;

import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.FrameThemeInfo;
import com.wugu.doublecamera.databinding.ItemFrameThemeBinding;
import com.wugu.doublecamera.utils.ViewUtil;

public class PosterThemeAdapter extends ABaseAdapter<ItemFrameThemeBinding, FrameThemeInfo> {
    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemFrameThemeBinding> vBViewHolder, int i, FrameThemeInfo frameThemeInfo) {
        if (frameThemeInfo == null) {
            return;
        }
        ViewUtil.setImageDrawable(((ItemFrameThemeBinding) vBViewHolder.getVBinding()).ivThemeImage, frameThemeInfo.getThemeName());
    }
}
