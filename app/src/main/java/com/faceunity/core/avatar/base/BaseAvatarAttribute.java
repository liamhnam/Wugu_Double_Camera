package com.faceunity.core.avatar.base;

import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.support.FURenderBridge;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, m1293d2 = {"Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "", "()V", "avatarId", "", "getAvatarId$fu_core_all_featureRelease", "()J", "setAvatarId$fu_core_all_featureRelease", "(J)V", "hasLoaded", "", "getHasLoaded", "()Z", "setHasLoaded", "(Z)V", "mAvatarController", "Lcom/faceunity/core/avatar/control/AvatarController;", "getMAvatarController$fu_core_all_featureRelease", "()Lcom/faceunity/core/avatar/control/AvatarController;", "mAvatarController$delegate", "Lkotlin/Lazy;", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public abstract class BaseAvatarAttribute {

    public static final Companion INSTANCE;
    private static final String TAG;
    private boolean hasLoaded;

    private final Lazy mAvatarController = LazyKt.lazy(new Function0<AvatarController>() {
        @Override
        public final AvatarController invoke() {
            return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMAvatarController$fu_core_all_featureRelease();
        }
    });
    private long avatarId = -1;

    public final AvatarController getMAvatarController$fu_core_all_featureRelease() {
        return (AvatarController) this.mAvatarController.getValue();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m1293d2 = {"Lcom/faceunity/core/avatar/base/BaseAvatarAttribute$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return BaseAvatarAttribute.TAG;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        TAG = "KIT_PTA_" + companion.getClass().getName();
    }

    public final long getAvatarId() {
        return this.avatarId;
    }

    public final void setAvatarId$fu_core_all_featureRelease(long j) {
        this.avatarId = j;
    }

    protected final boolean getHasLoaded() {
        return this.hasLoaded;
    }

    protected final void setHasLoaded(boolean z) {
        this.hasLoaded = z;
    }
}
