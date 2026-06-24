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
import com.wugu.doublecamera.adapter.PictureSelectAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.PictureSelectItem;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentPicSelectBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.BurstSelectVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PictureSelectFragment extends ABaseFragment<FragmentPicSelectBinding, BurstSelectVModel> {
    private FrameInfo frameInfo;
    private MainVModel mainVModel;
    private final PictureSelectAdapter pictureAdapter = new PictureSelectAdapter();
    private final List<PictureSelectItem> burstItemList = new ArrayList();
    private final HashMap<Integer, String> mapBurstUrl = new HashMap<>();

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
        ((BurstSelectVModel) this.mViewModel).cancelCountdown();
        ((FragmentPicSelectBinding) this.mViewBinding).layoutSplicing.releaseAllView();
        super.onDestroyView();
    }

    private void initView() {
        ((FragmentPicSelectBinding) this.mViewBinding).rvPicture.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        ((FragmentPicSelectBinding) this.mViewBinding).rvPicture.setAdapter(this.pictureAdapter);
        ((FragmentPicSelectBinding) this.mViewBinding).rvPicture.setItemAnimator(null);
        try {
            String str = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_CONFIRM)))).resPath;
            String str2 = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(1014))).resPath;
            ViewUtil.setImageDrawable(((FragmentPicSelectBinding) this.mViewBinding).btnEnter, str);
            ViewUtil.setViewGroupBg(((FragmentPicSelectBinding) this.mViewBinding).rvPicture, str2);
        } catch (NullPointerException unused) {
        }
        if (App.mSystemMode == 2) {
            ViewUtil.setUiLocate(((FragmentPicSelectBinding) this.mViewBinding).getRoot(), 1013);
        } else {
            ViewUtil.setViewGroupBg(((FragmentPicSelectBinding) this.mViewBinding).getRoot(), AppConfig.BASE_FOLDER + "default_ui/6-管理背景.png");
        }
    }

    private void initData() {
        startCountdown(SpManager.getInstance().getBurstSelectTime());
    }

    private void initListener() {
        ((FragmentPicSelectBinding) this.mViewBinding).btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m983x221c270e(view);
            }
        });
        this.pictureAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m984x13c5cd2d(baseQuickAdapter, view, i);
            }
        });
    }

    void m983x221c270e(View view) {
        showLoadAnim(getString(C1910R.string.processing_photo));
        ((FragmentPicSelectBinding) this.mViewBinding).layoutSplicing.compoundImage(new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m982x307280ef(str);
            }
        });
    }

    void m982x307280ef(String str) {
        dismissLoadAnim();
        if (this.frameInfo == null) {
            return;
        }
        this.mainVModel.uploadPrintLD.postValue(str);
    }

    void m984x13c5cd2d(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        PictureSelectItem item = this.pictureAdapter.getItem(i);
        if (item == null) {
            return;
        }
        ((FragmentPicSelectBinding) this.mViewBinding).layoutSplicing.setPicture(item.getPhotoName(), false);
    }

    private void initObserver() {
        ((BurstSelectVModel) this.mViewModel).getCountdownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m986x26b44c09((Integer) obj);
            }
        });
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m987x185df228((String) obj);
            }
        });
        this.mainVModel.picSelectUrlLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m989xfbb13e66((List) obj);
            }
        });
    }

    void m986x26b44c09(Integer num) {
        ((FragmentPicSelectBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        if (num.intValue() == 0) {
            showLoadAnim(getString(C1910R.string.processing_photo));
            ((FragmentPicSelectBinding) this.mViewBinding).layoutSplicing.compoundImage(new IStringListener() {
                @Override
                public final void setValue(String str) {
                    this.f$0.m985x350aa5ea(str);
                }
            });
        }
    }

    void m985x350aa5ea(String str) {
        dismissLoadAnim();
        if (this.frameInfo == null) {
            return;
        }
        this.mainVModel.uploadPrintLD.postValue(str);
    }

    void m987x185df228(String str) {
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        this.frameInfo = orderFrameInfo;
        if (orderFrameInfo == null) {
            return;
        }
        ((FragmentPicSelectBinding) this.mViewBinding).layoutSplicing.setFrameInfo(this.frameInfo);
    }

    void m989xfbb13e66(List list) {
        this.burstItemList.clear();
        String str = requireActivity().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.burstItemList.add(new PictureSelectItem(str + ((String) it.next()), false));
        }
        this.pictureAdapter.submitList(this.burstItemList);
        ((BurstSelectVModel) this.mViewModel).uploadNegPhotos(list);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m988xa079847();
            }
        }, 500L);
    }

    void m988xa079847() {
        PictureSelectItem item;
        if (this.frameInfo == null) {
            return;
        }
        for (int i = 0; i < this.frameInfo.getPhotoInfoList().size() && i < this.pictureAdapter.getItemCount() && (item = this.pictureAdapter.getItem(i)) != null && !TextUtils.isEmpty(item.getPhotoName()); i++) {
            ((FragmentPicSelectBinding) this.mViewBinding).layoutSplicing.setPicture(item.getPhotoName(), true);
        }
    }

    private void startCountdown(int i) {
        ((BurstSelectVModel) this.mViewModel).startCountdown(i);
        ((FragmentPicSelectBinding) this.mViewBinding).layoutCountdown.setCount(i);
    }
}
