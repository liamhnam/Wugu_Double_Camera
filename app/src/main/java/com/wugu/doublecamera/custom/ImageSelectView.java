package com.wugu.doublecamera.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.databinding.LayoutSelectIamgeBinding;

public class ImageSelectView extends ConstraintLayout {
    private LayoutSelectIamgeBinding binding;
    private String imagePath;

    public ImageSelectView(Context context) {
        super(context);
        initView(context);
    }

    public ImageSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        this.binding = LayoutSelectIamgeBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void setImage(String str) {
        this.binding.ivPhoto.setRotation(0.0f);
        this.imagePath = str;
        Glide.with(this.binding.ivPhoto).load(str).into(this.binding.ivPhoto);
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public AppCompatImageView getImageView() {
        return this.binding.ivPhoto;
    }

    public void setSelect(boolean z) {
        if (z) {
            this.binding.ivStroke.setBackgroundResource(C1910R.drawable.shape_selected);
            this.binding.ivSelected.setVisibility(0);
        } else {
            this.binding.ivStroke.setBackground(null);
            this.binding.ivSelected.setVisibility(8);
        }
    }

    @Override
    public boolean isSelected() {
        return this.binding.ivSelected.getVisibility() == 0;
    }
}
