package com.imagine.moga.helper;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class s implements q {
    private IBinder a;

    s(IBinder iBinder) {
        this.a = iBinder;
    }

    public final int a(int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeInt(i);
            this.a.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            int readInt = obtain2.readInt();
            return readInt;
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
        return 0;
    }

    public final int a(int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.a.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            int readInt = obtain2.readInt();
            return readInt;
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
        return 0;
    }

    public final void a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            try {
                this.a.transact(12, obtain, obtain2, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void a(k kVar, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeStrongBinder(kVar != null ? kVar.asBinder() : null);
            obtain.writeInt(i);
            try {
                this.a.transact(1, obtain, obtain2, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void a(n nVar, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeStrongBinder(nVar != null ? nVar.asBinder() : null);
            obtain.writeInt(i);
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        }   catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.a;
    }

    public final float b(int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.a.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            float readFloat = obtain2.readFloat();
            return readFloat;
        }   catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            obtain2.recycle();
            obtain.recycle();
        }
        return 0;
    }

    public final void b() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            this.a.transact(13, obtain, obtain2, 0);
            obtain2.readException();
        }   catch (RemoteException e) {
        e.printStackTrace();
    }finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void b(k kVar, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeStrongBinder(kVar != null ? kVar.asBinder() : null);
            obtain.writeInt(i);
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        }   catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void b(n nVar, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeStrongBinder(nVar != null ? nVar.asBinder() : null);
            obtain.writeInt(i);
            this.a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        }   catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final int c(int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.a.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            int readInt = obtain2.readInt();
            return readInt;
        }   catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            obtain2.recycle();
            obtain.recycle();
        }
        return 0;
    }

    public final void c(k kVar, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeStrongBinder(kVar != null ? kVar.asBinder() : null);
            obtain.writeInt(i);
            this.a.transact(10, obtain, obtain2, 0);
            obtain2.readException();
        }   catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final boolean c() {
        boolean z = false;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            this.a.transact(14, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
            obtain2.recycle();
            obtain.recycle();
            return z;
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
        }
        return false;
    }

    public final void d(int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.a.transact(9, obtain, obtain2, 0);
            obtain2.readException();
        }   catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final int e(int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.bda.controller.IControllerService");
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.a.transact(11, obtain, obtain2, 0);
            obtain2.readException();
            int readInt = obtain2.readInt();
            return readInt;
        }   catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            obtain2.recycle();
            obtain.recycle();
        }
        return 0;
    }
}
