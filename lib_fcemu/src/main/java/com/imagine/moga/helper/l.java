package com.imagine.moga.helper;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class l extends Binder implements k {
    public l() {
        attachInterface(this, "com.bda.controller.IControllerListener");
    }

    public static k a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.bda.controller.IControllerListener");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof k)) ? new m(iBinder) : (k) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        x xVar = null;
        switch (i) {
            case 1:
                t tVar = null;
                parcel.enforceInterface("com.bda.controller.IControllerListener");
                if (parcel.readInt() != 0) {
                    tVar = (t) t.CREATOR.createFromParcel(parcel);
                }
                a(tVar);
                parcel2.writeNoException();
                return true;
            case 2:
                v vVar = null;
                parcel.enforceInterface("com.bda.controller.IControllerListener");
                if (parcel.readInt() != 0) {
                    vVar = (v) v.CREATOR.createFromParcel(parcel);
                }
                a(vVar);
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("com.bda.controller.IControllerListener");
                if (parcel.readInt() != 0) {
                    xVar = (x) x.CREATOR.createFromParcel(parcel);
                }
                a(xVar);
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("com.bda.controller.IControllerListener");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
