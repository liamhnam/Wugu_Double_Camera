package com.chad.library.adapter4.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class DataBindingHolder<DB extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final DB binding;

    public DataBindingHolder(DB db) {
        super(db.getRoot());
        this.binding = db;
    }

    public DataBindingHolder(View view) {
        super(view);
        DB db = (DB) DataBindingUtil.bind(view);
        this.binding = db;
        if (db == null) {
            throw new NullPointerException("DataBinding is Null. Please check Layout resource or ItemView");
        }
    }

    public DataBindingHolder(int i, ViewGroup viewGroup) {
        this(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    public DB getBinding() {
        return this.binding;
    }
}
