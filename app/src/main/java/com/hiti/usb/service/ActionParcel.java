package com.hiti.usb.service;

import android.os.Parcel;
import android.os.Parcelable;

public class ActionParcel implements Parcelable {
    public final Parcelable.Creator<ActionParcel> CREATOR = new Parcelable.Creator<ActionParcel>() {
        @Override
        public ActionParcel createFromParcel(Parcel parcel) {
            return new ActionParcel(Action.valueOf(parcel.readString()), parcel.readInt(), (ErrorCode) parcel.readValue(null), parcel.readValue(null));
        }

        @Override
        public ActionParcel[] newArray(int i) {
            return new ActionParcel[i];
        }
    };
    public final Action action;
    public Object data;
    public ErrorCode erroCode;
    public int jobId;

    ActionParcel(Action action) {
        this.action = action;
    }

    ActionParcel(Action action, int i, ErrorCode errorCode, Object obj) {
        this.action = action;
        this.jobId = i;
        setErrorCode(errorCode);
        setData(obj);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ActionParcel setData(Object obj) {
        this.data = obj;
        return this;
    }

    public ActionParcel setErrorCode(ErrorCode errorCode) {
        this.erroCode = errorCode;
        return this;
    }

    public ActionParcel setJobId(int i) {
        this.jobId = i;
        return this;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.action.name());
        parcel.writeInt(this.jobId);
        parcel.writeValue(this.erroCode);
        parcel.writeValue(this.data);
    }
}
