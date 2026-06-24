package com.wugu.doublecamera.widget;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class NotStickMutableLiveData<T> extends MutableLiveData<T> {
    @Override
    public void observe(LifecycleOwner lifecycleOwner, Observer<? super T> observer) {
        super.observe(lifecycleOwner, observer);
        try {
            hook(observer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void hook(Observer observer) throws Exception {
        Field declaredField = LiveData.class.getDeclaredField("mObservers");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(this);
        Method declaredMethod = obj.getClass().getDeclaredMethod("get", Object.class);
        declaredMethod.setAccessible(true);
        Object objInvoke = declaredMethod.invoke(obj, observer);
        Object value = objInvoke instanceof Map.Entry ? ((Map.Entry) objInvoke).getValue() : null;
        if (value == null) {
            throw new NullPointerException("Wrapper can not be bull!");
        }
        Field declaredField2 = value.getClass().getSuperclass().getDeclaredField("mLastVersion");
        declaredField2.setAccessible(true);
        Field declaredField3 = LiveData.class.getDeclaredField("mVersion");
        declaredField3.setAccessible(true);
        declaredField2.set(value, declaredField3.get(this));
    }
}
