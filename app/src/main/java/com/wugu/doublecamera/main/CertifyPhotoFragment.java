package com.wugu.doublecamera.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.CertifyRetakeAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.PhotoItem;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentCertifyPhotoBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.ICaptureListener;
import com.wugu.doublecamera.main.p025vm.CertifyPhotoVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.spi.Configurator;

public class CertifyPhotoFragment extends ABaseFragment<FragmentCertifyPhotoBinding, CertifyPhotoVModel> implements ICaptureListener {
    private int bgColorSelectedIndex;
    private FrameInfo frameInfo;
    private MainVModel mainVModel;
    private int photoStep;
    private final CertifyRetakeAdapter retakeAdapter = new CertifyRetakeAdapter();
    private List<Bitmap[]> retakeBitmapsList;
    private int retakeCount;
    private List<Bitmap> retakeList;
    private int retakePictureSelectedIndex;

    @Override
    public void recordEnd(String str) {
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        LoggerUtil.m1181d("fragment", "CertifyPhotoFragment onViewCreated ");
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initData();
        initObserver();
        initListener();
        gotoNextPhotoStep(1);
    }

    @Override
    public void onPause() {
        super.onPause();
        LoggerUtil.m1181d("fragment", "CertifyPhotoFragment onPause");
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.closeCamera();
        ((CertifyPhotoVModel) this.mViewModel).cancelCountdown();
        this.mainVModel = null;
        clearTempRetakeList();
        Glide.get(requireContext()).clearMemory();
    }

    private void initView() {
        ((FragmentCertifyPhotoBinding) this.mViewBinding).rvRetake.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
        ((FragmentCertifyPhotoBinding) this.mViewBinding).rvRetake.setAdapter(this.retakeAdapter);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).rvRetake.setItemAnimator(null);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCountdown, UiPosIndexEnum.PHOTO_COUNTDOWN);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.CERTIFY_BG_PREVIEW);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).ivRed, UiPosIndexEnum.CERTIFY_RED_BG);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).ivBlue, UiPosIndexEnum.CERTIFY_BLUE_BG);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).ivWhite, UiPosIndexEnum.CERTIFY_WHITE_BG);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).viewConfirmBg, UiPosIndexEnum.CERTIFY_COLOR_BG);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).btnConfirm, UiPosIndexEnum.PHOTO_BTN_CONFIRM);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).btnRetake, UiPosIndexEnum.PHOTO_BTN_RETAKE);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).btnStartPhoto, UiPosIndexEnum.PHOTO_BTN_PHOTO);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).tvRetakeCount, UiPosIndexEnum.PHOTO_TV_RETAKE_COUNT);
    }

    private void initData() {
        App.mCertifySelectIndex = 0;
        this.retakePictureSelectedIndex = 0;
        this.retakeCount = SpManager.getInstance().getRetakeCount();
    }

    private void initObserver() {
        ((CertifyPhotoVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m892xef5feb47((Integer) obj);
            }
        });
        ((CertifyPhotoVModel) this.mViewModel).getSaveImageFinishLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m893xeee98548((Integer) obj);
            }
        });
        ((CertifyPhotoVModel) this.mViewModel).getCutoutFinishLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m894xee731f49((Integer) obj);
            }
        });
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m895xedfcb94a((String) obj);
            }
        });
        this.mainVModel.digitalCameraResetLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m898xec99874d((Integer) obj);
            }
        });
    }

    void m892xef5feb47(Integer num) {
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        int countAndStep = ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.setCountAndStep(num.intValue(), this.photoStep);
        LoggerUtil.m1181d("fragment", "CertifyPhotoFragment getCountDownLD photoStep " + this.photoStep + " re " + countAndStep);
        if (countAndStep == 0) {
            gotoNextPhotoStep(2);
        } else if (countAndStep == 1) {
            confirm();
        } else if (countAndStep == 2) {
            showLoadAnim(getString(C1910R.string.processing_photo));
        }
    }

    void m893xeee98548(Integer num) {
        this.retakeAdapter.submitList(null);
        clearTempRetakeList();
        this.mainVModel.photoUrlLD.postValue(((CertifyPhotoVModel) this.mViewModel).getImageList());
        this.mainVModel.fragmentLD.postValue(10);
    }

    void m894xee731f49(Integer num) {
        dismissLoadAnim();
        if (num.intValue() == 0) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.error));
            return;
        }
        if (this.retakeBitmapsList == null) {
            this.retakeBitmapsList = new ArrayList();
        }
        this.retakeBitmapsList.add(((CertifyPhotoVModel) this.mViewModel).getOutputBitmaps());
        int photoConfirmTime = SpManager.getInstance().getPhotoConfirmTime();
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCountdown.setCount(photoConfirmTime);
        ((CertifyPhotoVModel) this.mViewModel).startCountdown(photoConfirmTime);
        selectBgColor(0);
    }

    void m895xedfcb94a(String str) {
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        this.frameInfo = orderFrameInfo;
        if (orderFrameInfo == null) {
            return;
        }
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.initCamera(requireActivity(), ((CertifyPhotoVModel) this.mViewModel).getBeautyLevel(), this.frameInfo.getFrameType(), this);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.setCutPhoto(AppConfig.isBelongHeadSys());
        Iterator<FramePhotoInfo> it = this.frameInfo.getPhotoInfoList().iterator();
        if (it.hasNext()) {
            FramePhotoInfo next = it.next();
            PhotoItem photoItem = new PhotoItem();
            photoItem.setHeight(next.getHeight());
            photoItem.setWidth(next.getWidth());
            photoItem.setDegrees(next.getDegrees());
            photoItem.setX(next.getLocationX());
            photoItem.setY(next.getLocationY());
            photoItem.setPhotoName(Configurator.NULL);
            setCurrentPhotoInfo(photoItem);
        }
    }

    void m898xec99874d(Integer num) {
        LoggerUtil.m1181d("fragment", "digitalCameraResetLD " + num);
        if (num.intValue() == 1) {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m896xed86534b();
                }
            });
            dismissLoadAnim();
            ToastHelper.getInstance().showToast(getString(C1910R.string.resuming));
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m897xed0fed4c();
                }
            }, 3000L);
            return;
        }
        showLoadAnim(getString(C1910R.string.camera_reconnecting));
        ((CertifyPhotoVModel) this.mViewModel).pauseCountdown();
    }

    void m896xed86534b() {
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.reconnect();
    }

    void m897xed0fed4c() {
        ((CertifyPhotoVModel) this.mViewModel).resumeCountdown();
    }

    private void initListener() {
        ((FragmentCertifyPhotoBinding) this.mViewBinding).btnStartPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m889xfb1e1fac(view);
            }
        });
        ((FragmentCertifyPhotoBinding) this.mViewBinding).btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m890xfaa7b9ad(view);
            }
        });
        ((FragmentCertifyPhotoBinding) this.mViewBinding).btnRetake.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m891xfa3153ae(view);
            }
        });
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m885xd9c3e9ba(view);
            }
        });
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m886xd94d83bb(view);
            }
        });
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m887xd8d71dbc(view);
            }
        });
        this.retakeAdapter.addOnItemChildClickListener(C1910R.id.layout_root, new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m888xd860b7bd(baseQuickAdapter, view, i);
            }
        });
    }

    void m889xfb1e1fac(View view) {
        gotoNextPhotoStep(2);
    }

    void m890xfaa7b9ad(View view) {
        confirm();
    }

    void m891xfa3153ae(View view) {
        retake();
    }

    void m885xd9c3e9ba(View view) {
        selectBgColor(0);
    }

    void m886xd94d83bb(View view) {
        selectBgColor(1);
    }

    void m887xd8d71dbc(View view) {
        selectBgColor(2);
    }

    void m888xd860b7bd(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        selectRetakePicture(i);
    }

    private void gotoNextPhotoStep(int i) {
        LoggerUtil.m1181d("fragment", "gotoNextPhotoStep " + i);
        this.photoStep = i;
        if (i == 1) {
            ((FragmentCertifyPhotoBinding) this.mViewBinding).tvRetakeCount.setVisibility(8);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).btnRetake.setVisibility(8);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).btnConfirm.setVisibility(8);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).rvRetake.setVisibility(8);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).btnStartPhoto.setVisibility(0);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivRed.setVisibility(8);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivWhite.setVisibility(8);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivBlue.setVisibility(8);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivCutoutPicture.setVisibility(8);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.setVisibility(0);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).viewConfirmBg.setVisibility(8);
            ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.CERTIFY_BG_PREVIEW);
            int photoPreviewTime = SpManager.getInstance().getPhotoPreviewTime();
            ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCountdown.setCount(photoPreviewTime);
            ((CertifyPhotoVModel) this.mViewModel).startCountdown(photoPreviewTime);
            return;
        }
        if (i == 2) {
            ((FragmentCertifyPhotoBinding) this.mViewBinding).btnStartPhoto.setVisibility(8);
            SoundHelper.getInstance().playSoundEffect(10);
            int photoReadyTime = SpManager.getInstance().getPhotoReadyTime();
            ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCountdown.setCount(photoReadyTime);
            ((CertifyPhotoVModel) this.mViewModel).startCountdown(photoReadyTime);
            return;
        }
        ((FragmentCertifyPhotoBinding) this.mViewBinding).tvRetakeCount.setVisibility(0);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).btnRetake.setVisibility(0);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).btnConfirm.setVisibility(0);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).rvRetake.setVisibility(0);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).btnStartPhoto.setVisibility(8);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivRed.setVisibility(0);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivWhite.setVisibility(0);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivBlue.setVisibility(0);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivCutoutPicture.setVisibility(0);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.setVisibility(8);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).viewConfirmBg.setVisibility(0);
        ViewUtil.setUiLocate(((FragmentCertifyPhotoBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.CERTIFY_BG_CONFIRM);
    }

    private void retake() {
        LoggerUtil.m1181d("fragment", "retake");
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.cameraPreview();
        gotoNextPhotoStep(1);
        this.retakeCount--;
    }

    private void confirm() {
        LoggerUtil.m1181d("fragment", "retake");
        ((FragmentCertifyPhotoBinding) this.mViewBinding).btnConfirm.setEnabled(false);
        ((CertifyPhotoVModel) this.mViewModel).saveImage(requireContext(), this.retakeList.get(this.retakePictureSelectedIndex), this.retakeBitmapsList.get(this.retakePictureSelectedIndex), this.retakePictureSelectedIndex);
        ToastHelper.getInstance().showToast(getString(C1910R.string.saving));
    }

    private void takePhotoFinish() {
        LoggerUtil.m1181d("fragment", "takePhotoFinish");
        ((FragmentCertifyPhotoBinding) this.mViewBinding).tvRetakeCount.setText(getString(C1910R.string.remain_count, Integer.valueOf(this.retakeCount)));
        if (this.retakeCount <= 0) {
            ((FragmentCertifyPhotoBinding) this.mViewBinding).btnRetake.setEnabled(false);
            ViewUtil.setImageViewGray(((FragmentCertifyPhotoBinding) this.mViewBinding).btnRetake, true, 0.0f);
        }
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.showTempPicture();
            }
        }, 500L);
        gotoNextPhotoStep(3);
    }

    private void selectRetakePicture(int i) {
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivCutoutPicture.setImageBitmap(this.retakeAdapter.getItem(i));
        this.retakeAdapter.setSelectedPosition(i);
        this.retakeAdapter.notifyItemChanged(this.retakePictureSelectedIndex);
        this.retakeAdapter.notifyItemChanged(i);
        this.retakePictureSelectedIndex = i;
        selectBgColor(this.bgColorSelectedIndex);
    }

    private void selectBgColor(int i) {
        List<Bitmap[]> list = this.retakeBitmapsList;
        if (list == null || list.size() == 0 || this.retakePictureSelectedIndex >= this.retakeBitmapsList.size()) {
            return;
        }
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivCutoutPicture.setImageBitmap(this.retakeBitmapsList.get(this.retakePictureSelectedIndex)[i]);
        this.bgColorSelectedIndex = i;
        App.mCertifySelectIndex = i;
        if (i == 0) {
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivRed.setBackgroundResource(C1910R.drawable.shape_bg_certify);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivBlue.setBackground(null);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivWhite.setBackground(null);
        } else if (i == 1) {
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivRed.setBackground(null);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivBlue.setBackgroundResource(C1910R.drawable.shape_bg_certify);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivWhite.setBackground(null);
        } else {
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivRed.setBackground(null);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivBlue.setBackground(null);
            ((FragmentCertifyPhotoBinding) this.mViewBinding).ivWhite.setBackgroundResource(C1910R.drawable.shape_bg_certify);
        }
    }

    public void showTempPicture() {
        if (this.retakeList == null) {
            this.retakeList = new ArrayList();
        }
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.post(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.m899x89bb448f();
            }
        });
    }

    void m899x89bb448f() throws Throwable {
        Bitmap ivCameraBitmap = ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.getIvCameraBitmap();
        LoggerUtil.m1181d("fragment", "showTempPicture bitmap " + ivCameraBitmap);
        if (ivCameraBitmap == null || ivCameraBitmap.isRecycled()) {
            return;
        }
        this.retakeList.add(ivCameraBitmap);
        this.retakeAdapter.submitList(this.retakeList);
        int size = this.retakeList.size() - 1;
        this.retakeAdapter.setSelectedPosition(size);
        this.retakeAdapter.notifyItemChanged(this.retakePictureSelectedIndex);
        this.retakeAdapter.notifyItemChanged(size);
        this.retakePictureSelectedIndex = size;
        showLoadAnim(getString(C1910R.string.certify_loading));
        setLoadImage(C1910R.mipmap.ic_certify_loading);
        ((CertifyPhotoVModel) this.mViewModel).cutPictureByNet(ivCameraBitmap, new String[]{"#FF0000", "#398EDB", "#FFFFFF"}, requireContext(), this.frameInfo);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).ivCutoutPicture.setImageBitmap(ivCameraBitmap);
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
        List<Bitmap[]> list2 = this.retakeBitmapsList;
        if (list2 != null) {
            for (Bitmap[] bitmapArr : list2) {
                for (Bitmap bitmap2 : bitmapArr) {
                    if (bitmap2 != null && !bitmap2.isRecycled()) {
                        bitmap2.recycle();
                    }
                }
            }
            this.retakeBitmapsList.clear();
            this.retakeBitmapsList = null;
        }
    }

    private void setCurrentPhotoInfo(PhotoItem photoItem) {
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.setCurrentPhotoInfo(photoItem, null);
    }

    @Override
    public void finishCapture(boolean z) {
        LoggerUtil.m1181d("fragment", "finishCapture " + z);
        dismissLoadAnim();
        if (z) {
            takePhotoFinish();
            return;
        }
        ToastHelper.getInstance().showToast(getString(C1910R.string.capture_error));
        gotoNextPhotoStep(1);
        ((FragmentCertifyPhotoBinding) this.mViewBinding).layoutCamera.cameraPreview();
    }
}
