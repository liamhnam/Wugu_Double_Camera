package com.faceunity.core.faceunity;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.callback.OnPosterRenderCallback;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.controller.poster.PosterController;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.entity.FURenderOutputData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUFaceProcessorDetectModeEnum;
import com.faceunity.core.enumeration.FUHumanProcessorDetectModeEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUInputTextureEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.faceunity.core.enumeration.PosterFaceEnum;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.utils.BitmapUtils;
import com.faceunity.core.utils.DecimalUtils;
import com.faceunity.core.utils.FULogger;
import com.faceunity.core.utils.FileUtils;
import com.faceunity.core.utils.GlUtil;
import com.p020hp.jipp.model.PowerState;
import com.p020hp.jipp.model.Status;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 U2\u00020\u0001:\u0001UB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010:\u001a\u00020;2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u000e\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\u001cJ\u0016\u0010>\u001a\u00020;2\u0006\u0010?\u001a\u00020\u001c2\u0006\u0010@\u001a\u00020\u001cJ\u0010\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020BH\u0002J\b\u0010D\u001a\u00020;H\u0002J\b\u0010E\u001a\u00020;H\u0002J \u0010F\u001a\u0012\u0012\u0004\u0012\u00020B0Gj\b\u0012\u0004\u0012\u00020B`H2\u0006\u0010I\u001a\u00020\u001cH\u0002J\u0018\u0010J\u001a\u00020;2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\u001cH\u0002J\u0010\u0010J\u001a\u00020;2\u0006\u0010N\u001a\u000202H\u0002J\u0018\u0010O\u001a\u00020;2\u0006\u00101\u001a\u0002022\u0006\u00108\u001a\u000209H\u0002J\u0006\u0010P\u001a\u00020;J&\u0010Q\u001a\u00020;2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\u001c2\u0006\u00101\u001a\u0002022\u0006\u0010R\u001a\u000209J\u001e\u0010Q\u001a\u00020;2\u0006\u0010N\u001a\u0002022\u0006\u00101\u001a\u0002022\u0006\u0010R\u001a\u000209J\u0018\u0010S\u001a\u00020;2\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001cH\u0002J\u0016\u0010T\u001a\u00020;2\u0006\u00101\u001a\u0002022\u0006\u0010R\u001a\u000209R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0010\u0010&\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010#\"\u0004\b*\u0010%R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010#\"\u0004\b0\u0010%R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010#\"\u0004\b5\u0010%R\u000e\u00106\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006V"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FUPosterKit;", "", "()V", "handleData", "Lcom/faceunity/core/entity/FUBundleData;", "hasPhotoDraw", "", "hasTemplateDraw", "isNeedPhotoDraw", "mFUAIKit", "Lcom/faceunity/core/faceunity/FUAIKit;", "getMFUAIKit", "()Lcom/faceunity/core/faceunity/FUAIKit;", "mFUAIKit$delegate", "Lkotlin/Lazy;", "mFURenderKit", "Lcom/faceunity/core/faceunity/FURenderKit;", "getMFURenderKit", "()Lcom/faceunity/core/faceunity/FURenderKit;", "mFURenderKit$delegate", "mPosterController", "Lcom/faceunity/core/controller/poster/PosterController;", "getMPosterController", "()Lcom/faceunity/core/controller/poster/PosterController;", "mPosterController$delegate", "mViewPortScale", "", "mViewPortX", "", "mViewPortY", "mergeTexId", "photoBytes", "", "photoHeight", "getPhotoHeight", "()I", "setPhotoHeight", "(I)V", "photoRGBABytes", "photoTextureId", "photoWidth", "getPhotoWidth", "setPhotoWidth", "posterRenderCallback", "Lcom/faceunity/core/callback/OnPosterRenderCallback;", "templateBytes", "templateHeight", "getTemplateHeight", "setTemplateHeight", "templatePath", "", "templateWidth", "getTemplateWidth", "setTemplateWidth", "viewHeight", "viewWidth", "warpIntensity", "", "bindController", "", "bindPhotoData", StreamInformation.KEY_INDEX, "bindSurfaceSize", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "convertFaceRect", "", "faceRect", "destroyPhotoTexture", "doMerge", "getPhotoMaskData", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "trackFace", "loadPhotoData", "photoBitmap", "Landroid/graphics/Bitmap;", "photoTexId", "photoPath", "loadTemplateData", "onDestroy", "renderPoster", "intensity", "scale", "updateTemplate", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUPosterKit {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile FUPosterKit INSTANCE = null;
    public static final String TAG = "KIT_FUPosterKit";
    private FUBundleData handleData;
    private boolean hasPhotoDraw;
    private boolean hasTemplateDraw;
    private boolean isNeedPhotoDraw;

    private final Lazy mFUAIKit;

    private final Lazy mFURenderKit;

    private final Lazy mPosterController;
    private float mViewPortScale;
    private int mViewPortX;
    private int mViewPortY;
    private int mergeTexId;
    private byte[] photoBytes;
    private int photoHeight;
    private byte[] photoRGBABytes;
    private int photoTextureId;
    private int photoWidth;
    private OnPosterRenderCallback posterRenderCallback;
    private byte[] templateBytes;
    private int templateHeight;
    private String templatePath;
    private int templateWidth;
    private int viewHeight;
    private int viewWidth;
    private double warpIntensity;

    @JvmStatic
    public static final FUPosterKit getInstance(FUBundleData fUBundleData, OnPosterRenderCallback onPosterRenderCallback) {
        return INSTANCE.getInstance(fUBundleData, onPosterRenderCallback);
    }

    private final FUAIKit getMFUAIKit() {
        return (FUAIKit) this.mFUAIKit.getValue();
    }

    private final FURenderKit getMFURenderKit() {
        return (FURenderKit) this.mFURenderKit.getValue();
    }

    private final PosterController getMPosterController() {
        return (PosterController) this.mPosterController.getValue();
    }

    private FUPosterKit() {
        this.mPosterController = LazyKt.lazy(new Function0<PosterController>() {
            @Override
            public final PosterController invoke() {
                return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMPosterController$fu_core_all_featureRelease();
            }
        });
        this.mFURenderKit = LazyKt.lazy(new Function0<FURenderKit>() {
            @Override
            public final FURenderKit invoke() {
                return FURenderKit.INSTANCE.getInstance();
            }
        });
        this.mFUAIKit = LazyKt.lazy(new Function0<FUAIKit>() {
            @Override
            public final FUAIKit invoke() {
                return FUAIKit.INSTANCE.getInstance();
            }
        });
        this.templateWidth = 720;
        this.templateHeight = Status.Code.serverErrorInternalError;
        this.warpIntensity = 1.0d;
        this.photoWidth = 720;
        this.photoHeight = Status.Code.serverErrorInternalError;
        this.mViewPortScale = 1.0f;
    }

    public FUPosterKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final FUBundleData access$getHandleData$p(FUPosterKit fUPosterKit) {
        FUBundleData fUBundleData = fUPosterKit.handleData;
        if (fUBundleData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handleData");
        }
        return fUBundleData;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FUPosterKit$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/faceunity/FUPosterKit;", "TAG", "", "getInstance", "handleData", "Lcom/faceunity/core/entity/FUBundleData;", "callback", "Lcom/faceunity/core/callback/OnPosterRenderCallback;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FUPosterKit getInstance(FUBundleData handleData, OnPosterRenderCallback callback) {
            Intrinsics.checkParameterIsNotNull(handleData, "handleData");
            Intrinsics.checkParameterIsNotNull(callback, "callback");
            if (FUPosterKit.INSTANCE == null) {
                synchronized (this) {
                    if (FUPosterKit.INSTANCE == null) {
                        FUPosterKit.INSTANCE = new FUPosterKit(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            FUPosterKit fUPosterKit = FUPosterKit.INSTANCE;
            if (fUPosterKit == null) {
                Intrinsics.throwNpe();
            }
            fUPosterKit.handleData = handleData;
            FUPosterKit fUPosterKit2 = FUPosterKit.INSTANCE;
            if (fUPosterKit2 == null) {
                Intrinsics.throwNpe();
            }
            fUPosterKit2.posterRenderCallback = callback;
            FUPosterKit fUPosterKit3 = FUPosterKit.INSTANCE;
            if (fUPosterKit3 == null) {
                Intrinsics.throwNpe();
            }
            fUPosterKit3.bindController(handleData);
            FUPosterKit fUPosterKit4 = FUPosterKit.INSTANCE;
            if (fUPosterKit4 == null) {
                Intrinsics.throwNpe();
            }
            return fUPosterKit4;
        }
    }

    public final void bindController(FUBundleData handleData) {
        BaseSingleController.loadControllerBundle$fu_core_all_featureRelease$default(getMPosterController(), new FUFeaturesData(handleData, null, false, null, 0L, 30, null), null, 2, null);
    }

    public final int getTemplateWidth() {
        return this.templateWidth;
    }

    public final void setTemplateWidth(int i) {
        this.templateWidth = i;
    }

    public final int getTemplateHeight() {
        return this.templateHeight;
    }

    public final void setTemplateHeight(int i) {
        this.templateHeight = i;
    }

    public final int getPhotoWidth() {
        return this.photoWidth;
    }

    public final void setPhotoWidth(int i) {
        this.photoWidth = i;
    }

    public final int getPhotoHeight() {
        return this.photoHeight;
    }

    public final void setPhotoHeight(int i) {
        this.photoHeight = i;
    }

    public final void bindSurfaceSize(int width, int height) {
        this.viewWidth = width;
        this.viewHeight = height;
    }

    public final void renderPoster(Bitmap photoBitmap, int photoTexId, String templatePath, double intensity) {
        Intrinsics.checkParameterIsNotNull(photoBitmap, "photoBitmap");
        Intrinsics.checkParameterIsNotNull(templatePath, "templatePath");
        this.templatePath = templatePath;
        this.warpIntensity = intensity;
        loadPhotoData(photoBitmap, photoTexId);
        if (this.hasPhotoDraw) {
            loadTemplateData(templatePath, intensity);
            if (this.hasTemplateDraw) {
                doMerge();
            }
        }
    }

    public final void renderPoster(String photoPath, String templatePath, double intensity) {
        Intrinsics.checkParameterIsNotNull(photoPath, "photoPath");
        Intrinsics.checkParameterIsNotNull(templatePath, "templatePath");
        this.templatePath = templatePath;
        this.warpIntensity = intensity;
        loadPhotoData(photoPath);
        if (this.hasPhotoDraw) {
            loadTemplateData(templatePath, intensity);
            if (this.hasTemplateDraw) {
                doMerge();
            }
        }
    }

    public final void bindPhotoData(int index) {
        if (this.isNeedPhotoDraw) {
            float[] fArr = new float[PowerState.Code.resetSoftGraceful];
            getMPosterController().getLandmarksData$fu_core_all_featureRelease(index, fArr);
            PosterController mPosterController = getMPosterController();
            int i = this.photoWidth;
            int i2 = this.photoHeight;
            byte[] bArr = this.photoRGBABytes;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            mPosterController.loadPosterPhoto$fu_core_all_featureRelease(i, i2, bArr, fArr);
            this.hasPhotoDraw = true;
            String str = this.templatePath;
            if (str == null) {
                return;
            }
            if (str == null) {
                Intrinsics.throwNpe();
            }
            loadTemplateData(str, this.warpIntensity);
            if (this.hasTemplateDraw) {
                doMerge();
            }
        }
    }

    public final void updateTemplate(String templatePath, double intensity) {
        Intrinsics.checkParameterIsNotNull(templatePath, "templatePath");
        if (!this.hasPhotoDraw) {
            FULogger.m294e(TAG, "please renderPoster first");
            return;
        }
        this.hasTemplateDraw = false;
        loadTemplateData(templatePath, intensity);
        if (this.hasTemplateDraw) {
            doMerge();
        }
    }

    private final void loadPhotoData(String photoPath) {
        Bitmap bitmapLoadBitmapFromExternal = FileUtils.loadBitmapFromExternal(photoPath, 720);
        destroyPhotoTexture();
        loadPhotoData(bitmapLoadBitmapFromExternal, GlUtil.createImageTexture(bitmapLoadBitmapFromExternal));
    }

    private final void loadPhotoData(Bitmap photoBitmap, int photoTexId) {
        this.photoTextureId = photoTexId;
        this.photoWidth = photoBitmap.getWidth();
        int height = photoBitmap.getHeight();
        this.photoHeight = height;
        scale(this.photoWidth, height);
        this.photoRGBABytes = FileUtils.loadRgbaByteFromBitmap(photoBitmap);
        this.photoBytes = BitmapUtils.INSTANCE.getNV21(this.photoWidth, this.photoHeight, photoBitmap, false);
        int iTrackFace = 0;
        for (int i = 0; i < 50; i++) {
            getMFUAIKit().clearCameraCache();
            FUAIKit mFUAIKit = getMFUAIKit();
            byte[] bArr = this.photoBytes;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            iTrackFace = mFUAIKit.trackFace(bArr, FUInputBufferEnum.FU_FORMAT_NV21_BUFFER, this.photoWidth, this.photoHeight, 0);
            if (iTrackFace > 0) {
                break;
            }
        }
        if (iTrackFace == 0) {
            OnPosterRenderCallback onPosterRenderCallback = this.posterRenderCallback;
            if (onPosterRenderCallback != null) {
                OnPosterRenderCallback.DefaultImpls.onPhotoLoaded$default(onPosterRenderCallback, PosterFaceEnum.POSTER_ERROR_NO_FACE, null, 2, null);
            }
            this.isNeedPhotoDraw = false;
            return;
        }
        if (iTrackFace == 1) {
            if (getMPosterController().checkRotation$fu_core_all_featureRelease()) {
                OnPosterRenderCallback onPosterRenderCallback2 = this.posterRenderCallback;
                if (onPosterRenderCallback2 != null) {
                    OnPosterRenderCallback.DefaultImpls.onPhotoLoaded$default(onPosterRenderCallback2, PosterFaceEnum.POSTER_ERROR_ROTATE_FACE, null, 2, null);
                }
                this.isNeedPhotoDraw = false;
                return;
            }
            float[] fArr = new float[PowerState.Code.resetSoftGraceful];
            getMPosterController().getLandmarksData$fu_core_all_featureRelease(0, fArr);
            PosterController mPosterController = getMPosterController();
            int i2 = this.photoWidth;
            int i3 = this.photoHeight;
            byte[] bArr2 = this.photoRGBABytes;
            if (bArr2 == null) {
                Intrinsics.throwNpe();
            }
            mPosterController.loadPosterPhoto$fu_core_all_featureRelease(i2, i3, bArr2, fArr);
            this.hasPhotoDraw = true;
            return;
        }
        this.isNeedPhotoDraw = true;
        OnPosterRenderCallback onPosterRenderCallback3 = this.posterRenderCallback;
        if (onPosterRenderCallback3 != null) {
            onPosterRenderCallback3.onPhotoLoaded(PosterFaceEnum.POSTER_RIGHT_FACE, getPhotoMaskData(iTrackFace));
        }
    }

    private final void loadTemplateData(String templatePath, double warpIntensity) {
        Bitmap bitmapLoadBitmapFromLocal = FileUtils.loadBitmapFromLocal(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), templatePath);
        if (bitmapLoadBitmapFromLocal == null) {
            FULogger.m294e(TAG, "loadTemplateData failed TemplateData path:" + templatePath);
            return;
        }
        this.templateWidth = bitmapLoadBitmapFromLocal.getWidth();
        this.templateHeight = bitmapLoadBitmapFromLocal.getHeight();
        byte[] bArrLoadRgbaByteFromBitmap = FileUtils.loadRgbaByteFromBitmap(bitmapLoadBitmapFromLocal);
        this.templateBytes = BitmapUtils.getNV21$default(BitmapUtils.INSTANCE, this.templateWidth, this.templateHeight, bitmapLoadBitmapFromLocal, false, 8, null);
        boolean z = false;
        int iTrackFace = 0;
        for (int i = 0; i < 50; i++) {
            getMFUAIKit().clearCameraCache();
            FUAIKit mFUAIKit = getMFUAIKit();
            byte[] bArr = this.templateBytes;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            iTrackFace = mFUAIKit.trackFace(bArr, FUInputBufferEnum.FU_FORMAT_NV21_BUFFER, this.templateWidth, this.templateHeight, 0);
            if (iTrackFace > 0) {
                break;
            }
        }
        if (iTrackFace > 0) {
            float[] fArr = new float[PowerState.Code.resetSoftGraceful];
            getMPosterController().getLandmarksData$fu_core_all_featureRelease(0, fArr);
            getMPosterController().fixPosterFaceParam$fu_core_all_featureRelease(warpIntensity);
            getMPosterController().loadPosterTemplate$fu_core_all_featureRelease(this.templateWidth, this.templateHeight, bArrLoadRgbaByteFromBitmap, fArr);
            z = true;
        } else {
            OnPosterRenderCallback onPosterRenderCallback = this.posterRenderCallback;
            if (onPosterRenderCallback != null) {
                onPosterRenderCallback.onTemplateLoaded(iTrackFace);
            }
        }
        this.hasTemplateDraw = z;
    }

    private final void scale(int photoWidth, int photoHeight) {
        int i = this.viewWidth;
        float f = photoHeight;
        int i2 = this.viewHeight;
        float f2 = photoWidth;
        float f3 = ((i * f) / i2) / f2;
        float f4 = 1;
        if (f3 > f4) {
            this.mViewPortY = 0;
            float f5 = i2 / f;
            this.mViewPortScale = f5;
            this.mViewPortX = (int) ((i - (f5 * f2)) / 2);
            return;
        }
        if (f3 < f4) {
            this.mViewPortX = 0;
            float f6 = i / f2;
            this.mViewPortScale = f6;
            this.mViewPortY = (int) ((i2 - (f6 * f)) / 2);
            return;
        }
        this.mViewPortX = 0;
        this.mViewPortY = 0;
        this.mViewPortScale = i / f2;
    }

    private final ArrayList<float[]> getPhotoMaskData(int trackFace) {
        ArrayList<float[]> arrayList = new ArrayList<>();
        for (int i = 0; i < trackFace; i++) {
            float[] data = DecimalUtils.copyArray(getMPosterController().getFaceRectData$fu_core_all_featureRelease(i, 0));
            Intrinsics.checkExpressionValueIsNotNull(data, "data");
            arrayList.add(convertFaceRect(data));
        }
        return arrayList;
    }

    private final float[] convertFaceRect(float[] faceRect) {
        float f = faceRect[0];
        float f2 = this.mViewPortScale;
        int i = this.mViewPortX;
        float f3 = faceRect[1] * f2;
        int i2 = this.mViewPortY;
        return new float[]{(f * f2) + i, f3 + i2, (faceRect[2] * f2) + i, (faceRect[3] * f2) + i2};
    }

    private final void doMerge() {
        if (this.hasPhotoDraw && this.hasTemplateDraw) {
            FURenderInputData fURenderInputData = new FURenderInputData(this.templateWidth, this.templateHeight);
            fURenderInputData.setTexture(new FURenderInputData.FUTexture(FUInputTextureEnum.FU_ADM_FLAG_COMMON_TEXTURE, this.photoTextureId));
            fURenderInputData.setImageBuffer(new FURenderInputData.FUImageBuffer(FUInputBufferEnum.FU_FORMAT_NV21_BUFFER, this.templateBytes, null, null, 12, null));
            fURenderInputData.setRenderConfig(new FURenderInputData.FURenderConfig(FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE, 0, 90, CameraFacingEnum.CAMERA_FRONT, FUTransformMatrixEnum.CCROT0_FLIPVERTICAL, FUTransformMatrixEnum.CCROT0_FLIPVERTICAL, false, false, false, 448, null));
            boolean z = false;
            int i = 0;
            while (true) {
                if (i > 50) {
                    break;
                }
                FURenderOutputData.FUTexture texture = FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().renderWithInput(fURenderInputData, 1).getTexture();
                this.mergeTexId = texture != null ? texture.getTexId() : 0;
                if (getMFUAIKit().isTracking() > 0) {
                    z = true;
                    break;
                }
                i++;
            }
            OnPosterRenderCallback onPosterRenderCallback = this.posterRenderCallback;
            if (onPosterRenderCallback != null) {
                onPosterRenderCallback.onMergeResult(z, this.mergeTexId);
            }
        }
    }

    public final void onDestroy() throws InterruptedException {
        this.photoBytes = null;
        this.photoRGBABytes = null;
        this.templateBytes = null;
        this.mViewPortScale = 1.0f;
        this.mViewPortX = 0;
        this.mViewPortY = 0;
        this.hasPhotoDraw = false;
        this.isNeedPhotoDraw = false;
        this.hasTemplateDraw = false;
        this.posterRenderCallback = null;
        int i = this.photoTextureId;
        if (i != 0) {
            GlUtil.deleteTextures(new int[]{i});
            this.photoTextureId = 0;
        }
        int i2 = this.mergeTexId;
        if (i2 != 0) {
            GlUtil.deleteTextures(new int[]{i2});
            this.mergeTexId = 0;
        }
        getMFUAIKit().faceProcessorSetDetectMode(FUFaceProcessorDetectModeEnum.VIDEO);
        getMFUAIKit().setHumanProcessorDetectMode(FUHumanProcessorDetectModeEnum.VIDEO);
        BaseSingleController.release$fu_core_all_featureRelease$default(FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMPosterController$fu_core_all_featureRelease(), null, 1, null);
    }

    private final void destroyPhotoTexture() {
        int i = this.photoTextureId;
        if (i != 0) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            this.photoTextureId = 0;
        }
    }
}
