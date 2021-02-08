package com.imagine.moga.helper;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class m implements k {
    private IBinder a;

    m(IBinder iBinder) {
        this.a = iBinder;
    }

    public final void a(t tVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerListener");
            if (tVar != null) {
                obtain.writeInt(1);
                tVar.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void a(v vVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerListener");
            if (vVar != null) {
                obtain.writeInt(1);
                vVar.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void a(x xVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerListener");
            if (xVar != null) {
                obtain.writeInt(1);
                xVar.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(3, obtain, obtain2, 0);
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
