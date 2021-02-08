package com.imagine.moga.helper;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

final class h implements ServiceConnection {
    private /* synthetic */ c a;

    h(c cVar) {
        this.a = cVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.c = r.a(iBinder);
        this.a.a();
        if (this.a.f == 5) {
            this.a.b(5);
            this.a.b(7);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.c = null;
    }
}
