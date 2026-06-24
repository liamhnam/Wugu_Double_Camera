package com.epson.isv.eprinterdriver.Ctrl;

import android.os.Parcel;
import android.os.Parcelable;

class JobIDParcel implements Parcelable {
    public static final Parcelable.Creator<JobIDParcel> CREATOR = new Parcelable.Creator<JobIDParcel>() {
        @Override
        public JobIDParcel createFromParcel(Parcel parcel) {
            return new JobIDParcel(parcel);
        }

        @Override
        public JobIDParcel[] newArray(int i) {
            return new JobIDParcel[i];
        }
    };
    int jobID;

    @Override
    public int describeContents() {
        return 0;
    }

    public JobIDParcel() {
    }

    public JobIDParcel(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.jobID = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.jobID);
    }

    public void setJobID(int i) {
        this.jobID = i;
    }

    public JobIDParcel(int i) {
        this.jobID = i;
    }

    public int getJobID() {
        return this.jobID;
    }
}
