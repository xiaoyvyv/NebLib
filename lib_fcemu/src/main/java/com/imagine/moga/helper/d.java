package com.imagine.moga.helper;

final class d extends l {
    private /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void a(t tVar) {
        if (tVar.b == 1 && this.a.h != null) {
            Runnable fVar = new f(this.a, tVar);
            if (this.a.g != null) {
                this.a.g.post(fVar);
            } else {
                fVar.run();
            }
        }
    }

    public final void a(v vVar) {
        if (vVar.b == 1 && this.a.h != null) {
            g gVar = new g(this.a, vVar);
            if (this.a.g != null) {
                this.a.g.post(gVar);
            } else {
                gVar.run();
            }
        }
    }

    public final void a(x xVar) {
        if (xVar.b == 1 && this.a.h != null) {
            i iVar = new i(this.a, xVar);
            if (this.a.g != null) {
                this.a.g.post(iVar);
            } else {
                iVar.run();
            }
        }
    }
}
