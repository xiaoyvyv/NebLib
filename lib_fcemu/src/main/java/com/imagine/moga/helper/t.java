package com.imagine.moga.helper;

import android.os.Parcel;
import android.os.Parcelable;

public final class t extends a implements Parcelable {
    public static final Creator CREATOR = new u();
    public final int c;
    public final int d;

    t(Parcel parcel) {
        super(parcel);
        this.c = parcel.readInt();
        this.d = parcel.readInt();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
    }
}
