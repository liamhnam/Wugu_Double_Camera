package com.faceunity.core.model.facebeauty;

import android.util.Log;
import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.controller.facebeauty.FaceBeautyParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.enumeration.FUFaceBeautyMultiModePropertyEnum;
import com.faceunity.core.enumeration.FUFaceBeautyPropertyModeEnum;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b6\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\bI\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010Ê\u0001\u001a\u00030Ë\u00012\b\u0010Ì\u0001\u001a\u00030Í\u00012\u0007\u0010Î\u0001\u001a\u00020EJ\u0017\u0010Ï\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0005\u0012\u00030Ñ\u00010Ð\u0001H\u0014J\n\u0010Ò\u0001\u001a\u00030\u009a\u0001H\u0014J\u0012\u0010Ó\u0001\u001a\u00030Ë\u00012\b\u0010Ì\u0001\u001a\u00030Í\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR$\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\rR$\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR$\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\rR$\u0010 \u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000b\"\u0004\b\"\u0010\rR$\u0010#\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000b\"\u0004\b%\u0010\rR$\u0010&\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u000b\"\u0004\b(\u0010\rR$\u0010)\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u000b\"\u0004\b+\u0010\rR$\u0010,\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u000b\"\u0004\b.\u0010\rR&\u0010/\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u000b\"\u0004\b1\u0010\rR$\u00102\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u000b\"\u0004\b4\u0010\rR$\u00105\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u000b\"\u0004\b7\u0010\rR&\u00108\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u000b\"\u0004\b:\u0010\rR$\u0010;\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u000b\"\u0004\b=\u0010\rR$\u0010>\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u000b\"\u0004\b@\u0010\rR$\u0010A\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u000b\"\u0004\bC\u0010\rR\u000e\u0010D\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010F\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u000b\"\u0004\bH\u0010\rR$\u0010I\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u000b\"\u0004\bK\u0010\rR\u000e\u0010L\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010M\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010\u000b\"\u0004\bO\u0010\rR$\u0010Q\u001a\u00020P2\u0006\u0010\u0007\u001a\u00020P@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR$\u0010V\u001a\u00020P2\u0006\u0010\u0007\u001a\u00020P@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010S\"\u0004\bX\u0010UR$\u0010Y\u001a\u00020P2\u0006\u0010\u0007\u001a\u00020P@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010S\"\u0004\b[\u0010UR$\u0010\\\u001a\u00020P2\u0006\u0010\u0007\u001a\u00020P@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010S\"\u0004\b^\u0010UR$\u0010_\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010\u000b\"\u0004\ba\u0010\rR$\u0010b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010\u000b\"\u0004\bd\u0010\rR$\u0010e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010\u000b\"\u0004\bg\u0010\rR&\u0010h\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u000b\"\u0004\bj\u0010\rR\u000e\u0010k\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010l\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u000b\"\u0004\bn\u0010\rR$\u0010o\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\u000b\"\u0004\bq\u0010\rR$\u0010r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\u000b\"\u0004\bt\u0010\rR$\u0010u\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010\u000b\"\u0004\bw\u0010\rR$\u0010x\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010\u0011\"\u0004\bz\u0010\u0013R$\u0010{\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010\u000b\"\u0004\b}\u0010\rR%\u0010~\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010\u000b\"\u0005\b\u0080\u0001\u0010\rR'\u0010\u0081\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u000b\"\u0005\b\u0083\u0001\u0010\rR)\u0010\u0084\u0001\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001\"\u0006\b\u0087\u0001\u0010\u0088\u0001R'\u0010\u0089\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u0010\u000b\"\u0005\b\u008b\u0001\u0010\rR)\u0010\u008c\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u0010\u000b\"\u0005\b\u008e\u0001\u0010\rR\u000f\u0010\u008f\u0001\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R'\u0010\u0090\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010\u000b\"\u0005\b\u0092\u0001\u0010\rR'\u0010\u0093\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u000b\"\u0005\b\u0095\u0001\u0010\rR'\u0010\u0096\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u000b\"\u0005\b\u0098\u0001\u0010\rR!\u0010\u0099\u0001\u001a\u00030\u009a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u009d\u0001\u0010\u009e\u0001\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R'\u0010\u009f\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b \u0001\u0010\u000b\"\u0005\b¡\u0001\u0010\rR)\u0010¢\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b£\u0001\u0010\u000b\"\u0005\b¤\u0001\u0010\rR\u000f\u0010¥\u0001\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010¦\u0001\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R'\u0010§\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¨\u0001\u0010\u000b\"\u0005\b©\u0001\u0010\rR'\u0010ª\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b«\u0001\u0010\u000b\"\u0005\b¬\u0001\u0010\rR)\u0010\u00ad\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b®\u0001\u0010\u000b\"\u0005\b¯\u0001\u0010\rR'\u0010°\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b±\u0001\u0010\u000b\"\u0005\b²\u0001\u0010\rR'\u0010³\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b´\u0001\u0010\u000b\"\u0005\bµ\u0001\u0010\rR'\u0010¶\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b·\u0001\u0010\u000b\"\u0005\b¸\u0001\u0010\rR\u000f\u0010¹\u0001\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R'\u0010º\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b»\u0001\u0010\u000b\"\u0005\b¼\u0001\u0010\rR\u000f\u0010½\u0001\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R'\u0010¾\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¿\u0001\u0010\u000b\"\u0005\bÀ\u0001\u0010\rR\u000f\u0010Á\u0001\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R'\u0010Â\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÃ\u0001\u0010\u000b\"\u0005\bÄ\u0001\u0010\rR\u000f\u0010Å\u0001\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010Æ\u0001\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R'\u0010Ç\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÈ\u0001\u0010\u000b\"\u0005\bÉ\u0001\u0010\r¨\u0006Ô\u0001"}, m1293d2 = {"Lcom/faceunity/core/model/facebeauty/FaceBeauty;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "TAG", "", "value", "", "blurIntensity", "getBlurIntensity", "()D", "setBlurIntensity", "(D)V", "", "blurType", "getBlurType", "()I", "setBlurType", "(I)V", "browHeightIntensity", "getBrowHeightIntensity", "setBrowHeightIntensity", "browSpaceIntensity", "getBrowSpaceIntensity", "setBrowSpaceIntensity", "browThickIntensity", "getBrowThickIntensity", "setBrowThickIntensity", "canthusIntensity", "getCanthusIntensity", "setCanthusIntensity", "changeFramesIntensity", "getChangeFramesIntensity", "setChangeFramesIntensity", "cheekBonesIntensity", "getCheekBonesIntensity", "setCheekBonesIntensity", "cheekCircleIntensity", "getCheekCircleIntensity", "setCheekCircleIntensity", "cheekLongIntensity", "getCheekLongIntensity", "setCheekLongIntensity", "cheekNarrowIntensity", "getCheekNarrowIntensity", "setCheekNarrowIntensity", "cheekNarrowIntensityV2", "getCheekNarrowIntensityV2", "setCheekNarrowIntensityV2", "cheekShortIntensity", "getCheekShortIntensity", "setCheekShortIntensity", "cheekSmallIntensity", "getCheekSmallIntensity", "setCheekSmallIntensity", "cheekSmallIntensityV2", "getCheekSmallIntensityV2", "setCheekSmallIntensityV2", "cheekThinningIntensity", "getCheekThinningIntensity", "setCheekThinningIntensity", "cheekVIntensity", "getCheekVIntensity", "setCheekVIntensity", "chinIntensity", "getChinIntensity", "setChinIntensity", "chinMode", "Lcom/faceunity/core/enumeration/FUFaceBeautyPropertyModeEnum;", "clarityIntensity", "getClarityIntensity", "setClarityIntensity", "colorIntensity", "getColorIntensity", "setColorIntensity", "colorMode", "delspotIntensity", "getDelspotIntensity", "setDelspotIntensity", "", "enableBlurUseMask", "getEnableBlurUseMask", "()Z", "setEnableBlurUseMask", "(Z)V", "enableHeavyBlur", "getEnableHeavyBlur", "setEnableHeavyBlur", "enableSkinDetect", "getEnableSkinDetect", "setEnableSkinDetect", "enableSkinSeg", "getEnableSkinSeg", "setEnableSkinSeg", "eyeBrightIntensity", "getEyeBrightIntensity", "setEyeBrightIntensity", "eyeCircleIntensity", "getEyeCircleIntensity", "setEyeCircleIntensity", "eyeEnlargingIntensity", "getEyeEnlargingIntensity", "setEyeEnlargingIntensity", "eyeEnlargingIntensityV2", "getEyeEnlargingIntensityV2", "setEyeEnlargingIntensityV2", "eyeEnlargingMode", "eyeHeightIntensity", "getEyeHeightIntensity", "setEyeHeightIntensity", "eyeLidIntensity", "getEyeLidIntensity", "setEyeLidIntensity", "eyeRotateIntensity", "getEyeRotateIntensity", "setEyeRotateIntensity", "eyeSpaceIntensity", "getEyeSpaceIntensity", "setEyeSpaceIntensity", "faceShape", "getFaceShape", "setFaceShape", "faceShapeIntensity", "getFaceShapeIntensity", "setFaceShapeIntensity", "faceThreeIntensity", "getFaceThreeIntensity", "setFaceThreeIntensity", "filterIntensity", "getFilterIntensity", "setFilterIntensity", "filterName", "getFilterName", "()Ljava/lang/String;", "setFilterName", "(Ljava/lang/String;)V", "forHeadIntensity", "getForHeadIntensity", "setForHeadIntensity", "forHeadIntensityV2", "getForHeadIntensityV2", "setForHeadIntensityV2", "forHeadMode", "lipThickIntensity", "getLipThickIntensity", "setLipThickIntensity", "longNoseIntensity", "getLongNoseIntensity", "setLongNoseIntensity", "lowerJawIntensity", "getLowerJawIntensity", "setLowerJawIntensity", "mFaceBeautyController", "Lcom/faceunity/core/controller/facebeauty/FaceBeautyController;", "getMFaceBeautyController", "()Lcom/faceunity/core/controller/facebeauty/FaceBeautyController;", "mFaceBeautyController$delegate", "Lkotlin/Lazy;", "mouthIntensity", "getMouthIntensity", "setMouthIntensity", "mouthIntensityV2", "getMouthIntensityV2", "setMouthIntensityV2", "mouthMode", "narrowFaceMode", "nonSkinBlurIntensity", "getNonSkinBlurIntensity", "setNonSkinBlurIntensity", "noseIntensity", "getNoseIntensity", "setNoseIntensity", "noseIntensityV2", "getNoseIntensityV2", "setNoseIntensityV2", "philtrumIntensity", "getPhiltrumIntensity", "setPhiltrumIntensity", "redIntensity", "getRedIntensity", "setRedIntensity", "removeLawPatternIntensity", "getRemoveLawPatternIntensity", "setRemoveLawPatternIntensity", "removeLawPatternMode", "removePouchIntensity", "getRemovePouchIntensity", "setRemovePouchIntensity", "removePouchMode", "sharpenIntensity", "getSharpenIntensity", "setSharpenIntensity", "smallFaceMode", "smileIntensity", "getSmileIntensity", "setSmileIntensity", "thinNoseMode", "thinningFaceMode", "toothIntensity", "getToothIntensity", "setToothIntensity", "addPropertyMode", "", "fuFaceBeautyMultiModePropertyEnum", "Lcom/faceunity/core/enumeration/FUFaceBeautyMultiModePropertyEnum;", "modeEnum", "buildParams", "Ljava/util/LinkedHashMap;", "", "getModelController", "removePropertyMode", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FaceBeauty extends BaseSingleModel {
    private final String TAG;
    private double blurIntensity;
    private int blurType;
    private double browHeightIntensity;
    private double browSpaceIntensity;
    private double browThickIntensity;
    private double canthusIntensity;
    private double changeFramesIntensity;
    private double cheekBonesIntensity;
    private double cheekCircleIntensity;
    private double cheekLongIntensity;
    private double cheekNarrowIntensity;

    @Deprecated
    private double cheekNarrowIntensityV2;
    private double cheekShortIntensity;
    private double cheekSmallIntensity;

    @Deprecated
    private double cheekSmallIntensityV2;
    private double cheekThinningIntensity;
    private double cheekVIntensity;
    private double chinIntensity;
    private FUFaceBeautyPropertyModeEnum chinMode;
    private double clarityIntensity;
    private double colorIntensity;
    private FUFaceBeautyPropertyModeEnum colorMode;
    private double delspotIntensity;
    private boolean enableBlurUseMask;
    private boolean enableHeavyBlur;
    private boolean enableSkinDetect;
    private boolean enableSkinSeg;
    private double eyeBrightIntensity;
    private double eyeCircleIntensity;
    private double eyeEnlargingIntensity;

    @Deprecated
    private double eyeEnlargingIntensityV2;
    private FUFaceBeautyPropertyModeEnum eyeEnlargingMode;
    private double eyeHeightIntensity;
    private double eyeLidIntensity;
    private double eyeRotateIntensity;
    private double eyeSpaceIntensity;
    private int faceShape;
    private double faceShapeIntensity;
    private double faceThreeIntensity;
    private double filterIntensity;
    private String filterName;
    private double forHeadIntensity;

    @Deprecated
    private double forHeadIntensityV2;
    private FUFaceBeautyPropertyModeEnum forHeadMode;
    private double lipThickIntensity;
    private double longNoseIntensity;
    private double lowerJawIntensity;

    private final Lazy mFaceBeautyController;
    private double mouthIntensity;

    @Deprecated
    private double mouthIntensityV2;
    private FUFaceBeautyPropertyModeEnum mouthMode;
    private FUFaceBeautyPropertyModeEnum narrowFaceMode;
    private double nonSkinBlurIntensity;
    private double noseIntensity;

    @Deprecated
    private double noseIntensityV2;
    private double philtrumIntensity;
    private double redIntensity;
    private double removeLawPatternIntensity;
    private FUFaceBeautyPropertyModeEnum removeLawPatternMode;
    private double removePouchIntensity;
    private FUFaceBeautyPropertyModeEnum removePouchMode;
    private double sharpenIntensity;
    private FUFaceBeautyPropertyModeEnum smallFaceMode;
    private double smileIntensity;
    private FUFaceBeautyPropertyModeEnum thinNoseMode;
    private FUFaceBeautyPropertyModeEnum thinningFaceMode;
    private double toothIntensity;

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 1, 16})
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;
        public static final int[] $EnumSwitchMapping$10;
        public static final int[] $EnumSwitchMapping$11;
        public static final int[] $EnumSwitchMapping$12;
        public static final int[] $EnumSwitchMapping$13;
        public static final int[] $EnumSwitchMapping$14;
        public static final int[] $EnumSwitchMapping$15;
        public static final int[] $EnumSwitchMapping$16;
        public static final int[] $EnumSwitchMapping$17;
        public static final int[] $EnumSwitchMapping$18;
        public static final int[] $EnumSwitchMapping$19;
        public static final int[] $EnumSwitchMapping$2;
        public static final int[] $EnumSwitchMapping$20;
        public static final int[] $EnumSwitchMapping$21;
        public static final int[] $EnumSwitchMapping$22;
        public static final int[] $EnumSwitchMapping$23;
        public static final int[] $EnumSwitchMapping$3;
        public static final int[] $EnumSwitchMapping$4;
        public static final int[] $EnumSwitchMapping$5;
        public static final int[] $EnumSwitchMapping$6;
        public static final int[] $EnumSwitchMapping$7;
        public static final int[] $EnumSwitchMapping$8;
        public static final int[] $EnumSwitchMapping$9;

        static {
            int[] iArr = new int[FUFaceBeautyMultiModePropertyEnum.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[FUFaceBeautyMultiModePropertyEnum.COLOR_INTENSITY.ordinal()] = 1;
            iArr[FUFaceBeautyMultiModePropertyEnum.REMOVE_POUCH_INTENSITY.ordinal()] = 2;
            iArr[FUFaceBeautyMultiModePropertyEnum.REMOVE_NASOLABIAL_FOLDS_INTENSITY.ordinal()] = 3;
            iArr[FUFaceBeautyMultiModePropertyEnum.CHEEK_THINNING_INTENSITY.ordinal()] = 4;
            iArr[FUFaceBeautyMultiModePropertyEnum.CHEEK_NARROW_INTENSITY.ordinal()] = 5;
            iArr[FUFaceBeautyMultiModePropertyEnum.CHEEK_SMALL_INTENSITY.ordinal()] = 6;
            iArr[FUFaceBeautyMultiModePropertyEnum.EYE_ENLARGING_INTENSITY.ordinal()] = 7;
            iArr[FUFaceBeautyMultiModePropertyEnum.CHIN_INTENSITY.ordinal()] = 8;
            iArr[FUFaceBeautyMultiModePropertyEnum.FOREHEAD_INTENSITY.ordinal()] = 9;
            iArr[FUFaceBeautyMultiModePropertyEnum.NOSE_INTENSITY.ordinal()] = 10;
            iArr[FUFaceBeautyMultiModePropertyEnum.MOUTH_INTENSITY.ordinal()] = 11;
            int[] iArr2 = new int[FUFaceBeautyMultiModePropertyEnum.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[FUFaceBeautyMultiModePropertyEnum.COLOR_INTENSITY.ordinal()] = 1;
            iArr2[FUFaceBeautyMultiModePropertyEnum.REMOVE_POUCH_INTENSITY.ordinal()] = 2;
            iArr2[FUFaceBeautyMultiModePropertyEnum.REMOVE_NASOLABIAL_FOLDS_INTENSITY.ordinal()] = 3;
            iArr2[FUFaceBeautyMultiModePropertyEnum.CHEEK_THINNING_INTENSITY.ordinal()] = 4;
            iArr2[FUFaceBeautyMultiModePropertyEnum.CHEEK_NARROW_INTENSITY.ordinal()] = 5;
            iArr2[FUFaceBeautyMultiModePropertyEnum.CHEEK_SMALL_INTENSITY.ordinal()] = 6;
            iArr2[FUFaceBeautyMultiModePropertyEnum.EYE_ENLARGING_INTENSITY.ordinal()] = 7;
            iArr2[FUFaceBeautyMultiModePropertyEnum.CHIN_INTENSITY.ordinal()] = 8;
            iArr2[FUFaceBeautyMultiModePropertyEnum.FOREHEAD_INTENSITY.ordinal()] = 9;
            iArr2[FUFaceBeautyMultiModePropertyEnum.NOSE_INTENSITY.ordinal()] = 10;
            iArr2[FUFaceBeautyMultiModePropertyEnum.MOUTH_INTENSITY.ordinal()] = 11;
            int[] iArr3 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr3[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr4 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$3 = iArr4;
            iArr4[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr4[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr5 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$4 = iArr5;
            iArr5[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr5[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr6 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$5 = iArr6;
            iArr6[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr6[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr7 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$6 = iArr7;
            iArr7[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr7[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr8 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$7 = iArr8;
            iArr8[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr8[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr9 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$8 = iArr9;
            iArr9[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr9[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            iArr9[FUFaceBeautyPropertyModeEnum.MODE3.ordinal()] = 3;
            int[] iArr10 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$9 = iArr10;
            iArr10[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr10[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr11 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$10 = iArr11;
            iArr11[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr11[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr12 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$11 = iArr12;
            iArr12[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr12[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr13 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$12 = iArr13;
            iArr13[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr13[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            iArr13[FUFaceBeautyPropertyModeEnum.MODE3.ordinal()] = 3;
            int[] iArr14 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$13 = iArr14;
            iArr14[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr14[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr15 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$14 = iArr15;
            iArr15[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr15[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr16 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$15 = iArr16;
            iArr16[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr16[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr17 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$16 = iArr17;
            iArr17[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr17[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr18 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$17 = iArr18;
            iArr18[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr18[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr19 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$18 = iArr19;
            iArr19[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr19[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr20 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$19 = iArr20;
            iArr20[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr20[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            iArr20[FUFaceBeautyPropertyModeEnum.MODE3.ordinal()] = 3;
            int[] iArr21 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$20 = iArr21;
            iArr21[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr21[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr22 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$21 = iArr22;
            iArr22[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr22[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr23 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$22 = iArr23;
            iArr23[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr23[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            int[] iArr24 = new int[FUFaceBeautyPropertyModeEnum.values().length];
            $EnumSwitchMapping$23 = iArr24;
            iArr24[FUFaceBeautyPropertyModeEnum.MODE1.ordinal()] = 1;
            iArr24[FUFaceBeautyPropertyModeEnum.MODE2.ordinal()] = 2;
            iArr24[FUFaceBeautyPropertyModeEnum.MODE3.ordinal()] = 3;
        }
    }

    private final FaceBeautyController getMFaceBeautyController() {
        return (FaceBeautyController) this.mFaceBeautyController.getValue();
    }

    public FaceBeauty(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.TAG = "FaceBeauty";
        this.mFaceBeautyController = LazyKt.lazy(new Function0<FaceBeautyController>() {
            @Override
            public final FaceBeautyController invoke() {
                return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMFaceBeautyController$fu_core_all_featureRelease();
            }
        });
        this.colorMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.removePouchMode = FUFaceBeautyPropertyModeEnum.MODE1;
        this.removeLawPatternMode = FUFaceBeautyPropertyModeEnum.MODE1;
        this.thinningFaceMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.narrowFaceMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.smallFaceMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.eyeEnlargingMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.chinMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.forHeadMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.thinNoseMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.mouthMode = FUFaceBeautyPropertyModeEnum.MODE2;
        this.filterName = "origin";
        this.blurType = 2;
        this.faceShape = 4;
        this.faceShapeIntensity = 1.0d;
        this.chinIntensity = 0.5d;
        this.forHeadIntensity = 0.5d;
        this.forHeadIntensityV2 = 0.5d;
        this.mouthIntensity = 0.5d;
        this.mouthIntensityV2 = 0.5d;
        this.eyeSpaceIntensity = 0.5d;
        this.eyeRotateIntensity = 0.5d;
        this.longNoseIntensity = 0.5d;
        this.philtrumIntensity = 0.5d;
        this.browHeightIntensity = 0.5d;
        this.browSpaceIntensity = 0.5d;
        this.eyeHeightIntensity = 0.5d;
        this.browThickIntensity = 0.5d;
        this.lipThickIntensity = 0.5d;
    }

    @Override
    public FaceBeautyController getModelController() {
        return getMFaceBeautyController();
    }

    public final void addPropertyMode(FUFaceBeautyMultiModePropertyEnum fuFaceBeautyMultiModePropertyEnum, FUFaceBeautyPropertyModeEnum modeEnum) {
        Intrinsics.checkParameterIsNotNull(fuFaceBeautyMultiModePropertyEnum, "fuFaceBeautyMultiModePropertyEnum");
        Intrinsics.checkParameterIsNotNull(modeEnum, "modeEnum");
        switch (WhenMappings.$EnumSwitchMapping$0[fuFaceBeautyMultiModePropertyEnum.ordinal()]) {
            case 1:
                this.colorMode = modeEnum;
                break;
            case 2:
                this.removePouchMode = modeEnum;
                break;
            case 3:
                this.removeLawPatternMode = modeEnum;
                break;
            case 4:
                this.thinningFaceMode = modeEnum;
                break;
            case 5:
                this.narrowFaceMode = modeEnum;
                break;
            case 6:
                this.smallFaceMode = modeEnum;
                break;
            case 7:
                this.eyeEnlargingMode = modeEnum;
                break;
            case 8:
                this.chinMode = modeEnum;
                break;
            case 9:
                this.forHeadMode = modeEnum;
                break;
            case 10:
                this.thinNoseMode = modeEnum;
                break;
            case 11:
                this.mouthMode = modeEnum;
                break;
        }
    }

    public final void removePropertyMode(FUFaceBeautyMultiModePropertyEnum fuFaceBeautyMultiModePropertyEnum) {
        Intrinsics.checkParameterIsNotNull(fuFaceBeautyMultiModePropertyEnum, "fuFaceBeautyMultiModePropertyEnum");
        switch (WhenMappings.$EnumSwitchMapping$1[fuFaceBeautyMultiModePropertyEnum.ordinal()]) {
            case 1:
                this.colorMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
            case 2:
                this.removePouchMode = FUFaceBeautyPropertyModeEnum.MODE1;
                break;
            case 3:
                this.removeLawPatternMode = FUFaceBeautyPropertyModeEnum.MODE1;
                break;
            case 4:
                this.thinningFaceMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
            case 5:
                this.narrowFaceMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
            case 6:
                this.smallFaceMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
            case 7:
                this.eyeEnlargingMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
            case 8:
                this.chinMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
            case 9:
                this.forHeadMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
            case 10:
                this.thinNoseMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
            case 11:
                this.mouthMode = FUFaceBeautyPropertyModeEnum.MODE2;
                break;
        }
    }

    public final String getFilterName() {
        return this.filterName;
    }

    public final void setFilterName(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.filterName = value;
        updateAttributes(FaceBeautyParam.FILTER_NAME, value);
        updateAttributes("filter_level", Double.valueOf(this.filterIntensity));
    }

    public final double getFilterIntensity() {
        return this.filterIntensity;
    }

    public final void setFilterIntensity(double d) {
        this.filterIntensity = d;
        updateAttributes("filter_level", Double.valueOf(d));
    }

    public final boolean getEnableHeavyBlur() {
        return this.enableHeavyBlur;
    }

    public final void setEnableHeavyBlur(boolean z) {
        this.enableHeavyBlur = z;
        updateAttributes(FaceBeautyParam.HEAVY_BLUR, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final boolean getEnableSkinDetect() {
        return this.enableSkinDetect;
    }

    public final void setEnableSkinDetect(boolean z) {
        this.enableSkinDetect = z;
        updateAttributes(FaceBeautyParam.SKIN_DETECT, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final boolean getEnableSkinSeg() {
        return this.enableSkinSeg;
    }

    public final void setEnableSkinSeg(boolean z) {
        this.enableSkinSeg = z;
        updateAttributes(FaceBeautyParam.ENABLE_SKIN_SEG, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final double getNonSkinBlurIntensity() {
        return this.nonSkinBlurIntensity;
    }

    public final void setNonSkinBlurIntensity(double d) {
        this.nonSkinBlurIntensity = d;
        updateAttributes(FaceBeautyParam.NON_SKIN_BLUR_SCALE, Double.valueOf(d));
    }

    public final int getBlurType() {
        return this.blurType;
    }

    public final void setBlurType(int i) {
        this.blurType = i;
        updateAttributes(FaceBeautyParam.BLUR_TYPE, Integer.valueOf(i));
    }

    public final boolean getEnableBlurUseMask() {
        return this.enableBlurUseMask;
    }

    public final void setEnableBlurUseMask(boolean z) {
        this.enableBlurUseMask = z;
        updateAttributes(FaceBeautyParam.BLUR_USE_MASK, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final double getBlurIntensity() {
        return this.blurIntensity;
    }

    public final void setBlurIntensity(double d) {
        this.blurIntensity = d;
        updateAttributes(FaceBeautyParam.BLUR_INTENSITY, Double.valueOf(d));
    }

    public final double getColorIntensity() {
        return this.colorIntensity;
    }

    public final void setColorIntensity(double d) {
        this.colorIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$2[this.colorMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.COLOR_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes(FaceBeautyParam.COLOR_INTENSITY_M2, Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.COLOR_INTENSITY is not supported " + this.colorMode);
            updateAttributes(FaceBeautyParam.COLOR_INTENSITY_M2, Double.valueOf(d));
        }
    }

    public final double getRedIntensity() {
        return this.redIntensity;
    }

    public final void setRedIntensity(double d) {
        this.redIntensity = d;
        updateAttributes(FaceBeautyParam.RED_INTENSITY, Double.valueOf(d));
    }

    public final double getSharpenIntensity() {
        return this.sharpenIntensity;
    }

    public final void setSharpenIntensity(double d) {
        this.sharpenIntensity = d;
        updateAttributes(FaceBeautyParam.SHARPEN_INTENSITY, Double.valueOf(d));
    }

    public final double getEyeBrightIntensity() {
        return this.eyeBrightIntensity;
    }

    public final void setEyeBrightIntensity(double d) {
        this.eyeBrightIntensity = d;
        updateAttributes(FaceBeautyParam.EYE_BRIGHT_INTENSITY, Double.valueOf(d));
    }

    public final double getToothIntensity() {
        return this.toothIntensity;
    }

    public final void setToothIntensity(double d) {
        this.toothIntensity = d;
        updateAttributes(FaceBeautyParam.TOOTH_WHITEN_INTENSITY, Double.valueOf(d));
    }

    public final double getRemovePouchIntensity() {
        return this.removePouchIntensity;
    }

    public final void setRemovePouchIntensity(double d) {
        this.removePouchIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$3[this.removePouchMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.REMOVE_POUCH_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes(FaceBeautyParam.REMOVE_POUCH_INTENSITY_M2, Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.REMOVE_POUCH_INTENSITY is not supported " + this.removePouchMode);
            updateAttributes(FaceBeautyParam.REMOVE_POUCH_INTENSITY, Double.valueOf(d));
        }
    }

    public final double getRemoveLawPatternIntensity() {
        return this.removeLawPatternIntensity;
    }

    public final void setRemoveLawPatternIntensity(double d) {
        this.removeLawPatternIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$4[this.removeLawPatternMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.REMOVE_NASOLABIAL_FOLDS_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes(FaceBeautyParam.REMOVE_NASOLABIAL_FOLDS_INTENSITY_M2, Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.REMOVE_NASOLABIAL_FOLDS_INTENSITY is not supported " + this.removeLawPatternMode);
            updateAttributes(FaceBeautyParam.REMOVE_NASOLABIAL_FOLDS_INTENSITY, Double.valueOf(d));
        }
    }

    public final double getClarityIntensity() {
        return this.clarityIntensity;
    }

    public final void setClarityIntensity(double d) {
        this.clarityIntensity = d;
        updateAttributes(FaceBeautyParam.CLARITY, Double.valueOf(d));
    }

    public final double getDelspotIntensity() {
        return this.delspotIntensity;
    }

    public final void setDelspotIntensity(double d) {
        this.delspotIntensity = d;
        updateAttributes(FaceBeautyParam.DELSPOT, Double.valueOf(d));
    }

    public final int getFaceShape() {
        return this.faceShape;
    }

    public final void setFaceShape(int i) {
        this.faceShape = i;
        updateAttributes(FaceBeautyParam.FACE_SHAPE, Integer.valueOf(i));
    }

    public final double getFaceShapeIntensity() {
        return this.faceShapeIntensity;
    }

    public final void setFaceShapeIntensity(double d) {
        this.faceShapeIntensity = d;
        updateAttributes(FaceBeautyParam.FACE_SHAPE_INTENSITY, Double.valueOf(d));
    }

    public final double getCheekThinningIntensity() {
        return this.cheekThinningIntensity;
    }

    public final void setCheekThinningIntensity(double d) {
        this.cheekThinningIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$5[this.thinningFaceMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.CHEEK_THINNING_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes(FaceBeautyParam.CHEEK_THINNING_INTENSITY_M2, Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.CHEEK_THINNING_INTENSITY is not supported " + this.thinningFaceMode);
            updateAttributes(FaceBeautyParam.CHEEK_THINNING_INTENSITY_M2, Double.valueOf(d));
        }
    }

    public final double getCheekVIntensity() {
        return this.cheekVIntensity;
    }

    public final void setCheekVIntensity(double d) {
        this.cheekVIntensity = d;
        updateAttributes(FaceBeautyParam.CHEEK_V_INTENSITY, Double.valueOf(d));
    }

    public final double getCheekLongIntensity() {
        return this.cheekLongIntensity;
    }

    public final void setCheekLongIntensity(double d) {
        this.cheekLongIntensity = d;
        updateAttributes(FaceBeautyParam.CHEEK_LONG_INTENSITY, Double.valueOf(d));
    }

    public final double getCheekCircleIntensity() {
        return this.cheekCircleIntensity;
    }

    public final void setCheekCircleIntensity(double d) {
        this.cheekCircleIntensity = d;
        updateAttributes(FaceBeautyParam.CHEEK_CIRCLE_INTENSITY, Double.valueOf(d));
    }

    public final double getCheekNarrowIntensity() {
        return this.cheekNarrowIntensity;
    }

    public final void setCheekNarrowIntensity(double d) {
        this.cheekNarrowIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$6[this.narrowFaceMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.CHEEK_NARROW_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes("cheek_narrow_mode2", Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.CHEEK_NARROW_INTENSITY is not supported " + this.narrowFaceMode);
            updateAttributes("cheek_narrow_mode2", Double.valueOf(d));
        }
    }

    public final double getCheekNarrowIntensityV2() {
        return this.cheekNarrowIntensityV2;
    }

    public final void setCheekNarrowIntensityV2(double d) {
        this.cheekNarrowIntensityV2 = d;
        updateAttributes("cheek_narrow_mode2", Double.valueOf(d));
    }

    public final double getCheekShortIntensity() {
        return this.cheekShortIntensity;
    }

    public final void setCheekShortIntensity(double d) {
        this.cheekShortIntensity = d;
        updateAttributes(FaceBeautyParam.CHEEK_SHORT_INTENSITY, Double.valueOf(d));
    }

    public final double getCheekSmallIntensity() {
        return this.cheekSmallIntensity;
    }

    public final void setCheekSmallIntensity(double d) {
        this.cheekSmallIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$7[this.smallFaceMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.CHEEK_SMALL_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes("cheek_small_mode2", Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.CHEEK_SMALL_INTENSITY is not supported " + this.smallFaceMode);
            updateAttributes("cheek_small_mode2", Double.valueOf(d));
        }
    }

    public final double getCheekSmallIntensityV2() {
        return this.cheekSmallIntensityV2;
    }

    public final void setCheekSmallIntensityV2(double d) {
        this.cheekSmallIntensityV2 = d;
        updateAttributes("cheek_small_mode2", Double.valueOf(d));
    }

    public final double getCheekBonesIntensity() {
        return this.cheekBonesIntensity;
    }

    public final void setCheekBonesIntensity(double d) {
        this.cheekBonesIntensity = d;
        updateAttributes(FaceBeautyParam.INTENSITY_CHEEKBONES_INTENSITY, Double.valueOf(d));
    }

    public final double getLowerJawIntensity() {
        return this.lowerJawIntensity;
    }

    public final void setLowerJawIntensity(double d) {
        this.lowerJawIntensity = d;
        updateAttributes(FaceBeautyParam.INTENSITY_LOW_JAW_INTENSITY, Double.valueOf(d));
    }

    public final double getEyeEnlargingIntensity() {
        return this.eyeEnlargingIntensity;
    }

    public final void setEyeEnlargingIntensity(double d) {
        this.eyeEnlargingIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$8[this.eyeEnlargingMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.EYE_ENLARGING_INTENSITY, Double.valueOf(d));
            return;
        }
        if (i == 2) {
            updateAttributes("eye_enlarging_mode2", Double.valueOf(d));
        } else if (i == 3) {
            updateAttributes(FaceBeautyParam.EYE_ENLARGING_INTENSITY_M3, Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.EYE_ENLARGING_INTENSITY is not supported " + this.eyeEnlargingMode);
            updateAttributes("eye_enlarging_mode2", Double.valueOf(d));
        }
    }

    public final double getEyeEnlargingIntensityV2() {
        return this.eyeEnlargingIntensityV2;
    }

    public final void setEyeEnlargingIntensityV2(double d) {
        this.eyeEnlargingIntensityV2 = d;
        updateAttributes("eye_enlarging_mode2", Double.valueOf(d));
    }

    public final double getChinIntensity() {
        return this.chinIntensity;
    }

    public final void setChinIntensity(double d) {
        this.chinIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$9[this.chinMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.CHIN_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes(FaceBeautyParam.CHIN_INTENSITY_M2, Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.CHIN_INTENSITY is not supported " + this.chinMode);
            updateAttributes(FaceBeautyParam.CHIN_INTENSITY_M2, Double.valueOf(d));
        }
    }

    public final double getForHeadIntensity() {
        return this.forHeadIntensity;
    }

    public final void setForHeadIntensity(double d) {
        this.forHeadIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$10[this.forHeadMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.FOREHEAD_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes("intensity_forehead_mode2", Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.FOREHEAD_INTENSITY is not supported " + this.forHeadMode);
            updateAttributes("intensity_forehead_mode2", Double.valueOf(d));
        }
    }

    public final double getForHeadIntensityV2() {
        return this.forHeadIntensityV2;
    }

    public final void setForHeadIntensityV2(double d) {
        this.forHeadIntensityV2 = d;
        updateAttributes("intensity_forehead_mode2", Double.valueOf(d));
    }

    public final double getNoseIntensity() {
        return this.noseIntensity;
    }

    public final void setNoseIntensity(double d) {
        this.noseIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$11[this.thinNoseMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.NOSE_INTENSITY, Double.valueOf(d));
        } else if (i == 2) {
            updateAttributes("intensity_nose_mode2", Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.NOSE_INTENSITY is not supported " + this.thinNoseMode);
            updateAttributes("intensity_nose_mode2", Double.valueOf(d));
        }
    }

    public final double getNoseIntensityV2() {
        return this.noseIntensityV2;
    }

    public final void setNoseIntensityV2(double d) {
        this.noseIntensityV2 = d;
        updateAttributes("intensity_nose_mode2", Double.valueOf(d));
    }

    public final double getMouthIntensity() {
        return this.mouthIntensity;
    }

    public final void setMouthIntensity(double d) {
        this.mouthIntensity = d;
        int i = WhenMappings.$EnumSwitchMapping$12[this.mouthMode.ordinal()];
        if (i == 1) {
            updateAttributes(FaceBeautyParam.MOUTH_INTENSITY, Double.valueOf(d));
            return;
        }
        if (i == 2) {
            updateAttributes("intensity_mouth_mode2", Double.valueOf(d));
        } else if (i == 3) {
            updateAttributes(FaceBeautyParam.MOUTH_INTENSITY_M3, Double.valueOf(d));
        } else {
            Log.e(this.TAG, "FaceBeautyParam.MOUTH_INTENSITY is not supported " + this.mouthMode);
            updateAttributes("intensity_mouth_mode2", Double.valueOf(d));
        }
    }

    public final double getMouthIntensityV2() {
        return this.mouthIntensityV2;
    }

    public final void setMouthIntensityV2(double d) {
        this.mouthIntensityV2 = d;
        updateAttributes("intensity_mouth_mode2", Double.valueOf(d));
    }

    public final double getCanthusIntensity() {
        return this.canthusIntensity;
    }

    public final void setCanthusIntensity(double d) {
        this.canthusIntensity = d;
        updateAttributes(FaceBeautyParam.CANTHUS_INTENSITY, Double.valueOf(d));
    }

    public final double getEyeSpaceIntensity() {
        return this.eyeSpaceIntensity;
    }

    public final void setEyeSpaceIntensity(double d) {
        this.eyeSpaceIntensity = d;
        updateAttributes(FaceBeautyParam.EYE_SPACE_INTENSITY, Double.valueOf(d));
    }

    public final double getEyeRotateIntensity() {
        return this.eyeRotateIntensity;
    }

    public final void setEyeRotateIntensity(double d) {
        this.eyeRotateIntensity = d;
        updateAttributes(FaceBeautyParam.EYE_ROTATE_INTENSITY, Double.valueOf(d));
    }

    public final double getLongNoseIntensity() {
        return this.longNoseIntensity;
    }

    public final void setLongNoseIntensity(double d) {
        this.longNoseIntensity = d;
        updateAttributes(FaceBeautyParam.LONG_NOSE_INTENSITY, Double.valueOf(d));
    }

    public final double getPhiltrumIntensity() {
        return this.philtrumIntensity;
    }

    public final void setPhiltrumIntensity(double d) {
        this.philtrumIntensity = d;
        updateAttributes(FaceBeautyParam.PHILTRUM_INTENSITY, Double.valueOf(d));
    }

    public final double getSmileIntensity() {
        return this.smileIntensity;
    }

    public final void setSmileIntensity(double d) {
        this.smileIntensity = d;
        updateAttributes(FaceBeautyParam.SMILE_INTENSITY, Double.valueOf(d));
    }

    public final double getEyeCircleIntensity() {
        return this.eyeCircleIntensity;
    }

    public final void setEyeCircleIntensity(double d) {
        this.eyeCircleIntensity = d;
        updateAttributes(FaceBeautyParam.EYE_CIRCLE_INTENSITY, Double.valueOf(d));
    }

    public final double getBrowHeightIntensity() {
        return this.browHeightIntensity;
    }

    public final void setBrowHeightIntensity(double d) {
        this.browHeightIntensity = d;
        updateAttributes(FaceBeautyParam.BROW_HEIGHT_INTENSITY, Double.valueOf(d));
    }

    public final double getBrowSpaceIntensity() {
        return this.browSpaceIntensity;
    }

    public final void setBrowSpaceIntensity(double d) {
        this.browSpaceIntensity = d;
        updateAttributes(FaceBeautyParam.BROW_SPACE_INTENSITY, Double.valueOf(d));
    }

    public final double getEyeLidIntensity() {
        return this.eyeLidIntensity;
    }

    public final void setEyeLidIntensity(double d) {
        this.eyeLidIntensity = d;
        updateAttributes(FaceBeautyParam.INTENSITY_EYE_LID, Double.valueOf(d));
    }

    public final double getEyeHeightIntensity() {
        return this.eyeHeightIntensity;
    }

    public final void setEyeHeightIntensity(double d) {
        this.eyeHeightIntensity = d;
        updateAttributes(FaceBeautyParam.INTENSITY_EYE_HEIGHT, Double.valueOf(d));
    }

    public final double getBrowThickIntensity() {
        return this.browThickIntensity;
    }

    public final void setBrowThickIntensity(double d) {
        this.browThickIntensity = d;
        updateAttributes(FaceBeautyParam.INTENSITY_BROW_THICK, Double.valueOf(d));
    }

    public final double getLipThickIntensity() {
        return this.lipThickIntensity;
    }

    public final void setLipThickIntensity(double d) {
        this.lipThickIntensity = d;
        updateAttributes(FaceBeautyParam.INTENSITY_LIP_THICK, Double.valueOf(d));
    }

    public final double getFaceThreeIntensity() {
        return this.faceThreeIntensity;
    }

    public final void setFaceThreeIntensity(double d) {
        this.faceThreeIntensity = d;
        updateAttributes(FaceBeautyParam.FACE_THREED, Double.valueOf(d));
    }

    public final double getChangeFramesIntensity() {
        return this.changeFramesIntensity;
    }

    public final void setChangeFramesIntensity(double d) {
        this.changeFramesIntensity = d;
        updateAttributes(FaceBeautyParam.CHANGE_FRAMES, Double.valueOf(d));
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put(FaceBeautyParam.FILTER_NAME, this.filterName);
        linkedHashMap2.put("filter_level", Double.valueOf(this.filterIntensity));
        linkedHashMap2.put(FaceBeautyParam.BLUR_INTENSITY, Double.valueOf(this.blurIntensity));
        linkedHashMap2.put(FaceBeautyParam.HEAVY_BLUR, Double.valueOf(this.enableHeavyBlur ? 1.0d : 0.0d));
        linkedHashMap2.put(FaceBeautyParam.SKIN_DETECT, Double.valueOf(this.enableSkinDetect ? 1.0d : 0.0d));
        linkedHashMap2.put(FaceBeautyParam.ENABLE_SKIN_SEG, Double.valueOf(this.enableSkinSeg ? 1.0d : 0.0d));
        linkedHashMap2.put(FaceBeautyParam.NON_SKIN_BLUR_SCALE, Double.valueOf(this.nonSkinBlurIntensity));
        linkedHashMap2.put(FaceBeautyParam.BLUR_TYPE, Integer.valueOf(this.blurType));
        linkedHashMap2.put(FaceBeautyParam.BLUR_USE_MASK, Double.valueOf(this.enableBlurUseMask ? 1.0d : 0.0d));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$13[this.colorMode.ordinal()] != 1 ? FaceBeautyParam.COLOR_INTENSITY_M2 : FaceBeautyParam.COLOR_INTENSITY, Double.valueOf(this.colorIntensity));
        linkedHashMap2.put(FaceBeautyParam.RED_INTENSITY, Double.valueOf(this.redIntensity));
        linkedHashMap2.put(FaceBeautyParam.SHARPEN_INTENSITY, Double.valueOf(this.sharpenIntensity));
        linkedHashMap2.put(FaceBeautyParam.EYE_BRIGHT_INTENSITY, Double.valueOf(this.eyeBrightIntensity));
        linkedHashMap2.put(FaceBeautyParam.TOOTH_WHITEN_INTENSITY, Double.valueOf(this.toothIntensity));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$14[this.removePouchMode.ordinal()] != 1 ? FaceBeautyParam.REMOVE_POUCH_INTENSITY_M2 : FaceBeautyParam.REMOVE_POUCH_INTENSITY, Double.valueOf(this.removePouchIntensity));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$15[this.removeLawPatternMode.ordinal()] != 1 ? FaceBeautyParam.REMOVE_NASOLABIAL_FOLDS_INTENSITY_M2 : FaceBeautyParam.REMOVE_NASOLABIAL_FOLDS_INTENSITY, Double.valueOf(this.removeLawPatternIntensity));
        linkedHashMap2.put(FaceBeautyParam.CLARITY, Double.valueOf(this.clarityIntensity));
        linkedHashMap2.put(FaceBeautyParam.DELSPOT, Double.valueOf(this.delspotIntensity));
        linkedHashMap2.put(FaceBeautyParam.FACE_SHAPE, Integer.valueOf(this.faceShape));
        linkedHashMap2.put(FaceBeautyParam.FACE_SHAPE_INTENSITY, Double.valueOf(this.faceShapeIntensity));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$16[this.thinningFaceMode.ordinal()] != 1 ? FaceBeautyParam.CHEEK_THINNING_INTENSITY_M2 : FaceBeautyParam.CHEEK_THINNING_INTENSITY, Double.valueOf(this.cheekThinningIntensity));
        linkedHashMap2.put(FaceBeautyParam.CHEEK_V_INTENSITY, Double.valueOf(this.cheekVIntensity));
        linkedHashMap2.put(FaceBeautyParam.CHEEK_LONG_INTENSITY, Double.valueOf(this.cheekLongIntensity));
        linkedHashMap2.put(FaceBeautyParam.CHEEK_CIRCLE_INTENSITY, Double.valueOf(this.cheekCircleIntensity));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$17[this.narrowFaceMode.ordinal()] != 1 ? "cheek_narrow_mode2" : FaceBeautyParam.CHEEK_NARROW_INTENSITY, Double.valueOf(this.cheekNarrowIntensity));
        linkedHashMap2.put(FaceBeautyParam.CHEEK_SHORT_INTENSITY, Double.valueOf(this.cheekShortIntensity));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$18[this.smallFaceMode.ordinal()] != 1 ? "cheek_small_mode2" : FaceBeautyParam.CHEEK_SMALL_INTENSITY, Double.valueOf(this.cheekSmallIntensity));
        linkedHashMap2.put(FaceBeautyParam.INTENSITY_CHEEKBONES_INTENSITY, Double.valueOf(this.cheekBonesIntensity));
        linkedHashMap2.put(FaceBeautyParam.INTENSITY_LOW_JAW_INTENSITY, Double.valueOf(this.lowerJawIntensity));
        int i = WhenMappings.$EnumSwitchMapping$19[this.eyeEnlargingMode.ordinal()];
        linkedHashMap2.put(i != 1 ? i != 2 ? FaceBeautyParam.EYE_ENLARGING_INTENSITY_M3 : "eye_enlarging_mode2" : FaceBeautyParam.EYE_ENLARGING_INTENSITY, Double.valueOf(this.eyeEnlargingIntensity));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$20[this.chinMode.ordinal()] != 1 ? FaceBeautyParam.CHIN_INTENSITY_M2 : FaceBeautyParam.CHIN_INTENSITY, Double.valueOf(this.chinIntensity));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$21[this.forHeadMode.ordinal()] != 1 ? "intensity_forehead_mode2" : FaceBeautyParam.FOREHEAD_INTENSITY, Double.valueOf(this.forHeadIntensity));
        linkedHashMap2.put(WhenMappings.$EnumSwitchMapping$22[this.thinNoseMode.ordinal()] != 1 ? "intensity_nose_mode2" : FaceBeautyParam.NOSE_INTENSITY, Double.valueOf(this.noseIntensity));
        int i2 = WhenMappings.$EnumSwitchMapping$23[this.mouthMode.ordinal()];
        linkedHashMap2.put(i2 != 1 ? i2 != 2 ? FaceBeautyParam.MOUTH_INTENSITY_M3 : "intensity_mouth_mode2" : FaceBeautyParam.MOUTH_INTENSITY, Double.valueOf(this.mouthIntensity));
        linkedHashMap2.put(FaceBeautyParam.CANTHUS_INTENSITY, Double.valueOf(this.canthusIntensity));
        linkedHashMap2.put(FaceBeautyParam.EYE_SPACE_INTENSITY, Double.valueOf(this.eyeSpaceIntensity));
        linkedHashMap2.put(FaceBeautyParam.EYE_ROTATE_INTENSITY, Double.valueOf(this.eyeRotateIntensity));
        linkedHashMap2.put(FaceBeautyParam.LONG_NOSE_INTENSITY, Double.valueOf(this.longNoseIntensity));
        linkedHashMap2.put(FaceBeautyParam.PHILTRUM_INTENSITY, Double.valueOf(this.philtrumIntensity));
        linkedHashMap2.put(FaceBeautyParam.SMILE_INTENSITY, Double.valueOf(this.smileIntensity));
        linkedHashMap2.put(FaceBeautyParam.EYE_CIRCLE_INTENSITY, Double.valueOf(this.eyeCircleIntensity));
        linkedHashMap2.put(FaceBeautyParam.BROW_HEIGHT_INTENSITY, Double.valueOf(this.browHeightIntensity));
        linkedHashMap2.put(FaceBeautyParam.BROW_SPACE_INTENSITY, Double.valueOf(this.browSpaceIntensity));
        linkedHashMap2.put(FaceBeautyParam.INTENSITY_EYE_LID, Double.valueOf(this.eyeLidIntensity));
        linkedHashMap2.put(FaceBeautyParam.INTENSITY_EYE_HEIGHT, Double.valueOf(this.eyeHeightIntensity));
        linkedHashMap2.put(FaceBeautyParam.INTENSITY_BROW_THICK, Double.valueOf(this.browThickIntensity));
        linkedHashMap2.put(FaceBeautyParam.INTENSITY_LIP_THICK, Double.valueOf(this.lipThickIntensity));
        linkedHashMap2.put(FaceBeautyParam.FACE_THREED, Double.valueOf(this.faceThreeIntensity));
        linkedHashMap2.put(FaceBeautyParam.CHANGE_FRAMES, Double.valueOf(this.changeFramesIntensity));
        return linkedHashMap;
    }
}
