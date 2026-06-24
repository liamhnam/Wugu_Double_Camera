package com.wugu.doublecamera.main.p025vm;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.bean.StickerTab;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.dbSheet.StickerEntity;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.ArrayList;
import java.util.List;

public class StickerVModel extends ViewModel {
    private final MutableLiveData<List<StickerTab>> stickerFolderLD = new MutableLiveData<>();
    private final MutableLiveData<List<String>> stickerListLD = new MutableLiveData<>();

    public LiveData<List<StickerTab>> getStickerFolderLD() {
        return this.stickerFolderLD;
    }

    public LiveData<List<String>> getStickerListLD() {
        return this.stickerListLD;
    }

    public void queryStickerFolders() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1168x665ae047();
            }
        });
    }

    void m1168x665ae047() {
        List<StickerEntity> allStickers = DbHelper.getInstance().getAllStickers();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (StickerEntity stickerEntity : allStickers) {
            if (stickerEntity.getIsEnable() && !TextUtils.isEmpty(stickerEntity.getLocalPath()) && !arrayList2.contains(stickerEntity.getFolderName())) {
                arrayList2.add(stickerEntity.getFolderName());
                arrayList.add(new StickerTab(stickerEntity.getFolderName(), stickerEntity.getFolderIconPath()));
            }
        }
        this.stickerFolderLD.postValue(arrayList);
    }

    public void queryStickerList(final String str) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1169x55da1c85(str);
            }
        });
    }

    void m1169x55da1c85(String str) {
        List<StickerEntity> stickerByFolder = DbHelper.getInstance().getStickerByFolder(str);
        ArrayList arrayList = new ArrayList();
        for (StickerEntity stickerEntity : stickerByFolder) {
            if (stickerEntity.getIsEnable() && !TextUtils.isEmpty(stickerEntity.getLocalPath())) {
                arrayList.add(stickerEntity.getLocalPath());
            }
        }
        this.stickerListLD.postValue(arrayList);
    }
}
