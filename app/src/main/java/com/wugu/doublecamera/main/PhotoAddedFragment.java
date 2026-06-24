package com.wugu.doublecamera.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.adapter.PhotoAddedAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.PhotoAddedItem;
import com.wugu.doublecamera.databinding.FragmentEffectBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.main.p025vm.EffectVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.SoundHelper;
import java.util.List;

public class PhotoAddedFragment extends ABaseFragment<FragmentEffectBinding, EffectVModel> {
    private int currentIndex;
    private int effectIndex;
    private int filterIndex;
    private MainVModel mainVModel;
    private int makeupIndex;
    private AppCompatImageView[] modelImageViews;
    private PhotoAddedAdapter photoAddedAdapter;
    private boolean stopModelAnim = true;
    private long lastClickTime = 0;

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initListener();
        initObserver();
        initData();
    }

    @Override
    public void onDestroyView() {
        this.mainVModel = null;
        super.onDestroyView();
    }

    private void initView() {
        this.photoAddedAdapter = new PhotoAddedAdapter();
        ((FragmentEffectBinding) this.mViewBinding).recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        ((FragmentEffectBinding) this.mViewBinding).recyclerView.setAdapter(this.photoAddedAdapter);
        ((FragmentEffectBinding) this.mViewBinding).recyclerView.setItemAnimator(null);
        ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivBg, UiPosIndexEnum.PHOTO_EFFECT_FILTER_BG);
        ViewUtil.setImageDrawableVisible(((FragmentEffectBinding) this.mViewBinding).ivFilter, UiPosIndexEnum.PHOTO_BTN_FILTER);
        ViewUtil.setImageDrawableVisible(((FragmentEffectBinding) this.mViewBinding).ivMakeup, UiPosIndexEnum.PHOTO_BTN_MAKEUP);
        ViewUtil.setImageDrawableVisible(((FragmentEffectBinding) this.mViewBinding).ivEffect, UiPosIndexEnum.PHOTO_BTN_EFFECT);
        ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).recyclerView, UiPosIndexEnum.PHOTO_FILTER_LIST);
        if (AppConfig.isBelongHeadSys()) {
            ((FragmentEffectBinding) this.mViewBinding).ivMakeup.setImageAlpha(120);
            ((FragmentEffectBinding) this.mViewBinding).ivEffect.setImageAlpha(120);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivMakeup, true, 0.1f);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivEffect, true, 0.1f);
        } else {
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivFilter, 1008);
        }
        if (App.mChooseFragmentType == 211) {
            ((FragmentEffectBinding) this.mViewBinding).ivEffect.setVisibility(8);
        }
    }

    private void initListener() {
        ((FragmentEffectBinding) this.mViewBinding).ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m972xf9f9bf99(view);
            }
        });
        ((FragmentEffectBinding) this.mViewBinding).ivMakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m973x6f73e5da(view);
            }
        });
        ((FragmentEffectBinding) this.mViewBinding).ivEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m974xe4ee0c1b(view);
            }
        });
        this.photoAddedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m975x5a68325c(baseQuickAdapter, view, i);
            }
        });
    }

    void m972xf9f9bf99(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (AppConfig.isBelongHeadSys()) {
            ((FragmentEffectBinding) this.mViewBinding).ivFilter.setImageAlpha(255);
            ((FragmentEffectBinding) this.mViewBinding).ivMakeup.setImageAlpha(120);
            ((FragmentEffectBinding) this.mViewBinding).ivEffect.setImageAlpha(120);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivFilter, false, 1.0f);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivMakeup, true, 0.1f);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivEffect, true, 0.1f);
        } else {
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivFilter, 1008);
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivMakeup, UiPosIndexEnum.PHOTO_BTN_MAKEUP);
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivEffect, UiPosIndexEnum.PHOTO_BTN_EFFECT);
        }
        ((EffectVModel) this.mViewModel).queryFilterList();
    }

    void m973x6f73e5da(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (AppConfig.isBelongHeadSys()) {
            ((FragmentEffectBinding) this.mViewBinding).ivFilter.setImageAlpha(120);
            ((FragmentEffectBinding) this.mViewBinding).ivMakeup.setImageAlpha(255);
            ((FragmentEffectBinding) this.mViewBinding).ivEffect.setImageAlpha(120);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivMakeup, false, 1.0f);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivFilter, true, 0.1f);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivEffect, true, 0.1f);
        } else {
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivFilter, UiPosIndexEnum.PHOTO_BTN_FILTER);
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivMakeup, 1009);
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivEffect, UiPosIndexEnum.PHOTO_BTN_EFFECT);
        }
        ((EffectVModel) this.mViewModel).queryMakeupList();
    }

    void m974xe4ee0c1b(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (AppConfig.isBelongHeadSys()) {
            ((FragmentEffectBinding) this.mViewBinding).ivFilter.setImageAlpha(120);
            ((FragmentEffectBinding) this.mViewBinding).ivMakeup.setImageAlpha(120);
            ((FragmentEffectBinding) this.mViewBinding).ivEffect.setImageAlpha(255);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivEffect, false, 1.0f);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivMakeup, true, 0.1f);
            ViewUtil.setImageViewGray(((FragmentEffectBinding) this.mViewBinding).ivFilter, true, 0.1f);
        } else {
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivFilter, UiPosIndexEnum.PHOTO_BTN_FILTER);
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivMakeup, UiPosIndexEnum.PHOTO_BTN_MAKEUP);
            ViewUtil.setUiLocate(((FragmentEffectBinding) this.mViewBinding).ivEffect, 1010);
        }
        ((EffectVModel) this.mViewModel).queryEffectList();
    }

    void m975x5a68325c(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SoundHelper.getInstance().playSoundEffect(6);
        PhotoAddedItem item = this.photoAddedAdapter.getItem(i);
        if (item == null) {
            return;
        }
        if (item.getPhotoAddedType() == 1) {
            if (!enableChangeEffect(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION)) {
                return;
            } else {
                this.filterIndex = i;
            }
        } else if (item.getPhotoAddedType() == 2) {
            if (!enableChangeEffect(600)) {
                return;
            } else {
                this.makeupIndex = i;
            }
        } else if (!enableChangeEffect(600)) {
            return;
        } else {
            this.effectIndex = i;
        }
        notifyItemChange(i);
        this.mainVModel.photoAddedLD.postValue(item);
    }

    private boolean enableChangeEffect(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastClickTime < i) {
            return false;
        }
        this.lastClickTime = jCurrentTimeMillis;
        return true;
    }

    private void initObserver() {
        ((EffectVModel) this.mViewModel).getPhotoAddedListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m976xdc1f5abf((List) obj);
            }
        });
    }

    void m976xdc1f5abf(List list) {
        int i;
        if (list.isEmpty()) {
            return;
        }
        int photoAddedType = ((PhotoAddedItem) list.get(0)).getPhotoAddedType();
        this.photoAddedAdapter.submitList(list);
        if (photoAddedType == 1) {
            i = this.filterIndex;
        } else if (photoAddedType == 2) {
            i = this.makeupIndex;
        } else {
            i = this.effectIndex;
        }
        notifyItemChange(i);
    }

    private void initData() {
        ((EffectVModel) this.mViewModel).queryFilterList();
    }

    private void notifyItemChange(int i) {
        this.photoAddedAdapter.setSelectedPosition(i);
        if (this.currentIndex < this.photoAddedAdapter.getItemCount()) {
            this.photoAddedAdapter.notifyItemChanged(this.currentIndex);
        }
        this.currentIndex = i;
        if (i < this.photoAddedAdapter.getItemCount()) {
            this.photoAddedAdapter.notifyItemChanged(this.currentIndex);
        }
    }

    public void startAnim() {
        if (this.modelImageViews == null) {
            this.modelImageViews = new AppCompatImageView[]{((FragmentEffectBinding) this.mViewBinding).ivFilter, ((FragmentEffectBinding) this.mViewBinding).ivMakeup, ((FragmentEffectBinding) this.mViewBinding).ivEffect};
        }
        if (this.stopModelAnim) {
            this.stopModelAnim = false;
            ((FragmentEffectBinding) this.mViewBinding).ivEffect.post(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1673lambda$startAnim$5$comwugudoublecameramainPhotoAddedFragment();
                }
            });
        }
    }

    void m1673lambda$startAnim$5$comwugudoublecameramainPhotoAddedFragment() {
        playScaleAnimation(this.modelImageViews[0], 0);
    }

    public void stopAnim() {
        this.stopModelAnim = true;
    }

    public void playScaleAnimation(AppCompatImageView appCompatImageView, int i) {
        if (this.stopModelAnim) {
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(appCompatImageView, "scaleX", 1.0f, 1.05f, 1.0f);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(appCompatImageView, "scaleY", 1.0f, 1.05f, 1.0f);
        objectAnimatorOfFloat2.setDuration(200L);
        objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.addListener(new C19571(i));
        animatorSet.start();
    }

    class C19571 extends AnimatorListenerAdapter {
        final int val$index;

        C19571(int i) {
            this.val$index = i;
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            if (this.val$index < PhotoAddedFragment.this.modelImageViews.length - 1) {
                Handler handler = new Handler();
                final int i = this.val$index;
                handler.postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m977xfb8ec278(i);
                    }
                }, 10L);
                return;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m978xfb185c79();
                }
            }, 1000L);
        }

        void m977xfb8ec278(int i) {
            PhotoAddedFragment photoAddedFragment = PhotoAddedFragment.this;
            int i2 = i + 1;
            photoAddedFragment.playScaleAnimation(photoAddedFragment.modelImageViews[i2], i2);
        }

        void m978xfb185c79() {
            PhotoAddedFragment photoAddedFragment = PhotoAddedFragment.this;
            photoAddedFragment.playScaleAnimation(photoAddedFragment.modelImageViews[0], 0);
        }
    }
}
