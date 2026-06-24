package com.wugu.doublecamera.main;

import android.R;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.p020hp.jipp.model.TimeoutPredicate;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.PhotoAdapter;
import com.wugu.doublecamera.adapter.RetakeAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.PhotoAddedItem;
import com.wugu.doublecamera.bean.PhotoItem;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.EffectEntity;
import com.wugu.doublecamera.databinding.FragmentPhotoBinding;
import com.wugu.doublecamera.enums.FrameRemarkEnum;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.ICaptureListener;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.PhotoVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FragmentUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import com.wugu.facebeauty.bean.BeautifyItem;
import com.wugu.facebeauty.bean.FilterParams;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.p040ws.RealWebSocket;
import org.apache.log4j.spi.Configurator;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PhotoFragment extends ABaseFragment<FragmentPhotoBinding, PhotoVModel> implements ICaptureListener {
    private int currentFrameH;
    private int currentFrameW;
    private int defaultBeautyLevel;
    private FilterParams filterParams;
    private FrameInfo frameInfo;
    private MainVModel mainVModel;
    private Bitmap originalFrameBitmap;
    private PhotoAddedFragment photoAddedFragment;
    private int photoStep;
    private int remainPictureNum;
    private int retakeCount;
    private List<Bitmap> retakeList;
    private int retakePictureSelectedIndex;
    private final PhotoAdapter photoAdapter = new PhotoAdapter();
    private final RetakeAdapter retakeAdapter = new RetakeAdapter();

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        LoggerUtil.m1181d("PhotoFragment", "PhotoFragment Create");
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initData();
        initView();
        initObserver();
        initListener();
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                PhotoFragment.lambda$onViewCreated$0();
            }
        });
        ((FragmentPhotoBinding) this.mViewBinding).layoutCountdown.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1690lambda$onViewCreated$1$comwugudoublecameramainPhotoFragment();
            }
        });
    }

    static void lambda$onViewCreated$0() {
        ThreadClock.sleep(2000L);
        SoundHelper.getInstance().playSoundEffect(13);
    }

    void m1690lambda$onViewCreated$1$comwugudoublecameramainPhotoFragment() {
        gotoNextPhotoStep(1);
    }

    @Override
    public void onPause() {
        super.onPause();
        LoggerUtil.m1181d("PhotoFragment", "PhotoFragment onPause");
        PhotoAddedFragment photoAddedFragment = this.photoAddedFragment;
        if (photoAddedFragment != null) {
            photoAddedFragment.stopAnim();
            FragmentUtil.removeFragment(requireActivity(), this.photoAddedFragment);
            this.photoAddedFragment = null;
        }
        ((FragmentPhotoBinding) this.mViewBinding).layoutBeauty.stopAnim();
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.closeCamera();
        ((PhotoVModel) this.mViewModel).cancelCountdown();
        this.mainVModel = null;
        clearTempRetakeList();
        Bitmap bitmap = this.originalFrameBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.originalFrameBitmap.recycle();
            this.originalFrameBitmap = null;
        }
        this.frameInfo = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LoggerUtil.m1181d("PhotoFragment", "PhotoFragment onDestroy");
    }

    private void initView() {
        ((FragmentPhotoBinding) this.mViewBinding).rvPhoto.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentPhotoBinding) this.mViewBinding).rvPhoto.setAdapter(this.photoAdapter);
        ((FragmentPhotoBinding) this.mViewBinding).rvPhoto.setItemAnimator(null);
        ((FragmentPhotoBinding) this.mViewBinding).rvRetake.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
        ((FragmentPhotoBinding) this.mViewBinding).rvRetake.setAdapter(this.retakeAdapter);
        ((FragmentPhotoBinding) this.mViewBinding).rvRetake.setItemAnimator(null);
        this.retakePictureSelectedIndex = 0;
        this.retakeCount = SpManager.getInstance().getRetakeCount();
        this.photoAddedFragment = new PhotoAddedFragment();
        FragmentUtil.showFragment(((FragmentPhotoBinding) this.mViewBinding).fragmentPhotoAdded.getId(), requireActivity(), this.photoAddedFragment, false);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).layoutBeauty, UiPosIndexEnum.PHOTO_BEAUTY_POS);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).fragmentPhotoAdded, UiPosIndexEnum.PHOTO_EFFECT_FILTER_POS);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).rvPhoto, UiPosIndexEnum.PHOTO_PICTURE_LIST);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).rvRetake, UiPosIndexEnum.PHOTO_RETAKE_LIST);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).layoutCountdown, UiPosIndexEnum.PHOTO_COUNTDOWN);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).tvRetakeCount, UiPosIndexEnum.PHOTO_TV_RETAKE_COUNT);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).btnConfirm, UiPosIndexEnum.PHOTO_BTN_CONFIRM);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).btnRetake, UiPosIndexEnum.PHOTO_BTN_RETAKE);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto, UiPosIndexEnum.PHOTO_BTN_PHOTO);
        ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PHOTO_PREVIEW_BG);
        if (App.mSystemMode == 2) {
            ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).layoutCamera, 1011);
        } else if (AppConfig.isBelongHeadSys()) {
            ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).layoutCamera, UiPosIndexEnum.PHOTO_PREVIEW_POS);
        }
        ((FragmentPhotoBinding) this.mViewBinding).layoutBeauty.postDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1689lambda$initView$2$comwugudoublecameramainPhotoFragment();
            }
        }, 500L);
    }

    void m1689lambda$initView$2$comwugudoublecameramainPhotoFragment() {
        ((FragmentPhotoBinding) this.mViewBinding).layoutBeauty.setDefaultSelect(this.defaultBeautyLevel);
    }

    private void initData() {
        App.mIsIdle = false;
        this.defaultBeautyLevel = SpManager.getInstance().getDefaultBeautyLevel(1);
        LoggerUtil.m1181d("photoFragment", "getDefaultBeauty " + this.defaultBeautyLevel);
        OrderManager.mRedeemCodeDto = null;
        ActivityManager activityManager = (ActivityManager) requireContext().getSystemService(TimeoutPredicate.activity);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        LoggerUtil.m1181d("photoFragment", "hhy availableMemory " + ((memoryInfo.availMem / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE));
    }

    private void initObserver() {
        ((PhotoVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1682lambda$initObserver$3$comwugudoublecameramainPhotoFragment((Integer) obj);
            }
        });
        ((PhotoVModel) this.mViewModel).getSaveImageFinishLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1685lambda$initObserver$6$comwugudoublecameramainPhotoFragment((Integer) obj);
            }
        });
        ((PhotoVModel) this.mViewModel).getBeautyItemLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1686lambda$initObserver$7$comwugudoublecameramainPhotoFragment((BeautifyItem) obj);
            }
        });
        ((PhotoVModel) this.mViewModel).getAiCreateIdLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1688lambda$initObserver$9$comwugudoublecameramainPhotoFragment((String) obj);
            }
        });
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1678lambda$initObserver$10$comwugudoublecameramainPhotoFragment((String) obj);
            }
        });
        this.mainVModel.digitalCameraResetLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1681lambda$initObserver$13$comwugudoublecameramainPhotoFragment((Integer) obj);
            }
        });
        this.mainVModel.photoAddedLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.addPhotoAddedItem((PhotoAddedItem) obj);
            }
        });
    }

    void m1682lambda$initObserver$3$comwugudoublecameramainPhotoFragment(Integer num) {
        ((FragmentPhotoBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        int countAndStep = ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setCountAndStep(num.intValue(), this.photoStep);
        if (countAndStep == 0) {
            gotoNextPhotoStep(2);
            return;
        }
        if (countAndStep == 1) {
            LoggerUtil.m1181d("photoFragment", "countdown confirm");
            confirm();
        } else if (countAndStep == 2) {
            showLoadAnim(getString(C1910R.string.processing_photo));
        }
    }

    void m1685lambda$initObserver$6$comwugudoublecameramainPhotoFragment(Integer num) {
        this.retakeAdapter.submitList(null);
        clearTempRetakeList();
        LoggerUtil.m1181d("PhotoFragment", "getSaveImageFinishLD remainPictureNum " + this.remainPictureNum);
        if (this.remainPictureNum <= 0) {
            if (App.mSystemMode == 2) {
                this.mainVModel.picSelectUrlLD.postValue(((PhotoVModel) this.mViewModel).getImageList());
                AppUtil.runOnUIDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m1683lambda$initObserver$4$comwugudoublecameramainPhotoFragment();
                    }
                }, 120L);
                return;
            } else {
                this.mainVModel.photoUrlLD.postValue(((PhotoVModel) this.mViewModel).getImageList());
                AppUtil.runOnUIDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m1684lambda$initObserver$5$comwugudoublecameramainPhotoFragment();
                    }
                }, 120L);
                return;
            }
        }
        this.photoAdapter.notifyItemChanged(num.intValue());
        ((FragmentPhotoBinding) this.mViewBinding).rvPhoto.smoothScrollToPosition(this.photoAdapter.getItemCount() - this.remainPictureNum);
    }

    void m1683lambda$initObserver$4$comwugudoublecameramainPhotoFragment() {
        this.mainVModel.fragmentLD.postValue(4);
    }

    void m1684lambda$initObserver$5$comwugudoublecameramainPhotoFragment() {
        ((FragmentPhotoBinding) this.mViewBinding).btnConfirm.setEnabled(true);
        if (App.mChooseFragmentType == 213) {
            this.mainVModel.fragmentLD.postValue(12);
        } else {
            this.mainVModel.fragmentLD.postValue(5);
        }
    }

    void m1686lambda$initObserver$7$comwugudoublecameramainPhotoFragment(BeautifyItem beautifyItem) {
        this.filterParams = beautifyItem.filterParams;
        if (beautifyItem.filterParams.filterType == 3) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.filter_anime_tips));
        }
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setBeautyItem(beautifyItem);
    }

    void m1688lambda$initObserver$9$comwugudoublecameramainPhotoFragment(String str) {
        dismissLoadAnim();
        if (TextUtils.isEmpty(str)) {
            this.retakeCount++;
            new AlertDialog.Builder(requireContext()).setTitle(C1910R.string.checking_ai_fail).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.m1687lambda$initObserver$8$comwugudoublecameramainPhotoFragment(dialogInterface, i);
                }
            }).create().show();
        } else {
            this.mainVModel.fragmentLD.postValue(11);
            ((PhotoVModel) this.mViewModel).cancelCountdown();
        }
    }

    void m1687lambda$initObserver$8$comwugudoublecameramainPhotoFragment(DialogInterface dialogInterface, int i) {
        retake();
    }

    void m1678lambda$initObserver$10$comwugudoublecameramainPhotoFragment(String str) {
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        this.frameInfo = orderFrameInfo;
        if (orderFrameInfo == null) {
            return;
        }
        LoggerUtil.m1181d("PhotoFragment", "photoFragment frameName " + this.frameInfo.getFrameName());
        this.originalFrameBitmap = BitmapUtil.getLocalBitmap(requireContext(), this.frameInfo.getFramePath());
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.initCamera(requireActivity(), getDefaultBeauty(this.defaultBeautyLevel), this.frameInfo.getFrameType(), this);
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setCutPhoto(AppConfig.isBelongHeadSys() && App.mChooseFragmentType != 213);
        initPhotoAdapter(this.frameInfo);
    }

    void m1681lambda$initObserver$13$comwugudoublecameramainPhotoFragment(Integer num) {
        if (num.intValue() == 1) {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1679lambda$initObserver$11$comwugudoublecameramainPhotoFragment();
                }
            });
            dismissLoadAnim();
            ToastHelper.getInstance().showToast(getString(C1910R.string.resuming));
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1680lambda$initObserver$12$comwugudoublecameramainPhotoFragment();
                }
            }, 3000L);
            return;
        }
        showLoadAnim(getString(C1910R.string.camera_reconnecting));
        ((PhotoVModel) this.mViewModel).pauseCountdown();
    }

    void m1679lambda$initObserver$11$comwugudoublecameramainPhotoFragment() {
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.reconnect();
    }

    void m1680lambda$initObserver$12$comwugudoublecameramainPhotoFragment() {
        ((PhotoVModel) this.mViewModel).resumeCountdown();
        ((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto.setEnabled(true);
        ViewUtil.setImageViewGray(((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto, false, 1.0f);
    }

    private void initListener() {
        ((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1674lambda$initListener$14$comwugudoublecameramainPhotoFragment(view);
            }
        });
        ((FragmentPhotoBinding) this.mViewBinding).btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1675lambda$initListener$15$comwugudoublecameramainPhotoFragment(view);
            }
        });
        ((FragmentPhotoBinding) this.mViewBinding).btnRetake.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1676lambda$initListener$16$comwugudoublecameramainPhotoFragment(view);
            }
        });
        ((FragmentPhotoBinding) this.mViewBinding).layoutBeauty.setListener(new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.chooseBeauty(i);
            }
        });
        this.retakeAdapter.addOnItemChildClickListener(C1910R.id.layout_root, new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1677lambda$initListener$17$comwugudoublecameramainPhotoFragment(baseQuickAdapter, view, i);
            }
        });
    }

    void m1674lambda$initListener$14$comwugudoublecameramainPhotoFragment(View view) {
        gotoNextPhotoStep(2);
    }

    void m1675lambda$initListener$15$comwugudoublecameramainPhotoFragment(View view) {
        LoggerUtil.m1181d("PhotoFragment", "btnConfirm");
        confirm();
    }

    void m1676lambda$initListener$16$comwugudoublecameramainPhotoFragment(View view) {
        retake();
    }

    void m1677lambda$initListener$17$comwugudoublecameramainPhotoFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        selectRetakePicture(i);
    }

    private void initPhotoAdapter(FrameInfo frameInfo) {
        if (frameInfo.getPhotoInfoList() == null || frameInfo.getPhotoInfoList().isEmpty()) {
            ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setOriginWH();
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<String> imageList = ((PhotoVModel) this.mViewModel).getImageList();
        int size = frameInfo.getPhotoInfoList().size() / getPhotoMultiple();
        LoggerUtil.m1181d("PhotoFragment", "photoSize " + size);
        this.remainPictureNum = size;
        int photoMultiple = getPhotoMultiple();
        for (FramePhotoInfo framePhotoInfo : frameInfo.getPhotoInfoList()) {
            PhotoItem photoItem = new PhotoItem();
            photoItem.setHeight(framePhotoInfo.getHeight());
            photoItem.setWidth(framePhotoInfo.getWidth());
            photoItem.setDegrees(framePhotoInfo.getDegrees());
            photoItem.setX(framePhotoInfo.getLocationX());
            photoItem.setY(framePhotoInfo.getLocationY());
            photoItem.setPhotoName(Configurator.NULL);
            imageList.add(Configurator.NULL);
            arrayList.add(photoItem);
            if (photoMultiple > 1 && arrayList.size() == frameInfo.getPhotoInfoList().size() / photoMultiple) {
                break;
            }
        }
        if (arrayList.size() > 0) {
            setCurrentPhotoInfo((PhotoItem) arrayList.get(0));
        }
        this.photoAdapter.setFrameBitmap(this.originalFrameBitmap);
        this.retakeAdapter.setFrameBitmap(this.originalFrameBitmap);
        this.photoAdapter.setShowFrame(!(frameInfo.getFrameType() == 1 || SpManager.getInstance().getSystemMode() == 2));
        this.photoAdapter.submitList(arrayList);
        setPhotoTimesText();
    }

    @Override
    public void finishCapture(boolean z) {
        LoggerUtil.m1181d("PhotoFragment", "finishCapture " + z);
        dismissLoadAnim();
        if (z) {
            takePhotoFinish();
            return;
        }
        ToastHelper.getInstance().showToast(getString(C1910R.string.capture_error));
        gotoNextPhotoStep(1);
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.cameraPreview();
    }

    @Override
    public void recordEnd(String str) {
        if (((FragmentPhotoBinding) this.mViewBinding).layoutCamera.isSystemCamera()) {
            this.mainVModel.saveVideo(str, this.currentFrameW, this.currentFrameH);
        } else {
            this.mainVModel.compoundAndSaveVideo(str, AppConfig.TEMP_DIR + "/ffmpeg" + AppUtil.getSystemTime("yyyyMMdd_HHmmss") + ".mp4");
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    private BeautifyItem getDefaultBeauty(int i) {
        return ((PhotoVModel) this.mViewModel).getBeautyLevelItem(i);
    }

    public void chooseBeauty(int i) {
        LoggerUtil.m1181d("PhotoFragment", "chooseBeauty " + i);
        SoundHelper.getInstance().playSoundEffect(3);
        ((PhotoVModel) this.mViewModel).setBeautyFace(i);
    }

    public void addPhotoAddedItem(PhotoAddedItem photoAddedItem) {
        int photoAddedType = photoAddedItem.getPhotoAddedType();
        if (photoAddedType == 1) {
            LoggerUtil.m1181d("PhotoFragment", "FILTER " + photoAddedItem.getName());
            ((PhotoVModel) this.mViewModel).setFilterIndex(photoAddedItem.getName());
            return;
        }
        if (photoAddedType == 2) {
            LoggerUtil.m1181d("PhotoFragment", "MAKEUP " + photoAddedItem.getName());
            ((PhotoVModel) this.mViewModel).setMakeupIndex(photoAddedItem.getName());
        } else if (photoAddedType == 3 || photoAddedType == 4 || photoAddedType == 5) {
            EffectEntity effectByDemo = DbHelper.getInstance().getEffectByDemo(photoAddedItem.getDemoPath());
            if (effectByDemo == null) {
                ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setEffectUrl(4, null);
            } else {
                LoggerUtil.m1181d("PhotoFragment", "EFFECT " + effectByDemo.getEffectUrl());
                ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setEffectUrl(effectByDemo.getEffectType(), effectByDemo.getEffectUrl());
            }
        }
    }

    private void setPreviewGroupVisible(boolean z) {
        ((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto.setVisibility(z ? 0 : 4);
        ((FragmentPhotoBinding) this.mViewBinding).layoutBeauty.setVisibility(z ? 0 : 4);
        ((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto.setEnabled(z);
        ((FragmentPhotoBinding) this.mViewBinding).layoutBeauty.setEnabled(z);
        if (z) {
            ((FragmentPhotoBinding) this.mViewBinding).layoutBeauty.startAnim();
            this.photoAddedFragment.startAnim();
        } else {
            ((FragmentPhotoBinding) this.mViewBinding).layoutBeauty.stopAnim();
            this.photoAddedFragment.stopAnim();
        }
    }

    private void gotoNextPhotoStep(int i) {
        int photoConfirmTime;
        LoggerUtil.m1181d("PhotoFragment", "gotoNextPhotoStep " + i);
        this.photoStep = i;
        if (i == 1) {
            ((FragmentPhotoBinding) this.mViewBinding).fragmentPhotoAdded.setVisibility(0);
            ((FragmentPhotoBinding) this.mViewBinding).tvRetakeCount.setVisibility(8);
            ((FragmentPhotoBinding) this.mViewBinding).btnRetake.setVisibility(8);
            ((FragmentPhotoBinding) this.mViewBinding).btnConfirm.setVisibility(8);
            ((FragmentPhotoBinding) this.mViewBinding).rvRetake.setVisibility(8);
            ((FragmentPhotoBinding) this.mViewBinding).rvPhoto.setVisibility(8);
            setPreviewGroupVisible(true);
            photoConfirmTime = SpManager.getInstance().getPhotoPreviewTime();
            ((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto.setEnabled(false);
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m979xfb39ce94();
                }
            }, 2000L);
            ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PHOTO_PREVIEW_BG);
            if (App.mSystemMode == 2) {
                moveCameraLayoutPos(1011);
            } else {
                moveCameraLayoutPos(UiPosIndexEnum.PHOTO_PREVIEW_POS);
            }
        } else if (i == 2) {
            ViewUtil.setUiLocate(((FragmentPhotoBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PHOTO_CONFIRM_BG);
            if (App.mSystemMode == 2) {
                moveCameraLayoutPos(1012);
            } else {
                moveCameraLayoutPos(UiPosIndexEnum.PHOTO_CONFIRM_POS);
            }
            ((FragmentPhotoBinding) this.mViewBinding).fragmentPhotoAdded.setVisibility(8);
            setPreviewGroupVisible(false);
            photoConfirmTime = SpManager.getInstance().getPhotoReadyTime();
            SoundHelper.getInstance().playSoundEffect(10);
        } else {
            ((FragmentPhotoBinding) this.mViewBinding).tvRetakeCount.setVisibility(0);
            ((FragmentPhotoBinding) this.mViewBinding).btnRetake.setVisibility(0);
            ((FragmentPhotoBinding) this.mViewBinding).btnConfirm.setVisibility(0);
            ((FragmentPhotoBinding) this.mViewBinding).rvRetake.setVisibility(0);
            ((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto.setVisibility(8);
            ((FragmentPhotoBinding) this.mViewBinding).btnConfirm.setEnabled(true);
            if (this.frameInfo.getFrameType() != 1) {
                ((FragmentPhotoBinding) this.mViewBinding).rvPhoto.setVisibility(0);
            }
            photoConfirmTime = SpManager.getInstance().getPhotoConfirmTime();
        }
        ((FragmentPhotoBinding) this.mViewBinding).layoutCountdown.setCount(photoConfirmTime);
        ((PhotoVModel) this.mViewModel).startCountdown(photoConfirmTime);
    }

    void m979xfb39ce94() {
        if (this.mViewBinding != 0) {
            ((FragmentPhotoBinding) this.mViewBinding).btnStartPhoto.setEnabled(true);
        }
    }

    private void aiEnhancedPhoto() {
        if (this.retakePictureSelectedIndex >= this.retakeList.size()) {
            return;
        }
        Bitmap bitmap = this.retakeList.get(this.retakePictureSelectedIndex);
        if (bitmap == null || TextUtils.isEmpty(this.frameInfo.getAiModuleIds())) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.generate_fail));
        } else {
            showLoadAnim(getString(C1910R.string.generating));
            ((PhotoVModel) this.mViewModel).aiEnhance(getContext(), bitmap, this.frameInfo.getFrameKey());
        }
    }

    private void takePhotoFinish() {
        ((FragmentPhotoBinding) this.mViewBinding).tvRetakeCount.setText(getString(C1910R.string.remain_count, Integer.valueOf(this.retakeCount)));
        if (this.retakeCount <= 0) {
            setEnableImageView(false, ((FragmentPhotoBinding) this.mViewBinding).btnRetake);
        }
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.showTempPicture();
            }
        }, 300L);
        gotoNextPhotoStep(3);
    }

    private void selectRetakePicture(int i) {
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setIvCameraBitmap(this.retakeAdapter.getItem(i));
        this.retakeAdapter.setSelectedPosition(i);
        this.retakeAdapter.notifyItemChanged(this.retakePictureSelectedIndex);
        this.retakeAdapter.notifyItemChanged(i);
        this.retakePictureSelectedIndex = i;
    }

    public void showTempPicture() {
        if (this.retakeList == null) {
            this.retakeList = new ArrayList();
        }
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m981x9884d678();
            }
        });
    }

    void m981x9884d678() {
        Bitmap ivCameraBitmap = ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.getIvCameraBitmap();
        if (ivCameraBitmap != null) {
            this.retakeList.add(ivCameraBitmap);
            this.retakeAdapter.submitList(this.retakeList);
            int size = this.retakeList.size() - 1;
            this.retakeAdapter.setSelectedPosition(size);
            this.retakeAdapter.notifyItemChanged(this.retakePictureSelectedIndex);
            this.retakeAdapter.notifyItemChanged(size);
            this.retakePictureSelectedIndex = size;
        }
        ((FragmentPhotoBinding) this.mViewBinding).rvRetake.setVisibility(0);
    }

    private void clearTempRetakeList() {
        List<Bitmap> list = this.retakeList;
        if (list != null) {
            for (Bitmap bitmap : list) {
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
            this.retakeList.clear();
            this.retakeList = null;
        }
    }

    private void retake() {
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.cameraPreview();
        gotoNextPhotoStep(1);
        this.retakeCount--;
    }

    private void confirm() {
        if (this.frameInfo.getFrameType() == 1) {
            aiEnhancedPhoto();
            return;
        }
        ((FragmentPhotoBinding) this.mViewBinding).btnConfirm.setEnabled(false);
        ActivityManager activityManager = (ActivityManager) requireContext().getSystemService(TimeoutPredicate.activity);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        LoggerUtil.m1181d("PhotoFragment", "remainPictureNum = " + this.remainPictureNum + ", av " + ((memoryInfo.availMem / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE));
        this.mainVModel.retakeConfirm(this.frameInfo.getFrameKey(), this.retakePictureSelectedIndex, this.remainPictureNum <= 1);
        Bitmap ivCameraBitmap = ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.getIvCameraBitmap();
        PhotoItem item = this.photoAdapter.getItem(((PhotoVModel) this.mViewModel).getImageList().size() - this.remainPictureNum);
        if (ivCameraBitmap != null && !ivCameraBitmap.isRecycled()) {
            LoggerUtil.m1181d("PhotoFragment", "bitmap != null");
            String subFrameName = AppConfig.getSubFrameName();
            ((PhotoVModel) this.mViewModel).saveImage(requireContext(), ivCameraBitmap, subFrameName, ((PhotoVModel) this.mViewModel).getImageList().size() - this.remainPictureNum);
            if (item != null) {
                item.setPhotoName(subFrameName);
            }
        }
        this.retakeCount = SpManager.getInstance().getRetakeCount();
        int i = this.remainPictureNum - 1;
        this.remainPictureNum = i;
        if (i > 0) {
            setPhotoTimesText();
            ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.cameraPreview();
            gotoNextPhotoStep(1);
            PhotoItem item2 = this.photoAdapter.getItem(((PhotoVModel) this.mViewModel).getImageList().size() - this.remainPictureNum);
            if (item2 != null) {
                setCurrentPhotoInfo(item2);
            }
            setEnableImageView(true, ((FragmentPhotoBinding) this.mViewBinding).btnRetake);
            return;
        }
        dismissLoadAnim();
        AnimUtil.startBtnClickAnim(((FragmentPhotoBinding) this.mViewBinding).btnConfirm);
        ToastHelper.getInstance().showToast(getString(C1910R.string.saving));
    }

    private void setPhotoTimesText() {
        int size = (this.frameInfo.getPhotoInfoList().size() - this.remainPictureNum) + 1;
        int size2 = this.frameInfo.getPhotoInfoList().size();
        int photoMultiple = getPhotoMultiple();
        if (photoMultiple != 1) {
            size = ((this.frameInfo.getPhotoInfoList().size() - (this.remainPictureNum * photoMultiple)) / photoMultiple) + 1;
            size2 /= photoMultiple;
        }
        ((FragmentPhotoBinding) this.mViewBinding).tvPhotoTimes.setText(size + MqttTopic.TOPIC_LEVEL_SEPARATOR + size2);
    }

    private int getPhotoMultiple() {
        FrameInfo frameInfo = this.frameInfo;
        if (frameInfo == null) {
            return 1;
        }
        int i = frameInfo.isNeedCut() ? 2 : 1;
        if (TextUtils.isEmpty(this.frameInfo.getRemark()) || !this.frameInfo.getRemark().contains(FrameRemarkEnum.IS_BROKEN_LINE)) {
            return i;
        }
        String remarkValueByKey = StringUtil.getRemarkValueByKey(this.frameInfo.getRemark(), FrameRemarkEnum.IS_BROKEN_LINE);
        return (TextUtils.isEmpty(remarkValueByKey) || !remarkValueByKey.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) ? i : i * 2;
    }

    private void setCurrentPhotoInfo(final PhotoItem photoItem) {
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m980x9cbb615e(photoItem);
            }
        });
    }

    void m980x9cbb615e(PhotoItem photoItem) {
        if (photoItem.getX() + photoItem.getWidth() > this.originalFrameBitmap.getWidth() || photoItem.getY() + photoItem.getHeight() > this.originalFrameBitmap.getHeight() || photoItem.getWidth() <= 0 || photoItem.getHeight() <= 0) {
            ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setOriginWH();
            return;
        }
        ((FragmentPhotoBinding) this.mViewBinding).layoutCamera.setCurrentPhotoInfo(photoItem, this.frameInfo.getFrameType() != 1 ? Bitmap.createBitmap(this.originalFrameBitmap, photoItem.getX(), photoItem.getY(), photoItem.getWidth(), photoItem.getHeight()) : null);
        this.currentFrameW = photoItem.getWidth();
        this.currentFrameH = photoItem.getHeight();
        this.retakeAdapter.setPictureInfo(photoItem.getHeight(), photoItem.getWidth(), photoItem.getX(), photoItem.getY());
    }

    private void setEnableImageView(boolean z, AppCompatImageView appCompatImageView) {
        appCompatImageView.setEnabled(z);
        ViewUtil.setImageViewGray(appCompatImageView, !z, 0.0f);
    }

    private void moveCameraLayoutPos(int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition == null) {
            return;
        }
        int i2 = App.mSystemMode == 2 ? 100 : 420;
        AnimUtil.moveGroupView(((FragmentPhotoBinding) this.mViewBinding).layoutCamera, uiPosition.f2441x, uiPosition.f2442y, 500);
        AnimUtil.moveView(((FragmentPhotoBinding) this.mViewBinding).tvPhotoTimes, uiPosition.f2441x + i2, uiPosition.f2442y - 70, 500);
    }
}
