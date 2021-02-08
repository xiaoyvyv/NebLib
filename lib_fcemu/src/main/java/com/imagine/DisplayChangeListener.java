package com.imagine;

import android.hardware.display.DisplayManager.DisplayListener;

final class DisplayChangeListener implements DisplayListener {
    private final static int DisplayAdded = 0;
    private final static int DisplayChanged = 1;
    private final static int DisplayRemoved = 2;
    private DisplayListenerHelper listenerHelper;

    DisplayChangeListener(DisplayListenerHelper displayListenerHelper) {
        this.listenerHelper = displayListenerHelper;
    }

    public final void onDisplayAdded(int displayId) {
        this.listenerHelper.displayChange(displayId, DisplayAdded);
    }

    public final void onDisplayChanged(int displayId) {
        this.listenerHelper.displayChange(displayId, DisplayChanged);
    }

    public final void onDisplayRemoved(int displayId) {
        this.listenerHelper.displayChange(displayId, DisplayRemoved);
    }
}
