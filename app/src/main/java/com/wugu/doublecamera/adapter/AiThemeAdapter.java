package com.wugu.doublecamera.adapter;

import androidx.core.view.ViewCompat;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.AiThemeItem;
import com.wugu.doublecamera.databinding.ItemAiThemeBinding;

public class AiThemeAdapter extends ABaseAdapter<ItemAiThemeBinding, AiThemeItem> {
    private int selectedIndex;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemAiThemeBinding> vBViewHolder, int i, AiThemeItem aiThemeItem) {
        if (aiThemeItem == null) {
            return;
        }
        ItemAiThemeBinding itemAiThemeBinding = (ItemAiThemeBinding) vBViewHolder.getVBinding();
        itemAiThemeBinding.tvThemeName.setText(aiThemeItem.themeName);
        if (i == this.selectedIndex) {
            itemAiThemeBinding.tvThemeName.setTextColor(-1);
            itemAiThemeBinding.tvThemeName.setBackgroundResource(C1910R.drawable.shape_item_theme_selected);
        } else {
            itemAiThemeBinding.tvThemeName.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            itemAiThemeBinding.tvThemeName.setBackground(null);
        }
    }

    public void setSelectedIndex(int i) {
        this.selectedIndex = i;
    }
}
