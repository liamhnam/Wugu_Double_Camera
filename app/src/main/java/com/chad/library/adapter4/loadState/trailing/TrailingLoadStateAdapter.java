package com.chad.library.adapter4.loadState.trailing;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.chad.library.adapter4.loadState.LoadState;
import com.chad.library.adapter4.loadState.LoadStateAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1292d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001.B\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0017\u001a\u00020\u0018J\u001d\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u001cJ\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u00122\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\u0006\u0010#\u001a\u00020\u0018J\u0006\u0010$\u001a\u00020\u0018J\b\u0010%\u001a\u00020\u0005H\u0002J\b\u0010&\u001a\u00020\u0018H\u0002J\u0015\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00028\u0000H\u0017¢\u0006\u0002\u0010)J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010+\u001a\u0004\u0018\u00010\rJ\b\u0010,\u001a\u00020-H\u0016R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bR\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006/"}, m1293d2 = {"Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter;", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/chad/library/adapter4/loadState/LoadStateAdapter;", "isLoadEndDisplay", "", "(Z)V", "isAutoLoadMore", "()Z", "setAutoLoadMore", "mDelayNextLoadFlag", "mNotFullPageNextLoadFlag", "<set-?>", "Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter$OnTrailingListener;", "onTrailingListener", "getOnTrailingListener", "()Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter$OnTrailingListener;", "preloadSize", "", "getPreloadSize", "()I", "setPreloadSize", "(I)V", "checkDisableLoadMoreIfNotFullPage", "", "checkPreload", "itemCount", "currentPosition", "checkPreload$com_github_CymChad_brvah", "displayLoadStateAsItem", "loadState", "Lcom/chad/library/adapter4/loadState/LoadState;", "getTheBiggestNumber", "numbers", "", "invokeFailRetry", "invokeLoadMore", "isFullScreen", "loadAction", "onViewAttachedToWindow", "holder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V", "setOnLoadMoreListener", "listener", "toString", "", "OnTrailingListener", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public abstract class TrailingLoadStateAdapter<VH extends RecyclerView.ViewHolder> extends LoadStateAdapter<VH> {
    private boolean isAutoLoadMore;
    private final boolean isLoadEndDisplay;
    private boolean mDelayNextLoadFlag;
    private boolean mNotFullPageNextLoadFlag;
    private OnTrailingListener onTrailingListener;
    private int preloadSize;

    @Metadata(m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter$OnTrailingListener;", "", "isAllowLoading", "", "onFailRetry", "", "onLoad", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnTrailingListener {
        default boolean isAllowLoading() {
            return true;
        }

        void onFailRetry();

        void onLoad();
    }

    public TrailingLoadStateAdapter() {
        this(false, 1, null);
    }

    public TrailingLoadStateAdapter(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    public final boolean getIsLoadEndDisplay() {
        return this.isLoadEndDisplay;
    }

    public TrailingLoadStateAdapter(boolean z) {
        this.isLoadEndDisplay = z;
        this.isAutoLoadMore = true;
    }

    public final OnTrailingListener getOnTrailingListener() {
        return this.onTrailingListener;
    }

    public final boolean getIsAutoLoadMore() {
        return this.isAutoLoadMore;
    }

    public final void setAutoLoadMore(boolean z) {
        this.isAutoLoadMore = z;
    }

    public final int getPreloadSize() {
        return this.preloadSize;
    }

    public final void setPreloadSize(int i) {
        this.preloadSize = i;
    }

    @Override
    public boolean displayLoadStateAsItem(LoadState loadState) {
        boolean z;
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        return super.displayLoadStateAsItem(loadState) || (((z = loadState instanceof LoadState.NotLoading)) && !loadState.getEndOfPaginationReached()) || (this.isLoadEndDisplay && z && loadState.getEndOfPaginationReached());
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        loadAction();
    }

    private final void loadAction() {
        RecyclerView recyclerView;
        if (this.isAutoLoadMore) {
            OnTrailingListener onTrailingListener = this.onTrailingListener;
            boolean z = false;
            if (onTrailingListener != null && !onTrailingListener.isAllowLoading()) {
                z = true;
            }
            if (z || this.mNotFullPageNextLoadFlag || this.mDelayNextLoadFlag || !(getLoadState() instanceof LoadState.NotLoading) || getLoadState().getEndOfPaginationReached() || (recyclerView = getRecyclerView()) == null) {
                return;
            }
            if (recyclerView.isComputingLayout()) {
                this.mDelayNextLoadFlag = true;
                recyclerView.post(new Runnable() {
                    @Override
                    public final void run() {
                        TrailingLoadStateAdapter.loadAction$lambda$0(this.f$0);
                    }
                });
            } else {
                invokeLoadMore();
            }
        }
    }

    public static final void loadAction$lambda$0(TrailingLoadStateAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mDelayNextLoadFlag = false;
        this$0.invokeLoadMore();
    }

    public final void checkPreload$com_github_CymChad_brvah(int itemCount, int currentPosition) {
        if (currentPosition <= itemCount - 1 && (itemCount - currentPosition) - 1 <= this.preloadSize) {
            loadAction();
        }
    }

    public final void invokeLoadMore() {
        setLoadState(LoadState.Loading.INSTANCE);
        OnTrailingListener onTrailingListener = this.onTrailingListener;
        if (onTrailingListener != null) {
            onTrailingListener.onLoad();
        }
    }

    public final void invokeFailRetry() {
        setLoadState(LoadState.Loading.INSTANCE);
        OnTrailingListener onTrailingListener = this.onTrailingListener;
        if (onTrailingListener != null) {
            onTrailingListener.onFailRetry();
        }
    }

    public final void checkDisableLoadMoreIfNotFullPage() {
        final RecyclerView.LayoutManager layoutManager;
        this.mNotFullPageNextLoadFlag = true;
        final RecyclerView recyclerView = getRecyclerView();
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null) {
            return;
        }
        if (layoutManager instanceof LinearLayoutManager) {
            recyclerView.post(new Runnable() {
                @Override
                public final void run() {
                    TrailingLoadStateAdapter.checkDisableLoadMoreIfNotFullPage$lambda$1(this.f$0);
                }
            });
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            recyclerView.post(new Runnable() {
                @Override
                public final void run() {
                    TrailingLoadStateAdapter.checkDisableLoadMoreIfNotFullPage$lambda$2(layoutManager, this, recyclerView);
                }
            });
        }
    }

    public static final void checkDisableLoadMoreIfNotFullPage$lambda$1(TrailingLoadStateAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFullScreen()) {
            this$0.mNotFullPageNextLoadFlag = false;
        }
    }

    public static final void checkDisableLoadMoreIfNotFullPage$lambda$2(RecyclerView.LayoutManager manager, TrailingLoadStateAdapter this$0, RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(manager, "$manager");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(recyclerView, "$recyclerView");
        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
        int[] iArr = new int[staggeredGridLayoutManager.getSpanCount()];
        staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(iArr);
        int theBiggestNumber = this$0.getTheBiggestNumber(iArr) + 1;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null && theBiggestNumber == adapter.getItemCount()) {
            return;
        }
        this$0.mNotFullPageNextLoadFlag = false;
    }

    private final boolean isFullScreen() {
        RecyclerView.Adapter adapter;
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView == null || (adapter = recyclerView.getAdapter()) == null) {
            return true;
        }
        RecyclerView recyclerView2 = getRecyclerView();
        RecyclerView.LayoutManager layoutManager = recyclerView2 != null ? recyclerView2.getLayoutManager() : null;
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        return (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1 == adapter.getItemCount() && linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) ? false : true;
    }

    private final int getTheBiggestNumber(int[] numbers) {
        int i = -1;
        if (numbers != null) {
            if (!(numbers.length == 0)) {
                for (int i2 : numbers) {
                    if (i2 > i) {
                        i = i2;
                    }
                }
            }
        }
        return i;
    }

    public final TrailingLoadStateAdapter<VH> setOnLoadMoreListener(OnTrailingListener listener) {
        this.onTrailingListener = listener;
        return this;
    }

    public String toString() {
        return StringsKt.trimIndent("\n            TrailingLoadStateAdapter ->\n            [isLoadEndDisplay: " + this.isLoadEndDisplay + "],\n            [isAutoLoadMore: " + this.isAutoLoadMore + "],\n            [preloadSize: " + this.preloadSize + "],\n            [loadState: " + getLoadState() + "]\n        ");
    }
}
