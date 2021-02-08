package com.imagine.moga.helper;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class o extends Binder implements n {
    public o() {
        attachInterface(this, "com.bda.controller.IControllerMonitor");
    }

    public static n a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.bda.controller.IControllerMonitor");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof n)) ? new p(iBinder) : (n) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.bda.controller.IControllerMonitor");
                a(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("com.bda.controller.IControllerMonitor");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
