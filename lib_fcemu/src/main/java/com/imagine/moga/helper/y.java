package com.imagine.moga.helper;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class y implements Creator {
    y() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new x(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new x[i];
    }
}
