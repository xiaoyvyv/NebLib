package com.imagine.moga.helper;

final class g implements Runnable {
    private v a;
    private /* synthetic */ c b;

    public g(c cVar, v vVar) {
        this.b = cVar;
        this.a = vVar;
    }

    public final void run() {
        if (this.b.h != null) {
            this.b.h.a(this.a);
        }
    }
}
