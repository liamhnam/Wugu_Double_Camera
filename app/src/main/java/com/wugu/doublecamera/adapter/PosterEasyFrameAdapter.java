package com.wugu.doublecamera.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.faceunity.core.media.video.VideoRecordHelper;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.databinding.ItemPosterEasyFrameBinding;

public class PosterEasyFrameAdapter extends ABaseAdapter<ItemPosterEasyFrameBinding, FrameInfo> {
    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemPosterEasyFrameBinding> vBViewHolder, int i, FrameInfo frameInfo) {
        if (frameInfo == null) {
            return;
        }
        ItemPosterEasyFrameBinding itemPosterEasyFrameBinding = (ItemPosterEasyFrameBinding) vBViewHolder.getVBinding();
        Glide.with(itemPosterEasyFrameBinding.getRoot()).load(frameInfo.getFramePath()).override(VideoRecordHelper.MAX_VIDEO_LENGTH, VideoRecordHelper.MAX_VIDEO_LENGTH).diskCacheStrategy(DiskCacheStrategy.ALL).into(itemPosterEasyFrameBinding.getRoot());
    }
}
