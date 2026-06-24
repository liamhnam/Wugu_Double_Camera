package com.wugu.doublecamera.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.ReplaceBgAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentReplaceBgBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.ReplaceBgVModel;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ToastHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ReplaceBgFragment extends ABaseFragment<FragmentReplaceBgBinding, ReplaceBgVModel> {
    private FrameInfo frameInfo;
    private MainVModel mainVModel;
    private final List<String> photoNameList = new ArrayList();
    private final List<String> cutNameList = new ArrayList();
    private final ReplaceBgAdapter replaceBgAdapter = new ReplaceBgAdapter();

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initData();
        initListener();
        initObserver();
    }

    @Override
    public void onDestroyView() {
        this.mainVModel = null;
        ((ReplaceBgVModel) this.mViewModel).cancelCountdown();
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.releaseAllView();
        super.onDestroyView();
    }

    private void initView() {
        ((FragmentReplaceBgBinding) this.mViewBinding).rvBg.setLayoutManager(new GridLayoutManager(requireContext(), 2, 0, false));
        ((FragmentReplaceBgBinding) this.mViewBinding).rvBg.setAdapter(this.replaceBgAdapter);
        ((FragmentReplaceBgBinding) this.mViewBinding).rvBg.setItemAnimator(null);
        try {
            ViewUtil.setImageDrawable(((FragmentReplaceBgBinding) this.mViewBinding).btnEnter, ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_CONFIRM)))).resPath);
        } catch (NullPointerException unused) {
        }
        ViewUtil.setViewGroupBg(((FragmentReplaceBgBinding) this.mViewBinding).getRoot(), AppConfig.BASE_FOLDER + "default_ui/6-管理背景.png");
        ((FragmentReplaceBgBinding) this.mViewBinding).btnEnter.setEnabled(false);
    }

    private void initData() {
        ((ReplaceBgVModel) this.mViewModel).queryBgList();
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setShowBg(true);
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setShowSelectStroke(false);
    }

    private void initListener() {
        ((FragmentReplaceBgBinding) this.mViewBinding).btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1017x998ff08d(view);
            }
        });
        this.replaceBgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1018x53077e2c(baseQuickAdapter, view, i);
            }
        });
    }

    void m1017x998ff08d(View view) {
        ((ReplaceBgVModel) this.mViewModel).cancelCountdown();
        showLoadAnim(getString(C1910R.string.processing_photo));
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.compoundImage(new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m1016xe01862ee(str);
            }
        });
    }

    void m1016xe01862ee(String str) {
        this.mainVModel.uploadPrintLD.postValue(str);
        dismissLoadAnim();
    }

    void m1018x53077e2c(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String item = this.replaceBgAdapter.getItem(i);
        if (TextUtils.isEmpty(item) || this.replaceBgAdapter.getSelectedPosition() == i) {
            ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.clearPictureBg();
            ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setCurrentSelectIndex(0);
            Iterator<String> it = this.photoNameList.iterator();
            while (it.hasNext()) {
                ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setPicture(it.next(), true);
            }
            int selectedPosition = this.replaceBgAdapter.getSelectedPosition();
            this.replaceBgAdapter.setSelectedPosition(-1);
            this.replaceBgAdapter.notifyItemChanged(selectedPosition);
            return;
        }
        if (((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.getPhotoBgPath() == null) {
            ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setCurrentSelectIndex(0);
            Iterator<String> it2 = this.cutNameList.iterator();
            while (it2.hasNext()) {
                ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setPicture(it2.next(), true);
            }
        }
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setPictureBg(item);
        ReplaceBgAdapter replaceBgAdapter = this.replaceBgAdapter;
        replaceBgAdapter.notifyItemChanged(replaceBgAdapter.getSelectedPosition());
        this.replaceBgAdapter.setSelectedPosition(i);
        this.replaceBgAdapter.notifyItemChanged(i);
    }

    private void initObserver() {
        ((ReplaceBgVModel) this.mViewModel).getCountdownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1019x1d683d69((Integer) obj);
            }
        });
        ((ReplaceBgVModel) this.mViewModel).getBgPathListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1020xd6dfcb08((List) obj);
            }
        });
        ((ReplaceBgVModel) this.mViewModel).getCutoutNmLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1021x905758a7((List) obj);
            }
        });
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1024xbcbe0184();
            }
        }, 500L);
    }

    void m1019x1d683d69(Integer num) {
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        if (num.intValue() == 0) {
            ((FragmentReplaceBgBinding) this.mViewBinding).btnEnter.performClick();
        }
    }

    void m1020xd6dfcb08(List list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.replaceBgAdapter.submitList(list);
    }

    void m1021x905758a7(List list) {
        String absolutePath = requireContext().getFilesDir().getAbsolutePath();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!str.contains("_cutout")) {
                return;
            } else {
                this.cutNameList.add(absolutePath + MqttTopic.TOPIC_LEVEL_SEPARATOR + str);
            }
        }
        dismissLoadAnim();
        ToastHelper.getInstance().showToast(getString(C1910R.string.pls_select_frame_bg));
        ((FragmentReplaceBgBinding) this.mViewBinding).btnEnter.setEnabled(true);
        startCountdown(SpManager.getInstance().getReplaceBgTime());
    }

    void m1024xbcbe0184() {
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1022x49cee646((String) obj);
            }
        });
        this.mainVModel.photoUrlLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1023x34673e5((List) obj);
            }
        });
    }

    void m1022x49cee646(String str) {
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        this.frameInfo = orderFrameInfo;
        if (orderFrameInfo == null) {
            return;
        }
        ((ReplaceBgVModel) this.mViewModel).setFrameInfo(this.frameInfo);
        setImgToFrame();
    }

    void m1023x34673e5(List list) {
        if (list.isEmpty()) {
            return;
        }
        showLoadAnim(getString(C1910R.string.processing_photo));
        String absolutePath = requireContext().getFilesDir().getAbsolutePath();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.photoNameList.add(absolutePath + MqttTopic.TOPIC_LEVEL_SEPARATOR + ((String) it.next()));
        }
        ((ReplaceBgVModel) this.mViewModel).uploadAndCutNegPhotos(requireContext().getFilesDir().getAbsolutePath(), list);
        setImgToFrame();
    }

    private void setImgToFrame() {
        FrameInfo frameInfo = this.frameInfo;
        if (frameInfo == null || frameInfo.getPhotoInfoList() == null || this.photoNameList.isEmpty()) {
            return;
        }
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setFrameInfo(this.frameInfo);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1025xa4352272();
            }
        }, 300L);
    }

    void m1025xa4352272() {
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setCurrentSelectIndex(0);
        Iterator<String> it = this.photoNameList.iterator();
        while (it.hasNext()) {
            ((FragmentReplaceBgBinding) this.mViewBinding).layoutSplicing.setPicture(it.next(), true);
        }
    }

    private void startCountdown(int i) {
        ((ReplaceBgVModel) this.mViewModel).startCountdown(i);
        ((FragmentReplaceBgBinding) this.mViewBinding).layoutCountdown.setCount(i);
    }
}
