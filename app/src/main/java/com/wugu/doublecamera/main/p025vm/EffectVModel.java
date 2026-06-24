package com.wugu.doublecamera.main.p025vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.bean.PhotoAddedItem;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.dbSheet.EffectEntity;
import com.wugu.doublecamera.database.dbSheet.FilterEntity;
import com.wugu.doublecamera.database.dbSheet.MakeupEntity;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.ArrayList;
import java.util.List;

public class EffectVModel extends ViewModel {
    private final MutableLiveData<List<PhotoAddedItem>> photoAddedListLD = new MutableLiveData<>();

    public LiveData<List<PhotoAddedItem>> getPhotoAddedListLD() {
        return this.photoAddedListLD;
    }

    public void queryFilterList() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1154xebbcf4df();
            }
        });
    }

    void m1154xebbcf4df() {
        List<FilterEntity> allFilter = DbHelper.getInstance().getAllFilter();
        ArrayList arrayList = new ArrayList();
        for (FilterEntity filterEntity : allFilter) {
            if (filterEntity.getIsEnable()) {
                arrayList.add(new PhotoAddedItem(1, filterEntity.getFilterName(), filterEntity.getFilterImagePath()));
            }
        }
        this.photoAddedListLD.postValue(arrayList);
    }

    public void queryMakeupList() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1155x178e90cd();
            }
        });
    }

    void m1155x178e90cd() {
        List<MakeupEntity> allMakeup = DbHelper.getInstance().getAllMakeup();
        ArrayList arrayList = new ArrayList();
        for (MakeupEntity makeupEntity : allMakeup) {
            if (makeupEntity.getIsEnable()) {
                arrayList.add(new PhotoAddedItem(2, makeupEntity.getMakeupName(), makeupEntity.getMakeupDemoPath()));
            }
        }
        this.photoAddedListLD.postValue(arrayList);
    }

    public void queryEffectList() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1153x571ad2a4();
            }
        });
    }

    void m1153x571ad2a4() {
        List<EffectEntity> allEffect = DbHelper.getInstance().getAllEffect();
        ArrayList arrayList = new ArrayList();
        for (EffectEntity effectEntity : allEffect) {
            if (effectEntity.getIsEnable()) {
                arrayList.add(new PhotoAddedItem(effectEntity.getEffectType(), effectEntity.getEffectName(), effectEntity.getEffectDemoUrl()));
            }
        }
        this.photoAddedListLD.postValue(arrayList);
    }
}
