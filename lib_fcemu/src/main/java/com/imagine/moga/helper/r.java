package com.imagine.moga.helper;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class r extends Binder implements q {
    public static q a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.bda.controller.IControllerService");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof q)) ? new s(iBinder) : (q) queryLocalInterface;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        int a;
        switch (i) {
            case 1:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                a(l.a(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                b(l.a(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                a(o.a(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                b(o.a(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                a = a(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 6:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                a = a(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 7:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                float b = b(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeFloat(b);
                return true;
            case 8:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                a = c(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 9:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                d(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                c(l.a(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 11:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                a = e(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 12:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                a();
                parcel2.writeNoException();
                return true;
            case 13:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                b();
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("com.bda.controller.IControllerService");
                boolean c = c();
                parcel2.writeNoException();
                parcel2.writeInt(c ? 1 : 0);
                return true;
            case 1598968902:
                parcel2.writeString("com.bda.controller.IControllerService");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
