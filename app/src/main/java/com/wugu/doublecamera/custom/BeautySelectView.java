package com.wugu.doublecamera.custom;

import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.databinding.LayoutBeautyListBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.ViewUtil;

public class BeautySelectView extends ConstraintLayout {
    private AnimatorSet animatorSet;
    private LayoutBeautyListBinding binding;
    private ImageView currentView;
    private IIntListener listener;

    public BeautySelectView(Context context) {
        super(context);
        initView(context);
    }

    public BeautySelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public void setListener(IIntListener iIntListener) {
        this.listener = iIntListener;
    }

    public void testClick() {
        this.binding.ivBeautyTwo.performClick();
    }

    private void initView(Context context) {
        this.binding = LayoutBeautyListBinding.inflate(LayoutInflater.from(context), this, true);
        initListener();
        ViewUtil.setImageDrawableVisible(this.binding.ivBeautyTitle, UiPosIndexEnum.PHOTO_BEAUTY_TITLE);
        ViewUtil.setImageDrawableVisible(this.binding.ivBeautyOne, UiPosIndexEnum.PHOTO_BEAUTY_ONE);
        ViewUtil.setImageDrawableVisible(this.binding.ivBeautyTwo, UiPosIndexEnum.PHOTO_BEAUTY_TWO);
        ViewUtil.setImageDrawableVisible(this.binding.ivBeautyThree, UiPosIndexEnum.PHOTO_BEAUTY_THREE);
        if (AppConfig.isBelongHeadSys()) {
            ViewUtil.setImageDrawableVisible(this.binding.ivBeautyNone, UiPosIndexEnum.PHOTO_BEAUTY_NONE);
            this.binding.ivBeautyThree.post(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1576lambda$initView$0$comwugudoublecameracustomBeautySelectView();
                }
            });
        } else if (App.mSystemMode == 2) {
            ViewUtil.setUiLocate(this.binding.ivBeautyNone, 1004);
        }
        this.currentView = this.binding.ivBeautyNone;
    }

    void m1576lambda$initView$0$comwugudoublecameracustomBeautySelectView() {
        this.binding.ivBeautyOne.setImageAlpha(160);
        this.binding.ivBeautyTwo.setImageAlpha(160);
        this.binding.ivBeautyThree.setImageAlpha(160);
        ViewUtil.setImageViewGray(this.binding.ivBeautyOne, true, 0.1f);
        ViewUtil.setImageViewGray(this.binding.ivBeautyTwo, true, 0.1f);
        ViewUtil.setImageViewGray(this.binding.ivBeautyThree, true, 0.1f);
    }

    private void initListener() {
        this.binding.ivBeautyNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m837xb1cf7121(view);
            }
        });
        this.binding.ivBeautyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m838x27499762(view);
            }
        });
        this.binding.ivBeautyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m839x9cc3bda3(view);
            }
        });
        this.binding.ivBeautyThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m840x123de3e4(view);
            }
        });
    }

    void m837xb1cf7121(View view) {
        itemClick((ImageView) view, 0);
    }

    void m838x27499762(View view) {
        itemClick((ImageView) view, 1);
    }

    void m839x9cc3bda3(View view) {
        itemClick((ImageView) view, 2);
    }

    void m840x123de3e4(View view) {
        itemClick((ImageView) view, 3);
    }

    private void itemClick(ImageView imageView, int i) {
        if (AppConfig.isBelongHeadSys()) {
            setShape(imageView);
        } else if (App.mSystemMode == 2) {
            setViewSelected(imageView);
        }
        IIntListener iIntListener = this.listener;
        if (iIntListener != null) {
            iIntListener.setValue(i);
        }
    }

    private void setShape(ImageView imageView) {
        ImageView imageView2 = this.currentView;
        if (imageView2 != null) {
            imageView2.setImageAlpha(160);
            ViewUtil.setImageViewGray(this.currentView, true, 0.1f);
        }
        this.currentView = imageView;
        imageView.setImageAlpha(255);
        ViewUtil.setImageViewGray(this.currentView, false, 1.0f);
    }

    private void setViewSelected(ImageView imageView) {
        ImageView imageView2 = this.currentView;
        if (imageView2 == imageView) {
            return;
        }
        if (imageView2 == this.binding.ivBeautyNone) {
            ViewUtil.setImageDrawable(this.currentView, UiPosIndexEnum.PHOTO_BEAUTY_NONE);
        } else if (this.currentView == this.binding.ivBeautyOne) {
            ViewUtil.setImageDrawable(this.currentView, UiPosIndexEnum.PHOTO_BEAUTY_ONE);
        } else if (this.currentView == this.binding.ivBeautyTwo) {
            ViewUtil.setImageDrawable(this.currentView, UiPosIndexEnum.PHOTO_BEAUTY_TWO);
        } else if (this.currentView == this.binding.ivBeautyThree) {
            ViewUtil.setImageDrawable(this.currentView, UiPosIndexEnum.PHOTO_BEAUTY_THREE);
        }
        if (imageView == this.binding.ivBeautyNone) {
            ViewUtil.setImageDrawable(imageView, 1004);
        } else if (imageView == this.binding.ivBeautyOne) {
            ViewUtil.setImageDrawable(imageView, 1005);
        } else if (imageView == this.binding.ivBeautyTwo) {
            ViewUtil.setImageDrawable(imageView, 1006);
        } else if (imageView == this.binding.ivBeautyThree) {
            ViewUtil.setImageDrawable(imageView, 1007);
        }
        this.currentView = imageView;
    }

    public void startAnim() {
        if (this.animatorSet == null) {
            AnimatorSet breathAnim = AnimUtil.getBreathAnim(this.binding.ivBeautyTitle, 2000);
            this.animatorSet = breathAnim;
            breathAnim.start();
        }
    }

    public void stopAnim() {
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet == null) {
            return;
        }
        animatorSet.cancel();
        this.animatorSet = null;
    }

    public void setDefaultSelect(int i) {
        if (i == 0) {
            this.binding.ivBeautyNone.performClick();
            return;
        }
        if (i == 1) {
            this.binding.ivBeautyOne.performClick();
        } else if (i == 2) {
            this.binding.ivBeautyTwo.performClick();
        } else {
            if (i != 3) {
                return;
            }
            this.binding.ivBeautyThree.performClick();
        }
    }
}
