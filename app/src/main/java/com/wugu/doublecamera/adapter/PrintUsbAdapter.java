package com.wugu.doublecamera.adapter;

import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.databinding.ItemPrintUsbBinding;

public class PrintUsbAdapter extends ABaseAdapter<ItemPrintUsbBinding, String> {
    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemPrintUsbBinding> vBViewHolder, int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ItemPrintUsbBinding itemPrintUsbBinding = (ItemPrintUsbBinding) vBViewHolder.getVBinding();
        Glide.with(itemPrintUsbBinding.getRoot().getContext()).load(str).override(300, 300).into(itemPrintUsbBinding.getRoot());
    }
}
