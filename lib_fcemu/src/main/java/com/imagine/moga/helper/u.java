package com.imagine.moga.helper;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class u implements Creator {
    u() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new t(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new t[i];
    }
}
