package com.imagine.moga.helper;

import android.content.Context;
import android.os.Handler;

public final class c {
    public final Context a;
    public boolean b = false;
    public q c = null;
    public o d = new e();
    public final h e = new h(this);
    public int f = 6;
    Handler g = null;
    j h = null;
    private l i = new d(this);

    public c(Context context) {
        this.a = context;
    }

    public final int a(int i) {
        if (this.c != null) {
            try {
                return this.c.c(1, i);
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public final void a() {
        if (this.h != null && this.c != null) {
            try {
                this.c.c(this.i, this.f);
            } catch (Exception e) {
                try {
                    this.c.a(this.i, this.f);
                } catch (Exception e2) {
                }
            }
        }
    }

    public final void a(j jVar, Handler handler) {
        if (this.c != null) {
            try {
                this.c.b(this.i, this.f);
            } catch (Exception e) {
            }
        }
        this.h = jVar;
        this.g = handler;
        a();
    }

    public final void b(int i) {
        if (this.c != null) {
            try {
                this.c.d(1, i);
            } catch (Exception ignored) {
            }
        }
    }
}
