package com.wugu.doublecamera.adapter;

import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.databinding.ItemReplaceBgBinding;

public class ReplaceBgAdapter extends ABaseAdapter<ItemReplaceBgBinding, String> {
    private int selectedPosition = -1;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemReplaceBgBinding> vBViewHolder, int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ItemReplaceBgBinding itemReplaceBgBinding = (ItemReplaceBgBinding) vBViewHolder.getVBinding();
        Glide.with(vBViewHolder.itemView.getContext()).load(str).override(300, 300).into(itemReplaceBgBinding.getRoot());
        if (this.selectedPosition == i) {
            itemReplaceBgBinding.getRoot().setBackgroundResource(C1910R.drawable.shape_selected);
        } else {
            itemReplaceBgBinding.getRoot().setBackground(null);
        }
    }

    public void setSelectedPosition(int i) {
        this.selectedPosition = i;
    }

    public int getSelectedPosition() {
        return this.selectedPosition;
    }
}
