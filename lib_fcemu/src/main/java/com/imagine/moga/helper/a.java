package com.imagine.moga.helper;

import android.os.Parcel;
import android.os.Parcelable;

public class a implements Parcelable {
    public static final Creator CREATOR = new b();
    public final long a;
    final int b;

    a(Parcel parcel) {
        this.a = parcel.readLong();
        this.b = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.a);
        parcel.writeInt(this.b);
    }
}
