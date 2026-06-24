package com.chad.library.adapter4;

import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.arthenica.ffmpegkit.StreamInformation;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.loadState.LoadState;
import com.chad.library.adapter4.loadState.leading.DefaultLeadingLoadStateAdapter;
import com.chad.library.adapter4.loadState.leading.LeadingLoadStateAdapter;
import com.chad.library.adapter4.loadState.trailing.DefaultTrailingLoadStateAdapter;
import com.chad.library.adapter4.loadState.trailing.TrailingLoadStateAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00014B;\b\u0002\u0012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010-\u001a\u00020\u00002\u000e\u0010\u000b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003J\u001e\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020/2\u000e\u0010\u000b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003J\u0016\u00100\u001a\u00020\u00002\u000e\u0010\u000b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003J\u001e\u00100\u001a\u00020\u00002\u0006\u0010.\u001a\u00020/2\u000e\u0010\u000b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003J\u0006\u00101\u001a\u00020\u0000J\u0006\u00102\u001a\u00020\u0000J\u0016\u00103\u001a\u00020\u00002\u000e\u0010\u000b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00030\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001f\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00030\u00108F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0017\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010$\u001a\"\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00030%j\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003`&X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010'\u001a\"\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00030%j\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003`&X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010(\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b)\u0010\u001e\"\u0004\b*\u0010 R\u0017\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u00065"}, m1293d2 = {"Lcom/chad/library/adapter4/QuickAdapterHelper;", "", "contentAdapter", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "leadingLoadStateAdapter", "Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter;", "trailingLoadStateAdapter", "Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter;", "config", "Landroidx/recyclerview/widget/ConcatAdapter$Config;", "(Lcom/chad/library/adapter4/BaseQuickAdapter;Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter;Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter;Landroidx/recyclerview/widget/ConcatAdapter$Config;)V", "adapter", "Landroidx/recyclerview/widget/ConcatAdapter;", "getAdapter", "()Landroidx/recyclerview/widget/ConcatAdapter;", "afterAdapterList", "", "getAfterAdapterList", "()Ljava/util/List;", "beforeAdapterList", "getBeforeAdapterList", "getContentAdapter", "()Lcom/chad/library/adapter4/BaseQuickAdapter;", "firstAdapterOnViewAttachChangeListener", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnViewAttachStateChangeListener;", "lastAdapterOnViewAttachChangeListener", "value", "Lcom/chad/library/adapter4/loadState/LoadState;", "leadingLoadState", "getLeadingLoadState", "()Lcom/chad/library/adapter4/loadState/LoadState;", "setLeadingLoadState", "(Lcom/chad/library/adapter4/loadState/LoadState;)V", "getLeadingLoadStateAdapter", "()Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter;", "mAdapter", "mAfterList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mBeforeList", "trailingLoadState", "getTrailingLoadState", "setTrailingLoadState", "getTrailingLoadStateAdapter", "()Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter;", "addAfterAdapter", StreamInformation.KEY_INDEX, "", "addBeforeAdapter", "clearAfterAdapters", "clearBeforeAdapters", "removeAdapter", "Builder", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class QuickAdapterHelper {
    private final BaseQuickAdapter<?, ?> contentAdapter;
    private BaseQuickAdapter.OnViewAttachStateChangeListener firstAdapterOnViewAttachChangeListener;
    private BaseQuickAdapter.OnViewAttachStateChangeListener lastAdapterOnViewAttachChangeListener;
    private final LeadingLoadStateAdapter<?> leadingLoadStateAdapter;
    private final ConcatAdapter mAdapter;
    private final ArrayList<BaseQuickAdapter<?, ?>> mAfterList;
    private final ArrayList<BaseQuickAdapter<?, ?>> mBeforeList;
    private final TrailingLoadStateAdapter<?> trailingLoadStateAdapter;

    public QuickAdapterHelper(BaseQuickAdapter baseQuickAdapter, LeadingLoadStateAdapter leadingLoadStateAdapter, TrailingLoadStateAdapter trailingLoadStateAdapter, ConcatAdapter.Config config, DefaultConstructorMarker defaultConstructorMarker) {
        this(baseQuickAdapter, leadingLoadStateAdapter, trailingLoadStateAdapter, config);
    }

    private QuickAdapterHelper(BaseQuickAdapter<?, ?> baseQuickAdapter, LeadingLoadStateAdapter<?> leadingLoadStateAdapter, TrailingLoadStateAdapter<?> trailingLoadStateAdapter, ConcatAdapter.Config config) {
        this.contentAdapter = baseQuickAdapter;
        this.leadingLoadStateAdapter = leadingLoadStateAdapter;
        this.trailingLoadStateAdapter = trailingLoadStateAdapter;
        this.mBeforeList = new ArrayList<>(0);
        this.mAfterList = new ArrayList<>(0);
        ConcatAdapter concatAdapter = new ConcatAdapter(config, (RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[0]);
        this.mAdapter = concatAdapter;
        if (leadingLoadStateAdapter != null) {
            concatAdapter.addAdapter(leadingLoadStateAdapter);
            BaseQuickAdapter.OnViewAttachStateChangeListener onViewAttachStateChangeListener = new BaseQuickAdapter.OnViewAttachStateChangeListener() {
                @Override
                public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
                    Intrinsics.checkNotNullParameter(holder, "holder");
                }

                @Override
                public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
                    Intrinsics.checkNotNullParameter(holder, "holder");
                    this.this$0.getLeadingLoadStateAdapter().checkPreload$com_github_CymChad_brvah(holder.getBindingAdapterPosition());
                }
            };
            baseQuickAdapter.addOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
            this.firstAdapterOnViewAttachChangeListener = onViewAttachStateChangeListener;
        }
        concatAdapter.addAdapter(baseQuickAdapter);
        if (trailingLoadStateAdapter != null) {
            concatAdapter.addAdapter(trailingLoadStateAdapter);
            BaseQuickAdapter.OnViewAttachStateChangeListener onViewAttachStateChangeListener2 = new BaseQuickAdapter.OnViewAttachStateChangeListener() {
                @Override
                public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
                    Intrinsics.checkNotNullParameter(holder, "holder");
                }

                @Override
                public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
                    Intrinsics.checkNotNullParameter(holder, "holder");
                    TrailingLoadStateAdapter<?> trailingLoadStateAdapter2 = this.this$0.getTrailingLoadStateAdapter();
                    RecyclerView.Adapter<? extends RecyclerView.ViewHolder> bindingAdapter = holder.getBindingAdapter();
                    trailingLoadStateAdapter2.checkPreload$com_github_CymChad_brvah(bindingAdapter != null ? bindingAdapter.getItemCount() : 0, holder.getBindingAdapterPosition());
                }
            };
            baseQuickAdapter.addOnViewAttachStateChangeListener(onViewAttachStateChangeListener2);
            this.lastAdapterOnViewAttachChangeListener = onViewAttachStateChangeListener2;
        }
    }

    public final BaseQuickAdapter<?, ?> getContentAdapter() {
        return this.contentAdapter;
    }

    public final LeadingLoadStateAdapter<?> getLeadingLoadStateAdapter() {
        return this.leadingLoadStateAdapter;
    }

    public final TrailingLoadStateAdapter<?> getTrailingLoadStateAdapter() {
        return this.trailingLoadStateAdapter;
    }

    public final ConcatAdapter getMAdapter() {
        return this.mAdapter;
    }

    public final void setLeadingLoadState(LoadState value) {
        Intrinsics.checkNotNullParameter(value, "value");
        LeadingLoadStateAdapter<?> leadingLoadStateAdapter = this.leadingLoadStateAdapter;
        if (leadingLoadStateAdapter == null) {
            return;
        }
        leadingLoadStateAdapter.setLoadState(value);
    }

    public final LoadState getLeadingLoadState() {
        LoadState loadState;
        LeadingLoadStateAdapter<?> leadingLoadStateAdapter = this.leadingLoadStateAdapter;
        return (leadingLoadStateAdapter == null || (loadState = leadingLoadStateAdapter.getLoadState()) == null) ? new LoadState.NotLoading(false) : loadState;
    }

    public final void setTrailingLoadState(LoadState value) {
        Intrinsics.checkNotNullParameter(value, "value");
        TrailingLoadStateAdapter<?> trailingLoadStateAdapter = this.trailingLoadStateAdapter;
        if (trailingLoadStateAdapter == null) {
            return;
        }
        trailingLoadStateAdapter.setLoadState(value);
    }

    public final LoadState getTrailingLoadState() {
        LoadState loadState;
        TrailingLoadStateAdapter<?> trailingLoadStateAdapter = this.trailingLoadStateAdapter;
        return (trailingLoadStateAdapter == null || (loadState = trailingLoadStateAdapter.getLoadState()) == null) ? new LoadState.NotLoading(false) : loadState;
    }

    public final QuickAdapterHelper addBeforeAdapter(BaseQuickAdapter<?, ?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        addBeforeAdapter(this.mBeforeList.size(), adapter);
        return this;
    }

    public final QuickAdapterHelper addBeforeAdapter(int index, BaseQuickAdapter<?, ?> adapter) {
        BaseQuickAdapter.OnViewAttachStateChangeListener onViewAttachStateChangeListener;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        if (index < 0 || index > this.mBeforeList.size()) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + this.mBeforeList.size() + ". Given:" + index);
        }
        if (index == 0 && (onViewAttachStateChangeListener = this.firstAdapterOnViewAttachChangeListener) != null) {
            if (this.mBeforeList.isEmpty()) {
                this.contentAdapter.removeOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
            } else {
                ((BaseQuickAdapter) CollectionsKt.first((List) this.mBeforeList)).removeOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
            }
            adapter.addOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
        }
        if (this.leadingLoadStateAdapter != null) {
            index++;
        }
        if (this.mAdapter.addAdapter(index, adapter)) {
            this.mBeforeList.add(adapter);
        }
        return this;
    }

    public final QuickAdapterHelper clearBeforeAdapters() {
        Iterator<T> it = this.mBeforeList.iterator();
        while (it.hasNext()) {
            BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) it.next();
            this.mAdapter.removeAdapter(baseQuickAdapter);
            BaseQuickAdapter.OnViewAttachStateChangeListener onViewAttachStateChangeListener = this.firstAdapterOnViewAttachChangeListener;
            if (onViewAttachStateChangeListener != null) {
                baseQuickAdapter.removeOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
            }
        }
        this.mBeforeList.clear();
        return this;
    }

    public final QuickAdapterHelper addAfterAdapter(BaseQuickAdapter<?, ?> adapter) {
        boolean zAddAdapter;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        BaseQuickAdapter.OnViewAttachStateChangeListener onViewAttachStateChangeListener = this.lastAdapterOnViewAttachChangeListener;
        if (onViewAttachStateChangeListener != null) {
            if (this.mAfterList.isEmpty()) {
                this.contentAdapter.removeOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
            } else {
                ((BaseQuickAdapter) CollectionsKt.last((List) this.mAfterList)).removeOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
            }
            adapter.addOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
        }
        if (this.trailingLoadStateAdapter == null) {
            zAddAdapter = this.mAdapter.addAdapter(adapter);
        } else {
            zAddAdapter = this.mAdapter.addAdapter(r0.getAdapters().size() - 1, adapter);
        }
        if (zAddAdapter) {
            this.mAfterList.add(adapter);
        }
        return this;
    }

    public final QuickAdapterHelper addAfterAdapter(int index, BaseQuickAdapter<?, ?> adapter) {
        int size;
        int size2;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        if (index < 0 || index > this.mAfterList.size()) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + this.mAfterList.size() + ". Given:" + index);
        }
        if (index == this.mAfterList.size()) {
            addAfterAdapter(adapter);
        } else {
            if (this.trailingLoadStateAdapter == null) {
                size = this.mAdapter.getAdapters().size();
                size2 = this.mAfterList.size();
            } else {
                size = this.mAdapter.getAdapters().size() - 1;
                size2 = this.mAfterList.size();
            }
            if (this.mAdapter.addAdapter((size - size2) + index, adapter)) {
                this.mAfterList.add(adapter);
            }
        }
        return this;
    }

    public final QuickAdapterHelper clearAfterAdapters() {
        Iterator<T> it = this.mAfterList.iterator();
        while (it.hasNext()) {
            BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) it.next();
            this.mAdapter.removeAdapter(baseQuickAdapter);
            BaseQuickAdapter.OnViewAttachStateChangeListener onViewAttachStateChangeListener = this.lastAdapterOnViewAttachChangeListener;
            if (onViewAttachStateChangeListener != null) {
                baseQuickAdapter.removeOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
            }
        }
        this.mAfterList.clear();
        return this;
    }

    public final List<BaseQuickAdapter<?, ?>> getBeforeAdapterList() {
        List<BaseQuickAdapter<?, ?>> listUnmodifiableList = Collections.unmodifiableList(this.mBeforeList);
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(mBeforeList)");
        return listUnmodifiableList;
    }

    public final List<BaseQuickAdapter<?, ?>> getAfterAdapterList() {
        List<BaseQuickAdapter<?, ?>> listUnmodifiableList = Collections.unmodifiableList(this.mAfterList);
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(mAfterList)");
        return listUnmodifiableList;
    }

    public final QuickAdapterHelper removeAdapter(BaseQuickAdapter<?, ?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        if (!Intrinsics.areEqual(adapter, this.contentAdapter)) {
            this.mAdapter.removeAdapter(adapter);
            this.mBeforeList.remove(adapter);
            this.mAfterList.remove(adapter);
            BaseQuickAdapter.OnViewAttachStateChangeListener onViewAttachStateChangeListener = this.firstAdapterOnViewAttachChangeListener;
            if (onViewAttachStateChangeListener != null) {
                adapter.removeOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
                if (this.mBeforeList.isEmpty()) {
                    this.contentAdapter.addOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
                } else {
                    ((BaseQuickAdapter) CollectionsKt.first((List) this.mBeforeList)).addOnViewAttachStateChangeListener(onViewAttachStateChangeListener);
                }
            }
            BaseQuickAdapter.OnViewAttachStateChangeListener onViewAttachStateChangeListener2 = this.lastAdapterOnViewAttachChangeListener;
            if (onViewAttachStateChangeListener2 != null) {
                adapter.removeOnViewAttachStateChangeListener(onViewAttachStateChangeListener2);
                if (this.mAfterList.isEmpty()) {
                    this.contentAdapter.addOnViewAttachStateChangeListener(onViewAttachStateChangeListener2);
                } else {
                    ((BaseQuickAdapter) CollectionsKt.last((List) this.mAfterList)).addOnViewAttachStateChangeListener(onViewAttachStateChangeListener2);
                }
            }
        }
        return this;
    }

    @Metadata(m1292d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0011\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0014\u0010\u0011\u001a\u00020\u00002\f\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bJ\u0010\u0010\u0015\u001a\u00020\u00002\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0014\u0010\u0015\u001a\u00020\u00002\f\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m1293d2 = {"Lcom/chad/library/adapter4/QuickAdapterHelper$Builder;", "", "contentAdapter", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "(Lcom/chad/library/adapter4/BaseQuickAdapter;)V", "config", "Landroidx/recyclerview/widget/ConcatAdapter$Config;", "leadingLoadStateAdapter", "Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter;", "trailingLoadStateAdapter", "Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter;", "attachTo", "Lcom/chad/library/adapter4/QuickAdapterHelper;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "build", "setConfig", "setLeadingLoadStateAdapter", "loadListener", "Lcom/chad/library/adapter4/loadState/leading/LeadingLoadStateAdapter$OnLeadingListener;", "loadStateAdapter", "setTrailingLoadStateAdapter", "loadMoreListener", "Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter$OnTrailingListener;", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public static final class Builder {
        private ConcatAdapter.Config config;
        private final BaseQuickAdapter<?, ?> contentAdapter;
        private LeadingLoadStateAdapter<?> leadingLoadStateAdapter;
        private TrailingLoadStateAdapter<?> trailingLoadStateAdapter;

        public Builder(BaseQuickAdapter<?, ?> contentAdapter) {
            Intrinsics.checkNotNullParameter(contentAdapter, "contentAdapter");
            this.contentAdapter = contentAdapter;
            ConcatAdapter.Config DEFAULT = ConcatAdapter.Config.DEFAULT;
            Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
            this.config = DEFAULT;
        }

        public final Builder setTrailingLoadStateAdapter(TrailingLoadStateAdapter<?> loadStateAdapter) {
            this.trailingLoadStateAdapter = loadStateAdapter;
            return this;
        }

        public final Builder setTrailingLoadStateAdapter(TrailingLoadStateAdapter.OnTrailingListener loadMoreListener) {
            DefaultTrailingLoadStateAdapter defaultTrailingLoadStateAdapter = new DefaultTrailingLoadStateAdapter(false, 1, null);
            defaultTrailingLoadStateAdapter.setOnLoadMoreListener(loadMoreListener);
            return setTrailingLoadStateAdapter(defaultTrailingLoadStateAdapter);
        }

        public final Builder setLeadingLoadStateAdapter(LeadingLoadStateAdapter<?> loadStateAdapter) {
            this.leadingLoadStateAdapter = loadStateAdapter;
            return this;
        }

        public final Builder setLeadingLoadStateAdapter(LeadingLoadStateAdapter.OnLeadingListener loadListener) {
            DefaultLeadingLoadStateAdapter defaultLeadingLoadStateAdapter = new DefaultLeadingLoadStateAdapter();
            defaultLeadingLoadStateAdapter.setOnLeadingListener(loadListener);
            return setLeadingLoadStateAdapter(defaultLeadingLoadStateAdapter);
        }

        public final Builder setConfig(ConcatAdapter.Config config) {
            Intrinsics.checkNotNullParameter(config, "config");
            this.config = config;
            return this;
        }

        public final QuickAdapterHelper build() {
            return new QuickAdapterHelper(this.contentAdapter, this.leadingLoadStateAdapter, this.trailingLoadStateAdapter, this.config, null);
        }

        public final QuickAdapterHelper attachTo(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            QuickAdapterHelper quickAdapterHelper = new QuickAdapterHelper(this.contentAdapter, this.leadingLoadStateAdapter, this.trailingLoadStateAdapter, this.config, null);
            recyclerView.setAdapter(quickAdapterHelper.getMAdapter());
            return quickAdapterHelper;
        }
    }
}
