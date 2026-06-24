package com.wugu.doublecamera.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.wugu.doublecamera.databinding.LayoutCountdownBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.utils.ViewUtil;

public class CountdownView extends ConstraintLayout {
    private LayoutCountdownBinding binding;

    public CountdownView(Context context) {
        super(context);
        initView(context);
    }

    public CountdownView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        LayoutCountdownBinding layoutCountdownBindingInflate = LayoutCountdownBinding.inflate(LayoutInflater.from(context), this, true);
        this.binding = layoutCountdownBindingInflate;
        ViewUtil.setUiLocate(layoutCountdownBindingInflate.ivCountBg, UiPosIndexEnum.PHOTO_COUNTDOWN_BG);
    }

    public void setCountText(String str) {
        this.binding.tvCount.setText(str);
    }

    public void setCount(int i) {
        this.binding.tvCount.setText(i + "");
    }
}
