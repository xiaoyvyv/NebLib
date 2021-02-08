package com.imagine.moga.helper;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

public final class v extends com.imagine.moga.helper.a implements Parcelable {
    public static final Creator CREATOR = new w();
    private SparseArray c;
    private SparseArray d;

    v(Parcel parcel) {
        super(parcel);
        int i = 0;
        int readInt = parcel.readInt();
        this.c = new SparseArray(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            this.c.put(parcel.readInt(), parcel.readFloat());
        }
        this.d = new SparseArray(parcel.readInt());
        while (i < readInt) {
            this.d.put(parcel.readInt(), parcel.readFloat());
            i++;
        }
    }

    public final float a(int i) {
        return ((Float) this.c.get(i, Float.valueOf(0.0f))).floatValue();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        super.writeToParcel(parcel, i);
        int size = this.c.size();
        parcel.writeInt(size);
        for (i2 = 0; i2 < size; i2++) {
            parcel.writeInt(this.c.keyAt(i2));
            parcel.writeFloat(((Float) this.c.valueAt(i2)).floatValue());
        }
        i2 = this.d.size();
        parcel.writeInt(i2);
        while (i3 < i2) {
            parcel.writeInt(this.d.keyAt(i3));
            parcel.writeFloat(((Float) this.d.valueAt(i3)).floatValue());
            i3++;
        }
    }
}
