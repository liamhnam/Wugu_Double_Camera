package com.wugu.doublecamera.utils;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.bean.UiPosition;
import java.io.File;

public class ViewUtil {
    public static void setViewGroupBg(ViewGroup viewGroup, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null || TextUtils.isEmpty(uiPosition.resPath)) {
            return;
        }
        setViewGroupBg(viewGroup, uiPosition.resPath);
    }

    public static void setViewGroupBg(ViewGroup viewGroup, int i, int i2) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            viewGroup.setBackgroundResource(i2);
        } else if (!TextUtils.isEmpty(uiPosition.resPath)) {
            setViewGroupBg(viewGroup, uiPosition.resPath);
        } else {
            viewGroup.setBackgroundResource(i2);
        }
    }

    public static void setViewGroupBg(final ViewGroup viewGroup, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Glide.with(viewGroup.getContext()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL).into(new CustomTarget<Drawable>() {
            @Override
            public void onLoadCleared(Drawable drawable) {
            }

            @Override
            public void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
            }

            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                viewGroup.setBackground(drawable);
            }
        });
    }

    public static void setImageDrawable(final ImageView imageView, String str) {
        Glide.with(imageView.getContext()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL).into(new CustomTarget<Drawable>() {
            @Override
            public void onLoadCleared(Drawable drawable) {
            }

            @Override
            public void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
            }

            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                imageView.setImageDrawable(drawable);
            }
        });
    }

    public static void setImageBackground(ImageView imageView, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null || TextUtils.isEmpty(uiPosition.resPath)) {
            return;
        }
        setImageBackground(imageView, uiPosition.resPath);
    }

    public static void setImageBackground(final ImageView imageView, String str) {
        Glide.with(imageView.getContext()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL).into(new CustomTarget<Drawable>() {
            @Override
            public void onLoadCleared(Drawable drawable) {
            }

            @Override
            public void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
            }

            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                imageView.setBackground(drawable);
            }
        });
    }

    public static void setViewPosition(View view, int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) view.getLayoutParams();
            if (i >= 0) {
                layoutParams.x = i;
            }
            if (i2 >= 0) {
                layoutParams.y = i2;
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setViewPosition(ViewGroup viewGroup, int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) viewGroup.getLayoutParams();
            if (i >= 0) {
                layoutParams.x = i;
            }
            if (i2 >= 0) {
                layoutParams.y = i2;
            }
            viewGroup.setLayoutParams(layoutParams);
        }
    }

    public static void setViewPosition(ViewGroup viewGroup, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition != null && uiPosition.f2441x > 0 && uiPosition.f2442y > 0) {
            setViewPosition(viewGroup, uiPosition.f2441x, uiPosition.f2442y);
        }
    }

    public static void setImageViewGray(ImageView imageView, boolean z, float f) {
        if (!z) {
            imageView.setColorFilter((ColorFilter) null);
            return;
        }
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(f);
        imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public static void setImageDrawable(ImageView imageView, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null || TextUtils.isEmpty(uiPosition.resPath)) {
            return;
        }
        setImageDrawable(imageView, uiPosition.resPath);
    }

    public static boolean setImageDrawable(ImageView imageView, int i, int i2) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            imageView.setImageResource(i2);
            return true;
        }
        if (!uiPosition.isVisible) {
            imageView.setImageDrawable(null);
            imageView.setVisibility(8);
            return false;
        }
        if (!TextUtils.isEmpty(uiPosition.resPath)) {
            setImageDrawable(imageView, uiPosition.resPath);
        } else {
            imageView.setImageResource(i2);
        }
        return true;
    }

    public static void setImageDrawableVisible(AppCompatImageView appCompatImageView, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            return;
        }
        if (!uiPosition.isVisible) {
            appCompatImageView.setImageDrawable(null);
            appCompatImageView.setVisibility(8);
        } else {
            if (TextUtils.isEmpty(uiPosition.resPath)) {
                return;
            }
            setImageDrawable(appCompatImageView, uiPosition.resPath);
        }
    }

    public static void setUiLocate(TextView textView, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition != null && uiPosition.f2441x > 0 && uiPosition.f2442y > 0) {
            setViewPosition(textView, uiPosition.f2441x, uiPosition.f2442y);
        }
    }

    public static void setUiLocate(ImageView imageView, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            return;
        }
        if (!TextUtils.isEmpty(uiPosition.resPath) && new File(uiPosition.resPath).exists()) {
            Glide.with(imageView).load(uiPosition.resPath).into(imageView);
        }
        if (uiPosition.f2441x <= 0 || uiPosition.f2442y <= 0) {
            return;
        }
        setViewPosition(imageView, uiPosition.f2441x, uiPosition.f2442y);
    }

    public static void setUiCustomLocate(ImageView imageView, int i, int i2, int i3) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (i2 != 0 || i3 != 0) {
            setViewPosition(imageView, i2, i3);
        }
        if (uiPosition == null || TextUtils.isEmpty(uiPosition.resPath) || !new File(uiPosition.resPath).exists()) {
            return;
        }
        Glide.with(imageView).load(uiPosition.resPath).into(imageView);
    }

    public static void setUiLocate(ImageView imageView, int i, int i2) {
        boolean z;
        UiPosition uiPosition;
        UiPosition uiPosition2 = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition2 == null) {
            return;
        }
        if (TextUtils.isEmpty(uiPosition2.resPath) || !new File(uiPosition2.resPath).exists()) {
            z = true;
        } else {
            Glide.with(imageView).load(uiPosition2.resPath).into(imageView);
            z = false;
        }
        if (z && (uiPosition = App.mUiPosMap.get(Integer.valueOf(i2))) != null && !TextUtils.isEmpty(uiPosition.resPath) && new File(uiPosition.resPath).exists()) {
            Glide.with(imageView).load(uiPosition.resPath).into(imageView);
        }
        if (uiPosition2.f2441x <= 0 || uiPosition2.f2442y <= 0) {
            return;
        }
        setViewPosition(imageView, uiPosition2.f2441x, uiPosition2.f2442y);
    }

    public static void setUiLocate(ViewGroup viewGroup, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            return;
        }
        if (uiPosition.f2441x > 0 && uiPosition.f2442y > 0 && (viewGroup.getParent() instanceof AbsoluteLayout)) {
            AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) viewGroup.getLayoutParams();
            layoutParams.x = uiPosition.f2441x;
            layoutParams.y = uiPosition.f2442y;
            viewGroup.setLayoutParams(layoutParams);
        }
        if (TextUtils.isEmpty(uiPosition.resPath)) {
            return;
        }
        setViewGroupBg(viewGroup, uiPosition.resPath);
    }

    public static void setUiLocate(ViewGroup viewGroup, int i, int i2) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            UiPosition uiPosition2 = App.mUiPosMap.get(Integer.valueOf(i2));
            if (uiPosition2 == null || TextUtils.isEmpty(uiPosition2.resPath)) {
                return;
            }
            setViewGroupBg(viewGroup, uiPosition2.resPath);
            return;
        }
        if (uiPosition.f2441x > 0 && uiPosition.f2442y > 0 && (viewGroup.getParent() instanceof AbsoluteLayout)) {
            AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) viewGroup.getLayoutParams();
            layoutParams.x = uiPosition.f2441x;
            layoutParams.y = uiPosition.f2442y;
            viewGroup.setLayoutParams(layoutParams);
        }
        if (!TextUtils.isEmpty(uiPosition.resPath) && new File(uiPosition.resPath).exists()) {
            setViewGroupBg(viewGroup, uiPosition.resPath);
            return;
        }
        UiPosition uiPosition3 = App.mUiPosMap.get(Integer.valueOf(i2));
        if (uiPosition3 == null || TextUtils.isEmpty(uiPosition3.resPath)) {
            return;
        }
        setViewGroupBg(viewGroup, uiPosition3.resPath);
    }

    public static void setUI(AppCompatImageView appCompatImageView, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            return;
        }
        if (!uiPosition.isVisible) {
            appCompatImageView.setImageDrawable(null);
            appCompatImageView.setVisibility(8);
            return;
        }
        if (uiPosition.f2441x > 0 && uiPosition.f2442y > 0 && (appCompatImageView.getParent() instanceof AbsoluteLayout)) {
            setViewPosition(appCompatImageView, uiPosition.f2441x, uiPosition.f2442y);
        }
        if (TextUtils.isEmpty(uiPosition.resPath)) {
            return;
        }
        setImageDrawable(appCompatImageView, uiPosition.resPath);
    }

    public static boolean setUI(AppCompatImageView appCompatImageView, int i, int i2) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            appCompatImageView.setImageResource(i2);
            return true;
        }
        if (!uiPosition.isVisible) {
            appCompatImageView.setImageDrawable(null);
            appCompatImageView.setVisibility(8);
            return false;
        }
        if (uiPosition.f2441x > 0 && uiPosition.f2442y > 0 && (appCompatImageView.getParent() instanceof AbsoluteLayout)) {
            setViewPosition(appCompatImageView, uiPosition.f2441x, uiPosition.f2442y);
        }
        if (!TextUtils.isEmpty(uiPosition.resPath)) {
            setImageDrawable(appCompatImageView, uiPosition.resPath);
        } else {
            appCompatImageView.setImageResource(i2);
        }
        return true;
    }

    public static boolean setUI(AppCompatImageView appCompatImageView, int i, int i2, boolean z) {
        if (App.mUiPosMap.get(Integer.valueOf(i)) == null) {
            appCompatImageView.setImageResource(i2);
            return z;
        }
        return setUI(appCompatImageView, i, i2);
    }
}
