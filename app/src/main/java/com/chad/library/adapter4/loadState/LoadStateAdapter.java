package com.chad.library.adapter4.loadState;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.chad.library.adapter4.fullspan.FullSpanAdapterType;
import com.chad.library.adapter4.loadState.LoadState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004:\u00012B\u0005¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0011J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dJ\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0014H\u0017J\u001d\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010$J\u001b\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\u001d¢\u0006\u0002\u0010%J)\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\u001d2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'¢\u0006\u0002\u0010)J\u001d\u0010*\u001a\u00028\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010-J\u001b\u0010*\u001a\u00028\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010.\u001a\u00020\u001d¢\u0006\u0002\u0010/J\u0010\u00100\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0014H\u0017J\u000e\u00101\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0011R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR$\u0010\t\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u00063"}, m1293d2 = {"Lcom/chad/library/adapter4/loadState/LoadStateAdapter;", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/chad/library/adapter4/fullspan/FullSpanAdapterType;", "()V", "isLoading", "", "()Z", "loadState", "Lcom/chad/library/adapter4/loadState/LoadState;", "getLoadState", "()Lcom/chad/library/adapter4/loadState/LoadState;", "setLoadState", "(Lcom/chad/library/adapter4/loadState/LoadState;)V", "loadStateListeners", "Ljava/util/ArrayList;", "Lcom/chad/library/adapter4/loadState/LoadStateAdapter$LoadStateListener;", "Lkotlin/collections/ArrayList;", "<set-?>", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "addLoadStateListener", "", "listener", "displayLoadStateAsItem", "getItemCount", "", "getItemViewType", "position", "getStateViewType", "onAttachedToRecyclerView", "onBindViewHolder", "holder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;Lcom/chad/library/adapter4/loadState/LoadState;)V", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "payloads", "", "", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/util/List;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;Lcom/chad/library/adapter4/loadState/LoadState;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "viewType", "(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onDetachedFromRecyclerView", "removeLoadStateListener", "LoadStateListener", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public abstract class LoadStateAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements FullSpanAdapterType {
    private LoadState loadState = LoadState.None.INSTANCE;
    private final ArrayList<LoadStateListener> loadStateListeners = new ArrayList<>(0);
    private RecyclerView recyclerView;

    @Metadata(m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/loadState/LoadStateAdapter$LoadStateListener;", "", "loadState", "", "previousState", "Lcom/chad/library/adapter4/loadState/LoadState;", "currentState", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface LoadStateListener {
        void loadState(LoadState previousState, LoadState currentState);
    }

    public int getStateViewType(LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        return 0;
    }

    public abstract void onBindViewHolder(VH holder, LoadState loadState);

    public abstract VH onCreateViewHolder(ViewGroup parent, LoadState loadState);

    public final LoadState getLoadState() {
        return this.loadState;
    }

    public final void setLoadState(LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        if (Intrinsics.areEqual(this.loadState, loadState)) {
            return;
        }
        LoadState loadState2 = this.loadState;
        boolean zDisplayLoadStateAsItem = displayLoadStateAsItem(loadState2);
        boolean zDisplayLoadStateAsItem2 = displayLoadStateAsItem(loadState);
        if (zDisplayLoadStateAsItem && !zDisplayLoadStateAsItem2) {
            notifyItemRemoved(0);
        } else if (zDisplayLoadStateAsItem2 && !zDisplayLoadStateAsItem) {
            notifyItemInserted(0);
        } else if (zDisplayLoadStateAsItem && zDisplayLoadStateAsItem2) {
            notifyItemChanged(0);
        }
        this.loadState = loadState;
        Iterator<T> it = this.loadStateListeners.iterator();
        while (it.hasNext()) {
            ((LoadStateListener) it.next()).loadState(loadState2, loadState);
        }
    }

    public final boolean isLoading() {
        return Intrinsics.areEqual(this.loadState, LoadState.Loading.INSTANCE);
    }

    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return (VH) onCreateViewHolder(parent, this.loadState);
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        onBindViewHolder(holder, this.loadState);
    }

    @Override
    public final void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public final int getItemViewType(int position) {
        return getStateViewType(this.loadState);
    }

    @Override
    public final int getItemCount() {
        return displayLoadStateAsItem(this.loadState) ? 1 : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        this.recyclerView = null;
    }

    public boolean displayLoadStateAsItem(LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        return (loadState instanceof LoadState.Loading) || (loadState instanceof LoadState.Error);
    }

    public final void addLoadStateListener(LoadStateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.loadStateListeners.add(listener);
    }

    public final void removeLoadStateListener(LoadStateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.loadStateListeners.remove(listener);
    }
}
