package com.chad.library.adapter4.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter4.fullspan.FullSpanAdapterType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \f2\u00020\u00012\u00020\u0002:\u0001\fB!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m1293d2 = {"Lcom/chad/library/adapter4/viewholder/StateLayoutVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/chad/library/adapter4/fullspan/FullSpanAdapterType;", "parent", "Landroid/view/ViewGroup;", "stateView", "Landroid/view/View;", "stateLayout", "Landroid/widget/FrameLayout;", "(Landroid/view/ViewGroup;Landroid/view/View;Landroid/widget/FrameLayout;)V", "changeStateView", "", "Companion", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class StateLayoutVH extends RecyclerView.ViewHolder implements FullSpanAdapterType {

    public static final Companion INSTANCE = new Companion(null);
    private final FrameLayout stateLayout;

    public StateLayoutVH(ViewGroup viewGroup, View view, FrameLayout frameLayout, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            frameLayout = new FrameLayout(viewGroup.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            INSTANCE.setStateView(frameLayout, view);
        }
        this(viewGroup, view, frameLayout);
    }

    public StateLayoutVH(ViewGroup parent, View view, FrameLayout stateLayout) {
        super(stateLayout);
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(stateLayout, "stateLayout");
        this.stateLayout = stateLayout;
    }

    public final void changeStateView(View stateView) {
        INSTANCE.setStateView(this.stateLayout, stateView);
    }

    @Metadata(m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\t"}, m1293d2 = {"Lcom/chad/library/adapter4/viewholder/StateLayoutVH$Companion;", "", "()V", "setStateView", "", "rootView", "Landroid/view/ViewGroup;", "stateView", "Landroid/view/View;", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void setStateView(ViewGroup rootView, View stateView) {
            if (stateView == null) {
                rootView.removeAllViews();
                return;
            }
            ViewParent parent = stateView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(stateView);
            }
            if (stateView.getLayoutParams() == null) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 17;
                stateView.setLayoutParams(layoutParams);
            }
            rootView.removeAllViews();
            rootView.addView(stateView);
        }
    }
}
