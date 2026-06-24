package com.wugu.doublecamera.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.databinding.ItemPosterFrameBinding;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;

public class PosterFrameAdapter extends ABaseAdapter<ItemPosterFrameBinding, FrameInfo> {
    public String bgPath;
    private int selectedIndex;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemPosterFrameBinding> vBViewHolder, int i, FrameInfo frameInfo) {
        if (frameInfo == null) {
            return;
        }
        ItemPosterFrameBinding itemPosterFrameBinding = (ItemPosterFrameBinding) vBViewHolder.getVBinding();
        setFramePrice(itemPosterFrameBinding, frameInfo);
        Glide.with(itemPosterFrameBinding.ivFrame).load(frameInfo.getFramePath()).diskCacheStrategy(DiskCacheStrategy.ALL).override(300, 400).into(itemPosterFrameBinding.ivFrame);
        if (i == this.selectedIndex) {
            ViewUtil.setImageBackground(itemPosterFrameBinding.ivFrameSelected, this.bgPath);
        } else {
            itemPosterFrameBinding.ivFrameSelected.setBackground(null);
        }
    }

    private void setFramePrice(ItemPosterFrameBinding itemPosterFrameBinding, FrameInfo frameInfo) {
        itemPosterFrameBinding.tvFramePrice.setText("￥ " + StringUtil.getPriceStr(frameInfo.getPrice()));
    }

    public void setBgItemRes(UiPosition uiPosition) {
        if (uiPosition != null) {
            this.bgPath = uiPosition.resPath;
        }
    }

    public void setSelectedIndex(int i) {
        this.selectedIndex = i;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }
}
