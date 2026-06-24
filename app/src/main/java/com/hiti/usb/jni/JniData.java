package com.hiti.usb.jni;

import com.hiti.usb.service.ErrorCode;
import java.util.ArrayList;

public class JniData {
    private ErrorCode errCode;
    private Object retData = null;

    public static class IntArray {
        private ArrayList<Integer> list;

        public IntArray(int i) {
            this.list = new ArrayList<>(i);
        }

        public void add(int i) {
            this.list.add(Integer.valueOf(i));
        }

        public int get(int i) {
            return this.list.get(i).intValue();
        }

        public int getSize() {
            return this.list.size();
        }
    }

    public JniData(int i, Object obj) {
        this.errCode = new ErrorCode(i);
        setRetData(obj);
    }

    public JniData(ErrorCode errorCode, Object obj) {
        setErrorCode(errorCode);
        setRetData(obj);
    }

    public ErrorCode getErrorCode() {
        return this.errCode;
    }

    public Object getRetData() {
        return this.retData;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errCode = errorCode;
    }

    public void setRetData(Object obj) {
        this.retData = obj;
    }
}
