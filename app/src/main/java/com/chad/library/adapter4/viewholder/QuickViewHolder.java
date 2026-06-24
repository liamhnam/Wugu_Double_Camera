package com.chad.library.adapter4.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001f\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u000e\u001a\u00020\u0003J\u0010\u0010\u0013\u001a\u00020\u00122\b\b\u0001\u0010\u000e\u001a\u00020\u0003J\u001a\u0010\u0014\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u0003J\u001a\u0010\u0016\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\b\u0001\u0010\u0017\u001a\u00020\u0003J\u0018\u0010\u0018\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u0019\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0012J\u001a\u0010\u001b\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u001a\u0010\u001e\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u001a\u0010!\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\b\u0001\u0010\"\u001a\u00020\u0003J\u0018\u0010#\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0012J\u001a\u0010$\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\u0010%\u001a\u0004\u0018\u00010&J\u001a\u0010$\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\b\u0001\u0010'\u001a\u00020\u0003J\u001a\u0010(\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u0003J\u001a\u0010)\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\b\b\u0001\u0010*\u001a\u00020\u0003J\u0018\u0010+\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0012J\u001b\u0010-\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\b*\u00020\u0003¢\u0006\u0002\u0010\u000fR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, m1293d2 = {"Lcom/chad/library/adapter4/viewholder/QuickViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "resId", "", "parent", "Landroid/view/ViewGroup;", "(ILandroid/view/ViewGroup;)V", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "views", "Landroid/util/SparseArray;", "getView", "T", "viewId", "(I)Landroid/view/View;", "getViewOrNull", "isEnabled", "", "isSelected", "setBackgroundColor", "color", "setBackgroundResource", "backgroundRes", "setEnabled", "setGone", "isGone", "setImageBitmap", "bitmap", "Landroid/graphics/Bitmap;", "setImageDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "setImageResource", "imageResId", "setSelected", "setText", "value", "", "strId", "setTextColor", "setTextColorRes", "colorRes", "setVisible", "isVisible", "findView", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public class QuickViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> views;

    public QuickViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        this.views = new SparseArray<>();
    }

    public QuickViewHolder(int i, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(i, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "from(parent.context).inflate(resId, parent, false)");
        this(viewInflate);
    }

    public final <T extends View> T getView(int viewId) {
        T t = (T) getViewOrNull(viewId);
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(("No view found with id " + viewId).toString());
    }

    public final <T extends View> T getViewOrNull(int viewId) {
        T t = (T) this.views.get(viewId);
        if (t != null) {
            if (t instanceof View) {
                return t;
            }
            return null;
        }
        T t2 = (T) this.itemView.findViewById(viewId);
        if (t2 == null) {
            return null;
        }
        this.views.put(viewId, t2);
        return t2;
    }

    public final <T extends View> T findView(int i) {
        return (T) this.itemView.findViewById(i);
    }

    public final QuickViewHolder setText(int viewId, CharSequence value) {
        ((TextView) getView(viewId)).setText(value);
        return this;
    }

    public final QuickViewHolder setText(int viewId, int strId) {
        ((TextView) getView(viewId)).setText(strId);
        return this;
    }

    public final QuickViewHolder setTextColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(color);
        return this;
    }

    public final QuickViewHolder setTextColorRes(int viewId, int colorRes) {
        ((TextView) getView(viewId)).setTextColor(ContextCompat.getColor(this.itemView.getContext(), colorRes));
        return this;
    }

    public final QuickViewHolder setImageResource(int viewId, int imageResId) {
        ((ImageView) getView(viewId)).setImageResource(imageResId);
        return this;
    }

    public final QuickViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ((ImageView) getView(viewId)).setImageDrawable(drawable);
        return this;
    }

    public final QuickViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ((ImageView) getView(viewId)).setImageBitmap(bitmap);
        return this;
    }

    public final QuickViewHolder setBackgroundColor(int viewId, int color) {
        getView(viewId).setBackgroundColor(color);
        return this;
    }

    public final QuickViewHolder setBackgroundResource(int viewId, int backgroundRes) {
        getView(viewId).setBackgroundResource(backgroundRes);
        return this;
    }

    public final QuickViewHolder setVisible(int viewId, boolean isVisible) {
        getView(viewId).setVisibility(isVisible ? 0 : 4);
        return this;
    }

    public final QuickViewHolder setGone(int viewId, boolean isGone) {
        getView(viewId).setVisibility(isGone ? 8 : 0);
        return this;
    }

    public final QuickViewHolder setEnabled(int viewId, boolean isEnabled) {
        getView(viewId).isSelected();
        getView(viewId).setEnabled(isEnabled);
        return this;
    }

    public final boolean isEnabled(int viewId) {
        return getView(viewId).isEnabled();
    }

    public final QuickViewHolder setSelected(int viewId, boolean isSelected) {
        getView(viewId).setSelected(isSelected);
        return this;
    }

    public final boolean isSelected(int viewId) {
        return getView(viewId).isSelected();
    }
}
