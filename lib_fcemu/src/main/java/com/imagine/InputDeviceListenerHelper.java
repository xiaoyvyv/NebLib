package com.imagine;

import android.app.Activity;
import android.content.Context;
import android.hardware.input.InputManager;

final class InputDeviceListenerHelper {

    InputDeviceListenerHelper(Activity activity) {
        InputManager systemService = (InputManager) activity.getSystemService(Context.INPUT_SERVICE);
        if (systemService != null) {
            //注册一个输入设备接入监听
            InputDeviceListener inputDeviceListener = new InputDeviceListener(this);
            systemService.registerInputDeviceListener(inputDeviceListener, null);
        }
    }

    public native void deviceChange(int deviceId, int event);
}
