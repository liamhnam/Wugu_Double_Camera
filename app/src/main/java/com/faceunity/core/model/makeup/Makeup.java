package com.faceunity.core.model.makeup;

import com.faceunity.core.controller.makeup.MakeupParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorRGBData;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0003\b\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J+\u0010º\u0001\u001a$\u0012\u0005\u0012\u00030¼\u0001\u0012\u0005\u0012\u00030½\u00010»\u0001j\u0011\u0012\u0005\u0012\u00030¼\u0001\u0012\u0005\u0012\u00030½\u0001`¾\u0001H\u0014J\n\u0010¿\u0001\u001a\u00030À\u0001H\u0002J\u001f\u0010Á\u0001\u001a\u00030À\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\t\b\u0002\u0010Â\u0001\u001a\u00020(H\u0007R(\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0004R(\u0010\n\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\u0004R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R$\u0010%\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001f\"\u0004\b'\u0010!R$\u0010)\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020(@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010.\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020(@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R(\u00101\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\b\"\u0004\b3\u0010\u0004R$\u00104\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0010\"\u0004\b6\u0010\u0012R$\u00107\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0019\"\u0004\b9\u0010\u001bR(\u0010:\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\b\"\u0004\b<\u0010\u0004R$\u0010=\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0010\"\u0004\b?\u0010\u0012R$\u0010@\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0019\"\u0004\bB\u0010\u001bR$\u0010C\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u001f\"\u0004\bE\u0010!R$\u0010F\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0019\"\u0004\bH\u0010\u001bR(\u0010I\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\b\"\u0004\bK\u0010\u0004R$\u0010L\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0010\"\u0004\bN\u0010\u0012R$\u0010O\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u001f\"\u0004\bQ\u0010!R(\u0010R\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\b\"\u0004\bT\u0010\u0004R(\u0010U\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\b\"\u0004\bW\u0010\u0004R(\u0010X\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\b\"\u0004\bZ\u0010\u0004R(\u0010[\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\b\"\u0004\b]\u0010\u0004R$\u0010^\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u0010\"\u0004\b`\u0010\u0012R$\u0010a\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\u0010\"\u0004\bc\u0010\u0012R$\u0010d\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\u0010\"\u0004\bf\u0010\u0012R$\u0010g\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\u0010\"\u0004\bi\u0010\u0012R$\u0010j\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\u0019\"\u0004\bl\u0010\u001bR$\u0010m\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010\u001f\"\u0004\bo\u0010!R$\u0010p\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\u001f\"\u0004\br\u0010!R$\u0010s\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010\u001f\"\u0004\bu\u0010!R$\u0010v\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bw\u0010\u001f\"\u0004\bx\u0010!R(\u0010y\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010\b\"\u0004\b{\u0010\u0004R$\u0010|\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u0010\u0010\"\u0004\b~\u0010\u0012R&\u0010\u007f\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010\u0019\"\u0005\b\u0081\u0001\u0010\u001bR'\u0010\u0082\u0001\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0083\u0001\u0010\u0019\"\u0005\b\u0084\u0001\u0010\u001bR+\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0001\u0010\b\"\u0005\b\u0087\u0001\u0010\u0004R'\u0010\u0088\u0001\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0089\u0001\u0010\u0010\"\u0005\b\u008a\u0001\u0010\u0012R'\u0010\u008b\u0001\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020(@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010+\"\u0005\b\u008c\u0001\u0010-R+\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010\b\"\u0005\b\u008f\u0001\u0010\u0004R'\u0010\u0090\u0001\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010\u0010\"\u0005\b\u0092\u0001\u0010\u0012R'\u0010\u0093\u0001\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u0010\"\u0005\b\u0095\u0001\u0010\u0012R'\u0010\u0096\u0001\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0010\"\u0005\b\u0098\u0001\u0010\u0012R'\u0010\u0099\u0001\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020(@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010+\"\u0005\b\u009b\u0001\u0010-R'\u0010\u009c\u0001\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010\u0019\"\u0005\b\u009e\u0001\u0010\u001bR'\u0010\u009f\u0001\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b \u0001\u0010\u0019\"\u0005\b¡\u0001\u0010\u001bR'\u0010¢\u0001\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b£\u0001\u0010\u001f\"\u0005\b¤\u0001\u0010!R+\u0010¥\u0001\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¦\u0001\u0010\b\"\u0005\b§\u0001\u0010\u0004R'\u0010¨\u0001\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b©\u0001\u0010\u0010\"\u0005\bª\u0001\u0010\u0012R'\u0010«\u0001\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¬\u0001\u0010\u0019\"\u0005\b\u00ad\u0001\u0010\u001bR'\u0010®\u0001\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¯\u0001\u0010\u001f\"\u0005\b°\u0001\u0010!R+\u0010±\u0001\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b²\u0001\u0010\b\"\u0005\b³\u0001\u0010\u0004R'\u0010´\u0001\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bµ\u0001\u0010\u0010\"\u0005\b¶\u0001\u0010\u0012R'\u0010·\u0001\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¸\u0001\u0010\u0019\"\u0005\b¹\u0001\u0010\u001b¨\u0006Ã\u0001"}, m1293d2 = {"Lcom/faceunity/core/model/makeup/Makeup;", "Lcom/faceunity/core/model/makeup/SimpleMakeup;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "blusherBundle", "getBlusherBundle", "()Lcom/faceunity/core/entity/FUBundleData;", "setBlusherBundle", "blusherBundle2", "getBlusherBundle2", "setBlusherBundle2", "Lcom/faceunity/core/entity/FUColorRGBData;", "blusherColor", "getBlusherColor", "()Lcom/faceunity/core/entity/FUColorRGBData;", "setBlusherColor", "(Lcom/faceunity/core/entity/FUColorRGBData;)V", "blusherColor2", "getBlusherColor2", "setBlusherColor2", "", "blusherIntensity", "getBlusherIntensity", "()D", "setBlusherIntensity", "(D)V", "", "blusherTexBlend", "getBlusherTexBlend", "()I", "setBlusherTexBlend", "(I)V", "blusherTexBlend2", "getBlusherTexBlend2", "setBlusherTexBlend2", "browWarpType", "getBrowWarpType", "setBrowWarpType", "", "enableBrowWarp", "getEnableBrowWarp", "()Z", "setEnableBrowWarp", "(Z)V", "enableTwoLipColor", "getEnableTwoLipColor", "setEnableTwoLipColor", "eyeBrowBundle", "getEyeBrowBundle", "setEyeBrowBundle", "eyeBrowColor", "getEyeBrowColor", "setEyeBrowColor", "eyeBrowIntensity", "getEyeBrowIntensity", "setEyeBrowIntensity", "eyeLashBundle", "getEyeLashBundle", "setEyeLashBundle", "eyeLashColor", "getEyeLashColor", "setEyeLashColor", "eyeLashIntensity", "getEyeLashIntensity", "setEyeLashIntensity", "eyeLashTexBlend", "getEyeLashTexBlend", "setEyeLashTexBlend", "eyeLineIntensity", "getEyeLineIntensity", "setEyeLineIntensity", "eyeLinerBundle", "getEyeLinerBundle", "setEyeLinerBundle", "eyeLinerColor", "getEyeLinerColor", "setEyeLinerColor", "eyeLinerTexBlend", "getEyeLinerTexBlend", "setEyeLinerTexBlend", "eyeShadowBundle", "getEyeShadowBundle", "setEyeShadowBundle", "eyeShadowBundle2", "getEyeShadowBundle2", "setEyeShadowBundle2", "eyeShadowBundle3", "getEyeShadowBundle3", "setEyeShadowBundle3", "eyeShadowBundle4", "getEyeShadowBundle4", "setEyeShadowBundle4", "eyeShadowColor", "getEyeShadowColor", "setEyeShadowColor", "eyeShadowColor2", "getEyeShadowColor2", "setEyeShadowColor2", "eyeShadowColor3", "getEyeShadowColor3", "setEyeShadowColor3", "eyeShadowColor4", "getEyeShadowColor4", "setEyeShadowColor4", "eyeShadowIntensity", "getEyeShadowIntensity", "setEyeShadowIntensity", "eyeShadowTexBlend", "getEyeShadowTexBlend", "setEyeShadowTexBlend", "eyeShadowTexBlend2", "getEyeShadowTexBlend2", "setEyeShadowTexBlend2", "eyeShadowTexBlend3", "getEyeShadowTexBlend3", "setEyeShadowTexBlend3", "eyeShadowTexBlend4", "getEyeShadowTexBlend4", "setEyeShadowTexBlend4", "foundationBundle", "getFoundationBundle", "setFoundationBundle", "foundationColor", "getFoundationColor", "setFoundationColor", "foundationIntensity", "getFoundationIntensity", "setFoundationIntensity", "heightLightIntensity", "getHeightLightIntensity", "setHeightLightIntensity", "highLightBundle", "getHighLightBundle", "setHighLightBundle", "highLightColor", "getHighLightColor", "setHighLightColor", "isMakeupItemNew", "setMakeupItemNew", "lipBundle", "getLipBundle", "setLipBundle", "lipColor", "getLipColor", "setLipColor", "lipColor2", "getLipColor2", "setLipColor2", "lipColorV2", "getLipColorV2", "setLipColorV2", "lipHighLightEnable", "getLipHighLightEnable", "setLipHighLightEnable", "lipHighLightStrength", "getLipHighLightStrength", "setLipHighLightStrength", "lipIntensity", "getLipIntensity", "setLipIntensity", "lipType", "getLipType", "setLipType", "pupilBundle", "getPupilBundle", "setPupilBundle", "pupilColor", "getPupilColor", "setPupilColor", "pupilIntensity", "getPupilIntensity", "setPupilIntensity", "pupilTexBlend", "getPupilTexBlend", "setPupilTexBlend", "shadowBundle", "getShadowBundle", "setShadowBundle", "shadowColor", "getShadowColor", "setShadowColor", "shadowIntensity", "getShadowIntensity", "setShadowIntensity", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "resetMakeup", "", "setCombinedConfig", "isReset", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class Makeup extends SimpleMakeup {
    private FUBundleData blusherBundle;
    private FUBundleData blusherBundle2;
    private FUColorRGBData blusherColor;
    private FUColorRGBData blusherColor2;
    private double blusherIntensity;
    private int blusherTexBlend;
    private int blusherTexBlend2;
    private int browWarpType;
    private boolean enableBrowWarp;
    private boolean enableTwoLipColor;
    private FUBundleData eyeBrowBundle;
    private FUColorRGBData eyeBrowColor;
    private double eyeBrowIntensity;
    private FUBundleData eyeLashBundle;
    private FUColorRGBData eyeLashColor;
    private double eyeLashIntensity;
    private int eyeLashTexBlend;
    private double eyeLineIntensity;
    private FUBundleData eyeLinerBundle;
    private FUColorRGBData eyeLinerColor;
    private int eyeLinerTexBlend;
    private FUBundleData eyeShadowBundle;
    private FUBundleData eyeShadowBundle2;
    private FUBundleData eyeShadowBundle3;
    private FUBundleData eyeShadowBundle4;
    private FUColorRGBData eyeShadowColor;
    private FUColorRGBData eyeShadowColor2;
    private FUColorRGBData eyeShadowColor3;
    private FUColorRGBData eyeShadowColor4;
    private double eyeShadowIntensity;
    private int eyeShadowTexBlend;
    private int eyeShadowTexBlend2;
    private int eyeShadowTexBlend3;
    private int eyeShadowTexBlend4;
    private FUBundleData foundationBundle;
    private FUColorRGBData foundationColor;
    private double foundationIntensity;
    private double heightLightIntensity;
    private FUBundleData highLightBundle;
    private FUColorRGBData highLightColor;
    private boolean isMakeupItemNew;
    private FUBundleData lipBundle;
    private FUColorRGBData lipColor;
    private FUColorRGBData lipColor2;
    private FUColorRGBData lipColorV2;
    private boolean lipHighLightEnable;
    private double lipHighLightStrength;
    private double lipIntensity;
    private int lipType;
    private FUBundleData pupilBundle;
    private FUColorRGBData pupilColor;
    private double pupilIntensity;
    private int pupilTexBlend;
    private FUBundleData shadowBundle;
    private FUColorRGBData shadowColor;
    private double shadowIntensity;

    @Override
    public final void setCombinedConfig(FUBundleData fUBundleData) {
        setCombinedConfig$default(this, fUBundleData, false, 2, null);
    }

    public Makeup(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.lipColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.lipColorV2 = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.lipColor2 = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.eyeLinerColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.eyeLashColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.blusherColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.blusherColor2 = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.foundationColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.highLightColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.shadowColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.eyeBrowColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.pupilColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.eyeShadowColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.eyeShadowColor2 = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.eyeShadowColor3 = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.eyeShadowColor4 = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.pupilTexBlend = 1;
    }

    public static void setCombinedConfig$default(Makeup makeup, FUBundleData fUBundleData, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        makeup.setCombinedConfig(fUBundleData, z);
    }

    public final void setCombinedConfig(FUBundleData controlBundle, boolean isReset) {
        setCombined(controlBundle);
        if (isReset) {
            resetMakeup();
            return;
        }
        LinkedHashMap<String, Object> linkedHashMapBuildParams = buildParams();
        linkedHashMapBuildParams.remove(MakeupParam.COMBINATION);
        updateAttributesBackground("reset", linkedHashMapBuildParams);
    }

    public final double getLipIntensity() {
        return this.lipIntensity;
    }

    public final void setLipIntensity(double d) {
        this.lipIntensity = d;
        updateAttributesBackground("makeup_intensity_lip", Double.valueOf(d));
    }

    public final double getPupilIntensity() {
        return this.pupilIntensity;
    }

    public final void setPupilIntensity(double d) {
        this.pupilIntensity = d;
        updateAttributesBackground("makeup_intensity_pupil", Double.valueOf(d));
    }

    public final double getEyeShadowIntensity() {
        return this.eyeShadowIntensity;
    }

    public final void setEyeShadowIntensity(double d) {
        this.eyeShadowIntensity = d;
        updateAttributesBackground("makeup_intensity_eye", Double.valueOf(d));
    }

    public final double getEyeLineIntensity() {
        return this.eyeLineIntensity;
    }

    public final void setEyeLineIntensity(double d) {
        this.eyeLineIntensity = d;
        updateAttributesBackground("makeup_intensity_eyeLiner", Double.valueOf(d));
    }

    public final double getEyeLashIntensity() {
        return this.eyeLashIntensity;
    }

    public final void setEyeLashIntensity(double d) {
        this.eyeLashIntensity = d;
        updateAttributesBackground("makeup_intensity_eyelash", Double.valueOf(d));
    }

    public final double getEyeBrowIntensity() {
        return this.eyeBrowIntensity;
    }

    public final void setEyeBrowIntensity(double d) {
        this.eyeBrowIntensity = d;
        updateAttributesBackground("makeup_intensity_eyeBrow", Double.valueOf(d));
    }

    public final double getBlusherIntensity() {
        return this.blusherIntensity;
    }

    public final void setBlusherIntensity(double d) {
        this.blusherIntensity = d;
        updateAttributesBackground("makeup_intensity_blusher", Double.valueOf(d));
    }

    public final double getFoundationIntensity() {
        return this.foundationIntensity;
    }

    public final void setFoundationIntensity(double d) {
        this.foundationIntensity = d;
        updateAttributesBackground(MakeupParam.FOUNDATION_INTENSITY, Double.valueOf(d));
    }

    public final double getHeightLightIntensity() {
        return this.heightLightIntensity;
    }

    public final void setHeightLightIntensity(double d) {
        this.heightLightIntensity = d;
        updateAttributesBackground(MakeupParam.HIGHLIGHT_INTENSITY, Double.valueOf(d));
    }

    public final double getShadowIntensity() {
        return this.shadowIntensity;
    }

    public final void setShadowIntensity(double d) {
        this.shadowIntensity = d;
        updateAttributesBackground(MakeupParam.SHADOW_INTENSITY, Double.valueOf(d));
    }

    public final FUBundleData getLipBundle() {
        return this.lipBundle;
    }

    public final void setLipBundle(FUBundleData fUBundleData) {
        updateMakeupBundle(MakeupParam.TEX_LIP, fUBundleData);
        this.lipBundle = fUBundleData;
    }

    public final int getLipType() {
        return this.lipType;
    }

    public final void setLipType(int i) {
        this.lipType = i;
        updateAttributesBackground(MakeupParam.LIP_TYPE, Integer.valueOf(i));
    }

    public final boolean getEnableTwoLipColor() {
        return this.enableTwoLipColor;
    }

    public final void setEnableTwoLipColor(boolean z) {
        this.enableTwoLipColor = z;
        updateAttributesBackground(MakeupParam.IS_TWO_COLOR, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final FUColorRGBData getLipColor() {
        return this.lipColor;
    }

    public final void setLipColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.lipColor = value;
        updateAttributesBackground("makeup_lip_color", value.toScaleColorArray());
    }

    public final FUColorRGBData getLipColorV2() {
        return this.lipColorV2;
    }

    public final void setLipColorV2(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.lipColorV2 = value;
        updateAttributesBackground(MakeupParam.MAKEUP_LIP_COLOR_V2, value.toScaleColorArray());
    }

    public final boolean getLipHighLightEnable() {
        return this.lipHighLightEnable;
    }

    public final void setLipHighLightEnable(boolean z) {
        this.lipHighLightEnable = z;
        updateAttributesBackground(MakeupParam.MAKEUP_LIP_HIGH_LIGHT_ENABLE, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final double getLipHighLightStrength() {
        return this.lipHighLightStrength;
    }

    public final void setLipHighLightStrength(double d) {
        this.lipHighLightStrength = d;
        updateAttributesBackground(MakeupParam.MAKEUP_LIP_HIGH_LIGHT_STRENGTH, Double.valueOf(d));
    }

    public final FUColorRGBData getLipColor2() {
        return this.lipColor2;
    }

    public final void setLipColor2(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.lipColor2 = value;
        updateAttributesBackground(MakeupParam.MAKEUP_LIP_COLOR2, value.toScaleColorArray());
    }

    public final boolean getEnableBrowWarp() {
        return this.enableBrowWarp;
    }

    public final void setEnableBrowWarp(boolean z) {
        this.enableBrowWarp = z;
        updateAttributesBackground(MakeupParam.BROW_WARP, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final int getBrowWarpType() {
        return this.browWarpType;
    }

    public final void setBrowWarpType(int i) {
        this.browWarpType = i;
        updateAttributesBackground(MakeupParam.BROW_WARP_TYPE, Integer.valueOf(i));
    }

    public final FUColorRGBData getEyeLinerColor() {
        return this.eyeLinerColor;
    }

    public final void setEyeLinerColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.eyeLinerColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_EYE_LINER_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getEyeLashColor() {
        return this.eyeLashColor;
    }

    public final void setEyeLashColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.eyeLashColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_EYE_LASH_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getBlusherColor() {
        return this.blusherColor;
    }

    public final void setBlusherColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.blusherColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_BLUSHER_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getBlusherColor2() {
        return this.blusherColor2;
    }

    public final void setBlusherColor2(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.blusherColor2 = value;
        updateAttributesBackground(MakeupParam.MAKEUP_BLUSHER_COLOR2, value.toScaleColorArray());
    }

    public final FUColorRGBData getFoundationColor() {
        return this.foundationColor;
    }

    public final void setFoundationColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.foundationColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_FOUNDATION_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getHighLightColor() {
        return this.highLightColor;
    }

    public final void setHighLightColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.highLightColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_HIGH_LIGHT_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getShadowColor() {
        return this.shadowColor;
    }

    public final void setShadowColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.shadowColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_SHADOW_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getEyeBrowColor() {
        return this.eyeBrowColor;
    }

    public final void setEyeBrowColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.eyeBrowColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_EYE_BROW_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getPupilColor() {
        return this.pupilColor;
    }

    public final void setPupilColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.pupilColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_PUPIL_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getEyeShadowColor() {
        return this.eyeShadowColor;
    }

    public final void setEyeShadowColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.eyeShadowColor = value;
        updateAttributesBackground(MakeupParam.MAKEUP_EYE_SHADOW_COLOR, value.toScaleColorArray());
    }

    public final FUColorRGBData getEyeShadowColor2() {
        return this.eyeShadowColor2;
    }

    public final void setEyeShadowColor2(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.eyeShadowColor2 = value;
        updateAttributesBackground(MakeupParam.MAKEUP_EYE_SHADOW_COLOR2, value.toScaleColorArray());
    }

    public final FUColorRGBData getEyeShadowColor3() {
        return this.eyeShadowColor3;
    }

    public final void setEyeShadowColor3(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.eyeShadowColor3 = value;
        updateAttributesBackground(MakeupParam.MAKEUP_EYE_SHADOW_COLOR3, value.toScaleColorArray());
    }

    public final FUColorRGBData getEyeShadowColor4() {
        return this.eyeShadowColor4;
    }

    public final void setEyeShadowColor4(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.eyeShadowColor4 = value;
        updateAttributesBackground(MakeupParam.MAKEUP_EYE_SHADOW_COLOR4, value.toScaleColorArray());
    }

    public final FUBundleData getEyeBrowBundle() {
        return this.eyeBrowBundle;
    }

    public final void setEyeBrowBundle(FUBundleData fUBundleData) {
        updateMakeupBundle("tex_brow", fUBundleData);
        this.eyeBrowBundle = fUBundleData;
    }

    public final FUBundleData getEyeShadowBundle() {
        return this.eyeShadowBundle;
    }

    public final void setEyeShadowBundle(FUBundleData fUBundleData) {
        updateMakeupBundle("tex_eye", fUBundleData);
        this.eyeShadowBundle = fUBundleData;
    }

    public final FUBundleData getEyeShadowBundle2() {
        return this.eyeShadowBundle2;
    }

    public final void setEyeShadowBundle2(FUBundleData fUBundleData) {
        updateMakeupBundle(MakeupParam.TEX_EYE_SHADOW2, fUBundleData);
        this.eyeShadowBundle2 = fUBundleData;
    }

    public final FUBundleData getEyeShadowBundle3() {
        return this.eyeShadowBundle3;
    }

    public final void setEyeShadowBundle3(FUBundleData fUBundleData) {
        updateMakeupBundle(MakeupParam.TEX_EYE_SHADOW3, fUBundleData);
        this.eyeShadowBundle3 = fUBundleData;
    }

    public final FUBundleData getEyeShadowBundle4() {
        return this.eyeShadowBundle4;
    }

    public final void setEyeShadowBundle4(FUBundleData fUBundleData) {
        updateMakeupBundle(MakeupParam.TEX_EYE_SHADOW4, fUBundleData);
        this.eyeShadowBundle4 = fUBundleData;
    }

    public final FUBundleData getPupilBundle() {
        return this.pupilBundle;
    }

    public final void setPupilBundle(FUBundleData fUBundleData) {
        updateMakeupBundle("tex_pupil", fUBundleData);
        this.pupilBundle = fUBundleData;
    }

    public final FUBundleData getEyeLashBundle() {
        return this.eyeLashBundle;
    }

    public final void setEyeLashBundle(FUBundleData fUBundleData) {
        updateMakeupBundle("tex_eyeLash", fUBundleData);
        this.eyeLashBundle = fUBundleData;
    }

    public final FUBundleData getEyeLinerBundle() {
        return this.eyeLinerBundle;
    }

    public final void setEyeLinerBundle(FUBundleData fUBundleData) {
        updateMakeupBundle("tex_eyeLiner", fUBundleData);
        this.eyeLinerBundle = fUBundleData;
    }

    public final FUBundleData getBlusherBundle() {
        return this.blusherBundle;
    }

    public final void setBlusherBundle(FUBundleData fUBundleData) {
        updateMakeupBundle("tex_blusher", fUBundleData);
        this.blusherBundle = fUBundleData;
    }

    public final FUBundleData getBlusherBundle2() {
        return this.blusherBundle2;
    }

    public final void setBlusherBundle2(FUBundleData fUBundleData) {
        updateMakeupBundle(MakeupParam.TEX_BLUSHER2, fUBundleData);
        this.blusherBundle2 = fUBundleData;
    }

    public final FUBundleData getFoundationBundle() {
        return this.foundationBundle;
    }

    public final void setFoundationBundle(FUBundleData fUBundleData) {
        updateMakeupBundle(MakeupParam.TEX_FOUNDATION, fUBundleData);
        this.foundationBundle = fUBundleData;
    }

    public final FUBundleData getHighLightBundle() {
        return this.highLightBundle;
    }

    public final void setHighLightBundle(FUBundleData fUBundleData) {
        updateMakeupBundle("tex_highlight", fUBundleData);
        this.highLightBundle = fUBundleData;
    }

    public final FUBundleData getShadowBundle() {
        return this.shadowBundle;
    }

    public final void setShadowBundle(FUBundleData fUBundleData) {
        this.shadowBundle = fUBundleData;
        updateMakeupBundle(MakeupParam.TEX_SHADOW, fUBundleData);
    }

    public final int getEyeShadowTexBlend() {
        return this.eyeShadowTexBlend;
    }

    public final void setEyeShadowTexBlend(int i) {
        this.eyeShadowTexBlend = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_EYE_SHADOW, Integer.valueOf(i));
    }

    public final int getEyeShadowTexBlend2() {
        return this.eyeShadowTexBlend2;
    }

    public final void setEyeShadowTexBlend2(int i) {
        this.eyeShadowTexBlend2 = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_EYE_SHADOW2, Integer.valueOf(i));
    }

    public final int getEyeShadowTexBlend3() {
        return this.eyeShadowTexBlend3;
    }

    public final void setEyeShadowTexBlend3(int i) {
        this.eyeShadowTexBlend3 = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_EYE_SHADOW3, Integer.valueOf(i));
    }

    public final int getEyeShadowTexBlend4() {
        return this.eyeShadowTexBlend4;
    }

    public final void setEyeShadowTexBlend4(int i) {
        this.eyeShadowTexBlend4 = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_EYE_SHADOW4, Integer.valueOf(i));
    }

    public final int getEyeLashTexBlend() {
        return this.eyeLashTexBlend;
    }

    public final void setEyeLashTexBlend(int i) {
        this.eyeLashTexBlend = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_EYE_LASH, Integer.valueOf(i));
    }

    public final int getEyeLinerTexBlend() {
        return this.eyeLinerTexBlend;
    }

    public final void setEyeLinerTexBlend(int i) {
        this.eyeLinerTexBlend = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_EYE_LINER, Integer.valueOf(i));
    }

    public final int getBlusherTexBlend() {
        return this.blusherTexBlend;
    }

    public final void setBlusherTexBlend(int i) {
        this.blusherTexBlend = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_BLUSHER, Integer.valueOf(i));
    }

    public final int getBlusherTexBlend2() {
        return this.blusherTexBlend2;
    }

    public final void setBlusherTexBlend2(int i) {
        this.blusherTexBlend2 = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_BLUSHER2, Integer.valueOf(i));
    }

    public final int getPupilTexBlend() {
        return this.pupilTexBlend;
    }

    public final void setPupilTexBlend(int i) {
        this.pupilTexBlend = i;
        updateAttributesBackground(MakeupParam.BLEND_TEX_PUPIL, Integer.valueOf(i));
    }

    public final boolean getIsMakeupItemNew() {
        return this.isMakeupItemNew;
    }

    public final void setMakeupItemNew(boolean z) {
        this.isMakeupItemNew = z;
        getMMakeupController().setMakeupItemNew(z);
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        FUBundleData combined = getCombined();
        if (combined != null) {
            linkedHashMap.put(MakeupParam.COMBINATION, combined);
        }
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put(MakeupParam.LIP_TYPE, Integer.valueOf(this.lipType));
        linkedHashMap2.put(MakeupParam.IS_TWO_COLOR, Double.valueOf(this.enableTwoLipColor ? 1.0d : 0.0d));
        linkedHashMap2.put(MakeupParam.MAKEUP_LIP_HIGH_LIGHT_ENABLE, Double.valueOf(this.lipHighLightEnable ? 1.0d : 0.0d));
        linkedHashMap2.put(MakeupParam.MAKEUP_LIP_HIGH_LIGHT_STRENGTH, Double.valueOf(this.lipHighLightStrength));
        linkedHashMap2.put(MakeupParam.BROW_WARP, Double.valueOf(this.enableBrowWarp ? 1.0d : 0.0d));
        linkedHashMap2.put(MakeupParam.MAKEUP_MACHINE_LEVEL, Double.valueOf(getMachineLevel() ? 1.0d : 0.0d));
        linkedHashMap2.put(MakeupParam.BROW_WARP_TYPE, Integer.valueOf(this.browWarpType));
        linkedHashMap2.put("makeup_intensity", Double.valueOf(getMakeupIntensity()));
        linkedHashMap2.put("filter_level", Double.valueOf(getFilterIntensity()));
        linkedHashMap2.put("makeup_intensity_lip", Double.valueOf(this.lipIntensity));
        linkedHashMap2.put("makeup_intensity_eyeLiner", Double.valueOf(this.eyeLineIntensity));
        linkedHashMap2.put("makeup_intensity_blusher", Double.valueOf(this.blusherIntensity));
        linkedHashMap2.put("makeup_intensity_pupil", Double.valueOf(this.pupilIntensity));
        linkedHashMap2.put("makeup_intensity_eyeBrow", Double.valueOf(this.eyeBrowIntensity));
        linkedHashMap2.put("makeup_intensity_eye", Double.valueOf(this.eyeShadowIntensity));
        linkedHashMap2.put("makeup_intensity_eyelash", Double.valueOf(this.eyeLashIntensity));
        linkedHashMap2.put(MakeupParam.FOUNDATION_INTENSITY, Double.valueOf(this.foundationIntensity));
        linkedHashMap2.put(MakeupParam.HIGHLIGHT_INTENSITY, Double.valueOf(this.heightLightIntensity));
        linkedHashMap2.put(MakeupParam.SHADOW_INTENSITY, Double.valueOf(this.shadowIntensity));
        FUBundleData fUBundleData = this.lipBundle;
        if (fUBundleData != null) {
            linkedHashMap2.put(MakeupParam.TEX_LIP, fUBundleData);
        }
        FUBundleData fUBundleData2 = this.eyeBrowBundle;
        if (fUBundleData2 != null) {
            linkedHashMap2.put("tex_brow", fUBundleData2);
        }
        FUBundleData fUBundleData3 = this.eyeShadowBundle;
        if (fUBundleData3 != null) {
            linkedHashMap2.put("tex_eye", fUBundleData3);
        }
        FUBundleData fUBundleData4 = this.eyeShadowBundle2;
        if (fUBundleData4 != null) {
            linkedHashMap2.put(MakeupParam.TEX_EYE_SHADOW2, fUBundleData4);
        }
        FUBundleData fUBundleData5 = this.eyeShadowBundle3;
        if (fUBundleData5 != null) {
            linkedHashMap2.put(MakeupParam.TEX_EYE_SHADOW3, fUBundleData5);
        }
        FUBundleData fUBundleData6 = this.eyeShadowBundle4;
        if (fUBundleData6 != null) {
            linkedHashMap2.put(MakeupParam.TEX_EYE_SHADOW4, fUBundleData6);
        }
        FUBundleData fUBundleData7 = this.pupilBundle;
        if (fUBundleData7 != null) {
            linkedHashMap2.put("tex_pupil", fUBundleData7);
        }
        FUBundleData fUBundleData8 = this.eyeLashBundle;
        if (fUBundleData8 != null) {
            linkedHashMap2.put("tex_eyeLash", fUBundleData8);
        }
        FUBundleData fUBundleData9 = this.eyeLinerBundle;
        if (fUBundleData9 != null) {
            linkedHashMap2.put("tex_eyeLiner", fUBundleData9);
        }
        FUBundleData fUBundleData10 = this.blusherBundle;
        if (fUBundleData10 != null) {
            linkedHashMap2.put("tex_blusher", fUBundleData10);
        }
        FUBundleData fUBundleData11 = this.blusherBundle2;
        if (fUBundleData11 != null) {
            linkedHashMap2.put(MakeupParam.TEX_BLUSHER2, fUBundleData11);
        }
        FUBundleData fUBundleData12 = this.foundationBundle;
        if (fUBundleData12 != null) {
            linkedHashMap2.put(MakeupParam.TEX_FOUNDATION, fUBundleData12);
        }
        FUBundleData fUBundleData13 = this.highLightBundle;
        if (fUBundleData13 != null) {
            linkedHashMap2.put("tex_highlight", fUBundleData13);
        }
        FUBundleData fUBundleData14 = this.shadowBundle;
        if (fUBundleData14 != null) {
            linkedHashMap2.put(MakeupParam.TEX_SHADOW, fUBundleData14);
        }
        linkedHashMap2.put("makeup_lip_color", this.lipColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_LIP_COLOR_V2, this.lipColorV2.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_LIP_COLOR2, this.lipColor2.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_EYE_LINER_COLOR, this.eyeLinerColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_EYE_LASH_COLOR, this.eyeLashColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_BLUSHER_COLOR, this.blusherColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_BLUSHER_COLOR2, this.blusherColor2.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_FOUNDATION_COLOR, this.foundationColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_HIGH_LIGHT_COLOR, this.highLightColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_SHADOW_COLOR, this.shadowColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_EYE_BROW_COLOR, this.eyeBrowColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_PUPIL_COLOR, this.pupilColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_EYE_SHADOW_COLOR, this.eyeShadowColor.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_EYE_SHADOW_COLOR2, this.eyeShadowColor2.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_EYE_SHADOW_COLOR3, this.eyeShadowColor3.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.MAKEUP_EYE_SHADOW_COLOR4, this.eyeShadowColor4.toScaleColorArray());
        linkedHashMap2.put(MakeupParam.BLEND_TEX_EYE_SHADOW, Integer.valueOf(this.eyeShadowTexBlend));
        linkedHashMap2.put(MakeupParam.BLEND_TEX_EYE_SHADOW2, Integer.valueOf(this.eyeShadowTexBlend2));
        linkedHashMap2.put(MakeupParam.BLEND_TEX_EYE_SHADOW3, Integer.valueOf(this.eyeShadowTexBlend3));
        linkedHashMap2.put(MakeupParam.BLEND_TEX_EYE_SHADOW4, Integer.valueOf(this.eyeShadowTexBlend4));
        linkedHashMap2.put(MakeupParam.BLEND_TEX_EYE_LASH, Integer.valueOf(this.eyeLashTexBlend));
        linkedHashMap2.put(MakeupParam.BLEND_TEX_EYE_LINER, Integer.valueOf(this.eyeLinerTexBlend));
        linkedHashMap2.put(MakeupParam.BLEND_TEX_BLUSHER, Integer.valueOf(this.blusherTexBlend));
        linkedHashMap2.put(MakeupParam.BLEND_TEX_BLUSHER2, Integer.valueOf(this.blusherTexBlend2));
        linkedHashMap2.put(MakeupParam.BLEND_TEX_PUPIL, Integer.valueOf(this.pupilTexBlend));
        return linkedHashMap;
    }

    private final void resetMakeup() {
        setLipType(0);
        setLipHighLightEnable(false);
        setLipHighLightStrength(0.0d);
        setEnableTwoLipColor(false);
        setEnableBrowWarp(false);
        setMachineLevel(false);
        setBrowWarpType(0);
        setMakeupIntensity(1.0d);
        setFilterIntensity(1.0d);
        setEyeLineIntensity(0.0d);
        setLipIntensity(0.0d);
        setBlusherIntensity(0.0d);
        setPupilIntensity(0.0d);
        setEyeBrowIntensity(0.0d);
        setEyeShadowIntensity(0.0d);
        setEyeLashIntensity(0.0d);
        setFoundationIntensity(0.0d);
        setHeightLightIntensity(0.0d);
        setShadowIntensity(0.0d);
        setLipBundle(null);
        setEyeBrowBundle(null);
        setEyeShadowBundle(null);
        setEyeShadowBundle2(null);
        setEyeShadowBundle3(null);
        setEyeShadowBundle4(null);
        setPupilBundle(null);
        setEyeLashBundle(null);
        setEyeLinerBundle(null);
        setBlusherBundle(null);
        setBlusherBundle2(null);
        setFoundationBundle(null);
        setHighLightBundle(null);
        setShadowBundle(null);
        setLipColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setLipColorV2(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setLipColor2(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setEyeLinerColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setEyeLashColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setBlusherColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setBlusherColor2(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setFoundationColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setHighLightColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setShadowColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setEyeBrowColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setPupilColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setEyeShadowColor(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setEyeShadowColor2(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setEyeShadowColor3(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setEyeShadowColor4(new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d));
        setEyeShadowTexBlend(0);
        setEyeShadowTexBlend2(0);
        setEyeShadowTexBlend3(0);
        setEyeShadowTexBlend4(0);
        setEyeLashTexBlend(0);
        setEyeLinerTexBlend(0);
        setBlusherTexBlend(0);
        setBlusherTexBlend2(0);
        setPupilTexBlend(1);
    }
}
