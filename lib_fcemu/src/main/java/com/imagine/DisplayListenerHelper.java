package com.imagine;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;

public class DisplayListenerHelper {
    private DisplayManager displayManager;

    DisplayListenerHelper(Activity activity) {
        this.displayManager = (DisplayManager) activity.getSystemService(Context.DISPLAY_SERVICE);
        this.displayManager.registerDisplayListener(new DisplayChangeListener(this), null);
    }

    public native void displayChange(int i, int i2);

    public Display getDisplay(int displayId) {
        return this.displayManager.getDisplay(displayId);
    }

    public Display[] getPresentationDisplays() {
        return this.displayManager.getDisplays("android.hardware.display.category.PRESENTATION");
    }
}
