package com.faceunity.core.renderer.texture;

import android.graphics.Bitmap;
import android.opengl.EGLConfig;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.entity.FURenderFrameData;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.entity.FURenderOutputData;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUInputTextureEnum;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.glview.GLTextureView;
import com.faceunity.core.listener.OnGlRendererListener;
import com.faceunity.core.program.ProgramTexture2d;
import com.faceunity.core.utils.GlUtil;
import com.faceunity.core.utils.ScreenUtils;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u009e\u0001\u001a\u00020\u001aH$J\n\u0010\u009f\u0001\u001a\u00030 \u0001H\u0002J\n\u0010¡\u0001\u001a\u00030 \u0001H\u0014J\n\u0010¢\u0001\u001a\u00030 \u0001H\u0004J%\u0010£\u0001\u001a\u00030 \u00012\u0007\u0010¤\u0001\u001a\u00020/2\u0007\u0010¥\u0001\u001a\u00020\b2\u0007\u0010¦\u0001\u001a\u00020\bH\u0002J\u0013\u0010§\u0001\u001a\u00030 \u00012\u0007\u0010¨\u0001\u001a\u00020fH\u0004J\n\u0010©\u0001\u001a\u00030 \u0001H$J\n\u0010ª\u0001\u001a\u00030 \u0001H\u0016J\u001d\u0010«\u0001\u001a\u00030 \u00012\u0007\u0010¬\u0001\u001a\u00020\u001a2\b\u0010\u00ad\u0001\u001a\u00030®\u0001H\u0014J\u001c\u0010¯\u0001\u001a\u00030 \u00012\u0007\u0010°\u0001\u001a\u00020/2\u0007\u0010±\u0001\u001a\u00020/H\u0016J\u0016\u0010²\u0001\u001a\u00030 \u00012\n\u0010³\u0001\u001a\u0005\u0018\u00010´\u0001H\u0016J\t\u0010µ\u0001\u001a\u000205H$J\u0011\u0010¶\u0001\u001a\u00030 \u00012\u0007\u0010·\u0001\u001a\u000205J\u0011\u0010¸\u0001\u001a\u00030 \u00012\u0007\u0010¹\u0001\u001a\u00020/J\u001c\u0010º\u0001\u001a\u00030 \u00012\u0007\u0010°\u0001\u001a\u00020/2\u0007\u0010±\u0001\u001a\u00020/H$J\u0016\u0010»\u0001\u001a\u00030 \u00012\n\u0010³\u0001\u001a\u0005\u0018\u00010´\u0001H$J\n\u0010¼\u0001\u001a\u00030 \u0001H\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\u000eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0011\u0010\u0013\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u001a\u0010\u0015\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\n\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\n\"\u0004\b'\u0010\u0018R\u001a\u0010(\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\n\"\u0004\b*\u0010\u0018R\u001a\u0010+\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\n\"\u0004\b-\u0010\u0018R\u001a\u0010.\u001a\u00020/X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u000205X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020;X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010@\u001a\u00020/X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u00101\"\u0004\bB\u00103R\u000e\u0010C\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001a\u0010M\u001a\u00020NX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u001a\u0010S\u001a\u00020TX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\u001a\u0010Y\u001a\u000205X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u00107\"\u0004\bZ\u00109R\u000e\u0010[\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010^\u001a\u00020_8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bb\u0010c\u001a\u0004\b`\u0010aR\u000e\u0010d\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010e\u001a\u0004\u0018\u00010fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010g\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\n\"\u0004\bi\u0010\u0018R\u001a\u0010j\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\n\"\u0004\bl\u0010\u0018R\u001a\u0010m\u001a\u00020/X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u00101\"\u0004\bo\u00103R\u001a\u0010p\u001a\u00020/X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u00101\"\u0004\br\u00103R\u001a\u0010s\u001a\u00020/X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u00101\"\u0004\bu\u00103R\u001c\u0010v\u001a\u0004\u0018\u00010wX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010y\"\u0004\bz\u0010{R\u001a\u0010|\u001a\u000205X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u00107\"\u0004\b~\u00109R\u001c\u0010\u007f\u001a\u00020\bX\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010\n\"\u0005\b\u0081\u0001\u0010\u0018R\u0016\u0010\u0082\u0001\u001a\u00020/X\u0084\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u00101R\u0016\u0010\u0084\u0001\u001a\u00020/X\u0084\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0085\u0001\u00101R\u0016\u0010\u0086\u0001\u001a\u00020/X\u0084\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u00101R\u0016\u0010\u0088\u0001\u001a\u00020/X\u0084\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u00101R\u0016\u0010\u008a\u0001\u001a\u00020/X\u0084\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u00101R\u001d\u0010\u008c\u0001\u001a\u00020/X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u00101\"\u0005\b\u008e\u0001\u00103R\u001d\u0010\u008f\u0001\u001a\u00020/X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u00101\"\u0005\b\u0091\u0001\u00103R\u001d\u0010\u0092\u0001\u001a\u00020/X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u00101\"\u0005\b\u0094\u0001\u00103R\u001d\u0010\u0095\u0001\u001a\u00020/X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0096\u0001\u00101\"\u0005\b\u0097\u0001\u00103R\u001d\u0010\u0098\u0001\u001a\u00020/X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0001\u00101\"\u0005\b\u009a\u0001\u00103R\u001d\u0010\u009b\u0001\u001a\u00020/X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009c\u0001\u00101\"\u0005\b\u009d\u0001\u00103¨\u0006½\u0001"}, m1293d2 = {"Lcom/faceunity/core/renderer/texture/BaseFUTextureRenderer;", "Lcom/faceunity/core/glview/GLTextureView$Renderer;", "gLTextureView", "Lcom/faceunity/core/glview/GLTextureView;", "glRendererListener", "Lcom/faceunity/core/listener/OnGlRendererListener;", "(Lcom/faceunity/core/glview/GLTextureView;Lcom/faceunity/core/listener/OnGlRendererListener;)V", "CAMERA_TEXTURE_MATRIX", "", "getCAMERA_TEXTURE_MATRIX", "()[F", "CAMERA_TEXTURE_MATRIX_BACK", "getCAMERA_TEXTURE_MATRIX_BACK", "TAG", "", "getTAG", "()Ljava/lang/String;", "TEXTURE_MATRIX", "getTEXTURE_MATRIX", "TEXTURE_MATRIX_CCRO_FLIPV_0", "getTEXTURE_MATRIX_CCRO_FLIPV_0", "currentFUMvpMatrix", "getCurrentFUMvpMatrix", "setCurrentFUMvpMatrix", "([F)V", "currentFURenderInputData", "Lcom/faceunity/core/entity/FURenderInputData;", "getCurrentFURenderInputData", "()Lcom/faceunity/core/entity/FURenderInputData;", "setCurrentFURenderInputData", "(Lcom/faceunity/core/entity/FURenderInputData;)V", "currentFURenderOutputData", "Lcom/faceunity/core/entity/FURenderOutputData;", "getCurrentFURenderOutputData", "()Lcom/faceunity/core/entity/FURenderOutputData;", "setCurrentFURenderOutputData", "(Lcom/faceunity/core/entity/FURenderOutputData;)V", "currentFUTexMatrix", "getCurrentFUTexMatrix", "setCurrentFUTexMatrix", "defaultFUMvpMatrix", "getDefaultFUMvpMatrix", "setDefaultFUMvpMatrix", "defaultFUTexMatrix", "getDefaultFUTexMatrix", "setDefaultFUTexMatrix", "deviceOrientation", "", "getDeviceOrientation", "()I", "setDeviceOrientation", "(I)V", "drawSmallViewport", "", "getDrawSmallViewport", "()Z", "setDrawSmallViewport", "(Z)V", "externalInputType", "Lcom/faceunity/core/enumeration/FUExternalInputEnum;", "getExternalInputType", "()Lcom/faceunity/core/enumeration/FUExternalInputEnum;", "setExternalInputType", "(Lcom/faceunity/core/enumeration/FUExternalInputEnum;)V", "faceUnity2DTexId", "getFaceUnity2DTexId", "setFaceUnity2DTexId", "frameCount", "frameFuRenderMinCount", "getGLTextureView", "()Lcom/faceunity/core/glview/GLTextureView;", "setGLTextureView", "(Lcom/faceunity/core/glview/GLTextureView;)V", "getGlRendererListener", "()Lcom/faceunity/core/listener/OnGlRendererListener;", "setGlRendererListener", "(Lcom/faceunity/core/listener/OnGlRendererListener;)V", "inputBufferType", "Lcom/faceunity/core/enumeration/FUInputBufferEnum;", "getInputBufferType", "()Lcom/faceunity/core/enumeration/FUInputBufferEnum;", "setInputBufferType", "(Lcom/faceunity/core/enumeration/FUInputBufferEnum;)V", "inputTextureType", "Lcom/faceunity/core/enumeration/FUInputTextureEnum;", "getInputTextureType", "()Lcom/faceunity/core/enumeration/FUInputTextureEnum;", "setInputTextureType", "(Lcom/faceunity/core/enumeration/FUInputTextureEnum;)V", "isActivityPause", "setActivityPause", "mBitmap2dTexId", "mBitmapMvpMatrix", "mBitmapTexMatrix", "mFURenderKit", "Lcom/faceunity/core/faceunity/FURenderKit;", "getMFURenderKit", "()Lcom/faceunity/core/faceunity/FURenderKit;", "mFURenderKit$delegate", "Lkotlin/Lazy;", "mIsBitmapPreview", "mShotBitmap", "Landroid/graphics/Bitmap;", "originMvpMatrix", "getOriginMvpMatrix", "setOriginMvpMatrix", "originTexMatrix", "getOriginTexMatrix", "setOriginTexMatrix", "originalHeight", "getOriginalHeight", "setOriginalHeight", "originalTextId", "getOriginalTextId", "setOriginalTextId", "originalWidth", "getOriginalWidth", "setOriginalWidth", "programTexture2d", "Lcom/faceunity/core/program/ProgramTexture2d;", "getProgramTexture2d", "()Lcom/faceunity/core/program/ProgramTexture2d;", "setProgramTexture2d", "(Lcom/faceunity/core/program/ProgramTexture2d;)V", "renderSwitch", "getRenderSwitch", "setRenderSwitch", "smallViewMatrix", "getSmallViewMatrix", "setSmallViewMatrix", "smallViewportBottomPadding", "getSmallViewportBottomPadding", "smallViewportHeight", "getSmallViewportHeight", "smallViewportHorizontalPadding", "getSmallViewportHorizontalPadding", "smallViewportTopPadding", "getSmallViewportTopPadding", "smallViewportWidth", "getSmallViewportWidth", "smallViewportX", "getSmallViewportX", "setSmallViewportX", "smallViewportY", "getSmallViewportY", "setSmallViewportY", "surfaceViewHeight", "getSurfaceViewHeight", "setSurfaceViewHeight", "surfaceViewWidth", "getSurfaceViewWidth", "setSurfaceViewWidth", "touchX", "getTouchX", "setTouchX", "touchY", "getTouchY", "setTouchY", "buildFURenderInputData", "deleteBitmapTexId", "", "destroyGlSurface", "dismissImageTexture", "drawBitmapFrame", "texId", "texMatrix", "mvpMatrix", "drawImageTexture", "bitmap", "drawRenderFrame", "onDrawFrame", "onRenderBefore", "input", "fuRenderFrameData", "Lcom/faceunity/core/entity/FURenderFrameData;", "onSurfaceChanged", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "onSurfaceCreated", "config", "Landroid/opengl/EGLConfig;", "prepareRender", "setFURenderSwitch", "isOpen", "setTransitionFrameCount", "count", "surfaceChanged", "surfaceCreated", "updateTexImage", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public abstract class BaseFUTextureRenderer implements GLTextureView.Renderer {
    private final float[] TEXTURE_MATRIX;
    private float[] currentFUMvpMatrix;
    private volatile FURenderOutputData currentFURenderOutputData;
    private float[] currentFUTexMatrix;
    private float[] defaultFUMvpMatrix;
    private float[] defaultFUTexMatrix;
    private boolean drawSmallViewport;
    private int faceUnity2DTexId;
    private int frameCount;
    private int frameFuRenderMinCount;
    private GLTextureView gLTextureView;
    private OnGlRendererListener glRendererListener;
    private boolean isActivityPause;
    private int mBitmap2dTexId;
    private float[] mBitmapMvpMatrix;
    private float[] mBitmapTexMatrix;
    private boolean mIsBitmapPreview;
    private Bitmap mShotBitmap;
    private float[] originMvpMatrix;
    private float[] originTexMatrix;
    private int originalHeight;
    private int originalTextId;
    private int originalWidth;
    private ProgramTexture2d programTexture2d;
    private volatile boolean renderSwitch;
    private float[] smallViewMatrix;
    private final int smallViewportBottomPadding;
    private final int smallViewportHeight;
    private final int smallViewportHorizontalPadding;
    private final int smallViewportTopPadding;
    private final int smallViewportWidth;
    private int smallViewportX;
    private int smallViewportY;
    private int touchX;
    private int touchY;
    private final String TAG = "KIT_BaseFURenderer";

    private final Lazy mFURenderKit = LazyKt.lazy(new Function0<FURenderKit>() {
        @Override
        public final FURenderKit invoke() {
            return FURenderKit.INSTANCE.getInstance();
        }
    });
    private final float[] CAMERA_TEXTURE_MATRIX = {0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    private final float[] CAMERA_TEXTURE_MATRIX_BACK = {0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    private final float[] TEXTURE_MATRIX_CCRO_FLIPV_0 = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private int surfaceViewWidth = 1;
    private int surfaceViewHeight = 1;
    private volatile FURenderInputData currentFURenderInputData = new FURenderInputData(0, 0);
    private FUExternalInputEnum externalInputType = FUExternalInputEnum.EXTERNAL_INPUT_TYPE_CAMERA;
    private FUInputTextureEnum inputTextureType = FUInputTextureEnum.FU_ADM_FLAG_COMMON_TEXTURE;
    private FUInputBufferEnum inputBufferType = FUInputBufferEnum.FU_FORMAT_NV21_BUFFER;
    private int deviceOrientation = 90;

    protected abstract FURenderInputData buildFURenderInputData();

    protected abstract void drawRenderFrame();

    protected final FURenderKit getMFURenderKit() {
        return (FURenderKit) this.mFURenderKit.getValue();
    }

    protected void onRenderBefore(FURenderInputData input, FURenderFrameData fuRenderFrameData) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        Intrinsics.checkParameterIsNotNull(fuRenderFrameData, "fuRenderFrameData");
    }

    protected abstract boolean prepareRender();

    protected abstract void surfaceChanged(int width, int height);

    protected abstract void surfaceCreated(EGLConfig config);

    protected void updateTexImage() {
    }

    public BaseFUTextureRenderer(GLTextureView gLTextureView, OnGlRendererListener onGlRendererListener) {
        this.gLTextureView = gLTextureView;
        this.glRendererListener = onGlRendererListener;
        float[] fArr = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        this.TEXTURE_MATRIX = fArr;
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        this.defaultFUTexMatrix = fArrCopyOf;
        float[] fArrCopyOf2 = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf2, "java.util.Arrays.copyOf(this, size)");
        this.defaultFUMvpMatrix = fArrCopyOf2;
        float[] fArrCopyOf3 = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf3, "java.util.Arrays.copyOf(this, size)");
        this.currentFUTexMatrix = fArrCopyOf3;
        float[] fArrCopyOf4 = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf4, "java.util.Arrays.copyOf(this, size)");
        this.currentFUMvpMatrix = fArrCopyOf4;
        float[] fArrCopyOf5 = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf5, "java.util.Arrays.copyOf(this, size)");
        this.originTexMatrix = fArrCopyOf5;
        float[] fArrCopyOf6 = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf6, "java.util.Arrays.copyOf(this, size)");
        this.originMvpMatrix = fArrCopyOf6;
        float[] fArrCopyOf7 = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf7, "java.util.Arrays.copyOf(this, size)");
        this.smallViewMatrix = fArrCopyOf7;
        this.renderSwitch = true;
        this.smallViewportWidth = ScreenUtils.INSTANCE.dip2px(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), 90);
        this.smallViewportHeight = ScreenUtils.INSTANCE.dip2px(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), 160);
        this.smallViewportHorizontalPadding = ScreenUtils.INSTANCE.dip2px(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), 16);
        this.smallViewportTopPadding = ScreenUtils.INSTANCE.dip2px(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), 88);
        this.smallViewportBottomPadding = ScreenUtils.INSTANCE.dip2px(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), 100);
        float[] fArrCopyOf8 = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf8, "java.util.Arrays.copyOf(this, size)");
        this.mBitmapMvpMatrix = fArrCopyOf8;
        float[] fArrCopyOf9 = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf9, "java.util.Arrays.copyOf(this, size)");
        this.mBitmapTexMatrix = fArrCopyOf9;
    }

    protected final GLTextureView getGLTextureView() {
        return this.gLTextureView;
    }

    protected final OnGlRendererListener getGlRendererListener() {
        return this.glRendererListener;
    }

    protected final void setGLTextureView(GLTextureView gLTextureView) {
        this.gLTextureView = gLTextureView;
    }

    protected final void setGlRendererListener(OnGlRendererListener onGlRendererListener) {
        this.glRendererListener = onGlRendererListener;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final float[] getTEXTURE_MATRIX() {
        return this.TEXTURE_MATRIX;
    }

    public final float[] getCAMERA_TEXTURE_MATRIX() {
        return this.CAMERA_TEXTURE_MATRIX;
    }

    public final float[] getCAMERA_TEXTURE_MATRIX_BACK() {
        return this.CAMERA_TEXTURE_MATRIX_BACK;
    }

    public final float[] getTEXTURE_MATRIX_CCRO_FLIPV_0() {
        return this.TEXTURE_MATRIX_CCRO_FLIPV_0;
    }

    protected final ProgramTexture2d getProgramTexture2d() {
        return this.programTexture2d;
    }

    protected final void setProgramTexture2d(ProgramTexture2d programTexture2d) {
        this.programTexture2d = programTexture2d;
    }

    protected final int getSurfaceViewWidth() {
        return this.surfaceViewWidth;
    }

    protected final void setSurfaceViewWidth(int i) {
        this.surfaceViewWidth = i;
    }

    protected final int getSurfaceViewHeight() {
        return this.surfaceViewHeight;
    }

    protected final void setSurfaceViewHeight(int i) {
        this.surfaceViewHeight = i;
    }

    protected final FURenderInputData getCurrentFURenderInputData() {
        return this.currentFURenderInputData;
    }

    protected final void setCurrentFURenderInputData(FURenderInputData fURenderInputData) {
        Intrinsics.checkParameterIsNotNull(fURenderInputData, "<set-?>");
        this.currentFURenderInputData = fURenderInputData;
    }

    protected final int getOriginalTextId() {
        return this.originalTextId;
    }

    protected final void setOriginalTextId(int i) {
        this.originalTextId = i;
    }

    protected final int getOriginalWidth() {
        return this.originalWidth;
    }

    protected final void setOriginalWidth(int i) {
        this.originalWidth = i;
    }

    protected final int getOriginalHeight() {
        return this.originalHeight;
    }

    protected final void setOriginalHeight(int i) {
        this.originalHeight = i;
    }

    protected final FUExternalInputEnum getExternalInputType() {
        return this.externalInputType;
    }

    protected final void setExternalInputType(FUExternalInputEnum fUExternalInputEnum) {
        Intrinsics.checkParameterIsNotNull(fUExternalInputEnum, "<set-?>");
        this.externalInputType = fUExternalInputEnum;
    }

    protected final FUInputTextureEnum getInputTextureType() {
        return this.inputTextureType;
    }

    protected final void setInputTextureType(FUInputTextureEnum fUInputTextureEnum) {
        Intrinsics.checkParameterIsNotNull(fUInputTextureEnum, "<set-?>");
        this.inputTextureType = fUInputTextureEnum;
    }

    protected final FUInputBufferEnum getInputBufferType() {
        return this.inputBufferType;
    }

    protected final void setInputBufferType(FUInputBufferEnum fUInputBufferEnum) {
        Intrinsics.checkParameterIsNotNull(fUInputBufferEnum, "<set-?>");
        this.inputBufferType = fUInputBufferEnum;
    }

    protected final int getDeviceOrientation() {
        return this.deviceOrientation;
    }

    protected final void setDeviceOrientation(int i) {
        this.deviceOrientation = i;
    }

    protected final int getFaceUnity2DTexId() {
        return this.faceUnity2DTexId;
    }

    protected final void setFaceUnity2DTexId(int i) {
        this.faceUnity2DTexId = i;
    }

    protected final FURenderOutputData getCurrentFURenderOutputData() {
        return this.currentFURenderOutputData;
    }

    protected final void setCurrentFURenderOutputData(FURenderOutputData fURenderOutputData) {
        this.currentFURenderOutputData = fURenderOutputData;
    }

    protected final float[] getDefaultFUTexMatrix() {
        return this.defaultFUTexMatrix;
    }

    protected final void setDefaultFUTexMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.defaultFUTexMatrix = fArr;
    }

    protected final float[] getDefaultFUMvpMatrix() {
        return this.defaultFUMvpMatrix;
    }

    protected final void setDefaultFUMvpMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.defaultFUMvpMatrix = fArr;
    }

    protected final float[] getCurrentFUTexMatrix() {
        return this.currentFUTexMatrix;
    }

    protected final void setCurrentFUTexMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.currentFUTexMatrix = fArr;
    }

    protected final float[] getCurrentFUMvpMatrix() {
        return this.currentFUMvpMatrix;
    }

    protected final void setCurrentFUMvpMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.currentFUMvpMatrix = fArr;
    }

    protected final float[] getOriginTexMatrix() {
        return this.originTexMatrix;
    }

    protected final void setOriginTexMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.originTexMatrix = fArr;
    }

    protected final float[] getOriginMvpMatrix() {
        return this.originMvpMatrix;
    }

    protected final void setOriginMvpMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.originMvpMatrix = fArr;
    }

    protected final float[] getSmallViewMatrix() {
        return this.smallViewMatrix;
    }

    protected final void setSmallViewMatrix(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.smallViewMatrix = fArr;
    }

    protected final boolean getRenderSwitch() {
        return this.renderSwitch;
    }

    protected final void setRenderSwitch(boolean z) {
        this.renderSwitch = z;
    }

    protected final boolean getIsActivityPause() {
        return this.isActivityPause;
    }

    protected final void setActivityPause(boolean z) {
        this.isActivityPause = z;
    }

    protected final boolean getDrawSmallViewport() {
        return this.drawSmallViewport;
    }

    protected final void setDrawSmallViewport(boolean z) {
        this.drawSmallViewport = z;
    }

    protected final int getSmallViewportWidth() {
        return this.smallViewportWidth;
    }

    protected final int getSmallViewportHeight() {
        return this.smallViewportHeight;
    }

    protected final int getSmallViewportX() {
        return this.smallViewportX;
    }

    protected final void setSmallViewportX(int i) {
        this.smallViewportX = i;
    }

    protected final int getSmallViewportY() {
        return this.smallViewportY;
    }

    protected final void setSmallViewportY(int i) {
        this.smallViewportY = i;
    }

    protected final int getSmallViewportHorizontalPadding() {
        return this.smallViewportHorizontalPadding;
    }

    protected final int getSmallViewportTopPadding() {
        return this.smallViewportTopPadding;
    }

    protected final int getSmallViewportBottomPadding() {
        return this.smallViewportBottomPadding;
    }

    protected final int getTouchX() {
        return this.touchX;
    }

    protected final void setTouchX(int i) {
        this.touchX = i;
    }

    protected final int getTouchY() {
        return this.touchY;
    }

    protected final void setTouchY(int i) {
        this.touchY = i;
    }

    public final void setFURenderSwitch(boolean isOpen) {
        GLTextureView gLTextureView;
        if (!isOpen && (gLTextureView = this.gLTextureView) != null) {
            gLTextureView.queueEvent(new Runnable() {
                @Override
                public final void run() {
                    BaseFUTextureRenderer.this.getMFURenderKit().clearCacheResource();
                }
            });
        }
        this.renderSwitch = isOpen;
    }

    public final void setTransitionFrameCount(int count) {
        this.frameFuRenderMinCount = count;
    }

    @Override
    public void onSurfaceCreated(EGLConfig config) {
        GlUtil.logVersionInfo();
        this.programTexture2d = new ProgramTexture2d();
        this.frameCount = 0;
        surfaceCreated(config);
        OnGlRendererListener onGlRendererListener = this.glRendererListener;
        if (onGlRendererListener != null) {
            onGlRendererListener.onSurfaceCreated();
        }
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        if (this.surfaceViewWidth != width || this.surfaceViewHeight != height) {
            this.surfaceViewWidth = width;
            this.surfaceViewHeight = height;
            surfaceChanged(width, height);
        }
        this.smallViewportX = (width - this.smallViewportWidth) - this.smallViewportHorizontalPadding;
        this.smallViewportY = this.smallViewportBottomPadding;
        OnGlRendererListener onGlRendererListener = this.glRendererListener;
        if (onGlRendererListener != null) {
            onGlRendererListener.onSurfaceChanged(width, height);
        }
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDrawFrame() {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.faceunity.core.renderer.texture.BaseFUTextureRenderer.onDrawFrame():void");
    }

    private final void drawBitmapFrame(int texId, float[] texMatrix, float[] mvpMatrix) {
        if (this.mBitmap2dTexId > 0) {
            GLES20.glClear(16640);
            ProgramTexture2d programTexture2d = this.programTexture2d;
            if (programTexture2d != null) {
                programTexture2d.drawFrame(texId, texMatrix, mvpMatrix);
            }
        }
    }

    protected final void drawImageTexture(final Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        this.mIsBitmapPreview = true;
        this.mShotBitmap = bitmap;
        GLTextureView gLTextureView = this.gLTextureView;
        if (gLTextureView != null) {
            gLTextureView.queueEvent(new Runnable() {
                @Override
                public final void run() {
                    BaseFUTextureRenderer.this.deleteBitmapTexId();
                    BaseFUTextureRenderer.this.mBitmap2dTexId = GlUtil.createImageTexture(bitmap);
                    BaseFUTextureRenderer baseFUTextureRenderer = BaseFUTextureRenderer.this;
                    float[] fArrChangeMvpMatrixCrop = GlUtil.changeMvpMatrixCrop(baseFUTextureRenderer.getSurfaceViewWidth(), BaseFUTextureRenderer.this.getSurfaceViewHeight(), bitmap.getWidth(), bitmap.getHeight());
                    Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixCrop, "GlUtil.changeMvpMatrixCr… bitmap.height.toFloat())");
                    baseFUTextureRenderer.mBitmapMvpMatrix = fArrChangeMvpMatrixCrop;
                    Matrix.scaleM(BaseFUTextureRenderer.this.mBitmapMvpMatrix, 0, 1.0f, -1.0f, 1.0f);
                }
            });
        }
        GLTextureView gLTextureView2 = this.gLTextureView;
        if (gLTextureView2 != null) {
            gLTextureView2.requestRender();
        }
    }

    protected final void dismissImageTexture() {
        this.mShotBitmap = null;
        this.mIsBitmapPreview = false;
        GLTextureView gLTextureView = this.gLTextureView;
        if (gLTextureView != null) {
            gLTextureView.queueEvent(new Runnable() {
                @Override
                public final void run() {
                    BaseFUTextureRenderer.this.deleteBitmapTexId();
                }
            });
        }
        GLTextureView gLTextureView2 = this.gLTextureView;
        if (gLTextureView2 != null) {
            gLTextureView2.requestRender();
        }
    }

    public final void deleteBitmapTexId() {
        int i = this.mBitmap2dTexId;
        if (i > 0) {
            GlUtil.deleteTextures(new int[]{i});
            this.mBitmap2dTexId = 0;
        }
    }

    protected void destroyGlSurface() {
        deleteBitmapTexId();
        int i = this.originalTextId;
        if (i != 0) {
            GlUtil.deleteTextures(new int[]{i});
            this.originalTextId = 0;
        }
        int i2 = this.faceUnity2DTexId;
        if (i2 != 0) {
            GlUtil.deleteTextures(new int[]{i2});
            this.faceUnity2DTexId = 0;
        }
        ProgramTexture2d programTexture2d = this.programTexture2d;
        if (programTexture2d != null) {
            programTexture2d.release();
            this.programTexture2d = null;
        }
        OnGlRendererListener onGlRendererListener = this.glRendererListener;
        if (onGlRendererListener != null) {
            onGlRendererListener.onSurfaceDestroy();
        }
    }
}
