package com.imagine;

import android.app.Activity;
import android.app.Presentation;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback2;
import android.view.Window;

/**
 * 辅助屏幕，有外显设备时，输出画面到外显设备
 */
public class PresentationHelper extends Presentation implements OnDismissListener, Callback2 {
    private NesView nesView;

    PresentationHelper(Activity activity, Display display, long j) {
        super(activity, display);
        setOnDismissListener(this);
        this.nesView = new NesView(activity, j);
    }

    private native void onSurfaceCreated(long j, Surface surface);

    private native void onSurfaceDestroyed(long j);

    private native void onSurfaceRedrawNeeded(long j);

    public final void deinit() {
        this.nesView.index = 0;
        dismiss();
    }

    protected final void onCreate(Bundle bundle) {
        Window window = getWindow();
        window.takeSurface(this);
        setContentView(this.nesView);
    }

    public final void onDismiss(DialogInterface dialogInterface) {
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.nesView.index != 0) {
            onSurfaceCreated(this.nesView.index, surfaceHolder.getSurface());
        }
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.nesView.index != 0) {
            onSurfaceDestroyed(this.nesView.index);
        }
    }

    // 需要重新绘制
    public final void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
        if (this.nesView.index != 0) {
            onSurfaceRedrawNeeded(this.nesView.index);
        }
    }
}
