package com.imagine;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Handler;

import com.imagine.moga.helper.c;
import com.imagine.moga.helper.j;
import com.imagine.moga.helper.t;
import com.imagine.moga.helper.v;
import com.imagine.moga.helper.x;

import java.util.List;

final class MOGAHelper implements j {
    private c a;

    @SuppressLint("WrongConstant")
    MOGAHelper(Context context) {
        this.a = new c(context);
        c cVar = this.a;
        if (!cVar.b) {
            Intent intent = new Intent("com.bda.controller.IControllerService");
            List queryIntentServices = cVar.a.getPackageManager().queryIntentServices(intent, 0);
            if (queryIntentServices.size() != 1) {
                throw new SecurityException();
            }
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentServices.get(0);
            intent.setComponent(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
            cVar.a.startService(intent);
            cVar.a.bindService(intent, cVar.e, 129);
            cVar.b = true;
        }
        this.a.a(this, new Handler());
    }

    private native void keyEvent(int i, int i2, long j);

    private native void motionEvent(float f, float f2, float f3, float f4, float f5, float f6, long j);

    private native void stateEvent(int i, int i2);

    public final void a(t tVar) {
        keyEvent(tVar.d, tVar.c, tVar.a);
    }

    public final void a(v vVar) {
        motionEvent(vVar.a(0), vVar.a(1), vVar.a(11), vVar.a(14), vVar.a(17), vVar.a(18), vVar.a);
    }

    public final void a(x xVar) {
        stateEvent(xVar.c, xVar.d);
    }

    final void exit() {
        c cVar = this.a;
        cVar.a(null, null);
        if (cVar.c != null) {
            cVar.c.b(cVar.d, cVar.f);
        }
        if (cVar.b) {
            cVar.a.unbindService(cVar.e);
            cVar.b = false;
        }
        this.a = null;
    }

    final int getState(int i) {
        return this.a.a(i);
    }

    final void onPause() {
        c cVar = this.a;
        cVar.f = 6;
        cVar.b(cVar.f);
        cVar.a();
    }

    final void onResume() {
        c cVar = this.a;
        cVar.f = 5;
        cVar.b(cVar.f);
        cVar.a();
    }
}
