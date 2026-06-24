package com.wugu.doublecamera.bean;

import android.text.TextUtils;
import com.wugu.doublecamera.database.dbSheet.UiPositionEntity;

public class UiPosition {
    public boolean isVisible;
    public String resPath;

    public int f2441x;

    public int f2442y;

    public UiPosition(int i, int i2, String str) {
        this.isVisible = true;
        this.f2441x = i;
        this.f2442y = i2;
        this.resPath = str;
    }

    public UiPosition(UiPositionEntity uiPositionEntity) {
        this.isVisible = true;
        this.f2441x = uiPositionEntity.getX();
        this.f2442y = uiPositionEntity.getY();
        if (TextUtils.isEmpty(uiPositionEntity.getResPath())) {
            this.resPath = uiPositionEntity.getDefaultResPath();
        } else {
            this.resPath = uiPositionEntity.getResPath();
        }
        this.isVisible = uiPositionEntity.getIsVisible();
    }
}
