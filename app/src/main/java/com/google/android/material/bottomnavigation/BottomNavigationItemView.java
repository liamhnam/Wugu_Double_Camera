package com.google.android.material.bottomnavigation;

import android.content.Context;
import com.google.android.material.C1156R;
import com.google.android.material.navigation.NavigationBarItemView;

public class BottomNavigationItemView extends NavigationBarItemView {
    public BottomNavigationItemView(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId() {
        return C1156R.layout.design_bottom_navigation_item;
    }

    @Override
    protected int getItemDefaultMarginResId() {
        return C1156R.dimen.design_bottom_navigation_margin;
    }
}
