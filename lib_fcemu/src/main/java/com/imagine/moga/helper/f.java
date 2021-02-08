package com.imagine.moga.helper;

final class f implements Runnable {
    private t a;
    private /* synthetic */ c b;

    public f(c cVar, t tVar) {
        this.b = cVar;
        this.a = tVar;
    }

    public final void run() {
        if (this.b.h != null) {
            this.b.h.a(this.a);
        }
    }
}
