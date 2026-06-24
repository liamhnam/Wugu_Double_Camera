package com.chad.library.adapter4.loadState.trailing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.C0844R;
import com.chad.library.adapter4.loadState.LoadState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0010B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u0011"}, m1293d2 = {"Lcom/chad/library/adapter4/loadState/trailing/DefaultTrailingLoadStateAdapter;", "Lcom/chad/library/adapter4/loadState/trailing/TrailingLoadStateAdapter;", "Lcom/chad/library/adapter4/loadState/trailing/DefaultTrailingLoadStateAdapter$TrailingLoadStateVH;", "isLoadEndDisplay", "", "(Z)V", "getStateViewType", "", "loadState", "Lcom/chad/library/adapter4/loadState/LoadState;", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "TrailingLoadStateVH", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class DefaultTrailingLoadStateAdapter extends TrailingLoadStateAdapter<TrailingLoadStateVH> {
    public DefaultTrailingLoadStateAdapter() {
        this(false, 1, null);
    }

    public DefaultTrailingLoadStateAdapter(boolean z) {
        super(z);
    }

    public DefaultTrailingLoadStateAdapter(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    @Metadata(m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\t¨\u0006\u0010"}, m1293d2 = {"Lcom/chad/library/adapter4/loadState/trailing/DefaultTrailingLoadStateAdapter$TrailingLoadStateVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "view", "Landroid/view/View;", "(Landroid/view/ViewGroup;Landroid/view/View;)V", "loadCompleteView", "getLoadCompleteView", "()Landroid/view/View;", "loadEndView", "getLoadEndView", "loadFailView", "getLoadFailView", "loadingView", "getLoadingView", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public static final class TrailingLoadStateVH extends RecyclerView.ViewHolder {
        private final View loadCompleteView;
        private final View loadEndView;
        private final View loadFailView;
        private final View loadingView;

        public TrailingLoadStateVH(ViewGroup viewGroup, View view, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 2) != 0) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(C0844R.layout.brvah_trailing_load_more, viewGroup, false);
                Intrinsics.checkNotNullExpressionValue(view, "from(parent.context).inf…load_more, parent, false)");
            }
            this(viewGroup, view);
        }

        public TrailingLoadStateVH(ViewGroup parent, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(view, "view");
            View viewFindViewById = this.itemView.findViewById(C0844R.id.load_more_load_complete_view);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.…_more_load_complete_view)");
            this.loadCompleteView = viewFindViewById;
            View viewFindViewById2 = this.itemView.findViewById(C0844R.id.load_more_loading_view);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.id.load_more_loading_view)");
            this.loadingView = viewFindViewById2;
            View viewFindViewById3 = this.itemView.findViewById(C0844R.id.load_more_load_fail_view);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "itemView.findViewById(R.…load_more_load_fail_view)");
            this.loadFailView = viewFindViewById3;
            View viewFindViewById4 = this.itemView.findViewById(C0844R.id.load_more_load_end_view);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "itemView.findViewById(R.….load_more_load_end_view)");
            this.loadEndView = viewFindViewById4;
        }

        public final View getLoadCompleteView() {
            return this.loadCompleteView;
        }

        public final View getLoadingView() {
            return this.loadingView;
        }

        public final View getLoadFailView() {
            return this.loadFailView;
        }

        public final View getLoadEndView() {
            return this.loadEndView;
        }
    }

    @Override
    public TrailingLoadStateVH onCreateViewHolder(ViewGroup parent, LoadState loadState) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        TrailingLoadStateVH trailingLoadStateVH = new TrailingLoadStateVH(parent, null, 2, 0 == true ? 1 : 0);
        trailingLoadStateVH.getLoadFailView().setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DefaultTrailingLoadStateAdapter.onCreateViewHolder$lambda$2$lambda$0(this.f$0, view);
            }
        });
        trailingLoadStateVH.getLoadCompleteView().setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DefaultTrailingLoadStateAdapter.onCreateViewHolder$lambda$2$lambda$1(this.f$0, view);
            }
        });
        return trailingLoadStateVH;
    }

    public static final void onCreateViewHolder$lambda$2$lambda$0(DefaultTrailingLoadStateAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.invokeFailRetry();
    }

    public static final void onCreateViewHolder$lambda$2$lambda$1(DefaultTrailingLoadStateAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.invokeLoadMore();
    }

    @Override
    public void onBindViewHolder(TrailingLoadStateVH holder, LoadState loadState) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        if (loadState instanceof LoadState.NotLoading) {
            if (loadState.getEndOfPaginationReached()) {
                holder.getLoadCompleteView().setVisibility(8);
                holder.getLoadingView().setVisibility(8);
                holder.getLoadFailView().setVisibility(8);
                holder.getLoadEndView().setVisibility(0);
                return;
            }
            holder.getLoadCompleteView().setVisibility(0);
            holder.getLoadingView().setVisibility(8);
            holder.getLoadFailView().setVisibility(8);
            holder.getLoadEndView().setVisibility(8);
            return;
        }
        if (loadState instanceof LoadState.Loading) {
            holder.getLoadCompleteView().setVisibility(8);
            holder.getLoadingView().setVisibility(0);
            holder.getLoadFailView().setVisibility(8);
            holder.getLoadEndView().setVisibility(8);
            return;
        }
        if (loadState instanceof LoadState.Error) {
            holder.getLoadCompleteView().setVisibility(8);
            holder.getLoadingView().setVisibility(8);
            holder.getLoadFailView().setVisibility(0);
            holder.getLoadEndView().setVisibility(8);
            return;
        }
        if (loadState instanceof LoadState.None) {
            holder.getLoadCompleteView().setVisibility(8);
            holder.getLoadingView().setVisibility(8);
            holder.getLoadFailView().setVisibility(8);
            holder.getLoadEndView().setVisibility(8);
        }
    }

    @Override
    public int getStateViewType(LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        return C0844R.layout.brvah_trailing_load_more;
    }
}
