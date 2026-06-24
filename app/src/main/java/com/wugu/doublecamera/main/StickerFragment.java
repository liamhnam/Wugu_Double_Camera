package com.wugu.doublecamera.main;

import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.adapter.StickerAdapter;
import com.wugu.doublecamera.adapter.StickerTabAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.StickerTab;
import com.wugu.doublecamera.databinding.FragmentStickerBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.StickerVModel;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.SoundHelper;
import java.util.List;

public class StickerFragment extends ABaseFragment<FragmentStickerBinding, StickerVModel> {
    private int lastTabIndex = 0;
    private MainVModel mainVModel;
    private StickerAdapter stickerItemAdapter;
    private StickerTabAdapter stickerTabAdapter;

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
        this.stickerItemAdapter = new StickerAdapter();
        this.stickerTabAdapter = new StickerTabAdapter();
        ((FragmentStickerBinding) this.mViewBinding).rvItem.setLayoutManager(new GridLayoutManager(requireContext(), 5));
        ((FragmentStickerBinding) this.mViewBinding).rvItem.setAdapter(this.stickerItemAdapter);
        ((FragmentStickerBinding) this.mViewBinding).rvTab.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
        ((FragmentStickerBinding) this.mViewBinding).rvTab.setAdapter(this.stickerTabAdapter);
        ((FragmentStickerBinding) this.mViewBinding).rvTab.setItemAnimator(null);
        ViewUtil.setUI(((FragmentStickerBinding) this.mViewBinding).ivBg, UiPosIndexEnum.PRINT_STICKER_BG);
        if (App.mSystemMode == 2) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentStickerBinding) this.mViewBinding).rvTab.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) ((FragmentStickerBinding) this.mViewBinding).rvItem.getLayoutParams();
            layoutParams.topMargin = 100;
            layoutParams2.topMargin = 10;
            ((FragmentStickerBinding) this.mViewBinding).rvTab.setLayoutParams(layoutParams);
            ((FragmentStickerBinding) this.mViewBinding).rvItem.setLayoutParams(layoutParams2);
        }
    }

    private void initListener() {
        this.stickerTabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1697lambda$initListener$0$comwugudoublecameramainStickerFragment(baseQuickAdapter, view, i);
            }
        });
        this.stickerItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1698lambda$initListener$1$comwugudoublecameramainStickerFragment(baseQuickAdapter, view, i);
            }
        });
    }

    void m1697lambda$initListener$0$comwugudoublecameramainStickerFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (this.lastTabIndex == i) {
            return;
        }
        this.stickerTabAdapter.setSelectedIndex(i);
        this.stickerTabAdapter.notifyItemChanged(this.lastTabIndex);
        this.stickerTabAdapter.notifyItemChanged(i);
        this.lastTabIndex = i;
        StickerTab item = this.stickerTabAdapter.getItem(i);
        if (item != null) {
            ((StickerVModel) this.mViewModel).queryStickerList(item.folder);
        }
    }

    void m1698lambda$initListener$1$comwugudoublecameramainStickerFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SoundHelper.getInstance().playSoundEffect(6);
        this.mainVModel.stickerAddLD.postValue(this.stickerItemAdapter.getItem(i));
    }

    private void initObserver() {
        ((StickerVModel) this.mViewModel).getStickerFolderLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1699lambda$initObserver$2$comwugudoublecameramainStickerFragment((List) obj);
            }
        });
        ((StickerVModel) this.mViewModel).getStickerListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1700lambda$initObserver$3$comwugudoublecameramainStickerFragment((List) obj);
            }
        });
    }

    void m1699lambda$initObserver$2$comwugudoublecameramainStickerFragment(List list) {
        this.stickerTabAdapter.submitList(list);
        if (list == null || list.size() <= 0) {
            return;
        }
        ((StickerVModel) this.mViewModel).queryStickerList(((StickerTab) list.get(0)).folder);
    }

    void m1700lambda$initObserver$3$comwugudoublecameramainStickerFragment(List list) {
        this.stickerItemAdapter.submitList(list);
    }

    private void initData() {
        ((StickerVModel) this.mViewModel).queryStickerFolders();
    }
}
