package com.wugu.doublecamera.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.bean.PhotoItem;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.LayoutCameraViewBinding;
import com.wugu.doublecamera.device.camera.CameraHandler;
import com.wugu.doublecamera.device.camera.camera2.Camera2Handler;
import com.wugu.doublecamera.device.camera.camerausb.canon1500d.CameraCanon1500D;
import com.wugu.doublecamera.listener.IBitmapListener;
import com.wugu.doublecamera.listener.ICaptureListener;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.facebeauty.bean.BeautifyItem;

public class CameraView extends ConstraintLayout implements IBitmapListener {
    private final LayoutCameraViewBinding binding;
    private CameraHandler cameraHandler;
    private Bitmap currentBitmap;
    private int frameType;
    private boolean isCutPhoto;
    private boolean isShowPreviewAnim;

    public CameraView(Context context) {
        this(context, null);
    }

    public CameraView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCutPhoto = true;
        this.isShowPreviewAnim = true;
        this.binding = LayoutCameraViewBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void initCamera(Activity activity, BeautifyItem beautifyItem, int i, ICaptureListener iCaptureListener) {
        this.frameType = i;
        if (this.cameraHandler != null) {
            return;
        }
        if (App.selectedCameraIndex == 1) {
            this.binding.ivCamera.setVisibility(8);
            this.cameraHandler = new CameraHandler(new Camera2Handler(activity, this.binding.glSurface, beautifyItem, iCaptureListener, this), null);
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1579lambda$initCamera$0$comwugudoublecameracustomCameraView();
                }
            }, 1000L);
        } else if (App.selectedCameraIndex == 2) {
            this.binding.ivCamera.setVisibility(4);
            this.cameraHandler = new CameraHandler(null, new CameraCanon1500D(activity, this.binding.glSurface, beautifyItem, iCaptureListener, this));
        }
    }

    void m1579lambda$initCamera$0$comwugudoublecameracustomCameraView() {
        setZoom(SpManager.getInstance().getCameraZoom());
        setExposure(SpManager.getInstance().getCameraExposure());
    }

    public void closeCamera() {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.close();
        this.cameraHandler = null;
    }

    public void stopCamera() {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.stopCamera();
    }

    public void reopenCamera() {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.reopen();
        this.binding.ivCamera.setImageBitmap(null);
        Bitmap bitmap = this.currentBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.currentBitmap.recycle();
        this.currentBitmap = null;
    }

    @Override
    public void onBitmapResult(final Bitmap bitmap, int i) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1580lambda$onBitmapResult$1$comwugudoublecameracustomCameraView(bitmap);
            }
        });
    }

    void m1580lambda$onBitmapResult$1$comwugudoublecameracustomCameraView(Bitmap bitmap) {
        setIvCameraBitmap(bitmap);
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public void setCutPhoto(boolean z) {
        this.isCutPhoto = z;
    }

    public void setShowPreviewAnim(boolean z) {
        this.isShowPreviewAnim = z;
    }

    public void reconnect() {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.reconnect();
    }

    public boolean isSystemCamera() {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return false;
        }
        return cameraHandler.isSystemCamera();
    }

    public void setZoom(int i) {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.setZoom(i);
    }

    public void setExposure(int i) {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.setExposure(i);
    }

    public int setCountAndStep(int i, int i2) {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return -1;
        }
        if (cameraHandler.isSystemCamera()) {
            if (i2 == 2) {
                if (i == 3) {
                    SoundHelper.getInstance().playSoundEffect(4);
                }
                if (i == SpManager.getInstance().getPhotoReadyTime() && this.frameType != 1) {
                    this.cameraHandler.startRecord();
                }
            }
            if (i == 0) {
                if (i2 == 1) {
                    return 0;
                }
                if (i2 != 2) {
                    return 1;
                }
                this.cameraHandler.capture();
                this.binding.ivCamera.setVisibility(0);
                SoundHelper.getInstance().playSoundEffect(5);
                return 2;
            }
        } else {
            if (i2 == 2) {
                if (i == SpManager.getInstance().getPhotoReadyTime() && this.frameType != 1) {
                    AppUtil.runOnUIDelayed(new Runnable() {
                        @Override
                        public final void run() {
                            this.f$0.m1581lambda$setCountAndStep$2$comwugudoublecameracustomCameraView();
                        }
                    }, 600L);
                } else if (i == 3) {
                    SoundHelper.getInstance().playSoundEffect(4);
                } else if (i == 1) {
                    this.cameraHandler.capture();
                } else if (i == 0) {
                    this.binding.ivCamera.setVisibility(0);
                    SoundHelper.getInstance().playSoundEffect(5);
                    return 2;
                }
            }
            if (i == 0) {
                if (i2 == 1) {
                    return 0;
                }
                if (i2 == 3) {
                    return 1;
                }
            }
        }
        return -1;
    }

    void m1581lambda$setCountAndStep$2$comwugudoublecameracustomCameraView() {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.startRecord();
    }

    public void cameraPreview() {
        if (this.cameraHandler == null) {
            return;
        }
        if (this.isShowPreviewAnim) {
            this.binding.vCircleAnim.setVisibility(0);
            this.binding.vCircleAnim.bringToFront();
            this.binding.vCircleAnim.startAnimation();
        }
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1578lambda$cameraPreview$3$comwugudoublecameracustomCameraView();
            }
        }, 50L);
    }

    void m1578lambda$cameraPreview$3$comwugudoublecameracustomCameraView() {
        this.binding.ivCamera.setImageBitmap(null);
        this.binding.ivCamera.setVisibility(4);
        this.cameraHandler.preview();
    }

    public void setBeautyItem(BeautifyItem beautifyItem) {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.setBeautyFace(beautifyItem);
    }

    public void setEffectUrl(int i, String str) {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        cameraHandler.setEffectUrl(i, str);
    }

    public void setCurrentPhotoInfo(PhotoItem photoItem, Bitmap bitmap) {
        if (this.cameraHandler == null) {
            return;
        }
        if (!this.isCutPhoto) {
            setOriginWH();
            this.cameraHandler.setFrameWH(photoItem.getWidth(), photoItem.getHeight());
        } else {
            if (bitmap != null) {
                Glide.with(getContext()).load(bitmap).into(this.binding.ivPhotoBorder);
            }
            cameraCutView(photoItem.getWidth(), photoItem.getHeight());
        }
    }

    public void setOriginWH() {
        CameraHandler cameraHandler = this.cameraHandler;
        if (cameraHandler == null) {
            return;
        }
        int[] previewSize = cameraHandler.getPreviewSize();
        cameraCutView(previewSize[0], previewSize[1]);
    }

    public void setIvCameraBitmap(Bitmap bitmap) {
        this.currentBitmap = BitmapUtil.copyBitmap(bitmap);
        this.binding.ivCamera.setImageBitmap(this.currentBitmap);
    }

    public void setIvCameraBitmapNotC(Bitmap bitmap) {
        this.currentBitmap = bitmap;
        this.binding.ivCamera.setImageBitmap(this.currentBitmap);
    }

    public Bitmap getIvCameraBitmap() {
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap = this.currentBitmap;
        if ((bitmap == null || bitmap.isRecycled()) && (bitmapDrawable = (BitmapDrawable) this.binding.ivCamera.getDrawable()) != null) {
            return bitmapDrawable.getBitmap();
        }
        return this.currentBitmap;
    }

    private void cameraCutView(int i, int i2) {
        this.cameraHandler.setBitmapRect(i, i2);
    }
}
