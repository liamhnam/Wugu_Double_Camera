package com.chad.library.adapter4.loadState.leading;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.chad.library.adapter4.loadState.LoadState;
import com.chad.library.adapter4.loadState.LoadStateAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001&B\u0005¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0019J\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0006\u0010\u001d\u001a\u00020\u0017J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\u0015\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00028\u0000H\u0017¢\u0006\u0002\u0010!J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010#\u001a\u0004\u0018\u00010\fJ\b\u0010$\u001a\u00020%H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006'"}, m1293d2 = {"Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter;", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/chad/library/adapter4/loadState/LoadStateAdapter;", "()V", "isLoadEnable", "", "()Z", "setLoadEnable", "(Z)V", "mDelayNextLoadFlag", "<set-?>", "Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter$OnLeadingListener;", "onLeadingListener", "getOnLeadingListener", "()Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter$OnLeadingListener;", "preloadSize", "", "getPreloadSize", "()I", "setPreloadSize", "(I)V", "checkPreload", "", "currentPosition", "checkPreload$com_github_CymChad_brvah", "displayLoadStateAsItem", "loadState", "Lcom/chad/library/adapter4/loadState/LoadState;", "invokeLoad", "loadAction", "onViewAttachedToWindow", "holder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V", "setOnLeadingListener", "listener", "toString", "", "OnLeadingListener", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public abstract class LeadingLoadStateAdapter<VH extends RecyclerView.ViewHolder> extends LoadStateAdapter<VH> {
    private boolean isLoadEnable = true;
    private boolean mDelayNextLoadFlag;
    private OnLeadingListener onLeadingListener;
    private int preloadSize;

    @Metadata(m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter$OnLeadingListener;", "", "isAllowLoading", "", "onLoad", "", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnLeadingListener {
        default boolean isAllowLoading() {
            return true;
        }

        void onLoad();
    }

    public final OnLeadingListener getOnLeadingListener() {
        return this.onLeadingListener;
    }

    public final boolean getIsLoadEnable() {
        return this.isLoadEnable;
    }

    public final void setLoadEnable(boolean z) {
        this.isLoadEnable = z;
    }

    public final int getPreloadSize() {
        return this.preloadSize;
    }

    public final void setPreloadSize(int i) {
        this.preloadSize = i;
    }

    @Override
    public boolean displayLoadStateAsItem(LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        return loadState instanceof LoadState.Loading;
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        loadAction();
    }

    private final void loadAction() {
        RecyclerView recyclerView;
        if (this.isLoadEnable) {
            OnLeadingListener onLeadingListener = this.onLeadingListener;
            boolean z = false;
            if (onLeadingListener != null && !onLeadingListener.isAllowLoading()) {
                z = true;
            }
            if (z || this.mDelayNextLoadFlag || !(getLoadState() instanceof LoadState.NotLoading) || getLoadState().getEndOfPaginationReached() || (recyclerView = getRecyclerView()) == null) {
                return;
            }
            if (recyclerView.isComputingLayout()) {
                this.mDelayNextLoadFlag = true;
                recyclerView.post(new Runnable() {
                    @Override
                    public final void run() {
                        LeadingLoadStateAdapter.loadAction$lambda$0(this.f$0);
                    }
                });
            } else {
                invokeLoad();
            }
        }
    }

    public static final void loadAction$lambda$0(LeadingLoadStateAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mDelayNextLoadFlag = false;
        this$0.invokeLoad();
    }

    public final void checkPreload$com_github_CymChad_brvah(int currentPosition) {
        if (currentPosition >= 0 && currentPosition <= this.preloadSize) {
            loadAction();
        }
    }

    public final void invokeLoad() {
        setLoadState(LoadState.Loading.INSTANCE);
        OnLeadingListener onLeadingListener = this.onLeadingListener;
        if (onLeadingListener != null) {
            onLeadingListener.onLoad();
        }
    }

    public final LeadingLoadStateAdapter<VH> setOnLeadingListener(OnLeadingListener listener) {
        this.onLeadingListener = listener;
        return this;
    }

    public String toString() {
        return StringsKt.trimIndent("\n            LeadingLoadStateAdapter ->\n            [isLoadEnable: " + this.isLoadEnable + "],\n            [preloadSize: " + this.preloadSize + "],\n            [loadState: " + getLoadState() + "]\n        ");
    }
}
