package com.imagine.moga.helper;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class p implements n {
    private IBinder a;

    p(IBinder iBinder) {
        this.a = iBinder;
    }

    public final void a(int i, int i2, String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerMonitor");
            obtain.writeInt(i);
            obtain.writeInt(i2);
            obtain.writeString(str);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.a;
    }
}
