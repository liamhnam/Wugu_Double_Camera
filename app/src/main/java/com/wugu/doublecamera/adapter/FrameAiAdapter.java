package com.wugu.doublecamera.adapter;

import android.graphics.Color;
import com.brother.sdk.print.pdl.PrintImageUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.databinding.ItemFrameBinding;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;

public class FrameAiAdapter extends ABaseAdapter<ItemFrameBinding, FrameInfo> {
    public String bgPath;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemFrameBinding> vBViewHolder, int i, FrameInfo frameInfo) {
        if (frameInfo == null) {
            return;
        }
        ItemFrameBinding itemFrameBinding = (ItemFrameBinding) vBViewHolder.getVBinding();
        ViewUtil.setViewGroupBg(itemFrameBinding.getRoot(), this.bgPath);
        itemFrameBinding.tvFramePrice.setText(StringUtil.getPriceStr(frameInfo.getPrice()));
        itemFrameBinding.ivFrameExample.setBackgroundColor(Color.parseColor("#E3E3E3"));
        itemFrameBinding.ivFrameExample.setPadding(0, 0, 0, 0);
        Glide.with(itemFrameBinding.ivFrameExample).load(frameInfo.getFramePath()).diskCacheStrategy(DiskCacheStrategy.ALL).override(PrintImageUtil.ROUND_ROTATE, 400).into(itemFrameBinding.ivFrameExample);
    }

    public void setBgItemRes(UiPosition uiPosition) {
        if (uiPosition != null) {
            this.bgPath = uiPosition.resPath;
        }
    }
}
